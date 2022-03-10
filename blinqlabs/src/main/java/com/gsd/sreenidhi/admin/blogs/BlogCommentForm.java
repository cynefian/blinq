package com.gsd.sreenidhi.admin.blogs;

public class BlogCommentForm {
	private int ID_BLOG;
	private int  ID_COMMENT;
	private String TX_COMMENT_MESSAGE;
	private int DEPTH;
	private int COUNT_LIKES;
	private String TS_CREATE;
	private boolean FL_EDITED;
	private int ID_SYS_USER;
	private String TX_NAME;
	private  String TX_EMAIL;
	private int ID_PARENT;
	private boolean FL_SYSTEM_USER;
	private String USR_IMAGE;
	
	public int getID_BLOG() {
		return ID_BLOG;
	}
	public void setID_BLOG(int iD_BLOG) {
		ID_BLOG = iD_BLOG;
	}
	public int getID_COMMENT() {
		return ID_COMMENT;
	}
	public void setID_COMMENT(int iD_COMMENT) {
		ID_COMMENT = iD_COMMENT;
	}
	public String getTX_COMMENT_MESSAGE() {
		return TX_COMMENT_MESSAGE;
	}
	public void setTX_COMMENT_MESSAGE(String tX_COMMENT_MESSAGE) {
		TX_COMMENT_MESSAGE = tX_COMMENT_MESSAGE;
	}
	public int getDEPTH() {
		return DEPTH;
	}
	public void setDEPTH(int dEPTH) {
		DEPTH = dEPTH;
	}
	public int getCOUNT_LIKES() {
		return COUNT_LIKES;
	}
	public void setCOUNT_LIKES(int cOUNT_LIKES) {
		COUNT_LIKES = cOUNT_LIKES;
	}
	public String getTS_CREATE() {
		return TS_CREATE;
	}
	public void setTS_CREATE(String tS_CREATE) {
		TS_CREATE = tS_CREATE;
	}
	public boolean isFL_EDITED() {
		return FL_EDITED;
	}
	public void setFL_EDITED(boolean fL_EDITED) {
		FL_EDITED = fL_EDITED;
	}
	public int getID_SYS_USER() {
		return ID_SYS_USER;
	}
	public void setID_SYS_USER(int iD_SYS_USER) {
		ID_SYS_USER = iD_SYS_USER;
	}
	public String getTX_NAME() {
		return TX_NAME;
	}
	public void setTX_NAME(String tX_NAME) {
		TX_NAME = tX_NAME;
	}
	public String getTX_EMAIL() {
		return TX_EMAIL;
	}
	public void setTX_EMAIL(String tX_EMAIL) {
		TX_EMAIL = tX_EMAIL;
	}
	public int getID_PARENT() {
		return ID_PARENT;
	}
	public void setID_PARENT(int i) {
		ID_PARENT = i;
	}
	public boolean isFL_SYSTEM_USER() {
		return FL_SYSTEM_USER;
	}
	public void setFL_SYSTEM_USER(boolean fL_SYSTEM_USER) {
		FL_SYSTEM_USER = fL_SYSTEM_USER;
	}
	public String getUSR_IMAGE() {
		return USR_IMAGE;
	}
	public void setUSR_IMAGE(String uSR_IMAGE) {
		USR_IMAGE = uSR_IMAGE;
	}
	
	
}
