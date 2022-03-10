<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

    <jsp:body>
    
     <!-- start main banner area -->
        <div class="animated-banner-area header-secondary bg-theme-90">
	  <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
		
		
		 <section class="service-section">
            <div class="container">
                <div class="section-heading2">
                    <span class="alt-font">Oops...</span>
                    <h2>Something went wrong</h2>
                </div>

                <div class="row">
                    <div class="col-lg-6 col-md-12 sm-text-center sm-margin-30px-bottom">
                        <img src="${pageContext.request.contextPath}/resources/img/illustration/robot-msg-error.png" alt="" class="img-effect" />
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="padding-50px-left md-padding-30px-left sm-no-padding">

                            <div class="margin-30px-bottom hover-icon">
                                <h4 class="font-size18 sm-font-size16 xs-font-size15 margin-10px-bottom text-dark-gray"><span class="service-icon"><i class="fas fa-exclamation-triangle"></i></span>Exception:</h4>
                             </div>
                            <div>
                            	<p>Message: ${exception.getExceptionLocalizedMessage()}</p>
                            	<p>Cause: ${exception.getExceptionCause()}</p>
                            	<p>Hash: ${exception.getExceptionHashCode()}</p>
                            </div>
                
                        </div>

                    </div>

                </div>

            </div>
        </section>
		
		
		<div class="alert alert-danger">
			<strong>${exception.getExceptionStackTrace()}</strong> 
		</div>	
		
	  	<section>
	  		<div>
	  			<br/>
	  		</div>
  		</section>
  		
	</jsp:body>
</page:platform>   	