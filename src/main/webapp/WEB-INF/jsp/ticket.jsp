<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 13.05.2018
  Time: 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ticket</title>
</head>
<body>
<c:set var="tickets" value="${requestScope.tickets}" />
<table>
    <c:forEach items="${tickets}" var="ticket">
        <tr>
            <td>${ticket.id}</td>
            <td>${ticket.value}</td>
            <td>${ticket.amount}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
