<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
	
	   <!-- start main banner area -->
        <div class="animated-banner-area header-secondary bg-theme-90">
	  <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
	
	
	<section class="bg-theme-light">
		<div class="container">
			<div class="paper">
			
				<div class="text-center">
					<h5>Cookie Policy</h5>
				</div>
				<div class="mt-3">
					<div >
						<h6>Information about our use of Cookies:</h6>
					</div>
					<div  class="mt-4 mb-4">
						Our site uses cookies to distinguish you from other users of our site. 
						This helps us to provide you with a good experience when you browse our site and also 
						allows us to improve our site. By continuing to browse the site, you are agreeing to our use of cookies.
					</div>
					
					<div  class="mt-4 mb-4">
						A cookie is a small file of letters and numbers that we store on your browser or the hard drive of your 
						computer if you agree. Cookies contain information that is transferred to your computer's hard drive.
					</div>
				</div>
				
				<div class="mt-3">
					<div >
						<h6>We use the following Cookies:</h6>
					</div>
					<div  class="mt-4 mb-4">
						<b>Strictly Necessary Cookies.</b> <br/>
						These cookies are necessary for the website to function and cannot be switched off in our systems. 
						They are usually only set in response to actions made by you which amount to a request for services, 
						such as setting your privacy preferences, logging in or filling in forms.

						You can set your browser to block or alert you about these cookies, but some parts of the site will 
						not then work. These cookies do not store any personally identifiable information.
					</div>
					<div  class="mt-4 mb-4">
						<b>Analytical/Performance cookies.</b> <br/>
						They allow us to recognize and count the number of visitors and to see how visitors move around our 
						website when they are using it. This helps us to improve the way our website works, for example, 
						by ensuring that users are finding what they are looking for easily.
					</div>
					
					<div  class="mt-4 mb-4">
						<b>Functionality cookies.</b> <br/>
						 These are used in connection with certain functionality on our website. They enable us to 
						 determine if certain functionality on our website will work with your browser, for example.
					</div>
					
					<div  class="mt-4 mb-4">
						<b>Targeting cookies.</b> <br/>
						 These cookies record your visit to our website, the pages you have visited and the links you have followed. 
						 We will use this information to make our website and our advertisements on other websites more 
						 relevant to your interests. We may also share this information with third parties for this purpose.
					</div>
				</div>
				
				<div class="mt-3">
					<div >
						<h6>Third Party Cookies:</h6>
					</div>
					<div  class="mt-4 mb-4">
						Please note that third parties (including, for example, advertising networks and providers of 
						external services like web traffic analysis services) may also use cookies, over which we have no control. 
						These cookies are likely to be analytical/performance cookies or targeting cookies.
					</div>
				</div>
				
				<div class="mt-3">
					<div >
						<h6>Browser Settings for Cookies:</h6>
					</div>
					<div  class="mt-4 mb-4">
						You can block cookies by activating the setting on your browser that allows you to refuse the setting of all 
						or some cookies. However, if you use your browser settings to block all cookies (including essential cookies) 
						you may not be able to access all or parts of our site.
					</div>
				</div>
				
				
			</div>
		</div>
	</section>
  	
  	<section class="bg-theme-light">
  		<div>
  			<br/>
  		</div>
  	</section>
  
 <
</jsp:body>
</page:platform> 