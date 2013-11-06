var URL_SECTION = 'account';
	
$(document).ready(function() {
	loadGrid();
});

function buildGrid(div_id, data){
	console.log('zzz');
	var titles			= ['Nome', 'Email', 'Perfil'];
	var columns_key		= ['fullName', 'email', 'profileName'];
	var columns_size	= ['40', '35', '15'];
	var columns_sort	= ['fullName', 'email', 'profileName'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
	console.log('gr: ' + data[0].fullName);
}