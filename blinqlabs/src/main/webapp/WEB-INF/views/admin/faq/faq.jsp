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
	
	
	<c:if test="${not empty failuremessage}">
					 	<script>
					 	 	$(window).on("load", function() {
						    	toastr.error('${failuremessage}', 'FAQ', { "progressBar": true });
						    });
					  	</script>
					</c:if>
					
					<c:if test="${not empty successmessage}">
					 	<script>
					 	 	$(window).on("load", function() {
						    	toastr.success('${successmessage}', 'FAQ', { "progressBar": true });
						    });
					  	</script>
					</c:if>
					
	<section>				
		<div id="page-wrapper">
			<div class="features-tabs-section" style="margin-top: 100px;">
    			<div class="container">
    			
    					<div>
							<button type="button" class="btn btn-lg btn-outline-primary" data-toggle="modal" data-target="#addFAQModal">
								  <i class="fas fa-plus"></i> Add FAQ
							</button>
						</div>
					
						<!-- Modal -->
						
						<div class="modal fade" id="addFAQModal" tabindex="-1" role="dialog">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/faqs" method="POST" name="FaqForm" modelAttribute="faqBean">
						      <div class="modal-header">
						        	<h4 class="modal-title" id="exampleModalLabel1">Add FAQ</h4>
                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      </div>
						      <div class="modal-body">
						     	  <div class="form-group">
		                               
		                                <div>
		                                	<label for="machine-id" class="control-label">Question:</label>
		                                	<input class="form-control form-control-lg" type="text" required placeholder="Question" id="TX_QUESTION" name="TX_QUESTION" path="TX_QUESTION" >
		                                </div>
		                                
		                                <div>
		                                	<label for="machine-id" class="control-label">Answer:</label>
		                                	<input class="form-control form-control-lg" type="text" required placeholder="Answer" id="TX_ANSWER" name="TX_ANSWER" path="TX_ANSWER" >
		                                </div>
                                	</div>
						      </div>
						      <div class="modal-footer">
						      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            	 	<button type="submit" class="btn btn-primary" >Add</button> 
						      </div>
						      </form>
						    </div>
						  </div>
						</div>
				</div>
			</div>
		</div>
	</section>
	
	<section>
	<div class="container">
		<c:if test="${empty faqlist}">
						<div>
							<h4>You do not have any FAQs yet. </h4>
						</div>
					</c:if>
					
					<c:if test="${not empty faqlist}">
						<c:forEach var="entry" items="${faqlist}" varStatus="loop">
							<div class="card">
                                    <div class="card-header" id="heading${loop.index}">
                                        <div class="mb-0">
                                            <h5 class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse${loop.index}" aria-expanded="false" aria-controls="collapse${loop.index}">
                                            	<span class="badge badge-outline badge-pill badge-primary">${loop.index + 1}</span>
	                                        	<span class="item-title">${entry.TX_QUESTION}</span>
                                    		</h5>
                                        </div>
                                        <div>
                                        	<span class="float-right">
                                        		<button class="btn btn-outline-dark" data-toggle="modal" data-target="#editFAQModal${entry.ID}"><i class="fas fa-pencil-alt"></i></button>
                                        		<a href="${contextPath}/admin/deleteFaq?id=${entry.ID}" ><button class="btn btn-outline-danger"><i class="fas fa-trash-alt"></i></button></a>
                                        	</span>
                                        </div>
                                    </div>
                                    <div id="collapse${loop.index}" class="collapse" aria-labelledby="heading${loop.index}" data-parent="#accordion">
                                        <div class="card-body">
                                            ${entry.TX_ANSWER}
                                        </div>
                                    </div>
                                </div>
						
					
					<!-- Edit Modal -->
						
						<div class="modal fade" id="editFAQModal${entry.ID}" tabindex="-1" role="dialog">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/editFaq?id=${entry.ID}" method="POST" name="FaqForm" modelAttribute="faqBean">
						      <div class="modal-header">
						        	<h4 class="modal-title" id="exampleModalLabel1">EDIT FAQ</h4>
                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      </div>
						      <div class="modal-body">
						     	  <div class="form-group">
		                               
		                                <div>
		                                	<label for="machine-id" class="control-label">Question:</label>
		                                	<input class="form-control form-control-lg" type="text" required id="TX_QUESTION" name="TX_QUESTION" path="TX_QUESTION" value="${entry.TX_QUESTION}">
		                                </div>
		                                
		                                <div>
		                                	<label for="machine-id" class="control-label">Answer:</label>
		                                	<input class="form-control form-control-lg" type="text" required id="TX_ANSWER" name="TX_ANSWER" path="TX_ANSWER" value="${entry.TX_ANSWER}">
		                                </div>
                                	</div>
						      </div>
						      <div class="modal-footer">
						      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            	 	<button type="submit" class="btn btn-primary" >SAVE</button> 
						      </div>
						      </form>
						    </div>
						  </div>
						</div>
						
						</c:forEach>
					</c:if>
		</div>
	</section>
	
	</jsp:body>
	
</page:user-landing>