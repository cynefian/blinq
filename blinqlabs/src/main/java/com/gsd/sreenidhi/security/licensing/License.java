package com.gsd.sreenidhi.security.licensing;


public class License {
	private String tx_accountID;
	
	private int id_account;
	private String tx_license;
	private int id;
	private String licensedProduct;
	private String licenseValidity;
	private String licenseType;
	
	private String selectProduct;
	private String machineId;
	
	private int queryLicenseCount;
	
	public String getTx_accountID() {
		return tx_accountID;
	}
	public void setTx_accountID(String tx_accountID) {
		this.tx_accountID = tx_accountID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_account() {
		return id_account;
	}
	public void setId_account(int id_account) {
		this.id_account = id_account;
	}
	public String getTx_license() {
		return tx_license;
	}
	public void setTx_license(String tx_license) {
		this.tx_license = tx_license;
	}
	public String getSelectProduct() {
		return selectProduct;
	}
	public void setSelectProduct(String selectProduct) {
		this.selectProduct = selectProduct;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getLicensedProduct() {
		return licensedProduct;
	}
	public void setLicensedProduct(String licensedProduct) {
		this.licensedProduct = licensedProduct;
	}
	public String getLicenseValidity() {
		return licenseValidity;
	}
	public void setLicenseValidity(String licenseValidity) {
		this.licenseValidity = licenseValidity;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	/**
	 * @return the queryLicenseCount
	 */
	public int getQueryLicenseCount() {
		return queryLicenseCount;
	}
	/**
	 * @param queryLicenseCount the queryLicenseCount to set
	 */
	public void setQueryLicenseCount(int queryLicenseCount) {
		this.queryLicenseCount = queryLicenseCount;
	}
	
	
}
