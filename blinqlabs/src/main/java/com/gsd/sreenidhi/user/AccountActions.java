package com.gsd.sreenidhi.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AccountActions {

	public List<AccountSettingForm> getAccount(int usrId, JdbcTemplate jdbcTemplate) {
		final String QUERY_SQL = "select " + 
				"TX_FIRSTNAME, " + 
				"TX_LASTNAME, " + 
				"ID_ACCOUNT, " + 
				"TX_WEBSITE, " + 
				"TX_CONTACT_NUM, " + 
				"TX_BIO," +
				"TX_IMAGE," +
				"TX_EMAIL, " +
				"FL_2FA, " +
				"accounts.TS_CREATE AS TS_PWD_UPDATE " +
				"from users " + 
				"join accounts " + 
				"on users.id_account = accounts.id " + 
				"where accounts.id = " + usrId;
		
		List<AccountSettingForm> accSettingList = jdbcTemplate.query(QUERY_SQL, new RowMapper<AccountSettingForm>() {
			public AccountSettingForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				AccountSettingForm acc = new AccountSettingForm();
				acc.setId(resulSet.getInt("ID_ACCOUNT"));
				acc.setTx_email(resulSet.getString("TX_EMAIL"));
				acc.setTx_firstname(resulSet.getString("TX_FIRSTNAME"));
				acc.setTx_lastname(resulSet.getString("TX_LASTNAME"));
				acc.setTx_website(resulSet.getString("TX_WEBSITE"));
				acc.setTx_contact_num(resulSet.getString("TX_CONTACT_NUM"));
				acc.setTx_bio(resulSet.getString("TX_BIO"));
				acc.setTX_IMAGE(resulSet.getString("TX_IMAGE"));
				acc.setFL_2FA(resulSet.getInt("FL_2FA")==1?true:false);
				acc.setTS_PWD_UPDATE(resulSet.getString("TS_PWD_UPDATE"));
				return acc;
			}
		});
		
		return accSettingList;
	}

	public List<AccountVerificationForm> getAccountVerification(int usrId, JdbcTemplate jdbcTemplate) {

		String ACC_VERIFICATION_SQL = "SELECT "
				+ "ACCOUNT_VERIFICATION.FL_EMAIL_VERIFIED AS FL_EMAIL_VERIFIED, "
				+ "ACCOUNT_VERIFICATION.TX_EMAIL_VERIFIED AS TX_EMAIL_VERIFIED, "
				+ "ACCOUNT_VERIFICATION.TS_EMAIL_VERIFIED AS TS_EMAIL_VERIFIED, "
				+ "ACCOUNT_VERIFICATION.FL_MOB_VERIFIED AS FL_MOB_VERIFIED, "
				+ "ACCOUNT_VERIFICATION.TX_MOB_VERIFIED AS TX_MOB_VERIFIED, "
				+ "ACCOUNT_VERIFICATION.TS_MOB_VERIFIED AS TS_MOB_VERIFIED, "
				+ "ACCOUNTS.TX_EMAIL AS TX_EMAIL, "
				+ "USERS.TX_CONTACT_NUM AS TX_CONTACT_NUM "
				+ "FROM ACCOUNT_VERIFICATION "
				+ "JOIN ACCOUNTS ON ACCOUNTS.ID = ACCOUNT_VERIFICATION.ID_USER "
				+ "JOIN USERS on USERS.ID_ACCOUNT = ACCOUNTS.ID "
				+ "WHERE ACCOUNTS.ID = " + usrId;
		
		List<AccountVerificationForm> accVerifiedList = jdbcTemplate.query(ACC_VERIFICATION_SQL, new RowMapper<AccountVerificationForm>() {
			public AccountVerificationForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				AccountVerificationForm acc = new AccountVerificationForm();
				acc.setFL_EMAIL_VERIFIED(resulSet.getInt("FL_EMAIL_VERIFIED")==1?true:false);
				acc.setTX_EMAIL_VERIFIED(resulSet.getString("FL_EMAIL_VERIFIED"));
				acc.setTS_EMAIL_VERIFIED(resulSet.getString("TS_EMAIL_VERIFIED"));
				acc.setFL_MOB_VERIFIED(resulSet.getInt("FL_MOB_VERIFIED")==1?true:false);
				acc.setTX_MOB_VERIFIED(resulSet.getString("TX_MOB_VERIFIED"));
				acc.setTS_MOB_VERIFIED(resulSet.getString("TS_MOB_VERIFIED"));
				acc.setTX_EMAIL(resulSet.getString("TX_EMAIL"));
				acc.setTX_CONTACT_NUM(resulSet.getString("TX_CONTACT_NUM"));
				return acc;
			}
		});
		
		if(accVerifiedList.size()>0) {
			return accVerifiedList;
		}else {
			return null;
		}
	}

}
