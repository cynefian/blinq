<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<div class="m-4 p-2 text-center">
   			<h4>Workflow Configurations & Automation</h4>
   		</div>
   	   
   	<div class="row">
   		
   		<div class="col-lg-5 col-md-5 col-sm-12 text-center flex-space-between">
   			<div class="text-center">
   				<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/workflow.png" style="width:50%">
   			</div>
   			
   			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/illustration/undraw_ideation_2a64.svg"> 
   		</div>
   		<div class="col-lg-5 col-md-5 col-sm-12">
   		     		<div class="row m-4 p-2">
               			<div class="col-md-3">
          					<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/expert-analysis.svg" style="width:50%">
                		</div>
                    	<div class="col-md-9">
                    		<h6>Expert Analysis</h6>
                     		<span class="lead">Not sure where to start?<br/>
								Have our experts perform an assessment for you.</span>
                   		</div>
             		</div>
           			<div class="row m-4 p-2">
	              		<div class="col-md-3">
	              			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/install-config.svg" style="width:50%">
	               		</div>
	               		<div class="col-md-9">
	               			<h6>Installation and Configuration</h6>
							<span class="lead">Want to set up your instance correctly the first time?<br/>
								Our experts will get it done the right way.</span>
                    	</div>
                   	 </div>
                    <div class="row m-4 p-2">
	                    <div class="col-md-3">
	                    	<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/data-migration.svg" style="width:50%">
	               		</div>
	               		<div class="col-md-9">
	               			<h6>Data Migration</h6>
	               			<span class="lead">Considering switching to a new technology, but worried about preserving legacy data?<br/>
	               			We has the technical expertise.</span>
	               		</div>
              		</div>
           			<div class="row m-4 p-2">
	               		<div class="col-md-3">
	               			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/customizations.svg" style="width:50%">
	                    </div>
	                    <div class="col-md-9">
	                    	<h6>Workflow Customizations</h6>
	                    	<span class="lead">Default workflows not adequate for your team?<br/>
								Our experts will work with you to develop the workflow for your process.</span>
	                    </div>
                    </div>
                   	<div class="row m-4 p-2">
                    	<div class="col-md-3">
	                         			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/script.png" style="width:50%">
	               		</div>
	               		<div class="col-md-9">
	               			<h6>Custom Scripting</h6>
	               			<span class="lead">Worried about custom coding or scripting requirements?<br/>
	               			We have you covered.</span>
	               		</div>
              		</div>
             		
            		<div class="row m-4 p-2">
	               		<div class="col-md-3">
	               			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/icons/integration.svg" style="width:50%">
	               		</div>
	               		<div class="col-md-9">
	               			<h6>Integrations</h6>
	               			<span class="lead">Want all your DevOps tools tightly integrated with each other?<br/> 
	               			Blinqlabs will deliver a seamless Delivery pipeline by making sure all your tools work perfectly together.</span>
	               		</div>
              		</div>
      
   	</div>
      
 </div>