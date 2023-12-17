<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>My Cool Site</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
</head>
<body>

<form class="language-form" action="#" th:action="@{/Language}" method="post">
    <label for="language"></label>
    <select id="language" name="language" onchange="this.form.submit()">
        <option value="en" th:selected="${session.lang == null || session.lang == 'en'}">English</option>
        <option value="ru" th:selected="${session.lang == 'ru'}">Russian</option>
    </select>
</form>

<div th:if="${session.userID == null}">
    <form action="#" th:action="@{/Register}" method="get" class="reg-form">
        <input type="submit" value="Register"/>
    </form>
    <form action="#" th:action="@{/Authorization}" method="get" class="auth-form">
        <input type="submit" value="Authorization"/>
    </form>
</div>

<div th:if="${session.userID != null}">
    <form action="#" th:action="@{/Exit}" method="post" class="auth-form">
        <input type="submit" value="Exit"/>
        <input type="hidden" name="command" value="sign_out"/>
    </form>
    <div th:if="${session.isAdmin}">
        <div class="admin-links">
            <h1>Admin Mode</h1>
            <a class="admin-btn" th:href="@{/Admin/AddFilm}">Add Film</a>
            <br>
            <a class="admin-btn" th:href="@{/Admin/BanList}">Ban List</a>
        </div>
    </div>
    <div th:unless="${session.isAdmin}">
        <div class="user-links">
            <a class="user-btn" th:href="@{/User/Library}">Library</a>
            <br>
            <a class="user-btn" th:href="@{/User/Cart}">Cart</a>
            <br>
        </div>
    </div>
</div>

<ul>
    <li th:each="film : ${films}">
        <a th:href="@{/Film(filmId=${film.flmId})}" th:text="${film.flmName}"></a>
        <br>
        <span th:if="${film.flmDiscount != 0}" style="text-decoration: line-through;"
              th:text="${film.flmPrice}"></span>
        <span th:unless="${film.flmDiscount != 0}" th:text="${film.flmPrice}"></span>
        <br>
        <span>Age Restriction: </span><span th:text="${film.flmAge}"></span>
        <br>
        <span>Author: </span><span th:text="${film.flmAuthor}"></span>
        <br>
        <span>Categories: </span>
        <span th:each="category : ${film.categories}" th:text="${category.catName}"></span>
        <br>
        <a th:if="${session.isAdmin}" th:href="@{/Admin/EditFilm(filmId=${film.flmId})}">Edit Film</a>
        <br>
    </li>
</ul>

</body>
</html>