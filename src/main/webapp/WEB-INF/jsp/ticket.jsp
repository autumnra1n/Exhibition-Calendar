<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="locale.jsp" />
<html lang="${language}">
<head>
    <title>Ticket</title>
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
<c:if test="${not empty requestScope.no_tickets_left}">
    <fmt:message key="no.tickets.left"/>
</c:if>
<c:if test="${not empty requestScope.no_such_ticket}">
    <fmt:message key="no.such.ticket"/>
</c:if>
<c:if test="${not empty requestScope.no_such_tickets}">
    <fmt:message key="no.such.tickets"/>
</c:if>
<c:set var="ticketList" value="${requestScope.tickets}" scope="page" />
<c:set var="ticketEntity" value="${requestScope.ticket}" scope="page" />
<c:if test="${empty ticketList && empty ticketEntity && not empty sessionScope.user}">
    <form action="api">
        <input type="hidden" name="command" value="find_all_tickets">
        <input type="hidden" name="currentPage" value="1">
        <div class="form-group">
            <div class="container center_div">
                <label for="record">Select tickets per page:</label>
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
    <c:if test="${sessionScope.user.role.name == 'admin'}">
        <form action="api">
            <input type="hidden" name="command" value="forward_to_admin_page"/>
            <div class=text-center>
                <button type="submit" class="btn btn-primary">To admin page</button>
            </div>
        </form>
    </c:if>
</c:if>
<c:if test="${not empty ticketList}">
    <div class="container">
    <form action="api">
    <input type="hidden" name="command" value="logout"/>
    <div class=text-right>
        <button type="submit" class="btn btn-primary">Logout</button>
    </div>
    </form>
    <h2>Ticket table</h2>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>value</th>
            <th>amount</th>
            <th>exposition name</th>
        </tr>
        </thead>
        <c:forEach items="${ticketList}" var="ticket">
            <tbody>
            <tr class="table-primary">
                <td><c:out value="${ticket.id}"/></td>
                <td><c:out value="${ticket.value}"/></td>
                <td><c:out value="${ticket.amount}"/></td>
                <td><c:out value="${ticket.exposition.theme}"/></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    </div>
    <c:if test="${param.command == 'find_ticket_by_exposition_id'}">
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <c:if test="${requestScope.currentPage != 1}">
                <form action="api" method="post">
                    <input type="hidden" name="command" value="find_ticket_by_exposition_id"/>
                    <input type="hidden" name="expositionId" value="${requestScope.expositionId}"/>
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
                            <input type="hidden" name="command" value="find_ticket_by_exposition_id"/>
                            <input type="hidden" name="expositionId" value="${requestScope.expositionId}"/>
                            <input type="hidden" name="limit" value="${requestScope.limit}"/>
                            <input type="hidden" name="currentPage" value="${i}"/>
                            <button type="submit" class="btn btn-default">${i}</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                <form action="api" method="post">
                    <input type="hidden" name="command" value="find_ticket_by_exposition_id"/>
                    <input type="hidden" name="expositionId" value="${requestScope.expositionId}"/>
                    <input type="hidden" name="limit" value="${requestScope.limit}"/>
                    <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
                    <button type="submit" class="btn btn-primary">Next</button>
                </form>
            </c:if>
        </ul>
    </nav>
    </c:if>
    <c:if test="${param.command == 'find_all_tickets'}">
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${requestScope.currentPage != 1}">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="find_all_tickets"/>
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
                                <input type="hidden" name="command" value="find_all_tickets"/>
                                <input type="hidden" name="limit" value="${requestScope.limit}"/>
                                <input type="hidden" name="currentPage" value="${i}"/>
                                <button type="submit" class="btn btn-default">${i}</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="find_all_tickets"/>
                        <input type="hidden" name="limit" value="${requestScope.limit}"/>
                        <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
                        <button type="submit" class="btn btn-primary">Next</button>
                    </form>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <br>
