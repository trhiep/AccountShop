<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : forgotPassword
    Created on : Jul 8, 2023, 2:36:03 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/login.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <div class="center" style="width: 500px">
            <h1>FORGOT PASSWORD</h1>
            <form method="POST" action = "forgotPassword">
                <c:if test="${empty isSentCaptcha}">
                    <div class="txt_field">
                        <label style="font-weight: bold">Username</label>
                        <input type="text" name="username" value="" required/>
                    </div>
                    <p class="text-center" style="color:red; font-weight: bold">${errMsg}</p>
                    <input type="submit" value="Send recovery email">
                </c:if>
            </form> 
            <c:if test="${isSentCaptcha == true}">
                <form action="enterNewPassword" method="POST">
                    <div class="txt_field">
                        <label style="font-weight: bold">Verification Code</label>
                        <input type="text" name="enteredCode" value="" placeholder="Check your mail to get verification code!" required/>
                        <input type="hidden" name="usernameIsChanging" value="${username}" />
                    </div>
                    <input type="submit" value="SETUP NEW PASSWORD">
                </form>
            </c:if>
            <c:if test="${isNotVerified == true}">
                <br><p class="text-center" style="color:red; font-weight: bold">Your email is not verified!</p>
                <p class="text-center" style="color:red; font-weight: bold">Please contact admin</p>
            </c:if>
            <div class="signup_link">
                Already have an account? <a href="login">Login</a><br>
                Do not have an account? <a href="signup">Signup</a>
            </div>

        </div>
    </body>
</html>
