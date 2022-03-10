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
	
	<div id="page-wrapper" class="page-wrapper">
	<!--  side bar -->	
         <%-- <jsp:include page="/WEB-INF/shared/user-sidebar.jsp" />  --%>
  		
	 	    <div class="container">
		      <div class="header">
			      <div class="card text-center mt-3">
			      	<div class="card-body text-white align-items-center d-flex no-block p-15 bg-primary mx-3 px-3">
		                   <div class="">
									<c:if test="${not empty sessionScope.userImage}">
		                            	<img src="${sessionScope.userImage}" alt="user" class="rounded-circle" width="60">
		                            </c:if>
		                            <c:if test="${empty sessionScope.userImage}">
		                            	<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="60">
		                            </c:if>
										</div>
		                   <div class="m-l-10 px-2 mx-2">
		                        <%-- <h4 class="m-b-0"><span>${fname} ${lname}</span></h4> --%> 
		                         <h4 class="m-b-0">
												<span>${sessionScope.firstname} ${sessionScope.lastname}</span>
											</h4>
		                        <%-- <h4 class="m-b-0"><span><%session.getAttribute("firstname");%> <%session.getAttribute("lastname");%></span></h4> --%>
		                       <p class=" m-b-0 text-white">
												<security:authentication property="principal.username" />
											</p>
		                   </div>
	               </div>
		      
		    	 </div>
		    
		  	</div>
		  </div>

  

  <div class="">
    <div class="container">
    	<div>
    		<h2 class="lead text-primary">Licenses </h2>
    	</div>
    		<div class="m-4 row">
    		<div class=" col-lg-6 col-sm-12 text-left">
    			<form action="#" method="GET" id="licenseFilterForm">
	    			<div>
	    				<input type="checkbox" ${evalStatus==true?'checked':''} id="evalCheck" onclick="getLicenses();"><span class="h6"> Evaluation</span>
	    				<input type="checkbox" ${regStatus==true?'checked':''}  id="regCheck" onclick="getLicenses();"><span class="h6"> Registered</span>
	    			</div><br/>
	    			<div>
	    				<input type="checkbox" ${activeStatus==true?'checked':''} id="activeCheck" onclick="getLicenses();"><span class="h6"> Active</span>
	    				<input type="checkbox" ${expiredStatus==true?'checked':''} id="expiredCheck" onclick="getLicenses();"><span class="h6"> Expired</span>
	    			</div>
    			</form>
    			<script>
    				function  getLicenses(){
    					var evaluation = document.getElementById("evalCheck");
    					var registered = document.getElementById("regCheck");
    					var active = document.getElementById("activeCheck");
    					var expired = document.getElementById("expiredCheck");
    					
    					var evalVal=0;
    					var regVal=0;
    					var actVal=0;
    					var expVal=0;
    					
    					if(evaluation.checked){
    						evalVal=1
    					}
    					if(registered.checked){
    						regVal=1
    					}
    					if(active.checked){
    						actVal=1
    					}
    					if(expired.checked){
    						expVal=1
    					}
    					
    					subAction = "${contextPath}/user/userLicenses?filter=all&type="+evalVal+regVal+actVal+expVal+"&page=1";
    					document.getElementById("licenseFilterForm").action = subAction;
    					document.getElementById("licenseFilterForm").submit();
    					
    				}
    			
    			</script>
    		</div>
      		<div class="text-right col-lg-6 col-sm-12">
        		
						<button type="button" class="btn btn-info m-3" data-toggle="modal" data-target="#licenseGenerateModal" data-whatever="@fat">Generate new Evaluation License</button>
			  			
			  			 <div class="modal fade" id="licenseGenerateModal" tabindex="-1" role="dialog" aria-labelledby="licenseGenerateModalLabel1">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20" action="userLicenses" method="POST" name="licenseForm" modelAttribute="licenseBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title" id="exampleModalLabel1">Product Licensing</h4>
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                        </div>
			                        <div class="modal-body">
			                            
			                                <div class="form-group">
			                                <div>
			                                    <label for="product-name" class="control-label">Select Product:</label>
			                                    <select class="select2 form-control custom-select" required style="width: 100%; height:36px;" id="selectProduct" name="selectProduct" path="selectProduct">
			                                    <option>Select</option>
			                                    <optgroup label="Evaluation Product">
			                                          <c:if test="${not empty productList}">
						                                     <c:forEach var="entry" items="${productList}">
						                                   		  <option value=${entry.ID}>${entry.TX_PRODUCT_NAME}</option>
						                                     </c:forEach>
					                                    </c:if>
			                                    </optgroup>
			                                	</select>
			                                </div>
			                                
			                                <div>
			                                	<label for="machine-id" class="control-label">Machine ID:</label>
			                                	<input class="form-control form-control-lg" type="text" required placeholder="Machine ID" id="machineId" name="machineId" path="machineId" >
			                                </div>
			                                </div>
			                                
			                            
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="submit" class="btn btn-primary" >Generate License</button> 
			                            <!-- <button class="btn btn-block btn-lg btn-info " type="submit ">Generate License</button> -->
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>    
            		</div>
   		</div>
      
      
       <c:if test="${not empty successmessage}">
		 	<script>
		 	 	$(window).on("load", function() {
			    	toastr.success('${successmessage}', 'Success', { "progressBar": true });
			    });
		  	</script>
		</c:if>

 		<c:if test="${not empty failuremessage}">
		 	<script>
		 	 	$(window).on("load", function() {
			    	toastr.error('${failuremessage}', 'Success', { "progressBar": true });
			    });
		  	</script>
		</c:if>
		
		
		<c:if test="${not empty licenseList}">
		 	 <table class="table table-hover">
				  <thead class="thead-default">
				    <tr>
				      <th>#</th>
				      <th>Product</th>
				      <th>License Validity Expires</th>
				      <th>License Type</th>
				      <th>Status</th>
				    </tr>
				  </thead>
				  <tbody>
				  
				  <c:forEach var="entry" items="${licenseList}" varStatus="loop">
				  
				  <tr>
				      <th scope="row">${loop.index+1}</th>
				      <td>${entry.licensedProduct}</td>
				      <td>
					      <fmt:parseDate pattern="MM/dd/yyyy" value="${entry.licenseValidity}" var="parsedDate" />
						  <fmt:formatDate value="${parsedDate}" pattern="dd MMM yyyy" var="readableValidity"/>
					      ${readableValidity}
				      </td>
				      <td>${entry.licenseType}</td>
				      <td>
				      
				      <p>
					    <jsp:useBean id="today" class="java.util.Date" />
					    <%-- <b><c:out value="${today}"/></b> --%>
					</p>
				      
				      <fmt:setLocale value="en_US" />
						<fmt:parseDate var="testdate" value="${entry.licenseValidity}" pattern="MM/dd/yyyy" />
						<c:if test="${today.time gt testdate.time}">
						    <p><b><span class="text-secondary">Expired</span></b></p>
						</c:if>
				      
				    	 <c:if test="${today.time le testdate.time}">
				      	<div>
				      		<span class="lead text-success">Valid</span>
				      	</div>
				      	<div>
				      		<!-- <a href="#licenseDisplayModal">Show License</a> -->
				      		<!-- <button type="button" class="btn btn-info" data-toggle="modal" data-target="#licenseDisplayModal" data-license=${entry.tx_license} id="showLicenseBtn">Show License</button> -->
				      		                  <textarea class="form-control" id="license-text-area">${entry.tx_license}</textarea>
				      		
				      		<div id="licenseDisplayModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title" id="myLargeModalLabel">License</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            </div>
                                            <div class="modal-body">
                                              <textarea class="form-control" id="license-text"></textarea>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
				    	</div>
				    	</c:if>
				    	
				      </td>
				    </tr>
				</c:forEach>
				  
				    
				  </tbody>
				</table>
				
				                    <ul class="pagination float-right">
	                                                                               <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/user/userLicenses?filter=${currentFilter}&page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                                    	<c:if test="${currentPage==loop.index}">
	                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/userLicenses?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    	<c:if test="${currentPage!=loop.index}">
	                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/userLicenses?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    </c:forEach>
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/user/userLicenses?filter=${currentFilter}&page=${currentPage + 1 }" tabindex="-1">Next</a>
	                                        	</li>
	                                        </c:if>

	                                    </ul>
		</c:if>
		
		<c:if test="${empty licenseList}">
			<span>You do not have any licenses yet.</span>
		</c:if>
		
     
                            
    </div>
  </div>
 
  </div>
  
 		    
 <script>
 $(document).on("click", "#showLicenseBtn", function () {
     var thisLicense = $(this).data('license');
     $(".modal-body #license-text").val( thisLicense );
     // As pointed out in comments, 
     // it is superfluous to have to manually call the modal.
     // $('#addBookDialog').modal('show');
});
 
 </script>
 
  

</jsp:body>
</page:user-landing>
