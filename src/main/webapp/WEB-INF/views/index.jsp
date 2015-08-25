<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.postIt {
	width: 50px;
	height: 50px;
	background-color: yellow;	
}
</style>
<title>Home page</title>
</head>
<body>
	<script type="text/javascript">
		function printPostIt(e) {
			var cursorX = e.clientX;
			var cursorY = e.clientY;
			var postIt = document.createElement('div');
			postIt.classList.add("postIt");
			postIt.style.position = "absolute";
			console.log(cursorX + " " + cursorX)
			postIt.style.left = cursorX + "px";
			postIt.style.top = cursorY + "px";
			document.body.appendChild(postIt);
		}
		document.addEventListener("click", printPostIt);
	</script>
</body>
</html>