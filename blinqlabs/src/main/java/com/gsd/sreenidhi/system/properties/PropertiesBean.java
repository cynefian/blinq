package com.gsd.sreenidhi.system.properties;

import org.springframework.beans.factory.annotation.Value;

public class PropertiesBean {

	@Value("${application.name}")
	private String applicationName;
	
	@Value("${smtp.Host}")
	private String smtpHost;
	
	@Value("${smtp.Port}")
	private String smtpPort;
	
	@Value("${smtp.User}")
	private String smtpUsername;
	
	@Value("${smtp.Password}")
	private String smtpPassword;
	
	@Value("${smtp.From}")
	private String fromEmail;
	
	@Value("${smtp.Default.Subject}")
	private String defaultSubject;
	
	@Value("${smtp.Password}")
	private String fromPassword;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getDefaultSubject() {
		return defaultSubject;
	}

	public void setDefaultSubject(String defaultSubject) {
		this.defaultSubject = defaultSubject;
	}

	public String getFromPassword() {
		return fromPassword;
	}

	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}

	
}

