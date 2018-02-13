<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/corpo.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/retrospectiva/lista.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home page</title>
</head>
<body>
	<%@include file="../header.jsp" %>
	<div class="time" style="background-color: #${timeRetrospectivas.cor}">
		<h2 class="time-nome">${timeRetrospectivas.titulo}</h2>
		<c:if test="${timeRetrospectivas.deTime}">
			<a class="time-editar glyphicon glyphicon-cog" href="<c:url value='/time/mostra?timeId=${timeRetrospectivas.timeId}'/>"></a>
		</c:if>
	</div>
	<div class="corpo">
		<div class="infos">
			<div class="infos-time">
				<span class="infos-time_dado" style="color: #${timeRetrospectivas.cor}">
					<span class="--numero">${timeRetrospectivas.numeroRetrospectivas}</span> Retrospectivas
				</span>
				<span class="infos-time_dado" style="color: #${timeRetrospectivas.cor}">
					<span class="--numero">${timeRetrospectivas.numeroIntegrantes }</span> Participantes
				</span>
				<div class="infos-lista_fotos">
					<c:forEach var="integrante" items="${timeRetrospectivas.integrantes}">
						<img class="--foto" alt="${integrante}" title="${integrante}" src="<c:url value='/usuario/perfil/${integrante}'/>">
					</c:forEach>
				</div>
				<c:if test="${timeRetrospectivas.deTime}">
					<a class="infos-time_integrante" href="<c:url value='/time/mostra?timeId=${timeRetrospectivas.timeId}'/>">Add membros</a>
				</c:if>
			</div>
			<div class="infos-retrospectiva">
				<a class="infos-retrospectiva_nova" href="<c:url value='/retrospectiva/nova?timeId=${timeRetrospectivas.timeId}'/>">Criar Retrospectiva</a>
				<a class="infos-retrospectiva_disposicao glyphicon glyphicon-th-large --minimized" data-selected></a>
				<a class="infos-retrospectiva_disposicao glyphicon glyphicon-stop --maximized"></a>
			</div>
		</div>
		<ul class="lista-retrospectivas --minimized">
			<c:forEach var="lousa" items="${timeRetrospectivas.lousas}">
				<li class="retrospectiva">
					<a href="<c:url value='/retrospectiva/mostra?id=${lousa.retroId}'/>">
						<img alt="${lousa.nome}" src="${lousa.endereco}" class="retrospectiva-imagem"></img>
					</a>
				</li>
			</c:forEach>
		</ul>
		<script src="<c:url value='/assets/js/retrospectiva/lista.js'/>"></script>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>