package com.gsd.sreenidhi.admin.services;

public class SENForm {
	private int ID;
	private int ID_ACCOUNT;
	private String TX_ACCOUNT_EMAIL;
	private String TX_ENTITLEMENT_DESCRIPTION;
	private String TX_ENTITLEMENT;
	private String TS_START;
	private String TX_DURATION;
	private String TS_END;
	private int ID_ENTITLEMENT_TYPE;
	private String TX_ENTITLEMENT_TYPE;
	private String TX_ENTITLEMENT_TYPE_DESCRIPTION;
	private int ROLLABLE_SETTING;
	private int FL_ACTIVE;
	private int ROLLABLE_FLAG;
	private String TX_TIER_JSON;
	private int entitlementCount;
	private int FL_MONTHLY_BILLING;
	
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getID_ACCOUNT() {
		return ID_ACCOUNT;
	}
	public void setID_ACCOUNT(int iD_ACCOUNT) {
		ID_ACCOUNT = iD_ACCOUNT;
	}
	public String getTX_ENTITLEMENT() {
		return TX_ENTITLEMENT;
	}
	public void setTX_ENTITLEMENT(String tX_ENTITLEMENT) {
		TX_ENTITLEMENT = tX_ENTITLEMENT;
	}
	public String getTS_START() {
		return TS_START;
	}
	public void setTS_START(String tS_START) {
		TS_START = tS_START;
	}
	public String getTS_END() {
		return TS_END;
	}
	public void setTS_END(String tS_END) {
		TS_END = tS_END;
	}
	public int getID_ENTITLEMENT_TYPE() {
		return ID_ENTITLEMENT_TYPE;
	}
	public void setID_ENTITLEMENT_TYPE(int iD_ENTITLEMENT_TYPE) {
		ID_ENTITLEMENT_TYPE = iD_ENTITLEMENT_TYPE;
	}
	public String getTX_ENTITLEMENT_TYPE() {
		return TX_ENTITLEMENT_TYPE;
	}
	public void setTX_ENTITLEMENT_TYPE(String tX_ENTITLEMENT_TYPE) {
		TX_ENTITLEMENT_TYPE = tX_ENTITLEMENT_TYPE;
	}
	public String getTX_ENTITLEMENT_TYPE_DESCRIPTION() {
		return TX_ENTITLEMENT_TYPE_DESCRIPTION;
	}
	public void setTX_ENTITLEMENT_TYPE_DESCRIPTION(String tX_ENTITLEMENT_TYPE_DESCRIPTION) {
		TX_ENTITLEMENT_TYPE_DESCRIPTION = tX_ENTITLEMENT_TYPE_DESCRIPTION;
	}
	public String getTX_ACCOUNT_EMAIL() {
		return TX_ACCOUNT_EMAIL;
	}
	public void setTX_ACCOUNT_EMAIL(String tX_ACCOUNT_EMAIL) {
		TX_ACCOUNT_EMAIL = tX_ACCOUNT_EMAIL;
	}
	public int getROLLABLE_FLAG() {
		return ROLLABLE_FLAG;
	}
	public void setROLLABLE_FLAG(int rOLLABLE_FLAG) {
		ROLLABLE_FLAG = rOLLABLE_FLAG;
	}
	public String getTX_TIER_JSON() {
		return TX_TIER_JSON;
	}
	public void setTX_TIER_JSON(String tX_TIER_JSON) {
		TX_TIER_JSON = tX_TIER_JSON;
	}
	public int getROLLABLE_SETTING() {
		return ROLLABLE_SETTING;
	}
	public void setROLLABLE_SETTING(int rOLLABLE_SETTING) {
		ROLLABLE_SETTING = rOLLABLE_SETTING;
	}
	public String getTX_DURATION() {
		return TX_DURATION;
	}
	public void setTX_DURATION(String tX_DURATION) {
		TX_DURATION = tX_DURATION;
	}
	public String getTX_ENTITLEMENT_DESCRIPTION() {
		return TX_ENTITLEMENT_DESCRIPTION;
	}
	public void setTX_ENTITLEMENT_DESCRIPTION(String tX_ENTITLEMENT_DESCRIPTION) {
		TX_ENTITLEMENT_DESCRIPTION = tX_ENTITLEMENT_DESCRIPTION;
	}
	public int getFL_ACTIVE() {
		return FL_ACTIVE;
	}
	public void setFL_ACTIVE(int fL_ACTIVE) {
		FL_ACTIVE = fL_ACTIVE;
	}
	public int getEntitlementCount() {
		return entitlementCount;
	}
	public void setEntitlementCount(int entitlementCount) {
		this.entitlementCount = entitlementCount;
	}
	public int getFL_MONTHLY_BILLING() {
		return FL_MONTHLY_BILLING;
	}
	public void setFL_MONTHLY_BILLING(int fL_MONTHLY_BILLING) {
		FL_MONTHLY_BILLING = fL_MONTHLY_BILLING;
	}
	
	
	
}
