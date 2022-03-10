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
		
		
		<section>
		<!-- start main banner area -->
        <div class="theme-banner full-screen" data-scroll-index="0">

            <div class="triangle-shape"></div>
            <div class="square-shape"></div>
            <div class="square-shape-two"></div>
            <div class="round-shape-one"></div>
            <div class="shape-five"></div>

            <!-- start right image banner -->
            <div class="banner-wrapper-position">

                <img src="${pageContext.request.contextPath}/resources/img/banner/banner-01.png" alt="" class="screen-one">
                <img src="${pageContext.request.contextPath}/resources/img/banner/shape-2.png" alt="" class="shape-three">

            </div>
            <!-- end right image banner -->

            <div class="container">

                <div class="row">

                    <!-- start left banner text -->
                    <div class="col-lg-7 col-md-12 sm-margin-70px-bottom xs-margin-10px-bottom">
                        <div class="header-text sm-width-90 xs-width-95">

                            <div class="banner-wrapper">
                                <div class="slogan wow fadeInUp" data-wow-delay=".1s"><span>Training</span></div>
                                <h1 class="main-title text-color-md-blue wow fadeInUp" data-wow-delay=".3s">Want to level up?</h1>
                                <p class="sub-title width-80 xs-width-90 wow fadeInUp text-color-light-blue" data-wow-delay=".5s">Blinqlabs offers the resources you need to develop new skills, improve your team's work, and advance your professional career.</p>

                                <div class="wow fadeInUp story-video" data-wow-delay=".7s">
                                    <a href="javascript:void(0)" class="butn style-one fill margin-10px-right vertical-align-middle">Get Started</a>
                                    
                            </div>

                        </div>
                    </div>
                    <!-- end banner text -->

				
                
                </div>

            </div>
        </div>
        <!-- end main banner area -->
        </div>
        
        
		
		<div class="section-heading2">
            <h2>Designed with your goals in mind</h2>
            <p>Purpose-fit guidance and support for your needs</p>
        </div>      
		
		<div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/tutorials.jpg" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Tutorials</h5>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/hands-on.jpg" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Hands-on Training</h5>
                                </div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/certification.jpg" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Certifications</h5>
                                </div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                </div>
            </div>
            
            
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-12 order-2 order-lg-1">
                     	<div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/agile-icon.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Agile</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    
                    </div>
                    
                    <div class="col-lg-3 col-md-12 order-2 order-lg-1">
                     	<div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/safe.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">SAFe</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    
                    </div>
                    
                    <div class="col-lg-3 col-md-12 order-2 order-lg-1">
                     	<div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/devops.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">DevOps</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    
                    </div>
                    
                    <div class="col-lg-3 col-md-12 order-2 order-lg-1">
                     	<div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/testing.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Testing</h5>
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