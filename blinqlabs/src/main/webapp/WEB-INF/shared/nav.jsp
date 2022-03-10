<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<div
	class="navbar navbar-dark bg-dark navbar-expand-lg fixed-top page-scroll"
	role="banner">
	<div id="brand-logo">
		<a class="navbar-brand" href="${contextPath}/index.jsp"> <img
			src="${pageContext.request.contextPath}/resources/images/logo-light.png"
			class="mr-2 img-fluid" /> <spring:eval
				expression="@propertyConfigurer.getProperty('application.name')" />
		</a>

	</div>

	<div class="container no-override">

		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navbar-collapse">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse justify-content-end"
			id="navbar-collapse">
			<ul class="navbar-nav">

				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown">
						Showcase <i class="ion-chevron-down"></i>
				</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${contextPath}/products">Products</a> <a
							class="dropdown-item" href="${contextPath}/features">Features</a> <a
							class="dropdown-item" href="${contextPath}/services">Services</a> <a
							class="dropdown-item" href="${contextPath}/training">Training</a> <a
							class="dropdown-item" href="${contextPath}/analytics">Analytics</a>
					</div></li>
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown">
						Resources <i class="ion-chevron-down"></i>
				</a>
					<div class="dropdown-menu">
						<!--<a class="dropdown-item" href="${contextPath}/invoice.html">Invoice page</a> -->
						<a class="dropdown-item" href="${contextPath}/apidoc">API Documentation</a> <a
							class="dropdown-item" href="${contextPath}/support">Support</a>
						<!-- <a class="dropdown-item" href="${contextPath}/support-topic.html">Support Topics</a> -->
						<a class="dropdown-item" href="${contextPath}/status">Status</a>
						<!-- <a class="dropdown-item" href="${contextPath}/404.html">404 Page</a>  -->
						<a class="dropdown-item" href="${contextPath}/docs">Documents</a> <a
							class="dropdown-item" href="${contextPath}/roadmap">Roadmap</a> <a
							class="dropdown-item" href="${contextPath}/versions">Release Versions</a> <a
							class="dropdown-item" href="${contextPath}/timeline">Timeline</a> <a
							class="dropdown-item" href="${contextPath}/comingsoon">Coming Soon</a>

					</div></li>
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown">
						Company <i class="ion-chevron-down"></i>
				</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${contextPath}/aboutus">About us</a> <a
							class="dropdown-item" href="${contextPath}/values">Values</a> <a
							class="dropdown-item" href="${contextPath}/partners">Partners</a> <a
							class="dropdown-item" href="${contextPath}/portfolio">Portfolio</a> <a
							class="dropdown-item" href="${contextPath}/gallery">Gallery</a> <a
							class="dropdown-item" href="${contextPath}/news">NEWS</a> <a
							class="dropdown-item" href="${contextPath}/contactus">Contact us</a>
						<!-- <a class="dropdown-item" href="${contextPath}/portfolio-item.html">Portfolio Item</a> -->
					</div></li>
				<li class="nav-item dropdown dropdown-extend"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown">
						Components <i class="ion-chevron-down"></i>
				</a>
					<div class="dropdown-menu dropdown-extend-menu" role="menu">
						<div class="row">
							<div class="col-md-3">
								<a class="dropdown-item" href="${contextPath}/docs/alerts.html"><i
									class="ion-alert-circled"></i> Alerts</a> <a class="dropdown-item"
									href="${contextPath}/docs/animations.html"><i class="ion-wand"></i>
									Animations</a> <a class="dropdown-item" href="${contextPath}/docs/badges.html"><i
									class="ion-ios-pricetag-outline"></i> Badges</a> <a
									class="dropdown-item" href="${contextPath}/docs/buttons.html"><i
									class="ion-ios-paperplane"></i> Buttons</a> <a
									class="dropdown-item" href="${contextPath}/docs/button-groups.html"><i
									class="ion-ios-browsers"></i> Button groups</a> <a
									class="dropdown-item" href="${contextPath}/docs/cards.html"><i
									class="ion-ios-albums-outline"></i> Cards</a>
							</div>
							<div class="col-md-3">
								<a class="dropdown-item" href="${contextPath}/docs/carousel.html"><i
									class="ion-android-arrow-dropright-circle"></i> Carousel</a> <a
									class="dropdown-item" href="${contextPath}/docs/accordion.html"><i
									class="ion-navicon"></i> Accordion</a> <a class="dropdown-item"
									href="${contextPath}/docs/cta.html"><i class="ion-funnel"></i> Call to
									action</a> <a class="dropdown-item" href="${contextPath}/docs/dropdowns.html"><i
									class="ion-arrow-down-b"></i> Dropdowns</a> <a
									class="dropdown-item" href="${contextPath}/docs/forms.html"><i
									class="ion-clipboard"></i> Forms</a> <a class="dropdown-item"
									href="${contextPath}/docs/input-groups.html"><i class="ion-toggle"></i>
									Input groups</a>
							</div>
							<div class="col-md-3">
								<a class="dropdown-item" href="${contextPath}/docs/icons.html"><i
									class="ion-social-octocat"></i> Icons</a> <a class="dropdown-item"
									href="${contextPath}/docs/list-groups.html"><i
									class="ion-ios-list-outline"></i> List groups</a> <a
									class="dropdown-item" href="${contextPath}/docs/modals.html"><i
									class="ion-monitor"></i> Modals</a> <a class="dropdown-item"
									href="${contextPath}/docs/navs.html"><i class="ion-navigate"></i> Navs</a> <a
									class="dropdown-item" href="${contextPath}/docs/navbars.html"><i
									class="ion-navicon-round"></i> Navbars</a> <a class="dropdown-item"
									href="${contextPath}/docs/plugins.html"><i class="ion-compose"></i> New
									plugins</a>
							</div>
							<div class="col-md-3">
								<a class="dropdown-item" href="${contextPath}/docs/progress.html"><i
									class="ion-stats-bars d-inline-block"
									style="transform: rotate(90deg);"></i> Progress bars</a> <a
									class="dropdown-item" href="${contextPath}/docs/stats-cards.html"><i
									class="ion-arrow-graph-up-right"></i> Stats cards</a> <a
									class="dropdown-item" href="${contextPath}/docs/tables.html"><i
									class="ion-grid"></i> Tables</a> <a class="dropdown-item"
									href="${contextPath}/docs/typography.html"><i class="ion-paintbrush"></i>
									Typography</a>
							</div>
						</div>
					</div></li>
				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown">
						Pricing <i class="ion-chevron-down"></i>
				</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${contextPath}/pricing">Pricing charts</a> <a
							class="dropdown-item" href="${contextPath}/comparisioncharts">Comparison
							tables</a>
					</div></li>

				<li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown">
						Community <i class="ion-chevron-down"></i>
				</a>
					<div class="dropdown-menu dropdown-menu-right">
						<a class="dropdown-item" href="${contextPath}/blog">Blog</a> <a
							class="dropdown-item" href="${contextPath}/blog-cols">Blog cols</a> <a
							class="dropdown-item" href="${contextPath}/blog-timeline">Blog timeline</a> <a
							class="dropdown-item" href="${contextPath}/blogpost">Blog Post</a>
					</div></li>
			</ul>
		</div>
	</div>

	<div id="login" class="nav-item">
		<security:authorize
			access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER','ROLE_USER')"
			var="isUSer" />

		<c:if test="${not isUSer}">
			<c:if test="${empty param.error}">
				<!-- <a href="${contextPath}/signin" class="nav-link nav-link--rounded">Signin</a> -->
				<a href="${contextPath}/authenticate" class="nav-link nav-link--rounded">Signin</a>
			</c:if>
		</c:if>

		<c:if test="${isUSer}">
			<ul class="navbar-nav">
				<%-- <li class="nav-item dropdown"><a href="#"
					class="nav-link dropdown-toggle" data-toggle="dropdown"> <security:authentication
							property="principal.username" /> <i class="ion-chevron-down"></i>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="${contextPath}/user/user_account">Account</a>
							<a class="dropdown-item" href="${contextPath}/j_spring_security_logout">Logout</a>
						</div>
				</li> --%>
				
				
				<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="rounded-circle" width="31"></a>
                            <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY ">
                            
                            
                            
							<div class="card  mb-3 text-center">
	                                <span class="with-arrow"><span class="bg-primary"></span></span>
	                                <!-- <div class="d-flex no-block align-items-center p-15 bg-primary text-white m-b-10"> -->
	                                <div class="card-body text-white align-items-center d-flex no-block p-15 bg-primary mx-3 px-3" >
	                                    <div class=""><img src="${pageContext.request.contextPath}/resources/images/users/1.jpg" alt="user" class="img-circle" width="60"></div>
	                                    <div class="m-l-10 px-2 mx-2">
	                                         <h4 class="m-b-0"><span>${sessionScope.firstname} ${sessionScope.lastname}</span></h4>
	                                        <p class=" m-b-0"><security:authentication property="principal.username" /></p>
	                                    </div>
	                                </div>
                                </div>
                                
                                <a class="dropdown-item" href="javascript:void(0)"><i class="fa fa-envelope-open  m-r-5 m-l-5"></i> Inbox</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="javascript:void(0)"><i class="fa fa-user-circle m-r-5 m-l-5"></i> Account Setting</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${contextPath}/j_spring_security_logout"><i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
                                <div class="dropdown-divider"></div>
                                <div class="p-l-30 p-10"><a href="${contextPath}/user/userprofile" class="btn btn-sm btn-success btn-rounded">View Profile</a></div>
                            </div>
                            
                        </li>
                        
				
				
			</ul>
		</c:if>
		
	</div>
</div>
