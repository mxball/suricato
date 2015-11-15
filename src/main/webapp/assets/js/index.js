$('.abertas').click(function(){
	console.log("clicando");
	$('.fechado').addClass("hideme");
	$('.aberto').removeClass("hideme");
});

$('.fechadas').click(function(){
	$('.aberto').addClass("hideme");
	$('.fechado').removeClass("hideme");
});

$('.retros').click(function(event){
	console.log(event.target)
	$('.retrospectiva').addClass("hideme");
	var elemento = event.target.dataset.id; 
	console.log(elemento);
	$("." + elemento).removeClass("hideme");
})