var room = 0;
var config = new Array();
var fl_error = false;


var room_new = 0;
var config_new = new Array();
var fl_error_new = false;


function education_fields() {
	var element = document.getElementById("TX_TYPE");
	if (element.value=="-"){
		document.getElementById("sa-basic").click();
	}
    room++;
    var objTo = document.getElementById('education_fields')
    var divtest = document.createElement("div");
    divtest.setAttribute("class", "form-group removeclass" + room);
    var rdiv = 'removeclass' + room;
    
    var cmd;
    var cls;
    if (element.value=="SCRIPT"){
    	if(document.getElementById("TX_COMMAND").value==""){
    		cmd="No data";
			cls="text-warning";
    	}else{
    		cmd = document.getElementById("TX_COMMAND").value;
        	cls="";	
    	}
    	
    }else if (element.value=="FILE"){
    	if(document.getElementById("TX_FILE").value==""){
    		cmd="No data";
			cls="text-warning";
    	}else{
    		cmd = document.getElementById("TX_FILE").value;
        	cls="";
    	}
    }else{
    	cmd = "ERROR";
    	cls="text-danger";
    }
    
    divtest.innerHTML = '<div class="row table-active p-2">'
    						+'<div class="col-md-10">'
    							+ '<div class="row">'
									+ '<div class="col-sm-5">'
										+ '<div class="form-group">'
											+'<label id="config['+room+'].TX_CONFIG_NAME" name="config['+room+'].TX_CONFIG_NAME" >'+document.getElementById("TX_CONFIG_NAME").value+'</label>'
										+'</div>'
									+'</div>'
									+'<div class="col-sm-5"> '
										+'<div class="form-group"> '
											+'<label id="config['+room+'].TX_TYPE" name="config['+room+'].TX_TYPE" >'+document.getElementById("TX_TYPE").value+'</label > '
										+'</div>'
									+'</div>'
								+'</div>'
								+'<div class="row">'
									+'<span class="'+cls+'">'+cmd+'</span>'
								+'</div>'
							+'</div>'
							+'<div class="col-md-2 text-right">'
								+ ' <div class="form-group"> '
									+'<button class="btn btn-danger" type="button" onclick="remove_education_fields(' + room + ');"> <i class="fa fa-minus"></i> </button> '
								+ '</div>'
							+'</div>'
						+'</div>';

    objTo.appendChild(divtest)
    config.push({"rank": room,"tx_CONFIG_NAME": document.getElementById("TX_CONFIG_NAME").value,"tx_TYPE":document.getElementById("TX_TYPE").value,"tx_COMMAND": document.getElementById("TX_COMMAND").value,"tx_FILE":document.getElementById("TX_FILE").value});
    document.getElementById("TX_CONFIG_NAME").value = "";
    document.getElementById("TX_COMMAND").value = "";
    document.getElementById("TX_FILE").value = "";
    document.getElementById("TX_TYPE").value = "-";
    document.getElementById("script-div").style.display = "none";
    document.getElementById("file-div").style.display = "none";
}

function remove_education_fields(rid) {
    $('.removeclass' + rid).remove();
    for( var i = 0; i < config.length; i++){
    	   if ( config[i]["RANK"] === rid) {
    		   config.splice(i, 1); 
    	   }
    	}
}

function validateAdd(){
	var toolNameElement = document.getElementById("TX_DEVOPS_TOOL_NAME");
	var sourceElement =  document.getElementById("TX_DEVOPS_TOOL_DOWNLOAD_LOC");
	var portElement =  document.getElementById("TX_PORT");
	var toolCatElement =  document.getElementById("ID_CATEGORY");
	var addErrorElement =  document.getElementById("form-add-error");
	
	var formAddElement = document.getElementById("addToolsForm");
	
	var errMsg = "";
	if(toolNameElement.value==null || toolNameElement.value==""){
		errMsg = "Please enter a name for the tool."
	}else if(sourceElement.value==null || sourceElement.value==""){
		errMsg = "Please enter the docker repository location."
	}else if(portElement.value==null || portElement.value==""){
		errMsg = "Please enter the detaulf port number for the tool."
	}else if(toolCatElement.value==null || toolCatElement.value=="" || toolCatElement.value=="-"){
		errMsg = "Please select a category."
	}else{
		errMsg="";
	}
	if(fl_error==true){
		errMsg = "Please correct any configuration errors.";
	}
	if(errMsg==""){
		document.getElementById("TX_CONFIG").value = JSON.stringify(config);
		formAddElement.submit();
	}else{
		addErrorElement.innerHTML = errMsg;
	}
}


