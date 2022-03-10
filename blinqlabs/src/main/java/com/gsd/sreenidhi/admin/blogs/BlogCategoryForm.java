package com.gsd.sreenidhi.admin.blogs;

public class BlogCategoryForm {
	
	private int ID;
	private String TX_CATEGORY;
	private boolean FL_ACTIVE;
	private int COUNTER;
	
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
	public boolean isFL_ACTIVE() {
		return FL_ACTIVE;
	}
	public void setFL_ACTIVE(boolean fL_ACTIVE) {
		FL_ACTIVE = fL_ACTIVE;
	}
	public int getCOUNTER() {
		return COUNTER;
	}
	public void setCOUNTER(int cOUNTER) {
		COUNTER = cOUNTER;
	}

}
