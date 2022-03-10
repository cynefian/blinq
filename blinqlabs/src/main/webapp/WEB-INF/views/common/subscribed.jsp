<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
		
			<!-- start main banner area -->
	        <div class="animated-banner-area header-secondary bg-theme-90">
		  <!-- start shape area -->
	            <div class="header-shape sm-display-none">
	                <img
							src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png"
							class="img-fluid width-100" alt="">
	            </div>
	            <!-- end shape area -->
			</div>
		
		<div class="container">
		<section>
	  		<div>
	  			<div>
	  				<h2 class="text-primary">Yay!</h2>
	  				<h6>You have successfully subscribed to our mailing list. </h6>
	  				<p>Look forward to updated, ideas, articles, tricks and more.</p>
	  			</div>
	  		</div>
	  		
	  		<div>
	  			<div class="text-center">
	  				<a href="${contextPath}/"><button type="button" class="butn">Go Home</button></a>
	  			</div>
	  		</div>
	  		
	  		
	  	</section>
	  	
	  	<section>
	  		<div>
	  			<br />
	  		</div>
	  	</section>
  
  
		</div>
	</jsp:body>
</page:platform>
