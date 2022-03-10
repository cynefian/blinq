package com.gsd.sreenidhi.admin.features;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class FeatureActions {

	
	/**
	 * This method is to check is a particular feature is active or not.
	 * An inactive feature will disable the corresponding functionality.
	 * @return
	 */
	public boolean validateFeatureFunctionality(JdbcTemplate jdbcTemplate, String featureName) {
		
		boolean status;
		
		final String GET_FEATURES_SQL = "SELECT FT.ID AS ID_FEATURE, FT.TX_FEATURE AS TX_FEATURE, FT.FL_ACTIVE AS FL_ACTIVE, FS.ID AS ID_SECTION, FS.TX_SECTION AS TX_SECTION FROM FEATURE_TOGGLE FT "
				+ " JOIN FEATURE_SECTION_MAP FTM ON FT.ID = FTM.ID_FEATURE "
				+ " JOIN FEATURE_SECTION FS ON FS.ID = FTM.ID_SECTION"
				+ " WHERE  FT.TX_FEATURE = '" + featureName +"' "
				+ " ORDER BY TX_SECTION, TX_FEATURE";

		List<FeatureForm> featureList = jdbcTemplate.query(GET_FEATURES_SQL, new RowMapper<FeatureForm>() {
			public FeatureForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				FeatureForm feature = new FeatureForm();

				feature.setId(resulSet.getInt("ID_FEATURE"));
				feature.setTx_feature(resulSet.getString("TX_FEATURE"));
				feature.setFl_active(resulSet.getInt("FL_ACTIVE") == 1 ? true : false);
				feature.setTx_section(resulSet.getString("TX_SECTION"));
				feature.setId_section(resulSet.getInt("ID_SECTION"));
				
				return feature;
			}
		});
		
		if(featureList.get(0).isFl_active()) {
			status = true;
		}else {
			status = false;
		}
		
		return status;
	}

}
