<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<page:user-landing>
	<jsp:body>
		<div id="page-wrapper">
			<div class="features-tabs-section" style="margin-top: 100px;">
    				<div class="header">
		        <h3>Service Admininstration</h3>
		      </div>
		
		      <div class="tabs-wrapper" id="main-tab-index">
		        <ul class="nav nav-tabs justify-content-center"
						id="feature-tabs">
		          <li>
		            <a href="#Types" data-toggle="tab" class="active">Service Types</a>
		          </li>
		          <li>
		            <a href="#Pricing" data-toggle="tab">Pricing</a>
		          </li>
		        </ul>
		
		        <div class="tab-content" id="main-tab-content">
		          <div class="tab-pane fade show active" id="Types">
		          		<div class="basket">
							<div>
								<div class="h4"> Service Types</div>
								<div class="text-right"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#newServiceType" data-whatever="@fat"><i class="fas fa-plus"></i>Add Service Type</button></div>						
							</div>
							
							<div class="modal fade" id="newServiceType" tabindex="-1" role="dialog" aria-labelledby="newEntitlementModalLabel">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20" action="${contextPath}/admin/addServiceType" method="POST" name="ServiceTypeForm" modelAttribute="ServiceTypeBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title" id="productCreateModalLabel1">Add New Service Type</h4>
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                        </div>
			                        <div class="modal-body">
			                            
			                        	<div class="form-group">
			                                <div>
			                                    <label for="product-name" class="control-label">Type Name:</label>
			                                	<input class="form-control form-control-lg" type="text" required placeholder="Service Name" id="TX_SERVICE_TYPE_NAME" name="TX_SERVICE_TYPE_NAME" path="TX_SERVICE_TYPE_NAME" >
			                                </div>
			                                
			                                <div>
			                                    <label for="product-name" class="control-label">Description:</label>
			                                    <textarea class="form-control" rows="3" id="TX_SERVICE_TYPE_DESCRIPTION" name="TX_SERVICE_TYPE_DESCRIPTION" path="TX_SERVICE_TYPE_DESCRIPTION"></textarea>
			                                </div>
			                                
			                                <div>
			                                	<label for="fl-active" class="control-label">Allow Rollover</label>
			                                		<select class="custom-select mr-sm-2" id="FL_ALLOW_ROLLOVER" name="FL_ALLOW_ROLLOVER" path="FL_ALLOW_ROLLOVER">
			                                            <option selected>Choose...</option>
			                                            <option value="true">YES</option>
			                                            <option value="false">NO</option>
			                                        </select>
			                                </div>
			                                
			                                 <div>
			                                	<label for="fl-active" class="control-label">Monthly Billing?</label>
			                                		<select class="custom-select mr-sm-2" id="FL_MONTHLY_BILLING" name="FL_MONTHLY_BILLING" path="FL_MONTHLY_BILLING">
			                                            <option selected>Choose...</option>
			                                            <option value="true">YES</option>
			                                            <option value="false">NO</option>
			                                        </select>
			                                </div>
			                            </div>
			                                
			                            
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="submit" class="btn btn-primary" >Create</button> 
			                           
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>
			            
							<div class="m-2">
								<c:if test="${empty entitlementTypes}">
									There are no Entitlement Types currently defined
								</c:if>
								<c:if test="${not empty entitlementTypes}">
								<table class="table table-hover">
								  <thead class="thead-default">
								    <tr>
								      <th>#</th>
								      <th>Entitlement Type</th>
								      <th>Description</th>
								      <th>Rollable</th>
								      <th>Monthly Billing</th>
								    </tr>
								  </thead>
								  <tbody>
								  
								  <c:forEach var="entry" items="${entitlementTypes}" varStatus="loop">
									 <tr>
									  <th scope="row">${loop.index+1}</th>
								      <td>${entry.TX_SERVICE_TYPE_NAME}</td>
								      <td>${entry.TX_SERVICE_TYPE_DESCRIPTION}</td>
								      <td>
								      <form class="form-horizontal m-t-20" id="checkboxForm_${entry.ID}" action="#" method="POST">
								      		<div>
	                            							<label class="custom-toggle">
								  				<c:if test="${true==entry.FL_ALLOW_ROLLOVER}">
								  					<input type="checkbox"
																onclick="save_checkbox(${entry.ID},1);" id="ROLLABLE_${entry.ID}"
																name="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}"
																path="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}" value="1"
																checked>
								  				</c:if>
								  				<c:if test="${true!=entry.FL_ALLOW_ROLLOVER}">
								  					<input type="checkbox"
																onclick="save_checkbox(${entry.ID},0);" id="ROLLABLE_${entry.ID}"
																name="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}"
																path="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}" value="0">
								  				</c:if>
								  				
									  			 	<span class="custom-toggle-slider rounded-circle"></span>
									  			</label>
								  			</div>
														  			
										</form>
											<script>
												function save_checkbox(id,val) {
													var documentForm = "checkboxForm_"+id;
													document.getElementById(documentForm).action = "${contextPath}/admin/service/updateEntitlementRollable?id="
															+ id
															+ "&value="
															+ val;
													document.getElementById(documentForm).submit();// Form submission
												}
											</script>				  			
										</td>
										
										<td>
								      <form class="form-horizontal m-t-20" id="rollableForm_${entry.ID}" action="#" method="POST">
								      		<div>
	                            							<label class="custom-toggle">
								  				<c:if test="${true==entry.FL_MONTHLY_BILLING}">
								  					<input type="checkbox"
																onclick="save_monthly(${entry.ID},1);" id="MONTHLY_${entry.ID}"
																name="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}"
																path="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}" value="1"
																checked>
								  				</c:if>
								  				<c:if test="${true!=entry.FL_MONTHLY_BILLING}">
								  					<input type="checkbox"
																onclick="save_monthly(${entry.ID},0);" id="MONTHLY_${entry.ID}"
																name="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}"
																path="${entry.TX_SERVICE_TYPE_NAME}_${entry.ID}" value="0">
								  				</c:if>
								  				
									  			 	<span class="custom-toggle-slider rounded-circle"></span>
									  			</label>
								  			</div>
														  			
										</form>
											<script>
													function save_monthly(id,val) {
														var documentForm = "checkboxForm_"+id;
														document.getElementById(documentForm).action = "${contextPath}/admin/service/updateEntitlementMonthlyBilling?id="
																+ id
																+ "&value="
																+ val;
														document.getElementById(documentForm).submit();// Form submission
													}
											</script>				  			
										</td>
										
								     </tr>
								  </c:forEach>
								  </tbody>
								  </table>
								</c:if>
							</div>
						
						
						</div>
		          </div>
		          <div class="tab-pane fade" id="Pricing">
		          		<div class="basket">
							<div>
								<div class="h4"> Pricing</div>
								<div class="text-right"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#newEntitlementModal" data-whatever="@fat"><i class="fas fa-plus"></i>Add Service Type</button></div>						
							</div>
							
							<div class="m-2">
							
							</div>
						</div>
		          </div>
		          
		          
		        </div>
		      </div>
    			</div>
    		
    	</div>
	</jsp:body>
</page:user-landing>