<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
</head>
<body>

<form class="language-form" action="">
    <label for="language"></label>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.text.english"/></option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="language.text.russian"/></option>
    </select>
</form>
<c:choose>
    <c:when test="${empty sessionScope.userID}">
        <form action="${pageContext.request.contextPath}/Register" method="get" class="reg-form">
            <input type="submit" value="<fmt:message key="registration"/>"/>
        </form>
        <form action="${pageContext.request.contextPath}/Authorization" method="get" class="auth-form">
            <input type="submit" value="<fmt:message key="authorisation"/>"/>
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/Exit" method="post" class="auth-form">
            <input type="submit" value="<fmt:message key="exit"/>"/>
            <input type="hidden" name="command" value="sign_out"/>
        </form>
        <c:choose>
            <c:when test="${not empty sessionScope.isAdmin}">
                <div class="admin-links">
                    <h1><fmt:message key="admin_mode"/></h1>
                    <a class="admin-btn" href="${pageContext.request.contextPath}/Admin/AddFilm"><fmt:message
                            key="add_film"/></a><br>
                    <a class="admin-btn" href="${pageContext.request.contextPath}/Admin/BanList"><fmt:message
                            key="to_ban_list"/></a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="user-links">
                    <a class="user-btn" href="${pageContext.request.contextPath}/Library"><fmt:message
                            key="library"/></a><br>
                    <a class="user-btn" href="${pageContext.request.contextPath}/Cart"><fmt:message key="cart"/></a><br>
                </div>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<ul>
    <c:forEach var="film" items="${films}">
        <li>
            <a href="${pageContext.request.contextPath}/Film?filmId=${film.flmId}">${film.flmName}</a><br>
            <c:choose>
                <c:when test="${film.flmDiscount != 0}">
                    <span style="text-decoration: line-through;">${film.flmPrice}</span>
                    ${film.realPrice}<br>
                </c:when>
                <c:otherwise>
                    ${film.flmPrice}<br>
                </c:otherwise>
            </c:choose>

            <fmt:message key="age_restriction"/>: ${film.flmAge}<br>
            <fmt:message key="author"/>: ${film.flmAuthor}<br>
            <fmt:message key="categories"/>:
            <c:forEach var="category" items="${film.categories}">
                ${category.catName}
            </c:forEach><br>
        </li>
        <c:if test="${not empty sessionScope.isAdmin}">
            <a href="${pageContext.request.contextPath}/Admin/EditFilm?filmId=${film.flmId}"><fmt:message
                    key="edit_film"/></a><br>
        </c:if>
    </c:forEach>
</ul>

</body>
</html>
