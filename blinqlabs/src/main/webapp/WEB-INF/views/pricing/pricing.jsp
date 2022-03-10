<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
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
                <img src="${pageContext.request.contextPath}/resources/img/banner/banner-shape-wave.png" class="img-fluid width-100" alt="">
            </div>
            <!-- end shape area -->
		</div>
		
		
   <!-- start how it work section -->
        <section>
            <div class="container">
                <div class="section-heading2">
                    <h3>Blinqlabs provides different pricing models for your unique needs</h3>
                </div>
            </div>
            <div class="container-fluid no-padding sm-padding-15px-lr">
                <div class="horizontaltab tab-style">
                    <ul class="resp-tabs-list hor_1">
                        <li><i class="fas fa-infinity"></i><span
							class="display-block sm-display-inline-block sm-vertical-align-top">Saas</span></li>
                        <li><i class="fas fa-headset"></i><span
							class="display-block sm-display-inline-block sm-vertical-align-top">Technical Support</span></li>
                    </ul>
                    
                    <div class="resp-tabs-container hor_1">
                        <div>
                        	<jsp:include
								page="/WEB-INF/views/showcase/pricing/saas.jsp" />
                        </div>
                      
                        <div>
                            <jsp:include
								page="/WEB-INF/views/showcase/pricing/techsupport.jsp" />
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