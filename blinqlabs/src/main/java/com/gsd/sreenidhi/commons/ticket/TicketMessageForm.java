package com.gsd.sreenidhi.commons.ticket;

public class TicketMessageForm {

	private String EMAIL_MESSAGE = "There's a message waiting for you at our portal. <br/><br/>"
			+ "Please <a href=\"https://blinqlabs.com/authenticate\">login</a> to view your message.";
	private String SYSTEM_MESSAGE = "There is a new Update to your <a href=\"{a-href}\" >ticket</a>";
	private String image = "";
	
	public String getEMAIL_MESSAGE() {
		return EMAIL_MESSAGE;
	}
	public void setEMAIL_MESSAGE(String eMAIL_MESSAGE) {
		EMAIL_MESSAGE = eMAIL_MESSAGE;
	}
	public String getSYSTEM_MESSAGE() {
		return SYSTEM_MESSAGE;
	}
	public void setSYSTEM_MESSAGE(String sYSTEM_MESSAGE) {
		SYSTEM_MESSAGE = sYSTEM_MESSAGE;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}