function updateInput(element){
	if (element.value=="SCRIPT"){
		document.getElementById("script-div").style.display = "block";
		document.getElementById("file-div").style.display = "none";
		document.getElementById("TX_COMMAND").value="";
		document.getElementById("TX_FILE").value="";
	}else if (element.value=="FILE"){
		document.getElementById("script-div").style.display = "none";
		document.getElementById("file-div").style.display = "block";
		document.getElementById("TX_COMMAND").value="";
		document.getElementById("TX_FILE").value="";
	}else{
		document.getElementById("script-div").style.display = "none";
		document.getElementById("file-div").style.display = "none";
	}
}


$(window).on("load", function() {
	document.getElementById("file-div").style.display = "none";
	document.getElementById("script-div").style.display = "none";
	document.getElementById("file-div-new").style.display = "none";
	document.getElementById("script-div-new").style.display = "none";
});




!function($) {
    "use strict";

    var SweetAlert = function() {};

    //examples 
    SweetAlert.prototype.init = function() {
        
    //Basic
    $('#sa-basic').click(function(){
        swal("There is an error in your configuration. Please fix!");
    });

},
//init
$.SweetAlert = new SweetAlert, $.SweetAlert.Constructor = SweetAlert
}(window.jQuery),

//initializing 
function($) {
"use strict";
$.SweetAlert.init()
}(window.jQuery);





function modify_Config(row) {
	var rowActionElement = 'toolConfigAction_'+row;
	document.getElementById(rowActionElement).value="DELETE";
    
	var rowInfoElement = 'toolConfigActionLabel_'+row;
    document.getElementById(rowInfoElement).innerHTML="Marked for Deletion";
    document.getElementById("deleteConfig_"+row).style.display = "none";
    document.getElementById("resetConfig_"+row).style.display = "block";
}

function reset_Config(row){
	var rowInfoElement = 'toolConfigActionLabel_'+row;
	var rowActionElement = 'toolConfigAction_'+row;
	document.getElementById(rowActionElement).value="";
	document.getElementById(rowInfoElement).innerHTML="";
	document.getElementById("deleteConfig_"+row).style.display = "block";
    document.getElementById("resetConfig_"+row).style.display = "none";
}

function fieldUpdated(row){
	var rowActionElement = 'toolConfigAction_'+row;
	document.getElementById(rowActionElement).value="UPDATE";
    
	var rowInfoElement = 'toolConfigActionLabel_'+row;
    document.getElementById(rowInfoElement).innerHTML="Updated";
    document.getElementById("deleteConfig_"+row).style.display = "block";
    document.getElementById("resetConfig_"+row).style.display = "none";
}

function updateToolConfig(toolId, confCount){
	var conf = new Array();
	var formUpdateConfigElement = document.getElementById("toolConfigUpdateForm_"+toolId);
	var cmdTxt;
	var scriptTxt;
	for (var i=0;i<confCount;i++){
		var rowActionElement = 'toolConfigAction_'+i;
		var action = document.getElementById(rowActionElement).value;
		
		if(document.getElementById("toolConfigCommand_"+i)!=null){
			cmdTxt = document.getElementById("toolConfigCommand_"+i).value;
		}
		
		if(document.getElementById("toolConfigScript_"+i)!=null){
			scriptTxt = document.getElementById("toolConfigScript_"+i).value;
		}
		
		conf.push({"rank": i,"id": document.getElementById("toolConfigId_"+i).value,"tx_COMMAND": scriptTxt, "tx_FILE":cmdTxt,"action":action});
	}
	var configuration = conf.concat(config_new);
	document.getElementById("TX_CONFIG_EDIT_"+toolId).value = JSON.stringify(configuration);
	formUpdateConfigElement.submit();
}



