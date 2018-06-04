<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="locale.jsp" />
<html lang="${language}">
<head>
    <title>Admin</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
        <c:if test="${not empty requestScope.invalid_limit}">
            <fmt:message key="invalid.limit"/>
        </c:if>
        <c:if test="${not empty requestScope.invalid_limit}">
            <fmt:message key="invalid.limit"/>
        </c:if>
        <c:if test="${not empty requestScope.invalid_limit}">
            <fmt:message key="invalid.limit"/>
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
    <c:if test="${not empty requestScope.invalid_id_type}">
        <fmt:message key="invalid.id.type"/>
    </c:if>
    <c:if test="${not empty requestScope.invalid_id_length}">
        <fmt:message key="invalid.id.length"/>
    </c:if>
    <c:if test="${not empty requestScope.invalid_showroom_location}">
        <fmt:message key="invalid.showroom.location"/>
    </c:if>
    <c:if test="${not empty requestScope.invalid_showroom_name}">
        <fmt:message key="invalid.showroom.name"/>
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
        <c:if test="${not empty requestScope.no_such_payments}">
            <fmt:message key="no.such.payments"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_payment}">
            <fmt:message key="no.such.payment"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_role}">
            <fmt:message key="no.such.role"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_showroom}">
            <fmt:message key="no.such.showroom"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_showrooms}">
            <fmt:message key="no.such.showrooms"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_ticket}">
            <fmt:message key="no.such.ticket"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_tickets}">
            <fmt:message key="no.such.tickets"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_user}">
            <fmt:message key="no.such.user"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_users}">
            <fmt:message key="no.such.users"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_expositions}">
            <fmt:message key="no.such.expositions"/>
        </c:if>
        <c:if test="${not empty requestScope.no_such_exposition}">
            <fmt:message key="no.such.exposition"/>
        </c:if>
