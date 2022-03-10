package com.gsd.sreenidhi.admin.settings.devops;

import java.util.List;

public class DevOpsToolsForm {
	private int ID_DEVOPS_TOOL;
	private String TX_DEVOPS_TOOL_NAME;
	private String TX_DEVOPS_TOOL_IMAGE;
	private String TX_DEVOPS_TOOL_DOWNLOAD_LOC;
	private int TX_PORT;
	private boolean FL_DEVOPS_TOOL_STATUS;
	private String TS_DEVOPS_TOOL;
	private int ID_CATEGORY;
	private String TX_CATEGORY;
	private String TX_CATEGORY_CODE;
	private String TX_CATEGORY_DESCRIPTION;
	private boolean FL_CATEGORY_STATUS;
	private String TS_CATEGORY_TIMESTAMP;
	private List<ToolConfigForm> CONFIG;
	private String TX_CONFIG;
	
	
	public String getTX_DEVOPS_TOOL_NAME() {
		return TX_DEVOPS_TOOL_NAME;
	}
	public void setTX_DEVOPS_TOOL_NAME(String tX_DEVOPS_TOOL_NAME) {
		TX_DEVOPS_TOOL_NAME = tX_DEVOPS_TOOL_NAME;
	}
	public String getTX_DEVOPS_TOOL_IMAGE() {
		return TX_DEVOPS_TOOL_IMAGE;
	}
	public void setTX_DEVOPS_TOOL_IMAGE(String tX_DEVOPS_TOOL_IMAGE) {
		TX_DEVOPS_TOOL_IMAGE = tX_DEVOPS_TOOL_IMAGE;
	}
	public String getTX_DEVOPS_TOOL_DOWNLOAD_LOC() {
		return TX_DEVOPS_TOOL_DOWNLOAD_LOC;
	}
	public void setTX_DEVOPS_TOOL_DOWNLOAD_LOC(String tX_DEVOPS_TOOL_DOWNLOAD_LOC) {
		TX_DEVOPS_TOOL_DOWNLOAD_LOC = tX_DEVOPS_TOOL_DOWNLOAD_LOC;
	}
	public boolean isFL_DEVOPS_TOOL_STATUS() {
		return FL_DEVOPS_TOOL_STATUS;
	}
	public void setFL_DEVOPS_TOOL_STATUS(boolean fL_DEVOPS_TOOL_STATUS) {
		FL_DEVOPS_TOOL_STATUS = fL_DEVOPS_TOOL_STATUS;
	}
	public String getTS_DEVOPS_TOOL() {
		return TS_DEVOPS_TOOL;
	}
	public void setTS_DEVOPS_TOOL(String tS_DEVOPS_TOOL) {
		TS_DEVOPS_TOOL = tS_DEVOPS_TOOL;
	}
	public int getID_CATEGORY() {
		return ID_CATEGORY;
	}
	public void setID_CATEGORY(int iD_CATEGORY) {
		ID_CATEGORY = iD_CATEGORY;
	}
	public String getTX_CATEGORY() {
		return TX_CATEGORY;
	}
	public void setTX_CATEGORY(String tX_CATEGORY) {
		TX_CATEGORY = tX_CATEGORY;
	}
	public String getTX_CATEGORY_CODE() {
		return TX_CATEGORY_CODE;
	}
	public void setTX_CATEGORY_CODE(String tX_CATEGORY_CODE) {
		TX_CATEGORY_CODE = tX_CATEGORY_CODE;
	}

	public boolean isFL_CATEGORY_STATUS() {
		return FL_CATEGORY_STATUS;
	}
	public void setFL_CATEGORY_STATUS(boolean fL_CATEGORY_STATUS) {
		FL_CATEGORY_STATUS = fL_CATEGORY_STATUS;
	}
	public String getTS_CATEGORY_TIMESTAMP() {
		return TS_CATEGORY_TIMESTAMP;
	}
	public void setTS_CATEGORY_TIMESTAMP(String tS_CATEGORY_TIMESTAMP) {
		TS_CATEGORY_TIMESTAMP = tS_CATEGORY_TIMESTAMP;
	}
	public int getID_DEVOPS_TOOL() {
		return ID_DEVOPS_TOOL;
	}
	public void setID_DEVOPS_TOOL(int iD_DEVOPS_TOOL) {
		ID_DEVOPS_TOOL = iD_DEVOPS_TOOL;
	}
	public String getTX_CATEGORY_DESCRIPTION() {
		return TX_CATEGORY_DESCRIPTION;
	}
	public void setTX_CATEGORY_DESCRIPTION(String tX_CATEGORY_DESCRIPTION) {
		TX_CATEGORY_DESCRIPTION = tX_CATEGORY_DESCRIPTION;
	}
	public int getTX_PORT() {
		return TX_PORT;
	}
	public void setTX_PORT(int tX_PORT) {
		TX_PORT = tX_PORT;
	}
	public List<ToolConfigForm> getCONFIG() {
		return CONFIG;
	}
	public void setCONFIG(List<ToolConfigForm> cONFIG) {
		CONFIG = cONFIG;
	}
	public String getTX_CONFIG() {
		return TX_CONFIG;
	}
	public void setTX_CONFIG(String tX_CONFIG) {
		TX_CONFIG = tX_CONFIG;
	}
	
}
