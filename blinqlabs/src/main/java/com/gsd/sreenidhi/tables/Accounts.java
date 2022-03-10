package com.gsd.sreenidhi.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="ACCOUNTS")
public class Accounts {
	
	@Id
	@GeneratedValue
	@Column(name="ID")	
	private int id;
	
	@NotEmpty
    @Email
	@Pattern(regexp=".+@.+\\..+")
	@Column(name="TX_EMAIL")	
	private String email;

	
	@NotEmpty
    @Size(min=6, max=20)
	@Column(name="TX_PASSWORD")	
	private String password;
	
	@Column(name="FL_ENABLED")	
	private int enabled;
	
	@Column(name="TS_CREATE")	
	private String timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
