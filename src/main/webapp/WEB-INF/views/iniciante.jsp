<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="<c:url value='/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/assets/css/iniciante.css'/>">
</script>

<span class="iniciante-titulo">BEM VINDO AO SURICATO!</span>
<span class="iniciante-mensagem">Facilite suas retrospectivas independente de onde seus integrantes estejam!</span>
<div class="lista-opcoes">
	<a class="opcao --retrospectiva" href="<c:url value='/retrospectiva/nova'/>">
		<span class="glyphicon glyphicon-plus"></span>
		<span class="opcao-texto">Criar Retrospectiva</span>
	</a>
	<a class="opcao --time" href="<c:url value='/time/novo'/>">
		<span class="glyphicon glyphicon-plus"></span>
		<span class="opcao-texto">Criar time</span>
	</a>
</div>
<script type="text/javascript" src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>