<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Library</title>
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
