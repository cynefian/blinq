package com.gsd.sreenidhi.user.messages;

public class UserMessageForm {
	
	int id;
	String TX_SUBJECT;
	String TX_BODY;
	int ID_TO;
	int ID_FROM;
	String TX_FROM_NAME;
	String TX_FROM_USER_IMAGE;
	boolean TX_READ;
	boolean TX_STAR;
	String TX_TIME;
	String TX_ATTACHMENT;
	String TX_FLAG;
	int ID_PRIORITY;
	int ID_PARENT;	
	
	int queryMessageCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTX_SUBJECT() {
		return TX_SUBJECT;
	}
	public void setTX_SUBJECT(String tX_SUBJECT) {
		TX_SUBJECT = tX_SUBJECT;
	}
	public String getTX_BODY() {
		return TX_BODY;
	}
	public void setTX_BODY(String tX_BODY) {
		TX_BODY = tX_BODY;
	}
	public int getID_TO() {
		return ID_TO;
	}
	public void setID_TO(int iD_TO) {
		ID_TO = iD_TO;
	}
	public int getID_FROM() {
		return ID_FROM;
	}
	public void setID_FROM(int iD_FROM) {
		ID_FROM = iD_FROM;
	}
	public String getTX_FROM_NAME() {
		return TX_FROM_NAME;
	}
	public void setTX_FROM_NAME(String tX_FROM_NAME) {
		TX_FROM_NAME = tX_FROM_NAME;
	}
	public boolean isTX_READ() {
		return TX_READ;
	}
	public void setTX_READ(boolean tX_READ) {
		TX_READ = tX_READ;
	}
	public boolean isTX_STAR() {
		return TX_STAR;
	}
	public void setTX_STAR(boolean tX_STAR) {
		TX_STAR = tX_STAR;
	}
	public String getTX_TIME() {
		return TX_TIME;
	}
	public void setTX_TIME(String tX_TIME) {
		TX_TIME = tX_TIME;
	}
	public String getTX_ATTACHMENT() {
		return TX_ATTACHMENT;
	}
	public void setTX_ATTACHMENT(String tX_ATTACHMENT) {
		TX_ATTACHMENT = tX_ATTACHMENT;
	}
	public String getTX_FLAG() {
		return TX_FLAG;
	}
	public void setTX_FLAG(String string) {
		TX_FLAG = string;
	}
	public int getID_PRIORITY() {
		return ID_PRIORITY;
	}
	public void setID_PRIORITY(int iD_PRIORITY) {
		ID_PRIORITY = iD_PRIORITY;
	}
	public int getID_PARENT() {
		return ID_PARENT;
	}
	public void setID_PARENT(int iD_PARENT) {
		ID_PARENT = iD_PARENT;
	}
	/**
	 * @return the queryMessageCount
	 */
	public int getQueryMessageCount() {
		return queryMessageCount;
	}
	/**
	 * @param queryMessageCount the queryMessageCount to set
	 */
	public void setQueryMessageCount(int queryMessageCount) {
		this.queryMessageCount = queryMessageCount;
	}
	public String getTX_FROM_USER_IMAGE() {
		return TX_FROM_USER_IMAGE;
	}
	public void setTX_FROM_USER_IMAGE(String tX_FROM_USER_IMAGE) {
		TX_FROM_USER_IMAGE = tX_FROM_USER_IMAGE;
	}
	

}
