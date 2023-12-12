<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/customerFilm.css">
    <title>My Cool Site</title>
</head>
<body>
<div class="header">
    <a id="to-main-page" class="header-link" href="${pageContext.request.contextPath}"><fmt:message
            key="to_main_page"/></a><br>
</div>

<c:if test="${not isBanned && empty sessionScope.isAdmin}">
    <div class="user-links">
        <a class="user-btn" href="${pageContext.request.contextPath}/User/Library"><fmt:message key="library"/></a><br>
        <a class="user-btn" href="${pageContext.request.contextPath}/User/Cart"><fmt:message key="cart"/></a><br>
    </div>
</c:if>

<div class="content">
    <h1 class="film-header">${film.flmName}</h1><br>

    <c:if test="${not isPaid}">
        <c:choose>
            <c:when test="${film.flmDiscount != 0}">
                <strike>${film.flmPrice}</strike> ${film.realPrice}<br>
            </c:when>
            <c:otherwise>
                ${film.flmPrice}<br>
            </c:otherwise>
        </c:choose>

        <c:if test="${not isFilmInCart && not isBanned && empty sessionScope.isAdmin}">
            <form action="${pageContext.request.contextPath}/User/Cart/Add" method="post">
                <input type="hidden" name="filmID" value="${film.flmId}">
                <input type="submit" value="<fmt:message key="add_to_cart"/>">
            </form>
        </c:if>
    </c:if>

    <fmt:message key="age_restriction"/>: ${film.flmAge}<br>
    <fmt:message key="author"/>: ${film.flmAuthor}<br>
    <fmt:message key="categories"/>:
    <c:forEach var="category" items="${film.categories}">
        ${category.catName}
    </c:forEach><br>

    <video width="640" height="400" controls>
        <source src="${pageContext.request.contextPath}/Video/Trailer?trailerPath=${media.fmTrailerPath}"
                type="video/mp4">
    </video>
    <br>

    <p>${film.flmDescription}</p>

    <c:if test="${(isPaid && not isBanned) || not empty sessionScope.isAdmin}">
        <video width="640" height="480" controls>
            <source src="${pageContext.request.contextPath}/Video/Film?filmPath=${media.fmFilmPath}"
                    type="video/mp4">
        </video>
        <br>
    </c:if>

    <c:forEach var="feedback" items="${feedbacks}">
        ${feedback.fbkAuthor.usrEmail}<br>
        <c:if test="${not empty sessionScope.isAdmin}">
            <form action="${pageContext.request.contextPath}/Admin/Ban" method="post">
                <input type="hidden" name="authorId" value="${feedback.fbkAuthor.usrId}">
                <input type="hidden" name="filmId" value="${film.flmId}">
                <input type="hidden" name="command" value="ban"/>

                <input type="submit" value="<fmt:message key="ban"/>"/>
            </form>
        </c:if>
        ${feedback.fbkRating}<br>
        <p>${feedback.fbkText}</p>
    </c:forEach>

    <c:if test="${not isBanned && empty sessionScope.isAdmin && isPaid}">
        <form:form action="${pageContext.request.contextPath}/User/Feedback" method="post" modelAttribute="feedback">
            <label for="filmFeedback"><fmt:message key="feedback"/>:</label>
            <form:textarea id="filmFeedback" name="filmFeedback" maxlength="5000" path="fbkText"/>

            <label for="rating"><fmt:message key="mark"/>:</label>
            <form:input type="number" min="0" max="10" id="rating" name="rating" path="fbkRating"/><br>

            <form:input type="hidden" name="filmID" value="${film.flmId}" path="fbkFilm"/>

            <input type="submit" value="<fmt:message key="leave_feedback"/>">
        </form:form>
    </c:if>
</div>
</body>
</html>
