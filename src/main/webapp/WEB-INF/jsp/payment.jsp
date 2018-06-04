<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="locale.jsp" />
<html lang="${language}">
<head>
    <title>Payment</title>
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
<c:set var="paymentList" value="${requestScope.payments}" />
<c:set var="paymentEntity" value="${requestScope.payment}" />
<c:if test="${empty paymentList && empty paymentEntity && not empty sessionScope.user}">
    <form action="api">
        <input type="hidden" name="command" value="find_payment_by_user_id">
        <input type="hidden" name="currentPage" value="1">
        <input type="hidden" name="userId" value="${sessionScope.user.id}">
        <div class="form-group">
            <div class="container center_div">
                <label for="record">Select payments per page:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="record" name="limit">
                        <option value="3" selected>3</option>
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
    <c:if test="${sessionScope.user.role.name == 'admin'}">
        <form action="api">
            <input type="hidden" name="command" value="forward_to_admin_page"/>
            <div class=text-center>
                <button type="submit" class="btn btn-primary">To admin page</button>
            </div>
        </form>
    </c:if>
</c:if>
<c:if test="${not empty paymentList}">
<div class="container">
    <form action="api">
    <input type="hidden" name="command" value="logout"/>
    <div class=text-right>
    <button type="submit" class="btn btn-primary">Logout</button>
    </div>
    </form>
    <h2>Payment table</h2>
    <table class="table">
    <thead>
    <tr>
    <th>id</th>
    <th>date</th>
    <th>user login</th>
    </tr>
    </thead>
    <c:forEach items="${paymentList}" var="payment">
        <tbody>
        <tr class="table-primary">
            <td><c:out value="${payment.id}"/></td>
            <td><c:out value="${payment.date}"/></td>
            <td><c:out value="${requestScope.payment.ticket.name}"/></td>
            <td><c:out value="${requestScope.payment.ticket.value}"/></td>
            <td><c:out value="${requestScope.payment.ticket.exposition.name}"/></td>
            <td><c:out value="${payment.user.login}"/></td>
        </tr>
        </tbody>
    </c:forEach>
    </table>
    <c:if test="${param.command == 'find_all_payments'}">
    <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
    <c:if test="${requestScope.currentPage != 1}">
        <form action="api" method="post">
            <input type="hidden" name="command" value="find_all_payments"/>
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
                    <input type="hidden" name="command" value="find_all_payments"/>
                    <input type="hidden" name="limit" value="${requestScope.limit}"/>
                    <input type="hidden" name="currentPage" value="${i}"/>
                    <button type="submit" class="btn btn-default">${i}</button>
                </form>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
        <form action="api" method="post">
            <input type="hidden" name="command" value="find_all_payments"/>
            <input type="hidden" name="limit" value="${requestScope.limit}"/>
            <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
            <button type="submit" class="btn btn-primary">Next</button>
        </form>
    </c:if>
    </ul>
    </nav>
    </c:if>
    <c:if test="${param.command == 'find_users_by_role_id'}">
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${requestScope.currentPage != 1}">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="find_users_by_role_id"/>
                        <input type="hidden" name="roleId" value="${requestScope.roleId}"/>
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
                                <input type="hidden" name="command" value="find_users_by_role_id"/>
                                <input type="hidden" name="roleId" value="${requestScope.roleId}"/>
                                <input type="hidden" name="limit" value="${requestScope.limit}"/>
                                <input type="hidden" name="currentPage" value="${i}"/>
                                <button type="submit" class="btn btn-default">${i}</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="find_users_by_role_id"/>
                        <input type="hidden" name="roleId" value="${requestScope.roleId}"/>
                        <input type="hidden" name="limit" value="${requestScope.limit}"/>
                        <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
                        <button type="submit" class="btn btn-primary">Next</button>
                    </form>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <div class="row justify-content-center">
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_all_showrooms"/>
            <input type="hidden" name="currentPage" value="1">
            <div class="container center_div">
                <div class="form-group">
                    <label for="r">Select showrooms per page:</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="r" name="limit">
                            <option value="3"selected>3</option>
                            <option value="6">6</option>
                            <option value="9">9</option>
                        </select>
                    </div>
                </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button class="btn btn-primary">Select showrooms</button>
            </div>
        </form>
    </div>
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_all_expositions"/>
            <input type="hidden" name="currentPage" value="1">
            <div class="container center_div">
                <div class="form-group">
                    <label for="recss">Select expositions per page:</label>
                    <div class="col-sm-4">
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
                <button class="btn btn-primary">Select expositions</button>
            </div>
        </form>
    </div>
    </div>
    <br>
    <c:if test="${sessionScope.user.role.name == 'admin'}">
        <div class="row justify-content-center">
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="find_payment_by_id"/>
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="recssss">SChoose payment:</label>
                            <select class="form-control" id="recssss" name="paymentId">
                                <c:forEach items="${paymentList}" var="payment">
                                    <option value="${payment.id}">${payment.id}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-3 text-center">
                        <button type="submit" class="btn btn-primary">Select payment</button>
                    </div>
                </form>
            </div>
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="find_all_payments"/>
                    <input type="hidden" name="currentPage" value="1">
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="records">Select payments per page:</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="records" name="limit">
                                    <option value="3"selected>3</option>
                                    <option value="6">6</option>
                                    <option value="9">9</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-3 text-center">
                        <button class="btn btn-primary">Select payments</button>
                    </div>
                </form>
            </div>
            <div class="col-sm-3">
            <form action="api">
            <input type="hidden" name="command" value="find_payment_by_id"/>
            <div class="container center_div">
                <div class="form-group">
                    <label for="expo">Choose user id:</label>
                    <select class="form-control" id="expo" name="paymentId">
                        <c:forEach items="${paymentList}" var="payment">
                            <option value="${payment.id}">${payment.id}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Select users payments</button>
            </div>
            </form>
            </div>
        <br>
        <div class="row justify-content-center">
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="forward_to_admin_page"/>
                    <div class=text-center>
                        <button type="submit" class="btn btn-primary">To admin page</button>
                    </div>
                </form>
            </div>
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="find_payment_by_user_id"/>
                    <input type="hidden" name="currentPage" value="1">
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="rec">Select payments per page:</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="rec" name="limit">
                                    <option value="3"selected>3</option>
                                    <option value="6">6</option>
                                    <option value="9">9</option>
                                </select>
                            </div>
                            <label for="re">Type user id:</label>
                            <input id="re" class="form-control" name="userId"
                                   value="" required>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-3 text-center">
                        <button class="btn btn-primary">Select payment</button>
                    </div>
                </form>
            </div>
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="find_payment_by_ticket_id"/>
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="resc">Type ticket id:</label>
                            <div class="col-sm-4">
                                <input id="resc" class="form-control" name="ticketId"
                                       value="" required>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-3 text-center">
                        <button class="btn btn-primary">Select payment</button>
                    </div>
                </form>
            </div>
        </div>
        <br>
        </div>
    </c:if>
