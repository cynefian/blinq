package com.gsd.sreenidhi.admin.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gsd.sreenidhi.admin.tickets.WorkLogForm;
import com.gsd.sreenidhi.billing.BillingCycleForm;
import com.gsd.sreenidhi.billing.BillingForm;
import com.gsd.sreenidhi.commons.AttachmentForm;
import com.gsd.sreenidhi.commons.serviceEntitlement.TierRolloverForm;
import com.gsd.sreenidhi.security.licensing.License;
import com.gsd.sreenidhi.utils.CalendarUtils;

public class EntitlementActions {

	public List<ServiceTypeForm> getEntitlementTypes(JdbcTemplate jdbcTemplate) {
		String SELECT_ENTITLEMENT_TYPES_SQL = "SELECT  "
				+ " 	 ID, TX_ENTITLEMENT_TYPE,  TX_DESCRIPTION, FL_ROLLOVER, FL_MONTHLY_BILLING "
				+ " FROM   SERVICE_ENTITLEMENT_TYPE ";
				
		
		
		List<ServiceTypeForm> typeList = jdbcTemplate.query(SELECT_ENTITLEMENT_TYPES_SQL, new RowMapper<ServiceTypeForm>() {
			public ServiceTypeForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				ServiceTypeForm type = new ServiceTypeForm();
				type.setID(resulSet.getInt("ID"));
				type.setTX_SERVICE_TYPE_NAME(resulSet.getString("TX_ENTITLEMENT_TYPE"));
				type.setTX_SERVICE_TYPE_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
				type.setFL_ALLOW_ROLLOVER(resulSet.getInt("FL_ROLLOVER")==1?true:false);
				type.setFL_MONTHLY_BILLING(resulSet.getInt("FL_MONTHLY_BILLING")==1?true:false);
				return type;
			}
		});
		return typeList;
	}

	public void insertEntitlementType(JdbcTemplate jdbcTemplate,
			@Valid ServiceTypeForm serviceTypeBean) {
		final KeyHolder typeKey = new GeneratedKeyHolder();
		final String INSERT_ENTITLEMENT_TYPE_SQL = "INSERT INTO SERVICE_ENTITLEMENT_TYPE (TX_ENTITLEMENT_TYPE,  TX_DESCRIPTION, FL_ROLLOVER, FL_MONTHLY_BILLING) VALUES (?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENTITLEMENT_TYPE_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, serviceTypeBean.getTX_SERVICE_TYPE_NAME());
				preparedStatement.setString(2, serviceTypeBean.getTX_SERVICE_TYPE_DESCRIPTION());
				preparedStatement.setInt(3, serviceTypeBean.isFL_ALLOW_ROLLOVER()==true?1:0);
				preparedStatement.setInt(4, serviceTypeBean.isFL_MONTHLY_BILLING()==true?1:0);
				
				return preparedStatement;
			}
		}, typeKey);
	}

	public void updateEntitlementTypeRollable(JdbcTemplate jdbcTemplate, String id, String value) {
		final String UPDATE_ENTITLEMENT_TYPE_SQL = "UPDATE SERVICE_ENTITLEMENT_TYPE SET FL_ROLLOVER = ? WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENTITLEMENT_TYPE_SQL);
				preparedStatement.setInt(1, Integer.parseInt(value)==1?0:1);
				preparedStatement.setInt(2, Integer.parseInt(id));
				return preparedStatement;
			}
		});
	}

	public void updateEntitlementTypeMonthly(JdbcTemplate jdbcTemplate, String id, String value) {
		final String UPDATE_ENTITLEMENT_TYPE_SQL = "UPDATE SERVICE_ENTITLEMENT_TYPE SET FL_MONTHLY_BILLING = ? WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENTITLEMENT_TYPE_SQL);
				preparedStatement.setInt(1, Integer.parseInt(value)==1?0:1);
				preparedStatement.setInt(2, Integer.parseInt(id));
				return preparedStatement;
			}
		});
	}
	public List<SENForm> getEntitlementList(HttpSession session, JdbcTemplate jdbcTemplate, boolean filterUser, boolean usePageFilters, String page, String filter) {
		
		int pg=0;
		String statusQuery = "";
		boolean useSubQuery;
		boolean activeWhere = false;
		
		if("ACTIVE".equalsIgnoreCase(filter)) {
			statusQuery = statusQuery + " WHERE SERVICE_ENTITLEMENT.FL_ACTIVE = 1 ";
			activeWhere = true;
		}else if("INACTIVE".equalsIgnoreCase(filter)){
			statusQuery = statusQuery + " WHERE SERVICE_ENTITLEMENT.FL_ACTIVE = 0 ";
			activeWhere = true;
		}else {
			statusQuery = "";
		}
		
		if(page==null || "".equals(page)) {
			pg=1;
		}else {
			pg = Integer.parseInt(page);
		}
	 int countOnPage = 10;
	 
	 
	 
	 final String userId = session.getAttribute("userId").toString().trim();
	 final int usrId = Integer.parseInt(userId);
	
	 String searchByUser = "";
	 if (filterUser) {
		 if(activeWhere) {
			 searchByUser = " AND ACCOUNTS.ID = " + usrId ;	 
		 }else {
			 searchByUser = " WHERE ACCOUNTS.ID = " + usrId ;
		 }
		 
	 }
	 
	 String queryStringPlaceHolder = statusQuery + searchByUser;
	  
		int totalCount =  getEntitlementListCount(session, jdbcTemplate, filterUser, page, filter);
		double rounder = (double)totalCount/(double) countOnPage;
	 	int pages = (int) Math.ceil(rounder);
	 
	 
	 	String BLOG_CATEGORIES_SQL = "";
	 if(usePageFilters) {
		 BLOG_CATEGORIES_SQL = 
					" DECLARE @PageNumber AS INT, @RowspPage AS INT"
							 + " SET @PageNumber = " + pg
							 + " SET @RowspPage =  " + countOnPage
							 + " SELECT * FROM ( "
					+ " SELECT  "
					+ "  ROW_NUMBER() OVER(ORDER BY SERVICE_ENTITLEMENT.ID DESC) AS NUMBER, "		 
					+ " 	  SERVICE_ENTITLEMENT.ID          AS ID, "
					+ " 	  SERVICE_ENTITLEMENT.ID_ACCOUNT          AS ID_ACCOUNT, "
					+ "       ACCOUNTS.TX_EMAIL 		AS TX_ACCOUNT_EMAIL, "
					+ " 	  SERVICE_ENTITLEMENT.TX_ENTITLEMENT          AS TX_ENTITLEMENT, "
					+ " 	  SERVICE_ENTITLEMENT.TX_ENTITLEMENT_DESCRIPTION          AS TX_ENTITLEMENT_DESCRIPTION, "
					+ " 	  SERVICE_ENTITLEMENT.TS_START          AS TS_START, "
					+ " 	  SERVICE_ENTITLEMENT.TX_END_CRITERIA          AS TX_DURATION, "
					+ "       SERVICE_ENTITLEMENT.TS_END AS TS_END, "
					+ "       SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE   AS ID_ENTITLEMENT_TYPE, "
					+ "       SERVICE_ENTITLEMENT_TYPE.TX_ENTITLEMENT_TYPE   AS TX_ENTITLEMENT_TYPE, "
					+ "       SERVICE_ENTITLEMENT_TYPE.TX_DESCRIPTION   AS TX_ENTITLEMENT_TYPE_DESCRIPTION, "
					+ "       SERVICE_ENTITLEMENT.FL_ACTIVE AS FL_ACTIVE "
					+ " FROM   SERVICE_ENTITLEMENT "
					+ " JOIN SERVICE_ENTITLEMENT_TYPE ON SERVICE_ENTITLEMENT_TYPE.ID = SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE"
					+ " JOIN ACCOUNTS ON ACCOUNTS.ID =  SERVICE_ENTITLEMENT.ID_ACCOUNT"
					+ queryStringPlaceHolder 
					+ "												 ) AS TBL "
					 + " WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
		 
	 }else {
		 BLOG_CATEGORIES_SQL = 
					" SELECT  "
					+ " 	  SERVICE_ENTITLEMENT.ID          AS ID, "
					+ " 	  SERVICE_ENTITLEMENT.ID_ACCOUNT          AS ID_ACCOUNT, "
					+ "       ACCOUNTS.TX_EMAIL 		AS TX_ACCOUNT_EMAIL, "
					+ " 	  SERVICE_ENTITLEMENT.TX_ENTITLEMENT          AS TX_ENTITLEMENT, "
					+ " 	  SERVICE_ENTITLEMENT.TX_ENTITLEMENT_DESCRIPTION          AS TX_ENTITLEMENT_DESCRIPTION, "
					+ " 	  SERVICE_ENTITLEMENT.TS_START          AS TS_START, "
					+ " 	  SERVICE_ENTITLEMENT.TX_END_CRITERIA          AS TX_DURATION, "
					+ "       SERVICE_ENTITLEMENT.TS_END AS TS_END, "
					+ "       SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE   AS ID_ENTITLEMENT_TYPE, "
					+ "       SERVICE_ENTITLEMENT_TYPE.TX_ENTITLEMENT_TYPE   AS TX_ENTITLEMENT_TYPE, "
					+ "       SERVICE_ENTITLEMENT_TYPE.TX_DESCRIPTION   AS TX_ENTITLEMENT_TYPE_DESCRIPTION, "
					+ "       SERVICE_ENTITLEMENT.FL_ACTIVE AS FL_ACTIVE "
					+ " FROM   SERVICE_ENTITLEMENT "
					+ " JOIN SERVICE_ENTITLEMENT_TYPE ON SERVICE_ENTITLEMENT_TYPE.ID = SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE"
					+ " JOIN ACCOUNTS ON ACCOUNTS.ID =  SERVICE_ENTITLEMENT.ID_ACCOUNT"
					+ queryStringPlaceHolder 
					;
	 }
	 
		
		
			
		
		
		List<SENForm> SENFormList = jdbcTemplate.query(BLOG_CATEGORIES_SQL, new RowMapper<SENForm>() {
			public SENForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				SENForm sen = new SENForm();
				sen.setID(resulSet.getInt("ID"));
				sen.setID_ACCOUNT(resulSet.getInt("ID_ACCOUNT"));
				sen.setTX_ACCOUNT_EMAIL(resulSet.getString("TX_ACCOUNT_EMAIL"));
				sen.setTX_ENTITLEMENT(resulSet.getString("TX_ENTITLEMENT"));
				sen.setTX_ENTITLEMENT_DESCRIPTION(resulSet.getString("TX_ENTITLEMENT_DESCRIPTION"));
				sen.setTS_START(resulSet.getString("TS_START"));
				sen.setTX_DURATION(resulSet.getString("TX_DURATION"));
				sen.setTS_END(resulSet.getString("TS_END"));
				sen.setID_ENTITLEMENT_TYPE(resulSet.getInt("ID_ENTITLEMENT_TYPE"));
				sen.setTX_ENTITLEMENT_TYPE(resulSet.getString("TX_ENTITLEMENT_TYPE"));
				sen.setTX_ENTITLEMENT_TYPE_DESCRIPTION(resulSet.getString("TX_ENTITLEMENT_TYPE_DESCRIPTION"));
				sen.setFL_ACTIVE(resulSet.getInt("FL_ACTIVE"));
				return sen;
			}
		});
		return SENFormList;
	}

	
	public int getEntitlementListCount(HttpSession session, JdbcTemplate jdbcTemplate, boolean filterUser, String page, String filter) {
		
		 int countOnPage = 10;
		 
		 final String userId = session.getAttribute("userId").toString().trim();
		 final int usrId = Integer.parseInt(userId);
		 
		 String statusQuery = "";
		 boolean activeWhere = false;
		 if("ACTIVE".equalsIgnoreCase(filter)) {
				statusQuery = statusQuery + " WHERE SERVICE_ENTITLEMENT.FL_ACTIVE = 0 ";
				activeWhere = true;
			}else if("INACTIVE".equalsIgnoreCase(filter)){
				statusQuery = statusQuery + " WHERE SERVICE_ENTITLEMENT.FL_ACTIVE = 0 ";
				activeWhere = true;
			}else {
				statusQuery = "";
			}
		 
		 String searchByUser = "";
		 if (filterUser) {
			 if(activeWhere) {
				 searchByUser = " AND ACCOUNTS.ID = " + usrId ;	 
			 }else {
				 searchByUser = " WHERE ACCOUNTS.ID = " + usrId ;
			 }
			 
		 }
		
		 String queryStringPlaceHolder = statusQuery + searchByUser;
		 
		 final String GET_ENTITLEMENT_COUNT_SQL =  "SELECT COUNT(*) AS COUNT "
				 + " FROM   SERVICE_ENTITLEMENT "
					+ " JOIN SERVICE_ENTITLEMENT_TYPE ON SERVICE_ENTITLEMENT_TYPE.ID = SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE"
					+ " JOIN ACCOUNTS ON ACCOUNTS.ID =  SERVICE_ENTITLEMENT.ID_ACCOUNT"
					+ queryStringPlaceHolder ;
			
			List<SENForm> sencountList = jdbcTemplate.query(GET_ENTITLEMENT_COUNT_SQL, new RowMapper<SENForm>() {
	            public SENForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	            	SENForm sen = new SENForm();
	            	
	            	sen.setEntitlementCount(resulSet.getInt("COUNT"));
	            	return sen;
	            }
	        });
		 	
	 		int totalCount =  sencountList.get(0).getEntitlementCount();
	 		double rounder = (double)totalCount/(double) countOnPage;
		 	int pages = (int) Math.ceil(rounder);
		 	return totalCount;
		
	}
	
	
	public List<SENForm> getSENdetails(HttpSession session, JdbcTemplate jdbcTemplate, String id) {
		String SEN_SQL = 
				 " SELECT  "
				+ " 	  SERVICE_ENTITLEMENT.ID          AS ID, "
				+ " 	  SERVICE_ENTITLEMENT.ID_ACCOUNT          AS ID_ACCOUNT, "
				+ "       ACCOUNTS.TX_EMAIL 		AS TX_ACCOUNT_EMAIL, "
				+ " 	  SERVICE_ENTITLEMENT.TX_ENTITLEMENT          AS TX_ENTITLEMENT, "
				+ " 	  SERVICE_ENTITLEMENT.TX_ENTITLEMENT_DESCRIPTION          AS TX_ENTITLEMENT_DESCRIPTION, "
				+ " 	  SERVICE_ENTITLEMENT.TS_START          AS TS_START, "
				+ " 	  SERVICE_ENTITLEMENT.TX_END_CRITERIA          AS TX_DURATION, "
				+ "       SERVICE_ENTITLEMENT.TS_END AS TS_END, "
				+ "       SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE   AS ID_ENTITLEMENT_TYPE, "
				+ "       SERVICE_ENTITLEMENT_TYPE.TX_ENTITLEMENT_TYPE   AS TX_ENTITLEMENT_TYPE, "
				+ "       SERVICE_ENTITLEMENT_TYPE.TX_DESCRIPTION   AS TX_ENTITLEMENT_TYPE_DESCRIPTION, "
				+ "       SERVICE_ENTITLEMENT.FL_ACTIVE AS FL_ACTIVE "
				+ " FROM   SERVICE_ENTITLEMENT "
				+ " JOIN SERVICE_ENTITLEMENT_TYPE ON SERVICE_ENTITLEMENT_TYPE.ID = SERVICE_ENTITLEMENT.ID_ENTITLEMENT_TYPE"
				+ " JOIN ACCOUNTS ON ACCOUNTS.ID =  SERVICE_ENTITLEMENT.ID_ACCOUNT"
				+ " WHERE SERVICE_ENTITLEMENT.ID = " + id;
		
		
		List<SENForm> SENFormList = jdbcTemplate.query(SEN_SQL, new RowMapper<SENForm>() {
			public SENForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				SENForm sen = new SENForm();
				sen.setID(resulSet.getInt("ID"));
				sen.setID_ACCOUNT(resulSet.getInt("ID_ACCOUNT"));
				sen.setTX_ACCOUNT_EMAIL(resulSet.getString("TX_ACCOUNT_EMAIL"));
				sen.setTX_ENTITLEMENT(resulSet.getString("TX_ENTITLEMENT"));
				sen.setTX_ENTITLEMENT_DESCRIPTION(resulSet.getString("TX_ENTITLEMENT_DESCRIPTION"));
				sen.setTS_START(resulSet.getString("TS_START"));
				sen.setTX_DURATION(resulSet.getString("TX_DURATION"));
				sen.setTS_END(resulSet.getString("TS_END"));
				sen.setID_ENTITLEMENT_TYPE(resulSet.getInt("ID_ENTITLEMENT_TYPE"));
				sen.setTX_ENTITLEMENT_TYPE(resulSet.getString("TX_ENTITLEMENT_TYPE"));
				sen.setTX_ENTITLEMENT_TYPE_DESCRIPTION(resulSet.getString("TX_ENTITLEMENT_TYPE_DESCRIPTION"));
				sen.setFL_ACTIVE(resulSet.getInt("FL_ACTIVE"));
				return sen;
			}
		});
		return SENFormList;
	}
	
	public List<SENRatesForm> getSENRates(HttpSession session, JdbcTemplate jdbcTemplate, String id) {
		
		String SEN_RATES_SQL = 
				 " SELECT  "
				+ " 	  ID, "
				+ " 	  ID_ENTITLEMENT, "
				+ "       TX_TIER, "
				+ "       TX_LIMIT, "
				+ " 	  TX_COST "
				+ " FROM   SERVICE_ENTITLEMENT_RATES "
				+ " WHERE ID_ENTITLEMENT = " + id;
		
		List<SENRatesForm> SENRatesFormList = jdbcTemplate.query(SEN_RATES_SQL, new RowMapper<SENRatesForm>() {
			public SENRatesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				SENRatesForm sen = new SENRatesForm();
				sen.setID(resulSet.getInt("ID"));
				sen.setID_ENTITLEMENT(resulSet.getInt("ID_ENTITLEMENT"));
				sen.setTX_TIER(resulSet.getString("TX_TIER"));
				sen.setTX_COST(resulSet.getString("TX_COST"));
				sen.setTX_LIMIT(resulSet.getString("TX_LIMIT"));
				return sen;
			}
		});
		return SENRatesFormList;
		
	}

	public List<AttachmentForm> getSENAttachments(HttpSession session, JdbcTemplate jdbcTemplate, String id, String page) {
		int pg;
		if("".equals(page)|| page==null) {
			pg = 1;
		}else {
			pg = Integer.parseInt(page);
		}
		
		int countOnPage = 10;
		String SEN_ATTACH_SQL = 
				" DECLARE @PageNumber AS INT, @RowspPage AS INT"
						 + " SET @PageNumber = " + pg
						 + " SET @RowspPage =  " + countOnPage
						 + "SELECT * FROM ( "
								 + "				  SELECT "
								 + "				   ROW_NUMBER() OVER(ORDER BY T1.ID DESC) AS NUMBER, "
								 + "				   T1.* FROM "
								 + "( SELECT "
								 + "	ATTACHMENTS.ID, "
								 + "	ATTACHMENTS.TX_ATTACHMENT, "
								 + "	ATTACHMENTS.TX_NAME, "
								 + "	ATTACHMENTS.TX_TYPE, "
								 + "	ATTACHMENTS.TX_SIZE "
								 + "	FROM   SERVICE_ENTITLEMENT_ATTACHMENTS "
								 + "	JOIN ATTACHMENTS ON ATTACHMENTS.ID = SERVICE_ENTITLEMENT_ATTACHMENTS.ID_ATTACHMENT "
								 + "	WHERE SERVICE_ENTITLEMENT_ATTACHMENTS.ID_ENTITLEMENT =   " + id
								 + "UNION "
								 + "SELECT "
								 + "	ATTACHMENTS.ID, "
								 + "	ATTACHMENTS.TX_ATTACHMENT, "
								 + "	ATTACHMENTS.TX_NAME, "
								 + "	ATTACHMENTS.TX_TYPE, "
								 + "	ATTACHMENTS.TX_SIZE "
								 + "	FROM SERVICE_BILLING_ATTACHMENTS "
								 + "	JOIN ATTACHMENTS ON SERVICE_BILLING_ATTACHMENTS.ID_ATTACHMENT = ATTACHMENTS.ID "
								 + "	JOIN SERVICE_BILLING_CYCLE ON SERVICE_BILLING_CYCLE.ID = SERVICE_BILLING_ATTACHMENTS.ID_SERVICE_BILLING_CYCLE "
								 + "	WHERE SERVICE_BILLING_CYCLE.ID_ENTITLEMENT = " + id
								 + "	) AS T1 "
								 + "	 ) AS TBL "
								 + "	   WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage +  1) AND (@PageNumber * @RowspPage)";
			
		
		List<AttachmentForm> SENAttachFormList = jdbcTemplate.query(SEN_ATTACH_SQL, new RowMapper<AttachmentForm>() {
			public AttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				AttachmentForm sen = new AttachmentForm();
				sen.setID(resulSet.getInt("ID"));
				sen.setTX_DATA(resulSet.getString("TX_ATTACHMENT"));
				sen.setTX_NAME(resulSet.getString("TX_NAME"));
				sen.setTX_TYPE(resulSet.getString("TX_TYPE"));
				sen.setTX_SIZE(resulSet.getString("TX_SIZE"));
				return sen;
			}
		});
		return SENAttachFormList;
	}
	
	
	public int getSENAttachmentsCount(HttpSession session, JdbcTemplate jdbcTemplate, String id) {
	
		String SEN_ATTACH_SQL = "SELECT Count(*) AS ATTACH_COUNT "
				+ "FROM   (SELECT ATTACHMENTS.ID, "
				+ "               ATTACHMENTS.TX_ATTACHMENT, "
				+ "               ATTACHMENTS.TX_NAME, "
				+ "               ATTACHMENTS.TX_TYPE, "
				+ "               ATTACHMENTS.TX_SIZE "
				+ "        FROM   SERVICE_ENTITLEMENT_ATTACHMENTS "
				+ "               JOIN ATTACHMENTS "
				+ "                 ON ATTACHMENTS.ID = "
				+ "SERVICE_ENTITLEMENT_ATTACHMENTS.ID_ATTACHMENT "
				+ "        WHERE  SERVICE_ENTITLEMENT_ATTACHMENTS.ID_ENTITLEMENT = " + id
				+ "        UNION "
				+ "        SELECT ATTACHMENTS.ID, "
				+ "               ATTACHMENTS.TX_ATTACHMENT, "
				+ "               ATTACHMENTS.TX_NAME, "
				+ "               ATTACHMENTS.TX_TYPE, "
				+ "               ATTACHMENTS.TX_SIZE "
				+ "        FROM   SERVICE_BILLING_ATTACHMENTS "
				+ "               JOIN ATTACHMENTS "
				+ "                 ON SERVICE_BILLING_ATTACHMENTS.ID_ATTACHMENT = ATTACHMENTS.ID "
				+ "               JOIN SERVICE_BILLING_CYCLE "
				+ "                 ON SERVICE_BILLING_CYCLE.ID = "
				+ "                    SERVICE_BILLING_ATTACHMENTS.ID_SERVICE_BILLING_CYCLE "
				+ "        WHERE  SERVICE_BILLING_CYCLE.ID_ENTITLEMENT = "+id+") AS T1";
				
			
		
		List<AttachmentForm> SENAttachFormList = jdbcTemplate.query(SEN_ATTACH_SQL, new RowMapper<AttachmentForm>() {
			public AttachmentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				AttachmentForm sen = new AttachmentForm();
				sen.setAttachCount(resulSet.getInt("ATTACH_COUNT"));
				return sen;
			}
		});
		return SENAttachFormList.get(0).getAttachCount();
	}

	public List<WorkLogForm> getEntitlementActivities(HttpSession session, JdbcTemplate jdbcTemplate, String id, String startDate, String endDate) {

		final String GET_SEN_WORKLOG= 
				 " SELECT  "
				 + " TICKET_WORK_LOG.ID, "
				 + " TICKET_WORK_LOG.ID_TICKET, "
				 + " TICKET_WORK_LOG.ID_USER, "
				 + " TICKET_WORK_LOG.TX_WORKLOG, "
				 + " TICKET_WORK_LOG.TX_DATETIME,"
				 + " TICKET_WORK_LOG.TX_DESCRIPTION,"
				 + " USERS.TX_FIRSTNAME, "
				 + " USERS.TX_LASTNAME "
			+ " FROM TICKET_WORK_LOG "
			+ " JOIN USERS ON TICKET_WORK_LOG.ID_USER = USERS.ID "
			+ " JOIN TICKETS ON TICKETS.ID = TICKET_WORK_LOG.ID_TICKET"
	 		+ " WHERE TICKETS.ID_SERVICE_ENTITLEMENT = " + id ; 
	 		
	 		List<WorkLogForm> senActivityList = jdbcTemplate.query(GET_SEN_WORKLOG, new RowMapper<WorkLogForm>() {
				public WorkLogForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					WorkLogForm wrk = new WorkLogForm();
					wrk.setID(resulSet.getInt("ID"));
					wrk.setID_TICKET(resulSet.getInt("ID_TICKET"));
					wrk.setID_USER(resulSet.getInt("ID_USER"));
					wrk.setTX_WORKLOG(resulSet.getString("TX_WORKLOG"));
					wrk.setTX_USER(resulSet.getString("TX_FIRSTNAME") + " " + resulSet.getString("TX_LASTNAME"));
					wrk.setTX_DATE(resulSet.getString("TX_DATETIME"));
					wrk.setTX_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
					return wrk;
				}
			});
	 		
	 		String removeIndexes = "";
	 		
	 		for (int i=0;i<senActivityList.size();i++) {
	 			try {
	 				if(startDate!=null && endDate!=null) {
	 					if(CalendarUtils.compareDates(CalendarUtils.dateToStringDateTimeReadable(CalendarUtils.stringToDateOnly(senActivityList.get(i).getTX_DATE(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd"), startDate)<0 
	 							|| CalendarUtils.compareDates(CalendarUtils.dateToStringDateTimeReadable(CalendarUtils.stringToDateOnly(senActivityList.get(i).getTX_DATE(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd"), endDate)>0) {
	 						removeIndexes = removeIndexes +","+i;
	 					}
 					}else if(startDate!=null) {
 						if(CalendarUtils.compareDates(CalendarUtils.dateToStringDateTimeReadable(CalendarUtils.stringToDateOnly(senActivityList.get(i).getTX_DATE(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd"), startDate)<0) {
 							removeIndexes = removeIndexes +","+i;
 						}
 					}else if(endDate!=null) {
	 					if(CalendarUtils.compareDates(CalendarUtils.dateToStringDateTimeReadable(CalendarUtils.stringToDateOnly(senActivityList.get(i).getTX_DATE(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd"), endDate)>0) {
	 						removeIndexes = removeIndexes +","+i;
						}
 					}
 				} catch (ParseException e) {
					e.printStackTrace();
				}
	 		}
	 		
	 		String[] idxArray = removeIndexes.split(",");

	 		
	 		for(int j=idxArray.length;j>0;j--) {
	 			if(idxArray[j-1]!=null && !"".equals(idxArray[j-1])) {
	 				int idxr = Integer.parseInt(idxArray[j-1]);
	 				senActivityList.remove(idxr);
	 			}
	 		}
	 		
	 		return senActivityList;
		
	}

	public List<BillingCycleForm> getEntitlementBillingCycles(HttpSession session, JdbcTemplate jdbcTemplate, String id) {
		final String GET_SEN_WORKLOG= 
				 " SELECT  ID, "
				 + " TS_START_DATE, "
				 + " TS_END_DATE, "
				 + " FL_COMPLETE "
			+ " FROM SERVICE_BILLING_CYCLE "
			+ " WHERE ID_ENTITLEMENT = " + id ; 
	 		
	 		List<BillingCycleForm> senBillingList = jdbcTemplate.query(GET_SEN_WORKLOG, new RowMapper<BillingCycleForm>() {
				public BillingCycleForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					BillingCycleForm bill = new BillingCycleForm();
					bill.setID(resulSet.getInt("ID"));
					bill.setTS_START_DATE(resulSet.getString("TS_START_DATE"));
					bill.setTS_END_DATE(resulSet.getString("TS_END_DATE"));
					bill.setFL_COMPLETE(resulSet.getInt("FL_COMPLETE")==1?true:false);
					bill.setID_ENTITLEMENT(Integer.parseInt(id));
					
					return bill;
				}
			});
	 		
	 		
		return senBillingList;
	}

	public List<TierRolloverForm> getEntitlementRollOvers(JdbcTemplate jdbcTemplate, String id) {
		final String GET_ROLLOVER_HOURS = "SELECT SERVICE_TIER_ROLLOVER.ID             AS ID_SERVICE_TIER_ROLLOVER, "
				+ "       SERVICE_TIER_ROLLOVER.NUM_ROLLOVER, "
				+ "       SERVICE_TIER_ROLLOVER.NUM_BALANCE, "
				+ "       SERVICE_BILLING_CYCLE.ID             AS ID_SERVICE_BILLING_CYCLE, "
				+ "       SERVICE_BILLING_CYCLE.ID_ENTITLEMENT AS ID_ENTITLEMENT "
				+ "FROM   SERVICE_TIER_ROLLOVER "
				+ "       JOIN SERVICE_BILLING_CYCLE "
				+ "         ON SERVICE_BILLING_CYCLE.ID = "
				+ "            SERVICE_TIER_ROLLOVER.ID_SERVICE_BILLING_CYCLE "
				+ "WHERE  SERVICE_TIER_ROLLOVER.FL_ACTIVE = 1 "
				+ "       AND SERVICE_BILLING_CYCLE.ID_ENTITLEMENT = " + id;
		
		List<TierRolloverForm> senRolloverList = jdbcTemplate.query(GET_ROLLOVER_HOURS, new RowMapper<TierRolloverForm>() {
			public TierRolloverForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				TierRolloverForm tier = new TierRolloverForm();
				tier.setID_SERVICE_TIER_ROLLOVER(resulSet.getInt("ID_SERVICE_TIER_ROLLOVER"));
				tier.setID_BILLING_CYCLE(resulSet.getInt("ID_SERVICE_BILLING_CYCLE"));
				tier.setID_ENTITLEMT(resulSet.getInt("ID_ENTITLEMENT"));
				tier.setNUM_ROLLOVER_ORIG(resulSet.getInt("NUM_ROLLOVER"));
				tier.setNUM_ROLLOVER_BALANCE(resulSet.getInt("NUM_BALANCE"));
				return tier;
			}
		});
		return senRolloverList;
	}
	
	
	public List<TierRolloverForm> getTicketEntitlementRollOvers(HttpSession session, JdbcTemplate jdbcTemplate, String id) {
		final String GET_ROLLOVER_HOURS = "SELECT SERVICE_TIER_ROLLOVER.ID             AS ID_SERVICE_TIER_ROLLOVER, "
				+ " SERVICE_TIER_ROLLOVER.ID_SERVICE_BILLING_CYCLE AS ID_SERVICE_BILLING_CYCLE, "
				+ " SERVICE_TIER_ROLLOVER.NUM_ROLLOVER, "
				+ " SERVICE_TIER_ROLLOVER.NUM_BALANCE, "
				+ " SERVICE_TIER_ROLLOVER.FL_ACTIVE, "
				+ " SERVICE_ENTITLEMENT.ID AS ID_ENTITLEMENT "
				+ " FROM "
				+ " SERVICE_TIER_ROLLOVER "
				+ " JOIN SERVICE_BILLING_CYCLE on SERVICE_BILLING_CYCLE.ID = SERVICE_TIER_ROLLOVER.ID_SERVICE_BILLING_CYCLE "
				+ " JOIN SERVICE_ENTITLEMENT ON SERVICE_ENTITLEMENT.ID  = SERVICE_BILLING_CYCLE.ID_ENTITLEMENT "
				+ " JOIN TICKETS ON TICKETS.ID_SERVICE_ENTITLEMENT = SERVICE_ENTITLEMENT.ID "
				+ " WHERE "
				+ " SERVICE_TIER_ROLLOVER.FL_ACTIVE = 1 "
				+ " AND TICKETS.ID = " + id
				+ " ORDER BY ID_SERVICE_TIER_ROLLOVER";
		
		List<TierRolloverForm> senRolloverList = jdbcTemplate.query(GET_ROLLOVER_HOURS, new RowMapper<TierRolloverForm>() {
			public TierRolloverForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				TierRolloverForm tier = new TierRolloverForm();
				tier.setID_SERVICE_TIER_ROLLOVER(resulSet.getInt("ID_SERVICE_TIER_ROLLOVER"));
				tier.setID_BILLING_CYCLE(resulSet.getInt("ID_SERVICE_BILLING_CYCLE"));
				tier.setID_ENTITLEMT(resulSet.getInt("ID_ENTITLEMENT"));
				tier.setNUM_ROLLOVER_ORIG(resulSet.getInt("NUM_ROLLOVER"));
				tier.setNUM_ROLLOVER_BALANCE(resulSet.getInt("NUM_BALANCE"));
				tier.setFL_ACTIVE(resulSet.getInt("FL_ACTIVE")==1?true:false);
				return tier;
			}
		});
		return senRolloverList;
	}

	public void updateEntitlementTypeRollableBalance(JdbcTemplate jdbcTemplate, int id_SERVICE_TIER_ROLLOVER,
			int ticketSenRolloverCycleHours) {
		final String UPDATE_ENTITLEMENT_TYPE_SQL = "UPDATE SERVICE_TIER_ROLLOVER SET NUM_BALANCE = ? WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENTITLEMENT_TYPE_SQL);
				preparedStatement.setInt(1, ticketSenRolloverCycleHours);
				preparedStatement.setInt(2, id_SERVICE_TIER_ROLLOVER);
				return preparedStatement;
			}
		});
		
	}

	public void updateEntitlementTypeRollableFlag(JdbcTemplate jdbcTemplate, int id_SERVICE_TIER_ROLLOVER, int value) {
		final String UPDATE_ENTITLEMENT_TYPE_SQL = "UPDATE SERVICE_TIER_ROLLOVER SET FL_ACTIVE = ? WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENTITLEMENT_TYPE_SQL);
				preparedStatement.setInt(1, value);
				preparedStatement.setInt(2, id_SERVICE_TIER_ROLLOVER);
				return preparedStatement;
			}
		});
		
	}
	
	
}
