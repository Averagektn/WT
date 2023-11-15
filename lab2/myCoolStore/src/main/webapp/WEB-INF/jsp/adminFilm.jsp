<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:useBean id="categories" scope="request" type="java.util.List"/>
<jsp:useBean id="ageRestrictions" scope="request" type="java.util.List"/>
<jsp:useBean id="film" scope="request" type="by.bsuir.mycoolsite.bean.Film"/>
<jsp:useBean id="command" scope="request" type="java.lang.String"/>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>

<h1><fmt:message key="admin_mode"/></h1>

<a href="${pageContext.request.contextPath}"><fmt:message key="to_main_page"/></a><br>

<form action="${pageContext.request.contextPath}/Controller" method="post">
    <input type="submit" value="<fmt:message key="exit"/>"/>
    <input type="hidden" name="command" value="sign_out"/>
</form>

<form action="${pageContext.request.contextPath}/Controller" method="post" enctype="multipart/form-data">
    <label for="filmTitle"><fmt:message key="film_name"/>:</label>
    <input type="text" id="filmTitle" name="filmTitle" value="${film.name}" required> <br>

    <label for="filmAuthor"><fmt:message key="film_author"/>:</label>
    <input type="text" id="filmAuthor" name="filmAuthor" value="${film.author}" required><br>

    <label for="filmCategory"><fmt:message key="categories"/>:</label>
    <select id="filmCategory" name="filmCategory" multiple required>
        <c:forEach var="category" items="${film.categories}">
            <option value="${category.id}" selected>${category.name}</option>
        </c:forEach>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select><br>

    <label for="filmAgeRestriction"><fmt:message key="age_restriction"/>:</label>
    <select id="filmAgeRestriction" name="filmAgeRestriction" required>
        <option value="${film.ageRestriction}" selected>${film.ageRestriction.toString()}</option>
        <c:forEach var="ageRestriction" items="${ageRestrictions}">
            <option value="${ageRestriction}">${ageRestriction}</option>
        </c:forEach>
    </select><br>

    <c:choose>
        <c:when test="${film.id == 0}">
            <label for="filmFile"><fmt:message key="film_file"/>:</label>
            <input type="file" id="filmFile" name="filmFile" required><br>

            <label for="trailerFile"><fmt:message key="trailer_file"/>:</label>
            <input type="file" id="trailerFile" name="trailerFile" required><br>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="filmId" value="${film.id}">
        </c:otherwise>
    </c:choose>

    <label for="filmDescription"><fmt:message key="film_description"/>:</label>
    <textarea id="filmDescription" name="filmDescription" required></textarea><br>

    <label for="filmPrice"><fmt:message key="film_price"/>:</label>
    <input type="number" step="0.01" id="filmPrice" name="filmPrice" min="0" value="${film.price}" required><br>

    <label for="filmDiscount"><fmt:message key="film_discount"/>:</label>
    <input type="number" id="filmDiscount" name="filmDiscount" min="0" max="100" value="${film.discount}" required><br>

    <input type="hidden" name="command" value="${command}">

    <input type="submit" value="<fmt:message key="add_film"/>">
</form>
</body>
</html>

