<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 12.4.2023.
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  session="true" %>
<html>
<head>
    <title>Index</title>
</head>
<body style="background-color:${color};">
<a href="colors.jsp">Background color chooser</a>

<form action="trigonometric" method="GET">
    Početni kut:
    <br><input type="number" name="a" min="0" max="360" step="1" value="0"><br>
    Završni kut:
    <br><input type="number" name="b" min="0" max="360" step="1" value="90"><br>
    <input type="submit" value="Tabeliraj"><input type="reset" value="Reset">
</form>
<a href="stories/funny.jsp">Funny Story</a>
<a href="report.jsp">Report Chart</a>
<form action="powers" method="GET">

    <br><input type="number" name="ap" min="-100" max="100" step="1" value="0"><br>
    <br><input type="number" name="bp" min="-100" max="100" step="1" value="100"><br>
    <br><input type="number" name="np" min="1" max="5" step="1" value="3"><br>
    <input type="submit" value="submit"><input type="reset" value="Reset">
</form>
<a href="appinfo.jsp">Session runtime</a>
<a href="<%=request.getContextPath()%>/glasanje">Glasanje</a>
</body>
</html>
