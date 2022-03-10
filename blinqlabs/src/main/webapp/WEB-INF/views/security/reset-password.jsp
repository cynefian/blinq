<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/img/logos/logo.png">
    <title><spring:eval	expression="@propertyConfigurer.getProperty('application.name')" /></title>
    <!-- Custom CSS -->
    <link href="${contextPath}/resources/css/xtreme/dist/css/style.min.css" rel="stylesheet">
  	<link href="${contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/dist/theme.min.css" rel="stylesheet" />
		
	<script src="${pageContext.request.contextPath}/resources/dist/theme.min.js"></script>
  
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <div class="main-wrapper">
    
       <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Login box.scss -->
        <!-- ============================================================== -->
        <c:if test="${not empty successmessage}">
		 	<script>
		 	 	$(window).on("load", function() {
			    	toastr.success('${successmessage}', 'Success', { "progressBar": true });
			    });
		  	</script>
		</c:if>
		<c:if test="${not empty failuremessage}">
		 	<script>
		 	 	$(window).on("load", function() {
			    	toastr.error('${failuremessage}', 'Failure', { "progressBar": true });
			    });
		  	</script>
		</c:if>
		
		<div class="auth-wrapper d-flex no-block justify-content-center align-items-center" style="background:url(${contextPath}/resources/css/xtreme/assets/images/big/auth-bg.jpg) no-repeat center center;">
            <div class="auth-box">
                <div>
                    <div class="logo">
                     <span class="db"><a href="${contextPath}"><img src="${pageContext.request.contextPath}/resources/img/logos/logo.png" width="15%" alt="logo" /></a></span>
                    	<a href="${contextPath}/index.jsp"><span class="db"><h3><spring:eval expression="@propertyConfigurer.getProperty('application.name')" /></h3></span></a>
                        <h5 class="font-medium m-b-20">Reset Password</h5>
                    </div>
                    <!-- Form -->
                    <div class="row">
                        <div class="col-12">
                            <form class="form-horizontal m-t-20" action="${contextPath}/changeForgottenPassword" modelAttribute="pwdBean" method="POST" name="pwdBeanForm" id="changePwdForm">
                            
                           	 <div class="form-group row ">
                                    <div class="col-12 ">
                                       <label class=" text-center h4 text-primary">${tx_email}</label>
                                       <input type="text" id="email" name="email" value="${tx_email}" style="display:none">
                                    </div>
                                </div>
                                
                                <div class="form-group row ">
                                    <div class="col-12 ">
                                        <input class="form-control form-control-lg" type="password" required placeholder="Password" id="password" name="newpassword1"
											path="password" onkeyup="validatePassword(this);">
                                    </div>
                                </div>
                                
                                <div class="form-group row ">
                                    <div class="col-12 ">
                                        <input class="form-control form-control-lg" type="password" required placeholder="Confirm Password" id="password2" name="newpassword2"
											path="password2" >
                                    </div>
                                </div>
                                 <div class="form-group row ">
                                    <div class="col-12 ">
                                        <label class="text-danger" id="error-msg"></label>
                                    </div>
                                </div>
                                
                                <div class="form-group text-center ">
                                    <div class="col-xs-12 p-b-20 ">
                                        <button class="btn btn-block btn-lg btn-info " type="button" onclick="validateChange();">Reset Password</button>
                                    </div>
                                </div>
                            </form>
                            
                             <div id="popover-content" style="display:none">
							    <div class="row">
							    	<h4>Password Policy</h4>
							    </div>
							    <div class="row">
		                       		<div class="m-1">
		                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="pwd-length-check-img" alt="" width="5%"/>
		                       			<span>The password must be alteast 8 characters long</span>
		                       		</div>
		                       		<div class="m-1">
		                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="pwd-lowercase-check-img" alt="" width="5%"/>
		                       			<span>The password must have atleast one lowercase letter</span>
		                       		</div>
		                       		<div class="m-1">
		                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="pwd-uppercase-check-img" alt="" width="5%"/>
		                       			<span>The password must have atleast one uppercase letter</span>
		                       		</div>
		                       		<div class="m-1">
		                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="pwd-number-check-img" alt="" width="5%"/>
		                       			<span>The password must have atleast one number</span>
		                       		</div>
		                       		<div class="m-1">
		                       			<img src="${pageContext.request.contextPath}/resources/img/icons/wrong.png" id="pwd-symbol-check-img" alt="" width="5%"/>
		                       			<span>The password must have atleast one of the accepted special character: (@#$!~%&*()<>:;'"?,.+=-_{}\/)</span>
		                       		</div>
		                       		<div class="m-1">
		                       			<input type="text" id="passwordValidation" value="" style="display:none">	
		                       		</div>
		                       	</div>
							</div>
							
                            <script>
                            
                            function validatePassword(element){
                        		var passwordVal = document.getElementById("password").value;
                        		
                        		var evalResultlength = false;
                        		if(passwordVal.length<8){
	                       			$('#pwd-length-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
	                       			evalResultlength = false;
	                       		}else{
	                       			$('#pwd-length-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
	                       			evalResultlength = true;
	                       		}
                        		
                        		
                        		var evalResultlower = false;
                        		var regExTesterlower = new RegExp("[a-z]+");
	                       			if (regExTesterlower.test(passwordVal)) {
	                       				$('#pwd-lowercase-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
	                       				evalResultlower = true;
	                       			}else{
	                       	        	$('#pwd-lowercase-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
	                       	     		 evalResultlower = false;
	                       	        }
	                       			
	                       			
	                       			var evalResultupper = false;
	                       			var regExTesterupper = new RegExp("[A-Z]+");
                       			if (regExTesterupper.test(passwordVal)) {
                       				$('#pwd-uppercase-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
                       				evalResultupper = true;
                       			}else{
                       	        	$('#pwd-uppercase-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
                       	        	evalResultupper = false;
                       	        }
                       			
                       			
                       			var evalResultnumber = false;
                       			var regExTesternumber = new RegExp("[0-9]+");
                       			if (regExTesternumber.test(passwordVal)) {
                       				$('#pwd-number-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
                       				evalResultnumber = true;
                       			}else{
                       	        	$('#pwd-number-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
                       	        	evalResultnumber = false;
                       	        }
                       			
                       			//var regExTestersymbol = new RegExp("[~!@#\$%^&*_\-+=`|\\(){}\[\]:;\"'<>,\.?\/\]+");
                       			var evalResultsymbol = false;
                       			var regExTestersymbol = new RegExp("[@#$!~%&*()<>:;?,.+=_{}\/\\'\"\-]+");
                       			if (regExTestersymbol.test(passwordVal)) {
                       				$('#pwd-symbol-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/check.png");
                       				evalResultsymbol = true;
                       			}else{
                       	        	$('#pwd-symbol-check-img').attr("src","${pageContext.request.contextPath}/resources/img/icons/wrong.png");
                       	        	evalResultsymbol = false;
                       	        }
                       			
                       			
                       			
                       			if(evalResultlength==true && evalResultlower==true && evalResultupper==true && evalResultnumber==true && evalResultsymbol==true){
                       				document.getElementById("passwordValidation").value = "true";
                       			}else{
                       				document.getElementById("passwordValidation").value = "false";
                       			}
                       			
                       			
                       			$(element).popover({
                        			placement: 'right',
                        			container: 'body',
                        			html: true,
                        			content: function() {
                        			       return $('#popover-content').html();
                        	        }
                        		})
                  			}
                            
                            
                            	function validateChange(){
                            		var pwdValid = document.getElementById("passwordValidation").value;
                            		var p1 = document.getElementById("password").value;
                            		var p2 = document.getElementById("password2").value;
                            		var errEl = document.getElementById("error-msg");
                            		
                            		var n = p1.localeCompare(p2);
                            		
                            		
                            		var proceedData="false";
                            		if(((document.getElementById("password").value==null || document.getElementById("password").value=="")
                            				||(document.getElementById("password2").value==null || document.getElementById("password2").value=="")
                            				){
                            			toastr.error('All fields are required.', 'Missing data', { "progressBar": true });
                            			proceedData="false";
                            		}else{
                            			proceedData="true";
                            		}
                     
                            		var proceedPwds="false";
                            		if(pwdValid=="true"
                            				&& n==0){
                            			proceedPwds="true";
                            			errEl.innerHTML="";
                            			errEl.style.display = "none";
                            		}else{
                            			proceedPwds="false";
                            			if(pwdValid=="true"){
                            				toastr.error('Please check your passwords', 'Password mismatch', { "progressBar": true });
                            				errEl.innerHTML="Passwords don't match";
                                			errEl.style.display = "block"
                            			}else{
                            				toastr.error('Please create the password as per policy.', 'Password validation failed', { "progressBar": true });
                            				toastr.info('<ul class="list-style no-margin-bottom">'
                            						+'<li>Password must have atleast one lowercase letter</li>'
                            						+'<li>Password must have atleast one uppercase letter</li>'
                            						+'<li>Password must have atleast one number</li>'
                            						+'<li>Password must have atleast one acceptable special character </li>'
                            						+'@#$!~%&*()<>:;?,.+=_{}\/"-'+"'"
                            						+'</ul>. <button type="button" class="btn btn-secondary clear">OK</button>', 'Password Policy', { "showMethod": "fadeIn", "hideMethod": "fadeOut", timeOut: 60000 });
                            			}
                            		}
                            		
                            		if(proceedData=="true" && proceedPwds=="true"){
                            			document.getElementById("changePwdForm").submit();	
                            		}
                            	}
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Login box.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper scss in scafholding.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper scss in scafholding.scss -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Right Sidebar -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Right Sidebar -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- All Required js -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery/dist/jquery.min.js "></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/popper.js/dist/umd/popper.min.js "></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/bootstrap/dist/js/bootstrap.min.js "></script>
    <!-- ============================================================== -->
    <!-- This page plugin js -->
    <!-- ============================================================== -->
    <script>
    $('[data-toggle="tooltip "]').tooltip();
    $(".preloader ").fadeOut();
    
    </script>
    
     <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app.init.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/app-style-switcher.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.js"></script>
    
   <%--  <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/toastr/toastr-init.js"></script> --%>
    <%-- <script src="${pageContext.request.contextPath}/resources/js/toastr-init.js"></script> --%>
</body>

</html>