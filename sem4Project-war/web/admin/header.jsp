<%-- 
    Document   : header
    Created on : Jun 18, 2021, 2:05:53 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    if (session.getAttribute("admin") == null) {
        response.sendRedirect("error.jsp");
    }

%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title class="no-print">Dashboard</title>
        <link rel="shortcut icon" href="/images/logo-mb.png" type="image/png">
        <!-- GOOGLE FONT -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
        <!-- BOXICONS -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- APP CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/grid.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/app.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/custom.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/js/sweetalert2.min.css">
        <!-- SCRIPT -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/sweetalert2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jQuery.print.js"></script>
    </head>

    <body>

        <!-- SIDEBAR -->
        <div class="sidebar">
            <div class="sidebar-logo">
                <img src="https://cdn.iconscout.com/icon/free/png-256/home-803-450320.png" alt="">
                <div class="sidebar-close" id="sidebar-close">
                    <i class='bx bx-left-arrow-alt'></i>
                </div>
            </div>
            <%                if (session.getAttribute("accountID") != null) {
            %>
            <div class="sidebar-user">
                <div class="sidebar-user-info">
                    <div class="sidebar-user-name">${controller.account(sessionScope.accountID).username}</div>
                </div>
            </div>
            <%
                }
            %>
            <!-- SIDEBAR MENU -->
            <ul class="sidebar-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/" class="sidebar-menu-dropdown">
                        <i class='bx bx-home'></i>
                        <span>Home page</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin" class="">
                        <i class='bx bxl-stack-overflow'></i>
                        <span>Dashbroad</span>
                    </a>
                </li>
                <!-- end li-->
                <li class="sidebar-submenu">
                    <a href="#" class="sidebar-menu-dropdown">
                        <i class='bx bxs-news' ></i>
                        <span>News</span>
                        <div class="dropdown-icon"></div>
                    </a>
                    <ul class="sidebar-menu sidebar-menu-dropdown-content">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-categories?do=list">
                                Categories
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-news?do=list">
                                List news
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-comments?do=list">
                                Comments
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/news/statistic.jsp">
                                Report
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ManageRevenue?date=hientai" class="sidebar-menu-dropdown"/>
                    <img height="30px" src="https://img.icons8.com/dotty/80/000000/economic-improvement.png"/> 
                    <span> &nbsp;Revenue</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ManageBillOrder?date=hientai" class="sidebar-menu-dropdown"/>
                    <img height="30px" src="https://img.icons8.com/ios/50/000000/create-order--v2.png"/> 
                    <span>  &nbsp; Order</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Money?date=hientais" class="sidebar-menu-dropdown"/>
                    <img height="30px" src="${pageContext.request.contextPath}/storage/images/money-80.png "/> 
                    <span>  &nbsp;Recharge</span>
                    </a>
                </li>
                <li class="sidebar-submenu">
                    <a href="#" class="sidebar-menu-dropdown">
                        <i class='bx bx-music' ></i>
                        <span>Music</span>
                        <div class="dropdown-icon"></div>
                    </a>
                    <ul class="sidebar-menu sidebar-menu-dropdown-content">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-typesong?do=list" class="sidebar-menu-dropdown">
                                <i class='bx bxs-album'></i>
                                <span>Type of song</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-language?do=list" class="sidebar-menu-dropdown">
                                <i class='bx bx-equalizer'></i>
                                <span>Language of song</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-songs?do=list" class="sidebar-menu-dropdown">
                                <i class='bx bx-music' ></i>
                                <span>List Songs</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-songs?do=statistic" class="sidebar-menu-dropdown">
                                <i class='bx bx-cart'></i>
                                <span>Top sales</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-listener?do=view" class="sidebar-menu-dropdown">
                                <i class='bx bxs-hot'></i>
                                <span>Tracking listeners </span>
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- end li-->
                <li>
                    <a href="${pageContext.request.contextPath}/admin-artist?do=list" class="sidebar-menu-dropdown">
                        <i class='bx bxs-id-card'></i>
                        <span>Artist</span>
                    </a>
                </li>
                <!-- end li-->
                <li>
                    <a href="${pageContext.request.contextPath}/admin-supplier?do=list" class="sidebar-menu-dropdown">
                        <i class='bx bxs-ship'></i>
                        <span>Supplier</span>
                    </a>
                </li>
                <!-- end li-->
                <li>
                    <a href="${pageContext.request.contextPath}/admin-feedback?do=list">
                        <i class='bx bx-envelope'></i>
                        <span>Feedback</span>
                    </a>
                </li>
                <!-- end li-->
                <li class="sidebar-submenu">
                    <a href="#" class="sidebar-menu-dropdown">
                        <i class='bx bx-user'></i>
                        <span>Account</span>
                        <div class="dropdown-icon"></div>
                    </a>
                    <ul class="sidebar-menu sidebar-menu-dropdown-content">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin-account?do=list">
                                List account
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin-advertise?do=list" class="sidebar-menu-dropdown">
                        <i class='bx bx-planet'></i>
                        <span>Advertise</span>
                    </a>
                </li>
                <!-- end li-->
                <li>
                    <a href="">
                        <i class='bx bx-log-out bx-flip-horizontal'></i> 
                        <span>Sign out</span>
                    </a>
                </li>
                <!-- end li-->
            </ul>
            <!-- END SIDEBAR MENU -->
        </div>
        <!-- END SIDEBAR -->
        <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}">
        <!-- MAIN CONTENT -->
        <div class="main">
            <div class="modal">
                <div class="dialog">
                    <div class="modal-header flex-p">
                        <div class="flex woa"></div>
                        <h1 class="modal-title flex">Modal title</h1>
                        <div class="flex modal-close woa">
                            <button>
                                <svg aria-label="Đóng" class="_8-yf5 " fill="#262626" height="24" viewBox="0 0 48 48" width="24">
                                <path clip-rule="evenodd" d="M41.1 9.1l-15 15L41 39c.6.6.6 1.5 0 2.1s-1.5.6-2.1 0L24 26.1l-14.9 15c-.6.6-1.5.6-2.1 0-.6-.6-.6-1.5 0-2.1l14.9-15-15-15c-.6-.6-.6-1.5 0-2.1s1.5-.6 2.1 0l15 15 15-15c.6-.6 1.5-.6 2.1 0 .6.6.6 1.6 0 2.2z" fill-rule="evenodd"></path>
                                </svg>
                            </button>
                        </div>
                    </div>
                    <!-- model header -->
                    <div class="modal-body">
                        ...
                    </div>
                </div>
            </div>