package com.gsd.sreenidhi.admin.features;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.forms.FlexBean;
import com.gsd.sreenidhi.utils.CalendarUtils;

@Controller
public class FeatureController {
	public static FlexBean featureBean ;
	private static final Logger logger = LoggerFactory.getLogger(FeatureController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public FeatureController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@ModelAttribute("featureBean")
	public FeatureForm createFeatureFormBean() {
		return new FeatureForm();
	}
	

	@RequestMapping(value = "/admin/featureManagement", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_FEATURES')")
	public ModelAndView getFeatures(HttpSession session) {

		final String GET_FEATURES_SECTIIONS_SQL = "SELECT ID, TX_SECTION FROM FEATURE_SECTION";

		List<FeatureSectionForm> featureSectionList = this.jdbcTemplate.query(GET_FEATURES_SECTIIONS_SQL, new RowMapper<FeatureSectionForm>() {
			public FeatureSectionForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				FeatureSectionForm featureSection = new FeatureSectionForm();

				featureSection.setId_section(resulSet.getInt("ID"));
				featureSection.setTx_section(resulSet.getString("TX_SECTION"));
				
				return featureSection;
			}
		});
		
		
		for(int i=0;i<featureSectionList.size();i++) {
			

			final String GET_FEATURES_SQL = "SELECT FT.ID AS ID_FEATURE, FT.TX_FEATURE AS TX_FEATURE, FT.FL_ACTIVE AS FL_ACTIVE, FS.ID AS ID_SECTION, FS.TX_SECTION AS TX_SECTION FROM FEATURE_TOGGLE FT "
					+ " JOIN FEATURE_SECTION_MAP FTM ON FT.ID = FTM.ID_FEATURE "
					+ " JOIN FEATURE_SECTION FS ON FS.ID = FTM.ID_SECTION"
					+ " WHERE FS.ID = " + featureSectionList.get(i).getId_section()
					+ " ORDER BY TX_SECTION, TX_FEATURE";

			List<FeatureForm> featureList = this.jdbcTemplate.query(GET_FEATURES_SQL, new RowMapper<FeatureForm>() {
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
			
			featureSectionList.get(i).setFeatureList(featureList);

		}
		
		ModelAndView mv = new ModelAndView("admin/feature/manageFeatures");
		mv.addObject("featureList", featureSectionList);
		mv.addObject("featureToggleForm", featureBean);
		return mv;

	}

	@RequestMapping(value = "/admin/featureManagement", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_FEATURES')")
	public ModelAndView createFeature(@ModelAttribute("featureBean") @Valid FeatureForm featureBean,
			@RequestParam("action") String action, BindingResult result, Model model, RedirectAttributes redirectAttrs,
			HttpSession session) throws ParseException {
		
		ModelAndView mv = new ModelAndView();

		final FeatureForm fF = featureBean;
		
		String message="Undefined";
		boolean success = false;

		if ("CREATE_FEATURE".equalsIgnoreCase(action)) {

			/* Feature*/
			
			//Search for existing Feature
			final String search_Feature = "SELECT FT.ID AS ID_FEATURE, FT.TX_FEATURE AS TX_FEATURE, FT.FL_ACTIVE AS FL_ACTIVE, FS.ID AS ID_SECTION, FS.TX_SECTION AS TX_SECTION FROM FEATURE_TOGGLE FT "
					+ " JOIN FEATURE_SECTION_MAP FTM ON FT.ID = FTM.ID_FEATURE "
					+ " JOIN FEATURE_SECTION FS ON FS.ID = FTM.ID_SECTION"
					+ " WHERE FT.TX_FEATURE = '" + fF.getTx_feature() +"'"
					+ " AND FS.TX_SECTION = '" + fF.getTx_section() +"'"
					;
			
			List<FeatureForm> featureSrchList = this.jdbcTemplate.query(search_Feature, new RowMapper<FeatureForm>() {
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
			
			if(featureSrchList!=null && featureSrchList.size()>0) {
				success = false;
				message = "Feature already exists";
			}else {
				
				final KeyHolder featureKey = new GeneratedKeyHolder();

				final String INSERT_FEATURE_SQL = "INSERT INTO FEATURE_TOGGLE (TX_FEATURE, FL_ACTIVE) VALUES (?,?)";
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FEATURE_SQL,
								Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, fF.getTx_feature());
						preparedStatement.setInt(2, fF.isFl_active()?1:0);
						return preparedStatement;
					}
				}, featureKey);
				featureBean.setId(featureKey.getKey().intValue());

				if (featureBean.getId() != 0) {
					// SEARCH EXISTING FEATURE
					final String GET_FEATURES_SQL = "SELECT ID, TX_SECTION FROM FEATURE_SECTION WHERE TX_SECTION LIKE '%"
							+ featureBean.getTx_section() + "%'";
					List<FeatureForm> featureList = this.jdbcTemplate.query(GET_FEATURES_SQL, new RowMapper<FeatureForm>() {
						public FeatureForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
							FeatureForm feature = new FeatureForm();
							feature.setTx_section(resulSet.getString("TX_SECTION"));
							feature.setId_section(resulSet.getInt("ID"));
							return feature;
						}
					});

					// Feature Section
					if (featureList != null && featureList.size() > 0) {
						featureBean.setId_section(featureList.get(0).getId_section());
					} else {
						final KeyHolder featureSectionKey = new GeneratedKeyHolder();

						final String INSERT_FEATURE_SECTION_SQL = "INSERT INTO FEATURE_SECTION (TX_SECTION) VALUES (?)";
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection
										.prepareStatement(INSERT_FEATURE_SECTION_SQL, Statement.RETURN_GENERATED_KEYS);
								preparedStatement.setString(1, fF.getTx_section());
								return preparedStatement;
							}
						}, featureSectionKey);
						featureBean.setId_section(featureSectionKey.getKey().intValue());

					}

					if (featureBean.getId_section() != 0) {
						// Feature Section Map
						final KeyHolder featureSectionMapKey = new GeneratedKeyHolder();
						final int id_feature = featureBean.getId();
						final int id_section = featureBean.getId_section();

						final String INSERT_FEATURE_SECTION_MAP_SQL = "INSERT INTO FEATURE_SECTION_MAP (ID_SECTION, ID_FEATURE) VALUES (?,?)";
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection
										.prepareStatement(INSERT_FEATURE_SECTION_MAP_SQL, Statement.RETURN_GENERATED_KEYS);
								preparedStatement.setInt(1, id_section);
								preparedStatement.setInt(2, id_feature);
								return preparedStatement;
							}
						}, featureSectionMapKey);
						featureBean.setId_section(featureSectionMapKey.getKey().intValue());
						success = true;
						message = "Feature has been created";
						
