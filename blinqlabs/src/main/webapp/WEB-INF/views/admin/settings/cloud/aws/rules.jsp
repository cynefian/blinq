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
													<h3>AWS Rules</h3>
												</div>
												<div>
													<a href="${contextPath}/admin/settings/cloud/aws"><button type="button" class="btn btn-info"><i class="fas fa-arrow-left"></i></button></a>
													<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newRuleModal"><i class="far fa-plus-square"></i>  New Rule</button>
												</div>
												
												
										<!-- Link Modal -->
											<div class="modal fade" id="newRuleModal" tabindex="-1" role="dialog">
											  <div class="modal-dialog modal-lg" role="document">
											    <div class="modal-content">
											    <form class="form-horizontal m-t-20" id="newRuleForm" action="${contextPath}/admin/settings/cloud/aws/newRule" method="POST" name="RulesForm" modelAttribute="rulesBean">
											      <div class="modal-header">
											        	<h4 class="modal-title" id="exampleModalLabel1">New Rule</h4>
					                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
											      </div>
											      <div class="modal-body">
											     	  <div class="form-group">
							                              	<div class="row">
							                              		<div class="col-lg-8 col-sm-12">
							                              			<div class="m-2">
									                                	<label class="control-label">Select the tool for which the rule should be applied<span class="text-danger"> *</span>:</label>
									                                	<select class="form-control"  id="ID_DEVOPS_TOOL" name="ID_DEVOPS_TOOL" >
										                                	<option value="">-</option>
																		       	<c:forEach var="tool" items="${dTools}">
																		       		<c:set var="existingRule" value="0"/>
																		        	<c:forEach var="rule" items="${ruleList}"> 
																				       	<c:if test="${tool.ID_DEVOPS_TOOL==rule.ID_DEVOPS_TOOL}">
																			    		   	<c:set var="existingRule" value="1"/>
																				       	</c:if>
																				    </c:forEach> 
																			    	<c:if test="${existingRule=='0'}">
																				       	<option value="${tool.ID_DEVOPS_TOOL}">${tool.TX_DEVOPS_TOOL_NAME} </option>																				       	
																			       	</c:if>
																	         	</c:forEach>
																	    </select>
									                                </div>
									                                <div class="m-2">
									                                	<label  class="control-label">VPC<span class="text-danger"> *</span>:</label>
									                                	<select class="form-control"  id="ID_VPC" name="ID_VPC" onchange="updateVPC();">
										                                	<option value="">-</option>
										                                	<c:forEach var="vpc" items="${vpcList}">
										                                		<option value="${vpc.ID_COMPONENT}">${vpc.TX_COMPONENT_NAME}  -  ${vpc.TX_COMPONENT_KEY} </option>
										                                	</c:forEach>
									                                	</select>
									                                	<script>
			    											 					function updateVPC() {
			    											 						document.getElementById("naclValue").value = "";
			    											 						document.getElementById("rtValue").value = "";
					    											 				$.ajax({ 
					    											 					type: 'GET',
					    											 					url : '${pageContext.request.contextPath}/admin/settings/cloud/aws/rules/getVPCNACL',
					    											 					data:{
					    											 							key : document.getElementById("ID_VPC").value
					    											 						},
					    											 					success:function(responseText){
					    											 						document.getElementById("naclValue").value = responseText[0].tx_COMPONENT_KEY;
					    											 						document.getElementById("naclId").value = responseText[0].id_COMPONENT;
					    											 						
					    											 					}
					    											 				});
					    											 				
					    											 				$.ajax({ 
					    											 					url : '${pageContext.request.contextPath}/admin/settings/cloud/aws/rules/getVPCRouteTable',
					    											 					data:{
					    											 							key : document.getElementById("ID_VPC").value
					    											 						},
					    											 					success:function(responseText){
					    											 						document.getElementById("rtValue").value = responseText[0].tx_COMPONENT_KEY;
					    											 						document.getElementById("rtId").value = responseText[0].id_COMPONENT;
					    											 					}
					    											 				});
					    											 			
					    											 				$.ajax({ 
					    											 					url : '${pageContext.request.contextPath}/admin/settings/cloud/aws/rules/getVPCSecGroup',
					    											 					data:{
					    											 							key : document.getElementById("ID_VPC").value
					    											 						},
					    											 					success:function(responseText){
					    											 						
					    											 						var x = document.getElementById("ID_SEC_GROUP");
					    											 						while (x.firstChild) {
					    											 						    x.removeChild(x.firstChild);
					    											 						}
					    											 						
					    											 						var opt = document.createElement("option");
					    											 						opt.text = "-";
					    											 						opt.value = "-";
				    											 							x.add(opt);
				    											 							
				    											 							
					    											 						
					    											 						for (var i = 0; i < responseText.length; i++) {
					    											 							var option = document.createElement("option");
					    											 							option.text = responseText[i].tx_COMPONENT_KEY;
					    											 							option.value = responseText[i].id_COMPONENT;
					    											 							x.add(option);
					    											 				        }
					    											 						x.disabled = false;
					    											 						
					    											 					}
					    											 				});
					    											 				
					    											 				$.ajax({ 
					    											 					url : '${pageContext.request.contextPath}/admin/settings/cloud/aws/rules/getVPCSubnet',
					    											 					data:{
					    											 							key : document.getElementById("ID_VPC").value
					    											 						},
					    											 					success:function(responseText){
					    											 						
					    											 						var x = document.getElementById("ID_SUBNET");
					    											 						while (x.firstChild) {
					    											 						    x.removeChild(x.firstChild);
					    											 						}
					    											 						
					    											 						var opt = document.createElement("option");
					    											 						opt.text = "-";
					    											 						opt.value = "-";
				    											 							x.add(opt);
				    											 							
				    											 							
					    											 						
					    											 						for (var i = 0; i < responseText.length; i++) {
					    											 							var option = document.createElement("option");
					    											 							option.text = responseText[i].tx_COMPONENT_KEY;
					    											 							option.value = responseText[i].id_COMPONENT;
					    											 							x.add(option);
					    											 				        }
					    											 						x.disabled = false;
					    											 						
					    											 					}
					    											 				});
			    											 						
			    											 					
			    											 					}
			    											 				
				    											 		</script> 
									                                </div>
									                                 <div class="m-2">
									                                	<label class="control-label">Security Group<span class="text-danger"> *</span>:</label>
									                                	<select class="form-control"  id="ID_SEC_GROUP" name="ID_SEC_GROUP" disabled>
										                                	<option value="">-</option>
									                                	</select>
									                                </div>
									                                <div class="m-2">
									                                	<label class="control-label">Subnet<span class="text-danger"> *</span>:</label>
									                                	<select class="form-control"  id="ID_SUBNET" name="ID_SUBNET" disabled>
										                                	<option value="">-</option>
									                                	</select>
									                                </div>
							                              		</div>
							                              		<div class="col-lg-4 col-sm-12 table-active pt-4">
							                              			<div class="m-2">
								                              			<label class="control-label">NACL<span class="text-danger"> *</span>:</label><input type="text" id="naclId" name="ID_NACL" style="visibility:hidden">
								                              			<input type="text" id="naclValue" class="form-control" value="" readonly >
								                              			
								                              			
							                              			</div>
							                              			<div class="m-2">
							                              				<label class="control-label">Route Table<span class="text-danger"> *</span>:</label><input type="text" id="rtId" name="ID_ROUTE_TABLE" style="visibility:hidden">
							                              				<input type="text" id="rtValue" class="form-control" value="" readonly>
							                              			</div>
							                              		
							                              		</div>
							                              	</div> 
							                             </div>
							                             
							                             <div>
							                             	<span class="text-danger" id="modal-error"></span>
							                             </div>
											      </div>
											      <div class="modal-footer">
											      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					                            	 	<button type="button" class="btn btn-primary" onclick="validateNewRule();">Create Rule</button> 
					                            	 	<script>
					                            	 		function validateNewRule(){
					                            	 			
					                            	 			var toolElement = document.getElementById("ID_DEVOPS_TOOL");
					                            	 			var vpcElement = document.getElementById("ID_VPC");
					                            	 			var sgElement = document.getElementById("ID_SEC_GROUP");
					                            	 			var subnetElement = document.getElementById("ID_SUBNET");
					                            	 			var nacllElement = document.getElementById("naclId");
					                            	 			var rtElement = document.getElementById("rtId");
					                            	 			var errMsgElement = document.getElementById("modal-error");
					                            	 			var errMsg = "";
					                            	 			if(toolElement.value==null || toolElement.value=="-" || toolElement.value==""){
					                            	 				errMsg = "You must select the tool that this rule applies to.";
					                            	 			}else if(vpcElement.value==null || vpcElement.value=="-"  || vpcElement.value==""){
					                            	 				errMsg = "Please select the VPC for this rule.";
					                            	 			}else if(sgElement.value==null || sgElement.value=="-" || sgElement.value==""){
					                            	 				errMsg = "Please select the Security Group for this rule. Check administrator configurations if required.";
					                            	 			}else if(subnetElement.value==null || subnetElement.value=="-" || subnetElement.value==""){
					                            	 				errMsg = "Please select the Subnet for this rule. Check administrator configurations if required.";
					                            	 			}else if(nacllElement.value==null || nacllElement.value=="-" || nacllElement.value==""){
					                            	 				errMsg = "Please contact administrator. Looks like there is a configuration error with NACL.";
					                            	 			}else if(rtElement.value==null || rtElement.value=="-" || rtElement.value==""){
					                            	 				errMsg = "Please contact administrator. Looks like there is a configuration error with Route Table.";
					                            	 			}else{
					                            	 				errMsg = "";
					                            	 			}
					                            	 			if(errMsg==""){
					                            	 				document.getElementById("newRuleForm").submit();
					                            	 			}else{
					                            	 				errMsgElement.innerHTML = errMsg;
					                            	 			}
					                            	 		}
					                            	 	</script>
											      </div>
											      </form>
											    </div>
											  </div>
											</div>
											
											
											</section>
							          </div>
							          	<div class="m-2 p-2 table-active text-center">
							          		<span class="h6"><i class="fas fa-info-circle"></i> Tools that do not have rule definitions will always use defaults</span>
							          	</div>
							          <div>
							          	<div class="row">
							          		<div class="col-md-10 col-sm-12">
							          		<table class="table">
											  <thead class="thead-default">
											    <tr>
											    	<th>#</th>
											      <th>Component</th>
											      <th>VPC</th>
											      <th>NACL</th>
											      <th>Route Table</th>
											      <th>Security Group</th>
											      <th>Subnet</th>
											      <th>Actions</th>
											    </tr>
											  </thead>
											  <tbody>
											  <c:if test="${not empty ruleList}">
											 	<c:forEach var="rule" items="${ruleList}" varStatus="loop">
											 		<tr>
											 			<td>${loop.index+1}</td>
												 		<td>${rule.TX_DEVOPS_TOOL}</td>
												 		<td>${rule.TX_VPC}</td>
												        <td>${rule.TX_NACL}</td>
													    <td>${rule.TX_ROUTE_TABLE}</td>
													    <td>${rule.TX_SEC_GROUP}</td>
													    <td>${rule.TX_SUBNET}</td>
													    <td>
														    <!-- <button type="button" class="btn btn-outline-info"><i class="fas fa-edit"></i></button> -->
													    	<button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#deleteRuleModal_${rule.ID}"><i class="far fa-trash-alt"></i></button>
													    </td>
													    <!-- Delete Rule Modal -->
