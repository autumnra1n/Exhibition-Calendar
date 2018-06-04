<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="locale.jsp" />
<html lang="${language}">
<head>
    <title>Exposition</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="expositionList" value="${requestScope.expositions}" />
<c:set var="expositionEntity" value="${requestScope.exposition}" />
<c:set var="user" value="${sessionScope.user}"/>
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
<c:if test="${not empty requestScope.invalid_date}">
    <fmt:message key="invalid.date"/>
</c:if>
<c:if test="${not empty requestScope.invalid_time}">
    <fmt:message key="invalid.time"/>
</c:if>
<c:if test="${not empty requestScope.invalid_description}">
    <fmt:message key="invalid.description"/>
</c:if>
<c:if test="${not empty requestScope.invalid_theme_length}">
    <fmt:message key="invalid.theme.length"/>
</c:if>
<c:if test="${empty expositionList && empty expositionEntity && not empty sessionScope.user}">
    <form action="api">
        <input type="hidden" name="command" value="find_all_expositions">
        <input type="hidden" name="currentPage" value="1">
        <div class="form-group">
            <div class="container center_div">
                <label for="record">Select exhibitions per page:</label>
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
<c:if test="${not empty expositionList && empty expositionEntity}">
<div class="container">
    <br>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="logout"/>
        <div class=text-right>
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <h2><fmt:message key="app.exposition.table"/></h2>
    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="app.id"/></th>
            <th><fmt:message key="app.theme"/></th>
            <th><fmt:message key="app.date"/></th>
            <th><fmt:message key="app.start.time"/></th>
            <th><fmt:message key="app.showroom.name"/></th>
        </tr>
        </thead>
        <c:forEach items="${expositionList}" var="exposition">
            <tbody>
            <tr class="table-primary">
                <td><c:out value="${exposition.id}"/></td>
                <td><c:out value="${exposition.theme}"/></td>
                <td><c:out value="${exposition.dateStart}"/></td>
                <td><c:out value="${exposition.startTime}"/></td>
                <td><c:out value="${exposition.showroom.name}"/></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
        </div>
    <c:if test="${param.command == 'find_all_expositions_by_showroomId'}">
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${requestScope.currentPage != 1}">
                    <form action="api">
                        <input type="hidden" name="command" value="find_exposition_by_showroom_id"/>
                        <input type="hidden" name="showroomId" value="${requestScope.showroomId}"/>
                        <input type="hidden" name="limit" value="${requestScope.limit}"/>
                        <input type="hidden" name="currentPage" value="${requestScope.currentPage-1}"/>
                        <button type="submit" class="btn btn-primary"><fmt:message key="app.previous"/></button>
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
                                <input type="hidden" name="command" value="find_exposition_by_showroom_id"/>
                                <input type="hidden" name="showroomId" value="${requestScope.showroomId}"/>
                                <input type="hidden" name="limit" value="${requestScope.limit}"/>
                                <input type="hidden" name="currentPage" value="${i}"/>
                                <button type="submit" class="btn btn-default">${i}</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="find_exposition_by_showroom_id"/>
                        <input type="hidden" name="showroomId" value="${requestScope.showroomId}"/>
                        <input type="hidden" name="limit" value="${requestScope.limit}"/>
                        <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
                        <button type="submit" class="btn btn-primary"><fmt:message key="app.next"/></button>
                    </form>
                </c:if>
            </ul>
        </nav>
        </c:if>
        <c:if test="${param.command == 'find_all_expositions'  || param.command == 'verify_profile'}">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:if test="${requestScope.currentPage != 1}">
                        <form action="api">
                            <input type="hidden" name="command" value="find_all_expositions"/>
                            <input type="hidden" name="limit" value="${requestScope.limit}"/>
                            <input type="hidden" name="currentPage" value="${requestScope.currentPage-1}"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="app.previous"/></button>
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
                                    <input type="hidden" name="command" value="find_all_expositions"/>
                                    <input type="hidden" name="limit" value="${requestScope.limit}"/>
                                    <input type="hidden" name="currentPage" value="${i}"/>
                                    <button type="submit" class="btn btn-default">${i}</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="find_all_expositions"/>
                            <input type="hidden" name="limit" value="${requestScope.limit}"/>
                            <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="app.next"/></button>
                        </form>
                    </c:if>
                </ul>
            </nav>
        </c:if>
