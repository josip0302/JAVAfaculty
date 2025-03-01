<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 13.4.2023.
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trigonometric</title>
</head>
<body>
<ol>
    <c:forEach var="u" items="${list}">
        <li>${u}</li>
    </c:forEach>

</ol>
</body>
</html>
