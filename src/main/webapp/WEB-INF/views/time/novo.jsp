<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Novo time</title>
	</head>
	<body>
		<fieldset id="time">
			<legend>Novo time</legend>
			<form action="<c:url value='/time/criar'/>">
				<input type='hidden' name='integrantes[0].id' value="${usuario.id}"/>
				<input type='hidden' name='integrantes[0].nome' value="${usuario.nome}"/>
				<form:errors path='time.nome'/>
				<label for="time">Time:</label>
				<input type='text' name='nome' id="time" value="${time.nome}"/>
				<input type="submit" value="Cadastrar"/>
			</form>
		</fieldset>
	</body>
</html>