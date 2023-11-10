<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<h1>Admin film</h1>

<form action="Controller" method="post" enctype="multipart/form-data">
    <label for="filmTitle">Название фильма:</label>
    <input type="text" id="filmTitle" name="filmTitle"><br>

    <label for="filmAuthor">Автор фильма:</label>
    <input type="text" id="filmAuthor" name="filmAuthor"><br>

    <label for="filmCategory">Категория:</label>
    <select id="filmCategory" name="filmCategory">
        <option value="категория1">Категория 1</option>
        <option value="категория2">Категория 2</option>
        <option value="категория3">Категория 3</option>
    </select><br>

    <label for="ageCategory">Возрастная категория:</label>
    <select id="ageCategory" name="ageCategory">
        <option value="0+">0+</option>
        <option value="6+">6+</option>
        <option value="12+">12+</option>
        <option value="16+">16+</option>
        <option value="18+">18+</option>
    </select><br>

    <label for="videoFile">Файл видео:</label>
    <input type="file" id="videoFile" name="videoFile"><br>

    <label for="trailerFile">Файл трейлера:</label>
    <input type="file" id="trailerFile" name="trailerFile"><br>

    <label for="filmDescription">Описание фильма:</label>
    <textarea id="filmDescription" name="filmDescription" rows="4"></textarea><br>

    <label for="filmPrice">Цена:</label>
    <input type="text" id="filmPrice" name="filmPrice"><br>

    <label for="filmDiscount">Скидка:</label>
    <input type="text" id="filmDiscount" name="filmDiscount"><br>

    <input type="submit" value="Добавить фильм">
</form>
</body>
</html>

