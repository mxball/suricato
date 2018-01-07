<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/login.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
		<title>Novo usuário</title>
	</head>
	<body>
		<fieldset class="login">
			<h1 class="login-titulo">suricato</h1>
			<form action="<c:url value='/usuario/cadastro'/>" class="login-usuario" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<form:errors path='usuario.nome' cssClass="login-usuario_error"/>
				<input type='text' name='nome' class="login-usuario_dado" value="${cadastroUsuarioDTO.nome}" placeholder="Usuário"/>
				<form:errors path='senha' cssClass="login-usuario_error"/>
				<form:errors path='usuario.senha' cssClass="login-usuario_error"/>
				<input type='password' name='senha' class="login-usuario_dado" placeholder="Senha"/>
				<form:errors path='usuario.confirmaSenha' cssClass="login-usuario_error"/>
				<input type='password' name='confirmaSenha' class="login-usuario_dado" placeholder="Confirme sua senha"/>
				<button type="submit" class="login-usuario_botao">CADASTRAR</button>
			</form>
			<span class="login-usuario_acao">
				Já possui cadastro? <a href='<c:url value="/login"/>' class="--link">Volte para o login</a>
			</span>
		</fieldset>
	</body>
</html>