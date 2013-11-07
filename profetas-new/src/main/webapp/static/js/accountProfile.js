var URL_SECTION = 'account-profile';
	
$(document).ready(function() {
	fillRoles();
	loadGrid();
});

function fillRoles(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:'profiles.html',
        success: function(data, textStatus, jqXHR){
        	var combo = $("#idProfile");
        	combo.empty();
            for (var i = 0; i < data.length; i++) {
            	combo.append('<option value="' + data[i].id + '">' + data[i].name +'</option>');
            }
        }
    });
}

function buildGrid(div_id, data){
	var titles			= ['Nome', 'Email', 'Perfil'];
	var columns_key		= ['fullName', 'email', 'profileName'];
	var columns_size	= ['40', '35', '15'];
	var columns_sort	= ['fullName', 'email', 'profileName'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}