<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
<form action="/hello" method="POST">
名：<input type="text" name="name">
<br />
<input type="submit" value="提交" />
</form>
<br />

<form action="/upload" method="POST" enctype="multipart/form-data">
文件：<input type="file" name="file">
<br />
<input type="submit" value="上传" />
</form>

</body>
</html>
