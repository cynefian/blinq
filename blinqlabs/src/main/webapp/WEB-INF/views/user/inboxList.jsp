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
		      <div class="card  mb-3 mt-3 text-center">
		      <div class="card-body text-white align-items-center d-flex no-block p-15 bg-primary mx-3 px-3" >
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
		                         <h4 class="m-b-0"><span>${sessionScope.firstname} ${sessionScope.lastname}</span></h4>
		                        <%-- <h4 class="m-b-0"><span><%session.getAttribute("firstname");%> <%session.getAttribute("lastname");%></span></h4> --%>
		                       <p class=" m-b-0 text-white" ><security:authentication property="principal.username" /></p>
		                   </div>
		               </div>
		      
		     </div>
		      </div>
		
		      
		    </div>
	

    <div class="basket">
      
        <div class="email-app">
        
        	<div class="row">
        	 	<div>
                     <h4>Messages </h4>
                 </div>
        	</div>
        
        		<form action="#" method="GET" id="messageSearchForm">
        		 <div class="row">
                                <div class="col-md-4">
                                   <div class="form-group m-2">
										    <label for="filterOptions">Filter</label>
										    <select class="form-control" id="filterOptions">
										      <option value="-">-</option>
										      <option value="F" ${filter=="F"?'selected="selected"':''}>Flagged</option>
										      <option value="R" ${filter=="R"?'selected="selected"':''}>Read</option>
										      <option value="S" ${filter=="S"?'selected="selected"':''}>Starred</option>
										      <option value="U" ${filter=="U"?'selected="selected"':''}>Unread</option>
										  </select>
									  </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group m-2">
										    <label for="messageSearch">Search</label>
										    <input placeholder="Search..." id="messageSearch" type="text" class="form-control" value="${search}">
								    </div>
                                </div>
                                <div class="col-md-4">
                                     <div class="form-group m-2">
                                      	<label for="searchbutton">.</label>
						    		 	<button type="button" id="searchbutton" onclick="triggerSearch();" class="btn btn-primary">Search</button>
						    		 </div>
                                </div>
                                </div>
        			</form>
				    
				    <script>
				    	function triggerSearch(){
				    		var filter;
				    		var searchtxt;
				    		var p = 1;
				    		var actionStr = "${contextPath}/user/UserMessages?page="+p;
				    		
				    		var selected = document.getElementById("filterOptions").value;
				    		if(selected!="-"){
				    			actionStr += "&filter="+selected;
				    		}
				    		var search = document.getElementById("messageSearch").value;
				    		if(search!=null && search!=""){
				    			actionStr += "&search="+search;
				    		}
				    		document.getElementById("messageSearchForm").action = actionStr;
				    		document.getElementById("messageSearchForm").method = "POST";
				    		document.getElementById("messageSearchForm").submit(); 
				    	}
				    </script>
							
        	
			
			 
			
					<c:if test="${not empty messagelist}">
			            <div class="table-responsive">
			            
			            <table class="table email-table no-wrap table-hover v-middle">
                        <tbody>
                         <c:forEach var="entry" items="${messagelist}">
                         
                                <!-- row -->
                                
								<c:if test="${entry.TX_READ}">
                                	<tr class="read">
                                </c:if>
                                
                                <c:if test="${not entry.TX_READ}">
                                	<tr class="unread">
                                </c:if>
                                
                                
                                    <!-- star -->
                                    <td class="starred">
                                    	<c:if test="${entry.TX_STAR==true}">
			                            	<a href="${contextPath}/user/message/updateMessageStar?star=0&source=list&id=${entry.id}"><i class="fas fa-star"></i></a>
			                            </c:if>
			                            <c:if test="${entry.TX_STAR==false}">
			                            	<a href="${contextPath}/user/message/updateMessageStar?star=1&source=list&id=${entry.id}"><i class="far fa-star"></i></a>
			                            </c:if>
                                    	
                                   	</td>
                                    <!-- User -->
                                    <td class="user-image">
	                                    <c:if test="${not empty entry.TX_FROM_USER_IMAGE}">
			                            	<img src="${entry.TX_FROM_USER_IMAGE}"alt="user" class="rounded-circle" width="30">
			                            </c:if>
			                            <c:if test="${empty entry.TX_FROM_USER_IMAGE}">
			                            	<img src="${pageContext.request.contextPath}/resources/css/xtreme/assets/images/users/1.jpg" alt="user" class="rounded-circle" width="30">
			                            </c:if>
                                    	
                                   	</td>
                                    <td class="user-name">
                                        <h6 class="m-b-0">${entry.TX_FROM_NAME}</h6>
                                    </td>
                                    <!-- Message -->
                                    <td  class="max-texts"> 
                                     <c:if test="${not entry.TX_READ}">
                                    	<a class="link" href="${contextPath}/user/message/readMessage?id=${entry.id}"><span class="text-primary text-darken-4">${entry.TX_SUBJECT}</span></a> 
                                     </c:if>
                                     
                                     <c:if test="${entry.TX_READ}">
                                     	<a class="link" href="${contextPath}/user/message/readMessage?id=${entry.id}"><span class="blue-grey-text text-darken-4">${entry.TX_SUBJECT}</span></a>
                                     </c:if>
                                    </td>
                                    <!-- Attachment -->
                                    <td class="clip">
	                                    <c:if test="${not empty entry.TX_ATTACHMENT}">
	                                    	<i class="fa fa-paperclip"></i>
	                                   	</c:if>
                                    </td>
                                    <!-- Time -->
                                    <td class="time"> ${entry.TX_TIME} </td>
                                 </tr>
                         </c:forEach>
                        </tbody>
                        </table>
                        
				                   
                        
                        </div>
                        
                             <div class="p-15 m-t-30">
                        <nav aria-label="Page navigation example">
                          <ul class="pagination float-right">
                                            <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/user/UserMessages?page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                        
	                                            <c:forEach begin="1" end="${totalPages}" varStatus="loop">
			                                    	<c:if test="${currentPage==loop.index}">
			                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/UserMessages?page=${loop.index}">${loop.index}</a></li>
			                                    	</c:if>
			                                    	<c:if test="${currentPage!=loop.index}">
			                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/UserMessages?page=${loop.index}">${loop.index}</a></li>
			                                    	</c:if>
			                                    </c:forEach>
	                                         
	                                        
	                                    
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/user/UserMessages?page=${currentPage + 1 }" tabindex="-1">Next</a>
	                                        	</li>
	                                        </c:if>

	                                    </ul>
                        </nav>
                    </div>
                  </c:if>
                  
                  
		<c:if test="${empty messagelist}">
			<span>You do not have any messages yet.</span>
		</c:if>
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
			    	toastr.error('${failuremessage}', 'Failure', { "progressBar": true });
			    });
		  	</script>
		</c:if>
		
		
	
		
     
                            
    </div>
  </div>
 

</jsp:body>
</page:user-landing>
