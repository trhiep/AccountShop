<%-- 
    Document   : searchProducts
    Created on : Jul 12, 2023, 9:53:39 PM
    Author     : tranh
--%>

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
                        <form method="GET" action="searchProducts">
                            <tbody>
                                <tr>
                                    <td>
                                        <input class="form-control" type="text" name="productName" value="${oldRequest}" />
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
                        <form method="GET" action="searchProducts">
                            <tr>
                                <td>
                                    <select class="form-control" name="catalogID">
                                        <option>${oldRequestCatalogID}</option>
                                        <c:forEach var="catalog" items="${catalogList}">
                                            <c:if test="${catalog.catalogID != oldRequestCatalogID}">
                                                <option>${catalog.catalogID}</option>
                                            </c:if>
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
                <c:if test="${empty productList}">
                    <div class="text-center" style="margin-top: 100px">
                        <img src="img/emptyTask.png" width="300" alt="emptyTask"/>
                        <h1 style="font-weight: bold; margin-top: 30px">NO PRODUCT FOUND</h1>
                    </div>
                </c:if>
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
                                        <input type="hidden" name="currentPage" value="viewCart" />
                                        <input class="btn btn-primary" type="submit" value="Add to cart"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
