package com.gsd.sreenidhi.user.tickets;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UserTicketsForm {
	
	private int ID;
	private int ID_THREAD ;
	private String TX_SUBJECT ;
	private String TX_BODY;
	private int ID_ACCOUNT;
	private String TX_ACCOUNT_NAME;
	private String TX_ACCOUNT_EMAIL;
	private boolean TX_READ;
	private String TX_TIME;
	private String TX_ATTACHMENT;
	private int ID_PRIORITY;
	private int ID_PARENT;
	private int ID_PRODUCT;
	private String TX_PRODUCT;
	private int ID_AGENT;
	private String TX_AGENT_NAME;
	private String TX_STATUS;
	private String TX_SEN;
	
	private String TX_DATA;
	private String TX_NAME;
	private String TX_TYPE;
	private String TX_SIZE;
	
	private int queryTicketCount;
	
	private int totalTickets;
	private int respondedTickets;
	private int pendingTickets;
	private int resolvedTickets;
	
	private List<MultipartFile> files;
	
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	public int getRespondedTickets() {
		return respondedTickets;
	}
	public void setRespondedTickets(int respondedTickets) {
		this.respondedTickets = respondedTickets;
	}
	public int getPendingTickets() {
		return pendingTickets;
	}
	public void setPendingTickets(int pendingTickets) {
		this.pendingTickets = pendingTickets;
	}
	public int getResolvedTickets() {
		return resolvedTickets;
	}
	public void setResolvedTickets(int resolvedTickets) {
		this.resolvedTickets = resolvedTickets;
	}
	public String getTX_STATUS() {
		return TX_STATUS;
	}
	public void setTX_STATUS(String tX_STATUS) {
		TX_STATUS = tX_STATUS;
	}
	public String getTX_ACCOUNT_NAME() {
		return TX_ACCOUNT_NAME;
	}
	public void setTX_ACCOUNT_NAME(String tX_ACCOUNT_NAME) {
		TX_ACCOUNT_NAME = tX_ACCOUNT_NAME;
	}
	public int getID_PRODUCT() {
		return ID_PRODUCT;
	}
	public void setID_PRODUCT(int iD_PRODUCT) {
		ID_PRODUCT = iD_PRODUCT;
	}
	public String getTX_PRODUCT() {
		return TX_PRODUCT;
	}
	public void setTX_PRODUCT(String tX_PRODUCT) {
		TX_PRODUCT = tX_PRODUCT;
	}
	public int getID_AGENT() {
		return ID_AGENT;
	}
	public void setID_AGENT(int iD_AGENT) {
		ID_AGENT = iD_AGENT;
	}
	public String getTX_AGENT_NAME() {
		return TX_AGENT_NAME;
	}
	public void setTX_AGENT_NAME(String tX_AGENT_NAME) {
		TX_AGENT_NAME = tX_AGENT_NAME;
	}

	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getID_THREAD() {
		return ID_THREAD;
	}
	public void setID_THREAD(int iD_THREAD) {
		ID_THREAD = iD_THREAD;
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
	public int getID_ACCOUNT() {
		return ID_ACCOUNT;
	}
	public void setID_ACCOUNT(int iD_ACCOUNT) {
		ID_ACCOUNT = iD_ACCOUNT;
	}
	public boolean getTX_READ() {
		return TX_READ;
	}
	public void setTX_READ(boolean b) {
		TX_READ = b;
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
	 * @return the queryTicketCount
	 */
	public int getQueryTicketCount() {
		return queryTicketCount;
	}
	/**
	 * @param queryTicketCount the queryTicketCount to set
	 */
	public void setQueryTicketCount(int queryTicketCount) {
		this.queryTicketCount = queryTicketCount;
	}
	public String getTX_SEN() {
		return TX_SEN;
	}
	public void setTX_SEN(String tX_SEN) {
		TX_SEN = tX_SEN;
	}
	public String getTX_DATA() {
		return TX_DATA;
	}
	public void setTX_DATA(String tX_DATA) {
		TX_DATA = tX_DATA;
	}
	public String getTX_NAME() {
		return TX_NAME;
	}
	public void setTX_NAME(String tX_NAME) {
		TX_NAME = tX_NAME;
	}
	public String getTX_TYPE() {
		return TX_TYPE;
	}
	public void setTX_TYPE(String tX_TYPE) {
		TX_TYPE = tX_TYPE;
	}
	public String getTX_SIZE() {
		return TX_SIZE;
	}
	public void setTX_SIZE(String tX_SIZE) {
		TX_SIZE = tX_SIZE;
	}
	public String getTX_ACCOUNT_EMAIL() {
		return TX_ACCOUNT_EMAIL;
	}
	public void setTX_ACCOUNT_EMAIL(String tX_ACCOUNT_EMAIL) {
		TX_ACCOUNT_EMAIL = tX_ACCOUNT_EMAIL;
	}
	
	

}
