<%-- 
    Document   : viewCart
    Created on : Jul 4, 2023, 2:31:06 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <c:if test="${empty userAcc}">
            <%@ include file="login.jsp"%>
        </c:if>

        <c:if test="${not empty userAcc}">
            <%@ include file="head.jsp"%>

            <div class="row container-fluid">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class = "ActionList">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th style="font-size: 30px; font-weight: bold" scope="col">SHOPPING CART</th>
                                </tr>
                            </thead>
                        </table>
                        <div class = "table-content">
                            <c:if test="${empty itemList}">
                                <div class ="text-center">
                                    <img src="img/emptyCart.png" width="500px" alt="emptyCart"/>
                                    <h5 style = "margin-top: 20px;
                                        margin-bottom: 20px;
                                        font-weight: bold;
                                        font-size: 24px">YOUR CART IS EMPTY</h5>
                                    <a href="viewProducts" style="text-decoration: none;
                                       background-color: dodgerblue;
                                       border-radius: 10px;
                                       color: white;
                                       padding: 10px;
                                       ">SHOPPING NOW</a>
                                </div>
                            </c:if>
                            <c:if test="${not empty itemList}">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th scope="col">No.</th>
                                            <th scope="col"></th>
                                            <th scope="col">Product name</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Price</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${productList}" var="cartItem" varStatus="counter">
                                        <form action="updateCart" method="POST">
                                            <tr>
                                                <td scope="row">${counter.count}</td>
                                                <td><img src="${cartItem.productImg}" height="75px"/></td>
                                                <td><a style="text-decoration: none;" href="viewDetail?productID=${cartItem.productID}" target="_blank">${cartItem.title}</a></td>
                                                <c:forEach var="item" items="${itemList}">
                                                    <c:if test="${item.productID == cartItem.productID}">
                                                        <td>
                                                            <input class="btn btn-warning" type="submit" value="-" name="btAction" />
                                                            <span style="margin: 0px 10px; font-weight: bold; font-size: 20px">${item.quantity}</span>
                                                            <c:if test="${item.quantity < cartItem.quantity}">
                                                                <input class="btn btn-success" type="submit" value="+" name="btAction"/>
                                                            </c:if>
                                                            <c:if test="${item.quantity >= cartItem.quantity}">
                                                                <input class="btn btn-success" type="submit" value="+" name="btAction" disabled/>
                                                            </c:if>
                                                        </td>
                                                        <td>${cartItem.price*item.quantity}</td>
                                                        <c:set var = "priceOfItem" scope = "request" value = "${cartItem.price*item.quantity}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <td>
                                                    <input class="btn btn-danger" type="submit" value="Delete" name="btAction"/>
                                                    <input type="hidden" name="productID" value="${cartItem.productID}" />
                                                    <input type="hidden" name="price" value="${cartItem.price}" />
                                                </td>
                                                <c:set var = "totalPrice" scope = "request" value = "${totalPrice + priceOfItem}"/>
                                            </tr>
                                        </form>
                                    </c:forEach>

                                    </tbody>
                                    <form action="placeOrder" method="POST">
                                        <tfoot>
                                            <tr class="table-primary">
                                                <th scope="row" style="font-size: 24px">Total</th>
                                                <th></th>
                                                <td>
                                                    <c:forEach items="${itemList}" var="item">
                                                        <input type="hidden" name="productID" value="${item.productID}" />
                                                        <input type="hidden" name="quantity" value="${item.quantity}" />
                                                        <input type="hidden" name="totalPrice" value="${totalPrice}" />
                                                    </c:forEach>
                                                </td>
                                                <td></td>
                                                <th scope="row" style="font-size: 24px">${totalPrice}</th>
                                                <td>
                                                    <input class="btn btn-primary"type="submit" value="Order" />
                                                </td>
                                            </tr>

                                        </tfoot>
                                    </form>
                                </table>
                            </c:if>
                        </div>

                    </div>
                    <div>

                        <p style="color: red">${balanceErr}</p>

                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
        </c:if>
    </body>
</html>
