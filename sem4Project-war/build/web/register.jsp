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
        <title>Bugs - Register new account</title>
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
            <h1 class="signup-heading">Register</h1>
            <%
                if (request.getParameter("username") != null) {
                    out.println("<p class='text-error'>Username is not valid, must not contain any special character or space and length of username must be from 5 to 10 characters!</p>");
                }
                if (request.getParameter("fields") != null) {
                    out.println("<p class='text-error'>Please, full fill these fields below!</p>");
                }
            %>
            <form action="${pageContext.request.contextPath}/clientAccount" method="post" autocomplete="off">
                <label for="username" class="signup-label">Username</label>
                <input type="text" id="username" name="username" class="signup-input" required="required" minlength="5" maxlength="12" pattern="[A-Za-z0-9]+">
                <p class="note_">(Username contains letters and numbers only, no punctuation or special characters)</p>
                <label for="password" class="signup-label">Password</label>
                <input type="password" id="password" name="password" class="signup-input" required="required">
                <label for="fullname" class="signup-label">Full name</label>
                <input type="text" id="fullname" name="fullname" class="signup-input" required="required">
                <label for="gender" class="signup-label">Gender</label>
                <select class="signup-input" name="gender" id="gender">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
                <button class="submit primary" type="submit" name="submit" value="register">Sign up</button>
            </form>
            <p class="signup-already">
                <span>Already have an account ?</span>
                <a href="login.jsp" class="signup-login-link">Sign in</a>
            </p>
        </div>
    </body>
</html>
