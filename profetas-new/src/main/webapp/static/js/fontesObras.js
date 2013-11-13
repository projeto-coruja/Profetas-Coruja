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
    $('#comentarios').val('');
    $('#idClassificacao').val('');
    
    $('#idGruMovimento').val('');
    $('#idLeitores').val();
    $('#idPersonagens').val();
    $('#idAutCitados').val();
    $('#idObrCitadas').val();
    $('#palavrasChave').val();
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
    var dataImpressao 		= $('#dataImpressao').val();
    var idLocalImpressao	= $('#idLocalImpressao').val();
    var idClassificacao 	= $('#idClassificacao').val();
    
    var idGruMovimento 	= $('#idGruMovimento').val();
    var idLeitores 		= $('#idLeitores').val();
    var idPersonagens 	= $('#idPersonagens').val();
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
    	"idLocalImpressao" : idLocalImpressao, "idClassificacao" : idClassificacao, 
    	"idGruMovimento" : idGruMovimento, "idLeitores" : idLeitores, "idPersonagens" : idPersonagens, 
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
	var titles			= ['Titulo'];
	var columns_key		= ['titulo'];
	var columns_size	= ['90'];
	var columns_sort	= ['titulo'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}

function fillPalavrasChave(strPalavrasChave){
	
}