<div class="row justify-content-center">
    <div class="col-sm-3">
        <form action="api" method="post">
            <input type="hidden" name="command" value="insert_payment"/>
            <input type="hidden" name="userId" value=${sessionScope.user.id}>
            <div class="container center_div">
                <div class="form-group">
                    <label for="r">Select ticket value to buy:</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="r" name="ticketId">
                            <c:forEach items="${ticketList}" var="ticket">
                                <option value="${ticket.id}">${ticket.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button class="btn btn-primary">Buy ticket</button>
            </div>
        </form>
    </div>
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_ticket_by_id"/>
            <input type="hidden" name="currentPage" value="1">
            <div class="form-group">
                <label for="rsss">Select ticket id:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="rsss" name="ticketId">
                        <c:forEach items="${ticketList}" var="ticket">
                            <option value="${ticket.id}">${ticket.id}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Find ticket</button>
            </div>
        </form>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_all_tickets"/>
                <input type="hidden" name="currentPage" value="1">
                <div class="col-sm-4">
                    <label for="rws">Select tickets per page:</label>
                    <select class="form-control" id="rws" name="limit">
                        <option value="3"selected>3</option>
                        <option value="6">6</option>
                        <option value="9">9</option>
                    </select>
                </div>
                <div class="col-md-3 text-center">
                    <button type="submit" class="btn btn-primary">Select all tickets</button>
                </div>
            </form>
        </div>
</div>
</div>
<div class="row justify-content-center">
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_all_showrooms"/>
                <input type="hidden" name="currentPage" value="1">
                <div class="container center_div">
                    <div class="form-group">
                        <label for="rw">Select showrooms per page:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="rw" name="limit">
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
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_payment_by_user_id"/>
                <input type="hidden" name="currentPage" value="1">
                <input type="hidden" name="userId" value="${sessionScope.user.id}"/>
                <div class="container center_div">
                    <div class="form-group">
                        <label for="rec">Select my payments per page:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="rec" name="limit">
                                <option value="3"selected>3</option>
                                <option value="6">6</option>
                                <option value="9">9</option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button class="btn btn-primary">Select my payments</button>
                </div>
            </form>
        </div>
    </div>
</div>
    <c:if test="${sessionScope.user.role.name == 'admin'}">
        <form action="api">
            <input type="hidden" name="command" value="forward_to_admin_page"/>
            <div class=text-center>
                <button type="submit" class="btn btn-primary">To admin page</button>
            </div>
        </form>
    </c:if>
</c:if>
<c:if test="${not empty ticketEntity}">
<div class="container">
    <br>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="logout"/>
        <div class=text-right>
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <h2>Ticket information</h2>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>value</th>
            <th>amount</th>
            <th>exposition name</th>
        </tr>
        </thead>
        <tbody>
        <tr class="table-primary">
            <td><c:out value="${requestScope.ticket.id}"/></td>
            <td><c:out value="${requestScope.ticket.value}"/></td>
            <td><c:out value="${requestScope.ticket.amount}"/></td>
            <td><c:out value="${requestScope.ticket.exposition.theme}"/></td>
        </tr>
        </tbody>
    </table>
    <br>
    <h4 class="text-center">Exposition description</h4>
    <p class="text-center"><c:out value="${requestScope.ticket.description}"/></p>
    <br>
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <form action="api" method="post">
                <input type="hidden" name="command" value="find_all_showrooms"/>
                <input type="hidden" name="userLogin" value=${sessionScope.user.login}>
                <input type="hidden" name="userId" value=${sessionScope.user.id}>
                <input type="hidden" name="ticketId" value=${sessionScope.ticket.id}>
                <div class="col-md-3 text-center">
                    <button class="btn btn-primary">Buy current ticket</button>
                </div>
            </form>
        </div>
        <div class="col-sm-3">
            <form action="api">
                <input type="hidden" name="command" value="find_ticket_by_id"/>
                <input type="hidden" name="currentPage" value="1">
                <div class="form-group">
                    <label for="l">Type ticket id:</label>
                    <input id="l" class="form-control" name="ticketId"
                           value="" required>
                </div>
                <br>
                <div class="col-md-3 text-center">
                    <button type="submit" class="btn btn-primary">Find ticket</button>
                </div>
            </form>
        </div>
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="find_all_tickets"/>
                    <input type="hidden" name="currentPage" value="1">
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="rsd">Select tickets per page:</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="rsd" name="limit">
                                    <option value="3"selected>3</option>
                                    <option value="6">6</option>
                                    <option value="9">9</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-3 text-center">
                        <button type="submit" class="btn btn-primary">Select all tickets</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br>
    <br>
    <div class="row justify-content-center">
        <div class="row justify-content-center">
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="find_all_showrooms"/>
                    <input type="hidden" name="currentPage" value="1">
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="rs">Select showrooms per page:</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="rs" name="limit">
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
            <div class="col-sm-3">
                <form action="api">
                    <input type="hidden" name="command" value="find_payment_by_user_id"/>
                    <input type="hidden" name="currentPage" value="1">
                    <input type="hidden" name="userId" value="${sessionScope.user.id}"/>
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="recd">Select my payments per page:</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="recd" name="limit">
                                    <option value="3"selected>3</option>
                                    <option value="6">6</option>
                                    <option value="9">9</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-3 text-center">
                        <button class="btn btn-primary">Select my payments</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.user.role.name == 'admin'}">
    <form action="api">
        <input type="hidden" name="command" value="forward_to_admin_page"/>
        <div class=text-center>
            <button type="submit" class="btn btn-primary">To admin page</button>
        </div>
    </form>
    </c:if>
</c:if>
</body>
</html>
