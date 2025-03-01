<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 5.6.2023.
  Time: 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<FORM NAME="form1" ACTION="<%=request.getContextPath()%>/servleti/main" METHOD="POST">
    <TABLE bgcolor="#D8D8D8">
        <tr>
            <td> Nickname </td>
            <td><input type="text" name="nick"></td>
        </tr>
        <tr>
            <td> Password </td>
            <td><input type="text" name="pass"></td>
        </tr>
            <td><INPUT TYPE="submit" VALUE="submit"></td>
        </TABLE>
</FORM>
<div><a href="<%=request.getContextPath()%>/servleti/register">Registriraj se</a></div>
<div><a href="<%=request.getContextPath()%>/servleti/logout">Odjavi se</a></div>
<div><c:forEach var="u" items="${users}">
    <tr>
        <li><a href="<%=request.getContextPath()%>/servleti/author/${u.getNick()}">${u.getNick()}</a></li>
    </tr>
</c:forEach></div>
</body>
</html>
