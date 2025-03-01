<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 13.4.2023.
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${naslov}</h1>
<p>${poruka}</p>
<ol>
    <c:forEach var="u" items="${mapaBendova}">
        <li>${u.value} <a href="glasanje-glasaj?like=${u.key}">Like</a> <a href="glasanje-glasaj?dis=${u.key}">Dislike</a> </li>
    </c:forEach>

</ol>
</body>
</html>
