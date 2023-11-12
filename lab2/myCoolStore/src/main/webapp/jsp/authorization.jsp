<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
</head>
<body>
<h1>Авторизация</h1>
<a href="${pageContext.request.contextPath}">На главную страницу</a><br>
<form action="${pageContext.request.contextPath}/Controller" method="post">
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

