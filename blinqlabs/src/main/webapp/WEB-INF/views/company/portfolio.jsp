<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:landing>

	<jsp:body>	
  <div class="portfolio-showcase">
    <div class="container">
      <div class="header">
        <h3>Our Portfolio</h3>
        <p>The best selection of our clients and projects we've worked on.</p>
      </div>
      <div class="project">
        <h3>iOS Video Application</h3>
        <div class="screen">
          <img src="images/portfolio1.png" class="img-fluid" alt="portfolio1" />
        </div>
        <p class="description">
          At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.
        </p>
        <div class="divider"></div>
        <div class="visit">
          <a class="btn-shadow btn-shadow-primary" href="portfolio-item.html">
            Visit Project Website
          </a>
        </div>
      </div>
      <div class="project">
        <h3>Designed with the whole company in mind</h3>
        <div class="screen">
          <img src="images/portfolio2.png" class="img-fluid" alt="portfolio2" />
        </div>
        <p class="description">
          At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.
        </p>
        <div class="divider"></div>
        <div class="visit">
          <a class="btn-shadow btn-shadow-primary" href="portfolio-item.html">
            Visit Project Website
          </a>
        </div>
      </div>
      <div class="project">
        <h3>Complete device setup development</h3>
        <div class="screen">
          <img src="images/portfolio3.png" class="img-fluid" alt="portfolio3" />
        </div>
        <p class="description">
          At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.
        </p>
        <div class="divider"></div>
        <div class="visit">
          <a class="btn-shadow btn-shadow-primary" href="portfolio-item.html">
            Visit Project Website
          </a>
        </div>
      </div>
    </div>
  </div>


</jsp:body>
</page:landing> 