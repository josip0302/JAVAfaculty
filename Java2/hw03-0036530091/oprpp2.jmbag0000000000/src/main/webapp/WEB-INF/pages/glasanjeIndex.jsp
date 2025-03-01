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
<h1>Glasanje za omiljeni bend:</h1>
<p>Od sljedećih bendova, koji Vam je bend najdraži? Kliknite na link kako biste glasali!</p>
<ol>
    <c:forEach var="u" items="${mapaBendova}">
        <li><a href="glasanje-glasaj?id=${u.key}">${u.value}</a></li>
    </c:forEach>

</ol>
</body>
</html>
