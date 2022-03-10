package com.gsd.sreenidhi.admin.products;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsd.sreenidhi.security.licensing.License;


@Controller

@SessionAttributes("productBean")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	private JdbcTemplate jdbcTemplate = null;
		
		@Autowired
		public ProductController(DataSource dataSource) {
			this.jdbcTemplate = (new JdbcTemplate(dataSource));
		}

		 @ModelAttribute("productBean")
		 public ProductForm createProdyctFormBean() {
		 	return new ProductForm();
		 }
		 
		 @RequestMapping(value ="/admin/adminProductList", method=RequestMethod.GET)  
		 public ModelAndView getProductList(HttpSession session) {
			 
			 final String userId = session.getAttribute("userId").toString().trim();
				final int usrId = Integer.parseInt(userId);
				
			
				
				List<ProductForm> productList =getProductList(2,1);
			 
				ModelAndView mv =  new ModelAndView("admin/product/manageProducts");
				mv.addObject("productList", productList);
		//		session.setAttribute("productList", productList);
				
			return mv; 
			 
		 }
		 
		 @RequestMapping(value ="/admin/adminProductList",method=RequestMethod.POST)  
		 public ModelAndView postProductList(@ModelAttribute("productBean") @Valid ProductForm productBean, 
				   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
			 final ProductForm pb = productBean; 
			 final KeyHolder productKey = new GeneratedKeyHolder();
			 
				final String INSERT_PRODUCT_SQL = "INSERT INTO PRODUCTS (FL_ACTIVE, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_LICENSE) VALUES (?,?,?,?)";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL, Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setInt(1, pb.isFL_ACTIVE()?1:0);
						preparedStatement.setString(2, pb.getTX_PRODUCT_NAME());
						preparedStatement.setString(3, pb.getTX_PRODUCT_DESC());
						preparedStatement.setInt(4, pb.isFL_LICENSE()?1:0);
						return preparedStatement;
					}
				},productKey);	
			 productBean.setID(productKey.getKey().intValue());
			 ModelAndView mv =  getProductList(session);
			 return mv;
		 }
		 
		 @RequestMapping(value ="/admin/adminProductList",method=RequestMethod.PUT)  
		 public void updateProductList(@ModelAttribute("productBean") @Valid ProductForm productBean, 
				   BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
			 
			 final ProductForm pb = productBean; 
			 
				final String INSERT_PRODUCT_SQL = "UPDATE PRODUCTS SET FL_ACTIVE=?, TX_PRODUCT_NAME=?, TX_PRODUCT_DESC=?, FL_LICENSE=? WHERE ID = ?";    	
				jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
						preparedStatement.setInt(1, pb.isFL_ACTIVE()?1:0);
						preparedStatement.setString(2, pb.getTX_PRODUCT_NAME());
						preparedStatement.setString(3, pb.getTX_PRODUCT_DESC());
						preparedStatement.setInt(4, pb.isFL_LICENSE()?1:0);
						preparedStatement.setInt(4, pb.getID());
						return preparedStatement;
					}
				});	
			 
			 getProductList(session);
		 }
		 
		 
		 @RequestMapping(value ="/productDDList", method=RequestMethod.GET)  
		 public void getProductDDList(HttpServletRequest request,
		            HttpServletResponse response, HttpSession session) throws IOException {
			 
			 final String userId = session.getAttribute("userId").toString().trim();
				final int usrId = Integer.parseInt(userId);
				
			
				
				List<ProductForm> productList =getProductList(2,1);
				
				String ops="";
				if(productList!=null) {
					for(int i=0;i<productList.size();i++) {
						ops=ops+productList.get(i).getID()+":"+productList.get(i).getTX_PRODUCT_NAME()+",";
					}
				}
				
				if(ops.length()>1) {
					ops = ops.substring(0,ops.length()-1);
				}
			 
				response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(ops);
			 
		 }

		 public List<ProductForm> getProductList(int active, int license){
			 String productSearchQuery;
			 if(active==0) {
				//Only In-Active Products
				 if(license==0) {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_ACTIVE = 0 AND FL_LICENSE = 0";
				 }else if (license==1) {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_ACTIVE = 0 AND FL_LICENSE = 1";
				 }else {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_ACTIVE = 0";
				 }
				 
				 
			 }else if (active ==1) {
				 //Only Active Products
				 if(license==0) {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_ACTIVE = 1 AND FL_LICENSE = 0";
				 }else if (license==1) {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_ACTIVE = 1 AND FL_LICENSE = 1";
				 }else {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_ACTIVE = 1";
				 }
			 }else {
				 //All Products
				 if(license==0) {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_LICENSE = 0";
				 }else if (license==1) {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS WHERE FL_LICENSE = 1";
				 }else {
					 productSearchQuery = "SELECT ID, TX_PRODUCT_NAME, TX_PRODUCT_DESC, FL_ACTIVE, FL_LICENSE FROM PRODUCTS";
				 }
			 }
			 
			 final String GET_PRODUCTS_QUERY_SQL = productSearchQuery;
			 List<ProductForm> productList = this.jdbcTemplate.query(GET_PRODUCTS_QUERY_SQL, new RowMapper<ProductForm>() {
		            public ProductForm mapRow(ResultSet resulSet, int rowNum) throws SQLException {
		            	ProductForm product = new ProductForm();
		            	product.setID(resulSet.getInt("ID"));
		            	product.setTX_PRODUCT_NAME(resulSet.getString("TX_PRODUCT_NAME"));
		            	product.setTX_PRODUCT_DESC(resulSet.getString("TX_PRODUCT_DESC"));
		            	product.setFL_ACTIVE(resulSet.getInt("FL_ACTIVE")==1?true:false);
		            	product.setFL_LICENSE(resulSet.getInt("FL_LICENSE")==1?true:false);
		            	return product;
		            }
		        });
			 
			 return productList;
		 }
}
