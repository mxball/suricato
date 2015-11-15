function mostraLousa() {
	var lousa = $("#idLousa").find(":selected");
	var url = lousa.data().url;
	
	var pai = $('#formLousa');
    var img = document.querySelector("#imagem");  
    img.src = "/suricato" + url;  
	img.after(lousa);
}

mostraLousa();