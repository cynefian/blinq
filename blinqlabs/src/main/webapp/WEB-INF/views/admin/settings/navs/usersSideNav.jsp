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

		<ul class="nav nav-bordered nav-vertical flex-md-column" id="settings-tab">
			  <li class="nav-header">Users</li>
			  <li class="nav-item">
				    <a class="nav-link" href="${contextPath}/admin/settings/users/viewAllUsers" id="settingSystemInfoLink" >View Users</a>
			  </li>
			  <li class="nav-header">Groups</li>
			  <li class="nav-item">
				    <a class="nav-link"  href="${contextPath}/admin/settings/users/viewAllGroups" id="settingBlogSettingsLink" >View Groups</a>
			  </li>
			  <li class="nav-header">Coordination</li>
			  <li class="nav-item">
				    <a class="nav-link"  href="${contextPath}/admin/settings/techcos" id="settingBlogSettingsLink" >Technical Coordinators</a>
			  </li>
		</ul>
</div>