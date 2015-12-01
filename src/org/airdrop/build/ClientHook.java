package org.airdrop.build;

import org.airdrop.network.SimpleClient;

public class ClientHook {
	
	private SimpleClient CLIENT;
	
	public static void main(String args[]) {
		new ClientHook().hook();
	}
	
	public void hook() {
		CLIENT = new SimpleClient();
		CLIENT.listen();
	}

}