<div class="modal fade" id="deleteRuleModal_${rule.ID}" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <form class="form-horizontal m-t-20" id="newRuleForm" action="${contextPath}/admin/settings/cloud/aws/deleteRule" method="POST" name="RulesForm" modelAttribute="rulesBean">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Rule</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">
        
        	<div class="form-group">
            <label class="form-control-label">Rule ID: ${rule.ID}</label>
          </div>
          <div class="form-group">
            <label class="form-control-label text-danger h4">Are you sure you want to delete this rule?</label>
          </div>
          <div class="form-group">
            <label class="form-control-label lead">This action cannot be undone.</label>
          </div>
          <div>
          	<input type="text" id="DELETE_ID_${rule.ID}" name="ID" value="${rule.ID}" style="visibility:hidden">
          </div>
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-danger">Delete</button>
      </div>
       </form>
    </div>
  </div>
</div>

												    </tr>
											 	</c:forEach> 	
											  </c:if>
											  <c:if test="${empty ruleList}">
											  	<tr>
												  	<span class="text-danger h4"> No Rule definitions found.</span>	
											  	</tr>
											  </c:if>
											  </tbody>
											</table>
							          		
							          		</div>
							          		<div class="col-md-2 col-sm-12 table-primary">
							          			<div class="text-center">
							          				<h4>Defaults</h4>
							          			</div>
							          			<div>
							          				<span class="h6 text-primary">VPC: ${defaultRuleList[0].TX_VPC}</span>
							          			</div>
							          			<div>
							          				<span class="h6 text-primary">NACL: ${defaultRuleList[0].TX_NACL}</span>
							          			</div>
							          			<div>
							          				<span class="h6 text-primary">Route Table: ${defaultRuleList[0].TX_ROUTE_TABLE}</span>
							          			</div>
							          			<div>
							          				<span class="h6 text-primary">Security Group: ${defaultRuleList[0].TX_SEC_GROUP}</span>
							          			</div>
							          			<div>
							          				<span class="h6 text-primary">Subnet: ${defaultRuleList[0].TX_SUBNET}</span>
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
		          
		        </div>
		      </div>
		    </div>
		  
		
		</div>
		
	</jsp:body>
</page:user-landing>