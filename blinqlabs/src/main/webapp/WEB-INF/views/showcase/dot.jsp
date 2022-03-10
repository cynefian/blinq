<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
	
	  <!-- start main banner area -->
        <div class="animated-banner-area header-secondary bg-theme-90">
	  <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img
					src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png"
					class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
	
	
	<section>
		<div class="container">
			<div class="section-heading2">
                   <h2>The most comprehensive <br />Tool chain on the cloud</h2>
                   <p>or get our help to set it up on-prem.</p>
               </div>
               
                <div class="row">
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                 <img src="${pageContext.request.contextPath}/resources/img/icons/icon-continuous-integration.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Integration</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/continuous-deployment.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Deployment</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/continuous-testing.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Testing</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/continuous-delivery.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Delivery</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
             	 </div>
             	 <br/>
             	 <div class="row">
             	 
             	 <div class="col-lg-1 col-md-12 sm-margin-30px-bottom">
             	 </div>
             	 
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/explore.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Exploration</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/continuous-monitoring.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Monitoring</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/continuous-security.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Security</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-1 col-md-12 sm-margin-30px-bottom">
             	 </div>
                    
               </div>
               
               <br/><br/>
               <div class="row">
                <div class="col-lg-6 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/ICON-learning.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Learning</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                     <div class="col-lg-6 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <img src="${pageContext.request.contextPath}/resources/img/icons/continuousimprovement.png" class="img-fluid" alt=""/>
                            </div>
                            <h5 class="text-color-md-blue">Continuous Improvement</h5>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
               </div>
      	</div>
	</section>
	
	<section class="bg-theme-light">
  		<div>
  			<div class="container">
	                <div class="row">
                    <!-- start left banner text -->
                    <div
							class="col-lg-6 col-md-12 sm-margin-40px-bottom xs-margin-30px-bottom">
                        <div
								class="header-text sm-width-75 xs-width-100">
                            <h1
									class="line-height-55 md-line-height-50 xs-line-height-40 xs-font-size28 wow fadeInUp text-primary"
									data-wow-delay=".1s">Grow your business with powerful and fully customizable tools and services</h1>
                            <p
									class="text-primary font-size16 line-height-28 xs-font-size14 xs-line-height-26 margin-30px-bottom sm-margin-20px-bottom width-80 xs-width-90 wow fadeInUp"
									data-wow-delay=".2s">With our passion to work hard and deliver excellent results, we solve the needs of our customers and develop innovation.</p>
                            
                            			<a href="javascript:void(0)"
									class="butn style-two md-lg margin-10px-right vertical-align-middle">
                            			<button class="btn btn-primary">Get Started</button>
								</a>
                        </div>
                    </div>
                    <!-- end banner text -->

                    <!-- start right image banner -->
                    <div class="col-lg-6 col-md-12 sm-text-center">
                        <div class="creative-banner-img">
                            <img
									src="${pageContext.request.contextPath}/resources/img/content/content-03.png"
									class="img-fluid float-right width-100 sm-width-auto" alt="">
                        </div>
                    </div>
                    <!-- end right image banner -->
                </div>
            </div>
  		</div>
  	</section>
  	
  	
  <section class="lg" data-scroll-index="1">
            <div class="container">
                
                <div class="row">
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <i class="fas fa-dice-d6"></i>
                                <div class="box-circle bg-blue"></div>
                            </div>
                            <h5 class="text-color-md-blue">Comprehensive Platform</h5>
                            <p class="margin-30px-bottom sm-margin-25px-bottom sm-width-90 xs-width-100 text-color-light-blue">We offer the most up-to-date (customizable) OS/tool combinations for your processes and pipelines </p>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".4s">
                            <div class="title-box">
                                <i class="fab fa-stumbleupon"></i>
                                <div class="box-circle bg-yellow"></div>
                            </div>
                            <h5 class="text-color-md-blue">Scalable Pipeline</h5>
                            <p class="margin-30px-bottom sm-margin-25px-bottom sm-width-90 xs-width-100 text-color-light-blue">Our scalable, always-on infrastructure ensures that teams of all sizes can check-in multiple pull requests throughout the day without queuing or bottlenecks, ensuring rapid feedback.</p>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-12">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".6s">
                            <div class="title-box">
                                <!-- <i class="ti-paint-roller text-orange"></i> -->
                                <i class="fas fa-chart-line"></i>
                                <div class="box-circle bg-green"></div>
                            </div>
                            <h5 class="text-color-md-blue">Increased efficiency</h5>
                            <p class="margin-30px-bottom sm-margin-25px-bottom sm-width-90 xs-width-100 text-color-light-blue">With rapid development and faster error detection, with comprehensive analytics and live environments, Blinqlabs enhances developer productivity.</p>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                </div>

         <br/>
                
                <div class="row">
                
                	<div class="col-lg-2 col-md-12 sm-margin-30px-bottom">
                        
                    </div>
                    
                    
                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".2s">
                            <div class="title-box">
                                <i class="fas fa-shield-alt"></i>
                                <div class="box-circle bg-orange"></div>
                            </div>
                            <h5 class="text-color-md-blue">Enterprise Security and Support</h5>
                            <p class="margin-30px-bottom sm-margin-25px-bottom sm-width-90 xs-width-100 text-color-light-blue">Our secure tunneling protocol and industry level encryption ensures the highest degree of security.</p>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-12 sm-margin-30px-bottom">
                        <div class="features-block4 wow fadeInUp" data-wow-delay=".4s">
                            <div class="title-box">
                                <i class="fas fa-boxes"></i>
                                <div class="box-circle bg-green"></div>
                            </div>
                            <h5 class="text-color-md-blue">One stop solution</h5>
                            <p class="margin-30px-bottom sm-margin-25px-bottom sm-width-90 xs-width-100 text-color-light-blue">Blinqlabs provides a one-stop-solution for all your DevOps and Agile requirements.</p>
                            <a href="javascript:void(0)"><span class="icon text-color-light-blue opacity5">&#10230;</span></a>
                        </div>
                    </div>
                    
                    <div class="col-lg-2 col-md-12 sm-margin-30px-bottom">
                        
                    </div>
               
            </div>
        </section>
        
          <section>
		<div class="container">
			<div class="section-heading2">
                   <h2>Ship better software, faster</h2>
                   <div>
                   		<img src="${pageContext.request.contextPath}/resources/img/content/vector-devops.png"
					class="img-fluid width-100" alt="">
                   </div>
               </div>
      	</div>
	</section>
  	

	<section>
  		<div>
  			<br />
  		</div>
  	</section>
	
	</jsp:body>
</page:platform>
