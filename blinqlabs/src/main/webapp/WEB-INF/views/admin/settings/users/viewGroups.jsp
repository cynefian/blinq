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
		
		
		
		<div id="page-wrapper">
		
			<div class="ml-3 page-canvas" >
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
			</div>
		</div>
		
		
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
									<jsp:include page="/WEB-INF/views/admin/settings/navs/usersSideNav.jsp" />
								</div>
								<div class="col-lg-10 border-left">
								
									<div>
							            	<section>
							            		<div class="row mt-2">
							            			<div class="col-lg-6 col-sm-12">
									            		<div id="creadcrumbs" class="p-2 m-2">
									            			Settings >> ${mainTabselect} > ${subTabselect}
									            		</div>	
							            			</div>
							            			<div class="col-lg-6 col-sm-12 p-4">
							            				<div class="text-right">
							            					<button class="btn btn-primary" data-toggle="modal" data-target="#newGroupModal">Create New Group</button>
							            				</div>
							            				
							            				<!-- New Group Modal -->
						
															<div class="modal fade" id="newGroupModal" tabindex="-1" role="dialog">
															  <div class="modal-dialog" role="document">
															    <div class="modal-content">
															    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/groups/newGroup" method="POST" name="GroupPermissionsForm" modelAttribute="groupPermissionsBean">
															      <div class="modal-header">
															        	<h4 class="modal-title" id="exampleModalLabel1">New Group</h4>
									                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
															      </div>
															      <div class="modal-body">
															     	  <div class="form-group">
											                               
											                                <div>
											                                	<label for="TX_GROUP_NAME" class="control-label">Name of new Group: </label>
											                                	<input type="text" id="TX_GROUP_NAME" name="TX_GROUP_NAME" path="TX_GROUP_NAME"/>
											                                	
											                                </div>
											                             </div>
															      </div>
															      <div class="modal-footer">
															      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									                            	 	<button type="submit" class="btn btn-primary" >Add</button> 
															      </div>
															      </form>
															    </div>
															  </div>
															</div>
							            			</div>
							            		</div>
							            		
												<div class="text-center">
												
													 <c:if test="${empty groupList}">
														<span>No Groups defined.</span>
													</c:if>
													
													<c:if test="${not empty groupList}">
														<c:forEach var="group" items="${groupList}" varStatus="loop">
															<div class="row">
																<div class="col-md-4 col-sm-4 text-left text-primary">
																	<p>${loop.index+1}. ${group.TX_GROUP_NAME}</p>
																</div>
																<div class="col-md-4 col-sm-4 text-left text-primary">
																	<p>${group.GROUP_USER_COUNT}</p>
																</div>
																<div class="col-md-4 col-sm-4">
																	<div class="text-align-center col-md-6 offset-6 inner">
																		<div>
																			<a href="${contextPath}/admin/settings/users/viewGroup?id=${group.ID_GROUP}" method="GET" class="font-bold link"><i class="fas fa-eye"></i></a><br/>
																		</div>
																		<c:if test="${group.GROUP_USER_COUNT == 0}">
																			<div>
																				<a href="${contextPath}/admin/settings/users/deleteGroup?id=${group.ID_GROUP}" method="GET" class="font-bold link text-danger"><i class="far fa-trash-alt"></i></i></a><br/>
																			</div>
																		</c:if>
																	</div>
																</div>
															</div>
															<hr/>
														</c:forEach>
													</c:if>
												</div>
											</section>
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
		  
		
		</div>
		
	</jsp:body>
</page:user-landing>