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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<page:user-landing>
	<jsp:body>
	
	 <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div id="preloader" class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        
        <div id="loader" class="spinner-ring hidden spinner-overlay"></div>
        
        
		<div id="page-wrapper">
			<div class="container">
				<div class="m-t-2 p-2">
	   					<a href="${contextPath}/admin/adminServiceEntitlements"><button type="button" class="btn btn-info"><i class="fas fa-arrow-alt-circle-left"></i></button></a>
						<br/><br/>
				</div>
				
				 <div class="m-2">
				
					<c:if test="${empty SENFormList}">
		           		<h4>There was an error retrieving Entitlement details.</h4>
		           	</c:if>
		           	
		           	<c:if test="${not empty SENFormList}">
		           		<c:forEach var="entry" items="${SENFormList}">
		           			<div class="card card-hover border border-primary">
							    <div class="card-header bg-light">
								    <div class="row">
								    	<div class="col-md-6">
								    		<h4 class="m-b-0 text-primary">${entry.TX_ACCOUNT_EMAIL}</h4>
								    	</div>
								    	<div class="col-md-6">
								    		<div class="d-flex flex-row">
	                               			 	<div class="p-10 ">
	                               			 		 <c:if test="${entry.FL_ACTIVE=='0'}">
											        	<span class="badge badge-pill badge-light">INACTIVE</span>
											        </c:if>
											        <c:if test="${entry.FL_ACTIVE=='1'}">
											        	<span class="badge badge-pill badge-success">ACTIVE</span>
										        	</c:if> 	
	                               			 	</div>
	                               			 	
	                               			 	<div class="">
	                               			 		<form action="" method="" id="toggleUpdateForm_${entry.ID}">
											            <label class="custom-toggle">
											            <c:if test="${entry.FL_ACTIVE=='0'}">
												        	<input type="checkbox" onclick="updateSenStatus(this);" id="${entry.ID}" name="${entry.ID}_${entry.ID_ACCOUNT}" path="${entry.ID}_${entry.ID_ACCOUNT}" value="0" >
												        </c:if>
												        <c:if test="${entry.FL_ACTIVE=='1'}">
												        	<input type="checkbox" onclick="updateSenStatus(this);" id="${entry.ID}" name="${entry.ID}_${entry.ID_ACCOUNT}" path="${entry.ID}_${entry.ID_ACCOUNT}" value="1" checked >
												        </c:if>
												        <span class="custom-toggle-slider rounded-circle"></span>
											  			</label>
										  			</form>
	                               			 	</div>
	                               			 </div>
	                                   	</div>
								    </div>
							        
							    </div>
							    <div class="card-body">
							    	<div class="row align-center">
							    		<div class="col-md-6 p-2">
							    			<h3 class="card-title"> ${entry.TX_ENTITLEMENT_TYPE}</h3>
							    			<span class="text-muted">${entry.TX_ENTITLEMENT_DESCRIPTION}</span>
							    		</div>
							    		
							    	</div>
							        
							    </div>
							    <div class="bg-secondary text-white">
							    	
							    </div>
							</div>
							
							<div>
								<div class="row">
									<div class="col-md-9">
									
										<!-- Nav tabs -->
											<ul class="nav nav-tabs" role="tablist">
											    <li class="nav-item"> <a id="infoTab" class="nav-link active" data-toggle="tab" href="#info" role="tab"><span class="hidden-sm-up"><i class="fas fa-info-circle"></i></span> <span class="hidden-xs-down">Info</span></a> </li>
											    <li class="nav-item"> <a id="activityTab" class="nav-link" data-toggle="tab" href="#activity" role="tab"><span class="hidden-sm-up"><i class="fas fa-cog"></i></span> <span class="hidden-xs-down">Activity</span></a> </li>
											    <li class="nav-item"> <a id="attachTab" class="nav-link" data-toggle="tab" href="#attachments" role="tab"><span class="hidden-sm-up"><i class="fas fa-paperclip"></i></span> <span class="hidden-xs-down">Attachments</span></a> </li>
											</ul>
											<!-- Tab panes -->
											<div class="tab-content tabcontent-border">
											    <div class="tab-pane active" id="info" role="tabpanel">
											        <div class="m-2 p-20">
											            <h3>Entitlement Rate(s)</h3>
											            	<div>
										        				<c:if test="${not empty senRatesList}">
										        						<div class="table-responsive">
										                                    <table class="table">
										                                        <thead class="bg-info text-white">
										                                            <tr>
										                                                <th>#</th>
										                                                <th>Tier</th>
										                                                <th>Cost</th>
										                                                <th>Limits</th>
										                                            </tr>
										                                        </thead>
										                                        <tbody>
										                                        	<c:forEach var="rate" items="${senRatesList}" varStatus="loop">
											                                            <tr>
											                                                <td>${loop.index + 1}</td>
											                                                <td>${rate.TX_TIER}</td>
											                                                <td>$ ${rate.TX_COST}</td>
											                                                <td>${rate.TX_LIMIT}</td>
											                                            </tr>
										                                            </c:forEach>
										                                        </tbody>
										                                    </table>
										                                </div>
										        				</c:if>   
										        				
										        				<c:if test="${empty senRatesList}">
										        					<div class="text-center">
											        						Information not found or Error retrieving information. Please contact Administrator for support.
											        					</div>
										        				</c:if> 	
											            	</div>
											        </div>
											    </div>
											    <div class="tab-pane  p-20" id="activity" role="tabpanel">
											    
												    <div class="m-2 p-20">
												            	<div>
													            		
													            		<div class="table-active p-4 m-2">
													            			<form id="dateFilterForm" action="" method="">
														            			<span>Filters:</span>
														            			<div class="row">
															            			<div class="col-sm-12 col-md-6">
															                             <h4 class="card-title">Start Date</h4>
														                                   <div class="form-group">
														                                        <input type="date" id="startDateFilter" class="form-control" value="${startDate}">
														                                    </div>
																                    </div>
																                    
																                    <div class="col-sm-12 col-md-6">
																                        <h4 class="card-title">End Date</h4>
														                                    <div class="form-group">
														                                        <input type="date" id="endDateFilter" class="form-control"  value="${endDate}">
														                                    </div>
																                    </div>
																                </div>
																                <div>
																                	<input type="text" value="${entry.ID}" style="display:none" id="senIdFilter">
																                </div>
														            			<div class="row">
															            			<div class="col-md-12 text-right">
															            				<button type="button" class="btn btn-primary" onclick="clearFilter(${entry.ID});">Clear</button>
															            				<button type="button" class="btn btn-info" onclick="filterActivity();">Filter</button>
															            			</div>
															            		</div>
														            		</form>
													            		</div>
													            	<div class="m-2 text-center">
													            		<h6 class="card-subtitle p-2">Time invested: ${senUsageTime}</h6>
													            	</div>
													            	
													            	<div class="m-2">
												        				<c:if test="${not empty senActivityList}">
												        						<div class="table-responsive">
												                                    <table class="table">
												                                        <thead class="bg-info text-white">
												                                            <tr>
												                                                <th>#</th>
												                                                <th>Date</th>
												                                                <th>Ticket</th>
												                                                <th>Worklog</th>
												                                            </tr>
												                                        </thead>
												                                        <tbody>
												                                        	<c:forEach var="work" items="${senActivityList}" varStatus="loop">
													                                            <tr>
													                                                <td>${loop.index + 1}</td>
													                                                <td>${work.TX_DATE}</td>
													                                                <td><a href="${contextPath}/ticketDetails?id=${work.ID_TICKET}">${work.ID_TICKET}</a></td>
													                                                <td>
													                                                	${work.TX_WORKLOG} 
													                                                	<c:if test="${not empty work.TX_DESCRIPTION}">
													                                                		<button type="button" class="btn btn-link" id="description_${loop.index + 1}" onclick="showDescription('${work.TX_DESCRIPTION}');"><i class="fas fa-comment-alt"></i></button>
													                                                	</c:if>
													                                                </td>
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
															                            
												                                </div>
												        				</c:if>
												        				
												        				<c:if test="${empty senActivityList}">
												        					<div class="text-center">
												        						There is no activity recorded for this Entitlement. Try modifying the filter.
												        					</div>
												        				</c:if>
											        				</div>
											        				
											        				    	
												            	</div>
												        </div>
											    
											    
											    
											    
											    </div>
											    <div class="tab-pane p-20" id="attachments" role="tabpanel">
											    	<div class="m-2 p-2">
											    		<div class="row">
											    			<form id="uploadSenAttachmentForm" action = "" method = "post" enctype = "multipart/form-data" modelAttribute="attachmentForm">
							                                    <div class="input-group m-1 p-2">
							                                        <div class="custom-file">
							                                            <input type="file" class="custom-file-input" id="inputUploadFile" onchange="updateLabel();">
							                                            <label class="custom-file-label" id="fileUploadLabel" for="inputUploadFile">Choose file</label>
							                                        </div>
							                                        <div class="input-group-append">
							                                            <button class="btn btn-outline-info" type="button" onclick="processAttachment();" id="uploadButton">Upload</button>
							                                        </div>
							                                    </div>
							                                    <div>
							                                    <textarea id="TX_DATA" name="TX_DATA" value="" style="display:none"></textarea>
							                                    <input type="text" id="ID_LINK" name="ID_LINK" value="${entry.ID}" style="display:none">  
							                                    <input type="text" id="TX_NAME" name="TX_NAME" value="" style="display:none">
							                                    <input type="text" id="TX_TYPE" name="TX_TYPE" value="" style="display:none">
							                                    <input type="text" id="TX_SIZE" name="TX_SIZE" value="" style="display:none">
							                                    </div>
							                                </form>
											    		</div>
											    		
											    		<div>
											    			<c:if test="${not empty senAttachList}">
											    				<div class="table-responsive">
										                                    <table class="table">
										                                        <thead class="bg-info text-white">
										                                            <tr>
										                                                <th>Attachment</th>
										                                                <th>Action</th>
										                                            </tr>
										                                        </thead>
										                                        <tbody>
										                                        	<c:forEach var="attach" items="${senAttachList}" varStatus="loop">
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
											                                                </span>  <span class="p-2 text-danger"><a href="${contextPath}/admin/deleteSenAttachment?id=${attach.ID}&sen=${entry.ID}" class="text-danger"><i class="fas fa-trash-alt"></i></a></span> </td>
											                                            </tr>
										                                            </c:forEach>
										                                        </tbody>
										                                    </table>
										                                </div>
										                                
										                                <ul class="pagination float-right">
                                                                             <c:if test="${currentPage!=1 && currentPage>0}">
									                                        	 <li class="page-item mr-2">
									                                            	<a class="page-link" href="${contextPath}/admin/SENdetails?source=ATTACH&id=${entry.ID}&page=${currentPage - 1 }" tabindex="-1">Previous</a>
									                                        	</li>
									                                        </c:if>
									                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
									                                    	<c:if test="${currentPage==loop.index}">
									                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/SENdetails?source=ATTACH&id=${entry.ID}&page=${loop.index}">${loop.index}</a></li>
									                                    	</c:if>
									                                    	<c:if test="${currentPage!=loop.index}">
									                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/SENdetails?source=ATTACH&id=${entry.ID}&page=${loop.index}">${loop.index}</a></li>
									                                    	</c:if>
									                                    </c:forEach>
									                                    	<c:if test="${currentPage!=totalPages}">
									                                        	 <li class="page-item ml-2">
									                                            	<a class="page-link" href="${contextPath}/admin/SENdetails?source=ATTACH&id=${entry.ID}&page=${currentPage + 1 }" tabindex="-1">Next</a>
									                                        	</li>
									                                        </c:if>
								
									                                    </ul>
											    			</c:if>
											    			<c:if test="${empty senAttachList}">
											    				<div class="m-2 p-2 text-center">
											    					There are no attachments
											    				</div>
											    			</c:if>
											    		</div>
											    	</div>
											    </div>
											</div>
																					
									
									</div>
									<div class="col-md-3 bg-info text-white">
										<div class="m-2 p-2">
								    		<strong>${entry.TX_ENTITLEMENT}</strong>
								    	</div>
										<div class="m-2 p-2">
										
							    			 <p class="card-text"><i class="fas fa-calendar-alt"></i>  Start Date: ${entry.TS_START}</p>
								        	 <p class="card-text"><i class="fas fa-calendar-alt"></i>  End Date: ${entry.TS_END}
								        	 	<c:if test="${entry.TX_DURATION != 'DEFINED'}">
										        	<div class="badge badge-outline badge-pill badge-secondary">${entry.TX_DURATION}</div>
										        </c:if>
										        </p>	 
										</div>
										
										<div class="m-1">
											<div class="card text-primary">
												<div class="card-title p-2">
													<span class="h5">Current Billing Cycle</span>
												</div>
												<div class="card-body">
													<p class="card-text"><i class="fas fa-calendar-alt"></i> Cycle Start: ${currentCycleStart}</p>
													<p class="card-text"><i class="fas fa-calendar-alt"></i> Cycle End: ${currentCycleEnd}</p>
													<p class="card-text"><i class="fas fa-server"></i> Tier: ${currentTier}</p>
													<p class="card-text"><i class="fas fa-dollar-sign"></i> Tier Cost: ${currentTierCost}</p>
													<p class="card-text"><i class="fas fa-hourglass-start"></i> Total Rollover Hours: ${totalRolloverHours}</p>
													<p class="card-text"><i class="fas fa-hourglass-half"></i> Avail. Rollover Hours: ${availRolloverHours}</p>
													<p class="card-text"><i class="fas fa-hourglass-half"></i> Cycle time invested: ${cycleCaltotalTime}</p>
													<p class="card-text"><i class="fas fa-hourglass-half"></i> Cycle hours billable: ${billableHour}</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
		           	</c:if>
	           	</div>
			</div>
		</div>
		
		<script>
		
		
		function clearFilter(id){
			var filterForm = document.getElementById("dateFilterForm");
			filterForm.action="${contextPath}/admin/SENdetails?id="+id+"&source=ACTIVITY";
			filterForm.method = "POST";
			filterForm.submit();
		}
		
		function filterActivity(){
			var startDate = document.getElementById("startDateFilter").value;
			var endDate = document.getElementById("endDateFilter").value;
			var senId = document.getElementById("senIdFilter").value;
			var filterForm = document.getElementById("dateFilterForm");
			
			filterForm.action="${contextPath}/admin/SENdetails?id="+senId+"&startDate="+startDate+"&endDate="+endDate+"&source=ACTIVITY";
			filterForm.method = "POST";
			filterForm.submit();
			
		}
		
		
		function showDescription(idx){
			document.getElementById("descriptionContent").innerHTML = idx;
			document.getElementById("showDescriptionButton").click();
		}
	
			
		$(document).ready(function() {
			document.getElementById("uploadButton").disabled = true;
			if("${pageTab}"=="ATTACH"){
				$('#attachTab').click();
			}else if ("${pageTab}"=="ACTIVITY"){
				$('#activityTab').click();
			}else{
				$('#infoTab').click();
			}
		});
		
		function processAttachment(){
			
			$('#preloader').show();
			//$('#loader').removeClass('hidden')
			
			var input = document.getElementById("inputUploadFile")
			var file = input.files[0];
			
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
			    		 	document.getElementById("uploadSenAttachmentForm").action = "${contextPath}/admin/uploadSenAttachment";
							document.getElementById("uploadSenAttachmentForm").method = "POST";
							document.getElementById("uploadSenAttachmentForm").submit(); 
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

                	  // Return BLOB image after conversion
                	    var blob= new Blob([uInt8Array], { type: contentType });
                	  //return blib;
                	 
                	 
                	 
                	 
                	// var blob=new Blob([tx_data]);
                	    var link=document.createElement('a');
                	    link.href=window.URL.createObjectURL(blob);
                	    link.download=tx_name;
                	    link.click(); 

                	    
                	    
                	/*  var resultwindow = window.open(tx_name);
                	 var doc = $('body', resultwindow.document);
                	 doc.append(tx_data); */
                	 
                	 //window.location.href = resultwindow;
                	   // window.location.href = tx_data;
                  }  
             });
		}
		</script>
	</jsp:body>
</page:user-landing>