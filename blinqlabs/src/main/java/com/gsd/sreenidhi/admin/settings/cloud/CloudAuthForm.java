package com.gsd.sreenidhi.admin.settings.cloud;

public class CloudAuthForm {
	private int ID_CLOUD_AUTH;
	private String TX_IDENTIFIER;
	private String TX_ACCESS_KEY;
	private String TX_ACCESS_SECRET;
	private String TS_TIMESTAMP;
	
	
	public String getTX_IDENTIFIER() {
		return TX_IDENTIFIER;
	}
	public void setTX_IDENTIFIER(String tX_IDENTIFIER) {
		TX_IDENTIFIER = tX_IDENTIFIER;
	}
	public String getTX_ACCESS_KEY() {
		return TX_ACCESS_KEY;
	}
	public void setTX_ACCESS_KEY(String tX_ACCESS_KEY) {
		TX_ACCESS_KEY = tX_ACCESS_KEY;
	}
	public String getTX_ACCESS_SECRET() {
		return TX_ACCESS_SECRET;
	}
	public void setTX_ACCESS_SECRET(String tX_ACCESS_SECRET) {
		TX_ACCESS_SECRET = tX_ACCESS_SECRET;
	}
	public String getTS_TIMESTAMP() {
		return TS_TIMESTAMP;
	}
	public void setTS_TIMESTAMP(String tS_TIMESTAMP) {
		TS_TIMESTAMP = tS_TIMESTAMP;
	}
	public int getID_CLOUD_AUTH() {
		return ID_CLOUD_AUTH;
	}
	public void setID_CLOUD_AUTH(int iD_CLOUD_AUTH) {
		ID_CLOUD_AUTH = iD_CLOUD_AUTH;
	}
	
}
