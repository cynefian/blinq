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
		<div id="page-wrapper">
				<div class="features-tabs-section" style="margin-top: 100px;">
	    			<div class="container">
	    				<div class="row">
		    				<div class="col-md-6 text-left">
								<button type="button" class="btn btn-info" data-toggle="modal" data-target="#newInvoiceModal" data-whatever="@fat"><i class="fas fa-plus"></i> Add Invoice</button>
								<br/><br/>
							</div>
						</div>
						
						<div class="modal fade" id="newInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="newInvoiceModalLabel">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20" action="${contextPath}/admin/addInvoice" method="POST" id="InvoiceForm" name="invoiceForm" modelAttribute="invoiceBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title" id="productCreateModalLabel1">Add New Invoice</h4>
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                        </div>
			                        <div class="modal-body">
			                            
			                        	<div class="form-group">
			                                
			                                <div class=" m-1 p-2">
				                                <div class="custom-file">
				                                    <input type="file" class="custom-file-input" id="inputUploadFile" onchange="updateLabel();">
				                                    <label class="custom-file-label" id="fileUploadLabel" for="inputUploadFile">Choose file</label>
				                                </div>
					                        </div>
					                         <div class="m-4 p-2">
				                                <div class="custom-file">
				                                    <label for="TX_ATTACH_DESCRIPTION" class="control-label">Description:</label>
				                                    <textarea id="TX_ATTACH_DESCRIPTION" class="form-control" rows="3" placeholder="description" name="TX_ATTACH_DESCRIPTION" path="TX_ATTACH_DESCRIPTION"></textarea>
				                                </div>
					                        </div>
					                        <div class="m-4">
					                        	<br/>
					                        </div>
					                        <div class="m-4 p-2">
			                                    <label for="TX_ACCOUNT_EMAIL" class="control-label">Account:</label>
			                                	<input class="form-control form-control-lg" type="text" required placeholder="email" id="TX_ACCOUNT_EMAIL" name="TX_ACCOUNT_EMAIL" path="TX_ACCOUNT_EMAIL" onkeyup="getUserId();">
			                                	<div class="border border-primary ajax-autocomplete" id="ajax-list">
			                                		<ul class="list-group p-3 m-2" id="showList">
			                                		</ul>
			                                	</div>
			                                </div>
					                        <div>
						                        <textarea id="TX_DATA" name="TX_DATA" style="display:none"></textarea>
						                        <input type="text" id="ID_LINK" name="ID_LINK" value="" style="display:none">  
						                        <input type="text" id="TX_NAME" name="TX_NAME" value="" style="display:none">
						                        <input type="text" id="TX_TYPE" name="TX_TYPE" value="" style="display:none">
						                        <input type="text" id="TX_SIZE" name="TX_SIZE" value="" style="display:none">
					                        </div>
			                            </div>
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="button" class="btn btn-primary" onClick="validateSubmit();">Add</button> 
			                           
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>
			            
			            <div class="m-2 p-2">
			            	<c:if test="${not empty invoiceList}">
				            	<div class="table-responsive">
		                            <table class="table">
		                                <thead class="bg-info text-white">
		                                    <tr>
		                                        <th>User</th>
		                                        <th>Attachment</th>
		                                        <th>Actions</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
			            					<c:forEach var="invoice" items="${invoiceList}">
			            						<tr>
	                                                <td>
	                                                	<div>
	                                                		<div class="text-primary h5">${invoice.TX_EMAIL}</div>
	                                                		<div>${invoice.TX_FIRSTNAME} ${invoice.TX_LASTNAME}</div>
	                                                	</div>
	                                                
	                                                </td>
	                                                <td>
	                                                	<c:if test="${fn:containsIgnoreCase(invoice.TX_ATTACH_TYPE, 'pdf')}">
		                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/pdf.png" alt="pdf" width="20px">
		                                                </c:if>
		                                                <c:if test="${fn:containsIgnoreCase(invoice.TX_ATTACH_TYPE, 'image')}">
		                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/image.png" alt="image" width="20px">
		                                                </c:if>
		                                                <c:if test="${fn:containsIgnoreCase(invoice.TX_ATTACH_TYPE, 'word')}">
		                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/word.png" alt="word" width="20px">
		                                                </c:if>
		                                                <c:if test="${fn:containsIgnoreCase(invoice.TX_ATTACH_TYPE, 'excel')}">
		                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/excel.png" alt="excel" width="20px">
		                                                </c:if>
		                                                <c:if test="${fn:containsIgnoreCase(invoice.TX_ATTACH_TYPE, 'sheet')}">
		                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/excel.png" alt="excel" width="20px">
		                                                </c:if>
		                                                <c:if test="${fn:containsIgnoreCase(invoice.TX_ATTACH_TYPE, 'powerpoint')}">
		                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/powerpoint.png" alt="powerpoint" width="20px">
		                                                </c:if>
		                                                <c:if test="${fn:containsIgnoreCase(invoice.TX_ATTACH_TYPE, 'presentation')}">
		                                                	<img src="${pageContext.request.contextPath}/resources/img/icons/powerpoint.png" alt="powerpoint" width="20px">
		                                                </c:if>
		                                                <span class="text-info">  ${invoice.TX_ATTACH_NAME} </span>
		                                                <div>
		                                                	Date Uploaded: <span class="text-secondary">${invoice.TX_DATE}</span>
		                                                </div>
		                                                <div>
		                                                	Description: <span class="text-primary">${invoice.TX_ATTACH_DESCRIPTION}</span>
		                                                </div>
	                                               </td>
		                                            <td>
		                                             	<div class="row">
			                                            	<div class="col-md-5 m-2">
			                                            		<button type="button" class="btn waves-effect waves-light btn-light" onclick="downloadAttachment(${invoice.ID_ATTACH});"><i class="fas fa-download"></i></button>
			                                            	</div>
			                                            	 <div class="col-md-5 m-2" >
				                                            	 <form action="${contextPath}/admin/deleteInvoice?id=${invoice.ID_ATTACH}" method="POST">
				                                            	 	<button type="submit" class="btn waves-effect waves-light btn-danger"><i class="fas fa-trash-alt"></i></button>
				                                            	 </form>
			                                            	 </div>
		                                            	 </div>
		                                            </td>
	                                            </tr>
	                                            
			            					</c:forEach>
			            				</tbody>
			            			</table>
			            		</div>
			            		
			            		<ul class="pagination float-right">
	                                                                               <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/admin/invoice?page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                                    	<c:if test="${currentPage==loop.index}">
	                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/invoice?page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    	<c:if test="${currentPage!=loop.index}">
	                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/invoice?page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    </c:forEach>
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/admin/invoice?page=${currentPage + 1 }" tabindex="-1">Next</a>
	                                        	</li>
	                                        </c:if>

	                                    </ul>
			            	</c:if>
			            	
		            	 	<c:if test="${empty invoiceList}">
		            	 		<div class="text-center">
		            	 			No Attachments found.
		            	 		</div>
		            	 	</c:if>
			            </div>
			            
					</div>
				</div>
			</div>
			
			<script>
			
			$(document).ready(function() {
    			$('#ajax-list').hide();
    		});
			
			function validateSubmit(){
				processAttachment();
			}
			
		    function getUserId(){  
	   	    	   var userNameElement = document.getElementById("TX_ACCOUNT_EMAIL").value;
	   	    	   if(userNameElement != ''&& userNameElement!=null)  
	   	           {  
	   	                $.ajax({  
	   	                     url:"${contextPath}/admin/service/SEN/getUserAccount",  
	   	                     data:{key: userNameElement},  
	   	                     success:function(data)  
	   	                     {  
	   	                    	var res = data.toString().split(",");
	   	                    	var items = "";
	   	                    	for (var i in res) {
	   	                    	 	items = items + "<li class='ajax-autocomplete-item'>"+res[i]+"</li>";
	   	                    	}
	   	                    	$('#ajax-list').show();
	   	                    	$('#showList').html(items);  
	   	                     }  
	   	                });  
	   	           }else{
	   	        	$('#showList').html('');
	   	        	$('#ajax-list').hide();
	   	           }  
	   	      }  
	   	      $(document).on('click', 'li', function(){  
	   	           $('#TX_ACCOUNT_EMAIL').val($(this).text());  
	   	        	$('#showList').html('');
	   	        	$('#ajax-list').hide();
	   	      });  
			
	   	      
	   	      
	   	   function processAttachment(){
	   		   $('#preloader').show();
				//$('#loader').removeClass('hidden')
				
				var input = document.getElementById("inputUploadFile")
				var file = input.files[0];
				if(file==null){
					alert("Missing Invoice Attachment"); 
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
					    		 document.getElementById("InvoiceForm").submit();
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
	</jsp:body>
</page:user-landing>