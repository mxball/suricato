$('.time').click(function(){
	$('.retroPessoal').addClass("hideme");
	$('.retroTimes').removeClass("hideme");
});

$('.pessoais').click(function(){
	$('.retroTimes').addClass("hideme");
	$('.retroPessoal').removeClass("hideme");
});

$('.abertas').click(function(){
	console.log("clicando");
	$('.fechado').addClass("hideme");
	$('.aberto').removeClass("hideme");
});

$('.fechadas').click(function(){
	$('.aberto').addClass("hideme");
	$('.fechado').removeClass("hideme");
});