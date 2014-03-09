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
	//
	prepareAutor();
    prepareLocais();
    prepareGrupoMovimento();
    prepareLeitores();
    prepareAutCitados();
    prepareObrCitadas();
});

function prepareAutor(){
	$("#strAutor").autocomplete({
        source: "personagem/search.html",
        select: function( event, ui ) {
            $( "#strAutor" ).val( ui.item.label );
            $( "#idAutor" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#strAutor" ).val( ui.item.label );
            return false;
        }
    });
}
//
function prepareLocais(){
    $("#acLocalImpressaoTxt").autocomplete({
        source: "local/search.html",
        select: function( event, ui ) {
            $( "#acLocalImpressaoTxt" ).val( ui.item.label );
            $( "#acLocalImpressaoId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#acLocalImpressaoTxt" ).val( ui.item.label );
            return false;
        }
    });
    
    $("#acLocalProducaoTxt").autocomplete({
        source: "local/search.html",
        select: function( event, ui ) {
            $( "#acLocalProducaoTxt" ).val( ui.item.label );
            $( "#acLocalProducaoId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#acLocalProducaoTxt" ).val( ui.item.label );
            return false;
        }
    });
}
//
function prepareGrupoMovimento(){
    $("#acGruMovimentoTxt").autocomplete({
        source: "grupo-movimento/search.html",
        select: function( event, ui ) {
            $( "#acGruMovimentoTxt" ).val( ui.item.label );
            $( "#acGruMovimentoId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#acGruMovimentoTxt" ).val( ui.item.label );
            return false;
        }
    });
}
//
function prepareLeitores(){
    $("#acLeitoresTxt").autocomplete({
        source: "personagem/search.html",
        select: function( event, ui ) {
            $( "#acLeitoresTxt" ).val( ui.item.label );
            $( "#acLeitoresId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#acLeitoresTxt" ).val( ui.item.label );
            return false;
        }
    });
}
//
function prepareAutCitados(){
    $("#acAutCitadosTxt").autocomplete({
        source: "personagem/search.html",
        select: function( event, ui ) {
            $( "#acAutCitadosTxt" ).val( ui.item.label );
            $( "#acAutCitadosId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#acAutCitadosTxt" ).val( ui.item.label );
            return false;
        }
    });
}
//
function prepareObrCitadas(){
    $("#acObrCitadasTxt").autocomplete({
        source: "fontes-obras/search.html",
        select: function( event, ui ) {
            $( "#acObrCitadasTxt" ).val( ui.item.label );
            $( "#acObrCitadasId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#acObrCitadasTxt" ).val( ui.item.label );
            return false;
        }
    });
}
//

function clearFields(){
	$('#localizacao').val('');
	$('#idAutor').val('');
	$('#strAutor').val('');
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
    //
    $('#strAutor').val('');
	$('#idAutor').val('');

	$('#acLocalImpressaoTxt').val('');
	$('#acLocalImpressaoId').val('');
	$('#fontLstLocalImpressao').html('');
	
	$('#acLocalProducaoTxt').val('');
	$('#acLocalProducaoId').val('');
	$('#fontLstLocalProducao').html('');
	
	$('#acGruMovimentoTxt').val('');
	$('#acGruMovimentoId').val('');
	$('#fontLstGruMovimento').html('');
	
	$('#acLeitoresTxt').val('');
	$('#acLeitoresId').val('');
	$('#fontLstLeitores').html('');
	
	$('#acAutCitadosTxt').val('');
	$('#acAutCitadosId').val('');
	$('#fontLstAutCitados').html('');
	
	$('#acObrCitadasTxt').val('');
	$('#acObrCitadasId').val('');
	$('#fontLstObrCitadas').html('');
	//
    fontLstLocalImpressao = [];
    fontLstLocalProducao = [];
    fontLstGruMovimento = [];
    fontLstLeitores = [];
    fontLstAutCitados = [];
    fontLstObrCitadas = [];
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
	var idAutor		= $('#idAutor').val();
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
    	idLeitores = JSON.parse("[" + idLeitores + "]");
    var idAutCitados 	= $('#idAutCitados').val();
    	idAutCitados = JSON.parse("[" + idAutCitados + "]");
    var idObrCitadas 	= $('#idObrCitadas').val();
    	idObrCitadas = JSON.parse("[" + idObrCitadas + "]");
    
	    var $keywords = $("#palavrasChave").siblings(".tagsinput").children(".tag");  
	    var tags = [];  
	    for (var i = $keywords.length; i--;) {  
	        tags.push($($keywords[i]).text().substring(0, $($keywords[i]).text().length -  1).trim());  
	    }
	var palavrasChave 	= tags;    
    
    var data = JSON.stringify({ "id" : id, "titulo" : titulo, "localizacao" : localizacao, "idAutor" : idAutor,
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
	var columns_key		= ['strAutor', 'titulo'];
	var columns_size	= ['45', '45'];
	var columns_sort	= ['strAutor', 'titulo'];
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