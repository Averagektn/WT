<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h1>Cart</h1>
<jsp:useBean id="films" scope="request" type="java.util.List"/>
<c:forEach var="film" items="${films}">
    <li>
        <p>${film.name}</p><br>
        <c:choose>
            <c:when test="${film.discount != 0}">
                <strike>${film.price}</strike> ${film.getRealPrice()}<br>
            </c:when>
            <c:otherwise>
                ${film.price}<br>
            </c:otherwise>
        </c:choose>

        Возрастные ограничения: ${film.ageRestriction.toString()}<br>
        Автор: ${film.author}<br>
        Категории:
        <c:forEach var="category" items="${film.categories}">
            ${category.name}
        </c:forEach><br>
</c:forEach>
</body>
</html>
