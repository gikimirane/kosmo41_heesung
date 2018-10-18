<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FileUpload/uploadForm</title>
</head>
<body>

   <h2>파일 업로드 폼</h2>
   
	<form name="fileFrm" method="post" action="uploadAction"
	   enctype= "multipart/form-data">
	제목 : <input type="text" name="title" /><br>
	첨부파일1 : <input type="file" name="userfile1" /><br>
	첨부파일2 : <input type="file" name="userfile2" /><br>   
	<button type="submit" class="btn btn-danger">파일업로드</button>
   
</form>

</body>
</html>