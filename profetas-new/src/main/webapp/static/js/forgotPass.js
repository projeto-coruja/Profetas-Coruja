$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
});

function checkEmail(){
	var email = $('#email').val();
	if(email.trim() == '' || !validateEmail()){
		addMessage(jQuery.i18n.prop('err_email_invalid'), 'error');
		return false;
	}		
	return true;
}
function validateEmail(){	
	return true;
}

function checkFieldsStepOne(){
	if(!checkEmail())
		return false;
	return true;
}

function saveForm(){
	if(!checkFieldsStepOne())
		return;
	
	var url			= 'forgot-pass.html';
	var email		= $('#email').val();
	
	var data = JSON.stringify({ "email" : email });
    $.ajax({
        url : url,
        type : "POST",
        traditional : true,
        contentType : "application/json",
        dataType : "json",
        data : data,
        success : function(data) {
        	if(data == null)
        		return;
        	
        	if(TXT_SUCCESS == data.type.toLowerCase()){
        		$('#email').val('');
        		addMessage(data.message, 'success');
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