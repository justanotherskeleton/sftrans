package org.airdrop.test;

import org.airdrop.network.SimpleClient;
import org.airdrop.network.SimpleServer;
import org.airdrop.network.Network;

public class SimpleMessageTest {
	
	SimpleClient client;
	SimpleServer server;
	
	public static void main(String args[]) {
		//args: 0=am i host?
		
		//boolean isHost = Boolean.parseBoolean(args[0]);
		boolean isHost = true;
		new SimpleMessageTest().start(isHost);
	}
	
	public void start(boolean isHost) {
		client = new SimpleClient();
		
		if(isHost) {
			server = new SimpleServer(Network.TCP_PORT, Network.UDP_PORT);
			server.register(Network.BASE_KRYO);
			server.listen();
		}
		
		//System.out.println("not stalling");
		
		client.connectTo(server.getIp(), Network.TCP_PORT, Network.UDP_PORT);
		client.register(Network.BASE_KRYO);
	}

}
