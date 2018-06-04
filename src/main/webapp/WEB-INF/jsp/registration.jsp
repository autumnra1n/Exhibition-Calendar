<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="locale.jsp" />
<html lang="${language}">
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${not empty requestScope.invalid_login}">
    <fmt:message key="invalid.login"/>
</c:if>
<c:if test="${not empty requestScope.invalid_password}">
    <fmt:message key="invalid.password"/>
</c:if>
<c:if test="${not empty requestScope.invalid_email}">
    <fmt:message key="invalid.email"/>
</c:if>
<c:if test="${not empty requestScope.invalid_first_name}">
    <fmt:message key="invalid.first.name"/>
</c:if>
<c:if test="${not empty requestScope.invalid_last_name}">
    <fmt:message key="invalid.last.name"/>
</c:if>
<c:if test="${not empty requestScope.invalid_login}">
    <fmt:message key="invalid.login"/>
</c:if>
<c:if test="${not empty requestScope.invalid_password}">
    <fmt:message key="invalid.password"/>
</c:if>
<c:if test="${not empty requestScope.invalid_email}">
    <fmt:message key="invalid.email"/>
</c:if>
<c:if test="${not empty requestScope.invalid_first_name}">
    <fmt:message key="invalid.first.name"/>
</c:if>
<c:if test="${not empty requestScope.invalid_last_name}">
    <fmt:message key="invalid.last.name"/>
</c:if>
<div class="col-md-4 text-center">
    <fmt:message key="app.type.firstName"/>
    <h1><fmt:message key="app.type.email"/></h1>
</div>
<form action="api" method="post">
    <input type="hidden" name="command" value="insert_user"/>
        <div class="container center_div">
            <div class="form-group col-md-4">
                <label for="loginForm"><fmt:message key="app.type.login"/></label>
                <input id="loginForm" class="form-control" name="login"
                       value="" required>
                <br>
                <label for="passwordForm"><fmt:message key="app.type.password"/></label>
                <input id="passwordForm" class="form-control" name="password"
                       value="" required>
                <label for="emailForm"><fmt:message key="app.type.password"/></label>
                <input id="emailForm" class="form-control" name="email"
                       value="" required>
                <label for="firstNameForm"><fmt:message key="app.type.firstName"/></label>
                <input id="firstNameForm" class="form-control" name="firstName"
                       value="" required>
                <label for="lastNameForm"><fmt:message key="app.type.lastName"/></label>
                <input id="lastNameForm" class="form-control" name="lastName"
                       value="" required>
            </div>
            <div class="col-md-4 text-center">
                <button type="submit" class="btn btn-primary"><fmt:message key="app.registration"/></button>
            </div>
        </div>
</form>
</body>
</html>
