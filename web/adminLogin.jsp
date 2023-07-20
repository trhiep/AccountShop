<%-- 
    Document   : adminLogin
    Created on : Jun 19, 2023, 3:44:23 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/login.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <div class="center">
            <h1>Admin Login</h1>
            <form method="POST" action = "adminLogin">
                <div class="txt_field">
                    <label>Username</label>
                    <input type="text" name="username" value="" required/>
                </div>
                <div class="txt_field">
                    <label>Password</label>
                    <input type="password" name="password" value="" required />
                </div>
                <div class="signup_link" style="color:red;font-weight: bold;"><label>${MESSAGE}</label></div>
                <input type="submit" value="Login">
                <div class="signup_link"></div>
            </form>
        </div>
    </body>
</html>
