<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : managerHeader
    Created on : Jun 27, 2023, 1:41:31 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <div class="container-fluid header">

            <c:if test="${userAcc.role == 1}">
                <form action="usersManager" method = "GET" class="headerBtn">
                    <input type="submit" value= "User Management"/>
                </form>
                <form action="stockManager" method="GET" class="headerBtn"> 
                    <input type="submit" value="Account Stock"/>
                </form>
                <form action="orderManager" method="GET" class="headerBtn"> 
                    <input type="submit" value="Order Management"/>
                </form>
                <form action="balanceManager" method="GET" class="headerBtn"> 
                    <input type="submit" value="Deposit Request"/>
                </form>
            </c:if>

            <c:if test="${userAcc.role == 2}">
                <form action="stockManager" method="GET" class="headerBtn"> 

                    <input type="submit" value="Account Stock"/>
                </form>
            </c:if>

            <c:if test="${userAcc.role == 3}">
                <form action="orderManager" method="GET" class="headerBtn"> 
                    <input type="submit" value="Order Management"/>
                </form>
                <form action="balanceManager" method="GET" class="headerBtn"> 
                    <input type="submit" value="Deposit Request"/>
                </form>

            </c:if>

            <c:if test="${userAcc.role != 0 && userAcc.role != 1}">
                <form action="changeManagerInformation" method="GET" class="headerBtn"> 
                    <input type="submit" value="Change Information"/>
                </form>
            </c:if>

            <form action="logout" method="POST" class="logouBtn"> 
                <input type="submit" value="Logout" name="btAction" />
            </form>

        </div>
    </body>
</html>
