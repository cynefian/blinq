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
         
       	<div id="page-wrapper" class="page-wrapper">
		     
		      
		      
		      <script>
		      window.addEventListener('load', function() {
		    	  
		    	  document.querySelector('input[type="file"]').addEventListener('change', function() {
    			      if (this.files && this.files[0]) {
    			          var img = document.getElementById("header-img");  // $('img')[0]
    			          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
    			          img.onload = imageIsLoaded;
    			      }
    			  });
        			  
        			  
			      document.getElementById("change-image-btn").addEventListener('click', function() {
					  document.getElementById("headerbgFile").click();
				      if (this.files && this.files[0]) {
				          var img = document.getElementById("header-img");  // $('img')[0]
				          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
				          img.onload = imageIsLoaded;
				      }
				  });
		      
		      });
		      
		      function imageIsLoaded() { 
     			 // alert(this.src);  // blob url
     			  // update width and height ...
     			  toDataURL(this.src, function(dataUrl) {
     				  console.log('RESULT:', dataUrl);
     				  document.getElementById("TX_IMAGE").value=dataUrl;
     				})
     			}
     			
     			function toDataURL(url, callback) {
     				  var xhr = new XMLHttpRequest();
     				  xhr.onload = function() {
     				    var reader = new FileReader();
     				    reader.onloadend = function() {
     				      callback(reader.result);
     				    }
     				    reader.readAsDataURL(xhr.response);
     				  };
     				  xhr.open('GET', url);
     				  xhr.responseType = 'blob';
     				  xhr.send();
     				}
		      </script>
		     
		       <div class="container">
		         <div>
		         <div class="card  mb-3 mt-3 text-center">
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
		                        <%-- <h4 class="m-b-0"><span>${fname} ${lname}</span></h4> --%> 
		                         <h4 class="m-b-0"><span>${sessionScope.firstname} ${sessionScope.lastname}</span></h4>
		                        <%-- <h4 class="m-b-0"><span><%session.getAttribute("firstname");%> <%session.getAttribute("lastname");%></span></h4> --%>
		                       <p class=" m-b-0 text-white" ><security:authentication property="principal.username" /></p>
		                   </div>
		               </div>
		      
		     	</div>
	     	</div>
		     	
     		<div class="basket">
     			<div class="row">
     		
     				<div class="col-lg-9 col-sm-12">
     				
     					     
					     <c:if test="${not empty accSettingList}">
					     	<c:forEach var="entry" items="${accSettingList}">
		     
					                <div class="card">
					                
					                 <div class="card-body">
		                                <h4 class="card-title">Profile</h4>
		                            </div>
		                            <hr>
		                            
		                            <form class="form-horizontal m-t-20" action="${contextPath}/user/useraccountsettings" method="POST" name="accountSettingsForm" modelAttribute="AccountSettingsBean" id="accountUpdateForm">
			                            <div class="row">
				     						<div class="col-lg-6 col-sm-12">
				     							<div>
				     								<div class="card-body">
						                            	<c:if test="${not empty entry.TX_IMAGE}">
						                            		<img alt="header" src="${entry.TX_IMAGE}" id="header-img" width="60" height=60 value="header-img" path="header-img" class="header-image"/>
						                            	</c:if>
						                            	
						                            	<c:if test="${empty entry.TX_IMAGE}">
						                            		<img alt="header" src="${pageContext.request.contextPath}/resources/images/users/1.jpg" id="header-img" width="60" height=60 value="header-img" path="header-img" class="header-image"/>
						                            	</c:if>
						                            	
						                            	<div class=" mt-2 p-2 text-right">
						                            		<button class="btn btn-outline-primary" type="button" id="change-image-btn">Profile Picture</button>
						                            	</div>
												    </div>
											    </div>
											    
											      <div class="card hidden-element">
							                            <div class="card-body">
							                                <h4 class="card-title">Choose Profile Image</h4>
							                                    <fieldset class="form-group">
							                                        <input type="file" class="form-control-file" id="headerbgFile">
							                                    </fieldset>
							                            </div>
							                        </div>
						                        
					     						</div>
					     						<div class="col-lg-6 col-sm-12">
					     							<div class="card-body">
						                                <div class="row">
						                                    <div class="col-sm-12 col-md-12">
						                                        <div class="form-group">
						                                            <label for="inputfname" class="control-label col-form-label">First Name</label>
						                                            <input type="text" class="form-control" placeholder="First Name Here"  id="tx_firstname"  name="tx_firstname" path="tx_firstname" value="${entry.tx_firstname}">
						                                        </div>
						                                    </div>
						                                   </div>
						                                   <div class="row">
						                                    <div class="col-sm-12 col-md-12">
						                                        <div class="form-group">
						                                            <label for="inputlname2" class="control-label col-form-label">Last Name</label>
						                                            <input type="text" class="form-control" placeholder="Last Name Here" id="tx_lastname" name="tx_lastname" path="tx_lastname" value="${entry.tx_lastname}">
						                                        </div>
						                                    </div>
						                                </div>
						                            </div>
					     						</div>
					     					</div>
					     					<hr>
					     					<div class="row">
					     						<div class="col-lg-12 col-sm-12">
				     							 	 <div class="card-body">
						                                <h4 class="card-title">Contact Info &amp; Bio</h4>
						                                <div class="row">
						                                    <div class="col-sm-12 col-md-6">
						                                        <div class="form-group">
						                                            <label for="email1" class="control-label col-form-label">Email</label>
						                                            <input type="email" class="form-control" placeholder="Email Here" id="tx_email" name="tx_email" path="tx_email" value="${entry.tx_email}" disabled>
						                                        </div>
						                                    </div>
						                                    <div class="col-sm-12 col-md-6">
						                                        <div class="form-group">
						                                            <label for="wen1" class="control-label col-form-label">Website</label>
						                                            <input type="text" class="form-control" placeholder="http://" id="tx_website" name="tx_website" path="tx_website" value="${entry.tx_website}">
						                                        </div>
						                                    </div>
						                                </div>
						                                <div class="row">
						                                    <div class="col-12">
						                                        <div class="form-group">
						                                            <label for="cono" class="control-label col-form-label">Contact No</label>
						                                            <input type="text" class="form-control" placeholder="Contact No Here" id="tx_contact_num" name="tx_contact_num" path="tx_contact_num" value="${entry.tx_contact_num}" >
						                                        </div>
						                                    </div>
						                                    <div class="col-12">
						                                        <div class="form-group">
						                                            <label for="bio" class="control-label col-form-label">Bio</label>
						                                            <textarea class="form-control" id="tx_bio" name="tx_bio" path="tx_bio" >${entry.tx_bio}</textarea>
						                                        </div>
						                                    </div>
						                                </div>
						                            </div>
				     						</div>
				     					</div>
	                                  	<hr>
			                            <div class="row">
				     						<div class="col-lg-12 col-sm-12">
					     						 <div class="card-body">
					                                <div class="action-form">
					                                    <div class="form-group m-b-0 text-right">
					                                        <input type="text" id="TX_IMAGE" name="TX_IMAGE" path="TX_IMAGE" value="${entry.TX_IMAGE}">
					                                        <button type="button" class="btn btn-info waves-effect waves-light" onclick="submitChange();">Save</button>
					                                    </div>
					                                </div>
					                            </div>
				     						</div>
			     						</div>
									 </form>
			                      </div>
                      				<script>
                      					function submitChange(){
                      						document.getElementById("accountUpdateForm").submit();	
                      					}
                      					
                      				</Script>
                        
               				</c:forEach>
					 	</c:if>
                   </div>
     			
	     			<div class="col-lg-3 col-sm-12 table-active p-3">
	     				<security:authorize access="hasRole('ROLE_ROOT') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUPER') OR hasAuthority('PERM_ADMIN_WEBSITE')">
		     				<div class="text-right">
		     					<button type="button" class="btn btn-sm btn-outline-primary" data-toggle="modal" data-target="#authoritiesModal">View Authorities</button>
		     				</div>
		     				<hr/>
		     				
		     				<!-- Authorities Modal -->
						
								<div class="modal fade" id="authoritiesModal" tabindex="-1" role="dialog">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        	<h4 class="modal-title" id="exampleModalLabel1">Authorities</h4>
		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								      </div>
								      <div class="modal-body">
								     	  <div class="form-group">
								     	  	<c:if test="${not empty authorities}">
								     	  		<c:forEach var="auth" items="${authorities}">
								     	  			<div>
								     	  				<p>${auth}</p>
								     	  			</div>
								     	  		</c:forEach>
								     	  	</c:if>
								     	  	<c:if test="${empty authorities}">
								     	  		<div>
				                                	<p>You do not have any Authority</p>	
				                                </div>
								     	  	</c:if>
				                                
				                            </div>
								      </div>
								      <div class="modal-footer">
								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		                              </div>
								    </div>
								  </div>
								</div>
								
	     				</security:authorize>
	     				<div>
	     					<c:if test="${not empty accVeriyList}">
	     						<div class="mt-2">
	     							<c:if test="${not empty accVeriyList[0].FL_EMAIL_VERIFIED}">
	     								<c:if test="${accVeriyList[0].FL_EMAIL_VERIFIED==true}">
			     							<p class="lead h5 text-success"> <i class="far fa-check-circle"></i> Email Verified</p>
			     							<p>${accVeriyList[0].TX_EMAIL}</p>
			     						</c:if>
			     						<c:if test="${accVeriyList[0].FL_EMAIL_VERIFIED==false}">
			     							<p class="lead h5 text-danger"> <i class="far fa-times-circle"></i> Email Not Verified</p>
			     							<p>${accVeriyList[0].TX_EMAIL}</p>
			     							<div class="text-right">
				     							<form action="${contextPath}/user/verifyEmail" method="GET"  modelAttribute="AccountVerificationForm">
				     								<button type="submit" class="btn btn-outline-primary">Verify Email</button>
				     								<input type="text" value="${userid}" id="ID_USER_EMAIL" name="ID_USER"  style="visibility:hidden">
				     							</form>
			     							</div>
			     						</c:if>
	     							</c:if>
	     							
	     							<c:if test="${empty accVeriyList[0].FL_EMAIL_VERIFIED}">
	     								<p class="lead h5 text-danger"> <i class="far fa-times-circle"></i> Email Not Verified</p>
			     							<p>${accVeriyList[0].TX_EMAIL}</p>
			     							<div class="text-right">
				     							<form action="${contextPath}/user/verifyEmail" method="GET"  modelAttribute="AccountVerificationForm">
				     								<button type="submit" class="btn btn-outline-primary">Verify Email</button>
				     								<input type="text" value="${userid}" id="ID_USER_EMAIL" name="ID_USER"  style="visibility:hidden">
				     							</form>
			     							</div>
	     							</c:if>
		     						
	     						</div>		
	     						<hr/>
	     						<div class="mt-2">
	     							<c:if test="${not empty accVeriyList[0].TX_CONTACT_NUM}">
	     								<c:if test="${not empty accVeriyList[0].FL_MOB_VERIFIED}">
	     									<c:if test="${accVeriyList[0].FL_MOB_VERIFIED==true}">
				     							<p class="lead h5 text-success"> <i class="far fa-check-circle"></i> Phone Verified</p>
				     							<p>${accVeriyList[0].TX_CONTACT_NUM}</p>
				     						</c:if>
				     						<c:if test="${accVeriyList[0].FL_MOB_VERIFIED==false}">
				     							<p class="lead h5 text-danger"> <i class="far fa-times-circle"></i> Phone Not Verified</p>
				     							<p>${accVeriyList[0].TX_CONTACT_NUM}</p>
				     							<div class="text-right">
				     							<form action="${contextPath}/user/verifyPhone" method="GET"  modelAttribute="AccountVerificationForm">
				     								<button type="submit" class="btn btn-outline-primary">Verify Phone</button>
				     								<input type="text" value="${userid}" id="ID_USER_PHONE" name="ID_USER"  style="visibility:hidden">
				     							</form>
			     							</div>
				     						</c:if>
	     								</c:if>
	     								<c:if test="${empty accVeriyList[0].FL_MOB_VERIFIED}">
	     									<p class="lead h5 text-danger"> <i class="far fa-times-circle"></i> Phone Not Verified</p>
				     							<p>${accVeriyList[0].TX_CONTACT_NUM}</p>
				     							<div class="text-right">
				     							<form action="${contextPath}/user/verifyPhone" method="GET"  modelAttribute="AccountVerificationForm">
				     								<button type="submit" class="btn btn-outline-primary">Verify Phone</button>
				     								<input type="text" value="${userid}" id="ID_USER_PHONE" name="ID_USER"  style="visibility:hidden">
				     							</form>
			     							</div>
	     								</c:if>
	     							</c:if>
	     							<c:if test="${empty accVeriyList[0].TX_CONTACT_NUM}">
	     								<p class="lead h5 text-warn"> <i class="fas fa-exclamation-triangle"></i> Phone Not specified</p>
	     							</c:if>
	     						</div>		
	     					</c:if>
	     					
	     					<c:if test="${empty accVeriyList}">
	     						<div class="mt-2">
	     							<p class="lead h5 text-danger"> <i class="far fa-times-circle"></i> Email Not Verified</p>
	     							<p>${accVeriyList[0].TX_EMAIL}</p>
	     							<div class="text-right">
				     							<form action="${contextPath}/user/verifyEmail" method="GET" modelAttribute="AccountVerificationForm">
				     								<button type="submit" class="btn btn-outline-primary">Verify Email</button>
				     								<input type="text" value="${userid}" id="ID_USER_EMAIL" name="ID_USER"  style="visibility:hidden">
				     							</form>
			     							</div>
	     						</div>
	     						<hr/>
	     						<div class="mt-2">	
	     							<c:if test="${empty accVeriyList[0].TX_CONTACT_NUM}">
	     								<p class="lead h5 text-warn"> <i class="fas fa-exclamation-triangle"></i> Phone Not specified</p>
	     							</c:if>
	     							<c:if test="${not empty accVeriyList[0].TX_CONTACT_NUM}">
	     								<p class="lead h5 text-danger"> <i class="far fa-times-circle"></i> Phone Not Verified</p>
	     								<p>${accVeriyList[0].TX_CONTACT_NUM}</p>
	     								<div class="text-right">
				     							<form action="${contextPath}/user/verifyPhone" method="GET"  modelAttribute="AccountVerificationForm">
				     								<button type="submit" class="btn btn-outline-primary">Verify Phone</button>
				     								<input type="text" value="${userid}" id="ID_USER_PHONE" name="ID_USER"  style="visibility:hidden">
				     							</form>
			     							</div>
	     							</c:if>
	     						</div>
	     						
	     					</c:if>
     					</div>
     					<hr/>
     					<div>
     						<div>
     							<p>Password Last Updated: <br/><span class="text-info"> ${accSettingList[0].TS_PWD_UPDATE}</span></p>
     							<div class="text-right">
     								<a href="${contextPath}/user/passwordManagement"><button type="button" class="btn btn-sm btn-outline-secondary">Update Password</button></a>
     							</div>
     						</div>
     						<div class="mt-4">
     							<c:if test="${accSettingList[0].FL_2FA==true}">
                           			<p>2FA: <span class="text-success" >Enabled</span></p>
                           			<div class="text-right">
                           				<a href="${contextPath}/user/enable2FA"><button type="button" class="btn btn-sm btn-outline-secondary">Reset 2FA</button></a>
                           			</div>
                           		</c:if>
                           		<c:if test="${accSettingList[0].FL_2FA==false}">
                           			<p>2FA: <span class="text-danger">Disabled</span></p>
                           			<div class="text-right">
                           				<a href="${contextPath}/user/enable2FA"><button type="button" class="btn btn-sm btn-outline-secondary">Enable 2FA</button></a>
                           			</div>
                           		</c:if>
     						</div>
     					</div>
     					
     					
     				</div>
     		
     			</div>     
                        
        	 </div>
         
             <script>
	         	document.getElementById("TX_IMAGE").style.visibility = "hidden";
	         </script>
		</div>
		
	</jsp:body>
</page:user-landing>
