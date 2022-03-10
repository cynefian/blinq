package com.gsd.sreenidhi.admin.features;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeatureSectionController {

	private static final Logger logger = LoggerFactory.getLogger(FeatureSectionController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public FeatureSectionController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@ModelAttribute("featureSectionBean")
	public FeatureSectionForm createFeatureSectionFormBean() {
		return new FeatureSectionForm();
	}
	

	@RequestMapping(value = "/admin/featureManagement/section", method = RequestMethod.GET)
	public ModelAndView getFeatures(HttpSession session) {
		
		final String GET_FEATURES_SECTIIONS_SQL = "SELECT FS.ID AS ID, FS.TX_SECTION AS TX_SECTION, (SELECT COUNT(*) FROM FEATURE_SECTION_MAP FSM WHERE FSM.ID_SECTION = FS.ID) AS FEATURE_COUNT"
				+ " FROM FEATURE_SECTION FS";
				

		List<FeatureSectionForm> featureSectionList = this.jdbcTemplate.query(GET_FEATURES_SECTIIONS_SQL, new RowMapper<FeatureSectionForm>() {
			public FeatureSectionForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				FeatureSectionForm featureSection = new FeatureSectionForm();

				featureSection.setId_section(resulSet.getInt("ID"));
				featureSection.setTx_section(resulSet.getString("TX_SECTION"));
				featureSection.setFeature_count(resulSet.getInt("FEATURE_COUNT"));
				
				return featureSection;
			}
		});
		
		ModelAndView mv = new ModelAndView("admin/feature/manageSections");
		
		mv.addObject("sectionList",featureSectionList);
		
		return mv;
	}
}
