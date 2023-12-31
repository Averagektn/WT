<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My Cool Site</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/register.css">
</head>
<body>
<div class="header">
    <a id="to-main-page" class="header-link" href="${pageContext.request.contextPath}">
        <fmt:message key="to_main_page"/>
    </a>
</div>

<div class="content">
    <h1 id="registration-header"><fmt:message key="reg_header"/></h1>

    <form:form action="${pageContext.request.contextPath}/Register" method="post" modelAttribute="user"
               class="registration-form">
        <label for="emailRegister"><fmt:message key="email"/>:</label>
        <form:input type="email" id="emailRegister" name="emailRegister" path="usrEmail"/><br>

        <label for="passwordRegister"><fmt:message key="password"/>:</label>
        <form:input type="password" id="passwordRegister" name="passwordRegister" path="usrPassword"/><br>

        <input id="register-btn" type="submit" value="<fmt:message key="register"/>">
    </form:form>
</div>
</body>
</html>
