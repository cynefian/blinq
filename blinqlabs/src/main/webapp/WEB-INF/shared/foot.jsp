<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

	<footer class="footer-style3 bg-white bg-img cover-background" data-background="${pageContext.request.contextPath}/resources/img/content/footer.png">
            <div class="container lg-container">
                <div class="footer-5">
                    <div class="row">
                        <div class="col-lg-3 col-md-6 sm-margin-50px-bottom xs-margin-30px-bottom">
                            <span class="footer-logo margin-25px-bottom display-inline-block">
                                <img src="${pageContext.request.contextPath}/resources/img/logos/blinqlabs.png" alt="logo">
                            </span>
                            <ul>
                                <li><a href="javascript:void(0)">info@blinqlabs.com</a></li>
                          <!--       <li>(+1) 201 286 8643</li>
                                <li>(+91) 702 251 6660</li> -->
                            </ul>
                            <div class="footer-icon">
                                <ul class="no-margin-bottom">
                                    <li>
                                        <a href="javascript:void(0)"><i class="fab fa-facebook-f"></i></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)"><i class="fab fa-twitter"></i></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)"><i class="fab fa-google-plus-g"></i></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)"><i class="fab fa-linkedin-in"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>

						<div class="col-lg-9 col-md-12 sm-margin-50px-bottom xs-margin-30px-bottom">
							<div class="row">
		                        <div class="col-lg-3 col-md-6 sm-margin-50px-bottom xs-margin-30px-bottom">
		                            <h4>About</h4>
		                            <ul class="no-margin-bottom">
		                                <li><a href="${contextPath}/aboutus">About Us</a></li>
		                                <li><a href="${contextPath}/careers">Careers</a></li>
		                                <li><a href="${contextPath}/oss">Open Source Projects</a></li>
		                                <li><a href="${contextPath}/faq?type=g"">FAQ</a></li>
		                            </ul>
		                        </div>
		
		                        <div class="col-lg-3 col-md-6 xs-margin-30px-bottom">
		                            <h4>Services</h4>
		                            <ul class="no-margin-bottom">
		                                <li><a href="${contextPath}/dot">Toolchain</a></li>
		                                <li><a href="${contextPath}/training-certification">Training</a></li>
		                                <li><a href="${contextPath}/services">Enterprise Services</a></li>
		                                <li><a href="${contextPath}/smb">SMB</a></li>
		                            </ul>
		                        </div>
		
		                        <div class="col-lg-3 col-md-6">
		                            <h4>Quick Links</h4>
		                            <ul class="no-margin-bottom">
		                            	<li><a href="${contextPath}/blog?page=1">Blogs</a></li>
		                                <li><a href="${contextPath}/sales">Pricing</a></li>
		                                <li><a href="javascript:void(0)">Status</a></li>
		                                <li><a href="${contextPath}/contact">Contact Us</a></li>
		                                
		                            </ul>
		                        </div>
	                        </div>
	                        
	                        
	                      
		                    <div class="row m-4 p-4">
		                        <div class="col-lg-9 col-md-12 col-sm-12 text-center">
		                              <div class="email-box  wow fadeInUp" data-wow-delay=".3s">
		                              	<form action="${contextPath}/subscribe" method="POST" modelAttribute="SubscriptionBean" id = "subscriptionForm" name="SubscriptionForm">
			                                <span>Sign up for the newsletter and we'll inform you of updates, offers and more.</span>
			                                <div class="input">
			                                    <input type="email" placeholder="Enter your email" name="TX_EMAIL" id="TX_EMAIL" for="TX_EMAIL">
			                                    <button type="submit" class="butn m-2"><span>Subscribe</span></button>
			                                </div>
		                                </form>
		                                
		                            </div>
		                        </div>
		                    </div>
							               
							            
							            
                       </div>
                    </div>
                </div>
            </div>
            <div class="footer-style3-bottom">
                <div class="container">
                	
                	<div class="row">	
                		<div class="col-md-12">
                			<div class="text-center">
								<ul class="footer-breadcrumb">
								  <li><a href="${contextPath}/privacyPolicy">Privacy Policy</a></li>
								  <li><a href="${contextPath}/termsofUse">Terms of use</a></li>
								  <li><a href="${contextPath}/cookieSettings">Cookie Settings</a></li>
								</ul>      		
							</div>
                		</div>
                	</div>
                	
                	<div class="row"> 
                		<div class="col-md-12">
                			<div class="text-center">
								<ul class="footer-breadcrumb">
								  <li>&copy; 2020 Blinqlabs</li>
								  <li>All rights reserved</li>
								</ul>      		
							</div>
                		</div>
               		</div>
                </div>
            </div>
        </footer>
        <!-- end footer section -->
        
        <!-- GDPR Compliance -->
        	<div class="gdpr">
        		<div class="banner-wrapper">
        			<div class="basket">
        				<div class="row">
        					<div class="col-lg-8 col-sm-12">
        						By clicking "Accept Cookies", you agree to the storing of first- and third-party cookies on your device 
								to enhance site navigation, analyze site usage, site performance, and assist in providing you with relevant 
								content. For more information see our <a href="${contextPath}/cookieSettings"><span class="text-info">cookie policy</span></a>.</p>
        					</div>
        					<div class="col-lg-4 col-sm-12 text-right">
        						<button aria-label="Close" type="button" class="btn btn-primary"><span aria-hidden="true">Accept Cookies</span></button>	
        						<button aria-label="Close" type="button" class="btn btn-info"><span aria-hidden="true">x</span></button>		
        					</div>
        				</div>
					    <p>
							
					    
				    </div>
			  </div>
        	</div>
        	<script>
        		// Banner Trigger if Not Closed
	        	if (!localStorage.bannerClosed) {
	        	  $('.gdpr').css('display', 'inherit');
	        	} else {
	        	  $('.gdpr').css('display', 'none');
	        	}
	        	$('.gdpr button').click(function() {
	        	  $('.gdpr').css('display', 'none');
	        	  localStorage.bannerClosed = 'true';
	        	});
	        	$('.banner-accept').click(function() {
	        	  $('.gdpr').css('display', 'none');
	        	  localStorage.bannerClosed = 'true';
	        	});
	        	if (navigator.userAgent.match(/Opera|OPR\//)) {
	        	  $('.gdpr').css('display', 'inherit');
	        	}
        	</script>
        <!-- End GDPR Complicance -->
