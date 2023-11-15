<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Библиотека фильмов</title>
</head>
<body>
<a href="${pageContext.request.contextPath}">На главную страницу</a><br>
<form action="${pageContext.request.contextPath}/Controller" method="post">
    <input type="submit" value="Выйти"/>
    <input type="hidden" name="command" value="sign_out"/>
</form>
<c:forEach var="film" items="${films}">
    <a href="${pageContext.request.contextPath}/Film?filmId=${film.id}">${film.name}</a><br>

    Возрастные ограничения: ${film.ageRestriction.toString()}<br>
    Автор: ${film.author}<br>

</c:forEach>

</body>
</html>
