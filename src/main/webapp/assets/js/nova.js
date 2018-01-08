const seletorLousa = document.querySelector("#idLousa");

document.querySelectorAll(".quadro-lousa_seletor").forEach((lousa) => {
	lousa.onchange = () => {
		seletorLousa.value = lousa.dataset.lousaId;
	}
	
});

seletorLousa.onchange = () => {
	document.querySelector("#quadro_" + seletorLousa.value).checked = true;
}