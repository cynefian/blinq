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
																		            		
							            		<div class="row m-2">
								            		<c:if test="${not empty groupPerms}">
								            		
								            			<div class="col-md-6 col-sm-6">
								            				<div class="text-left text-primary">
							            						<h4>${groupPerms[0].TX_GROUP_NAME}</h4>
										            		</div>
								            			</div>
								            		
								            			<div class="col-md-6 col-sm-6">
								            				<div class="text-right">
										            			<div class="btn-group" role="group" aria-label="Group Options">
										            			<a href="${contextPath}/admin/settings/users/viewGroup?id=${groupPerms[0].ID_GROUP}">
													            			<button type="button" class="btn btn-outline-primary active">
													            			 <i class="fas fa-toggle-on"></i>
																				    Permissions
																			</button>
																	 	 </a>
																	  
																		  <a href="${contextPath}/admin/settings/users/viewGroupUsers?groupid=${groupPerms[0].ID_GROUP}">
																			  <button type="button" class="btn btn-outline-primary">
																			   	 <i class="fas fa-users"></i>
																			    	Users
																			  </button>
																		  </a>
																</div>
															</div>
									            		</div>
							            			</c:if>
							            		</div>
							            		
							            		
							        
							            		
							            		
										            
							            				<div>
								            		        <c:if test="${empty permissionSection}">
																<span>Something's wrong. Please check your configurations.</span>
															</c:if>
															
															<c:if test="${not empty permissionSection}">
																
																<c:forEach var="section" items="${permissionSection}">
																	<div>
																		<section>
																				 <div>
																					 <table class="table">
																						 <tbody>
																					    	<tr class="table-info">
								                    											<td>${section.TX_PERM_SECTION}</td>
						                    												</tr>
						                    											</tbody>
						                    										</table>
																				</div>
																				
																				 <c:if test="${empty groupPerms}">
																					<span>Something's wrong. Please check your configurations.</span>
																				</c:if>
																				
																				<c:if test="${not empty groupPerms}">
																				     
										                                        	<c:forEach var="entry" items="${groupPerms}">
										                                        		<c:if test="${entry.TX_PERM_SECTION==section.TX_PERM_SECTION}">
										                                        		
										                                        			<div class="row m-4">
										                                        			
											                                        			<div class="col-md-6 col-sm-6">
											                                        				<h5>${entry.TX_PERMISSION}</h5>
																									<p class="blockquote-footer">${entry.TX_DESCRIPTION}</p>
											                                        			</div>
							
											                                        			<div class="col-md-6 col-sm-6 text-right">
											                                        			
											                                        				<div>
											                                        					<form action="#" id="checkboxForm_${entry.ID_PERMISSION}" modelAttribute="GroupPermissionsBean" method="POST" name="GroupPermissionsForm" >
											                                        						<input type="text" id="ID_GROUP_${entry.ID_PERMISSION}" name="ID_GROUP" value="${entry.ID_GROUP}" style="visibility:hidden">
											                                        						<input type="text" id="ID_PERMISSION_${entry.ID_PERMISSION}" name="ID_PERMISSION" value="${entry.ID_PERMISSION}" style="visibility:hidden">
											                                        						<input type="text" id="FL_STATUS_${entry.ID_PERMISSION}" name="FL_STATUS" value="" style="visibility:hidden">
											                                        					
												                                							<label class="custom-toggle">
																							  				<c:if test="${true==entry.FL_STATUS}">
																							  					<input type="checkbox"
																															onclick="save_checkbox(this);" id="${entry.ID_PERMISSION}"
																															name="${entry.ID_GROUP}" 
																															value="1"
																															checked>
																							  				</c:if>
																							  				<c:if test="${true!=entry.FL_STATUS}">
																							  					<input type="checkbox"
																															onclick="save_checkbox(this);" id="${entry.ID_PERMISSION}"
																															name="${entry.ID_GROUP}" 
																															value="0">
																							  				</c:if>
																							  				
																								  			 	<span class="custom-toggle-slider rounded-circle"></span>
																								  			</label>
																							  			</form>
																						  			</div>
																						  	   </div>
																						  	  <hr/>
											                                        		</div>
												                                         </c:if>
									                                            	</c:forEach>
										                                      </c:if> 
																		</section>
																	</div>
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
					</script>
						
		          
		        </div>
		      </div>
		    </div>
		
	</jsp:body>
</page:user-landing>