</c:if>
<c:if test="${not empty paymentEntity && sessionScope.user.role.name =='admin'}">
<div class="container">
    <br>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="logout"/>
        <div class=text-right>
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <h2>Payment information</h2>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>date</th>
            <th>user login</th>
        </tr>
        </thead>
        <tbody>
        <tr class="table-primary">
            <td><c:out value="${requestScope.payment.id}"/></td>
            <td><c:out value="${requestScope.payment.date}"/></td>
            <td><c:out value="${requestScope.payment.ticket.description}"/></td>
            <td><c:out value="${requestScope.payment.ticket.value}"/></td>
            <td><c:out value="${requestScope.payment.ticket.exposition.theme}"/></td>
            <td><c:out value="${requestScope.payment.user.login}"/></td>
        </tr>
        </tbody>
    </table>
    <br>
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_payment_by_id"/>
                <div class="container center_div">
                    <div class="form-group">
                        <label for="loginForm">Type payment id:</label>
                        <input id="loginForm" class="form-control" name="paymentId"
                               value="" required>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button type="submit" class="btn btn-primary">Find payment</button>
                </div>
            </form>
        </div>
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_all_payments"/>
                <input type="hidden" name="currentPage" value="1">
                <div class="container center_div">
                    <div class="form-group">
                        <label for="payments">Select payments per page:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="payments" name="limit">
                                <option value="3"selected>3</option>
                                <option value="6">6</option>
                                <option value="9">9</option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button class="btn btn-primary">Select payments</button>
                </div>
            </form>
        </div>
    </div>
    <br>
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_payment_by_user_id"/>
                <input type="hidden" name="currentPage" value="1">
                <div class="container center_div">
                    <div class="form-group">
                        <label for="pay">Select payments per page:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="pay" name="limit">
                                <option value="3"selected>3</option>
                                <option value="6">6</option>
                                <option value="9">9</option>
                            </select>
                        </div>
                        <label for="paym">Type user id:</label>
                        <input id="paym" class="form-control" name="userId"
                               value="" required>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button class="btn btn-primary">Select payment</button>
                </div>
            </form>
        </div>
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_payment_by_ticket_id"/>
                <input type="hidden" name="ticketId" value=""/>
                <div class="container center_div">
                    <div class="form-group">
                        <label for="paymss">Type ticket id:</label>
                        <input id="paymss" class="form-control" name="ticketId"
                               value="" required>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button class="btn btn-primary">Find payment</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_all_showrooms"/>
                <input type="hidden" name="currentPage" value="1">
                <div class="container center_div">
                    <div class="form-group">
                        <label for="show">Select showrooms per page:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="show" name="limit">
                                <option value="3"selected>3</option>
                                <option value="6">6</option>
                                <option value="9">9</option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button class="btn btn-primary">Select showrooms</button>
                </div>
            </form>
        </div>
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_all_expositions"/>
                <input type="hidden" name="currentPage" value="1">
                <div class="container center_div">
                    <div class="form-group">
                        <label for="recsss">Select expositions per page:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="recsss" name="limit">
                                <option value="3"selected>3</option>
                                <option value="6">6</option>
                                <option value="9">9</option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button class="btn btn-primary">Select expositions</button>
                </div>
            </form>
        </div>
    </div>
</div>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="forward_to_admin_page"/>
        <div class=text-center>
            <button type="submit" class="btn btn-primary">To admin page</button>
        </div>
    </form>
</c:if>
</body>
</html>
