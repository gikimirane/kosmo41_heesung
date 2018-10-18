<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="700" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bId }</td>
			<td>${dto.bName }</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent }">└[답글]</c:forEach>
				<a href="content_view.do?bId=${dto.bId }">${dto.bTitle }</a>
			</td>
			<td>${dto.bDate }</td>
			<td>${dto.bHit }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><a href="write_view.do">글작성</a></td>
		</tr>
		<tr>
			<!-- ?가 파라미터 page=파라미터 이름  =뒤에 넣을값.-->
			<td colspan="5" align="center">
			<!-- 처음 -->
			<c:choose>
			<c:when test="${(page.curPage -1)<1 }">
				[&lt;&lt;]&nbsp;
			</c:when>
			<c:otherwise>
				<a href="list.do?page=1">[&lt;&lt;]</a>&nbsp;
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(page.curPage -1)<1 }">
				[&lt;]&nbsp;
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.curPage-1 }">[&lt;]</a>&nbsp;
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 -->
			<c:forEach begin="${page.startPage }" end="${page.endPage }" step="1" var="page2">
			<c:choose>
			<c:when test="${page.curPage == page2}">
				 [${page2 }]&nbsp;
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page2}">[${page2 }]</a> &nbsp;
			</c:otherwise>
			</c:choose>
				
			</c:forEach>
			
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(page.curPage + 1)> page.totalPage }">
				[&gt;]&nbsp;
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.curPage+1 }">[&gt;]</a>&nbsp;
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage }">
				[&gt;&gt;]&nbsp;
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.totalPage}">[&gt;&gt;]</a>&nbsp;
			</c:otherwise>
			</c:choose>
			
			</td>
		</tr>
	</table>
</body>
</html>