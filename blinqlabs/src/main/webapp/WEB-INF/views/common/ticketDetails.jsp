<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<page:user-landing>
	<jsp:body>
	
	       <!-- ============================================================== -->
            <div class="page-wrapper">
            <div class="m-4 p-4">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- basic table -->
               <div>
                 <c:if test="${empty ticketList}">
                 
                 <div id="backButton" class="col-2 m-t-10 m-b-10">
	                	<button type="button" class="btn btn-primary" onclick="history.back()"><i class="fas fa-arrow-circle-left" style="font-size:24px"></i></button>
                </div>
                 	<div>
                 		<h4 class="page-title text-center">Error retrieving Ticket Information.</h4>
                 	</div>
                 </c:if>
                 
                  <c:if test="${not empty ticketList}">
                 	<div>
                 	
                 		 <div ID="actionPanel" class="row m-2 p-2">
				                
				                <div id="panelButton" class="col-8 m-t-10 m-b-10">
					                <c:if test="${ticketList[0].TX_STATUS!='ESCALATED' && ticketList[0].TX_STATUS!='CLOSED'}">
					                	<a href="${contextPath}/updateStatus?id=${ticketList[0].TICKET_ID}&action=6" method="POST">
					                		<button type="submit" class="btn">Escalate</button>
					                	</a>
					                </c:if>
					                
					                 <c:if test="${ticketList[0].TX_STATUS!='CLOSED'}">
						                 <security:authorize access="hasAuthority('PERM_TICKET_WORK')">
						                 	<a href="${contextPath}/updateStatus?id=${ticketList[0].TICKET_ID}&action=5" method="POST">
						                		<button type="submit" class="btn">Close</button>
						                	</a>
					                	</security:authorize>
					                </c:if>
					            
					                <c:if test="${ticketList[0].TX_STATUS!='CLOSED'}">
					                	<div class="btn-group">
									 <!--    <a class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" href="#">Actions  <span class="caret"></span></a> -->
									 <button class="btn dropdown-toggle" type="button" data-toggle="dropdown">Actions <span class="caret"></span></button>
									    <ul class="dropdown-menu">
									       <a href="${contextPath}/updateStatus?id=${ticketList[0].TICKET_ID}&action=1" method="POST"> <div class="m-2">
										      <li>Open</li>
										    </div></a>
										    <div><li class="divider"></li></div>
										  		<a href="${contextPath}/updateStatus?id=${ticketList[0].TICKET_ID}&action=2" method="POST">  <div class="m-2">
										      	<li>In Progress</li>
										    </div></a>
										    
										    <security:authorize access="hasAuthority('PERM_TICKET_WORK')">
											    <div><li class="divider"></li></div>
											 	 <a href="${contextPath}/updateStatus?id=${ticketList[0].TICKET_ID}&action=3" method="POST">  <div class="m-2">
											      <li>Investigation in Progress</li>
											    </div></a>
										    </security:authorize>
										    
										    <security:authorize access="hasAuthority('PERM_TICKET_WORK')">
											    <div><li class="divider"></li></div>
											  	<a href="${contextPath}/updateStatus?id=${ticketList[0].TICKET_ID}&action=4" method="POST">  <div class="m-2">
											      <li>Waiting for Customer</li>
											    </div></a>
										    </security:authorize>
									    </ul>
									  </div>
					                	
					                </c:if>
					            
					               <c:if test="${ticketList[0].TX_STATUS=='OPEN'}">
					               		<a href="${contextPath}/deleteTicket?id=${ticketList[0].TICKET_ID}&source=USER"><button type="button" class="btn waves-effect waves-light btn-danger"><i class="far fa-trash-alt"></i></button></a>
					               </c:if>
					            </div> 
			                </div>
                 			
	                 		<div class="m-t-4">
	                 			  <div class="card">
		                            <div class="card-body bg-dark text-white">
			                            <div class="row">
		                                    <div class="col-6 m-t-10 m-b-10 text-justify">
			                           		    <h4 class="card-title">${ticketList[0].TICKET_SUBJECT}</h4>
			                                </div>
		                                     <div class="col-6 m-t-10 m-b-10 text-right">
			                                     <c:if test="${ticketList[0].TX_STATUS=='OPEN'}">
			                                    	<span class="label label-primary">${ticketList[0].TX_STATUS}</span>
			                                    </c:if>
			                                    <c:if test="${ticketList[0].TX_STATUS=='IN PROGRESS'}">
			                                    	<span class="label label-warning">${ticketList[0].TX_STATUS}</span>
			                                    </c:if>
			                                    <c:if test="${ticketList[0].TX_STATUS=='CLOSED'}">
			                                    	<span class="label label-success">${ticketList[0].TX_STATUS}</span>
			                                    </c:if>
			                                    <c:if test="${ticketList[0].TX_STATUS=='INVESTIGATION'}">
			                                    	<span class="label label-default text-dark">${ticketList[0].TX_STATUS}</span>
			                                    </c:if>
			                                    <c:if test="${ticketList[0].TX_STATUS=='WAITING FOR CUSTOMER'}">
			                                    	<span class="label label-info">${ticketList[0].TX_STATUS}</span>
			                                    </c:if>
			                                    <c:if test="${ticketList[0].TX_STATUS=='ESCALATED'}">
			                                    	<span class="label label-danger">${ticketList[0].TX_STATUS}</span>
			                                    </c:if>
		                                        
		                                    </div>
		                                </div>
		                                <div class="row text-center">
			                                <div class="col-6 m-t-10 m-b-10 text-left">
			                                	<div aria-label="breadcrumb">
												    <div>ID: ${ticketList[0].TICKET_ID}</div>
												    <div>Priority: ${ticketList[0].PRIORITY}</div>
												</div>
				                            </div>
				                            <div class="col-6 m-t-10 m-b-10 text-right">
				                                <h5 class="m-t-30">Support Staff</h5>
				                                <c:if test="${not empty ticketList[0].AGENT_NAME}">
				                                	<span>${ticketList[0].AGENT_NAME}</span>
				                                </c:if>
				                                
				                                <c:if test="${empty ticketList[0].AGENT_NAME}">
				                                <h6 class="card-subtitle">This ticket has not yet been addressed.</h6>
				                                	
				                                </c:if>
				                                
				                            </div>
		                                </div>
		                            </div>
		                            <div class="card-body">
			                            
			                            	<div>
												<div class="row">
													<div class="col-md-9">
													
														<!-- Nav tabs -->
															<ul class="nav nav-tabs" role="tablist">
															    <li class="nav-item"> <a id="infoTab" class="nav-link active" data-toggle="tab" href="#info" role="tab"><span class="hidden-sm-up"><i class="fas fa-info-circle"></i></span> <span class="hidden-xs-down">Info</span></a> </li>
															    <li class="nav-item"> <a id="attachTab" class="nav-link" data-toggle="tab" href="#attachments" role="tab"><span class="hidden-sm-up"><i class="fas fa-paperclip"></i></span> <span class="hidden-xs-down">Attachments</span></a> </li>
															    <li class="nav-item"> <a id="attachTab" class="nav-link" data-toggle="tab" href="#worklog" role="tab"><span class="hidden-sm-up"><i class="fas fa-clock"></i></span> <span class="hidden-xs-down">Work Log</span></a> </li>
															</ul>
															<!-- Tab panes -->
															<div class="tab-content tabcontent-border">
															    <div class="tab-pane active" id="info" role="tabpanel">
															        <div class="m-2 p-20">
															        	<div>
																            <h3>Description:</h3>
															            	<p class="text-primary">${ticketList[0].THREAD_BODY}</p>
														            	</div>
														            	<div>
														            		<h6>Total Logged Hours: ${totalLoggedWork}</h6>
														            	</div>
															        </div>
															    </div>
															    <div class="tab-pane p-20" id="attachments" role="tabpanel">
															    	<div class="m-2 p-2">
															    		<div>
															    			<c:if test="${not empty ticketAttachments}">
															    				<div class="table-responsive">
														                                    <table class="table">
														                                        <thead class="bg-info text-white">
														                                            <tr>
														                                                <th>Attachment</th>
														                                                <th>Action</th>
														                                            </tr>
														                                        </thead>
														                                        <tbody>
														                                        	<c:forEach var="attach" items="${ticketAttachments}" varStatus="loop">
															                                            <tr>
															                                                <td>
															                                                <c:if test="${fn:containsIgnoreCase(attach.TX_TYPE, 'pdf')}">
															                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/pdf.png" alt="pdf" width="20px">
															                                                </c:if>
															                                                <c:if test="${fn:containsIgnoreCase(attach.TX_TYPE, 'image')}">
															                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/image.png" alt="image" width="20px">
															                                                </c:if>
															                                                <c:if test="${fn:containsIgnoreCase(attach.TX_TYPE, 'word')}">
															                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/word.png" alt="word" width="20px">
															                                                </c:if>
															                                                <c:if test="${fn:containsIgnoreCase(attach.TX_TYPE, 'excel')}">
															                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/excel.png" alt="excel" width="20px">
															                                                </c:if>
															                                                <c:if test="${fn:containsIgnoreCase(attach.TX_TYPE, 'sheet')}">
															                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/excel.png" alt="excel" width="20px">
															                                                </c:if>
															                                                <c:if test="${fn:containsIgnoreCase(attach.TX_TYPE, 'powerpoint')}">
															                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/powerpoint.png" alt="powerpoint" width="20px">
															                                                </c:if>
															                                                <c:if test="${fn:containsIgnoreCase(attach.TX_TYPE, 'presentation')}">
															                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/powerpoint.png" alt="powerpoint" width="20px">
															                                                </c:if>
															                                                <span>  ${attach.TX_NAME}</span>
															                                                
															                                                </td>
															                                                <td><span class="p-2">
															                                                 <button type="button" class="btn waves-effect waves-light btn-light" onclick="downloadAttachment(${attach.ID})"><i class="fas fa-download"></i></button>
															                                            </tr>
														                                            </c:forEach>
														                                        </tbody>
														                                    </table>
														                                </div>
														                                
														                                <ul class="pagination float-right">
				                                                                             <c:if test="${currentPage!=1 && currentPage>0}">
													                                        	 <li class="page-item mr-2">
													                                            	<a class="page-link" href="${contextPath}/admin/SENdetails?source=ATTACH&id=${ticketList[0].TICKET_ID}&page=${currentPage - 1 }" tabindex="-1">Previous</a>
													                                        	</li>
													                                        </c:if>
													                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
													                                    	<c:if test="${currentPage==loop.index}">
													                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/ticketDetails?source=ATTACH&id=${ticketList[0].TICKET_ID}&page=${loop.index}">${loop.index}</a></li>
													                                    	</c:if>
													                                    	<c:if test="${currentPage!=loop.index}">
													                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/ticketDetails?source=ATTACH&id=${ticketList[0].TICKET_ID}&page=${loop.index}">${loop.index}</a></li>
													                                    	</c:if>
													                                    </c:forEach>
													                                    	<c:if test="${currentPage!=totalPages}">
													                                        	 <li class="page-item ml-2">
													                                            	<a class="page-link" href="${contextPath}/ticketDetails?source=ATTACH&id=${ticketList[0].TICKET_ID}&page=${currentPage + 1 }" tabindex="-1">Next</a>
													                                        	</li>
													                                        </c:if>
												
													                                    </ul>
															    			</c:if>
															    			<c:if test="${empty ticketAttachments}">
															    				<div class="m-2 p-2 text-center">
															    					There are no attachments
															    				</div>
															    			</c:if>
															    		</div>
															    	</div>
															    </div>
															    
															    <div class="tab-pane" id="worklog" role="tabpanel">
															        <div class="m-2 p-20">
															            <security:authorize access="hasAuthority('PERM_TICKET_WORK')">
															            	<c:if test="${ticketList[0].TX_STATUS!='CLOSED'}">
																            	<div class="row m-2 p-2">
																            		<div class="col-md-12 text-right">
																            			<button type="button" class="btn waves-effect waves-light btn-info" data-toggle="modal" data-target="#logWorkModal">Log Work</button>
																            		</div>
																            	</div>
															            	</c:if>
															            	
															            	<div class="modal fade" id="logWorkModal" tabindex="-1" role="dialog">
																			  <div class="modal-dialog" role="document">
																			    <div class="modal-content">
																			    <form class="form-horizontal m-t-20" action="${contextPath}/admin/service/sen/addWorklog" method="POST" name="worklogForm" modelAttribute="worklogForm">
																			      <div class="modal-header">
																			        	<h4 class="modal-title" id="exampleModalLabel1">Log Work</h4>
													                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																			      </div>
																			      <div class="modal-body">
																			     	  <div>
															                                <div class="form-group">
															                                	<label for="TX_ACCESS_KEY" class="control-label">Time Investment:</label>
															                                	<input type="text"class="form-control" placeholder="3w 2d 5h 30m" name="TX_WORKLOG" ID = "TX_WORKLOG">
															                                </div>
															                           </div>
															                             <div>
															                                <div class="form-group">
															                                	<label for="TX_ACCESS_KEY" class="control-label">Description:</label>
															                                	<textarea id="TX_DESCRIPTION" name="TX_DESCRIPTION" class="form-control"></textarea>
															                                </div>
															                           </div>
															                           <div>
														                                	<input type="text" id="ID_TICKET" name="ID_TICKET" value="${ticketList[0].TICKET_ID}" style="display:none">
														                                </div>
															                           
																			      </div>
																			      <div class="modal-footer">
																			      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
													                            	 	<button type="submit" class="btn btn-primary">Save</button> 
																			      </div>
																			      </form>
																		    </div>
																		  </div>
																		</div>
																		</security:authorize>
																		
																		<div>
																			<c:if test="${empty worklog}">
																				<div class="text-center"> There is no work logged for this ticket.</div>
																			</c:if>
																		
																			<c:if test="${not empty worklog}">
																				 <table class="table table-hover">
																					  <thead class="thead-default">
																					    <tr>
																					      <th>ID #</th>
																					      <th>Log</th>
																					      <th>User</th>
																					      <th>Date Recorded</th>
																					    </tr>
																					  </thead>
																					  <tbody>
																	
																						<c:forEach var="work" items="${worklog}" varStatus="loop">
																							<tr>
																						      <th scope="row">${loop.index + 1}</th>
																						      <td>${work.TX_WORKLOG}
																						      	<c:if test="${not empty work.TX_DESCRIPTION}">
											                                                		<button type="button" class="btn btn-link" id="description_${loop.index + 1}" onclick="showDescription('${work.TX_DESCRIPTION}');"><i class="fas fa-comment-alt"></i></button>
											                                                	</c:if>
																						      
																						      </td>		
																						      <td>${work.TX_USER}</td>
																						      <td>${work.TX_DATE}</td>
																						     </tr>
																						</c:forEach>
																					</tbody>
																				</table>
																				
																				<button type="button" style="display:none" data-toggle="modal" data-target="#descriptionModal" id="showDescriptionButton">Show Description</button>
								                                                <div class="modal fade" id="descriptionModal" tabindex="-1" role="dialog" aria-labelledby="newTicketModalLabel1">
																	                <div class="modal-dialog modal-lg" role="document">
																	                    <div class="modal-content">
																	                    	<div class="modal-header">
																	                            <h4 class="modal-title" id="exampleModalLabel1">Description</h4>
																	                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
																	                        </div>
																	                        <div class="modal-body">
																	                            <div class="container-fluid">
																	                                <div class="form-group">
																	                            	    <div id="descriptionContent" class="m-2 p-4">
																	                            	    	
																	                            	    </div>
																                            	    </div>
															                            	   </div>
														                            	 	</div>
														                            	 </div>
													                            	</div>
													                            </div>
													                            
																			</c:if>
																		</div>
															        </div>
															    </div>
															</div>
																									
													
													</div>
													<div class="col-md-3 bg-light text-dark">
														<div class="m-2 p-2">
															<h5 class="m-t-30">Ticket Creator</h5>
							                                <span>${ticketList[0].ACCOUNT_NAME}</span><br/>
							                                <span>${ticketList[0].ACCOUNT_EMAIL}</span>
							                            </div>
							                             <div class="m-2 p-2">Created: ${ticketList[0].CREATE_TIME}</div>
							                             <div class="m-2 p-2">
							                             	<h5 class="m-t-30">Entitlement</h5>
							                             		<div class="text-primary">${senList[0].TX_ENTITLEMENT}  
							                             			<c:if test="${senList[0].FL_ACTIVE==1}">
							                             				<span class="badge badge-secondary">ACTIVE SEN</span>
							                             			</c:if>
							                             			<c:if test="${senList[0].FL_ACTIVE!=1}">
							                             				<span class="badge badge-secondary">INACTIVE SEN</span>
							                             			</c:if>
							                             			
							                             			</div>
							                             		<div class="m-2 p-2">Start Date: ${senList[0].TS_START}
																	<br/>End Date: ${senList[0].TS_END}</div>
							                             </div>
													</div>
												</div>
											</div>
			                        </div>
		                        </div>
	                        </div>	
                 			   
                 			 
                 		 	 <c:forEach var="entry" items="${ticketList}" varStatus="loop">
                 		 	 
                 		 	<c:set var="index" value="${loop.index}" />
							<c:set var="index" value="${index + 1}" />
							 
	                 		 	 <c:if test="${!loop.first}">
                 		 	 		<div class="card border border-info">
                 		 	 		 <div class="card-body bg-light">
	                 		 	 		 <div class="row text-center">
			                                <div class="col-6 m-t-4 m-b-4 text-left">
				                               ${entry.THREAD_USER_NAME}<br/>
	                 		 	 		 		${entry.THREAD_USER_EMAIL}
	                 		 	 		    </div>
				                            
				                            <div class="col-6 m-t-4 m-b-4 text-right">
				                         	     ${entry.THREAD_TIME}<br/>
				                         	     <c:if test="${entry.ID_THREAD_USER==sessionScope.userId}">
				                         	     	<button type="button"  class="btn waves-effect waves-light btn-secondary" id="clickCommentEditModal${entry.THREAD_ID}" onclick="loadEditContent(${entry.THREAD_ID})"><i class="fas fa-edit"></i></button>
				                         	     	<button type="button"  id="launchCommentEditModal${entry.THREAD_ID}" data-toggle="modal" data-target="#editCommentModal${entry.THREAD_ID}" style="display:none"><i class="fas fa-edit"></i></button>
													 <div class="modal fade" id="editCommentModal${entry.THREAD_ID}" tabindex="-1" role="dialog" aria-labelledby="editCommentModalLabel1">
											             <div class="modal-dialog modal-lg" role="document">
											             	<form action="updateComment?id=${entry.THREAD_ID}" modelAttribute="ticketDetailsBean" method="POST" name="ticketCommentEditForm" id="ticketCommentEditForm${entry.THREAD_ID}">
												                 <div class="modal-content">
												                     <div class="modal-header">
												                         <h4 class="modal-title" >Edit</h4>
												                         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												                     </div>
												                     <div class="modal-body">
												                         <div class="container-fluid">
												                         	<div id="Edit Comment ModalContent">
												                         		<textarea class="form-control" rows="3" id="EDIT_THREAD_BODY${entry.THREAD_ID}" name="THREAD_BODY" path="THREAD_BODY">${entry.THREAD_BODY}</textarea>
												                         	</div>
												                         </div>
												                         <div>
												                         	<input type="text" id="editCommentThreadId" id="EDIT_COMMENT_TICKET_ID${entry.THREAD_ID}" name="TICKET_ID" value="${entry.TICKET_ID}" style="display:none">
												                         </div>
																	</div>
																	<div class="modal-footer">
																	    <button type="submit" class="btn btn-primary">Save</button>
											                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											                        </div>
																</div>
															</form>
														</div>
													</div>
												 </c:if>
											</div>
	                 		 	 		 		
	                 		 	 		 </div>
                 		 	 		 </div>
		    	                        <div class="card-body  bg-white">
	    	    	                        ${entry.THREAD_BODY}
	    	    	                         <c:if test="${entry.FL_EDIT=='1'}">
	                 		 	 		 			<div class="badge badge-secondary">EDITED</div>
	                 		 	 		 		</c:if>
	                	        	    </div>
	                	        	     <div class="card-footer text-muted bg-secondary">
										    	<c:if test="${entry.FL_ATTACHMENT=='1'}">
										    		<span>
										    			<button type="button" class="btn waves-effect waves-light btn-secondary" onclick="viewThreadAttachments(${entry.THREAD_ID})"> <i class="fas fa-paperclip"></i>  View Attachments</button>
										    		</span>
										    	</c:if>
										    	<c:if test="${entry.FL_ATTACHMENT=='0'}">
										    		 No Attachments
										    	</c:if>
										  </div>
	                    		    </div>
	                    		    <br/>
	                 		 	 </c:if>
	                 		 
	                 		 </c:forEach> 
                 	
                 	</div>
                 </c:if>
                 
                 
                 <c:if test="${not empty ticketList}">
	                 <c:if test="${ticketList[0].TX_STATUS!='CLOSED'}">
		                 <div>
		               		<form class="form-horizontal m-t-20" action="postDetails?id=${ticketList[0].TICKET_ID}" modelAttribute="ticketDetailsBean" method="POST" name="ticketCommentForm" id="ticketCommentForm">
			               		<div class="form-group">
				                     <textarea class="form-control" rows="3" placeholder="Text Here..." id="THREAD_BODY" name="THREAD_BODY" path="THREAD_BODY"></textarea>
			             		</div>
			             		<div class="input-group m-1 p-2">
		                                <div class="custom-file">
		                                    <input type="file" class="custom-file-input" id="inputUploadFile" onchange="updateLabel();">
		                                    <label class="custom-file-label" id="fileUploadLabel" for="inputUploadFile">Choose file</label>
		                                </div>
		                        </div>
		                        <div>
			                        <textarea id="TX_DATA" name="TX_DATA" value="" style="display:none"></textarea>
			                        <input type="text" id="ID_LINK" name="ID_LINK" value="${ticketList[0].TICKET_ID}" style="display:none">  
			                        <input type="text" id="TX_NAME" name="TX_NAME" value="" style="display:none">
			                        <input type="text" id="TX_TYPE" name="TX_TYPE" value="" style="display:none">
			                        <input type="text" id="TX_SIZE" name="TX_SIZE" value="" style="display:none">
		                        </div>
			             		 <div class="modal-footer">
		                             <button type="button" class="btn btn-primary" onclick="processAttachment();">Submit</button> 
								 </div>
		               		</form>
		                </div>
	                </c:if>
                </c:if>
                
                 <c:if test="${ticketList[0].TX_STATUS=='CLOSED'}">
                 	<div class="text-center">
                 		<span>This ticket is closed.</span>
                 	</div>
                 </c:if>
                
                <input type="button" id="modalActivator" style="display:none" data-toggle="modal" data-target="#attachmentModal" data-whatever="@fat">
                <div class="modal fade" id="attachmentModal" tabindex="-1" role="dialog" aria-labelledby="attachmentModalModalLabel1">
		             <div class="modal-dialog modal-lg" role="document">
		                 <div class="modal-content">
		                     <div class="modal-header">
		                         <h4 class="modal-title" id="exampleModalLabel1">Attachments</h4>
		                         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                     </div>
		                     <div class="modal-body">
		                         <div class="container-fluid">
		                         	<div id="attachmentModalContent">
		                         	
		                         	</div>
		                         </div>
							</div>
							<div class="modal-footer">
	                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                        </div>
						</div>
					</div>
				</div>
				
				</div>
                </div>
             </div>
             
             <script>
             
             function showDescription(idx){
     			document.getElementById("descriptionContent").innerHTML = idx;
     			document.getElementById("showDescriptionButton").click();
     		}
		
					$(document).ready(function() {
						if("${tktTab}"=="ATTACH"){
							$('#attachTab').click();
						}else{
							$('#infoTab').click();
						}
					});
					
					function loadEditContent(item){
						CKEDITOR.replace('EDIT_THREAD_BODY'+item);
						//document.getElementById("EDIT_THREAD_BODY").value = ${ticketList[item].THREAD_BODY};
						//document.getElementById("editCommentThreadId").value = ${ticketList[item].THREAD_ID} ;
						document.getElementById("launchCommentEditModal"+item).click();
					}
					
					function saveEdit(){
						document.getElementById("ticketCommentEditForm").action="updateComment?id="+document.getElementById("editCommentThreadId").value;
						document.getElementById("ticketCommentEditForm").method="POST";
						//document.getElementById("ticketCommentEditForm").submit();
						
					}
					
					function viewThreadAttachments(threadid){
						var attachContentDiv = document.getElementById("attachmentModalContent")
						attachContentDiv.innerHTML = "";
						$.ajax({  
	  	                     url:"${contextPath}/viewThreadAttachment",  
	  	                     data:{key: threadid},  
	  	                     success:function(response)  
	  	                     {  
	  	                    	var itemCount = response.length;
	  	                    	
	  	                    	if(itemCount>0){
	  	                    		for(var i=0;i<itemCount;i++){
	  	                    			var id_data = response[i].ID;
		  	                    		var tx_data =  response[i].TX_DATA;
				  	                 	var tx_name =  response[i].TX_NAME;
				  	                 	var tx_type =  response[i].TX_TYPE;
				  	                 	var tx_size =  response[i].TX_SIZE;
				  	                 	
				  	                 	
				  	                 	var img="";
				  	                 	if(tx_type.includes("pdf")){
				  	                 		img='<img src="${pageContext.request.contextPath}/resources/img/icons/pdf.png" alt="pdf" width="20px">';
				  	                 	}else if(tx_type.includes("image")){
				  	                 		img='<img src="${pageContext.request.contextPath}/resources/img/icons/image.png" alt="pdf" width="20px">';
				  	                 	}if(tx_type.includes("word")){
				  	                 		img='<img src="${pageContext.request.contextPath}/resources/img/icons/word.png" alt="pdf" width="20px">';
				  	                 	}if(tx_type.includes("excel")){
				  	                 		img='<img src="${pageContext.request.contextPath}/resources/img/icons/excel.png" alt="pdf" width="20px">';
				  	                 	}if(tx_type.includes("sheet")){
				  	                 		img='<img src="${pageContext.request.contextPath}/resources/img/icons/excel.png" alt="pdf" width="20px">';
				  	                 	}if(tx_type.includes("powerpoint")){
				  	                 		img='<img src="${pageContext.request.contextPath}/resources/img/icons/powerpoint.png" alt="pdf" width="20px">';
				  	                 	}if(tx_type.includes("presentation")){
				  	                 		img='<img src="${pageContext.request.contextPath}/resources/img/icons/powerpoint.png" alt="pdf" width="20px">';
				  	                 	}
				  	                 	
				  	                 	
				  	                  	
				  	                 	var item= document.createElement("Div");
				  	                 	item.className = 'row';
				  	                 	var itemdata = document.createElement("Div");
				  	                 	itemdata.className = 'col-md-10';
				  	                 	itemdata.innerHTML = img + " " + tx_name;
				  	                 	item.appendChild(itemdata);
				  	                 	var itemAction = document.createElement("Div");
				  	                 	itemAction.className = 'col-md-2';
				  	                 	itemAction.innerHTML = '<button type="button" class="btn waves-effect waves-light btn-light"><i class="fas fa-download"></i></button>';
				  	                 	itemAction.addEventListener("click", function(){
					  	      				downloadAttachment(id_data);
					  	    			});
				  	                 	item.appendChild(itemAction);
				  	                 	attachContentDiv.appendChild(item);
				  	                }
	  	                    		$("#modalActivator").click();
	  	                    	}
	  	                     }  
	  	                });
					}
					
					function processAttachment(){
						
						$('#preloader').show();
						//$('#loader').removeClass('hidden')
						
						var input = document.getElementById("inputUploadFile")
						var file = input.files[0];
						if(file==null){
							document.getElementById("ticketCommentForm").submit(); 
		     			}else{
						
							var reader = new FileReader();
							   reader.readAsDataURL(file);
							   reader.onload = function () {
							     console.log(reader.result);
							     document.getElementById("TX_DATA").value = reader.result;
							     	var tx_type = document.getElementById("TX_TYPE").value;
							     if(tx_type.trim() == "application/pdf" || tx_type.trim().includes("image/") 
							    		 || tx_type.trim() == "application/msword" || tx_type.trim() == "application/vnd.openxmlformats-officedocument.wordprocessingml.document" 
							    		 || tx_type.trim() == "application/vnd.ms-excel" || tx_type.trim() == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" 
							    		 || tx_type.trim() == "application/vnd.ms-powerpoint" || tx_type.trim() == "application/vnd.openxmlformats-officedocument.presentationml.presentation" ){
							    
							    	 var tx_size = document.getElementById("TX_SIZE").value;
							    	 if(tx_size<10485760){
							    		 	document.getElementById("ticketCommentForm").submit(); 
							    	 }else{
							    		 alert("Attachments must be less than 10MB is size.");
							    		 $('#loader').addClass('hidden');
							    	 }
							    	   
							     }else{
							    	 alert("Unsupported file type.");
							    	 $('#preloader').hide();
							    	 //$('#loader').addClass('hidden');
							     }
							   };
							   reader.onerror = function (error) {
							     console.log('Error: ', error);
							     document.getElementById("TX_DATA").value = "";
							     alert("Error processing attachment");
							     $('#preloader').hide();
							     //$('#loader').addClass('hidden');
							   };
		     			}
					}
					
					function updateLabel(){
						var input = document.getElementById("inputUploadFile");
						var file = input.files[0];
						var filenm = input.files.item(0).name;
						var filesize = input.files.item(0).size;
						var filetype = input.files.item(0).type;
						
						var fileName = input.value;
						
						document.getElementById("fileUploadLabel").innerHTML = fileName;
						document.getElementById("TX_NAME").value = filenm;
						document.getElementById("TX_TYPE").value = filetype;
						document.getElementById("TX_SIZE").value = filesize;
						document.getElementById("uploadButton").disabled = false;
					}
					
					function downloadAttachment(id){
						$.ajax({  
			                  url:"${contextPath}/common/downloadAttachment",  
			                  data:{key: id},  
			                  success:function(response)  
			                  {  
			                	 var tx_data =  response[0].TX_DATA;
			                	 var tx_name =  response[0].TX_NAME;
			                	 var tx_type =  response[0].TX_TYPE;
			                	 var tx_size =  response[0].TX_SIZE;
			                	 
			                	 
			                	  // Split into two parts
			                	  const parts = tx_data.split(';base64,');

			                	  // Hold the content type
			                	  const contentType = parts[0].split(':')[1];

			                	  // Decode Base64 string
			                	  const decodedData = window.atob(parts[1]);

			                	  // Create UNIT8ARRAY of size same as raw data length
			                	  const uInt8Array = new Uint8Array(decodedData.length);

			                	  // Insert all character code into uInt8Array
			                	  for (let i = 0; i < decodedData.length; ++i) {
			                	    uInt8Array[i] = decodedData.charCodeAt(i);
			                	  }

			                	    var blob= new Blob([uInt8Array], { type: contentType });
			                	
			                	    var link=document.createElement('a');
			                	    link.href=window.URL.createObjectURL(blob);
			                	    link.download=tx_name;
			                	    link.click(); 
  }  
			             });
					}
				
				</script>


	            <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/ckeditor/ckeditor.js"></script>
	            
	            <script>
	            	CKEDITOR.replace( 'THREAD_BODY' );
	             /*    ClassicEditor.create(document.getElementById('THREAD_BODY')); */
	            </script>
	            

	</jsp:body>
</page:user-landing>
