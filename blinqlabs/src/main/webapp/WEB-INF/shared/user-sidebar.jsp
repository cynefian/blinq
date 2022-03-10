<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="sidebarnavigator">


   
    
    <div class=container-fluid>
    
	<!--  side bar -->
	<aside class="left-sidebar sidebar">

		<!-- Sidebar scroll-->
		<div class="scroll-sidebar">
			<!-- Sidebar navigation-->
			<nav class="sidebar-nav">
				<ul id="sidebarnav">
					<li class="sidebar-item nav-small-cap">
						<div>
							<br /> <br /> <br /> 
							<a class="dropdown-item" href="${contextPath}/user/userprofile">
							<i	class="fa fa-user-circle m-r-5 m-l-5"></i> My Profile</a> 
							
							<a class="dropdown-item" href="${contextPath}/inboxList">
							<i class="fa fa-envelope-open  m-r-5 m-l-5"></i> Inbox</a> 
								
							<a class="dropdown-item" href="${contextPath}/user/ticketList">
							<i class="fa fa-phone-square  m-r-5 m-l-5"></i> Tickets</a> 
								
							<a class="dropdown-item" href="${contextPath}/user/useraccountsettings">
							<i class="fa fa-cogs m-r-5 m-l-5"></i> Account Settings</a> 
							
							<a class="dropdown-item" href="${contextPath}/user/passwordmanagement">
							<i class="fa fa-unlock-alt m-r-5 m-l-5"></i> Password Management</a> 
							
							<a class="dropdown-item" href="${contextPath}/j_spring_security_logout">
							<i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
						</div>
					</li>
				</ul>
			</nav>
		</div>
	</aside>
	</div>

