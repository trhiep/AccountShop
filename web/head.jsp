<%-- 
    Document   : head
    Created on : Jun 28, 2023, 10:34:56 AM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/head.css">
    </head>
    <body>
        <div class="container-fluid header">

            <a href="homepage"><img src="img/logo.png" width="225" height="75" alt="logo"/></a>

            <ul>
                <c:if test="${not empty userAcc}">
                    <li><a href="viewAccount">Hi, ${userAcc.username}</a></li>
                    </c:if>
                    
                <c:if test="${not empty userAcc}">
                    <li><a href="viewCart">Cart<label style="margin-left: 10px; font-size: 18px">${itemsInCart}</label></a></li>
                    </c:if>

                <c:if test="${empty userAcc}">
                    <li><a href="login">Login</a></li>
                    </c:if>
                    
                <c:if test="${not empty userAcc}">
                    <li><a href="deposit">Deposit</a></li>
                    </c:if>
                <li><a href="viewProducts">Products</a></li>
            </ul>
        </div>

    </body>
</html>