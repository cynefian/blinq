package com.gsd.sreenidhi.admin.services;

public class ServiceTypeForm {

	private int ID;
	private String TX_SERVICE_TYPE_NAME;
	private String TX_SERVICE_TYPE_DESCRIPTION;
	private boolean FL_ALLOW_ROLLOVER;
	private boolean FL_MONTHLY_BILLING;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTX_SERVICE_TYPE_NAME() {
		return TX_SERVICE_TYPE_NAME;
	}
	public void setTX_SERVICE_TYPE_NAME(String tX_SERVICE_TYPE_NAME) {
		TX_SERVICE_TYPE_NAME = tX_SERVICE_TYPE_NAME;
	}
	public String getTX_SERVICE_TYPE_DESCRIPTION() {
		return TX_SERVICE_TYPE_DESCRIPTION;
	}
	public void setTX_SERVICE_TYPE_DESCRIPTION(String tX_SERVICE_TYPE_DESCRIPTION) {
		TX_SERVICE_TYPE_DESCRIPTION = tX_SERVICE_TYPE_DESCRIPTION;
	}
	public boolean isFL_ALLOW_ROLLOVER() {
		return FL_ALLOW_ROLLOVER;
	}
	public void setFL_ALLOW_ROLLOVER(boolean fL_ALLOW_ROLLOVER) {
		FL_ALLOW_ROLLOVER = fL_ALLOW_ROLLOVER;
	}
	public boolean isFL_MONTHLY_BILLING() {
		return FL_MONTHLY_BILLING;
	}
	public void setFL_MONTHLY_BILLING(boolean fL_MONTHLY_BILLING) {
		FL_MONTHLY_BILLING = fL_MONTHLY_BILLING;
	}

	
	
}

