package com.gsd.sreenidhi.admin.blogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gsd.sreenidhi.admin.settings.SettingsForm;
import com.gsd.sreenidhi.user.AccountActions;
import com.gsd.sreenidhi.user.AccountSettingForm;
import com.gsd.sreenidhi.utils.CalendarUtils;

public class BlogActions {

	public List<BlogForm> getAllBlogs(int page, String type, String q, JdbcTemplate jdbcTemplate) {
		
		 int countOnPage = 15;
		 
		String queryStringPlaceHolder = "";
		 if(type!=null && type.equals("p")) {
			 queryStringPlaceHolder = " WHERE BLOG_ARTICLES.FL_ACTIVE = 'true' " + ((q!=null && q.length()>2)?" AND " + q.replace("eq", "=").replaceAll("%sp", " "):"") ;
		 }else if(type!=null && type.equals("d")) {
			 queryStringPlaceHolder = " WHERE BLOG_ARTICLES.FL_ACTIVE = 'false' " + ((q!=null && q.length()>2)?" AND " + q.replace("eq", "=").replaceAll("%sp", " "):"");
		 }else {
			 queryStringPlaceHolder ="" + ((q!=null && q.length()>2)?" WHERE " + q.replace("eq", "=").replaceAll("%sp", " "):"");
		 }
		 

			final String GET_BLOG_SQL = 
					 " DECLARE @PageNumber AS INT, @RowspPage AS INT"
					+ " SET @PageNumber = " + page
					+ " SET @RowspPage = " + countOnPage
					+ " SELECT * FROM ( "
					+ " 	  SELECT ROW_NUMBER() OVER(ORDER BY BLOG_ARTICLES.ID DESC) AS NUMBER, "
					+ " 	  BLOG_ARTICLES.ID          AS ID, "
					+ "       BLOG_ARTICLES.TX_TITLE    AS TX_TITLE, "
					+ "       BLOG_ARTICLES.TS_CREATE   AS TS_CREATE, "
					+ "       BLOG_ARTICLES.ID_AUTHOR   AS ID_AUTHOR, "
					+ "       USERS.TX_FIRSTNAME AS TX_FIRSTNAME, "
					+ "       USERS.TX_LASTNAME  AS TX_LASTNAME, "
					+ "       BLOG_ARTICLES.TX_IMAGE    AS TX_IMAGE, "
					+ "       BLOG_ARTICLES.TX_BODY     AS TX_BODY, "
					+ "       BLOG_ARTICLES.FL_ACTIVE   AS FL_ACTIVE, "
					+ " 	  BLOG_ARTICLES.TX_TAGS     AS TX_TAGS	"
					+ "FROM   BLOG_ARTICLES "
					+ "       JOIN USERS "
					+ "         ON USERS.ID_ACCOUNT = BLOG_ARTICLES.ID_AUTHOR "
					+ " 	  JOIN BLOG_CATEGORY_LINK	BCL "
					+ " 		ON BCL.ID_BLOG = BLOG_ARTICLES.ID"
					+ " 	  JOIN BLOG_CATEGORIES BC"
					+ "			ON BC.ID = BCL.ID_BLOG_CATEGORY		"
					+ queryStringPlaceHolder 
					+ "			) AS TBL "
					+ "WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
					
			List<BlogForm> blogList = jdbcTemplate.query(GET_BLOG_SQL, new RowMapper<BlogForm>() {
				public BlogForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					BlogForm blog = new BlogForm();
					blog.setID_BLOG(resulSet.getInt("ID"));
					blog.setTX_TITLE(resulSet.getString("TX_TITLE"));
					blog.setID_AUTHOR(resulSet.getInt("ID_AUTHOR"));
					blog.setTX_IMAGE(resulSet.getString("TX_IMAGE"));
					blog.setTX_BODY(resulSet.getString("TX_BODY")!=null?(resulSet.getString("TX_BODY").length()>50?resulSet.getString("TX_BODY").toString().substring(0,50):resulSet.getString("TX_BODY")):"");
					blog.setFL_ACTIVE(resulSet.getBoolean("FL_ACTIVE"));
					blog.setTX_AUTHOR(resulSet.getString("TX_FIRSTNAME")+" "+resulSet.getString("TX_LASTNAME"));
					blog.setTX_TAGS(resulSet.getString("TX_TAGS"));
					
					return blog;
				}
			});
		return blogList;
	}

	
	
	public List<BlogForm> getBlogCounter(int page, String type, String q, JdbcTemplate jdbcTemplate) {
		 
		String queryStringPlaceHolder = "";
		 if(type!=null && type.equals("p")) {
			 queryStringPlaceHolder = " WHERE BLOG_ARTICLES.FL_ACTIVE = 'true' " + ((q!=null && q.length()>2)?" AND " + q.replace("eq", "="):"") ;
		 }else if(type!=null && type.equals("d")) {
			 queryStringPlaceHolder = " WHERE BLOG_ARTICLES.FL_ACTIVE = 'false' " + ((q!=null && q.length()>2)?" AND " + q.replace("eq", "="):"");
		 }else {
			 queryStringPlaceHolder ="" + ((q!=null && q.length()>2)?" WHERE " + q.replace("eq", "="):"");
		 }
		 
		final String GET_BLOG_COUNT_SQL = "SELECT COUNT(*) AS COUNT " + 
				   "						 from BLOG_ARTICLES    " 
				   + " 	  JOIN BLOG_CATEGORY_LINK	BCL "
					+ " 		ON BCL.ID_BLOG = BLOG_ARTICLES.ID"
					+ " 	  JOIN BLOG_CATEGORIES BC"
					+ "			ON BC.ID = BCL.ID_BLOG_CATEGORY		"
					+ queryStringPlaceHolder  +
					" " ;
	
		List<BlogForm> blogcountList = jdbcTemplate.query(GET_BLOG_COUNT_SQL, new RowMapper<BlogForm>() {
           public BlogForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
           	BlogForm blg = new BlogForm();
           	
           	blg.setBlogCount(resulSet.getInt("COUNT"));
           	return blg;
           }
       });
		
		return blogcountList;
	}



	public List<BlogForm> getBlogById(String id, String status, JdbcTemplate jdbcTemplate) {
		final String GET_BLOG_SQL = "SELECT BA.ID          AS ID, "
				+ "       BA.TX_TITLE    AS TX_TITLE, "
				+ "       BA.TS_CREATE   AS TS_CREATE, "
				+ "       BA.ID_AUTHOR   AS ID_AUTHOR, "
				+ " 	  BA.TX_TAGS 	 AS TX_TAGS, "
				+ "       U.TX_FIRSTNAME AS TX_FIRSTNAME, "
				+ "       U.TX_LASTNAME  AS TX_LASTNAME, "
				+ " 	  U.TX_BIO 		 AS	TX_BIO, "
				+ " 	  U.TX_IMAGE	 AS USER_IMAGE,	"
				+ "       BA.TX_IMAGE    AS TX_IMAGE, "
				+ "       BA.TX_BODY     AS TX_BODY, "
				+ "       BA.FL_ACTIVE   AS FL_ACTIVE, "
				+ " 	  BC.TX_CATEGORY AS TX_CATEGORY,	"
				+ " 	  BC.ID			 AS ID_CATEGORY	"
				+ "FROM   BLOG_ARTICLES BA "
				+ "       JOIN USERS U "
				+ "         ON U.ID_ACCOUNT = BA.ID_AUTHOR "
				+ " 	  JOIN BLOG_CATEGORY_LINK	BCL "
				+ " 		ON BCL.ID_BLOG = BA.ID"
				+ " 	  JOIN BLOG_CATEGORIES BC"
				+ "			ON BC.ID = BCL.ID_BLOG_CATEGORY		"
				+ "WHERE  BA.ID = " + id;
		
		List<BlogForm> blogList = jdbcTemplate.query(GET_BLOG_SQL, new RowMapper<BlogForm>() {
			public BlogForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				BlogForm blog = new BlogForm();
				blog.setID_BLOG(resulSet.getInt("ID"));
				blog.setTX_TITLE(resulSet.getString("TX_TITLE"));
				blog.setID_AUTHOR(resulSet.getInt("ID_AUTHOR"));
				blog.setTX_IMAGE(resulSet.getString("TX_IMAGE"));
				blog.setTX_BODY(resulSet.getString("TX_BODY"));
				blog.setTX_TAGS(resulSet.getString("TX_TAGS"));
				blog.setFL_ACTIVE(resulSet.getBoolean("FL_ACTIVE"));
				blog.setTS_CREATE(resulSet.getString("TS_CREATE"));
				blog.setTX_AUTHOR(resulSet.getString("TX_FIRSTNAME")+" "+resulSet.getString("TX_LASTNAME"));
				blog.setTX_USER_BIO(resulSet.getString("TX_BIO"));
				blog.setTX_CATEGORY(resulSet.getString("TX_CATEGORY"));
				blog.setID_CATEGORY(resulSet.getInt("ID_CATEGORY"));
				blog.setAUTHOR_IMAGE(resulSet.getString("USER_IMAGE"));
				return blog;
			}
		});
		
		return blogList;
	}



	public List<BlogCategoryForm> getBlogCategories(String id, String status, JdbcTemplate jdbcTemplate) {

		String BLOG_CATEGORIES_SQL = "SELECT  "
				+ " 	  BLOG_CATEGORIES.ID          AS ID, "
				+ "       BLOG_CATEGORIES.TX_CATEGORY AS TX_CATEGORY, "
				+ "       BLOG_CATEGORIES.FL_ACTIVE   AS FL_ACTIVE "
				+ " FROM   BLOG_CATEGORIES "
				+ " WHERE BLOG_CATEGORIES.FL_ACTIVE = 1 ";
		
		
		List<BlogCategoryForm> blogCatList = jdbcTemplate.query(BLOG_CATEGORIES_SQL, new RowMapper<BlogCategoryForm>() {
			public BlogCategoryForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				BlogCategoryForm blog = new BlogCategoryForm();
				blog.setID(resulSet.getInt("ID"));
				blog.setTX_CATEGORY(resulSet.getString("TX_CATEGORY"));
				blog.setFL_ACTIVE(resulSet.getBoolean("FL_ACTIVE"));
				return blog;
			}
		});
				
		return blogCatList;
	}

	
	
	public List<BlogCommentForm> getAllComments(int page, JdbcTemplate jdbcTemplate, String id) {
		
		
	
		
		
		 final String GET_COMMENTS_SQL = 
				 "			SELECT "
				 + "				BLOG_ARTICLES.ID AS ID_BLOG, "
				 + "				BLOG_COMMENTS.ID AS ID_COMMENT, "
				 + "				BLOG_COMMENTS.TX_BODY AS TX_COMMENT_MESSAGE, "
				 + "				BLOG_COMMENTS.ID_DEPTH AS DEPTH, "
				 + "				BLOG_COMMENTS.TX_COUNT_LIKES AS COUNT_LIKES, "
				 + "				BLOG_COMMENTS.TS_CREATE AS TS_CREATE, "
				 + "				BLOG_COMMENTS.FL_EDITED AS FL_EDITED, "
				 + "				BLOG_COMMENT_AUTHORS.ID_USER AS ID_AUTHOR_SYS_USER, "
				 + "				BLOG_COMMENT_AUTHORS.TX_NAME AS TX_AUTHOR_NAME, "
				 + "				BLOG_COMMENT_AUTHORS.TX_EMAIL AS TX_AUTHOR_EMAIL, "
				 + "				BLOG_COMMENT_HIERARCHY.ID_CMT_PARENT AS ID_PARENT, "
				 + " 				USERS.TX_IMAGE AS AUTH_IMAGE"
				 + "			FROM "
				 + "				BLOG_ARTICLES "
				 + "			JOIN BLOG_COMMENT_LINK "
				 + "				ON BLOG_COMMENT_LINK.ID_BLOG = BLOG_ARTICLES.ID "
				 + "			JOIN	BLOG_COMMENTS "
				 + "				ON BLOG_COMMENT_LINK.ID_COMMENT =  BLOG_COMMENTS.ID "
				 + "			JOIN BLOG_COMMENT_AUTHORS "
				 + "				ON 	BLOG_COMMENT_AUTHORS.ID_COMMENT = BLOG_COMMENTS.ID "
				 + "			JOIN BLOG_COMMENT_HIERARCHY "
				 + "				ON 	BLOG_COMMENTS.ID = BLOG_COMMENT_HIERARCHY.ID_COMMENT"
				 + "			JOIN USERS"
				 + "				ON USERS.ID_ACCOUNT = BLOG_COMMENT_AUTHORS.ID_USER"
				 + " 			WHERE BLOG_ARTICLES.ID = " + id
				 + " 			";
				 
			
		 List<BlogCommentForm> blogCmtList = jdbcTemplate.query(GET_COMMENTS_SQL, new RowMapper<BlogCommentForm>() {
				public BlogCommentForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
					BlogCommentForm blogCmt = new BlogCommentForm();
					
					blogCmt.setID_BLOG(resulSet.getInt("ID_BLOG"));
					blogCmt.setID_COMMENT(resulSet.getInt("ID_COMMENT"));
					blogCmt.setTX_COMMENT_MESSAGE(resulSet.getString("TX_COMMENT_MESSAGE"));
					blogCmt.setDEPTH(resulSet.getInt("DEPTH"));
					blogCmt.setCOUNT_LIKES(resulSet.getInt("COUNT_LIKES"));
					blogCmt.setTS_CREATE(resulSet.getString("TS_CREATE"));
					blogCmt.setFL_EDITED(resulSet.getInt("FL_EDITED")==0?false:true);
					blogCmt.setID_SYS_USER(resulSet.getInt("ID_AUTHOR_SYS_USER"));
					blogCmt.setTX_NAME(resulSet.getString("TX_AUTHOR_NAME"));
					blogCmt.setTX_EMAIL(resulSet.getString("TX_AUTHOR_EMAIL"));
					blogCmt.setID_PARENT(resulSet.getInt("ID_PARENT"));
					blogCmt.setUSR_IMAGE(resulSet.getString("AUTH_IMAGE"));
					return blogCmt;
				}
			});
		 
		return blogCmtList;
	}



	public void addComment(@Valid BlogCommentForm blogCommentBean, JdbcTemplate jdbcTemplate, HttpSession session) {
		
		final String INSET_BLOG_COMMENT_SQL = "INSERT INTO BLOG_COMMENTS(TX_BODY, ID_DEPTH, TX_COUNT_LIKES, TS_CREATE, FL_EDITED) VALUES(?,?,?,?,?)";
		
		final KeyHolder blogCmtKey = new GeneratedKeyHolder();
		final KeyHolder blogCmtLinkKey = new GeneratedKeyHolder();
		final KeyHolder blogCmtAuthorKey = new GeneratedKeyHolder();
		final KeyHolder blogCmtHierarchyKey = new GeneratedKeyHolder();
		
		Date dt = new Date();
		String date = CalendarUtils.dateToStringDateTimeReadable(dt);
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSET_BLOG_COMMENT_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, blogCommentBean.getTX_COMMENT_MESSAGE());
				preparedStatement.setInt(2, blogCommentBean.getDEPTH());
				preparedStatement.setInt(3, 0);
				preparedStatement.setString(4, date);
				preparedStatement.setInt(5, 0);
				
				return preparedStatement;
			}
		}, blogCmtKey);
		
		int blogCommentId = blogCmtKey.getKey().intValue();
		blogCommentBean.setID_COMMENT(blogCommentId);
		
		final String INSET_BLOG_COMMENT_LINK_SQL = "INSERT INTO BLOG_COMMENT_LINK(ID_BLOG, ID_COMMENT) VALUES(?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSET_BLOG_COMMENT_LINK_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, blogCommentBean.getID_BLOG());
				preparedStatement.setInt(2, blogCommentId);
				
				return preparedStatement;
			}
		}, blogCmtLinkKey);
		
		int blogCommentLinkId = blogCmtLinkKey.getKey().intValue();
		
		
		List<AccountSettingForm> accSettingList = null;
		String userId ;
		 int usrId=0;
		if(session.getAttribute("userId")!=null) {
			  userId = session.getAttribute("userId").toString().trim();
			  usrId = Integer.parseInt(userId);
				
			AccountActions aa = new AccountActions();
			accSettingList = aa.getAccount(usrId, jdbcTemplate)	;
		}
		
		final int uid = usrId;
		final List<AccountSettingForm> acList = accSettingList;
		
		final String INSET_BLOG_COMMENT_AUTHOR_SQL = "INSERT INTO BLOG_COMMENT_AUTHORS(FL_SYSTEM_USER, ID_USER, TX_NAME, TX_EMAIL, ID_COMMENT) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSET_BLOG_COMMENT_AUTHOR_SQL,
				Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, blogCommentBean.isFL_SYSTEM_USER()==true?1:0);
				preparedStatement.setInt(2, session.getAttribute("userId")!=null?uid:0);
				preparedStatement.setString(3, blogCommentBean.getTX_NAME());
				preparedStatement.setString(4, session.getAttribute("userId")!=null?acList.get(0).getTx_email():blogCommentBean.getTX_EMAIL());
				preparedStatement.setInt(5, blogCommentBean.getID_COMMENT());
				
				return preparedStatement;
			}
		}, blogCmtAuthorKey);
		
		int blogCommentAuthorId = blogCmtAuthorKey.getKey().intValue();
		
		
		final String INSET_BLOG_COMMENT_HIERARCHY_SQL = "INSERT INTO BLOG_COMMENT_HIERARCHY(ID_COMMENT,ID_CMT_PARENT) VALUES(?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSET_BLOG_COMMENT_HIERARCHY_SQL,
				Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1,blogCommentId);
				preparedStatement.setInt(2, blogCommentBean.getID_PARENT());
				
				return preparedStatement;
			}
		}, blogCmtHierarchyKey);
		
		int blogCommentHierarchyId = blogCmtHierarchyKey.getKey().intValue();
		
		System.out.println("All Good");
	}
}
