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
                <div id="loginformDiv">
                    <div class="logo">
                     	<span class="db"><a href="${contextPath}"><img src="${pageContext.request.contextPath}/resources/img/logos/logo.png" width="15%" alt="logo" /></a></span>
                        <a href="${contextPath}/index.jsp"><span class="db"><h3><spring:eval expression="@propertyConfigurer.getProperty('application.name')" /></h3></span></a>
                        <h5 class="font-medium m-b-20">Sign In</h5>
                    </div>
                    <!-- Form -->
                    <div class="row">
                        <div class="col-12">
                            <form class="form-horizontal m-t-20" id="loginform" action="j_spring_security_check" method="POST" name="loginForm" role="form">
                                
                                <security:authorize
										access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER','ROLE_USER')"
										var="isUSer" />
										
                              <%--   <c:if test="${not isUSer}">
										<c:if test="${empty param.error}">
											<font size="2" color="red"> You are not logged in </font>
										</c:if>
									</c:if> --%>
									
									<c:if test="${not empty param.error}">
										<script>
										$( window ).on( "load", function() {
										    	toastr.error('Please check your username and password.', 'Invalid Credentials', { "progressBar": true });
										    });
								    	</script>
									</c:if>
									
                                
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="ti-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control form-control-lg" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" name="j_username" id="j_username" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon2"><i class="ti-pencil"></i></span>
                                    </div>
                                    <input type="password" class="form-control form-control-lg" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" name="j_password" id="j_password"
											required>
                                </div>
                                <div class="form-group row">
                                    <div class="col-md-12">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="customCheck1" name="_spring_security_remember_me">
                                            <label class="custom-control-label" for="customCheck1">Remember me</label>
                                            <a href="javascript:void(0)" id="to-recover" class="text-dark float-right"><i class="fa fa-lock m-r-5"></i> Forgot password?</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <div class="col-xs-12 p-b-20">
                                        <button class="btn btn-block btn-lg btn-info" type="button" onclick="authenticate();">Log In</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">
                                        <div class="social">
                                          <!--   <a href="javascript:void(0)" class="btn  btn-facebook" data-toggle="tooltip" title="" data-original-title="Login with Facebook"> <i aria-hidden="true" class="fab  fa-facebook"></i> </a>
                                            <a href="javascript:void(0)" class="btn btn-googleplus" data-toggle="tooltip" title="" data-original-title="Login with Google"> <i aria-hidden="true" class="fab  fa-google-plus"></i> </a> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group m-b-0 m-t-10">
                                    <div class="col-sm-12 text-center">
                                        Don't have an account? <a href="${contextPath}/register" class="text-info m-l-5"><b>Sign Up</b></a>
                                    </div>
                                </div>
                            </form>
                            <hr/>
                            <div>
                            	<span class="m-2 p-2">By using our services, you agree to all <a href="${contextPath}/termsofUse" class="text-info">Terms of Use</a></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="recoverform">
                    <div class="logo">
                        <span class="db"><a href="${contextPath}"><img src="${pageContext.request.contextPath}/resources/img/logos/logo.png" width="15%" alt="logo" /></a></span>
                        <a href="${contextPath}/index.jsp"><span class="db"><h3><spring:eval expression="@propertyConfigurer.getProperty('application.name')" /></h3></span></a>
                        <h5 class="font-medium m-b-20">Recover Password</h5>
                        <span>Enter your Email and instructions will be sent to you!</span>
                    </div>
                    <div class="row m-t-20">
                        <!-- Form -->
                        <form class="col-12" action="${contextPath}/recoveryPage" method="POST" modelAttribute="userBean" name="UserForm" id="resetPaswordForm">
                            <!-- email -->
                            <div class="form-group row">
                                <div class="col-12">
                                    <input class="form-control form-control-lg" type="email" name="email" required="" placeholder="Email">
                                </div>
                            </div>
                            <!-- pwd -->
                            <div class="row m-t-20">
                                <div class="col-12">
                                    <button class="btn btn-block btn-lg btn-danger" type="submit" name="action">Reset</button>
                                </div>
                                <div class="col-12">
                                    <a href="${contextPath}/authenticate" class="text-dark float-right">Cancel</a>
                                </div>
                            </div>
                        </form>
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
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- ============================================================== -->
    <!-- This page plugin js -->
    <!-- ============================================================== -->
    
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
    
    <script src="${pageContext.request.contextPath}/resources/js/blinqlabs/security/authenticate.js"></script>
    
    
</body>

</html>