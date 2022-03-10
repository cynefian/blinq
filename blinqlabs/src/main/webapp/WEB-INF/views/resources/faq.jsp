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
		
	   <section class="service-section">
            <div class="container">
            

                <div class="row">
                    
                    <div class="col-lg-6 col-md-12">
                         <div class="header-text sm-width-75 xs-width-100 sm-padding-30px-bottom">
                        	 <h1 class="line-height-55 md-line-height-50 xs-line-height-40 wow fadeInUp text-theme-color xs-font-size30" data-wow-delay=".1s">Frequently Asked Questions</h1>
                         </div>
                     </div>
                    
                    <div class="col-lg-6 col-md-12 sm-text-center sm-margin-30px-bottom">
                        <img src="${pageContext.request.contextPath}/resources/img/illustration/faq.png" alt="" class="img-effect" />
                    </div>
                </div>
           </div>
       </section>
       
       
       <section>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12 order-2 order-lg-1">
                        <div class="padding-30px-right sm-no-padding">
                            <div id="accordion" class="accordion-style">
                            
                                <c:if test="${empty faqlist}">
									<div>
										<h4>There aren't any FAQs published yet. </h4>
									</div>
								</c:if>
								
								<c:if test="${not empty faqlist}">
									<c:forEach var="entry" items="${faqlist}" varStatus="loop">
										<div class="card">
			                                    <div class="card-header" id="heading${loop.index}">
			                                        <div class="mb-0">
			                                            <h5 class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse${loop.index}" aria-expanded="false" aria-controls="collapse${loop.index}">
			                                            	<span class="badge badge-outline badge-pill badge-primary">${loop.index + 1}</span>
				                                        	<span class="item-title">${entry.TX_QUESTION}</span>
			                                    		</h5>
			                                        </div>
			                                    </div>
			                                    <div id="collapse${loop.index}" class="collapse" aria-labelledby="heading${loop.index}" data-parent="#accordion">
			                                        <div class="card-body">
			                                            ${entry.TX_ANSWER}
			                                        </div>
			                                    </div>
			                                </div>
			                                
			                            </c:forEach>
			                        </c:if>
                            </div>
                        </div>
                    </div>
                  
                </div>
            </div>
        </section>
        
        
		
  	<section>
  		<div>
  			<br/>
  		</div>
  	</section>
  
  

	</jsp:body>
</page:platform> 