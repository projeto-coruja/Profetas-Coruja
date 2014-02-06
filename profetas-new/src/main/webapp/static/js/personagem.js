var URL_SECTION = 'personagem';

var lstEncontros = [];
var EncontroClass = function EncontroClass(){
	this._index			= null;
    this.id				= null;
    this.nome			= null;
    this.data			= null;
    this.idPersonagem	= null;
    this.descPersonagem	= null;
    this.idLocal		= null;
    this.descLocal		= null;
};
var globalId = 0;
var generateUUID = function(){
	globalId++;
	if(globalId >= 1000000){
		globalId = 0;
	}
	return globalId;
};
function fillEncontros(encontrosStr, idDiv){
	if(encontrosStr != undefined && encontrosStr != ''){
		var encontros = JSON.parse(encontrosStr);
		var encontro, 
			i, enc_size;
		for(var i in encontros){
			encontro = new EncontroClass();
			encontro._index	= generateUUID();
			encontro.id		= encontros[i]['id'];
		    encontro.nome   = encontros[i]['nome'];
		    encontro.data   = encontros[i]['data'];
		    encontro.idPersonagem	= encontros[i]['idPersonagem'];
		    encontro.descPersonagem	= encontros[i]['descPersonagem'];
		    encontro.idLocal		= encontros[i]['idLocal'];
		    encontro.descLocal		= encontros[i]['descLocal'];
		    lstEncontros.push(encontro);
		}
		loadEncontros();
	}
}


$(document).ready(function() {
    $("#saveForm").click(function(){
        saveForm();
    });
    loadGrid();
    //Encontros
    loadEncontros();
    prepareEncontros();
    //
});

function prepareEncontros(){
    $("#persEnc").autocomplete({
        source: "personagem/search.html",
        select: function( event, ui ) {
            $( "#persEnc" ).val( ui.item.label );
            $( "#personagemId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#persEnc" ).val( ui.item.label );
            return false;
        }
    });
    //
    $("#localEnc").autocomplete({
        source: "local/search.html",
        select: function( event, ui ) {
            $( "#localEnc" ).val( ui.item.label );
            $( "#localId" ).val( ui.item.id );
            return false;
        },
        focus: function( event, ui ) {
            $( "#localEnc" ).val( ui.item.label );
            return false;
        }
    });
}

//-->Encontros
function loadEncontros(){
    var html = '';
    var _idPersonagem = $('#id').val();
        html += '<table id="lst_encontros" class="grid">';
            html += '<thead><tr>';
                html += '<th>Nome</th>';
                html += '<th>Data</th>';
                html += '<th>Personagem</th>';
                html += '<th>Local</th>';
                html += '<th></th>';
                html += '<th style="visibility: hidden">Id</th>';
            html += '</tr></thead>';
            html += '<tbody>';
            for(var i in lstEncontros){
                html += '<tr id="'+lstEncontros[i]['id']+'">';
                    html += '<td>'+lstEncontros[i]['nome']+'</td>';
                    html += '<td>'+lstEncontros[i]['data']+'</td>';
                    html += '<td>'+lstEncontros[i]['descPersonagem']+'</td>';
                    html += '<td>'+lstEncontros[i]['descLocal']+'</td>';
                    html += '<td align="center"><img onclick="removeEncontro('+lstEncontros[i]['id']+')" src="static/images/delete.png" /></td>';
                    html += '<td style="visibility: hidden">'+lstEncontros[i]['id']+'</td>';
                html += '<tr>';
            }
                html += '</tr>';
                    html += '<td><input id="nomeEnc" type="text" /></td>';
                    html += '<td><input id="dataEnc" type="text" /></td>';
                    html += '<td><input id="persEnc" type="text" /> <input type="hidden" id="personagemId" name="personagemId" /></td>';
                    html += '<td><input id="localEnc" type="text" /> <input type="hidden" id="localId" name="localId" /></td>';
                    html += '<td align="center"><img onclick="addEncontro()" src="static/images/add.png" /></td>';
                    html += '<td style="visibility: hidden"></td>';
                html += '</tr>';
            html += '</tbody>';
        html += '</table>';

    $('#lstEncontros').html(html);
}

function addEncontro(){
    var encontro = new EncontroClass();
    encontro.nome    = $('#nomeEnc').val();;
    encontro.data    = $('#dataEnc').val();
    encontro.idPersonagem	= $('#personagemId').val();
    encontro.descPersonagem	= $('#persEnc').val();
    encontro.idLocal		= $('#localId').val();
    encontro.descLocal		= $('#localEnc').val();
   
    lstEncontros.push(encontro);
    loadEncontros();
    prepareEncontros();
}
function removeEncontro(idEncontro){
	$('#lst_encontros tr:eq('+idEncontro+')').remove();
	/*console.log('remove: ' + idEncontro);
	console.log('1-size: ' + lstEncontros.length);
	lstEncontros.splice(idEncontro, 1);
	console.log('2-size: ' + lstEncontros.length);*/
	
	//loadEncontros();
    prepareEncontros();
}
//-->Encontros

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
    $('#refBibliografica').val('');
    $('#idReligioes').val('');
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
       
    var url            = URL_SECTION+'/save.html';
    var id            = $('#id').val();
    var nome        = $('#nome').val();
    var sobrenome        = $('#sobrenome').val();
    var apelido            = $('#apelido').val();
    var idNascimento    = $('#idNascimento').val();
    var dataNascimento    = $('#dataNascimento').val();
    var idMorte     = $('#idMorte').val();
    var dataMorte    = $('#dataMorte').val();
    var biografia    = $('#biografia').val();
    var ocupacao    = $('#ocupacao').val();
    var formacao    = $('#formacao').val();
   
    var refBibliografica    = $('#refBibliografica').val();
    var idReligioes    = $('#idReligioes').val();
    var idObras        = $('#idObras').val();
    var idCorrespondencias    = $('#idCorrespondencias').val();
    var idLocaisPers        = $('#idLocaisPers').val();
   
    var data = JSON.stringify({ "id" : id, "nome" : nome, "sobrenome" : sobrenome, "apelido" : apelido,
        "idNascimento" : idNascimento, "dataNascimento" : dataNascimento, "idMorte" : idMorte, "dataMorte" : dataMorte,
        "biografia" : biografia, "ocupacao" : ocupacao, "formacao" : formacao, "refBibliografica" : refBibliografica,
        "idReligioes" : idReligioes, "idObras" : idObras, "idCorrespondencias" : idCorrespondencias,
        "idLocaisPers" : idLocaisPers, "encontros":lstEncontros });
   
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
    var titles          = ['Nome', 'Sobrenome'];
    var columns_key     = ['nome', 'sobrenome'];
    var columns_size    = ['45', '45'];
    var columns_sort    = ['nome', 'sobrenome'];
    var corujaGrid = new CorujaGrid();
    corujaGrid.paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data);
}

function makeReadonly(){
    $('#personagem :input').prop('readonly', true);
    $('#editForm').prop('hidden', false);
    $('#saveForm').prop('hidden', true);
}

function reloadEdit(id){
    window.location.href = URL_SECTION+'.html?id='+id;
}