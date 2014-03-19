var URL_SECTION = 'grupo-movimento';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	loadGrid();
	//
	prepareLocais();
});

function checkFields(){
	var nome	= $('#nome').val();
	if(nome == undefined || nome == ''){
		addMessage(jQuery.i18n.prop('err_nome_required'), 'error');
		return false;
	}
	return true;
}

function clearFields(){
	$('#nome').val('');
    $('#anoInicio').val('');
    $('#anoFim').val('');
    $('#descricao').val('');

    $('#idLocais').val('');    
    $('#acLocalTxt').val('');
	$('#acLocalId').val('');	
    gruMovLstLocais = [];
    $('#gruMovLstLocais').html('');
}

function saveForm(){
	if(!checkFields())
		return;
	
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
    var nome		= $('#nome').val();
    var anoInicio	= $('#anoInicio').val();	    
    var anoFim		= $('#anoFim').val();
    var descricao	= $('#descricao').val();
    var idLocais	= $('#idLocais').val();
    	idLocais = JSON.parse("[" + idLocais + "]");
    
    var data = JSON.stringify({ "id" : id, "nome" : nome, "anoInicio" : anoInicio, "anoFim" : anoFim, 
    	"descricao" : descricao, "idLocais" : idLocais });
    
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
	var titles			= ['Nome', 'Ano Inicio', 'Ano Fim'];
	var columns_key		= ['nome', 'anoInicio', 'anoFim'];
	var columns_size	= ['70', '10', '10'];
	var columns_sort	= ['nome'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}

/////////
function prepareLocais(){
    $("#acLocalTxt").autocomplete({
        source: "local/search.html",
        select: function( event, ui ) {
            $( "#acLocalTxt" ).val( ui.item.label );
            $( "#acLocalId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#acLocalTxt" ).val( ui.item.label );
            return false;
        }
    });
}