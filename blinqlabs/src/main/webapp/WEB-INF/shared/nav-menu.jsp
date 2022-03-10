<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<!-- start menu area -->
							<ul class="navbar-nav ml-auto" id="nav" style="display: none;">
								<li><a href="${contextPath}/">Home</a>
									<ul>
										<li><a href="${contextPath}/aboutus">Who we are</a></li>
										<li><a href="${contextPath}/whyus">Why work with us</a></li>
										<li><a href="${contextPath}/careers">Careers</a></li>
									</ul>
								</li>
									
								<li><a href="#">Products</a>
									<ul>
										<li><a href="${contextPath}/products/cheetah">Cheetah</a></li>
									</ul>
								</li>
								
								<li><a href="${contextPath}/services">Services</a>
									<ul>
										<li><a href="${contextPath}/enterpriseServices">Enterprise Services</a></li>
										<li><a href="${contextPath}/smb">Startups and SMBs</a></li>
										<li><a href="${contextPath}/dot">DevOps Tool Chain (Saas)</a></li>
										<li><a href="${contextPath}/oss">Open Source Projects</a></li>
										<li><a href="${contextPath}/training-certification">Training and Certifications</a></li>
									</ul></li>
									
								<li><a href="${contextPath}/blog?page=1">Blog</a></li>
								
								<li><a href="javascript:void(0)">Support</a>
									<ul>
											<li><a href="${contextPath}/support">Technical Support</a></li>
											<li><a href="${contextPath}/sales">Purchasing and Licensing</a></li>
											<!-- <li><a href="${contextPath}/docs">Documentation</a></li> -->
											<li><a href="${contextPath}/faq?type=g">FAQ</a></li>
											<li><a href="${contextPath}/contact">Contact Us</a></li>
									</ul></li>
								
							</ul>
							<!-- end menu area -->

							<!-- start attribute navigation -->
							<div class="attr-nav sm-no-margin sm-margin-65px-right mr-4">
								<ul>
									<li class="search"><a href="javascript:void(0)"><i
											class="fas fa-search" ></i></a></li>
								</ul>
							</div>
							<!-- end attribute navigation -->

						



						<div id="login">
							<security:authorize
								access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER','ROLE_USER')"
								var="isUSer" />

							<c:if test="${not isUSer}">
								<c:if test="${empty param.error}">
									<!-- <a href="signin" class="nav-link nav-link--rounded">Signin</a> -->
									<a href="${contextPath}/authenticate" class="butn style-four margin-15px-right vertical-align-middle">Login</a>
								</c:if>
							</c:if>

							<c:if test="${isUSer}">
								<ul class="navbar-nav">
									<%-- <li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown"> <security:authentication
							property="principal.username" /> <i class="ion-chevron-down"></i>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="user_account">Account</a>
							<a class="dropdown-item" href="j_spring_security_logout">Logout</a>
						</div>
				</li> --%>


									<li class="nav-item dropdown"><a
										class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic"
										href="" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">
									
											
											<c:if test="${not empty sessionScope.userImage}">
								                            	<img src="${sessionScope.userImage}" alt="user" class="rounded-circle" width="31">
								                            </c:if>
								                            <c:if test="${empty sessionScope.userImage}">
								                            	<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="31">
								                            </c:if>
								                            
											</a>
											
											
										<div
											class="dropdown-menu dropdown-menu-right user-dd animated flipInY ">



											<div class="card  mb-3 text-center">
												<span class="with-arrow"><span class="bg-primary"></span></span>
												<!-- <div class="d-flex no-block align-items-center p-15 bg-primary text-white m-b-10"> -->
												<div
													class="card-body text-white align-items-center d-flex no-block p-15 bg-primary mx-3 px-3">
													<div class="">
															<c:if test="${not empty sessionScope.userImage}">
								                            	<img src="${sessionScope.userImage}" alt="user" class="img-circle" width="60">
								                            </c:if>
								                            <c:if test="${empty sessionScope.userImage}">
								                            	<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="img-circle" width="60">
								                            </c:if>
													
													</div>
													<div class="m-l-10 px-2 mx-2">
														<h4 class="m-b-0">
															<span>${sessionScope.firstname}
																${sessionScope.lastname}</span>
														</h4>
														<p class=" m-b-0">
															<security:authentication property="principal.username" />
														</p>
													</div>
												</div>
											</div>

											<a class="dropdown-item" href="${contextPath}/user/UserMessages?page=1"><i
												class="fa fa-envelope-open  m-r-5 m-l-5"></i> Inbox</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="${contextPath}/user/useraccountsettings"><i
												class="fa fa-user-circle m-r-5 m-l-5"></i> Account Setting</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="${contextPath}/j_spring_security_logout"><i
												class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
											<div class="dropdown-divider"></div>
											<div class="p-l-30 p-10">
												<a href="${contextPath}/user/userprofile"
													class="btn btn-sm btn-success btn-rounded">View Profile</a>
											</div>
										</div></li>



								</ul>
							</c:if>

						</div>
