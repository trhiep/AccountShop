<%-- 
    Document   : userManager
    Created on : Jun 27, 2023, 1:18:35 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Managers</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/managerHeader.css"/>
        <link rel="stylesheet" href="css/usersManager.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <%@ include file="managerHeader.jsp" %>

        <c:if test="${empty usersList}">
            <div>
                Empty List!
            </div>
        </c:if>

        <c:if test="${not empty usersList}">
            <div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col">USERS IN DATABASE</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Username</th>
                            <th scope="col">Password</th>
                            <th scope="col">Full Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Role</th>
                            <th scope="col">Verified</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${usersList}" var="user"  varStatus = "counter">
                        <form action="updateUser" method="POST">
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td>
                                    ${user.username}
                                    <input type="hidden" name="username" value="${user.username}" />
                                </td>
                                <td><input class="form-control" type="text" name="password" value="${user.password}" /></td>
                                <td><input class="form-control" type="text" name="fullname" value="${user.fullname}" /></td>
                                <td><input class="form-control" type="text" name="email" value="${user.email}" /></td>
                                <td>
                                    <select class="form-control" name="role">
                                        <c:forEach var="role" items="${roleList}">
                                            <option value="${role.roleID}" <c:if test="${role.roleID == user.role}">selected</c:if>>${role.roleName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select class="form-control" name="isVerified">
                                        <option>${user.isVerified?"Yes":"No"}</option>
                                        <option>${!user.isVerified?"Yes":"No"}</option>
                                    </select>
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
                        <th scope="row"></th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr> 
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <th scope="row">ADD NEW USER</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr> 
                    <form action="addNewUser" method="POST">
                        <tr>         
                            <td></td>
                            <td><input class="form-control" type="text" name="username" value="" placeholder ="Username"/></td>
                            <td><input class="form-control" type="text" name="password" value="" placeholder ="Password"/></td>
                            <td><input class="form-control" type="text" name="fullname" value="" placeholder ="Fullname"/></td>
                            <td><input class="form-control" type="text" name="email" value="" placeholder ="Email"/></td>
                            <td><select class="form-control" name="role">
                                    <c:forEach var="role" items="${roleList}">
                                        <option value="${role.roleID}" <c:if test="${role.roleID == user.role}">selected</c:if>>${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><select class="form-control" name="isVerified">
                                    <option>Yes</option>
                                    <option selected>No</option>
                                </select>
                            </td>
                            <td>
                                <input class="btn btn-success" type="submit" value="   Add   "/>
                                <input class="btn btn-secondary" type="reset" value=" Reset " />
                            </td>
                        </tr> 
                    </form>
                    <c:if test="${isAdded == true}">
                        <c:set var="MESSAGE" scope="session" value="Add new user successfully!"/>
                        <c:if test="${isSuccess == false}">
                            <c:set var="MESSAGE" scope="session" value="Error! Something went wrong!"/>
                        </c:if>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <th scope="row">${MESSAGE}</th>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </c:if>
    </body>
</html>
