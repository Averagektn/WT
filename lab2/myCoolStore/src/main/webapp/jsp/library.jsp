<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<ul>
    <jsp:useBean id="films" scope="request" type="java.util.List"/>
    <c:forEach var="film" items="${films}">
        <li>
            <a href="Controller?id=${film.id}">${film.name}</a><br>

            Возрастные ограничения: ${film.ageRestriction.toString()}<br>
            Автор: ${film.author}<br>

            Категории:
            <c:forEach var="category" items="${film.categories}">
                ${category.name}
            </c:forEach><br>

            ${film.description}
        </li>
    </c:forEach>
</ul>
</body>
</html>
