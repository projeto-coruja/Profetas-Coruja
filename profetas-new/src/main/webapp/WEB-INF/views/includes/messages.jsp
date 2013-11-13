<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="corujaMessages"></div>
<c:if test="${not empty coruja_message}">
	<script type="text/javascript">
		addMessage("${coruja_message.message}", "${coruja_message.type.description}");
	</script>
</c:if>