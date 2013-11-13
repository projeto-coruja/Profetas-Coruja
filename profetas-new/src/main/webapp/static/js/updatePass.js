$(document).ready(function() {
	$("#updatePass").click(function(){
		updatePass();
	});
});

function checkPasswords(){
	var password = $('#passwordOrig').val();
	var retype_pass = $('#passwordCopy').val();
	
	if(password.trim() == ''){
		addMessage(jQuery.i18n.prop('err_pass_required'), 'error');
		return false;
	}
	if(password.length < MIN_PASSWORD_SIZE || password.length > MAX_PASSWORD_SIZE){
		addMessage(jQuery.i18n.prop('err_pass_size', MIN_PASSWORD_SIZE, MAX_PASSWORD_SIZE), 'error');
		return false;
	}
	if(password.trim() != retype_pass.trim()){
		addMessage(jQuery.i18n.prop('err_pass_dont_match'), 'error');
		return false;
	}
	return true;
}

function checkFieldsStepTwo(){
	if(!checkPasswords())
		return false;
	return true;
}

function updatePass(){
	if(!checkFieldsStepTwo())
		return;
	
	var url			= 'update-pass.html';
	var email		= $('#email').val();
	var password	= $('#passwordOrig').val();
	var activationCode = $('#activationCode').val();
	
	var data = JSON.stringify({ "email" : email, "password" : password, "activationCode" : activationCode });
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