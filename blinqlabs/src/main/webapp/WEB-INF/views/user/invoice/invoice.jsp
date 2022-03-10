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
	    			
	    			<div>
	    				<h5>Your Attachments and Invoices</h5>
	    			</div>
	    				
						<div class="m-2 p-2">
			            	<c:if test="${not empty invoiceList}">
				            	<div class="table-responsive">
		                            <table class="table">
		                                <thead class="bg-info text-white">
		                                    <tr>
		                                        
		                                        <th>Attachment</th>
		                                        <th>Actions</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
			            					<c:forEach var="invoice" items="${invoiceList}">
			            						<tr>
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
		                                            	 <button type="button" class="btn waves-effect waves-light btn-light" onclick="downloadAttachment(${invoice.ID_ATTACH});"><i class="fas fa-download"></i></button>
		                                            </td>
	                                            </tr>
			            					</c:forEach>
			            				</tbody>
			            			</table>
			            		</div>
			            		<ul class="pagination float-right">
	                                                                               <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/user/orderInvoice?page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                                    	<c:if test="${currentPage==loop.index}">
	                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/orderInvoice?page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    	<c:if test="${currentPage!=loop.index}">
	                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/orderInvoice?page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    </c:forEach>
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/user/orderInvoice?page=${currentPage + 1 }" tabindex="-1">Next</a>
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