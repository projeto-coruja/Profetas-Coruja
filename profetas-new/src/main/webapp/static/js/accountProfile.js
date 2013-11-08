var URL_SECTION = 'account-profile';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	loadGrid();
});

function checkFields(){
	var idProfile	= $('#idProfile').val();
	if(idProfile == undefined || idProfile == ''){
		addMessage(jQuery.i18n.prop('err_profile_required'), 'error');
		return false;
	}
	return true;
}

function saveForm(){
	if(!checkFields())
		return;
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
	var idProfile	= $('#idProfile').val();
    var data = JSON.stringify({ "id" : id, "idProfile" : idProfile });
    $.ajax({
        url : url,
        type : "POST",
        traditional : true,
        contentType : "application/json",
        dataType : "json",
        data : data,
        success : function(data) {
        	if(TXT_SUCCESS == data.type.toLowerCase()){
        		addMessage(data.message, 'sucess');
        		loadGrid();
        	} else{
        		addMessage(data.message, 'error');
        	}
        },
        error : function (response) {
        	addMessage(jQuery.i18n.prop('msg_internal_server_error'), 'error');
        },
    });	    
    return false;
}

function buildGrid(div_id, data){
	var titles			= ['Nome', 'Email', 'Perfil'];
	var columns_key		= ['fullName', 'email', 'profileName'];
	var columns_size	= ['40', '35', '15'];
	var columns_sort	= ['fullName', 'email', 'profileName'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}