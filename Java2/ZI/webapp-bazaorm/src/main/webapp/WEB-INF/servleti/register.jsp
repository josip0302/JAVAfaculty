<%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 5.6.2023.
  Time: 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registriraj se</title>
</head>
<body>
<FORM NAME="form2" ACTION="<%=request.getContextPath()%>/servleti/register" METHOD="POST">
    <TABLE bgcolor="#D8D8D8">
        <tr>
            <td> First name </td>
            <td><input type="text" name="firstName"></td>
        </tr>
        <tr>
            <td> Last name </td>
            <td><input type="text" name="lastName"></td>
        </tr>

        <tr>
            <td> E-mail </td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td> Nickname </td>
            <td><input type="text" name="nick"></td>
        </tr>
        <tr>
            <td> Password </td>
            <td><input type="text" name="pass"></td>
        </tr>
        <td><INPUT TYPE="submit" VALUE="show"></td>
    </TABLE>
</FORM>
</body>
</html>
