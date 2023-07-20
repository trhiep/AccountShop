<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : deposit
    Created on : Jun 28, 2023, 10:22:47 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deposit</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/deposit.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <c:if test="${empty Users}">
            <%@ include file="login.jsp"%>
        </c:if>

        <c:if test="${not empty Users}">
            <%@ include file="head.jsp"%>

            <div class="row container-fluid">
                <div class="col-md-12">
                    <h3 class="balanceDisplay"><a>YOUR BALANCE: ${userBalance.balance}vnđ</a></h3>
                </div>
            </div>

            <div class="row container-fluid">

                <div class="col-md-7">
                    <div style="margin-left: 100px;margin-top: 35px;">
                        <h3 style="font-weight: bolder;margin-bottom: 10px">DEPOSIT INSTRUCTIONS</h3>
                        <p style="font-size: 24px">
                            Step 1: Select the amount you want to deposit into your account from the form below.<br>
                            Step 2: Use any banking app to scan the QR code next to it and make a payment with the message: <a style="color: red; font-weight: bold; font-size: 28px">DEPOSIT ${Users.username}</a>.<br>
                            Step 3: Check confirm the payment and click the <a style="color: white; background-color: rgb(13, 110, 253); padding: 7px; border-radius: 6px">SEND REQUEST</a> button.<br>
                            Step 4: Wait for transaction confirmation by admin. Confirmation will take less than 30 minutes.<br>
                        </p>
                    </div>

                    <div style="margin-left: 100px;margin-top: 35px;">
                        <form method="POST" action="deposit">
                            <h3 style="font-weight: bolder;margin-bottom: 10px">DEPOSIT REQUEST FORM</h3>
                            <label class="my-1 mr-2" style="font-size: 24px" for="inlineFormCustomSelectPref">Amount of money</label>
                            <select name="amount" style="font-size: 24px; margin-left: 20px" class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                                <option value="50000">50.000</option>
                                <option value="100000">100.000</option>
                                <option value="200000">200.000</option>
                                <option value="500000">500.000</option>
                                <option value="1000000">1.000.000</option>
                                <option value="5000000">5.000.000</option>
                            </select>

                            <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                <input style="font-size: 200%" type="checkbox" class="custom-control-input" id="customControlInline" required>
                                <label class="custom-control-label" style="font-size: 24px" for="customControlInline">I have completed the payment.</label>
                            </div>
                            <button style="font-size: 24px" type="submit" class="btn btn-primary my-1">SEND REQUEST</button>
                        </form>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="text-center" style = "margin-top: 65px">
                        <img src="img/bank.jpg" width="430" height="572.5" alt="bank"/>
                    </div>
                </div>
            </div>

            <div class="row container-fluid" style="margin-top: 40px">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col" style="font-size: 24px">REQUESTING DEPOSIT LIST</th>
                            </tr>
                        </thead>
                    </table>
                    <c:if test="${empty requestingList}">
                        <div class ="text-center">
                            <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                            <h5 style = "margin-top: 20px">YOU DO NOT HAVE ANY REQUEST NOW</h5>
                        </div>
                    </c:if>
                    <c:if test="${not empty requestingList}">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Request ID</th>
                                    <th scope="col">Amount</th>
                                    <th scope="col">Request date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestingList}" var="request" varStatus = "counter">
                                <form action="deposit" method="POST">
                                    <tr>
                                        <th scope="row">${counter.count}</th>
                                        <td>${request.id}</td>
                                        <td>${request.amount}</td>
                                        <td>${request.date}</td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
                <div class="col-md-1"></div>
            </div>                

            <div class="row container-fluid" style="margin-top: 40px; margin-bottom: 40px">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col" style="font-size: 24px">COMPLETED REQUEST DEPOSIT HISTORY</th>
                            </tr>
                        </thead>
                    </table>
                    <c:if test="${empty depositHistoryList}">
                        <div class ="text-center">
                            <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                            <h5 style = "margin-top: 20px">YOU HAVE NOT CREATED ANY PAYMENT REQUEST</h5>
                        </div>
                    </c:if>

                    <c:if test="${not empty depositHistoryList}">
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
                                        <c:if test="${depoHistory.status == true}">
                                            <td style="color: green">Accepted</td>
                                        </c:if>
                                        <c:if test="${depoHistory.status == false}">
                                            <td style="color: red">Denied</td>
                                        </c:if>
                                        <td>${depoHistory.actionDate}</td>
                                        <td>${depoHistory.actionBy}</td>
                                        <td>${depoHistory.reason}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
                <div class="col-md-1"></div>
            </div>
        </c:if>
    </body>
</html>
