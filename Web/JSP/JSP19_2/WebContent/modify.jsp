<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
	<%!
		Connection con;
		Statement stmt;
		ResultSet resultSet;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "scott";
		String upw = "tiger";
		String query = "select*from member";
		
		String name, id, pw, phone1, phone2, phone3, gender;
	%>
	<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function form_check() 
	{
		submit_ajax();
	}
	function submit_ajax() 
	{
		var queryString = $("#ModifyProcess").serialize();
		
		$.ajax({
			url : '/JSP19_2/ModifyProcess',
 			type : 'POST',
			data : queryString,
			dataType: 'json',
			success : function(json) {	
				var results = eval(json);
				if(results[0].result=="ok")
				{
					alert("수정되었습니다.")
					window.location.replace("modifyResult.jsp");
				}else
				{
					alert(results[0].desc);
				}
			}
		});
	}
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		id = (String)session.getAttribute("id");
	
		String query = "select * from member where id='"+id+"'";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url, uid, upw);
		stmt = con.createStatement();
		resultSet = stmt.executeQuery(query);
		
		String phone="";
		while(resultSet.next()){
			name = resultSet.getString("name");
			pw = resultSet.getString("pw");
			phone = resultSet.getString("phone");
			gender = resultSet.getString("gender");
		}
		phone1 = phone.substring(0, 3);
		phone2 = phone.substring(3, 7);
		phone3 = phone.substring(7, 11);
	%>
	<form name="ModifyProcess" id="ModifyProcess">
		아이디 :<%=id %><br>
		비밀번호 :<input type="text" name="pw" size="10"><br>
		이름 :<input type="text" name="name" size="10" value=<%=name %>><br>
		전화번호 :<select name="phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="019">019</option>
		</select>-
		<input type="text" name="phone2" size="5" value=<%=phone2 %>>-
		<input type="text" name="phone3" size="5" value=<%=phone3 %>>
		
		<%
			if(gender.equals("man")){
		%>
		성별 구분:<input type="radio" name="gender" value="man" checked="checked">남 &nbsp;
				<input type="radio" name="gender" value="woman">여<br>
		<%
			}else{
		%>
		성별 구분:<input type="radio" name="gender" value="man">남 &nbsp;
				<input type="radio" name="gender" value="woman" checked="checked">여<br>
		<%
			}
		%>
		<input type="button" value="정보수정" onclick="form_check()">
	</form>
</body>
</html>