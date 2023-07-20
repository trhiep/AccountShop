<%-- 
    Document   : depositRequestManager
    Created on : Jun 27, 2023, 5:28:04 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Balance Manager</title>
        <link rel="stylesheet" href="css/managerHeader.css"/>
        <link rel="stylesheet" href="css/balanceManager.css"/>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <%@ include file="managerHeader.jsp" %>
        <div class = "DepositRequest">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">REQUEST DEPOSIT LIST</th>
                    </tr>
                </thead>
            </table>
            <c:if test="${empty requestList}">
                <div class ="text-center">
                    <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                    <h5 style = "margin-top: 20px">REQUEST DEPOSIT LIST IS EMPTY NOW</h5>
                </div>
            </c:if>

            <c:if test="${not empty requestList}">
                <div>
                    <table class="table table-striped">
                        <thead>

                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Date</th>
                                <th scope="col">Accept</th>
                                <th scope="col">Deny</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestList}" var="request">
                            <form action="depositRequestManager" method="POST">
                                <tr>
                                    <th scope="row">${request.id}</th>
                                <input type="hidden" name="id" value="${request.id}"/>
                                <td>
                                    ${request.username}
                                    <input type="hidden" name="username" value="${request.username}"/>
                                </td>
                                <td>
                                    ${request.amount}
                                    <input type="hidden" name="amount" value="${request.amount}"/>
                                </td>
                                <td>
                                    ${request.date}
                                    <input type="hidden" name="requestDate" value="${request.date}"/>
                                </td>
                                <td>
                                    <input class="btn btn-success" type="submit" value="Accept" name = "btAction"/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="reason" value="" placeholder="Deny reason"/>
                                </td>
                                <td>
                                    <input class="btn btn-danger" type="submit" value="Deny" name = "btAction"/>
                                </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>   
        </div>

        <div class = "DepositHistory">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">DEPOSIT HISTORY LIST</th>
                    </tr>
                </thead>
            </table>
            <c:if test="${empty depositHistoryList}">
                <div class ="text-center">
                    <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                    <h5 style = "margin-top: 20px">DEPOSIT HISTORY IS EMPTY NOW</h5>
                </div>
            </c:if>

            <c:if test="${not empty depositHistoryList}">
                <div>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Request date</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action Date</th>
                                <th scope="col">Action By</th>
                                <th scope="col">Reason</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${depositHistoryList}" var="depoHistory">
                                <tr>
                                    <th scope="row">${depoHistory.requestID}</th>
                                    <td>${depoHistory.username}</td>
                                    <td>${depoHistory.amount}</td>
                                    <td>${depoHistory.requestDate}</td>
                                    <td>${depoHistory.status?"Accept":"Deny"}</td>
                                    <td>${depoHistory.actionDate}</td>
                                    <td>${depoHistory.actionBy}</td>
                                    <td>${depoHistory.reason}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>   
        </div>
    </body>
</html>
