var URL_SECTION = 'personagem';
	
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
        	var _loc_nascimento = $("#idNascimento");
        	_loc_nascimento.empty();
        	_loc_nascimento.append('<option value="-1">Selecione um</option>');
        	
        	var _loc_morte = $("#idMorte");
        	_loc_morte.empty();
        	_loc_morte.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	_loc_nascimento.append('<option value="' + data[i].id + '">' + data[i].nome +'</option>');
            	_loc_morte.append('<option value="' + data[i].id + '">' + data[i].nome +'</option>');
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
	var nome		= $('#nome').val();
	var apelido		= $('#apelido').val();
    var idNascimento	= $('#idNascimento').val();
    var dataNascimento	= $('#dataNascimento').val();
    var idMorte 	= $('#idMorte').val();
    var dataMorte	= $('#dataMorte').val();
    var biografia	= $('#biografia').val();
    var ocupacao	= $('#ocupacao').val();
    var formacao	= $('#formacao').val();
    
    var data = JSON.stringify({ "id" : id, "nome" : nome, "apelido" : apelido, 
    	"idNascimento" : idNascimento, "dataNascimento" : dataNascimento, "idMorte" : idMorte, "dataMorte" : dataMorte, 
    	"biografia" : biografia, "ocupacao" : ocupacao, "formacao" : formacao });
    
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
	var titles			= ['Nome', 'Apelido'];
	var columns_key		= ['nome', 'apelido'];
	var columns_size	= ['45', '45'];
	var columns_sort	= ['nome', 'apelido'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}