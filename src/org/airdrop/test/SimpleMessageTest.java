package org.airdrop.test;

import org.airdrop.network.SimpleClient;
import org.airdrop.network.SimpleServer;
import org.airdrop.network.Network;

public class SimpleMessageTest {
	
	SimpleClient client;
	SimpleServer server;
	
	public static void main(String args[]) {
		//args: 0=am i host?
		
		boolean isHost = Boolean.parseBoolean(args[0]);
		new SimpleMessageTest().start(isHost);
	}
	
	public void start(boolean isHost) {
		client = new SimpleClient();
		
		if(isHost) {
			server = new SimpleServer(Network.TCP_PORT, Network.UDP_PORT);
		}
	}

}
