package org.airdrop.network;

public class DataBlock implements Packet{
	
	public byte[] packagedData;
	public byte id;
	
	public DataBlock(byte[] packagedData, byte id) {
		this.packagedData = packagedData;
		this.id = id;
	}
	
	public DataBlock() {
		//alternate without arguments
	}

	public byte[] getPackagedData() {
		return packagedData;
	}

	public void setPackagedData(byte[] packagedData) {
		this.packagedData = packagedData;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}
	
}
