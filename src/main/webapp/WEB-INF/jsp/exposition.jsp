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
    <title>Exposition</title>
</head>
<body>
<c:set var="expositions" value="${requestScope.expositions}" />
<table>
    <c:forEach items="${expositions}" var="exsposition">
        <tr>
            <td>${exsposition.id}</td>
            <td>${exsposition.theme}</td>
            <td>${exsposition.dateStart}</td>
            <td>${exsposition.startTime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
