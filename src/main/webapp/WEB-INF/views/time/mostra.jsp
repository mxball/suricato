<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" type="image/png" href="<c:url value='/assets/images/logo.png'/>" />
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/corpo.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Montserrat:800" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value='/assets/css/cadastro.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/time/integrante.css'/>">
		<title>Novo time</title>
	</head>
	<body>
		<%@include file="/WEB-INF/views/header.jsp" %>
		<div class="corpo">
			<h2 class="corpo-descricao"><fmt:message key="team.edit.title"/></h2>
			<form action="<c:url value='/time/atualizar'/>" class="cadastro" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type='hidden' value="${time.id}" name="id"/>
				<label class="cadastro-descricao" for="nome">
					<fmt:message key="team"/> <form:errors path='time.nome' class="cadastro-erro"/>
				</label>
				<input type='text' name='nome' id="nome" class="cadastro-campo" value="${time.nome}" placeholder="<fmt:message key="team.name"/>"/>
				<label class="cadastro-descricao" for="Participantes">
					<fmt:message key="team.members"/> <form:errors path='time.integrantes' class="cadastro-erro"/>
				</label>
				<label class="cadastro-campo --lista-integrantes" for="novoIntegrante">
					<c:forEach var="integrante" items="${time.integrantes}" varStatus="status">
						<span class="integrante">
							${integrante.nome}
							<c:if test="${integrante != usuario }">
								<a class="integrante-remove glyphicon glyphicon-remove" data-usuario-id="${integrante.id}" data-time-id="${time.id}" data-posicao="${status.index}"></a>
							</c:if>
						</span>
						<input type='hidden' value="${integrante.id}" class="cadastro-campo_id" id="integrante_nome_${status.index}"/>
						<input type='hidden' value="${integrante.nome}" class="cadastro-campo_nome" id="integrante_id_${status.index}"/>
					</c:forEach>
					<input type="text" class="integrante-adiciona" id="novoIntegrante">
				</label>
				
				<label class="cadastro-descricao">
					<fmt:message key="team.color"/> <form:errors path='time.cor' class="cadastro-erro"/>
				</label>
				
				<input type="radio" name="cor" id="cor-azul" value="68e1ff" class="--seletorCor" ${time.cor eq '68e1ff' ? 'checked' : ''}>
				<label for="cor-azul" class="cadastro-campo_cor --azul"></label>
				
				<input type="radio" name="cor" id="cor-vermelho" value="ff6885" class="--seletorCor" ${time.cor eq 'ff6885' ? 'checked' : ''}>
				<label for="cor-vermelho" class="cadastro-campo_cor --vermelho"></label>
				
				<input type="radio" name="cor" id="cor-amarelo" value="ffb647" class="--seletorCor" ${time.cor eq 'ffb647' ? 'checked' : ''}>
				<label for="cor-amarelo" class="cadastro-campo_cor --amarelo"></label>
				
				<input type="radio" name="cor" id="cor-marinho" value="699ce0" class="--seletorCor" ${time.cor eq '699ce0' ? 'checked' : ''}>
				<label for="cor-marinho" class="cadastro-campo_cor --marinho"></label>
				
				<input type="radio" name="cor" id="cor-verde" value="6de069" class="--seletorCor" ${time.cor eq '6de069' ? 'checked' : ''}>
				<label for="cor-verde" class="cadastro-campo_cor --verde"></label>
				
				<input type="radio" name="cor" id="cor-rosa" value="eb5fea" class="--seletorCor" ${time.cor eq 'eb5fea' ? 'checked' : ''}>
				<label for="cor-rosa" class="cadastro-campo_cor --rosa"></label>
				
				<button type="submit" class="cadastro-botao"><fmt:message key="team.edit.button"/></button>
			</form>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
		<script src="<c:url value='/assets/js/time/mostra.js'/>"></script>
	</body>
</html>