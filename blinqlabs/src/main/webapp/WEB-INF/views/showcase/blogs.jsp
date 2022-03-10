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
					
					<security:authorize
								access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER','ROLE_USER')"
								var="isUSer" />
                
                <c:if test="${empty blogList}">
	                <!-- Card -->
	                <div class="card text-center m-t-20 m-b-20">
					    <div class="card-header">
					        <h3>Oh oh...</h3>
					    </div>
					    <div class="card-body">
					        <h2 class="card-title">Blog Posts</h2>
					        <h3 class="card-text">There aren't any blogs published yet.</h3>
					        <c:if test="${isUSer}">
					        	<a href="${contextPath}/admin/blogs/newBlog" method="POST" class="btn btn-info">Create new post</a>
					        </c:if>
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
					
						<c:forEach var="blogline" items="${bList}" > 
							<div class="row">
								<c:forEach var="blog" items="${blogline}" >
									<div class="col-lg-4 col-md-12">
										 
										 
										 <c:if test="${not empty blog}">
										 	<!-- Card -->
										 	
										 	<a href="${contextPath}/blogs/viewBlog?id=${blog.ID_BLOG}" >
											 	<div class="card border-primary mb-3 card-hover shadow">
											 		
											 		<c:if test="${blog.FL_ACTIVE==true}">
												 		<div class="badge">
												 			<i class="fas fa-bookmark"></i>
												 		</div>
											 		</c:if>
											 		
											 		
												  <div class="card-body mt-3">
												    <h4 class="card-title">${blog.TX_TITLE}</h4>
												    <p class="card-text">${blog.TX_BODY} ...</p>
												    <button type="button" class="butn style-one  primary" onclick="${contextPath}/blogs/viewBlog?id=${blog.ID_BLOG}"><span>Read</span></button>
												    
												    <br/>
												  	<div class="image-clip" >  
														<c:if test="${not empty blog.TX_IMAGE}">
															<img class="card-img-top img-responsive" src="${blog.TX_IMAGE}" alt="${blog.TX_TITLE}">
														</c:if>
														<c:if test="${empty blog.TX_IMAGE}">
															<img class="card-img-top img-responsive" src="${pageContext.request.contextPath}/resources/images/abstract1.jpg" alt="${blog.TX_TITLE}">
														</c:if>
												  	</div>
												  </div>
												  
												  <!--  <div class="card-badge"><i class="far fa-check-square"></div> -->
												</div>
											</a>

										 	<!-- <div class="card card-hover shadow"> -->
											
											   <%--  <div class="card-body bg-light">
											        <h2 class="card-title text-primary">${blog.TX_TITLE}</h2>
											        <p>${blog.TX_BODY} ... </p>
											        <a href="${contextPath}/admin/blogs/viewBlog?id=${blog.ID_BLOG}&status=${blog.FL_ACTIVE}" class="btn btn-primary">View Full Blog</a>
											    </div>
											     <div class="card-footer bg-secondary text-white">
											        <p>${blog.TX_AUTHOR}</p>
											        <p class="float-right">${blog.TS_CREATE}</p>
											    </div> --%>
											    
											<!-- </div> -->
											<!-- Card -->
										</c:if>
										
									</div>
								</c:forEach>
							</div>
							<br/>
						</c:forEach>
					
				</c:if>
				
				
				<c:if test="${not empty blogList}">
					<div>
						<ul class="pagination float-right">
	                        
	                        <c:if test="${currentPage!=1 && currentPage>0}">
	                        	 <li class="page-item mr-2">
	                            	<a class="page-link" href="${contextPath}/blog?page=${currentPage - 1 }&type=${blogType}&q=${query}" tabindex="-1">Previous</a>
	                        	</li>
	                        </c:if>
	                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                    	<c:if test="${currentPage==loop.index}">
	                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/blog?page=${loop.index}&type=${blogType}&q=${query}">${loop.index}</a></li>
	                    	</c:if>
	                    	<c:if test="${currentPage!=loop.index}">
	                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/blog?page=${loop.index}&type=${blogType}&q=${query}">${loop.index}</a></li>
	                    	</c:if>
	                    </c:forEach>
	                    	<c:if test="${currentPage!=totalPages}">
	                        	 <li class="page-item ml-2">
	                            	<a class="page-link" href="${contextPath}/blog?page=${currentPage + 1 }&type=${blogType}&q=${query}" tabindex="-1">Next</a>
	                        	</li>
	                        </c:if>
	                    
	                    
	                    </ul>
					</div>
				</c:if>
                
            </div>
		
		 
        
        <section>
        	<div>
        		<br/>
        	</div>
        </section>
                  
	</jsp:body>
</page:platform> 