<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:platform>

	<jsp:body>	
	
	 <script>

	 $(window).on('load', function() {
 			 activateTab();
 			});
	 
	 function activateTab(){
		 if ($(".horizontaltab").length !== 0) {
             $('.horizontaltab').easyResponsiveTabs({
                 type: 'default', //Types: default, vertical, accordion
                 width: 'auto', //auto or any width like 600px
                 fit: true, // 100% fit in a container
                 tabidentify: 'hor_1', // The tab groups identifier
                 activate: function(event) { // Callback function if tab is switched
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
	
  
  <!-- start service section -->
        <section class="service-section">
            <div class="container">
                <div class="section-heading2">
                    <span class="alt-font">Exclusive Services</span>
                    <h2>We Provide Awesome Services</h2>
                    <p>Purpose-fit guidance and support for your enterprise needs</p>
                </div>

                <div class="row">
                    <div class="col-lg-6 col-md-12 sm-text-center sm-margin-30px-bottom">
                        <img src="${pageContext.request.contextPath}/resources/img/blog/blog-7.jpg" alt="" class="img-effect" />
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="padding-50px-left md-padding-30px-left sm-no-padding">

                            <div class="margin-25px-bottom separator-dashed-line-horrizontal-full hover-icon">
                                <h4 class="font-size18 sm-font-size16 xs-font-size15 margin-10px-bottom text-dark-gray"><span class="service-icon"><i class="fas fa-clone"></i></span>Accelerating and Transforming your SDLC</h4>
                                <p>We provide world class products and services for your SDLC through industry established practices focusing on speed without compromise to quality with enterprise-grade security, scalability & reliability.</p>
                            </div>
                            <div class="margin-25px-bottom separator-dashed-line-horrizontal-full hover-icon">
                                <h4 class="font-size18 sm-font-size16 xs-font-size15 margin-10px-bottom text-dark-gray"><span class="service-icon"><i class="fab fa-delicious"></i></span>The DevOps Simulation Workshop</h4>
                                <p>Develop a shared understanding of what DevOps means to your company, and recognize where the gaps are in how you operate today.</p>
                            </div>
                            <div class="margin-30px-bottom hover-icon">
                                <h4 class="font-size18 sm-font-size16 xs-font-size15 margin-10px-bottom text-dark-gray"><span class="service-icon"><i class="fas fa-chart-pie"></i></span>Modernization starts here</h4>
                                <p>Strategic guidance to transform the way your teams operate, from adopting DevOps practices to planning for Agile at scale. With quick and detailed answers for your unique situations, we have approaches to help with all your transformation goals. </p>
                            </div>

                            <a href="javascript:void(0)" class="butn small">Learn more</a>

                        </div>

                    </div>

                </div>

            </div>
        </section>
        <!-- end service section -->

        <!-- start innovate business section -->
        <section class="bg-theme-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 col-md-12 order-2 order-lg-1">
                        <div class="padding-30px-right sm-no-padding">
                            <h4 class="sm-margin-lr-auto sm-text-center xs-width-100">It's all about agility, reliability and reducing the total cost for your business.</h4>
                            <div id="accordion" class="accordion-style">
                                <div class="card">
                                    <div class="card-header" id="headingOne">
                                        <div class="mb-0">
                                            <h5 class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        <span class="counts">1</span>
                                            <span class="item-title">Speed and Agility</span>
                                    </h5>
                                        </div>
                                    </div>
                                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                        <div class="card-body">
                                            We help develop fully automated CI/CD/CT processes in your DevOps pipeline so your developers can concentrate on what's important - CODE.
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-header" id="headingTwo">
                                        <div class="mb-0">
                                            <h5 class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                   <span class="counts">2</span>
                                      <span class="item-title">Product and Service Reliability</span>
                                </h5>
                                        </div>
                                    </div>
                                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                        <div class="card-body">
                                            We help you choose and implement the industry leading tools and methodologies for your DevOps and Agile processes. Whether you choose on-prem or Cloud solutions, we can work to implement existing procedures or define new ones to reduce your time to delivery without any compromise to quality. 
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-header" id="headingThree">
                                        <div class="mb-0">
                                            <h5 class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    <span class="counts">3</span>
                                            <span class="item-title">How we help you reduce your costs?</span>
                                   </h5>
                                        </div>
                                    </div>
                                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                                        <div class="card-body">
                                            As responsibility for observability shifts left, we will exercise more influence over monitoring early in the application development process. We help put control back in the hands of developers to fix what's broken in their code even before they are tested.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5 col-md-12 sm-text-center sm-margin-30px-bottom md-margin-five-top sm-no-margin-top order-1 order-lg-2">
                        <img src="${pageContext.request.contextPath}/resources/img/content/content-01.png" alt="" class="img-effect" />
                    </div>
                </div>
            </div>
        </section>
        <!-- end innovate business section -->

        <!-- start contact section -->
        <section class="parallax cover-background theme-overlay" data-overlay-dark="9" data-background="img/banner/bg1.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-md-9 text-center center-col">
                        <h4 class="text-white sm-margin-10px-bottom">Haven't found what you like? feel free to contact</h4>
                        <p class="text-white font-size16 xs-font-size14 margin-30px-bottom sm-margin-20px-bottom">We always provide you our best services.</p>
                        <a href="javascript:void(0)" class="butn white"><span>Contact Us</span></a>
                    </div>

                </div>
            </div>

        </section>
        <!-- end contact section -->

        <!-- start how it work section -->
        <section>
            <div class="container">
                <div class="section-heading2">
                    <span class="alt-font">How we work</span>
                    <h3>Our Working Process</h3>
                </div>
            </div>
            <div class="container-fluid no-padding sm-padding-15px-lr">
                <div class="horizontaltab tab-style">
                    <ul class="resp-tabs-list hor_1">
                        <li><i class="fab fa-renren text-theme-color"></i><span class="display-block sm-display-inline-block sm-vertical-align-top">Discover</span></li>
                        <li><i class="fas fa-code text-theme-color"></i><span class="display-block sm-display-inline-block sm-vertical-align-top">Design</span></li>
                        <li><i class="fas fa-database text-theme-color"></i><span class="display-block sm-display-inline-block sm-vertical-align-top sm-width-50">Implement</span></li>
                        <li><i class="fas fa-rocket text-theme-color"></i><span class="display-block sm-display-inline-block sm-vertical-align-top">Launch</span></li>
                        <li><i class="fas fa-rocket text-theme-color"></i><span class="display-block sm-display-inline-block sm-vertical-align-top">Support</span></li>
                    </ul>
                    
                    <div class="resp-tabs-container hor_1">
                        <div>
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-5 sm-margin-20px-bottom sm-text-center">
                                        <img src="${pageContext.request.contextPath}/resources/img/content/discover.png" alt="" class="img-effect" />
                                    </div>
                                    <div class="col-lg-5 offset-lg-1">

                                        <h5>Discovery Sessions</h5>
                                        <p>We work with C-class executives to gather your requirement, along with having sessions with development teams to understand existing development practives and procedures.</p>

                                        <ul class="list-style no-margin-bottom">
                                            <li>Development teams, along with executives will have analysis sessions to see their existing standing on the Radar chart</li>
                                            <li>We help you analyze your process flow to identify bottlenecks and eliminate unnecessary tasks.</li>
                                            <li>We analyze your current development process to help identify missing processes or improvements</li>
                                            <li>Your business deserves the best software. We identify the tools and softwares being used in your landscape and recommend improvements based on the FOSS structure.</li>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </div>
                      
                        <div>
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-5 sm-margin-20px-bottom sm-text-center">
                                        <img src="${pageContext.request.contextPath}/resources/img/content/tab-content-05.png" alt="" class="img-effect" />
                                    </div>
                                    <div class="col-lg-5 offset-lg-1">
                                        <h5>Now ready for design</h5>
                                        <p>We help design a robust and Agile DevOps process integrated with a DevOps mindset for faster delivery without compromise to quality</p>

                                        <ul class="list-style no-margin-bottom">
                                            <li>We help design your Agile and DevOps processes keeping in mind to incorporate existing corporate policies.</li>
                                            <li>We will have on-going design sessions with all stakeholders </li>
                                            <li>We work in an Agile fashion, so your feedback and changes are always welcome.</li>
                                            

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-5 order-lg-1 order-2">
                                        <h5>Connect with development.</h5>
                                        <p>We help implement a completely automated CI/CD/CT process with an Agile mindset.</p>

                                        <ul class="list-style no-margin-bottom">
                                            <li>Fully automated CI/CD/CT processes</li>
                                            <li>Fully customizable</li>
                                            <li>Solve your problem faster</li>
                                            <li>There are many variations</li>
                                            <li>Your business deserves best software</li>
                                        </ul>

                                    </div>
                                    <div class="col-lg-5 offset-lg-1 order-lg-2 order-1 sm-text-center sm-margin-20px-bottom">
                                        <img src="${pageContext.request.contextPath}/resources/img/content/tab-content-02.png" alt="" class="img-effect" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-5 sm-margin-20px-bottom sm-text-center">
                                        <img src="${pageContext.request.contextPath}/resources/img/content/tab-content-03.png" alt="" class="img-effect" />
                                    </div>
                                    <div class="col-lg-5 offset-lg-1">
                                        <h5>You have a ready process</h5>
                                        <p>Whether you launch at scale or in phases, teams using the new process will see results from day 1.</p>

                                        <ul class="list-style no-margin-bottom">
                                        	<li>Commit and done</li>
                                            <li>Faster Lead Time</li>
                                            <li>Faster Recovery</li>
                                            <li>Fewer Defects</li>
                                            <li>Less time spent fixing security issues</li>
                                            <li>Exceed profitability goals</li>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="container">
                                <div class="row">
                                    
                                    <div class="col-lg-5 offset-lg-1">
                                        <h5>Exclusive Support</h5>
                                        <p>The best way to avoid getting stuck in fire-fighting mode is preparation. Each deployment is unique, so we offer a breadth of services to design, implement, and optimize solutions for scale.</p>

                                        <ul class="list-style no-margin-bottom">
                                       		<li>Purpose-fit guidance and support for your enterprise needs</li>
                                            <li>Customized workflows and processes</li>
                                            <li>We work closely with your teams to provide complete ALM, source control management, and much more.</li>
                                            <li>We deliver an incredible experience with all of the products by providing dedicated services and solutions for complex enterprise needs.</li>
                                            <li>Whether you need help translating documentation, providing onsite demos or training, or purchasing in your local currency.</li>
                                            <li>You want onsite help, Solve your problems with us.</li>

                                        </ul>
                                    </div>
                                    <div class="col-lg-5 sm-margin-20px-bottom sm-text-center">
                                        <img src="${pageContext.request.contextPath}/resources/img/content/support.png" alt="" class="img-effect" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
                
               
                
            </div>
        </section>
        <!-- end how it work section -->
  
  	<section>
  		<div>
  			<br/>
  		</div>
  	</section>
  

</jsp:body>
</page:platform> 