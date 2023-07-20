<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : enterNewPassword
    Created on : Jul 8, 2023, 3:27:31 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter new password</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/login.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <div class="center" style="width: 500px">
            <h1>NEW PASSWORD</h1>
            <c:if test="${empty isChanged}">
                <form method="POST" action="saveNewPassword">
                    <div class="txt_field">
                        <label>Username</label>
                        <input type="text" name="usernameIsChanging" value="${usernameIsChanging}" disabled/>
                    </div>
                    <div class="txt_field">
                        <label>New password</label>
                        <input type="password" name="newPassword" value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one  number and one uppercase and lowercase letter, and at least 8 or more characters" required/>
                    </div>
                        <input type="hidden" name="username" value="${usernameIsChanging}" />
                    <input style="margin-bottom: 20px" type="submit" value="SUBMIT">
                </form>
            </c:if>
            <c:if test="${isChanged == true}">
                <p class="text-center" style="color:green; font-weight: bold">CHANGE PASSWORD SUCCESSFULLY</p>
                <div class="signup_link">
                    <a href="login">Login</a><br>
                </div>
            </c:if>
        </div>
    </body>
</html>
