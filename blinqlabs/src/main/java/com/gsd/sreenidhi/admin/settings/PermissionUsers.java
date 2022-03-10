package com.gsd.sreenidhi.admin.settings;

import java.util.List;

public class PermissionUsers {
	
	private String PERMISSION_TYPE;
	private int ID_GROUP;
	private String TX_GROUP_NAME;
	private int ID_USER;
	private String TX_FIRSTNAME;
	private String TX_LASTNAME;
	private String TX_IMAGE;
	private String TX_EMAIL;
	private List<String> TYPES;
	
	
	public String getPERMISSION_TYPE() {
		return PERMISSION_TYPE;
	}
	public void setPERMISSION_TYPE(String pERMISSION_TYPE) {
		PERMISSION_TYPE = pERMISSION_TYPE;
	}
	public int getID_GROUP() {
		return ID_GROUP;
	}
	public void setID_GROUP(int iD_GROUP) {
		ID_GROUP = iD_GROUP;
	}
	public String getTX_GROUP_NAME() {
		return TX_GROUP_NAME;
	}
	public void setTX_GROUP_NAME(String tX_GROUP_NAME) {
		TX_GROUP_NAME = tX_GROUP_NAME;
	}
	public int getID_USER() {
		return ID_USER;
	}
	public void setID_USER(int iD_USER) {
		ID_USER = iD_USER;
	}
	public String getTX_FIRSTNAME() {
		return TX_FIRSTNAME;
	}
	public void setTX_FIRSTNAME(String tX_FIRSTNAME) {
		TX_FIRSTNAME = tX_FIRSTNAME;
	}
	public String getTX_LASTNAME() {
		return TX_LASTNAME;
	}
	public void setTX_LASTNAME(String tX_LASTNAME) {
		TX_LASTNAME = tX_LASTNAME;
	}
	public String getTX_IMAGE() {
		return TX_IMAGE;
	}
	public void setTX_IMAGE(String tX_IMAGE) {
		TX_IMAGE = tX_IMAGE;
	}
	public String getTX_EMAIL() {
		return TX_EMAIL;
	}
	public void setTX_EMAIL(String tX_EMAIL) {
		TX_EMAIL = tX_EMAIL;
	}
	public List<String> getTYPES() {
		return TYPES;
	}
	public void setTYPES(List<String> tYPES) {
		TYPES = tYPES;
	}

}
