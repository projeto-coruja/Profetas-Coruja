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