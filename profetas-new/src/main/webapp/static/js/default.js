const TXT_SUCCESS	= 'success';
const MIN_FULLNAME_SIZE	= 3;
const MAX_FULLNAME_SIZE	= 100;
const MIN_PASSWORD_SIZE = 5;
const MAX_PASSWORD_SIZE = 20;


$(document).ready(function() {
	loadMessages();		
});
/**
 * load the file bundle/messages.properties
 */
function loadMessages(){
	jQuery.i18n.properties({
	    name:'messages', 
	    path:'./bundle/', 
	    mode:'both'
	});	
}
/**
 * @returns: http://domain/context
 */
function urlContext(){
    var context = document.location.href;
    /*if(context == undefined)
    	context = window.location.href;*/
    context = context.substring(0, context.lastIndexOf('/')) + '/';
    return context;
}

function redirectToIndex() {
	window.location = urlContext() + "index.html";
	return false;
}

/**
 * @param name
 * @returns the param by name
 */
function getParameter(name){
    var regexS  = "[\\?&]"+name+"=([^&#]*)";
    var regex   = new RegExp( regexS );
    var tmpURL  = window.location.href;
    var results = regex.exec( tmpURL );
    if(results == null)
        return "";
    else
        return results[1];
}

function changeUrlWithoutReload(urlPath){
	window.history.pushState("", "", urlPath);
}

/* Messages */
var CorujaMessage = function() {
	this.message = '';
	this.id = new Date().getTime();
	this.type = 'default';
	this.render = function() {
		var typeclass = '';
		switch (this.type) {
		case 'error':
			typeclass = 'errMsg';
			break;
		case 'warn':
			typeclass = 'warnMsg';
			break;
		case 'info':
			typeclass = 'infoMsg';
			break;
		case 'success':
			typeclass = 'infoMsg';
			break;
		}
		var html = '<div id=' + this.id + ' class="corujaMsg ' + typeclass
				+ '">';
		html += '<p>' + this.message + '</p></div>';
		$('.corujaMessages').append(html);
		$('#' + this.id).fadeIn(400).delay(4000).fadeOut(400);
		window.setTimeout("removeMessage('" + this.id + "')", 4800);

	};
};

function removeMessage(id) {
	$('#' + id).remove();
}

// add a message to be shown for 4 seconds
var addMessage = function(msg, type) {	
	var marMsg = new CorujaMessage();
	marMsg.message = msg;
	marMsg.type = type;
	marMsg.render();
};