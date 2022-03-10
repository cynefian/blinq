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
	
	<div class="wrapper">
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
								            	<div class="row m-2">
								            		<div class="col-lg-6 col-sm-12">
								            			<div id="creadcrumbs" class="p-2 m-2">
									            			Settings >> ${mainTabselect} > ${subTabselect}
									            		</div>
								            		</div>
								            		
								            		
							            			<div class="col-lg-6 col-sm-12 p-4">
							            				<div class="text-right">
							            					<button class="btn btn-primary" data-toggle="modal" data-target="#newUserModal">Create New User</button>
							            				</div>
							            				
							            				
							            				<!-- New User Modal -->
						
															<div class="modal fade" id="newUserModal" tabindex="-1" role="dialog">
															  <div class="modal-dialog" role="document">
															    <div class="modal-content">
															    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/users/newUser" method="POST" name="UsersForm" modelAttribute="usersBean">
																      <div class="modal-header">
																        	<h4 class="modal-title" id="exampleModalLabel1">New User</h4>
										                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																      </div>
																      <div class="modal-body">
																     	  <div class="form-group">
																		  	  	<label for="TX_FIRSTNAME">First Name:</label>
																		    	<input type="text" class="form-control" id="TX_FIRSTNAME"name="TX_FIRSTNAME" placeholder="Firstname">
																		  </div>
																		   <div class="form-group">
																		  	  	<label for="TX_LASTNAME">Last Name:</label>
																		    	<input type="text" class="form-control" id="TX_LASTNAME" name="TX_LASTNAME" placeholder="Lastname">
																		  </div>
																		  <div class="form-group">
																		  	  	<label for="TX_EMAIL">Email:</label>
																		    	<input type="email" class="form-control" id="TX_EMAIL"  name="TX_EMAIL"placeholder="someone@somewhere.com">
																		  </div>
																		  
																		  <div class="form-group">
																		  	  	<label for="password">Password:</label>
																		    	<input type="password" class="form-control" id="password" name="password" placeholder="*****">
																		  </div>
																		  
																      </div>
																      <div class="modal-footer">
																      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										                            	 	<button type="submit" class="btn btn-primary" >Add User</button> 
																      </div>
															      </form>
															    </div>
															  </div>
															</div>
															
															
															
							            			</div>
								            		
								            	</div>
													
												<div>
												<div class="col-md-6 col-sm-6">
								            				<div class="text-left text-primary">
							            						<h4>Search</h4>
										            		</div>
								            			</div>
													<form action="${contextPath}/admin/settings/users/search" method="POST" id="userSearchForm" modelAttribute="UserSearchBean" name="UserSearchForm" >					            		
									            		<div class="row m-2">
							            						<div class="col-md-4 col-sm-12">
										            				 <div class="form-group">
																	    <label for="TX_USER">User</label>
																	    <input type="text" class="form-control" id="TX_USER" name="TX_USER" placeholder="User Name or Email" value="${searchQuery}">
																	  </div>
										            			</div>
										            		
										            			<div class="col-md-4 col-sm-12">
										            				 <div class="form-group">
																	    <label for="TX_GROUP">Group</label>
																	     <select class="form-control" id="ID_GROUP" NAME="ID_GROUP">
																		     <option value="">-</option>
																		       <c:forEach var="group" items="${groupList}">
																			     	 <option value="${group.ID_GROUP}"  ${group.ID_GROUP == groupId ? 'selected="selected"' : ''}>${group.TX_GROUP_NAME}</option>
																		         </c:forEach>
																				

																		    </select>
																	  </div>
											            		</div>
											            		
											            		<div class="col-md-2 col-sm-12">
												            		<label for="userSearch">.</label>
										            				 <button type="submit" class="form-control btn btn-primary" id="userSearch" name="userSearch">Search</button>
											            		</div>
											            		
											            		<c:if test="${not empty groupId}">
											            			<div class="col-md-2 col-sm-12">
													            		<label for="userSearch">Group ID: <span class="text-primary">${groupId}</span></label>
											            				 <button type="button" class="form-control btn btn-primary" id="addUserGroup" data-toggle="modal" data-target="#addUsertoGroupModal">Add User to Group</button>
												            		</div>
											            		</c:if>
											            		
											            		
									            		</div>
									            		<hr/>
								            		</form>
							            		</div>
							            		
							            		<!-- Add User to Group Modal -->
						
													<div class="modal fade" id="addUsertoGroupModal" tabindex="-1" role="dialog">
													  <div class="modal-dialog" role="document">
													    <div class="modal-content">
													    <form class="form-horizontal m-t-20" action="#" method="POST" id="addUserToGroupFom">
													      <div class="modal-header">
													        	<h4 class="modal-title" id="exampleModalLabel1">Add User to Group</h4>
							                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
													      </div>
													      <div class="modal-body">
													     	  <div class="form-group">
									                                <div>
									                                	<label for="machine-id" class="control-label">Group ID: ${groupId}</label>
									                                </div>
									                               
									                                <div class="form-group">
																	    <label for="TX_USER">User's Email ID:</label>
																	    <input type="text" class="form-control" id="TX_USER_SEARCH" name="TX_USER_SEARCH" placeholder="User Email">
																	  </div>
									                                
							                                	</div>
													      </div>
													      <div class="modal-footer">
													      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							                            	 	<button type="button" class="btn btn-success" onclick="addUserToGroup();">Add User</button> 
													      </div>
													      </form>
													      
													         <script>				
							        	function addUserToGroup(){
							        		var formElement = document.getElementById("addUserToGroupFom");
							        		var userFieldElement = document.getElementById("TX_USER_SEARCH");
							        		
							        		var userValue = userFieldElement.value;
							        		formElement.action = "${contextPath}/admin/settings/users/addUserToGroup?groupid="+${groupId}+"&user="+userValue;
							        		formElement.submit();
							        		
							        	}
							        
							        </script>			
							        
													    </div>
													  </div>
													</div>
							        
							     	            
							            				<div>
								            		        <c:if test="${empty groupUsers}">
																<span>No users found for this group.</span>
															</c:if>
															
															<c:if test="${not empty groupUsers}">
																
																<c:forEach var="user" items="${groupUsers}">
																	<div>
																		<div class="row m-4">
																			<div class="col-lg-1 col-sm-4">
																				<c:if test="${not empty user.TX_USER_IMAGE}">
																					<img src="${user.TX_USER_IMAGE}" width="31" class="rounded-circle" />
																				</c:if>
																				<c:if test="${empty user.TX_USER_IMAGE}">
																					<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" width="31" class="rounded-circle" />
																				</c:if>
																			</div>
																			<div class="col-lg-4 col-sm-8">
						                                        				<h5>${user.TX_FIRSTNAME} ${user.TX_LASTNAME}</h5>
																				<p class="blockquote-footer">${user.TX_EMAIL}</p>
																					<c:if test="${user.FL_ENABLED==true}">
																						<span class="badge badge-outline badge-pill badge-success">ACTIVE</span>
																					</c:if>
																					<c:if test="${user.FL_ENABLED==false}">
																						<span class="badge badge-outline badge-pill badge-light">INACTIVE</span>
																					</c:if>
						                                        			</div>
																		
																			<div class="col-lg-4 col-sm-12">
																				<c:if test="${empty user.USER_GROUPS}">
																					<p>User is not in any group.
																				</c:if>
																				
																				<c:if test="${not empty user.USER_GROUPS}">
																					<c:forEach var="group" items="${user.USER_GROUPS}" varStatus="loop">
																						<c:if test="${loop.index<=2}">
																							<p><a href="${contextPath}/admin/settings/users/viewGroup?id=${group.ID_GROUP}">${group.TX_GROUP_NAME} </a></p>
																						</c:if>
																						<c:if test="${loop.index==3}">
																							<p>...</p>
																						</c:if>
																						<c:if test="${loop.index>3}">
																						
																						</c:if>
																					</c:forEach>
																				</c:if>
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
				
				 <script>
					function save_checkbox(el) {
							if(el.value==1){
								document.getElementById("FL_STATUS_"+el.id).value="true";
							}else{
								document.getElementById("FL_STATUS_"+el.id).value="false";
							}
							document.getElementById("checkboxForm_"+el.id).action = "${contextPath}/admin/settings/users/updateGroupPermission";
							document.getElementById("checkboxForm_"+el.id).submit();// Form submission
						}
					
					
					function deleteUserFromGroup(el) {

						
						var uid = el.id.replace("USER_","");
						var gid = el.name.replace("GROUP_","");
						var element = document.getElementById("deleteUserFromGroupForm_"+uid);
						element.action = "${contextPath}/action/settings/users/deleteUserFromGroup?userid="+uid+"&groupid="+gid;
						element.submit();// Form submission
					}
					
					
					</script>
						
		          
		        </div>
		      </div>
		    </div>
		
	</jsp:body>
</page:user-landing>