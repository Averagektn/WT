<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.userID}">
        <form action="Register" method="post">
            <input type="submit"  value="Регистрация"/>
            <input type="hidden" name="command" value="register"/>
        </form>
        <form action="Authorization" method="post">
            <input type="submit" value="Авторизация"/>
            <input type="hidden" name="command" value="authorization"/>
        </form>
    </c:when>
    <c:otherwise>
        <form action="Controller" method="post">
            <input type="submit"  value="Выйти"/>
            <input type="hidden" name="command" value="sign_out"/>
        </form>
        <c:choose>
            <c:when test="${not empty sessionScope.isAdmin}">
                <h1>ADMIN MODE</h1>
                <form action="Controller" method="post">
                    <input type="submit"  value="Добавить фильм"/>
                    <input type="hidden" name="command" value="add_film"/>
                </form>
            </c:when>
            <c:otherwise>
                <form action="Controller" method="post">
                    <input type="submit"  value="Библиотека фильмов"/>
                    <input type="hidden" name="command" value="library"/>
                </form>
                <form action="Controller" method="post">
                    <input type="submit"  value="Корзина"/>
                    <input type="hidden" name="command" value="cart"/>
                </form>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<ul>
    <c:forEach var="film" items="${films}">
        <li>
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
            Категории:
            <c:forEach var="category" items="${film.categories}">
                ${category.name}
            </c:forEach><br>
        </li>
        <c:if test="${not empty sessionScope.isAdmin}">
            <form action="Controller" method="post">
                <input type="submit"  value="Редактировать фильм"/>
                <input type="hidden" name="command" value="edit_film"/>
            </form>
        </c:if>
    </c:forEach>
</ul>

</body>
</html>
