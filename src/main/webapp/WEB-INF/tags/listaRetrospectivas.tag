<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="retrospectivasAbertas" type="java.util.List" required="true" %>
<%@ attribute name="retrospectivasFechadas" type="java.util.List" required="true" %>
<div class="lista-abertas">
	<c:if test="${not empty retrospectivasAbertas}">
		<c:forEach var="retrospectiva" items="${retrospectivasAbertas}">
			<p class="retro">${retrospectiva.nome}</p>
			<a class="retro" href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img class="retrospectiva-img" src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
		</c:forEach>
	</c:if>
</div>
<div class="lista-fechadas hideme">
	<c:if test="${not empty retrospectivasFechadas}">
		<c:forEach var="retrospectiva" items="${retrospectivasFechadas}">
			<p class="retro">${retrospectiva.nome}</p>
			<a class="retro" href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img class="retrospectiva-img" src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
		</c:forEach>
	</c:if>
</div>