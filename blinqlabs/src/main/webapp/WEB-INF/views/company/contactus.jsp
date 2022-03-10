<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
   
   <div class="main-wrapper">
   		<c:if test="${not empty failuremessage}">
			 	<script>
			 	 	$(window).on("load", function() {
				    	toastr.error('${failuremessage}', 'Feature', { "progressBar": true });
				    });
			  	</script>
			</c:if>
			
			<c:if test="${not empty successmessage}">
			 	<script>
			 	 	$(window).on("load", function() {
				    	toastr.success('${successmessage}', 'Feature', { "progressBar": true });
				    });
			  	</script>
			</c:if>
   </div>
 <!-- start main banner area -->
        <div class="animated-banner-area header-secondary bg-theme-90">
	  <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
		
		
		
		 <!-- start contact info section -->
        <section class="bg-light-gray">
            <div class="container">

                <div class="row">
                    <div class="col-lg-4 col-md-12 sm-margin-20px-bottom">
                        <div class="services-block padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin wow fadeInUp">
                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
                                <i class="fas fa-map-marker-alt text-theme-color"></i>
                                <div class="box-circle-large"></div>
                                <div class="box-circle-small"></div>
                            </div>
                            <div class="text-extra-dark-gray text-uppercase text-small font-weight-600 alt-font margin-5px-bottom">Visit Our Office</div>
                            <p class="center-col">37500 Carson Street
                                <br>Farmington HIlls, MI - 48331.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12 sm-margin-20px-bottom wow fadeInUp" data-wow-delay="0.2s">
                        <div class="services-block padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin">
                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
                                <i class="fas fa-comments text-theme-color"></i>
                                <div class="box-circle-large"></div>
                                <div class="box-circle-small"></div>
                            </div>
                            <div class="text-extra-dark-gray text-uppercase text-small font-weight-600 alt-font margin-5px-bottom">Let's Talk</div>
                            <p class="center-col">Phone: (+1) 862 234 0431
                               </p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="services-block padding-45px-tb padding-25px-lr sm-padding-35px-tb sm-padding-20px-lr xs-padding-30px-tb xs-padding-15px-lr last-paragraph-no-margin">
                            <div class="title-box margin-25px-bottom sm-margin-15px-bottom">
                                <i class="fas fa-envelope text-theme-color"></i>
                                <div class="box-circle-large"></div>
                                <div class="box-circle-small"></div>
                            </div>
                            <div class="text-extra-dark-gray text-uppercase text-small font-weight-600 alt-font margin-5px-bottom">E-mail Us</div>
                            <p class="center-col"><a href="javascript:void(0)">info@blinqlabs.com</a>
                        </div>
                    </div>
                    <!-- end -->
                </div>

            </div>
        </section>
        <!-- end contact info section -->
        
         <!-- start map section -->
        <div id="map"></div>
        <!-- end map section -->
        

        <!-- start contact form -->
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-12 wow fadeIn">

                        <div class="section-heading">
                            <h3>Get in Touch</h3>
                            <p class="width-55 sm-width-75 xs-width-95">We are available 24/7 by e-mail. You can also ask a question about our services through our contact form.</p>
                        </div>

                        <form action="${contextPath}/ContactUs/sendMessage" method="POST" modelAttribute="ContactUsMessageBean" id = "ContactUsMessageForm" name="ContactUsMessageForm">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-wrap">
                                        <input class="form-input" type="text" ID="TX_NAME" name="TX_NAME" placeholder="Your Full Name:" data-constraints="@Required">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-wrap">
                                        <input class="form-input" type="text" ID="TX_PHONE" name="TX_PHONE" placeholder="Phone Number:">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-wrap">
                                        <input class="form-input" type="text" ID="TX_EMAIL" name="TX_EMAIL" placeholder="Email:" data-constraints="@Required @Email">

                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-wrap">
                                        <textarea class="form-input" ID="TX_MESSAGE"  name="TX_MESSAGE" rows="7" placeholder="Message:" data-constraints="@Required"></textarea>
                                    </div>
                                </div>

                                <div class="col-md-12 margin-15px-top text-center">
                                    <button type="submit" class="butn style-one fill">Submit Comment</button>
                                    <div class="snackbars" id="form-output-global"></div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </section>
        <!-- end contact form -->

       
		
		
		
  	<section>
  		<div>
  			<br/>
  		</div>
  	</section>
  
</jsp:body>
</page:platform> 