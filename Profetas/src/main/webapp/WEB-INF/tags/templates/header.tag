<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="type" required="true" type="java.lang.String" description="Tipo de header a ser utilizado (basic / full)" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<meta charset="utf-8">
<title>Profetas</title>

<!-- Import dos styles CSS -->
<link rel="stylesheet" type="text/css"	href="<c:url value = "/css/principal.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value = "/css/tabs.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value = "/css/controle.css"/>" />

<!-- CSS das validacoes -->
<link rel="stylesheet" type="text/css" href="<c:url value = "/css/validationEngine.jquery.css" />" />

<!-- Import dos javascripts -->
<script src="<c:url value = "/javascript/jquery.js" />" type="text/javascript"	charset="utf-8"></script>
<script src="<c:url value = "/javascript/utility.js" />" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value = "/javascript/cookie.js" />" type="text/javascript"	charset="utf-8"></script>

<c:if test="${type == 'full'}">

	<!-- Import dos javascripts -->
	<script type="text/javascript" src="<c:url value ="/javascript/ajax.js"/>" charset="utf-8"></script>
	<script type="text/javascript" src="<c:url value ="/javascript/instrucao.js"/>" charset="utf-8"></script>
	<script type="text/javascript" src="<c:url value ="/javascript/chili-1.7.pack.js"/>" ></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" charset="utf-8"></script>

	<!-- Import dos scripts de validacao de formulario -->
	<script src="<c:url value ="/javascript/jquery.js"/>" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value ="/javascript/validate.js"/>" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value ="/javascript/validate_pt_br.js"/>" type="text/javascript" charset="utf-8"></script>
	
</c:if>