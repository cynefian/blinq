<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
	
	 <script>
			$(window).on('load', function() {
				activateTab();
			});

			function activateTab() {
				if ($(".horizontaltab").length !== 0) {
					$('.horizontaltab').easyResponsiveTabs({
						type : 'default', //Types: default, vertical, accordion
						width : 'auto', //auto or any width like 600px
						fit : true, // 100% fit in a container
						tabidentify : 'hor_1', // The tab groups identifier
						activate : function(event) { // Callback function if tab is switched
							var $tab = $(this);
							var $info = $('#nested-tabInfo');
							var $name = $('span', $info);
							$name.text($tab.text());
							$info.show();
						}
					});
				}
			}
		</script>
	
	  
	    <!-- start main banner area -->
        <div class="animated-banner-area header-secondary bg-theme-90">
	  <!-- start shape area -->
            <div class="header-shape sm-display-none">
                <img
					src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png"
					class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
	
 

        <!-- start how it work section -->
        <section>
            <div class="container">
                <div class="section-heading2">
                    <h3>Our Services</h3>
                </div>
            </div>
            <div class="container-fluid no-padding sm-padding-15px-lr">
                <div class="horizontaltab tab-style">
                    <ul class="resp-tabs-list hor_1">
                        <li><i class="fas fa-infinity"></i><span
							class="display-block sm-display-inline-block sm-vertical-align-top">DevOps</span></li>
                        <li><i class="fas fa-cloud"></i><span
							class="display-block sm-display-inline-block sm-vertical-align-top">Cloud</span></li>
                        <li><i class="fas fa-project-diagram"></i><span
							class="display-block sm-display-inline-block sm-vertical-align-top sm-width-50">Workflows & Automation</span></li>
                        <li><i class="fas fa-window-restore"></i><span
							class="display-block sm-display-inline-block sm-vertical-align-top">Custom App Development</span></li>
                        <li><i
							class="fas fa-chalkboard-teacher text-theme-color"></i><span
							class="display-block sm-display-inline-block sm-vertical-align-top">Training</span></li>
                    </ul>
                    
                    <div class="resp-tabs-container hor_1">
                        <div>
                        	<jsp:include
								page="/WEB-INF/views/showcase/services/devops.jsp" />
                        </div>
                      
                        <div>
                            <jsp:include
								page="/WEB-INF/views/showcase/services/cloud.jsp" />
                        </div>
                        
                        <div>
                             <jsp:include
								page="/WEB-INF/views/showcase/services/workflow.jsp" />
                        </div>
                         <div>
                             <jsp:include
								page="/WEB-INF/views/showcase/services/appdev.jsp" />
                        </div>
                         <div>
                             <jsp:include
								page="/WEB-INF/views/showcase/services/training.jsp" />
                        </div>
                    </div>
                    
                </div>
                
               
                
            </div>
        </section>
        <!-- end how it work section -->
  
   <!-- start contact section -->
        <section class="parallax cover-background theme-overlay"
			data-overlay-dark="9" data-background="img/banner/bg1.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-md-9 text-center center-col">
                        <h4 class="text-white sm-margin-10px-bottom">Haven't found what you are looking for? Feel free to contact us.</h4>
                        <p
							class="text-white font-size16 xs-font-size14 margin-30px-bottom sm-margin-20px-bottom">We always provide you our best services.</p>
                        <a href="javascript:void(0)" class="butn white"><span>Contact Us</span></a>
                    </div>

                </div>
            </div>

        </section>
        <!-- end contact section -->
        
        
        
        <section>
            <div >
                <div class="row m-4 p-4">
                    <div class="col-md-9 text-center center-col">
                        <h4 class="sm-margin-10px-bottom">Technical Support</h4>
                    </div>
                </div>
                
                <div class="m-4 p-4">
            	     <div class="section-heading3 wow fadeInDown"
							data-wow-delay=".2s">
	                  
	                    <p
								class="width-55 sm-width-75 xs-width-95 center-col text-theme-color">Focus on what makes you different, focus on your products, leave the DevOps infrastructure to us,  make it timeless.
	                        <br /> <span class="small-title">View the available pricing models on our <a href="${contextPath}/sales"><span  class="text-primary">Pricing page</span></a></span>
							</p>
	                </div>
				</div>
                
          </div>
     </section>
        
  	<section>
  		<div>
  			<br />
  		</div>
  	</section>
  
 
  

</jsp:body>
</page:platform>
