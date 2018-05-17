<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 16.05.2018
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
    </div>
    <div class="row">
    </div>
    <div class="row">

    </div>

    <div class="row">

        <form action="api" method="post">
            <input type="hidden" name="command" value="verify_login"/>
            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="icon_prefix" type="text" class="validate" name="login"
                           value="${requestScope.login}" required>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">lock</i>
                    <input id="icon_lock" class="validate" type="text" name="password" value="${requestScope.password}" required>
                    <label for="icon_lock"><"login"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s2 offset-s3">
                    <button class="btn waves-effect waves-light pink darken-4" type="submit">
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </div>
        </form>

    </div>
</div>
</body>
</html>
