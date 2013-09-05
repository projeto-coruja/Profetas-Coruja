<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="string" required="true" type="java.lang.String" description="Nome do campo" %>
<%@ attribute name="array" required="true" type="java.lang.String[]" description="Nome do campo" %>

<c:forEach var="str" items="${array}">
	<c:if test="${string eq str}">
		<jsp:doBody/>
	</c:if>
</c:forEach>