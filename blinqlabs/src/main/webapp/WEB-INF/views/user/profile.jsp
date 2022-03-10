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
	
	<div id="page-wrapper" class="page-wrapper">
	<!--  side bar -->	
         <%-- <jsp:include page="/WEB-INF/shared/user-sidebar.jsp" />  --%>
  		
		    <div class="container">
		      <div class="header">
		      <div class="card  mb-3 mt-3 text-center">
		      <div
							class="card-body text-white align-items-center d-flex no-block p-15 bg-primary px-3">
		                   <div class="">
		                   		<c:if test="${not empty sessionScope.userImage}">
		                            	<img src="${sessionScope.userImage}"
										alt="user" class="rounded-circle" width="60">
		                            </c:if>
		                            <c:if
									test="${empty sessionScope.userImage}">
		                            	<img
										src="${pageContext.request.contextPath}/resources/images/users/1.jpg"
										alt="user" class="rounded-circle" width="60">
		                            </c:if>
		                   </div>
		                   <div class="m-l-10 px-2 mx-2">
		                        <%-- <h4 class="m-b-0"><span>${fname} ${lname}</span></h4> --%> 
		                         <h4 class="m-b-0">
									<span>${sessionScope.firstname} ${sessionScope.lastname}</span>
								</h4>
		                        <%-- <h4 class="m-b-0"><span><%session.getAttribute("firstname");%> <%session.getAttribute("lastname");%></span></h4> --%>
		                       <p class=" m-b-0 text-white">
									<security:authentication property="principal.username" />
								</p>
		                   </div>
		               </div>
		      
		     </div>
		      </div>
		
		      
		    </div>
		
  

    <div class="basket">
    
 <!--    <div class="row">
    	<div class="col-lg-9 col-sm-9 p-2">
    	
    	</div>
    	
    	<div class="col-lg-3 col-sm-3 border-left p-2">
    		<div>
    			<p class="lead">
  					Links
				</p>
    		</div>
    	</div>
    </div>
     -->
    
    
    
    
    <div class="card-columns">
    
    	<div class="card card-hover border border-info mb-3 ">
               <div class="card-header bg-info">
                   <h4 class="m-b-0 text-white">Messages</h4>
						</div>
               <div class="card-body style=" background-color:#eef5f9">
               	<div class="row">
               		<div class="col-md-6 col-sm-6">
               				<div>
                  		<a
											href="${contextPath}/user/UserMessages?page=1&filter=U"><h4>
												<i class="far fa-envelope"></i> Inbox  (${inboxCount}) </h4></a>
                  	</div>
                  	<div>
                  		<a
											href="${contextPath}/user/UserMessages?page=1&filter=S"><h4>
												<i class="fas fa-star text-yellow"></i> Starred  (${starCount})</h4></a>
                  	</div>	
               		</div>
               		<div class="col-md-6 col-sm-6 wow fadeInUp">
               		
               		</div>
               	</div>
               	
               	<div class="text-right">
               		<a href="${contextPath}/user/UserMessages?page=1"
									class="btn btn-inverse">View all Messages  (${allMessageCount})</a>
               	</div>
               	
               	
                   
               </div>
           </div>
    
    
    
    <security:authorize access="hasAuthority('PERM_LICENSE')">
     <div class="card  card-hover border border-info mb-3 ">
                                    <div class="card-header bg-info">
                                        <h4 class="m-b-0 text-white">Licenses</h4>
						</div>
                                    <div class="card-body">
                                       <div>
                                    		<a
									href="${contextPath}/user/userLicenses?page=1&filter=all"><h4>Product Licenses  (${licenseCount})</h4></a>
                                    	</div>
                                    	<div>
                                    		<a href="javascript:void(0)"><h4>Saas Licenses</h4></a>
                                    	</div>
                                    	<div class="text-right">
                                    		<a
									href="${contextPath}/user/userLicenses?page=1&filter=all"
									class="btn btn-inverse">View all Licenses</a>
                                    	</div>
                                        
                                    </div>
                                </div>
                              </security:authorize>
                                
                                
                                <security:authorize access="hasAuthority('PERM_FEATURE_TECH_SUPPORT') OR hasAuthority('PERM_FEATURE_CLOUD') "> 
                          <div class="card card-hover border border-info mb-3 ">
                                    <div class="card-header bg-info">
                                        <h4 class="m-b-0 text-white">Services</h4>
						</div>
                                    <div class="card-body">
                                        <h4 class="card-title"><a href="${contextPath}/user/userServices">Entitlements (${entitlementCount})</a></h4>
                                    </div>
                                </div>
                                </security:authorize>
                                
                                
                                <security:authorize access="hasAuthority('PERM_USER_PAYMENT')  "> 
                                <div
									class="card card-hover border border-info mb-3 ">
                                    <div class="card-header bg-info">
                                        <h4 class="m-b-0 text-white">Payment & Billing</h4>
									</div>
                                    <div class="card-body">
                                        <h3 class="card-title">Special title treatment</h3>
                                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                                        <a href="javascript:void(0)"
								class="btn btn-inverse">Go somewhere</a>
                                    </div>
                                </div>
                                </security:authorize>
                                
                                <div
						class="card card-hover border border-info mb-3 ">
                                    <div class="card-header bg-info">
                                        <h4 class="m-b-0 text-white">Security</h4>
						</div>
                                    <div class="card-body">
                                       <div>
                                    		<p> Last Password changed: <span
										class="h6">${passwordChanged}</span>
								</p>
                                    	</div>
                                    	<div>
                                    		<c:if test="${doublesecure==true}">
                                    			<p>2FA: <span
											class="text-success">Enabled</span>
									</p>
                                    		</c:if>
                                    		<c:if
									test="${doublesecure==false}">
                                    			<p>2FA: <span
											class="text-danger">Disabled</span>
									</p>
                                    		</c:if>
                                   		</div>
                                    		
                                    	<div>	
                                    		<div class="row text-center">
                                    			<c:if
										test="${not empty accVeriyList}">
	                                    			<div
											class="col-lg-5 col-sm-5 table-active m-2 p-3">
	                                    				<div class="mt-2">
								     							<c:if
													test="${not empty accVeriyList[0].FL_EMAIL_VERIFIED}">
								     								<c:if
														test="${accVeriyList[0].FL_EMAIL_VERIFIED==true}">
										     							<p class="h2">
															<i class="far fa-envelope-open"></i>  <span
																class="lead h5 text-success"> <i
																class="far fa-check-circle"></i> Email Verified</span>
														</p>
										     						</c:if>
										     						<c:if
														test="${accVeriyList[0].FL_EMAIL_VERIFIED==false}">
										     							<p class="h2">
															<i class="far fa-envelope-open"></i>  <span
																class="lead h5 text-danger"> <i
																class="far fa-times-circle"></i> Email Not Verified</span>
														</p>
										     						</c:if>
								     							</c:if>
								     							
								     							<c:if
													test="${empty accVeriyList[0].FL_EMAIL_VERIFIED}">
								     								<p class="h2">
														<i class="far fa-envelope-open"></i>  <span
															class="lead h5 text-danger"> <i
															class="far fa-times-circle"></i> Email Not Verified</span>
													</p>
										     					</c:if>
									     						
								     						</div>
	                                    			
	                                    			</div>
	                                    			<div
											class="col-lg-5 col-sm-5 table-active p-3 m-2">
															<c:if
												test="${not empty accVeriyList[0].TX_CONTACT_NUM}">
								     								<c:if
													test="${not empty accVeriyList[0].FL_MOB_VERIFIED}">
								     									<c:if
														test="${accVeriyList[0].FL_MOB_VERIFIED==true}">
											     							<p class="h2">
															<i class="fas fa-mobile"></i>  <span
																class="lead h5 text-success"> <i
																class="far fa-check-circle"></i> Phone Verified</span>
														</p>
											     						</c:if>
											     						<c:if
														test="${accVeriyList[0].FL_MOB_VERIFIED==false}">
											     							<p class="h2">
															<i class="fas fa-mobile"></i><span
																class="lead h5 text-danger"> <i
																class="far fa-times-circle"></i> Phone Not Verified</span>
														</p>
											     						</c:if>
								     								</c:if>
								     								<c:if
													test="${empty accVeriyList[0].FL_MOB_VERIFIED}">
								     									<p class="h2">
														<i class="fas fa-mobile"></i>  <span
															class="lead h5 text-danger"> <i
															class="far fa-times-circle"></i> Phone Not Verified</span>
													</p>
											     					</c:if>
								     							</c:if>
								     							<c:if test="${empty accVeriyList[0].TX_CONTACT_NUM}">
								     								<p class="h2">
													<i class="fas fa-mobile"></i><span
														class="lead h5 text-warn"> <i
														class="fas fa-exclamation-triangle"></i> Phone Not specified</span>
												</p>
								     							</c:if>
	                                    			
	                                    			</div>
	                                    		</c:if>
	                                    		
	                                    		<c:if
										test="${empty accVeriyList}">
	                                    			<div
											class="col-lg-5 col-sm-5 table-active m-2 p-3">
	                                    				<div class="mt-2">
	                                    					<p class="h2">
													<i class="far fa-envelope-open"></i>  <span
														class="lead h5 text-danger"> <i
														class="far fa-times-circle"></i> Email Not Verified</span>
												</p>
	                                    				</div>
	                                   				</div>
	                                    				
	                                    			<div
											class="col-lg-5 col-sm-5 table-active p-3 m-2 ">
	                                    				<div class="mt-2">
	                                    					<p class="h2">
													<i class="fas fa-mobile"></i>  <span
														class="lead h5 text-warn"> <i
														class="fas fa-exclamation-triangle"></i> Phone Not specified</span>
												</p>
	                                    				</div>
	                                    			</div>
	                                    		</c:if>
                                    		</div>
                                    	</div>
                                    	
                                   		<div class="text-right">
                                    		<a
									href="${contextPath}/user/useraccountsettings"
									class="btn btn-inverse">Account Settings</a>
                                        </div>
                                    </div>
                                </div>
                                
                                <security:authorize
						access="hasAuthority('PERM_FEATURE_TICKETING')">
	                                <div
							class="card card-hover border  border-info mb-3 ">
	                                    <div class="card-header bg-info">
	                                        <h4 class="m-b-0 text-white">Help and Support</h4>
							</div>
	                                    <div class="card-body">
	                                       <div class="row m-t-1">
			                                    <!-- Column -->
			                                    <div
										class="col-md-6 col-lg-3 col-xlg-3">
			                                        <a class="card card-hover"
											href="${contextPath}/user/UserTickets?page=1&filter=all"
											method="GET">
			                                            <div
												class="box bg-info text-center">
			                                                <h1
													class="font-light text-white">${totalTickets}</h1>
			                                                <h6
													class="text-white">Total Tickets</h6>
			                                            </div>
			                                        </a>
			                                    </div>
			                                    <!-- Column -->
			                                    <div
										class="col-md-6 col-lg-3 col-xlg-3">
			                                        <a class="card card-hover"
											href="${contextPath}/user/UserTickets?page=1&filter=responded"
											method="GET">
			                                            <div
												class="box bg-primary text-center">
			                                                <h1
													class="font-light text-white">${respondedTickets}</h1>
			                                                <h6
													class="text-white">Responded</h6>
			                                            </div>
			                                        </a>
			                                    </div>
			                               		<!-- Column -->
			                                    <div
										class="col-md-6 col-lg-3 col-xlg-3">
			                                        <a class="card card-hover"
											href="${contextPath}/user/UserTickets?page=1&filter=resolved"
											method="GET">
			                                            <div
												class="box bg-success text-center">
			                                                <h1
													class="font-light text-white">${resolvedTickets}</h1>
			                                                <h6
													class="text-white">Resolve</h6>
			                                            </div>
			                                        </a>
			                                    </div>
			                                    <!-- Column -->
			                                    <div
										class="col-md-6 col-lg-3 col-xlg-3">
			                                        <a class="card card-hover"
											href="${contextPath}/user/UserTickets?page=1&filter=pending"
											method="GET">
			                                            <div
												class="box bg-dark text-center">
			                                                <h1
													class="font-light text-white">${pendingTickets}</h1>
			                                                <h6
													class="text-white">Pending</h6>
			                                            </div>
			                                        </a>
			                                    </div>
			                                    <!-- Column -->
			                                
			                                </div>
			                                
			                                <div class="text-right">
	                                        	<a
										href="${contextPath}/user/UserTickets?page=1&filter=all"
										class="btn btn-inverse">Get Support</a>
	                                        </div>
	                                    </div>
	                                </div>
                                </security:authorize>
                                
                                      
                                
                                
    </div>
   
    </div>

 
  </div>
  
 		    
 
 
  

</jsp:body>
</page:user-landing>
