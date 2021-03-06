<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="https://fonts.googleapis.com/css?family=Montserrat:800" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/assets/css/retros.css'/>">
<h2 class="lista-times"><fmt:message key="home.retrospective.title"/></h2>
<c:forEach var="timeRetros" items="${mapaTimes.timesRetrosAbertas}">
	<c:choose>
		<c:when test="${timeRetros.pessoal}">
		 	<c:set var="url" value="/retrospectiva/pessoal/abertas"></c:set>
		</c:when>
		<c:otherwise>
	 		<c:set var="url" value="/retrospectiva/time/abertas?timeId=${timeRetros.timeId}"></c:set>
		</c:otherwise>
	</c:choose>
	<a href="<c:url value='${url}'/>" class="time" style="background: linear-gradient(to bottom, #${timeRetros.cor}, #${timeRetros.corGradiente})">
		<c:choose>
			<c:when test="${timeRetros.nomeTime eq 'Pessoal'}">
				<h3 class="time-nome"><fmt:message key="retrospective.individual"/></h3>
			</c:when>
			<c:otherwise>
				<h3 class="time-nome">${timeRetros.nomeTime}</h3>
			</c:otherwise>
		</c:choose>
		<span class="time-quantidade">
			<span class="--numero">${timeRetros.quantidadeDeRetros}</span> <fmt:message key="home.retrospective"/>
		</span>
		<span class="time-integrantes">
			<span class="--numero">${timeRetros.quantidadeIntegrantes}</span> <fmt:message key="home.team.members"/>
		</span>
	</a>
</c:forEach>
<div class="lista-cadastros">
	<a class="cadastro --retrospectiva" href="<c:url value='/retrospectiva/nova'/>"><fmt:message key="retrospective.new"/></a>
	<a class="cadastro --time" href="<c:url value='/time/novo'/>"><fmt:message key="team.new"/></a>
</div>
<h2 class="lista-times"><fmt:message key="home.retrospective.closed.title"/></h2>
<c:forEach var="timeRetros" items="${mapaTimes.timesRetrosFechadas}">
	<c:choose>
		<c:when test="${timeRetros.pessoal}">
		 	<c:set var="url" value="/retrospectiva/pessoal/fechadas"></c:set>
		</c:when>
		<c:otherwise>
	 		<c:set var="url" value="/retrospectiva/time/fechadas?timeId=${timeRetros.timeId}"></c:set>
		</c:otherwise>
	</c:choose>
	<a href="<c:url value='${url}'/>" class="time" style="background: linear-gradient(to bottom, #${timeRetros.cor}, #${timeRetros.corGradiente})">
		<h3 class="time-nome">${timeRetros.nomeTime}</h3>
		<span class="time-quantidade">
			<span class="--numero">${timeRetros.quantidadeDeRetros}</span> <fmt:message key="home.retrospective"/>
		</span>
		<span class="time-integrantes">
			<span class="--numero">${timeRetros.quantidadeIntegrantes}</span> <fmt:message key="home.team.members"/>
		</span>
	</a>
</c:forEach>