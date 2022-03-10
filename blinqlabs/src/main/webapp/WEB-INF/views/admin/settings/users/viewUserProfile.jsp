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
	
	<div class="container">
	 <c:if test="${not empty failuremessage}">
					 	<script>
					 	 	$(window).on("load", function() {
						    	toastr.error('${failuremessage}', 'Feature', { "progressBar": true });
						    });
					  	</script>
					</c:if>
					
					<c:if test="${not empty successmessage}">
					 	<script>
					 	 	$(window).on("load", function() {
						    	toastr.success('${successmessage}', 'Feature', { "progressBar": true });
						    });
					  	</script>
					</c:if>
	</div>
					
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
									<jsp:include page="/WEB-INF/views/admin/settings/navs/usersSideNav.jsp" />
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
													
												
							            				<div>
								            		        <c:if test="${empty userInfo}">
																<span>Error Retrieving User Info.</span>
															</c:if>
															
															<c:if test="${not empty userInfo}">
																
																<c:forEach var="user" items="${userInfo}">
																
																	<div class="card bg-lights">
	 																	<div class="card-body">
																			<div class="row">
																				<div class="col-lg-7 col-md-7 col-sm-12">
																					<div class="row">
																					
																						<div class="col-lg-4 col-sm-4">
																							<c:if test="${not empty user.TX_USER_IMAGE}">
																                            	<img src="${user.TX_USER_IMAGE}" alt="user" class="rounded-circle" width="200">
																                            </c:if>
																                            <c:if test="${empty user.TX_USER_IMAGE}">
																                            	<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="200">
																                            </c:if>
																						</div>
																						<div class="col-lg-8 col-sm-8 pl-5">
																							<div class="text-primary">
																								<h1>${user.TX_FIRSTNAME} ${user.TX_LASTNAME}</h1>
																							</div>
																							<div class="offset-2">
																								<h3 class="lead">${user.TX_EMAIL}</h3>
																							</div>
																							
																							<div class="offset-2">
																								<p>Bio: <em>${user.TX_BIO}</em></p>	
																							</div>
																							
																						</div>
																					</div>
																				
																				</div>
																				<div class="col-lg-5 col-md-5 col-sm-12 text-right">
																					<div>
																						<p>Website: <em><a href="${user.TX_WEBSITE}" target="_blank">${user.TX_WEBSITE}</a></em></p>	
																					</div>
																					<div >
																						<p>Contact: <em>${user.TX_CONTACT_NUM}</em></p>	
																					</div>
																				</div>
																				
																			</div>
																			<div class="row m-2">
																				<div class="col-lg-2 col-sm-12">
																					<c:if test="${user.FL_ENABLED==true}">
																						<span class="badge badge-outline badge-pill badge-success">ACTIVE</span>
																					</c:if>
																					<c:if test="${user.FL_ENABLED==false}">
																						<span class="badge badge-outline badge-pill badge-light">INACTIVE</span>
																					</c:if>
																				</div>
																				<div class="col-lg-10 col-sm-12 text-right">
																					<div >
																					  <c:if test="${user.FL_ENABLED==false}">
																					  		<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#enableProfileModal"><i class="fas fa-user-lock"></i>Enable User Account</button>
																					  </c:if>
																					  <c:if test="${user.FL_ENABLED==true}">
																					  		<button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#disableProfileModal"><i class="fas fa-user-lock"></i>Disable User Account</button>
																					  </c:if>
																					  
																					  <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#credentialModal"><i class="fas fa-user-edit"></i>Update User Credentials</button>
																					  <!--
																					  	 <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#deleteUserModal"><i class="fas fa-user-slash"></i>Delete User Account</button> 
																					  	-->
																					</div>
																					
																					<!-- New User Modal -->
																						<div class="modal fade" id="credentialModal" tabindex="-1" role="dialog">
																						  <div class="modal-dialog" role="document">
																						    <div class="modal-content">
																						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/users/updateCredentials?user=${user.TX_EMAIL}&id=${user.ID_USER}" method="POST" name="UsersForm" modelAttribute="usersBean">
																							      <div class="modal-header">
																							        	<h4 class="modal-title" id="exampleModalLabel1">Update Credentials</h4>
																	                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																							      </div>
																							      <div class="modal-body">
																							     	  <div class="form-group text-left">
																									  	  	<label for="password">New Password:</label>
																									    	<input type="password" class="form-control" id="password" name="password" placeholder="*****">
																									  </div>
																									  
																							      </div>
																							      <div class="modal-footer">
																							      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																	                            	 	<button type="submit" class="btn btn-primary" >Update</button> 
																							      </div>
																						      </form>
																						    </div>
																						  </div>
																						</div>
																						
																						<!-- Enable User Modal -->
																						<div class="modal fade" id="enableProfileModal" tabindex="-1" role="dialog">
																						  <div class="modal-dialog" role="document">
																						    <div class="modal-content">
																						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/users/enableProfile?status=1&user=${user.TX_EMAIL}&id=${user.ID_USER}" method="POST" name="UsersForm" modelAttribute="usersBean">
																							      <div class="modal-header">
																							        	<h4 class="modal-title" id="exampleModalLabel1">Enable Profile</h4>
																	                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																							      </div>
																							      <div class="modal-body">
																							     	  <div class="form-group">
																									  	  	<label for="password">You are attempting to enable the user profile</label>
																									  </div>
																									  
																							      </div>
																							      <div class="modal-footer">
																							      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																	                            	 	<button type="submit" class="btn btn-primary" >Confirm</button> 
																							      </div>
																						      </form>
																						    </div>
																						  </div>
																						</div>
																						
																						
																						<!-- Disable User Modal -->
																						<div class="modal fade" id="disableProfileModal" tabindex="-1" role="dialog">
																						  <div class="modal-dialog" role="document">
																						    <div class="modal-content">
																						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/users/disableProfile?status=0&user=${user.TX_EMAIL}&id=${user.ID_USER}" method="POST" name="UsersForm" modelAttribute="usersBean">
																							      <div class="modal-header">
																							        	<h4 class="modal-title" id="exampleModalLabel1">Disable Profile</h4>
																	                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																							      </div>
																							      <div class="modal-body">
																							     	  <div class="form-group">
																									  	  	<label for="password">You are attempting to disable the user profile</label>
																									  </div>
																									  
																							      </div>
																							      <div class="modal-footer">
																							      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																	                            	 	<button type="submit" class="btn btn-primary" >Confirm</button> 
																							      </div>
																						      </form>
																						    </div>
																						  </div>
																						</div>
																						
																						<!-- Delete User Modal -->
																						<div class="modal fade" id="deleteUserModal" tabindex="-1" role="dialog">
																						  <div class="modal-dialog" role="document">
																						    <div class="modal-content">
																						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/users/deleteUser?user=${user.TX_EMAIL}&id=${user.ID_USER}" method="POST">
																							      <div class="modal-header">
																							        	<h4 class="modal-title" id="exampleModalLabel1">Delete User</h4>
																	                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																							      </div>
																							      <div class="modal-body">
																							     	  <div class="form-group text-left">
																							     	  	<span class="text-danger"><h2><i class="fas fa-exclamation-triangle"></i>Warning!</h2></span>
																							     	  	
																							     	  	<h6>This action cannot be undone.</h6>
																									  	  	<label for="password">Are you sure you want to delete this user account?</label>
																									  </div>
																									  
																							      </div>
																							      <div class="modal-footer">
																							      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																	                            	 	<button type="submit" class="btn btn-danger" ><i class="fas fa-trash-alt"></i> Delete</button> 
																							      </div>
																						      </form>
																						    </div>
																						  </div>
																						</div>
																						
																					
																					
																				</div>
																			
																			</div>
																		</div>
																	</div>
																	
																	<hr/>
																	
																	<div class="row">
																		<div class="col-lg-6 col-sm-6">
																			<h2>Profile Info</h2>
																		</div>
																		<div class="col-lg-6 col-sm-6 text-right">
																		
																		</div>
																	</div>
																	
																	<div id="groups-div" class="card border-primary mb-3">
																	 	<div class="card-body">
												            		        <c:if test="${empty user.USER_GROUPS}">
																				<span>Group Info. not available</span>
																			</c:if>
																			
																			<c:if test="${not empty user.USER_GROUPS}">
																				<div class="row">
																					<div class="col-md-6 col-sm-6">
																						<h3 class="text-primary">Groups</h3>
																					</div>
																					
																					
																					<!-- Add USer to Group Modal -->
						
																						<div class="modal fade" id="addUserToGroupModal" tabindex="-1" role="dialog">
																						  <div class="modal-dialog" role="document">
																						    <div class="modal-content">
																						    <form class="form-horizontal m-t-20" action="#" method="POST" id="addGroupForm">
																						      <div class="modal-header">
																						        	<h4 class="modal-title" id="exampleModalLabel1">Select group to add the user</h4>
																                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																						      </div>
																						      <div class="modal-body">
																						     	  <div class="form-group">
																		                               <div class="form-group">
																										    <label for="groupSelection">Groups:</label>
																										    <select class="form-control" id="groupSelection">
																										      <option value="-">-</option>
																										      <c:if test="${not empty groupsList}">
																										      	<c:forEach var="group" items="${groupsList}">
																										      		<c:if test="${not empty group}">
																										      		 	<option value="${group.ID_GROUP}">${group.TX_GROUP_NAME}</option>
																										      		 </c:if>
																										      	</c:forEach>
																										      </c:if>
																										    </select>
																										  </div>
																		                                
																                                	</div>
																						      </div>
																						      <div class="modal-footer">
																						      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																                            	 	<button type="button" class="btn btn-primary" onclick="addUserToGroup();">ADD</button> 
																						      </div>
																						      </form>
																						    </div>
																						  </div>
																						</div>
																						
																						<script>
																							function addUserToGroup(){
																								var selectedGroup = document.getElementById("groupSelection").value;
																								if(selectedGroup!=null && selectedGroup!="-"){
																									document.getElementById("addGroupForm").action = "${contextPath}/admin/settings/users/addUserToGroupById?user=${user.ID_USER}&groupid="+selectedGroup+"&source=USER_PROFILE";
																									document.getElementById("addGroupForm").submit();
																								}
																							}
																							
																						</script>
																				
																					<div class="col-md-6 col-sm-6 text-right">
																						<button class="btn btn-primary" data-toggle="modal" data-target="#addUserToGroupModal">Add User to Group</button>	
																					</div>
																				</div>
																				<h5>${user.TX_FIRSTNAME} ${user.TX_LASTNAME} is part of the following groups: </h5>
																				 <c:if test="${not empty user.USER_GROUPS}">
																				 	<c:forEach var="group" items="${user.USER_GROUPS}">
																						<div class="m-2 p-3">
																							<div class="row">
																								<div class="col-md-6 col-sm-12">
																									<a href="${contextPath}/admin/settings/users/viewGroup?id=${group.ID_GROUP}">${group.TX_GROUP_NAME}</a>
																								</div>
																								<div class="col-md-6 col-sm-12 text-right">
																									<a href="${contextPath}/action/settings/users/deleteUserFromGroup?userid=${user.ID_USER}&groupid=${group.ID_GROUP}"><button class="btn btn-outline-danger"><i class="fas fa-trash-alt"></i></button></a>
																								</div>
																							</div>
																										
																						</div> 
																						<hr/>
																					</c:forEach>
																				 </c:if>
																				 
																				 <c:if test="${empty user.USER_GROUPS}">
																				 	<div class="m-2 p-3">
																				 		<span class="font-weight-normal">This user is not part of any group.</span>
																				 	</div>
																				 </c:if>
																				
																			
																			</c:if>
																		</div>
																	</div>
																	
																</c:forEach>
																
																
																
																
																
																<div id="permissions-div" class="card border-primary mb-3 ">
																	<div class="card-body">
																		<h3 class="text-primary">Permissions</h3>	
																		<section class="m-4 p-4">
																			<div>
																				<div class="row">
																					<div class="col-lg-8 col-sm-12">
																						<h4 class="text-primary">User Level Permissions</h4>		
																					</div>
																					<div class="col-lg-4 col-sm-12 text-right">
																						<button class="btn btn-primary" data-toggle="modal" data-target="#addUserPermissionsModal">Add Permission</button>	
																					</div>
																					
																					<!-- Add USer to Group Modal -->
						
																						<div class="modal fade" id="addUserPermissionsModal" tabindex="-1" role="dialog">
																						  <div class="modal-dialog" role="document">
																						    <div class="modal-content">
																						    <form class="form-horizontal m-t-20" action="#" method="POST" id="UserLevelPermissionsForm">
																						      <div class="modal-header">
																						        	<h4 class="modal-title" id="exampleModalLabel1">Select User Level Permissions</h4>
																                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																						      </div>
																						      <div class="modal-body">
																						     	  <div class="form-group">
																						     	  
																			                    		<div class="form-group">
																										    <label for="permissionSelection">Permission:</label>
																										    <select class="form-control" id="permissionSelection">
																										      <option value="-">-</option>
																										      <c:if test="${not empty permissionsList}">
																										      	<c:forEach var="permission" items="${permissionsList}">
																										      		<c:if test="${not empty permission}">
																										      		 	<option value="${permission.ID}">${permission.TX_PERMISSION}</option>
																										      		 </c:if>
																										      	</c:forEach>
																										      </c:if>
																										    </select>
																										  </div>       
																		                            </div>
																						      </div>
																						      <div class="modal-footer">
																						      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																                            	 	<button type="button" class="btn btn-primary" onclick="saveUserLevelPermissions();">ADD</button> 
																						      </div>
																						      </form>
																						    </div>
																						  </div>
																						</div>
																						
																						<script>
																								function saveUserLevelPermissions(){
																									var selectedPerm = document.getElementById("permissionSelection").value;
																									if(selectedPerm!=null && selectedPerm!="-"){
																										document.getElementById("UserLevelPermissionsForm").action = "${contextPath}/admin/settings/users/addUserLvlPermission?userid=${userInfo[0].ID_USER}&permid="+selectedPerm;
																										document.getElementById("UserLevelPermissionsForm").submit();
																									}
																								}
																							
																							
																						</script>
																				</div>
																				
																				<c:if test="${empty userLvlPermission}">
																					<span>User Level Permission Info. not available</span>
																				</c:if>
																				
																				<c:if test="${not empty userLvlPermission}">
																					<c:forEach var="ulp" items="${userLvlPermission}">
																						<div class="row">
																							<div class="col-lg-8 col-sm-12">
																								<p>${ulp.TX_PERMISSION}</p>
																							</div>
																							<div class="col-lg-4 col-sm-12">
																									<form action="#" id="checkboxForm_${ulp.ID_PERMISSION}" method="POST" >
												                                        						<input type="text" id="ID_USER_${ulp.ID_PERMISSION}" name="ID_USER" value="${user.ID_USER}">
												                                        						<input type="text" id="ID_PERMISSION_${ulp.ID_PERMISSION}" name="ID_PERMISSION" value="${ulp.ID_PERMISSION}">
												                                        						
												                                        						
												                                        						<script>
													                                        						document.getElementById("ID_USER_${ulp.ID_PERMISSION}").style.visibility = "hidden";
												                                        			            	document.getElementById("ID_PERMISSION_${ulp.ID_PERMISSION}").style.visibility = "hidden";
												                                        			            </script>
													                                							<label class="custom-toggle">
																								  				
																								  					<input type="checkbox"
																																onclick="save_checkbox(this);" id="${ulp.ID_PERMISSION}"
																																name="${user.ID_USER}" 
																																value="1"
																																checked>
																								  				
																								  				
																									  			 	<span class="custom-toggle-slider rounded-circle"></span>
																									  			</label>
																								  			</form>
																								  		
																							</div>
																							
																						</div>
																						<hr/>
																					</c:forEach>
																						<script>
																												function save_checkbox(el) {
																														document.getElementById("checkboxForm_"+el.id).action = "${contextPath}/admin/settings/users/deleteUserLvlPermission?userid=${userInfo[0].ID_USER}&permid="+el.id;
																														document.getElementById("checkboxForm_"+el.id).submit();// Form submission
																													}
																											</script>
																				</c:if>
																			</div>
																		</section>
																		<hr/>
																		<section class="m-4 p-4">
																			<div>
																				<h4 class="text-primary">Group Level Permissions</h4>		
																				<div class="card bg-light text-dark text-center mb-3">
																				  <div class="card-body">
																				    <h5 class="card-title"><i class="fas fa-exclamation-circle"></i> 
																				    		These Permissions are inherited from groups.</h5>
																				    <p class="card-text">
																				    	<h6>
																				    		In order to modify these permissions, you need to remove the user from the corresponding group and assign the permission at the User Level.
																				    	</h6>
																				    </p>
																				  </div>
																				</div>
																				<c:if test="${empty groupLvlPermission}">
																					<span>Group Level Permission Info. not available</span>
																				</c:if>
																				
																				<c:if test="${not empty groupLvlPermission}">
																					
																					<table class="table table-hover">
																						  <thead class="thead-default">
																						    <tr>
																						      <th>Permission</th>
																						      <th>Inherited Groups</th>
																						      <th>Status</th>
																						    </tr>
																						  </thead>
																						  	<tbody>
																					
																								<c:forEach var="perm" items="${groupLvlPermission}">
																									<tr>
																								      <td><h6 class="text-secondary">${perm.TX_PERMISSION}</h6></td>
																								      <td>
																								      		<c:forEach var="permgroup" items="${perm.LIST_GROUP}">
																													<div>
																														<p><a href="${contextPath}/admin/settings/users/viewGroupByName?name=${permgroup.TX_GROUP_NAME}">${permgroup.TX_GROUP_NAME}</a></p>
																													</div>
																												</c:forEach>
																								      </td>
																								      <td>
																								      		<label class="custom-toggle">
																													<input type="checkbox" id="${perm.ID_PERMISSION}"
																																				value="1"
																																				checked disabled>
																													<span class="custom-toggle-slider rounded-circle"></span>
																												</label>
																								      </td>
																								</c:forEach>
																							</tbody>
																					</table>
																				</c:if>
																				
																				
																				
																			</div>
																		</section>
										            		      	</div>  
																</div>
																
																
																
																
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