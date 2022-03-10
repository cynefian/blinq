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
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><spring:eval
		expression="@propertyConfigurer.getProperty('application.name')" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="shortcut icon"
	href="${contextPath}/resources/images/favicon.ico">

<link
	href="${contextPath}/resources/scss/bootstrap/bootstrap.css"
	rel="stylesheet" />
<link
	href="${contextPath}/resources/dist/theme.min.css"
	rel="stylesheet" />

<!-- javascript -->
<script
	src="${pageContext.request.contextPath}/resources/dist/theme.min.js"></script>

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body class="signup-rotate">
	<div class="container">
		<div class="header">
			<a href="${contextPath}/index.jsp" class="logo"> <img
				src="${pageContext.request.contextPath}/resources/images/logo-dark.png"
				class="mr-2 mb-1 img-fluid" /> <spring:eval
					expression="@propertyConfigurer.getProperty('application.name')" />
			</a>

		</div>
		<div class="rotate-container">
		
		<div class="change-view active">
						Don't have an account? <a href="#" data-toggle="popover"
							data-placement="bottom" data-content="Click here!"
							data-trigger="manual">Create one here</a>
					</div>
					<div class="change-view">
						Already have an account? <a href="#">Sign in here</a>
					</div>
					
					
			<div class="wrappers">
			
			<!-- Sign in Section -->
				<div class="wrapper front clearfix">
					<div class="formy">
						<div class="row">
							<div class="col-md-12">
								<form name="loginForm" role="form"
									action="j_spring_security_check" method="POST">
									<security:authorize
										access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN','ROLE_SUPER','ROLE_USER')"
										var="isUSer" />

									<c:if test="${not isUSer}">
										<c:if test="${empty param.error}">
											<font size="2" color="red"> You are not logged in </font>
										</c:if>
									</c:if>



									<c:if test="${isUSer}">
										<font size="2" color="green"> You are logged in as <security:authentication
												property="principal.username" /> with the role of: <b><security:authentication
													property="principal.authorities" /></b>
										</font>
										<a href="${contextPath}/j_spring_security_logout">Logout</a>
									</c:if>

									<br />
									<c:if test="${not empty param.error}">
										<font size="2" color="red"><b>Either your username
												or password is wrong</b></font>
									</c:if>

									<h4>Log in to your account</h4>
									<div class="form-group">
										<label for="email2">Email address</label> <input type="email"
											class="form-control" id="loginid" name="j_username" required />
									</div>
									<div class="form-group">
										<label for="password2">Password</label> <input type="password"
											class="form-control" id="loginpassword" name="j_password"
											required />
									</div>
									<div class="checkbox">
										<label> <input type="checkbox" id="rememberme"
											name="_spring_security_remember_me"> Remember me
										</label>
									</div>
									<div class="text-center mt-4">
										<input type="submit" id="loginBtn"
											class="submit btn-block btn-shadow btn-shadow-info"
											value="Sign in">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Registration Section -->

				<c:url value="/registerForm" var="registerForm" />
				<c:url value="/registerForm/passwordValidator"
					var="registerFormPasswordValidator" />

				<div class="wrapper back clearfix">
					<div class="formy">
						<div class="row">
							<div class="col-md-12">
								<form role="form" action="${registerForm}"
									modelAttribute="registerBean" method="POST">
									<div class="form-group">
										<label for="name">First name</label> <input type="text"
											class="form-control" id="firstname" name="firstname"
											path="username" /> 
									</div>
									<div class="form-group">
										<label for="name">Last name</label> <input type="text"
											class="form-control" id="lastname" name="lastname"
											path="username" /> 
									</div>
									<div class="form-group">
										<label for="email">Email address</label> <input type="email"
											class="form-control" id="email" name="email" path="email" />
									</div>
									<div class="form-group">
										<label for="password">Password</label> <input type="password"
											class="form-control" id="password1" name="password1"
											path="password" /> 
									</div>
									<div class="form-group">
										<label for="password">Confirm Password</label> <input type="password"
											class="form-control" id="password2" name="password2"
											path="password" /> 
									</div>
									<div class="checkbox">
										<label> <input type="checkbox" name="terms">
											You have read & agree to the <a href="#">Terms of service</a>.
										</label>
									</div>
									<div class="text-center mt-4">
										<input type="submit" id="registerBtn"
											class="submit btn-block btn-shadow btn-shadow-info"
											value="Create account">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			
					
				
		</div>
		
		<%-- <div>
			<div class="alert alert-success">
				<strong>${successmessage}</strong>
			</div>
			
			<div class="alert alert-failure">
				<strong>${failuremessage}</strong>
			</div>
		</div> --%>
	</div>

	<script type="text/javascript">
		$(function() {
			// this makes the forms rotate back and forth using the class .flipped
			var $btns = $(".change-view");
			var $wrappers = $(".wrappers");
			$btns.click(function(e) {
				e.preventDefault();

				$wrappers.toggleClass("flipped");
				$btns.removeClass("active");

				if ($wrappers.hasClass("flipped")) {
					$btns.eq(1).addClass("active");

					setTimeout(function() {
						$wrappers.addClass("flipped-ended");
					}, 350);
				} else {
					$btns.eq(0).addClass("active");

					setTimeout(function() {
						$wrappers.removeClass("flipped-ended");
					}, 350);
				}
			});

			// initializing popover for demonstration only, you can remove it!
			$(".change-view a").popover();
			$(".change-view a").popover('show');
		});
	</script>
</body>
</html>