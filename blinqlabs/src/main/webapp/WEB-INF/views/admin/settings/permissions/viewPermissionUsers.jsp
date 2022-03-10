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
	
		<c:set var="mainTabselect" value="${mainTab}" />
		<c:set var="subTabselect" value="${subTab}" />
		
		<div class="m-10">
			<div class="features-tabs-section" style="margin-top: 100px;">
		      <div class="header">
		        <h3>System Settings</h3>
		      </div>
		
		      <div class="tabs-wrapper" id="main-tab-index">
		       		<jsp:include page="/WEB-INF/views/admin/settings/navs/top-nav.jsp" />
		       </div>
		       
		       
				<div>
		        <div class="tab-content" id="main-tab-content">
		          <div class="tab-pane fade show active" id="setting-content">
		          		<div class="basket">
	
							<div class="row">
								<div class="col-lg-2" id="settings-tab-index">
									<jsp:include page="/WEB-INF/views/admin/settings/navs/systemSideNav.jsp" />
								</div>
								<div class="col-lg-10 border-left">
								
									<div class="row">
									
										<div class="col-md-12 p-2">
							            	<div>
								            	<div>
								            		<div id="creadcrumbs" class="p-2 m-2">
								            			Settings >> ${mainTabselect} > ${subTabselect}
								            		</div>
								            	</div>
													
													<div class="row m-4">
													
														<div class=" col-lg-6 col-sm-6 text-left text-primary">
															<h4>${permissionInfo[0].TX_PERMISSION}</h4>
														</div>
													
														<div class="col-lg-6 col-sm-6 text-right">
															 <a href="${contextPath}/admin/settings/permissions" method="GET">
										                		<button type="submit" class="btn btn-primary"><i class="fas fa-arrow-circle-left" style="font-size:24px"></i> All Permissions</button>
										                	</a>
														</div>
														
													</div>
													<hr/>
												
							            		
							            		<!-- Add User to Group Modal -->
						
														<div>
								            		        <c:if test="${empty permissionUsers}">
																<span>No users found for this group.</span>
															</c:if>
															
															<c:if test="${not empty permissionUsers}">
																
																<c:forEach var="user" items="${permissionUsers}">
																	<div>
																		<div class="row m-4">
																			<div class="col-lg-1 col-sm-4">
																				<c:if test="${not empty user.TX_IMAGE}">
																					<img src="${user.TX_IMAGE}" width="31" class="rounded-circle" />
																				</c:if>
																				<c:if test="${empty user.TX_IMAGE}">
																					<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" width="31" class="rounded-circle" />
																				</c:if>
																			</div>
																			<div class="col-lg-4 col-sm-8">
						                                        				<h5>${user.TX_FIRSTNAME} ${user.TX_LASTNAME}</h5>
																				<p class="blockquote-footer">${user.TX_EMAIL}</p>
						                                        			</div>
																		
																			<div class="col-lg-4 col-sm-12">
																				<div id="PERMISSION_SETTING_${user.ID_USER}">
																					<c:if test="${not empty user.TYPES}">
																						<c:forEach var="type" items="${user.TYPES}">
																							<span class="badge badge-secondary">${type}</span>
																						</c:forEach>
																					</c:if>
																					<c:if test="${empty user.TYPES}">
																						<p> Unable to identify</p>
																					</c:if>
																				</div>
																			</div>
																			<div class="col-lg-1 col-sm-6 text-right">
																				<c:if test="${not empty groupId}">
																					<form action="#" method="POST" id="deleteUserFromGroupForm_${user.ID_USER}" >
																						<button type="button" class="form-control btn btn-outline-danger" name="GROUP_${groupId}" id="USER_${user.ID_USER}" onclick="deleteUserFromGroup(this);">
																							<i class="fas fa-trash-alt"></i>
																						</button>						
																					</form>											
																				</c:if>
																			</div>
																			<div class="col-lg-2 col-sm-6 text-right">
																				<p><a href="${contextPath}/admin/settings/users/viewUserProfile?userid=${user.ID_USER}">View User Profile</a></p>
																			</div>
																		</div>
																	</div>
																	<hr/>
																</c:forEach>
																
																
																
															</c:if>
														</div>
													          
										          	</div>
										          
										         </div>
										     
										        
										       
										       </div>
											</div>
							       
								
								</div>
							
							</div>
						
						</div>
		          </div>
		          <script>
		         	//$(window).on("load", function() {
					$(document).ready(function() {
						
						 $('#main-tab-index a[id="'+"${mainTabselect}"+'Link"]').addClass("active");
						 $('#settings-tab-index ul li a[id="setting'+"${subTabselect}"+'Link"]').addClass("active");
					}); 
					
					
				</script>
				
			    </div>
		      </div>
		    </div>
		
	</jsp:body>
</page:user-landing>