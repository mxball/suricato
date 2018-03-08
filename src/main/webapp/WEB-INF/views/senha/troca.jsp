<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<form action="<c:url value='/senha/troca' />" method='POST' class="login-usuario">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="token" value="${tokenSenha.token}" />
				<c:if test="${not empty error}">
					<span class="login-usuario_error" id="login-errors">${error}</span>
				</c:if>
				<form:errors path='novaSenhaDTO.senha' cssClass="login-usuario_error"/>
				<input type='password' name='senha' class="login-usuario_dado" placeholder="<fmt:message key="user.password"/>"/>
				<form:errors path='novaSenhaDTO.confirmaSenha' cssClass="login-usuario_error"/>
				<input type='password' name='confirmaSenha' class="login-usuario_dado" placeholder="<fmt:message key="user.confirm.password"/>"/>
				
				<button type="submit" class="login-usuario_botao"><fmt:message key="user.confirm.password"/></button>
			</form>
			
			<select class="linguagem">
				<option value="pt" class="linguagem-opcao">Português</option>
				<option value="en" class="linguagem-opcao">English</option>
			</select>
		</fieldset>
	</body>
	<script src="<c:url value='/assets/js/language.js'/>"></script>
</html>