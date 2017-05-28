<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sf:form method="POST" modelAttribute="customer">
		<h2>회원수정</h2>
		<fieldset>
			<div>
				<p>id :</p>
				<sf:input path="id" readonly="true"/>
			</div>
			<div>
				<p>name : </p>
				<sf:input path="name" />
			</div>
			<div>
				<p>address</p>
				<sf:input path="address" />
			</div>
			<div>
				<p>email</p>
				<sf:input path="email" />
			</div>
		</fieldset>
		<div>
			<input type="submit" value="확인" />
		</div>
	</sf:form>
</body>
</html>