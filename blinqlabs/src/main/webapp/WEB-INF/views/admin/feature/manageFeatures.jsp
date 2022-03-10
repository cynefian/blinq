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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:user-landing>
	<jsp:body>
		<div id="page-wrapper"  class="page-wrapper">
			<div class="features-tabs-section" style="margin-top: 30px;">
    			<div class="canvas">
    			<p class="h2">Feature Management</p>
    			<hr/>
    			<div class="row">
    			
    			<div class="border-right sidebar-canvas">
	    			<div class="list-group">
	    				<a href="${contextPath}/admin/featureManagement" class="list-group-item list-group-item-action active">Features</a>
	    				<a href="${contextPath}/admin/featureManagement/section" class="list-group-item list-group-item-action">Sections</a>
	    				<a href="#" class="list-group-item list-group-item-action">Associations</a>
	    			</div>
    			
    			</div>
    			
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
		
		
					<div class="row">
						<div class="col-md-10 float-left">
							
					 	    <div class="input-group">
						    	<button type="button" class="btn btn-info"
											data-toggle="modal" data-target="#featureCreateModal">Create Feature</button>
								<button type="button" class="btn btn-info ml-2"
											data-toggle="modal" data-target="#sectionCreateModal">Create Section</button>
						      	<input type="text" class="form-control ml-2" id="searchInput"
											placeholder="Search for Section or Feature..."
											onkeyup="featureSectionSearch();">
						      	<span class="input-group-btn">
						        	<button class="btn btn-secondary" type="button">Go!</button>
						      	</span>
						    </div>
						
						  <script>
									function featureSectionSearch() {
										var input, section_body, section_row, filter, div1, div2, txtValue;
										input = document.getElementById('searchInput');
										filter = input.value.toUpperCase();
										
										div1 = document.getElementsByName("section");
										var active = new Array(div1.length);
										
										for (i = 0; i < div1.length; i++) {
											features = div1[i].children[1].children[0].children;
											active[i] = false;
											for(j=0; j < features.length; j++){
												if(features[j].id.toUpperCase().includes(filter)){
													features[j].style.display = "";
													active[i] = true;
												}else{
													features[j].style.display = "none";
												}
											}
											
											if(div[i].id.toUpperCase().includes(filter)){
												div[i].style.display = "";
											}else if(active[i] == true){
												div[i].style.display = "";
											}else{
												div[i].style.display = "none";
											}
										}
									}
								</script>
					  
						</div>
						<div class="col-md-2">
							<a
										href="${contextPath}/admin/featureManagement"
										method="GET">
								<button class="btn-pill btn-pill-success float-right">
											<i class="fa fa-refresh" aria-hidden="true"></i> Refresh</button>
							</a>
						</div>
						
						<br />
					</div>
					
					
					<div class="modal fade" id="featureCreateModal" tabindex="-1"
								role="dialog" aria-labelledby="featureCreateModalLabel1">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20"
											action="${contextPath}/admin/featureManagement?action=CREATE_FEATURE"
											method="POST" name="featureForm" modelAttribute="featureBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title"
													id="productCreateModalLabel1">Create Feature</h4>
			                            <button type="button" class="close"
													data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
			                        </div>
			                        <div class="modal-body">
			                            
			                        	<div class="form-group">
			                                <div>
			                                    <label for="product-name"
															class="control-label">Feature Name:</label>
			                                	<input
															class="form-control form-control-lg" type="text" required
															placeholder="Feature Name" id="tx_feature"
															name="tx_feature" path="tx_feature">
			                                </div>
			                                
			                                <div>
			                                	<label for="product-desc"
															class="control-label">Section</label>
			                                	<textarea class="form-control"
															rows="3" placeholder="Text Here..." id="tx_section"
															name="tx_section" path="tx_section"></textarea>
			                                </div>
			                                
			                                <div>
			                                	<label for="fl-active"
															class="control-label">Active?</label>
			                                		<select
															class="custom-select mr-sm-2" id="fl_active"
															name="fl_active" path="fl_active">
			                                            <option selected>Choose...</option>
			                                            <option value="true">YES</option>
			                                            <option value="false">NO</option>
			                                        </select>
			                                </div>
			                              </div>
			                                
			                            
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button"
													class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="submit"
													class="btn btn-primary">Create Feature</button> 
			                           
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>    
			            
			            
			            
			            <div class="modal fade" id="sectionCreateModal"
								tabindex="2" role="dialog"
								aria-labelledby="sectionCreateModalLabel1">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                   		<form class="form-horizontal m-t-20"
											action="${contextPath}/admin/featureManagement?action=CREATE_SECTION"
											method="POST" name="featureForm" modelAttribute="featureBean">
			                       		 <div class="modal-header">
				                            <h4 class="modal-title"
													id="sectionCreateModalLabel1">Create Section</h4>
				                            <button type="button" class="close"
													data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
				                        </div>
				                        <div class="modal-body">
				                        	<div class="form-group">
				                                <div>
				                                    <label for="product-name"
															class="control-label">Section Name:</label>
				                                	<input
															class="form-control form-control-lg" type="text" required
															placeholder="Section Name" id="tx_section"
															name="tx_section" path="tx_section">
				                                </div>
				                            
					                        </div>
					                        <div class="modal-footer">
					                            <button type="button"
														class="btn btn-default" data-dismiss="modal">Close</button>
					                             <button type="submit"
														class="btn btn-primary">Create Section</button> 
					                           
					                        </div>
					                       </div>
			                        </form>
			                    </div>
			                </div>
			            </div>    
    	
    	<form id="deleteSectionForm" action="#" method="POST"> </form>
    	
    	
		
			<c:if test="${not empty featureList}">
			<form class="form-horizontal m-t-20" id="checkboxForm" action="#"
									method="POST">
			
				  <c:forEach var="section" items="${featureList}">
				  
				  
				  		<div class="card m-3" id="section_${section.tx_section}"
											name="section">
	                            <div
												class="card-body bg-primary text-white">
	                              	<div>
	                                	<h4 class="card-title">${section.tx_section}</h4>
	                                </div>
	                                <div>
		                                <c:if
														test="${fn:length(section.featureList) eq 0}">
									        <button class="btn-pill btn-pill-warning float-right"
															onclick="deleteSection(this);"
															name="${section.tx_section}">
															<i class="fa fa-trash" aria-hidden="true"></i>
														</button>
			                                
			                                <script>
				                            	function deleteSection(el){
			                                		document.getElementById("deleteSectionForm").action = "${contextPath}/admin/deleteSection?section="+el.name;
			                                		document.getElementById("deleteSectionForm").submit();// Form submission
			                                	}
			                                </script>
			                            </c:if>
	                                </div>
	                                
	                            </div>
	                            <div class="card-body text-white" name="section-body">
	                                <div class="row" name="section-row">
	                                
	                                		<c:forEach var="entry" items="${section.featureList}">
	                                				
	                                				<div class="col-md-6" name="features" id="feature_${entry.tx_feature}">
		                                				<div class="card bg-light">
			                                				<div class="card-body text-white">
			                                					<div class="row"
																		id="feature_${entry.tx_feature}_item" name="feature">
			                                						<div class="col-md-2">
			                                							<label class="custom-toggle">
														  				<c:if test="${true==entry.fl_active}">
														  					<input type="checkbox"
																						onclick="save_checkbox(this);" id="${entry.id}"
																						name="${entry.tx_feature}_${entry.id}"
																						path="${entry.tx_feature}_${entry.id}" value="1"
																						checked>
														  				</c:if>
														  				<c:if test="${true!=entry.fl_active}">
														  					<input type="checkbox"
																						onclick="save_checkbox(this);" id="${entry.id}"
																						name="${entry.tx_feature}_${entry.id}"
																						path="${entry.tx_feature}_${entry.id}" value="0">
														  				</c:if>
														  				
															  			 	<span class="custom-toggle-slider rounded-circle"></span>
															  			</label>
														  			</div>
														  			<div class="col-md-8">
															  			<span
																				class="custom-toggle-field lead text-primary align-middle ">${entry.tx_feature}</span>
															  		</div>
			                                						<div
																			class=" col-md-2 text-right text-info">
													  					<a
																				href="${contextPath}/admin/featureDetails?id=${entry.id}"
																				title="View Details..."><i
																				class="fa fa-ellipsis-v" aria-hidden="true"></i></a>
													  				</div>
														  		</div>
			                                				</div>
		                                				</div>
	                                				
												  		
											  		</div>
	                                		
	                                		</c:forEach>
	                                
	                                </div>
	                            </div>
	                  	</div>
				  </c:forEach>
				  <script>
							function save_checkbox(el) {
								document.getElementById("checkboxForm").action = "${contextPath}/admin/checkboxUpdate?id="
										+ el.id
										+ "&feature="
										+ el.name
										+ "&value="
										+ el.value
										+ "&page=manageFeatures";
								document.getElementById("checkboxForm")
										.submit();// Form submission
							}
						</script>
				</form>
			</c:if>
		
					<c:if test="${empty featureList}">
						<span>You do not have any Features yet.</span>
					</c:if>
					</div>
					
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="productEditModal" tabindex="-1"
			role="dialog" aria-labelledby="productEditModalLabel1">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20"
						action="${contextPath}/admin/adminProductList"
						method="POST" name="featureForm" modelAttribute="featureBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title"
								id="productEditModalLabel1">Edit Product</h4>
			                            <button type="button" class="close"
								data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
			                        </div>
			                        <div class="modal-body">
			                            
			                        	<div class="form-group">
			                                <div>
			                                    <label for="product-name"
										class="control-label">Feature Name:</label>
			                                	<input
										class="form-control form-control-lg" type="text" required
										id="tx_feature" name="tx_feature" path="tx_feature">
			                                </div>
			                                
			                                <div>
			                                	<label for="product-desc"
										class="control-label">Section</label>
			                                	<textarea class="form-control"
										rows="3" id="tx_section" name="tx_section" path="tx_section"></textarea>
			                                </div>
			                                
			                                <div>
			                                	<label for="fl-active"
										class="control-label">Active?</label>
			                                		<select
										class="custom-select mr-sm-2" id="fl_active" name="fl_active"
										path="fl_active">
				                                			<option selected>Choose...</option>
				                                            <option value="true">YES</option>
				                                            <option value="false">NO</option>
				                                	</select>
			                                </div>
			                            </div>
			                                
			                            
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button"
								class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="submit"
								class="btn btn-primary">Update Feature</button> 
			                           
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>
			            
			            <script>
																$(
																		'#productEditModal')
																		.on(
																				'show.bs.modal',
																				function(
																						e) {

																					var button = $(event.relatedTarget); // Button that triggered the modal
																					var entry = button
																							.data('whatever'); // Extract info from data-* attributes

																					var modal = $(this);
																					modal
																							.find(
																									'#TX_PRODUCT_NAME')
																							.val(
																									entry);

																				})
															</script>
	</jsp:body>
</page:user-landing>