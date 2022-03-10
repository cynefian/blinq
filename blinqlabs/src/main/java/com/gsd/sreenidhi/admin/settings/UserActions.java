package com.gsd.sreenidhi.admin.settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.gsd.sreenidhi.user.UserForm;

public class UserActions {

	public List<UsersForm> getUSers(JdbcTemplate jdbcTemplate, String groupid, int page, String userSearchStr) {
		
		 int countOnPage = 25;
		 
		String subQuery = "";
		
		int gid;
		if(groupid!=null && !groupid.trim().equals("")) {
			gid = Integer.parseInt(groupid);
			subQuery = " WHERE GROUPS.ID = " + gid;
		}else {
			subQuery = "";
		}
		
		if(userSearchStr!=null && !userSearchStr.trim().equals("")) {
			String subs = "(ACCOUNTS.TX_EMAIL LIKE '%"+userSearchStr+"%' OR TX_FIRSTNAME LIKE '%"+userSearchStr+"%'   OR TX_LASTNAME LIKE '%"+userSearchStr+"%' )";
			if(subQuery.equals("")) {
				subQuery = " WHERE " + subs;				
			}else {
				subQuery = subQuery + " AND " + subs;
			}

		}
		
		

		String USERS_SQL = 
						"DECLARE @PageNumber AS INT, @RowspPage AS INT"
						+ " SET @PageNumber = " + page
						+ " SET @RowspPage = " + countOnPage
						+ " SELECT * FROM ( "
						+ "									SELECT ROW_NUMBER() OVER(ORDER BY T.ID_USER DESC) AS NUMBER, "
						+ "									T.ID_USER AS ID_USER, "
						+ "									T.TX_FIRSTNAME AS TX_FIRSTNAME, "
						+ "									T.TX_LASTNAME AS TX_LASTNAME, "
						+ "									T.TX_EMAIL AS TX_EMAIL, "
						+ "									T.FL_ENABLED AS FL_ENABLED, "
						+ "									T.TX_WEBSITE AS TX_WEBSITE, "
						+ "									T.TX_BIO AS TX_BIO, "
						+ "									T.TX_CONTACT_NUM AS TX_CONTACT_NUM , "
						+ "									T.TX_USER_IMAGE AS TX_USER_IMAGE "
						+ "									FROM ( "
						+ "										SELECT DISTINCT  USERS.ID AS ID_USER, "
						+ "										USERS.TX_FIRSTNAME AS TX_FIRSTNAME, "
						+ "										USERS.TX_LASTNAME AS TX_LASTNAME, "
						+ "										ACCOUNTS.TX_EMAIL AS TX_EMAIL, "
						+ "										ACCOUNTS.FL_ENABLED AS FL_ENABLED, "
						+ "										USERS.TX_WEBSITE AS TX_WEBSITE, "
						+ "										USERS.TX_BIO AS TX_BIO, "
						+ "										USERS.TX_CONTACT_NUM AS TX_CONTACT_NUM , "
						+ "										USERS.TX_IMAGE AS TX_USER_IMAGE "
						+ "										FROM USERS "
						+ "										LEFT OUTER JOIN ACCOUNTS "
						+ "										ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
						+ "										LEFT OUTER JOIN USER_GROUP_LINK "
						+ "										ON USERS.ID = USER_GROUP_LINK.ID_USER "
						+ "										LEFT OUTER JOIN GROUPS "
						+ "										ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
						+ 	subQuery								
						+ "									) AS T "
						+ "								) AS TBL "
						+ "				 WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
		
		
		
		List<UsersForm> userList = jdbcTemplate.query(USERS_SQL, new RowMapper<UsersForm>() {
			public UsersForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				UsersForm user = new UsersForm();
				
					user.setID_USER(resulSet.getInt("ID_USER"));
					user.setTX_FIRSTNAME(resulSet.getString("TX_FIRSTNAME"));
					user.setTX_LASTNAME(resulSet.getString("TX_LASTNAME"));
					user.setTX_EMAIL(resulSet.getString("TX_EMAIL"));
					user.setFL_ENABLED(resulSet.getInt("FL_ENABLED")==1?true:false);
					user.setTX_WEBSITE(resulSet.getString("TX_WEBSITE"));
					user.setTX_BIO(resulSet.getString("TX_BIO"));
					user.setTX_CONTACT_NUM(resulSet.getString("TX_CONTACT_NUM"));
					user.setTX_USER_IMAGE(resulSet.getString("TX_USER_IMAGE"));
				
					String USER_GROUPS_SQL = "SELECT DISTINCT "
						+ "GROUPS.ID AS ID_GROUP, "
						+ "GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME "
						+ "FROM USERS "
						+ "LEFT OUTER JOIN ACCOUNTS "
						+ "ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
						+ "LEFT OUTER JOIN USER_GROUP_LINK "
						+ "ON USERS.ID = USER_GROUP_LINK.ID_USER "
						+ "LEFT OUTER JOIN GROUPS "
						+ "ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
						+ "WHERE USERS.ID=" + user.getID_USER();
				
				
						List<GroupPermissionsForm> userGroupsList = jdbcTemplate.query(USER_GROUPS_SQL, new RowMapper<GroupPermissionsForm>() {
							public GroupPermissionsForm mapRow(ResultSet resultSet, int rowNum) throws SQLException {
								GroupPermissionsForm group = new GroupPermissionsForm();
								
								group.setID_GROUP(resultSet.getInt("ID_GROUP"));
								group.setTX_GROUP_NAME(resultSet.getString("TX_GROUP_NAME"));
								return group;
							}
						});
					
						user.setUSER_GROUPS(userGroupsList);
				
				return user;
			}
		});
		
		
		return userList;
	}

	public List<UsersForm> getUserProfile(JdbcTemplate jdbcTemplate, String userid) {
		
		String GET_USER_SQL = "SELECT DISTINCT  USERS.ID AS ID_USER, "
				+ "										USERS.TX_FIRSTNAME AS TX_FIRSTNAME, "
				+ "										USERS.TX_LASTNAME AS TX_LASTNAME, "
				+ "										ACCOUNTS.TX_EMAIL AS TX_EMAIL, "
				+ "										ACCOUNTS.FL_ENABLED AS FL_ENABLED, "
				+ "										USERS.TX_WEBSITE AS TX_WEBSITE, "
				+ "										USERS.TX_BIO AS TX_BIO, "
				+ "										USERS.TX_CONTACT_NUM AS TX_CONTACT_NUM , "
				+ "										USERS.TX_IMAGE AS TX_USER_IMAGE "
				+ "										FROM USERS "
				+ "										LEFT OUTER JOIN ACCOUNTS "
				+ "										ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
				+ " WHERE USERS.ID=" +userid;
		
		
				List<UsersForm> userInfoList = jdbcTemplate.query(GET_USER_SQL, new RowMapper<UsersForm>() {
					public UsersForm mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						UsersForm user = new UsersForm();
						
						user.setID_USER(resultSet.getInt("ID_USER"));
						user.setTX_FIRSTNAME(resultSet.getString("TX_FIRSTNAME"));
						user.setTX_LASTNAME(resultSet.getString("TX_LASTNAME"));
						user.setTX_EMAIL(resultSet.getString("TX_EMAIL"));
						user.setFL_ENABLED(resultSet.getInt("FL_ENABLED")==1?true:false);
						user.setTX_USER_IMAGE(resultSet.getString("TX_USER_IMAGE"));
						user.setTX_BIO(resultSet.getString("TX_BIO"));
						user.setTX_WEBSITE(resultSet.getString("TX_WEBSITE"));
						user.setTX_CONTACT_NUM(resultSet.getString("TX_CONTACT_NUM"));
						
						String USER_GROUPS_SQL = "SELECT DISTINCT "
								+ "GROUPS.ID AS ID_GROUP, "
								+ "GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME "
								+ "FROM USERS "
								+ "LEFT OUTER JOIN ACCOUNTS "
								+ "ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
								+ "LEFT OUTER JOIN USER_GROUP_LINK "
								+ "ON USERS.ID = USER_GROUP_LINK.ID_USER "
								+ "LEFT OUTER JOIN GROUPS "
								+ "ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
								+ "WHERE USERS.ID=" + user.getID_USER();
						
						
								List<GroupPermissionsForm> userGroupsList = jdbcTemplate.query(USER_GROUPS_SQL, new RowMapper<GroupPermissionsForm>() {
									public GroupPermissionsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
										GroupPermissionsForm group = new GroupPermissionsForm();
										
										group.setID_GROUP(resulSet.getInt("ID_GROUP"));
										group.setTX_GROUP_NAME(resulSet.getString("TX_GROUP_NAME"));
										return group;
									}
								});
							
								user.setUSER_GROUPS(userGroupsList);
						
						
						return user;
					}
				});
				
				return userInfoList;
	}

	public List<GroupPermissionsForm> getUserLvlPermissions(JdbcTemplate jdbcTemplate, String userid) {

		
		String GET_GROUP_PERMISSIONS_SQL = "SELECT DISTINCT "
				+ "SYSTEM_PERMISSIONS.ID AS ID_PERMISSION, "
				+ "SYSTEM_PERMISSIONS.TX_PERMISSION AS TX_PERMISSION "
				+ "from USERS "
				+ "JOIN USER_PERMISSION_LINK "
				+ "ON USERS.ID = USER_PERMISSION_LINK.ID_USER "
				+ "JOIN SYSTEM_PERMISSIONS "
				+ "ON USER_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "JOIN ACCOUNTS "
				+ "ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
				+ "WHERE USERS.ID = " + userid;
		
		
		List<GroupPermissionsForm> permList = jdbcTemplate.query(GET_GROUP_PERMISSIONS_SQL, new RowMapper<GroupPermissionsForm>() {
			public GroupPermissionsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				GroupPermissionsForm perm = new GroupPermissionsForm();
					perm.setID_PERMISSION(resulSet.getInt("ID_PERMISSION"));
					perm.setTX_PERMISSION(resulSet.getString("TX_PERMISSION"));
				return perm;
			}
		});
				
		return permList;
	
	}

	public List<UserPermissionGroupLvlForm> getGroupLvlPermissions(JdbcTemplate jdbcTemplate, String userid) {
		String GET_USER_GROUP_LVL_PERMISSIONS_SQL = "SELECT Main.TX_PERMISSION, "
				+ "       LEFT(Main.groups,Len(Main.groups)-1) As TX_GROUPS "
				+ "FROM "
				+ "    ( "
				+ "        SELECT DISTINCT ST2.TX_PERMISSION, "
				+ "            ( "
				+ "                SELECT ST1.TX_GROUP_NAME + ',' AS [text()] "
				+ "                FROM (select DISTINCT "
				+ "						SYSTEM_PERMISSIONS.ID AS ID_PERMISSION, "
				+ "						SYSTEM_PERMISSIONS.TX_PERMISSION AS TX_PERMISSION, "
				+ "						GROUPS.ID AS ID_GROUP , "
				+ "						GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME "
				+ "						FROM SYSTEM_PERMISSIONS "
				+ "						JOIN GROUP_PERMISSION_LINK "
				+ "						ON GROUP_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "						JOIN GROUPS "
				+ "						ON GROUPS.ID = GROUP_PERMISSION_LINK.ID_GROUP "
				+ "						JOIN USER_GROUP_LINK "
				+ "						ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
				+ "						JOIN USERS ON USERS.ID = USER_GROUP_LINK.ID_USER "
				+ "						WHERE USERS.ID = " + userid
				+ "						AND GROUP_PERMISSION_LINK.FL_STATUS = 1) AS ST1 "
				+ " "
				+ "                WHERE ST1.TX_PERMISSION = ST2.TX_PERMISSION "
				+ "                ORDER BY ST1.TX_PERMISSION "
				+ "                FOR XML PATH ('') "
				+ "            ) [groups] "
				+ "        FROM (select "
				+ "						SYSTEM_PERMISSIONS.ID AS ID_PERMISSION, "
				+ "						SYSTEM_PERMISSIONS.TX_PERMISSION AS TX_PERMISSION, "
				+ "						GROUPS.ID AS ID_GROUP , "
				+ "						GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME "
				+ "						FROM SYSTEM_PERMISSIONS "
				+ "						JOIN GROUP_PERMISSION_LINK "
				+ "						ON GROUP_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "						JOIN GROUPS "
				+ "						ON GROUPS.ID = GROUP_PERMISSION_LINK.ID_GROUP "
				+ "						JOIN USER_GROUP_LINK "
				+ "						ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
				+ "						JOIN USERS ON USERS.ID = USER_GROUP_LINK.ID_USER "
				+ "						WHERE USERS.ID = " + userid
				+ "						AND GROUP_PERMISSION_LINK.FL_STATUS = 1) AS ST2 "
				+ "    ) [Main]";
		
		
		List<UserPermissionGroupLvlForm> permList = jdbcTemplate.query(GET_USER_GROUP_LVL_PERMISSIONS_SQL, new RowMapper<UserPermissionGroupLvlForm>() {
			public UserPermissionGroupLvlForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				
				
				UserPermissionGroupLvlForm perm = new UserPermissionGroupLvlForm();
					perm.setTX_PERMISSION(resulSet.getString("TX_PERMISSION"));
					
					String groups = resulSet.getString("TX_GROUPS");
					String[] groupList = null;
					
					List<GroupPermissionsForm> gf = new ArrayList();
					if(groups.contains(",")) {
						groupList = groups.split(",");
						
						for(int i=0;i<groupList.length;i++) {
							GroupPermissionsForm gpf = new GroupPermissionsForm();
							gpf.setTX_GROUP_NAME(groupList[i]);
							gf.add(gpf);
						}
					}else {
						GroupPermissionsForm gpf = new GroupPermissionsForm();
						gpf.setTX_GROUP_NAME(groups);
						gf.add(gpf);
					}
					
					perm.setLIST_GROUP(gf);
				return perm;
				
				
			}
		});
		
		return permList;
		
	}

	public void addUserLvlPermission(JdbcTemplate jdbcTemplate, String userid, String permid) {
		final String INSERT_USER_PERMISSION_SQL = "INSERT INTO USER_PERMISSION_LINK(ID_USER, ID_PERMISSION) VALUES(?,?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_USER_PERMISSION_SQL);
				preparedStatement.setInt(1, Integer.parseInt(userid));
				preparedStatement.setInt(2, Integer.parseInt(permid));
				return preparedStatement;
			}
		});
		
	}

	public void deleteUserLvlPermission(JdbcTemplate jdbcTemplate, String userid, String permid) {
final String INSERT_USER_PERMISSION_SQL = "DELETE FROM USER_PERMISSION_LINK WHERE ID_USER=? AND ID_PERMISSION = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_USER_PERMISSION_SQL);
				preparedStatement.setInt(1, Integer.parseInt(userid));
				preparedStatement.setInt(2, Integer.parseInt(permid));
				return preparedStatement;
			}
		});
		
	}

	public List<SimpleGrantedAuthority> getAuthorities(JdbcTemplate jdbcTemplate, String tx_email) {
		
		
		String GET_USER_GROUP_LVL_PERMISSIONS_SQL = "SELECT AC.TX_EMAIL, AR.TX_ROLE AS TX_ROLE FROM AUTHORITY A, ACCOUNTS AC,ACCOUNT_ROLE AR WHERE A.ID_ACCOUNT = AC.ID AND A.ID_ACCOUNT_ROLE = AR.ID AND AC.TX_EMAIL = '" + tx_email + "'";
		
		List<SimpleGrantedAuthority> authenticationList = jdbcTemplate.query(GET_USER_GROUP_LVL_PERMISSIONS_SQL, new RowMapper<SimpleGrantedAuthority>() {
			public SimpleGrantedAuthority mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				String role = resulSet.getString("TX_ROLE");
				SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role);
				return auth;
			}
		});
		
		return authenticationList;
	}

	private List<SimpleGrantedAuthority> getPermissionAuthorities(JdbcTemplate jdbcTemplate, String tx_email) {
			
		String GET_USER_GROUP_LVL_PERMISSIONS_SQL = "SELECT Main.TX_PERMISSION_CODE "
				+ "				 FROM "
				+ "				     ( "
				+ "				         SELECT DISTINCT ST2.TX_PERMISSION_CODE, "
				+ "				             ( "
				+ "				                 SELECT ST1.TX_GROUP_NAME  + ',' AS [text()] "
				+ "				                 FROM (select "
				+ "				 						SYSTEM_PERMISSIONS.ID AS ID_PERMISSION, "
				+ "				 						SYSTEM_PERMISSIONS.TX_PERMISSION_CODE AS TX_PERMISSION_CODE, "
				+ "				 						GROUPS.ID AS ID_GROUP , "
				+ "				 						GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME "
				+ "				 						FROM SYSTEM_PERMISSIONS "
				+ "				 						JOIN GROUP_PERMISSION_LINK "
				+ "				 						ON GROUP_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "				 						JOIN GROUPS "
				+ "				 						ON GROUPS.ID = GROUP_PERMISSION_LINK.ID_GROUP "
				+ "				 						JOIN USER_GROUP_LINK "
				+ "				 						ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
				+ "				 						JOIN USERS ON USERS.ID = USER_GROUP_LINK.ID_USER "
				+ "				 						JOIN ACCOUNTS ON USERS.ID_ACCOUNT  = ACCOUNTS.ID "
				+ "										WHERE ACCOUNTS.TX_EMAIL = '" + tx_email +"' "
				+ "				 						AND GROUP_PERMISSION_LINK.FL_STATUS = 1) AS ST1 "
				+ "				   "
				+ "				                 WHERE ST1.TX_PERMISSION_CODE = ST2.TX_PERMISSION_CODE "
				+ "				                 ORDER BY ST1.TX_PERMISSION_CODE "
				+ "				                 FOR XML PATH ('') "
				+ "				             ) [groups] "
				+ "				         FROM (select "
				+ "				 						SYSTEM_PERMISSIONS.ID AS ID_PERMISSION, "
				+ "				 						SYSTEM_PERMISSIONS.TX_PERMISSION_CODE AS TX_PERMISSION_CODE, "
				+ "				 						GROUPS.ID AS ID_GROUP , "
				+ "				 						GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME "
				+ "				 						FROM SYSTEM_PERMISSIONS "
				+ "				 						JOIN GROUP_PERMISSION_LINK "
				+ "				 						ON GROUP_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "				 						JOIN GROUPS "
				+ "				 						ON GROUPS.ID = GROUP_PERMISSION_LINK.ID_GROUP "
				+ "				 						JOIN USER_GROUP_LINK "
				+ "				 						ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
				+ "				 						JOIN USERS ON USERS.ID = USER_GROUP_LINK.ID_USER "
				+ "										JOIN ACCOUNTS ON USERS.ID_ACCOUNT  = ACCOUNTS.ID "
				+ "				 						WHERE ACCOUNTS.TX_EMAIL = '" + tx_email +"' "
				+ "				 						AND GROUP_PERMISSION_LINK.FL_STATUS = 1) AS ST2 "
				+ "				     ) [Main] "
				+ " "
				+ "					 UNION "
				+ " "
				+ "					 SELECT "
				+ "				 SYSTEM_PERMISSIONS.TX_PERMISSION_CODE AS TX_PERMISSION_CODE "
				+ "				 from USERS "
				+ "				 JOIN USER_PERMISSION_LINK "
				+ "				 ON USERS.ID = USER_PERMISSION_LINK.ID_USER "
				+ "				 JOIN SYSTEM_PERMISSIONS "
				+ "				 ON USER_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "				 JOIN ACCOUNTS "
				+ "				 ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
				+ "				 WHERE ACCOUNTS.TX_EMAIL = '" + tx_email +"'";
		
		List<SimpleGrantedAuthority> authenticationList = jdbcTemplate.query(GET_USER_GROUP_LVL_PERMISSIONS_SQL, new RowMapper<SimpleGrantedAuthority>() {
			public SimpleGrantedAuthority mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				String role = resulSet.getString("TX_PERMISSION_CODE");
				SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role);
				return auth;
			}
		});
		
		return authenticationList;
	}
	
	public void loadUserPermissions(JdbcTemplate jdbcTemplate) {

		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}
		
		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		
		List<SimpleGrantedAuthority> updatedAuthorities = getPermissionAuthorities(jdbcTemplate, auth.getName());
		updatedAuthorities.addAll(oldAuthorities);
		
	    Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
	    SecurityContextHolder.getContext().setAuthentication(newAuth);
	    
	}
	
	

}
