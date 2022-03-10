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
		 <li class="nav-header">Application Settings</li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/dashboard" id="settingDashboardLink">Dashboard</a>
		 </li>
		 <li class="nav-item">
		   <a  class="nav-link " href="${contextPath}/admin/settings/about" id="settingAboutLink" >About</a>
		 </li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/integrations"  id="settingIntegrationsLink" >Integrations</a>
		  </li>
		  
		  <li class="nav-header">DevOps</li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/devops/categories" id="settingCategoriesLink">Categories</a>
		 </li>
		 <li class="nav-item">
		   <a  class="nav-link " href="${contextPath}/admin/settings/devops/tools" id="settingDevOpsLink" >Tools</a>
		 </li>
	</ul>
									
</div>