package com.gsd.sreenidhi.admin.settings.coordination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class CoordinationActions {
	
	String GET_COORDINATION_LINKS_SQL = "SELECT TC.ID_USER_ACCOUNT        AS ID_USER_ACCOUNT, "
			+ "       USER_ACCOUNT.TX_FIRSTNAME AS TX_USER_FIRSTNAME, "
			+ "       USER_ACCOUNT.TX_LASTNAME  AS TX_USER_LAST_NAME, "
			+ "       AUA.TX_EMAIL              AS TX_USER_ACCOUNT_EMAIL, "
			+ "       USER_ACCOUNT.TX_IMAGE     AS TX_USER_IMAGE, "
			+ "       TC.ID_USER_COORDINATOR    AS ID_USER_COORDINATOR, "
			+ "       USER_TC.TX_FIRSTNAME      AS TX_TC_FIRSTNAME, "
			+ "       USER_TC.TX_LASTNAME       AS TX_TC_LAST_NAME, "
			+ "       ATC.TX_EMAIL              AS TX_TC_EMAIL, "
			+ "       USER_TC.TX_IMAGE          AS TX_TC_IMAGE "
			+ "FROM   TECHNICAL_COORDINATOR TC "
			+ "       JOIN USERS USER_ACCOUNT "
			+ "         ON TC.ID_USER_ACCOUNT = USER_ACCOUNT.ID "
			+ "       JOIN ACCOUNTS AUA "
			+ "         ON AUA.ID = USER_ACCOUNT.ID_ACCOUNT "
			+ "       JOIN USERS USER_TC "
			+ "         ON TC.ID_USER_COORDINATOR = USER_TC.ID "
			+ "       JOIN ACCOUNTS ATC "
			+ "         ON ATC.ID = USER_TC.ID_ACCOUNT";

	public List<TechCoordinationForm> getTCLinks(JdbcTemplate jdbcTemplate) {
	
		List<TechCoordinationForm> tcList = jdbcTemplate.query(GET_COORDINATION_LINKS_SQL, new RowMapper<TechCoordinationForm>() {
			public TechCoordinationForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				TechCoordinationForm tc = new TechCoordinationForm();
					tc.setID_USER_ACCOUNT(resulSet.getInt("ID_USER_ACCOUNT"));
					tc.setTX_USER_FIRSTNAME(resulSet.getString("TX_USER_FIRSTNAME"));
					tc.setTX_USER_LAST_NAME(resulSet.getString("TX_USER_LAST_NAME"));
					tc.setTX_USER_ACCOUNT_EMAIL(resulSet.getString("TX_USER_ACCOUNT_EMAIL"));
					tc.setTX_USER_IMAGE(resulSet.getString("TX_USER_IMAGE"));
					tc.setID_USER_COORDINATOR(resulSet.getInt("ID_USER_COORDINATOR"));
					tc.setTX_TC_FIRSTNAME(resulSet.getString("TX_TC_FIRSTNAME"));
					tc.setTX_TC_LAST_NAME(resulSet.getString("TX_TC_LAST_NAME"));
					tc.setTX_TC_EMAIL(resulSet.getString("TX_TC_EMAIL"));
					tc.setTX_TC_IMAGE(resulSet.getString("TX_TC_IMAGE"));
				return tc;
			}
		});
				
		return tcList;
	}

}
