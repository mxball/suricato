<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/login.css'/>">
		
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
		<title>Login</title>
	</head>
	<body>
		<fieldset class="login">
			<h1 class="login-titulo">suricato</h1>
			<form action="<c:url value='/login' />" method='POST' class="login-usuario">
				<c:if test="${not empty error}">
					<span class="login-usuario_error" id="login-errors">${error}</span>
				</c:if>
				<input type='text' name='username' class="login-usuario_dado" placeholder="Usuário"/>
				<input type='password' name='password' class="login-usuario_dado" placeholder="Senha"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" class="login-usuario_botao">LOGIN</button>
			</form>
			<span class="login-usuario_acao">
				Não possui cadastro? <a href='<c:url value="/usuario/cadastro"/>' class="--link">Cadastre-se já</a>
			</span>
		</fieldset>
	</body>
</html>