<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
 
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <header class="topbar">
            <nav class="navbar top-navbar navbar-expand-md navbar-dark bg-primary fixed-top page-scroll">
                <div class="navbar-header">
                    <!-- This is for the sidebar toggle which is visible on mobile only -->
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                   
                   <!-- ============================================================== -->
                    <!-- Logo -->
                    <!-- ============================================================== -->
                   
                    <a class="navbar-brand" href="${contextPath}/index.jsp">
                        <!-- Logo icon -->
                        <b class="logo-icon">
                            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                           
                            <!-- Light Logo icon -->
                            <img src="${pageContext.request.contextPath}/resources/img/logos/white-logo-only.png" width="30" alt="homepage" class="light-logo" />
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text -->
                        <span class="logo-text white">
                            
                            <spring:eval expression="@propertyConfigurer.getProperty('application.name')" />
                        </span>
                    </a>
                   
               		<!-- ============================================================== -->
                    <!-- Toggle which is visible on mobile only -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i class="ti-more"></i></a>
                </div>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
                <div class="navbar-collapse collapse" id="navbarSupportedContent">
                    <!-- ============================================================== -->
                    <!-- toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-left mr-auto">
                        <li class="nav-item d-none d-md-block"><a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)" data-sidebartype="mini-sidebar"><i class="mdi mdi-menu font-24"></i></a></li>
                           
                       
                        
                       <security:authorize access="hasRole('ROLE_ROOT') OR hasRole('ROLE_ADMIN') OR hasAuthority('PERM_ADMIN_WEBSITE')"> 
                        <!-- ============================================================== -->
                        <!-- Admin menu -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown mega-dropdown"><a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="d-none d-md-block">Admin <i class="fa fa-angle-down"></i></span>
                             <span class="d-block d-md-none"><i class="mdi mdi-dialpad font-24"></i></span>
                            </a>
                            <div class="dropdown-menu animated bounceInDown">
                                <div class="mega-dropdown-menu row">
                                    <div class="col-lg-3 col-xlg-2 m-b-30">
                                        <h4 class="m-b-20">News and Articles</h4>
                                        <!-- Accordian -->
                                        <div id="accordionOne">
                                            <div class="card m-b-5">
                                                <div class="card-header" id="headingOneOne">
                                                    <h5 class="mb-0">
                                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOneOne" aria-expanded="true" aria-controls="collapseOneOne">
                                                  	Blogs
                                                </button>
                                              </h5>
                                                </div>
                                                <div id="collapseOneOne" class="collapse show" aria-labelledby="headingOneOne" data-parent="#accordionOne">
                                                    <div class="card-body">
                                                    	<security:authorize access="hasAuthority('PERM_BLOG_CREATE')"> 
		                                                    <div class="m-1 p-1">
		                                                        <a href="${contextPath}/admin/blogs/newBlog" method="POST"><i class="fas fa-newspaper"></i>  Post New Blog</a>
		                                                    </div>
	                                                   </security:authorize>
	                                                     <div class="m-1 p-1">
	                                                     	<a href="${contextPath}/admin/blogs/viewAllBlogs?page=1&type=a" method="GET"><i class="fas fa-rss"></i>  View All Blog Articles</a>
	                                                   	</div>
	                                                   	<div class="m-1 p-1">
	                                                     	<a href="${contextPath}/admin/blogs/viewAllBlogs?page=1&type=p" method="GET"><i class="fas fa-rss-square"></i>  View Published Blogs</a>
	                                                   	</div>
	                                                   	<div class="m-1 p-1">
	                                                     	<a href="${contextPath}/admin/blogs/viewAllBlogs?page=1&type=d" method="GET"><i class="fas fa-edit"></i>  View Blog Drafts</a>
	                                                   	</div>
                                                   	</div>
                                                </div>
                                            </div>
                                           
                                            <div class="card m-b-5">
                                                <div class="card-header" id="headingTwoOne">
                                                    <h5 class="mb-0">
                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwoOne" aria-expanded="false" aria-controls="collapseTwoOne">
                                                  News
                                                </button>
                                              </h5>
                                                </div>
                                                <div id="collapseTwoOne" class="collapse" aria-labelledby="headingTwoOne" data-parent="#accordionOne">
                                                     <div class="card-body">
                                                     	<div>
                                                     	<a href="#" method="POST"><i class="fas fa-plus-circle"></i>  Post News Article</a>
                                                        
                                                     	</div>
                                                       
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                           
                                            <div class="card m-b-5">
	                                            <security:authorize access="hasAuthority('PERM_FEATURE_DOCUMENTATION')">
	                                                <div class="card-header" id="headingThreeOne">
	                                                    <h5 class="mb-0">
			                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThreeOne" aria-expanded="false" aria-controls="collapseThreeOne">
			                                                  Documentation
			                                                </button>
			                                              </h5>
	                                                </div>
                                                </security:authorize>
                                                <div id="collapseThreeOne" class="collapse" aria-labelledby="headingThreeOne" data-parent="#accordionOne">
                                                      <div class="card-body">
                                                        <a href="#" method="">Cheetah</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 m-b-30">
                                        <h4 class="m-b-20">PRODUCTS</h4>
                                        <!-- Accordian -->
                                        <div id="accordion">
                                            <div class="card m-b-5">
                                                <div class="card-header" id="headingOne">
                                                    <h5 class="mb-0">
                                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                  Product Catalogue
                                                </button>
                                              </h5>
                                                </div>
                                                <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                                    <div class="card-body">
                                                        <a href="${contextPath}/admin/adminProductList" method="GET">View Product List</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <security:authorize access="hasAuthority('PERM_ADMIN_SERVICES')">
	                                            <div class="card m-b-5">
	                                                <div class="card-header" id="headingTwo">
	                                                    <h5 class="mb-0">
	                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	                                                  Services
	                                                </button>
	                                              </h5>
	                                                </div>
	                                                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
	                                                	
		                                                     <div class="card-body">
		                                                     	<div class="m-1 p-1">
		                                                        	<a href="${contextPath}/admin/ServiceAdministration" method="">Service Administration</a>
		                                                        </div>
		                                                        <div class="m-1 p-1">
		                                                        	<a href="${contextPath}/admin/adminServiceEntitlements" method="">Service Entitlements</a>
		                                                        </div>
		                                                    </div>
	                                                  
	                                                </div>
	                                            </div>
                                             </security:authorize>
                                            <div class="card m-b-5">
                                                <div class="card-header" id="headingThree">
                                                    <h5 class="mb-0">
                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                  Purchases / Sales
                                                </button>
                                              </h5>
                                                </div>
                                                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                                                      <div class="card-body">
                                                        <a href="#" method="">Sales Overview</a>
                                                    </div>
                                                     <div class="card-body">
                                                        <a href="${contextPath}/admin/invoice" method="">Invoice</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3  m-b-30">
                                    	<div>
	                                        <h4 class="m-b-20">User Management</h4>
	                                       	 <ul class="list-style-none">
	                                            <security:authorize access="hasAuthority('PERM_PERMISSIONS_VIEW')">
	                                            	<li><a href="${contextPath}/admin/settings/permissions">Permissions</a></li>
	                                            </security:authorize>
	                                            <security:authorize access="hasAuthority('PERM_USERS_CREATE_MODIFY') OR hasAuthority('PERM_USERS_VIEW')">
	                                            	<li><a href="${contextPath}/admin/settings/users/viewAllUsers">Users</a></li>
	                                            </security:authorize>
	                                            <security:authorize access="hasAuthority('PERM_GROUPS_CREATE_MODITY') OR hasAuthority('PERM_GROUPS_VIEW')">
	                                            	<li><a href="${contextPath}/admin/settings/users/viewAllGroups">Groups</a></li>
	                                            </security:authorize>
	                                            
	                                        </ul>
                                        </div>
                                        
                                       	<div class="mt-4">
	                                        <h4 class="m-b-20">Project / Client Management</h4>
	                                       	 <ul class="list-style-none">
	                                            <security:authorize access="hasAuthority('PERM_PERMISSIONS_VIEW')">
	                                            	<li><a href="${contextPath}/admin/settings/techcos">Technical Coordinators</a></li>
	                                            </security:authorize>
	                                        </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-xlg-4 m-b-30">
                                        <h4 class="m-b-20">Administration</h4>
                                        <!-- List style -->
                                        <ul class="list-style-none">
                                        	<security:authorize access="hasAuthority('PERM_FEATURES')">
                                            	<li><a href="${contextPath}/admin/featureManagement" method="GET">Feature Management</a></li>
                                            </security:authorize>
                                            <security:authorize access="hasAuthority('PERM_SUBSCRIPTION')">
                                            	<li><a href="${contextPath}/admin/subscriptionManagement" method="GET">Subscription Management</a></li>
                                            </security:authorize>
                                            <security:authorize access="hasAuthority('PERM_LICENSE')">
                                            	<li><a href="${contextPath}/admin/adminLicense?page=1" method="GET">License Management</a></li>
                                            </security:authorize>
                                            <security:authorize access="hasAuthority('PERM_TICKET_VIEW') OR hasAuthority('PERM_TICKET_WORK')"> 
                                            	<li><a href="${contextPath}/admin/adminTickets?filter=all&page=1" method="GET">Ticket Management</a></li>
                                            </security:authorize>
                                            <li><a href="${contextPath}/admin/faqs?type=a">FAQs</a></li>
                                            <security:authorize access="hasAuthority('PERM_SETTINGS_MODIFY') OR hasAuthority('PERM_SETTINGS_VIEW')">
                                            	<li><a href="${contextPath}/admin/system-settings">Settings</a></li>
                                            </security:authorize>
                                            <li><a href="javascript:void(0)">About</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- End Admin menu -->
                        <!-- ============================================================== -->
                        </security:authorize>
                        
                        
                        
                        
                        
                        
                           
                            <!-- ============================================================== -->
                        <!-- mega menu -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown mega-dropdown"><a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="d-none d-md-block">Services<i class="fa fa-angle-down"></i></span>
                             <span class="d-block d-md-none"><i class="mdi mdi-dialpad font-24"></i></span>
                            </a>
                            <div class="dropdown-menu animated bounceInDown">
                                <div class="mega-dropdown-menu row">
                                  <!--   <div class="col-lg-3 col-xlg-2 m-b-30"> -->
                                       <!--  <h4 class="m-b-20">CAROUSEL</h4> -->
                                        <!-- CAROUSEL -->
                                        <!-- 
                                        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                            <div class="carousel-inner" role="listbox">
                                                <div class="carousel-item active">
                                                    <div class="container p-0"> <img class="d-block img-fluid" src="${pageContext.request.contextPath}/resources/css/xtreme/assets/images/big/img1.jpg" alt="First slide"></div>
                                                </div>
                                                <div class="carousel-item">
                                                    <div class="container p-0"><img class="d-block img-fluid" src="${pageContext.request.contextPath}/resources/css/xtreme/assets/images/big/img2.jpg" alt="Second slide"></div>
                                                </div>
                                                <div class="carousel-item">
                                                    <div class="container p-0"><img class="d-block img-fluid" src="${pageContext.request.contextPath}/resources/css/xtreme/assets/images/big/img3.jpg" alt="Third slide"></div>
                                                </div>
                                            </div>
                                            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a>
                                            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next"> <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only">Next</span> </a>
                                        </div> -->
                                        <!-- End CAROUSEL -->
                                    <!-- </div>  -->
                                    <div class="col-lg-3 m-b-30">
                                        <h4 class="m-b-20">Available Services</h4>
                                        
                                         <div id="accordion">
                                         
                                        <c:set var="hasSEN" value="false" />
                                        <security:authorize access="hasAuthority('PERM_FEATURE_TECH_SUPPORT')">
                                       	 <c:set var="hasSEN" value="true" />
                                       	 <div class="card m-b-5">
	                                       	 <div class="card-header" id="headingOone">
	                                                    <h5 class="mb-0">
	                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOone" aria-expanded="false" aria-controls="collapseOone">
	                                                  Service Entitlements
	                                                </button>
	                                              </h5>
	                                               <div id="collapseOone" class="collapse" aria-labelledby="headingOone" data-parent="#accordion">
	                                                    <div class="card-body">
	                                                        <a href="${contextPath}/user/userServices">View Service Entitlements</a>
	                                                    </div>
	                                                </div>
	                                                </div>
                                              </div>
	                                       
                                        </security:authorize>
                                        <c:set var="hascloud" value="false" />
                                        <security:authorize access="hasAuthority('PERM_FEATURE_CLOUD')"> 
	                                        <!-- Accordian -->
	                                        <c:set var="hascloud" value="true" />
	                                       
	                                            <div class="card m-b-5">
	                                                <div class="card-header" id="headingTtwo">
	                                                    <h5 class="mb-0">
	                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTtwo" aria-expanded="false" aria-controls="collapseTtwo">
	                                                  Infrastructure as a Service (IaaS)
	                                                </button>
	                                              </h5>
	                                                </div>
	                                                <div id="collapseTtwo" class="collapse" aria-labelledby="headingTtwo" data-parent="#accordion">
	                                                    <div class="card-body">
	                                                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry.
	                                                    </div>
	                                                </div>
	                                            </div>
	                                            <div class="card m-b-5">
	                                                <div class="card-header" id="headingTthree">
	                                                    <h5 class="mb-0">
	                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTthree" aria-expanded="false" aria-controls="collapseTthree">
	                                                  Software as a Service (Saas)
	                                                </button>
	                                              </h5>
	                                                </div>
	                                                <div id="collapseTthree" class="collapse" aria-labelledby="headingTthree" data-parent="#accordion">
	                                                    <div class="card-body">
	                                                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry.
	                                                    </div>
	                                                </div>
	                                            </div>
	                                    </security:authorize>
                                         </div>
                                        <c:if test="${(hascloud == false) and (hasSEN == false)}">
                                        	<div class="text-warning">
                                                 There are no Quick Links to display.
                                             </div>
                                        </c:if>
                                    </div>
                                    <!-- 
                                    <div class="col-lg-3  m-b-30">
                                        <h4 class="m-b-20">CONTACT US</h4>
                                        <form>
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="exampleInputname1" placeholder="Enter Name"> </div>
                                            <div class="form-group">
                                                <input type="email" class="form-control" placeholder="Enter email"> </div>
                                            <div class="form-group">
                                                <textarea class="form-control" id="exampleTextarea" rows="3" placeholder="Message"></textarea>
                                            </div>
                                            <button type="submit" class="btn btn-info">Submit</button>
                                        </form>
                                    </div>
                                     -->
                                    <div class="col-lg-3 col-xlg-4 m-b-30">
                                        <h4 class="m-b-20">Quick Links</h4>
                                        <c:set var="haslinks" value="false" />
                                        <!-- List style -->
                                        <ul class="list-style-none">
                                         	<security:authorize access="hasAuthority('PERM_FEATURE_LAB')"> 
                                            	<li><a href="javascript:void(0)"><i class="fas fa-server"></i> My Lab</a></li>
                                            	<c:set var="haslinks" value="true" />
                            				</security:authorize>
                            				<security:authorize access="hasAuthority('PERM_LICENSE')">
                                            	<li><a href="${contextPath}/user/userLicenses?page=1&filter=all"><i class="fas fa-key"></i> Licenses</a></li>
                                            	<c:set var="haslinks" value="true" />
                            				</security:authorize>
                            				<security:authorize access="hasAuthority('PERM_FEATURE_ORDERS')">
                                           		<li><a href="${contextPath}/user/orderInvoice"><i class="fas fa-file"></i> Orders & Invoices</a></li>
                                           		<c:set var="haslinks" value="true" />
                                            </security:authorize>
                                            
                                            <%-- <security:authorize access="hasAuthority('PERM_FEATURE_DOWNLOADS')">
                                           	 	<li><a href="javascript:void(0)"><i class="fas fa-download"></i> Downloads</a></li>
                                           	 	<c:set var="haslinks" value="true" />
                                            </security:authorize> --%>
                                        </ul>
                                        <c:if test="${haslinks == false }">
                                        	<div class="text-warning">
                                                 You do not qualify for any Quick Links.
                                             </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- End mega menu -->
                        <!-- ============================================================== -->
                        
                        
                           
                           
                            <li class="nav-item dropdown ">
                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                           
                            <span class="d-none d-md-block">User <i class="fa fa-angle-down"></i></span>
                             <span class="d-block d-md-none"><i class="mdi mdi-dialpad font-24"></i></span>
                            </a>
                            <div class="dropdown-menu " aria-labelledby="navbarDropdown">
	                            <a class="dropdown-item" href="${contextPath}/user/userprofile">User Profile</a> 
	                            <a class="dropdown-item" href="${contextPath}/user/useraccountsettings" method="GET">Account Settings</a> 
	                            <a class="dropdown-item" href="${contextPath}/user/passwordManagement" method="GET">Password Management</a> 
						    </div>
                            </li>  
                            
                           
                    </ul>
                    <!-- ============================================================== -->
                    <!-- Right side toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-right">
                        
                      	<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:if test="${not empty sessionScope.userImage}">
                            	<img src="${sessionScope.userImage}" alt="user" class="rounded-circle" width="31">
                            </c:if>
                            <c:if test="${empty sessionScope.userImage}">
                            	<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="31">
                            </c:if>
                            
                            
                            </a>
                            <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY ">
                            
                            
                            
							<div class="card  mb-3 text-center">
	                                <span class="with-arrow"><span class="bg-primary"></span></span>
	                                <!-- <div class="d-flex no-block align-items-center p-15 bg-primary text-white m-b-10"> -->
	                                <div class="card-body text-white align-items-center d-flex no-block p-15 bg-primary mx-3 px-3" >
	                                    <div class="">
	                                    	<c:if test="${not empty sessionScope.userImage}">
				                            	<img src="${sessionScope.userImage}" alt="user" class="rounded-circle" width="60">
				                            </c:if>
				                            <c:if test="${empty sessionScope.userImage}">
				                            	<img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="60">
				                            </c:if>
	                                    </div>
	                                    <div class="m-l-10 px-2 mx-2">
	                                         <h4 class="m-b-0"><span>${sessionScope.firstname} ${sessionScope.lastname}</span></h4>
	                                        <p class=" m-b-0"><security:authentication property="principal.username" /></p>
	                                    </div>
	                                </div>
                                </div>
                                
                                <security:authorize access="hasAuthority('PERM_FEATURE_MESSAGING')">
                                	<a class="dropdown-item" href="${contextPath}/user/UserMessages?page=1"><i class="fa fa-envelope-open  m-r-5 m-l-5"></i> Inbox</a>
                                </security:authorize>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${contextPath}/user/useraccountsettings"><i class="fa fa-user-circle m-r-5 m-l-5"></i> Account Setting</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${contextPath}/j_spring_security_logout"><i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
                                <div class="dropdown-divider"></div>
                                <div class="p-l-30 p-10"><a href="${contextPath}/user/userprofile" class="btn btn-sm btn-success btn-rounded">View Profile</a></div>
                            </div>
                            
                        </li>
                     </ul>
                </div>
            </nav>
        </header>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                       
                        <li class="sidebar-item"> 
                        <a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false"><i class="fa fa-user-circle m-r-5 m-l-5"></i><span class="hide-menu">User</span></a>
                            <ul aria-expanded="false" class="collapse  first-level">
                                <li class="sidebar-item"><a href="${contextPath}/user/userprofile" class="sidebar-link"><i class="fa fa-street-view  m-r-5 m-l-5"></i><span class="hide-menu"> Overview </span></a></li>
                                <li class="sidebar-item"><a href="${contextPath}/user/useraccountsettings"  method="GET" class="sidebar-link"><i class="fa fa-cog  m-r-5 m-l-5"></i><span class="hide-menu"> Account Settings </span></a></li>
                                <li class="sidebar-item"><a href="${contextPath}/user/passwordManagement" method="GET" class="sidebar-link"><i class="fa fa-unlock  m-r-5 m-l-5"></i><span class="hide-menu"> Password Management</span></a></li>
                            </ul>
                        </li>
                     
                     	<security:authorize access="hasAuthority('PERM_LICENSE') OR hasAuthority('PERM_FEATURE_TECH_SUPPORT') OR hasAuthority('PERM_FEATURE_CLOUD') OR hasAuthority('PERM_FEATURE_LAB') OR hasAuthority('PERM_FEATURE_ORDERS') ">
	                        <li class="sidebar-item"> <a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false"><i class="mdi mdi-widgets"></i><span class="hide-menu">My Lab </span></a>
	                            <ul aria-expanded="false" class="collapse first-level">
	                            	<security:authorize access="hasAuthority('PERM_LICENSE')">
	                                	<li class="sidebar-item"><a href="${contextPath}/user/userLicenses?page=1&filter=all" method="GET" class="sidebar-link"><i class="fa fa-key  m-r-5 m-l-5"></i><span class="hide-menu"> My Licenses</span></a></li>
	                              	</security:authorize>
	                              	 <security:authorize access="hasAuthority('PERM_FEATURE_TECH_SUPPORT') OR hasAuthority('PERM_FEATURE_CLOUD') "> 
	                                	<li class="sidebar-item"><a href="${contextPath}/user/userServices" class="sidebar-link"><i class="fas fa-cog m-r-5 m-l-5"></i><span class="hide-menu"> My Services</span></a></li>
	                                </security:authorize>
	                                <security:authorize access="hasAuthority('PERM_FEATURE_LAB')">
	                                	<li class="sidebar-item"><a href="${contextPath}/user/userLab" class="sidebar-link"><i class="fas fa-server m-r-5 m-l-5"></i><span class="hide-menu"> My Lab</span></a></li>
	                                </security:authorize>
	                                <security:authorize access="hasAuthority('PERM_FEATURE_ORDERS')">
	                                   <li class="sidebar-item"><a href="${contextPath}/user/orderInvoice" class="sidebar-link"><i class="fa fa-dollar-sign  m-r-5 m-l-5"></i><span class="hide-menu"> Orders</span></a></li>
	                                </security:authorize>
	                            </ul>
	                        </li>
                       </security:authorize>
                       
                       <security:authorize access="hasAuthority('PERM_FEATURE_MESSAGING')">
                       	 	<li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${contextPath}/user/UserMessages?page=1" method="GET" aria-expanded="false"><i class="fa fa-envelope-open  m-r-5 m-l-5"></i><span class="hide-menu">Inbox</span></a></li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('PERM_FEATURE_TICKETING')">
                        	<li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${contextPath}/user/UserTickets?page=1&filter=all" method="GET" aria-expanded="false"><i class="fa fa-phone-square m-r-5 m-l-5"></i><span class="hide-menu">Tickets</span></a></li>
                        </security:authorize>
                        <security:authorize access="hasAuthority('PERM_FEATURE_DOCUMENTATION')">
                        	<li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="https://docs.blinqlabs.com" target="_blank" aria-expanded="false"><i class="mdi mdi-content-paste"></i><span class="hide-menu">Documentation</span></a></li>
                        </security:authorize> 
                        
                        <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="${contextPath}/j_spring_security_logout" aria-expanded="false"><i class="mdi mdi-directions"></i><span class="hide-menu">Log Out</span></a></li>
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </aside>

        
        
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app.min.js"></script>
    <!-- minisidebar -->
    <script>
    $(function() {
        "use strict";
        $("#main-wrapper").AdminSettings({
            Theme: false, // this can be true or false ( true means dark and false means light ),
            Layout: 'vertical',
            LogoBg: 'skin1', // You can change the Value to be skin1/skin2/skin3/skin4/skin5/skin6 
            NavbarBg: 'skin5', // You can change the Value to be skin1/skin2/skin3/skin4/skin5/skin6
            SidebarType: 'mini-sidebar', // You can change it full / mini-sidebar / iconbar / overlay
            SidebarColor: 'skin1', // You can change the Value to be skin1/skin2/skin3/skin4/skin5/skin6
            SidebarPosition: false, // it can be true / false ( true means Fixed and false means absolute )
            HeaderPosition: false, // it can be true / false ( true means Fixed and false means absolute )
            BoxedLayout: false, // it can be true / false ( true means Boxed and false means Fluid ) 
        });
    });
    </script>
    <!-- app -->
    <%-- <script src="${pageContext.request.contextPath}/resources/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app.init.js"></script> --%>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app-style-switcher.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/custom.min.js"></script>
    <!--This page JavaScript -->
    <!--chartis chart-->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <!--c3 charts -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/c3.min.js"></script>
    <!--chartjs -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chart.js/dist/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/pages/dashboards/dashboard1.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.js"></script>