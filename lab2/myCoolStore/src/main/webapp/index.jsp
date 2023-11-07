<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>


<c:if test="${not empty sessionScope.isAdmin}">
    <h1>ADMIN MODE</h1>
</c:if>

<c:choose>
    <c:when test="${empty sessionScope.userID}">
        <form action="Register" method="post">
            <input type="submit" name="command" value="register"/>
        </form>
        <form action="Authorization" method="post">
            <input type="submit" name="command" value="authorization"/>
        </form>
    </c:when>
    <c:otherwise>
        <form action="Controller" method="post">
            <input type="submit" name="command" value="sign_out"/>
        </form>
    </c:otherwise>
</c:choose>

<ul>
    <jsp:useBean id="films" scope="request" type="java.util.List"/>
    <c:forEach var="film" items="${films}">
        <li>
            <a href="Film?id=${film.id}">${film.name}</a><br>
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
        </li>
    </c:forEach>
</ul>
</body>
</html>
