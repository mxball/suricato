<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Nova retrospectiva</title>
		<link rel="icon" type="image/png" href="<c:url value='/assets/images/logo.png'/>" />
		<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/corpo.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Montserrat:800" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value='/assets/css/cadastro.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/retrospectiva/cadastroLousa.css'/>">
	</head>
	<body>
		<%@include file="../header.jsp" %>
		<div class="corpo">
			<h2 class="corpo-descricao"><fmt:message key="retrospective.new.title"/></h2>
			<form:form role="form" commandName="retrospectiva" servletRelativeAction="/retrospectiva/cria" class="cadastro" method="post">			
				<label for="time" class="cadastro-descricao">
					<fmt:message key="retrospective.team"/> <form:errors path='adicionaRetrospectivaDTO.timeId' class="cadastro-erro"/> 
				</label>
				<select name="timeId" id="time" class="cadastro-campo">
					<option value=""><fmt:message key="retrospective.individual"/></option>
					<c:forEach var="time" items="${usuario.conjuntoTimes}">
						<option value="${time.id}" ${time.id eq timeId ? 'selected' : '' }>${time.nome}</option>
					</c:forEach>
				</select>
				<label for="idLousa" class="cadastro-descricao">
					<fmt:message key="retrospective.activity"/> <form:errors path='adicionaRetrospectivaDTO.lousaId' class="cadastro-erro"/>
				</label>
				<select name="lousaId"  id="idLousa" onchange="mostraLousa()" class="cadastro-campo">
					<c:forEach items="${lousas}" var="lousa" varStatus="status">
						<option value="${lousa.id}" id="atividade_${status.index}" data-url="${lousa.endereco}">${lousa.nome}</option>
					</c:forEach>
				</select>
				<div class="quadro">
					<c:forEach items="${lousas}" var="lousa" varStatus="status">
						<div class="quadro-lousa">
							<input type="radio" name="lousa" readonly="readonly" id="quadro_${lousa.id}" class="quadro-lousa_seletor" data-lousa-id="${lousa.id}">
							<label for="quadro_${lousa.id}">
								<img alt="${lousa.nome}" src="${lousa.endereco}" class="quadro-lousa_foto"></img>
							</label>
						</div>
					</c:forEach>
				</div>
				<label for="dataInicio" class="calendario cadastro-descricao --data">
					<fmt:message key='retrospective.date.begin'/>
					<suricato:calendario id="dataInicio" value="${hoje}"/>
				</label>
				<label for="dataFim" class="calendario cadastro-descricao --data">
					<fmt:message key='retrospective.date.end'/>
					<suricato:calendario id="dataFim"/>
				</label>
				<button type="submit" class="cadastro-botao --retrospectiva"><fmt:message key="retrospective.new.button"/></button>
			</form:form>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/nova.js'/>"></script>
</html>