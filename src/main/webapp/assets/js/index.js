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
	var elemento = event.target.id; 
	$("." + elemento).removeClass("hideme");
})