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
		<div id="page-wrapper">
			<div class="features-tabs-section" style="margin-top: 100px;">
    			<div class="container">
    				<div class="row">
	    				<div class="col-md-6 text-left">
							<button type="button" class="btn btn-info" data-toggle="modal" data-target="#newEntitlementModal" data-whatever="@fat"><i class="fas fa-plus"></i> New Entitlement</button>
							<br/><br/>
						</div>
						<div class="col-md-6 text-right">
							<form id="senFilterForm" name="senFilterForm" action="" method="POST">
	                                    <div class="form-group m-b-30">
	                                        <select class="custom-select mr-sm-2" id="inlineSenFilter" onchange="updateSenFilter(this);">
	                                            <option value="ALL" ${((currentFilter != 'ACTIVE') && (currentFilter != 'INACTIVE')) ? 'selected="selected"' : ''}>All</option>
	                                            <option value="ACTIVE" ${currentFilter == 'ACTIVE' ? 'selected="selected"' : ''}>Active</option>
	                                            <option value="INACTIVE" ${currentFilter == 'INACTIVE' ? 'selected="selected"' : ''}>Inactive</option>
	                                        </select>
	                                    </div>
	                                </form>
						</div>
					</div>
					<div class="modal fade" id="newEntitlementModal" tabindex="-1" role="dialog" aria-labelledby="newEntitlementModalLabel">
			                <div class="modal-dialog" role="document">
			                    <div class="modal-content">
			                    <form class="form-horizontal m-t-20" action="${contextPath}/admin/addServiceEntitlement" method="POST" id="EntitlementForm" name="senForm" modelAttribute="senBean">
			                        <div class="modal-header">
			                            <h4 class="modal-title" id="productCreateModalLabel1">Add New Entitlement</h4>
			                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                        </div>
			                        <div class="modal-body">
			                            
			                        	<div class="form-group">
			                        		
			                        	<div id="errorElement" class="text-danger">
			                        	
			                        	</div>
			                        	
			                                <div>
			                                    <label for="TX_ACCOUNT_EMAIL" class="control-label">Account:</label>
			                                	<input class="form-control form-control-lg" type="text" required placeholder="email" id="TX_ACCOUNT_EMAIL" name="TX_ACCOUNT_EMAIL" path="TX_ACCOUNT_EMAIL" onkeyup="getUserId();">
			                                	<div class="border border-primary ajax-autocomplete" id="ajax-list">
			                                		<ul class="list-group p-3 m-2" id="showList">
			                                		</ul>
			                                	</div>
			                                </div>
			                                
			                                <div>
			                                    <label for="TX_ACCOUNT_EMAIL" class="control-label">Entitlement Description:</label>
			                                    <textarea class="form-control" rows="3" placeholder="description" id="TX_ENTITLEMENT_DESCRIPTION" name="TX_ENTITLEMENT_DESCRIPTION" path="TX_ENTITLEMENT_DESCRIPTION"></textarea>
			                                </div>
			                                
			                                <c:set var="rollable" value="FALSE"/>
			                                <c:set var="tierRows" value="0"/>
			                                <input type="text" id="tierRowCount" value="0" style="visibility:hidden"/>
			                                
			                                <div class="m-2">
			                                	<label for="fl-active" class="control-label">Entitlement Type</label>
			                                		<select class="custom-select mr-sm-2" id="ID_ENTITLEMENT_TYPE" name="ID_ENTITLEMENT_TYPE" path="ID_ENTITLEMENT_TYPE" onchange="entitlementData(this);">
			                                            <option value="" selected>Choose...</option>
			                                            <c:forEach var="entry" items="${entitlementTypes}">
													     	 <option value="${entry.ID}">${entry.TX_SERVICE_TYPE_NAME}</option>
													     </c:forEach>
			                                        </select>
			                                </div>
			                                
			                                 <div>
			                                	<label class="control-label">Start Date:</label>
			                                	<div class="form-group">
			                                        <input type="date" class="form-control" value="" id="TS_START" name="TS_START" required>
			                                    </div>
			                                </div>
			                                
			                                <div>
			                                	     <label for="fl-active" class="control-label">Ending</label>
						                                <div class="form-check form-check-inline">
						                                    <div class="custom-control custom-radio">
						                                        <input type="radio" class="custom-control-input" id="end-date-radio" name="radio-stacked" onclick="updateDuration();">
						                                        <label class="custom-control-label" for="end-date-radio">End Date</label>
						                                    </div>
						                                </div>
						                                <div class="form-check form-check-inline">
						                                    <div class="custom-control custom-radio">
						                                        <input type="radio" class="custom-control-input" id="duration-radio" name="radio-stacked" onclick="updateDuration();">
						                                        <label class="custom-control-label" for="duration-radio">Duration</label>
						                                    </div>
						                                </div>
						                            
						                        <div id="endDateDiv">
							                        <label class="control-label">End Date:</label>
				                                	<div class="form-group">
				                                        <input type="date" class="form-control" value="" id="end_date" name="end_date" onchange="updateDatedEndDate(this);">
				                                    </div>
						                        </div>
			                                	<div id="durationDiv">
			                                		<label for="TX_ACCOUNT_EMAIL" class="control-label">Duration</label>
			                                		<div class="row">
			                                			<div class="col-md-6">
			                                				
							                                    <div class="form-group m-b-30">
							                                        <label class="mr-sm-2" for="durationSelectionCriteria">Select</label>
							                                        <select class="custom-select mr-sm-2" id="durationSelectionCriteria" onclick="updateCascadeSelection(this);">
							                                            <option selected>Choose...</option>
							                                            <option value="YEAR(S)">Year(s)</option>
							                                            <option value="MONTH(S)">Month(s)</option>
							                                            <option value="WEEK(S)">Week(s)</option>
							                                        </select>
							                                    </div>
									                    </div>
			                                			<div class="col-md-6">
			                                				<div id="yearsCascadeSelection">
		                                				        <div class="form-group m-b-30">
							                                        <label class="mr-sm-2" for="durationSelectionCriteria">Select</label>
							                                        <select class="custom-select mr-sm-2" id="durationYearsCascadeSelection" onChange="UpdateYearlyEndDate(this);">
							                                            <option selected>Choose...</option>
							                                            <option value="1">ONE</option>
							                                            <option value="2">TWO</option>
							                                            <option value="3">THREE</option>
							                                            <option value="4">FOUR</option>
							                                            <option value="5">FIVE</option>
							                                        </select>
							                                    </div>
									                        </div>
			                                				<div id="monthsCascadeSelection">
		                                				        <div class="form-group m-b-30">
							                                        <label class="mr-sm-2" for="durationSelectionCriteria">Select</label>
							                                        <select class="custom-select mr-sm-2" id="durationMonthsCascadeSelection"  onChange="UpdateMonthlyEndDate(this);">
							                                            <option selected>Choose...</option>
							                                            <option value="1">ONE</option>
							                                            <option value="2">TWO</option>
							                                            <option value="3">THREE</option>
							                                            <option value="4">FOUR</option>
							                                            <option value="5">FIVE</option>
							                                            <option value="6">SIX</option>
							                                            <option value="7">SEVEN</option>
							                                            <option value="8">EIGHT</option>
							                                            <option value="9">NINE</option>
							                                            <option value="10">TEN</option>
							                                            <option value="11">ELEVEN</option>
							                                            <option value="12">TWENVE</option>
							                                           
							                                        </select>
							                                    </div>
									                       </div>
			                                				<div id="weeksCascadeSelection">
			                                				<div class="card-body">
								                                    <div class="form-group m-b-30">
								                                        <label class="mr-sm-2" for="durationSelectionCriteria">Select</label>
								                                        <select class="custom-select mr-sm-2" id="durationWeeksCascadeSelection"  onChange="UpdateWeeklyEndDate(this);">
								                                            <option selected>Choose...</option>
								                                            <option value="1">ONE</option>
								                                            <option value="2">TWO</option>
								                                            <option value="3">THREE</option>
								                                            <option value="4">FOUR</option>
								                                            <option value="5">FIVE</option>
								                                        </select>
								                                    </div>
									                            </div>
			                                				</div>
			                                			</div>
			                                		</div>
			                                	</div>
			                                	
			                                	<div>
			                                		<input type="text" value="" id="TX_DURATION" name="TX_DURATION" style="visibility:hidden">
			                                		<input type="text" value="" id="TS_END" name="TS_END" style="visibility:hidden">
			                                	</div>
			                                </div>
			                                
			                                <div class="m-2" id="typeDetailsDiv">
			                                	<div id="EntitlementTypeTitle"></div>
			                                	<div id="EntitlementTypeRollable"> Allow Rollable: <span id="EntitlementTypeRollableValue"></span></div>
			                                	<div id="EntitlementTypePrice">
			                                	
				                                		<div class="row m-2" id="optionRollableDiv">
				                                			<div class="col-md-8">
						                                		<div>Use Rollable: </div>
						                                		<div> 
						                                			<label class="custom-toggle">
															  			<input type="checkbox"
																			onclick="validateRollable(this);" id="ROLLABLE_FLAG"
																			name="ROLLABLE_FLAG"
																			path="ROLLABLE_FLAG" value="0">
														  				<span class="custom-toggle-slider rounded-circle"></span>
																  	</label>
											  					</div>
										  					</div>
										  					<div class="col-md-4">
										  						<div id="rollableButton">
										  							<button type="button" class="btn btn-default" onclick="addTier();">Add Tier</button>
										  						</div>
										  					</div>
									  					</div>
								  					
								  					<div class="m-2">
								  						<div id="pricingTier">
								  							<div id="tiers">
									  							<div id="row_${tierRows}" class="row table-active m-2 p-2">
									  								 <div>
									                                    <input class="form-control form-control-lg" type="text"  id="TX_TIER_ID_${tierRows}" name="TX_TIER_ID_${tierRows}" path="TX_TIER_ID_${tierRows}" style="display:none">
									                                </div>
									  								 <div class="col-md-5">
									                                    <input class="form-control form-control-lg" type="text" required placeholder="Tier Name" id="TX_TIER_NAME_${tierRows}" name="TX_TIER_NAME_${tierRows}" path="TX_TIER_NAME_${tierRows}">
									                                </div>
									                                <div class="col-md-3">
									                                    <input class="form-control form-control-lg" type="text" required placeholder="Cost" id="TX_TIER_PRICE_${tierRows}" name="TX_TIER_PRICE_${tierRows}" path="TX_TIER_PRICE_${tierRows}">
									                                </div>
									                                <div class="col-md-3">
									                                    <input class="form-control form-control-lg" type="number" required placeholder="Limit" id="TX_TIER_LIMIT_${tierRows}" name="TX_TIER_LIMIT_${tierRows}" path="TX_TIER_LIMIT_${tierRows}">
									                                </div>
									                                <div class="col-md-1">
									                                 </div>
									  							</div>
								  							</div>
								  						</div>
								  					</div>
			                                	</div>
			                                </div>
			                            </div>
			                            
			                            <div id="hiddenElements">
			                            	<input type="text" id="TX_TIER_JSON" name="TX_TIER_JSON" path="TX_TIER_JSON" style="visibility:hidden" />
			                            </div>
			                                
			                            
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			                             <button type="button" class="btn btn-primary" onClick="validateSubmit();">Create</button> 
			                           
			                        </div>
			                        </form>
			                    </div>
			                </div>
			            </div>
			            
			            <div class="m-2">
			            	<c:if test="${empty SENFormList}">
			            		<h4>There are no Entitlements</h4>
			            	</c:if>
			            	
			            	<c:if test="${not empty SENFormList}">
			            		<c:forEach var="entry" items="${SENFormList}">
			            			<div class="card card-hover border border-primary">
									    <div class="card-header bg-light">
									    <div class="row">
									    	<div class="col-md-6">
									    		<h4 class="m-b-0 text-primary">${entry.TX_ACCOUNT_EMAIL}</h4>
									    	</div>
									    	<div class="col-md-6">
									    	
									    	
									    		<div class="d-flex flex-row">
                                       			 	<div class="p-10 ">
                                       			 		 <c:if test="${entry.FL_ACTIVE=='0'}">
												        	<span class="badge badge-pill badge-light">INACTIVE</span>
												        </c:if>
												        <c:if test="${entry.FL_ACTIVE=='1'}">
												        	<span class="badge badge-pill badge-success">ACTIVE</span>
											        	</c:if> 	
                                       			 	</div>
                                       			 	
                                       			 	<div class="">
                                       			 		<form action="" method="" id="toggleUpdateForm_${entry.ID}">
												            <label class="custom-toggle">
												            <c:if test="${entry.FL_ACTIVE=='0'}">
													        	<input type="checkbox" onclick="updateSenStatus(this);" id="${entry.ID}" name="${entry.ID}_${entry.ID_ACCOUNT}" path="${entry.ID}_${entry.ID_ACCOUNT}" value="0" >
													        </c:if>
													        <c:if test="${entry.FL_ACTIVE=='1'}">
													        	<input type="checkbox" onclick="updateSenStatus(this);" id="${entry.ID}" name="${entry.ID}_${entry.ID_ACCOUNT}" path="${entry.ID}_${entry.ID_ACCOUNT}" value="1" checked >
													        </c:if>
													        <span class="custom-toggle-slider rounded-circle"></span>
												  			</label>
											  			</form>
                                       			 	</div>
                                       			 </div>
                                        	</div>
									    </div>
									        
									    </div>
									    <div class="card-body">
									    	<div class="row align-center">
									    		<div class="col-md-6 p-2">
									    			<h3 class="card-title"> ${entry.TX_ENTITLEMENT_TYPE}</h3>
									    			<span class="text-muted">${entry.TX_ENTITLEMENT_DESCRIPTION}</span>
									    		</div>
									    		<div class="col-md-6 p-2">
									    			 <p class="card-text"><i class="fas fa-calendar-alt"></i>  Start Date: ${entry.TS_START}</p>
										        	 <p class="card-text"><i class="fas fa-calendar-alt"></i>  End Date: ${entry.TS_END}
										        	 	<c:if test="${entry.TX_DURATION != 'DEFINED'}">
												        	<div class="badge badge-outline badge-pill badge-secondary">${entry.TX_DURATION}</div>
												        </c:if>
												        </p>	 
									    		</div>
									    	</div>
									        
									        <div class="text-right">
									         <a href="${contextPath}/admin/SENdetails?id=${entry.ID}" class="btn btn-inverse">View Details</a>
									         </div>
									    </div>
									    <div class="bg-secondary text-white">
									    	<div class="m-2 p-2">
									    		Service Entitlement Number: <strong>${entry.TX_ENTITLEMENT}</strong>
									    	</div>
									    	
									    	<div class="text-right">
									    	
									    	</div>
									    </div>
									</div>
			            		
			            		
			            		</c:forEach>
			            		
			            		 <ul class="pagination float-right">
	                                                                               <c:if test="${currentPage!=1 && currentPage>0}">
	                                        	 <li class="page-item mr-2">
	                                            	<a class="page-link" href="${contextPath}/admin/adminServiceEntitlements?filter=${currentFilter}&page=${currentPage - 1 }" tabindex="-1">Previous</a>
	                                        	</li>
	                                        </c:if>
	                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
	                                    	<c:if test="${currentPage==loop.index}">
	                                    		<li class="page-item active" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/adminServiceEntitlements?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    	<c:if test="${currentPage!=loop.index}">
	                                    		<li class="page-item" id="paginationLink${loop.index}"><a class="page-link" href="${contextPath}/admin/adminServiceEntitlements?filter=${currentFilter}&page=${loop.index}">${loop.index}</a></li>
	                                    	</c:if>
	                                    </c:forEach>
	                                    	<c:if test="${currentPage!=totalPages}">
	                                        	 <li class="page-item ml-2">
	                                            	<a class="page-link" href="${contextPath}/admin/adminServiceEntitlements?filter=${currentFilter}&page=${currentPage + 1 }" tabindex="-1">Next</a>
	                                        	</li>
	                                        </c:if>

	                                    </ul>
	                                    
			            	</c:if>
			            </div>
    			
    			</div>
   			</div>
   		</div>
   		
    		<script>
   	 
    		$(document).ready(function() {
    			$('#ajax-list').hide();
    			$('#typeDetailsDiv').hide();
    			$('#rollableButton').hide();
    			$('#endDateDiv').hide();
    			$('#durationDiv').hide();
    			$('#yearsCascadeSelection').hide();
    			$('#monthsCascadeSelection').hide();
    			$('#weeksCascadeSelection').hide();
    		});
    		
    		function updateSenStatus(el){
    			var update = "";
    			
    			if (el.value == "0"){
    				update = "1";
    			}else{
    				update = "0";
    			}
    			
    			document.getElementById("toggleUpdateForm_"+el.id).action = "${contextPath}/admin/updateSENStatus?id="+el.id+"&val="+update;
    			document.getElementById("toggleUpdateForm_"+el.id).method = "POST";
    			document.getElementById("toggleUpdateForm_"+el.id).submit();
    			
    		}
    		
    		function updateDatedEndDate(el){
    			document.getElementById("TS_END").value=el.value;
    		}
    		
    		function UpdateYearlyEndDate(el){
    			document.getElementById("TS_END").value=el.value+" YEARS";
    		}
    		
    		function UpdateMonthlyEndDate(el){
    			document.getElementById("TS_END").value=el.value+" MONTHS";
    		}
    		
    		function UpdateWeeklyEndDate(el){
    			document.getElementById("TS_END").value=el.value+" WEEKS";
    		}
    		
    		function updateDuration(){
    			if($("#end-date-radio").is(":checked")){
    				$('#endDateDiv').show();
    				$('#durationDiv').hide();
    				$('#yearsCascadeSelection').hide();
        			$('#monthsCascadeSelection').hide();
        			$('#weeksCascadeSelection').hide();
        			$('#yearsCascadeSelection').val("0");
        			$('#monthsCascadeSelection').val("0");
        			$('#weeksCascadeSelection').val("0");
    			}else if($("#duration-radio").is(":checked")){
    				$('#endDateDiv').hide();
    				$('#durationDiv').show();
    			}
    		}
    		
    		function updateCascadeSelection(el){
    			$('#yearsCascadeSelection').hide();
    			$('#monthsCascadeSelection').hide();
    			$('#weeksCascadeSelection').hide();
    			$('#yearsCascadeSelection').val("0");
    			$('#monthsCascadeSelection').val("0");
    			$('#weeksCascadeSelection').val("0");
    			
    			if(el.value=="YEAR(S)"){
    				$('#yearsCascadeSelection').show();
        		}else if(el.value=="MONTH(S)"){
    				$('#monthsCascadeSelection').show();
        		}else if(el.value=="WEEK(S)"){
    				$('#weeksCascadeSelection').show();
    			}
    			
    		}
    		
    		function validateSubmit(){
    			
    			if($("#end-date-radio").is(":checked")){
    				document.getElementById("TX_DURATION").value = "DEFINED";
    			}else{
    				var timeframe = document.getElementById("durationSelectionCriteria").value;
    				var timeslot =  ""
    				if(timeframe=="YEAR(S)"){
    					timeslot = document.getElementById("durationYearsCascadeSelection").value;
            		}else if(timeframe=="MONTH(S)"){
            			timeslot = document.getElementById("durationMonthsCascadeSelection").value;
            		}else if(timeframe=="WEEK(S)"){
            			timeslot = document.getElementById("durationWeeksCascadeSelection").value;
        			}
    				document.getElementById("TX_DURATION").value = timeslot + " " + timeframe;
    			}
    			
    			var form = $("#EntitlementForm");
    			var data = getFormData(form);
    			
    			var rows=document.getElementById("tiers");
    			var rowObjects = rows.getElementsByClassName("row");
    			
    			var accountElement = document.getElementById("TX_ACCOUNT_EMAIL").value;
    			var entitlementTypeElement = document.getElementById("ID_ENTITLEMENT_TYPE").value;
    			var startDateElement = document.getElementById("TS_START").value;
    			var endDateElement = document.getElementById("TS_END").value;
    			var basetiername = document.getElementById("TX_TIER_NAME_0").value;
    			var basetiercost = document.getElementById("TX_TIER_PRICE_0").value;
    			
    			if(accountElement=="" || accountElement==null){
    				$("#errorElement").html("Account name is Required.");	
    			}else if(entitlementTypeElement=="" || entitlementTypeElement==null || entitlementTypeElement=="Choose..."){
    				$("#errorElement").html("Entitlement Type is Required.");	
    			}else if(startDateElement=="" || startDateElement==null){
    				$("#errorElement").html("Start Date is Required");	
    			}else if(endDateElement=="" || endDateElement==null){
    				$("#errorElement").html("End Date is Required.");	
    			}else if(basetiername=="" || basetiername==null){
    				$("#errorElement").html("You must specify the name of the tier.");	
    			}else if(basetiercost=="" || basetiercost==null){
    				$("#errorElement").html("You must specify the cost of the tier.");	
    			}else {
    				$("#errorElement").html("");
    				
    				var json = "{";
        			for(var i=0;i<rowObjects.length;i++){
        				if(i>0){
        					var row_json = ",";
        				}else{
        					var row_json = "\"rows\": [";	
        				}
        				
        				var id = rowObjects[i].children[0].firstElementChild.value
        				var key = rowObjects[i].children[1].firstElementChild.value
        				var value = rowObjects[i].children[2].firstElementChild.value
        				var limit = rowObjects[i].children[3].firstElementChild.value
        				row_json = row_json+ "{"
        					+"\"id\":\"" + id +"\","
        					+"\"key\":\""+key+"\","
        					+"\"value\":\""+value+"\","
        					+"\"limit\":\""+limit+"\""
        					+"}"
        				json = json + row_json;
        			}
        			json = json + "]}";
        			
        			console.log(json);
        			
    			//	document.getElementById("TX_TIER_JSON").value = data;
    				document.getElementById("TX_TIER_JSON").value = json;
        			document.getElementById("EntitlementForm").submit();
    			}
    		}
    		
    		function getFormData($form){
    		    var unindexed_array = $form.serializeArray();
    		    var indexed_array = {};

    		    $.map(unindexed_array, function(n, i){
    		        indexed_array[n['name']] = n['value'];
    		    });

    		    return indexed_array;
    		}
    		
    		
    		function addTier(){
    			var basecount = Number(document.getElementById("tierRowCount").value);
    			var count = basecount + 1 ;
    			var tiers = document.getElementById("tiers");
    			
    			// Creating a div element
    			var divElement = document.createElement("Div");
    			divElement.id = "row_"+count;

    			// Styling it
    			divElement.className = 'row table-active m-2 p-2';
    			
    			
    			var colZero = document.createElement("Div");
    			var idInput = document.createElement("input");
    			idInput.type = "text";
    			idInput.style.display = "none";
    			idInput.id = "TX_TIER_ID_"+count;
    			idInput.name = "TX_TIER_ID_"+count;
    			idInput.path = "TX_TIER_ID_"+count;
    			colZero.appendChild(idInput);
    			
    			
    			var colOne = document.createElement("Div");
    			colOne.className = 'col-md-5';
    			var titleInput = document.createElement("input");
    			titleInput.type = "text";
    			titleInput.className = 'form-control form-control-lg';
    			titleInput.placeholder = "Tier Name";
    			titleInput.id = "TX_TIER_NAME_"+count;
    			titleInput.name = "TX_TIER_NAME_"+count;
    			titleInput.path = "TX_TIER_NAME_"+count;
    			colOne.appendChild(titleInput);
    			
    			var colTwo = document.createElement("Div");
    			colTwo.className = 'col-md-3';
    			var costInput = document.createElement("input");
    			costInput.type = "text";
    			costInput.className = 'form-control form-control-lg';
    			costInput.placeholder = "Cost";
    			costInput.id = "TX_TIER_PRICE_"+count;
    			costInput.name = "TX_TIER_PRICE_"+count;
    			titleInput.path = "TX_TIER_PRICE_"+count;
    			colTwo.appendChild(costInput);
    			
    			var colThree = document.createElement("Div");
    			colThree.className = 'col-md-3';
    			var limitInput = document.createElement("input");
    			limitInput.type = "text";
    			limitInput.className = 'form-control form-control-lg';
    			limitInput.placeholder = "Limit";
    			limitInput.id = "TX_TIER_LIMIT_"+count;
    			limitInput.name = "TX_TIER_LIMIT_"+count;
    			limitInput.path = "TX_TIER_LIMIT_"+count;
    			colThree.appendChild(limitInput);
    			
    			var colFour = document.createElement("Div");
    			colFour.className = 'col-md-1';
    			var delBtn = document.createElement("Button");
    			delBtn.className = 'btn btn-danger';
    			delBtn.id = "DEL_TIER_"+count;
    			delBtn.innerHTML = '<i class="far fa-trash-alt"></i>';
    			delBtn.addEventListener("click", function(){
    			    removeRow(count);
    			});
    			colFour.appendChild(delBtn);
    			
    			divElement.appendChild(colZero);
    			divElement.appendChild(colOne);
    			divElement.appendChild(colTwo);
    			divElement.appendChild(colThree);
    			divElement.appendChild(colFour);

    		
    			tiers.appendChild(divElement);
    			
    			document.getElementById("tierRowCount").value = count;
    			
    		}
    		
    		function removeRow(count){
    			var rowElement = document.getElementById("row_"+count);
    			rowElement.remove();
    		}
    				
    		function validateRollable(el){
    			if (el.checked){
    				$('#rollableButton').show();
    				el.value = "1";
    			}else{
    				$('#rollableButton').hide();
    				el.value = "0";
    			}	
    		}
    		
    		function entitlementData(el){
    			if(el.value!="" && el.value!=null){
    				$('#typeDetailsDiv').show();
        			 $.ajax({  
  	                     url:"${contextPath}/admin/service/SEN/getEntitlementRollable",  
  	                     data:{key: el.value},  
  	                     success:function(data)  
  	                     {  
  	                    	 $('#EntitlementTypeRollableValue').html(data);
  	                    	 if(data=="TRUE"){
  	                    		 $('#optionRollableDiv').show();
  	                    	 }else{
  	                    		 var inputs = document.getElementById('ROLLABLE_FLAG');
  	                    	     inputs.checked = false;
  	                    	     $('#rollableButton').hide();
  	                    		 $('#optionRollableDiv').hide();
  	                    	 }
  	                     }  
  	                });
    			}else{
    				$('#typeDetailsDiv').hide();
    			}
    		}
    		
    		function updateSenFilter(el){
    			
				var filter = "";
				
				if(el.value=="ACTIVE"){
					filter="ACTIVE";
				}else if(el.value=="INACTIVE"){
					filter="INACTIVE";
				}else{
					filter="ALL";
				}
				
				document.getElementById("senFilterForm").action = "${contextPath}/admin/adminServiceEntitlements?filter="+filter+"&page=1";
				document.getElementById("senFilterForm").method="POST";
				document.getElementById("senFilterForm").submit();
			}
    		
   	      function getUserId(){  
   	    	   var userNameElement = document.getElementById("TX_ACCOUNT_EMAIL").value;
   	    	   if(userNameElement != ''&& userNameElement!=null)  
   	           {  
   	                $.ajax({  
   	                     url:"${contextPath}/admin/service/SEN/getUserAccount",  
   	                     data:{key: userNameElement},  
   	                     success:function(data)  
   	                     {  
   	                    	var res = data.toString().split(",");
   	                    	var items = "";
   	                    	for (var i in res) {
   	                    	 	items = items + "<li class='ajax-autocomplete-item'>"+res[i]+"</li>";
   	                    	}
   	                    	$('#ajax-list').show();
   	                    	$('#showList').html(items);  
   	                     }  
   	                });  
   	           }else{
   	        	$('#showList').html('');
   	        	$('#ajax-list').hide();
   	           }  
   	      }  
   	      $(document).on('click', 'li', function(){  
   	           $('#TX_ACCOUNT_EMAIL').val($(this).text());  
   	        	$('#showList').html('');
   	        	$('#ajax-list').hide();
   	      });  
   	   
   		</script> 
   		
   		
   	
   	</jsp:body>
</page:user-landing>