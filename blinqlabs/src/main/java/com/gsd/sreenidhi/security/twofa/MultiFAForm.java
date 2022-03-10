package com.gsd.sreenidhi.security.twofa;

public class MultiFAForm {
	private String TX_MFA_MESSAGE = "<table width=\"100%\"> \r\n" + 
			"<tbody> \r\n" + 
			"<tr> \r\n" + 
			"<td>\r\n" + 
			"  As per your request, below is the temp authentication code. \r\n" + 
			"  <br/>\r\n" + 
			"  <br/>\r\n" + 
			"  <b>{secret}</b>\r\n" + 
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
	
	private String image = "";

	public String getTX_MFA_MESSAGE() {
		return TX_MFA_MESSAGE;
	}

	public void setTX_MFA_MESSAGE(String tX_MFA_MESSAGE) {
		TX_MFA_MESSAGE = tX_MFA_MESSAGE;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

}
