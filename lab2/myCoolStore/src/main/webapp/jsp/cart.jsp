<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>
<jsp:useBean id="total" scope="request" type="java.math.BigDecimal"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
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

<c:if test="${total != 0}">
    <form action="Controller" method="post">
        <input type="submit" value="Оплатить ${total}">
        <input type="hidden" name="command" value="buy"/>
    </form>
</c:if>
<a href="/myCoolStore/">На главную страницу</a>

</body>
</html>
