package com.gsd.sreenidhi.user;

public class AccountVerificationForm {

	private int ID_USER;
	private boolean FL_EMAIL_VERIFIED;
	private String TX_EMAIL_VERIFIED;
	private String TS_EMAIL_VERIFIED;
	private boolean FL_MOB_VERIFIED;
	private String TX_MOB_VERIFIED;
	private String TS_MOB_VERIFIED;
	private String TX_EMAIL;
	private String TX_CONTACT_NUM;
	private String TX_RETURN_CODE;
	private String image = "";
	
	
	private String TX_EMAIL_VERIFY_CONTENT = "<table width=\"100%\"> \r\n" + 
			"<tbody> \r\n" + 
			"<td>&nbsp;</td> \r\n" + 
			"</tr> \r\n" + 
			"<tr> \r\n" + 
			"<td>\r\n" + 
			"  As per your request, we have initiated your email verification process. \r\n" + 
			" <br/>\r\n" + 
			"  Your secret code for email verification is below.  \r\n" + 
			"  <br/>\r\n" + 
			"  <br/>\r\n" + 
			"  <b> ${secret} </b>\r\n" + 
			"  \r\n" + 
			"  </td> \r\n" + 
			"</tr> \r\n" + 
			"<tr> \r\n" + 
			"<td>&nbsp;</td> \r\n" + 
			"</tr> \r\n" + 
			"<tr> \r\n" + 
			"<td>If you do not recognize this action. Please inform us and change your account password immediately.</td> \r\n" + 
			"</tr> \r\n" + 
			"<tr> \r\n" + 
			"<td>&nbsp;</td> \r\n" + 
			"</tr> \r\n" + 
			"</tbody> \r\n" + 
			"</table> \r\n" ;
	
	public boolean isFL_EMAIL_VERIFIED() {
		return FL_EMAIL_VERIFIED;
	}
	public void setFL_EMAIL_VERIFIED(boolean fL_EMAIL_VERIFIED) {
		FL_EMAIL_VERIFIED = fL_EMAIL_VERIFIED;
	}
	public String getTX_EMAIL_VERIFIED() {
		return TX_EMAIL_VERIFIED;
	}
	public void setTX_EMAIL_VERIFIED(String tX_EMAIL_VERIFIED) {
		TX_EMAIL_VERIFIED = tX_EMAIL_VERIFIED;
	}
	public String getTS_EMAIL_VERIFIED() {
		return TS_EMAIL_VERIFIED;
	}
	public void setTS_EMAIL_VERIFIED(String tS_EMAIL_VERIFIED) {
		TS_EMAIL_VERIFIED = tS_EMAIL_VERIFIED;
	}
	public boolean isFL_MOB_VERIFIED() {
		return FL_MOB_VERIFIED;
	}
	public void setFL_MOB_VERIFIED(boolean fL_MOB_VERIFIED) {
		FL_MOB_VERIFIED = fL_MOB_VERIFIED;
	}
	public String getTX_MOB_VERIFIED() {
		return TX_MOB_VERIFIED;
	}
	public void setTX_MOB_VERIFIED(String tX_MOB_VERIFIED) {
		TX_MOB_VERIFIED = tX_MOB_VERIFIED;
	}
	public String getTS_MOB_VERIFIED() {
		return TS_MOB_VERIFIED;
	}
	public void setTS_MOB_VERIFIED(String tS_MOB_VERIFIED) {
		TS_MOB_VERIFIED = tS_MOB_VERIFIED;
	}
	public String getTX_EMAIL() {
		return TX_EMAIL;
	}
	public void setTX_EMAIL(String tX_EMAIL) {
		TX_EMAIL = tX_EMAIL;
	}
	public String getTX_CONTACT_NUM() {
		return TX_CONTACT_NUM;
	}
	public void setTX_CONTACT_NUM(String tX_CONTACT_NUM) {
		TX_CONTACT_NUM = tX_CONTACT_NUM;
	}
	public int getID_USER() {
		return ID_USER;
	}
	public void setID_USER(int iD_USER) {
		ID_USER = iD_USER;
	}
	public String getTX_EMAIL_VERIFY_CONTENT() {
		return TX_EMAIL_VERIFY_CONTENT;
	}
	public void setTX_EMAIL_VERIFY_CONTENT(String tX_EMAIL_VERIFY_CONTENT) {
		TX_EMAIL_VERIFY_CONTENT = tX_EMAIL_VERIFY_CONTENT;
	}
	public String getTX_RETURN_CODE() {
		return TX_RETURN_CODE;
	}
	public void setTX_RETURN_CODE(String tX_RETURN_CODE) {
		TX_RETURN_CODE = tX_RETURN_CODE;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

	
}
