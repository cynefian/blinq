<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:user-landing>
	<jsp:body>
		<c:set var="mainTabselect" value="${mainTab}" />
		<c:set var="subTabselect" value="${subTab}" />
		
		<div class="m-10">
			<div class="features-tabs-section" style="margin-top: 100px;">
		      <div class="header">
		        <h3>System Settings</h3>
		      </div>
		
		      <div class="tabs-wrapper" id="main-tab-index">
		       		<jsp:include page="/WEB-INF/views/admin/settings/navs/top-nav.jsp" />
		       </div>
		       
		       
				<div>
		        <div class="tab-content" id="main-tab-content">
		          <div class="tab-pane fade show active" id="setting-content">
		          		<div class="basket">
	
							<div class="row">
								<div class="col-lg-2" id="settings-tab-index">
									<jsp:include page="/WEB-INF/views/admin/settings/navs/applicationSideNav.jsp" />
								</div>
								<div class="col-lg-10 border-left">
								
									<div>
							            	<section>
							            		<div id="creadcrumbs" class="p-2 m-2">
							            			Settings >> ${mainTabselect} > ${subTabselect}
							            		</div>
												<div class="text-center">
													<h3>${mainTab} </h3>
													<p>${subTab}</p>
												</div>
												
												<div class="text-right">
													<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addCategoryModal"><i class="fas fa-plus-circle"></i> New Category</button>
												</div><hr/>
												
												<!-- add Category Modal -->
												
												<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog">
												  <div class="modal-dialog" role="document">
												    <div class="modal-content">
												    <form class="form-horizontal m-t-20" action="${contextPath}/admin/settings/devops/categories/addCategory" method="POST" name="DevOpsCategoriesForm" modelAttribute="devOpsCategoriesBean">
													      <div class="modal-header">
													        	<h4 class="modal-title" id="exampleModalLabel1">Add Category</h4>
							                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
													      </div>
													      <div class="modal-body">
													     	  <div >
									                               
									                                <div class="form-group">
									                                	<label for="TX_CATEGORY" class="control-label">Category Name </label>
									                                	<input type="text" class="form-control" id="TX_CATEGORY" name="TX_CATEGORY" >
							    	                                </div>
									                                
									                                <div class="form-group">
									                                	<label for="machine-id" class="control-label">Category Code</label>
									                                	<input type="text" class="form-control" id="TX_CAT_CODE" name="TX_CAT_CODE" >
									                                </div>
									                                <div class="form-group">
									                                	<label for="machine-id" class="control-label">Are you sure you want to delete this article?</label>
									                                	<textarea class="form-control" id="TX_DESCRIPTION" name="TX_DESCRIPTION" rows="3"></textarea>
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
						
												<div>
													<c:if test="${not empty catList}">
														<c:forEach var="cat" items="${catList}">
															<div class="row">
																<div class="col-lg-6 col-sm-12">
																	<p class="text-primary h4">${cat.TX_CATEGORY}</p>		
																	<p class="text-info">[ ${cat.TX_CAT_CODE} ]</p><p>${cat.TX_DESCRIPTION}</p>
																</div>
																<div class="col-lg-6 col-sm-12">
																	<div class="text-right">
																		<form action="#" method="POST" id="categoryUpdateForm_${cat.ID}">
																			<label class="custom-toggle">
									                                			<c:if test="${true!=cat.FL_STATUS}">
									                                				<input type="checkbox" onclick="saveCategory(this);" id="${cat.ID}" name="${cat.TX_CATEGORY}_${cat.ID}" path="${cat.TX_CATEGORY}_${cat.ID}" value="0" >
									                                			</c:if>
									                                			<c:if test="${true==cat.FL_STATUS}">
									                        						<input type="checkbox" onclick="saveCategory(this);" id="${cat.ID}" name="${cat.TX_CATEGORY}_${cat.ID}" path="${cat.TX_CATEGORY}_${cat.ID}" value="1" checked >
									                                			</c:if>
								                                			 	<span class="custom-toggle-slider rounded-circle"></span>
																  			</label>
															  			</form>
															  			<script>
															  				function saveCategory(el){
															  					var form = "categoryUpdateForm_"+el.id;
															  					document.getElementById(form).action = "${contextPath}/admin/settings/devops/categories/updateCategory?id="+el.id+"&value="+el.value;
															  					document.getElementById(form).submit();
															  				}
															  			</script>
														  			</div>
																</div>
															</div>
															<hr/>
														</c:forEach>
													</c:if>
													<c:if test="${empty catList}">
														<div class="text-center">
															<p class="text-danger h2"><i class="fas fa-exclamation-circle"></i><span class="h4">There are no categories defined.</span></p>
														</div>
													</c:if>
												</div>
											</section>
							          </div>
							
								
								</div>
							
							</div>
						
						</div>
		          </div>
		          <script>
		         	//$(window).on("load", function() {
					$(document).ready(function() {
						
						 $('#main-tab-index a[id="'+"${mainTabselect}"+'Link"]').addClass("active");
						 $('#settings-tab-index ul li a[id="setting'+"${subTabselect}"+'Link"]').addClass("active");
						}); 
				</script>
		          
		        </div>
		      </div>
		    </div>
		  
		
		</div>
		
	</jsp:body>
</page:user-landing>