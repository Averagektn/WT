<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="film" scope="request" type="by.bsuir.mycoolsite.bean.Film"/>
<jsp:useBean id="feedbacks" scope="request" type="java.util.List"/>
<jsp:useBean id="isPaid" scope="request" type="java.lang.Boolean"/>
<jsp:useBean id="isBanned" scope="request" type="java.lang.Boolean"/>

<html>
<head>
    <title>${film.name}</title>
</head>
<body>
<h1>${film.name}</h1><br>
<c:if test="${not isPaid}">
    <c:when test="${film.discount != 0}">
        <strike>${film.price}</strike> ${film.getRealPrice()}<br>
    </c:when>
    <c:otherwise>
        ${film.price}<br>
    </c:otherwise>
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
${film.description}

<c:if test="${isPaid && not isBanned}">
    <%--        <video width="320" height="240" controls>
                <source src="${film.media.filmPath}" type="video/mp4">
                Your browser does not support the video tag.
            </video><br>--%>
</c:if>

<c:forEach var="feedback" items="${feedbacks}">
    ${feedback.author.email}<br>
    ${feedback.rating}<br>
    <p>${feedback.text}</p>
</c:forEach>

<c:if test="${not isBanned}">
    <form action="Controller" method="post">
        <label for="filmFeedback">Отзыв:</label>
        <textarea id="filmFeedback" name="filmFeedback" rows="4"></textarea>
        <input type="hidden" name="command" value="add_feedback"/>
    </form>
</c:if>

</body>
</html>
