var SEARCH_VIEW			= 'SEARCH_VIEW';
var SEARCH_PERSONAGEM	= 'personagem';
var SEARCH_FONTEOBRA	= 'fonte-obra';
var URL_SECTION 		= null;

$(document).ready(function() {
	$('#tabs').tabs();
	
	$("#searchPersonagem").click(function(){
		searchPersonagem();
	});
	
	$("#searchFonteObra").click(function(){
		searchFonteObra();
	});
});

function searchPersonagem() {
	var _text = $('#txtPersonagem').val();
	loadGrid(null, null, 1, _text, SEARCH_PERSONAGEM);
	URL_SECTION = 'personagem';
}

function searchFonteObra() {
	var _text = $('#txtFonteObra').val();
	loadGrid(null, null, 1, _text, SEARCH_FONTEOBRA);
	URL_SECTION = 'fontes-obras';
}

function loadGrid(orderBy, orderType, page, search_words, search_type){
	var div_id = 'container_grid';
	//var _orderBy	= 'id';
	var _orderType	= 'desc';
	var _page		= 1;
	if(orderBy != '' && orderBy != undefined) {_orderBy = orderBy;}
	if(orderType != '' && orderType != undefined) {_orderType = orderType;}
	if(page != '' && page != undefined) {_page = page;}
	
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'search/'+search_type+'.html',
        data:{
        	//orderBy: _orderBy,
        	orderType: _orderType,
        	page:_page,
        	searchBy: search_words
        },
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	buildGrid(div_id, data, search_type);
        }
    });
}

function buildGrid(div_id, data, grid_type){
	var titles			= [];
	var columns_key		= [];
	var columns_size	= [];
	var columns_sort	= [];
	
	if(grid_type == SEARCH_PERSONAGEM){
		titles			= ['Nome', 'Sobrenome'];
		columns_key		= ['nome', 'sobrenome'];
		columns_size	= ['45', '45'];
		columns_sort	= ['nome', 'sobrenome'];
	} else if(grid_type == SEARCH_FONTEOBRA){
		titles			= ['Autor', 'Titulo'];
		columns_key		= ['strAutor', 'titulo'];
		columns_size	= ['45', '45'];
		columns_sort	= ['strAutor', 'titulo'];
	} else { return; }
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data, grid_type);
}