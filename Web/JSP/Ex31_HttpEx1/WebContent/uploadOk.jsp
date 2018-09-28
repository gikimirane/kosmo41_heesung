<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="org.json.simple.JSONArray" %>
<%@page import="org.json.simple.JSONObject" %>

<%
	String path = request.getRealPath("fileFolder");

	int size = 1024*1024*10;
	String file = "";
	String oriFile = "";
	
	JSONObject obj = new JSONObject();
	
	try{
		MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();
		
		file = multi.getFilesystemName(str);
		oriFile = multi.getOriginalFileName(str);
		
		if(file == null){
			obj.put("success",new Integer(2));
			obj.put("desc","fail..");
		}else{
			obj.put("success",new Integer(1));
			obj.put("desc","성공");
		}
	}catch(Exception e){
		obj.put("success",new Integer(3));
		obj.put("desc",e.getMessage());
	}
	
	out.println(obj.toJSONString());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>