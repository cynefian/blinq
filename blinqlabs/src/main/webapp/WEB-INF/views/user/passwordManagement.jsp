<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:user-landing>

	<jsp:body>
	
	<div id="page-wrapper" class="page-wrapper">
	 	    <div class="container">
		      <div class="header">
		       <div class="card mt-3 text-center">
			      <div class="card-body text-white align-items-center d-flex no-block p-15 bg-primary mx-3 px-3" >
		               <div class="">
		                  <c:if test="${not empty accSettingList[0].TX_IMAGE}">
		                  		<img src="${accSettingList[0].TX_IMAGE}" alt="user" class="img-circle" width="60">
		                  </c:if>
		                  
		                  <c:if test="${empty accSettingList[0].TX_IMAGE}">
		                  		<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="img-circle" width="60">
		                  </c:if>
		               </div>
		               <div class="m-l-10 px-2 mx-2">
		                     <h4 class="m-b-0"><span>${sessionScope.firstname} ${sessionScope.lastname}</span></h4>
		                   <p class=" m-b-0 text-white" ><security:authentication property="principal.username" /></p>
		               </div>
		           </div>
		 
		 		</div>
 			</div>
				
	
	
		</div>
      
   
   

    	<div class="container">
    
	    	<div class="row">
	    		<div class="col-lg-9 col-sm-12">
	    			<div>
						<div class="text-success">
							${successmessage}
						</div>
						<div class="text-danger">
							${failuremessage}
						</div>
					</div>
			  
			       <div class="card">
				
						<div class="card-header bg-info col-md-12 col-sm-12">
			                  <h4 class="m-b-0 text-white">Reset your Password</h4>
			             </div>
				
						<div class="card-body ">
				
								<div class="formy">
									<div class="row">
										<div class="col-md-12 col-sm-12">
											<form class="form-horizontal m-t-20" action="passwordManagement" method="POST" name="passwordManagementForm" modelAttribute="passwordManagementBean">
												
												
												<div class="form-group">
													<label for="passwordOrig">Current Password</label> <input type="password"
														class="form-control" id="currentpassword" name="currentpassword" path="currentpassword"
														required />
												</div>
												
												<div class="form-group">
													<label for="passwordnew1">New Password</label> <input type="password"
														class="form-control" id="newpassword1" name="newpassword1"  path="newpassword1"
														required />
												</div>
												<div class="form-group">
													<label for="passwordnew1">Confirm Password</label> <input type="password"
														class="form-control" id="newpassword2" name="newpassword2"  path="newpassword2"
														required />
												</div>
												
												<div class="text-center mt-4">
													<input type="submit" id="loginBtn"
														class="submit btn-block btn-shadow btn-shadow-info"
														value="Update">
												</div>
											</form>
										</div>
									</div>
								</div>
							
							</div>
					</div>
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
	    		<div class="col-lg-3 col-sm-12 table-info p-1">
	    			
   					<div class="text-right p-3">
   						<a href="${contextPath}/user/useraccountsettings">Account Settings</a>
   					</div>
   					<hr/>
	    			<div >
   						<div>
   							<p class="lead h2">Password Last Updated: <br/><span class="text-info h5"> ${accSettingList[0].TS_PWD_UPDATE}</span></p>
   						</div>
   						<div class="mt-4">
   							<c:if test="${accSettingList[0].FL_2FA==true}">
                         			<p class="lead h2">2FA: <span class="h5 text-success" >Enabled</span></p>
                         			<div class="text-right">
                           				<a href="${contextPath}/user/enable2FA"><button type="button" class="btn btn-sm btn-outline-secondary">Reset 2FA</button></a>
                           			</div>
                         		</c:if>
                         		<c:if test="${accSettingList[0].FL_2FA==false}">
                         			<p class="lead h2">2FA: <span class="h5 text-danger">Disabled</span></p>
                         			<div class="text-right">
                         				<a href="${contextPath}/user/enable2FA"><button type="button" class="btn btn-sm btn-outline-secondary">Enable 2FA</button></a>
                         			</div>
                         		</c:if>
   						</div>
   					</div>
   					
	    		</div>
	    	
	    	</div>
		</div>
	 </div>
	
</jsp:body>
</page:user-landing>
