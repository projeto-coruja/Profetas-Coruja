var URL_SECTION = 'grupo-movimento';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	fillLocals();
	loadGrid();
});

function fillLocals(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:URL_SECTION+'/local.html',
        success: function(data, textStatus, jqXHR){
        	var combo = $("#idLocais");
        	combo.empty();
            for (var i = 0; i < data.length; i++) {
            	var _place = data[i].place;
            	var str_place = '';
            	if(_place != undefined && _place != null)
            		str_place = ' ('+ _place+')';
            	combo.append('<option value="' + data[i].id + '">' + data[i].nome +str_place+ '</option>');
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
	console.log('riipahhh');
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
    var nome		= $('#nome').val();
    var anoInicio	= $('#anoInicio').val();	    
    var anoFim		= $('#anoFim').val();
    var descricao	= $('#descricao').val();
    var idLocais	= $('#idLocais').val();
    
var data = JSON.stringify({ "id" : id, "nome" : nome, "anoInicio" : anoInicio, "anoFim" : anoFim, "descricao" : descricao, "idLocais" : idLocais });
    
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
	console.log('ds7');
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
	var titles			= ['Nome', 'Ano Inicio', 'Ano Fim'];
	var columns_key		= ['nome', 'anoInicio', 'anoFim'];
	var columns_size	= ['70', '10', '10'];
	var columns_sort	= ['nome'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}