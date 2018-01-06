<link rel="stylesheet" href="<c:url value='/assets/css/header.css'/>">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div class="cabecalho">
		<h2 class="cabecalho-titulo">
			<a href="<c:url value="/"/>" class="cabecalho-home">
				suricato
			</a>
		</h2>
		<div class="cabecalho-usuario">
			<span class="cabecalho-usuario_nome">${usuario.nome}</span>
			<form action="<c:url value='/logout'/>" method="post" class="cabecalho-usuario_sair">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" class="logout">Sair</button>
			</form>
		</div>
</div>