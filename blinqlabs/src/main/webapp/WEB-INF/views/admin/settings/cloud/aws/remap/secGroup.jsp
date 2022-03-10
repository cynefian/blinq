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
		       
		       <c:set var="vpc" value="" />
				<div>
		        <div class="tab-content" id="main-tab-content">
		          <div class="tab-pane fade show active" id="setting-content">
		          		<div class="basket">
	
							<div class="row">
								<div class="col-lg-2" id="settings-tab-index">
									<jsp:include page="/WEB-INF/views/admin/settings/navs/cloudSideNav.jsp" />
								</div>
								<div class="col-lg-10 border-left">
								
									<div>
							            	<section>
							            		<div id="creadcrumbs" class="p-2 m-2">
							            			Settings >> ${mainTabselect} > ${subTabselect}
							            		</div>
												<div class="text-center text-primary">
													<h3>Re-map Security Groups </h3>
													<c:forEach var="sg" items="${secGroups}">
														<c:if test="${sg.ID_COMPONENT==sgComponent.ID_COMPONENT}">
															<h5>The subnet <span class="text-secondary">(${sg.TX_COMPONENT_KEY})</span> appears in the following rules. Please choose new secGroups to remap the existing rules.</h5>														
															 <c:set var="vpc" value="${sg.ID_PARENT}"/>
														</c:if>
													</c:forEach>

												</div>
											</section>
							          </div>
							          
							          <div class="m-2 p-4 border-primary">
							          		
							          </div>
							          
							          <div class="m-2">
											<a href="${contextPath}/admin/settings/cloud/aws"><button type="button" class="btn btn-info"><i class="fas fa-arrow-left"></i></button></a>
									  </div>
									  
									  <div class="m-2">
										  <div class="row">
										  		<table class="table">
											  <thead class="thead-default">
											    <tr>
											    	<th>#</th>
											      	<th>Component</th>
											      	<th>VPC</th>
											       	<th>Security Group</th>
											    	</tr>
											  </thead>
											  <tbody>
											  		<c:forEach var="rule" items="${sgRulesList}" varStatus="loop">
													  	<tr>
												 			<td>${loop.index+1}</td>
													 		<td>${rule.TX_DEVOPS_TOOL}</td>
													 		<td>${rule.TX_VPC}</td>
													        <td>${rule.TX_SUBNET}</td>
												        </tr>	
											  		</c:forEach>
											   </tbody>
											</table>
										  </div>
									  </div>
									  <hr/>
									  <form class="form-horizontal m-t-20" id="remapRuleForm" action="${contextPath}/admin/settings/cloud/aws/remapSecGroup" method="POST" name="RulesForm" modelAttribute="rulesBean">
									  	<div>
									  		<h5>Choose the Security Group to re-map the above rules: <span class="text-danger"> *</span></h5>
									  		<select class="form-control"  id="ID_SUBNET" name="ID_SUBNET">
			                                	<option value="">-</option>
		                                	
			                                	<c:forEach var="sg" items="${secGroups}">
			                                		<c:if test="${(sg.ID_PARENT==vpc) and (sg.ID_COMPONENT!=sgComponent.ID_COMPONENT)}">
			                                			<option value="${sg.ID_COMPONENT}">${sg.TX_COMPONENT_NAME} - ${sg.TX_COMPONENT_KEY}</option>
			                                		</c:if>
			                                	</c:forEach>
									  		</select>
									  		<div>
									  			<input type="text" id="ID" name="ID" value="${sgComponent.ID_COMPONENT}" style="visibility:hidden">
									  		</div>
									  	</div>
									  	<hr/>
									    <div class="text-right">
										  		<a href="${contextPath}/admin/settings/cloud/aws"><button type="button" class="btn btn-secondary">Cancel</button></a>
										  		<button type="submit" class="btn btn-primary">Submit</button>
										</div>
									</form>
								
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