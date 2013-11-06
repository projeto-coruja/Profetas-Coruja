var URL_SECTION = 'correspondencia';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	fillLocals();
	fillPersonagems();
	loadGrid();
});

function fillLocals(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:URL_SECTION+'/local.html',
        success: function(data, textStatus, jqXHR){
        	var combo = $("#idLocal");
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].nome + '</option>');
            }
        }
    });
}

function fillPersonagems(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:URL_SECTION+'/personagem.html',
        success: function(data, textStatus, jqXHR){
        	var idRemetente = $('#idRemetente');
        	idRemetente.empty();
        	idRemetente.append('<option value="-1">Selecione um</option>');
        	
        	var idDestinatario = $('#idDestinatario');
        	idDestinatario.empty();
        	idDestinatario.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	idRemetente.append('<option value="' + data[i].id + '">' + data[i].nome + '</option>');
            	idDestinatario.append('<option value="' + data[i].id + '">' + data[i].nome + '</option>');
            }
        }
    });
}

function updateForm(id){
	window.location.href = URL_SECTION+'.html?id='+id;
}
function deleteForm(id){
	var url = URL_SECTION+'/delete.html';
	var data = JSON.stringify({ "id" : id });
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
}

function saveForm(){
	console.log('save');
	
	//if(!checkFields())
		//return;
		
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

function loadGrid(orderBy, orderType, page, search_words){
	var div_id = 'container_grid';
	var _orderBy	= 'id';
	var _orderType	= 'desc';
	var _page		= 1;
	if(orderBy != '' && orderBy != undefined) {_orderBy = orderBy;}
	if(orderType != '' && orderType != undefined) {_orderType = orderType;}
	if(page != '' && page != undefined) {_page = page;}
	
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:URL_SECTION+'/list.html',
        data:{
        	orderBy: _orderBy,
        	orderType: _orderType,
        	page:_page,
        	searchBy: search_words
        },
        success: function(data, textStatus, jqXHR){         	
        	buildGrid(div_id, data);
        }
    });
}

function buildGrid(div_id, data){
	var titles			= ['Remetente', 'Destinatário', 'Local', 'Data'];
	var columns_key		= ['nomeRemetente', 'nomeDestinatario', 'nomeLocal', 'data'];
	var columns_size	= ['30', '30', '20', '10'];
	var columns_sort	= ['nomeRemetente', 'nomeDestinatario'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}