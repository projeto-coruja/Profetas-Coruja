<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('SAVE')">

<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/personagem.js'/>" type="text/javascript"></script>

<div class="content">
    <span class="title"><spring:message code="ttl_personagem"/></span>
   
    <form:form id="personagem" modelAttribute="personagem">
        <form:hidden id="id" path="id" />
        <div class="label-box"><label for="nome"><spring:message code="lbl_nome"/></label><span class="required"> (*)</span></div>
        <div class="input-box"><form:input id="nome" path="nome" size="50" maxlength="100" /></div>       
        <br />
       
        <div class="label-box"><label for="sobrenome"><spring:message code="lbl_sobrenome"/></label></div>
        <div class="input-box"><form:input id="sobrenome" path="sobrenome" size="50" maxlength="100" /></div>       
        <br />
       
        <div class="label-box"><label for="apelido"><spring:message code="lbl_apelido"/></label></div>
        <div class="input-box"><form:input id="apelido" path="apelido" size="50" maxlength="100" /></div>       
        <br />
       
        <div class="label-box"><label for="idNascimento"><spring:message code="lbl_loc_nascimento"/></label></div>
        <div class="input-box">
            <form:hidden id="idNascimento" path="idNascimento" />
            <input type="text" id="acLocalNascimentoTxt" /><img onclick="addItemToTable('idNascimento', 'persLstLocalNascimento', 'acLocalNascimentoTxt', 'acLocalNascimentoId', 'false')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acLocalNascimentoId" name="acLocalNascimentoId" />
        </div>
        <div id="persLstLocalNascimento"></div>
        <br />
       
        <div class="label-box"><label for="data_nascimento"><spring:message code="lbl_data_nascimento"/></label></div>
        <div class="input-box"><form:input id="dataNascimento" path="dataNascimento" size="50" maxlength="100" /></div>       
        <br />
       
        <div class="label-box"><label for="idMorte"><spring:message code="lbl_loc_morte"/></label></div>
        <div class="input-box">
            <form:hidden id="idMorte" path="idMorte" />
            <input type="text" id="acLocalMorteTxt" /><img onclick="addItemToTable('idMorte', 'persLstLocalMorte', 'acLocalMorteTxt', 'acLocalMorteId', 'false')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acLocalMorteId" name="acLocalMorteId" />
        </div>
        <div id="persLstLocalMorte"></div>
        <br />
       
        <div class="label-box"><label for="data_morte"><spring:message code="lbl_data_morte"/></label></div>
        <div class="input-box"><form:input id="dataMorte" path="dataMorte" size="50" maxlength="100" /></div>       
        <br />
       
        <div class="label-box"><label for="biografia"><spring:message code="lbl_biografia"/></label></div>
        <div class="input-box"><form:textarea id="biografia" path="biografia" cols="70" rows="5" /></div>       
        <br />
       
        <div class="label-box"><label for="ocupacao"><spring:message code="lbl_ocupacao"/></label></div>
        <div class="input-box"><form:input id="ocupacao" path="ocupacao" size="50" maxlength="100" /></div>       
        <br />
       
        <div class="label-box"><label for="formacao"><spring:message code="lbl_formacao"/></label></div>
        <div class="input-box"><form:textarea id="formacao" path="formacao" cols="70" rows="5" /></div>       
        <br />
       
        <div class="label-box"><label for="refBibliografica"><spring:message code="lbl_ref_bibliografica"/></label></div>
        <div class="input-box"><form:textarea id="refBibliografica" path="refBibliografica" cols="70" rows="5" /></div>   
        <br />
       
        <div class="label-box"><label for="idReligioes"><spring:message code="lbl_religioes"/></label></div>
		<div class="input-box">
			<form:hidden id="idReligioes" path="idReligioes" />
			<input type="text" id="acReligioesTxt" /><img onclick="addItemToTable('idReligioes', 'persLstReligioes', 'acReligioesTxt', 'acReligioesId', 'true')" src="<c:url value='/static/images/add.png'/> " />
			<input type="hidden" id="acReligioesId" name="acReligioesId" />
		</div>
		<div id="persLstReligioes"></div>
		<br />

       	<div class="label-box"><label for="idObras"><spring:message code="lbl_obras"/></label></div>
		<div class="input-box">
			<form:hidden id="idObras" path="idObras" />
			<input type="text" id="acObrasTxt" /><img onclick="addItemToTable('idObras', 'persLstObras', 'acObrasTxt', 'acObrasId', 'true')" src="<c:url value='/static/images/add.png'/> " />
			<input type="hidden" id="acObrasId" name="acObrasId" />
		</div>
		<div id="persLstObras"></div>
		<br />
		
        <div class="label-box"><label for="idLocaisPers"><spring:message code="lbl_locais_passou"/></label></div>
        <div class="input-box">
            <form:hidden id="idLocaisPers" path="idLocaisPers" />
            <input type="text" id="acLocaisPassoulTxt" /><img onclick="addItemToTable('idLocaisPers', 'persLstLocaisPassou', 'acLocaisPassoulTxt', 'acLocaisPassouId', 'true')" src="<c:url value='/static/images/add.png'/> " />
            <input type="hidden" id="acLocaisPassouId" name="acLocaisPassouId" />
        </div>
        <div id="persLstLocaisPassou"></div>
        <br />
       
        <div class="label-box"><label for="idEncontros"><spring:message code="lbl_encontros"/></label></div>
        <div id="lstEncontros"></div>
        <br />
       
        <div class="left">
            <div id="saveForm" class="button button-size"><spring:message code="btn_register"/></div>
            <div hidden="true" id="editForm" class="button button-size" onClick="reloadEdit(${personagem.id})"><spring:message code="btn_edit"/></div>
        </div>
    </form:form>
   
    <jsp:include page="../includes/grid.jsp" flush="true" />
   
    <script type="text/javascript">
        fillListLocais('${personagem.idNascimento}', 'persLstLocalNascimento', 'idNascimento', false);
        fillListLocais('${personagem.idMorte}', 'persLstLocalMorte', 'idMorte', false);
        fillListLocais('${personagem.strLocaisPers}', 'persLstLocaisPassou', 'idLocaisPers', true);
        fillListReligioesCrencas('${personagem.strReligioes}', 'persLstReligioes', 'idReligioes', true);
        fillListObras('${personagem.strObras}', 'persLstObras', 'idObras', true);
        fillEncontros('${personagem.encontrosJson}', 'lstEncontros');
    </script>
   
    <c:if test="${readonly}">
        <script type="text/javascript">
            makeReadonly();
        </script>
    </c:if>
   
</div>

</sec:authorize>

<jsp:include page="../includes/accessDenied.jsp" flush="true" />