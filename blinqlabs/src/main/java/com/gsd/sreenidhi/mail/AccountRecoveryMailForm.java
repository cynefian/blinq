package com.gsd.sreenidhi.mail;

public class AccountRecoveryMailForm {
	
	private String recoveryMessage = "			<table width=\"100%\">  \r\n" + 
			"			<tbody>  \r\n" + 
			"			<tr>  \r\n" + 
			"			<td>You are receiving this email because you requested for account recovery through the password reset process on our website.</td>  \r\n" + 
			"			</tr>  \r\n" + 
			"			<tr>  \r\n" + 
			"			<td>&nbsp;</td>  \r\n" + 
			"			</tr>  \r\n" + 
			"              <tr>  \r\n" + 
			"			<td>Your secret code to recover your account is <b>{code}</b> <br/>"
			+ "           The code is valid only for <u>30 minutes</u> from the time of request. </td>  \r\n" + 
			"			</tr>  \r\n" + 
			"              <tr>  \r\n" + 
			"			<td>&nbsp;</td>  \r\n" + 
			"			</tr>  \r\n" + 
			"			<tr>  \r\n" + 
			"			<td>If you believe you received this email in error or if you suspect your account to be compromized, please change your password immediately and inform our support team so we can further look into the cause.</td>  \r\n" + 
			"			</tr>  \r\n" + 
			"			<tr>  \r\n" + 
			"			<td>&nbsp;</td>  \r\n" + 
			"			</tr>  \r\n" + 
			"             <tr>  \r\n" + 
			"			<td>As always, we recommend you to enable Two-Factor-Authentication (2FA) on your account for additional security.</td>  \r\n" + 
			"			</tr>\r\n" + 
			"              <tr>  \r\n" + 
			"			<td>&nbsp;</td>  \r\n" + 
			"			</tr>  \r\n" + 
			"              <tr>  \r\n" + 
			"			<td> "
			+ "          You can also click on the following link to recover your account:"
			+"            {link} "
			+ "          </td>  \r\n" + 
			"			</tr>  \r\n" + 
			"              <tr>  \r\n" + 
			"			<td>&nbsp;</td>  \r\n" + 
			"			</tr>  \r\n" + 
			"			</tbody>  \r\n" + 
			"			</table>  \r\n" ;

	public String getRecoveryMessage() {
		return recoveryMessage;
	}

	public void setRecoveryMessage(String recoveryMessage) {
		this.recoveryMessage = recoveryMessage;
	}
	
	
}
