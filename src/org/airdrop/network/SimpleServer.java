package org.airdrop.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
	String ip, id;
	
	public SimpleServer(int tcp, int udp) {
		server = new Server();
		kryo = server.getKryo();
		server.start();
		String ip = AWSgetIp();
		this.ip = ip;
		log("IP assigned: " + this.ip);
		
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
	
	public String AWSgetIp() {
        URL whatismyip = null;
		try {
			whatismyip = new URL("http://checkip.amazonaws.com");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return null;
    }
	
	public String getIp() {
		return this.ip;
	}
	
	public void register(Class object) { //add error checking
		kryo.register(object);
		log("Registered " + object.getCanonicalName() + " with Kryo serial agent");
		
	}
	
	public void register(Class[] objects) {
		for(Class c : objects) {
			kryo.register(c);
			log("Registered " + c.getCanonicalName() + " with Kryo serial agent");
		}
	}

}
