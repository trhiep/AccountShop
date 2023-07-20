<%-- 
    Document   : viewProducts
    Created on : Jul 2, 2023, 1:37:46 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <link rel="stylesheet" href="css/viewProducts.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <%@ include file="head.jsp"%>
        <div class="row container-fluid">
            <div class="col-md-2 filterBox" style="margin-bottom: 30px">
                <div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>FIND PRODUCT BY NAME</th>
                            </tr>
                        </thead>
                        <form method="POST" action = "searchProducts">
                            <tbody>
                                <tr>
                                    <td>
                                        <input class="form-control" type="text" name="productName" value="" />
                                        <input style="margin-top: 10px" class="btn btn-success text-center" type="submit" value="Search By Name" name="btAction"/>
                                    </td>
                                </tr>
                            </tbody>
                        </form>
                    </table>
                </div>
                <div style="margin-top: 30px">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>FIND PRODUCT BY CATALOG</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form method="POST" action = "searchProducts">
                            <tr>
                                <td>
                                    <select class="form-control" name="catalogID">
                                        <c:forEach var="catalog" items="${catalogList}">
                                            <option>${catalog.catalogID}</option>
                                        </c:forEach>
                                    </select>
                                    <input style="margin-top: 10px" class="btn btn-success text-center" type="submit" value="Search By Catalog" name="btAction"/>
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-10 content">
                <div class="row gy-3">
                    <c:forEach var="product" items="${productList}">
                        <div class="col-md-3">
                            <form action="addToCart" method="POST">
                                <div class="card" style="max-height: 650px">
                                    <img class="card-img-top" src="${product.productImg}" alt="${product.productImg}" height="350px">
                                    <div class="card-body">
                                        <a style="text-decoration: none" href="viewDetail?productID=${product.productID}"><h4 class="card-title">${product.title}</h4></a>
                                        <p style="height: 75px" class="card-text">${product.shortDetail}</p>
                                        <p style="font-weight: bold; font-size: 20px">Price: ${product.price} vnđ</p>
                                        <input type="hidden" name="productID" value="${product.productID}" />
                                        <input type="hidden" name="price" value="${product.price}" />
                                        <input type="hidden" name="currentPage" value="viewProducts" />
                                        <c:if test="${not empty userAcc}">
                                            <c:set var = "isExistInCart" scope = "request" value = "0"/>
                                            <c:forEach var="item" items="${itemList}">
                                                <c:if test="${product.productID == item.productID}">
                                                    <c:if test="${item.quantity < product.quantity}">
                                                        <input class="btn btn-primary" type="submit" value="Add to cart"/>
                                                    </c:if>
                                                    <c:if test="${item.quantity >= product.quantity}">
                                                        <input class="btn btn-primary" type="submit" value="Add to cart" disabled/>
                                                    </c:if>
                                                    <c:set var = "isExistInCart" scope = "request" value = "1"/>
                                                </c:if>
                                            </c:forEach>

                                            <c:if test="${isExistInCart == 0}">
                                                <input class="btn btn-primary" type="submit" value="Add to cart"/>
                                            </c:if>
                                        </c:if>

                                        <c:if test="${empty userAcc}">
                                            <input class="btn btn-primary" type="submit" value="Add to cart"/>
                                        </c:if>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination pagination-lg justify-content-center" style="
                            background-color: white;
                            margin: 30px 0px">
                            <c:forEach var="p" begin="1" end="${endPage}">
                                <li class="page-item"><a class="page-link ${page == p?"active":""}" href="viewProducts?page=${p}">${p}</a></li>
                                </c:forEach>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>