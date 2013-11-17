var URL_SECTION = 'encontro';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	loadGrid();
});

function checkFields(){
	var nome	= $('#nome').val();
	var _data	= $('#data').val();
	if(nome == undefined || nome == ''){
		addMessage(jQuery.i18n.prop('err_nome_required'), 'error');
		return false;
	}
	if(_data == undefined || _data == ''){
		addMessage(jQuery.i18n.prop('err_data_required'), 'error');
		return false;
	}
	return true;
}

function clearFields(){
	$('#nome').val('');
    $('#data').val('');
    $('#idPersonagem').val('');
    $('#idLocal').val('');
}

function saveForm(){
	if(!checkFields())
		return;
		
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
	var nome		= $('#nome').val();
	var _data		= $('#data').val();
	var idPersonagem = $('#idPersonagem').val();
    var idLocal		= $('#idLocal').val();
    
    var data = JSON.stringify({ "id" : id, "nome" : nome, "data" : _data, "idPersonagem" : idPersonagem, "idLocal" : idLocal });
    
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
	var titles			= ['Nome', 'Data'];
	var columns_key		= ['nome', 'data'];
	var columns_size	= ['45', '45'];
	var columns_sort	= ['nome', 'data'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}