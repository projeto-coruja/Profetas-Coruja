<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="required" required="false" type="java.lang.String" description="Nome do campo" %>
<%@ attribute name="name" required="true" type="java.lang.String" description="Nome do campo" %>
<%@ attribute name="title" required="true" type="java.lang.String" description="Título (ie. Nome do autor)" %>
<%@ attribute name="tip" required="true" type="java.lang.String" description="Instrução do campo" %>
<%@ attribute name="hasStatus" required="false" type="java.lang.String" description="Instrução do campo" %>

<c:if test="${not empty title}">
	<tr>
		<td class="tdPesquisa"><label class="labelFormsSearch" id="l${name}" for="${name}">&#8226 ${title} <c:if test="${required == 'true'}"><font color="red">*</font></c:if></label></td>
	</tr>
</c:if>	
<tr>
	<td class="field"><jsp:doBody/>
	<label></label><c:if test="${not empty tip}"><a href="#"><img class="iconeAjuda" src="<c:url value="/images/icone_ajuda.png"/>" title="${tip}" /></a></c:if>
	</td>
	<c:if test="${not empty hasStatus && hasStatus eq 'true'}"><td class="status"></td></c:if>
</tr>