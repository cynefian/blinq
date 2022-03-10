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
		 	<security:authorize access="hasAuthority('PERM_FEATURE_TECH_SUPPORT')">
		 		 <div class="container mt-3">
		 		 	
		 		 	
		 		 	<div class="row m-2 p-2">
	    				<div class="col-md-6 text-left">
							
						</div>
						<div class="col-md-6 text-right">
							<form id="senFilterForm" name="senFilterForm" action="" method="POST">
	                                    <div class="form-group m-b-30">
	                                        <select class="custom-select mr-sm-2" id="inlineSenFilter" onchange="updateSenFilter(this);">
	                                            <option value="ALL" ${((currentFilter != 'ACTIVE') && (currentFilter != 'INACTIVE')) ? 'selected="selected"' : ''}>All</option>
	                                            <option value="ACTIVE" ${currentFilter == 'ACTIVE' ? 'selected="selected"' : ''}>Active</option>
	                                            <option value="INACTIVE" ${currentFilter == 'INACTIVE' ? 'selected="selected"' : ''}>Inactive</option>
	                                        </select>
	                                    </div>
	                                </form>
						</div>
					</div>
		 		 	<div class="m-2">
			            	<c:if test="${empty SENFormList}">
			            		<h4>There are no Entitlements</h4>
			            	</c:if>
			            	
			            	<c:if test="${not empty SENFormList}">
			            		<c:forEach var="entry" items="${SENFormList}">
			            			<div class="card card-hover border border-primary">
									    <div class="card-header bg-light">
									    <div class="row">
									    	<div class="col-md-6">
									    		<h4 class="m-b-0 text-primary">${entry.TX_ENTITLEMENT_TYPE}</h4>
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
                                       			 </div>
                                        	</div>
									    </div>
									        
									    </div>
									    <div class="card-body">
									    	<div class="row align-center">
									    		<div class="col-md-6 p-2">
									    			<span class="text-muted">${entry.TX_ENTITLEMENT_DESCRIPTION}</span>
									    		</div>
									    		<div class="col-md-6 p-2">
									    			 <p class="card-text"><i class="fas fa-calendar-alt"></i>  Start Date: ${entry.TS_START}</p>
										        	 <p class="card-text"><i class="fas fa-calendar-alt"></i>  End Date: ${entry.TS_END}
										        	 	<c:if test="${entry.TX_DURATION != 'DEFINED'}">
												        	<div class="badge badge-outline badge-pill badge-secondary">${entry.TX_DURATION}</div>
												        </c:if>
												        </p>	 
									    		</div>
									    	</div>
									        
									        <div class="text-right">
									         <a href="${contextPath}/user/SENdetails?id=${entry.ID}" class="btn btn-inverse">View Details</a>
									         </div>
									    </div>
									    <div class="bg-secondary text-white">
									    	<div class="m-2 p-2">
									    		Service Entitlement Number: <strong>${entry.TX_ENTITLEMENT}</strong>
									    	</div>
									    	
									    	<div class="text-right">
									    	
									    	</div>
									    </div>
									</div>
			            		
			            		
			            		</c:forEach>
			            		
			            		 <ul class="pagination float-right">
	                                                                               <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/user/serviceEntitlements?filter=${currentFilter}&page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                                    	<c:if test="${currentPage==loop.index}">
	                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/serviceEntitlements?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    	<c:if test="${currentPage!=loop.index}">
	                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/user/serviceEntitlements?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    </c:forEach>
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/user/serviceEntitlements?filter=${currentFilter}&page=${currentPage + 1 }" tabindex="-1">Next</a>
	                                        	</li>
	                                        </c:if>

	                                    </ul>
	                                    
			            	</c:if>
			            </div>
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 	
		 		 </div>
		 	</security:authorize>
		
		 	<security:authorize access="hasAuthority('PERM_FEATURE_CLOUD')">
				    <div class="container mt-3">
				    	<div class="row">
				    		<div class="col-lg-12 col-sm-12">
				    			<div class="text-right m-3">
				    				<button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#newServiceModal">Request New Service</button>
				    			</div>		
				    			<!-- Edit credentials Modal -->
									<div class="modal fade" id="newServiceModal" tabindex="-1" role="dialog">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/cloud/newService" method="POST" name="CloudAuthForm" modelAttribute="cloudAuthBean">
									      <div class="modal-header">
									        	<h4 class="modal-title" id="exampleModalLabel1">Update AWS Connectivity</h4>
			                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									      </div>
									      <div class="modal-body">
									     	  <div>
					                               
					                                <div class="form-group">
					                                	<label for="TX_ACCESS_KEY" class="control-label">Provider:</label>
					                                	<input type="text"class="form-control" name="TX_IDENTIFIER" ID = "TX_IDENTIFIER" value="${auth.TX_IDENTIFIER}" >
					                                </div>
					                                
					                                <div class="form-group">
					                                	<label for="TX_ACCESS_KEY" class="control-label">Service:</label>
					                                	<input type="text" class="form-control" name="TX_ACCESS_KEY" ID = "TX_ACCESS_KEY">
					                                </div>
					                           </div>
									      </div>
									      <div class="modal-footer">
									      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                            	 	<button type="submit" class="btn btn-danger" >Save</button> 
									      </div>
									      </form>
								    </div>
								  </div>
								</div>
							</div>
				    	</div>
				      <div class="row">
				                            <!-- column -->
				                            <div class="col-md-4 col-sm-4">
				                                <div class="card  card-hover">
				                                    <div class="card-header bg-info">
				                                        <h4 class="m-b-0 text-white">Platform</h4></div>
				                                    <div class="card-body">
				                                        <h3 class="card-title">Special title treatment</h3>
				                                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
				                                        <a href="javascript:void(0)" class="btn btn-inverse">Go somewhere</a>
				                                    </div>
				                                </div>
				                            </div>
				                            <!-- column -->
				                            <!-- column -->
				                            
				                            <div class="col-md-4 col-sm-4">
				                                <div class="card  card-hover">
				                                    <div class="card-header bg-info">
				                                        <h4 class="m-b-0 text-white">Software</h4></div>
				                                    <div class="card-body">
				                                        <h3 class="card-title">Special title treatment</h3>
				                                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
				                                        <a href="javascript:void(0)" class="btn btn-inverse">Go somewhere</a>
				                                    </div>
				                                </div>
				                            </div>
				                            
				                            <div class="col-md-4 col-sm-4">
				                                <div class="card  card-hover">
				                                    <div class="card-header bg-info">
				                                        <h4 class="m-b-0 text-white">Services</h4></div>
				                                    <div class="card-body">
				                                        <h3 class="card-title">Special title treatment</h3>
				                                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
				                                        <a href="javascript:void(0)" class="btn btn-inverse">Go somewhere</a>
				                                    </div>
				                                </div>
				                            </div>
				                            
		                            </div>
                            <div class="row">
                      			<div class="col-md-12 col-sm-12">
	                                <div class="card  card-hover">
	                                    <div class="card-header bg-info">
	                                        <h4 class="m-b-0 text-white">VPC, Infrastructure and Links</h4></div>
	                                    <div class="card-body">
	                                        <h3 class="card-title">Special title treatment</h3>
	                                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
	                                        <a href="javascript:void(0)" class="btn btn-inverse">Go somewhere</a>
	                                    </div>
	                                </div>
	                            </div>
                      		</div>
                 </div>
		  </security:authorize>
		
		</div>

<script>
function updateSenFilter(el){
	
	var filter = "";
	
	if(el.value=="ACTIVE"){
		filter="ACTIVE";
	}else if(el.value=="INACTIVE"){
		filter="INACTIVE";
	}else{
		filter="ALL";
	}
	
	document.getElementById("senFilterForm").action = "${contextPath}/user/userServices?filter="+filter+"&page=1";
	document.getElementById("senFilterForm").method="POST";
	document.getElementById("senFilterForm").submit();
}

</script>  

	</jsp:body>
</page:user-landing>
