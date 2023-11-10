<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="film" scope="request" type="by.bsuir.mycoolsite.bean.Film"/>
<jsp:useBean id="feedbacks" scope="request" type="java.util.List"/>
<jsp:useBean id="isPaid" scope="request" type="java.lang.Boolean"/>
<jsp:useBean id="isBanned" scope="request" type="java.lang.Boolean"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<h1>${film.name}</h1><br>

<a href="/myCoolStore/">На главную страницу</a><br>

<c:if test="${not isPaid}">
    <c:choose>
        <c:when test="${film.discount != 0}">
            <strike>${film.price}</strike> ${film.getRealPrice()}<br>
        </c:when>
        <c:otherwise>
            ${film.price}<br>
        </c:otherwise>
    </c:choose>
    <c:if test="${not isBanned && empty sessionScope.isAdmin}">
        <form action="Controller" method="post">
            <input type="hidden" name="filmID" value="${film.id}">
            <input type="hidden" name="command" value="add_to_cart"/>

            <input type="submit" value="Добавить в корзину">
        </form>
    </c:if>
</c:if>


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
<p>${film.description}</p>

<c:if test="${isPaid && not isBanned}">
    <%--        <video width="320" height="240" controls>
                <source src="${film.media.filmPath}" type="video/mp4">
                Your browser does not support the video tag.
            </video><br>--%>
</c:if>

<c:forEach var="feedback" items="${feedbacks}">
    ${feedback.author.email}<br>
    <c:if test="${not empty sessionScope.isAdmin}">
        <form action="Controller" method="post">
            <input type="submit"  value="Забанить"/>
            <input type="hidden" name="command" value="ban"/>
        </form>
    </c:if>
    ${feedback.rating}<br>
    <p>${feedback.text}</p>
</c:forEach>

<c:if test="${not isBanned && empty sessionScope.isAdmin}">
    <form action="Controller" method="post">
        <label for="filmFeedback">Отзыв:</label>
        <textarea id="filmFeedback" name="filmFeedback" maxlength="5000"></textarea>

        <label for="rating">Оценка:</label>
        <input type="number" min="0" max="10" id="rating" name="rating"><br>

        <input type="hidden" name="filmID" value="${film.id}">
        <input type="hidden" name="command" value="add_feedback"/>

        <input type="submit" value="Оставить отзыв">
    </form>
</c:if>

</body>
</html>
