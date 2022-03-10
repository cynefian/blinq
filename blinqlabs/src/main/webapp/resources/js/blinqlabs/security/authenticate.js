function authenticate(){
	
	if(document.getElementById("j_username").value==null || document.getElementById("j_username").value==""){
		toastr.error('Please enter login id', 'Failure', { "progressBar": true });
	}else if(document.getElementById("j_password").value==null || document.getElementById("j_password").value==""){
		toastr.error('Please enter password', 'Failure', { "progressBar": true });
	}else{
		if(document.getElementById("customCheck1").checked==true){
			setCookie("username",document.getElementById("j_username").value, 14);
		}
		document.getElementById("loginform").submit();
	}
}

function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

$(window).on("load", function() {
	  var user = getCookie("username");
	  if (user != "") {
		  document.getElementById("j_username").value = user;
		  document.getElementById("customCheck1").checked = true;
	  } else {
	   // do nothing; 
	  }
	  
	  
	  function getCookie(cname) {
		  var name = cname + "=";
		  var ca = document.cookie.split(';');
		  for(var i = 0; i < ca.length; i++) {
		    var c = ca[i];
		    while (c.charAt(0) == ' ') {
		      c = c.substring(1);
		    }
		    if (c.indexOf(name) == 0) {
		      return c.substring(name.length, c.length);
		    }
		  }
		  return "";
		}

	  
});

$('[data-toggle="tooltip"]').tooltip();
$(".preloader").fadeOut();
// ============================================================== 
// Login and Recover Password 
// ============================================================== 
$('#to-recover').on("click", function() {
    $("#loginformDiv").slideUp();
    $("#recoverform").fadeIn();
});