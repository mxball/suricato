<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/lousa/postIt.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/lousa/lousa.css'/>">
		<title>Glad/Sad</title>
	</head>
	<body>
		<div id="lousa">
			<img src="<c:url value="/assets/images/gladSad.png"/>"/>
		</div>
		<script src="<c:url value='/assets/js/lousa/postIt.js'/>"></script>
	</body>
</html>