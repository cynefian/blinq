<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:landing>

	<jsp:body>	
  <div class="features-tabs-section" style="margin-top: 100px;">
    <div class="container">
      <div class="header">
        <h3>Accomplish more with React</h3>
        <p>Choose your favorite formats for your own sites</p>
      </div>

      <div class="tabs-wrapper">
        <ul class="nav nav-tabs justify-content-center" id="feature-tabs">
          <li>
            <a href="#home" class="active" data-toggle="tab">Upstart your development</a>
          </li>
          <li>
            <a href="#profile" data-toggle="tab">Get ready in half the time</a>
          </li>
          <li>
            <a href="#messages" data-toggle="tab">Collaborate with everyone</a>
          </li>
          <li>
            <a href="#settings" data-toggle="tab">Get more done faster</a>
          </li>
        </ul>

        <div class="tab-content">
          <div class="tab-pane fade show active" id="home">
            <div class="row">
              <div class="col-lg-6 order-lg-2 image">
                <img src="${pageContext.request.contextPath}/resources/images/portfolioitem1.png" class="img-fluid" alt="pic2" />
              </div>
              <div class="col-lg-6 order-lg-1 info">
                <h4>You don't need to have any advanced technical</h4>
                <p>
                  Whether you want to fill this paragraph with some text like I'm doing right now, this place is perfect to describe some features or anything you want - React has a complete solution for you.
                </p>
                <p>
                  You have complete control over the look & feel of your website, we offer the best quality so you take your site up and running in no time.
                </p>
              </div>
            </div>
          </div>
          <div class="tab-pane fade" id="profile">
            <div class="row">
              <div class="col-lg-6 image">
                <img src="${pageContext.request.contextPath}/resources/images/tabs/pic1.png" class="img-fluid" alt="pic1" />
              </div>
              <div class="col-lg-6 info">
                <h4>You don't need to have any advanced technical</h4>
                <p>
                  Whether you want to fill this paragraph with some text like I'm doing right now, this place is perfect to describe some features or anything you want - React has a complete solution for you.
                </p>
                <p>
                  You have complete control over the look & feel of your website, we offer the best quality so you take your site up and running in no time.
                </p>
              </div>
            </div>        
          </div>
          <div class="tab-pane fade" id="messages">
            <div class="row">
              <div class="col-lg-6 order-lg-2 image">
                <img src="${pageContext.request.contextPath}/resources/images/tabs/pic2.png" class="img-fluid" alt="pic3" />
              </div>
              <div class="col-lg-6 order-lg-1 info">
                <h4>You don't need to have any advanced technical</h4>
                <p>
                  Whether you want to fill this paragraph with some text like I'm doing right now, this place is perfect to describe some features or anything you want - React has a complete solution for you.
                </p>
                <p>
                  You have complete control over the look & feel of your website, we offer the best quality so you take your site up and running in no time.
                </p>
              </div>
            </div>
          </div>
          <div class="tab-pane fade" id="settings">
            <div class="row">
              <div class="col-lg-6 image">
                <img src="${pageContext.request.contextPath}/resources/images/tabs/pic1.png" class="img-fluid" alt="pic4" />
              </div>
              <div class="col-lg-6 info">
                <h4>You don't need to have any advanced technical</h4>
                <p>
                  Whether you want to fill this paragraph with some text like I'm doing right now, this place is perfect to describe some features or anything you want - React has a complete solution for you.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="features-section">
    <div class="container">
      <div class="header text-center">
        <h2>Need an easy way to customize your site?</h2>
        <p class="mt-3">React is perfect for novice developers and experts alike.</p>
      </div>
      <div class="feature">
        <div class="row">
          <div class="col-md-6">
            <div class="info">
              <h4 class="mt-lg-5">You don't need to have great technical experience to use your product.</h4>
              <p>
                Whether you want to fill this paragraph with some text like I'm doing right now, this place
                is perfect to describe some features or anything you want - React has a complete solution for you.
              </p>
            </div>
          </div>
          <div class="col-md-6 text-center">
            <img src="${pageContext.request.contextPath}/resources/images/feature4.png" class="img-fluid" alt="feature1" />
          </div>
        </div>
      </div>
      <div class="divider"></div>
      <div class="feature">
        <div class="row">
          <div class="col-md-6 order-md-2">
            <div class="info">
              <h4 class="mt-lg-4">A fully featured well design template that works.</h4>
              <p>
                You have complete control over the look & feel of your website, we offer the best quality so you
                take your site up and running in no time.
              </p>
              <p>
                Write some text here to explain the features of your site or application, it
                has lots of uses.
              </p>
            </div>
          </div>
          <div class="col-md-6 order-md-1 text-center">
            <img src="${pageContext.request.contextPath}/resources/images/feature2.png" class="img-fluid" alt="feature2" />
          </div>
        </div>
      </div>
      <div class="divider"></div>
      <div class="feature">
        <div class="row">
          <div class="col-md-6">
            <div class="info">
              <h4 class="mt-lg-5">You don't need to have great technical experience to use your product.</h4>
              <p>
                Whether you want to fill this paragraph with some text like I'm doing right now, this place
                is perfect to describe some features or anything you want - React has a complete solution for you.
              </p>
            </div>
          </div>
          <div class="col-md-6 text-center">
            <img src="${pageContext.request.contextPath}/resources/images/feature3.png" class="img-fluid" alt="feature1" />
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="features-grid-section">
    <div class="container">
      <div class="row">
        <div class="col-md-4 feature">
          <img src="${pageContext.request.contextPath}/resources/images/circle-icons/one-color/creditcard.png" alt="creditcard" />
          <strong>Act locally, work globally</strong>
          <p>You can work with international customers right out of the box while you can keep staying in your country.</p>
        </div>
        <div class="col-md-4 feature">
          <img src="${pageContext.request.contextPath}/resources/images/circle-icons/one-color/dev.png" alt="dev" />
          <strong>Act globally, work as usual</strong>
          <p>With some customization you can make this product apply to your branding guidelines and amaze your customers at the same time.</p>
        </div>
        <div class="col-md-4 feature">
          <img src="${pageContext.request.contextPath}/resources/images/circle-icons/one-color/globe.png" alt="globe" />
          <strong>Develop once, run everywhere</strong>
          <p>We don't impose any design restrictions, you are free to customize it as you see fit and it's easy to use.</p>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4 feature">
          <img src="${pageContext.request.contextPath}/resources/images/circle-icons/one-color/support.png" alt="support" />
          <strong>Act globally, work as usual</strong>
          <p>We're always happy to offer support if you happen to have any doubts about anything, if you need
            some new stuff contact us.</p>
        </div>
        <div class="col-md-4 feature">
          <img src="${pageContext.request.contextPath}/resources/images/circle-icons/one-color/mail.png" alt="mail" />
          <strong>Develop once, run everywhere</strong>
          <p>We'll be adding some new stuff to make it even more awesome, if you have any idea please let us know.</p>
        </div>
        <div class="col-md-4 feature">
          <img src="${pageContext.request.contextPath}/resources/images/circle-icons/one-color/locked.png" alt="locked" />
          <strong>Act locally, work globally</strong>
          <p>This thing will work on any device, it has a very easy to understand documentation and is made with SASS.</p>
        </div>
      </div>
    </div>
  </div>

  <div class="slider-section">
    <div class="container">
      <h3 class="header">Includes all pages that a complete theme should have</h3>

      <div class="slide-wrapper">
        <div class="slideshow">
          <div class="btn-nav prev"></div>
          <div class="btn-nav next"></div>
          <div class="slide active">
            <img src="${pageContext.request.contextPath}/resources/images/slider/slide3.png" alt="slide3" />
          </div>
          <div class="slide">
            <img src="${pageContext.request.contextPath}/resources/images/slider/slide4.png" alt="slide4" />
          </div>
          <div class="slide">
            <img src="${pageContext.request.contextPath}/resources/images/slider/slide1.png" alt="slide1" />
          </div>
          <div class="slide">
            <img src="${pageContext.request.contextPath}/resources/images/slider/slide5.png" alt="slide5" />
          </div>
          <div class="slide">
            <img src="${pageContext.request.contextPath}/resources/images/slider/slide2.png" alt="slide2" />
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="blog-showcase">
    <div class="container">
      <div class="header">
        <h3>Showcasing some blog posts</h3>
      </div>
      <div class="posts">
        <div class="row">
          <div class="col-md-4">
            <a class="pic" href="blogpost.html">
              <img src="${pageContext.request.contextPath}/resources/images/blog1.png" class="img-fluid" alt="blog1" />
              <div class="bg">
                <p>How to get your first 100 users</p>
              </div>
            </a>
          </div>
          <div class="col-md-4">
            <a class="pic" href="blogpost.html">
              <img src="${pageContext.request.contextPath}/resources/images/blog2.png" class="img-fluid" alt="blog2" />
              <div class="bg">
                <p>How to develop scalable Rails apps</p>
              </div>
            </a>
          </div>
          <div class="col-md-4">
            <a class="pic" href="blogpost.html">
              <img src="${pageContext.request.contextPath}/resources/images/blog3.png" class="img-fluid" alt="blog3" />
              <div class="bg">
                <p>Enjoy your life at the beach</p>
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>

</jsp:body>
</page:landing> 