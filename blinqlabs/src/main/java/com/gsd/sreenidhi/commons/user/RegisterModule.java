package com.gsd.sreenidhi.commons.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gsd.sreenidhi.exceptions.SystemException;
import com.gsd.sreenidhi.mail.SendMail;
import com.gsd.sreenidhi.mail.WelcomMailLForm;
import com.gsd.sreenidhi.register.Register;
import com.gsd.sreenidhi.security.encryption.Bcrypt;
import com.gsd.sreenidhi.user.messages.MessageActions;
import com.gsd.sreenidhi.utils.CalendarUtils;
import com.jcraft.jsch.Session;

public class RegisterModule {
	
	private JdbcTemplate jdbcTemplate = null;
	
	@Autowired
	public RegisterModule(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	public void registerAccount(Register rF) {
		
		

		final Register registerForm = rF;
		final KeyHolder accountKey = new GeneratedKeyHolder();
		final KeyHolder userKey = new GeneratedKeyHolder();
			
			Bcrypt bcrypte = new Bcrypt();
			final String encryptedPassword = "{bcrypt}"+bcrypte.encode(rF.getPassword());
			
			final String INSERT_ACCOUNTS_SQL = "INSERT INTO ACCOUNTS (TX_EMAIL, TX_PASSWORD, FL_ENABLED, TS_CREATE, FL_2FA) VALUES (?,?,?,?,?)";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNTS_SQL, Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, registerForm.getEmail());
					preparedStatement.setString(2, encryptedPassword);
					preparedStatement.setInt(3, 1);
					preparedStatement.setString(4, CalendarUtils.dateToStringDateTimeReadable(new Date()));
					preparedStatement.setInt(5, 0);
					return preparedStatement;
				}
			},accountKey);	
    		
    		
    		final String INSERT_AUTHORITY_SQL = "INSERT INTO AUTHORITY(ID_ACCOUNT, ID_ACCOUNT_ROLE) values (?,?)";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHORITY_SQL);
					preparedStatement.setInt(1, accountKey.getKey().intValue());
					preparedStatement.setInt(2, 4);
					return preparedStatement;
				}
			});	
    	
    		final String INSERT_USER_SQL = "INSERT INTO USERS(ID_ACCOUNT, TX_FIRSTNAME, TX_LASTNAME) values (?,?,?)";    	
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, accountKey.getKey().intValue());
					preparedStatement.setString(2, registerForm.getFirstname());
					preparedStatement.setString(3, registerForm.getLastname());
					return preparedStatement;
				}
			},userKey);	
    		
    		Date d = new Date();
    		MessageActions ma = new MessageActions();
    		WelcomMailLForm wml = new WelcomMailLForm();
    		
    		
    		final String INSERT_ACCOUNT_VERIFICATION_SQL = "INSERT INTO ACCOUNT_VERIFICATION(FL_EMAIL_VERIFIED, TX_EMAIL_VERIFIED, TS_EMAIL_VERIFIED, FL_MOB_VERIFIED, TX_MOB_VERIFIED, TS_MOB_VERIFIED, ID_USER)" 
    				+ " VALUES(0,null,null, 0, null, null, ?)" ; 
    				
    		jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_VERIFICATION_SQL);
					preparedStatement.setInt(1, userKey.getKey().intValue());
					return preparedStatement;
				}
			});	
    		
    		WelcomMailLForm wmf = new WelcomMailLForm();
			String emailMessage=wmf.getTX_SIGNUP_MESSAGE().replace("${username}",registerForm.getFirstname()+" "+registerForm.getLastname()).replace("${dateTime}", CalendarUtils.getCurrentTimeStamp());
			SendMail sm = new SendMail();
			  
			  final String emailMsg = emailMessage;
				
			    ExecutorService service = Executors.newFixedThreadPool(4);
			    service.submit(new Runnable() {
			        public void run() {
			        	sm.sendMessage(emailMsg, rF.getEmail(), null, "Welcome to Blinqlabs.", false);
			        }
			    });
			
			ma.insertMessage(this.jdbcTemplate, "Welcome!",emailMessage, accountKey.getKey().intValue(), 1, 0, 0, CalendarUtils.dateToString(d), null, null, 0, null);
    		
    		
	}

}
