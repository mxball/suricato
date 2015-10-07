<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Novo usuário</title>
	</head>
	<body>
		<form action="<c:url value='/usuario/novo'/>">
			<input type='hidden' name='permissao.id' value="1"/><br/>
			<form:errors path='usuario.nome'/><br/>
			Usuário: <input type='text' name='nome' value="${usuario.nome}"/><br/>
			<form:errors path='usuario.senha'/><br/>
			Senha: <input type='password' name='senha' value="${usuario.senha}"/><br/>
			<input type="submit" value="Cadastrar"/>
		</form>
	</body>
</html>