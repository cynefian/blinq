package com.gsd.sreenidhi.user.messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class MessageActions {

	public List<UserMessageForm> getMessageCount(JdbcTemplate jdbcTemplate, String userId, String filter) {
		 
		String filterQuery = null;
		String subQ = "";
		
		if(filter!=null && filter.equalsIgnoreCase("U")) {
			filterQuery = " TX_READ = 0";
		}else if(filter!=null && filter.equalsIgnoreCase("S")) {
			filterQuery = " TX_STAR = 1";
		}else if(filter!=null && filter.equalsIgnoreCase("R")) {
			filterQuery = " TX_READ = 1";
		}else if(filter!=null && filter.equalsIgnoreCase("F")) {
			filterQuery = " TX_FLAG = 1";
		}else {
			filterQuery = null;
		}
		
		if(filterQuery!=null) {
			subQ = " AND " + filterQuery;
		}
		
		final String GET_MSG_COUNT_SQL =  "SELECT COUNT(*) AS COUNT "
				 + "						 FROM MESSAGES "
				 + "						 WHERE ID_TO = "+ userId + subQ
				 + "";
			
			List<UserMessageForm> msgcountList = jdbcTemplate.query(GET_MSG_COUNT_SQL, new RowMapper<UserMessageForm>() {
	            public UserMessageForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	UserMessageForm tkt = new UserMessageForm();
	            	
	            	tkt.setQueryMessageCount(resulSet.getInt("COUNT"));
	            	return tkt;
	            }
	        });
			
			return msgcountList;
	}

	public void insertMessage(JdbcTemplate jdbcTemplate, String TX_MESSAGE, String TX_BODY, int ID_TO, int ID_FROM, int TX_READ, int TX_STAR, String dateToString,
			String TX_ATTACH, String TX_FLAG, int ID_PRIORITY, String ID_PARENT) {
		final String INSERT_WELCOME_MESSAG = "INSERT INTO MESSAGES (TX_SUBJECT,TX_BODY, ID_TO, ID_FROM, TX_READ, TX_STAR, TX_TIME, TX_ATTACHMENT, TX_FLAG, ID_PRIORITY,ID_PARENT ) " + 
				"values(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WELCOME_MESSAG);
				preparedStatement.setString(1, TX_MESSAGE);
				preparedStatement.setString(2, TX_BODY);
				preparedStatement.setInt(3, ID_TO);
				preparedStatement.setInt(4, ID_FROM);
				preparedStatement.setInt(5, TX_READ);
				preparedStatement.setInt(6, TX_STAR);
				preparedStatement.setString(7, dateToString);
				preparedStatement.setString(8, TX_ATTACH);
				preparedStatement.setString(9, TX_FLAG);
				preparedStatement.setInt(10, ID_PRIORITY);
				preparedStatement.setString(11, ID_PARENT);
				
				return preparedStatement;
			}
		});
	
		
	}


}
