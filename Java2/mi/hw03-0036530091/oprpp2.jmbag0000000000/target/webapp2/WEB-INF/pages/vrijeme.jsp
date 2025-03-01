<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 17.4.2023.
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vrijeme</title>
</head>

<body style="background-image:url(${slika})">
<p><%=new SimpleDateFormat("dd.MM.yyyy.  HH:mm:ss").format(Calendar.getInstance().getTime())%></p>

<a href="odabirSlike.jsp">  Odabir slike</a>
</body>
</html>
