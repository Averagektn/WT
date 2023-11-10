<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>
<jsp:useBean id="total" scope="request" type="java.math.BigDecimal"/>

<html>
<head>
    <title>Cart</title>
</head>
<body>
<h1>Cart</h1>

<c:forEach var="film" items="${films}">
    <a href="Film?filmId=${film.id}">${film.name}</a><br>
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

    <form action="Controller" method="post">
        <input type="submit" value="Удалить">
        <input type="hidden" name="filmID" value="${film.id}">
        <input type="hidden" name="command" value="remove_from_cart"/>
    </form>
</c:forEach>

<form action="Controller" method="post">
    <input type="submit" value="Оплатить ${total}">
    <input type="hidden" name="command" value="buy"/>
</form>

</body>
</html>
