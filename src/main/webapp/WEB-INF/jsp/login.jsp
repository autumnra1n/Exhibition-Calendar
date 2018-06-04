<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="locale.jsp" />
<html lang="${language}">
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body class="m-3">
<c:if test="${not empty requestScope.invalid_login}">
    <fmt:message key="invalid.login"/>
</c:if>
<c:if test="${not empty requestScope.invalid_password}">
    <fmt:message key="invalid.password"/>
</c:if>
<c:if test="${not empty requestScope.invalid_limit}">
    <fmt:message key="invalid.limit"/>
</c:if>
<c:if test="${not empty requestScope.invalid_limit_length}">
    <fmt:message key="invalid.limitLength"/>
</c:if>
<c:if test="${not empty requestScope.invalid_current_page_type}">
    <fmt:message key="invalid.currentPageType"/>
</c:if>
<c:if test="${not empty requestScope.invalid_current_page_length}">
    <fmt:message key="invalid.currentPageLength"/>
</c:if>
<div class="col-md-4 text-center">
    <h1><fmt:message key="app.exhibition.calendar"/></h1>
</div>
        <form action="api" method="post">
            <div class="container center_div">
            <div class="form-group col-md-4">
                <input type="hidden" name="command" value="verify_profile"/>
                <input type="hidden" name="currentPage" value="1">
                    <label for="loginForm"><fmt:message key="app.type.login"/>:</label>
                    <input id="loginForm" class="form-control" name="login"
                           value="" required>
                    <br>
                    <label for="passwordForm"><fmt:message key="app.type.password"/></label>
                    <input id="passwordForm" class="form-control" name="password"
                       value="" required>
            </div>
            </div>
            <div class="form-group">
                <div class="container center_div">
                    <label for="records"><fmt:message key="app.exhb.page"/>:</label>
                    <div class="col-sm-2">
                    <select class="form-control" id="records" name="limit">
                        <option value="3"selected>3</option>
                        <option value="6">6</option>
                        <option value="9">9</option>
                    </select>
                    </div>
                </div>
                <br>
            </div>
            <div class="form-group">
            <div class="container center_div">
                <label for="locale"><fmt:message key="app.choose.locale"/>:</label>
                <div class="col-sm-2">
                    <select class="form-control" id="locale" name="locale">
                        <option value="en">en</option>
                        <option value="ru">ru</option>
                    </select>
                </div>
            </div>
            <br>
            </div>
            <div class="col-md-4 text-center">
                <button type="submit" class="btn btn-primary"><fmt:message key="app.sign.in"/></button>
            </div>
        </form>
        <form action="api" method="post">
            <div class="col-md-4 text-center">
                <input type="hidden" name="command" value="forward_to_registration_page"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="app.sign.up"/></button>
            </div>
        </form>
    </div>
</body>
</html>
