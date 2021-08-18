<%-- 
    Document   : header
    Created on : Jun 23, 2021, 2:22:55 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    int user_id = 0;
    if (session.getAttribute("accountID") != null) {
        user_id = Integer.parseInt(session.getAttribute("accountID").toString());
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>
            Bugs - Music for Life
        </title>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/storage/images/favicon.ico?2" id="favicon"/>
        <!-- GOOGLE FONTS -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;700;900&display=swap"
              rel="stylesheet">
        <!-- OWL CAROUSEL -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
              integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
              crossorigin="anonymous" />
        <!-- JQUERY -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/sweetalert2.min.js"></script>

        <!-- BOX ICONS -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- APP CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app/grid.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app/app.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app/audioplayer.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/sweetalert2.min.css">
    </head>
    <body>
        <input type="hidden" id="url" value="${pageContext.request.contextPath}">
        <!-- NAV -->
        <div class="nav-wrapper">
            <div class="container">
                <div class="nav">
                    <a href="${pageContext.request.contextPath}/" class="logo">
                        <i class='bx bx-music bx-tada main-color'></i>Bug<span class="main-color">s</span>
                    </a>
                    <div class="form-search">
                        <form action="${pageContext.request.contextPath}/clientSearch" method="get">
                            <input type="text" name="s" placeholder="Enter song, artist, news ...">
                            <button type="submit" name="submit" value="search" class="btn-search"><i class='bx bx-search'></i></button>
                        </form>
                    </div>
                    <ul class="nav-menu" id="nav-menu">
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/clientNews?do=list">News</a></li>
                        <li><a href="${pageContext.request.contextPath}/clientChart?do=release">New release</a></li>
                        <li><a href="${pageContext.request.contextPath}/clientGenre?do=list">Genre</a></li>
                        <li><a href="${pageContext.request.contextPath}/clientChart?do=view">Chart</a></li>
                        <li class="search-li"><a href="#">Search</a></li>
                            <%
                                if (session.getAttribute("accountID") == null) {

                            %>
                        <li>
                            <a href="" class="login-btn">
                                <span>Sign in</span>
                            </a>
                        </li>

                        <% }
                        %>
                    </ul>
                    <% if (session.getAttribute("accountID") != null) {

                    %>
                    <div class="avatar-menu dropdown">
                        <img src="${pageContext.request.contextPath}/storage/profile/${controller.account(sessionScope.accountID).avatar}" alt="" class="dropdown-toggle" data-toggle="user-menu">
                        <ul id="user-menu" class="dropdown-menu">
                            <li class="dropdown-menu-item">
                                <a href="${pageContext.request.contextPath}/clientAccount?do=profile&id=<%=user_id%>" class="dropdown-menu-link">
                                    <div>
                                        <i class='bx bx-user'></i>
                                    </div>
                                    <span>Profile</span>
                                </a>
                            </li>
                            <li class="dropdown-menu-item">
                                <a href="${pageContext.request.contextPath}/clientAccount?do=mysongs" class="dropdown-menu-link">
                                    <div>
                                        <i class='bx bx-headphone'></i>
                                    </div>
                                    <span>My songs</span>
                                </a>
                            </li>
                             <li class="dropdown-menu-item">
                                <a href="${pageContext.request.contextPath}/clientAccount?do=favorite" class="dropdown-menu-link">
                                    <div>
                                        <i class='bx bx-heart'></i>
                                    </div>
                                    <span>My favorite song</span>
                                </a>
                            </li>
                             <li class="dropdown-menu-item">
                                <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=all" class="dropdown-menu-link">
                                    <div>
                                        <i class='bx bx-shopping-bag'></i>
                                    </div>
                                    <span>History order</span>
                                </a>
                            </li>
                            <%
                                if (session.getAttribute("admin") != null) {
                            %>
                            <li class="dropdown-menu-item">
                                <a href="${pageContext.request.contextPath}/admin" class="dropdown-menu-link">
                                    <div>
                                        <i class='bx bxs-home-circle'></i>
                                    </div>
                                    <span>Admin panel</span>
                                </a>
                            </li>
                            <%
                                }
                            %>
                            <li class="dropdown-menu-item">
                                <a href="${pageContext.request.contextPath}/clientAccount?do=exit" class="dropdown-menu-link">
                                    <div>
                                        <i class='bx bx-log-out' ></i>
                                    </div>
                                    <span>Log out</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <%                             }
                    %>

                    <!-- MOBILE MENU TOGGLE -->
                    <div class="hamburger-menu" id="hamburger-menu">
                        <div class="hamburger"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END NAV -->
        <!-- LOGIN FORM --->
        <div class="phanhoi-container">
            <form method="post" class="phanhoi-form" id="frmLogin">
                <div class="phanhoi-title">
                    <img src="${pageContext.request.contextPath}/storage/images/login.png" alt="">
                </div>
                <div class="phanhoi-bg">
                    <div class="phanhoi-error"></div>
                    <div class="phanhoi-box">
                        <p>Username</p>
                        <input type="text" name="username" id="username" placeholder="Username" required>
                    </div>
                    <div class="phanhoi-box">
                        <p>Password</p>
                        <input type="password" name="password" id="password" placeholder="password" required>
                    </div>
                    <div class="phanhoi-box">
                        <button type="submit">Sign in</button>
                    </div>
                    <div class="phanhoi-end center">
                        You don't have an account ? <a href="${pageContext.request.contextPath}/register.jsp">Register now</a>
                    </div>
                </div>
            </form>
        </div>