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
			<legend>Novo usuário</legend>
			<form action="<c:url value='/usuario/novo'/>">
				<input type='hidden' name='permissao.id' value="1"/>
				<form:errors path='usuario.nome'/>
				<label for="nome">Usuário:</label>
				<input type='text' name='nome' id="nome" value="${usuario.nome}"/>
				<form:errors path='usuario.senha'/>
				<label for="senha">Senha:</label>
				<input type='password' name='senha' id="senha" value="${usuario.senha}"/>
				<input type="submit" value="Cadastrar"/>
			</form>
		</fieldset>
	</body>
</html>