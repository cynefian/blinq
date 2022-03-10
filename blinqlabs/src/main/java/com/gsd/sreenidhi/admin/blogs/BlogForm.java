package com.gsd.sreenidhi.admin.blogs;

public class BlogForm {
	
	private int ID_BLOG;
	private String TX_TITLE;
	private String TS_CREATE;
	private int ID_AUTHOR;
	private String TX_AUTHOR;
	private String AUTHOR_IMAGE;
	private String TX_IMAGE;
	private String TX_BODY;
	private boolean FL_ACTIVE;
	private int blogCount;
	private String TX_SUBMIT_ACTION;
	private String TX_USER_BIO;
	private String TX_CATEGORY;
	private String TX_TAGS;
	private int ID_CATEGORY;
	private String Q_FORWARD;
	
	
	public String getTX_USER_BIO() {
		return TX_USER_BIO;
	}
	public void setTX_USER_BIO(String tX_USER_BIO) {
		TX_USER_BIO = tX_USER_BIO;
	}
	public String getTX_AUTHOR() {
		return TX_AUTHOR;
	}
	public void setTX_AUTHOR(String tX_AUTHOR) {
		TX_AUTHOR = tX_AUTHOR;
	}
	
	/**
	 * @return the iD_BLOG
	 */
	public int getID_BLOG() {
		return ID_BLOG;
	}
	/**
	 * @param iD_BLOG the iD_BLOG to set
	 */
	public void setID_BLOG(int iD_BLOG) {
		ID_BLOG = iD_BLOG;
	}
	/**
	 * @return the tX_TITLE
	 */
	public String getTX_TITLE() {
		return TX_TITLE;
	}
	/**
	 * @param tX_TITLE the tX_TITLE to set
	 */
	public void setTX_TITLE(String tX_TITLE) {
		TX_TITLE = tX_TITLE;
	}
	/**
	 * @return the tS_CREATE
	 */
	public String getTS_CREATE() {
		return TS_CREATE;
	}
	/**
	 * @param tS_CREATE the tS_CREATE to set
	 */
	public void setTS_CREATE(String tS_CREATE) {
		TS_CREATE = tS_CREATE;
	}
	/**
	 * @return the iD_AUTHOR
	 */
	public int getID_AUTHOR() {
		return ID_AUTHOR;
	}
	/**
	 * @param iD_AUTHOR the iD_AUTHOR to set
	 */
	public void setID_AUTHOR(int iD_AUTHOR) {
		ID_AUTHOR = iD_AUTHOR;
	}
	/**
	 * @return the tX_IMAGE
	 */
	public String getTX_IMAGE() {
		return TX_IMAGE;
	}
	/**
	 * @param tX_IMAGE the tX_IMAGE to set
	 */
	public void setTX_IMAGE(String tX_IMAGE) {
		TX_IMAGE = tX_IMAGE;
	}
	/**
	 * @return the tX_BODY
	 */
	public String getTX_BODY() {
		return TX_BODY;
	}
	/**
	 * @param tX_BODY the tX_BODY to set
	 */
	public void setTX_BODY(String tX_BODY) {
		TX_BODY = tX_BODY;
	}
	/**
	 * @return the fL_ACTIVE
	 */
	public boolean isFL_ACTIVE() {
		return FL_ACTIVE;
	}
	/**
	 * @param fL_ACTIVE the fL_ACTIVE to set
	 */
	public void setFL_ACTIVE(boolean fL_ACTIVE) {
		FL_ACTIVE = fL_ACTIVE;
	}
	/**
	 * @return the blogCount
	 */
	public int getBlogCount() {
		return blogCount;
	}
	/**
	 * @param blogCount the blogCount to set
	 */
	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}
	/**
	 * @return the tX_SUBMIT_ACTION
	 */
	public String getTX_SUBMIT_ACTION() {
		return TX_SUBMIT_ACTION;
	}
	/**
	 * @param tX_SUBMIT_ACTION the tX_SUBMIT_ACTION to set
	 */
	public void setTX_SUBMIT_ACTION(String tX_SUBMIT_ACTION) {
		TX_SUBMIT_ACTION = tX_SUBMIT_ACTION;
	}
	public String getTX_CATEGORY() {
		return TX_CATEGORY;
	}
	public void setTX_CATEGORY(String tX_CATEGORY) {
		TX_CATEGORY = tX_CATEGORY;
	}
	public String getTX_TAGS() {
		return TX_TAGS;
	}
	public void setTX_TAGS(String tX_TAGS) {
		TX_TAGS = tX_TAGS;
	}
	public int getID_CATEGORY() {
		return ID_CATEGORY;
	}
	public void setID_CATEGORY(int iD_CATEGORY) {
		ID_CATEGORY = iD_CATEGORY;
	}
	public String getQ_FORWARD() {
		return Q_FORWARD;
	}
	public void setQ_FORWARD(String q_FORWARD) {
		Q_FORWARD = q_FORWARD;
	}
	public String getAUTHOR_IMAGE() {
		return AUTHOR_IMAGE;
	}
	public void setAUTHOR_IMAGE(String aUTHOR_IMAGE) {
		AUTHOR_IMAGE = aUTHOR_IMAGE;
	}
	

	
}
