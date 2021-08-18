<%-- 
    Document   : login
    Created on : Jul 10, 2021, 10:16:55 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bugs - Login</title>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/storage/images/favicon.ico?2" id="favicon"/>

        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app/login.css" type="text/css">
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/" class="logo">
            <i class='bx bx-music bx-tada main-color'></i><span>Bug</span><span class="main-color">s</span>
        </a>
        <div class="signup">
            <h1 class="signup-heading">Sign in</h1>
            <%
                if (request.getParameter("not") != null) {
                    out.println("<p class='text-error'>These credentials is not match any our record!</p>");
                }
                if (request.getParameter("fields") != null) {
                    out.println("<p class='text-error'>Please, full fill these fields below!</p>");
                }
                if (request.getParameter("block") != null) {
                    out.println("<p class='text-error'>Your account has locked, contact admin or hotline 0921.2222.2222</p>");
                }
            %>
            <form action="${pageContext.request.contextPath}/clientAccount" method="post" autocomplete="off">
                <label for="username" class="signup-label">Username</label>
                <input type="text" id="username" name="username" class="signup-input" required="required" minlength="5" maxlength="10">
                <label for="password" class="signup-label">Password</label>
                <input type="password" id="password" name="password" class="signup-input" required="required">
                <button class="submit primary" type="submit" name="submit" value="login">Sign in</button>
            </form>
            <p class="signup-already">
                <span>Don't have an account ?</span>
                <a href="register.jsp" class="signup-login-link">Register</a>
            </p>
        </div>
    </body>
</html>
