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
        <div class="animated-banner-area full-screen bg-theme-90">

            <!-- start banner image -->
            <div class="banner-content-img wow fadeInRight" data-wow-delay=".2s">
                <img src="${pageContext.request.contextPath}/resources/img/content/banner-content-img.png" alt="" />
            </div>
            <!-- end banner image -->

            <!-- start banner container -->
            <div class="container lg-container">
                <div class="row">

                    <!-- start left banner text -->
                    <div class="col-lg-7 col-md-12">
                        <div class="header-text sm-width-95 sm-center-col sm-text-center xs-width-100">
                            <h1>We help software teams <br/>
							  <span
							     class="txt-rotate text-yellow"
							     data-period="2000"
							     data-rotate='[ "explore", "analyze", "build", "test", "deploy", "monitor" ]'></span><strong> better software</strong>, <br/>faster
							</h1>
                            <p class="wow fadeInUp width-80 xs-width-95 sm-center-col" data-wow-delay=".2s">Increase agility, shorten releases, improve reliability and stay ahead of the competition.</p>
                            <div class="wow fadeInUp story-video" data-wow-delay=".4s">
                            	<a href="${contextPath}/register" class="butn style-two margin-15px-right vertical-align-middle">Get Started</a>
                                <%-- <a href="${contextPath}/resources/video/down-time.mp4" class="icon-play video vertical-align-middle"></a> --%>
                            </div>
                        </div>
                    </div>
                    <!-- end banner text -->
                    
                    <script>
                    var TxtRotate = function(el, toRotate, period) {
                    	  this.toRotate = toRotate;
                    	  this.el = el;
                    	  this.loopNum = 0;
                    	  this.period = parseInt(period, 10) || 2000;
                    	  this.txt = '';
                    	  this.tick();
                    	  this.isDeleting = false;
                    	};

                    	TxtRotate.prototype.tick = function() {
                    	  var i = this.loopNum % this.toRotate.length;
                    	  var fullTxt = this.toRotate[i];

                    	  if (this.isDeleting) {
                    	    this.txt = fullTxt.substring(0, this.txt.length - 1);
                    	  } else {
                    	    this.txt = fullTxt.substring(0, this.txt.length + 1);
                    	  }

                    	  this.el.innerHTML = '<span class="wrap">'+this.txt+'</span>';

                    	  var that = this;
                    	  var delta = 300 - Math.random() * 100;

                    	  if (this.isDeleting) { delta /= 2; }

                    	  if (!this.isDeleting && this.txt === fullTxt) {
                    	    delta = this.period;
                    	    this.isDeleting = true;
                    	  } else if (this.isDeleting && this.txt === '') {
                    	    this.isDeleting = false;
                    	    this.loopNum++;
                    	    delta = 500;
                    	  }

                    	  setTimeout(function() {
                    	    that.tick();
                    	  }, delta);
                    	};

                    	window.onload = function() {
                    	  var elements = document.getElementsByClassName('txt-rotate');
                    	  for (var i=0; i<elements.length; i++) {
                    	    var toRotate = elements[i].getAttribute('data-rotate');
                    	    var period = elements[i].getAttribute('data-period');
                    	    if (toRotate) {
                    	      new TxtRotate(elements[i], JSON.parse(toRotate), period);
                    	    }
                    	  }
                    	  // INJECT CSS
                    	  var css = document.createElement("style");
                    	  css.type = "text/css";
                    	  css.innerHTML = ".txt-rotate > .wrap { border-right: 0.08em solid #666 }";
                    	  document.body.appendChild(css);
                    	};
                    </script>

                </div>
            </div>
            <!-- end banner container -->

            <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img src="${pageContext.request.contextPath}/resources/img/content/header-bg.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->

        </div>
        <!-- end main banner area -->

        <!-- start features section -->
        <section>
            <div class="container lg-container">
             	 <div class="m-2 p-2 text-center  text-primary">
                	<h4 class="header-text ">Your DevOps and Cloud Experts</h4>
                	<p class="lead">Achieve DevOps Maturity and Excellence from Conception to Release.</p>
                </div>
              
                <div class="row">
                    <div class="col-lg-4 col-md-12 sm-margin-10px-bottom wow fadeInUp" data-wow-delay=".2s">
                        <div class="service-block5">
                            <div class="icon-box5 width-80px display-flex position-relative overflow-hidden bg-theme-90">
                                <i class="fas fa-cubes text-white"></i>
                            </div>
                            <h5 class="font-weight-700 margin-25px-bottom sm-margin-15px-bottom"><span class="font-weight-300">World class </span>Products</h5>
                            <p class="no-margin-bottom width-80 md-width-95 sm-width-75 xs-width-95 center-col">we help implement leading DevOps Products and tools to help teams unleash their full potential, from startups to enterprise</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12 sm-margin-10px-bottom wow fadeInUp" data-wow-delay=".4s">
                        <div class="service-block5">
                            <div class="icon-box5 width-80px display-flex position-relative overflow-hidden bg-theme-90">
                                <i class="fas fa-users text-white"></i>
                            </div>
                            <h5 class="font-weight-700 margin-25px-bottom sm-margin-15px-bottom"><span class="font-weight-300">Expert</span> Services</h5>
                            <p class="no-margin-bottom width-80 md-width-95 sm-width-75 xs-width-95 center-col">we have the capabilities and experience to deliver the answers you need to move forward. We can help you take decisive action and achieve sustainable results. </p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12 wow fadeInUp" data-wow-delay=".6s">
                        <div class="service-block5">
                            <div class="icon-box5 width-80px display-flex position-relative overflow-hidden bg-theme-90">
                                <i class="fas fa-chart-line text-white"></i>
                            </div>
                            <h5 class="font-weight-700 margin-25px-bottom sm-margin-15px-bottom"><span class="font-weight-300">Digital </span>Business Transformation</h5>
                            <p class="no-margin-bottom width-80 md-width-95 sm-width-75 xs-width-95 center-col">we help teams work faster and smarter, together; thus enabling companies bring their innovations to market earlier and transform successfully</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- end features section -->
        
         <div class="section-title text-center margin-60px-bottom md-margin-50px-bottom xs-margin-30px-bottom wow fadeInDown" data-wow-delay=".2s">
            <h4 class="font-weight-700 margin-10px-bottom">Transform your business</h4>
            <h3 class="font-weight-700 margin-10px-bottom">WE BRING <span class="text-green">SUCCESS </span>TO YOUR PROJECTS</h3>
            <p class="width-40 md-width-60 sm-width-80 xs-width-95 center-col">Everything we do enables a smarter, faster, safer, cleaner and brighter tomorrow</p>
        </div>
       
       <div class="bg-theme-light text-center p-4">
       		<h4 class="sm-margin-lr-auto sm-text-center xs-width-100">Products and Services</h4>
       		    <div class="row">
	        		<div class="col-md-3">
	        			 <div class="service-block3 m-2 p-2">
                            <img src="${pageContext.request.contextPath}/resources/img/blinqlabs/provision.png" width="25%" alt=""/>
                            <h5>DevOps as a Service</h5>
                            <p class="width-85 md-width-100 lead">Blinqlabs enables you to provision on-demand DevOps tools at the click of a button.</p>
                            <p><a href="${contextPath}/dot" class="text-primary">Learn more</a></p>
                        </div>
	        		</div>    
	            	<div class="col-md-2">
	        			 <div class="service-block3 m-2 p-2">
                             <img src="${pageContext.request.contextPath}/resources/img/blinqlabs/package.png" width="25%" alt=""/>
                            <h5>Products</h5>
                            <p class="width-85 md-width-100 lead">Discover our automation solution and hosting platform.</p>
                            <p><a href="${contextPath}/products/cheetah" class="text-primary">View</a>
                        </div>
	        		</div>
	        		<div class="col-md-2">
	        			 <div class="service-block3 m-2 p-2">
                             <img src="${pageContext.request.contextPath}/resources/img/blinqlabs/services.png" width="25%" alt=""/>
                            <h5>Services</h5>
                            <p class="width-85 md-width-100 lead">Blinqlabs provides DevOps and Cloud Expertise, Servces and more.</p>
                            <p><a href="${contextPath}/services" class="text-primary">Learn more</a></p>
                        </div>
	        		</div>
	        		<div class="col-md-2">
	        			 <div class="service-block3 m-2 p-2">
                             <img src="${pageContext.request.contextPath}/resources/img/blinqlabs/training.png" width="25%" alt=""/>
                            <h5>Trainings</h5>
                            <p class="width-85 md-width-100 lead">Join our experts to understand and learn various technologies and products.</p>
                            <p><a href="" class="text-primary">Browse</a></p>
                        </div>
	        		</div>
	        		<div class="col-md-3">
	        			 <div class="service-block3 m-2 p-2">
                             <img src="${pageContext.request.contextPath}/resources/img/blinqlabs/resources.png" width="20%" alt=""/>
                            <h5>Resources</h5>
                            <p class="width-85 md-width-100 lead">View our latest bogs, events, white papers and events.</p>
                            <p><a href="" class="text-primary">Explore</a></p>
                        </div>
	        		</div>
	            </div>
   			
       	</div>
       	
       		<!-- <div>
       			<div class="row">
	       			<div class="col-lg-6 col-sm-12 p-4" style="background-color:#546c91">
	       				<h4 class="text-white">Blogs</h4>	
	       			</div>
	       			
	       			<div class="col-lg-6 col-sm-12 p-4" style="background-color:#a1c3f7">
	       				<h4>Events</h4>
	       			</div>
	       		</div>
	       	</div> -->
       	
       	 <section>
            <div class="container lg-container">

                <div class="section-title text-center margin-60px-bottom md-margin-50px-bottom xs-margin-30px-bottom wow fadeInDown" data-wow-delay=".2s">
                    <h3 class="font-weight-700 margin-10px-bottom">You deserve better business</h3>
                    <p class="width-40 md-width-60 sm-width-80 xs-width-95 center-col">Deliver apps faster, improve developer productivity and delight your customers. Find out what BlinqLabs can do for your business.</p>
                </div>
                
           </div>
       </section>

      
        <!-- start clients section -->
      <%--   <section class="no-padding-top">
            <div class="section-clients no-padding-top">
                <div class="container wow fadeInUp" data-wow-delay=".2s">
                    <div class="owl-carousel owl-theme clients" id="clients">
                        <div class="item"><img alt="partner-image" src="${pageContext.request.contextPath}/resources/img/partners/client-01.png"></div>
                        <div class="item"><img alt="partner-image" src="${pageContext.request.contextPath}/resources/img/partners/client-02.png"></div>
                        <div class="item"><img alt="partner-image" src="${pageContext.request.contextPath}/resources/img/partners/client-03.png"></div>
                        <div class="item"><img alt="partner-image" src="${pageContext.request.contextPath}/resources/img/partners/client-04.png"></div>
                        <div class="item"><img alt="partner-image" src="${pageContext.request.contextPath}/resources/img/partners/client-05.png"></div>
                        <div class="item"><img alt="partner-image" src="${pageContext.request.contextPath}/resources/img/partners/client-06.png"></div>
                    </div>
                </div>
            </div>
        </section> --%>
        <!-- end clients section -->

        <!-- start extra section -->
        <section class="no-padding-tb sm-text-center wow fadeInUp position-relative z-index-9 bg-transparent" data-wow-delay=".2s">
            <div class="container lg-container">
                <div class="parallax cover-background padding-90px-all md-padding-70px-all sm-padding-50px-all xs-padding-50px-tb xs-padding-30px-lr wow fadeInUp theme-overlay-90 border-radius-4" data-overlay-dark="9" data-background="${contextPath}/resources/img/content/footer-bg-01.jpg">
                    <div class="position-relative z-index-1">
                        <h4 class="text-white no-margin-bottom xs-margin-20px-bottom display-inline-block vertical-align-middle">Ready to get Started?</h4>
                        <a href="${contextPath}/contact" class="butn style-two float-right sm-float-none margin-30px-left xs-no-margin">
                            <span>Contact Us</span>
                        </a>
                        
                        <c:if test="${empty sessionScope.firstname}">
                        	 <a href="${contextPath}/register" class="butn style-two float-right sm-float-none margin-30px-left xs-no-margin">
	                            <span>Create an Account</span>
	                        </a>
                        </c:if>
                        
                    </div>
                </div>
            </div>
        </section>
        <!-- end extra section -->

</jsp:body>
</page:platform> 