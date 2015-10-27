<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${time.nome}</title>
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">	
	</head>
	<body>
		<form:form role="form" commandName="usuario" servletRelativeAction="/time/adicionaUsuario">
			<input type="hidden" name="times[0].id" value="${time.id}">
			Novo usuario: <input type="text" name="nome" id="nomeUsuario" data-url="${pageContext.request.contextPath}">
			<button type="submit">Adicionar</button>
		</form:form>
		<table>
			<thead>
				<tr>
					<th>
						Nome
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="usuario" items="${time.integrantes}">
					<tr>
						<td>
							${usuario.nome}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/time/mostra.js'/>"></script>
</html>