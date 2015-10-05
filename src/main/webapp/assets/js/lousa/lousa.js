document.querySelector("#lousa").addEventListener("click", getAcao);

function getAcao(event) {
	$(".postIt").draggable();
}

function getTexto(coordenadas) {
	console.log("Teste " + coordenadas);
	var cursorX = (coordenadas.clientX * 100.0) / window.innerWidth;
	var cursorY = (coordenadas.clientY * 100.0) / window.innerHeight;
	var text = createTextarea("comments");

	text.style.left = cursorX + "%";
	text.style.top = cursorY + "%";
	text.classList.add("texto");

	document.querySelector("#lousa").appendChild(text);
}

function createTextarea(name) {
	var textarea = document.createElement("textarea");
	textarea.name = name;
	textarea.maxLength = 100;
	return textarea;
}
