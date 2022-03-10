<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:user-landing>

	<jsp:body>
<!--  side bar -->	
         
       	<div id="page-wrapper">
			   <div class="container">
			   
			   <c:if test="${not empty failuremessage}">
				 	<script>
							$(window).on("load", function() {
								toastr.error('${failuremessage}', 'Feature', {
									"progressBar" : true
								});
							});
						</script>
				</c:if>
				
				<c:if test="${not empty successmessage}">
				 	<script>
							$(window).on(
									"load",
									function() {
										toastr.success('${successmessage}',
												'Feature', {
													"progressBar" : true
												});
									});
						</script>
				</c:if>
				
		         <br/><br/><br/><br/>
		         <div>
		         <div class="card  mb-3 text-center">
				      <div class="card-body text-white align-items-center d-flex no-block p-15 bg-primary mx-3 px-3" >
		                   <div class="">
		                   <c:if test="${not empty accSettingList[0].TX_IMAGE}">
		                   		<img src="${accSettingList[0].TX_IMAGE}" alt="user" class="img-circle" width="60"></div>
		                   </c:if>
		                   
		                   <c:if test="${empty accSettingList[0].TX_IMAGE}">
		                   		<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="img-circle" width="60"></div>
		                   </c:if>
		                   <div class="m-l-10 px-2 mx-2">
		                         <h4 class="m-b-0"><span>${sessionScope.firstname} ${sessionScope.lastname}</span></h4>
		                       <p class=" m-b-0 text-white" ><security:authentication property="principal.username" /></p>
		                   </div>
		               </div>
		      
		     	</div>
	     	</div>
		     	
     		<div class="basket">
     			<div class="row">
     				<div class="col-lg-6 col-md-6 col-sm-12 text-center">
     					<img src="${pageContext.request.contextPath}/resources/img/illustration/mobile-check.png" width="100%">
     				</div>
     				<div class="col-lg-6 col-md-6 col-sm-12">
     					<div class="mt-4">
     						<p class="text-center"><span class="text-warning h3"><i class="fas fa-exclamation-triangle"></i>  </span><span class=" h5 text-info">Do not refresh or navigate away from this page.</span></p>
     						
     					</div>
     					<div class="mt-4 p-4">
     						<p class="text-primary h4">We have sent an OTP to your phone. Please enter the code to complete phone verification.</p>
     					</div>
     					<div class="text-center">
     						<form action="${contextPath}/user/verifyPhone" method="POST" modelAttribute="AccountVerificationForm">
     							<div>
     								<input type="text" id="ID_USER" name="ID_USER" value="${userid}">
     							</div>
     							<div>
	     							<input type="text" id="TX_RETURN_CODE" name="TX_RETURN_CODE" placeholder="********">
	     							<button type="submit" class="btn btn-primary">Submit</button>
     							</div>
     						</form>
     					</div>
     					
     					<div  class="mt-4 p-4 text-center">
     						<form action="${contextPath}/user/verifyPhone" method="GET"  modelAttribute="AccountVerificationForm">
     							<p>Did not receive your secret code?</p>
   								<button type="submit" class="btn-pill btn-pill-sm btn-pill-secondary">Request new code</button>
   								<div>
   									<input type="text" value="${userid}" id="ID_USER_EMAIL" name="ID_USER" >
   								</div>
  							</form>
     					</div>
     					
     				</div>
     			</div>
   			</div>
   			  <script>
	         	document.getElementById("ID_USER").style.visibility = "hidden";
	         	document.getElementById("ID_USER_EMAIL").style.visibility = "hidden";
         	</script>
		     
		</div>    		 

	</jsp:body>
</page:user-landing>
