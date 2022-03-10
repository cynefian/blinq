package com.gsd.sreenidhi.commons;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsd.sreenidhi.admin.services.ServiceTypeForm;
import com.gsd.sreenidhi.admin.tickets.AdminTicketController;


@Controller
@RestController
public class AttachmentController {
	
	private static final long serialVersionUID = 1195564381169111658L;

	private static final Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public AttachmentController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}


	@RequestMapping(value = "/common/downloadAttachment", method = RequestMethod.GET)
	public @ResponseBody List<AttachmentForm> getAttachment(@RequestParam("key") String key, HttpSession session) {
		String GET_ATTACHMENT_SQL = "SELECT ID, TX_ATTACHMENT, TX_NAME, TX_TYPE, TX_SIZE FROM ATTACHMENTS WHERE ID = " + key;

		List<AttachmentForm> SENAttachFormList = jdbcTemplate.query(GET_ATTACHMENT_SQL, new RowMapper<AttachmentForm>() {
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
}
