<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
	<thead>
		<tr>
			<th>
				Nome
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="usuario" items="${usuarios}">
			<tr>
				<td>
					${usuario.nome}
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>