<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	table {
		border-spacing: 0px;
	}
	th,td {
		border : 1px solid black;
		text-align: center;
	}
	a {
		text-decoration: none;
		color:black;
	}
</style>
</head>
<body>
	<table>
	<tr><th>ID</th><th>PW</th><th>수정</th><th>삭제</th></tr>
		<c:forEach var="list" items="${memberList}" varStatus="vs">
			<tr><td>${memberList[vs.index].id}</td><td>${memberList[vs.index].pw}</td>
			<td><a href="memberUpdate.jsp?id=${memberList[vs.index].id}">회원 수정 </a></td>
			<td><a href="delete.do?id=${memberList[vs.index].id}">회원 삭제</a></td> </tr>
		</c:forEach>
	</table>
</body>
</html>