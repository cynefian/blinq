package com.gsd.sreenidhi.admin.settings;

import java.util.List;

public class UserPermissionGroupLvlForm {
	
	private int ID_PERMISSION;
	private String TX_PERMISSION;
	private List<GroupPermissionsForm> LIST_GROUP;
	
	
	public int getID_PERMISSION() {
		return ID_PERMISSION;
	}
	public void setID_PERMISSION(int iD_PERMISSION) {
		ID_PERMISSION = iD_PERMISSION;
	}
	public String getTX_PERMISSION() {
		return TX_PERMISSION;
	}
	public void setTX_PERMISSION(String tX_PERMISSION) {
		TX_PERMISSION = tX_PERMISSION;
	}
	public List<GroupPermissionsForm> getLIST_GROUP() {
		return LIST_GROUP;
	}
	public void setLIST_GROUP(List<GroupPermissionsForm> lIST_GROUP) {
		LIST_GROUP = lIST_GROUP;
	}
	
	
}
