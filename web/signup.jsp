<%-- 
    Document   : signup
    Created on : Jun 30, 2023, 2:26:59 PM
    Author     : tranh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
        <link rel="stylesheet" href="bootstrap-5.3.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/login.css"/>
        <link rel="icon" type="image/x-icon" href="img/icon.png">
    </head>
    <body>
        <div class="center" style="width: 600px">
            <h1>CREATE NEW ACCOUNT</h1>
            <c:if test="${signupFailed == true}">
                <form method="POST" action = "signup">
                    <div class="txt_field">
                        <label>Username</label>
                        <input type="text" name="username" value="" pattern="[a-z0-9]+\w{4,16}" title="Username can only contain 5 to 16 lowercase letters and numbers." required/>
                        <c:if test="${not empty UsernameErr}">
                            <label>${UsernameErr}</label>
                        </c:if>
                    </div>
                    <div class="txt_field">
                        <label>Full Name</label>
                        <input type="text" name="fullname" value="" pattern="[^$&+,:;=?@#|'<>.-^*()%!]" title="Fullname can only contain letters." required/>
                    </div>
                    <div class="txt_field">
                        <label>Email</label>
                        <input type="text" name="email" value="" pattern="[a-z0-9._%+\-]+@[a-z0-9.\-]+\.[a-z]{2,}$" placeholder="Example: name@mail.com" required/>
                    </div>
                    <div class="txt_field">
                        <label>Password</label>
                        <input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one  number and one uppercase and lowercase letter, and at least 8 or more characters">
                    </div>
                    <div class="txt_field">
                        <label>Repeat password</label>
                        <input type="password" name="rePassword" required />
                        <c:if test="${not empty RePasswordErr}">
                            <label>${RePasswordErr}</label>
                        </c:if>
                    </div>

                    <div class="form-check d-flex justify-content-center mb-5">
                        <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3c" required/>
                        <label class="form-check-label" for="form2Example3">
                            I agree all statements in <a href="termsofservice.html" target="_blank">Terms of service</a>
                        </label>
                    </div>

                    <input type="submit" value="Signup">
                    <div class="signup_link">
                        Already have an account? <a href="login">Login</a><br>
                        <a style="text-decoration: none" href="forgotPassword">Forgot Password?</a>
                    </div>
                </form>
            </c:if>

            <c:if test="${signupFailed == false}">
                <h4 class="text-center" style="color: green; font-weight: bold">CREATE NEW ACCOUNT SUCCESSFULLY</h4>
                <h5 class="text-center" style="color: green; font-weight: bold">Do you want to verify your email now?</h5>
                <div class="signup_link">
                    <a href="login">I will do it later</a><br>
                    <a href="emailSignupVerification">I want to verify my email now</a>
                </div>
            </c:if>

        </div>
    </body>
</html>
