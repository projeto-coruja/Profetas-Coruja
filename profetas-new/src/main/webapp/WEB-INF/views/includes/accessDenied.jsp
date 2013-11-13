<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAnonymous()">
	<div class="content">
		Access denied.
	</div> 
</sec:authorize>