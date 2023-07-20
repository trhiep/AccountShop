<%-- 
    Document   : HomePage
    Created on : Jun 25, 2023, 1:00:25 PM
    Author     : tranh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ZShop</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <%@ include file="head.jsp"%>

        <div class="row container-fluid" style="margin-top: 130px">
            <div class="col-md-1"></div>
            <div class="col-md-10 text-center">
                <a href="searchProducts?btAction=searchByCatalogID&catalogID=${hompageCatalog}"><img style="border-radius: 25px" src="${backgroundImg}" width="75%" alt="quizlet"/></a>
            </div>
            <div class="col-md-1"></div>
        </div>

        <div class="row container-fluid" style="margin-top: 100px">
            <div class="col-md-12 content">
                <div><h1 class="text-center" style="font-size: 50px;
                         font-weight: bolder;
                         margin-bottom: 30px">CATALOGS</h1></div>
                <div class="row gy-3" style="justify-content: center">
                    <c:forEach var="catalog" items="${calalogList}">
                        <div class="col-md-2" style="width: 350px">
                            <div class="card" style="max-height: 650px">
                                <img class="card-img-top" src="${catalog.catalogImg}" alt="${catalog.catalogImg}" height="350px">
                                <div class="card-body text-center">
                                    <h5 class="card-title">${catalog.catalogName}</h5>
                                    <a href="searchProducts?btAction=searchByCatalogID&catalogID=${catalog.catalogID}" class="btn btn-primary">Explore</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div style="margin-top: 30px"><h1 class="text-center" style="font-size: 30px;
                         font-weight: bolder;
                         margin-bottom: 30px"><a style="text-decoration: none;
                         color: white;
                         background-color: dodgerblue;
                         border-radius: 10px;
                         padding: 10px 20px" href="viewProducts">Explore More</a></h1></div>
            </div>
        </div>
        
        <%@ include file="footer.jsp"%>
    </body>
</html>
