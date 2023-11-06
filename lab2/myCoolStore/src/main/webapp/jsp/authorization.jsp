<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Форма Авторизации</title>
</head>
<body>
<h1>Авторизация</h1>
<form action="Controller" method="post">
    <label for="emailAuth">Электронная почта:</label>
    <input type="email" id="emailAuth" name="emailAuth" required>
    <br>
    <label for="passwordAuth">Пароль:</label>
    <input type="password" id="passwordAuth" name="passwordAuth" required>
    <br>
    <input type="submit" value="Авторизоваться">
    <input type="hidden" name="command" value="sign_in" />
</form>
</body>
</html>

