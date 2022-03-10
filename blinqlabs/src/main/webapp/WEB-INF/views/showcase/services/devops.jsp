<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<div class="">
                        	<div class="row">
                        		<div class="col-lg-12 col-sm-12">
                        			<div class="hero">
		                            <div class="image-with-content" style="--image-url(${pageContext.request.contextPath}/resources/img/banner/devops-folks.jpg)">
		                            	<img id="section-img" src="${pageContext.request.contextPath}/resources/img/banner/devops-folks.jpg">
		                            	<div class="img-text-block image-overlay">
			                                <div class="row m-4 p-4 hero-message">
			                                    <div class="col-lg-5 offset-lg-1 m-4">
			                                        <h1 class="text-white">DevOps is our culture</h1>
			                                        <p class="h5">We implement DevOps best practices and tools for custom tailored requirements and streamline a process to make it seamless and efficient.</p>
			
			                                        <ul class="list-style m-4">
			                                            <li class="lead m-4 p-2">  We help you develop the entire DevOps Pipeline</li>
			                                            <li class="lead m-4 p-2">  Implement the various stages including Continuous Integration, Deployment, Testing and Monitoring.</li>
			                                            <li class="lead m-4 p-2">  Provide training for Developers, Operations and Leadership on DevOps best practices and approach.</li>
			                                            <li class="lead m-4 p-2">  Assist in the migration of existing practices to a DevOps rich environment.</li>
			                                            <li class="lead m-4 p-2">  Build the DevOps process along with existing or new Agile environments.</li>
			                                        </ul>
			
			                                    </div>
			                                    <div class="col-lg-5 sm-margin-20px-bottom sm-text-center">
			                                        <div>
				                                        <div class="m-4 p-2">
				                                        	<div class="row">
				                                        		<div class="col-md-3">
				                                        			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/saas.png" width="40%">
				                                        		</div>
				                                        		<div class="col-md-9">
				                                        			<h6 class="text-white">SaaS Hosting</h6>
				                                        			<span class="lead">Managed hosting and provide global delivery.</span>
				                                        		</div>
				                                       		</div>
			                                       		</div>
			                                        	
			                                        	<div class="m-4 p-2" >
				                                        	<div class="row">
				                                        		<div class="col-md-3">
				                                        			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/enterprise.png" width="40%">
				                                        		</div>
				                                        		<div class="col-md-9">
				                                        			<h6 class="text-white">Enterprise Solutions</h6>
																	<span class="lead">We implement the SAFe Framework and offer customized Enterprise Solutions.</span>
				                                        		</div>
				                                       		</div>
			                                       		</div>
			                                       		
			                                       		<div class="m-4 p-2">
				                                        	<div class="row">
				                                        		<div class="col-md-3">
				                                        			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/consult.png" width="40%">
				                                        		</div>
				                                        		<div class="col-md-9">
				                                        			<h6 class="text-white">Customized Consulting</h6>
				                                        			<span class="lead">We specialize in niche partner consulting using the following tool sets: Git, Atlassian, Sonatype, SonarQube, Docker, Elastic and many more.</span>
				                                        		</div>
				                                       		</div>
			                                       		</div>
			                                        </div>
			                                    </div>
			                                </div>
		                                </div>
		                            </div>
		                            </div>
	                            </div>
                        	</div>
                            <div class="m-4 p-2">
	                            <div class="container">
	                            	<div class="text-center">
	                            		<h2>ACCELERATE</h2>
	                            		<p><span class="display-4">To happen or to make something happen faster or earlier than expected.</span></p>
	                            		<p><span class="lead">Catalyzing Growth with Cutting-Edge Solutions that Accelerate Innovation, Revenue and Sustainability</span></p>
	                            	</div>
	                            
	                            </div>
                            </div>
                            
                            <div>
                            	<div class="row">
                            		<div class="col-lg-5 col-sm-12  vertical-align-middle flex-center">
                            			<div class="text-center m-4 p-2">
                            				<h4 class="text-primary">Managed Services</h4>
                            				<span class="text-secondary lead">Our managed DevOps services enable your business to achieve the 'always-on' state by providing access to the IT services that address issues related to cost, quality of service and data security.</span>
                            				
                            				<div class="m-4">
                            					<a href="${contectPath}/dot"><button type="button" class="butn style-one">Learn More</button></a>
                            				</div>
                            			</div>
                            		</div>
                            		<div class="col-lg-7 col-sm-12">
                            			<img src="${pageContext.request.contextPath}/resources/img/banner/hosting.jpg" class="img-block" style="width:100%;">
                            		</div>
	                            </div>
                            </div>
                            
                              <div class="m-4 p-2">
	                            <div class="container">
	                            	<div class="text-center">
	                            		<h2>OPTIMIZE</h2>
	                            		<p><span class="display-4">To make the best or most effective use of (a situation or resource)</span></p>
	                            		<p><span class="lead">Engineering result-oriented business solutions that streamline your digital transformation journey</span></p>
	                            	</div>
	                            
	                            </div>
                            </div>
                            
                             <div>
                            	<div class="row">
                            	<div class="col-lg-7 col-sm-12">
                            			<img src="${pageContext.request.contextPath}/resources/img/banner/devops-banner.jpg" class="img-block" style="width:100%;">
                            		</div>
                            		<div class="col-lg-5 col-sm-12  vertical-align-middle flex-center">
                            			<div class="text-center m-4 p-2">
                            				<h4 class="text-primary">DevOps</h4>
                            				<span class="text-secondary lead">For successful DevOps, we dwell on agility and collaboration throughout the IT and business domains, resulting in continuous delivery of innovative software products and apps that deliver great value to all the stakeholders.</span>
                            				
                            				<div class="m-4">
                            					<a href="${contectPath}/contact"><button type="button" class="butn style-one">Contact Us</button></a>
                            				</div>
                            			</div>
                            		</div>
                            		
	                            </div>
                            </div>
                            
                            <div class="m-4 p-2">
	                            <div class="container">
	                            	<div class="text-center">
	                            		<h2>EXECUTE</h2>
	                            		<p><span class="display-4">To carry out or put into effect (a plan, order, or course of action).</span></p>
	                            		<p><span class="lead">Blinqlabs tightly intergates DevOps and Agile practices with all activites</span></p>
	                            	</div>
	                            
	                            </div>
                            </div>
                            
                            <div>
                            	<div class="row">
                            	
                            		<div class="col-lg-5 col-sm-12  vertical-align-middle flex-center">
                            			<div class="text-center m-4 p-2">
                            				<h4 class="text-primary">Agile</h4>
                            				<span class="text-secondary lead">Blinqlabs combines its business knowledge with agile practices and proficiencies to help you build disruptive products. Our services span from pre to post product lifecycle phases for better business agility.</span>
                            				
                            				<div class="m-4">
                            					<a href="${contectPath}/contact"><button type="button" class="butn style-one">Contact Us</button></a>
                            				</div>
                            			</div>
                            		</div>
                            		
                            		<div class="col-lg-7 col-sm-12">
                            			<img src="${pageContext.request.contextPath}/resources/img/banner/agile.jpg" class="img-block" style="width:100%;">
                            		</div>
                            		
	                            </div>
                            </div>
                            
                            <div class="m-4 p-2">
	                            <div class="container">
	                            	<div class="text-center">
	                            		<h2>MODERNIZE</h2>
	                            		<p><span class="display-4">To make a system, methods, etc. more modern and more suitable for use at the present time</span></p>
	                            		<p><span class="lead">Streamlining mission critical business processes to drive efficiency, productivity and performance</span></p>
	                            	</div>
	                            
	                            </div>
                            </div>
                            
                            <div>
                            	<div class="row">
                            	<div class="col-lg-7 col-sm-12">
                            			<img src="${pageContext.request.contextPath}/resources/img/banner/testing.jpg" class="img-block" style="width:100%;">
                            		</div>
                            		<div class="col-lg-5 col-sm-12  vertical-align-middle flex-center">
                            			<div class="text-center m-4 p-2">
                            				<h4 class="text-primary">Cheetah</h4>
                            				<span class="text-secondary lead">Experience effective test automation of web & mobile apps with an easy & intuitive software testing framework, Cheetah. It is pillared on a strong architecture combined with an intuitive Automation Engine that promises to take your test automation to the next level.</span>
                            				
                            				<div class="m-4">
                            					<a href="${contectPath}/cheetah"><button type="button" class="butn style-one">Learn More</button></a>
                            				</div>
                            			</div>
                            		</div>
                            		
	                            </div>
                            </div>
                           </div>