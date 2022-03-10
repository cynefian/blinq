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
								
									<div>
							            	<section>
							            		<div id="creadcrumbs" class="p-2 m-2">
							            			Settings >> ${mainTabselect} > ${subTabselect}
							            		</div>
												<div class="text-center">
												
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
													 <c:if test="${empty permissionGroups}">
														<span>No Groups have this permission</span>
													</c:if>
													
													<c:if test="${not empty permissionGroups}">
														<c:forEach var="group" items="${permissionGroups}" varStatus="loop">
															<div class="row">
																<div class="col-md-6 col-sm-6 text-left text-primary">
																	<p>${loop.index+1}. ${group.TX_GROUP_NAME}</p>
																</div>
																<div class="col-md-6 col-sm-6 text-right">
																	<a href="${contextPath}/admin/settings/users/viewGroup?id=${group.ID}" method="GET" class="font-bold link">View Group Info</a><br/>
																</div>
																<hr/>
															</div>
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