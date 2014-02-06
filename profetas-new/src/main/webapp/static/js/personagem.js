var URL_SECTION = 'personagem';

var lstEncontros = [];
var EncontroClass = function EncontroClass(){
    this.id                = null;
    this.nome            = null;
    this.data            = null;
    this.idPersonagem    = null;
    this.idLocal        = null;
};
   
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
    console.log('_idPersonagem: ' + _idPersonagem);
        html += '<table>';
            html += '<thead><tr>';
                html += '<th>Nome</th>';
                html += '<th>Data</th>';
                html += '<th>Personagem</th>';
                html += '<th>Local</th>';
                html += '<th>+</th>';
                html += '<th>-</th>';
            html += '</tr></thead>';
            html += '<tbody>';
            for(var i in lstEncontros){
                html += '<tr>';
                    html += '<td >'+lstEncontros[i]['nome']+'</td>';
                    html += '<td >'+lstEncontros[i]['data']+'</td>';
                    html += '<td >'+lstEncontros[i]['idPersonagem']+'</td>';
                    html += '<td >'+lstEncontros[i]['idLocal']+'</td>';
                    html += '<td ><img onclick="removeEncontro()" src="static/images/delete.png" /></td>';
                html += '<tr>';
            }
                html += '</tr>';
                    html += '<td ><input id="nomeEnc" type="text" size="10" /></td>';
                    html += '<td ><input id="dataEnc" type="text" size="10" /></td>';
                    html += '<td ><input id="persEnc" type="text" size="5" /> <input type="hidden" id="personagemId" name="personagemId" /></td>';
                    html += '<td ><input id="localEnc" type="text" size="5" /> <input type="hidden" id="localId" name="localId" /></td>';
                    html += '<td ><img onclick="addEncontro()" src="static/images/add.png" /></td>';
                html += '</tr>';
            html += '</tbody>';
        html += '</table>';

    $('#lstEncontros').html(html);
}

function addEncontro(){
    var nomeEnc  = $('#nomeEnc').val();
    var dataEnc  = $('#dataEnc').val();
    var persEnc  = $('#personagemId').val();
    var localEnc = $('#localId').val();
   
    console.log('nomeEnc: ' + nomeEnc);
    console.log('dataEnc: ' + dataEnc);
    console.log('persEnc: ' + persEnc);
    console.log('localEnc: ' + localEnc);
   
    var encontro = new EncontroClass();
    encontro.nome    = $('#nomeEnc').val();;
    encontro.data    = $('#dataEnc').val();
    encontro.idPersonagem    = $('#personagemId').val();
    encontro.idLocal    = $('#localId').val();
   
    lstEncontros.push(encontro);
   
    console.log('encontros: ' + lstEncontros.length);
    loadEncontros();
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
    //$('#idEncontros').val('');
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
    //var idEncontros    = $('#idEncontros').val();
    var idObras        = $('#idObras').val();
    var idCorrespondencias    = $('#idCorrespondencias').val();
    var idLocaisPers        = $('#idLocaisPers').val();
   
    var data = JSON.stringify({ "id" : id, "nome" : nome, "sobrenome" : sobrenome, "apelido" : apelido,
        "idNascimento" : idNascimento, "dataNascimento" : dataNascimento, "idMorte" : idMorte, "dataMorte" : dataMorte,
        "biografia" : biografia, "ocupacao" : ocupacao, "formacao" : formacao, "refBibliografica" : refBibliografica,
        "idReligioes" : idReligioes, "idObras" : idObras, "idCorrespondencias" : idCorrespondencias,
        "idLocaisPers" : idLocaisPers, "encontros":lstEncontros });
    //"idEncontros" : idEncontros,
    //json: {"sobrenome":"Valverde","nome":"Jimmy","apelido":"Ghost","encontros":[{"id":1,"label":"Uno"},{"id":2,"label":"Dos"},{"id":3,"label":"Tres"}]}
    //"encontros":[{"id":1,"label":"Uno"},{"id":2,"label":"Dos"},{"id":3,"label":"Tres"}]
    console.log('waaa: ' + data);
   
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
    var titles            = ['Nome', 'Sobrenome'];
    var columns_key        = ['nome', 'sobrenome'];
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