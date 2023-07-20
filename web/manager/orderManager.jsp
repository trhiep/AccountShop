<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : orderManager
    Created on : Jun 25, 2023, 1:42:12 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All orders</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/managerHeader.css"/>
        <link rel="stylesheet" href="css/usersManager.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <%@ include file="managerHeader.jsp" %>

        <c:if test="${empty ordersHistoryList}">
            <div>
                Empty List!
            </div>
        </c:if>

        <c:if test="${not empty ordersHistoryList}">
            <div>
                <div class="text-center">
                    <h3 style="font-size: 30px; font-weight: bold">ALL ORDERS</h3>
                </div>
                <h4 class="text-center" style="font-weight: bold; font-size: 24px">Total: <span style="color: red">${totalOrdered}</span> completed orders.</h4>

                <table class="table table-hover">
                    <thead>

                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Order ID</th>
                            <th scope="col">Username</th>
                            <th scope="col">Account ID</th>
                            <th scope="col">Product ID</th>
                            <th scope="col">Email</th>
                            <th scope="col">Password</th>
                            <th scope="col">Expired</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ordersHistoryList}" var="order"  varStatus = "counter">
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td><input class="form-control" type="text" name="password" value="${order.orderID}" disabled/></td>
                                <td><input class="form-control" type="text" name="fullname" value="${order.username}" disabled/></td>
                                <td><input class="form-control" type="text" name="email" value="${order.accountID}" disabled/></td>
                                <td><input class="form-control" type="text" name="email" value="${order.productID}" disabled/></td>
                                <td><input class="form-control" type="text" name="email" value="${order.email}" disabled/></td>
                                <td><input class="form-control" type="text" name="email" value="${order.password}" disabled/></td>
                                <td><input class="form-control" type="text" name="email" value="${order.dueDate}" disabled/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination pagination-lg justify-content-center" style="
                            background-color: white;
                            margin: 30px 0px">
                            <c:forEach var="p" begin="1" end="${endPage}">
                                <li class="page-item"><a class="page-link ${page == p?"active":""}" href="orderManager?page=${p}">${p}</a></li>
                                </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </c:if>
    </body>
</html>
