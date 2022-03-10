package com.gsd.sreenidhi.admin.workbench;

import java.util.List;

public class UserServicesForm {
	private int ID_SERVICE;
	private String TX_LAB_SRVC_KEY;
	private String TX_INFRASTRUCTURE_TYPE;
	private int ID_INFRASTRUCTURE;
	private boolean FL_INFRA_INITIALIZED;
	private String TX_INFRA_TIMESTAMP_CREATE;
	private int ID_INFRA_SERVICE_TYPE;
	private String TX_INFRA_SRVC_TYPE;
	private int ID_SRVC_TOOL;
	private String TX_SRVC_TOOL_NAME;
	private String TX_SRVC_TOOL_IMG;
	private String TX_SRVC_TOOL_CATEGORY;
	private String TX_SRVC_TOOL_CAT_CODE;
	private String TX_PORT;
	private String ID_CONTAINER;
	private boolean FL_SERVICE_STATUS;
	private String TX_SERVICE_URL;
	private int ID_WORKBENCH;
	private String TX_WORKBENCH_NAME;
	private String TX_WORKBENCH_KEY;
	private int ID_USER;
	private String TS_SRVC_TIMESTAMP_CREATE;
	private boolean FL_SRVC_INITIALIZED;
	private String TX_IP ;
	private String TX_ID_VPC ;
	private int ID_EC2;
	private String TX_ID_EC2;
	private String TX_NAME_EC2;
	private String TX_ID_AMI;
	private boolean FL_INFRASTRUCTURE_STATUS;
	private int TX_SERVICES_COUNT ;
	private List<ServiceInitializationForm> initializer;
	private boolean implStat;
	
