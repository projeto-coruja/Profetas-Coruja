var URL_SECTION = 'correspondencia';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	loadGrid();
});

function fillRemAndDes(idRem, idDes){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'personagens.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var idRemetente = $('#idRemetente');
        	idRemetente.empty();
        	idRemetente.append('<option value="-1">Selecione um</option>');
        	
        	var idDestinatario = $('#idDestinatario');
        	idDestinatario.empty();
        	idDestinatario.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	var _sel_Rem = '';
            	if(parseInt(idRem) == data[i].id){ _sel_Rem = ' selected="selected"'; }
            	idRemetente.append('<option value="' + data[i].id+'"'+ _sel_Rem+'>' + data[i].nome + '</option>');

            	var _sel_Des = '';
            	if(parseInt(idDes) == data[i].id){ _sel_Des = ' selected="selected"'; }
            	idDestinatario.append('<option value="' + data[i].id+'"'+ _sel_Des+'>' + data[i].nome + '</option>');
            }
        }
    });
}

function checkFields(){
	var idRemetente	= $('#idRemetente').val();
	var idDestinatario = $('#idDestinatario').val();
	if(idRemetente == undefined || idRemetente == '' || idRemetente == -1){
		addMessage(jQuery.i18n.prop('err_remetente_required'), 'error');
		return false;
	}
	if(idDestinatario == undefined || idDestinatario == '' || idDestinatario == -1){
		addMessage(jQuery.i18n.prop('err_destinatario_required'), 'error');
		return false;
	}
	return true;
}

function clearFields(){
	$('#idRemetente').val('');
    $('#idDestinatario').val('');
    $('#idLocal').val('');
    $('#data').val('');
}

function saveForm(){
	if(!checkFields())
		return;
		
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
	var idRemetente	= $('#idRemetente').val();
	var idDestinatario = $('#idDestinatario').val();
    var idLocal	= $('#idLocal').val();
    var _data	= $('#data').val();
    
    var data = JSON.stringify({ "id" : id, "idRemetente" : idRemetente, "idDestinatario" : idDestinatario, 
    	"idLocal" : idLocal, "data" : _data });
    
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
        		addMessage(data.message, 'sucess');
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
	var titles			= ['Remetente', 'Destinatário', 'Local', 'Data'];
	var columns_key		= ['nomeRemetente', 'nomeDestinatario', 'nomeLocal', 'data'];
	var columns_size	= ['30', '30', '20', '10'];
	var columns_sort	= ['nomeRemetente', 'nomeDestinatario'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}