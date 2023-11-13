<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<h1>Регистрация</h1>
<a href="${pageContext.request.contextPath}">На главную страницу</a><br>
<form action="${pageContext.request.contextPath}/Controller" method="post">
    <label for="emailRegister">Электронная почта:</label>
    <input type="email" id="emailRegister" name="emailRegister" required><br>

    <label for="passwordRegister">Пароль:</label>
    <input type="password" id="passwordRegister" name="passwordRegister" required><br>

    <input type="submit" value="Зарегистрироваться">

    <input type="hidden" name="command" value="register"/>
</form>
</body>
</html>

