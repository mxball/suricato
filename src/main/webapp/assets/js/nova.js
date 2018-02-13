const seletorLousa = document.querySelector("#idLousa");

for(var lousa of document.querySelectorAll(".quadro-lousa_seletor")) {
	lousa.addEventListener("change", function(event) {
		seletorLousa.value = event.target.dataset.lousaId;
	})
}

seletorLousa.onchange = () => {
	document.querySelector("#quadro_" + seletorLousa.value).checked = true;
}