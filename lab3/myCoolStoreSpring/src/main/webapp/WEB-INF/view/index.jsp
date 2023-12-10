<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.userID}">
        <form action="Register" method="post" class="reg-form">
            <input type="submit" value="registration"/>
            <input type="hidden" name="command" value="register"/>
        </form>
        <form action="Authorization" method="post" class="auth-form">
            <input type="submit" value="authorisation"/>
            <input type="hidden" name="command" value="authorization"/>
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/Controller" method="post" class="auth-form">
            <input type="submit" value="exit"/>
            <input type="hidden" name="command" value="sign_out"/>
        </form>
        <c:choose>
            <c:when test="${not empty sessionScope.isAdmin}">
                <div class="admin-links">
                    <h1>Admin mode</h1>
                    <a class="admin-btn" href="Admin/Film">Add film</a><br>
                    <a class="admin-btn" href="Admin/BanList"><fmt:message key="to_ban_list"/></a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="user-links">
                    <a class="user-btn" href="Library">Library</a><br>
                    <a class="user-btn" href="Cart">Cart</a><br>
                </div>
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

           Age restriction: ${film.ageRestriction.toString()}<br>
            Author: ${film.author}<br>
            Categories:
            <c:forEach var="category" items="${film.categories}">
                ${category.name}
            </c:forEach><br>
        </li>
        <c:if test="${not empty sessionScope.isAdmin}">
            <a href="Admin/Film?filmId=${film.id}"><fmt:message key="edit_film"/></a><br>
        </c:if>
    </c:forEach>
</ul>

</body>
</html>