						CalendarUtils cu = new CalendarUtils();
						final String ts = cu.getDateTimeGMT();
						
						
						final KeyHolder featureToggleHistoryKey = new GeneratedKeyHolder();
						final String INSERT_FEATURE_TOGGLE_HISTORY_SQL = "INSERT INTO FEATURE_TOGGLE_HISTORY (ID_FEATURE, FL_STATE, TX_UPDATE, TS_UPDATE) VALUES (?,?,?,?)";
						jdbcTemplate.update(new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement preparedStatement = connection
										.prepareStatement(INSERT_FEATURE_TOGGLE_HISTORY_SQL, Statement.RETURN_GENERATED_KEYS);
								preparedStatement.setInt(1, id_feature);
								preparedStatement.setInt(2, fF.isFl_active()?1:0);
								preparedStatement.setString(3, fF.isFl_active()?"Feature Created - Enabled":"Feature Created - Disabled");
								preparedStatement.setString(4, ts);
								return preparedStatement;
							}
						}, featureToggleHistoryKey);
						
						
						
					}else {
						success = false;
						message = "Could not Map Feature to Section.";
					}

				}else {
					success = false;
					message = "Failed to create Feature";
				}
			}
			
			
				
		}else if ("CREATE_SECTION".equalsIgnoreCase(action)) {

				/* Feature*/
				
				//Search for existing Feature
				final String search_section = "SELECT * FROM FEATURE_SECTION WHERE TX_SECTION='"+fF.getTx_section()+"'";
			
				List<FeatureForm> featureSrchList = this.jdbcTemplate.query(search_section, new RowMapper<FeatureForm>() {
					public FeatureForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
						FeatureForm feature = new FeatureForm();

						feature.setTx_section(resulSet.getString("TX_SECTION"));
						feature.setId_section(resulSet.getInt("ID"));
						return feature;
					}
				});
				
				if(featureSrchList!=null && featureSrchList.size()>0) {
					success = false;
					message = "Feature already exists";
				}else {
					
					final String create_section = "INSERT INTO FEATURE_SECTION(TX_SECTION) VALUES(?)";
					final KeyHolder sectionKey = new GeneratedKeyHolder();
					
					jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement preparedStatement = connection
									.prepareStatement(create_section, Statement.RETURN_GENERATED_KEYS);
							preparedStatement.setString(1, fF.getTx_section());
							return preparedStatement;
						}
					}, sectionKey);
					
					if(sectionKey!=null && sectionKey.getKey().intValue()>0) {
						success = true;
						message = "Section created successfully";
					}else {
						success = false;
						message = "Failed to create Section";
					}
				
				}

		}else if("UPDATE".equalsIgnoreCase(action)) {
			
		}else if("EDIT".equalsIgnoreCase(action)) {
			
		}else {
			
		}

		mv = getFeatures(session);
		
		if(success) {
			mv.addObject("successmessage",message);
		}else {
			mv.addObject("failuremessage",message);
		}
		return mv;
	}



	@RequestMapping(value = "/admin/checkboxUpdate", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_FEATURES')")
	public ModelAndView createFeature(@ModelAttribute("id") @Valid String id, 
			@ModelAttribute("feature") @Valid String feature, 
			@ModelAttribute("value") @Valid String value,
			@ModelAttribute("page") @Valid String page,
			HttpServletRequest request, HttpServletResponse response,
			Model model, BindingResult result, RedirectAttributes redirectAttrs,
			final HttpSession session) throws IOException, ParseException {
		String message="Undefined";
		boolean success=false;
		
		final String v = value;
		final String i = id;
		final String f = feature;
				
		final String UPDATE_FEATURE_TOGGLE_SQL = "UPDATE FEATURE_TOGGLE SET FL_ACTIVE = ? WHERE ID = ?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(UPDATE_FEATURE_TOGGLE_SQL);
				preparedStatement.setInt(1, (v.equals("1")?0:1));
				preparedStatement.setInt(2, Integer.parseInt(i));
				return preparedStatement;
			}
		});
		success = true;
		message = "Update Successful";
		
		CalendarUtils cu = new CalendarUtils();
		final String ts = cu.getDateTimeGMT();
		
		final KeyHolder featureToggleHistoryKey = new GeneratedKeyHolder();
		final String INSERT_FEATURE_TOGGLE_HISTORY_SQL = "INSERT INTO FEATURE_TOGGLE_HISTORY (ID_FEATURE, FL_STATE, TX_UPDATE, TS_UPDATE, ID_USER) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_FEATURE_TOGGLE_HISTORY_SQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, Integer.parseInt(i));
				preparedStatement.setInt(2, (v.equals("1")?0:1));
				preparedStatement.setString(3, (v.equals("1")?"Feature Disabled":"Feature Enabled"));
				preparedStatement.setString(4, ts);
				preparedStatement.setString(5, session.getAttribute("userId").toString().trim());
				
				return preparedStatement;
			}
		}, featureToggleHistoryKey);
		
		ModelAndView mv = new ModelAndView();
		if("manageFeatures".equalsIgnoreCase(page)) {
			mv = getFeatures(session);	
		}else if("featureDetails".equalsIgnoreCase(page)) {
			mv = getFeatureDetails(i,request, response, model, result, redirectAttrs, session);
		}
		
		
		if(success) {
			mv.addObject("successmessage",message);
		}else {
			mv.addObject("failuremessage",message);
		}
		return mv;
	}


	@RequestMapping(value = "/admin/featureDetails", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERM_FEATURES')")
	public ModelAndView getFeatureDetails(@ModelAttribute("id") @Valid String id,
			HttpServletRequest request, HttpServletResponse response,
			Model model, BindingResult result, RedirectAttributes redirectAttrs,
			HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("admin/feature/featureDetails");
	
		final String GET_FEATURES_SECTIONS_SQL = "SELECT FT.ID AS ID_FEATURE, FT.TX_FEATURE AS TX_FEATURE, FT.FL_ACTIVE AS FL_ACTIVE, FS.ID AS ID_SECTION, FS.TX_SECTION AS TX_SECTION FROM FEATURE_TOGGLE FT "
				+ " JOIN FEATURE_SECTION_MAP FTM ON FT.ID = FTM.ID_FEATURE "
				+ " JOIN FEATURE_SECTION FS ON FS.ID = FTM.ID_SECTION"
				+ " WHERE FT.ID = " + id
				+ " ORDER BY TX_SECTION, TX_FEATURE";

		List<FeatureDetailsForm> featureDetailsList = this.jdbcTemplate.query(GET_FEATURES_SECTIONS_SQL, new RowMapper<FeatureDetailsForm>() {
			public FeatureDetailsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				FeatureDetailsForm featureDetails = new FeatureDetailsForm();

				featureDetails.setID_FEATURE(resulSet.getInt("ID_FEATURE"));
				featureDetails.setTX_FEATURE(resulSet.getString("TX_FEATURE"));
				featureDetails.setFL_ACTIVE((resulSet.getInt("FL_ACTIVE")==1)?true:false);
				featureDetails.setTX_FEATURE(resulSet.getString("TX_FEATURE"));
				featureDetails.setID_SECTION(resulSet.getInt("ID_SECTION"));
				featureDetails.setTX_SECTION(resulSet.getString("TX_SECTION"));
			
				return featureDetails;
			}
		});
		
		
		final String GET_FEATURES_HISTORY_SQL = "SELECT FTH.ID AS ID, FTH.ID_FEATURE AS ID_FEATURE, FTH.FL_STATE AS FL_STATE,  FS.ID AS ID_SECTION, FS.TX_SECTION AS TX_SECTION,"
				+ "  FTH.TS_UPDATE AS TS_UPDATE, FTH.TX_UPDATE AS TX_UPDATE, FTH.ID_USER AS ID_USER, CONCAT(U.TX_FIRSTNAME,' ',U.TX_LASTNAME) AS TX_USER "
				+ "  FROM FEATURE_TOGGLE_HISTORY FTH "
				+ "  JOIN FEATURE_TOGGLE FT ON FT.ID = FTH.ID_FEATURE "
				+ "  JOIN FEATURE_SECTION_MAP FSM ON FSM.ID_FEATURE = FT.ID AND FSM.ID_FEATURE = FTH.ID_FEATURE "
				+ "  JOIN FEATURE_SECTION FS ON FS.ID = FSM.ID_SECTION "
				+ "  JOIN USERS U ON U.ID_ACCOUNT = FTH.ID_USER "
				+ "  WHERE FTH.ID_FEATURE="+id;
		
		List<FeatureHistoryForm> featureHistoryList = this.jdbcTemplate.query(GET_FEATURES_HISTORY_SQL, new RowMapper<FeatureHistoryForm>() {
			public FeatureHistoryForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				FeatureHistoryForm featureHistory = new FeatureHistoryForm();

				featureHistory.setID(resulSet.getInt("ID"));
				featureHistory.setID_FEATURE(resulSet.getInt("ID_FEATURE"));
				featureHistory.setTX_FEATURE(resulSet.getString("TX_SECTION"));
				featureHistory.setID_SECTION(resulSet.getInt("ID_SECTION"));
				featureHistory.setTX_SECTION(resulSet.getString("TX_SECTION"));
				featureHistory.setTX_UPDATE(resulSet.getString("TX_UPDATE"));
				featureHistory.setTS_UPDATE(resulSet.getString("TS_UPDATE"));
				featureHistory.setID_USER(resulSet.getInt("ID_USER"));
				featureHistory.setTX_USER(resulSet.getString("TX_USER"));
				
				return featureHistory;
			}
		});
		
		mv.addObject("featureDetails", featureDetailsList);
		mv.addObject("featureHistory",featureHistoryList);
		
		return mv;
	}

	
	
	@RequestMapping(value = "/admin/deleteFeature", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_FEATURES')")
	public ModelAndView deleteFeature(@ModelAttribute("id") @Valid String id,
			HttpServletRequest request, HttpServletResponse response,
			Model model, BindingResult result, RedirectAttributes redirectAttrs,
			final HttpSession session) throws IOException, ParseException {
		
		String message="";
		boolean success=false;
		
		
		final String INSERT_FEATURES_DELETED_SQL = "INSERT INTO FEATURE_DELETED(ID_FEATURE, TX_FEATURE,ID_SECTION,TX_SECTION, ID_USER, TS_TIME) VALUES(?,"
				+ "(SELECT TX_FEATURE FROM FEATURE_TOGGLE WHERE ID=?),"
				+ "(SELECT ID_SECTION FROM FEATURE_SECTION_MAP WHERE ID=?),"
				+ "(SELECT FS.TX_SECTION FROM FEATURE_SECTION FS JOIN FEATURE_SECTION_MAP FSM ON FSM.ID_SECTION = FS.ID WHERE FSM.ID_FEATURE=?),"
				+ "?,"
				+ "?)";
		
		KeyHolder featureDeleteKey =  new GeneratedKeyHolder();
		final String i = id;
		
		CalendarUtils cu = new CalendarUtils();
		final String ts = cu.getDateTimeGMT();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT_FEATURES_DELETED_SQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, Integer.parseInt(i));
				preparedStatement.setInt(2, Integer.parseInt(i));
				preparedStatement.setInt(3, Integer.parseInt(i));
				preparedStatement.setInt(4, Integer.parseInt(i));
				preparedStatement.setString(5, session.getAttribute("userId").toString());
				preparedStatement.setString(6, ts);
		
				return preparedStatement;
			}
		}, featureDeleteKey);
		
		if(featureDeleteKey!=null && featureDeleteKey.getKey().intValue() > 0) {
			final String DELETE_FEATURES_HISTORY_SQL = "DELETE FROM FEATURE_TOGGLE_HISTORY WHERE ID_FEATURE=?";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(DELETE_FEATURES_HISTORY_SQL);
					preparedStatement.setInt(1,Integer.parseInt(i));
					return preparedStatement;
				}
			});
			
			final String DELETE_FEATURES_SESSION_MAPPING_SQL = "DELETE FROM FEATURE_SECTION_MAP WHERE ID_FEATURE=?";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(DELETE_FEATURES_SESSION_MAPPING_SQL);
					preparedStatement.setInt(1,Integer.parseInt(i));
					return preparedStatement;
				}
			});
			
			final String DELETE_FEATURES_TOGGLE_SQL = "DELETE FROM FEATURE_TOGGLE WHERE ID=?";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(DELETE_FEATURES_TOGGLE_SQL);
					preparedStatement.setInt(1,Integer.parseInt(i));
					return preparedStatement;
				}
			});
			success = true;
			message = "Feature deleted successfully";
			
		}else {
			success = false;
			message = "Failed to Delete Feature";
		}
		
		
		
		ModelAndView mv;
		mv = getFeatures(session);
		
		if(success) {
			mv.addObject("successmessage",message);
		}else {
			mv.addObject("failuremessage",message);
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/admin/deleteSection", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('PERM_FEATURES')")
	public ModelAndView deleteSection(@ModelAttribute("section") @Valid final String section,
			HttpServletRequest request, HttpServletResponse response,
			Model model, BindingResult result, RedirectAttributes redirectAttrs,
			HttpSession session) throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		boolean success = false;
		String message = "";
		
		
		final String DELETE_FEATURES_TOGGLE_SQL = "DELETE FROM FEATURE_SECTION WHERE TX_SECTION=?";
		int ret = 0;
		ret = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_FEATURES_TOGGLE_SQL);
				preparedStatement.setString(1,section);
				return preparedStatement;
			}
		});
		
		if(ret>0) {
			success = true;
			message = "Section deleted successfully";	
		}else {
			success = false;
			message = "Failed to delete section";
		}
		
		
		
		mv = getFeatures(session);
		
		if(success) {
			mv.addObject("successmessage",message);
		}else {
			mv.addObject("failuremessage",message);
		}
		
		return mv;
	}
}
