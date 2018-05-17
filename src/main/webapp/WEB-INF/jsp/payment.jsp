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
    <title>Payment</title>
</head>
<body>
<c:set var="payments" value="${requestScope.payments}" />
<table>
    <c:forEach items="${payments}" var="payment">
        <tr>
            <td>${payment.id}</td>
            <td>${payment.date}</td>
            <td>${payment.user.login}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
