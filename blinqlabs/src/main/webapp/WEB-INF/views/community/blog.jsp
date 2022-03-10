<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:landing>

	<jsp:body>	
 <div class="blog-posts">
    <div class="container">
      <div class="row">
        <div class="col-lg-9">
          <div class="post">
            <a href="blogpost.html" class="pic">
              <img src="images/blogpost1.png" class="img-fluid" alt="blogpost" />
            </a>

            <div class="title">
              <a href="blogpost.html">A brief history of climate science</a>
            </div>
            <div class="author">
              <img src="images/testimonials/testimonial2.jpg" class="avatar" alt="author" />
              Jessica Smith, October 03, 2013
            </div>
            <p class="intro">
              By Ed Hawkins, University of Reading. Climate change is often seen as a recent phenomenon, but its roots are actually far older — the stuff that is currently happening is beyond my understanding.
              <br />
              Some people say design is about solving problems. Obviously designers solve problems but so do dentists. Design is about cultural invention.
            </p>
            <a href="blogpost.html" class="continue-reading">Continue reading this post</a>
          </div>

          <div class="post">
            <div class="video">
              <div class="iframe-wrapper">
                <iframe src="https://player.vimeo.com/video/22439234" width="620" height="350" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
              </div>
            </div>

            <div class="title">
              <a href="blogpost.html">Discover what is beyond our Solar System</a>
            </div>
            <div class="author">
              <img src="images/testimonials/testimonial2.jpg" class="avatar" alt="author" />
              Eric Hoffman, October 11, 2013
            </div>
            <p class="intro">
              By Ed Hawkins, University of Reading. Climate change is often seen as a recent phenomenon, but its roots are actually far older — the stuff that is currently happening is beyond my understanding.
              <br />
              Some people say design is about solving problems. Obviously designers solve problems but so do dentists. Design is about cultural invention.
            </p>
            <a href="blogpost.html" class="continue-reading">Continue reading this post</a>
          </div>

          <div class="post">
            <a href="blogpost.html" class="pic">
              <img src="images/blogpost2.png" class="img-fluid" alt="blogpost" />
            </a>

            <div class="title">
              <a href="blogpost.html">How to get really good at programming</a>
            </div>
            <div class="author">
              <img src="images/testimonials/testimonial4.jpg" class="avatar" alt="author" />
              Amanda Johnson, September 23, 2013
            </div>
            <p class="intro">
              By Ed Hawkins, University of Reading. Climate change is often seen as a recent phenomenon, but its roots are actually far older — the stuff that is currently happening is beyond my understanding.
            </p>
            <a href="blogpost.html" class="continue-reading">Continue reading this post</a>
          </div>

          <div class="post">
            <a href="blogpost.html" class="pic">
              <img src="images/blogpost3.png" class="img-fluid" alt="blogpost" />
            </a>

            <div class="title">
              <a href="blogpost.html">Get healthy riding a bicycle everyday</a>
            </div>
            <div class="author">
              <img src="images/testimonials/testimonial3.jpg" class="avatar" alt="author" />
              Benjamin Andrews, August 16, 2013
            </div>
            <p class="intro">
              By Ed Hawkins, University of Reading. Climate change is often seen as a recent phenomenon, but its roots are actually far older — the stuff that is currently happening is beyond my understanding.
              <br />
              Some people say design is about solving problems. Obviously designers solve problems but so do dentists. Design is about cultural invention.
            </p>
            <a href="blogpost.html" class="continue-reading">Continue reading this post</a>
          </div>

          <div class="pages">
            <ul class="pagination">
              <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
            </ul>
          </div>
        </div>
        <div class="col-lg-3 sidebar">
          <div class="search">
            <form>
              <span class="icomoon-search"></span>
              <input type="text" name="q" placeholder="Search on blog..." />
            </form>
          </div>
          <div class="updates">
            <strong>
              Free blog updates
              <i class="fa fa-rss"></i>
            </strong>
            <p>
              Never miss an update. 
              Sign up  to receieve an email whenever we post something in the blog.
            </p>
          </div>
          <div class="follow-tw">
            <img src="images/twitterfollow.png" alt="follow-tw" />
          </div>
          <div class="best-hits">
            <strong>Check out our best hits:</strong>
            <a href="#">How to start a business</a>
            <a href="#">How to sell online</a>
            <a href="#">Climate change when needed</a>
            <a href="#">Web development upstart</a>
            <a href="#">Learn Rails in 30 days</a>
          </div>
        </div>
      </div>
    </div>
  </div> 
 


</jsp:body>
</page:landing> 