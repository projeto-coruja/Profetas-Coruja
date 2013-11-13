var URL_SECTION = 'personagem';
	
$(document).ready(function() {
	$("#saveForm").click(function(){
		saveForm();
	});
	loadGrid();
});

function clearFields(){
	$('#nome').val('');
	$('#sobrenome').val('');
    $('#apelido').val('');
    $('#idNascimento').val('');
    $('#dataNascimento').val('');
    $('#idMorte').val('');
    $('#dataMorte').val('');
    $('#biografia').val('');
    $('#ocupacao').val('');
    $('#formacao').val('');
    $('#idRefBibliografica').val('');
    $('#idReligioes').val('');
    $('#idEncontros').val('');
    $('#idObras').val();
    $('#idCorrespondencias').val();
    $('idLocaisPers').val();
}

function checkFields(){
	var nome = $('#nome').val();
	if(nome == undefined || nome == ''){
		addMessage(jQuery.i18n.prop('err_nome_required'), 'error');
		return false;
	}
	return true;
}

function saveForm(){
	if(!checkFields())
		return;
		
	var url			= URL_SECTION+'/save.html';
	var id			= $('#id').val();
	var nome		= $('#nome').val();
	var sobrenome		= $('#sobrenome').val();
	var apelido			= $('#apelido').val();
    var idNascimento	= $('#idNascimento').val();
    var dataNascimento	= $('#dataNascimento').val();
    var idMorte 	= $('#idMorte').val();
    var dataMorte	= $('#dataMorte').val();
    var biografia	= $('#biografia').val();
    var ocupacao	= $('#ocupacao').val();
    var formacao	= $('#formacao').val();
    
    var idRefBibliografica	= $('#idRefBibliografica').val();
    var idReligioes	= $('#idReligioes').val();
    var idEncontros	= $('#idEncontros').val();
    var idObras		= $('#idObras').val();
    var idCorrespondencias	= $('#idCorrespondencias').val();
    var idLocaisPers		= $('#idLocaisPers').val();
    
    var data = JSON.stringify({ "id" : id, "nome" : nome, "sobrenome" : sobrenome, "apelido" : apelido, 
    	"idNascimento" : idNascimento, "dataNascimento" : dataNascimento, "idMorte" : idMorte, "dataMorte" : dataMorte, 
    	"biografia" : biografia, "ocupacao" : ocupacao, "formacao" : formacao, "idRefBibliografica" : idRefBibliografica, 
    	"idReligioes" : idReligioes, "idEncontros" : idEncontros, "idObras" : idObras, "idCorrespondencias" : idCorrespondencias, 
    	"idLocaisPers" : idLocaisPers });
    
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
	var titles			= ['Nome', 'Sobrenome'];
	var columns_key		= ['nome', 'sobrenome'];
	var columns_size	= ['45', '45'];
	var columns_sort	= ['nome', 'sobrenome'];
	var corujaGrid = new CorujaGrid();
	corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}