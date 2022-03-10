<!DOCTYPE html>
<%@ tag language="java" 
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<tag:user-landing>

	
		<div class="m-10">
			<div class="features-tabs-section" style="margin-top: 100px;">
		      <div class="header">
		        <h3>System Settings</h3>
		      </div>
		
		      <div class="tabs-wrapper" id="main-tab-index">
		        <ul class="nav nav-tabs justify-content-center" id="feature-tabs">
		          <li>
		            <a href="#Application" data-toggle="tab">Application</a>
		          </li>
		          <li>
		            <a href="#System" data-toggle="tab">System</a>
		          </li>
		        </ul>
		
		        <div class="tab-content" id="main-tab-content">
		          <div class="tab-pane fade" id="Application">
		          		<div class="basket">
	
							<div class="row">
								<div class="col-lg-2">
									<ul class="nav nav-bordered nav-vertical flex-md-column" id="settings-tab">
									  <li class="nav-header">Application Settings</li>
									  <li class="nav-item">
									    <a class="nav-link"  href="${contextPath}/admin/settings/dashboard" method="GET" class="active" >Dashboard</a>
									  </li>
									  <li class="nav-item">
									    <a class="nav-link"  href="${contextPath}/admin/settings/about" method="GET" class="active" >About</a>
									  </li>
									  <li class="nav-item">
									    <a class="nav-link"  href="${contextPath}/admin/settings/integrations"  method="GET" class="active">Integrations</a>
									  </li>
									  
									  
									</ul>
								</div>
								<div class="col-lg-10 border-left">
								
								 <div class="tab-content" id="sub-tab-content">
									          <div class="tab-pane fade" id="Dashboard">
									            <jsp:doBody />
									          </div>
									          
									          <div class="tab-pane fade" id="About">
									          	<jsp:doBody />
									          </div>
									          
									          <div class="tab-pane fade" id="Integrations">
									          	<jsp:doBody />
									          </div>	
								
									          	          
						
									 </div>
								
								</div>
							
							</div>
						
						</div>
		          </div>
		          <div class="tab-pane fade" id="System">
		          		<div class="basket">
	
							<div class="row">
								<div class="col-lg-2" id="sub-tab-index">
									<ul class="nav nav-bordered nav-vertical flex-md-column" id="settings-tab">
									  <li class="nav-header">System Settings</li>
									  <li class="nav-item">
									    <a class="nav-link" href="${contextPath}/admin/settings/systemInfo"  method="GET" class="active">System Info</a>
									  </li>
									  <li class="nav-item">
									    <a class="nav-link" href="${contextPath}/admin/settings/generalConfiguration"  method="GET" class="active">General Configuration</a>
									  </li>
									  <li class="nav-item">
									    <a class="nav-link" href="${contextPath}/admin/settings/announcementConfiguration"  method="GET" class="active" >Announcements</a>
									  </li>
									  <li class="nav-header">Blog Settings</li>
									  <li class="nav-item">
									    <a class="nav-link"  href="${contextPath}/admin/settings/blogs/blogSettings"  method="GET" class="active" >Blog Settings</a>
									  </li>
									  <li class="nav-item">
									    <!-- <a class="nav-link" href="#blogCategories" class="active" data-toggle="tab">Blog Categories</a> -->
									    <a class="nav-link" href="${contextPath}/admin/settings/blogs/blogCategories" method="GET"  class="active">Blog Categories</a>
									  </li>
									</ul>
								</div>
								<div class="col-lg-10 border-left">
								
									 <div class="tab-content" id="sub-tab-content">
									          <div class="tab-pane fade" id="SystemInfo">
									            <jsp:doBody />
									          </div>
									          
									          <div class="tab-pane fade" id="GeneralConfiguration">
									          	<jsp:doBody />
									          </div>
									          
									          <div class="tab-pane fade" id="AnnouncementConfiguration">
									          	<jsp:doBody />
									          </div>		          
									          
									          
									          <div class="tab-pane fade" id="BlogSettings">
									          	<jsp:doBody />
									          </div>
									          
									          <div class="tab-pane fade" id="BlogCategories">
									          		<jsp:doBody />
									          </div>
									 </div>
								</div>
							
							</div>
							
						</div>
		          </div>
		          
		          
		        </div>
		      </div>
		    </div>
		  
		
		</div>
		
		
	
</tag:user-landing>	