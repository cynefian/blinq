package com.gsd.sreenidhi.admin.settings.devops;

public class DevOpsCategoriesForm {
	
	private int ID ;
	private String TX_CATEGORY;
	private String TX_CAT_CODE;
	private String 	TX_DESCRIPTION;
	private boolean	FL_STATUS ;
	private String 	TS_TIMESTAMP;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTX_CATEGORY() {
		return TX_CATEGORY;
	}
	public void setTX_CATEGORY(String tX_CATEGORY) {
		TX_CATEGORY = tX_CATEGORY;
	}
	public String getTX_CAT_CODE() {
		return TX_CAT_CODE;
	}
	public void setTX_CAT_CODE(String tX_CAT_CODE) {
		TX_CAT_CODE = tX_CAT_CODE;
	}
	public String getTX_DESCRIPTION() {
		return TX_DESCRIPTION;
	}
	public void setTX_DESCRIPTION(String tX_DESCRIPTION) {
		TX_DESCRIPTION = tX_DESCRIPTION;
	}
	public boolean isFL_STATUS() {
		return FL_STATUS;
	}
	public void setFL_STATUS(boolean fL_STATUS) {
		FL_STATUS = fL_STATUS;
	}
	public String getTS_TIMESTAMP() {
		return TS_TIMESTAMP;
	}
	public void setTS_TIMESTAMP(String tS_TIMESTAMP) {
		TS_TIMESTAMP = tS_TIMESTAMP;
	}

}
