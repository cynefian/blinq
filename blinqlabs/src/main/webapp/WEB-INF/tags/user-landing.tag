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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
    
  <title><spring:eval expression="@propertyConfigurer.getProperty('application.name')" /></title>
  

  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/logos/logo.png"/>

  <!-- stylesheets -->
 
	<link href="${pageContext.request.contextPath}/resources/scss/bootstrap/bootstrap.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/resources/dist/theme.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/dist/css/style.min.css" rel="stylesheet"> 
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/prism/prism.css" >
    <link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/dragula/dist/dragula.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">
    
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
	<%-- <link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/ckeditor/samples/toolbarconfigurator/lib/codemirror/neo.css" rel="stylesheet" type="text/css" >
    <link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/ckeditor/samples/css/samples.css" rel="stylesheet" type="text/css" >
     --%>
  <!-- javascript -->
 	<%-- <script src="${pageContext.request.contextPath}/resources/dist/theme.min.js"></script>
   --%>
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
  
  
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/trumbowyg/dist/ui/trumbowyg.min.css"> 
</head>
<body>

<div id="main-wrapper">
  
  <div id="navigator">
		<jsp:include page="/WEB-INF/shared/user-nav.jsp" />
	</div>
	
	<div id="body-container"  class="body-layout">
		<jsp:doBody />
	</div>
	
	<div id="footer-section">
		 <jsp:include page="/WEB-INF/shared/footer.jsp" /> 
	</div>
</div>
	
   </body>
</html>