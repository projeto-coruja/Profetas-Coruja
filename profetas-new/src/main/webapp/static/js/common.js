function getStrSelected(){
	return ' selected="selected"';
}

function fillClassificacoes(idSelected){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'classificacoes.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $("#idClassificacao");
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].tipo + '</option>');
            }
        }
    });
}

/**
 * HTML id should be "idProfile"
 * @param idSelected: number
 */
function fillProfiles(idSelected){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'profiles.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $("#idProfile");
        	combo.empty();
        	if(data == null)
        		return;
            for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	if(parseInt(idSelected) == data[i].id){ _selected = ' selected="selected"'; }
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].name +'</option>';
            	combo.append(str);
            }
        }
    });
}
/**
 * HTML id should be "idLocal"
 * @param idSelected
 */
function fillLocals(idSelected, idDiv){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'locals.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo;
        	if(idDiv != undefined && idDiv != null){
        		combo = $('#'+idDiv);
        	} else{
        		combo = $('#idLocal');
        	}
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	if(parseInt(idSelected) == data[i].id){ _selected = ' selected="selected"'; }
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nome +'</option>';
            	combo.append(str);
            }
        }
    });
}
//Select Multiple
function fillMultipleLocals(idsSelected, idDiv){
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'locals.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	if(idDiv != undefined && idDiv != null){
        		combo = $('#'+idDiv);
        	} else{
        		combo = $('#idLocais');
        	}
        	combo.empty();
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	var pos = ids.indexOf(data[i].id);
            	if(pos > -1){
            		_selected = ' selected="selected"';
            	}
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nome +'</option>';
            	combo.append(str);
            }
        }
    });
}

function fillMultipleCorrespondencias(idsSelected, idDiv){
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'correspondencias.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $('#'+idDiv);
        	combo.empty();
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	var pos = ids.indexOf(data[i].id);
            	if(pos > -1){
            		_selected = ' selected="selected"';
            	}
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nomeRemetente + ' - '+  data[i].nomeDestinatario +'</option>';
            	combo.append(str);
            }
        }
    });
}

//Select Multiple
function fillGrupoMovimento(idSelected, idDiv){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'gru-movimentos.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo;
        	if(idDiv != undefined && idDiv != null){
        		combo = $('#'+idDiv);
        	} else{
        		combo = $('#idGruMovimento');
        	}
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	if(parseInt(idSelected) == data[i].id){ _selected = ' selected="selected"'; }
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nome +'</option>';
            	combo.append(str);
            }
        }
    });
}

function fillProfileList(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'profiles.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $("#idProfile");
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].name +'</option>');
            }
        }
    });
}

function fillPersonagens(idSelected, idDiv){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'personagens.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	var combo;
    		combo = $('#'+idDiv);
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	if(parseInt(idSelected) == data[i].id){ _selected = ' selected="selected"'; }
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nome +'</option>';
            	combo.append(str);
            }
        }
    });
}
function fillMultiplePersonagens(idsSelected, idDiv){
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'personagens.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $('#'+idDiv);
        	combo.empty();
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	var pos = ids.indexOf(data[i].id);
            	if(pos > -1){
            		_selected = ' selected="selected"';
            	}
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nome +'</option>';
            	combo.append(str);
            }
        }
    });
}

/**
 * 
 * @param idSelected
 * @param idDiv
 */
function fillObras(idSelected, idDiv){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'obras.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo;
        	if(idDiv != undefined && idDiv != null){
        		combo = $('#'+idDiv);
        	} else{
        		combo = $('#idLocal');
        	}
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	if(parseInt(idSelected) == data[i].id){ _selected = ' selected="selected"'; }
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].titulo +'</option>';
            	combo.append(str);
            }
        }
    });
}
//Select multiple
function fillMultipleObras(idsSelected, idDiv){
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'obras.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $('#'+idDiv);
        	combo.empty();
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	var pos = ids.indexOf(data[i].id);
            	if(pos > -1){
            		_selected = ' selected="selected"';
            	}
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].titulo +'</option>';
            	combo.append(str);
            }
        }
    });	
}

/**
 * 
 * @param idsSelected
 * @param idDiv
 */
