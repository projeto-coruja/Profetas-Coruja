$(document).ready(function() {
	fillProfileList();
	$("#saveUser").click(function(){
		if(!checkFields())
			return;
		
	    var url		= 'account/save.html';
	    var fullName	= $('#fullName').val();
	    var email		= $('#email').val();	    
	    var password	= $('#passwordOrig').val();
	    
	    var data = JSON.stringify({ "fullName" : fullName, "email" : email, "password" : password });
	    $.ajax({
	        url : url,
	        type : "POST",
	        traditional : true,
	        contentType : "application/json",
	        dataType : "json",
	        data : data,
	        success : function(data) {
	        	if(TXT_SUCCESS == data.type.toLowerCase()){
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
	});
});

function checkFields(){
	if(!checkFullName())
		return false;
	if(!checkEmail())
		return false;
	if(!checkPasswords())
		return false;
	return true;
}

function checkFullName(){
	var fullName	= $('#fullName').val();
	if(fullName.trim() == ''){
		addMessage(jQuery.i18n.prop('err_fullname_required'), 'error');
		return false;
	}
	if(fullName.length < MIN_FULLNAME_SIZE || fullName.length > MAX_FULLNAME_SIZE){
		addMessage(jQuery.i18n.prop('err_fullname_size', MIN_FIRSTNAME_SIZE, MAX_FIRSTNAME_SIZE), 'error');
		return false;
	}
	return true;
}

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