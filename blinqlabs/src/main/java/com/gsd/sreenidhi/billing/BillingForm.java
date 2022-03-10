package com.gsd.sreenidhi.billing;

public class BillingForm {
	

	private String EMAIL_MESSAGE = "There's a new Service report waiting for you at our portal. <br/><br/>"
			+ "Please <a href=\"https://blinqlabs.com/authenticate\">login</a> to view your message.";
	
	private String SYSTEM_MESSAGE = "There is a new report generated for your account. You can check the report at the Attachments tabs of the Service Entitlement Details page.";
	
	private String GENERIC_EMAIL_MESSAGE = "There's a new message waiting for you at our portal. <br/><br/>"
			+ "Please <a href=\"https://blinqlabs.com/authenticate\">login</a> to view your message.";
	
	private String EXPIRED_ROLLOVER_MESSAGE = "Looks like some of your unused hours have expired.";
	
	private String EXPIRED_SEN_MESSAGE = "Your Service Entitlement is no longer active. Please contact your Coordinator for next steps.";
	
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

	public String getGENERIC_EMAIL_MESSAGE() {
		return GENERIC_EMAIL_MESSAGE;
	}

	public void setGENERIC_EMAIL_MESSAGE(String gENERIC_EMAIL_MESSAGE) {
		GENERIC_EMAIL_MESSAGE = gENERIC_EMAIL_MESSAGE;
	}

	public String getEXPIRED_ROLLOVER_MESSAGE() {
		return EXPIRED_ROLLOVER_MESSAGE;
	}

	public void setEXPIRED_ROLLOVER_MESSAGE(String eXPIRED_ROLLOVER_MESSAGE) {
		EXPIRED_ROLLOVER_MESSAGE = eXPIRED_ROLLOVER_MESSAGE;
	}

	public String getEXPIRED_SEN_MESSAGE() {
		return EXPIRED_SEN_MESSAGE;
	}

	public void setEXPIRED_SEN_MESSAGE(String eXPIRED_SEN_MESSAGE) {
		EXPIRED_SEN_MESSAGE = eXPIRED_SEN_MESSAGE;
	}

}
