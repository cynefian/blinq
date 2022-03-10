package com.gsd.sreenidhi.commons.serviceEntitlement;

public class TierRolloverForm {
	private int ID_SERVICE_TIER_ROLLOVER;
	private int ID_ENTITLEMT; 
	private int ID_BILLING_CYCLE;
	private int NUM_ROLLOVER_ORIG;
	private int NUM_ROLLOVER_BALANCE;
	private boolean FL_ACTIVE;
	
	
	private int countRolloverRecords;

	public int getID_SERVICE_TIER_ROLLOVER() {
		return ID_SERVICE_TIER_ROLLOVER;
	}

	public void setID_SERVICE_TIER_ROLLOVER(int iD_SERVICE_TIER_ROLLOVER) {
		ID_SERVICE_TIER_ROLLOVER = iD_SERVICE_TIER_ROLLOVER;
	}

	public int getID_ENTITLEMT() {
		return ID_ENTITLEMT;
	}

	public void setID_ENTITLEMT(int iD_ENTITLEMT) {
		ID_ENTITLEMT = iD_ENTITLEMT;
	}

	public int getID_BILLING_CYCLE() {
		return ID_BILLING_CYCLE;
	}

	public void setID_BILLING_CYCLE(int iD_BILLING_CYCLE) {
		ID_BILLING_CYCLE = iD_BILLING_CYCLE;
	}

	public int getNUM_ROLLOVER_ORIG() {
		return NUM_ROLLOVER_ORIG;
	}

	public void setNUM_ROLLOVER_ORIG(int nUM_ROLLOVER_ORIG) {
		NUM_ROLLOVER_ORIG = nUM_ROLLOVER_ORIG;
	}

	public int getNUM_ROLLOVER_BALANCE() {
		return NUM_ROLLOVER_BALANCE;
	}

	public void setNUM_ROLLOVER_BALANCE(int nUM_ROLLOVER_BALANCE) {
		NUM_ROLLOVER_BALANCE = nUM_ROLLOVER_BALANCE;
	}

	public int getCountRolloverRecords() {
		return countRolloverRecords;
	}

	public void setCountRolloverRecords(int countRolloverRecords) {
		this.countRolloverRecords = countRolloverRecords;
	}

	public boolean isFL_ACTIVE() {
		return FL_ACTIVE;
	}

	public void setFL_ACTIVE(boolean fL_ACTIVE) {
		FL_ACTIVE = fL_ACTIVE;
	}
	
}
