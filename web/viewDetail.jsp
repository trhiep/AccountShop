<%-- 
    Document   : viewDetail
    Created on : Jul 3, 2023, 5:20:00 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <c:if test="${isExistProduct == true}">
                ${product.title}
            </c:if>
            <c:if test="${isExistProduct == false}">
                Product does not exist
            </c:if>
        </title>
        <link rel="stylesheet" href="css/viewDetail.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <%@ include file="head.jsp"%>
        <div class="container-fluid" style="margin-top: 50px">

            <c:if test="${isExistProduct == false}">
                <div class="text-center" style="margin-top: 100px">
                    <img src="img/emptyTask.png" width="300" alt="emptyTask"/>
                    <h1 style="font-weight: bold; margin-top: 30px">THIS PRODUCT DOES NOT EXIST</h1>
                </div>
            </c:if>
            <c:if test="${isExistProduct == true}">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-4">
                        <img style="border-radius: 30px" src="${product.productImg}" width="500px" alt="alt"/>
                    </div>
                    <div class="col-md-6">
                        <h2>${product.title}</h2>
                        <p>Rate: <c:if test="${empty ratePoint}">?</c:if>${ratePoint}/5.0</p>
                        <p align="justify">${product.longDetail}</p>
                        <h3>${product.price} vnđ</h3>
                        <form method="POST" action="addToCart">
                            <input type="hidden" name="productID" value="${product.productID}" />
                            <input type="hidden" id="maxQuantity" value="${quantity}"/>
                            <input type="hidden" name="price" value="${product.price}" />
                            <input type="hidden" name="currentPage" value="viewDetail" /><br>
                            <c:if test="${quantity == 0}">
                                <input style="margin-top: 10px" class="btn btn-success" type="submit" value="Add to cart" disabled/>
                                <p style="color: red;
                                   font-style: italic">This product is currently out of stock</p>
                            </c:if>
                            <c:if test="${quantity != 0}">
                                <c:if test="${not empty userAcc}">
                                    <c:if test="${isOutOfRequest == true}">
                                        <input style="margin-top: 10px" class="btn btn-success" type="submit" value="Add to cart" disabled/>
                                        <p style="color: red;
                                           font-style: italic">You can not buy more!</p>
                                    </c:if>
                                    <c:if test="${isOutOfRequest == false}">
                                        <input style="margin-top: 10px" class="btn btn-success" type="submit" value="Add to cart"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${empty userAcc}">
                                    <input style="margin-top: 10px" class="btn btn-success" type="submit" value="Add to cart" />
                                </c:if>
                            </c:if>
                        </form>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <div class="row" style="margin-bottom: 50px">
                    <div class="col-md-1"></div>
                    <div class="col-md-10" style="background-color: #c4c8cb; margin-top: 50px; border-radius: 35px">
                        <div class = "ActionList">
                            <div class="row">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th style="font-size: 30px; font-weight: bold" scope="col">REVIEWS</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class = "row">
                                <table class="table table-borderless">
                                    <c:if test="${empty productRateList}">
                                        <div class ="text-center">
                                            <img src="img/emtyReview.png" width="250" height="250" alt="emtyReview"/>
                                            <h5 style = "margin-top: 20px">THERE IS NO REVIEW AT ALL</h5>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty productRateList}">
                                        <thead>
                                        <th scope="row" style="font-size: 24px">Username</th>
                                        <th scope="row" style="font-size: 24px">Star</th>
                                        <th scope="row" style="font-size: 24px">Comment</th>
                                        <th scope="row" style="font-size: 24px">Date</th>
                                        <th scope="row" style="font-size: 24px"></th>
                                        </thead>
                                        <tbody>
                                            <c:if test="${not empty productRateList}">
                                                <c:forEach items="${productRateList}" var="productRate">
                                                    <c:forEach var="user" items="${usersList}">
                                                        <c:if test="${productRate.username == user.username}">
                                                        <form action="deleteProductRate" method="POST">
                                                            <tr>
                                                                <td>${user.username}</td>
                                                                <td>${productRate.star}</td>
                                                                <td>${productRate.comment}</td>
                                                                <td>${productRate.rateDate}</td>
                                                                <c:if test="${userAcc.username == user.username}">
                                                                <input type="hidden" name="productID" value="${productRate.productID}" />
                                                                <input type="hidden" name="rateID" value="${productRate.rateID}" />
                                                                <td><input class="btn btn-danger" type="submit" value="Detele" /></td>
                                                                </c:if>
                                                            </tr>
                                                        </form>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                        </c:if>
                                        </tbody>
                                    </c:if>
                                </table>
                            </div>

                            <div class="row">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th style="font-size: 24px; font-weight: bold" scope="col">REVIEWS THIS PRODUCT</th>
                                        </tr>
                                    </thead>
                                </table>
                                <table class="table table-borderless">
                                    <thead>
                                        <tr>
                                            <th scope="row" class="text-center">Star</th>
                                            <th scope="row" class="text-center">Comment</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <form action="rateProduct" method="POST">
                                        <c:if test="${empty userAcc}">
                                            <tr>
                                                <td style="width: 100px"><select class="form-control text-center" name="star">
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                    </select>
                                                </td>
                                                <td><input disabled class="form-control" type="text" name="comment" value="" placeholder ="Please login to post a review"/></td>
                                                <td>
                                                    <a class="btn btn-success" href="login">Login</a>
                                                </td>
                                            </tr>
                                        </c:if>

                                        <c:if test="${not empty userAcc}">
                                            <c:if test="${isBought == true && isRated == false}">
                                                <tr>
                                                    <td style="width: 100px"><select class="form-control text-center" name="star">
                                                            <option>1</option>
                                                            <option>2</option>
                                                            <option>3</option>
                                                            <option>4</option>
                                                            <option>5</option>
                                                        </select>
                                                    </td>
                                                    <td><input class="form-control" type="text" name="comment" value="" placeholder ="How do you feel about this product?"/></td>
                                                    <td>
                                                        <input type="hidden" name="productID" value="${product.productID}" />
                                                        <input class="btn btn-success" type="submit" value="Submit"/>
                                                    </td>
                                                </tr>
                                            </c:if>

                                            <c:if test="${isBought == true && isRated == true}">
                                                <tr>
                                                    <td style="width: 100px"><select class="form-control text-center" name="star" disabled>
                                                            <option>1</option>
                                                            <option>2</option>
                                                            <option>3</option>
                                                            <option>4</option>
                                                            <option>5</option>
                                                        </select>
                                                    </td>
                                                    <td><input class="form-control" type="text" name="comment" value="" placeholder ="You have already rated this product." disabled/></td>
                                                    <td>
                                                        <input type="hidden" name="productID" value="${product.productID}" />
                                                        <input class="btn btn-success" type="submit" value="Submit" disabled/>
                                                    </td>
                                                </tr>
                                            </c:if>

                                            <c:if test="${isBought == false}">
                                                <tr>
                                                    <td style="width: 100px"><select class="form-control text-center" name="star" disabled>
                                                            <option>1</option>
                                                            <option>2</option>
                                                            <option>3</option>
                                                            <option>4</option>
                                                            <option>5</option>
                                                        </select>
                                                    </td>
                                                    <td><input disabled class="form-control" type="text" name="comment" value="" placeholder ="Can't submit a review because you have never purchased this product!"/></td>
                                                    <td>
                                                        <input disabled class="btn btn-success" type="submit" value="Submit"/>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:if>
                                    </form>
                                    </tbody> 
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </c:if>
        </div>
    </body>
</html>