function fillMultipleReligioesCrencas(idsSelected, idDiv){
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'religioes-crencas.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $('#'+idDiv);
        	combo.empty();
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	var pos = ids.indexOf(data[i].id);
            	if(pos > -1){
            		_selected = ' selected="selected"';
            	}
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nome +'</option>';
            	combo.append(str);
            }
        }
    });	
}

/**
 * 
 * @param idsSelected
 * @param idDiv
 */
function fillMultipleEncontros(idsSelected, idDiv){
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'encontros.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $('#'+idDiv);
        	combo.empty();
        	if(data == null)
        		return;
        	for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	var pos = ids.indexOf(data[i].id);
            	if(pos > -1){
            		_selected = ' selected="selected"';
            	}
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].nome +'</option>';
            	combo.append(str);
            }
        }
    });	
}

/**/
function updateForm(id){
	window.location.href = URL_SECTION+'.html?id='+id;
}

function viewForm(id){
	window.location.href = URL_SECTION+'.html?id='+id+'&readonly=true';
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
        	if(data == null)
        		return;
        	
        	if(TXT_SUCCESS == data.type.toLowerCase()){
        		addMessage(data.message, 'success');
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
function commonSaveForm(_url, _data){
	$.ajax({
        url : _url,
        type : "POST",
        traditional : true,
        contentType : "application/json",
        dataType : "json",
        data : _data,
        success : function(data) {
        	if(data == null)
        		return;
        	
        	if(TXT_SUCCESS == data.type.toLowerCase()){
        		addMessage(data.message, 'success');
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
        	if(data == null)
        		return;
        	
        	buildGrid(div_id, data);
        }
    });
}
/* Add personagem */
function showAddPersonagem(divId) {
	var title = jQuery.i18n.prop('msg_cadastro_personagem');
	$('#popup-personagem').load(urlContext()+'basic-personagem.html?divId='+divId).dialog({
		autoOpen : false,
		modal : true,
		title : title,
		width : 600,
	});
	$("#popup-personagem").dialog('open');
}
/* AutoComplete Locais */
function fillListLocais(idsSelected, idDiv, identificador, multiple){
	var lstFields = ['nome', 'country'];
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	if(ids != undefined && ids != NaN && ids.length > 0){//TODO:
		paintItems(idDiv, ids, 'locals.html', lstFields, identificador, multiple);
	}
}
//
function fillListReligioesCrencas(idsSelected, idDiv, identificador, multiple){
	var lstFields = ['nome'];
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	if(ids != undefined && ids != NaN && ids.length > 0){
		paintItems(idDiv, ids, 'religioes-crencas.html', lstFields, identificador, multiple);
	}
}
//
function fillListObras(idsSelected, idDiv, identificador, multiple){
	var lstFields = ['titulo'];
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	if(ids != undefined && ids != NaN && ids.length > 0){
		paintItems(idDiv, ids, 'obras.html', lstFields, identificador, multiple);
	}
}
//
function fillListCorrespondencias(idsSelected, idDiv, identificador, multiple){
	var lstFields = ['nomeDestinatario', 'nomeRemetente'];
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	if(ids != undefined && ids != NaN && ids.length > 0){
		paintItems(idDiv, ids, 'correspondencias.html', lstFields, identificador, multiple);
	}
}
//
function fillListGruMovimento(idsSelected, idDiv, identificador, multiple){
	var lstFields = ['nome'];
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	if(ids != undefined && ids != NaN && ids.length > 0){
		paintItems(idDiv, ids, 'gru-movimentos.html', lstFields, identificador, multiple);
	}
}
//
function fillListPersonagens(idsSelected, idDiv, identificador, multiple){
	var lstFields = ['nome', 'sobrenome'];
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	if(ids != undefined && ids != NaN && ids.length > 0){
		paintItems(idDiv, ids, 'personagens.html', lstFields, identificador, multiple);
	}
}
//
function fillListObras(idsSelected, idDiv, identificador, multiple){
	var lstFields = ['titulo'];
	var str_ids = JSON.parse('['+ idsSelected +']');
	str_ids = str_ids.toString();
	split_ids = str_ids.split(",");
	var ids = [];
	for(var i = 0; i < split_ids.length; i++){
		ids.push(parseInt(split_ids[i]));
	}
	if(ids != undefined && ids != NaN && ids.length > 0){
		paintItems(idDiv, ids, 'obras.html', lstFields, identificador, multiple);
	}
}
/**/
var ItemClass = function ItemClass(){
    this.id				= null;
    this.description	= null;
};

function paintItems(_idDiv, _ids, _url, _fields, _identificador, _multtiple){
	var lstItems = [];
	var item;
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:_url,
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
    		for (var i = 0; i < data.length; i++) {
    			var pos = _ids.indexOf(data[i].id);
    			if(pos > -1){
    				item = new ItemClass();
    				item.id				= data[i].id;
    				var _txt = '';
    				for(var f in _fields){
    					var _length = _fields.length - 1;
    					var _temp = data[i][_fields[f]]; 
    					_txt += _temp != null ? _txt = _temp : '';
    					if(f != _length){
    						_txt += ' - '; 
    					}    						
    				}
    				item.description = _txt;
    				
    				lstItems.push(item);
    			}
    		}
    		createTableForItems(_idDiv, lstItems, _identificador, _multtiple);
        }
    });
}

