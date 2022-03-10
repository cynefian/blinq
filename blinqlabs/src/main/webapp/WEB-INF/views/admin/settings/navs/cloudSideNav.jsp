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
		 <li class="nav-header">Provisioning - Config</li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/cloud/ansible" id="settingAnsibleLink">Ansible</a>
		 </li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/cloud/chef" id="settingChefLink">Chef</a>
		 </li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/cloud/puppet" id="settingPuppetLink">Puppet</a>
		 </li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/cloud/rundeck" id="settingRundeckLink">RunDeck</a>
		 </li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/cloud/terraform" id="settingTerraformLink">Terraform</a>
		 </li>

		 <li class="nav-header">Platform</li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/cloud/aws" id="settingAWSLink">Amazon Web Services</a>
		 </li>
		 <li class="nav-item">
		   <a  class="nav-link " href="${contextPath}/admin/settings/cloud/gcp" id="settingGCPLink" >Google Cloud Platform</a>
		 </li>
		 <li class="nav-item">
		   <a class="nav-link " href="${contextPath}/admin/settings/cloud/azure"  id="settingAzureLink" >Azure</a>
		  </li>
	</ul>
									
</div>