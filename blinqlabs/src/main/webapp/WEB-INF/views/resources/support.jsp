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
		
		<!-- start main banner area -->
        <div class="banner-creative cover-background">

            <div class="container">

                <div class="row">

                    <!-- start left banner text -->
                    <div class="col-lg-6 col-md-12">
                        <div class="header-text sm-width-75 xs-width-100 sm-padding-30px-bottom">

                            <h1 class="line-height-55 md-line-height-50 xs-line-height-40 wow fadeInUp text-theme-color xs-font-size30" data-wow-delay=".1s">Get help with Blinqlabs services</h1>
                            <div class="wow fadeInUp story-video" data-wow-delay=".4s">
	                            
	                             <form name="searchForm" role="form" action="tech-support-search-query" method="POST">
		                             <div class="row">
		                                <div class="col-md-8">
		                                    <div class="form-wrap">
		                                        <input class="form-input" type="text" name="searchqqery" placeholder="Search..." data-constraints="@Required">
		                                    </div>
		                                </div>
		                                <div class="col-md-4 text-center">
		                                    <button type="submit" class="butn style-one fill">Search</button>
		                                </div>
		                             </div>
	                             </form>
	                                
                         
                            </div>
                        </div>
                    </div>
                    <!-- end banner text -->

                    <!-- start right image banner -->
                    <div class="col-lg-6 col-md-12 sm-text-center">
                        <div class="banner-img">
                            <img src="${pageContext.request.contextPath}/resources/img/content/about.png" class="img-fluid float-right width-100" alt="">
                        </div>
                    </div>
                    <!-- end right image banner -->

                </div>

            </div>

                    <!-- start shape area -->
        <div class="header-shape xs-display-none">
            <img src="${pageContext.request.contextPath}/resources/img/banner/header-bg2.png" class="img-fluid width-100" alt="">
        </div>
        <!-- end shape area -->

        </div>
        <!-- end main banner area -->
		
		
 	<section>
  		<div class="container">
  		
  		 	<div class="section-heading2">
                    <span class="alt-font">Resources</span>
            </div>
                
  			 <div class="row">
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/tutorials.jpg" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Documentation</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/health.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">System Health</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/billing.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Billing / Licensing</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                
                 </div>
  		
  		
  		<br/>
  		
  			 <div class="row">
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/feature.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Feature Requests or Defect reporting</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/blog.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Blogs</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/query.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">General Inquiries</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
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

</jsp:body>
</page:platform> 