<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : viewAccount
    Created on : Jun 30, 2023, 7:41:49 AM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Account</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <c:if test="${empty userAcc}">
            <%@ include file="login.jsp"%>
        </c:if>

        <c:if test="${not empty userAcc}">
            <%@ include file="head.jsp"%>

            <div class="row container-fluid" style="margin-top: 50px; margin-bottom: 30px">
                <div class="col-md-1"></div>
                <div class="col-md-9"><h3>Hello ${userAcc.fullname}</h3></div>
                <div class="col-md-1"><a href="logout" class="btn btn-danger">Logout</a></div>
                <div class="col-md-1"></div>
            </div>

            <c:if test="${action == 'Information'}">
                <div class="row container-fluid" style="margin-top: 20px">
                    <div class="col-md-1"></div>
                    <div class="col-md-2">
                        <div class="list-group">
                            <form action="viewAccount" method="POST">
                                <input class="list-group-item list-group-item-action active" type="submit" value="Information" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Security" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Balance" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Ordered" name="btAction" />
                            </form>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-7" style="background-color: #c4c8cb; border-radius: 35px; padding: 30px">
                        <div class="text-center" style="margin-top: 30px">
                            <h2>CHANGE INFORMATION</h2>
                        </div>
                        <div>
                            <table class="table table-borderless">
                                <tbody>
                                <form action="updateUser" method="POST">
                                    <tr><th scope="row">Username</th></tr>
                                    <tr>
                                        <td>
                                            <input class="form-control" type="text" name="" value="${userAcc.username}" disabled />
                                            <input type="hidden" name="username" value="${userAcc.username}" />
                                        </td>
                                    </tr>
                                    <tr><th scope="row">Full name</th></tr>
                                    <tr>
                                        <td><input class="form-control" type="text" name="fullname" value="${userAcc.fullname}" /></td>
                                    </tr>
                                    <tr><th scope="row">Email</th></tr>
                                    <tr>
                                        <td><input class="form-control" type="text" name="email" value="${userAcc.email}" /></td>
                                    </tr>
                                    <tr>
                                        <td class="text-center">
                                            <input type="hidden" name="password" value="${userAcc.password}" />
                                            <input type="hidden" name="oldEmail" value="${userAcc.email}" />
                                            <input type="hidden" name="role" value="${userAcc.role}" />
                                            <input type="hidden" name="isVerified" value="${userAcc.isVerified?"Yes":"No"}" />
                                            <input class="btn btn-warning align-content-center" type="submit" value="Update" name = "btAction"/>
                                        </td>
                                    </tr>
                                </form>
                                </tbody>
                            </table>
                        </div>
                        <c:if test="${userAcc.isIsVerified() == false}">
                            <div class="alert alert-warning text-center" role="alert">
                                <h5>WARNING: YOU HAVE NOT VERIFIED YOU EMAIL</h5>
                                Why you need to verify email?<br>
                                - Recover forgotten password<br>
                                - The purchased account will be sent to your email <br>
                                <br><a class="btn btn-primary" href="emailSignupVerification">VERIFY NOW</a>
                            </div>
                        </c:if>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </c:if>
            <c:if test="${action == 'Security'}">
                <div class="row container-fluid" style="margin-top: 20px">
                    <div class="col-md-1"></div>
                    <div class="col-md-2">
                        <div class="list-group">
                            <form action="viewAccount" method="POST">
                                <input class="list-group-item list-group-item-action" type="submit" value="Information" name="btAction" />
                                <input class="list-group-item list-group-item-action active" type="submit" value="Security" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Balance" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Ordered" name="btAction" />
                            </form>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-7" style="background-color: #c4c8cb; border-radius: 35px; padding: 30px">
                        <div class="text-center" style="margin-top: 30px">
                            <h2>CHANGE PASSWORD</h2>
                        </div>

                        <div>
                            <table class="table table-borderless">
                                <tbody>
                                <form action="changePassword" method="POST">
                                    <tr><th scope="row">Old password</th></tr>
                                    <tr>
                                        <td>
                                            <input class="form-control" type="password" name="oldPassword" value="" required=""/>
                                            <c:if test="${not empty oldPassErr}">
                                                <label style="color: red; font-weight: bold">${oldPassErr}</label>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr><th scope="row">New password</th></tr>
                                    <tr>
                                        <td>
                                            <input class="form-control" type="password" name="newPassword" value="" required/>
                                        </td>
                                    </tr>
                                    <tr><th scope="row">Confirm new password</th></tr>
                                    <tr>
                                        <td>
                                            <input class="form-control" type="password" name="confirmPassword" value="" required/>
                                            <c:if test="${not empty confirmPassErr}">
                                                <label style="color: red; font-weight: bold">${confirmPassErr}</label>
                                            </c:if>

                                        </td>
                                    </tr>

                                    <c:if test="${not empty successMessage}">
                                        <tr>
                                            <td><label style="color: green; font-weight: bold">${successMessage}</label></td>
                                        </tr>
                                    </c:if>

                                    <tr>
                                        <td class="text-center">
                                            <input type="hidden" name="username" value="${userAcc.username}" />
                                            <input class="form-control" type="hidden" name="fullname" value="${userAcc.fullname}" />
                                            <input class="form-control" type="hidden" name="email" value="${userAcc.email}" />
                                            <input type="hidden" name="role" value="User" />
                                            <input class="btn btn-warning align-content-center" type="submit" value="Update" name = "btAction"/>
                                        </td>
                                    </tr>
                                </form>
                                </tbody>
                            </table>
                        </div>  
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </c:if>
            <c:if test="${action == 'Balance'}">
                <div class="row container-fluid" style="margin-top: 20px; margin-bottom: 40px">
                    <div class="col-md-1"></div>
                    <div class="col-md-2">
                        <div class="list-group">
                            <form action="viewAccount" method="POST">
                                <input class="list-group-item list-group-item-action" type="submit" value="Information" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Security" name="btAction" />
                                <input class="list-group-item list-group-item-action active" type="submit" value="Balance" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Ordered" name="btAction" />
                            </form>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-7" style="background-color: #c4c8cb; border-radius: 35px; padding: 30px">
                        <div class="text-center" style="margin-top: 30px">
                            <h2>BALANCE HISTORY</h2>
                        </div>
                        <div>
                            <c:if test="${empty balanceHistoryList}">
                                <div class ="text-center">
                                    <img src="img/emptyTask.png" width="200" height="200" alt="emptyTask"/>
                                    <h5 style = "margin-top: 20px">BALANCE HISTORY IS EMPTY NOW</h5>
                                </div>
                            </c:if>
                            <c:if test="${not empty balanceHistoryList}">
                                <div>
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col">No.</th>
                                                <th scope="col">Date</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Amount</th>
                                                <th scope="col">Reason</th>
                                                <th scope="col">New Balance</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${balanceHistoryList}" var="balanceHistory"  varStatus = "counter">
                                            <form action="#" method="POST">
                                                <tr>
                                                    <th scope="row">${counter.count}</th>
                                                    <td>
                                                        <input class="form-control" type="text" name="date" value="${balanceHistory.date}" disabled/>
                                                    </td>
                                                    <td>
                                                        <c:if test="${balanceHistory.status == true}">
                                                            <input style="color: green" class="form-control" type="text" name="status" value="${balanceHistory.status?"Up":"Down"}" disabled/>
                                                        </c:if>
                                                        <c:if test="${balanceHistory.status == false}">
                                                            <input style="color: red" class="form-control" type="text" name="status" value="${balanceHistory.status?"Up":"Down"}" disabled/>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${balanceHistory.status == false}">
                                                            <input style="color:red" class="form-control" type="text" name="amount" value="-${balanceHistory.amount}" disabled/>
                                                        </c:if>
                                                        <c:if test="${balanceHistory.status == true}">
                                                            <input style="color:green" class="form-control" type="text" name="amount" value="+${balanceHistory.amount}" disabled/>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <input class="form-control" type="text" name="reason" value="${balanceHistory.reason}" disabled/>
                                                    </td>

                                                    <td>
                                                        <input class="form-control" type="text" name="newBalance" value="${balanceHistory.newBalance}" disabled/>
                                                    </td>

                                                </tr>
                                            </form>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                        </div>

                    </div>
                    <div class="col-md-1"></div>
                </div>
            </c:if>
            <c:if test="${action == 'Ordered'}">
                <div class="row container-fluid" style="margin-top: 20px; margin-bottom: 40px">
                    <div class="col-md-1"></div>
                    <div class="col-md-2">
                        <div class="list-group">
                            <form action="viewAccount" method="POST">
                                <input class="list-group-item list-group-item-action" type="submit" value="Information" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Security" name="btAction" />
                                <input class="list-group-item list-group-item-action" type="submit" value="Balance" name="btAction" />
                                <input class="list-group-item list-group-item-action active" type="submit" value="Ordered" name="btAction" />
                            </form>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-7" style="background-color: #c4c8cb; border-radius: 35px; padding: 30px">
                        <div class="text-center" style="margin-top: 30px">
                            <h2>ORDERED ACCOUNT</h2>
                        </div>
                        <div>
                            <c:if test="${empty orderList}">
                                <div class ="text-center">
                                    <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                                    <h5 style = "margin-top: 20px">ORDERED LIST IS EMPTY NOW</h5>
                                </div>
                            </c:if>

                            <c:if test="${not empty orderList}">
                                <div>
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col">Acc ID</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Password</th>
                                                <th scope="col">Expire Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${orderList}" var="account"  varStatus = "counter">
                                            <form action="updateAccount" method="POST">
                                                <tr>
                                                    <td>
                                                        <input class="form-control" type="text" name="accountID" value="${account.accountID}" disabled/>
                                                    </td>
                                                    <td>
                                                        <c:forEach var="product" items="${productList}">
                                                            <c:if test="${product.productID == account.productID}">
                                                                <a class="form-control" style="text-decoration: none" href="viewDetail?productID=${product.productID}">${product.title}</a>
                                                            </c:if>

                                                        </c:forEach>
                                                    </td>
                                                    <td>
                                                        <input class="form-control" type="text" name="email" value="${account.email}" disabled/>
                                                    </td>
                                                    <td><input class="form-control" type="text" name="password" value="${account.password}" disabled/></td>
                                                    <td><input class="form-control" type="text" name="dueDate" value="${account.dueDate}" disabled/></td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </c:if>
        </c:if>
    </body>
</html>
