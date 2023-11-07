<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<h1>
    Go to
    <a href="main">main.jsp</a>
</h1>
<form action="Register" method="post">
    <input type="submit" value="register"/>
</form>
<form action="Authorization" method="post">
    <input type="submit" value="authorization"/>
</form>
<ul>
    <jsp:useBean id="filmNames" scope="request" type="java.util.List"/>
    <c:forEach var="filmName" items="${filmNames}">
        <li>${filmName}</li>
    </c:forEach>
</ul>
</body>
</html>
