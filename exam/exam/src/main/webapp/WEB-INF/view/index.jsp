<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Exam task</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/Table" method="get">
    <input type="hidden" name="parser" value="dom">
    <input type="submit" value="DOM">
</form>
<form action="${pageContext.request.contextPath}/Table" method="get">
    <input type="hidden" name="parser" value="sax">
    <input type="submit" value="SAX">
</form>
<form action="${pageContext.request.contextPath}/Table" method="get">
    <input type="hidden" name="parser" value="stax">
    <input type="submit" value="STAX">
</form>
</body>
</html>
