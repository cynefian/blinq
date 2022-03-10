package com.gsd.sreenidhi.admin.settings;

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

public class GroupActions {

	public List<GroupPermissionsForm> getGroupPermissions(JdbcTemplate jdbcTemplate, int id) {
	
		String GET_GROUP_PERMISSIONS_SQL = "SELECT DISTINCT GROUPS.ID                           AS ID_GROUP, "
				+ "                GROUPS.TX_GROUP_NAME                AS TX_GROUP_NAME, "
				+ "                PERMISSION_SECTIONS.ID              AS ID_PERM_SECTION, "
				+ "                PERMISSION_SECTIONS.TX_PERM_SECTION AS TX_PERM_SECTION, "
				+ "                SYSTEM_PERMISSIONS.ID               AS ID_PERMISSION, "
				+ "                SYSTEM_PERMISSIONS.TX_PERMISSION    AS TX_PERMISSION, "
				+ "                SYSTEM_PERMISSIONS.TX_DESCRIPTION   AS TX_DESCRIPTION, "
				+ "                GROUP_PERMISSION_LINK.FL_STATUS     AS FL_STATUS "
				+ "FROM   SYSTEM_PERMISSIONS "
				+ "       LEFT OUTER JOIN GROUP_PERMISSION_LINK "
				+ "                    ON GROUP_PERMISSION_LINK.ID_PERMISSION = "
				+ "                       SYSTEM_PERMISSIONS.ID "
				+ "       LEFT OUTER JOIN GROUPS "
				+ "                    ON GROUP_PERMISSION_LINK.ID_GROUP = GROUPS.ID "
				+ "       LEFT OUTER JOIN PERMISSION_SECTIONS "
				+ "                    ON PERMISSION_SECTIONS.ID = "
				+ "                       SYSTEM_PERMISSIONS.ID_PERM_SECTION "
				+ "WHERE  GROUPS.ID = " + id
				+ "UNION "
				+ "SELECT DISTINCT GROUPS.ID                           AS ID_GROUP, "
				+ "                GROUPS.TX_GROUP_NAME                AS TX_GROUP_NAME, "
				+ "                PERMISSION_SECTIONS.ID              AS ID_PERM_SECTION, "
				+ "                PERMISSION_SECTIONS.TX_PERM_SECTION AS TX_PERM_SECTION, "
				+ "                SYSTEM_PERMISSIONS.ID               AS ID_PERMISSION, "
				+ "                SYSTEM_PERMISSIONS.TX_PERMISSION    AS TX_PERMISSION, "
				+ "                SYSTEM_PERMISSIONS.TX_DESCRIPTION   AS TX_DESCRIPTION, "
				+ "                '0'                                 AS FL_STATUS "
				+ "FROM   GROUPS, "
				+ "       GROUP_PERMISSION_LINK, "
				+ "       SYSTEM_PERMISSIONS "
				+ "       LEFT OUTER JOIN PERMISSION_SECTIONS "
				+ "                    ON PERMISSION_SECTIONS.ID = "
				+ "                       SYSTEM_PERMISSIONS.ID_PERM_SECTION "
				+ "WHERE  GROUPS.ID =  " + id
				+ "       AND SYSTEM_PERMISSIONS.ID NOT IN (SELECT ID_PERMISSION "
				+ "                                         FROM   GROUP_PERMISSION_LINK "
				+ "                                         WHERE "
				+ "           GROUP_PERMISSION_LINK.ID_GROUP = GROUPS.ID)";
		
		
		List<GroupPermissionsForm> permList = jdbcTemplate.query(GET_GROUP_PERMISSIONS_SQL, new RowMapper<GroupPermissionsForm>() {
			public GroupPermissionsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				GroupPermissionsForm perm = new GroupPermissionsForm();
					perm.setID_GROUP(resulSet.getInt("ID_GROUP"));
					perm.setTX_GROUP_NAME(resulSet.getString("TX_GROUP_NAME"));
					perm.setID_PERM_SECTION(resulSet.getInt("ID_PERM_SECTION"));
					perm.setTX_PERM_SECTION(resulSet.getString("TX_PERM_SECTION"));
					perm.setID_PERMISSION(resulSet.getInt("ID_PERMISSION"));
					perm.setTX_PERMISSION(resulSet.getString("TX_PERMISSION"));
					perm.setTX_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
					perm.setFL_STATUS(resulSet.getInt("FL_STATUS")==0?false:true);
				return perm;
			}
		});
				
