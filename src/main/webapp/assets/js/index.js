$('#tipo-aberta').click(function(){
	console.log("clicando");
	$('.lista-fechadas').addClass("hideme");
	$('.lista-abertas').removeClass("hideme");
});

$('#tipo-fechada').click(function(){
	$('.lista-abertas').addClass("hideme");
	$('.lista-fechadas').removeClass("hideme");
});
$('.retros').change(function(event){
	$('.retrospectiva').addClass("hideme");
	$('#novo-integrante').remove();
	var elemento = event.target; 
	$("." + elemento.id).removeClass("hideme");
	if(elemento.id != "pessoal") {
		var link = document.createElement("a");
		link.href = "/time/mostra?id=" + elemento.dataset.id;
		link.classList.add("link-novo");
		link.classList.add("hideme");
		link.id = "novo-integrante";
		link.textContent = "Adicionar integrante no time";
		$("#novo-time").after(link);
	}
})