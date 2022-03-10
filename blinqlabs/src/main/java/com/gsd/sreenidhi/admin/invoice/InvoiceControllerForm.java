package com.gsd.sreenidhi.admin.invoice;

public class InvoiceControllerForm {
	
	private String SYSTEM_MESSAGE = "There is a new Invoice uploaded to  your account. <br/><br/> You can view it in the <a href=\"{ahref}\">Invoices</a> page.";
	
	private String EMAIL_MESSAGE = "There's a new invoice waiting for you at our portal. <br/><br/>"
			+ "Please <a href=\"https://blinqlabs.com/authenticate\">login</a> to view.";
	
	private String image = "";

	public String getSYSTEM_MESSAGE() {
		return SYSTEM_MESSAGE;
	}

	public void setSYSTEM_MESSAGE(String sYSTEM_MESSAGE) {
		SYSTEM_MESSAGE = sYSTEM_MESSAGE;
	}

	public String getEMAIL_MESSAGE() {
		return EMAIL_MESSAGE;
	}

	public void setEMAIL_MESSAGE(String eMAIL_MESSAGE) {
		EMAIL_MESSAGE = eMAIL_MESSAGE;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
