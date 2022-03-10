package com.gsd.sreenidhi.security.passwordManagement;

public class PasswordManagementForm {
	
	private String email;
	private String currentpassword;
	private String newpassword1;
	private String newpassword2;
	
	private String pwCurrHash;
	private String pwNewHash;
	
	public String getPwCurrHash() {
		return pwCurrHash;
	}
	public void setPwCurrHash(String pwCurrHash) {
		this.pwCurrHash = pwCurrHash;
	}
	public String getPwNewHash() {
		return pwNewHash;
	}
	public void setPwNewHash(String pwNewHash) {
		this.pwNewHash = pwNewHash;
	}
	public String getCurrentpassword() {
		return currentpassword;
	}
	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}
	public String getNewpassword1() {
		return newpassword1;
	}
	public void setNewpassword1(String newpassword1) {
		this.newpassword1 = newpassword1;
	}
	public String getNewpassword2() {
		return newpassword2;
	}
	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
