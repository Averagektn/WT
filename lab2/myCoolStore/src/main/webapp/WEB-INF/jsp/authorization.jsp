<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/authorisation.css">
    <title>My Cool Site</title>
</head>
<body>
<div class="header">
    <a id="to-main-page" class="header-link" href="${pageContext.request.contextPath}"><fmt:message
            key="to_main_page"/></a><br>
</div>

<div class="content">
    <h1 id="authorisation-header"><fmt:message key="authorisation"/></h1>

    <form action="${pageContext.request.contextPath}/Controller" method="post" class="authorisation-form">
        <label for="emailAuthorization"><fmt:message key="email"/>:</label>
        <input type="email" id="emailAuthorization" name="emailAuthorization" required>
        <br>
        <label for="passwordAuthorization"><fmt:message key="password"/>:</label>
        <input type="password" id="passwordAuthorization" name="passwordAuthorization" required>
        <br>
        <input type="submit" id="authorise-btn" value="<fmt:message key="authorise"/>">
        <input type="hidden" name="command" value="sign_in"/>
    </form>
</div>
</body>
</html>
