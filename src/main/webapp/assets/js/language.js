window.onload = function() {
	const seletorLinguagem = document.querySelector(".linguagem")
	seletorLinguagem.addEventListener("change", function(event) {
		var url = window.location.href;
		if (url.indexOf('lang') != -1){
			const linguagemAntiga = url.substring(url.indexOf("lang"), url.indexOf("lang") + 7);
			url = url.replace(linguagemAntiga, 'lang=' + event.target.value);
		}
		else if (url.indexOf('?') > -1){
			url += '&lang=' + event.target.value;
		}else{
			url += '?lang=' + event.target.value;
		}
		window.location.href = url;
	});
	
	seletorLinguagem.value = document.cookie.substring(document.cookie.indexOf("lang") + 5, document.cookie.indexOf("lang") + 7);
}