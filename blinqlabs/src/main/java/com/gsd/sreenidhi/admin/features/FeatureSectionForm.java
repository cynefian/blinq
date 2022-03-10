package com.gsd.sreenidhi.admin.features;

import java.util.List;

public class FeatureSectionForm {
	private int id_section;
	private String tx_section;
	private int feature_count;
	private List<FeatureForm> featureList;
	
	public int getId_section() {
		return id_section;
	}
	public void setId_section(int id_section) {
		this.id_section = id_section;
	}
	public String getTx_section() {
		return tx_section;
	}
	public void setTx_section(String tx_section) {
		this.tx_section = tx_section;
	}
	public List<FeatureForm> getFeatureList() {
		return featureList;
	}
	public void setFeatureList(List<FeatureForm> featureList) {
		this.featureList = featureList;
	}
	public int getFeature_count() {
		return feature_count;
	}
	public void setFeature_count(int feature_count) {
		this.feature_count = feature_count;
	}
	
	

}
