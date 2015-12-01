package org.airdrop.network;

public class Network {
	
	public static final int TCP_PORT = 37614;
	public static final int UDP_PORT = 37615;
	
	public static final Class[] BASE_KRYO = { IncomingConnection.class, Packet.class, SimpleText.class };

}
