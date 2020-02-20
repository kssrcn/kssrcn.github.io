<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
.userBox {
	width: 600px;
	margin: 0 auto;
	border: 4px solid black;
}

.board {
	width: 600px;
	margin: 0 auto;
	border: 4px solid black;
}

.boardBox {
	padding: 20px 20px;
}

.board ul {
	padding: 0;
	margin: 0;
	list-style: none;
}

.board li a {
	display: block;
	color : black;
	border-bottom: 1px dotted #eeeeee;
	text-decoration: none;
}

.board li a:hover {
	background-color: #eeeeee;
}
}
</style>
</head>
<body>
	<header class="userBox">
		<div class="userStatus">${sessionScope.id} �� �ȳ��ϼ���!</div>
	</header>
	<nav class="board">
		<div class="boardBox">
			<h1>��������</h1>
			<ul>
				<c:forEach var="list" varStatus="vs" items='${boardList}'>
					<li><a href="showBoard.do?wId=${list.wId}">��ȣ:${list.wId} ����:${list.wTitle}</a></li>
				</c:forEach>
			</ul>
			<input type="button" onclick="location.href='writeForm.html'"
				value="�������� ����ϱ�"> <input type="button" onclick="location.href='logOut.do'" value="�α׾ƿ�">
		</div>
	</nav>
</body>
</html>