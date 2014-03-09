var URL_SECTION = 'profile';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	fillRoles();
	loadGrid();
});

function fillRoles(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'profile/roles.html',
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	
        	var combo = $("#idRoles");
        	combo.empty();
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].name +'</option>');
            }
        }
    });
}

function saveForm(){
	//if(!checkFields())
		//return;
		
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
    var name		= $('#name').val();
    var idRoles		= $('#idRoles').val();
   
    var data = JSON.stringify({ "id" : id, "name" : name, "idRoles" : idRoles });
    commonSaveForm(url, data);
}

function buildGrid(div_id, data){	
	var titles			= ['Nome'];
	var columns_key		= ['name'];
	var columns_size	= ['90'];
	var columns_sort	= ['name'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}