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
		
		
		
		   <!-- start service section -->
        <section>
            <div class="container lg-container">

                <div class="row align-items-center margin-50px-bottom">
                    <div class="col-lg-6 col-md-12 sm-margin-30px-bottom text-center wow fadeInLeft" data-wow-delay=".1s">
                        <img src="${pageContext.request.contextPath}/resources/img/content/about5-01.png" alt="" />
                    </div>
                    <div class="col-lg-6 col-md-12 wow fadeInRight" data-wow-delay=".1s">

                        <div class="padding-50px-left sm-no-padding-left">
                            <h4 class="font-weight-700 margin-30px-bottom xs-margin-25px-bottom"><span class="font-weight-300">Create impact </span>across your organization</h4>
                            <p class="margin-35px-bottom xs-margin-30px-bottom small-title width-85">Our passion to work hard and deliver excellent results. It could solve your customers problems and develop innovation.</p>
                            <ul class="list-style7">
                                <li><i class="fas fa-check"></i>Explore business processes for increased transparency</li>
                                <li><i class="fas fa-check"></i>Implement a complete CI/CD/CT pipeline for faster time to market</li>
                                <li><i class="fas fa-check"></i>Minimize unplanned downtime and avoid high costs</li>
                                <li><i class="fas fa-check"></i>Deliver actionable insights to the right people in real-time</li>
                                <li><i class="fas fa-check"></i>Unleash the true power of DevOps with solutions that empower people to investigate, monitor, analyze and act.</li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="row align-items-center margin-50px-bottom">

                    <div class="col-lg-6 col-md-12 wow fadeInLeft order-2 order-lg-1" data-wow-delay=".1s">

                        <div class="padding-50px-right sm-no-padding-right">

                            <h4 class="font-weight-700 margin-30px-bottom xs-margin-25px-bottom"><span class="font-weight-300">Powerful products & services for </span> powerful applications</h4>

                            <p class="margin-35px-bottom xs-margin-30px-bottom small-title width-85">Seamlessly use Products and Services for your CI/CD/CT pipeline with better insights into each software build using the most popular DevOps tools.</p>
                            <ul class="list-style7">
                                <li><i class="fas fa-check"></i>We help implement the leading DevOps tools and solutions using industry best practices</li>
                                <li><i class="fas fa-check"></i>Use our in-house Test Automation Framework "CHEETAH" for Live, automated and continuous testing for web & mobile apps </li>
                                <li><i class="fas fa-check"></i>Setup on-prem solutions or use our Saas based cloud solution for peace of mind.</li>
                                <li><i class="fas fa-check"></i>We help define, coach and implement a complete Agile landscape integrated with DevOps principles.</li>
                            </ul>

                        </div>

                    </div>
                    <div class="col-lg-6 col-md-12 text-center sm-margin-30px-bottom order-1 order-lg-2 wow fadeInRight" data-wow-delay=".1s">
                        <img src="${pageContext.request.contextPath}/resources/img/content/about5-02.png" alt="" />
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col-lg-6 col-md-12 sm-margin-30px-bottom wow fadeInLeft text-center" data-wow-delay=".1s">
                        <img src="${pageContext.request.contextPath}/resources/img/content/about5-03.png" alt="" />
                    </div>
                    <div class="col-lg-6 col-md-12 wow fadeInRight" data-wow-delay=".1s">

                        <div class="padding-50px-left sm-no-padding-left">

                            <h4 class="font-weight-700 margin-30px-bottom xs-margin-25px-bottom"><span class="font-weight-300">Send targeted messages </span> to the right people </h4>

                            <p class="margin-35px-bottom xs-margin-30px-bottom small-title width-85"> Monitoring and alerting are key to sharing information about how systems and applications are running, and getting everyone to a common understanding. This common understanding is vital
for making improvements, whether within a single team and function or across multiple teams and functions.</p>

                            <ul class="list-style7">
                                <li><i class="fas fa-check"></i>Your entire organization needs visibility into application health.</li>
                                <li><i class="fas fa-check"></i>We help implement Dashboards that add a visual element to application monitoring.</li>
                                <li><i class="fas fa-check"></i>Breadcrumbs make application development a lot easier</li>
                                <li><i class="fas fa-check"></i>We help you understand the importance of monitoring real business metrics</li>

                            </ul>

                        </div>

                    </div>
                </div>
            </div>
        </section>
        <!-- end service section -->
		
        
        
		
  	<section>
  		<div>
  			<br/>
  		</div>
  	</section>

	</jsp:body>
</page:platform> 
