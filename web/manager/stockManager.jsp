<%-- 
    Document   : accountManager
    Created on : Jun 25, 2023, 1:42:04 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock Managerment</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/managerHeader.css"/>
        <link rel="stylesheet" href="css/stockManager.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <%@ include file="managerHeader.jsp" %>

        <c:if test="${action == 'Catalog List'}">
            <div class="row container-fluid" style="margin-top: 20px">
                <div class="col-md-8"></div>
                <div class="col-md-2">
                    <div class="list-group text-center">
                        <form action="stockManager" method="POST">
                            <input class="list-group-item list-group-item-action active" type="submit" value="Catalog List" name="btAction" />
                            <input class="list-group-item list-group-item-action" type="submit" value="Product List" name="btAction" />
                            <input class="list-group-item list-group-item-action" type="submit" value="Account List" name="btAction" />
                        </form>
                    </div>
                </div>
            </div>
            <div class="row container-fluid">
                <div class = "ActionList">

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="tableHead" scope="col">CATALOG LIST</th>
                            </tr>
                        </thead>
                    </table>
                    <c:if test="${empty catalogList}">
                        <div class ="text-center">
                            <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                            <h5 style = "margin-top: 20px">CATALOG LIST IS EMPTY NOW</h5>
                        </div>
                    </c:if>
                    <c:if test="${not empty catalogList}">
                        <div>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Image Preview</th>
                                        <th scope="col">Display name</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${catalogList}" var="catalog"  varStatus = "counter">
                                    <form action="updateCatalog" method="POST">
                                        <tr>
                                            <td>
                                                ${catalog.catalogID}
                                                <input type="hidden" name="catalogID" value="${catalog.catalogID}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="catalogImg" value="${catalog.catalogImg}" />
                                            </td>
                                            <td>
                                                <img src="${catalog.catalogImg}" height="40px"/>
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="catalogName" value="${catalog.catalogName}" required/>
                                            </td>
                                            <td>
                                                <input class="btn btn-warning" type="submit" value="Update" name = "btAction"/>
                                                <input class="btn btn-danger" type="submit" value="Delete" name = "btAction" class = "deleteBtn"/>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <th scope="row">
                                        <c:if test="${not empty UPDATECATALOGMESSAGE}">
                                            ${UPDATECATALOGMESSAGE}
                                        </c:if>
                                    </th>
                                </tr> 
                                <tr>
                                    <th scope="row">ADD NEW CATALOG</th>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr> 
                                <form action="addNewCatalog" method="POST">
                                    <tr>         
                                        <td><input class="form-control" type="text" name="catalogID" value="" placeholder ="Catalog ID" required/></td>
                                        <td><input class="form-control" type="text" name="catalogImg" value="" placeholder ="Image link"/></td>
                                        <td></td>
                                        <td><input class="form-control" type="text" name="catalogName" value="" placeholder ="Display name" required/></td>
                                        <td>
                                            <input class="btn btn-success" type="submit" value="   Add   "/>
                                            <input class="btn btn-secondary" type="reset" value=" Reset " />
                                        </td>
                                    </tr> 
                                </form>
                                <c:if test="${isAdded == true}">
                                    <c:set var="MESSAGE" scope="session" value="Add new catalog successfully!"/>
                                    <c:if test="${isSuccess == false}">
                                        <c:set var="MESSAGE" scope="session" value="Error! Something went wrong!"/>
                                    </c:if>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <c:if test="${isSuccess == false}">
                                            <th style="color: red" scope="row">${MESSAGE}</th>
                                            </c:if>
                                            <c:if test="${isSuccess == true}">
                                            <th style="color: green" scope="row">${MESSAGE}</th>
                                            </c:if>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </c:if>   
                </div>
            </div>
        </c:if>

        <c:if test="${action == 'Product List'}">
            <div class="row container-fluid" style="margin-top: 20px">
                <div class="col-md-8"></div>
                <div class="col-md-2">
                    <div class="list-group text-center">
                        <form action="stockManager" method="POST">
                            <input class="list-group-item list-group-item-action" type="submit" value="Catalog List" name="btAction" />
                            <input class="list-group-item list-group-item-action active" type="submit" value="Product List" name="btAction" />
                            <input class="list-group-item list-group-item-action" type="submit" value="Account List" name="btAction" />
                        </form>
                    </div>
                </div>
            </div>
            <div class="row container-fluid">
                <div class = "ActionList">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="tableHead" scope="col">PRODUCT LIST</th>
                            </tr>
                        </thead>
                    </table>
                    <div class = "table-content">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Product ID</th>
                                    <th scope="col">Catalog ID</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Type</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Short Detail</th>
                                    <th scope="col">Long Detail</th>
                                    <th scope="col">Product Image</th>
                                    <th scope="col">Quantity</th>
                                </tr>
                            </thead>
                            <c:if test="${empty productList}">
                                <div class ="text-center">
                                    <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                                    <h5 style = "margin-top: 20px">PRODUCT LIST IS EMPTY NOW</h5>
                                </div>
                            </c:if>
                            <tbody>
                                <c:if test="${not empty productList}">
                                    <c:forEach items="${productList}" var="product"  varStatus = "counter">
                                    <form action="updateProduct" method="POST">
                                        <tr>
                                            <td>
                                                ${product.productID}
                                                <input class="form-control" type="hidden" name="productID" value="${product.productID}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="catalogID" value="${product.catalogID}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="title" value="${product.title}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="type" value="${product.type}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="price" value="${product.price}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="shortDetail" value="${product.shortDetail}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="longDetail" value="${product.longDetail}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="productImg" value="${product.productImg}" />
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="quantityDis" value="${product.quantity}" disabled/>
                                                <input class="form-control" type="hidden" name="quantity" value="${product.quantity}"/>
                                            </td>
                                            <td>
                                                <input class="btn btn-warning" type="submit" value="Update" name = "btAction"/>
                                                <input class="btn btn-danger" type="submit" value="Delete" name = "btAction"/>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <th scope="row"></th>
                            </tr> 
                            <form action="addNewProduct" method="POST">
                                <tr>         
                                    <th scope="row">ADD NEW PRODUCT</th>
                                    <td>
                                        <select class="form-control" name="catalogID" required>
                                            <c:forEach var="catalog" items="${catalogList}">
                                                <option>${catalog.catalogID}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input class="form-control" type="text" name="title" value="" placeholder ="Title" required/></td>
                                    <td><input class="form-control" type="text" name="type" value="" placeholder ="Type" required pattern="\d+(m|y)" title="Example: 1m, 2m, 1y, 6y,..."/></td>
                                    <td><input class="form-control" type="text" name="price" value="" placeholder ="Price" required/></td>
                                    <td><input class="form-control" type="text" name="shortDetail" value="" placeholder ="Short Detail" required/></td>
                                    <td><input class="form-control" type="text" name="longDetail" value="" placeholder ="Long Detail" required/></td>
                                    <td><input class="form-control" type="text" name="productImg" value="" placeholder ="Product Image Link"/></td>
                                    <td>
                                        <input class="btn btn-success" type="submit" value="   Add   "/>
                                        <input class="btn btn-secondary" type="reset" value="Reset " />
                                    </td>
                                </tr>
                            </form>
                            <c:if test="${isAdded == true}">
                                <c:set var="MESSAGE" scope="session" value="Add new product successfully!"/>
                                <c:if test="${isSuccess == false}">
                                    <c:set var="MESSAGE" scope="session" value="Error! Something went wrong!"/>
                                </c:if>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <c:if test="${isSuccess == false}">
                                        <th style="color: red" scope="row">${MESSAGE}</th>
                                        </c:if>
                                        <c:if test="${isSuccess == true}">
                                        <th style="color: green" scope="row">${MESSAGE}</th>
                                        </c:if>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </c:if>


        <c:if test="${action == 'Account List'}">
            <div class="row container-fluid" style="margin-top: 20px">
                <div class="col-md-8"></div>
                <div class="col-md-2">
                    <div class="list-group text-center">
                        <form action="stockManager" method="POST">
                            <input class="list-group-item list-group-item-action" type="submit" value="Catalog List" name="btAction" />
                            <input class="list-group-item list-group-item-action" type="submit" value="Product List" name="btAction" />
                            <input class="list-group-item list-group-item-action active" type="submit" value="Account List" name="btAction" />
                        </form>
                    </div>
                </div>
            </div>
            <div class="row container-fluid">
                <div class = "ActionList">

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="row" class="tableHead">ADD NEW ACCOUNT</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr> 
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col"></th>
                                <th scope="col">Product ID</th>
                                <th scope="col">Email</th>
                                <th scope="col">Password</th>
                                <th scope="col">Expire Date</th>
                                <th scope="col">Action</th>
                            </tr>

                        <form action="addNewAccount" method="POST">
                            <tr>         
                                <td></td>
                                <td></td>
                                <td>
                                    <select class="form-control" name="productID" required>
                                        <c:forEach var="product" items="${productList}">
                                            <option>${product.productID}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><input class="form-control" type="text" name="email" value="" placeholder ="Email" required/></td>
                                <td><input class="form-control" type="text" name="password" value="" placeholder ="Password" required/></td>
                                <td><input class="form-control" type="date" name="dueDate" value="" required/></td>

                                <td>
                                    <input class="btn btn-success" type="submit" value="   Add   "/>
                                    <input class="btn btn-secondary" type="reset" value=" Reset " />
                                </td>
                            </tr> 
                        </form>
                        <c:if test="${isAddedAcc == true}">
                            <c:set var="AddAccountMessage" scope="session" value="Add new account successfully!"/>
                            <c:if test="${isSuccess == false}">
                                <c:set var="AddAccountMessage" scope="session" value="Error! Something went wrong!"/>
                            </c:if>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <th scope="row">${AddAccountMessage}</th>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="tableHead" scope="col">ACCOUNT LIST</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        <form action="stockManager" method="POST">
                            <tr>
                                <th style="font-size: 24px" scope="col">Total: ${totalAccount} accounts</th>
                                <th scope="col"></th>
                                <th scope="col">
                                    <select  class="form-control" name="filterBtProductID">
                                        <option value="-1">All</option>
                                        <c:forEach var="product" items="${productList}">
                                            <option <c:if test="${product.productID == oldSearchValue}">selected</c:if> value="${product.productID}">${product.title}</option>
                                        </c:forEach>
                                    </select>
                                </th>
                                <th scope="col"><input class="btn btn-success" type="submit" value="Search" name="btAction"/></th>
                            </tr>
                        </form>
                        </thead>
                    </table>
                    <c:if test="${empty accountList}">
                        <div class ="text-center">
                            <img src="img/emptyTask.png" width="250" height="250" alt="emptyTask"/>
                            <h5 style = "margin-top: 20px">ACCOUNT LIST IS EMPTY NOW</h5>
                        </div>
                    </c:if>

                    <c:if test="${not empty accountList}">
                        <div>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">No.</th>
                                        <th scope="col">Account ID</th>
                                        <th scope="col">Product ID</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Password</th>
                                        <th scope="col">Expire Date</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${accountList}" var="account"  varStatus = "counter">
                                    <form action="updateAccount" method="POST">
                                        <tr>
                                            <th scope="row">${counter.count}</th>
                                            <td>${account.accountID}
                                                <input class="form-control" type="hidden" name="accountID" value="${account.accountID}" />
                                            </td>
                                            <td>
                                                <select  class="form-control" name="productID">
                                                    <option>${account.productID}</option>
                                                    <c:forEach var="product" items="${productList}">
                                                        <c:if test="${product.productID != account.productID}">
                                                            <option>${product.productID}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input class="form-control" type="text" name="email" value="${account.email}" />
                                            </td>
                                            <td><input class="form-control" type="text" name="password" value="${account.password}" /></td>
                                            <td><input class="form-control" type="text" name="dueDate" value="${account.dueDate}" /></td>
                                            <td>
                                                <input class="btn btn-warning" type="hidden" value="${account.productID}" name = "oldProductID"/>
                                                <input class="btn btn-warning" type="submit" value="Update" name = "btAction"/>
                                                <input class="btn btn-danger" type="submit" value="Delete" name = "btAction" class = "deleteBtn"/>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </c:if>

                </div>
            </div>
        </c:if>

    </body>
</html>
