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
    <title><spring:eval
		expression="@propertyConfigurer.getProperty('application.name')" /></title>
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
        
        <c:if test="${not empty failuremessage}">
				 	<script>
							$(window).on("load", function() {
								toastr.error('${failuremessage}', 'Feature', {
									"progressBar" : true
								});
							});
						</script>
				</c:if>
				
				<c:if test="${not empty successmessage}">
				 	<script>
							$(window).on(
									"load",
									function() {
										toastr.success('${successmessage}',
												'Feature', {
													"progressBar" : true
												});
									});
						</script>
				</c:if>
				
		
		<header class="topbar">
            <nav class="navbar top-navbar navbar-expand-md navbar-dark bg-primary fixed-top page-scroll">
                <div class="navbar-header">
                    <!-- This is for the sidebar toggle which is visible on mobile only -->
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                   
                   <!-- ============================================================== -->
                    <!-- Logo -->
                    <!-- ============================================================== -->
                        <!-- Logo icon -->
                        <b class="logo-icon">
                            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                           
                            <!-- Light Logo icon -->
                            <img src="${pageContext.request.contextPath}/resources/img/logos/white-logo-only.png" width="30" alt="homepage" class="light-logo" />
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text -->
                        <span class="logo-text white text-white">
                            
                            <spring:eval expression="@propertyConfigurer.getProperty('application.name')" />
                        </span>
                 	<!-- ============================================================== -->
                    <!-- Toggle which is visible on mobile only -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i class="ti-more"></i></a>
                </div>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
            </nav>
        </header>
		
		<div class="container">
			<div class="basket mt-4">
				<div class="row m-x-5">
					<div class="col-lg-12 col-sm-12 m-4 text-right">
						<a href="${contextPath}/invalidSession"><button type="button" class="btn-shadow btn-shadow-dark">Logout</button></a>
					</div>
					
				</div>
     			<div class="row mt-4">
     				<div class="col-lg-6 col-md-6 col-sm-12 text-center">
     					<img src="${pageContext.request.contextPath}/resources/img/illustration/undraw_authentication_fsn5.png" width="100%">
     				</div>
					<div class="col-lg-6 col-sm-12">
						<div class="mt-4 p-4">
	     						<p class="text-primary h4">Please enter the 2FA verification code from your authentication app.</p>
	     					</div>
	     					<div class="text-center">
	     						<form action="${contextPath}/validateAuth" method="POST" modelAttribute="AccountVerificationForm">
	     							<div>
	     								<input type="text" id="ID_USER" name="ID_USER" value="${userId}" style="visibility:hidden">
	     							</div>
	     							<div>
		     							<input type="text" id="TX_RETURN_CODE" name="TX_RETURN_CODE" placeholder="********">
		     							<button type="submit" class="btn btn-primary">Submit</button>
	     							</div>
	     						</form>
	     					</div>
	     					
					</div>
				</div>
				<div class="row mt-4">
					<div class="col-lg-12 col-sm-12 m-4 text-center">
						<p class="text-dark h6">Authenticator inaccessible or need to deactivate 2FA?</p>
						 <div>
						 	<a href="${contextPath}/temp2FA"><button type="button" class="btn btn-outline-dark">Send Code</button></a>
						 </div>
					</div>
				</div>
			
			</div>
		
		</div>
		
  	</div>
    <!-- ============================================================== -->
    <!-- All Required js -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- ============================================================== -->
    <!-- This page plugin js -->
    <!-- ============================================================== -->
    <script>
    $('[data-toggle="tooltip"]').tooltip();
    $(".preloader").fadeOut();
    // ============================================================== 
    // Login and Recover Password 
    // ============================================================== 
    $('#to-recover').on("click", function() {
        $("#loginform").slideUp();
        $("#recoverform").fadeIn();
    });
    
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