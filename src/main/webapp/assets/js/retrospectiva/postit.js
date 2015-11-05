
function createDotVote() {
	var dotVote = document.createElement("div");
	dotVote.classList.add("dotVote");
	return dotVote;
}

function adicionaDot() {
	$(this).parent().find(".dotVotes").append(createDotVote());
}
$(".like").on("click", adicionaDot);


function removeDot() {
	$(this).parent().find(".dotVotes .dotVote").first().remove();
}
$(".dislike").on("click", removeDot);