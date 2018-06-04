<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="locale.jsp" />
<html lang="${language}">
<head>
    <title>Role</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
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
<c:if test="${not empty requestScope.invalid_id_type}">
    <fmt:message key="invalid.id.type"/>
</c:if>
<c:if test="${not empty requestScope.invalid_id_length}">
    <fmt:message key="invalid.id.length"/>
</c:if>
<c:set var="roleList" value="${requestScope.roles}" />
<c:set var="roleEntity" value="${requestScope.role}" />
<c:if test="${empty roleList && empty roleEntity && sessionScope.user.role.name == 'admin'}">
    <form action="api">
        <input type="hidden" name="command" value="find_all_roles">
        <input type="hidden" name="currentPage" value="1">
        <div class="form-group">
            <div class="container center_div">
                <label for="record">Select roles per page:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="record" name="limit">
                        <option value="3"selected>3</option>
                        <option value="6">6</option>
                        <option value="9">9</option>
                    </select>
                </div>
            </div>
            <br>
        </div>
        <div class="col-md-4 text-center">
            <button type="submit" class="btn btn-primary">select</button>
        </div>
    </form>
        <form action="api">
            <input type="hidden" name="command" value="forward_to_admin_page"/>
            <div class=text-center>
                <button type="submit" class="btn btn-primary">To admin page</button>
            </div>
        </form>
</c:if>
<c:if test="${not empty roleList && sessionScope.user.role.name == 'admin'}">
<div class="container">
    <br>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="logout"/>
        <div class=text-right>
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <h2>Role table</h2>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        </thead>
        <c:forEach items="${roleList}" var="role">
            <tbody>
            <tr class="table-primary">
                <td><c:out value="${role.id}"/></td>
                <td><c:out value="${role.name}"/></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:if test="${requestScope.currentPage != 1}">
            <form action="api" method="post">
                <input type="hidden" name="command" value="find_all_roles"/>
                <input type="hidden" name="limit" value="${requestScope.limit}"/>
                <input type="hidden" name="currentPage" value="${requestScope.currentPage-1}"/>
                <button type="submit" class="btn btn-primary">Previous</button>
            </form>
        </c:if>
        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <form action="api" method="post" class="page-item">
                        <input type="hidden" name="command" value="find_all_roles"/>
                        <input type="hidden" name="limit" value="${requestScope.limit}"/>
                        <input type="hidden" name="currentPage" value="${i}"/>
                        <button type="submit" class="btn btn-default">${i}</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
            <form action="api" method="post">
                <input type="hidden" name="command" value="find_all_roles"/>
                <input type="hidden" name="limit" value="${requestScope.limit}"/>
                <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
                <button type="submit" class="btn btn-primary">Next</button>
            </form>
        </c:if>
    </ul>
</nav>
<div class="row justify-content-center">
<div class="col-sm-3">
    <form action="api">
        <input type="hidden" name="command" value="find_users_by_role_id"/>
        <input type="hidden" name="currentPage" value="1"/>
        <div class="container center_div">
            <div class="form-group">
                <label for="form">Type role id:</label>
                <input id="form" class="form-control" name="roleId"
                       value="" required>
            </div>
        </div>
        <br>
        <div class="col-md-3 text-center">
            <button type="submit" class="btn btn-primary">Select user</button>
        </div>
    </form>
</div>
<div class="col-sm-3">
    <form action="api">
        <input type="hidden" name="command" value="find_users_by_role_id"/>
        <input type="hidden" name="currentPage" value="1"/>
        <div class="container center_div">
            <div class="form-group">
                <label for="expo">Choose role id:</label>
                <select class="form-control" id="expo" name="roleId">
                    <c:forEach items="${roleList}" var="role">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select>
                <div class="col-sm-4">
                    <label for="recss">Choose users per page:</label>
                    <select class="form-control" id="recss" name="limit">
                        <option value="3"selected>3</option>
                        <option value="6">6</option>
                        <option value="9">9</option>
                    </select>
                </div>
            </div>
        </div>
        <br>
        <div class="col-md-3 text-center">
            <button type="submit" class="btn btn-primary">Select user</button>
        </div>
    </form>
</div>
</div>
<div class="row justify-content-center">
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_role_by_id"/>
            <div class="container center_div">
                <div class="form-group">
                    <label for="forms">Type role id:</label>
                    <input id="forms" class="form-control" name="roleId"
                           value="" required>
                </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Select role</button>
            </div>
        </form>
    </div>
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_role_by_id"/>
            <div class="container center_div">
                <div class="form-group">
                    <label for="expos">Choose role id:</label>
                    <select class="form-control" id="expos" name="roleId">
                        <c:forEach items="${roleList}" var="role">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Select role</button>
            </div>
        </form>
    </div>
</div>
<form action="api">
    <input type="hidden" name="command" value="forward_to_admin_page"/>
    <div class=text-center>
        <button type="submit" class="btn btn-primary">To admin page</button>
    </div>
</form>
</c:if>
<c:if test="${not empty roleEntity && sessionScope.user.role.name == 'admin'}">
<div class="container">
    <br>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="logout"/>
        <div class=text-right>
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <h2>Role information</h2>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        </thead>
        <tbody>
        <tr class="table-primary">
            <td><c:out value="${requestScope.role.id}"/></td>
            <td><c:out value="${requestScope.role.name}"/></td>
        </tr>
        </tbody>
    </table>
    <br>
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_users_by_role_id"/>
                <input type="hidden" name="roleId" value="${requestScope.role.id}"/>
                <input type="hidden" name="currentPage" value="1"/>
                <label for="rec">Select user per page:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="rec" name="limit">
                        <option value="3"selected>3</option>
                        <option value="6">6</option>
                        <option value="9">9</option>
                    </select>
                </div>
                <div class="col-md-3 text-center">
                    <button type="submit" class="btn btn-primary">Select user</button>
                </div>
            </form>
        </div>
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_role_by_id"/>
                <div class="container center_div">
                    <div class="form-group">
                        <label for="formss">Type role id:</label>
                        <input id="formss" class="form-control" name="roleId"
                               value="" required>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button type="submit" class="btn btn-primary">Select role</button>
                </div>
            </form>
        </div>
    <div class="col-sm-3">
    <form action="api">
        <input type="hidden" name="command" value="forward_to_admin_page"/>
        <div class=text-center>
            <button type="submit" class="btn btn-primary">To admin page</button>
        </div>
    </form>
    </div>
    </div>
</div>
</c:if>
</body>
</html>
