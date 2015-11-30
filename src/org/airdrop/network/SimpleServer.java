package org.airdrop.network;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class SimpleServer {
	
	Server server;
	Kryo kryo;
	
	public SimpleServer(int tcp, int udp) {
		server = new Server();
		kryo = server.getKryo();
		server.start();
		
		try {
			server.bind(tcp, udp);
		} catch (IOException e) {
			e.printStackTrace();
			log("Erroring during server port binding");
		}
		
		
	}
	
	public void log(String msg) {
		DateTime dt = new DateTime();
		DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
		System.out.println("[" + dtf.print(dt) + "] " + msg); 
	}
	
	public void listen() {
		server.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		          if (!(object instanceof Packet)) {
		             log("Unregcoginzed data presented on socket");
		          }
		          
		          if(object instanceof Packet) {
		        	  if(object instanceof IncomingConnection) {
		        		  IncomingConnection ic = (IncomingConnection)object;
		        		  log("Incoming connection made from " + ic.clientid + "@" + ic.ip);
		        		  //ask operator to accept
		        	  }
		        	  
		        	  if(object instanceof DataBlock) {
		        		  server.sendToAllTCP(object); //make this more efficient
		        	  }
		        	  
		        	  if(object instanceof SimpleText) {
		        		  server.sendToAllTCP(object); //this is fine
		        	  }
		          }
		       }
		    });
	}

}
