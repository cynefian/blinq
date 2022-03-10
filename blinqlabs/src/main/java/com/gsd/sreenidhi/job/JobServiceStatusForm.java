package com.gsd.sreenidhi.job;

public class JobServiceStatusForm {
	private int ID_TOOL;
	private String TX_TOOL;
	private int ID_SRVC;
	private String TX_SRVC_KEY;
	private String TX_STATUS;
	private int ID_ORDER;
	private boolean FL_INIT;
	private boolean FL_COMPLETE;
	private String TS_COMPELTE;
	

	public String getTX_SRVC_KEY() {
		return TX_SRVC_KEY;
	}
	public void setTX_SRVC_KEY(String tX_SRVC_KEY) {
		TX_SRVC_KEY = tX_SRVC_KEY;
	}
	public String getTX_STATUS() {
		return TX_STATUS;
	}
	public void setTX_STATUS(String tX_STATUS) {
		TX_STATUS = tX_STATUS;
	}
	public int getID_ORDER() {
		return ID_ORDER;
	}
	public void setID_ORDER(int iD_ORDER) {
		ID_ORDER = iD_ORDER;
	}
	public boolean isFL_COMPLETE() {
		return FL_COMPLETE;
	}
	public void setFL_COMPLETE(boolean fL_COMPLETE) {
		FL_COMPLETE = fL_COMPLETE;
	}
	public String getTS_COMPELTE() {
		return TS_COMPELTE;
	}
	public void setTS_COMPELTE(String tS_COMPELTE) {
		TS_COMPELTE = tS_COMPELTE;
	}
	public boolean isFL_INIT() {
		return FL_INIT;
	}
	public void setFL_INIT(boolean fL_INIT) {
		FL_INIT = fL_INIT;
	}
	public int getID_TOOL() {
		return ID_TOOL;
	}
	public void setID_TOOL(int iD_TOOL) {
		ID_TOOL = iD_TOOL;
	}
	public String getTX_TOOL() {
		return TX_TOOL;
	}
	public void setTX_TOOL(String tX_TOOL) {
		TX_TOOL = tX_TOOL;
	}
	public int getID_SRVC() {
		return ID_SRVC;
	}
	public void setID_SRVC(int iD_SRVC) {
		ID_SRVC = iD_SRVC;
	}
}
