<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/adminFilm.css">
    <title>My Cool Site</title>
</head>
<body>

<div class="header">
    <a id="to-main-page" class="header-link" href="${pageContext.request.contextPath}"><fmt:message
            key="to_main_page"/></a><br>
</div>

<div class="content">
    <form:form action="${pageContext.request.contextPath}/Admin/${command}" method="post" enctype="multipart/form-data"
               modelAttribute="film">
        <h1 class="admin-header"><fmt:message key="admin_mode"/></h1>

        <label for="filmTitle"><fmt:message key="film_name"/>:</label>
        <form:input type="text" value="${film.flmName}" id="filmTitle" name="filmTitle" required="true" path="flmName"/>
        <br>

        <label for="filmAuthor"><fmt:message key="film_author"/>:</label>
        <form:input type="text" id="filmAuthor" name="filmAuthor" value="${film.flmAuthor}" required="true"
                    path="flmAuthor"/><br>

        <label for="filmCategory"><fmt:message key="categories"/>:</label>
        <select id="filmCategory" name="filmCategory" multiple required>
            <c:forEach var="category" items="${film.categories}">
                <option value="${category.catId}" selected>${category.catName}</option>
            </c:forEach>
            <c:forEach var="category" items="${categories}">
                <option value="${category.catId}">${category.catName}</option>
            </c:forEach>
        </select><br>

        <label for="filmAgeRestriction"><fmt:message key="age_restriction"/>:</label>
        <form:select id="filmAgeRestriction" name="filmAgeRestriction" required="true" path="flmAge">
            <option value="${film.flmAge}" selected>${film.flmAge}</option>
            <c:forEach var="ageRestriction" items="${ageRestrictions}">
                <option value="${ageRestriction}">${ageRestriction}</option>
            </c:forEach>
        </form:select><br>

        <c:choose>
            <c:when test="${film.flmId == 0}">
                <label for="filmFile"><fmt:message key="film_file"/>:</label>
                <input type="file" id="filmFile" name="filmFile" required><br>

                <label for="trailerFile"><fmt:message key="trailer_file"/>:</label>
                <input type="file" id="trailerFile" name="trailerFile" required><br>
            </c:when>
            <c:otherwise>
                <form:input type="hidden" name="filmId" value="${film.flmId}" path="flmId"/>
            </c:otherwise>
        </c:choose>

        <label for="filmDescription"><fmt:message key="film_description"/>:</label>
        <form:textarea id="filmDescription" name="filmDescription" required="true" path="flmDescription"/><br>

        <label for="filmPrice"><fmt:message key="film_price"/>:</label>
        <form:input type="number" step="0.01" id="filmPrice" name="filmPrice" min="0" value="${film.flmPrice}"
                    required="true" path="flmPrice"/><br>

        <label for="filmDiscount"><fmt:message key="film_discount"/>:</label>
        <form:input type="number" id="filmDiscount" name="filmDiscount" min="0" max="100" value="${film.flmDiscount}"
                    required="true" path="flmDiscount"/><br>

        <input type="submit" value="<fmt:message key="add_film"/>">
    </form:form>
</div>
</body>
</html>