function config_fields() {
	var element = document.getElementById("TX_TYPE_NEW");
	if (element.value=="-"){
		document.getElementById("sa-basic-new").click();
	}
    room_new++;
    var objTo = document.getElementById('setup_fields')
    var divtest = document.createElement("div");
    divtest.setAttribute("class", "form-group removeclassnew" + room_new);
    var rdiv = 'removeclass' + room_new;
    
    var cmd;
    var cls;
    if (element.value=="SCRIPT"){
    	if(document.getElementById("TX_COMMAND_NEW").value==""){
    		cmd="No data";
			cls="text-warning";
    	}else{
    		cmd = document.getElementById("TX_COMMAND_NEW").value;
        	cls="";	
    	}
    	
    }else if (element.value=="FILE"){
    	if(document.getElementById("TX_FILE_NEW").value==""){
    		cmd="No data";
			cls="text-warning";
    	}else{
    		cmd = document.getElementById("TX_FILE_NEW").value;
        	cls="";
    	}
    }else{
    	cmd = "ERROR";
    	cls="text-danger";
    }
    
    divtest.innerHTML = '<div class="row table-active p-2">'
    						+'<div class="col-md-10">'
    							+ '<div class="row">'
									+ '<div class="col-sm-5">'
										+ '<div class="form-group">'
											+'<label id="config_new['+room_new+'].TX_CONFIG_NAME" name="config_new['+room_new+'].TX_CONFIG_NAME" >'+document.getElementById("TX_CONFIG_NAME_NEW").value+'</label>'
										+'</div>'
									+'</div>'
									+'<div class="col-sm-5"> '
										+'<div class="form-group"> '
											+'<label id="config_new['+room_new+'].TX_TYPE" name="config_new['+room_new+'].TX_TYPE" >'+document.getElementById("TX_TYPE_NEW").value+'</label > '
										+'</div>'
									+'</div>'
								+'</div>'
								+'<div class="row">'
									+'<span class="'+cls+'">'+cmd+'</span>'
								+'</div>'
							+'</div>'
							+'<div class="col-md-2 text-right">'
								+ ' <div class="form-group"> '
									+'<button class="btn btn-danger" type="button" onclick="remove_setup_fields(' + room_new + ');"> <i class="fa fa-minus"></i> </button> '
								+ '</div>'
							+'</div>'
						+'</div>';

    objTo.appendChild(divtest)
    config_new.push({"rank": room_new,"action":"INSERT","id_DEVOPS_TOOL":document.getElementById("ID_DEVOPS_TOOL_NEW").value,"tx_CONFIG_NAME": document.getElementById("TX_CONFIG_NAME_NEW").value,"tx_TYPE":document.getElementById("TX_TYPE_NEW").value,"tx_COMMAND": document.getElementById("TX_COMMAND_NEW").value,"tx_FILE":document.getElementById("TX_FILE_NEW").value});
    document.getElementById("TX_CONFIG_NAME_NEW").value = "";
    document.getElementById("TX_COMMAND_NEW").value = "";
    document.getElementById("TX_FILE_NEW").value = "";
    document.getElementById("TX_TYPE_NEW").value = "-";
    document.getElementById("script-div-new").style.display = "none";
    document.getElementById("file-div-new").style.display = "none";
}

function remove_setup_fields(rid) {
    $('.removeclassnew' + rid).remove();
    for( var i = 0; i < config_new.length; i++){
    	   if ( config_new[i]["RANK"] === rid) {
    		   config_new.splice(i, 1); 
    	   }
    	}
}

function updateInputNew(element){
	if (element.value=="SCRIPT"){
		document.getElementById("script-div-new").style.display = "block";
		document.getElementById("file-div-new").style.display = "none";
		document.getElementById("TX_COMMAND_NEW").value="";
		document.getElementById("TX_FILE_NEW").value="";
	}else if (element.value=="FILE"){
		document.getElementById("script-div-new").style.display = "none";
		document.getElementById("file-div-new").style.display = "block";
		document.getElementById("TX_COMMAND_NEW").value="";
		document.getElementById("TX_FILE_NEW").value="";
	}else{
		document.getElementById("script-div-new").style.display = "none";
		document.getElementById("file-div-new").style.display = "none";
	}
}

