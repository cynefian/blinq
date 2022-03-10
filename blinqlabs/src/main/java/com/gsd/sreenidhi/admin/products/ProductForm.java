package com.gsd.sreenidhi.admin.products;

public class ProductForm {
	
	private int ID;
	private String TX_PRODUCT_NAME; 
	private String TX_PRODUCT_DESC; 
	private boolean FL_ACTIVE;
	private boolean FL_LICENSE;
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param i the iD to set
	 */
	public void setID(int i) {
		ID = i;
	}
	/**
	 * @return the tX_PRODUCT_NAME
	 */
	public String getTX_PRODUCT_NAME() {
		return TX_PRODUCT_NAME;
	}
	/**
	 * @param tX_PRODUCT_NAME the tX_PRODUCT_NAME to set
	 */
	public void setTX_PRODUCT_NAME(String tX_PRODUCT_NAME) {
		TX_PRODUCT_NAME = tX_PRODUCT_NAME;
	}
	/**
	 * @return the tX_PRODUCT_DESC
	 */
	public String getTX_PRODUCT_DESC() {
		return TX_PRODUCT_DESC;
	}
	/**
	 * @param tX_PRODUCT_DESC the tX_PRODUCT_DESC to set
	 */
	public void setTX_PRODUCT_DESC(String tX_PRODUCT_DESC) {
		TX_PRODUCT_DESC = tX_PRODUCT_DESC;
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
	 * @return the fL_LICENSE
	 */
	public boolean isFL_LICENSE() {
		return FL_LICENSE;
	}
	/**
	 * @param fL_LICENSE the fL_LICENSE to set
	 */
	public void setFL_LICENSE(boolean fL_LICENSE) {
		FL_LICENSE = fL_LICENSE;
	}
	
	

}
