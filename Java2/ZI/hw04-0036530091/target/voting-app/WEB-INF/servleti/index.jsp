<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 27.5.2023.
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Glasanje</title>
</head>
<body>
<ol>

<c:forEach var="u" items="${list}">
    <li><a href="glasanje?pollId=${u}">${map1.get(u)}</a> <p>${map2.get(u)}</p></li>
</c:forEach>
</ol>
</body>
</html>
