<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : changeEmailVerification
    Created on : Jul 11, 2023, 8:24:35 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Email Verification</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/login.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <div class="center" style="width: 500px">
            <h1>EMAIL VERIFICATION</h1> 
            <form method="POST" action="changeEmailVerification">
                <c:if test="${isSuccess == false}">
                    <div class="txt_field">
                        <label>Email</label>
                        <input type="text" name="oldEmail" value="${userInfor.email}" disabled/>
                        <input type="hidden" name="newEmail" value="${newEmail}"/>
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
                    <input style="margin-bottom: 20px" type="submit" value="SUBMIT">
                </c:if>
            </form>
        </div>
    </body>
</html>
