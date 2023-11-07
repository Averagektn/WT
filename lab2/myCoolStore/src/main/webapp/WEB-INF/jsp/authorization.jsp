<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Форма Авторизации</title>
</head>
<body>
<h1>Авторизация</h1>
<form action="Controller" method="post">
    <label for="emailAuthorization">Электронная почта:</label>
    <input type="email" id="emailAuthorization" name="emailAuthorization" required>
    <br>
    <label for="passwordAuthorization">Пароль:</label>
    <input type="password" id="passwordAuthorization" name="passwordAuthorization" required>
    <br>
    <input type="submit" value="Авторизоваться">
    <input type="hidden" name="command" value="sign_in"/>
</form>
</body>
</html>

