<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>index</title>
</head>
<body>
	<h3>hello servlet!</h3>
	<form action="<%=request.getContextPath() %>/hello" method="POST">
		<input type="text" name="name">
		<input type="submit" value="提交" />
	</form>
	<h3>hi action!</h3>
	<form action="<%=request.getContextPath() %>/hi" method="POST">
		<input type="text" name="name">
		<input type="submit" value="提交" />
	</form>
</body>
</html>
