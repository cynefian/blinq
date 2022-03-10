package com.gsd.sreenidhi.admin.blogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.admin.settings.SettingsForm;
import com.gsd.sreenidhi.utils.CalendarUtils;

@Controller
public class BlogController {
	public static BlogForm blogBean ;
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public BlogController(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@ModelAttribute("blogBean")
	public BlogForm blogFormBean() {
		return new BlogForm();
	}
	
	
	@RequestMapping(value = "/admin/blogs/deleteBlog", method = RequestMethod.POST)
	public ModelAndView deleteBlog(HttpSession session,@RequestParam("id") final String id,@RequestParam(value="status", required=false) final String status) {
		
		
		final String DELETE_BLOG_CATEGORY_LINK_SQL = "DELETE FROM BLOG_CATEGORY_LINK WHERE ID_BLOG = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_BLOG_CATEGORY_LINK_SQL);
				preparedStatement.setString(1,id);
				return preparedStatement;
			}
		});
		
		final String DELETE_BLOG_COMMENT_AUTHOR_SQL = "DELETE FROM BLOG_COMMENT_AUTHORS WHERE ID_COMMENT IN (SELECT ID_COMMENT FROM BLOG_COMMENT_LINK WHERE ID_BLOG = ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_BLOG_COMMENT_AUTHOR_SQL);
				preparedStatement.setString(1,id);
				return preparedStatement;
			}
		});
		
		final String DELETE_BLOG_COMMENT_HIERARCHY_SQL = "DELETE FROM BLOG_COMMENT_HIERARCHY WHERE ID_COMMENT IN (SELECT ID_COMMENT FROM BLOG_COMMENT_LINK WHERE ID_BLOG = ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_BLOG_COMMENT_HIERARCHY_SQL);
				preparedStatement.setString(1,id);
				return preparedStatement;
			}
		});
		
		
		final String DELETE_BLOG_COMMENTS_SQL = "DELETE FROM BLOG_COMMENTS WHERE ID IN (SELECT ID_COMMENT FROM BLOG_COMMENT_LINK WHERE ID_BLOG = ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_BLOG_COMMENTS_SQL);
				preparedStatement.setString(1,id);
				return preparedStatement;
			}
		});
		
		
		final String DELETE_BLOG_COMMENT_LINKS_SQL = "DELETE FROM BLOG_COMMENT_LINK WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_BLOG_COMMENT_LINKS_SQL);
				preparedStatement.setString(1,id);
				return preparedStatement;
			}
		});
		
		
		final String DELETE_BLOG_SQL = "DELETE FROM BLOG_ARTICLES WHERE ID = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_BLOG_SQL);
				preparedStatement.setString(1,id);
				return preparedStatement;
			}
		});
		
		/*
		 * ModelAndView mv = getAllBlogs(session,1); mv.addObject("successmessage",
		 * "The blog has been successfully deleted"); return mv;
		 */		
		String redirection;
		if(status!=null&&status.equalsIgnoreCase("true")) {
			redirection = "redirect:/admin/blogs/viewAllBlogs?page=1&type=p";
		}else {
			redirection = "redirect:/admin/blogs/viewAllBlogs?page=1&type=a";
		}
		
		 return new ModelAndView(redirection);
	}
	
	
	@RequestMapping(value = "/admin/blogs/viewAllBlogs", method = RequestMethod.GET)
	public ModelAndView getAllBlogs(HttpSession session, @RequestParam("page") int page, @RequestParam("type") String type,  @RequestParam(value="q", required=false) String q) {
		
		BlogActions BA = new BlogActions();
		
		List<BlogForm> blogList = BA.getAllBlogs(page,type,q, this.jdbcTemplate);
		List<BlogForm> blogcountList = BA.getBlogCounter(page,type,q, this.jdbcTemplate);
		
		 int countOnPage = 15;
			
		 
	 	
 		int totalCount =  blogcountList.get(0).getBlogCount();
 		double rounder = (double)totalCount/(double) countOnPage;
	 	
	 	int pages = (int) Math.ceil(rounder);
	 	
	 	logger.info("Total count: " + totalCount);
	 	logger.info("Total rounder: " + rounder);
	 	logger.info("Total Pages: " + pages);
		
		
		
		BlogForm blist[][] = new BlogForm[5][3];
		int counter = 0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<3;j++) {
					if(counter<blogList.size()) {
						blist[i][j] = blogList.get(counter);
					}
					counter++;
				}
			}
		
		
		ModelAndView mv = new ModelAndView("admin/blogs/viewallblogs");
		mv.addObject("listCount",blogList.size());
		mv.addObject("bList", blist);
		mv.addObject("blogList", blogList);
		mv.addObject("totalPages",pages);
		mv.addObject("currentPage",page);
		mv.addObject("blogType", type);
		mv.addObject("query",q);
		return mv;
	}
	
	@RequestMapping(value = "/admin/blogs/viewBlog", method = RequestMethod.GET)
	public ModelAndView getBlogById(HttpSession session, @RequestParam("id") String id, @RequestParam("status") String status) throws SQLException {
		
		BlogActions BA = new BlogActions();
		
		List<BlogForm> blogList = BA.getBlogById(id,status, this.jdbcTemplate);
		List<BlogCategoryForm> blogCatList = BA.getBlogCategories(id,status, this.jdbcTemplate);
		List<BlogCommentForm> blogCommentList = BA.getAllComments(1, jdbcTemplate, id);	
			ModelAndView mv = null;
			
			 if(status!=null && status.equals("true")) {
				 mv = new ModelAndView("admin/blogs/viewblog");
				 mv.addObject("blogList",blogList);
				 mv.addObject("blogCatList",blogCatList);
				 mv.addObject("blogCommentList",blogCommentList);
			 }else {
				 mv = new ModelAndView("admin/blogs/newBlog");
				 mv.addObject("blogList",blogList);
				 mv.addObject("blogCatList",blogCatList);
			 }
			
			 
			 
		return mv;
	}
	
	@RequestMapping(value = "/admin/blogs/newBlog", method = RequestMethod.GET)
	public ModelAndView newBlog(HttpSession session) {

		String BLOG_CATEGORIES_SQL = "SELECT  "
				+ " 	  BLOG_CATEGORIES.ID          AS ID, "
				+ "       BLOG_CATEGORIES.TX_CATEGORY AS TX_CATEGORY, "
				+ "       BLOG_CATEGORIES.FL_ACTIVE   AS FL_ACTIVE "
				+ " FROM   BLOG_CATEGORIES "
				+ " WHERE BLOG_CATEGORIES.FL_ACTIVE = 1 ";
		
		
		List<BlogCategoryForm> blogCatList = this.jdbcTemplate.query(BLOG_CATEGORIES_SQL, new RowMapper<BlogCategoryForm>() {
			public BlogCategoryForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				BlogCategoryForm blog = new BlogCategoryForm();
				blog.setID(resulSet.getInt("ID"));
				blog.setTX_CATEGORY(resulSet.getString("TX_CATEGORY"));
				blog.setFL_ACTIVE(resulSet.getBoolean("FL_ACTIVE"));
				return blog;
			}
		});
				
		ModelAndView mv = new ModelAndView("admin/blogs/newBlog");
		mv.addObject("blogCatList",blogCatList);
		return mv;
		
	}
	
	@RequestMapping(value = "/admin/blogs/postBlog", method = RequestMethod.POST)
	public ModelAndView addBlogPost(@ModelAttribute("blogBean") @Valid BlogForm blogBean, @RequestParam("action") String action,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session){
		
		final BlogForm bF = blogBean;
		
		
		final KeyHolder blogKey = new GeneratedKeyHolder();

		CalendarUtils cu = new CalendarUtils();
		try {
			final String ts = cu.getDateTimeGMT();
		} catch (ParseException e) {
			ModelAndView mv = new ModelAndView("admin/blogs/newBlog");
			mv.addObject("failuremessage","Blog Failed.");
			return mv;
		}
		
		if(action!=null && action.equals("PUBLISH")) {
			bF.setFL_ACTIVE(true);
		}else {
			bF.setFL_ACTIVE(false);
		}

		if(bF.getID_BLOG()>0) {
					
			
			
			final String UPDATE_SQL = "UPDATE BLOG_ARTICLES "
					+ " SET TX_TITLE = ?, "
					+ " ID_AUTHOR = ?, "
					+ " TX_IMAGE = ?, "
					+ " TX_BODY = ?, "
					+ " FL_ACTIVE = ?, "
					+ " TX_TAGS = ?"
					+ " WHERE ID = ? ";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(UPDATE_SQL);
					preparedStatement.setString(1, bF.getTX_TITLE());
					preparedStatement.setInt(2, Integer.parseInt(session.getAttribute("userId").toString().trim()));
					preparedStatement.setString(3, bF.getTX_IMAGE());
					preparedStatement.setString(4, bF.getTX_BODY());
					preparedStatement.setInt(5, bF.isFL_ACTIVE()?1:0);
					preparedStatement.setString(6, bF.getTX_TAGS().replaceAll(",", " ").trim().replaceAll(" +", " "));
					preparedStatement.setInt(7, bF.getID_BLOG());
					return preparedStatement;
				}
			});
			
			
			final String UPDATE_BLOG_CAT_LINK_SQL = "UPDATE BLOG_CATEGORY_LINK "
					+ " SET ID_BLOG_CATEGORY = ? "
					+ " WHERE ID_BLOG = ? ";
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection
							.prepareStatement(UPDATE_BLOG_CAT_LINK_SQL);
					preparedStatement.setInt(1, bF.getID_CATEGORY());
					preparedStatement.setInt(2, bF.getID_BLOG());
					return preparedStatement;
				}
			});
			
			
	
		}else {
			
			Date dt = new Date();
			String date = CalendarUtils.dateToStringReadable(dt);
			
			
			final String INSERT_BLOG_SQL = "INSERT INTO BLOG_ARTICLES (TX_TITLE,  ID_AUTHOR, TX_IMAGE, TX_BODY, FL_ACTIVE, TS_CREATE, TX_TAGS) VALUES (?,?,?,?,?,?,?)";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BLOG_SQL,
							Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, bF.getTX_TITLE());
					preparedStatement.setInt(2, Integer.parseInt(session.getAttribute("userId").toString().trim()));
					preparedStatement.setString(3, bF.getTX_IMAGE());
					preparedStatement.setString(4, bF.getTX_BODY());
					preparedStatement.setInt(5, bF.isFL_ACTIVE()?1:0);
					preparedStatement.setString(6, date);
					preparedStatement.setString(7, bF.getTX_TAGS().replaceAll(",", " ").trim().replaceAll(" +", " "));
					return preparedStatement;
				}
			}, blogKey);
			blogBean.setID_BLOG(blogKey.getKey().intValue());
			
			
			
			final KeyHolder blogCatLinkKey = new GeneratedKeyHolder();
			
			final String INSERT_BLOG_CAT_LINK_SQL = "INSERT INTO BLOG_CATEGORY_LINK (ID_BLOG,  ID_BLOG_CATEGORY) VALUES (?,?)";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BLOG_CAT_LINK_SQL,
							Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, blogKey.getKey().intValue());
					preparedStatement.setInt(2, bF.getID_CATEGORY());
					return preparedStatement;
				}
			}, blogKey);
			
		}
		
		String redirection;
		
		if(action!=null && action.equals("PUBLISH")) {
			redirection = "redirect:/admin/blogs/viewBlog?id="+Integer.toString(blogBean.getID_BLOG())+"&status=true";
		}else {
			redirection = "redirect:/admin/blogs/viewBlog?id="+Integer.toString(blogBean.getID_BLOG())+"&status=false";
		}

		return new ModelAndView(redirection);
		
	}
	
	@RequestMapping(value = "/admin/blogs/createBlogCategory", method = RequestMethod.POST)
	public ModelAndView createBlogCategory(@ModelAttribute("SettingsBean") @Valid SettingsForm settingsBean, 
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session){
		
		final String INSET_BLOG_CATEGORY_SQL = "INSERT INTO BLOG_CATEGORIES(TX_CATEGORY, FL_ACTIVE) VALUES(?,?)";
		final SettingsForm sF = settingsBean;
		final KeyHolder blogCatKey = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSET_BLOG_CATEGORY_SQL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, sF.getTx_category_name());
				preparedStatement.setInt(2, 1);
				
				return preparedStatement;
			}
		}, blogCatKey);
		
		
		 String redirection = "redirect:/admin/settings/blogs/blogCategories?page=1";
		return new ModelAndView(redirection);
	}
	
	@RequestMapping(value = "/admin/settings/blogs/blogCategories", method = RequestMethod.GET)
	public ModelAndView getBlogCategory(HttpSession session,@RequestParam("page") int page) {
		
		 int countOnPage = 30;
			
		
		final String GET_BLOG_CATEGORY_COUNT_SQL = "SELECT COUNT(*) AS COUNT " + 
				   "						 from BLOG_CATEGORIES   " ;
	
		List<SettingsForm> blogCatcountList = this.jdbcTemplate.query(GET_BLOG_CATEGORY_COUNT_SQL, new RowMapper<SettingsForm>() {
            public SettingsForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
            	SettingsForm sF = new SettingsForm();
            	
            	sF.setBlog_category_count(resulSet.getString("COUNT"));
            	return sF;
            }
        });
	 	
 		int totalCount =  Integer.parseInt(blogCatcountList.get(0).getBlog_category_count().trim());
 		double rounder = (double)totalCount/(double) countOnPage;
	 	
	 	int pages = (int) Math.ceil(rounder);
	 	
	 	logger.info("Total count: " + totalCount);
	 	logger.info("Total rounder: " + rounder);
	 	logger.info("Total Pages: " + pages);
		
		
		final String GET_BLOG_SQL = 
				 " DECLARE @PageNumber AS INT, @RowspPage AS INT"
				+ " SET @PageNumber = " + page
				+ " SET @RowspPage = " + countOnPage
				+ " SELECT * FROM ( "
				+ " 	  SELECT ROW_NUMBER() OVER(ORDER BY BLOG_CATEGORIES.ID DESC) AS NUMBER, "
				+ " 	  BLOG_CATEGORIES.ID          AS ID, "
				+ "       BLOG_CATEGORIES.TX_CATEGORY AS TX_CATEGORY, "
				+ "       BLOG_CATEGORIES.FL_ACTIVE   AS FL_ACTIVE, "
				+ " ( SELECT COUNT(*) FROM BLOG_CATEGORY_LINK WHERE BLOG_CATEGORY_LINK.ID_BLOG_CATEGORY = BLOG_CATEGORIES.ID) AS CATEGORY_COUNT "
				+ " FROM   BLOG_CATEGORIES "
				+" 			"
				+" 		) AS TBL "
				+ "WHERE NUMBER BETWEEN ((@PageNumber - 1) * @RowspPage + 1) AND (@PageNumber * @RowspPage)";
				
		List<BlogCategoryForm> blogCatList = this.jdbcTemplate.query(GET_BLOG_SQL, new RowMapper<BlogCategoryForm>() {
			public BlogCategoryForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
				BlogCategoryForm blogCategory = new BlogCategoryForm();
				blogCategory.setID(resulSet.getInt("ID"));
				blogCategory.setTX_CATEGORY(resulSet.getString("TX_CATEGORY"));
				blogCategory.setFL_ACTIVE(resulSet.getInt("FL_ACTIVE")==1?true:false);
				blogCategory.setCOUNTER(resulSet.getInt("CATEGORY_COUNT"));
				
				return blogCategory;
			}
		});
		
	
		
		
		ModelAndView mv = new ModelAndView("admin/settings/blog/blogCategories");
		mv.addObject("listCount",blogCatList.size());
		mv.addObject("blogList", blogCatList);
		mv.addObject("totalPages",pages);
		mv.addObject("currentPage",page);
		mv.addObject("mainTab","System");
		mv.addObject("subTab","BlogCategories");
		
		return mv;
		
	}
	
	
	@RequestMapping(value = "/admin/settings/blogs/updateBlogCategoryFlag", method = RequestMethod.POST)
	public ModelAndView updateBlogCategory(HttpSession session,@RequestParam("id") int id,@RequestParam("value") int value) {
		
		final String UPDATE_BLOG_CATEGORY_SQL = "UPDATE BLOG_CATEGORIES " + 
				   "						 SET FL_ACTIVE = ?   " +
				   "						 WHERE ID = ?   " ;
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(UPDATE_BLOG_CATEGORY_SQL);
				preparedStatement.setInt(1, value==0?1:0);
				preparedStatement.setInt(2, id);
				return preparedStatement;
			}
		});
		
		
		String redirection = "redirect:/admin/settings/blogs/blogCategories?page=1";
		return new ModelAndView(redirection);
	}
	
	@RequestMapping(value = "/admin/settings/blogs/deleteBlogCategory", method = RequestMethod.POST)
	public ModelAndView deleteBlogCategory(HttpSession session,@RequestParam("id") int id, @RequestParam(value="remap",required=false) int remap) {
		
		
		final String REMAP_BLOG_CATEGORY_SQL = "UPDATE BLOG_CATEGORY_LINK " +
					"	SET ID_BLOG_CATEGORY = ? "+
				   "						 WHERE ID_BLOG_CATEGORY = ?   " ;
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(REMAP_BLOG_CATEGORY_SQL);
				preparedStatement.setInt(1, remap);
				preparedStatement.setInt(2, id);
				return preparedStatement;
			}
		});
		
		
		final String DELETE_BLOG_CATEGORY_SQL = "DELETE FROM BLOG_CATEGORIES " + 
				   "						 WHERE ID = ?   " ;
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(DELETE_BLOG_CATEGORY_SQL);
				preparedStatement.setInt(1, id);
				return preparedStatement;
			}
		});
		
		String redirection = "redirect:/admin/settings/blogs/blogCategories?page=1";
		return new ModelAndView(redirection);
	}
	
	@RequestMapping("/admin/blogs/submitComment" )
	public ModelAndView submitComment(@ModelAttribute("BlogCommentBean") @Valid BlogCommentForm blogCommentBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs,
			final HttpSession session) throws SQLException {
		
		BlogActions BA = new BlogActions();
		
		BA.addComment(blogCommentBean, jdbcTemplate, session);

		String redirection;
		
			redirection = "redirect:/admin/blogs/viewBlog?id="+blogCommentBean.getID_BLOG()+"&status=true";
		
		return new ModelAndView(redirection);
	
	}
}