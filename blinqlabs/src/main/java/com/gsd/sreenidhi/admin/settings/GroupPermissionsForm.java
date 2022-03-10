package com.gsd.sreenidhi.admin.settings;

public class GroupPermissionsForm {
	private int ID_GROUP;
	private String TX_GROUP_NAME;
	private int ID_PERM_SECTION;
	private String TX_PERM_SECTION;
	private int ID_PERMISSION;
	private String TX_PERMISSION;
	private String TX_DESCRIPTION;
	private boolean	FL_STATUS;
	private int GROUP_USER_COUNT;
	
	public String getTX_GROUP_NAME() {
		return TX_GROUP_NAME;
	}
	public void setTX_GROUP_NAME(String tX_GROUP_NAME) {
		TX_GROUP_NAME = tX_GROUP_NAME;
	}
	public String getTX_PERMISSION() {
		return TX_PERMISSION;
	}
	public void setTX_PERMISSION(String tX_PERMISSION) {
		TX_PERMISSION = tX_PERMISSION;
	}
	public boolean isFL_STATUS() {
		return FL_STATUS;
	}
	public void setFL_STATUS(boolean fL_STATUS) {
		FL_STATUS = fL_STATUS;
	}
	public String getTX_DESCRIPTION() {
		return TX_DESCRIPTION;
	}
	public void setTX_DESCRIPTION(String tX_DESCRIPTION) {
		TX_DESCRIPTION = tX_DESCRIPTION;
	}
	public int getID_PERMISSION() {
		return ID_PERMISSION;
	}
	public void setID_PERMISSION(int iD_PERMISSION) {
		ID_PERMISSION = iD_PERMISSION;
	}
	public int getID_GROUP() {
		return ID_GROUP;
	}
	public void setID_GROUP(int iD_GROUP) {
		ID_GROUP = iD_GROUP;
	}
	public int getID_PERM_SECTION() {
		return ID_PERM_SECTION;
	}
	public void setID_PERM_SECTION(int iD_PERM_SECTION) {
		ID_PERM_SECTION = iD_PERM_SECTION;
	}
	public String getTX_PERM_SECTION() {
		return TX_PERM_SECTION;
	}
	public void setTX_PERM_SECTION(String tX_PERM_SECTION) {
		TX_PERM_SECTION = tX_PERM_SECTION;
	}
	public int getGROUP_USER_COUNT() {
		return GROUP_USER_COUNT;
	}
	public void setGROUP_USER_COUNT(int gROUP_USER_COUNT) {
		GROUP_USER_COUNT = gROUP_USER_COUNT;
	}
}
