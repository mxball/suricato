<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${time.nome}</title>
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/time/mostra.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">	
	</head>
	<body>
		<%@include file="/WEB-INF/views/header.jsp" %>
		<form:form role="form" commandName="usuario" servletRelativeAction="/time/adicionaUsuario" id="adiciona-integrante">
			<input type="hidden" name="times[0].id" value="${time.id}">
			<input type="text" name="nome" id="nomeUsuario" placeholder="Nome do novo integrante">
			<button type="submit" id="adicionar">Adicionar</button>
		</form:form>
		<div id="integrantes">
			<table id="tabela-integrantes">
				<thead>
					<tr>
						<th>
							Nome
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${time.conjuntoIntegrantes}" varStatus="status">
						<tr class="${ status.count % 2 == 0 ? 'even' : 'odd'}">
							<td>
								${usuario.nome}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
	</body>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/time/mostra.js'/>"></script>
</html>