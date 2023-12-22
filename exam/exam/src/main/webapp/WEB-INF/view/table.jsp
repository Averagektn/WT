<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="dataset" scope="request" type="java.util.List"/>
<jsp:useBean id="pagesCount" scope="request" type="java.lang.Integer"/>

<html>
<head>
    <title>Exam task</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Patronymic</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="data" items="${dataset}">
        <tr>
            <td>${data.name}</td>
            <td>${data.surname}</td>
            <td>${data.patronymic}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <c:forEach var="pageNumber" begin="1" end="${pagesCount}">
        <c:url value="/Table" var="pageUrl">
            <c:param name="parser" value="${param.parser}" />
            <c:param name="page" value="${pageNumber}" />
        </c:url>
        <a href="${pageUrl}">${pageNumber}</a>
    </c:forEach>
</div>

</body>
</html>