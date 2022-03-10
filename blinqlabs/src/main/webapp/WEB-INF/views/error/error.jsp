<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
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
                    <span class="alt-font">HTTP Error Code: ${errorCode}</span>
                    <h2>${errorMsg}</h2>
                </div>
                 <div class="col-lg-12 col-md-12 sm-text-center sm-margin-30px-bottom text-center">
                 	<c:if test="${errorCode==400}">
                 	 	<img src="${pageContext.request.contextPath}/resources/img/illustration/no-fit.png" width="60%" alt="" class="img-effect" />
                 	 </c:if>
                 	 <c:if test="${errorCode==401}">
                 	 	<img src="${pageContext.request.contextPath}/resources/img/illustration/401.png" alt="" class="img-effect" />
                 	 </c:if>
                 	 <c:if test="${errorCode==403}">
                 	 	<img src="${pageContext.request.contextPath}/resources/img/illustration/403.png" alt="" class="img-effect" />
                 	 </c:if>
                 	<c:if test="${errorCode==404}">
                 	 	<img src="${pageContext.request.contextPath}/resources/img/illustration/lost.jpg" alt="" class="img-effect" />
                 	 </c:if>
                 	 <c:if test="${errorCode==405}">
                 	 	<img src="${pageContext.request.contextPath}/resources/img/illustration/undraw_hacker_mind_6y85.png" alt="" class="img-effect" />
                 	 </c:if>
                 	 <c:if test="${errorCode==500}">
                 	 	<img src="${pageContext.request.contextPath}/resources/img/illustration/500.png" alt="" class="img-effect" />
                 	 </c:if>
                 </div>
                
            </div>
        </section>
		
	</jsp:body>
</page:platform>