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
        <div class="main-banner-area">

            <!-- start right image banner -->
            <div class="right-bg">
                <img src="${pageContext.request.contextPath}/resources/img/content/cheetah.png" class="img-fluid float-right width-100" alt="">
            </div>
            <!-- end right image banner -->

            <!-- start shape area -->
            <div class="header-shape-bg">
                <img src="${pageContext.request.contextPath}/resources/img/banner/header-bg1.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->

            <!-- start banner text -->
            <div class="header-text">
                <div class="container">
                    <div class="row p-4 m-4">
                        <div class="col-lg-5 col-md-12 col-sm-12">
                            <h1 class=" wow fadeInUp" data-wow-delay=".1s">Cheetah helps you achieve faster and more efficient results.</h1>
                            <p class=" wow fadeInUp" data-wow-delay=".2s">Cheetah is a test automation framework that has been developed to help automate testing efforts across multiple technologies including Web, Mobile, WebServices, Database, Mainframe, etc...</p>
                          
                            
                            <div class="text-center center-col">
                            <a href="javascript:void(0)" class="butn style-one  primary"><span>Get Started</span></a>
                            </div>
                            
                            <br/><br/>
                              <div class="email-box">
                              	<form action="${contextPath}/subscribe" method="POST" modelAttribute="SubscriptionBean" id = "subscriptionForm" name="SubscriptionForm">
	                                <div class="input">
	                                    <input type="email" placeholder="Enter your email" name="TX_EMAIL" id="TX_EMAIL" for="TX_EMAIL">
	                                   <!--  <a href="javascript:void(0)" class="butn"><span>Subscribe</span></a> -->
	                                   <button type="submit" class="butn">Subscribe</button>
	                                </div>
	                                <span>Enter your e-mail to get the latest news.</span>
                                </form>
                            </div>
                            <br/> <br/>
                            <div>
                            	<p class="font-weight-700 text-primary wow fadeInUp">Expand your testing capacity, get faster feedback, and improve quality through test automation with Cheetah.</p>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
            <!-- end banner text -->

            <!-- start play button -->
             <div class="play-button-wrapper story-video">
                <a href="${contextPath}/video/down-time.mp4" class="btn-play video">
                    <i class="fa fa-play"></i>
                </a>
            </div> 
            <!-- end play button -->

        </div>
        <!-- end main banner area -->
	
	<section class="mt-5">
		<div class="container">
			<div class="section-heading2">
                   <h2>The most comprehensive <br />Test Automation framework</h2>
                   <p>Explore the advantages of automated testing with Cheetah.</p>
               </div>
               
                <div class="row">
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/paths.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Execute more tests in less time</h5>
                            <p>Maximize resources by testing in parallel on multiple servers, during off-peak hours.</p>
                          <!--   <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/icon-continuous-integration.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Support continuous development practices</h5>
                            <p>Trigger smoke and sanity tests from your CI server; execute full or partial regression suites.</p>
                            <!-- <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/scenarios.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Cover more scenarios with data-driven testing</h5>
                            <p>Efficiently test happy path scenarios, boundaries and edge cases.</p>
                           <!--  <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/devices.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Accelerate cross-browser and cross-device testing</h5>
                           <p>Ensure that your application performs consistently on every platform.</p>
                           <!--  <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
                        </div>
                    </div>
                    
             	 </div>
             	 
             	 <br/>
             	 
             	  <div class="row">
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/en.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Natural Language</h5>
                            <p>Define requireents and test scenarios is plain English Language</p>
                          <!--   <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/api.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Powerful APIs and functional libraries</h5>
                            <p>Cheetah does all the heavy work, you just need to invoke the required library.</p>
                            <!-- <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/report.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Enhanced reporting</h5>
                            <p>Obtain results in multiple configurable formats including screenshots, video recording, database and pdf</p>
                           <!--  <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/oss.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Completely open source</h5>
                           <p>Cheetah is a completely open source framework. No fees. No restrictions. Feel free to contribute.</p>
                           <!--  <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a> -->
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