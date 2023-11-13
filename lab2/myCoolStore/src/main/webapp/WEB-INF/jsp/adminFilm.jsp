<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="categories" scope="request" type="java.util.List"/>
<jsp:useBean id="ageRestrictions" scope="request" type="java.util.List"/>
<jsp:useBean id="film" scope="request" type="by.bsuir.mycoolsite.bean.Film"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<h1>Admin film</h1>

<a href="${pageContext.request.contextPath}">На главную страницу</a><br>

<form action="${pageContext.request.contextPath}/Controller" method="post">
    <input type="submit"  value="Выйти"/>
    <input type="hidden" name="command" value="sign_out"/>
</form>

<form action="${pageContext.request.contextPath}/Controller" method="post" enctype="multipart/form-data">
    <label for="filmTitle">Название фильма:</label>
    <input type="text" id="filmTitle" name="filmTitle" value="${film.name}"> <br>

    <label for="filmAuthor">Автор фильма:</label>
    <input type="text" id="filmAuthor" name="filmAuthor" value="${film.author}"><br>

    <label for="filmCategory">Категория:</label>
    <select id="filmCategory" name="filmCategory" multiple>
        <c:forEach var="category" items="${film.categories}">
            <option value="${category}" selected>${category.name}</option>
        </c:forEach>
        <c:forEach var="category" items="${categories}">
            <option value="${category}">${category.name}</option>
        </c:forEach>
    </select><br>

    <label for="filmAgeRestriction">Возрастная категория:</label>
    <select id="filmAgeRestriction" name="filmAgeRestriction">
        <option value="${film.ageRestriction}" selected>${film.ageRestriction.toString()}</option>
        <c:forEach var="ageRestriction" items="${ageRestrictions}">
            <option value="${ageRestriction}">${ageRestriction.toString()}</option>
        </c:forEach>
    </select><br>

    <label for="filmFile">Файл фильма:</label>
    <input type="file" id="filmFile" name="filmFile"><br>

    <label for="trailerFile">Файл трейлера:</label>
    <input type="file" id="trailerFile" name="trailerFile"><br>

    <label for="filmDescription">Описание фильма:</label>
    <textarea id="filmDescription" name="filmDescription"></textarea><br>

    <label for="filmPrice">Цена:</label>
    <input type="number" step="0.01" id="filmPrice" name="filmPrice" min="0" value="${film.price}"><br>

    <label for="filmDiscount">Скидка:</label>
    <input type="number" id="filmDiscount" name="filmDiscount" min="0" max="100" value="${film.discount}"><br>

    <input type="hidden" name="command" value="add_film">

    <input type="submit" value="Добавить фильм">
</form>
</body>
</html>

