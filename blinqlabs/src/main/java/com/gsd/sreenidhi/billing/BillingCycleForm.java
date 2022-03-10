package com.gsd.sreenidhi.billing;

public class BillingCycleForm {
	
	private int ID;
	private int ID_ENTITLEMENT;
	private String TS_START_DATE;
	private String TS_END_DATE;
	private String TX_DATA;
	private boolean FL_COMPLETE;
	private int billingCycleCount;
	private String TX_CYCLE_HOURS_INVESTED;
	private String TX_CYCLE_HOURS_BILLABLE;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getID_ENTITLEMENT() {
		return ID_ENTITLEMENT;
	}
	public void setID_ENTITLEMENT(int iD_ENTITLEMENT) {
		ID_ENTITLEMENT = iD_ENTITLEMENT;
	}
	public String getTS_START_DATE() {
		return TS_START_DATE;
	}
	public void setTS_START_DATE(String tS_START_DATE) {
		TS_START_DATE = tS_START_DATE;
	}
	public String getTS_END_DATE() {
		return TS_END_DATE;
	}
	public void setTS_END_DATE(String tS_END_DATE) {
		TS_END_DATE = tS_END_DATE;
	}
	public String getTX_DATA() {
		return TX_DATA;
	}
	public void setTX_DATA(String tX_DATA) {
		TX_DATA = tX_DATA;
	}
	public boolean isFL_COMPLETE() {
		return FL_COMPLETE;
	}
	public void setFL_COMPLETE(boolean fL_COMPLETE) {
		FL_COMPLETE = fL_COMPLETE;
	}
	public int getBillingCycleCount() {
		return billingCycleCount;
	}
	public void setBillingCycleCount(int billingCycleCount) {
		this.billingCycleCount = billingCycleCount;
	}
	public String getTX_CYCLE_HOURS_INVESTED() {
		return TX_CYCLE_HOURS_INVESTED;
	}
	public void setTX_CYCLE_HOURS_INVESTED(String tX_CYCLE_HOURS_INVESTED) {
		TX_CYCLE_HOURS_INVESTED = tX_CYCLE_HOURS_INVESTED;
	}
	public String getTX_CYCLE_HOURS_BILLABLE() {
		return TX_CYCLE_HOURS_BILLABLE;
	}
	public void setTX_CYCLE_HOURS_BILLABLE(String tX_CYCLE_HOURS_BILLABLE) {
		TX_CYCLE_HOURS_BILLABLE = tX_CYCLE_HOURS_BILLABLE;
	}

}
