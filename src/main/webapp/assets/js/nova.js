

function mostraLousa() {
	console.log("teste");	
	var lousa = $("#idLousa").find(":selected");
	console.log(lousa);
	var url = lousa.data().url;
	
	var pai = $('#formLousa');
    var img = document.querySelector("#imagem");  
    img.src = "/suricato" + url;  
	pai.append(img);
	console.log(img);
}

mostraLousa();