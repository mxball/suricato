<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="<c:url value='/assets/css/iniciante.css'/>">
</script>

<span class="iniciante-titulo"><fmt:message key="welcome.title"/></span>
<span class="iniciante-mensagem"><fmt:message key="welcome.description"/></span>
<div class="lista-opcoes">
	<a class="opcao --retrospectiva" href="<c:url value='/retrospectiva/nova'/>">
		<span class="glyphicon glyphicon-plus"></span>
		<span class="opcao-texto"><fmt:message key="retrospective.new"/></span>
	</a>
	<a class="opcao --time" href="<c:url value='/time/novo'/>">
		<span class="glyphicon glyphicon-plus"></span>
		<span class="opcao-texto"><fmt:message key="team.new"/></span>
	</a>
</div>
<script type="text/javascript" src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>