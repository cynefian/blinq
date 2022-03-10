package com.gsd.sreenidhi.user;

public class AccountSettingForm {
	
	private String tx_firstname;
	private String tx_lastname;
	private int id;
	private String tx_email;
	private String tx_contact_num;
	private String tx_website;
	private String tx_bio;
	private String TX_IMAGE;
	private boolean FL_2FA;
	private String TX_2FA;
	private String TS_PWD_UPDATE;
	
	public String getTx_firstname() {
		return tx_firstname;
	}
	public void setTx_firstname(String tx_firstname) {
		this.tx_firstname = tx_firstname;
	}
	public String getTx_lastname() {
		return tx_lastname;
	}
	public void setTx_lastname(String tx_lastname) {
		this.tx_lastname = tx_lastname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTx_email() {
		return tx_email;
	}
	public void setTx_email(String tx_email) {
		this.tx_email = tx_email;
	}
	public String getTx_contact_num() {
		return tx_contact_num;
	}
	public void setTx_contact_num(String tx_contact_num) {
		this.tx_contact_num = tx_contact_num;
	}
	public String getTx_website() {
		return tx_website;
	}
	public void setTx_website(String tx_website) {
		this.tx_website = tx_website;
	}
	public String getTx_bio() {
		return tx_bio;
	}
	public void setTx_bio(String tx_bio) {
		this.tx_bio = tx_bio;
	}
	public String getTX_IMAGE() {
		return TX_IMAGE;
	}
	public void setTX_IMAGE(String tX_IMAGE) {
		TX_IMAGE = tX_IMAGE;
	}
	public boolean isFL_2FA() {
		return FL_2FA;
	}
	public void setFL_2FA(boolean fL_2FA) {
		FL_2FA = fL_2FA;
	}
	public String getTS_PWD_UPDATE() {
		return TS_PWD_UPDATE;
	}
	public void setTS_PWD_UPDATE(String tS_PWD_UPDATE) {
		TS_PWD_UPDATE = tS_PWD_UPDATE;
	}
	public String getTX_2FA() {
		return TX_2FA;
	}
	public void setTX_2FA(String tX_2FA) {
		TX_2FA = tX_2FA;
	}

}
