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
			  <li class="nav-header">System Settings</li>
				  <li class="nav-item">
				    <a class="nav-link" href="${contextPath}/admin/settings/systemInfo" id="settingSystemInfoLink" >System Info</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="${contextPath}/admin/settings/generalConfiguration" id="settingGeneralConfigurationLink">General Configuration</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="${contextPath}/admin/settings/announcementConfiguration"  id="settingAnnouncementConfigurationLink">Announcements</a>
				  </li>
			  <li class="nav-header">Blog Settings</li>
				  <li class="nav-item">
				    <a class="nav-link"  href="${contextPath}/admin/settings/blogs/blogSettings" id="settingBlogSettingsLink" >Blog Settings</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="${contextPath}/admin/settings/blogs/blogCategories?page=1" id="settingBlogCategoriesLink" >Blog Categories</a>
				  </li>
			  <li class="nav-header">Permissions</li>
				  <li class="nav-item">
				    <a class="nav-link" href="${contextPath}/admin/settings/permissions" id="systemPermissionsLink" >System Permissions</a>
				  </li>
				  
		</ul>
</div>