<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div id="container">  	
	<ul>  			
		<li><a href="<c:url value="/"/>">Home</a></li>  			
		<li><a href='<c:url value="/retrospectiva/nova"/>'>Nova retrospectiva</a></li>  
		<li><a href='<c:url value="/time/novo"/>'>Novo time</a></li>
		<c:forEach var="time" items="${usuario.times}">
			<li><a href='<c:url value="/time/mostra?id=${time.id}"/>'>${time.nome}</a></li>
		</c:forEach>		
		<li id="logout">
			<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Logout">
			</form>
		</li>  		
	</ul>  	
</div>
