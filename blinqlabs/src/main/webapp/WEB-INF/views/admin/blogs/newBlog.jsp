<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<page:user-landing>

	<jsp:body>
         
           <!-- ============================================================== -->
            <div class="container">
            		<br/><br/>
            		
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
            		
            		
            		
            		  <div>
            		  
            		  <script>
            		  
            		  	var str = "${blogList[0].TX_TAGS}";
            			var tags = "";
            			
            		  $( window ).on( "load", function() { 
            			  
            			  document.getElementById("ID_BLOG").hidden = true;
            			  document.getElementById("TX_IMAGE").hidden = true;
            			  document.getElementById("TX_SUBMIT_ACTION").hidden = true;
            			  document.getElementById("ID_CATEGORY").hidden = true;
            			  document.getElementById("TX_TAGS").hidden = true;
            			  
            			  var img = document.getElementById("header-img");  // $('img')[0]
        				  toDataURL(img.src, function(dataUrl) {
            				  console.log('RESULT:', dataUrl);
            				  
            				  var tx_img_data = document.getElementById("TX_IMAGE"); 
            				  tx_img_data.value = dataUrl;
            				  tx_img_data.innerHTML =  tx_img_data.value;
            				})
            		  })
            		  
            			window.addEventListener('load', function() {
            			  document.querySelector('input[type="file"]').addEventListener('change', function() {
            			      if (this.files && this.files[0]) {
            			          var img = document.getElementById("header-img");  // $('img')[0]
            			          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
            			          img.onload = imageIsLoaded;
            			      }
            			  });
            			  
            			  document.getElementById("change-image-btn").addEventListener('click', function() {
            				  document.getElementById("headerbgFile").click();
            			      if (this.files && this.files[0]) {
            			          var img = document.getElementById("header-img");  // $('img')[0]
            			          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
            			          img.onload = imageIsLoaded;
            			      }
            			  });
            			  
            			  document.getElementById("save-blog").addEventListener('click', function() {
            				  
            				  var img = document.getElementById("header-img");  // $('img')[0]
            				  toDataURL(img.src, function(dataUrl) {
                				  console.log('RESULT:', dataUrl);
                				  
                				  var tx_img_data = document.getElementById("TX_IMAGE"); 
                				  tx_img_data.value = dataUrl;
                				  tx_img_data.innerHTML =  tx_img_data.value;
                				  
                				  var tx_submit_data = document.getElementById("TX_SUBMIT_ACTION"); 
                				  tx_submit_data.value = 'SAVE';
                				  tx_submit_data.innerHTML = tx_submit_data.value;
                				})
            				  
                		  });
            			  
            			  
	  					document.getElementById("publish-blog").addEventListener('click', function() {
            				  
            				  var img = document.getElementById("header-img");  // $('img')[0]
            				  toDataURL(img.src, function(dataUrl) {
                				  console.log('RESULT:', dataUrl);
                				  
                				  var tx_img_data = document.getElementById("TX_IMAGE"); 
                				  tx_img_data.value = dataUrl;
                				  tx_img_data.innerHTML =  tx_img_data.value;
                				  
                				  var tx_submit_data = document.getElementById("TX_SUBMIT_ACTION"); 
                				  tx_submit_data.value = 'PUBLISH';
                				  tx_submit_data.innerHTML = tx_submit_data.value;
                				})
            				  
                		  });
	  
            			  
            			});

            			function imageIsLoaded() { 
            			 // alert(this.src);  // blob url
            			  // update width and height ...
            			  toDataURL(this.src, function(dataUrl) {
            				  console.log('RESULT:', dataUrl);
            				  document.getElementById("TX_IMAGE").value=dataUrl;
            				})
            			}
            			
            			function toDataURL(url, callback) {
            				  var xhr = new XMLHttpRequest();
            				  xhr.onload = function() {
            				    var reader = new FileReader();
            				    reader.onloadend = function() {
            				      callback(reader.result);
            				    }
            				    reader.readAsDataURL(xhr.response);
            				  };
            				  xhr.open('GET', url);
            				  xhr.responseType = 'blob';
            				  xhr.send();
            				}

            		  </script>
            		  
            		  
            		  <div class="card hidden-element">
                            <div class="card-body">
                                <h4 class="card-title">Choose Header Image</h4>
                                <form class="m-t-20">
                                    <fieldset class="form-group">
                                        <input type="file" class="form-control-file" id="headerbgFile">
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                        
                          <script>
			                                	
		               		 	 	function performSaveAction(el){
		               		 	 	
		               		 	 	
			               		 	 	updateCatSelect();
				               		 	document.getElementById("blogform").action = "${contextPath}/admin/blogs/postBlog?action=SAVE";
				               		 	document.getElementById("blogform").method = "POST";
				               		 	document.getElementById("blogform").submit();// Form submission
			               		 	}
			               		 	function performPublishAction(el){
			               		 		updateCatSelect();
				               		 	document.getElementById("blogform").action = "${contextPath}/admin/blogs/postBlog?action=PUBLISH";
				               		 	document.getElementById("blogform").method = "POST";
				               		 	document.getElementById("blogform").submit();// Form submission
			               		 	}
			               			 function updateCatSelect(){
								    	d = document.getElementById("TX_CATEGORY").value;
								    	var tx_submit_data = document.getElementById("ID_CATEGORY"); 
		             				  	tx_submit_data.value = d;
		             				  	tx_submit_data.innerHTML = tx_submit_data.value;
		             				  	
		             				  	
		             				  	
		             				  	var tags_element_data = document.getElementById("TX_TAGS"); 
		             				  	tags_element_data.value = tags;
		             				  	
								    }
			               		 
	               		</script>
	               		
	               		<!-- Delete Modal -->
						
						<div class="modal fade" id="deleteBlogModal" tabindex="-1" role="dialog">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/blogs/deleteBlog?id="+${blogList[0].ID_BLOG}" method="POST" name="BlogForm" modelAttribute="BlogBean">
						      <div class="modal-header">
						        	<h4 class="modal-title" id="exampleModalLabel1">Confirm?</h4>
                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      </div>
						      <div class="modal-body">
						     	  <div class="form-group">
		                               
		                                <div>
		                                	<label for="machine-id" class="control-label">This action cannot be undone. </label>
		                                	
		                                </div>
		                                
		                                <div>
		                                	<label for="machine-id" class="control-label">Are you sure you want to delete this article?</label>
		                                	
		                                </div>
                                	</div>
						      </div>
						      <div class="modal-footer">
						      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            	 	<button type="submit" class="btn btn-danger" >Delete</button> 
						      </div>
						      </form>
						    </div>
						  </div>
						</div>
						
						<!-- Discard Modal -->
						<div class="modal fade" id="discardBlogModal" tabindex="-1" role="dialog">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						    <form class="form-horizontal m-t-20" action="${contextPath}/admin/blogs/viewAllBlogs?page=1&type=a" method="GET" name="BlogForm" modelAttribute="BlogBean">
						      <div class="modal-header">
						        	<h4 class="modal-title" id="exampleModalLabel1">Discard?</h4>
                            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      </div>
						      <div class="modal-body">
						     	  <div class="form-group">
		                               
		                                <div>
		                                	<label for="machine-id" class="control-label">This action cannot be undone. </label>
		                                	
		                                </div>
		                                
		                                <div>
		                                	<label for="machine-id" class="control-label">Are you sure you want to discard this article?</label>
		                                	
		                                </div>
                                	</div>
						      </div>
						      <div class="modal-footer">
						      		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            	 	<button type="submit" class="btn btn-danger" >Discard</button> 
						      </div>
						      </form>
						    </div>
						  </div>
						</div>
	               		
                        <c:if test="${not empty blogList}">
                        
                             
		               		<form class="form-horizontal m-t-20" action="#" modelAttribute="blogBean" method="" name="blogForm" id="blogform">
			               		<div class="form-group">
			               		
			               			
			               			<div class="p-3 blog-header" id="blog-header">
			               				<img alt="header" src="${blogList[0].TX_IMAGE}" id="header-img" width="100%" value="header-img" path="header-img" class="header-image"/>
			               				 <div class="image-hover">
										    <button class="btn btn-outline-primary" type="button" id="change-image-btn">Change Image</button>
										  </div>
		            		  		</div>
		            		  		<div class="form-group">
		            		  			<input type="text" id="ID_BLOG" name="ID_BLOG" path="ID_BLOG" value="${blogList[0].ID_BLOG}"/>
			               			</div>
			               			
		            		  		<div class="form-group">
		            		  			<input type="text" id="TX_IMAGE" name="TX_IMAGE" path="TX_IMAGE" value="${blogList[0].TX_IMAGE}"/>
			               			</div>
			               			<div class="p-3">
		                            	 <div class="form-group">
		                                    <input type="text" class="form-control title-text" id="TX_TITLE" name="TX_TITLE" path="TX_TITLE"  value="${blogList[0].TX_TITLE}"/>
		                                </div>
		                            </div>
		                            
		                              <div>
                           				      <div class="row">
										      		  <div class="col-sm-12 col-md-6">
					                                        <div class="form-group">
					                                            <label for="inputfname" class="control-label col-form-label">Category</label>
					                                            <select class="form-control" id="TX_CATEGORY"  name="TX_CATEGORY" path="TX_CATEGORY">
					                                            	<option value="">-</option>
															       <c:forEach var="blogCatentry" items="${blogCatList}">
																     	 <option value="${blogCatentry.ID}"  ${blogCatentry.ID == blogList[0].ID_CATEGORY ? 'selected="selected"' : ''}>${blogCatentry.TX_CATEGORY}</option>
															         </c:forEach>
															    </select>
															    <input type="text" id="ID_CATEGORY" name="ID_CATEGORY" path="ID_CATEGORY" value=""/>
															 
															    
					                                        </div>
					                                    </div>
		                                    </div>
		                                    <div class="row">
					                                    <div class="col-sm-12 col-md-6">
					                                        <div class="form-group">
					                                            <label for="inputlname2" class="control-label col-form-label">Tags</label>
					                                           	<input type="text" class="form-control m-2" id="tags-input"  onkeyup="checkInput(event,this.value)">
					                                            <div id="tag-div" ></div>
					                                            <input type="text" class="form-control m-2" id="TX_TAGS" name="TX_TAGS" path="TX_TAGS">
					                                        </div>
					                                    </div>
					                                    
					                                      <script>
					                                      
															var str = "${blogList[0].TX_TAGS}"
															var res = str.split(" ");
														
															
															res.forEach(processTag);
															
															function processTag(item,index){
																var arr = tags.split(" ");
																var n = arr.includes(item);
																if(n==false){
																	tags += " " + item;
																	document.getElementById("tag-div").innerHTML += "<span id='tag-"+item+"' class='tag-pill'>"+item+"<button type='button' id='deltag-"+item+"' class='btn-pill-span' onclick='removeFromTag(this);'>x</button><div class='float-left'></div>" 
																}
																document.getElementById("tags-input").value="";	
															}
															
															function checkInput(event,value){
																var x = event.which || event.keyCode;
																if(x==32 || x ==13){
																	var valueString = value.trim();
																	if(valueString!=""){
																		var formatted = valueString.replace(/,/g, ' ');
																		var val = formatted.split(" ");
																		
																		val.forEach(processTag);
																	}
																}
															}
															
															function removeFromTag(el){
																var element = el.id.replace('deltag-','');
																
																document.getElementById("tag-div").innerHTML = "";
																var arr = tags.split(" ");
																arr.splice( arr.indexOf(element), 1 );
																tags="";
																arr.forEach(processTag);
																
															}
															
															
														</script>
							                  </div>
							           </div>
										
		                             <textarea class="form-control" rows="3" id="TX_BODY" name="TX_BODY" path="TX_BODY">${blogList[0].TX_BODY}</textarea>
			             		</div>
			             			<div class="form-group">
		            		  			<input type="text" id="TX_SUBMIT_ACTION" name="TX_SUBMIT_ACTION" path="TX_SUBMIT_ACTION" value=""/>
			               			</div>
			             		 <div class="modal-footer">
			             		 	<c:if test="${not empty blogList[0].ID_BLOG}">
					             		 <div class="float-left">
					             		 	<button type="button" id="delete-blog" class="btn btn-danger" data-toggle="modal" data-target="#deleteBlogModal"><i class="fas fa-trash-alt"></i></button>
					             		 </div>
				             		 </c:if>
				             		 
				             		 <c:if test="${empty blogList[0].ID_BLOG}">
					             		 <div class="float-left">
					             		 	<button type="button" id="delete-blog" class="btn btn-danger"  data-toggle="modal" data-target="#discardBlogModal"><i class="fas fa-trash-alt"></i></button>
					             		 </div>
				             		 </c:if>
				             		 
				             		 
		                             <button type="button" id="save-blog" class="btn btn-primary" onclick="performSaveAction(this)">Save</button> 
		                             <button type="button" id="publish-blog" class="btn btn-success" onclick="performPublishAction(this)">Publish</button> 
								 </div>
		               		</form>
		               		
		               	  </c:if>
	            		  
	            		  <c:if test="${empty blogList}">
	            		  
	            		       <form class="form-horizontal m-t-20" action="#" modelAttribute="blogBean" method="POST" name="blogForm" id="blogform">
				               		<div class="form-group">
				               			
				               			<div class="p-3 blog-header" id="blog-header">
				               				<img alt="header" src="${pageContext.request.contextPath}/resources/images/abstract1.jpg" id="header-img" width="100%" value="header-img" path="header-img" class="header-image"/>
				               				 <div class="image-hover">
											    <button class="btn btn-outline-primary" type="button" id="change-image-btn">Change Image</button>
											  </div>
			            		  		</div>
			            		  		<div class="form-group">
			            		  			<input type="text" id="TX_IMAGE" name="TX_IMAGE" path="TX_IMAGE" value=""/>
				               			</div>
				               			<div class="p-3">
			                            	 <div class="form-group">
			                                    <input type="text" class="form-control title-text" id="TX_TITLE" name="TX_TITLE" path="TX_TITLE"  placeholder="Title"/>
			                                </div>
			                            </div>
			                              
			                            <div>
                           				      <div class="row">
										      		  <div class="col-sm-12 col-md-6">
					                                        <div class="form-group  has-success">
					                                            <label for="inputfname" class="control-label col-form-label">Category</label>
					                                             <select class="form-control" id="TX_CATEGORY"  name="TX_CATEGORY" path="TX_CATEGORY">
																     <option value="">-</option>
																     <c:forEach var="blogCatentry" items="${blogCatList}">
																     	 <option value="${blogCatentry.ID}">${blogCatentry.TX_CATEGORY}</option>
															         </c:forEach>
															     
															    </select>
															    <input type="text" id="ID_CATEGORY" name="ID_CATEGORY" path="ID_CATEGORY"/>
															    
					                                        </div>
					                                    </div>
							                  </div>
							                  <div class="row">
					                                    <div class="col-sm-12 col-md-6">
					                                        <div class="form-group">
					                                            <label for="inputlname2" class="control-label col-form-label">Tags</label>
					                                           	<input type="text" class="form-control m-2" id="tags-input"  onkeyup="checkInput(event,this.value)">
					                                            <div id="tag-div" ></div>
					                                            <input type="text" class="form-control m-2" id="TX_TAGS" name="TX_TAGS" path="TX_TAGS">
					                                        </div>
					                                    </div>
					                                    
					                                      <script>
					                                      
															var str = "${blogList[0].TX_TAGS}"
															var res = str.split(" ");
														
															
															res.forEach(processTag);
															
															function processTag(item,index){
																var arr = tags.split(" ");
																var n = arr.includes(item);
																if(n==false){
																	tags += " " + item;
																	document.getElementById("tag-div").innerHTML += "<span id='tag-"+item+"' class='tag-pill'>"+item+"<button type='button' id='deltag-"+item+"' class='btn-pill-span' onclick='removeFromTag(this);'>x</button><div class='float-left'></div>" 
																}
																document.getElementById("tags-input").value="";	
															}
															
															function checkInput(event,value){
																var x = event.which || event.keyCode;
																if(x==32 || x ==13){
																	var valueString = value.trim();
																	if(valueString!=""){
																		var formatted = valueString.replace(/,/g, ' ');
																		var val = formatted.split(" ");
																		
																		val.forEach(processTag);
																	}
																}
															}
															
															function removeFromTag(el){
																var element = el.id.replace('deltag-','');
																
																document.getElementById("tag-div").innerHTML = "";
																var arr = tags.split(" ");
																arr.splice( arr.indexOf(element), 1 );
																tags="";
																arr.forEach(processTag);
																
															}
															
															
														</script>
							                  </div>
							                  
							                  
							            
										</div>
										
			                             <div>
			                             	<textarea class="form-control" rows="3" id="TX_BODY" name="TX_BODY" path="TX_BODY" placeholder="Your content here..."></textarea>
			                            </div> 
			                            
			                             <!-- <textarea class="form-control" rows="3" placeholder="Text Here..." id="TX_BODY" name="TX_BODY" path="TX_BODY"></textarea> --> 
				             		</div>
				             		<div class="form-group">
		            		  			<input type="text" id="TX_SUBMIT_ACTION" name="TX_SUBMIT_ACTION" path="TX_SUBMIT_ACTION" value=""/>
			               			</div>
				             		 <div class="modal-footer">
				             		 
					             		 <c:if test="${not empty blogList[0].ID_BLOG}">
						             		 <div class="float-left">
						             		 	<button type="button" id="delete-blog" class="btn btn-danger" data-toggle="modal" data-target="#deleteBlogModal"><i class="fas fa-trash-alt"></i></button>
						             		 </div>
					             		 </c:if>
					             		 
					             		 
					             		 
					             		 <c:if test="${empty blogList[0].ID_BLOG}">
						             		 <div class="float-left">
						             		 	<button type="button" id="delete-blog" class="btn btn-danger" data-toggle="modal" data-target="#discardBlogModal"><i class="fas fa-trash-alt"></i></button>
						             		 </div>
					             		 </c:if>
					             		 
			                             <button type="button" id="save-blog" class="btn btn-primary" onclick="performSaveAction(this)">Save</button> 
			                             <button type="button" id="publish-blog" class="btn btn-success" onclick="performPublishAction(this)">Publish</button> 
									 </div>
			               		</form>
			               		
			               		
			              </c:if>
			              
			              
	            </div>
	            
	            
	            
	            <!-- CKEditor 5 -->
	            <script src="${pageContext.request.contextPath}/resources/ckeditor5-16.0.0/ckeditor.js"></script>
	            
	            <script>
	                ClassicEditor.create(document.getElementById('TX_BODY'));
	            </script>
	            
	            <script>
	        	document.getElementById("TX_IMAGE").style.visibility = "hidden";
            	document.getElementById("TX_SUBMIT_ACTION").style.visibility = "hidden";
            	document.getElementById("ID_CATEGORY").style.visibility = "hidden";
            	document.getElementById("TX_TAGS").style.visibility = "hidden";
	            </script>
	            
	            <!-- trumbowyg editor -->
	             <%-- <script>window.jQuery || document.write('<script src="js/vendor/jquery-3.3.1.min.js"><\/script>')</script>
	            <script src="${pageContext.request.contextPath}/resources/trumbowyg/dist/trumbowyg.min.js"></script>
	            
	            <script>
		            	$('#editor').trumbowyg({
		            	    btns: [['strong', 'em',], ['insertImage']],
		            	    autogrow: true
		            	});
		        </script>  --%>
		        
		        
		        <!-- Timymice Editor -->
                 
               <%--  <script src="${pageContext.request.contextPath}/resources/css/xtreme/assets/libs/tinymce/tinymce.min.js"></script>
				<script>
				    $(document).ready(function() {
				    	$('#TX_BODY').html("${blogList[0].TX_BODY}");
				    	
				        if ($("#TX_BODY").length > 0) {
				            tinymce.init({
				                selector: "textarea#TX_BODY",
				                theme: "modern",
				                height: 300,
				                plugins: [
				                    "advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
				                    "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
				                    "save table contextmenu directionality emoticons template paste textcolor"
				                ],
				                toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons",
				
				            });
				        }
				        
				      
				    });
				    </script>    --%>      		
            </div>
    </jsp:body>
    
</page:user-landing>
