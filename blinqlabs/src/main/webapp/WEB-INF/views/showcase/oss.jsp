<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

                            <h1 class="line-height-55 md-line-height-50 xs-line-height-40 wow fadeInUp text-theme-color xs-font-size30" data-wow-delay=".1s">FOSS</h1>
                            <p class="font-size16 line-height-28 xs-font-size14 xs-line-height-26 margin-30px-bottom sm-margin-20px-bottom width-80 xs-width-90 wow fadeInUp" data-wow-delay=".2s">We have Open-Sourced our testing framework to support other open-source softwares</p>
                            <div class="wow fadeInUp story-video" data-wow-delay=".4s"><a href="${contextPath}/products/cheetah" class="butn margin-10px-right vertical-align-middle">Get Started</a>
                            </div>
                        </div>
                    </div>
                    <!-- end banner text -->

                    <!-- start right image banner -->
                    <div class="col-lg-6 col-md-12 sm-text-center">
                        <div class="banner-img">
                            <img src="${pageContext.request.contextPath}/resources/img/illustration/Illustration 17.png" class="img-fluid float-right width-100" alt="">
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
	
	
	 <section class="service-section">
            <div class="container">
                <div class="section-heading2">
                    <h2>Test with Cheetah</h2>
                    <p>Cheetah framework provides full-service live and automated testing capabilities to help keep software bug-free.</p>
                </div>
                
                <div class="row">
                    
                   <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/pulse.png" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Live and Automated</h5>
                                </div>
                    			<div>
                    			       <p>Run live & automated tests in parallel, speeding test results and contributor feedback..</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/flash.png" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Get Started Quickly</h5>
                                </div>
                    			<div>
                    			       <p>Use our in-product training modules and guidelines to understand the basic configurations to get up and running fast.</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/data.png" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Dynamic Data and results</h5>
                                </div>
                    			<div>
                    			       <p>Integrate data from databases, web services or variuos flat files and obtain results as needed.</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
               </div>
               
               <div class="row">
                    
                   <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/error.png" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Detect issues Fast</h5>
                                </div>
                    			<div>
                    			       <p>Video recordings, screenshots, and trace results help identify test issues even when run automatically.</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/device.jpg" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Web and Mobile</h5>
                                </div>
                    			<div>
                    			       <p>Run tests across multiple devices and platforms. You can also test web services and databases.</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/link.png" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Pipeline Integration</h5>
                                </div>
                    			<div>
                    			       <p>Cheetah integrates well with existing DevOps pipeline to help identify issues faster and speed up product delivery, all the way increasing the quality of your product.</p>
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
	</jsp:body>
</page:platform> 