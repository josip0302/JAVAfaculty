<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 13.4.2023.
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Funny Story</title>
</head>
<% List<String> list= new ArrayList();list.add("red");list.add("green");list.add("blue");
String color=list.get(new Random().nextInt(3));
    request.setAttribute("color",color);%>
<body>
<p style="color:${color}">
    Kad sam upisivao javu, studenti s viših godina su mi savjetovali
    da ako budem imao curu da nađem još jednu, da svaka misli da sam s drugom, dok
    ja zapravo programiram zadaću iz jave.
</p>
</body>
</html>
