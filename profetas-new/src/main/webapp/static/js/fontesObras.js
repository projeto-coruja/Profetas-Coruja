var URL_SECTION = 'fontes-obras';
	
$(document).ready(function() {
	$('#palavrasChave').tagsInput({
	   'height':'30px',
	   'width':'395px',
	   'placeholderColor' : '#666666'
	});
	
	$("#saveForm").click(function(){
		saveForm();
	});
	loadGrid();
});

function clearFields(){
	$('#localizacao').val('');
	$('#autor').val('');
	$('#titulo').val('');
    $('#referenciasCirculacaoObra').val('');
    $('#comentarios').val('');
    $('#url').val('');
    $('#copiasManuscritas').val('');
    $('#traducoes').val('');
    $('#editor').val('');
    $('#dataImpressao').val('');
    $('#idLocalImpressao').val('');
    $('#produtor').val('');
    $('#dataProducao').val('');
    $('#idLocalProducao').val('');
    $('#comentarios').val('');
    $('#idClassificacao').val('');
    
    $('#idGruMovimento').val('');
    $('#idLeitores').val('');
    $('#idAutCitados').val('');
    $('#idObrCitadas').val('');
    $('#palavrasChave').val('');
    $('.tag').remove();
}

function checkFields(){
	var titulo	= $('#titulo').val();
	if(titulo == undefined || titulo == ''){
		addMessage(jQuery.i18n.prop('err_titulo_required'), 'error');
		return false;
	}
	return true;
}

function saveForm(){
	if(!checkFields())
		return;
	
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
	var localizacao	= $('#localizacao').val();
	var autor		= $('#autor').val();
	var titulo		= $('#titulo').val();
	var referenciaCompleta = $('#referenciaCompleta').val();
    var referenciasCirculacaoObra = $('#referenciasCirculacaoObra').val();
    var comentarios	= $('#comentarios').val();
    var url_fontes	= $('#url').val();
    var copiasManuscritas 	= $('#copiasManuscritas').val();    
    var traducoes	= $('#traducoes').val();
    var editor		= $('#editor').val();
    var dataImpressao 	= $('#dataImpressao').val();
    var idLocalImpressao	= $('#idLocalImpressao').val();
    var produtor		= $('#produtor').val();
    var dataProducao 	= $('#dataProducao').val();
    var idLocalProducao	= $('#idLocalProducao').val();
    var idClassificacao 	= $('#idClassificacao').val();
    
    var idGruMovimento 	= $('#idGruMovimento').val();
    var idLeitores 		= $('#idLeitores').val();
    var idAutCitados 	= $('#idAutCitados').val();
    var idObrCitadas 	= $('#idObrCitadas').val();
    
	    var $keywords = $("#palavrasChave").siblings(".tagsinput").children(".tag");  
	    var tags = [];  
	    for (var i = $keywords.length; i--;) {  
	        tags.push($($keywords[i]).text().substring(0, $($keywords[i]).text().length -  1).trim());  
	    }
	var palavrasChave 	= tags;    
    
    var data = JSON.stringify({ "id" : id, "titulo" : titulo, "localizacao" : localizacao, "autor" : autor,
    	"referenciaCompleta" : referenciaCompleta, "referenciasCirculacaoObra" : referenciasCirculacaoObra, 
    	"comentarios" : comentarios, "url" : url_fontes, "copiasManuscritas" : copiasManuscritas, 
    	"traducoes" : traducoes, "editor" : editor, "dataImpressao" : dataImpressao, 
    	"idLocalImpressao" : idLocalImpressao, "produtor" : produtor, "dataProducao" : dataProducao, 
    	"idLocalProducao" : idLocalProducao, "idClassificacao" : idClassificacao, 
    	"idGruMovimento" : idGruMovimento, "idLeitores" : idLeitores, 
    	"idAutCitados" : idAutCitados, "idObrCitadas" : idObrCitadas, "palavrasChave" : palavrasChave });
    
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
	var titles			= ['Autor', 'Titulo'];
	var columns_key		= ['autor', 'titulo'];
	var columns_size	= ['45', '45'];
	var columns_sort	= ['autor', 'titulo'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}

function fillPalavrasChave(strPalavrasChave){
	if(strPalavrasChave != undefined && strPalavrasChave != ''){
		var palavras_size = strPalavrasChave.length;
		var str_items = strPalavrasChave.substring(1, palavras_size - 1);
		$('#palavrasChave').val(str_items);
	}
}

function makeReadonly(){
	$('#fontes :input').prop('readonly', true);
	$('#editForm').prop('hidden', false);
	$('#saveForm').prop('hidden', true);
}

function reloadEdit(id){
	window.location.href = URL_SECTION+'.html?id='+id;
}