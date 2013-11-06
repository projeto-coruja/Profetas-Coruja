var URL_SECTION = 'local';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	drawMap();
	loadGrid();
});

var map;
function drawMap() {
	var opts = {'center': new google.maps.LatLng(52.3755991766591, 4.866943359375), 
	'zoom':11, 'mapTypeId': google.maps.MapTypeId.ROADMAP }
	map = new google.maps.Map(document.getElementById('mapdiv'),opts);
	
	google.maps.event.addListener(map,'click',function(event) {
		$('#latitude').val(event.latLng.lat());
		$('#longitude').val(event.latLng.lng());
		getPlace();
	});
}
function getPlace(){
	var _latitude	= $('#latitude').val();
	var _longitude	= $('#longitude').val();
	
	var localLatlng = new google.maps.LatLng(_latitude, _longitude);
	
    var geocoder = new google.maps.Geocoder(); 
    geocoder.geocode({ 'latLng': localLatlng },
      function (results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
              if (results[0]) {
                  var city = "", 
                  		state = "", 
                  		country = "";                  

                  for (var i = 0; i < results[0].address_components.length; i++) {
                      var addr = results[0].address_components[i];
                      if (addr.types[0] == 'country')
                          country = addr.long_name;
                      else if (addr.types[0] == ['administrative_area_level_1'])
                          state = addr.long_name;
                      else if (addr.types[0] == ['locality'])
                          city = addr.long_name;
                  }
                  $('#country').val(country);
                  $('#state').val(state);
                  $('#city').val(city);
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
    var country		= $('#country').val();
    var state		= $('#state').val();
    var city		= $('#city').val();
    var latitude	= $('#latitude').val();	    
    var longitude	= $('#longitude').val();
    
    var data = JSON.stringify({ "id" : id, "nome" : nome, "latitude" : latitude, "longitude" : longitude, 
    	"country" : country, "state" : state, "city" : city });
    
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
	var titles			= ['Nome'];
	var columns_key		= ['nome'];
	var columns_size	= ['90'];
	var columns_sort	= ['nome'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}