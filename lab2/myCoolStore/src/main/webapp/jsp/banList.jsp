<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="users" scope="request" type="java.util.List"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<a href="${pageContext.request.contextPath}">На главную страницу</a><br>
<form action="${pageContext.request.contextPath}/Controller" method="post">
    <input type="submit"  value="Выйти"/>
    <input type="hidden" name="command" value="sign_out"/>
</form>
<c:forEach var="user" items="${users}">
    <form action="${pageContext.request.contextPath}/Controller" method="post">
        <p>${user.email}</p>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="command" value="unban"/>

        <input type="submit" value="Разбанить"/>
    </form>
</c:forEach>
</body>
</html>
