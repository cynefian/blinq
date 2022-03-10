<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
	
	  <!-- start main banner area -->
        <div class="animated-banner-area header-secondary bg-theme-90">
	  <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
		
		
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
					        <a href="${contextPath}/blog?page=1&type=p" method="GET" class="btn btn-info">View All Posts</a>
					    </div>
					    <div class="card-footer text-muted">
					      
					    </div>
					</div>
					<!-- Card -->
				</c:if>
				
				<c:if test="${not empty blogList}">
			
		                 
					       <c:forEach var="blog" items="${blogList}">
					       <div class="row">
					       		<div class="col-lg-9 col-md-12"> 
					       		
						       		<div id="blog-details p-2">
										<img src="${blog.TX_IMAGE}" id="blog-img" width="100%"/>
										<div class="p-3">
			                            	 <div id="blog-title" class="blog-title text-center m-t-20 m-b-20" >
			                                    ${blog.TX_TITLE}
			                                </div>
			                                <div class="entry-meta mt-2">
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
					       		<div class="col-lg-3 col-md-12 bg-theme-light ">
					       			<div class=" m-2 p-2">
						       			<section class="bg-theme-light testimonials-section">
						       				<div class="testimonial p-2 m-2">
						       					<div class="section-heading2">
							                    	<span class="alt-font">About</span>
							                	</div>
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
						       			<section class="bg-theme-light p-2 m-2">
						       				 <div class="section-heading2">
							                    <span class="alt-font">Category</span>
							                    <div>
							       					<a href="${contextPath}/blog?q=BC.IDeq${blog.ID_CATEGORY}&page=1">
							       						<button class="btn btn-sm btn-outline-primary">${blog.TX_CATEGORY}</button>
							       					</a>
						       					</div>
							                </div>
						       				
						       			</section>
						       			<hr/>
						       			<section class="bg-theme-light p-2 m-2">
						       				 <div class="section-heading2">
							                    <span class="alt-font">Tags</span>
							                    	 <div id="tag-div" ></div>
							                    
							                </div>
						       			</section>
						       			<hr/>
						       		</div>
					       		</div>
					       
					       </div>
					       
					        <script>
								var str = "${blog.TX_TAGS}"
								var res = str.split(" ");
								res.forEach(processTag);
								
								function processTag(item,index){
								//	document.getElementById("tags").innerHTML += "<div class='btn btn-sm btn-info btn-rounded p-2 m-1'>"+item+"</div>"
								//	document.getElementById("tag-div").innerHTML += "<span id='tag-"+item+"' class='tag-pill'>"+item+"<div class='float-left'></div>"
									document.getElementById("tag-div").innerHTML += "<span id='tag-"+item+"' class='btn btn-sm btn-info btn-rounded p-2 m-1 text-white' >"+item+"</span>" 
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
												 		<div class="row">
											              <div class="text-primary">
										                    <span class="alt-font">${comment.TX_NAME}</span>
										                  </div>
										              	</div>
										              	<div class="row">
								       						<div class="entry-meta">
							                                	<span class="blog-posted-meta">on <a href=""> ${comment.TS_CREATE} </a></span> 
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
									
												<form class="form-horizontal m-t-20" action="${contextPath}/blogs/submitComment" modelAttribute="BlogCommentBean" method="POST" name="BlogCommentForm" id="blogCommentForm">
													<div id="commentFormDiv">
														
														<c:if test="${isUSer}">
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
															<div class="col-lg-12 col-sm-12 m-2">
																<textarea class="form-control" rows="3" id="TX_COMMENT_MESSAGE" name="TX_COMMENT_MESSAGE" path="TX_COMMENT_MESSAGE" placeholder="Comment here..."></textarea>
															</div>
														</div>
														
														<div class="row">
															<div class="text-right">
															 	<button type="submit" id="save-comment" class="btn btn-primary">Save</button>
															 </div>
														</div>
														<div class="row" id="hiddenCommentValues">
															<c:if test="${isUSer}">
																<input type="text" id="FL_SYSTEM_USER" name="FL_SYSTEM_USER" path="FL_SYSTEM_USER" value="true"/>
																<input type="text" id="TX_NAME" name="TX_NAME" path="TX_NAME" value="${sessionScope.firstname} ${sessionScope.lastname}"/>
																<input type="text" id="TX_EMAIL" name="TX_EMAIL" path="TX_EMAIL" value="${principal.username}"/>
																<script>
																	document.getElementById("TX_NAME").style.visibility = "hidden";
																	document.getElementById("TX_EMAIL").style.visibility = "hidden";
																</script>
															</c:if>
															<input type="text" id="ID_BLOG" name="ID_BLOG" path="ID_BLOG" value="${blogList[0].ID_BLOG}"/>
															<input type="text" id="DEPTH" name="DEPTH" path="DEPTH" value="0"/>
															<input type="text" id="ID_PARENT" name="ID_PARENT" path="ID_PARENT" value="0"/>
														</div>
													</div>
												</form>
										</div>
									</div>
									
									
									
					   
				</c:if>
                
            </div>
		<script>
					document.getElementById("ID_BLOG").style.visibility = "hidden";
	            	document.getElementById("DEPTH").style.visibility = "hidden";
	            	document.getElementById("ID_PARENT").style.visibility = "hidden";
	            	document.getElementById("FL_SYSTEM_USER").style.visibility = "hidden";
            	</script>
		 
        
        <section>
        	<div>
        		<br/>
        	</div>
        </section>
                  
	</jsp:body>
</page:platform> 