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
         
         
          <div class="container">
			   
			    <c:if test="${not empty successmessage}">
						 	<script>
						 	 	$(window).on("load", function() {
							    	toastr.success('${successmessage}', 'Success', { "progressBar": true });
							    });
						  	</script>
						</c:if>
					
						<c:if test="${not empty failuremessage}">
						 	<script>
						 	 	$(window).on("load", function() {
							    	toastr.error('${failuremessage}', 'Password Error', { "progressBar": true });
							    });
						  	</script>
						</c:if>
		</div>
       	<div id="page-wrapper">
			   <div class="container">
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
     					
     					<div class="text-center mt-4">
     						<p class="text-primary h4">We are glad you are taking security seriously.</p>
     					</div>
     					<img src="${pageContext.request.contextPath}/resources/img/illustration/secure-site.png" width="100%">
     				</div>
     				<div class="col-lg-6 col-md-6 col-sm-12">
     				
     					<div>
     						<p class="text-success h4">${successmessage}</p>
     						<c:if test="${not empty failuremessage}">
     							<p class="text-danger h4">Message: ${failuremessage}</p>
     						</c:if>
     					</div>
     				
     					<c:if test="${accSettingList[0].FL_2FA==true}">
     						<form action="${contextPath}/user/reset2FA" method="POST" modelAttribute="AccountVerificationForm">
	     						<div class="text-center m-4 pt-4">
	     							<p class="text-info h2">Two Factor Authentication is enabled for your account.</p>
	     							<br/>
	     							<p class="lead">Would you like to recreate your 2FA token?</p>
	     							<button type="button" class="btn-pill btn-pill-primary" data-toggle="modal" data-target="#reset2FAModal">Restart 2FA process</button>
	     						</div>
    						</form>
    						
    						<form action="${contextPath}/user/reset2FA" method="POST" modelAttribute="AccountVerificationForm">
    							<div class="text-center m-4 pt-4">
    									<button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#reset2FAModal">Disable 2FA</button>
    							</div>
    						</form>
    						
    						
    						<!-- 2FA Reset Modal -->
						
						<div class="modal fade" id="reset2FAModal" tabindex="-1" role="dialog">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						    <form class="form-horizontal m-t-20" action="${contextPath}/user/reset2FA" method="GET" name="accountVerificationForm" modelAttribute="AccountVerificationForm">
						      <div class="modal-header">
						        	<h4 class="modal-title" id="exampleModalLabel1">Reset 2FA</h4>
                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      </div>
						      <div class="modal-body">
						     	  <div class="form-group">
		                               
		                                <div>
		                                	<label for="machine-id" class="control-label text-danger">This action will reset your current 2FA token to make way for a new token. </label>
		                                </div>
		                                
		                                <div>
		                                	<label for="machine-id" class="control-label">Are you sure you want to proceed?</label>
		                                </div>
		                                
		                                <div>
		                                	<label for="machine-id" class="control-label">Enter your current 2FA code:  </label>
		                                	<input type="text" id="TX_RETURN_CODE" name="TX_RETURN_CODE" placeholder="******">
		                                </div>
		                                
                                	</div>
						      </div>
						      <div class="modal-footer">
						      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            	 	<button type="submit" class="btn btn-warning" >Reset 2FA</button> 
						      </div>
						      </form>
						    </div>
						  </div>
						</div>
						
						
     					</c:if>
     					<c:if test="${accSettingList[0].FL_2FA==false}">
	     					<div class="mt-4">
	     						<p class="text-center"><span class="text-warning h3"><i class="fas fa-exclamation-triangle"></i>  </span><span class=" h5 text-info">Do not refresh or navigate away from this page.</span></p>
	     						
	     					</div>
	     					<div class="mt-4 p-4">
	     						<p class="text-primary h5">Using the below QR code, enter the secret generated by your authenticator app.</p>
	     					</div>
	     					<div class="text-center">
	     						<img src="${qrUrl}">
	     						<br/>
	     						<form action="${contextPath}/user/enable2FA" method="POST" modelAttribute="AccountVerificationForm">
	     							<div>
	     								<input type="text" id="ID_USER" name="ID_USER" value="${userid}">
	     							</div>
	     							<div>
		     							<input type="text" id="TX_RETURN_CODE" name="TX_RETURN_CODE" placeholder="******">
		     							<button type="submit" class="btn btn-primary">Submit</button>
	     							</div>
	     						</form>
	     					</div>
     					</c:if>
     				</div>
     			</div>
   			</div>
   			  <script>
	         	document.getElementById("ID_USER").style.visibility = "hidden";
	        </script>
		     
		</div>    		 


	</jsp:body>
</page:user-landing>
