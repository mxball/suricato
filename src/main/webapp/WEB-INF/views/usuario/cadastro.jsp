<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/login.css'/>">
		<title>Novo usuário</title>
	</head>
	<body>
		<fieldset id="usuario">
			<img alt="suricato" src="<c:url value='/assets/images/logo.png'/>">
			<form:errors path='usuario.nome' cssClass="error"/>
			<form:errors path='usuario.senha' cssClass="error"/>
			<c:if test="${not empty error}">
				<span class="error" id="login-errors">${error}</span>
			</c:if>
			<form action="<c:url value='/usuario/novo'/>">
				<input type='hidden' name='permissao.id' value="1"/>
				<input type='text' name='nome' id="nome" value="${usuario.nome}" placeholder="Usuário"/>
				<input type='password' name='senha' id="senha" value="${usuario.senha}" placeholder="Senha"/>
				<input type="submit" value="Cadastrar"/>
			</form>
			<a href='<c:url value="/login"/>'>Voltar</a>
		</fieldset>
	</body>
</html>