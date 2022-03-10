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
									<jsp:include page="/WEB-INF/views/admin/settings/navs/cloudSideNav.jsp" />
								</div>
								<div class="col-lg-10 border-left">
								
									<div>
							            	<section>
							            		<div id="creadcrumbs" class="p-2 m-2">
							            			Settings >> ${mainTabselect} > ${subTabselect}
							            		</div>
												<div class="text-center">
													<h3>${mainTab} </h3>
													<p>${subTab}</p>
												</div>
												
												<security:authorize
													access="hasAuthority('PERM_CLOUD_CREDENTIALS_MODIFY')"
													var="isUSer" />
													
													<div class="row">
														<div class="col-lg-12 col-sm-12">
																<security:authorize access="hasAuthority('PERM_CLOUD_CONFIG_VIEW')">
																	<div>
																		<c:if test="${not empty authList}">
																			<c:forEach var="auth" items="${authList}">
																				<div class="card">
																					<div class="card-title bg-primary p-4">
																						<div class="row">
																							<div class="col-lg-8 col-sm-12">
																								<p class="text-white h4">AWS</p>
																								<p class="text-white lead"> ${auth.TX_IDENTIFIER}</p>
																							</div>
																							<div class="col-lg-4 col-sm-12">
																								<c:if test="${isUSer}">
																									<div class="text-right">
																										<button type="button" class="btn btn-light" data-toggle="modal" data-target="#awsCredentialsModal">Edit</button>
																									</div>
																									
																										<!-- Edit credentials Modal -->
																											<div class="modal fade" id="awsCredentialsModal" tabindex="-1" role="dialog">
																											  <div class="modal-dialog" role="document">
																											    <div class="modal-content">
																											    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/updateCredentials" method="POST" name="CloudAuthForm" modelAttribute="cloudAuthBean">
																											      <div class="modal-header">
																											        	<h4 class="modal-title" id="exampleModalLabel1">Update AWS Connectivity</h4>
																					                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																											      </div>
																											      <div class="modal-body">
																											     	  <div>
																							                               
																							                                <div class="form-group">
																							                                	<label for="TX_ACCESS_KEY" class="control-label">Name:</label>
																							                                	<input type="text"class="form-control" name="TX_IDENTIFIER" ID = "TX_IDENTIFIER" value="${auth.TX_IDENTIFIER}" >
																							                                </div>
																							                                
																							                                <div class="form-group">
																							                                	<label for="TX_ACCESS_KEY" class="control-label">Access Key:</label>
																							                                	<input type="text" class="form-control" name="TX_ACCESS_KEY" ID = "TX_ACCESS_KEY">
																							                                </div>
																							                                
																							                                <div class="form-group">
																							                                	<label for="machine-id" class="control-label">Secret Key:</label>
																							                                	<input type="text" class="form-control" name="TX_ACCESS_SECRET" ID = "TX_ACCESS_SECRET">
																							                                </div>
																							                                <div class="form-group"v>
						                      																					<input type="text" name="ID_CLOUD_AUTH" ID = "ID_CLOUD_AUTH" value="${auth.ID_CLOUD_AUTH}" style="visibility:hidden">
																							                                </div>
																					                                	</div>
																											      </div>
																											      <div class="modal-footer">
																											      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																					                            	 	<button type="submit" class="btn btn-danger" >Save</button> 
																											      </div>
																											      </form>
																											    </div>
																											  </div>
																											</div>
																											
																								</c:if>
																							</div>
																						</div>
																						
																						
																						
																					</div>
																					<div class="card-body table-active">
																						<p>Access Key: <span class="text-primary h5"> ${auth.TX_ACCESS_KEY}</span></p>
																						<p>Secret: <span class="text-primary h5"> ${auth.TX_ACCESS_SECRET}</span> </p>
																					</div>
																				</div>
																			</c:forEach>
																		</c:if>
																		
																		<c:if test="${empty authList}">
																			<p class="text-danger h5"> You do not have any authentication defined.</p>
																		</c:if>
																	</div>
																</security:authorize>
														</div>
													</div>
													<div>
														<div class="row">
															<div class="col-lg-6 col-sm-12">
																<a href="${contextPath}/admin/settings/cloud/aws/rules"><button type="button" class="btn btn-info"><i class="fas fa-flag-checkered"></i>   Rules</button></a>
															</div>
															<div class="col-lg-6 col-sm-12 text-right">
																<button type="button" class="btn btn-sm btn-secondary" data-toggle="modal" data-target="#linkVPCModal"><i class="fas fa-link"></i>   Link Existing VPC</button>
																<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#createVPCModal"><i class="fas fa-plus"></i>   Create New VPC</button>
															</div>
														</div>
														
														<!-- Link VPC Modal -->
														<div class="modal fade" id="linkVPCModal" tabindex="-1" role="dialog">
														  <div class="modal-dialog" role="document">
														    <div class="modal-content">
														    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/linkVPC" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBean">
														      <div class="modal-header">
														        	<h4 class="modal-title" id="exampleModalLabel1">Link VPC</h4>
								                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
														      </div>
														      <div class="modal-body">
														     	  <div class="form-group">
										                               
										                                <div class="m-2">
										                                	<label for="machine-id" class="control-label">VPC Name:</label>
										                                	<input type="text" ID="LINK_VPC_TX_COMPONENT_NAME" name="TX_COMPONENT_NAME" class="form-control">
										                                </div>
										                                
										                                <div class="m-2">
										                                	<label for="machine-id" class="control-label">VPC KEY / ID:</label>
										                                	<input type="text" ID="TX_COMPONENT_KEY" name="TX_COMPONENT_KEY" class="form-control">
										                                </div>
										                                
										                                <div class="col-md-4 m-2">
										                                	<p>Default: </p>
								                                			<label class="custom-toggle">
									                                			<c:if test="${not empty vpcList}">
									                                				<input type="checkbox"  id="" name="" onclick="updateDefaultStatus(this);">
									                                			</c:if>
									                                			<c:if test="${empty vpcList}">
									                        						<input type="checkbox"  id="" name="" value="1" checked disabled>
									                                			</c:if>
								                                			 	<span class="custom-toggle-slider rounded-circle"></span>
																  			</label>
																  			<input type="text" id="LINK_VPC_FL_DEFAULT" name="FL_DEFAULT" style="visibility:hidden">
																  			<script>
																  				function updateDefaultStatus(element){
																  					if(element.checked == true){
																  						document.getElementById("LINK_VPC_FL_DEFAULT").value = 1;
																  					}else{
																  						document.getElementById("LINK_VPC_FL_DEFAULT").value = 0;
																  					}
																  				}
																  			</script>
															  			</div>
								                                	</div>
														      </div>
														      <div class="modal-footer">
														      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								                            	 	<button type="submit" class="btn btn-primary" >Link</button> 
														      </div>
														      </form>
														    </div>
														  </div>
														</div>
														
														
														<!-- Create VPC Modal -->
														<div class="modal fade" id="createVPCModal" tabindex="-1" role="dialog">
														  <div class="modal-dialog" role="document">
														    <div class="modal-content">
														    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/newVPC" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBean">
														      <div class="modal-header">
														        	<h4 class="modal-title" id="exampleModalLabel1">New VPC</h4>
								                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
														      </div>
														      <div class="modal-body">
														     	  <div class="form-group">
										                                <div class="m-2">
										                                	<label for="machine-id" class="control-label">Enter a name for the VPC:</label>
										                                	<input type="text" ID="CREATE_VPC_TX_COMPONENT_NAME" name="TX_COMPONENT_NAME" class="form-control">
										                                </div>
									                            	</div>
									                            	
									                            	<div class="col-md-4 m-2">
										                                	<p>Would you like to make this the Default VPC? </p>
								                                			<label class="custom-toggle">
									                                				<input type="checkbox"  id="" name="" value="" onclick="updateDefaultStatus(this);">
									                                			<span class="custom-toggle-slider rounded-circle"></span>
																  			</label>
																  			<input type="text" id="CREATE_VPC_FL_DEFAULT" name="FL_DEFAULT" style="visibility:hidden">
																  			<script>
																  				function updateDefaultStatus(element){
																  					if(element.checked == true){
																  						document.getElementById("CREATE_VPC_FL_DEFAULT").value = 1;
																  						document.getElementById("CREATE_VPC_TX_COMPONENT_NAME").value = 1;
																  					}else{
																  						document.getElementById("CREATE_VPC_FL_DEFAULT").value = 0;
																  						document.getElementById("CREATE_VPC_TX_COMPONENT_NAME").value = 0;
																  					}
																  				}
																  			</script>
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
														
														<c:if test="${not empty vpcList}">
														   <div id="accordion">
														   		<c:forEach var="vpcitem" items="${vpcList}" varStatus="loop">
														   			<div class="border-info p-2 mb-3">
															   	        <div class="card m-b-5 ">
							                                                <div class="card-header bg-info" id="heading_${loop.index}">
								                                                <div class="row">
								                                                	<div class="col-lg-8 col-sm-12 text-left">
								                                                		<h5 class="mb-0">
											                                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapse_${loop.index}" aria-expanded="true" aria-controls="collapse_${loop.index}">
											                                                 	<p><span class="h4 text-white">${vpcitem.TX_COMPONENT_NAME}</span>   <span class="lead text-white">[ ${vpcitem.TX_COMPONENT_KEY} ]</span></p>
											                                                </button>
											                                              </h5>
											                                              <span class="h6 text-warning" id="accordion-error"></span>
								                                                	</div>
								                                                	<div class="col-lg-4 col-sm-12 text-right">
									                                                	<c:if test="${vpcitem.FL_DEFAULT==true}">
									                                                			<span class="text-white">Default</span>
									                                                		</c:if>
								                                                		<label class="custom-toggle"> 
									                                                		<c:if test="${vpcitem.FL_DEFAULT==true}">
												                                				<input type="checkbox"  id="vpcDefaultFlag_${vpcitem.ID_COMPONENT}" name="" value="1" checked disabled>
												                                			</c:if>
												                                			<c:if test="${vpcitem.FL_DEFAULT!=true}">
												                        						<input type="checkbox"  id="vpcDefaultFlag_${vpcitem.ID_COMPONENT}" name="" value="0" data-toggle="modal" data-target="#vpcDefaultsModal_${vpcitem.ID_COMPONENT}">
												                                			</c:if>
											                                			 	<span class="custom-toggle-slider rounded-circle"></span>
																			  			</label>
																			  		</div>
																			  		
												  								  			<!-- VPC Default Modal -->
						                                                        				<div class="modal fade" id="vpcDefaultsModal_${vpcitem.ID_COMPONENT}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/updateVPCDefaults" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"  onclick="dismissVPCDefaultsModal();"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																									      <p><span class="h5 text-primary">There can only be one default VPC. </span></p>
																									      <p><span class="lead"> This action will reassign the default status to the selected VPC. </span></p>
																									      <p><span> Do you wish to continue?</span></p>
																									      <input type="text" id="ID_COMPONENT" name="ID_COMPONENT" value="${vpcitem.ID_COMPONENT}" style="visibility:hidden">
																									  </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="dismissVPCDefaultsModal();">Close</button>
																		                            	 	<button type="submit" class="btn btn-primary" >Confirm</button> 
																								      </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
																						      
																						        <script>
																						      	function dismissVPCDefaultsModal(){
																						      		document.getElementById("vpcDefaultFlag_${subnet.ID_COMPONENT}").checked=false;
																						      	}
																						      </script>
							                                                    </div>
							                                                </div>
							                                                <div id="collapse_${loop.index}" class="collapse show" aria-labelledby="heading_${loop.index}" data-parent="#accordion">
							                                                    <div >
							                                                        <div class="row">
							                                                        	<div class="col-lg-9 col-sm-12 pr-0"> 
							                                                        		<div >
							                                                        			
							                                                        			<div class="row table-primary p-3 ml-0">
							                                                        				<div class="col-lg-6 col-sm-12">
							                                                        					<h4>Subnet:</h4>
							                                                        				</div>
							                                                        				<div class="col-lg-6 col-sm-12 text-right">
								                                                        				<button class="btn btn-sm btn-outline-primary" data-toggle="modal" data-target="#subnetModal"><i class="fas fa-plus"></i> Add</button>
							                                                        				</div>
							                                                        			</div>
							                                                        				
							                                                        				
							                                                        				<c:set var="subnetCount" value="0" />
							                                                        				<c:if test="${not empty subnetList}">
							                                                        				
							                                                        					<table class="table">
																											  <thead class="thead-default">
																											    <tr>
																											      <th>Name / Key</th>
																											      <th>Default</th>
																											      <th>Usage</th>
																											      <th>Enabled</th>
																											      <th>Actions</th>
																											    </tr>
																											  </thead>
																											  <tbody>
							                                                        				
									                                                        					<c:forEach var="subnet" items = "${subnetList}"  varStatus="loop">
									                                                        						<c:if test="${subnet.ID_PARENT==vpcitem.ID_COMPONENT}">
									                                                        						<c:set var="subnetCount" value="1" />
									                                                        						<tr>
									                                                        							<td><span class="text-primary h6">${subnet.TX_COMPONENT_NAME} </span><span class="lead"> [ ${subnet.TX_COMPONENT_KEY} ]</span></td>
									                                                        							<td>
									                                                        								<label class="custom-toggle"> 
																					                                			<c:if test="${subnet.FL_DEFAULT==true}">
																					                                				<input type="checkbox"  id="subnetDefaultFlag_${subnet.ID_COMPONENT}" name="" value="1" checked disabled>
																					                                			</c:if>
																					                                			<c:if test="${subnet.FL_DEFAULT!=true}">
																					                        						<input type="checkbox"  id="subnetDefaultFlag_${subnet.ID_COMPONENT}" name="" value="0" data-toggle="modal" data-target="#subnetDefaultsModal_${subnet.ID_COMPONENT}">
																					                                			</c:if>
																				                                			 	<span class="custom-toggle-slider rounded-circle"></span>
																												  			</label>
																												  			
																												  			<!-- Edit Subnet Modal -->
						                                                        				<div class="modal fade" id="subnetDefaultsModal_${subnet.ID_COMPONENT}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/updatesubnetDefaults" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Subnet for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"  onclick="dismissDefaultsModal();"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																									      <p><span class="h5 text-primary">There can only be one default subnet. </span></p>
																									      <p><span class="lead"> This action will reassign the default status to the selected subnet. </span></p>
																									      <p><span> Do you wish to continue?</span></p>
																									      <input type="text" id="ID_COMPONENT" name="ID_COMPONENT" value="${subnet.ID_COMPONENT}" style="visibility:hidden">
																									  </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="dismissDefaultsModal();">Close</button>
																		                            	 	<button type="submit" class="btn btn-primary" >Confirm</button> 
																								      </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
																						      
																						      <script>
																						      	function dismissDefaultsModal(){
																						      		document.getElementById("subnetDefaultFlag_${subnet.ID_COMPONENT}").checked=false;
																						      	}
																						      </script>
																								      
																												  				
									                                                        							</td>
									                                                        							<td> ${subnet.TX_USAGE_COUNTER} </td>
									                                                        							<td> YES</td>
									                                                        							<td>
										                                                        							<c:if test="${((subnet.FL_DEFAULT==true) or (subnet.TX_USAGE_COUNTER!='0'))}">
										                                                        								<button class="btn btn-outline-secondary" disabled><i class="fas fa-trash-alt"></i></button>
										                                                        							</c:if>
										                                                        							<c:if test="${((subnet.FL_DEFAULT!=true) and (subnet.TX_USAGE_COUNTER=='0'))}">
										                                                        								<button class="btn btn-outline-danger" data-toggle="modal" data-target="#deleteSUbnetModal_${subnet.ID_COMPONENT}"><i class="fas fa-trash-alt"></i></button>
										                                                        							</c:if>
										                                                        							
										                                                        											  			<!-- Edit Subnet Modal -->
						                                                        				<div class="modal fade" id="deleteSUbnetModal_${subnet.ID_COMPONENT}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/deleteSubnet" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Subnet for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								      		<p>Subnet: <span class="h4 text-primary">${subnet.TX_COMPONENT_KEY}</span>
																									      <p><span class="h5 text-danger">Are you sure you want to delete the subnet? </span></p>
																									      <input type="text" id="ID_COMPONENT" name="ID_COMPONENT" value="${subnet.ID_COMPONENT}" style="visibility:hidden">
																									  </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="submit" class="btn btn-danger" >Delete</button> 
																								      </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
																						      
									                                                        							</td>
								                                                        							</tr>
								                                                        						</c:if>
								                                                        					</c:forEach>
							                                                        					</tbody>
							                                                        					</table>
							                                                        				</c:if>
							                                                        				<c:if test="${subnetCount==0}">
							                                                        					<span class="text-danger lead"> Not defined</span> 
							                                                        				</c:if>
							                                                        			
							                                                        			
							                                                        			<!-- Edit Subnet Modal -->
						                                                        				<div class="modal fade" id="subnetModal" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" id="subnetLinkForm_${vpcitem.ID_COMPONENT}" action="${contextPath}/admin/settings/cloud/aws/vpc/addLinkSubnet" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Subnet for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								     	  <div class="form-group">
																								     	  
																									     	  	<div class="mb-2">
																								     	  			<h4 class="control-label">Create new Subnet</h4>
																								     	  			<label class="custom-toggle">
																			                                			<input type="checkbox" name="" value="" id="${vpcitem.ID_COMPONENT}" onclick="updatesubnetAction(this);">
																			                                			<span class="custom-toggle-slider rouded-circle"></span>
																										  			</label>
																								     	  		</div>
																								     	  		<script>
																								     	  			function updatesubnetAction(element){
																								     	  				var textelementidentifier = "TX_COMPONENT_NAME_"+element.id;
																								     	  				var keyelementidentifier = "TX_COMPONENT_KEY_"+element.id;
																								     	  				var actionelementidentifier = "TX_ACTION_"+element.id;
																								     	  				if(element.checked == true){
																								     	  					document.getElementById(textelementidentifier).value = "";
																								     	  					document.getElementById(keyelementidentifier).value = "";
																								     	  					document.getElementById(textelementidentifier).disabled = true;
																								     	  					document.getElementById(keyelementidentifier).disabled = true;
																								     	  					document.getElementById(actionelementidentifier).value = "CREATE";
																								     	  				}else{
																								     	  					document.getElementById(textelementidentifier).disabled = false;
																								     	  					document.getElementById(keyelementidentifier).disabled = false;
																								     	  					document.getElementById(actionelementidentifier).value = "LINK";
																								     	  				}
																								     	  			}
																								     	  		</script>
																								     	  		<hr/>
																								     	  		<div class="mb-2">
																								     	  			<h4 class="control-label">Link Existing Subnet</h4>
																								     	  		</div>
																				                               <div>
																				                                	<label for="TX_COMPONENT_NAME" class="control-label">Subnet Name: </label>
																				                                	<input type="text" id="TX_COMPONENT_NAME_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_NAME" class="form-control">
																				                                </div>
																				                                
																				                                <div>
																				                                	<label for="TX_COMPONENT_KEY" class="control-label">Subnet Key / ID: </label>
																				                                	<input type="text" id="TX_COMPONENT_KEY_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_KEY" class="form-control">
																				                                </div>
																				                                <div>
																				                                	<input type="text" id="ID_PARENT_${vpcitem.ID_COMPONENT}" name="ID_PARENT" value="${vpcitem.ID_COMPONENT}" style="visibility:hidden">
																				                                	<input type="text" id="TX_ACTION_${vpcitem.ID_COMPONENT}" name="TX_ACTION" value="LINK"  style="visibility:hidden">
																				                                	<input type="text" id="TX_DEFAULT_${vpcitem.ID_COMPONENT}" name="FL_DEFAULT" value=""  style="visibility:hidden">
																				                                </div>
																				                                <hr>
																				                                <div>
																				                                	<label class="control-label h5">Do you wish to make this the default subnet? </label><br/>
																				                                	<label class="custom-toggle">
																			                                			<input type="checkbox" id="${vpcitem.ID_COMPONENT}" name="" value="" onclick="updatesubnetDefaults(this);">
																			                                			<span class="custom-toggle-slider rouded-circle"></span>
																										  			</label>
																				                                </div>
																				                                <script>
																				                                	function updatesubnetDefaults(element){
																				                                		var defaultElementidentifier = "TX_DEFAULT_"+element.id;
																				                                		if (element.checked == true){
																				                                			document.getElementById(defaultElementidentifier).value = "true";
																				                                		}else{
																				                                			document.getElementById(defaultElementidentifier).value = "false";
																				                                		}
																				                                	}
																				                                </script>
																				                                <div>
																				                                	<span class="text-danger h6" id="modal-subnet-link-error_${vpcitem.ID_COMPONENT}"></span>
																				                                </div>
																				                            </div>
																								      </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="button" class="btn btn-primary" name="${vpcitem.ID_COMPONENT}" onclick="submitNewSubnet(this);">Submit</button> 
																								      </div>
																								      <script>
																								      	function submitNewSubnet(element){
																								      		var errMsg="";
																								      		if(document.getElementById(element.name).checked!=true){
																								      			var subnetNameElement = document.getElementById("TX_COMPONENT_NAME_" + element.name);
																								      			var subnetKeyElement = document.getElementById("TX_COMPONENT_KEY_" + element.name);
																								      			if(subnetNameElement.value==null || subnetNameElement.value==""){
																								      				errMsg = "Enter a name to identify the subnet.";
																								      			}else if(subnetKeyElement.value==null || subnetKeyElement.value==""){
																								      				errMsg = "Enter the Key of the subnet.";
																								      			}
																								      		}
																								      		if(errMsg==""){
																								      			document.getElementById("subnetLinkForm_"+element.name).submit();
																								      		}else{
																								      			document.getElementById("modal-subnet-link-error_"+element.name).innerHTML = errMsg;
																								      		}
																								      	}
																								      </script>
																								      </form>
																								    </div>
																								  </div>
																								</div>
							                                                        		</div>
							                                                        		
							                                                        	<div>
							                                                        		<div  class="p-2">
							                                                        			<div class="row table-primary p-3">
							                                                        				<div class="col-lg-6 col-sm-12">
							                                                        					<h4>Security Group:</h4>
							                                                        				</div>
							                                                        				<div class="col-lg-6 col-sm-12 text-right">
								                                                        				<button class="btn btn-sm btn-outline-primary" data-toggle="modal" data-target="#secGroupModal"><i class="fas fa-plus"></i> Add</button>
							                                                        				</div>
							                                                        			</div>
							                                                        		</div>
							                                                        		
							                                                        		<c:set var="sgCount" value="0" />
							                                                        		<c:if test="${not empty sgList}">
							                                                        		
							                                                        			<table class="table">
																								  <thead class="thead-default">
																								    <tr>
																								      <th>Name / Key</th>
																								      <th>Default</th>
																								      <th>Usage</th>
																								      <th>Enabled</th>
																								      <th>Actions</th>
																								    </tr>
																								  </thead>
																								  <tbody>
																									  <c:forEach var="sg" items = "${sgList}">
						                                                        						<c:if test="${sg.ID_PARENT==vpcitem.ID_COMPONENT}">
						                                                        							<c:set var="sgCount" value="1" />				
						                                                        								<tr>
								                                                        							<td><span class="text-primary h6">${sg.TX_COMPONENT_NAME} </span><span class="lead"> [ ${sg.TX_COMPONENT_KEY} ]</span></td>
								                                                        							<td>
							                                                        									<label class="custom-toggle"> 
																				                                			<c:if test="${sg.FL_DEFAULT==true}">
																				                                				<input type="checkbox"  id="sgDefaultFlag_${sg.ID_COMPONENT}" name="" value="1" checked disabled>
																				                                			</c:if>
																				                                			<c:if test="${sg.FL_DEFAULT!=true}">
																				                        						<input type="checkbox"  id="sgDefaultFlag_${sg.ID_COMPONENT}" name="" value="0" data-toggle="modal" data-target="#sgDefaultsModal_${sg.ID_COMPONENT}">
																				                                			</c:if>
																			                                			 	<span class="custom-toggle-slider rounded-circle"></span>
																											  			</label>
																												  			
																												  			<!-- Edit Security Group Modal -->
														                                                        				<div class="modal fade" id="sgDefaultsModal_${sg.ID_COMPONENT}" tabindex="-1" role="dialog">
																																  <div class="modal-dialog" role="document">
																																    <div class="modal-content">
																																    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/updateSGDefaults" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																																      <div class="modal-header">
																																        	<h4 class="modal-title">Subnet for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																										                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"  onclick="dismissSGDefaultsModal();"><span aria-hidden="true">&times;</span></button>
																																      </div>
																																      <div class="modal-body">
																																	      <p><span class="h5 text-primary">There can only be one default subnet. </span></p>
																																	      <p><span class="lead"> This action will reassign the default status to the selected subnet. </span></p>
																																	      <p><span> Do you wish to continue?</span></p>
																																	      <input type="text" id="ID_COMPONENT" name="ID_COMPONENT" value="${sg.ID_COMPONENT}" style="visibility:hidden">
																																	  </div>
																																      <div class="modal-footer">
																																      		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="dismissDefaultsModal();">Close</button>
																										                            	 	<button type="submit" class="btn btn-primary" >Confirm</button> 
																																      </div>
																															      </form>
																															      </div>
																														      	</div>
																														      </div>
																														      
																														      <script>
																														      	function dismissSGDefaultsModal(){
																														      		document.getElementById("sgDefaultFlag_${sg.ID_COMPONENT}").checked=false;
																														      	}
																														      </script>
																																      	
								                                                        							</td>
								                                                        							<td>${sg.TX_USAGE_COUNTER}</td>
								                                                        							<td>YES</td>
								                                                        							<td>
								                                                        									<c:if test="${((sg.FL_DEFAULT==true) or (sg.TX_USAGE_COUNTER!='0'))}">
										                                                        								<button class="btn btn-outline-secondary" disabled><i class="fas fa-trash-alt"></i></button>
										                                                        							</c:if>
										                                                        							<c:if test="${((sg.FL_DEFAULT!=true) and (sg.TX_USAGE_COUNTER=='0'))}">
										                                                        								<button class="btn btn-outline-danger" data-toggle="modal" data-target="#deleteSGModal_${sg.ID_COMPONENT}"><i class="fas fa-trash-alt"></i></button>
										                                                        							</c:if>
										                                                        							
                                                    											  			<!-- Del SG Modal -->
						                                                        				<div class="modal fade" id="deleteSGModal_${sg.ID_COMPONENT}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/deleteSecGroup" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Security Group for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								      		<p>Subnet: <span class="h4 text-primary">${sg.TX_COMPONENT_KEY}</span>
																									      <p><span class="h5 text-danger">Are you sure you want to delete the Security Groupt? </span></p>
																									      <input type="text" id="DEL_SG_ID_COMPONENT" name="ID_COMPONENT" value="${sg.ID_COMPONENT}" style="visibility:hidden">
																									  </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="submit" class="btn btn-danger" >Delete</button> 
																								      </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
								                                                        							</td>
									                                                        					</tr>
						                                                        						</c:if>
						                                                        					</c:forEach>
																									  
																								  </tbody>
																							  </table>
							                                                        		</c:if>
							                                                        		<c:if test="${sgCount==0}">
					                                                        					<span class="text-danger lead"> Not defined</span>
					                                                        				</c:if>
					                                                        				
					                                                        				<!-- Edit Security Group Modal -->
							                                                        		<div class="modal fade" id="secGroupModal" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" id="secGroupCreateForm_${vpcitem.ID_COMPONENT}" action="${contextPath}/admin/settings/cloud/aws/vpc/updateSecurityGroup" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Security Group for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								       <div class="modal-body">
																								     	  <div class="form-group">
																								     	  
																									     	  	<div class="mb-2">
																								     	  			<h4 class="control-label">Create new Security Group?</h4>
																								     	  			<label class="custom-toggle">
																			                                			<input type="checkbox" id="sgFlag_${vpcitem.ID_COMPONENT}" name="${vpcitem.ID_COMPONENT}" value="" onclick="updateSGAction(this);">
																			                                			<span class="custom-toggle-slider rouded-circle"></span>
																										  			</label>
																								     	  		</div>
																								     	  		<script>
																								     	  			function updateSGAction(element){
																								     	  				var textelementidentifier = "TX_COMPONENT_NAME_SG_"+element.name;
																								     	  				var keyelementidentifier = "TX_COMPONENT_KEY_SG_"+element.name;
																								     	  				var actionelementidentifier = "TX_ACTION_SG_"+element.name;
																								     	  				if(element.checked == true){
																								     	  					document.getElementById(textelementidentifier).value = "";
																								     	  					document.getElementById(keyelementidentifier).value = "";
																								     	  					document.getElementById(textelementidentifier).disabled = true;
																								     	  					document.getElementById(keyelementidentifier).disabled = true;
																								     	  					document.getElementById(actionelementidentifier).value = "CREATE";
																								     	  				}else{
																								     	  					document.getElementById(textelementidentifier).disabled = false;
																								     	  					document.getElementById(keyelementidentifier).disabled = false;
																								     	  					document.getElementById(actionelementidentifier).value = "LINK";
																								     	  				}
																								     	  			}
																								     	  		</script>
																								     	  		<hr/>
																								     	  		<div class="mb-2">
																								     	  			<h4 class="control-label">Link Existing Security Group</h4>
																								     	  		</div>
																				                               <div>
																				                                	<label for="TX_COMPONENT_NAME" class="control-label">Security Group Name: </label>
																				                                	<input type="text" id="TX_COMPONENT_NAME_SG_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_NAME" class="form-control">
																				                                </div>
																				                                
																				                                <div>
																				                                	<label for="TX_COMPONENT_KEY" class="control-label">Security Group Key / ID: </label>
																				                                	<input type="text" id="TX_COMPONENT_KEY_SG_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_KEY" class="form-control">
																				                                </div>
																				                                <div class="mb-2">
																								     	  			<h4 class="control-label">Default?</h4>
																								     	  			<label class="custom-toggle">
																			                                			<input type="checkbox" id="defaultSG_${vpcitem.ID_COMPONENT}" name="" value="" onclick="updateSGAction(this);">
																			                                			<span class="custom-toggle-slider rouded-circle"></span>
																										  			</label>
																								     	  		</div>
																				                              <div>
																				                                	<input type="text" id="ID_PARENT_SG_${vpcitem.ID_COMPONENT}" name="ID_PARENT" value="${vpcitem.ID_COMPONENT}" style="visibility:hidden">
																				                                	<input type="text" id="TX_ACTION_SG_${vpcitem.ID_COMPONENT}" name="TX_ACTION" value="LINK"  style="visibility:hidden">
																				                                	<input type="text" id="TX_DEFAULT_SG_${vpcitem.ID_COMPONENT}" name="FL_DEFAULT" value=""  style="visibility:hidden">
																				                                </div>
																				                                <div>
																				                                	<span class="text-danger" id="sec-grp-modal-error_${vpcitem.ID_COMPONENT}"></span>
																				                                </div>
																				                            </div>
																								      </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="button" class="btn btn-primary" name="${vpcitem.ID_COMPONENT}" onclick="validateSecGroupLink(this);">Submit</button> 
																								      </div>
																								      <script>
																								      	function validateSecGroupLink(element){
																								      		var flagIdentifier = document.getElementById("sgFlag_"+element.name);
																								      		var textelementidentifier = document.getElementById("TX_COMPONENT_NAME_SG_"+element.name);
																					     	  				var keyelementidentifier = document.getElementById("TX_COMPONENT_KEY_SG_"+element.name);
																					     	  				var defaultSGidentifier = document.getElementById("defaultSG_"+element.name);
																					     	  				var errMsg = "";
																					     	  				
																					     	  				if(defaultSGidentifier.checked==true){
																					     	  					document.getElementById("TX_DEFAULT_SG_"+element.name).value=true;
																					     	  				}else{
																					     	  					document.getElementById("TX_DEFAULT_SG_"+element.name).value=false;
																					     	  				}
																					     	  				if(flagIdentifier.checked!=true){
																					     	  					if(textelementidentifier.value == null || textelementidentifier.value ==""){
																					     	  						errMsg = "Enter a name to identify the Security Group";
																					     	  					}else if (keyelementidentifier.value == null || keyelementidentifier.value == ""){
																					     	  						errMsg = "Enter the key for the Security Group";
																					     	  					}
																					     	  				}
																					     	  				
																					     	  				if(errMsg==""){
																					     	  					document.getElementById("secGroupCreateForm_"+element.name).submit();
																					     	  				}else{
																					     	  					document.getElementById("sec-grp-modal-error_"+element.name).innerHTML = errMsg;
																					     	  				}
																								      	}
																								      </script>
																								      </form>
																								    </div>
																								  </div>
																								</div>
							                                                        		</div>
							                                                        		
							                                                        		
							                                                        		
							                                                        		<div>
							                                                        			<div class="row table-primary p-3">
							                                                        				<div class="col-lg-6 col-sm-12">
							                                                        					<h4>EC2:</h4>
							                                                        				</div>
							                                                        				<div class="col-lg-6 col-sm-12 text-right">
								                                                        				<button class="btn btn-sm btn-outline-primary" data-toggle="modal" data-target="#ec2Modal"><i class="fas fa-plus"></i> Add</button>
							                                                        				</div>
							                                                        			</div>
							                                                        			
							                                                        			<c:set var="e2Count" value="0" />
					                                                        					<c:if test="${not empty ec2List}">
					                                                        					
					                                                        						<table class="table">
																									  <thead class="thead-default">
																									    <tr>
																									      <th>Name / Key</th>
																									      <th>IP</th>
																									      <th>Usage</th>
																									      <th>Status</th>
																									      <th>Actions</th>
																									    </tr>
																									  </thead>
																									  <tbody>
																											  
						                                                        						<c:forEach var="ec" items = "${ec2List}">
						                                                        							<c:if test="${ec.ID_PARENT==vpcitem.ID_COMPONENT}">
							                                                        							<c:set var="e2Count" value="1" />
						                                                        									<tr>
									                                                        							<td>
									                                                        								<span class="text-primary h6">${ec.TX_COMPONENT_NAME} </span><span class="lead"> [ ${ec.TX_COMPONENT_KEY} ]</span> 
									                                                        								<button type="button"class="btn btn-outline-light" data-toggle="modal" data-target="#viewEc2Modal_${ec.ID_COMPONENT}"><span class="text-primary"><i class="fas fa-eye"></i></span></button>
									                                                        							</td>
									                                                        							<td>
										                                                        							${ec.TX_IP}
										                                                        							<button type="button"class="btn btn-outline-light" data-toggle="modal" data-target="#updateEC2IP_${ec.ID_COMPONENT}"><span class="text-info"><i class="far fa-edit"></i></span></button>
									                                                        							
									                                                        	<!-- Update EC2 IP Modal -->
						                                                        				<div class="modal fade" id="updateEC2IP_${ec.ID_COMPONENT}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/ec2/updateIP" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Compute Engine EC2 for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								      		<p>EC2: <span class="h4 text-primary">${ec.TX_COMPONENT_KEY}}</span>
																									      <p><span class="h5 text-danger">Enter the new IP address for this EC2 instance </span></p>
																									      <input type="text" class="form-control" id="TX_IP_ID_COMPONENT" name="TX_IP" value="${ec.TX_IP}" >
																									      <input type="text" id="DEL_EC_ID_COMPONENT" name="ID_COMPONENT" value="${ec.ID_COMPONENT}" style="visibility:hidden">
																									  </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="submit" class="btn btn-primary" >Update</button> 
																								      </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
									                                                        							
									                                                        							
									                                                        							</td>
									                                                        							<td> ${ec.TX_USAGE_COUNTER} </td>
									                                                        							<td> 
									                                                        							<c:if test="${ec.FL_STATUS==true}">
									                                                        								<span class="text-success">Running</span>
									                                                        							</c:if>
									                                                        							<c:if test="${ec.FL_STATUS!=true}">
									                                                        								<span class="text-danger">Stopped</span>
									                                                        							</c:if>
									                                                        							
									                                                        							</td>
									                                                        							<td>
									                                                        								<button type="button"class="btn btn-outline-light" data-toggle="modal" data-target="#changeStateEC2_${ec.ID_COMPONENT}"><span class="text-info"><i class="fas fa-power-off"></i></span></button>
										                                                        							
										                                                        							<c:if test="${((ec.FL_DEFAULT==true) or (ec.TX_USAGE_COUNTER!='0'))}">
										                                                        								<button class="btn btn-outline-secondary" disabled><i class="fas fa-trash-alt"></i></button>
										                                                        							</c:if>
										                                                        							<c:if test="${((ec.FL_DEFAULT==false) and (ec.TX_USAGE_COUNTER=='0') )}">
										                                                        								<button class="btn btn-outline-danger" data-toggle="modal" data-target="#deleteECModal_${ec.ID_COMPONENT}"><i class="fas fa-trash-alt"></i></button>
										                                                        							</c:if>
										                                                        							
                                                    											  			<!-- Del SG Modal -->
						                                                        				<div class="modal fade" id="deleteECModal_${ec.ID_COMPONENT}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/deleteEC" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Compute Engine EC2 for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								      		<p>Subnet: <span class="h4 text-primary">${ec.TX_COMPONENT_KEY}</span>
																									      <p><span class="h5 text-danger">Are you sure you want to delete the EC2? </span></p>
																									      <input type="text" id="DEL_EC_ID_COMPONENT" name="ID_COMPONENT" value="${ec.ID_COMPONENT}" style="visibility:hidden">
																									  </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="submit" class="btn btn-danger" >Delete</button> 
																								      </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
																						      
																								<!--  EC2 State modal -->
						                                                        				<div class="modal fade" id="changeStateEC2_${ec.ID_COMPONENT}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" id="ec2StateChangeForm_${ec.ID_COMPONENT}" action="${contextPath}/admin/settings/cloud/aws/ec2/changeState" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">EC2 Instance: ${ec.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								      		<div class="text-right">
																									      		<c:if test="${ec.FL_STATUS == true}">
																									      			<p><span class="h5 text-primary">Current EC2 state: <span class="text-success">Running </span></span></p>
																									      			<button type="button" class="btn btn-secondary" name="${ec.ID_COMPONENT}" id="START_${ec.ID_COMPONENT}" disabled onclick="changeEC2State('TRUE',this);">Start</button>
																									      			<button type="button" class="btn btn-danger" name="${ec.ID_COMPONENT}" id="STOP_${ec.ID_COMPONENT}" onclick="changeEC2State('FALSE',this);">Stop</button>
																									      		</c:if>
																									      		<c:if test="${ec.FL_STATUS != true}">
																									      			<p><span class="h5 text-primary">Current EC2 state: <span class="text-danger">Stopped </span></span></p>
																									      			<button type="button" class="btn btn-success" name="${ec.ID_COMPONENT}" id="START_${ec.ID_COMPONENT}" onclick="changeEC2State('TRUE',this);">Start</button>
																									      			<button type="button" class="btn btn-secondary" name="${ec.ID_COMPONENT}" id="STOP_${ec.ID_COMPONENT}" disabled  onclick="changeEC2State('FALSE',this);">Stop</button>
																									      		</c:if>
																								      		</div>
																								      		<div>
																								      			<input type="text" id="ID_COMPONENT_STATE_${ec.ID_COMPONENT}" name="ID_COMPONENT" value="${ec.ID_COMPONENT}" style="visibility:hidden">
																								      			<input type="text" id="FL_STATE_${ec.ID_COMPONENT}" name="FL_STATUS" value="" style="visibility:hidden">
																								      		</div>
																									  </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                              </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
																						      
																						      <script>
																						      	function changeEC2State(newState, element){
																						      		document.getElementById("FL_STATE_"+element.name).value=newState;
																						      		document.getElementById("ec2StateChangeForm_"+element.name).submit();
																						      	}
																						      
																						      </script>
																						      
									                                                        							</td>
								                                                        							</tr>
								                                                        							
								                                                        							<!-- View EC2 Info -->
								                                                        							
								                                                        							<div class="modal fade" id="viewEc2Modal_${ec.ID_COMPONENT}" tabindex="-1" role="dialog">
																													  <div class="modal-dialog" role="document">
																													    <div class="modal-content">
																													      <div class="modal-header">
																													        	<h4 class="modal-title" id="exampleModalLabel1">EC2: ${ec.TX_COMPONENT_KEY}</h4>
																							                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																													      </div>
																													      <div class="modal-body">
																													     	  <div>
																									                               <h4>
																										                                <div>
																										                                	Name: <span class="text-primary">${ec.TX_COMPONENT_NAME}</span>
																										                                </div>
																										                                
																										                                <div>
																										                                	Key: <span class="text-primary">${ec.TX_COMPONENT_KEY}</span>
																										                                </div>
																										                                
																										                                <div>
																										                                	IPv4 Address: <span class="text-primary">${ec.TX_IP}</span>
																										                                </div>
																										                                
																										                                <div>
																										                                	Subnet:	<span class="text-primary">${ec.TX_COMPUTE_EC2_SUBNET_KEY}</span>
																										                                </div>
																										                                
																										                                <div>
																										                                	Security Group:	<span class="text-primary">${ec.TX_COMPUTE_EC2_SEC_GROUP_KEY}</span>
																										                                </div>
																									                                </h4>
																							                                	</div>
																													      </div>
																													      <div class="modal-footer">
																													      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																							                              </div>
																													    </div>
																													  </div>
																													</div>
								                                                        							
								                                                        							
								                                                        							
								                                                        							
								                                                        							
								                                                        							
							                                                        							</c:if>
								                                                        					</c:forEach>
																										</tbody>
																									</table>			                                                        					
						                                                        				</c:if>
						                                                        				<c:if test="${e2Count==0}">
						                                                        					<span class="text-danger lead"> Not defined</span>
						                                                        				</c:if>
						                                                        				<!-- EC2 Modal -->
						                                                        				<div class="modal fade" id="ec2Modal" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" id="linkEC2Form" action="${contextPath}/admin/settings/cloud/aws/vpc/addLinkEC2" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">EC2 for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								     	  <div class="form-group">
																								     	  
																									     	  	<div class="mb-2">
																								     	  			<h4 class="control-label">Create new EC2</h4>
																								     	  			<label class="custom-toggle">
																			                                			<input type="checkbox" id="CREATE_NEW_FLAG_${vpcitem.ID_COMPONENT}" name="${vpcitem.ID_COMPONENT}" value="" onclick="updateEC2Action(this);">
																			                                			<span class="custom-toggle-slider rouded-circle"></span>
																										  			</label>
																								     	  		</div>
																								     	  		<script>
																								     	  			function updateEC2Action(element){
																								     	  				var textelementidentifier = "TX_COMPONENT_NAME_EC2_"+element.name;
																								     	  				var keyelementidentifier = "TX_COMPONENT_KEY_EC2_"+element.name;
																								     	  				var actionelementidentifier = "TX_ACTION_EC2_"+element.name;
																								     	  				var ipelementidentifier = "TX_IP_EC2_"+element.name;
																								     	  				if(element.checked == true){
																								     	  					document.getElementById(textelementidentifier).value = "";
																								     	  					document.getElementById(keyelementidentifier).value = "";
																								     	  					document.getElementById(ipelementidentifier).value = "";
																								     	  					document.getElementById(textelementidentifier).disabled = true;
																								     	  					document.getElementById(keyelementidentifier).disabled = true;
																								     	  					document.getElementById(ipelementidentifier).disabled = true;
																								     	  					document.getElementById(textelementidentifier).required = false;
																								     	  					document.getElementById(keyelementidentifier).required= false;
																								     	  					document.getElementById(ipelementidentifier).required = false;
																								     	  					document.getElementById(actionelementidentifier).value = "CREATE";
																								     	  				}else{
																								     	  					document.getElementById(textelementidentifier).disabled = false;
																								     	  					document.getElementById(keyelementidentifier).disabled = false;
																								     	  					document.getElementById(ipelementidentifier).disabled = false;
																								     	  					document.getElementById(textelementidentifier).required = true;
																								     	  					document.getElementById(keyelementidentifier).required= true;
																								     	  					document.getElementById(ipelementidentifier).required = true;
																								     	  					document.getElementById(actionelementidentifier).value = "LINK";
																								     	  				}
																								     	  			}
																								     	  		</script>
																								     	  		<hr/>
																								     	  		<div class="mb-2">
																								     	  			<h4 class="control-label">Link Existing EC2</h4>
																								     	  		</div>
																				                               <div>
																				                                	<label for="TX_COMPONENT_NAME" class="control-label">EC2 Name: </label>
																				                                	<input type="text" id="TX_COMPONENT_NAME_EC2_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_NAME" class="form-control">
																				                                </div>
																				                                
																				                                <div>
																				                                	<label for="TX_COMPONENT_KEY_EC2_${vpcitem.ID_COMPONENT}" class="control-label">EC2 Key / ID: </label>
																				                                	<input type="text" id="TX_COMPONENT_KEY_EC2_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_KEY" class="form-control">
																				                                </div>
																				                                <div>
																				                                	<label for="TX_IP_EC2_${vpcitem.ID_COMPONENT}" class="control-label">IPv4 Addr: </label>
																				                                	<input type="text" id="TX_IP_EC2_${vpcitem.ID_COMPONENT}" name="TX_IP" class="form-control">
																				                                </div>
																				                                  <div>
																				                                	<label for="TX_SUBNET_EC2_${vpcitem.ID_COMPONENT}" class="control-label">Select Subnet: <span class="text-danger">*</span></label>
																				                                	<select class="form-control"  id="TX_SUBNET_EC2_${vpcitem.ID_COMPONENT}" name="ID_COMPUTE_EC2_SUBNET" >
																					                                	<option value="">-</option>
																													       	<c:forEach var="subnet" items="${subnetList}">
																													     	 	<option value="${subnet.ID_COMPONENT}"  ${subnet.FL_DEFAULT == true ? 'selected="selected"' : ''}>${subnet.TX_COMPONENT_NAME} [ ${subnet.TX_COMPONENT_KEY} ]</option>
																												         	</c:forEach>
																												    </select>
																				                                </div>
																				                                  <div>
																				                                	<label for="TX_COMPONENT_KEY" class="control-label">Select Security Group: <span class="text-danger">*</span></label>
																				                                	<select class="form-control"  id="TX_SECGRP_EC2_${vpcitem.ID_COMPONENT}" name="ID_COMPUTE_EC2_SEC_GROUP" >
																					                                	<option value="">-</option>
																													       	<c:forEach var="sg" items="${sgList}">
																													     	 	<option value="${sg.ID_COMPONENT}">${sg.TX_COMPONENT_NAME} [ ${sg.TX_COMPONENT_KEY} ]</option>
																												         	</c:forEach>
																												    </select>
																				                                </div>
																				                                <div>
																				                                	<label for="TX_COMPONENT_KEY" class="control-label">Server Status: <span class="text-danger">*</span></label>
																				                                	<select class="form-control"  id="FL_EC2_STATUS_${vpcitem.ID_COMPONENT}" name="FL_STATUS" >
																					                                	<option value="">-</option>
																													    <option value="TRUE">Started / Running</option>
																													    <option value="FALSE">Stopped</option>
																												    </select>
																				                                </div>
																				                                <div>
																				                                	<input type="text" id="ID_PARENT_EC2_${vpcitem.ID_COMPONENT}" name="ID_PARENT" value="${vpcitem.ID_COMPONENT}" style="visibility:hidden">
																				                                	<input type="text" id="TX_ACTION_EC2_${vpcitem.ID_COMPONENT}" name="TX_ACTION" value="LINK"  style="visibility:hidden">
																				                                	<input type="text" id="TX_DEFAULT_EC2_${vpcitem.ID_COMPONENT}" name="FL_DEFAULT" value=""  style="visibility:hidden">
																				                                </div>
																				                                
																				                                <div>
																				                                	<span class="text-danger h6" id="ec2-error-msg"></span>
																				                                </div>
																				                            </div>
																								      </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="button" class="btn btn-primary" name="${vpcitem.ID_COMPONENT}" onclick="validateECSubmit(this);">Submit</button> 
																		                            	 	
																		                            	 	<script>
																										 		function validateECSubmit(element){
																										 			var errmsg = "";
																										 			var errElement = document.getElementById("ec2-error-msg");
																										 			var subnetElement = document.getElementById("TX_SUBNET_EC2_"+element.name);
																										 			var sgElement = document.getElementById("TX_SECGRP_EC2_"+element.name);
																										 			
																										 			var newCheck = document.getElementById("CREATE_NEW_FLAG_"+element.name)
																										 			var textelementidentifier = document.getElementById("TX_COMPONENT_NAME_EC2_"+element.name);
																							     	  				var keyelementidentifier = document.getElementById("TX_COMPONENT_KEY_EC2_"+element.name);
																							     	  				var ipelementidentifier = document.getElementById("TX_IP_EC2_"+element.name);
																										 			var ec2statusidentifier = document.getElementById("FL_EC2_STATUS_"+element.name);
																							     	  				
																							     	  				if(newCheck.checked == false){
																							     	  					if(textelementidentifier.value==""){
																										 					errmsg = "EC2 name cannot be Empty. ";
																											 			}else if(keyelementidentifier.value==""){
																											 				errmsg = "EC2 Key / ID cannot be Empty.";
																											 			}else if(ipelementidentifier.value==""){
																											 				errmsg = "IPv4 address cannot be Empty.";
																											 			}
																										 				
																										 			}
																										 			
																										 			if(subnetElement.value=="" || subnetElement.value=="-"){
																										 				errmsg = "Subnet cannot be Empty. ";
																										 			}else if(sgElement.value=="" || sgElement.value=="-"){
																										 				errmsg = "Security Group cannot be Empty.";
																										 			}else if(ec2statusidentifier.value=="" || ec2statusidentifier.value=="-"){
																										 				errmsg = "Please select current server state.";
																										 			}
																										 			
																										 			if(errmsg!=""){
																										 				$("#ec2-error-msg").text(errmsg);
																										 			}else{
																										 				document.getElementById("linkEC2Form").submit();
																										 			}
																										 		}
																										 	</script>
																										 	
																		                              </div>
																								      </form>
																								    </div>
																								  </div>
																								</div>
							                                                        			
							                                                        		</div>
							                                                        	</div>
							                                                        	<div class="col-lg-3 col-sm-12 table-info">
							                                                        		<div class="p-2">
							                                                        			<h4>
							                                                        				<button class="btn btn-sm btn-outline-primary" data-toggle="modal" data-target="#naclModal"><i class="fas fa-edit"></i> </button>
							                                                        				NACL: <br/>
							                                                        				<c:set var="naclCount" value="0" />
							                                                        				<c:if test="${not empty naclList}">
								                                                        					<c:forEach var="nacl" items = "${naclList}">
								                                                        						<c:if test="${nacl.ID_PARENT==vpcitem.ID_COMPONENT}">
								                                                        							<span class="text-primary h6">${nacl.TX_COMPONENT_NAME} </span><small> [ ${nacl.TX_COMPONENT_KEY} ]</small>
								                                                        							<c:set var="naclCount" value="1" />
								                                                        						</c:if>
								                                                        					</c:forEach>
								                                                        				</c:if>
								                                                        				<c:if test="${naclCount==0}">
								                                                        					<span class="text-danger lead"> Not defined</span>
								                                                        				</c:if>
							                                                        			</h4>
							                                                        			<!-- Edit NACL Modal -->
						                                                        				<div class="modal fade" id="naclModal" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/updateNACL" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">NACL for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								     	  <div class="form-group">
																				                               <div>
																				                                	<label for="TX_COMPONENT_NAME" class="control-label">NACL Name: </label>
																				                                	<input type="text" id="TX_COMPONENT_NAME_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_NAME" class="form-control">
																				                                </div>
																				                                
																				                                <div>
																				                                	<label for="TX_COMPONENT_KEY" class="control-label">NACL Key / ID: </label>
																				                                	<input type="text" id="TX_COMPONENT_KEY_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_KEY" class="form-control">
																				                                </div>
																				                                <div>
																				                                	<input type="text" id="ID_PARENT_${vpcitem.ID_COMPONENT}" name="ID_PARENT" value="${vpcitem.ID_COMPONENT}" style="visibility:hidden">
																				                                </div>
																				                            </div>
																								      </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="submit" class="btn btn-primary" >Submit</button> 
																								      </div>
																								      </form>
																								    </div>
																								  </div>
																								</div>
							                                                        		</div>
							                                                        		<div class="mb-4 p-2">
							                                                        			<h4>
							                                                        				<button class="btn btn-sm btn-outline-primary"  data-toggle="modal" data-target="#routeTableModal"><i class="fas fa-edit"></i> </button>
							                                                        				Route Table: <br/>
							                                                        				<c:set var="rtCount" value="0" />
							                                                        				<c:if test="${not empty rtList}">
								                                                        					<c:forEach var="rt" items = "${rtList}">
								                                                        						<c:if test="${rt.ID_PARENT==vpcitem.ID_COMPONENT}">
								                                                        							<span class="text-primary h6">${rt.TX_COMPONENT_NAME} </span><small> [ ${rt.TX_COMPONENT_KEY} ]</small>
								                                                        							<c:set var="rtCount" value="1" />				
								                                                        						</c:if>
								                                                        					</c:forEach>
								                                                        				</c:if>
								                                                        				<c:if test="${rtCount==0}">
								                                                        					<span class="text-danger lead"> Not defined</span>
								                                                        				</c:if>
						                                                        				</h4>
						                                                        				<!-- Edit Route Table Modal -->
						                                                        				<div class="modal fade" id="routeTableModal" tabindex="-1" role="dialog">
																								  <div class="modal-dialog" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/updateRouteTable" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Route Table for VPC: ${vpcitem.TX_COMPONENT_KEY}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								     	  <div class="form-group">
																				                               <div>
																				                                	<label for="TX_COMPONENT_NAME" class="control-label">Route Table Name: </label>
																				                                	<input type="text" id="TX_COMPONENT_NAME_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_NAME" class="form-control">
																				                                </div>
																				                                
																				                                <div>
																				                                	<label for="TX_COMPONENT_KEY" class="control-label">Link Route Table Key / ID: </label>
																				                                	<input type="text" id="TX_COMPONENT_KEY_${vpcitem.ID_COMPONENT}" name="TX_COMPONENT_KEY" class="form-control">
																				                                </div>
																				                                <div>
																				                                	<input type="text" id="ID_PARENT_${vpcitem.ID_COMPONENT}" name="ID_PARENT" value="${vpcitem.ID_COMPONENT}" style="visibility:hidden">
																				                                </div>
																				                            </div>
																								      </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="submit" class="btn btn-primary" >Submit</button> 
																								      </div>
																								      </form>
																								    </div>
																								  </div>
																								</div>
							                                                        		</div>
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		<!--  here -->
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        		
							                                                        	</div>
							                                                        
							                                                        </div>
							                                                    </div>
							                                                </div>
							                                            </div>
						                                            </div>
					                                            </c:forEach>
				                                            </div>
														
														</c:if>
														<c:if test="${empty vpcList}">
															<h5 class="text-danger">There are no VPC's defined</h5>	
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