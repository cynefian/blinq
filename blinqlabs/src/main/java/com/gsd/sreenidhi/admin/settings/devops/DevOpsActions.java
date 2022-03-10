package com.gsd.sreenidhi.admin.settings.devops;

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

public class DevOpsActions {

	public List<DevOpsToolsForm> getDOCatTools(JdbcTemplate jdbcTemplate, String filter) {
		
		String subQuery = "";
		if(filter!=null && filter.equalsIgnoreCase("active")) {
			subQuery = " WHERE DEVOPS_TOOLS.FL_STATUS = 1 AND DEVOPS_CATEGORIES.FL_STATUS = 1 ";
		}

		String GET_DEVOPS_TOOLS = "SELECT "
				+ " DEVOPS_TOOLS.ID AS ID_DEVOPS_TOOL ,"
				+ " DEVOPS_TOOLS.TX_TOOL_NAME AS TX_DEVOPS_TOOL_NAME ,"
				+ " DEVOPS_TOOLS.TX_TOOL_IMAGE AS TX_DEVOPS_TOOL_IMAGE ,"
				+ " DEVOPS_TOOLS.TX_DOWNLOAD_LOC AS TX_DEVOPS_TOOL_DOWNLOAD_LOC ,"
				+ " DEVOPS_TOOLS.FL_STATUS AS FL_DEVOPS_TOOL_STATUS, "
				+ " DEVOPS_TOOLS.TS_TIMESTAMP AS TS_DEVOPS_TOOL, "
				+ " DEVOPS_TOOLS.TX_PORT AS TX_DEVOPS_PORT, "
				+ " DEVOPS_CATEGORIES.ID AS ID_CATEGORY, "
				+ " DEVOPS_CATEGORIES.TX_CATEGORY AS TX_CATEGORY, "
				+ " DEVOPS_CATEGORIES.TX_CAT_CODE AS TX_CATEGORY_CODE, "
				+ " DEVOPS_CATEGORIES.TX_DESCRIPTION AS TX_DATEGORY_DESCRIPTION, "
				+ " DEVOPS_CATEGORIES.FL_STATUS AS FL_CATEGORY_STATUS, "
				+ " DEVOPS_CATEGORIES.TS_TIMESTAMP AS TS_CATEGORY_TIMESTAMP "
				+ " FROM DEVOPS_TOOLS "
				+ " JOIN DEVOPS_CATEGORY_TOOL_LINK ON DEVOPS_CATEGORY_TOOL_LINK.ID_DEVOPS_TOOL = DEVOPS_TOOLS.ID "
				+ " JOIN DEVOPS_CATEGORIES ON DEVOPS_CATEGORIES.ID = DEVOPS_CATEGORY_TOOL_LINK.ID_DEVOPS_CATEGORY "
				+ subQuery;
				
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_DEVOPS_TOOLS, new RowMapper<DevOpsToolsForm>() {
            public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	DevOpsToolsForm dTool = new DevOpsToolsForm();
            	dTool.setID_DEVOPS_TOOL(resulSet.getInt("ID_DEVOPS_TOOL"));
            	dTool.setTX_DEVOPS_TOOL_NAME(resulSet.getString("TX_DEVOPS_TOOL_NAME"));
            	dTool.setTX_DEVOPS_TOOL_IMAGE(resulSet.getString("TX_DEVOPS_TOOL_IMAGE"));
            	dTool.setTX_DEVOPS_TOOL_DOWNLOAD_LOC(resulSet.getString("TX_DEVOPS_TOOL_DOWNLOAD_LOC"));
            	dTool.setTX_PORT(resulSet.getInt("TX_DEVOPS_PORT"));
            	dTool.setFL_DEVOPS_TOOL_STATUS(resulSet.getInt("FL_DEVOPS_TOOL_STATUS")==1?true:false);
            	dTool.setTS_DEVOPS_TOOL(resulSet.getString("TS_DEVOPS_TOOL"));
            	dTool.setID_CATEGORY(resulSet.getInt("ID_CATEGORY"));
            	dTool.setTX_CATEGORY(resulSet.getString("TX_CATEGORY"));
            	dTool.setTX_CATEGORY_CODE(resulSet.getString("TX_CATEGORY_CODE"));
            	dTool.setTX_CATEGORY_DESCRIPTION(resulSet.getString("TX_DATEGORY_DESCRIPTION"));
            	dTool.setFL_CATEGORY_STATUS(resulSet.getInt("FL_CATEGORY_STATUS")==1?true:false);
            	dTool.setTS_CATEGORY_TIMESTAMP(resulSet.getString("TS_CATEGORY_TIMESTAMP"));
            	
            	List<ToolConfigForm> dConfList = getToolConfigurations(jdbcTemplate, resulSet.getInt("ID_DEVOPS_TOOL"));
            	
            	dTool.setCONFIG(dConfList);
				return dTool;
            }
        });
		
		return dToolList;
	}

	public List<ToolConfigForm> getToolConfigurations(JdbcTemplate jdbcTemplate, int toolId) {
		String GET_TOOL_CONFIG_SQL = "SELECT "
    			+ " DEVOPS_TOOL_CONFIG.ID AS ID_CONFIG, "
    			+ " DEVOPS_TOOL_CONFIG.TX_CONFIG_NAME AS TX_CONFIG_NAME, "
    			+ " DEVOPS_TOOL_CONFIG.TX_COMMAND AS TX_COMMAND, "
    			+ " DEVOPS_TOOL_CONFIG.ID_ORDER AS ID_ORDER, "
    			+ " DEVOPS_TOOL_CONFIG.TX_TYPE AS TX_TYPE, "
    			+ " DEVOPS_TOOL_CONFIG.TX_FILE AS TX_FILE, "
    			+ " DEVOPS_TOOL_CONFIG.FL_STATUS AS FL_STATUS "
    			+ "FROM DEVOPS_TOOLS "
    			+ "JOIN DEVOPS_TOOL_CONFIG_LINK on DEVOPS_TOOL_CONFIG_LINK.ID_DEVOPS_TOOL = devops_tools.ID "
    			+ "JOIN DEVOPS_TOOL_CONFIG on DEVOPS_TOOL_CONFIG.ID = DEVOPS_TOOL_CONFIG_LINK.ID_DEVOPS_TOOL_CONFIG "
    			+ "WHERE DEVOPS_TOOLS.ID = " + toolId;
    	
    	List<ToolConfigForm> dConfList = jdbcTemplate.query(GET_TOOL_CONFIG_SQL, new RowMapper<ToolConfigForm>() {
            public ToolConfigForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	ToolConfigForm dConfGorm = new ToolConfigForm();
            	dConfGorm.setID(resulSet.getInt("ID_CONFIG"));
            	dConfGorm.setTX_CONFIG_NAME(resulSet.getString("TX_CONFIG_NAME"));
            	dConfGorm.setTX_COMMAND(resulSet.getString("TX_COMMAND"));
            	dConfGorm.setID_ORDER(resulSet.getInt("ID_ORDER"));
            	dConfGorm.setTX_TYPE(resulSet.getString("TX_TYPE"));
            	dConfGorm.setTX_FILE(resulSet.getString("TX_FILE"));
            	dConfGorm.setFL_STATUS(resulSet.getInt("FL_STATUS")==1?true:false);
            	dConfGorm.setID_DEVOPS_TOOL(toolId);
            	return dConfGorm;
            }
    	});
    	return dConfList;
	}
	
	public List<DevOpsCategoriesForm> getDOCategories(JdbcTemplate jdbcTemplate) {

		String GET_DEVOPS_CATEGORIES_SQL=" SELECT ID, TX_CATEGORY, TX_CAT_CODE, TX_DESCRIPTION, FL_STATUS, TS_TIMESTAMP FROM DEVOPS_CATEGORIES";
		
		List<DevOpsCategoriesForm> dCatList = jdbcTemplate.query(GET_DEVOPS_CATEGORIES_SQL, new RowMapper<DevOpsCategoriesForm>() {
            public DevOpsCategoriesForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	DevOpsCategoriesForm dCat = new DevOpsCategoriesForm();
            	dCat.setID(resulSet.getInt("ID"));
            	dCat.setTS_TIMESTAMP(resulSet.getString("TS_TIMESTAMP"));
            	dCat.setTX_CAT_CODE(resulSet.getString("TX_CAT_CODE"));
            	dCat.setTX_CATEGORY(resulSet.getString("TX_CATEGORY"));
            	dCat.setTX_DESCRIPTION(resulSet.getString("TX_DESCRIPTION"));
            	dCat.setFL_STATUS(resulSet.getInt("FL_STATUS")==1?true:false);
                return dCat;
            }
        });
		
		return dCatList;
	}

	
	public String getDevOpsToolNameById(JdbcTemplate jdbcTemplate, int id) {
		String result="";
		String GET_DEVOPS_TOOL_SQL=" SELECT TX_TOOL_NAME FROM DEVOPS_TOOLS WHERE ID = " + id;
		
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_DEVOPS_TOOL_SQL, new RowMapper<DevOpsToolsForm>() {
            public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	DevOpsToolsForm dTool = new DevOpsToolsForm();
            	dTool.setTX_DEVOPS_TOOL_NAME(resulSet.getString("TX_TOOL_NAME"));
            	   return dTool;
            }
        });
		
		if(dToolList!=null && dToolList.size()>0) {
			result = dToolList.get(0).getTX_DEVOPS_TOOL_NAME();
		}
		return result;
	}
	
	public String getDevOpsToolImageById(JdbcTemplate jdbcTemplate, int id) {
		String result="";
		String GET_DEVOPS_TOOL_SQL=" SELECT TX_TOOL_IMAGE FROM DEVOPS_TOOLS WHERE ID = " + id;
		
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_DEVOPS_TOOL_SQL, new RowMapper<DevOpsToolsForm>() {
            public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	DevOpsToolsForm dTool = new DevOpsToolsForm();
            	dTool.setTX_DEVOPS_TOOL_IMAGE(resulSet.getString("TX_TOOL_IMAGE"));
            	   return dTool;
            }
        });
		
		if(dToolList!=null && dToolList.size()>0) {
			result = dToolList.get(0).getTX_DEVOPS_TOOL_IMAGE();
		}
		return result;
	}
	
	public String getDevOpsToolNameByLabJobId(JdbcTemplate jdbcTemplate, int id) {
		String result="";
		String GET_JOB_NAME_SQL = "SELECT "
				+ "DEVOPS_TOOLS.TX_TOOL_NAME AS TX_TOOL_IMAGE "
				+ "FROM DEVOPS_TOOLS "
				+ "JOIN LAB_SERVICE "
				+ "ON DEVOPS_TOOLS.ID = LAB_SERVICE.ID_SERVICE_TOOL "
				+ "WHERE LAB_SERVICE.ID = " + id;
		
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_JOB_NAME_SQL, new RowMapper<DevOpsToolsForm>() {
            public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	DevOpsToolsForm dTool = new DevOpsToolsForm();
            	dTool.setTX_DEVOPS_TOOL_IMAGE(resulSet.getString("TX_TOOL_IMAGE"));
            	   return dTool;
            }
        });
		
		if(dToolList!=null && dToolList.size()>0) {
			result = dToolList.get(0).getTX_DEVOPS_TOOL_IMAGE();
		}
		return result;
	}
	
	public String getToolLocationById(JdbcTemplate jdbcTemplate, int id) {
		
		String GET_DEVOPS_TOOLS = "SELECT "
				+ " TX_DOWNLOAD_LOC "
				+ " FROM DEVOPS_TOOLS "
				+ " WHERE ID = " + id;
				
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_DEVOPS_TOOLS, new RowMapper<DevOpsToolsForm>() {
            public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	DevOpsToolsForm dTool = new DevOpsToolsForm();
            	dTool.setTX_DEVOPS_TOOL_DOWNLOAD_LOC(resulSet.getString("TX_DOWNLOAD_LOC"));
            	return dTool;
            }
        });
		
		if(dToolList!=null && dToolList.size()>0) {
			return dToolList.get(0).getTX_DEVOPS_TOOL_DOWNLOAD_LOC();	
		}else {
			return null;
		}
	}


	public String getToolImageById(JdbcTemplate jdbcTemplate, int id) {
		
		String GET_DEVOPS_TOOLS = "SELECT "
				+ " TX_TOOL_IMAGE "
				+ " FROM DEVOPS_TOOLS "
				+ " WHERE ID = " + id;
				
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_DEVOPS_TOOLS, new RowMapper<DevOpsToolsForm>() {
	        public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	        	DevOpsToolsForm dTool = new DevOpsToolsForm();
	        	dTool.setTX_DEVOPS_TOOL_IMAGE(resulSet.getString("TX_TOOL_IMAGE"));
	        	return dTool;
	        }
	    });
		
		if(dToolList!=null && dToolList.size()>0) {
			return dToolList.get(0).getTX_DEVOPS_TOOL_IMAGE();	
		}else {
			return null;
		}
	}
	
	public int getToolPortById(JdbcTemplate jdbcTemplate, int id) {
		
		String GET_DEVOPS_TOOLS = "SELECT "
				+ " TX_PORT "
				+ " FROM DEVOPS_TOOLS "
				+ " WHERE ID = " + id;
				
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_DEVOPS_TOOLS, new RowMapper<DevOpsToolsForm>() {
	        public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	        	DevOpsToolsForm dTool = new DevOpsToolsForm();
	        	dTool.setTX_PORT(resulSet.getInt("TX_PORT"));
	        	return dTool;
	        }
	    });
		
		if(dToolList!=null && dToolList.size()>0) {
			return dToolList.get(0).getTX_PORT();	
		}else {
			return 0;
		}
	}
	
	public int getToolByName(JdbcTemplate jdbcTemplate, String name) {
		
		String GET_DEVOPS_TOOLS = "SELECT "
				+ " ID "
				+ " FROM DEVOPS_TOOLS "
				+ " WHERE TX_TOOL_NAME = '" + name + "'";
				
		List<DevOpsToolsForm> dToolList = jdbcTemplate.query(GET_DEVOPS_TOOLS, new RowMapper<DevOpsToolsForm>() {
	        public DevOpsToolsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
	        	DevOpsToolsForm dTool = new DevOpsToolsForm();
	        	dTool.setID_DEVOPS_TOOL(resulSet.getInt("ID"));
	        	return dTool;
	        }
	    });
		
		if(dToolList!=null && dToolList.size()>0) {
			return dToolList.get(0).getID_DEVOPS_TOOL();	
		}else {
			return 0;
		}
	}

	public void insertToolConfig(JdbcTemplate jdbcTemplate, ToolConfigForm toolConfigForm, int i, int toolKey) {
		final KeyHolder configKey = new GeneratedKeyHolder();

		String ADD_DEVOPS_TOOL_CONFIG = " INSERT INTO DEVOPS_TOOL_CONFIG (TX_CONFIG_NAME, TX_COMMAND, ID_ORDER, TX_TYPE, TX_FILE, FL_STATUS) VALUES (?,?,?,?,?,?)";

		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(ADD_DEVOPS_TOOL_CONFIG, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, toolConfigForm.getTX_CONFIG_NAME());
				preparedStatement.setString(2, toolConfigForm.getTX_COMMAND());
				preparedStatement.setInt(3, i);
				preparedStatement.setString(4, toolConfigForm.getTX_TYPE());
				preparedStatement.setString(5, toolConfigForm.getTX_FILE());
				preparedStatement.setInt(6, 1);
				return preparedStatement;
			}
		}, configKey);

		String ADD_DEVOPS_TOOL_CONFIG_LINK = " INSERT INTO DEVOPS_TOOL_CONFIG_LINK (ID_DEVOPS_TOOL, ID_DEVOPS_TOOL_CONFIG) VALUES (?,?)";
		final int toolId = toolKey;
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(ADD_DEVOPS_TOOL_CONFIG_LINK);
				preparedStatement.setInt(1, toolId);
				preparedStatement.setInt(2, configKey.getKey().intValue());
				return preparedStatement;
			}
		});
		
	}

}
