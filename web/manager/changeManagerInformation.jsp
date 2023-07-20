<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : changeManagerInformation
    Created on : Jul 19, 2023, 9:55:15 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Manager Information</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/managerHeader.css"/>
        <link rel="stylesheet" href="css/usersManager.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        
        <%@ include file="managerHeader.jsp" %>

        <c:if test="${action == 'Information'}">
            <div class="row container-fluid" style="margin-top: 20px">
                <div class="col-md-1"></div>
                <div class="col-md-2">
                    <div class="list-group">
                        <form action="changeManagerInformation" method="POST">
                            <input class="list-group-item list-group-item-action active" type="submit" value="Information" name="btAction" />
                            <input class="list-group-item list-group-item-action" type="submit" value="Security" name="btAction" />
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
                            <form action="updateManagerInformation" method="POST">
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
                        <form action="changeManagerInformation" method="POST">
                            <input class="list-group-item list-group-item-action" type="submit" value="Information" name="btAction" />
                            <input class="list-group-item list-group-item-action active" type="submit" value="Security" name="btAction" />
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
                            <form action="updateManagerPassword" method="POST">
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
    </body>
</html>
