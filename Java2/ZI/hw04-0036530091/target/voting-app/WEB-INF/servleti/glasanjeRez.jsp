<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 13.4.2023.
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style type="text/css">
        table.rez td {text-align: center;}
    </style>
</head>
<body>

<h1>Rezultati glasanja</h1>
<p>Ovo su rezultati glasanja.</p>
<table border="1" cellspacing="0" class="rez">
    <thead><tr><th>Bend</th><th>Like</th><th>Dislike</th><th>razlika</th></tr></thead>
    <tbody>
    <c:forEach var="u" items="${rez}">
        <tr><td>${u.key}</td><td>${u.value}</td><td>${dis.get(u.key)}</td><td>${raz.get(u.key)}</td></tr>
    </c:forEach>

    </tbody>
</table>
<h2>Grafički prikaz rezultata</h2>
<img alt="Pie-chart"src="<%=request.getContextPath()%>/glasanje-grafika"width="400"height="400"/>
<h2>Rezultati u XLS formatu</h2>
<p>Rezultati u XLS formatu dostupni su <a href="<%=request.getContextPath()%>/glasanje-xls">ovdje</a></p>
<h2>Razno</h2><p>Primjeri pjesama pobjedničkih bendova:</p><ul>
    <li><a href="${linkNumber1}"target="_blank">${bandNumber1}</a></li>
    <li><a href="${linkNumber2}"target="_blank">${bandNumber2}</a></li>
</ul>

</body>
</html>
