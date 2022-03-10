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
		<div id="page-wrapper"  class="page-wrapper">
			<div class="features-tabs-section" style="margin-top: 100px;">
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
					
					<div>
						<a href="${contextPath}/admin/featureManagement">
							<button class="btn btn-lg btn-outline-primary"><i class="fa fa-arrow-left" aria-hidden="true"></i> Go Back</button>
						</a>
						
						<button type="button" class="btn btn-lg btn-outline-primary float-right" data-toggle="modal" data-target="#deleteFeatureModal">
						  <i class="fa fa-trash" aria-hidden="true"></i>
						</button>
					</div>
					
					
					<!-- Modal -->
						<div class="modal fade" id="deleteFeatureModal" tabindex="-1" role="dialog">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="deleteFeatureModalLabel">Delete Feature</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">×</span>
						        </button>
						      </div>
						      <div class="modal-body">
						     	 <div class="alert alert-danger" role="alert">
								  <strong>
								  <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
								  Warning!
								  </strong>
								</div>
						        <h4>
						        	Deleting the feature will disable all functionality associated with the feature along with all data saved for the feature.
						        	<p>Are you sure you want to delete the feature?</p>
						        </h4>
						      </div>
						      <div class="modal-footer">
						      	<c:if test="${not empty featureDetails}">
						      		<form class="form-horizontal m-t-20" id="confirmDeleteForm" action="#" method="POST">
						      			<c:forEach var="feature" items="${featureDetails}">
						      				<button type="button" class="btn btn-primary" id="${feature.ID_FEATURE}" onclick="confirmDelete(this);">Confirm Delete</button>
						      			</c:forEach>
						      		</form>	
						      	</c:if>
						      	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						      	
						      	<script>
							      	function confirmDelete(el) {
					 					document.getElementById("confirmDeleteForm").action="${contextPath}/admin/deleteFeature?id="+el.id+"&feature=";
									 	document.getElementById("confirmDeleteForm").submit();// Form submission
					 				}
						      	</script>
						      </div>
						    </div>
						  </div>
						</div>

					
					<div>
						<c:if test="${not empty featureDetails}">
							<form class="form-horizontal m-t-20" id="checkboxForm" action="#" method="POST">
								<c:forEach var="feature" items="${featureDetails}">
									<div class="card m-3">
				                            <div class="card-body bg-primary text-white">
				                                <h2 class="card-title">${feature.TX_FEATURE}</h2>
				                            </div>
				                            <div class="card-body text-white">
				                                <div class="row">
				                                	<div class="text-primary">
				                                		<br/>
				                                		<div class="col-md-12">
				                                			<div class="row">
						                                		<div class="col-md-4">
						                                			<label class="custom-toggle">
						                                			<c:if test="${true!=feature.FL_ACTIVE}">
						                                				<input type="checkbox" onclick="save_checkbox(this);" id="${feature.ID_FEATURE}" name="${feature.TX_FEATURE}_${feature.ID_FEATURE}" path="${feature.TX_FEATURE}_${feature.ID_FEATURE}" value="0" >
						                                			</c:if>
						                                			<c:if test="${true==feature.FL_ACTIVE}">
						                        						<input type="checkbox" onclick="save_checkbox(this);" id="${feature.ID_FEATURE}" name="${feature.TX_FEATURE}_${feature.ID_FEATURE}" path="${feature.TX_FEATURE}_${feature.ID_FEATURE}" value="1" checked >
						                                			</c:if>
						                                			 	<span class="custom-toggle-slider rounded-circle"></span>
														  			</label>
													  			</div>
													  			<div class="col-md-8">
																	 <c:if test="${true!=feature.FL_ACTIVE}">
						                                				<h4>Status: Inactive</h4>
						                                			</c:if>
						                                			<c:if test="${true==feature.FL_ACTIVE}">
						                                				<h4>Status: Active</h4>
			                               		        			</c:if>
																</div>
															</div>
				                                		</div>
				                                		
				                                		<div>
				                                			<h4>Section: ${feature.TX_SECTION}</h4>
				                                		</div>
				                                		
				                                	</div>
				                           	    </div>
				                            </div>
			                        </div>
								</c:forEach>
								
								 <script>
					 				function save_checkbox(el) {
					 					 document.getElementById("checkboxForm").action="${contextPath}/admin/checkboxUpdate?id="+el.id+"&feature="+el.name+"&value="+el.value+"&page=featureDetails";
									 	document.getElementById("checkboxForm").submit();// Form submission
					 				}
								</script>
								
							</form>
								
						</c:if>
						
					</div>
						<div>
							<h4>History</h4>
						</div>
						<c:if test="${empty featureHistory}">
							<span>No History found.</span>
						</c:if>
						<c:if test="${not empty featureHistory}">
						
						<table class="table table-hover">
						
							 <thead class="thead-default">
							    <tr>
							      <th>Date</th>
							      <th>Description</th>
							      <th>User</th>
							    </tr>
							  </thead>
							  <tbody>
						
							<c:forEach var="detail" items="${featureHistory}">
								<tr>
									<td>
										<span>
										${detail.TS_UPDATE}</span>
									</td>
									<td>
										<span>${detail.TX_UPDATE}</span>
									</td>
									<td>
										<span>${detail.TX_USER}</span>
									</td>
									
								</tr>	
							</c:forEach>
						</table>	
						</c:if>	
					<div>
					
					</div>	
				
				</div>
			</div>
		</div>

	</jsp:body>
</page:user-landing>