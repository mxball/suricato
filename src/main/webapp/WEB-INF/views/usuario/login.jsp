<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" type="image/png" href="<c:url value='/assets/images/logo.png'/>" />
		<link rel="stylesheet" href="<c:url value='/assets/css/language.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/login.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Montserrat:800" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
		<title>Login</title>
	</head>
	<body>
		<fieldset class="login">
			<img alt="Logo suricato" src="<c:url value="/assets/images/logo-suricato.svg"/>" class="login-titulo">
			<form action="<c:url value='/login' />" method='POST' class="login-usuario">
				<c:if test="${not empty error}">
					<span class="login-usuario_error" id="login-errors">${error}</span>
				</c:if>
				
				<input type='text' name='username' class="login-usuario_dado" placeholder="<fmt:message key="user.name"/>"/>
				<input type='password' name='password' class="login-usuario_dado" placeholder="<fmt:message key="user.password"/>"/>
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" class="login-usuario_botao">LOGIN</button>
				
			</form>
			<span class="login-usuario_acao">
				<fmt:message key="user.new.text"/>
				<a href='<c:url value="/usuario/cadastro"/>' class="--link">
					<fmt:message key="user.signup"/>
				</a>
			</span>
			<select class="linguagem">
				<option value="pt" class="linguagem-opcao">Português</option>
				<option value="en" class="linguagem-opcao">English</option>
			</select>
		</fieldset>
	</body>
	<script src="<c:url value='/assets/js/language.js'/>"></script>
</html>