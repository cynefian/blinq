package com.gsd.sreenidhi.admin.settings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gsd.sreenidhi.admin.blogs.BlogCategoryForm;

public class PermissionsActions {
	
	public List<PermissionsForm> getPermissionSections(JdbcTemplate jdbcTemplate) {
		
		String GET_PERMISSIONS_SQL = "SELECT "
				+ "	PERMISSION_SECTIONS.TX_PERM_SECTION AS TX_PERM_SECTION "
				+ "FROM PERMISSION_SECTIONS ";
		
		List<PermissionsForm> permList = jdbcTemplate.query(GET_PERMISSIONS_SQL, new RowMapper<PermissionsForm>() {
			public PermissionsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				PermissionsForm perm = new PermissionsForm();
				perm.setTX_PERM_SECTION(resulSet.getString("TX_PERM_SECTION"));
				return perm;
			}
		});
				
		return permList;
	}



	public List<PermissionsForm> getPermissions(JdbcTemplate jdbcTemplate) {
		
		String GET_PERMISSIONS_SQL = "SELECT "
				+ "	SYSTEM_PERMISSIONS.ID AS PERMISSION_ID, "
				+ "	PERMISSION_SECTIONS.TX_PERM_SECTION AS TX_PERM_SECTION, "
				+ "	SYSTEM_PERMISSIONS.TX_PERMISSION AS TX_PERMISSION, "
				+ "	SYSTEM_PERMISSIONS.TX_DESCRIPTION AS TX_DESCRIPTION "
				+ "FROM SYSTEM_PERMISSIONS "
				+ "JOIN PERMISSION_SECTIONS "
				+ "	ON PERMISSION_SECTIONS.ID = SYSTEM_PERMISSIONS.ID_PERM_SECTION "
				+ "ORDER BY TX_PERM_SECTION";
		
		List<PermissionsForm> permList = jdbcTemplate.query(GET_PERMISSIONS_SQL, new RowMapper<PermissionsForm>() {
			public PermissionsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				PermissionsForm perm = new PermissionsForm();
				perm.setID(resulSet.getInt("PERMISSION_ID"));
				perm.setTX_PERM_SECTION(resulSet.getString("TX_PERM_SECTION"));
				perm.setTX_PERMISSION(resulSet.getString("TX_PERMISSION"));
				perm.setTX_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
				return perm;
			}
		});
		
		return permList;
	}



	public List<PermissionGroups> getPermissionGroups(JdbcTemplate jdbcTemplate, int id) {
		
		String GET_PERMISSIONS_SQL = "SELECT DISTINCT "
				+ "	GROUPS.ID AS ID, "
				+ "	GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME "
				+ "from GROUPS "
				+ "JOIN GROUP_PERMISSION_LINK "
				+ "ON GROUP_PERMISSION_LINK.ID_GROUP = GROUPS.ID "
				+ "JOIN SYSTEM_PERMISSIONS "
				+ "ON GROUP_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID"
				+ " WHERE GROUP_PERMISSION_LINK.FL_STATUS = 1 "
				+ " AND SYSTEM_PERMISSIONS.ID = " + id;
		
		List<PermissionGroups> permGroups = jdbcTemplate.query(GET_PERMISSIONS_SQL, new RowMapper<PermissionGroups>() {
			public PermissionGroups mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				PermissionGroups perm = new PermissionGroups();
				perm.setID(resulSet.getInt("ID"));
				perm.setTX_GROUP_NAME(resulSet.getString("TX_GROUP_NAME"));
				return perm;
			}
		});
		
		return permGroups;
	}
	
	public List<PermissionUsers> getPermissionUsers(JdbcTemplate jdbcTemplate, int id) {
		
		String GET_USER_LEVEL_PERMISSIONS_SQL = "SELECT DISTINCT "
				+ "'USER_LEVEL' AS PERMISSION_TYPE, "
				+ "NULL AS ID_GROUP, "
				+ "NULL AS TX_GROUP_NAME, "
				+ "USERS.ID AS ID_USER, "
				+ "USERS.TX_FIRSTNAME, "
				+ "USERS.TX_LASTNAME, "
				+ "USERS.TX_IMAGE, "
				+ "ACCOUNTS.TX_EMAIL "
				+ "from USERS "
				+ "JOIN USER_PERMISSION_LINK "
				+ "ON USERS.ID = USER_PERMISSION_LINK.ID_USER "
				+ "JOIN SYSTEM_PERMISSIONS "
				+ "ON USER_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "JOIN ACCOUNTS "
				+ "ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
				+ "WHERE SYSTEM_PERMISSIONS.ID = "+id
				+ " UNION "
				+ "SELECT DISTINCT "
				+ "'GROUP_LEVEL' AS PERMISSION_TYPE, "
				+ "GROUPS.ID AS ID_GROUP, "
				+ "GROUPS.TX_GROUP_NAME AS TX_GROUP_NAME, "
				+ "USERS.ID AS ID_USER, "
				+ "USERS.TX_FIRSTNAME, "
				+ "USERS.TX_LASTNAME, "
				+ "USERS.TX_IMAGE, "
				+ "ACCOUNTS.TX_EMAIL "
				+ "from GROUPS "
				+ "JOIN GROUP_PERMISSION_LINK "
				+ "ON GROUP_PERMISSION_LINK.ID_GROUP = GROUPS.ID "
				+ "JOIN SYSTEM_PERMISSIONS "
				+ "ON GROUP_PERMISSION_LINK.ID_PERMISSION = SYSTEM_PERMISSIONS.ID "
				+ "JOIN USER_GROUP_LINK "
				+ "ON USER_GROUP_LINK.ID_GROUP = GROUPS.ID "
				+ "JOIN USERS "
				+ "ON USERS.ID = USER_GROUP_LINK.ID_USER "
				+ "JOIN ACCOUNTS "
				+ "ON ACCOUNTS.ID = USERS.ID_ACCOUNT "
				+ "WHERE GROUP_PERMISSION_LINK.FL_STATUS = 1 "
				+ "AND SYSTEM_PERMISSIONS.ID = "+id;
		
		
		List<PermissionUsers> permUsers = jdbcTemplate.query(GET_USER_LEVEL_PERMISSIONS_SQL, new RowMapper<PermissionUsers>() {
			public PermissionUsers mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				PermissionUsers perm = new PermissionUsers();
				
				perm.setID_GROUP(resulSet.getInt("ID_GROUP"));
				perm.setTX_GROUP_NAME(resulSet.getString("TX_GROUP_NAME"));
				perm.setID_USER(resulSet.getInt("ID_USER"));
				perm.setTX_FIRSTNAME(resulSet.getString("TX_FIRSTNAME"));
				perm.setTX_LASTNAME(resulSet.getString("TX_LASTNAME"));
				perm.setTX_IMAGE(resulSet.getString("TX_IMAGE"));
				perm.setPERMISSION_TYPE(resulSet.getString("PERMISSION_TYPE"));
				perm.setTX_EMAIL(resulSet.getString("TX_EMAIL"));
				
				
				return perm;
			}
		});
		
		
		List<PermissionUsers> permList = new ArrayList();

		List<String> types = new ArrayList();
		
		boolean updated=false;
		for(int i=0;i<permUsers.size();i++) {
			if(permList!=null) {
				for(int j=0;j>permList.size();j++) {
					if(permUsers.get(i).getID_USER()==permList.get(j).getID_USER()) {
						types = new ArrayList();
						types.add(permList.get(j).getPERMISSION_TYPE());
						types.add(permUsers.get(i).getPERMISSION_TYPE());
						permList.get(j).setTYPES(types);
					}
				}
				if(!updated) {
					types = new ArrayList();
					types.add(permUsers.get(1).getPERMISSION_TYPE());
					permList.add(permUsers.get(i));
					permList.add(permUsers.get(i));
					permList.get(i).setTYPES(types);
				}
			}else {
				types = new ArrayList();
				types.add(permUsers.get(0).getPERMISSION_TYPE());
				permList.add(permUsers.get(0));
				permList.get(0).setTYPES(types);
			}
		}
		
		
		
		
		return permList;
	}



	public List<PermissionForm> getPermissionInfo(JdbcTemplate jdbcTemplate, int id) {
		String GET_PERMISSIONS_SQL =  "SELECT SYSTEM_PERMISSIONS.ID AS ID_PERMISSION,"
				+ "SYSTEM_PERMISSIONS.TX_PERMISSION AS TX_PERMISSION, "
				+ "SYSTEM_PERMISSIONS.TX_DESCRIPTION AS TX_DESCRIPTION, "
				+ "PERMISSION_SECTIONS.ID AS ID_PERM_SECTION, "
				+ "PERMISSION_SECTIONS.TX_PERM_SECTION AS TX_PERM_SECTION"
				+ " FROM SYSTEM_PERMISSIONS "
				+ " JOIN PERMISSION_SECTIONS "
				+ " ON SYSTEM_PERMISSIONS.ID_PERM_SECTION = PERMISSION_SECTIONS.ID "
				+ " WHERE SYSTEM_PERMISSIONS.ID = "+ id;
		
		List<PermissionForm> permInfo = jdbcTemplate.query(GET_PERMISSIONS_SQL, new RowMapper<PermissionForm>() {
			public PermissionForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				PermissionForm perm = new PermissionForm();
				
				perm.setID_PERMISSION(resulSet.getInt("ID_PERMISSION"));
				perm.setTX_PERMISSION(resulSet.getString("TX_PERMISSION"));
				perm.setTX_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
				perm.setID_PERM_SECTION(resulSet.getInt("ID_PERM_SECTION"));
				perm.setTX_PERM_SECTION(resulSet.getString("TX_PERM_SECTION"));
				
				
				return perm;
			}
		});
		
		return permInfo;
	}

}
