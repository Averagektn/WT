<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Film</title>
</head>
<body>
    <jsp:useBean id="film" scope="request" type="by.bsuir.mycoolsite.bean.Film"/>
    <a href="Controller?id=${film.id}">${film.name}</a><br>
    <c:choose>
        <c:when test="${film.discount != 0}">
            <strike>${film.price}</strike> ${film.getRealPrice()}<br>
        </c:when>
        <c:otherwise>
            ${film.price}<br>
        </c:otherwise>
    </c:choose>

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

    <c:if test="${not empty film.media.filmPath}">
<%--        <video width="320" height="240" controls>
            <source src="${film.media.filmPath}" type="video/mp4">
            Your browser does not support the video tag.
        </video><br>--%>
    </c:if>

    <jsp:useBean id="feedbacks" scope="request" type="java.util.List"/>
    <c:forEach var="feedback" items="${feedbacks}">
        ${feedback.author.email}<br>
        ${feedback.rating}<br>
        <p>${feedback.text}</p>
    </c:forEach>
</body>
</html>
