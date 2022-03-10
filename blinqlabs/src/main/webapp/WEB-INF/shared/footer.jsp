<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<div class="bottom-footer">
	<div class="footer footer--dark">
		<div class="container">
			<div class="row">
				<div class="col-md-3 menu">
					<h3>Overview</h3>
					<ul>
						<li><a href="${contextPath}/services">Services</a></li>
						<li><a href="${contextPath}/dot">DevOps ToolChain
								(Saas)</a></li>
						<li><a href="${contextPath}/sales">Pricing</a></li>
						<li><a href="${contextPath}/oss">FOSS</a></li>
						<li><a href="${contextPath}/blog?page=1">Blog</a></li>
					</ul>
				</div>
				<div class="col-md-3 menu">
					<h3>Blinqlabs</h3>
					<ul>
						<li><a href="${contextPath}/aboutus">About us</a></li>
						<li><a href="${contextPath}/services">Contact us</a></li>
						<li><a href="${contextPath}/careers">Jobs</a>
						 <%-- <a href="${contextPath}/aboutus" class="hiring"> We're hiring! </a> --%>
						</li>
						<%-- <li><a href="${contextPath}/blog">Status</a></li> --%>
					</ul>
				</div>
				<div class="col-md-2 menu">
					<h3>User Account</h3>
					<ul>
						<security:authorize access="hasAuthority('PERM_FEATURE_MESSAGING')">
							<li><a href="${contextPath}/user/UserMessages?page=1">Inbox</a></li>
						</security:authorize>
						<security:authorize access="hasAuthority('PERM_LICENSE')">
							<li><a href="${contextPath}/admin/adminLicense?page=1">Licenses</a></li>
						</security:authorize>
						<li><a href="${contextPath}/user/passwordManagement">Password Management</a></li>
						<security:authorize access="hasAuthority('PERM_FEATURE_TECH_SUPPORT') OR hasAuthority('PERM_FEATURE_CLOUD') "> 
							<li><a href="${contextPath}/user/UserTickets?page=1&filter=all">Support</a></li>
						</security:authorize>

					</ul>
				</div>
				<div class="col-md-4 newsletter h2 p-4">
					<a href="https://linkedin.com/company/blinqlabs"> <i class="fab fa-linkedin"></i>
					</a> <a href="https://twitter.com/blinqlabs"> <i class="fab fa-twitter-square"></i>
					</a>
				</div>
			</div>
			<div class="credits">
				<p>Copyright © 2020. Blinqlabs</p>
			</div>
		</div>
	</div>
</div>