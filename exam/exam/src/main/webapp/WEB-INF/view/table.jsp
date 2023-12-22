<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:useBean id="dataset" scope="request" type="java.util.List"/>

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
</body>
</html>