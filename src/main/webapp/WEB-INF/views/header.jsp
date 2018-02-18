<link rel="stylesheet" href="<c:url value='/assets/css/header.css'/>">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="cabecalho">
		<a href="<c:url value="/"/>" class="cabecalho-home">
			<img alt="Logo suricato" src="<c:url value="/assets/images/logo-suricato.svg"/>" class="cabecalho-home_logo">
		</a>
		<div class="cabecalho-usuario">
			<span class="cabecalho-usuario_nome">${usuario.nome}</span>
			<form action="<c:url value='/logout'/>" method="post" class="cabecalho-usuario_sair">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" class="logout"><fmt:message key="user.logout"/></button>
			</form>
		</div>
</div>