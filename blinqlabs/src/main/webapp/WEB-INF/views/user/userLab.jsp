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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:user-landing>

	<jsp:body>
	<div class="container">
        	<c:if test="${not empty failuremessage}">
			 	<script>
			 	 	$(window).on("load", function() {
				    	toastr.error('${failuremessage}', 'Lab', { "progressBar": true });
				    });
			  	</script>
			</c:if>
			
			<c:if test="${not empty successmessage}">
			 	<script>
			 	 	$(window).on("load", function() {
				    	toastr.success('${successmessage}', 'Lab', { "progressBar": true });
				    });
			  	</script>
			</c:if>
	</div>
		<div id="page-wrapper">
				    <div class="basket">
				    	<div class="row p-2">
				    		<div class="col-lg-12 col-sm-12">
				    			<div class="text-right">
				    				<button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#addWorkbenchModal"><i class="fas fa-plus-circle"></i>  New Worbench</button>
				    			</div>		
				    			
				    			<!-- Add Wokbench Modal -->
									<div class="modal fade" id="addWorkbenchModal" tabindex="-1" role="dialog">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
										    <form class="form-horizontal m-t-20" id="newWorkbenchForm" action="${contextPath}/user/addWorkbench" method="POST" name="WorkbenchForm" modelAttribute="workbenchBean">
											      <div class="modal-header">
											        	<h4 class="modal-title" id="exampleModalLabel1">New Workbench</h4>
					                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
											      </div>
											      <div class="modal-body">
											      		<div class="text-right">
							                                <div class="form-group">
							                                	<button type="button" class="btn btn-primary" onclick="randonNameGenerator();">Generate Name</button>
							                                	
							                               	</div>
								                       </div>
											     	  <div>
							                                <div class="form-group">
							                                	<label for="TX_ACCESS_KEY" class="control-label">Name: <span class="text-danger">*</span></label>
							                                	<div id="workbenchNameValidation"></div>
							                                	<input type="text"class="form-control" name="TX_WORKBENCH_NAME" ID = "TX_WORKBENCH_NAME" placeholder="workbench_name" onchange="validateWorkbenchName();" onkeyup="validateWorkbenchName();">
							                                	<label id="emailHelp" class="form-text text-muted">Your workbench name needs to be unique. This is how your workbench can be identified among all the others.</label>
							                                </div>
								                       </div>
								                       <div class="m-2 p-2">
									                       	<div class="row table-info">
									                       		<div class="m-1">
									                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="name-length-check-img" alt="" width="3%"/>
									                       			<span>The name must be alteast 8 characters long</span>
									                       		</div>
									                       		<div class="m-1">
									                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="name-Char-check-img" alt="" width="3%"/>
									                       			<span>The name can contain alphabets, numbers and underscore ( _ ) only.</span>
									                       		</div>
									                       		<div class="m-1">
									                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="name-Char-start-img" alt="" width="3%"/>
									                       			<span>The name must start with an alphabet.</span>
									                       		</div>
									                       		<div class="m-1">
									                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="name-Char-end-img" alt="" width="3%"/>
									                       			<span>The name must end with an alphabet or a number.</span>
									                       		</div>
									                       	</div>
								                       </div>
								                       <div>
								                       		<input type="text" id="workbenchNameValidationResult" value="" style="display:none">
								                       		<input type="text" id="workbenchNameLengthResult" value="" style="display:none">
								                       		<input type="text" id="workbenchNameCharResult" value="" style="display:none">
								                       		<input type="text" id="workbenchNameStartResult" value="" style="display:none">
								                       		<input type="text" id="workbenchNameEndResult" value="" style="display:none">
								                       </div>
								                   </div>
											      <div class="modal-footer">
											      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					                            	 	<button type="submit" class="btn btn-primary" id="addNewWorkbenchBtn" disabled onclick="submitNewWorkbench();">Create Workbench</button> 
											      </div>
									      </form>
									      
									       <script>
									       
									       	function randonNameGenerator(){
									       		var name = generateName();
									       		document.getElementById("TX_WORKBENCH_NAME").value = name;
									       		validateWorkbenchName();
									       		updateSubmitButton();
									       	}
					                       		function validateWorkbenchName(){
					                       			var workbenchName = document.getElementById("TX_WORKBENCH_NAME").value;
					                       			$.ajax({ 
 											 					url : '${pageContext.request.contextPath}/userLab/checkExistingWorkbench',
 											 					data:{
 											 							key : workbenchName
 											 						},
 											 					success:function(responseText){
 											 						var objTo = document.getElementById('workbenchNameValidation');
 											 						objTo.innerHTML = "";
 											 						var divtest = document.createElement("div");
 											 						if(responseText == "true"){
 											 							divtest.innerHTML = '<div class="m-4 p-2 text-center"> <img src="${pageContext.request.contextPath}/resources/img/icons/check.png" alt="" width="10%"/> Workbench name is available. </div>';
 											 							document.getElementById("workbenchNameValidationResult").value="PASS";
 											 						}else{
 											 							divtest.innerHTML = '<div class="m-4 p-2 text-center"> <img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" alt="" width="10%"/> The name is not available. </div>';
 											 							document.getElementById("workbenchNameValidationResult").value="FAIL";
 											 						}
 											 						  objTo.appendChild(divtest)
 											 					}
 											 					
 											 				});
						                       		if(workbenchName.length<8){
						                       			document.getElementById("workbenchNameLengthResult").value="FAIL";
						                       			$('#name-length-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
						                       		}else{
						                       			document.getElementById("workbenchNameLengthResult").value="PASS";
						                       			$('#name-length-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
						                       		}
					                       		
						                       		var regExTester = new RegExp("^[a-zA-Z0-9_]+$");
					                       			if (regExTester.test(workbenchName)) {
					                       				$('#name-Char-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
					                       				document.getElementById("workbenchNameCharResult").value="PASS";
					                       	        }else{
					                       	        	$('#name-Char-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
					                       	        	document.getElementById("workbenchNameCharResult").value="FAIL";
					                       	        }
					                       		
					                       			var regExStartTester = new RegExp("^[a-zA-Z]");
					                       			if(regExStartTester.test(workbenchName)){
						                       			document.getElementById("workbenchNameStartResult").value="PASS";
						                       			$('#name-Char-start-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
						                       		}else{
						                       			document.getElementById("workbenchNameStartResult").value="FAIL";
						                       			$('#name-Char-start-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
						                       		}
					                       			
					                       			var regExEndTester = new RegExp("[a-zA-Z0-9]$");
					                       			if(regExEndTester.test(workbenchName)){
						                       			document.getElementById("workbenchNameEndResult").value="PASS";
						                       			$('#name-Char-end-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
						                       		}else{
						                       			document.getElementById("workbenchNameEndResult").value="FAIL";
						                       			$('#name-Char-end-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
						                       		}
					                       			
					                       			updateSubmitButton();
					                       		
					                       		}
					                       		
					                       		function updateSubmitButton(){
					                       			if(document.getElementById("workbenchNameCharResult").value=="PASS"
				                       					&& document.getElementById("workbenchNameLengthResult").value=="PASS"
				                       					&& document.getElementById("workbenchNameValidationResult").value=="PASS"
				                       						&& document.getElementById("workbenchNameStartResult").value=="PASS"
				                       							&& document.getElementById("workbenchNameEndResult").value=="PASS"){
				                       					document.getElementById("addNewWorkbenchBtn").disabled = false;
					                       			}else{
					                       				document.getElementById("addNewWorkbenchBtn").disabled = true;
					                       			}
					                       		}
					                       	
					                       </script>	
								                       
								    </div>
								  </div>
								</div>
								
			    			</div>
		    			</div><hr/>
		    			
		    			
		    			<div>
		    				<c:if test="${empty wbList}">
		    					<div class="text-center m-4 p-4">
		    						<p class="h4 text-danger">You do not have any workbenches defined.</p>
		    						<p class="h6">Please create a new workbench to start using the lab.</p>
		    						<div class="m-2">
		    							<img src="${pageContext.request.contextPath}/resources/img/illustration/lab.png" width="40%">
		    						</div>
		    					</div>
		    				</c:if>
		    				<c:if test="${not empty wbList}">
		    					<c:forEach var="workbench" items="${wbList}">
		    						<div>
		    							<div class="row bg-primary">
		    								<div class="col-lg-11 col-sm-11 p-2">
		    									<p class="text-white">Name: <span class="h4 text-white">
		    										${workbench.TX_WORKBENCH_NAME}</span>
		    									</p>
		    									<p class="text-white">
		    										Key: <span>${workbench.TX_WORKBENCH_KEY}</span>
		    									</p>
		    								</div>
		    								<div class="col-lg-1 col-sm-1 p-2 text-right">
		    									<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#deleteWorkbenchModal_${workbench.ID}"><i class="far fa-trash-alt"></i></button>
		    								</div>
		    								
		    										<!-- Delete Wokbench Modal -->
												<div class="modal fade" id="deleteWorkbenchModal_${workbench.ID}" tabindex="-1" role="dialog">
												  <div class="modal-dialog" role="document">
												    <div class="modal-content">
													    <form class="form-horizontal m-t-20" action="${contextPath}/user/deleteWorkbench?id=${workbench.ID}" method="POST">
														      <div class="modal-header">
														        	<h4 class="modal-title" id="exampleModalLabel1">Delete Workbench</h4>
								                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
														      </div>
														      <div class="modal-body">
														     	  <div>
										                                <div class="form-group">
										                                	<label for="TX_ACCESS_KEY" class="control-label">All data associated with this workbench will be lost.</label>
										                                </div>
										                                <div class="form-group">
										                                	<label for="TX_ACCESS_KEY" class="control-label">Are you sure you want to continue?.</label>
										                                </div>
											                       </div>		
										                       </div>
														      <div class="modal-footer">
														      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								                            	 	<button type="submit" class="btn btn-danger" >Delete</button> 
														      </div>
												      </form>
											    </div>
											  </div>
											</div>
											
		    							</div>
		    							<div class="p-2 m-t-1">
		    								<div class="row">
		    									<div class="col-lg-8 col-sm-12">
		    									 	<div>
		    									 		<p class="text-primary"><strong>${sessionScope.userId}</strong>  <span class="text-secondary">/</span>   <strong>${workbench.TX_WORKBENCH_NAME}</strong></p>
		    									 	</div>
		    									</div>
		    									<div class="col-lg-4 col-sm-12 text-right">
		    										<button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#addInfraModal_${workbench.ID}">Add Service</button>
		    									</div>
		    								</div>
		    								
		    								
		    									<!-- Add Infra to Workbench Modal -->
												<div class="modal fade" id="addInfraModal_${workbench.ID}" tabindex="-1" role="dialog">
												  <div class="modal-dialog" role="document">
												    <div class="modal-content">
													    <form class="form-horizontal m-t-20" action="${contextPath}/userLab/createService" method="POST" modelAttribute="userServicesBean" name="UserServicesForm" id="UserServicesRequestForm_${workbench.ID}">
													          <div class="modal-header">
														        	<h4 class="modal-title" id="exampleModalLabel1">DevOps Service</h4>
								                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
														      </div>
														      <div class="modal-body">
														     	  <div>
														     	  		<c:if test="${empty srvcTools}">
															     	  		<div class="text-center m-2">
															     	  			<h4 class="text-danger">Unable to configure new services.</h4>
															     	  			<h6>The infrastructure defaults haven't been initialized.</h6>
															     	  			<h6>Please contact support.</h6>
															     	  		</div>
														     	  		</c:if>
														     	  		<c:if test="${not empty srvcTools}">
											                                <div class="form-group">
											                                	<label class="control-label">Choose available service: <span class="text-danger">*</span></label>
											                                	
											                                	<select class="form-control" id="ID_SRVC_TOOL_${workbench.ID}" name="ID_SRVC_TOOL" onchange="clearServiceRequestError(${workbench.ID});" >
											                                		<option value="-">-</option>
											                                		<c:if test="${not empty srvcTools}">
																						<c:forEach var="tool" items="${srvcTools}">
																						
																							<c:set var="workbenchhastool" value="0"/>
																							<c:if test="${not empty userServicesList}">
														    									<c:forEach var="srvc" items="${userServicesList}">
														    										<c:if test="${srvc.ID_WORKBENCH==workbench.ID}">
														    											<c:if test="${tool.TX_DEVOPS_TOOL_NAME==srvc.TX_SRVC_TOOL_NAME}">
														    												<c:set var="workbenchhastool" value="1"/>
														    											</c:if>
														    										</c:if>
														    									</c:forEach>
		    																				</c:if>
																					
																							
																							<c:if test="${workbenchhastool=='0'}">
																								<option value="${tool.ID_DEVOPS_TOOL}">
																									<c:if test="${not empty  tool.TX_DEVOPS_TOOL_IMAGE}">
											                                        					<p><img src="${tool.TX_DEVOPS_TOOL_IMAGE}" width="45">  ${tool.TX_DEVOPS_TOOL_NAME}   [${tool.TX_CATEGORY_CODE}]</p>
											                                        				</c:if>
											                                        				<c:if test="${empty tool.TX_DEVOPS_TOOL_IMAGE}">
											                                        					<p><img src="${pageContext.request.contextPath}/resources/images/software.png" width="45">  ${tool.TX_DEVOPS_TOOL_NAME}   [${tool.TX_CATEGORY_CODE}]</p>
											                                        				</c:if>
																								</option>
																							</c:if>
																						</c:forEach>
																					</c:if>
																					<c:if test="${empty srvcTools}">
																						<option> --- Config. error --- </option>
																					</c:if>
											                                	
											                                	</select>
											                                	
											                                	<input type="text" value="${workbench.ID_USER}" ID="ID_USER_${workbench.ID}" name="ID_USER" style="visibility:hidden">
											                                	<input type="text" value="${workbench.ID}" ID="ID_WORKBENCH_${workbench.ID}" name="ID_WORKBENCH" style="visibility:hidden">
											                                </div>
										                                </c:if>
										                           </div>	
										                           <div>
										                           		<label class="text-danger" id="newSrvcConfError_${workbench.ID}"></label>
										                           </div>	
										                       </div>
														      <div class="modal-footer">
														      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								                            	 	<button type="button" class="btn btn-primary" id="configureLabServiceBtn" name="${workbench.ID}" onclick="validateNewServiceOption(this);">Configure</button> 
														      </div>
												      </form>
												      <script>
												      	function validateNewServiceOption(element){
												      		if(document.getElementById("ID_SRVC_TOOL_"+element.name).value==null || document.getElementById("ID_SRVC_TOOL_"+element.name).value=="-" ){
												      			document.getElementById("newSrvcConfError_"+element.name).innerHTML = "Please select tool for service configuration.";
												      		}else{
												      			document.getElementById("newSrvcConfError_"+element.name).innerHTML = "";
												      			document.getElementById("UserServicesRequestForm_"+element.name).submit();
												      		}
												      	}
												      	
												      	function clearServiceRequestError(id){
												      		document.getElementById("newSrvcConfError_"+id).innerHTML = "";
												      	}
												      </script>
											    </div>
											  </div>
											</div>
		    							</div>
		    							
		    							<div>
		    								<c:set var="workbenchServiceFlag" value="${0}" />
		    								<c:if test="${not empty userServicesList}">
		    									<c:forEach var="srvc" items="${userServicesList}">
		    										<c:if test="${srvc.ID_WORKBENCH==workbench.ID}">
		    											 <c:set var="workbenchServiceFlag" value="${1}" />
		    											 
		    											 <div class="card bg-light m-t-1 m-b-1">
		    											 	 <div class="card-body">
		    											 	 	 <div class="row">
				    											 	<div class="col-lg-4 col-md-4 col-sm-12">
				    											 		<div class="row">
				    											 			<div class="col-lg-12 col-sm-12">
				    											 				<div style="float:left">
						    											 			<c:if test="${not empty  srvc.TX_SRVC_TOOL_IMG}">
							                                        					<img src="${srvc.TX_SRVC_TOOL_IMG}" width="45"> 
							                                        				</c:if>
							                                        				<c:if test="${empty  srvc.TX_SRVC_TOOL_IMG}">
							                                        					<img src="${pageContext.request.contextPath}/resources/images/software.png" width="45"> 
							                                        				</c:if>
						    											 		</div>
						    											 		<div >
						    											 		<br/>
						    											 			<h4>   ${srvc.TX_SRVC_TOOL_NAME}</h4>
						    											 		</div>
						    											 		<div >
						    											 		<br/>
						    											 			<button type="button"class="btn btn-outline-light" data-toggle="modal" data-target="#viewConfigInfo_${srvc.ID_SERVICE}"><span class="text-primary"><i class="fas fa-eye"></i> View Config Information</span></button>
						    											 			
						    											 			<!-- VPC Default Modal -->
			                                                        				<div class="modal fade" id="viewConfigInfo_${srvc.ID_SERVICE}" tabindex="-1" role="dialog">
																					  <div class="modal-dialog" role="document">
																					    <div class="modal-content">
																					    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/aws/vpc/updateVPCDefaults" method="POST" name="CloudComponentForm" modelAttribute="cloudComponentBoean">
																					      <div class="modal-header">
																					        	<h4 class="modal-title">Config Information</h4>
															                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
																					      </div>
																					      <div class="modal-body">
																					      		<div class="text-center text-info">
																						     		<p><span class="h5">Service ID:  ${srvc.TX_LAB_SRVC_KEY}</span></p>
																						      </div>
																						      <c:set var="hasConf" value="0"/>
																						      <c:if test="${not empty srvcConfList}">
																						      	<c:forEach var="conf" items="${srvcConfList}" varStatus="loop">
																						     		<c:if test="${conf.ID_LAB_SRVC == srvc.ID_SERVICE}"> 	
																						      			<c:set var="hasConf" value="1"/>
																						      				<div class="row table-active">
																						      					<div class="col-lg-12">
																						      						<h5 class="text-primary" id="ID_CONF_${conf.ID_LAB_SRVC}_${loop.index}">${conf.TX_CONFIG_NAME}</h5>		
																						      						<p><span class="lead text-secondary">${conf.TX_RESULT}</span></p>
																						      					</div>
																						      				</div>
																						      		</c:if>
																						      	</c:forEach>
																						      </c:if>
																						      
																						      <c:if test="${hasConf=='0'}">
																						      	<span class="">No Configurations found for this service</span>
																						      </c:if>
																						      
																						  </div>
																					      <div class="modal-footer">
																					      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
															                              </div>
																				      </form>
																				      </div>
																			      	</div>
																			      </div>
																						      
						    											 		</div>
					    											 		</div>
				    											 		</div>
				    											 		<div class="row">
				    											 			<c:if test="${not empty srvc.TX_SERVICE_URL}">
				    											 				URL: ${srvc.TX_SERVICE_URL}
				    											 			</c:if>
				    											 			<c:if test="${empty srvc.TX_SERVICE_URL}">
				    											 				URL: http://${srvc.TX_IP}:${srvc.TX_PORT}
				    											 			</c:if>  
				    											 		</div>
				    											 	</div>
				    											 	<div class="col-lg-4 col-md-4 col-sm-12">
				    											 		<div>
				    											 			Container ID: <span class="text-primary">${fn:substring(srvc.ID_CONTAINER,0,12)}</span>
				    											 		</div>
				    											 		<div>
				    											 			Infrastructure: ${srvc.TX_INFRASTRUCTURE_TYPE}
				    											 		</div>
				    											 		<div>
				    											 			Infrastructure Status: ${srvc.FL_INFRASTRUCTURE_STATUS}
				    											 		</div>
				    											 	</div>
				    											 	<div class="col-lg-4 col-sm-12 text-right">
				    											 		<div>
				    											 			Service Status: ${srvc.FL_SERVICE_STATUS}
				    											 		</div>
				    											 		<div>
				    											 			<img src="${pageContext.request.contextPath}/resources/img/loader/infinity-loader.gif" id="srvc-status-img_${srvc.ID_SERVICE}" width="10%">
				    											 			<label id="status_${srvc.ID_SERVICE}">Validating</label>
				    											 			<script>
				    											 				$(document).ready(function(){
				    											 					var myVar = setInterval(myTimer, 10000);
						    											 			function myTimer() {
						    											 				var elementIdentifier = "status_"+${srvc.ID_SERVICE};
						    											 				$.ajax({ 
						    											 					url : '${pageContext.request.contextPath}/userLab/validateService',
						    											 					data:{
						    											 							key : "${srvc.TX_LAB_SRVC_KEY}"
						    											 						},
						    											 					success:function(responseText){
						    											 						document.getElementById(elementIdentifier).innerHTML = responseText;
						    											 						if(responseText.includes("Completed - Ready")){
						    											 							$('#srvc-status-img_${srvc.ID_SERVICE}').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
						    											 						}
						    											 					}
						    											 					
						    											 				});
						    											 				
						    											 				$.ajax({ 
						    											 					url : '${pageContext.request.contextPath}/userLab/getServiceInitializationStatus',
						    											 					data:{
						    											 							key : "${srvc.TX_LAB_SRVC_KEY}"
						    											 						},
						    											 					success:function(responseText){
						    											 						var trHTML = '';
						    											 						if(responseText.length>0){
						    											 							for(var i=0;i<responseText.length;i++){
							    											 							var image = "";
							    											 							if(responseText[i].fl_INIT==true && responseText[i].fl_COMPLETE==true){
							    											 								image = "${pageContext.request.contextPath}/resources/img/icons/check.png";
							    											 							}else if(responseText[i].fl_INIT == true && responseText[i].fl_COMPELTE!=true){
							    											 								image = "${pageContext.request.contextPath}/resources/img/loader/infinity-loader.gif";
							    											 							}else{
							    											 								image = "${pageContext.request.contextPath}/resources/img/icons/pending.png";
							    											 							}
							    											 							
							    											 							
							    											 							trHTML += '<div class="row">' +
							    											 							'   <div class="col-md-2">'	+
							    											 							'      <img src="'+image+'" width="30">' +
							    											 							'   </div>' +
							    											 							'   <div class="col-md-10">'	+
							    											 							responseText[i].tx_STATUS +
							    											 							'   </div>' +
							    											 							 '</div>' 
							    											 						
							    											 						}
						    											 						}else{
						    											 							trHTML += "Queued..."
						    											 						}
						    											 						
						    											 						$('#statusDiv_${srvc.ID_SERVICE}').html(trHTML);
						    											 					}
						    											 					
						    											 				});
						    											 				
						    											 			}
				    											 				})
					    											 		</script> 
				    											 		</div>
				    											 	</div>
				    											 </div>
		    											 	 </div>
		    											 	 <div class="card-footer text-muted">
		    											 	 	<div class="row">
		    											 	 		<div class="col-lg-6 col-sm-6">
		    											 	 			<label id="srvc_key_${srvc.ID_SERVICE}">
		    											 	 				Unique ID: ${srvc.TX_LAB_SRVC_KEY}
		    											 	 			</label>	
		    											 	 		</div>
		    											 	 		<div class="col-lg-6 col-sm-6 text-right">
		    											 	 				<div class="dropdown mr-4">
		    											 	 				 <button type="button" class="btn btn-info mr-2" data-toggle="modal" data-target="#srvcStatushModal_${srvc.ID_SERVICE}">Check Status</button>
			    											 	 			
			    											 	 			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																			    <i class="fas fa-ellipsis-v"></i>
																			  </button>
																			  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
																			    <a class="dropdown-item" href="#">Start Service</a>
																			    <a class="dropdown-item" href="#">Stop Service</a>
																			  </div>
																			</div>
		    											 	 		</div>
																	
																	<!-- Add Wokbench Modal -->
																					<div class="modal fade" id="srvcStatushModal_${srvc.ID_SERVICE}" tabindex="-1" role="dialog">
																					  <div class="modal-dialog" role="document">
																					    <div class="modal-content">
																						    <form class="form-horizontal m-t-20" action="${contextPath}/user/addWorkbench" method="POST" name="WorkbenchForm" modelAttribute="workbenchBean">
																							      <div class="modal-header">
																							        	<h4 class="modal-title" id="exampleModalLabel1">Status: ${srvc.TX_LAB_SRVC_KEY}</h4>
																	                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																							      </div>
																							      <div class="modal-body">
																							     	  <div id="statusDiv_${srvc.ID_SERVICE}">
																							     	  		<h6>Checking Status...</h6>
																				                       </div>		
																			                       </div>
																							      <div class="modal-footer">
																							      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
																	                              </div>
																					      </form>
																				    </div>
																				  </div>
																				</div>
																				
		    											 	 	</div>
															    
															  </div>
		    											 </div>
		    										</c:if>
		    									</c:forEach>
		    								</c:if>
		    							
		    								<c:if test="${workbenchServiceFlag==0}">
		    									<div class="text-center">
			    									<p class="h4"> There are no services defined in this workbench.</p>
			    									<img src="${pageContext.request.contextPath}/resources/img/illustration/server-down.png" width="25%"/>
		    									</div>
		    								</c:if>
		    							</div>
		    						</div>
		    						<hr/>
		    					</c:forEach>
		    				</c:if>
		    				
		    			</div>
		    			
                 </div>
			  
		
		</div>
  
 <script src="${pageContext.request.contextPath}/resources/js/blinqlabs/lab/namegenerator.js"></script>
   
	</jsp:body>
</page:user-landing>
