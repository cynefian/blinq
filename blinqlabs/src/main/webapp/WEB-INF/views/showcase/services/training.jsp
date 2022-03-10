<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<span class="text-primary lead">Blinqlabs develops and
				conducts training courses relevant to real life scenarios
				encountered within a typical work environment. We provide
				specialized training courses that reach across the entire DevOps and
				Cloud spectrum.</span>
		</div>
	</div>
</div>

<div class="m-4 p-2">

	<div class="row">
		<div class="col-md-6">
			<img class="card-img img-fluid" src="${pageContext.request.contextPath}/resources/img/illustration/undraw_teaching_f1cm.svg">
		</div>
		<div class="col-md-6">
			<div class="row m-4 p-2">
				<div class="col-md-3">
					<img class="card-img img-fluid"
						src="${pageContext.request.contextPath}/resources/img/icons/training-onsite.svg"
						style="width: 50%">
				</div>
				<div class="col-md-9">
					<h6>Online, Webinar</h6>
					<span class="lead">Train in the comfort of your own
						environment. Addteq has the ability to conduct training via
						webinars. These courses range from a few hours to a full day
						training session.
					</span>
				</div>
			</div>
			<div class="row m-4 p-2">
				<div class="col-md-3">
					<img class="card-img img-fluid"
						src="${pageContext.request.contextPath}/resources/img/icons/training-online.svg"
						style="width: 50%">
				</div>
				<div class="col-md-9">
					<h6>On-site, Classroom Training</h6>
					<span class="lead">We offer on-site training courses,
						complete with hands-on labs.
					</span>
				</div>
			</div>
			<div class="row m-4 p-2">
				<div class="col-md-3">
					<img class="card-img img-fluid"
						src="${pageContext.request.contextPath}/resources/img/icons/training-devops.svg"
						style="width: 50%">
				</div>
				<div class="col-md-9">
					<h6>DevOps Training</h6>
					<span class="lead">DevOps Training is offered both on-site
						and online. DevOps Training is specifically designed with the
						client's environment in mind. It is fully customizable and
						tailored to each client's needs.
					</span>
				</div>
			</div>
			
				<div class="row m-4 p-2">
				<div class="col-md-3">
					<img class="card-img img-fluid"
						src="${pageContext.request.contextPath}/resources/img/icons/agile.png"
						style="width: 50%">
				</div>
				<div class="col-md-9">
					<h6>Scrum and Agile Training</h6>
					<span class="lead">Agile Training is offered both on-site
						and online. Agile Training is designed to allow the attendees gain Agile experience so as to work in a fast paced, Agile environment. 
					</span>
				</div>
			</div>
			
		</div>
	</div>
</div>
