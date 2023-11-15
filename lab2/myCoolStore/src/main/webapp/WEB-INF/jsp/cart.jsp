<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>
<jsp:useBean id="total" scope="request" type="java.math.BigDecimal"/>

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
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.text.english" /></option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="language.text.russian" /></option>
    </select>
</form>

<h1><fmt:message key="cart_header"/></h1>

<a href="${pageContext.request.contextPath}"><fmt:message key="to_main_page"/></a><br>
<form action="${pageContext.request.contextPath}/Controller" method="post">
    <input type="submit" value="<fmt:message key="exit"/>"/>
    <input type="hidden" name="command" value="sign_out"/>
</form>
<c:forEach var="film" items="${films}">
    <a href="${pageContext.request.contextPath}/Film?filmId=${film.id}">${film.name}</a><br>
    <c:choose>
        <c:when test="${film.discount != 0}">
            <strike>${film.price}</strike> ${film.getRealPrice()}<br>
        </c:when>
        <c:otherwise>
            ${film.price}<br>
        </c:otherwise>
    </c:choose>

    <fmt:message key="age_restriction"/>: ${film.ageRestriction.toString()}<br>
    <fmt:message key="author"/>: ${film.author}<br>

    <form action="${pageContext.request.contextPath}/Controller" method="post">
        <input type="submit" value="<fmt:message key="delete"/>">
        <input type="hidden" name="filmID" value="${film.id}">
        <input type="hidden" name="command" value="remove_from_cart"/>
    </form>
</c:forEach>

<c:if test="${total != 0}">
    <form action="${pageContext.request.contextPath}/Controller" method="post">
        <input type="submit" value="<fmt:message key="pay"/> ${total}">
        <input type="hidden" name="command" value="buy"/>
    </form>
</c:if>

</body>
</html>
