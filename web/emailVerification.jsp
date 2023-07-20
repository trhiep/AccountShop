<%-- 
    Document   : emailVerification
    Created on : Jul 9, 2023, 10:07:26 AM
    Author     : tranh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email Verification</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/login.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <div class="center" style="width: 500px">
            <h1>EMAIL VERIFICATION</h1> 
            <form method="POST" action="emailSignupVerification">
                <c:if test="${isSuccess == true}">
                    <h5 class="text-center" style="color: green; font-weight: bold">${successVerifyMess}</h5>
                    <div class="text-center" style="margin-bottom: 30px; margin-top: 20px">
                        <c:if test="${isLoggedin == true}">
                            <a class="btn btn-primary" href="homepage">GO TO HOMEPAGE</a><br>
                        </c:if>
                        <c:if test="${isLoggedin == false}">
                            <a class="btn btn-primary" href="login">LOGIN</a><br>
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${isSuccess == false}">
                    <div class="txt_field">
                        <label>Email</label>
                        <input type="text" name="email" value="${userInformation.email}" disabled/>
                    </div>
                    <div class="txt_field">
                        <label>Verification code</label>
                        <input type="text" name="enteredCode" value="" placeholder="Check your email to get verification code." required/>
                    </div>
                    <c:if test="${not empty wrongCode}">
                        <div class="text-center">
                            <label style="color: red; font-weight: bold; margin-bottom: 20px">${wrongCode}</label>
                        </div>
                    </c:if>
                    <c:if test="${isSentCodeFailed == true}">
                        <div class="text-center">
                            <label style="color: red; font-weight: bold; margin-bottom: 20px">Something went wrong! Try again later...</label>
                        </div>
                    </c:if>
                    <input style="margin-bottom: 20px" type="submit" value="SUBMIT">
                </c:if>
            </form>
        </div>
    </body>
</html>
