var contextPath = "${pageContext.request.contextPath}";

		function clearFilter(id){
			var filterForm = document.getElementById("dateFilterForm");
			filterForm.action=contextPath + "/admin/SENdetails?id="+id+"&source=ACTIVITY";
			filterForm.method = "POST";
			filterForm.submit();
		}
		
		function filterActivity(){
			var startDate = document.getElementById("startDateFilter").value;
			var endDate = document.getElementById("endDateFilter").value;
			var senId = document.getElementById("senIdFilter").value;
			var filterForm = document.getElementById("dateFilterForm");
			
			filterForm.action=contextPath + "/admin/SENdetails?id="+senId+"&startDate="+startDate+"&endDate="+endDate+"&source=ACTIVITY";
			filterForm.method = "POST";
			filterForm.submit();
			
		}
		
		
		function showDescription(idx){
			document.getElementById("descriptionContent").innerHTML = idx;
			document.getElementById("showDescriptionButton").click();
		}
	
			
		$(document).ready(function() {
			document.getElementById("uploadButton").disabled = true;
			if("${pageTab}"=="ATTACH"){
				$('#attachTab').click();
			}else if ("${pageTab}"=="ACTIVITY"){
				$('#activityTab').click();
			}else{
				$('#infoTab').click();
			}
		});
		
		function processAttachment(){
			
			$('#preloader').show();
			
			var input = document.getElementById("inputUploadFile")
			var file = input.files[0];
			
			var reader = new FileReader();
			   reader.readAsDataURL(file);
			   reader.onload = function () {
			     console.log(reader.result);
			     document.getElementById("TX_DATA").value = reader.result;
			     	var tx_type = document.getElementById("TX_TYPE").value;
			     if(tx_type.trim() == "application/pdf" || tx_type.trim().includes("image/") 
			    		 || tx_type.trim() == "application/msword" || tx_type.trim() == "application/vnd.openxmlformats-officedocument.wordprocessingml.document" 
			    		 || tx_type.trim() == "application/vnd.ms-excel" || tx_type.trim() == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" 
			    		 || tx_type.trim() == "application/vnd.ms-powerpoint" || tx_type.trim() == "application/vnd.openxmlformats-officedocument.presentationml.presentation" ){
			    
			    	 var tx_size = document.getElementById("TX_SIZE").value;
			    	 if(tx_size<10485760){
			    		 	document.getElementById("uploadSenAttachmentForm").action = contextPath + "/admin/uploadSenAttachment";
							document.getElementById("uploadSenAttachmentForm").method = "POST";
							document.getElementById("uploadSenAttachmentForm").submit(); 
			    	 }else{
			    		 alert("Attachments must be less than 10MB is size.");
			    		 $('#loader').addClass('hidden');
			    	 }
			    	   
			     }else{
			    	 alert("Unsupported file type.");
			    	 $('#preloader').hide();
			     }
			   };
			   reader.onerror = function (error) {
			     console.log('Error: ', error);
			     document.getElementById("TX_DATA").value = "";
			     alert("Error processing attachment");
			     $('#preloader').hide();
			   };
		}
		
		function updateLabel(){
			var input = document.getElementById("inputUploadFile");
			var file = input.files[0];
			var filenm = input.files.item(0).name;
			var filesize = input.files.item(0).size;
			var filetype = input.files.item(0).type;
			
			var fileName = input.value;
			
			document.getElementById("fileUploadLabel").innerHTML = fileName;
			document.getElementById("TX_NAME").value = filenm;
			document.getElementById("TX_TYPE").value = filetype;
			document.getElementById("TX_SIZE").value = filesize;
			document.getElementById("uploadButton").disabled = false;
		}
		
		function downloadAttachment(id){
			$.ajax({  
                  url:contextPath + "/common/downloadAttachment",  
                  data:{key: id},  
                  success:function(response)  
                  {  
                	 var tx_data =  response[0].TX_DATA;
                	 var tx_name =  response[0].TX_NAME;
                	 var tx_type =  response[0].TX_TYPE;
                	 var tx_size =  response[0].TX_SIZE;
                	 
                	  const parts = tx_data.split(';base64,');

                	  const contentType = parts[0].split(':')[1];

                	  const decodedData = window.atob(parts[1]);

                	  const uInt8Array = new Uint8Array(decodedData.length);

                	  for (let i = 0; i < decodedData.length; ++i) {
                	    uInt8Array[i] = decodedData.charCodeAt(i);
                	  }

                	    var blob= new Blob([uInt8Array], { type: contentType });
                	 
                	    var link=document.createElement('a');
                	    link.href=window.URL.createObjectURL(blob);
                	    link.download=tx_name;
                	    link.click(); 


                  }  
             });
		}