		return permList;
	}

	public void updateGroupPermissions(JdbcTemplate jdbcTemplate, int groupid, int permissionid,
			int value) {
		

		final String UPDATE_GROUP_PERMISSION_SQL = "IF NOT EXISTS(SELECT 1 "
				+ "              FROM   GROUP_PERMISSION_LINK "
				+ "              WHERE  ID_PERMISSION = ? "
				+ "                     AND ID_GROUP = ?) "
				+ "  INSERT INTO GROUP_PERMISSION_LINK "
				+ "              (ID_GROUP, "
				+ "               ID_PERMISSION, "
				+ "               FL_STATUS) "
				+ "  VALUES     (?, "
				+ "              ?, "
				+ "              ?) "
				+ "ELSE "
				+ "  UPDATE GROUP_PERMISSION_LINK "
				+ "  SET    FL_STATUS = ? "
				+ "  WHERE  ID_GROUP = ? "
				+ "         AND ID_PERMISSION = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(UPDATE_GROUP_PERMISSION_SQL);
				preparedStatement.setInt(1, permissionid);
				preparedStatement.setInt(2, groupid);
				preparedStatement.setInt(3, groupid);
				preparedStatement.setInt(4, permissionid);
				preparedStatement.setInt(5, value==1?0:1);
				preparedStatement.setInt(6, value==1?0:1);
				preparedStatement.setInt(7, groupid);
				preparedStatement.setInt(8, permissionid);
				
				return preparedStatement;
			}
		});
		
		
	}

	public List<GroupPermissionsForm> getGroups(JdbcTemplate jdbcTemplate) {
		String GET_GROUPS_SQL = "SELECT  GROUPS.ID AS ID_GROUP, GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME , COUNT(USERS.ID)  AS USER_COUNT "
				+ "FROM GROUPS "
				+ "LEFT JOIN USER_GROUP_LINK ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
				+ "LEFT JOIN USERS ON USERS.ID = USER_GROUP_LINK.ID_USER "
				+ "GROUP BY GROUPS.ID, TX_GROUP_NAME";
		
		List<GroupPermissionsForm> groupList = jdbcTemplate.query(GET_GROUPS_SQL, new RowMapper<GroupPermissionsForm>() {
			public GroupPermissionsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				GroupPermissionsForm group = new GroupPermissionsForm();
				group.setID_GROUP(resulSet.getInt("ID_GROUP"));
				group.setTX_GROUP_NAME(resulSet.getString("TX_GROUP_NAME"));
				group.setGROUP_USER_COUNT(resulSet.getInt("USER_COUNT"));	
				return group;
			}
		});
		
		return groupList;
	}

	public void removeUserFromGroup(JdbcTemplate jdbcTemplate, String groupid, String userid) {
		
		final String DELETE_USER_FROM_GROUP_SQL = "DELETE FROM USER_GROUP_LINK WHERE ID_GROUP = ? AND ID_USER = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_FROM_GROUP_SQL);
				preparedStatement.setInt(1, Integer.parseInt(groupid));
				preparedStatement.setInt(2, Integer.parseInt(userid));
				return preparedStatement;
			}
		});
		
	}

	public void addUserToGroup(JdbcTemplate jdbcTemplate, String groupid, String userid) {
		final String ADD_USER_TO_GROUP_SQL = "IF NOT EXISTS (SELECT * FROM USER_GROUP_LINK WHERE ID_GROUP = ? AND ID_USER = (SELECT USERS.ID "
				+ "FROM USERS JOIN ACCOUNTS "
				+ "ON ACCOUNTS.ID  = USERS.ID_ACCOUNT "
				+ "WHERE ACCOUNTS.TX_EMAIL = ?)) "
				+ "INSERT INTO USER_GROUP_LINK(ID_GROUP, ID_USER) VALUES (?, "
				+ "(SELECT USERS.ID "
				+ "FROM USERS JOIN ACCOUNTS "
				+ "ON ACCOUNTS.ID  = USERS.ID_ACCOUNT "
				+ "WHERE ACCOUNTS.TX_EMAIL = ?))";
				
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_TO_GROUP_SQL);
				preparedStatement.setInt(1, Integer.parseInt(groupid));
				preparedStatement.setString(2, userid);
				preparedStatement.setInt(3, Integer.parseInt(groupid));
				preparedStatement.setString(4, userid);
				return preparedStatement;
			}
		});
		
	}
	
	public void addUserToGroupById(JdbcTemplate jdbcTemplate, String groupid, String userid) {
		final String ADD_USER_TO_GROUP_SQL = "IF NOT EXISTS (SELECT * FROM USER_GROUP_LINK WHERE ID_GROUP = ? AND ID_USER = ?) "
				+ "INSERT INTO USER_GROUP_LINK(ID_GROUP, ID_USER) VALUES (?, ?)";
				
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_TO_GROUP_SQL);
				preparedStatement.setInt(1, Integer.parseInt(groupid));
				preparedStatement.setString(2, userid);
				preparedStatement.setInt(3, Integer.parseInt(groupid));
				preparedStatement.setString(4, userid);
				return preparedStatement;
			}
		});
		
	}

	public List<GroupPermissionsForm> getGroupPermissionsByName(JdbcTemplate jdbcTemplate, String name) {
		String GET_GROUP_PERMISSIONS_SQL = "SELECT DISTINCT GROUPS.ID                           AS ID_GROUP, "
				+ "				                 GROUPS.TX_GROUP_NAME                AS TX_GROUP_NAME, "
				+ "				                 PERMISSION_SECTIONS.ID              AS ID_PERM_SECTION, "
				+ "				                 PERMISSION_SECTIONS.TX_PERM_SECTION AS TX_PERM_SECTION, "
				+ "				                 SYSTEM_PERMISSIONS.ID               AS ID_PERMISSION, "
				+ "				                 SYSTEM_PERMISSIONS.TX_PERMISSION    AS TX_PERMISSION, "
				+ "				                 SYSTEM_PERMISSIONS.TX_DESCRIPTION   AS TX_DESCRIPTION, "
				+ "				                 GROUP_PERMISSION_LINK.FL_STATUS     AS FL_STATUS "
				+ "				 FROM   SYSTEM_PERMISSIONS "
				+ "				        LEFT OUTER JOIN GROUP_PERMISSION_LINK "
				+ "				                     ON GROUP_PERMISSION_LINK.ID_PERMISSION = "
				+ "				                        SYSTEM_PERMISSIONS.ID "
				+ "				        LEFT OUTER JOIN GROUPS "
				+ "				                     ON GROUP_PERMISSION_LINK.ID_GROUP = GROUPS.ID "
				+ "				        LEFT OUTER JOIN PERMISSION_SECTIONS "
				+ "				                     ON PERMISSION_SECTIONS.ID = "
				+ "				                        SYSTEM_PERMISSIONS.ID_PERM_SECTION "
				+ "				 WHERE  GROUPS.TX_GROUP_NAME =   '" + name + "'"
				+ "				 UNION "
				+ "				 SELECT DISTINCT GROUPS.ID                           AS ID_GROUP, "
				+ "				                 GROUPS.TX_GROUP_NAME                AS TX_GROUP_NAME, "
				+ "				                 PERMISSION_SECTIONS.ID              AS ID_PERM_SECTION, "
				+ "				                 PERMISSION_SECTIONS.TX_PERM_SECTION AS TX_PERM_SECTION, "
				+ "				                 SYSTEM_PERMISSIONS.ID               AS ID_PERMISSION, "
				+ "				                 SYSTEM_PERMISSIONS.TX_PERMISSION    AS TX_PERMISSION, "
				+ "				                 SYSTEM_PERMISSIONS.TX_DESCRIPTION   AS TX_DESCRIPTION, "
				+ "				                 '0'                                 AS FL_STATUS "
				+ "				 FROM   GROUPS, "
				+ "				        GROUP_PERMISSION_LINK, "
				+ "				        SYSTEM_PERMISSIONS "
				+ "				        LEFT OUTER JOIN PERMISSION_SECTIONS "
				+ "				                     ON PERMISSION_SECTIONS.ID = "
				+ "				                        SYSTEM_PERMISSIONS.ID_PERM_SECTION "
				+ "				 WHERE  GROUPS.TX_GROUP_NAME =  '" + name + "'"
				+ "				        AND SYSTEM_PERMISSIONS.ID NOT IN (SELECT ID_PERMISSION "
				+ "				                                          FROM   GROUP_PERMISSION_LINK "
				+ "				                                          WHERE "
				+ "				            GROUP_PERMISSION_LINK.ID_GROUP = GROUPS.ID)";
		
		
		List<GroupPermissionsForm> permList = jdbcTemplate.query(GET_GROUP_PERMISSIONS_SQL, new RowMapper<GroupPermissionsForm>() {
			public GroupPermissionsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				GroupPermissionsForm perm = new GroupPermissionsForm();
					perm.setID_GROUP(resulSet.getInt("ID_GROUP"));
					perm.setTX_GROUP_NAME(resulSet.getString("TX_GROUP_NAME"));
					perm.setID_PERM_SECTION(resulSet.getInt("ID_PERM_SECTION"));
					perm.setTX_PERM_SECTION(resulSet.getString("TX_PERM_SECTION"));
					perm.setID_PERMISSION(resulSet.getInt("ID_PERMISSION"));
					perm.setTX_PERMISSION(resulSet.getString("TX_PERMISSION"));
					perm.setTX_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
					perm.setFL_STATUS(resulSet.getInt("FL_STATUS")==0?false:true);
				return perm;
			}
		});
				
		return permList;
	}

	public void createGroup(JdbcTemplate jdbcTemplate, String tx_GROUP_NAME) {
		final String ADD_GROUP_SQL = "INSERT INTO GROUPS (TX_GROUP_NAME) VALUES (?)";
		
		final KeyHolder groupKey = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(ADD_GROUP_SQL,
								Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, tx_GROUP_NAME);
				return preparedStatement;
			}
		}, groupKey);
		
		
		
		PermissionsActions pa = new PermissionsActions();
		List<PermissionsForm> pList = pa.getPermissions(jdbcTemplate);
		
		for(int i=0;i<pList.size();i++) {
		
			final String ADD_GROUP_PERMISSIONS_SQL = "INSERT INTO GROUP_PERMISSION_LINK (ID_GROUP,ID_PERMISSION,FL_STATUS) values(?,"+pList.get(i).getID()+",?);";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(ADD_GROUP_PERMISSIONS_SQL);
					preparedStatement.setInt(1, groupKey.getKey().intValue());
					preparedStatement.setInt(2, 0);
					return preparedStatement;
				}
			});
			
		}
		
		
		
		
		
	}

}
