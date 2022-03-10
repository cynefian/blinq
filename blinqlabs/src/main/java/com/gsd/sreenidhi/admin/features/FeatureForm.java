package com.gsd.sreenidhi.admin.features;

public class FeatureForm {
	private int id;
	private String tx_feature;
	private boolean fl_active;
	private String tx_section;
	private int id_section;
	private boolean[] features;
	
	
	public boolean[] getFeatures() {
		return features;
	}
	public void setFeatures(boolean[] features) {
		this.features = features;
	}
	public String getTx_section() {
		return tx_section;
	}
	public void setTx_section(String tx_section) {
		this.tx_section = tx_section;
	}
	public int getId_section() {
		return id_section;
	}
	public void setId_section(int id_section) {
		this.id_section = id_section;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTx_feature() {
		return tx_feature;
	}
	public void setTx_feature(String tx_feature) {
		this.tx_feature = tx_feature;
	}
	public boolean isFl_active() {
		return fl_active;
	}
	public void setFl_active(boolean fl_active) {
		this.fl_active = fl_active;
	}

}
