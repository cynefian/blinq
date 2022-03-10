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

<div>
	<ul class="nav nav-tabs justify-content-center" id="feature-tabs">
		<li><a href="${contextPath}/admin/settings/dashboard" id="ApplicationLink"
			method="GET">Application</a></li>
		<li><a href="${contextPath}/admin/settings/cloud/aws" id="CloudLink"
			method="GET">Cloud</a></li>
		<li><a href="${contextPath}/admin/settings/systemInfo" id="SystemLink"
			method="GET">System</a></li>
		<li><a href="${contextPath}/admin/settings/users/viewAllUsers" id="UsersLink"
			method="GET">Users</a></li>
			
	</ul>

</div>
