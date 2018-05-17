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
    <title>Role</title>
</head>
<body>
<c:set var="roles" value="${requestScope.roles}" />
<table>
    <c:forEach items="${roles}" var="role">
        <tr>
            <td>${role.id}</td>
            <td>${role.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
