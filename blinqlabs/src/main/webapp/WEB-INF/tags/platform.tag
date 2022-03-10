<!DOCTYPE html>
<html lang="en">

<head>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-MXNHSYPX27"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-MXNHSYPX27');
</script>

    <!-- metas -->
    <meta charset="utf-8">
    <meta name="author" content="Gundlupet Sreenidhi" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="keywords" content="BLINQLABS" />
    <meta name="description" content="BLINQLABS" />

    <!-- title  -->
    <title>BLINQLABS</title>

    <!-- favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/logos/logo.png" />
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resources/img/logos/logo.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resources/img/logos/logo.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resources/img/logos/logo.png" />

	<!-- Bootstrap -->
	<link href="${pageContext.request.contextPath}/resources/scss/bootstrap/bootstrap.css" rel="stylesheet"/>
	
    <!-- plugins -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/amava/plugins.css" />

    <!-- search css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/search/search.css" />

    <!-- core style css -->
    <link href="${pageContext.request.contextPath}/resources/css/amava/styles.css" rel="stylesheet" id="colors" />

	<!-- Toastr css -->
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/toastr/build/toastr.min.css" rel="stylesheet">
	
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
	
	<!-- Custom css -->
	    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">
	<!-- Fontawesome-->
	<link href="${pageContext.request.contextPath}/resources/css/fontawesome-5.11.2/css/all.css" rel="stylesheet">
	
	 <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/jquery.min.js"></script>
    <!-- modernizr js -->
	
</head>

<body>
  
  <!-- start page loading -->
    <div id="preloader">
        <div class="row loader">
            <div class="loader-icon"></div>
        </div>
    </div>
    <!-- end page loading -->
    
    <!-- start main-wrapper section -->
    <div class="main-wrapper">
    
      <div id="navigator">
		<%-- <%@ include file="/WEB-INF/shared/nav.jsp" %> --%>
			<jsp:include page="/WEB-INF/shared/navigator.jsp" />
		</div>
	
	<div id="body-container"  class="body-layout">
		<jsp:doBody />
	</div>
	
	<div id="footer-section">
		<%--  <%@ include file="/WEB-INF/shared/footer.jsp" %> --%>
		<jsp:include page="/WEB-INF/shared/foot.jsp" />
	</div>

       <!-- start scroll to top -->
    <a href="javascript:void(0)" class="scroll-to-top"><i class="fas fa-angle-up" aria-hidden="true"></i></a>
    <!-- end scroll to top -->

	</div>
	
	<!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    
    <!-- all js include start -->
   
    <script src="${pageContext.request.contextPath}/resources/js/amava/modernizr.js"></script>
    <!-- bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/bootstrap.min.js"></script>
    <!-- scrollIt -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/scrollIt.min.js"></script>
    <!-- Serch -->
    <script src="${pageContext.request.contextPath}/resources/search/search.js"></script>
    <!-- navigation -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/nav-menu.js"></script>
    <!-- tab -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/easy.responsive.tabs.js"></script>
    <!-- owl carousel -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/owl.carousel.js"></script>
    <!-- jquery.counterup.min -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/jquery.counterup.min.js"></script>
    <!-- stellar js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/jquery.stellar.min.js"></script>
    <!-- waypoints js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/waypoints.min.js"></script>
    <!-- tab js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/tabs.min.js"></script>
    <!-- countdown js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/countdown.js"></script>
    <!-- jquery.magnific-popup js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/jquery.magnific-popup.min.js"></script>
    <!-- isotope.pkgd.min js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/isotope.pkgd.min.js"></script>
    <!-- wow js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/wow.js"></script>
    <!-- map js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/map.js"></script>
    <!-- custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/main.js"></script>
    <!-- contact form scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/mailform/jquery.form.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/amava/mailform/jquery.rd-mailform.min.c.js"></script>
    
      <!--chartis chart-->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <!--c3 charts -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/d3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/extra-libs/c3/c3.min.js"></script>
    <!--chartjs -->
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/chart.js/dist/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/pages/dashboards/dashboard1.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/css/xtreme/dist/js/custom.min.js"></script>
	
	
	 <!-- map js -->
    <script src="${pageContext.request.contextPath}/resources/js/amava/map.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAFLIVTTKakv3s-jInA_QtsytrueLQ3GME&amp;callback=initMap" async defer></script>
	
	
	
    <!-- all js include end -->

</body>

</html>