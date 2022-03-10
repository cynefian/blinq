package com.gsd.sreenidhi.user.tickets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TicketActions {

	public List<UserTicketsForm> getTicketCounts(JdbcTemplate jdbcTemplate, int page, String filter, String userId) {
		
		String queryStringPlaceHolder = "";
		 if(filter!=null) {
			 
			 if("all".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=""; 
			 }else if("pending".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (1,2,3,6)";
			 } 
			 else if("escalated".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (6)";
			 } 
			 else if("responded".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (4)";
			 } 
			 else if("resolved".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (5)";
			 } 
			 else if("closed".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (5)";
			 } 
			 else  {
				 queryStringPlaceHolder=""; 
			 } 
		 }
		 
		 
		 final String GET_TICKET_COUNT_SQL =  "SELECT count(*) AS COUNT "
				 + "												 from TICKETS "
				 + "												 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID "
				 + "												 WHERE TICKETS.ID_ACCOUNT =   " + userId + queryStringPlaceHolder
				 + "";
			
			List<UserTicketsForm> tktcountList = jdbcTemplate.query(GET_TICKET_COUNT_SQL, new RowMapper<UserTicketsForm>() {
	            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	UserTicketsForm tkt = new UserTicketsForm();
	            	
	            	tkt.setQueryTicketCount(resulSet.getInt("COUNT"));
	            	return tkt;
	            }
	        });
			
			return tktcountList;
	}

	public List<UserTicketsForm> getTickets(JdbcTemplate jdbcTemplate, int page, String filter, String userId) {
		
		int countOnPage = 10;
		
		String queryStringPlaceHolder = "";
		 if(filter!=null) {
			 
			 if("all".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=""; 
			 }else if("pending".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (1,2,3,6)";
			 } 
			 else if("escalated".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (6)";
			 } 
			 else if("responded".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (4)";
			 } 
			 else if("resolved".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (5)";
			 } 
			 else if("closed".equalsIgnoreCase(filter)) {
				 queryStringPlaceHolder=" AND STATUSTAB.ID in (5)";
			 } 
			 else  {
				 queryStringPlaceHolder=""; 
			 } 
		 }
		 
		 
		final String GET_TICKETS_QUERY_SQL = ""
				+ " DECLARE @PageNumber AS INT, @RowspPage AS INT"
				+ " SET @PageNumber = " + page
				+ " SET @RowspPage = "+ countOnPage
				+ " SELECT * FROM ( "
				+ "			SELECT									 ROW_NUMBER() OVER(ORDER BY TICKETS.ID DESC) AS NUMBER, "
				+ "					    					 TICKETS.ID AS ID, "
				+ "												 TICKETS.TX_SUBJECT AS SUBJECT, "
				+ "												 TICKETS.ID_ACCOUNT AS ID_ACCOUNT, "
				+ "												 (SELECT CONCAT(U1.TX_FIRSTNAME,' ',U1.TX_LASTNAME) FROM USERS U1 WHERE  U1.ID_ACCOUNT = TICKETS.ID_ACCOUNT) AS TX_ACCOUNT_NAME, "
				+ "												 TICKETS.TX_TIME AS TX_TIME, "
				+ "												 TICKETS.ID_PRIORITY AS ID_PRIORITY, "
				+ "												 STATUSTAB.TX_STATUS AS TX_STATUS , "
				+ "												 (SELECT CONCAT(U2.TX_FIRSTNAME,' ',U2.TX_LASTNAME) FROM USERS U2 WHERE  U2.ID_ACCOUNT = TICKETS.ID_AGENT) AS TX_AGENT_NAME, "
				+ "												 TICKETS.ID_SERVICE_ENTITLEMENT AS ID_SERVICE_ENTITLEMENT, "
				+ "												 (SELECT TX_ENTITLEMENT FROM SERVICE_ENTITLEMENT WHERE ID = TICKETS.ID_SERVICE_ENTITLEMENT) AS TX_ENTITLEMENT "
				+ "												 from TICKETS "
				+ "												 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID "
				+ "												 WHERE TICKETS.ID_ACCOUNT =   " + userId + queryStringPlaceHolder
				+ "												 ) AS TBL "
				+ " WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
		
		
		List<UserTicketsForm> tktList = jdbcTemplate.query(GET_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
	            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	UserTicketsForm tkt = new UserTicketsForm();
	            	
	            	tkt.setID(resulSet.getInt("ID"));
	            	tkt.setTX_SUBJECT(resulSet.getString("SUBJECT"));
	            	tkt.setID_ACCOUNT(resulSet.getInt("ID_ACCOUNT"));
	            	tkt.setTX_ACCOUNT_NAME(resulSet.getString("TX_ACCOUNT_NAME"));
	            	tkt.setTX_TIME(resulSet.getString("TX_TIME"));
	            	tkt.setID_PRIORITY(resulSet.getInt("ID_PRIORITY"));
	            	tkt.setTX_STATUS(resulSet.getString("TX_STATUS"));
	            	tkt.setTX_AGENT_NAME(resulSet.getString("TX_AGENT_NAME"));
	            	tkt.setID_PRODUCT(resulSet.getInt("ID_SERVICE_ENTITLEMENT"));
	            	tkt.setTX_PRODUCT(resulSet.getString("TX_ENTITLEMENT"));
	            	
	            	return tkt;
	            }
	        });
		return tktList;
	}

	public List<UserTicketsForm> getTotalCount(JdbcTemplate jdbcTemplate, int page, String filter, String userId) {
		final String GET_TOTAL_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
				"						 from TICKETS    " + 
				"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + 
				"						 WHERE TICKETS.ID_ACCOUNT = " + userId ;
		
		List<UserTicketsForm> tktTotalList = jdbcTemplate.query(GET_TOTAL_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	UserTicketsForm tkt = new UserTicketsForm();
            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
            	return tkt;
            }
        });
		return tktTotalList;
	}

	public List<UserTicketsForm> getResolvedCount(JdbcTemplate jdbcTemplate, int page, String filter, String userId) {
		final String GET_RESOLVED_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
				"						 from TICKETS    " + 
				"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + 
				"						 WHERE TICKETS.ID_ACCOUNT = " + userId +
				"						 and STATUSTAB.ID = 5" ;
		
		List<UserTicketsForm> tktResolvedList = jdbcTemplate.query(GET_RESOLVED_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	UserTicketsForm tkt = new UserTicketsForm();
            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
            	return tkt;
            }
        });
		return tktResolvedList;
	}

	public List<UserTicketsForm> getResponseCount(JdbcTemplate jdbcTemplate, int page, String filter, String userId) {
		// 
		final String GET_RESPONDED_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
				"						 from TICKETS    " + 
				"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + 
				"						 WHERE TICKETS.ID_ACCOUNT = " + userId +
				"						 and STATUSTAB.ID = 4" ;
		
		List<UserTicketsForm> tktRespList = jdbcTemplate.query(GET_RESPONDED_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	UserTicketsForm tkt = new UserTicketsForm();
            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
            	return tkt;
            }
        });
		return tktRespList;
	}

	public List<UserTicketsForm> getPendingCount(JdbcTemplate jdbcTemplate, int page, String filter, String userId) {
		final String GET_PENDING_TICKETS_QUERY_SQL = "SELECT COUNT(TICKETS.ID) AS COUNT_TOTAL       " + 
				"						 from TICKETS    " + 
				"						 JOIN STATUSTAB on TICKETS.ID_STATUS = STATUSTAB.ID  " + 
				"						 WHERE TICKETS.ID_ACCOUNT = " + userId +
				"						 and STATUSTAB.ID IN (1,2,3,6)" ;
 
		List<UserTicketsForm> tktPendingList = jdbcTemplate.query(GET_PENDING_TICKETS_QUERY_SQL, new RowMapper<UserTicketsForm>() {
            public UserTicketsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	UserTicketsForm tkt = new UserTicketsForm();
            	tkt.setTotalTickets(resulSet.getInt("COUNT_TOTAL"));
            	return tkt;
            }
        });
		return tktPendingList;
	}

}
