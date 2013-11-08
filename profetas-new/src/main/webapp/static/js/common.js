function getStrSelected(){
	return ' selected="selected"';
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
        	var combo = $("#idProfile");
        	combo.empty();
            for (var i = 0; i < data.length; i++) {
            	var _selected = '';
            	if(parseInt(idSelected) == data[i].id){
            		_selected = ' selected="selected"';
            	}
            	var str = '<option value="' + data[i].id+'"'+ _selected+'>' + data[i].name +'</option>';
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
        	var combo = $("#idProfile");
        	combo.empty();
        	combo.append('<option value="-1">Selecione um</option>');
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].name +'</option>');
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
function commonSaveForm(_url, _data){
	$.ajax({
        url : _url,
        type : "POST",
        traditional : true,
        contentType : "application/json",
        dataType : "json",
        data : _data,
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