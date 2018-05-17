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
    <title>Showroom</title>
</head>
<body>
<c:set var="showrooms" value="${requestScope.showrooms}" />
<table>
    <c:forEach items="${showrooms}" var="showroom">
        <tr>
            <td>${showroom.id}</td>
            <td>${showroom.name}</td>
            <td>${showroom.location}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
