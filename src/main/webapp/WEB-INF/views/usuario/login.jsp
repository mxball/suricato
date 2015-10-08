<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/login.css'/>">
		<title>Login</title>
	</head>
	<body>
		<fieldset id="usuario">
			<legend>Suricato</legend>
			<c:if test="${not empty error}">
				<span class="error" id="login-errors">${error}</span>
			</c:if>
			<form name='loginForm' action="<c:url value='/login' />" method='POST'>
				<label for="nome">Usuário:</label>
				<input type='text' name='username' id="nome"/><br/>
				<label for="senha">Senha:</label>
				<input type='password' name='password' id="senha"/><br/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Login"/>
			</form>
			<a href='<c:url value="/usuario/cadastro"/>'>Novo usuário</a>
		</fieldset>
	</body>
</html>