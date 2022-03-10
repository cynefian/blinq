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
                            	
                                
                                <div class="row m-t-40">
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                        <a class="card card-hover" href="${contextPath}/admin/adminTickets?filter=all&page=1" method="GET">
                                            <div class="box bg-dark text-center">
                                                <h1 class="font-light text-white">${totalTickets}</h1>
                                                <h6 class="text-white">Total Tickets</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                        <a class="card card-hover" href="${contextPath}/admin/adminTickets?filter=escalated&page=1" method="GET">
                                            <div class="box bg-danger text-center">
                                                <h1 class="font-light text-white">${escalatedTickets}</h1>
                                                <h6 class="text-white">Escalated</h6>
                                            </div>
                                        </a>
                                    </div>
                                    
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                        <a class="card card-hover" href="${contextPath}/admin/adminTickets?filter=pending&page=1" method="GET">
                                            <div class="box bg-primary text-center">
                                                <h1 class="font-light text-white">${pendingTickets}</h1>
                                                <h6 class="text-white">Pending</h6>
                                            </div>
                                        </a>
                                    </div>
                                    <!-- Column -->
                                    <div class="col-md-6 col-lg-3 col-xlg-3">
                                     	<a class="card card-hover" href="${contextPath}/admin/adminTickets?filter=responded&page=1" method="GET">
                                            <div class="box bg-info text-center">
                                                <h1 class="font-light text-white">${respondedTickets}</h1>
                                                <h6 class="text-white">Responded</h6>
                                            </div>
                                        </a>
                                    </div>
                                    
                                </div>
                                
                                	<div class="row justify-content-center">
				                        <div class="col-12 col-md-10 col-lg-8">
				                            <form class="card card-sm" id="searchForm" method="GET" action="">
				                                <div class="card-body row no-gutters align-items-center">
				                                    <div class="col-auto">
				                                        <i class="fas fa-search m-2"></i>
				                                    </div>
				                                    <!--end of col-->
				                                    <div class="col">
				                                        <input class="form-control form-control-lg form-control-borderless m-2" type="text" id="id" name="id" placeholder="Ticket Number...">
				                                    </div>
				                                    <!--end of col-->
				                                    <div class="col-auto">
				                                        <button class="btn btn-lg btn-success" id="searchforticketbutton m-2" type="button" onclick="performSearch();">Search</button>
				                                    </div>
				                                    <!--end of col-->
				                                </div>
				                            </form>
				                            <script>
				                            function performSearch(){
				                            	
				                            	document.getElementById("searchForm").action="${contextPath}/ticketDetails?id="+document.getElementById("id").value;
				                            	document.getElementById("searchForm").submit();
				                            }
				                            </script>
				                        </div>
				                        <!--end of col-->
				                    </div>
                                
                           
                                <c:if test="${empty ticketlist}">
									<span>You do not have any Tickets yet.</span>
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
													</div>
												</div>
											 	
											</div>
										 	<div class="card-body">
												<div class="">
													<span>Reporter: ${entry.TX_ACCOUNT_NAME}  </span><span class=" badge-outline badge-pill badge-secondary p-1">${entry.TX_ACCOUNT_EMAIL}</span>
												</div>
											</div> 
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
	                                    <!-- 
	                                        <li class="page-item disabled">
	                                            <a class="page-link" href="#" tabindex="-1">Previous</a>
	                                        </li> -->
	                                        
	                                        <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/admin/adminTickets?filter=${currentFilter}&page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                                    	<c:if test="${currentPage==loop.index}">
	                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/adminTickets?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    	<c:if test="${currentPage!=loop.index}">
	                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/adminTickets?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    </c:forEach>
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/admin/adminTickets?filter=${currentFilter}&page=${currentPage + 1 }" tabindex="-1">Next</a>
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
           
    </jsp:body>
</page:user-landing>
