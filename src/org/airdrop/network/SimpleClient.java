package org.airdrop.network;

import java.io.IOException;
import java.util.LinkedList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class SimpleClient {
	
	Client client;
	Kryo kryo;
	
	public SimpleClient() {
		client = new Client();
		kryo = client.getKryo();
		client.start();
		log("Client online, awaiting commands...");
	}
	
	public boolean connectTo(String ip, int tcpPort, int udpPort) {
		try {
			client.connect(5000, ip, tcpPort, udpPort);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		log("Client connected to " + ip + " using tcp/" + tcpPort + " udp/" + udpPort);
		return true;
	}
	
	public void log(String msg) {
		DateTime dt = new DateTime();
		DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
		System.out.println("[" + dtf.print(dt) + "] " + msg); 
	}
	
	public void listen() {
		  client.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		    	   
		    	  if (!(object instanceof Packet)) {
			          log("Unregcoginzed data presented on socket");
			      }
		    	   
		          if(object instanceof Packet) {
		        	  //deal with packet
		          }
		       }
		    });
	}
	
	public boolean register(Class object) { //add error checking
		kryo.register(object);
		log("Registered " + object.getCanonicalName() + " with Kryo serial agent");
		return true;
	}

}
