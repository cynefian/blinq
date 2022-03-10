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
									<jsp:include page="/WEB-INF/views/admin/settings/navs/systemSideNav.jsp" />
								</div>
								<div class="col-lg-10 border-left">
								
									<div>
							            	<section>
							            		<div id="creadcrumbs" class="p-2 m-2">
							            			Settings >> ${mainTabselect} > ${subTabselect}
							            		</div>
											</section>
											
											<section>
												<div >
													<h4>Add new Category</h4>
												</div>	
														<form class="form-horizontal m-t-20" action="${contextPath}/admin/blogs/createBlogCategory" method="POST" name="SettingsForm" modelAttribute="SettingsBean">
														      <div class="row">
														      		<div class="col-lg-8 col-md-12">
														      			<div class="form-group">
													                      <input type="text" class="form-control" placeholder="Category Name Here"  id="tx_category_name"  name="tx_category_name" path="tx_category_name">
													                    </div>
											                    	</div>
											                      
											                      <div class="col-lg-4 col-md-12">
												                       <div class="form-group m-b-0 text-left">
												                            <button type="submit" class="btn btn-info waves-effect waves-light">Add</button>
												                        </div>
											                      </div>
											                  </div>
											            </form>
											</section>
											<hr/>
											<section class="wrapper clearfix p-2 mt-2">
											<div class="row">
												<div class="col-lg-12">
													<h4>Existing Categories</h4>
													<div>
														<c:if test="${not empty blogList}">
															<table class="table table-hover">
															  <thead class="thead-default">
															    <tr>
															      <th>Category</th>
															      <th>Blog Count</th>
															      <th>Active Status</th>
															      <th class="text-right">Action</th>
															    </tr>
															  </thead>
															  <tbody>
															  
															  <c:forEach var="entry" items="${blogList}" varStatus="loop">
															  	<form class="form-horizontal m-t-20" id="categoryForm${entry.ID}" action="#" method="POST">
															  		<tr>
															  		     <td>${entry.TX_CATEGORY}</td>
															  		     <td>
																  		 	     <c:if test="${entry.COUNTER != 0}">
																	  		 	   	 <a href="${contextPath}/admin/blogs/viewAllBlogs?page=1&type=a&q=BC.IDeq${entry.ID}">${entry.COUNTER}</a> 
																	  		     </c:if>
																	  		     
																	  		     <c:if test="${entry.COUNTER == 0}">
																	  		     	${entry.COUNTER}
																	  		     </c:if>
																  		     
															  		     </td>
															  			<td>
															      		<label class="custom-toggle">
								                                			<c:if test="${true!=entry.FL_ACTIVE}">
								                                				<input type="checkbox" onclick="saveCategory(this);" id="${entry.ID}" name="${entry.TX_CATEGORY}_${entry.ID}" path="${entry.TX_CATEGORY}_${entry.ID}" value="0" >
								                                			</c:if>
								                                			<c:if test="${true==entry.FL_ACTIVE}">
								                        						<input type="checkbox" onclick="saveCategory(this);" id="${entry.ID}" name="${entry.TX_CATEGORY}_${entry.ID}" path="${entry.TX_CATEGORY}_${entry.ID}" value="1" checked >
								                                			</c:if>
							                                			 	<span class="custom-toggle-slider rounded-circle"></span>
															  			</label>
																      </td>
																      <td>
																      	<button type="button" class="btn btn-lg btn-outline-primary float-right" data-toggle="modal" data-target="#deleteFeatureModal${entry.ID}">
																		  <i class="fa fa-trash" aria-hidden="true"></i>
																		</button>
																      </td>
															  		</tr>
															  	</form>
															  	
															  		
												  			<script>
												 				function saveCategory(el) {
												 					document.getElementById("categoryForm${entry.ID}").action="${contextPath}/admin/settings/blogs/updateBlogCategoryFlag?id="+el.id+"&value="+el.value;
																 	document.getElementById("categoryForm${entry.ID}").submit();// Form submission
												 				}
															</script>
															     
																     
																     <!-- Modal -->
																	<div class="modal fade" id="deleteFeatureModal${entry.ID}" tabindex="-1" role="dialog">
																	  <div class="modal-dialog" role="document">
																	    <div class="modal-content">
																	      <div class="modal-header">
																	        <h5 class="modal-title" id="deleteFeatureModalLabel">Delete Category</h5>
																	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
																	          <span aria-hidden="true">×</span>
																	        </button>
																	      </div>
																	      <div class="modal-body">
																	     	 <div class="alert alert-danger" role="alert">
																			  <strong>
																			  <i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
																			  Warning! Deleting Category<br/>
																			  Name: ${entry.TX_CATEGORY} <br/>
																			  ID: ${entry.ID}
																			  </strong>
																			</div>
																	        <h4>
																	        	<c:if test="${entry.COUNTER != 0}">
																	        	<hr/>
																	  		 	   	 <div>
																	  		 	   	 	<p> Choose the category to remap existing blogs*</p>
																	  		 	   	 	 <select class="form-control has-danger" id="TX_CATEGORY"  name="TX_CATEGORY" path="TX_CATEGORY" onchange="updateRemap(this);">
																	  		 	   	 	 <option value="" selected="selected"> - </option>
																					       <c:forEach var="blogCatentry" items="${blogList}">
																					       		<c:if test="${blogCatentry.ID != entry.ID}" >
																					       			 <option value="${blogCatentry.ID}"}>${blogCatentry.TX_CATEGORY}</option>
																					       		</c:if>
																						     </c:forEach>
																					    </select>
																	  		 	   	 </div> 
																	  		     </c:if>
																	        	<br/><br/>
																	        	<hr/>
																	        	<p>Are you sure you want to delete the Category?</p>
																	        	<p>This Action cannot be undone.</p>
																	        	
																	        </h4>
																	      </div>
																	      <div class="modal-footer">
																	      	<form class="form-horizontal m-t-20" id="confirmDeleteForm" action="#" method="POST">
																      				<button type="button" class="btn btn-primary" id="${entry.ID}" onclick="confirmDelete(this, ${entry.COUNTER});">Confirm Delete</button>
																      				<input type="text" id="TX_REMAP"/>
																      		</form>	
																	      	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
																	      	
																	      	<script>
																	      		
																		      	function confirmDelete(el,count) {
																		      		
																		      		if((document.getElementById("TX_CATEGORY").value==null || document.getElementById("TX_CATEGORY").value=="")
																		      				&& count>0){
																		      			alert("You must select a category to remap existing blog articles");
																		      		}else{
																		      			document.getElementById("confirmDeleteForm").action="${contextPath}/admin/settings/blogs/deleteBlogCategory?id="+el.id+"&remap="+document.getElementById("TX_REMAP").value;
																					 	document.getElementById("confirmDeleteForm").submit();// Form submission	
																		      		}
																 					
																 				}
																		      	
																		      	function updateRemap(el){
																		      		document.getElementById("TX_REMAP").value=el.value;
																		      	}
																	      	</script>
																	      </div>
																	    </div>
																	  </div>
																	</div>
															  
															  </c:forEach>
															  
															    </tbody>
															  </table>
														</c:if>
														
													
														
														<c:if test="${empty blogList}">
															<div class="text-center">
																<p>You do not have any Blog Categories Yet.</p>
															</div>
															
														</c:if>
													
													</div>
												</div>
												
											</div>
											</section>
							          </div>
							
								
								</div>
							
							</div>
						
						</div>
		          </div>
		          <script>
		          
		      		document.getElementById("TX_REMAP").style.visibility = "hidden";
		      	
		      	
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