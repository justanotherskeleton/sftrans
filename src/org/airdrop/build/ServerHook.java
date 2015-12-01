package org.airdrop.build;
import org.airdrop.network.*;

public class ServerHook {
	
	private SimpleServer SERVER;
	
	public static void main(String[] args) {
		new ServerHook().hook();
	}
	
	public void hook() {
		SERVER = new SimpleServer(Network.TCP_PORT, Network.UDP_PORT);
		SERVER.listen();
	}

}
