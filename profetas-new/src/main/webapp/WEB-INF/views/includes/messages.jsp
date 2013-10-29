<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="maritacaMessages"></div>
<c:if test="${not empty maritaca_message}">
	<script type="text/javascript">
		addMessage("${maritaca_message.message}", "${maritaca_message.type.description}");
	</script>
</c:if>