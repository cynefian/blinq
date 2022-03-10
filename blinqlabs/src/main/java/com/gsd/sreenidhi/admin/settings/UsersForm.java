package com.gsd.sreenidhi.admin.settings;

import java.util.List;

public class UsersForm {
	
	private int ID_USER;
	private String TX_FIRSTNAME;
	private String TX_LASTNAME;
	private String TX_EMAIL;
	private String TX_WEBSITE;
	private String TX_BIO;
	private String TX_CONTACT_NUM;
	private String TX_USER_IMAGE;
	private String password;
	private boolean FL_ENABLED;
	private List<GroupPermissionsForm> USER_GROUPS;
	
	
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
	public String getTX_EMAIL() {
		return TX_EMAIL;
	}
	public void setTX_EMAIL(String tX_EMAIL) {
		TX_EMAIL = tX_EMAIL;
	}
	public String getTX_WEBSITE() {
		return TX_WEBSITE;
	}
	public void setTX_WEBSITE(String tX_WEBSITE) {
		TX_WEBSITE = tX_WEBSITE;
	}
	public String getTX_BIO() {
		return TX_BIO;
	}
	public void setTX_BIO(String tX_BIO) {
		TX_BIO = tX_BIO;
	}
	public String getTX_CONTACT_NUM() {
		return TX_CONTACT_NUM;
	}
	public void setTX_CONTACT_NUM(String tX_CONTACT_NUM) {
		TX_CONTACT_NUM = tX_CONTACT_NUM;
	}
	public String getTX_USER_IMAGE() {
		return TX_USER_IMAGE;
	}
	public void setTX_USER_IMAGE(String tX_USER_IMAGE) {
		TX_USER_IMAGE = tX_USER_IMAGE;
	}
	public List<GroupPermissionsForm> getUSER_GROUPS() {
		return USER_GROUPS;
	}
	public void setUSER_GROUPS(List<GroupPermissionsForm> uSER_GROUPS) {
		USER_GROUPS = uSER_GROUPS;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isFL_ENABLED() {
		return FL_ENABLED;
	}
	public void setFL_ENABLED(boolean fL_ENABLED) {
		FL_ENABLED = fL_ENABLED;
	}

}
