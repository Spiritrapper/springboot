<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="utf-8">

<title>HTML5</title>

</head>
<style>
.layout {
width : 500px;
margin : 0 auto;
margin-top : 40px;
}
.layout input, textarea{
width : 100%;
margin-top : 10px;
}

.layout textarea {
width: 100%;
margin-top : 10px;
min-height : 300px;

}
</style>
<body>
	<div class="layout">
	<form action="/board/writepro" method="post">
		<input name=title type="text">
		<textarea name="content"></textarea>
		<button type="submit">작성</button>
	</form>
	</div>
<p>HTML5<p>

</body>

</html>