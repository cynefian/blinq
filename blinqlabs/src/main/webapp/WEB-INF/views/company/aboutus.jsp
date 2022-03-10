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
        <div data-scroll-index="0">

            <div class="triangle-shape"></div>
            <div class="square-shape"></div>
            <div class="square-shape-two"></div>
            <div class="round-shape-one"></div>
            <div class="shape-five"></div>

			<div class="container">
				<div class="row">
					<div class="col-lg-5 col-md-12  sm-margin-70px-bottom xs-margin-10px-bottom">
						 <div class="header-text sm-width-90 xs-width-95 m-4 p-4">

                            <div class="banner-wrapper">
                                <!-- <div class="slogan wow fadeInUp" data-wow-delay=".1s"><span>Awesome</span> Landing Page</div> -->
                                <h1 class="main-title text-color-md-blue wow fadeInUp" data-wow-delay=".3s">Who we are</h1>
                                <p class="sub-title width-80 xs-width-90 wow fadeInUp text-color-light-blue" data-wow-delay=".5s">
                               		<span class="lead text-primary"> At Blinqlabs, we come to work every day to bring innovation at speed. </span><br/>
								</p>

                                <!-- <div class="wow fadeInUp story-video" data-wow-delay=".7s">
                                    <a href="javascript:void(0)" class="butn style-one fill margin-10px-right vertical-align-middle">Get Started</a>
                                    <a href="video/down-time.mp4" class="btn-play video vertical-align-middle"><i class="fa fa-play"></i></a>
                                </div> -->
                            </div>

                        </div>
					</div>
					<div class="col-lg-7 col-md-12 ">
						<!-- start right image banner -->
			            <div class="banner-wrapper-position">
						 <img src="${pageContext.request.contextPath}/resources/img/illustration/whoweare.png" alt="">
			            </div>
			            <!-- end right image banner -->		
					</div>	
				
				</div>
			</div>
    
        </div>
        <!-- end main banner area -->
     
        
			<div class="container">
				<p class="lead m-2 p-2" data-wow-delay=".5s">	
				At Blinqlabs, we combine efficiency and technical expertise to provide innovative results in the software development lifecycle.
                </p>
			</div>
								
		
		
		<section class="bg-theme-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-12 order-2 order-lg-1">
                   		<div class="section-heading2">
		                    <h2>Our Mission</h2>
		                    <p>Blinqlabs is a catalyst for innovation.</p>
		                </div>
                    </div>
                    
                    <div class="col-lg-6 col-md-12 order-2 order-lg-1">
                   		<div class="section-heading2">
		                    <h2>Our Essence</h2>
		                    <p>At our core, Blinqlabs operates on Imagination, Individuality, Inclusivity, & Impact.</p>
		                </div>
                    </div>
            	</div>

                 <div class="row">
                    <div class="col-lg-6 col-md-12 order-2 order-lg-1">
                   		<div class="section-heading2">
		                    <h2>Our Promise</h2>
		                    <p>We deliver products and services to achieve innovation at breakthrough speeds without compromize to quality or security.</p>
		                </div>
                    </div>
                    
                    <div class="col-lg-6 col-md-12 order-2 order-lg-1">
                   		<div class="section-heading2">
		                    <h2>Our Vibe</h2>
		                    <p>At Blinkqlabs, we make magic. We dream it, and then do it - reinventing what's possible.</p>
		                </div>
                    </div>
                </div>
            </div>
            
      </section>
        <div class="p-2" style="background-color:#1a79bf">
			<div class="container">
				<div class="row">
					<div class="col-md-6 text-white">
						<div>
							<h1 class="text-white">Automation</h1>
						</div>
						<div>
							<span class="lead text-white m-2">Our vision is to automate everything.</span>
							 <p class="wow fadeInUp width-80 xs-width-95 sm-center-col" data-wow-delay=".2s">Blinqlabs aims to provide business solutions to clients by custom tailoring automation platforms and solutions to fit our client needs. 
							Through the use of DevOps automation, we strive on creating innovative solutions to solve business processes. We help our clients modernize the software delivery process by providing solutions, provisioned instances, 
							create custom workflows, building business processes, conduct training, offer hosting, perform Cloud and DevOps services, and provide overall support services.</p>
						</div>	
					</div>
					<div class="col-md-6">
						<div class="m-4">
							 <img src="${pageContext.request.contextPath}/resources/img/blinqlabs/code.jpg" class="img-fluid width-100" alt="">
						</div>
					</div>
				</div>
				
			
			</div>
		
		</div>
        
        <section>
            <div class="container">
           		<div class="section-heading2">
                    <span class="alt-font">Our Values</span>
                </div>
        
		        <div>
		        
		        	<div class="card-columns">
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/innovation.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Innovation</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">It is our nature to Innovate.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/culture.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Culture</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We know it takes people with different ideas, strengths, interests, and cultural backgrounds to make our company succeed. We encourage healthy debate and differences of opinion..</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/integrity.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Integrity</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We are consistently open, honest, ethical and genuine.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/clearn.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Continuous Learning</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We are continuously moving forward, innovating, and improving..</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/speed.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Speed</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We believe in the power of aglity without any compromises.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/idea.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Ideas over Hierarchy</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We value everyone's idea to achieve the most innovation.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/collaborate.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Collaboration</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We believe in the genius power of team work to achieve great results.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/passion.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Passion</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We are commited in the heart and the mind.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/fun.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Fun</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">An environment where one can think big, have fun, be a little weird and do good..</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/respect.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Respect</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">We strive to show a deep respect for human beings inside and outside our company and for the communities in which they live.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/accountability.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Accountability</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">If it is to be, it's up to me.</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/individual.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Individuality</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">Respect for the Individual. We celebrate inclusion of different perspectives and experiences. Together, we grow and learn as we treat each other professionally while preserving our individuality (no strict dress codes here).</p>
		                        </div>
		        			</div>
		        		</div>
		        		<div class="card">
		        			<div class="card-body">
		        				<div class="services-block bg-light-gray padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp" data-wow-delay=".2s">
		                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
		                                <img src="${pageContext.request.contextPath}/resources/img/icons/be-the-change.png" class="img-fluid" alt="">
		                                <div class="box-circle-large"></div>
		                                <div class="box-circle-small"></div>
		                            </div>
		                            <h3 class="margin-10px-bottom font-size22 md-font-size20 xs-font-size18">Be the change you seek</h3>
		                            <p class="font-size16 line-height-28 sm-font-size14 sm-line-height-24">When we are active participants in the changes we wish to see in the world, we can have a more effective impact not only on our lives but on the lives of others as well.</p>
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
