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
         
           <!-- ============================================================== -->
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- basic table -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                            	<div>
                                	<h4 class="card-title">Tickets</h4>
                                	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#newTicketModal" data-whatever="@fat">New Ticket</button>
                                	<br/><br/>
                                		 <div class="modal fade" id="newTicketModal" tabindex="-1" role="dialog" aria-labelledby="newTicketModalLabel1">
							                <div class="modal-dialog modal-lg" role="document">
							                    <div class="modal-content">
							                    <%-- <form class="form-horizontal m-t-20" action="UserTickets" method="POST" name="userTicketsForm" modelAttribute="userTicketsBean"  enctype="multipart/form-data">> --%>
							                    
							                    <form class="form-horizontal m-t-20" action="${contextPath}/user/UserTickets" modelAttribute="userTicketsBean" method="POST" name="userTicketsBean" id="ticketForm">
							                        <div class="modal-header">
							                            <h4 class="modal-title" id="exampleModalLabel1">New Ticket</h4>
							                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							                        </div>
							                        <div class="modal-body">
							                            <div class="container-fluid">
							                                <div class="form-group">
							                                <div>
							                                    <label for="TX_SEN" class="control-label">Select SEN:</label>
							                                    <select class="select2 form-control custom-select" required style="width: 100%; height:36px;" id="TX_SEN" name="TX_SEN" path="TX_SEN">
							                                    <option value="">Select</option>
							                                        <c:if test="${not empty userSenList}">
									                                     <c:forEach var="entry" items="${userSenList}">
									                                   		  <option value="${entry.ID}">${entry.TX_ENTITLEMENT} - ${entry.TX_ENTITLEMENT_TYPE} : [${entry.TS_START} - ${entry.TS_END}]</option>
									                                     </c:forEach>
								                                    </c:if>
								                               </select>
							                                </div>
							                                <br/>
							                                
							                                <div>
							                                	<label for="TX_SUBJECT" class="control-label">Title:</label>
							                                	 <div class="form-group">
							                                        <input type="text" class="form-control" id="TX_SUBJECT" name="TX_SUBJECT" path="TX_SUBJECT" />
							                                    </div>
							                                </div>
							                                <br/>
							                                
								                                <div>
								                                	<label for="TX_BODY" class="control-label">Details:</label>
								                        	           <!--   <textarea name="ckeditor" id="ckeditor" cols="50" rows="15" class="ckeditor"> 
								                        			</textarea> --> 
								                        			<!--  <textarea>Next, get a free Tiny Cloud API key!</textarea> -->
								                        			<!-- <textarea id="TX_BODY" name="TX_BODY" path="TX_BODY" ></textarea> -->
								                        			<textarea class="form-control" rows="3" placeholder="Text Here..." id="TX_BODY" name="TX_BODY" path="TX_BODY"></textarea> 
	                               						        </div>
								                            	<br/>
								                            	
								                            	<div class="m-2 p-2">
								                            	<label class="control-label">Attachment:</label>
														    		<div class="row">
														    		        <div class="input-group m-1 p-2">
										                                        <div class="custom-file">
										                                            <input type="file" class="custom-file-input" id="inputUploadFile" onchange="updateLabel();">
										                                            <label class="custom-file-label" id="fileUploadLabel" for="inputUploadFile">Choose file</label>
										                                        </div>
										                                    </div>
										                                    <div>
										                                    <textarea id="TX_DATA" name="TX_DATA" value="" style="display:none"></textarea>
										                                    <input type="text" id="TX_NAME" name="TX_NAME" value="" style="display:none">
										                                    <input type="text" id="TX_TYPE" name="TX_TYPE" value="" style="display:none">
										                                    <input type="text" id="TX_SIZE" name="TX_SIZE" value="" style="display:none">
										                                    </div>
										                           </div>
													    		</div>
								              			     
							                                </div>
							                                <div id="error-msg" class="text-center text-danger">
							                                </div>
						                                </div>
							                        </div>
							                        <div class="modal-footer">
							                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							                             <button type="button" class="btn btn-primary"  onclick="processAttachment();">Submit Ticket</button> 
							                        </div>
							                        </form>
							                    </div>
							                </div>
							            </div>   
			                    </div>
			                    
			                    <div class="row m-t-40">
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                        <a class="card card-hover" href="${contextPath}/user/UserTickets?page=1&filter=all" method="GET">
                                            <div class="box bg-info text-center">
                                                <h1 class="font-light text-white">${totalTickets}</h1>
                                                <h6 class="text-white">Total Tickets</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                        <a class="card card-hover" href="${contextPath}/user/UserTickets?page=1&filter=responded" method="GET">
                                            <div class="box bg-primary text-center">
                                                <h1 class="font-light text-white">${respondedTickets}</h1>
                                                <h6 class="text-white">Responded</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                        <a class="card card-hover" href="${contextPath}/user/UserTickets?page=1&filter=resolved" method="GET">
                                            <div class="box bg-success text-center">
                                                <h1 class="font-light text-white">${resolvedTickets}</h1>
                                                <h6 class="text-white">Resolved</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                        <a class="card card-hover" href="${contextPath}/user/UserTickets?page=1&filter=pending" method="GET">
                                            <div class="box bg-dark text-center">
                                                <h1 class="font-light text-white">${pendingTickets}</h1>
                                                <h6 class="text-white">Pending</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <!-- Column -->
                                </div>
                                
                                <c:if test="${empty ticketlist}">
                                	<div class="text-center">
										<span>There are no tickets here.</span>
									</div>
								</c:if>
								
								<c:if test="${not empty ticketlist}">
									<c:forEach var="entry" items="${ticketlist}">
										<div class="card m-2 border border-primary">
											<div class="card-title">
												<div class="row p-2">
													<div class="col-md-10">
														<h4><a href="${contextPath}/ticketDetails?id=${entry.ID}" method="GET"  class="text-primary">${entry.TX_SUBJECT}</a></h4>
														<span class="card-subtitle mb-2 text-muted"><a href="${contextPath}/ticketDetails?id=${entry.ID}" method="GET" class="text-primary">${entry.ID}</a></span>
													</div>
													<div class="col-md-2 text-right">
														<c:if test="${entry.TX_STATUS=='OPEN'}">
					                                    	<span class="label label-primary">${entry.TX_STATUS}</span>
					                                    </c:if>
					                                    <c:if test="${entry.TX_STATUS=='IN PROGRESS'}">
					                                    	<span class="label label-warning">${entry.TX_STATUS}</span>
					                                    </c:if>
					                                    <c:if test="${entry.TX_STATUS=='CLOSED'}">
					                                    	<span class="label label-success">${entry.TX_STATUS}</span>
					                                    </c:if>
					                                    <c:if test="${entry.TX_STATUS=='INVESTIGATION'}">
					                                    	<span class="label label-default text-dark">${entry.TX_STATUS}</span>
					                                    </c:if>
					                                    <c:if test="${entry.TX_STATUS=='WAITING FOR CUSTOMER'}">
					                                    	<span class="label label-info">${entry.TX_STATUS}</span>
					                                    </c:if>
					                                    <c:if test="${entry.TX_STATUS=='ESCALATED'}">
					                                    	<span class="label label-danger">${entry.TX_STATUS}</span>
					                                    </c:if>
					                                    <div class="text-right m-2">
															<a href="${contextPath}/ticketDetails?id=${entry.ID}" method="GET" class="font-medium link">View Details</a>
														</div>
														<div>
														 <c:if test="${entry.TX_STATUS=='OPEN'}">
										               		<a href="${contextPath}/deleteTicket?id=${entry.ID}&source=USER"><button type="button" class="btn waves-effect waves-light btn-danger"><i class="far fa-trash-alt"></i></button></a>
										               </c:if>
														</div>
													</div>
												</div>
											 	
											</div>
										<%-- 	<div class="card-body">
												<div class="">
													<span>Reporter: ${entry.TX_ACCOUNT_NAME}</span>
													<div class="text-right">
														<a href="${contextPath}/ticketDetails?id=${entry.ID}" method="GET" class="font-medium link">View Details</a>
													</div>
												</div>
											</div> --%>
											<div class="card-footer bg-active">
												<div class="row">
													<div class="col-md-6">
														Entitlement: <span class="text-primary">${entry.TX_PRODUCT}</span>
													</div>
													<div class="col-md-6 text-right">
														Created: <span class="">${entry.TX_TIME}</span>
													</div>
												</div>
												
											</div>
										</div>
									</c:forEach>
								
	                                <div>
	                                    <ul class="pagination float-right">
	                                                                               <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/user/UserTickets?filter=${currentFilter}&page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                                    	<c:if test="${currentPage==loop.index}">
	                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/UserTickets?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    	<c:if test="${currentPage!=loop.index}">
	                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/UserTickets?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    </c:forEach>
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/user/UserTickets?filter=${currentFilter}&page=${currentPage + 1 }" tabindex="-1">Next</a>
	                                        	</li>
	                                        </c:if>

	                                    </ul>
	                                </div>
                                
                                </c:if>
								
                                
                                
                             </div>
                        </div>
                     </div>
               	</div>
             </div>
             
             <script>
             function processAttachment(){
     			
     			//$('#loader').removeClass('hidden')
     			
     			var senValue = document.getElementById("TX_SEN").value;
     			var titleValue = document.getElementById("TX_SUBJECT").value;
     			var errormsg = "";
     			if (senValue==null || senValue == ""){
     				errormsg = "You must choose an SEN to submit the ticket.";
     				document.getElementById("error-msg").innerHTML = errormsg;
     			}else if (titleValue==null || titleValue == ""){
     				errormsg = "Please enter the Title of the ticket";
     				document.getElementById("error-msg").innerHTML = errormsg;
     			}else{
     				errormsg = "";
     				document.getElementById("error-msg").innerHTML = errormsg;
	     			var input = document.getElementById("inputUploadFile")
	     			var file = input.files[0];
	     			if(file==null){
	     				document.getElementById("ticketForm").submit(); 
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
		     			    		 	document.getElementById("ticketForm").submit(); 
		     			    	 }else{
		     			    		 alert("Attachments must be less than 10MB is size.");
		     			    	 }
		     			    	   
		     			     }else{
		     			    	 alert("Unsupported file type.");
		     			     }
		     			   };
		     			   reader.onerror = function (error) {
		     			     console.log('Error: ', error);
		     			     document.getElementById("TX_DATA").value = "";
		     			     alert("Error processing attachment");
		     			   };
	     			}
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
     		}
             </script>
          
             
             
           <!-- This Page JS -->
   <%--  <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/ckeditor/samples/js/sample.js"></script>                      
   <script>
	    //default
	    initSample();
	
	    //inline editor
	    // We need to turn off the automatic editor creation first.
	    CKEDITOR.disableAutoInline = true;
	
	    CKEDITOR.inline('editor2', {
	        extraPlugins: 'sourcedialog',
	        removePlugins: 'sourcearea'
	    });
	
	    var editor1 = CKEDITOR.replace('editor1', {
	        extraAllowedContent: 'div',
	        height: 460
	    });
	    editor1.on('instanceReady', function() {
	        // Output self-closing tags the HTML4 way, like <br>.
	        this.dataProcessor.writer.selfClosingEnd = '>';
	
	        // Use line breaks for block elements, tables, and lists.
	        var dtd = CKEDITOR.dtd;
	        for (var e in CKEDITOR.tools.extend({}, dtd.$nonBodyContent, dtd.$block, dtd.$listItem, dtd.$tableContent)) {
	            this.dataProcessor.writer.setRules(e, {
	                indent: true,
	                breakBeforeOpen: true,
	                breakAfterOpen: true,
	                breakBeforeClose: true,
	                breakAfterClose: true
	            });
	        }
	        // Start in source mode.
	        this.setMode('source');
	    });
    </script> --%>
    
    <!--  <script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>
      <script>tinymce.init({ selector:'textarea' });</script>
     -->     
     
   <!--   <script src="https://cdn.ckeditor.com/ckeditor5/11.1.1/classic/ckeditor.js"></script>
   <script>
    ClassicEditor
        .create( document.querySelector( '#TX_BODY' ) )
        .catch( error => {
            console.error( error );
        } );
	</script> -->
	
	</jsp:body>
</page:user-landing>
