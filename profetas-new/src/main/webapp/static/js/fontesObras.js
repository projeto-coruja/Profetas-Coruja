var URL_SECTION = 'fontes-obras';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	fillLocals();
	fillClassificacoes();
	loadGrid();
});

function fillLocals(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:URL_SECTION+'/local.html',
        success: function(data, textStatus, jqXHR){
        	var combo = $("#idLocalImpressao");
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].nome + '</option>');
            }
        }
    });
}

function fillClassificacoes(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:URL_SECTION+'/classificacao.html',
        success: function(data, textStatus, jqXHR){
        	var combo = $("#idClassificacao");
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].tipo + '</option>');
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
	var titulo		= $('#titulo').val();
    var referenciasCirculacaoObra = $('#referenciasCirculacaoObra').val();
    var comentarios	= $('#comentarios').val();
    var url_fontes	= $('#url').val();
    var copiasManuscritas 	= $('#copiasManuscritas').val();    
    var traducoes	= $('#traducoes').val();
    var editor		= $('#editor').val();
    var dataImpressao 		= $('#dataImpressao').val();
    var idLocalImpressao	= $('#idLocalImpressao').val();
    var idClassificacao 	= $('#idClassificacao').val();
    
    var data = JSON.stringify({ "id" : id, "titulo" : titulo, "referenciasCirculacaoObra" : referenciasCirculacaoObra, 
    	"comentarios" : comentarios, "url" : url_fontes, "copiasManuscritas" : copiasManuscritas, 
    	"traducoes" : traducoes, "editor" : editor, "dataImpressao" : dataImpressao, 
    	"idLocalImpressao" : idLocalImpressao, "idClassificacao" : idClassificacao });
    
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
	var titles			= ['Titulo'];
	var columns_key		= ['titulo'];
	var columns_size	= ['90'];
	var columns_sort	= ['titulo'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}