<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('SAVE')">

<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/jquery.tagsinput.js'/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/jquery.tagsinput.css'/>" />

<script src="<c:url value='/static/js/fontesObras.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_obras_fontes"/></span>
	
	<form:form id="fontes" modelAttribute="fontes">
		<form:hidden id="id" path="id" />
		
		<div class="label-box"><label for="titulo"><spring:message code="lbl_titulo"/></label></div>
	    <div class="input-box"><form:textarea id="titulo" path="titulo" cols="70" rows="2" /></div>		
		<br />
		
		<div class="label-box"><label for="strAutor"><spring:message code="lbl_autor"/></label></div>
	    <div class="input-box">
	    	<form:input id="strAutor" path="strAutor" size="50" maxlength="100" />
	    	<form:hidden id="idAutor" path="idAutor" />
		    <img onclick="showAddPersonagem('Autor')" src="<c:url value="/static/images/new_personagem.png" />" />
	    </div>
		<br />
		
		<div class="label-box"><label for="localizacao"><spring:message code="lbl_localizacao"/></label></div>
	    <div class="input-box"><form:input id="localizacao" path="localizacao" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for=referenciaCompleta><spring:message code="lbl_ref_completa"/></label></div>
	    <div class="input-box"><form:textarea id="referenciaCompleta" path="referenciaCompleta" cols="70" rows="5" /></div>		
		<br />
		
		<div class="label-box"><label for=referenciasCirculacaoObra><spring:message code="lbl_ref_circulacao"/></label></div>
	    <div class="input-box"><form:textarea id="referenciasCirculacaoObra" path="referenciasCirculacaoObra" cols="70" rows="5" /></div>		
		<br />
		
		<div class="label-box"><label for="comentarios"><spring:message code="lbl_comentarios"/></label></div>
	    <div class="input-box"><form:textarea id="comentarios" path="comentarios" cols="70" rows="5" /></div>		
		<br />
		
		<div class="label-box"><label for="url"><spring:message code="lbl_url"/></label></div>
	    <div class="input-box"><form:input id="url" path="url" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="copiasManuscritas"><spring:message code="lbl_copias"/></label></div>
	    <div class="input-box"><form:textarea id="copiasManuscritas" path="copiasManuscritas" cols="70" rows="5" /></div>		
		<br />
		
		<div class="label-box"><label for="traducoes"><spring:message code="lbl_traducoes"/></label></div>
	    <div class="input-box"><form:textarea id="traducoes" path="traducoes" cols="70" rows="5" /></div>		
		<br />
		
		<div class="label-box"><label for="editor"><spring:message code="lbl_editor"/></label></div>
	    <div class="input-box"><form:input id="editor" path="editor" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="dataImpressao"><spring:message code="lbl_data_impressao"/></label></div>
	    <div class="input-box"><form:input id="dataImpressao" path="dataImpressao" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="idLocalImpressao"><spring:message code="lbl_local_impressao"/></label></div>
        <div class="input-box">
            <form:hidden id="idLocalImpressao" path="idLocalImpressao" />
            <input type="text" id="acLocalImpressaoTxt" /><img onclick="addItemToTable('idLocalImpressao', 'fontLstLocalImpressao', 'acLocalImpressaoTxt', 'acLocalImpressaoId', 'false')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acLocalImpressaoId" name="acLocalImpressaoId" />
        </div>
        <div id="fontLstLocalImpressao"></div>
        <br />

		<div class="label-box"><label for="produtor"><spring:message code="lbl_produtor"/></label></div>
	    <div class="input-box"><form:input id="produtor" path="produtor" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="dataProducao"><spring:message code="lbl_data_producao"/></label></div>
	    <div class="input-box"><form:input id="dataProducao" path="dataProducao" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="idLocalProducao"><spring:message code="lbl_local_producao"/></label></div>
        <div class="input-box">
            <form:hidden id="idLocalProducao" path="idLocalProducao" />
            <input type="text" id="acLocalProducaoTxt" /><img onclick="addItemToTable('idLocalProducao', 'fontLstLocalProducao', 'acLocalProducaoTxt', 'acLocalProducaoId', 'false')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acLocalProducaoId" name="acLocalProducaoId" />
        </div>
        <div id="fontLstLocalProducao"></div>
        <br />
		
		<div class="label-box"><label for="idClassificacao"><spring:message code="lbl_classificacao"/></label></div>
	    <div class="input-box">
	    	<form:select id="idClassificacao" path="idClassificacao">
		    </form:select>
	    </div>		
		<br />
		<!--  -->
		<div class="label-box"><label for="idGruMovimento"><spring:message code="lbl_gru_movimento"/></label></div>
        <div class="input-box">
            <form:hidden id="idGruMovimento" path="idGruMovimento" />
            <input type="text" id="acGruMovimentoTxt" /><img onclick="addItemToTable('idGruMovimento', 'fontLstGruMovimento', 'acGruMovimentoTxt', 'acGruMovimentoId', 'false')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acGruMovimentoId" name="acGruMovimentoId" />
        </div>
        <div id="fontLstGruMovimento"></div>
        <br />
		
		<div class="label-box"><label for="idLeitores"><spring:message code="lbl_leitores"/></label></div>
        <div class="input-box">
            <form:hidden id="idLeitores" path="idLeitores" />
            <input type="text" id="acLeitoresTxt" /><img onclick="addItemToTable('idLeitores', 'fontLstLeitores', 'acLeitoresTxt', 'acLeitoresId', 'true')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acLeitoresId" name="acLeitoresId" />
        </div>
        <div id="fontLstLeitores"></div>
        <br />
		
		<div class="label-box"><label for="idAutCitados"><spring:message code="lbl_autores_citados"/></label></div>
        <div class="input-box">
            <form:hidden id="idAutCitados" path="idAutCitados" />
            <input type="text" id="acAutCitadosTxt" /><img onclick="addItemToTable('idAutCitados', 'fontLstAutCitados', 'acAutCitadosTxt', 'acAutCitadosId', 'true')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acAutCitadosId" name="acAutCitadosId" />
        </div>
        <div id="fontLstAutCitados"></div>
        <br />
		
		<div class="label-box"><label for="idObrCitadas"><spring:message code="lbl_obras_citadas"/></label></div>
        <div class="input-box">
            <form:hidden id="idObrCitadas" path="idObrCitadas" />
            <input type="text" id="acObrCitadasTxt" /><img onclick="addItemToTable('idObrCitadas', 'fontLstObrCitadas', 'acObrCitadasTxt', 'acObrCitadasId', 'true')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acObrCitadasId" name="acObrCitadasId" />
        </div>
        <div id="fontLstObrCitadas"></div>
        <br />
		
		<div class="label-box"><label for="palavrasChave"><spring:message code="lbl_palavras_chave"/></label></div>
	    <div class="input-box"><form:input name="palavrasChave" id="palavrasChave" path="palavrasChave" /></div>
		<br />
		
		<div class="left">
			<div id="saveForm" class="button button-size"><spring:message code="btn_register"/></div>
			<div hidden="true" id="editForm" class="button button-size" onClick="reloadEdit(${fontes.id})"><spring:message code="btn_edit"/></div>
		</div>
	</form:form>
	
	<c:if test="${readonly}">
		<script type="text/javascript">
			makeReadonly();
		</script>
	</c:if>

	<jsp:include page="../includes/grid.jsp" flush="true" />
	
	<script type="text/javascript">
		fillListLocais('${fontes.idLocalImpressao}', 'fontLstLocalImpressao', 'idLocalImpressao', false);
		fillListLocais('${fontes.idLocalProducao}', 'fontLstLocalProducao', 'idLocalProducao', false);
		fillClassificacoes("${fontes.idClassificacao}");
		fillListGruMovimento('${fontes.idGruMovimento}', 'fontLstGruMovimento', 'idGruMovimento', false);
		fillListPersonagens('${fontes.strLeitores}', 'fontLstLeitores', 'idLeitores', true);
		fillListPersonagens('${fontes.strAutCitados}', 'fontLstAutCitados', 'idAutCitados', true);
		fillListObras('${fontes.strObrCitadas}', 'fontLstObrCitadas', 'idObrCitadas', true);
		fillPalavrasChave('${fontes.strPalChave}');
	</script>
</div>

<div id="popup-personagem" style="display: none;"></div>
</sec:authorize>

<jsp:include page="../includes/accessDenied.jsp" flush="true" />