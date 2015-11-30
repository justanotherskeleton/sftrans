package org.airdrop.network;

public class IncomingConnection implements Packet {
	
	public String ip, clientid;
	
	public IncomingConnection(String ip, String clientid) {
		this.ip = ip;
		this.clientid = clientid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

}

