<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/time/novo.css'/>">
		<title>Novo time</title>
	</head>
	<body>
		<%@include file="/WEB-INF/views/header.jsp" %>
		<fieldset id="adiciona-time">
			<legend>Novo time</legend>
			<form action="<c:url value='/time/criar'/>">
				<input type='hidden' name='integrantes[0].id' value="${usuario.id}"/>
				<input type='hidden' name='integrantes[0].nome' value="${usuario.nome}"/>
				<form:errors path='time.nome'/>
				<input type='text' name='nome' id="time" value="${time.nome}" placeholder="Nome do time"/>
				<input type="submit" id="cadastrar" value="Cadastrar"/>
			</form>
		</fieldset>
		<%@include file="/WEB-INF/views/footer.jsp" %>
	</body>
</html>