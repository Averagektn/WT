<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Форма Регистрации</title>
</head>
<body>
<h1>Регистрация</h1>
<form action="Controller" method="post">
    <label for="emailRegister">Электронная почта:</label>
    <input type="email" id="emailRegister" name="emailRegister" required><br>
    <label for="passwordRegister">Пароль:</label>
    <input type="password" id="passwordRegister" name="passwordRegister" required><br>
    <input type="submit" value="Зарегистрироваться">
    <input type="hidden" name="command" value="register"/>
</form>
</body>
</html>

