<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 5.6.2023.
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div><c:forEach var="u" items="${entries}">
    <tr>
        <li><a href="<%=request.getContextPath()%>/servleti/author/${u.getCreator()}/${u.getId()}">${u.getTitle()}</a></li>
    </tr>
</c:forEach></div>
<%if(request.getSession().getAttribute("current.user.id")!=null){%>
a href="<%=request.getContextPath()%>/servleti/author/">Dodaj novi BlogEntry</a>
<%}%>
</body>
</html>
