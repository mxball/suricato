<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div id="container">  	
	<ul>  			
		<li><a href="<c:url value="/"/>">Home</a></li>  			
		<li><a href="#">Section 2</a></li>  			
		<li><a href="#">Section 3</a></li>  			
		<li id="logout">
			<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Logout">
			</form>
		</li>  		
	</ul>  	
</div>
