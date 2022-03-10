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
            <div class="container">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- basic table -->
                
                <c:if test="${not empty failuremessage}">
					 	<script>
					 	 	$(window).on("load", function() {
						    	toastr.error('${failuremessage}', 'Feature', { "progressBar": true });
						    });
					  	</script>
					</c:if>
					
					<c:if test="${not empty successmessage}">
					 	<script>
					 	 	$(window).on("load", function() {
						    	toastr.success('${successmessage}', 'Feature', { "progressBar": true });
						    });
					  	</script>
					</c:if>
                
                <c:if test="${empty blogList}">
	                <!-- Card -->
	                <div class="card text-center m-t-20 m-b-20">
					    <div class="card-header">
					        <h3>Oh oh...</h3>
					    </div>
					    <div class="card-body">
					        <h3 class="card-text">This post does not exist</h3>
					        <a href="${contextPath}/admin/blogs/newBlog" method="POST" class="btn btn-info">Create new post</a>
					        <a href="${contextPath}/admin/blogs/viewAllBlogs?page=1&type=a" method="GET" class="btn btn-info">View All Posts</a>
					    </div>
					    <div class="card-footer text-muted">
					      
					    </div>
					</div>
					<!-- Card -->
				</c:if>
				
				<c:if test="${not empty blogList}">
				<br/><br/><br/>
				
				<security:authorize
			access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER')"
			var="isUSer" />
			
			<c:if test="${isUSer}">
				<div class="row">
					<div class="button-group">
					
						<!-- Delete Modal -->
								<div id="deleteModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                        <c:forEach var="blog" items="${blogList}">
	                                        <form class="form-horizontal m-t-20" action="${contextPath}/admin/blogs/deleteBlog?id=${blog.ID_BLOG}&status=true" method="POST" name="licenseForm" modelAttribute="licenseBean">
	                                            <div class="modal-header">
	                                                <h4 class="modal-title" id="myModalLabel">Delete Blog</h4>
	                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                                            </div>
	                                            <div class="modal-body">
	                                                <h4>Are you sure you want to delete this blog?</h4>
	                                                <p>This action cannot be undone.</p>
	                                            </div>
	                                            <div class="modal-footer">
	                                              	 <button type="submit" id="confirm-delete" class="btn btn-danger waves-effect">Delete</button>
	                                                 <button type="button" class="btn btn-info waves-effect" data-dismiss="modal">Close</button>
	                                            </div>
	                                            </form>
                                             </c:forEach>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
                                
                                
                                <!-- Edit Modal -->
								<div id="editModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                        <c:forEach var="blog" items="${blogList}">
	                                        <form class="form-horizontal m-t-20" action="${contextPath}/admin/blogs/viewBlog?status=false&id=${blog.ID_BLOG}" method="GET" name="licenseForm" modelAttribute="licenseBean">
	                                            <div class="modal-header">
	                                                <h4 class="modal-title" id="myModalLabel">Edit Blog</h4>
	                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                                            </div>
	                                            <div class="modal-body">
	                                                <h4>Blog will not be visible until the changes are published</h4>
	                                                <p>Continue?.</p>
	                                                <p></p>
	                                                <p>Blog ID: ${blog.ID_BLOG}</p>
	                                                
	                                            </div>
	                                            <div class="modal-footer">
	                                              	 <button type="submit" id="confirm-delete" class="btn btn-dark waves-effect">Edit</button>
	                                                 <button type="button" class="btn btn-info waves-effect" data-dismiss="modal">Close</button>
	                                            </div>
	                                            </form>
                                             </c:forEach>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
                                
					
						<button type="button" class="btn btn-outline-info btn-rounded" id="edit-blog-btn"  data-toggle="modal" data-target="#editModal"><i class="fas fa-pen-square"></i> Edit</button>
					 	<button type="button" class="btn btn-outline-danger btn-rounded" id="delete-blog-btn" data-toggle="modal" data-target="#deleteModal" ><i class="fas fa-trash-alt"></i> Delete</button>
					</div>
				</div>
				
				</c:if>
	                     
			       <c:forEach var="blog" items="${blogList}">
					       <br/><br/>
					       <div class="row">
					       		<div class="col-lg-9 col-md-12"> 
					       		
						       		<div id="blog-details">
										<img src="${blog.TX_IMAGE}" id="blog-img" width="100%"/>
										<div class="p-3">
			                            	 <div id="blog-title" class="blog-title text-center m-t-20 m-b-20" >
			                                    ${blog.TX_TITLE}
			                                </div>
			                                <div class="entry-meta">
			                                    <span class="blog-posted-meta">on <a href="#"> ${blog.TS_CREATE} </a> 
			                                    by <a href="#"> ${blog.TX_AUTHOR} </a></span> 
			                                </div>
			                                
			                            </div>
			                            <div class="p-3">
			                            	<div id="blog-body" class="blog-body">
			                                    ${blog.TX_BODY}
			                                </div>
			                            </div>
									</div>
					       		
					       		</div>
					       		<div class="col-lg-1 col-md-12"> 
					       		</div>
					       		<div class="col-lg-2 col-md-12">
					       			
					       					<div class="headers clearfix">About</div>
					       					<div class="row">
					       						<div class="author">
					       							<c:if test="${not empty blog.AUTHOR_IMAGE}">
					       								<img src="${blog.AUTHOR_IMAGE}" class="pic" alt="${blog.TX_AUTHOR}" />
					       							</c:if>
					       							
					       							<c:if test="${empty blog.AUTHOR_IMAGE}">
					       								<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" class="pic" alt="${blog.TX_AUTHOR}" />
					       							</c:if>
					       							
										              
										              <div class="name"><h5>${blog.TX_AUTHOR}</h5></div>
										              <div class="company"></div>
									            </div>
					       					</div><br/>
					       					<div class="row p-2">
					       						<p>${blog.TX_USER_BIO}</p>
					       					</div>
					       				</div>
					       			
					       			</section>
					       			<hr/>
					       			<section class="bg-theme-light">
					       				<div class="headers clearfix">Category</div>
					       				<div class="active">
					       					<h4>
						       					<a href="${contextPath}/admin/blogs/viewAllBlogs?page=1&type=a&q=BC.IDeq${blog.ID_CATEGORY}">
						       						<button class="btn btn-lg btn-outline-primary">${blog.TX_CATEGORY}</button>
						       					</a>
					       					</h4>
					       				</div>
					       				
					       			</section>
					       			<hr/>
					       			<section class="bg-theme-light">
					       				<div class="headers clearfix">Tags</div>
					       				 <div id="tag-div" ></div>
					       			</section>
					       		</div>
					       
					       </div>
					       
					       
					       <script>
								var str = "${blog.TX_TAGS}"
								var res = str.split(" ");
								res.forEach(processTag);
								
								function processTag(item,index){
								//	document.getElementById("tags").innerHTML += "<div class='btn btn-sm btn-info btn-rounded p-2 m-1'>"+item+"</div>"
									document.getElementById("tag-div").innerHTML += "<span id='tag-"+item+"' class='tag-pill'>"+item+"</span><div class='float-left'></div>"
								}
								
							</script>
							
										
								
					</c:forEach>
							<!-- Comments -->
							<hr/>
							<div>
							
							<div class="row">
								<div class="section-heading2 mb-5">
				                    <span class="alt-font text-primary">Comments</span>
				                </div>
								<!-- <div class="text-center text-primary">Comments</div> -->
							</div>
								<div id="row comments-div">
									
									<c:if test="${empty blogCommentList}">
										<div class="row">
											<span class="blog-posted-meta">There are no comments yet.</span>
										</div>
									</c:if>
									
									<c:if test="${not empty blogCommentList}">
										<c:forEach var="comment" items="${blogCommentList}" varStatus="loop">
										<c:set var="margin" value="${comment.DEPTH*5}"/>
										
										<jsp:useBean id="loop" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />

										<c:if test="<%=loop.getCount()%2==0%>">
											<c:set var="color" value="row-even" />
										</c:if>
										
										<c:if test="<%=loop.getCount()%2!=0%>">
										 <c:set var="color" value="row-odd" />
										</c:if>
										
								     
										<div class="row ml-${margin} ${color} p-3">
											 <div class="col-lg-2 col-md-2">
												 	  <div class="pro-pic" >
													 	  	<c:if test="${not empty comment.USR_IMAGE}">
								       								<img src="${comment.USR_IMAGE}" alt="user" class="rounded-circle" width="60">
								       							</c:if>
								       							
								       							<c:if test="${empty comment.USR_IMAGE}">
								       								<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="60">
								       							</c:if>
												 	  </div>
												 </div>
												 <div class="col-lg-10 col-md-10">
												 		<div class="row author">
											              <div class="name"><h5>${comment.TX_NAME}</h5> <span>[${comment.TX_EMAIL}]</span></div>
										              	</div>
										              	<div class="row p-2">
								       						<div >
								       							<p>Comment ID: ${comment.ID_COMMENT} </p>
							                                    <span class="blog-posted-meta">${comment.TS_CREATE}</span> 
							                                  
							                                 </div>
								       					</div>
								       					
								       					<div class="row mt-4">
								       						<p>${comment.TX_COMMENT_MESSAGE}</p>
								       					</div>
								       					<%-- <c:if test="${comment.DEPTH<3}">
								       					
									       					<div class="row">
									       						<p><button type="button" id="reply-btn-${comment.ID_COMMENT}" class='tag-pill' data-toggle="modal" data-target="#replyCommentModal-${comment.ID_COMMENT}">Reply</button></p>
									       						<div id="reply-div-${comment.ID_COMMENT}" class="ml-5">
									       							
									       						</div>
									       						
									       					</div>
								       					</c:if> --%>
												 </div>
											 </div>
											
											<!-- Reply Comment Modal -->
						
											<div class="modal fade" id="replyCommentModal-${comment.ID_COMMENT}" tabindex="-1" role="dialog">
											  <div class="modal-dialog" role="document">
											    <div class="modal-content m-10">
											    	<div id="comment-content${comment.ID_COMMENT}">
														<security:authorize
															access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER','ROLE_USER')"
															var="isUSer" />
												
															<form class="form-horizontal m-t-20" action="${contextPath}/admin/blogs/submitComment" modelAttribute="BlogCommentBean" method="POST" name="BlogCommentForm" id="blogReplyForm${comment.ID_COMMENT}">
																<div id="replyFormDiv${comment.ID_COMMENT}">
																	<div class="row" id="hiddenReplyValues${comment.ID_COMMENT}">
																		<input type="text" id="ID_BLOG${comment.ID_COMMENT}" name="ID_BLOG" path="ID_BLOG" value="${blogList[0].ID_BLOG}"/>
																		<input type="text" id="DEPTH${comment.ID_COMMENT}" name="DEPTH" path="DEPTH" value="${comment.DEPTH+1}"/>
																		<input type="text" id="ID_PARENT${comment.ID_COMMENT}" name="ID_PARENT" path="ID_PARENT" value="${comment.ID_COMMENT}"/>
																		
																		<script>
																			document.getElementById("ID_BLOG${comment.ID_COMMENT}").style.visibility = "hidden";
															            	document.getElementById("DEPTH${comment.ID_COMMENT}").style.visibility = "hidden";
															            	document.getElementById("ID_PARENT${comment.ID_COMMENT}").style.visibility = "hidden";
															            	
																		</script>
																	</div>
																	<c:if test="${isUSer}">
																		<input type="text" id="FL_SYSTEM_USER${comment.ID_COMMENT}" name="FL_SYSTEM_USER" path="FL_SYSTEM_USER" value="true"/>
																		<input type="text" id="TX_NAME${comment.ID_COMMENT}" name="TX_NAME" path="TX_NAME" value="${sessionScope.firstname} ${sessionScope.lastname}"/>
																		<input type="text" id="TX_EMAIL${comment.ID_COMMENT}" name="TX_EMAIL" path="TX_EMAIL" value="${principal.username}"/>
																		<script>
																			document.getElementById("TX_NAME${comment.ID_COMMENT}").style.visibility = "hidden";
																			document.getElementById("TX_EMAIL${comment.ID_COMMENT}").style.visibility = "hidden";
																			document.getElementById("FL_SYSTEM_USER${comment.ID_COMMENT}").style.visibility = "hidden";
																		</script>
																		<div class="text-primary">${sessionScope.firstname} ${sessionScope.lastname}</div>
																		
																	</c:if>
																	
																	<c:if test="${not isUSer}">
																	<input type="text" id="FL_SYSTEM_USER${comment.ID_COMMENT}" name="FL_SYSTEM_USER" path="FL_SYSTEM_USER" value="false"/>
																		<Script>
																			document.getElementById("FL_SYSTEM_USER${comment.ID_COMMENT}").style.visibility = "hidden";
																		</Script>
																		<div class="row">
																			<div class="col-md-6 col-sm-12">
																			 	<div class="form-group  has-success">
										                                            <label for="TX_NAME${comment.ID_COMMENT}" class="control-label col-form-label">Name</label>
										                                            <input type="text" id="TX_NAME${comment.ID_COMMENT}" name="TX_NAME" path="TX_NAME"/>
																				</div>
																			</div>
																			<div class="col-md-6 col-sm-12">
																				<label for="TX_EMAIL${comment.ID_COMMENT}"class="control-label col-form-label">Email</label>
										                                            <input type="text" id="TX_EMAIL${comment.ID_COMMENT}" name="TX_EMAIL" path="TX_EMAIL"/>
																			</div>
																		</div>
																	</c:if>
																	<div class="row">
																		<div class="col-lg-12 col-sm-12">
																			<textarea class="form-control" rows="3" id="TX_COMMENT_MESSAGE${comment.ID_COMMENT}" name="TX_COMMENT_MESSAGE" path="TX_COMMENT_MESSAGE" placeholder="Comment here..."></textarea>
																		</div>
																	</div>
																	
																	<div class="row text-right">
																		 <button type="submit" id="save-reply${comment.ID_COMMENT}" class="btn btn-primary">Save</button>
																	</div>
																</div>
															</form>
													</div>
											    </div>
											  </div>
											</div>
													
										
										</c:forEach>
									</c:if>
									
									</div>
							
							</div>
									
									<hr/>
									
									<!-- New Base Comment -->
									
									<div id="newComment">
										<div id="comment-content">
											<security:authorize
												access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER','ROLE_USER')"
												var="isUSer" />
									
												<form class="form-horizontal m-t-20" action="${contextPath}/admin/blogs/submitComment" modelAttribute="BlogCommentBean" method="POST" name="BlogCommentForm" id="blogCommentForm">
													<div id="commentFormDiv">
														<div class="row" id="hiddenCommentValues">
															<input type="text" id="ID_BLOG" name="ID_BLOG" path="ID_BLOG" value="${blogList[0].ID_BLOG}"/>
															<input type="text" id="DEPTH" name="DEPTH" path="DEPTH" value="0"/>
															<input type="text" id="ID_PARENT" name="ID_PARENT" path="ID_PARENT" value="0"/>
															
															
														</div>
														<c:if test="${isUSer}">
															<input type="text" id="FL_SYSTEM_USER" name="FL_SYSTEM_USER" path="FL_SYSTEM_USER" value="true"/>
															<input type="text" id="TX_NAME" name="TX_NAME" path="TX_NAME" value="${sessionScope.firstname} ${sessionScope.lastname}"/>
															<input type="text" id="TX_EMAIL" name="TX_EMAIL" path="TX_EMAIL" value="${principal.username}"/>
															<script>
																document.getElementById("TX_NAME").style.visibility = "hidden";
																document.getElementById("TX_EMAIL").style.visibility = "hidden";
															</script>
															<div class="text-primary">${sessionScope.firstname} ${sessionScope.lastname}</div>
															
														</c:if>
														
														<c:if test="${not isUSer}">
														<input type="text" id="FL_SYSTEM_USER" name="FL_SYSTEM_USER" path="FL_SYSTEM_USER" value="false"/>
															<div class="row">
																<div class="col-md-6 col-sm-12">
																 	<div class="form-group  has-success">
							                                            <label for="TX_NAME"class="control-label col-form-label">Name</label>
							                                            <input type="text" id="TX_NAME" name="TX_NAME" path="TX_NAME"/>
																	</div>
																</div>
																<div class="col-md-6 col-sm-12">
																	<label for="TX_EMAIL"class="control-label col-form-label">Email</label>
							                                            <input type="text" id="TX_EMAIL" name="TX_EMAIL" path="TX_EMAIL"/>
																</div>
															</div>
														</c:if>
														<div class="row">
															<div class="col-lg-12 col-sm-12">
																<textarea class="form-control" rows="3" id="TX_COMMENT_MESSAGE" name="TX_COMMENT_MESSAGE" path="TX_COMMENT_MESSAGE" placeholder="Comment here..."></textarea>
															</div>
														</div>
														
														<div class="row">
															 <button type="submit" id="save-comment" class="btn btn-primary text-right">Save</button>
														</div>
													</div>
												</form>
										</div>
									</div>
									
									
									
								
					
					   
				</c:if>
                
                   <!-- CKEditor 5 -->
	           <script src="${pageContext.request.contextPath}/resources/ckeditor5-16.0.0/ckeditor.js"></script>
	            
	            <script>
	                ClassicEditor.create(document.getElementById('TX_COMMENT_MESSAGE'));
	            </script>

				<script>
					document.getElementById("ID_BLOG").style.visibility = "hidden";
	            	document.getElementById("DEPTH").style.visibility = "hidden";
	            	document.getElementById("ID_PARENT").style.visibility = "hidden";
	            	document.getElementById("FL_SYSTEM_USER").style.visibility = "hidden";
            	</script>
				
            </div>
    </jsp:body>
</page:user-landing>