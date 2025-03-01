<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 17.4.2023.
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Odabir Slike</title>
</head>
<body>
<h1>Odaberite sliku</h1>
<a href="<%=request.getContextPath()%>/vrijeme?number=0"><img src="https://www.fer.unizg.hr/_news/icons/a1580ff3a85ebd78506b23ae43a064235410_icon.jpg" width="400"height="400"/>
</a>

<a href="<%=request.getContextPath()%>/vrijeme?number=1"><img src="https://www.fer.unizg.hr/lib/generic_theme/images/FER_logo_1.svg" width="400"height="400"/>
</a>
</body>
</html>
