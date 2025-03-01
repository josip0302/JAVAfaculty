<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 12.4.2023.
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>Background color chooser</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/setcolor?pickedBgCol=WHITE">WHITE</a>
<a  href="<%=request.getContextPath()%>/setcolor?pickedBgCol=RED">RED</a>
<a  href="<%=request.getContextPath()%>/setcolor?pickedBgCol=GREEN">GREEN</a>
<a href="<%=request.getContextPath()%>/setcolor?pickedBgCol=CYAN">CYAN</a>

</body>
</html>
