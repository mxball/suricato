<link rel="stylesheet" href="<c:url value='/assets/css/header.css'/>">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div id="container" class="head">
		<a href="<c:url value="/"/>"><img alt="suricato-logo" id="logo" src='<c:url value="/assets/images/logo.svg"/>'></a>
		<form action="<c:url value='/logout'/>" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit">Logout (${usuario.nome})</button>
		</form>
<!-- 	<ul>  			 -->
<%-- 		<c:forEach var="time" items="${usuario.times}"> --%>
<%-- 			<li><a href='<c:url value="/time/mostra?id=${time.id}"/>'>${time.nome}</a></li> --%>
<%-- 		</c:forEach>		 --%>
<!-- 		<li id="logout"> -->
<!-- 		</li>  		 -->
<!-- 	</ul>  	 -->
</div>