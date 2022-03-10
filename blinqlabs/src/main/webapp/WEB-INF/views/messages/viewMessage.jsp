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
         
          <div class="container mt-4">
          <div class="btn-toolbar justify-content-between" role="toolbar">
         	 <div class="btn-group mr-2" role="group" aria-label="First group">
	         	<a href="${contextPath}/user/UserMessages?page=1"><button type="button" class="btn btn-primary"><i class="fas fa-arrow-left"></i> View all messages</button></a>
	         </div>
			  <div class="btn-group mr-2" role="group" aria-label="Second group">
				    <button type="button" class="btn btn-info" alt="Flag">
				      <i class="far fa-flag"></i>
				    </button>
				    <button type="button" class="btn btn-info" alt="Mark Unread">
				      <i class="far fa-envelope-open"></i>
				    </button>
				    <button type="button" class="btn btn-info" alt="Print">
				      <i class="fas fa-print"></i>
				    </button>
				     <button type="button" class="btn btn-warn" alt="Report">
				      <i class="fas fa-exclamation-circle"></i>
				    </button>
				    <button type="button" class="btn btn-danger" alt="Delete">
				      <i class="fas fa-trash-alt"></i>
				    </button>
				    
			  </div>
			 
		  </div>
	         
         </div>
         <c:if test="${empty messagelist}">
        	 <div class="container">
		      <div class="header">
			      <div class="card  mb-3 text-center">
				      <div class="card-body text-danger align-items-center d-flex no-block p-15 border-info mx-3 px-3" >
				           	<h3>Error loading message.</h3>
				      </div>
			      </div>
		      </div>
    		</div>
         </c:if>
         
         
         <c:if test="${not empty messagelist}">
         	<c:forEach var="entry" items="${messagelist}">
         	
         		<div class="features-tabs-section" style="margin-top: 100px;">
				    <div class="container">
				      <div class="header">
				      <div class="card  mb-3 text-center">
				      <div class="card-body text-primary align-items-center d-flex no-block p-15 border-info" >
				      
				      		
				      			<div class="col-lg-6 col-sm-12 text-left">
				      					<div>
				                   		<c:if test="${not empty entry.TX_FROM_USER_IMAGE}">
					                            	<img src="${entry.TX_FROM_USER_IMAGE}" alt="user" class="rounded-circle" width="40">
					                            </c:if>
					                            <c:if test="${empty entry.TX_FROM_USER_IMAGE}">
					                            	<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="40">
					                            </c:if>
					                            <h4 class="m-b-0"><span>${entry.TX_FROM_NAME}</span></h4>
					                   </div>
					                   <div>
					                         
					                       	 <p class=" m-b-0"></p>
					                   </div>
				      			</div>
				      			
				      			<div class="col-lg-6 col-sm-12 text-right">
				      				<div>
				      					<P>${entry.TX_TIME}</P>
				      				</div>
				      				<div>
				      					<P>Priority: ${entry.ID_PRIORITY}</P>
				      				</div>
				      			</div>
				      			
				      		
				       </div>
				       <div class="row">
				      				<p>Message to: <span class="text-info"> ${sessionScope.firstname} ${sessionScope.lastname} </span></p>
				      			</div>
				      
					</div>
		    		<hr/>
		    	 	</div>
		    	 		<div class="text-primary mt-2 p-2 text-center">
		    	 			<h2>${entry.TX_SUBJECT}</h2>
		    	 		</div>
				     	<div>
				     		${entry.TX_BODY}
				     	</div>
				     	
				    </div>
			  	</div>
        	 </c:forEach>
         </c:if>
         
         
   
  </div>
  
  

</jsp:body>
</page:user-landing>
