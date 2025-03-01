<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.concurrent.TimeUnit" %><%--
  Created by IntelliJ IDEA.
  User: Josip
  Date: 13.4.2023.
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% long a=System.currentTimeMillis() -request.getSession().getCreationTime();
long days=TimeUnit.MILLISECONDS.toDays(a);
long hours=TimeUnit.MILLISECONDS.toHours(a)-TimeUnit.DAYS.toHours(days);
long minutes=TimeUnit.MILLISECONDS.toMinutes(a)-TimeUnit.DAYS.toMinutes(days)-TimeUnit.HOURS.toMinutes(hours);
long seconds=TimeUnit.MILLISECONDS.toSeconds(a) -TimeUnit.MINUTES.toSeconds(minutes)-TimeUnit.DAYS.toSeconds(days)-TimeUnit.HOURS.toSeconds(hours);
long miliseconds=a-TimeUnit.SECONDS.toMillis(seconds) -TimeUnit.MINUTES.toMillis(minutes)-TimeUnit.DAYS.toMillis(days)-TimeUnit.HOURS.toMillis(hours);
    request.setAttribute("day", days);
    request.setAttribute("hours",hours);
    request.setAttribute("minutes",minutes);
    request.setAttribute("seconds",seconds);
    request.setAttribute("miliseconds",miliseconds);%>
<p>Days:${day}</p>
<p>Hours:${hours}</p>
<p>Minutes:${minutes}</p>
<p>Seconds:${seconds}</p>
<p>Miliseconds:${miliseconds}</p>
</body>
</html>
