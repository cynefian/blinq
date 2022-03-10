<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
	
	   <!-- start main banner area -->
        <div class="animated-banner-area header-secondary bg-theme-90">
	  <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
	
	 <section class="service-section">
            <div class="container">
                <div class="section-heading2">
                    <span class="alt-font">Career</span>
                    <h2>Join our crew and Innovate.</h2>
                    
                </div>
	      <div class="row">
                    <div class="col-lg-6 col-md-12 sm-margin-30px-bottom">
                        <img src="${pageContext.request.contextPath}/resources/img/illustration/party.png" alt="" class="img-effect" />
                    </div>
                    <div class="col-lg-6 col-md-12 sm-text-center valign-center">
                    	<br/>
                         <h4 class="font-size18 sm-font-size16 xs-font-size15 margin-10px-bottom text-dark-gray"> We're committed to building a culture where everyone has the opportunity to do meaningful work and be recognized for their efforts.</h4>
                   
                   		<br/>
                   
                    	<a href="javascript:void(0)" class="butn small">Join Us</a>
                    </div>

                </div>
                
                
	 </section>
	 
	 <section class="bg-theme-light">
  		<div class="container">
  			 <div>
                   	<div class="section-heading2">
	                   <span class="alt-font">Perks</span>
	                   
	               </div>
	               
	                        <div class="row">
	                   <div class="col-lg-4 col-md-12 order-2 order-lg-1">
	                 			<div class="title-box margin-25px-bottom sm-margin-15px-bottom">
	                 				<img src="${pageContext.request.contextPath}/resources/img/icons/growth.png" class="img-fluid" alt="">
	                 				<h5>Growth</h5>
	                             </div>
	                 			<div>
	                 			       <p class="font-weight-700">We're growing really fast. Which means lots of opportunity for you to take on a new role. Take a class or join a lunch-and-learn. Get recognized</p>
	                 			</div>
	                 		</div>
	                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
	                 			<div class="title-box margin-25px-bottom sm-margin-15px-bottom">
	                 			<img src="${pageContext.request.contextPath}/resources/img/icons/Work_Life_Balance.png" class="img-fluid" alt="">
	                 				<h5>Work/Life Balance</h5>
	                             </div>
	                 			<div>
	                 			       <p class="font-weight-700">Life is good. We have flexible hours, loads of time off, awesome events, flexible schedules and a remote work program.</p>
	                 			</div>
	                 		</div>
	                 		
	                 		 <div class="col-lg-4 col-md-12 order-2 order-lg-1">
	                 			<div class="title-box margin-25px-bottom sm-margin-15px-bottom">
	                 				<img src="${pageContext.request.contextPath}/resources/img/icons/mental-health.png" class="img-fluid" alt="">
	                 				<h5>Mental Health</h5>
	                             </div>
	                 			<div>
	                 			       <p class="font-weight-700">From Yoga to Games, retreats, swags and pets; we help you stay healthy physically and mentally.</p>
	                 			</div>
	                 		</div>
	                  </div>
	                  <br/><br/>
	                
	                   <div class="row">
	                  
	                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
	                 			<div class="title-box margin-25px-bottom sm-margin-15px-bottom">
	                 				<img src="${pageContext.request.contextPath}/resources/img/icons/ownership.png" class="img-fluid" alt="">
	                 				<h5>Ownership</h5>
	                             </div>
	                 			<div>
	                 			       <p class="font-weight-700">We're all eligible for equity awards. That means when we play together, we win together.</p>
	                 			</div>
	                 		</div>
	                 		
	                 		<div class="col-lg-4 col-md-12 order-2 order-lg-1">
	                 			<div class="title-box margin-25px-bottom sm-margin-15px-bottom">
	                 				<img src="${pageContext.request.contextPath}/resources/img/icons/vacation.png" class="img-fluid" alt="">
	                 				<h5>Time off</h5>
	                             </div>
	                 			<div>
	                 			       <p class="font-weight-700">Parental leaves (more than legally required), time off to volunteer, sick leaves and vacation times - you get it all.</p>
	                 			</div>
	                 		</div>
	                 		
	                 		<div class="col-lg-4 col-md-12 order-2 order-lg-1">
                 				<div class="title-box margin-25px-bottom sm-margin-15px-bottom">
	                              <img src="${pageContext.request.contextPath}/resources/img/icons/allowance.png" class="img-fluid" alt="">
	                            	<h5>Allowances</h5>
	                            </div>
	                 		    <div>
	                 			       <p class="font-weight-700">Company Swag, free coffee, transportation - you know.</p>
	                 			</div>
	                 		</div>
	                  </div>   
	                      		
	                      
                </div>
  		</div>
  	</section>
  	
  	
	 
 	<section>
  		<div class="container">
  			<div class="section-heading">
  				<div class="row">
	                    <div class="col-lg-12 sm-margin-30px-bottom">
	                        <div class="item text-center">
	                        
					  			<div>
					  				<h5>Looking for available positions and opportunities?</h5>
					  			</div>
					  			<br/>
					  			<div>
							  		<a href="javascript:void(0)" class="butn style-two fill md-lg">Join Us</a>
								 </div>
					 		</div>
					 	</div>
			 	</div>
			 </div>
  		</div>
  	</section>
	 
  	<section>
  		<div>
  			<br/>
  		</div>
  	</section>
  
 <
</jsp:body>
</page:platform> 