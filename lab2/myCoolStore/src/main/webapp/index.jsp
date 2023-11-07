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
    <jsp:useBean id="films" scope="request" type="java.util.List" />
    <c:forEach var="film" items="${films}">
        <li>
            <a href="film?id=${film.id}">${film.name}</a><br>
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
<%--            <video width="320" height="240" controls>
                <source src="${film.media.trailerPath}" type="video/mp4">
                Your browser does not support the video tag.
            </video><br>--%>
            ${film.description}
        </li>
    </c:forEach>
</ul>
</body>
</html>
