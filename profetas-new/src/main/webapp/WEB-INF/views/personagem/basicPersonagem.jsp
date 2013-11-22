<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('SAVE')">
	<script src="<c:url value='/static/js/basicPersonagem.js'/>" type="text/javascript"></script>

	<form:form id="personagem" modelAttribute="personagem">
		<form:hidden id="divId" path="divId" />
		<div class="label-box"><label for="nome"><spring:message code="lbl_nome"/></label><span class="required"> (*)</span></div>
	    <div class="input-box"><form:input id="p_nome" path="nome" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="sobrenome"><spring:message code="lbl_sobrenome"/></label></div>
	    <div class="input-box"><form:input id="p_sobrenome" path="sobrenome" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="apelido"><spring:message code="lbl_apelido"/></label></div>
	    <div class="input-box"><form:input id="p_apelido" path="apelido" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="left">
			<div id="saveBasicForm" class="button button-size"><spring:message code="btn_register"/></div>
		</div>
	</form:form>
</sec:authorize>

<jsp:include page="../includes/accessDenied.jsp" flush="true" />