<div class="row justify-content-center">
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_exposition_by_id"/>
            <div class="form-group">
                <label for="expo"><fmt:message key="app.select.exhibitions"/></label>
                <div class="col-sm-4">
                    <select class="form-control" id="expo" name="expositionId">
                        <c:forEach items="${expositionList}" var="exposition">
                            <option value="${exposition.id}">${exposition.theme}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Select exhibition</button>
            </div>
        </form>
    </div>
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_all_showrooms"/>
            <input type="hidden" name="currentPage" value="1">
            <div class="container center_div">
                <div class="form-group">
                    <label for="records"><fmt:message key="app.select.showroom"/></label>
                    <div class="col-sm-4">
                        <select class="form-control" id="records" name="limit">
                            <option value="3" selected>3</option>
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
            <input type="hidden" name="command" value="find_payment_by_user_id"/>
            <input type="hidden" name="currentPage" value="1">
            <input type="hidden" name="userId" value="${sessionScope.user.id}"/>
            <div class="container center_div">
                <div class="form-group">
                    <label for="rec"><fmt:message key="app.select.my.payments"/></label>
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
<br>
<c:if test="${sessionScope.user.role.name == 'admin'}">
    <form action="api">
        <input type="hidden" name="command" value="forward_to_admin_page"/>
        <div class=text-center>
            <button type="submit" class="btn btn-primary"><fmt:message key="app.to.admin.page"/></button>
        </div>
    </form>
</c:if>
</c:if>
<c:if test="${empty expositionList && not empty expositionEntity}">
<div class="container">
    <br>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="logout"/>
        <div class=text-right>
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <h2>Exposition information</h2>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>theme</th>
            <th>date</th>
            <th>start time</th>
            <th>showroom name</th>
        </tr>
        </thead>
        <tbody>
            <tr class="table-primary">
                <td><c:out value="${requestScope.exposition.id}"/></td>
                <td><c:out value="${requestScope.exposition.theme}"/></td>
                <td><c:out value="${requestScope.exposition.dateStart}"/></td>
                <td><c:out value="${requestScope.exposition.startTime}"/></td>
                <td><c:out value="${requestScope.exposition.showroom.name}"/></td>
            </tr>
        </tbody>
    </table>
    <br>
    <h4 class="text-center">Exposition description</h4>
    <p class="text-center"><c:out value="${requestScope.exposition.description}"/></p>
    <br>
</div>
    <div class="row justify-content-center">
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_ticket_by_exposition_id"/>
            <input type="hidden" name="currentPage" value="1">
            <input type="hidden" name="expositionId" value="${requestScope.exposition.id}">
            <div class="form-group">
                <label for="tick">Select tickets per page</label>
                <div class="col-sm-4">
                <select class="form-control" id="tick" name="limit">
                    <option value="3" selected>3</option>
                    <option value="6">6</option>
                    <option value="9">9</option>
                </select>
            </div>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Tickets to current exposition</button>
            </div>
        </form>
    </div>
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_ticket_by_id"/>
            <input type="hidden" name="currentPage" value="1">
            <div class="form-group">
                <label for="loginForm">Type ticket id:</label>
                <input id="loginForm" class="form-control" name="ticketId"
                       value="" required>
            </div>
            <br>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Find ticket</button>
            </div>
        </form>
    </div>
    <br>
    </div>
    <div class="row justify-content-center">
    <div class="col-sm-3">
        <form action="api">
            <input type="hidden" name="command" value="find_all_tickets"/>
            <input type="hidden" name="currentPage" value="1">
            <div class="container center_div">
                <div class="form-group">
                    <label for="rs">Select tickets per page:</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="rs" name="limit">
                            <option value="3" selected>3</option>
                            <option value="6">6</option>
                            <option value="9">9</option>
                        </select>
                    </div>
                </div>
            </div>
            <br/>
            <div class="col-md-3 text-center">
                <button type="submit" class="btn btn-primary">Select all tickets</button>
            </div>
        </form>
    </div>
    <div class="col-sm-3">
    <form action="api">
        <input type="hidden" name="command" value="find_showroom_by_id"/>
        <input type="hidden" name="showroomId" value="${requestScope.exposition.showroom.id}"/>
        <div class="col-md-3 text-center">
            <button type="submit" class="btn btn-primary">Select current showroom</button>
        </div>
    </form>
    </div>
    </div>
    <br>
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
                                <option value="3" selected>3</option>
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
                        <label for="recs">Select expositions per page:</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="recs" name="limit">
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
