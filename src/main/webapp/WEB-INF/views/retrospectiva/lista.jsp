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
	<div class="time" style="background-color: #${time.cor}">
		<h2 class="time-nome">${time.nome}</h2>
		<a class="time-editar glyphicon glyphicon-cog" href="<c:url value='/time/mostra?timeId=${time.id}'/>"></a>
	</div>
	<div class="corpo">
		<div class="infos">
			<div class="infos-time">
				<span class="infos-time_dado" style="color: #${time.cor}">
					<span class="--numero">${retrospectivas.size()}</span> Retrospectivas
				</span>
				<span class="infos-time_dado" style="color: #${time.cor}">
					<span class="--numero">${time.integrantes.size() }</span> Participantes
				</span>
				<a class="infos-time_integrante" href="<c:url value='/time/mostra?timeId=${time.id}'/>">Add membros</a>
			</div>
			<div class="infos-retrospectiva">
				<a class="infos-retrospectiva_nova" href="<c:url value='/retrospectiva/nova?timeId=${time.id}'/>">Criar Retrospectiva</a>
				<a class="infos-retrospectiva_disposicao glyphicon glyphicon-th-large --minimized" data-selected></a>
				<a class="infos-retrospectiva_disposicao glyphicon glyphicon-stop --maximized"></a>
			</div>
		</div>
		<ul class="lista-retrospectivas --minimized">
			<c:forEach var="retro" items="${retrospectivas}">
				<li class="retrospectiva">
					<a href="<c:url value='/retrospectiva/mostra?id=${retro.id}'/>">
						<img alt="${retro.lousa.nome}" src="${retro.lousa.endereco}" class="retrospectiva-imagem"></img>
					</a>
				</li>
			</c:forEach>
		</ul>
		<script src="<c:url value='/assets/js/retrospectiva/lista.js'/>"></script>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>