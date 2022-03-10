<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" tagdir="/WEB-INF/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<!-- start header section -->
<header
	class="position-absolute width-100 transparent-header sm-bg-theme-90 sm-position-relative">

	<div class="navbar-default">

		<!-- start top search -->
		<div class="top-search bg-black-opacity-light">
			<div class="container lg-container">
				<form class="search-form" action="search.html" method="GET"
					accept-charset="utf-8">
					<div class="input-group">
						<span class="input-group-addon cursor-pointer">
							<button
								class="search-form_submit fas fa-search font-size18 text-white"
								type="submit"></button>
						</span> <input type="text" class="search-form_input form-control"
							name="s" autocomplete="off" placeholder="Type & hit enter...">
						<span class="input-group-addon close-search"><i
							class="fas fa-times font-size18 margin-5px-top"></i></span>
					</div>
				</form>
			</div>
		</div>
		<!-- end top search -->

		<div class="container lg-container">
			<div class="row align-items-center">
				<div class="col-12 col-lg-12">
					<div class="menu_area alt-font">
						<nav id="navbar" class="navbar navbar-expand-lg navbar-light no-padding">

							<div class="navbar-header navbar-header-custom">
								<!-- start logo -->
								<a href="${contextPath}/index.jsp" class="navbar-brand inner-logo"><img
									id="logo"
									src="${pageContext.request.contextPath}/resources/img/logos/white-logo-only.png"
									alt="logo"></a>
								<!-- end logo -->
							</div>

							<div class="navbar-toggler"></div>

							<jsp:include page="/WEB-INF/shared/nav-menu.jsp" />
						
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>


</header>
<!-- end header section -->

