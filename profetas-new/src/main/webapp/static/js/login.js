$(document).ready(function() {
	var _error = getParameter('error');
	if(_error === 'true'){
		$('#err_login').html('Usuario/Senha incorreto.');
		changeUrlWithoutReload('index.html');
	}
});