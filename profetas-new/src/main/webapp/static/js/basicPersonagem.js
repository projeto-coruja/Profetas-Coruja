$(document).ready(function() {
	$("#saveBasicForm").click(function(){
		saveBasicForm();
	});
});

function clearFields(){
	$('#p_nome').val('');
	$('#p_sobrenome').val('');
    $('#p_apelido').val('');
}

function saveBasicForm(){
	var url			= 'personagem/save.html';
	var nome		= $('#p_nome').val();
	var sobrenome	= $('#p_sobrenome').val();
	var apelido		= $('#p_apelido').val();
	var data = JSON.stringify({ "nome" : nome, "sobrenome" : sobrenome, "apelido" : apelido });

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
        		addMessage(data.message, 'success');
        		if(id == undefined || id == '') { clearFields(); }        		
        		addPersonagemToSelect(data.data, nome, sobrenome, apelido);
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

function addPersonagemToSelect(id, nome, sobrenome, apelido){
	var divId = $('#divId').val();
	var combo = $('#'+divId);
	var _selected = ' selected="selected"';
	var _apelido = apelido != '' ? '('+apelido+')' : '';
	var description = sobrenome + ' ' + nome + _apelido; 
	var str = '<option value="' + id+'"'+ _selected+'>' + description +'</option>';
	combo.append(str);
	//$("#popup-personagem").dialog('close');
}