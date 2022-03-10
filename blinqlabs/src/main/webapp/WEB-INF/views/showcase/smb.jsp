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
			<div class="container">
				 <div class="section-heading2">
                    <span class="alt-font"></span>
                    <h3>For your Startup, from ours</h3>
                    <h6>Regardless of what stage of growth you're in, our services will enable your teams to release higher quality software, faster. How do we know? Because our services were the foundation upon which we started and transformed, and our software teams still rely on these same services and tools each and every day.</h6>
                </div>
			</div>
		</section>
		
		 <section class="bg-theme-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/timer.png" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Effortless Setup</h5>
                                </div>
                    			<div>
                    			       <p>From Analysis, Design, coding, meetings and rollouts, you are juggling a million things daily. Setting up your DevOps chain should not be one of them. Get your team up and running in seconds.</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/flexibility.png" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Flexibility</h5>
                                </div>
                    			<div>
                    			       <p>With customizable tools, services that work in your environment, deep integrations with the agile mindset, and a rich set of solutions, to build your company with the tooling that your entire team loves.</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                    <div class="col-lg-4 col-md-12 order-2 order-lg-1">
                    	<div class="row">
                    		<div class="col-lg-2 col-md-2 order-2 order-lg-1">
                    			<img src="${pageContext.request.contextPath}/resources/img/icons/scalable-icon.gif" class="img-fluid width-100" alt="">
                    		</div>
                    		<div class="col-lg-10 col-md-10 order-2 order-lg-1">
                    			<div>
                    				<h5>Scalability</h5>
                                </div>
                    			<div>
                    			       <p>Great startups start with scale in mind. From 10 to 10,000, Our services and tools power tens of thousands of startups and are battle-tested for every stage of your evolution.</p>
                    			</div>
                    		</div>
                    	</div>
                    
                    </div>
                    
                </div>
            </div>
        </section>
        
        <section>
        	<div>
        		<div class="section-heading2">
                    <span class="alt-font">IF YOU USE IT</span>
                    <h2>We probably support it</h2>
                    <p>Purpose-fit guidance and support for your needs</p>
                </div>
                
        		<img src="${pageContext.request.contextPath}/resources/img/icons/tools.png" class="img-fluid width-100" alt="">
        		
        	</div>
        </section>
                  
	</jsp:body>
</page:platform> 