//
var gruMovLstLocais = [];
var persLstLocalNascimento = [];
var persLstLocalMorte = [];
var persLstLocaisPassou = [];
//var persLstCorrespondencias = [];
var persLstReligioes = [];
var persLstObras = [];

var fontLstLocalImpressao = [];
var fontLstLocalProducao = [];
var fontLstGruMovimento = [];
var fontLstLeitores = [];
var fontLstAutCitados = [];
var fontLstObrCitadas = [];
//

function createTableForItems(_idDiv, _itemList, _identificador, _multtiple){
	
	/////////////////////////////////////////////////////////////
	if(_idDiv == 'gruMovLstLocais'){ gruMovLstLocais = _itemList; }
	else if(_idDiv == 'persLstLocalNascimento'){ persLstLocalNascimento = _itemList; }
	else if(_idDiv == 'persLstLocalMorte'){ persLstLocalMorte = _itemList; }
	else if(_idDiv == 'persLstLocaisPassou'){ persLstLocaisPassou = _itemList; }
	//else if(_idDiv == 'persLstCorrespondencias'){ persLstCorrespondencias = _itemList; }
	else if(_idDiv == 'persLstReligioes'){ persLstReligioes = _itemList; }
	else if(_idDiv == 'persLstObras'){ persLstObras = _itemList; }
	
	else if(_idDiv == 'fontLstLocalImpressao'){ fontLstLocalImpressao = _itemList; }
	else if(_idDiv == 'fontLstLocalProducao'){ fontLstLocalProducao = _itemList; }
	else if(_idDiv == 'fontLstGruMovimento'){ fontLstGruMovimento = _itemList; }
	else if(_idDiv == 'fontLstLeitores'){ fontLstLeitores = _itemList; }
	else if(_idDiv == 'fontLstAutCitados'){ fontLstAutCitados = _itemList; }
	else if(_idDiv == 'fontLstObrCitadas'){ fontLstObrCitadas = _itemList; }
	/////////////////////////////////////////////////////////////
	
	var html = '<table>';
		for(var i in _itemList){
			html += '<tr>';
				//html += '<td>'+_itemList[i]['id']+'</td>';
				html += '<td>'+_itemList[i]['description']+'</td>';
				html += '<td><img onclick="removeItemFromTable('+"'"+_identificador+"'"+', '+"'"+_idDiv+"'"+', '+_itemList[i]['id']+', '+"'"+_multtiple+"'"+')" src="static/images/delete.png" /></td>';
			html += '</tr>';
		}
	html += '</table>';
	$('#'+_idDiv).html(html);
}
//TODO: Check duplicates
function addItemToTable(identificador, _idDiv, inputTxt, inputId, multiple){
	var valId	= $('#'+inputId).val();
	var valTxt	= $('#'+inputTxt).val();
	
	if(valId == undefined || valId == '' || valTxt == undefined || valTxt == ''){
		addMessage(jQuery.i18n.prop('err_item_required'), 'error');
		return;
	}
	
	var _itemList = [];
	var item = new ItemClass();
	item.id = valId;
	item.description = valTxt;
	
	$('#'+inputId).val('');
	$('#'+inputTxt).val('');

	/////////////////////////////////////////////////////////////
	if(_idDiv == 'gruMovLstLocais'){ _itemList = gruMovLstLocais; }
	else if(_idDiv == 'persLstLocalNascimento'){ _itemList = persLstLocalNascimento; }
	else if(_idDiv == 'persLstLocalMorte'){ _itemList = persLstLocalMorte; }
	else if(_idDiv == 'persLstLocaisPassou'){ _itemList = persLstLocaisPassou; }
	//else if(_idDiv == 'persLstCorrespondencias'){ _itemList = persLstCorrespondencias; }
	else if(_idDiv == 'persLstReligioes'){ _itemList = persLstReligioes; }
	else if(_idDiv == 'persLstObras'){ _itemList = persLstObras; }
	
	else if(_idDiv == 'fontLstLocalImpressao'){ _itemList = fontLstLocalImpressao; }
	else if(_idDiv == 'fontLstLocalProducao'){ _itemList = fontLstLocalProducao; }
	else if(_idDiv == 'fontLstGruMovimento'){ _itemList = fontLstGruMovimento; }
	else if(_idDiv == 'fontLstLeitores'){ _itemList = fontLstLeitores; }
	else if(_idDiv == 'fontLstAutCitados'){ _itemList = fontLstAutCitados; }
	else if(_idDiv == 'fontLstObrCitadas'){ _itemList = fontLstObrCitadas; }
	/////////////////////////////////////////////////////////////
	
	if(_itemList.length == 1 && (multiple == false || multiple == 'false')){
		addMessage(jQuery.i18n.prop('err_multiple_not_allowed'), 'error');
		return;
	}
	else{
		_itemList.push(item);
	}	
	
	setItemIds(identificador, _itemList);
	createTableForItems(_idDiv, _itemList, identificador, multiple);
}

