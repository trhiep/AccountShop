<%-- 
    Document   : login
    Created on : Jun 25, 2023, 2:01:08 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/login.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>

        <c:if test="${not empty successSignupMess}">
            <div class="center">
                <h1>${successSignupMess}</h1>
            </div>
        </c:if>

        <div class="center">
            <h1>LOGIN</h1>
            <form method="POST" action = "login">
                <div class="txt_field">
                    <label>Username</label>
                    <input type="text" name="username" value="" required/>
                </div>
                <div class="txt_field">
                    <label>Password</label>
                    <input type="password" name="password" value="" required />
                </div>
                <label>${LMESSAGE}</label>
                
                <input type="submit" value="Login">
                <div class="signup_link">
                    Do not have an account? <a href="signup">Signup</a><br>
                    <a href="forgotPassword">Forgot Password?</a>
                </div>
                
            </form>
        </div>
    </body>
</html>
