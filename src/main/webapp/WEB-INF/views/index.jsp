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
	<div class="retroHeader" id="container">
		<p class="retros" data-id='pessoal'>Pessoais</p>
		<c:forEach var="time" items="${usuario.times}">
			<p class="retros" data-id="time_${time.id}">${time.nome}</p>
		</c:forEach>
	</div>
	<div id="tipos">
		<p class="tipos abertas">Abertas</p>
		<p class="tipos fechadas">Fechadas</p>
	</div>
	<div class="retrospectiva pessoal hideme">
		<suricato:listaRetrospectivas retrospectivas="${usuario.retrospectivasAbertas}" legenda="Retrospectivas em aberto" classe="aberto"/>
		<suricato:listaRetrospectivas retrospectivas="${usuario.retrospectivasEncerradas}" legenda="Retrospectivas encerradas" classe="fechado"/>
	</div>
	<div>
		<c:forEach var="time" items="${usuario.times}">
			<div class="retrospectiva time_${time.id} hideme">
				<suricato:listaRetrospectivas retrospectivas="${time.retrospectivasAbertas}" legenda="Retrospectivas abertas do ${time.nome}" classe="aberto"/>
				<suricato:listaRetrospectivas retrospectivas="${time.retrospectivasEncerradas}" legenda="Retrospectivas encerradas do ${time.nome}" classe="fechado"/>
			</div>
		</c:forEach>
	</div>
<%@include file="footer.jsp" %>