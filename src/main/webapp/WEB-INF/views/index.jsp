<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home page</title>
</head>
<body>
	<%@include file="header.jsp" %>
	<div id="filtro-retrospectivas">
		<h2>Retrospectiva:</h2>
		<label for="pessoal">
			<input type="radio" name="filtro-grupo" id="pessoal" checked class="retros">
			Pessoais
		</label>
		<c:forEach var="time" items="${usuario.conjuntoTimes}">
			<label for="time_${time.id}" class="retros">
				<input type="radio" name="filtro-grupo" id="time_${time.id}" data-id="${time.id}" class="retros"> 
				${time.nome}
			</label>
		</c:forEach>
	</div>
	<div id="tipos">
		<label for="tipo-aberta">
			<input type="radio" name="filtro-tipos" id="tipo-aberta" checked class="tipo">
			Abertas
		</label>
		<label for="tipo-fechada">
			<input type="radio" name="filtro-tipos" id="tipo-fechada" class="tipo">
			Fechadas
		</label>
	</div>
	<div id="lista-retrospectivas">
		<div class="retrospectiva pessoal" data-usuario-id="${usuario.id}">
		</div>
		<div>
			<c:forEach var="time" items="${usuario.conjuntoTimes}">
				<div class="retrospectiva time time_${time.id} hideme" data-time-id="${time.id}">
				</div>
			</c:forEach>
		</div>
	</div>
	<%@include file="footer.jsp" %>
	<script src="<c:url value='/assets/js/index.js'/>"></script>
</body>
</html>