<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="legenda" type="java.lang.String" required="true" %>
<%@ attribute name="classe" type="java.lang.String" required="false"%>
<%@ attribute name="retrospectivas" type="java.util.List" required="true" %>
<c:if test="${not empty retrospectivas}">
	<fieldset class="${classe}">
		<c:forEach var="retrospectiva" items="${retrospectivas}">
			<p class="retro">${retrospectiva.nome}</p>
			<a class="retro" href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
		</c:forEach>
	</fieldset>
</c:if>
