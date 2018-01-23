<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/assets/css/retros.css'/>">
<h2 class="lista-times">Retrospectivas</h2>
<c:forEach var="timeRetros" items="${mapaTimes.timesRetrosAbertas}">
	<a href="<c:url value='/retrospectiva/time/abertas?timeId=${timeRetros.timeId}'/>" class="time" style="background-color: #${timeRetros.cor}">
		<h3 class="time-nome">${timeRetros.nomeTime}</h3>
		<span class="time-quantidade">
			<span class="--numero">${timeRetros.quantidadeDeRetros}</span> Retrospectivas
		</span>
		<span class="time-integrantes">
			<span class="--numero">${timeRetros.quantidadeIntegrantes}</span> Participantes
		</span>
	</a>
</c:forEach>
<div class="lista-cadastros">
	<a class="cadastro --retrospectiva" href="<c:url value='/retrospectiva/nova'/>">Criar Retrospectiva</a>
	<a class="cadastro --time" href="<c:url value='/time/novo'/>">Criar Time</a>
</div>
<h2 class="lista-times">Retrospectivas terminadas</h2>
<c:forEach var="timeRetros" items="${mapaTimes.timesRetrosFechadas}">
	<a href="<c:url value='/retrospectiva/time/fechadas?timeId=${timeRetros.timeId}'/>" class="time" style="background-color: #${timeRetros.cor}">
		<h3 class="time-nome">${timeRetros.nomeTime}</h3>
		<span class="time-quantidade">
			<span class="--numero">${timeRetros.quantidadeDeRetros}</span> Retrospectivas
		</span>
		<span class="time-integrantes">
			<span class="--numero">${timeRetros.quantidadeIntegrantes} Participantes</span>
		</span>
	</a>
</c:forEach>