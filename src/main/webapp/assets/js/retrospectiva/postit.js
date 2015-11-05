$(".like").click(adicionaDot);

function createDotVote() {
	var dotVote = document.createElement("div");
	dotVote.classList.add("dotVote");
	return dotVote;
}

function adicionaDot() {
	this.parentNode.querySelector(".dotVotes").appendChild(createDotVote());
}

$(".dislike").on("click", function() {
	$(this).parent().find(".dotVotes .dotVote").first().remove();
});