package com.gsd.sreenidhi.commons.ticket;

public class TicketDetailsForm {
	
	private int TICKET_ID; 
	private String TICKET_SUBJECT; 
	private String CREATE_TIME;
	private int PRIORITY; 
	private String TICKET_PRODUCT; 
	private int THREAD_ID;
	private String THREAD_BODY; 
	private boolean FL_READ; 
	private String THREAD_TIME; 
	private int ID_ATTACHMENT; 
	private int ID_THREAD_PARENT;
	private int ID_ACCOUNT;
	private String ACCOUNT_EMAIL;
	private String ACCOUNT_NAME;
	private int ID_AGENT;
	private String AGENT_NAME;
	private String AGENT_EMAIL;
	private int ID_STATUS;
	private String TX_STATUS;
	private int ID_THREAD_USER;
	private String THREAD_USER_NAME;
	private String THREAD_USER_EMAIL;
	private int FL_ATTACHMENT;
	private int FL_EDIT;
	
	private String TX_DATA;
	private String TX_NAME;
	private String TX_TYPE;
	private String TX_LINK;
	private String TX_SIZE;
	
	
	public int getTICKET_ID() {
		return TICKET_ID;
	}
	public void setTICKET_ID(int tICKET_ID) {
		TICKET_ID = tICKET_ID;
	}
	public String getTICKET_SUBJECT() {
		return TICKET_SUBJECT;
	}
	public void setTICKET_SUBJECT(String tICKET_SUBJECT) {
		TICKET_SUBJECT = tICKET_SUBJECT;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public int getPRIORITY() {
		return PRIORITY;
	}
	public void setPRIORITY(int pRIORITY) {
		PRIORITY = pRIORITY;
	}
	public String getTICKET_PRODUCT() {
		return TICKET_PRODUCT;
	}
	public void setTICKET_PRODUCT(String tICKET_PRODUCT) {
		TICKET_PRODUCT = tICKET_PRODUCT;
	}
	public int getTHREAD_ID() {
		return THREAD_ID;
	}
	public void setTHREAD_ID(int tHREAD_ID) {
		THREAD_ID = tHREAD_ID;
	}
	public String getTHREAD_BODY() {
		return THREAD_BODY;
	}
	public void setTHREAD_BODY(String tHREAD_BODY) {
		THREAD_BODY = tHREAD_BODY;
	}
	public boolean isFL_READ() {
		return FL_READ;
	}
	public void setFL_READ(boolean fL_READ) {
		FL_READ = fL_READ;
	}
	public String getTHREAD_TIME() {
		return THREAD_TIME;
	}
	public void setTHREAD_TIME(String tHREAD_TIME) {
		THREAD_TIME = tHREAD_TIME;
	}
	public int getID_ATTACHMENT() {
		return ID_ATTACHMENT;
	}
	public void setID_ATTACHMENT(int iD_ATTACHMENT) {
		ID_ATTACHMENT = iD_ATTACHMENT;
	}
	public int getID_THREAD_PARENT() {
		return ID_THREAD_PARENT;
	}
	public void setID_THREAD_PARENT(int iD_THREAD_PARENT) {
		ID_THREAD_PARENT = iD_THREAD_PARENT;
	}
	/**
	 * @return the iD_ACCOUNT
	 */
	public int getID_ACCOUNT() {
		return ID_ACCOUNT;
	}
	/**
	 * @param iD_ACCOUNT the iD_ACCOUNT to set
	 */
	public void setID_ACCOUNT(int iD_ACCOUNT) {
		ID_ACCOUNT = iD_ACCOUNT;
	}
	/**
	 * @return the aCCOUNT_EMAIL
	 */
	public String getACCOUNT_EMAIL() {
		return ACCOUNT_EMAIL;
	}
	/**
	 * @param aCCOUNT_EMAIL the aCCOUNT_EMAIL to set
	 */
	public void setACCOUNT_EMAIL(String aCCOUNT_EMAIL) {
		ACCOUNT_EMAIL = aCCOUNT_EMAIL;
	}
	/**
	 * @return the aCCOUNT_NAME
	 */
	public String getACCOUNT_NAME() {
		return ACCOUNT_NAME;
	}
	/**
	 * @param aCCOUNT_NAME the aCCOUNT_NAME to set
	 */
	public void setACCOUNT_NAME(String aCCOUNT_NAME) {
		ACCOUNT_NAME = aCCOUNT_NAME;
	}
	/**
	 * @return the iD_AGENT
	 */
	public int getID_AGENT() {
		return ID_AGENT;
	}
	/**
	 * @param iD_AGENT the iD_AGENT to set
	 */
	public void setID_AGENT(int iD_AGENT) {
		ID_AGENT = iD_AGENT;
	}
	/**
	 * @return the aGENT_NAME
	 */
	public String getAGENT_NAME() {
		return AGENT_NAME;
	}
	/**
	 * @param aGENT_NAME the aGENT_NAME to set
	 */
	public void setAGENT_NAME(String aGENT_NAME) {
		AGENT_NAME = aGENT_NAME;
	}
	/**
	 * @return the iD_STATUS
	 */
	public int getID_STATUS() {
		return ID_STATUS;
	}
	/**
	 * @param iD_STATUS the iD_STATUS to set
	 */
	public void setID_STATUS(int iD_STATUS) {
		ID_STATUS = iD_STATUS;
	}
	/**
	 * @return the tX_STATUS
	 */
	public String getTX_STATUS() {
		return TX_STATUS;
	}
	/**
	 * @param tX_STATUS the tX_STATUS to set
	 */
	public void setTX_STATUS(String tX_STATUS) {
		TX_STATUS = tX_STATUS;
	}
	/**
	 * @return the iD_THREAD_USER
	 */
	public int getID_THREAD_USER() {
		return ID_THREAD_USER;
	}
	/**
	 * @param iD_THREAD_USER the iD_THREAD_USER to set
	 */
	public void setID_THREAD_USER(int iD_THREAD_USER) {
		ID_THREAD_USER = iD_THREAD_USER;
	}
	/**
	 * @return the tHREAD_USER_NAME
	 */
	public String getTHREAD_USER_NAME() {
		return THREAD_USER_NAME;
	}
	/**
	 * @param tHREAD_USER_NAME the tHREAD_USER_NAME to set
	 */
	public void setTHREAD_USER_NAME(String tHREAD_USER_NAME) {
		THREAD_USER_NAME = tHREAD_USER_NAME;
	}
	/**
	 * @return the tHREAD_USER_EMAIL
	 */
	public String getTHREAD_USER_EMAIL() {
		return THREAD_USER_EMAIL;
	}
	/**
	 * @param tHREAD_USER_EMAIL the tHREAD_USER_EMAIL to set
	 */
	public void setTHREAD_USER_EMAIL(String tHREAD_USER_EMAIL) {
		THREAD_USER_EMAIL = tHREAD_USER_EMAIL;
	}
	public int getFL_ATTACHMENT() {
		return FL_ATTACHMENT;
	}
	public void setFL_ATTACHMENT(int fL_ATTACHMENT) {
		FL_ATTACHMENT = fL_ATTACHMENT;
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
	public String getTX_LINK() {
		return TX_LINK;
	}
	public void setTX_LINK(String tX_LINK) {
		TX_LINK = tX_LINK;
	}
	public String getTX_SIZE() {
		return TX_SIZE;
	}
	public void setTX_SIZE(String tX_SIZE) {
		TX_SIZE = tX_SIZE;
	}
	public int getFL_EDIT() {
		return FL_EDIT;
	}
	public void setFL_EDIT(int fL_EDIT) {
		FL_EDIT = fL_EDIT;
	}
	public String getAGENT_EMAIL() {
		return AGENT_EMAIL;
	}
	public void setAGENT_EMAIL(String aGENT_EMAIL) {
		AGENT_EMAIL = aGENT_EMAIL;
	}
	

}
