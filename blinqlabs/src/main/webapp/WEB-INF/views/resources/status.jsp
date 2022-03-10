<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:landing>

	<jsp:body>	
   <div class="status-page">
    <div class="container">
      <div class="header">
        <h3>React System Status</h3>
        <p>Current status and incident report</p>
      </div>

      <div class="current-status">
        <span class="updated">
          Updated 2 minutes ago
        </span>
        <div class="status">
          <div class="color green"></div>
          All sistems operational
        </div>
        <div class="help">
          Need help? <a href="${contextPath}/contactus">Contact us</a>.
        </div>
      </div>
      <ul class="list-group modules">
        <li class="list-group-item">
          Web API
          <div class="status">Operational</div>
        </li>
        <li class="list-group-item">
          Notifications
          <div class="status down">Down</div>
        </li>
        <li class="list-group-item">
          Application Monitoring
          <div class="status">Operational</div>
        </li>
        <li class="list-group-item">
          Browser Plugin
          <div class="status">Operational</div>
        </li>
      </ul>
      <div class="messages">
        <h3>Messages</h3>
        <div class="date">
          <div class="day">Today</div>
          <p class="ok">
            All systems operational
          </p>
        </div>
        <div class="date">
          <div class="day">Yesterday, October 25 2013</div>
          <p class="ok">
            All systems operational
          </p>
        </div>
        <div class="date">
          <div class="day">October 04 2013</div>
          <p class="ok">
            All systems operational
          </p>
        </div>
        <div class="date">
          <div class="day">October 03 2013</div>
          <p class="ok">
            All systems operational
          </p>
        </div>
        <div class="date">
          <div class="day">October 02 2013</div>
          <p class="issues">
            Application deployment issues
          </p>
          <p class="update">
            <strong>Resolved:</strong>
            We fixed the issues monitoring status, downtime was between 1pm and 5pm PT.
          </p>
          <p class="update">
            <strong>Investigating:</strong>
            We identified some issues deploying some applications and monitoring their status.
          </p>
        </div>
      </div>
      <div class="full-history">
        <a href="#">
          ← See full incident history
        </a>
      </div>
    </div>
  </div>


</jsp:body>
</page:landing> 