function removeItemFromTable(identificador, _idDiv, _itemId, multiple){
	var _itemList = [];
	
	/////////////////////////////////////////////////////////////
	if(_idDiv == 'gruMovLstLocais'){ _itemList = gruMovLstLocais; }
	else if(_idDiv == 'persLstLocalNascimento'){ _itemList = persLstLocalNascimento; }
	else if(_idDiv == 'persLstLocalMorte'){ _itemList = persLstLocalMorte; }
	else if(_idDiv == 'persLstLocaisPassou'){ _itemList = persLstLocaisPassou; }
	//else if(_idDiv == 'persLstCorrespondencias'){ _itemList = persLstCorrespondencias; }
	else if(_idDiv == 'persLstReligioes'){ _itemList = persLstReligioes; }
	else if(_idDiv == 'persLstObras'){ _itemList = persLstObras; }
	
	else if(_idDiv == 'fontLstLocalImpressao'){ _itemList = fontLstLocalImpressao; }
	else if(_idDiv == 'fontLstLocalProducao'){ _itemList = fontLstLocalProducao; }
	else if(_idDiv == 'fontLstGruMovimento'){ _itemList = fontLstGruMovimento; }
	else if(_idDiv == 'fontLstLeitores'){ _itemList = fontLstLeitores; }
	else if(_idDiv == 'fontLstAutCitados'){ _itemList = fontLstAutCitados; }
	else if(_idDiv == 'fontLstObrCitadas'){ _itemList = fontLstObrCitadas; }
	/////////////////////////////////////////////////////////////
	
	var pos = getPosistionOfItemIntoItemList(_itemList, _itemId);
	if(pos == undefined || pos == null){
		addMessage(jQuery.i18n.prop('err_removing_item_'), 'error');
	}
	_itemList.splice(parseInt(pos), 1);
	
	setItemIds(identificador, _itemList);
	createTableForItems(_idDiv, _itemList, identificador, multiple);
}

function setItemIds(identificador, lstItems){
	var lstIds = [];
	for(var i in lstItems){
		lstIds.push(lstItems[i]['id']);
	}
	
	$('#'+identificador).val(lstIds);
}

function getPosistionOfItemIntoItemList(lstItems, itemId){
	for(var o in lstItems){
		if(itemId == lstItems[o]['id']){
			return o;
 		}
	}
	return null;
}