<c:if test="${sessionScope.user.role.name == 'admin'}">
    <br>
    <br>
    <form action="api">
        <input type="hidden" name="command" value="logout"/>
        <div class=text-right>
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <br>
    <br>
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <h4>Insert exposition</h4>
            <br>
            <div class="form-group col-md-6">
                <form action="api" method="post">
                    <input type="hidden" name="command" value="insert_exposition"/>
                    <label for="theme">Type theme:</label>
                    <input id="theme" class="form-control" name="theme"
                       value="" required>
                    <label for="date">Type date</label>
                    <input id="date" class="form-control" name="date"
                       value="" required>
                    <label for="time">Type time</label>
                    <input id="time" class="form-control" name="time"
                       value="" required>
                    <label for="expositionDescription">Type description</label>
                    <input id="expositionDescription" class="form-control" name="expositionDescription"
                       value="" required>
                    <label for="showroomId">Type showroom id</label>
                    <input id="showroomId" class="form-control" name="showroomId"
                       value="" required>
                    <br>
                    <button type="submit" class="btn btn-primary">Insert exposition</button>
                </form>
            </div>
        </div>
        <div class="col-sm-3">
            <h4>Delete exposition</h4>
            <br>
            <div class="form-group col-md-6">
                <form action="api" method="post">
                    <input type="hidden" name="command" value="delete_exposition"/>
                    <label for="deleteexpo">Type id:</label>
                    <input id="deleteexpo" class="form-control" name="expositionId"
                           value="" required>
                    <br>
                    <div class="col-md-3 text-center">
                        <button type="submit" class="btn btn-primary">Delete exposition</button>
                    </div>
                </form>
                <form action="api">
                    <input type="hidden" name="command" value="find_all_expositions"/>
                    <input type="hidden" name="currentPage" value="1">
                    <div class="container center_div">
                        <div class="form-group">
                            <label for="records">Select expositions per page:</label>
                            <div class="col-sm-3">
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
                        <button class="btn btn-primary">Select expositions</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-3">
            <h4>Update exposition</h4>
            <br>
            <div class="form-group col-md-6">
                <form action="api" method="post">
                    <input type="hidden" name="command" value="update_exposition"/>
                    <label for="expositionId">Type id:</label>
                    <input id="expositionId" class="form-control" name="expositionId"
                           value="" required>
                    <label for="etheme">Type theme:</label>
                    <input id="etheme" class="form-control" name="theme"
                           value="" required>
                    <label for="edate">Type date</label>
                    <input id="edate" class="form-control" name="date"
                           value="" required>
                    <label for="etime">Type time</label>
                    <input id="etime" class="form-control" name="time"
                           value="" required>
                    <label for="eexpositionDescription">Type description</label>
                    <input id="eexpositionDescription" class="form-control" name="expositionDescription"
                           value="" required>
                    <label for="eshowroomId">Type showroom id</label>
                    <input id="eshowroomId" class="form-control" name="showroomId"
                           value="" required>
                    <br>
                    <button type="submit" class="btn btn-primary">Update exposition</button>
                </form>
            </div>
        </div>
        <br>
        <br>
        <div class="row justify-content-center">
            <div class="col-sm-3">
                <h4>Insert payment</h4>
                <br>
                <div class="form-group col-md-6">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="insert_payment"/>
                        <label for="userId">Type user id</label>
                        <input id="userId" class="form-control" name="userId"
                               value="" required>
                        <label for="tickId">Type ticket id</label>
                        <input id="tickId" class="form-control" name="ticketId"
                               value="" required>
                        <br>
                        <button type="submit" class="btn btn-primary">Insert payment</button>
                    </form>
                </div>
            </div>
            <div class="col-sm-3">
                <h4>Delete payment</h4>
                <br>
                <div class="form-group col-md-6">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="delete_payment"/>
                        <label for="deletepayment">Type id:</label>
                        <input id="deletepayment" class="form-control" name="paymentId"
                               value="" required>
                        <br>
                        <div class="col-md-3 text-center">
                            <button type="submit" class="btn btn-primary">Delete payment</button>
                        </div>
                    </form>
                    <form action="api">
                        <input type="hidden" name="command" value="find_all_payments"/>
                        <input type="hidden" name="currentPage" value="1">
                        <div class="container center_div">
                            <div class="form-group">
                                <label for="record">Select payments per page:</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="record" name="limit">
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
                <div class="col-sm-3">
                    <h4>Update payment</h4>
                    <br>
                    <div class="form-group col-md-6">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="update_payment"/>
                            <label for="dpaymentId">Type payment id</label>
                            <input id="dpaymentId" class="form-control" name="paymentId"
                                   value="" required>
                            <label for="duserId">Type user id</label>
                            <input id="duserId" class="form-control" name="userId"
                                   value="" required>
                            <label for="dtickId">Type ticket id</label>
                            <input id="dtickId" class="form-control" name="ticketId"
                                   value="" required>
                            <br>
                            <button type="submit" class="btn btn-primary">Update payment</button>
                        </form>
                    </div>
                </div>
        <br>
        <br>
        </div>
        <div class="row justify-content-center">
            <div class="col-sm-3">
                <h4>Insert role</h4>
                <br>
                <div class="form-group col-md-6">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="insert_role"/>
                        <label for="roleName">Type name:</label>
                        <input id="roleName" class="form-control" name="roleName"
                               value="" required>
                        <br>
                        <button type="submit" class="btn btn-primary">Insert role</button>
                    </form>
                </div>
            </div>
            <div class="col-sm-3">
                <h4>Delete Role</h4>
                <br>
                <div class="form-group col-md-6">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="delete_role"/>
                        <label for="deleterole">Type id:</label>
                        <input id="deleterole" class="form-control" name="roleId"
                               value="" required>
                        <br>
                        <div class="col-md-3 text-center">
                            <button type="submit" class="btn btn-primary">Delete role</button>
                        </div>
                    </form>
                    <form action="api">
                        <input type="hidden" name="command" value="find_all_roles"/>
                        <input type="hidden" name="currentPage" value="1">
                        <div class="container center_div">
                            <div class="form-group">
                                <label for="rolerecords">Select roles per page:</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="rolerecords" name="limit">
                                        <option value="3"selected>3</option>
                                        <option value="6">6</option>
                                        <option value="9">9</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="col-md-3 text-center">
                            <button class="btn btn-primary">Select roles</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-3">
                <h4>Update role</h4>
                <br>
                <div class="form-group col-md-6">
                    <form action="api" method="post">
                        <input type="hidden" name="command" value="update_role"/>
                        <label for="roleId">Type id:</label>
                        <input id="roleId" class="form-control" name="roleId"
                               value="" required>
                        <label for="rname">Type name:</label>
                        <input id="rname" class="form-control" name="roleName"
                               value="" required>
                        <br>
                        <button type="submit" class="btn btn-primary">Update role</button>
                    </form>
                </div>
            </div>
            <br>
            <br>
            <div class="row justify-content-center">
                <div class="col-sm-3">
                    <h4>Insert showroom</h4>
                    <br>
                    <div class="form-group col-md-6">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="insert_showroom"/>
                            <label for="name">Type name:</label>
                            <input id="name" class="form-control" name="name"
                                   value="" required>
                            <label for="location">Type location</label>
                            <input id="location" class="form-control" name="location"
                                   value="" required>
                            <label for="showroomDescription">Type description</label>
                            <input id="showroomDescription" class="form-control" name="showroomDescription"
                                   value="" required>
                            <br>
                            <button type="submit" class="btn btn-primary">Insert showroom</button>
                        </form>
                    </div>
                </div>
                <div class="col-sm-3">
                    <h4>Delete showroom</h4>
                    <br>
                    <div class="form-group col-md-6">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="delete_showroom"/>
                            <label for="deleteshow">Type id:</label>
                            <input id="deleteshow" class="form-control" name="showroomId"
                                   value="" required>
                            <br>
                            <div class="col-md-3 text-center">
                                <button type="submit" class="btn btn-primary">Delete showroom</button>
                            </div>
                        </form>
                        <form action="api">
                            <input type="hidden" name="command" value="find_all_showrooms"/>
                            <input type="hidden" name="currentPage" value="1">
                            <div class="container center_div">
                                <div class="form-group">
                                    <label for="srecords">Select showrooms per page:</label>
                                    <div class="col-sm-3">
                                        <select class="form-control" id="srecords" name="limit">
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
                </div>
                <div class="col-sm-3">
                    <h4>Update showroom</h4>
                    <br>
                    <div class="form-group col-md-6">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="update_showroom"/>
                            <label for="sshowroomId">Type id:</label>
                            <input id="sshowroomId" class="form-control" name="showroomId"
                                   value="" required>
                            <input type="hidden" name="command" value="insert_showroom"/>
                            <label for="sname">Type name:</label>
                            <input id="sname" class="form-control" name="name"
                                   value="" required>
                            <label for="slocation">Type location</label>
                            <input id="slocation" class="form-control" name="location"
                                   value="" required>
                            <label for="sshowroomDescription">Type description</label>
                            <input id="sshowroomDescription" class="form-control" name="showroomDescription"
                                   value="" required>
                            <br>
                            <button type="submit" class="btn btn-primary">Update showroom</button>
                        </form>
                    </div>
                </div>
                <br>
                <br>
            </div>
            <div class="row justify-content-center">
                <div class="col-sm-3">
                    <h4>Insert ticket</h4>
                    <br>
                    <div class="form-group col-md-6">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="insert_ticket"/>
                            <label for="ticketDescription">Type description:</label>
                            <input id="ticketDescription" class="form-control" name="description"
                                   value="" required>
                            <label for="value">Type value</label>
                            <input id="value" class="form-control" name="value"
                                   value="" required>
                            <label for="amount">Type amount</label>
                            <input id="amount" class="form-control" name="amount"
                                   value="" required>
                            <label for="eexpositionId">Type exposition id</label>
                            <input id="eexpositionId" class="form-control" name="expositionId"
                                   value="" required>
                            <br>
                            <button type="submit" class="btn btn-primary">Insert ticket</button>
                        </form>
                    </div>
                </div>
                <div class="col-sm-3">
                    <h4>Delete ticket</h4>
                    <br>
                    <div class="form-group col-md-6">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="delete_ticket"/>
                            <label for="deletetick">Type id:</label>
                            <input id="deletetick" class="form-control" name="ticketId"
                                   value="" required>
                            <br>
                            <div class="col-md-3 text-center">
                                <button type="submit" class="btn btn-primary">Delete ticket</button>
                            </div>
                        </form>
                        <form action="api">
                            <input type="hidden" name="command" value="find_all_tickets"/>
                            <input type="hidden" name="currentPage" value="1">
                            <div class="container center_div">
                                <div class="form-group">
                                    <label for="srsecords">Select tickets per page:</label>
                                    <div class="col-sm-3">
                                        <select class="form-control" id="srsecords" name="limit">
                                            <option value="3"selected>3</option>
                                            <option value="6">6</option>
                                            <option value="9">9</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="col-md-3 text-center">
                                <button class="btn btn-primary">Select tickets</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-sm-3">
                    <h4>Update ticket</h4>
                    <br>
                    <div class="form-group col-md-6">
                        <form action="api" method="post">
                            <input type="hidden" name="command" value="update_ticket"/>
                            <label for="ticketId">Type exposition id</label>
                            <input id="ticketId" class="form-control" name="ticketId"
                                   value="" required>
                            <label for="tticketDescription">Type description:</label>
                            <input id="tticketDescription" class="form-control" name="description"
                                   value="" required>
                            <label for="tvalue">Type value</label>
                            <input id="tvalue" class="form-control" name="value"
                                   value="" required>
                            <label for="tamount">Type amount</label>
                            <input id="tamount" class="form-control" name="amount"
                                   value="" required>
                            <label for="teexpositionId">Type exposition id</label>
                            <input id="teexpositionId" class="form-control" name="expositionId"
                                   value="" required>
                            <br>
                            <button type="submit" class="btn btn-primary">Update ticket</button>
                        </form>
                    </div>
                </div>
                <br>
                <br>
                <div class="row justify-content-center">
                    <div class="col-sm-3">
                        <h4>Insert user</h4>
                        <br>
                        <div class="form-group col-md-6">
                            <form action="api" method="post">
                                <input type="hidden" name="command" value="insert_user"/>
                                <label for="login">Type login:</label>
                                <input id="login" class="form-control" name="login"
                                       value="" required>
                                <label for="password">Type password</label>
                                <input id="password" class="form-control" name="password"
                                       value="" required>
                                <label for="email">Type email</label>
                                <input id="email" class="form-control" name="email"
                                       value="" required>
                                <label for="firstName">Type first name</label>
                                <input id="firstName" class="form-control" name="firstName"
                                       value="" required>
                                <label for="lastName">Type last name</label>
                                <input id="lastName" class="form-control" name="lastName"
                                       value="" required>
                                <br>
                                <button type="submit" class="btn btn-primary">Insert user</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <h4>Delete user</h4>
                        <br>
                        <div class="form-group col-md-6">
                            <form action="api" method="post">
                                <input type="hidden" name="command" value="delete_user"/>
                                <label for="deleteuser">Type id:</label>
                                <input id="deleteuser" class="form-control" name="userId"
                                       value="" required>
                                <br>
                                <div class="col-md-3 text-center">
                                    <button type="submit" class="btn btn-primary">Delete user</button>
                                </div>
                            </form>
                            <form action="api">
                                <input type="hidden" name="command" value="find_all_users"/>
                                <input type="hidden" name="currentPage" value="1">
                                <div class="container center_div">
                                    <div class="form-group">
                                        <label for="srsecordss">Select users per page:</label>
                                        <div class="col-sm-3">
                                            <select class="form-control" id="srsecordss" name="limit">
                                                <option value="3"selected>3</option>
                                                <option value="6">6</option>
                                                <option value="9">9</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-3 text-center">
                                    <button class="btn btn-primary">Select users</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <h4>Update user</h4>
                        <br>
                        <div class="form-group col-md-6">
                            <form action="api" method="post">
                                <input type="hidden" name="command" value="update_user"/>
                                <label for="userIdd">Type id:</label>
                                <input id="userIdd" class="form-control" name="userId"
                                       value="" required>
                                <label for="ulogin">Type login:</label>
                                <input id="ulogin" class="form-control" name="login"
                                       value="" required>
                                <label for="upassword">Type password</label>
                                <input id="upassword" class="form-control" name="password"
                                       value="" required>
                                <label for="uemail">Type email</label>
                                <input id="uemail" class="form-control" name="email"
                                       value="" required>
                                <label for="ufirstName">Type first name</label>
                                <input id="ufirstName" class="form-control" name="firstName"
                                       value="" required>
                                <label for="ulastName">Type last name</label>
                                <input id="ulastName" class="form-control" name="lastName"
                                       value="" required>
                                <label for="roleIdd">Type role id:</label>
                                <input id="roleIdd" class="form-control" name="roleId"
                                       value="" required>
                                <br>
                                <button type="submit" class="btn btn-primary">Update user</button>
                            </form>
                        </div>
                    </div>
            </div>
    </c:if>
</div>
</body>
</html>
