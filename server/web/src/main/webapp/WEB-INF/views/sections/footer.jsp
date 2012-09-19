<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<ul id="sub_footer">
	<li>
		<div>
			<span class="sub_footer_title"><spring:message code="project_name"/></span>
			<br /><br />
			<ul id="sub_footer_item">
				<li><a href="<c:url value="/about.html"/>" class="sub_footer_item"><spring:message code="project_about"/></a></li>
				<li><a href="<c:url value="/license.html"/>" class="sub_footer_item"><spring:message code="project_license"/></a></li>
				<li><a href="<c:url value="/disclaimer.html"/>" class="sub_footer_item"><spring:message code="project_disclaimer"/></a></li>
				<li><a href="<c:url value="/developers.html"/>" class="sub_footer_item"><spring:message code="project_developers"/></a></li>
			</ul>
		</div>
	</li>		
	<li>
		<div>
			<span class="sub_footer_title"><spring:message code="project_repositories"/></span>
			<br /><br />
			<ul id="sub_footer_item">
				<li><a href="https://sourceforge.net/p/maritaca/" class="sub_footer_item" target="_blank">Sourceforge</a></li>
				<li><a href="https://github.com/maritaca-unifesp/maritaca" class="sub_footer_item" target="_blank">Github</a></li>
			</ul>		
		</div>
	</li>		
	<li>
		<div>
			<span class="sub_footer_title"><spring:message code="project_documentation"/></span>
			<br />
		</div>
	</li>		
	<li>
		<div>
			<span class="sub_footer_title"><spring:message code="project_extras"/></span>
			<br />
		</div>
	</li>
</ul>