	public int getID_SERVICE() {
		return ID_SERVICE;
	}
	public void setID_SERVICE(int iD_SERVICE) {
		ID_SERVICE = iD_SERVICE;
	}
	public String getTX_INFRASTRUCTURE_TYPE() {
		return TX_INFRASTRUCTURE_TYPE;
	}
	public void setTX_INFRASTRUCTURE_TYPE(String tX_INFRASTRUCTURE_TYPE) {
		TX_INFRASTRUCTURE_TYPE = tX_INFRASTRUCTURE_TYPE;
	}
	public int getID_INFRASTRUCTURE() {
		return ID_INFRASTRUCTURE;
	}
	public void setID_INFRASTRUCTURE(int iD_INFRASTRUCTURE) {
		ID_INFRASTRUCTURE = iD_INFRASTRUCTURE;
	}
	public int getID_INFRA_SERVICE_TYPE() {
		return ID_INFRA_SERVICE_TYPE;
	}
	public void setID_INFRA_SERVICE_TYPE(int iD_INFRA_SERVICE_TYPE) {
		ID_INFRA_SERVICE_TYPE = iD_INFRA_SERVICE_TYPE;
	}
	public String getTX_INFRA_SRVC_TYPE() {
		return TX_INFRA_SRVC_TYPE;
	}
	public void setTX_INFRA_SRVC_TYPE(String tX_INFRA_SRVC_TYPE) {
		TX_INFRA_SRVC_TYPE = tX_INFRA_SRVC_TYPE;
	}
	public int getID_SRVC_TOOL() {
		return ID_SRVC_TOOL;
	}
	public void setID_SRVC_TOOL(int iD_SRVC_TOOL) {
		ID_SRVC_TOOL = iD_SRVC_TOOL;
	}
	public String getTX_SRVC_TOOL_NAME() {
		return TX_SRVC_TOOL_NAME;
	}
	public void setTX_SRVC_TOOL_NAME(String tX_SRVC_TOOL_NAME) {
		TX_SRVC_TOOL_NAME = tX_SRVC_TOOL_NAME;
	}
	public String getTX_SRVC_TOOL_IMG() {
		return TX_SRVC_TOOL_IMG;
	}
	public void setTX_SRVC_TOOL_IMG(String tX_SRVC_TOOL_IMG) {
		TX_SRVC_TOOL_IMG = tX_SRVC_TOOL_IMG;
	}
	public String getTX_SRVC_TOOL_CATEGORY() {
		return TX_SRVC_TOOL_CATEGORY;
	}
	public void setTX_SRVC_TOOL_CATEGORY(String tX_SRVC_TOOL_CATEGORY) {
		TX_SRVC_TOOL_CATEGORY = tX_SRVC_TOOL_CATEGORY;
	}
	public String getTX_SRVC_TOOL_CAT_CODE() {
		return TX_SRVC_TOOL_CAT_CODE;
	}
	public void setTX_SRVC_TOOL_CAT_CODE(String tX_SRVC_TOOL_CAT_CODE) {
		TX_SRVC_TOOL_CAT_CODE = tX_SRVC_TOOL_CAT_CODE;
	}
	public String getTX_PORT() {
		return TX_PORT;
	}
	public void setTX_PORT(String tX_PORT) {
		TX_PORT = tX_PORT;
	}
	public String getID_CONTAINER() {
		return ID_CONTAINER;
	}
	public void setID_CONTAINER(String iD_CONTAINER) {
		ID_CONTAINER = iD_CONTAINER;
	}
	public boolean isFL_SERVICE_STATUS() {
		return FL_SERVICE_STATUS;
	}
	public void setFL_SERVICE_STATUS(boolean fL_SERVICE_STATUS) {
		FL_SERVICE_STATUS = fL_SERVICE_STATUS;
	}
	public String getTX_SERVICE_URL() {
		return TX_SERVICE_URL;
	}
	public void setTX_SERVICE_URL(String tX_SERVICE_URL) {
		TX_SERVICE_URL = tX_SERVICE_URL;
	}
	public int getID_WORKBENCH() {
		return ID_WORKBENCH;
	}
	public void setID_WORKBENCH(int iD_WORKBENCH) {
		ID_WORKBENCH = iD_WORKBENCH;
	}
	public String getTX_WORKBENCH_NAME() {
		return TX_WORKBENCH_NAME;
	}
	public void setTX_WORKBENCH_NAME(String tX_WORKBENCH_NAME) {
		TX_WORKBENCH_NAME = tX_WORKBENCH_NAME;
	}
	public String getTX_WORKBENCH_KEY() {
		return TX_WORKBENCH_KEY;
	}
	public void setTX_WORKBENCH_KEY(String tX_WORKBENCH_KEY) {
		TX_WORKBENCH_KEY = tX_WORKBENCH_KEY;
	}
	public int getID_USER() {
		return ID_USER;
	}
	public void setID_USER(int iD_USER) {
		ID_USER = iD_USER;
	}
	public String getTX_IP() {
		return TX_IP;
	}
	public void setTX_IP(String tX_IP) {
		TX_IP = tX_IP;
	}
	public String getTX_ID_VPC() {
		return TX_ID_VPC;
	}
	public void setTX_ID_VPC(String tX_ID_VPC) {
		TX_ID_VPC = tX_ID_VPC;
	}
	public String getTX_ID_EC2() {
		return TX_ID_EC2;
	}
	public void setTX_ID_EC2(String tX_ID_EC2) {
		TX_ID_EC2 = tX_ID_EC2;
	}
	public String getTX_ID_AMI() {
		return TX_ID_AMI;
	}
	public void setTX_ID_AMI(String tX_ID_AMI) {
		TX_ID_AMI = tX_ID_AMI;
	}

	
	public boolean isFL_INFRASTRUCTURE_STATUS() {
		return FL_INFRASTRUCTURE_STATUS;
	}
	public void setFL_INFRASTRUCTURE_STATUS(boolean fL_INFRASTRUCTURE_STATUS) {
		FL_INFRASTRUCTURE_STATUS = fL_INFRASTRUCTURE_STATUS;
	}
	public int getTX_SERVICES_COUNT() {
		return TX_SERVICES_COUNT;
	}
	public void setTX_SERVICES_COUNT(int tX_SERVICES_COUNT) {
		TX_SERVICES_COUNT = tX_SERVICES_COUNT;
	}
	public String getTS_SRVC_TIMESTAMP_CREATE() {
		return TS_SRVC_TIMESTAMP_CREATE;
	}
	public void setTS_SRVC_TIMESTAMP_CREATE(String tS_SRVC_TIMESTAMP_CREATE) {
		TS_SRVC_TIMESTAMP_CREATE = tS_SRVC_TIMESTAMP_CREATE;
	}
	public boolean isFL_SRVC_INITIALIZED() {
		return FL_SRVC_INITIALIZED;
	}
	public void setFL_SRVC_INITIALIZED(boolean fL_SRVC_INITIALIZED) {
		FL_SRVC_INITIALIZED = fL_SRVC_INITIALIZED;
	}
	public boolean isFL_INFRA_INITIALIZED() {
		return FL_INFRA_INITIALIZED;
	}
	public void setFL_INFRA_INITIALIZED(boolean fL_INFRA_INITIALIZED) {
		FL_INFRA_INITIALIZED = fL_INFRA_INITIALIZED;
	}
	public String getTX_INFRA_TIMESTAMP_CREATE() {
		return TX_INFRA_TIMESTAMP_CREATE;
	}
	public void setTX_INFRA_TIMESTAMP_CREATE(String tX_INFRA_TIMESTAMP_CREATE) {
		TX_INFRA_TIMESTAMP_CREATE = tX_INFRA_TIMESTAMP_CREATE;
	}
	public String getTX_LAB_SRVC_KEY() {
		return TX_LAB_SRVC_KEY;
	}
	public void setTX_LAB_SRVC_KEY(String tX_LAB_SRVC_KEY) {
		TX_LAB_SRVC_KEY = tX_LAB_SRVC_KEY;
	}
	public List<ServiceInitializationForm> getInitializer() {
		return initializer;
	}
	public void setInitializer(List<ServiceInitializationForm> initializer) {
		this.initializer = initializer;
	}
	public String getTX_NAME_EC2() {
		return TX_NAME_EC2;
	}
	public void setTX_NAME_EC2(String tX_NAME_EC2) {
		TX_NAME_EC2 = tX_NAME_EC2;
	}
	public boolean isImplStat() {
		return implStat;
	}
	public void setImplStat(boolean implStat) {
		this.implStat = implStat;
	}
	public int getID_EC2() {
		return ID_EC2;
	}
	public void setID_EC2(int iD_EC2) {
		ID_EC2 = iD_EC2;
	}


}
