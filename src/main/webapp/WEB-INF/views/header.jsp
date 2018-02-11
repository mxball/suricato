<link rel="stylesheet" href="<c:url value='/assets/css/header.css'/>">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div class="cabecalho">
		<a href="<c:url value="/"/>" class="cabecalho-home">
			<img alt="Logo suricato" src="<c:url value="/assets/images/logo-suricato.svg"/>" class="cabecalho-home_logo">
		</a>
		<div class="cabecalho-usuario">
			<a href="/usuario/edita" class="cabecalho-usuario_nome">${usuario.nome}</a>
			<form action="<c:url value='/logout'/>" method="post" class="cabecalho-usuario_sair">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" class="logout">Sair</button>
			</form>
		</div>
</div>