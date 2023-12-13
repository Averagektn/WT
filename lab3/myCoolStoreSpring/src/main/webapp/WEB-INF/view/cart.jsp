<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:useBean id="films" scope="request" type="java.util.List"/>
<jsp:useBean id="total" scope="request" type="java.math.BigDecimal"/>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/cart.css">
    <title>My Cool Site</title>
</head>
<body>

<div class="header">
    <a id="to-main-page" class="header-link" href="${pageContext.request.contextPath}"><fmt:message
            key="to_main_page"/></a><br>
</div>

<div class="content">
    <h1 class="cart-header"><fmt:message key="cart_header"/></h1>

    <c:forEach var="film" items="${films}">
        <a href="${pageContext.request.contextPath}/Film?filmId=${film.flmId}">${film.flmName}</a><br>
        <c:choose>
            <c:when test="${film.flmDiscount != 0}">
                <strike>${film.flmPrice}</strike> ${film.realPrice}<br>
            </c:when>
            <c:otherwise>
                ${film.flmPrice}<br>
            </c:otherwise>
        </c:choose>

        <fmt:message key="age_restriction"/>: ${film.flmAge}<br>
        <fmt:message key="author"/>: ${film.flmAuthor}<br>

        <form action="${pageContext.request.contextPath}/User/Cart/Remove" method="post" class="cart-form">
            <input type="submit" value="<fmt:message key="delete"/>">
            <input type="hidden" name="filmID" value="${film.flmId}">
        </form>
    </c:forEach>

    <c:if test="${total != 0}">
        <form action="${pageContext.request.contextPath}/User/Cart/Buy" method="post" class="cart-form">
            <input type="submit" value="<fmt:message key="pay"/> ${total}">
        </form>
    </c:if>
</div>

</body>
</html>
