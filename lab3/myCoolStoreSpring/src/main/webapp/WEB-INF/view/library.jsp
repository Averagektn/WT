<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/library.css">
    <title><fmt:message key="library.text.title"/></title>
</head>
<body>

<div class="header">
    <a id="to-main-page" class="header-link" href="${pageContext.request.contextPath}"><fmt:message
            key="to_main_page"/></a><br>
</div>

<div class="content">
    <c:forEach var="film" items="${films}">
        <a href="${pageContext.request.contextPath}/Film?filmId=${film.id}">${film.name}</a><br>

        <fmt:message key="age_restriction"/>: ${film.ageRestriction.toString()}<br>
        <fmt:message key="author"/>: ${film.author}<br>

    </c:forEach>
</div>

</body>
</html>
