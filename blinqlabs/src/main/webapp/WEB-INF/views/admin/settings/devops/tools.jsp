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
		
		<script>
		
	    
	      function imageIsLoaded() { 
			 // alert(this.src);  // blob url
			  // update width and height ...
			  toDataURL(this.src, function(dataUrl) {
				  console.log('RESULT:', dataUrl);
				  document.getElementById("TX_DEVOPS_TOOL_IMAGE").value=dataUrl;
				})
			}
			
			function toDataURL(url, callback) {
				  var xhr = new XMLHttpRequest();
				  xhr.onload = function() {
				    var reader = new FileReader();
				    reader.onloadend = function() {
				      callback(reader.result);
				    }
				    reader.readAsDataURL(xhr.response);
				  };
				  xhr.open('GET', url);
				  xhr.responseType = 'blob';
				  xhr.send();
				}
		</script>
		
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
									<jsp:include page="/WEB-INF/views/admin/settings/navs/applicationSideNav.jsp" />
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
											
											<div class="text-right">
												<button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#addToolModal"><i class="fas fa-plus-circle"></i> New Tool</button>
											</div>
											
											<!-- add Tool Modal -->
												
												<div class="modal fade" id="addToolModal" tabindex="-1" role="dialog">
												  <div class="modal-dialog modal-lg" role="document">
												    <div class="modal-content">
												    <form class="form-horizontal m-t-20" id="addToolsForm" action="${contextPath}/admin/settings/devops/tools/addTool" method="POST" name="DevOpsToolsForm" modelAttribute="devOpsToolsBean">
													      <div class="modal-header">
													        	<h4 class="modal-title" id="exampleModalLabel1">Add Tool</h4>
							                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
													      </div>
													      <div class="modal-body">
													     	  <div >
													     	  
													     	  	<div>
													     	  		<div>
									     								<div class="card-body">
											                            	<div>
												                            	<img alt="header" src="${pageContext.request.contextPath}/resources/images/software.png" id="tool-img" width="200" />
											                            	</div>
											                            	<div class=" mt-2 p-2 text-right">
											                            		<button class="btn btn-outline-primary" type="button" id="change-image-btn">Tool Image</button>
											                            	</div>
											                            	<div>
												                            	<input type="text" id="TX_DEVOPS_TOOL_IMAGE" name="TX_DEVOPS_TOOL_IMAGE" path="TX_DEVOPS_TOOL_IMAGE" style="visibility:hidden">
											                            	</div>
																	    </div>
																    </div>
																    
																      <div class="card hidden-element">
												                            <div class="card-body">
												                                <h4 class="card-title">Choose Image</h4>
												                                    <fieldset class="form-group">
												                                        <input type="file" class="form-control-file" id="toolbgFile">
												                                    </fieldset>
												                            </div>
												                        </div>
													     	  	
													     	  		
													     	  		
													     	  		 <script>
																	      window.addEventListener('load', function() {
																	    	  
																	    	  document.querySelector('input[type="file"]').addEventListener('change', function() {
															    			      if (this.files && this.files[0]) {
															    			          var img = document.getElementById("tool-img");  // $('img')[0]
															    			          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
															    			          img.onload = imageIsLoaded;
															    			      }
															    			  });
															        			  
															        			  
																		      document.getElementById("change-image-btn").addEventListener('click', function() {
																				  document.getElementById("toolbgFile").click();
																			      if (this.files && this.files[0]) {
																			          var img = document.getElementById("tool-img");  // $('img')[0]
																			          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
																			          img.onload = imageIsLoaded;
																			      }
																			  });
																	      
																	      });
																	      </script>
													     	  	
													     	  		</div>
													     	  
													     	  
													     	  
														     	 <!-- Nav tabs -->
																	<ul class="nav nav-tabs" role="tablist">
																	    <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#add-tool-info" role="tab"><span class="hidden-xs-down">Info</span></a> </li>
																	    <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#add-tool-config" role="tab"><span class="hidden-xs-down">Config</span></a> </li>
																	</ul>
																	<!-- Tab panes -->
																	<div class="tab-content tabcontent-border">
																	    <div class="tab-pane active" id="add-tool-info" role="tabpanel">
																	        <div class="p-20 m-3">
																	            <div class="form-group">
												                                	<label for="TX_CATEGORY" class="control-label">Tool Name <span class="text-danger"> *</span></label>
												                                	<input type="text" class="form-control" id="TX_DEVOPS_TOOL_NAME" name="TX_DEVOPS_TOOL_NAME" >
										    	                                </div>
												                                
												                                 <div class="form-group">
												                                	<label for="TX_CATEGORY" class="control-label">Source location <span class="text-danger"> *</span></label>
												                                	<input type="text" class="form-control" id="TX_DEVOPS_TOOL_DOWNLOAD_LOC" name="TX_DEVOPS_TOOL_DOWNLOAD_LOC" >
										    	                                </div>
										    	                                
										    	                                 <div class="form-group">
												                                	<label for="TX_PORT" class="control-label">Service Port <span class="text-danger"> *</span></label>
												                                	<input type="text" class="form-control" id="TX_PORT" name="TX_PORT" placeholder="8080">
										    	                                </div>
										    	                                
												                                <div class="form-group">
												                                	<label for="machine-id" class="control-label">Category <span class="text-danger"> *</span></label>
												                                	<select class="form-control" id="ID_CATEGORY" name="ID_CATEGORY" >
												                                		<option>-</option>
												                                		<c:if test="${not empty catList}">
																							<c:forEach var="cat" items="${catList}">
																								<option value="${cat.ID}">
																									 <c:if test="${cat.FL_STATUS==false}"><span class="">${cat.TX_CATEGORY}</span><span class="">   [Disabled]</span></c:if>
																									 <c:if test="${cat.FL_STATUS==true}"><span class="text-primary strong">${cat.TX_CATEGORY}</span></c:if>
																								
																								</option>
																							</c:forEach>
																						</c:if>
												                                	</select>
												                                </div>
												                            </div>
																	    </div>
																    <div class="tab-pane  p-20" id="add-tool-config" role="tabpanel">
																	     <div class="p-20 m-3">
																     		<div class="card">
												                            	<div class="card-body">
												                                	<h4 class="card-title">Setup Configurations</h4>
													                             	
														                             	<div id="education_fields" class="m-t-20"></div>
														                             	<hr/>
													                                  		
														                               			 <div class="row">
															                               			 
															                               			 <div class="col-md-12">
															                               			    <div class="form-group">
															                               			    	<label for="TX_TYPE">Name:</label>
																                                            <input type="text" class="form-control" id="TX_CONFIG_NAME" name="TX_CONFIG_NAME" placeholder="Config Name">
																                                        </div>
																                                        <div class="form-group">
																										    <label for="TX_TYPE">Config Type:</label>
																										    <select class="form-control" id="TX_TYPE" name="TX_TYPE" onchange="updateInput(this);">
																										      <option value="-" selected:selected>-</option>
																										      <option value="SCRIPT">Inline Script</option>
																										      <option value="FILE">File</option>
																										    </select>
																										  </div>
																										  
																									  <div class="form-group" id="script-div">
																									  		<label for="TX_TYPE">Script:</label>
																                                            <textarea  class="form-control hidden" id="TX_COMMAND" name="TX_COMMAND" ></textarea>
																                                        </div>	  
																                                       <div class="form-group"  id="file-div">
																                                       		<label for="TX_TYPE">File Path:</label>
																                                            <input type="text" class="form-control hidden" id="TX_FILE" name="TX_FILE" placeholder="/file/path">
																                                        </div>
																                                     </div>
														                               			</div>
														                               			<div>
														                               			 <div class="col-md-12 text-right">
																                                        <div class="form-group">
																                                            <button class="btn btn-success" type="button" onclick="education_fields();"><i class="fa fa-plus"></i> Add</button>
																                                        </div>
																                                    </div>
														                               			</div>
														                                		
														                                		<hr/>
														                                		
														                                		<input type="text" class="form-control"  id="TX_CONFIG" name="TX_CONFIG" value="" style="visibility:hidden">
														                                		<input type="button" id="sa-basic" style="visibility:hidden">
														                             </div>
															                     </div>
																	     </div>
																    </div>
																</div>
															   <div>
								                                	<span class="text-danger h6" id="form-add-error"></span>
								                                </div>
															  </div>
													      </div>
													      <div class="modal-footer">
													      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							                            	 	<button type="button" class="btn btn-primary" onclick="validateAdd();">Add</button> 
													      </div>
												      </form>
												    </div>
												  </div>
												</div>
												
											<div>
												<c:if test="${not empty catList}">
													<c:forEach var="cat" items="${catList}">
														<section class="m-4 p-4">
																	 <div class="p-2">
																	 	<div class="row table-info pt-2">
																	 		<div class="col-lg-6 col-sm 12">
																				<p class="h4 text-dark">${cat.TX_CATEGORY}</p>
																	 		</div>
																	 		<div class="col-lg-6 col-sm 12">
																		 		<div class="text-right">
																		 		
																		 			<label class="custom-toggle">
											                                			<c:if test="${true!=cat.FL_STATUS}">
											                                				<input type="checkbox" id="${cat.TX_CATEGORY}" value="0" disabled >
											                                			</c:if>
											                                			<c:if test="${true==cat.FL_STATUS}">
											                        						<input type="checkbox" id="${cat.TX_CATEGORY}" value="1" checked disabled>
											                                			</c:if>
										                                			 	<span class="custom-toggle-slider rounded-circle"></span>
																		  			</label>
																	  			</div>
																	 		</div>
																	 	</div>
																	</div>
																	
																	 <c:if test="${empty toolList}">
																		<span>No Configuration found.</span>
																	</c:if>
																	
																	<c:if test="${not empty toolList}">
																	     
							                                        	<c:forEach var="entry" items="${toolList}">
							                                        		<c:if test="${entry.TX_CATEGORY==cat.TX_CATEGORY}">
							                                        		
							                                        			<div class="row m-4">
							                                        			
								                                        			<div class="col-md-8 col-sm-8">
								                                        				<div class="row">
								                                        					<div class="col-md-7">
								                                        							<p>
												                                        				<c:if test="${not empty  entry.TX_DEVOPS_TOOL_IMAGE}">
												                                        					<img src="${entry.TX_DEVOPS_TOOL_IMAGE}" width="45">
												                                        				</c:if>
												                                        				<c:if test="${empty  entry.TX_DEVOPS_TOOL_IMAGE}">
												                                        					<img src="${pageContext.request.contextPath}/resources/images/software.png" width="45">
												                                        				</c:if>
											                                        				 <span class="h4 text-primary ml-4">  ${entry.TX_DEVOPS_TOOL_NAME}</span>
										                                        					</p>
										                                        					<p>
										                                        						<span class="lead">${entry.TX_DEVOPS_TOOL_DOWNLOAD_LOC}</span>
										                                        					</p>
								                                        					</div>
								                                        					<div class="col-md-5">
								                                        						<p>Port: ${entry.TX_PORT}</p>
								                                        						<c:if test="${not empty entry.CONFIG}">
								                                        							<p>Config: <button type="button"class="btn btn-outline-light" data-toggle="modal" data-target="#viewToolConfig_${entry.ID_DEVOPS_TOOL}"><span class="text-primary"><i class="fas fa-eye"></i></span></button></p>
								                                        							
								                                        							<!-- View Config Modal -->
								                                        							<div class="modal fade" id="viewToolConfig_${entry.ID_DEVOPS_TOOL}" tabindex="-1" role="dialog">
																								  <div class="modal-dialog modal-lg" role="document">
																								    <div class="modal-content">
																								    <form class="form-horizontal m-t-20" id="toolConfigUpdateForm_${entry.ID_DEVOPS_TOOL}" action="${contextPath}/admin/settings/devops/tools/updateToolConfig" method="POST" name="DevOpsToolsForm" modelAttribute="devOpsToolsBean">
																								      <div class="modal-header">
																								        	<h4 class="modal-title">Setup Configuration for ${entry.TX_DEVOPS_TOOL_NAME}</h4>
																		                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																								      </div>
																								      <div class="modal-body">
																								      		
																								      		<c:set var="confCount" value="0"/>
																								      		<c:forEach var="confItem" items="${entry.CONFIG}" varStatus="loop">
																								      		<c:set var="confCount" value="${loop.index+1}"/>
																												<div class="m-2 m-2" id="viewToolConfig_${loop.index}">
																													<div class="table-active">
																														<div class="row ">
																															<div class="col-lg-10 col-sm-12">
																																<div class="row p-2">
																																	<div class="col-lg-2 col-sm-2">
																																		${loop.index+1}
																																	</div>
																													      			<div class="col-lg-5 col-sm-6">
																													      				<span>Name: <span class="text-primary">${confItem.TX_CONFIG_NAME}</span></span>
																													      			</div>
																													      			<div class="col-lg-5 col-sm-4">
																													      				<span>Type: <span class="text-primary">${confItem.TX_TYPE}</span></span>
																													      			</div>
																													      		</div>
																													      		<div class="row p-2">
																													      			<div class="col-lg-12">
																													      				<c:if test="${confItem.TX_TYPE=='SCRIPT'}">
																													      					<textarea class="form-control" id="toolConfigScript_${loop.index}"  onchange="fieldUpdated(${loop.index});">${confItem.TX_COMMAND}</textarea>
																													      				</c:if>
																													      				<c:if test="${confItem.TX_TYPE=='FILE'}">
																														      				<input class="form-control" type="text" id="toolConfigCommand_${loop.index}" value="${confItem.TX_FILE}" onchange="fieldUpdated(${loop.index});">
																													      				</c:if>
																													      			</div>
																													      		</div>
																															</div>
																															<div class="col-lg-2 col-sm-12 text-right">
																																<buttom type="button" class="btn btn-danger" onclick="modify_Config(${loop.index});" id="deleteConfig_${loop.index}"><i class="fas fa-trash-alt"></i></buttom>
																																<buttom type="button" class="btn btn-warning" onclick="reset_Config(${loop.index});" id="resetConfig_${loop.index}" style="display:none"><i class="far fa-window-close"></i></buttom>
																															</div>
																														</div>
																														<div class="row">
																															<div class="col-lg-8 col-sm-8">
																																<div class="p-3">
																													      			<label id="toolConfigActionLabel_${loop.index}" class="text-right text-danger"></label>
																													      			<input type="text" id="toolConfigAction_${loop.index}" name="toolConfigAction_${loop.index}" value="" style="display:none">
																													      			<input type="text" id="toolConfigId_${loop.index}" value="${confItem.ID}" style="display:none"/>
																													      		</div>
																												      		</div>
																												      		<div class="col-lg-4 col-sm-4">
																												      		
																												      		</div>
																											      		</div>
																										      		</div>
																													<hr/>
																								      			</div>
																								      		</c:forEach>
																								      		<input type="text" id="TX_CONFIG_EDIT_${entry.ID_DEVOPS_TOOL}" name="TX_CONFIG" value="" style="visibility:hidden">
																								      		<input type="text" id="ID_DEVOPS_TOOL_EDIT_${entry.ID_DEVOPS_TOOL}" name="ID_DEVOPS_TOOL" value="${entry.ID_DEVOPS_TOOL}" style="visibility:hidden">
																								      		
																								      		
																								      		
																								      		<div class="card-body">
																			                                	<h4 class="card-title">Add Configurations</h4>
																				                             	
																					                             	<div id="setup_fields" class="m-t-20"></div>
																					                             	<hr/>
																				                                  		
																			                               			 <div class="row">
																				                               			 
																				                               			 <div class="col-md-12">
																				                               			    <div class="form-group">
																				                               			    	<label for="TX_TYPE">Name:</label>
																					                                            <input type="text" class="form-control" id="TX_CONFIG_NAME_NEW" name="TX_CONFIG_NAME" placeholder="Config Name">
																					                                        </div>
																					                                        <div class="form-group">
																															    <label for="TX_TYPE_NEW">Config Type:</label>
																															    <select class="form-control" id="TX_TYPE_NEW" name="TX_TYPE" onchange="updateInputNew(this);">
																															      <option value="-" selected:selected>-</option>
																															      <option value="SCRIPT">Inline Script</option>
																															      <option value="FILE">File</option>
																															    </select>
																															  </div>
																															  
																														  <div class="form-group" id="script-div-new">
																														  		<label for="TX_COMMAND_NEW">Script:</label>
																					                                            <textarea  class="form-control hidden" id="TX_COMMAND_NEW" name="TX_COMMAND" ></textarea>
																					                                        </div>	  
																					                                       <div class="form-group"  id="file-div-new">
																					                                       		<label for="TX_FILE_NEW">File Path:</label>
																					                                            <input type="text" class="form-control hidden" id="TX_FILE_NEW" name="TX_FILE" placeholder="/file/path">
																					                                        </div>
																					                                     </div>
																			                               			</div>
																			                               			<div>
																			                               			 <div class="col-md-12 text-right">
																					                                        <div class="form-group">
																					                                            <button class="btn btn-success" type="button" onclick="config_fields();"><i class="fa fa-plus"></i> Add</button>
																					                                        </div>
																					                                    </div>
																			                               			</div>
																			                                		
																			                                		<hr/>
																			                                		
																			                                		<input type="text" class="form-control"  id="TX_CONFIG_NEW" name="TX_CONFIG" value="" style="visibility:hidden">
																			                                		<input type="button" id="sa-basic-new" style="visibility:hidden">
																			                                		<input type="text" class="form-control"  id="ID_DEVOPS_TOOL_NEW" name="ID_DEVOPS_TOOL" value="${entry.ID_DEVOPS_TOOL}" style="visibility:hidden">
																			                             </div>
																								      		
																								      		
																								      		
																								      </div>
																								      <div class="modal-footer">
																								      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																		                            	 	<button type="button" class="btn btn-primary" onclick="updateToolConfig(${entry.ID_DEVOPS_TOOL},${confCount});">Save</button> 
																								      </div>
																							      </form>
																							      </div>
																						      	</div>
																						      </div>
								                                        							
								                                        						</c:if>
								                                        					</div>
								                                        				</div>
								                                        			
																					</div>
				
								                                        			<div class="col-md-4 col-sm-4 text-right">
								                                        				<div class="text-right">
								                                        					<div class="m-2">
																								<form action="#" method="POST" id="categoryToolForm_${entry.ID_DEVOPS_TOOL}">
																									<label class="custom-toggle">
															                                			<c:if test="${true!=entry.FL_DEVOPS_TOOL_STATUS}">
															                                				<input type="checkbox" onclick="saveCategory(this);" id="${entry.ID_DEVOPS_TOOL}" name="${entry.TX_DEVOPS_TOOL_NAME}_${entry.ID_DEVOPS_TOOL}" path="${entry.TX_DEVOPS_TOOL_NAME}_${entry.ID_DEVOPS_TOOL}" value="0" >
															                                			</c:if>
															                                			<c:if test="${true==entry.FL_DEVOPS_TOOL_STATUS}">
															                        						<input type="checkbox" onclick="saveCategory(this);" id="${entry.ID_DEVOPS_TOOL}" name="${entry.TX_DEVOPS_TOOL_NAME}_${entry.ID_DEVOPS_TOOL}" path="${entry.TX_DEVOPS_TOOL_NAME}_${entry.ID_DEVOPS_TOOL}" value="1" checked >
															                                			</c:if>
														                                			 	<span class="custom-toggle-slider rounded-circle"></span>
																						  			</label>
																					  			</form>
																					  			<script>
																					  				function saveCategory(el){
																					  					var form = "categoryToolForm_"+el.id;
																					  					document.getElementById(form).action = "${contextPath}/admin/settings/devops/tools/updateTool?id="+el.id+"&value="+el.value;
																					  					document.getElementById(form).submit();
																					  				}
																					  			</script>
																				  			
								                                        						 <button type="button" class="btn btn-outline-primary"   data-toggle="modal" data-target="#editToolModal_${entry.ID_DEVOPS_TOOL}"><i class="fas fa-edit"></i></button> 
								                                        					</div>
								                                        				</div>
								                                        				
								                                        				
								                                        				<!-- Edit Modal -->
																									
																									<div class="modal fade" id="editToolModal_${entry.ID_DEVOPS_TOOL}" tabindex="-1" role="dialog">
																									  <div class="modal-dialog" role="document">
																									    <div class="modal-content">
																									    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/devops/tools/updateToolInfo" method="POST" name="DevOpsToolsForm" modelAttribute="devOpsToolsBean">
																									      <div class="modal-header">
																									        	<h4 class="modal-title" id="exampleModalLabel1">Edit ${entry.TX_DEVOPS_TOOL_NAME}</h4>
																			                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																									      </div>
																									      <div class="modal-body">
																									     	  <div >
																									     	  
																									     	  	<div>
																									     	  		<div>
																					     								<div class="card-body">
																							                            	<div>
																							                            		<c:if test="${not empty entry.TX_DEVOPS_TOOL_IMAGE}">
                																									                <img alt="header" src="${entry.TX_DEVOPS_TOOL_IMAGE}" id="tool-img_${entry.ID_DEVOPS_TOOL}" width="300" />
																							                            		</c:if>
																							                            		<c:if test="${empty entry.TX_DEVOPS_TOOL_IMAGE}">
																								                            		<img alt="header" src="${pageContext.request.contextPath}/resources/images/software.png" id="tool-img_${entry.ID_DEVOPS_TOOL}" width="300" />
																							                            		</c:if>
																							                            	</div>
																							                            	<div class=" mt-2 p-2 text-right">
																							                            		<button class="btn btn-outline-primary" type="button" id="change-image-btn_${entry.ID_DEVOPS_TOOL}">Tool Image</button>
																							                            	</div>
																							                            	<div>
																								                            	<input type="text" id="TX_DEVOPS_TOOL_IMAGE_${entry.ID_DEVOPS_TOOL}" name="TX_DEVOPS_TOOL_IMAGE" path="TX_DEVOPS_TOOL_IMAGE" value=" ${entry.TX_DEVOPS_TOOL_IMAGE}" style="visibility:hidden">
																							                            	</div>
																													    </div>
																												    </div>
																												    
																												      <div class="card hidden-element">
																								                            <div class="card-body">
																								                                <h4 class="card-title">Choose Image</h4>
																								                                    <fieldset class="form-group">
																								                                        <input type="file" class="form-control-file" id="toolbgFile_${entry.ID_DEVOPS_TOOL}">
																								                                    </fieldset>
																								                            </div>
																								                        </div>
																									     	  	
																									     	  		
																									     	  		
																									     	  		 <script>
																													      window.addEventListener('load', function() {
																													    	  
																													    	  document.getElementById("toolbgFile_${entry.ID_DEVOPS_TOOL}").addEventListener('change', function() {
																													    		  if (this.files && this.files[0]) {
																											    			          var img = document.getElementById("tool-img_${entry.ID_DEVOPS_TOOL}");  // $('img')[0]
																											    			          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
																											    			          img.onload = imageIsLoaded;
																											    			      }
																											    			  });
																											        			  
																											        			  
																														      document.getElementById("change-image-btn_${entry.ID_DEVOPS_TOOL}").addEventListener('click', function() {
																														    	  document.getElementById("toolbgFile_${entry.ID_DEVOPS_TOOL}").click();
																														    	  if (this.files && this.files[0]) {
																														    		  var img = document.getElementById("tool-img_${entry.ID_DEVOPS_TOOL}");  // $('img')[0]
																															          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
																															          img.onload = imageIsLoaded;
																															      }
																															  });
																													      
																													      });
																													      
																													      </script>
																									     	  	
																									     	  	</div>
																									     	        
																					                                 <div class="form-group">
																					                                	<label for="TX_CATEGORY" class="control-label">Source location</label>
																					                                	<input type="text" class="form-control" id="TX_DEVOPS_TOOL_DOWNLOAD_LOC_${entry.ID_DEVOPS_TOOL}" name="TX_DEVOPS_TOOL_DOWNLOAD_LOC" value="${entry.TX_DEVOPS_TOOL_DOWNLOAD_LOC}">
																			    	                                </div>
																			    	                                
																			    	                                 <div class="form-group">
																						                                	<label for="TX_PORT" class="control-label">Service Port</label>
																						                                	<input type="text" class="form-control" id="TX_PORT_${entry.ID_DEVOPS_TOOL}" name="TX_PORT" value="${entry.TX_PORT}">
																				    	                                </div>
																				    	                                
																					                                <div class="form-group">
																					                                	<label class="control-label">Category</label>
																					                                	<select class="form-control" id="ID_CATEGORY_${entry.ID_DEVOPS_TOOL}" name="ID_CATEGORY" >
																					                                		<option>-</option>
																					                                		<c:if test="${not empty catList}">
																																<c:forEach var="category" items="${catList}">
																																	<option value="${category.ID}" ${category.TX_CATEGORY == entry.TX_CATEGORY ? 'selected="selected"' : ''}>
																																		<p class="">${category.TX_CATEGORY} <c:if test="${category.FL_STATUS==false}"> [Disabled]</c:if></p>
																																	</option>
																																</c:forEach>
																															</c:if>
																					                                	
																					                                	</select>
																					                                </div>
																					                                
																					                                <div>
																					                                	<input type="text" id="TX_DEVOPS_TOOL_NAME_${entry.ID_DEVOPS_TOOL}" name="TX_DEVOPS_TOOL_NAME" value="${entry.TX_DEVOPS_TOOL_NAME}" style="visibility:hidden">
																					                                	<input type="text" id="ID_DEVOPS_TOOL_${entry.ID_DEVOPS_TOOL}" name="ID_DEVOPS_TOOL" value="${entry.ID_DEVOPS_TOOL}" style="visibility:hidden">
																					                                </div>
																					                            </div>
																									      </div>
																									      <div class="modal-footer">
																									      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																			                            	 	<button type="submit" class="btn btn-primary" >Save</button> 
																									      </div>
																									      </form>
																									    </div>
																									  </div>
																									</div>
								                                        				
								                                        				
								                                        				
								                                        			</div>
								                                        			<hr/>
								                                        		</div><hr/>
									                                         </c:if>
							                                            </c:forEach>
							                                      </c:if> 
																	
																	
																	
														</section>
													</c:forEach>
												</c:if>
												<c:if test="${empty catList}">
													<p class="h4 text-danger">Configuration Error</p>
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
		
		 <!--This page JavaScript -->
		    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery.repeater/jquery.repeater.min.js"></script>
		    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/jquery.repeater/repeater-init.js"></script>
		    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/jquery.repeater/dff.js"></script>
			
			<script src="${pageContext.request.contextPath}/resources/js/blinqlabs/sweetalert.js"></script>
		
	</jsp:body>
</page:user-landing>