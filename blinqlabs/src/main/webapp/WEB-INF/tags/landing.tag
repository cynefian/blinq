<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@tag description="Template Site Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" fragment="true" %>

<html lang="en"> 
<head>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-MXNHSYPX27"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-MXNHSYPX27');
</script>


  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
  <title><spring:eval expression="@propertyConfigurer.getProperty('application.name')" /></title>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/logos/logo.png"/>

  <!-- stylesheets -->
 
	<link href="${pageContext.request.contextPath}/resources/scss/bootstrap/bootstrap.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/resources/dist/theme.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/dist/css/style.min.css" rel="stylesheet">  
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.css" rel="stylesheet">
	
	
  <!-- javascript -->
 	<script src="${pageContext.request.contextPath}/resources/dist/theme.min.js"></script>
  
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
  
  <div id="navigator">
	<%-- <%@ include file="/WEB-INF/shared/nav.jsp" %> --%>
		<jsp:include page="/WEB-INF/shared/nav.jsp" />
		<br/>
		<br/>
		<br/>
	</div>
	
	<div id="body-container" class="body-layout">
		<jsp:doBody />
	</div>
	
	<div id="footer-section">
	<%--  <%@ include file="/WEB-INF/shared/footer.jsp" %> --%>
	<jsp:include page="/WEB-INF/shared/footer.jsp" />
	</div>
	
	<!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <!-- apps -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app.init.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app-style-switcher.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/custom.min.js"></script>
    <!--This page JavaScript -->
    <!--chartis chart-->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <!--c3 charts -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/c3.min.js"></script>
    <!--chartjs -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chart.js/dist/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/pages/dashboards/dashboard1.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/custom.min.js"></script>
	</body>
</html>