document.querySelector("#lousa").addEventListener("click", getAcao);

function getAcao(coordenadas) {
	if(document.querySelector("#adiciona-postit").checked) {
		getPostIt(coordenadas);
	} else {
		getTexto(coordenadas);
	}
}

function getTexto(coordenadas) {
	console.log("Teste " + coordenadas);
	document.createElement("textarea");
	
}