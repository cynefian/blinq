<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/img/logos/logo.png">
    <title><spring:eval	expression="@propertyConfigurer.getProperty('application.name')" /></title>
    <!-- Custom CSS -->
    <link href="${contextPath}/resources/css/xtreme/dist/css/style.min.css" rel="stylesheet">
  	<link href="${contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/dist/theme.min.css" rel="stylesheet" />
		
	<script src="${pageContext.request.contextPath}/resources/dist/theme.min.js"></script>
  
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <div class="main-wrapper">
    
       <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Login box.scss -->
        <!-- ============================================================== -->
        <c:if test="${not empty successmessage}">
		 	<script>
		 	 	$(window).on("load", function() {
			    	toastr.success('${successmessage}', 'Success', { "progressBar": true });
			    });
		  	</script>
		</c:if>
		<c:if test="${not empty failuremessage}">
		 	<script>
		 	 	$(window).on("load", function() {
			    	toastr.error('${failuremessage}', 'Failure', { "progressBar": true });
			    });
		  	</script>
		</c:if>
		
		<div class="auth-wrapper d-flex no-block justify-content-center align-items-center" style="background:url(${contextPath}/resources/css/xtreme/assets/images/big/auth-bg.jpg) no-repeat center center;">
            <div class="auth-box">
                <div>
                    <div class="logo">
                     <span class="db"><a href="${contextPath}"><img src="${pageContext.request.contextPath}/resources/img/logos/logo.png" width="15%" alt="logo" /></a></span>
                    	<a href="${contextPath}/index.jsp"><span class="db"><h3><spring:eval expression="@propertyConfigurer.getProperty('application.name')" /></h3></span></a>
                        <h5 class="font-medium m-b-20">Account Recovery</h5>
                    </div>
                    <div class="row">
                        <div class="col-12">
                             <div class="form-group row ">
                                    <div class="col-12 ">
                                        <label class="form-control form-control-lg text-primary">We could not find a valid reset request</label>
                                        <label class="text-danger">The code or link is not valid or could have expired. Please try again.</label>
                                        <label class="text-primary text-center h4">${tx_email}</label>
                                    </div>
                                </div>
                                <div class="form-group text-center ">
                                  <div class="col-xs-12 p-b-20 ">
                                  	 <form class="col-12" action="${contextPath}/recoveryPage" method="POST" modelAttribute="userBean" name="UserForm" id="resetPaswordForm">
                                  	 	<input type="email" name="email" value="${tx_email}" style="display:none">
                                        <button class="btn btn-block btn-lg btn-secondary " type="submit ">Request new code</button>
                                      </form>
                                   </div>
                                </div>
                                <div class="form-group text-center ">
                                    <div class="col-xs-12 p-b-20 ">
                                       <a href="${contextPath}/authenticate"> <button class="btn btn-block btn-lg btn-info " type="button ">Go to Login</button></a>
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Login box.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper scss in scafholding.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper scss in scafholding.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Right Sidebar -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Right Sidebar -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- All Required js -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery/dist/jquery.min.js "></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/popper.js/dist/umd/popper.min.js "></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/bootstrap/dist/js/bootstrap.min.js "></script>
    <!-- ============================================================== -->
    <!-- This page plugin js -->
    <!-- ============================================================== -->
    <script>
    $('[data-toggle="tooltip "]').tooltip();
    $(".preloader ").fadeOut();
    
    </script>
    
     <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
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
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.js"></script>
    
   <%--  <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/toastr/toastr-init.js"></script> --%>
    <%-- <script src="${pageContext.request.contextPath}/resources/js/toastr-init.js"></script> --%>
</body>

</html>