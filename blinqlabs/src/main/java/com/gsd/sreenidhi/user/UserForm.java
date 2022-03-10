package com.gsd.sreenidhi.user;

import java.util.List;

import org.jboss.aerogear.security.otp.api.Base32;

import com.gsd.sreenidhi.admin.settings.GroupPermissionsForm;

public class UserForm {

	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String user_image;
	private String TS_UPDATE;
	private boolean FL_2FA;
	private String TX_2FA;	
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	public String getTS_UPDATE() {
		return TS_UPDATE;
	}
	public void setTS_UPDATE(String tS_UPDATE) {
		TS_UPDATE = tS_UPDATE;
	}
	public boolean isFL_2FA() {
		return FL_2FA;
	}
	public void setFL_2FA(boolean fL_2FA) {
		FL_2FA = fL_2FA;
	}
	public String getTX_2FA() {
		return TX_2FA;
	}
	public void setTX_2FA(String tX_2FA) {
		TX_2FA = tX_2FA;
	}

	
}
