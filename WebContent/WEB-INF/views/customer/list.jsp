<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="list" items="${customers}">
		${list.id}
		${list.name}
		${list.address}
		${list.email}
		&nbsp;&nbsp;
		<a href="update?id=${list.id}">수정</a>
		&nbsp;
		<a href="delete?name=${list.name}">삭제</a>
		<br>
	</c:forEach>
</body>
</html>