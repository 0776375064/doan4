<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">
        Dashboard
    </div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-3 col-md-6 col-sm-12">
            <a href="" style="display:block;">
                <div class="box box-hover bg-purple">
                    <!-- COUNTER -->
                    <div class="counter">
                        <div class="counter-title">
                            Order
                        </div>
                        <div class="counter-info">
                            <div class="counter-count">
                                ${controller.countOrder()}
                            </div>
                            <i class='bx bx-shopping-bag'></i>
                        </div>
                    </div>
                    <!-- END COUNTER -->
                </div>
            </a>
        </div>
        <div class="col-3 col-md-6 col-sm-12">
            <a href="" style="display:block;">
                <div class="box box-hover bg-green">
                    <!-- COUNTER -->
                    <div class="counter">
                        <div class="counter-title">
                            Artist
                        </div>
                        <div class="counter-info">
                            <div class="counter-count">
                                ${controller.countArtist()}
                            </div>
                            <i class='bx bxs-id-card'></i>
                        </div>
                    </div>
                    <!-- END COUNTER -->
                </div>
            </a>
        </div>
        <div class="col-3 col-md-6 col-sm-12">
            <a href="" style="display:block;">
                <div class="box box-hover bg-red">
                    <!-- COUNTER -->
                    <div class="counter">
                        <div class="counter-title">
                            Song
                        </div>
                        <div class="counter-info">
                            <div class="counter-count">
                                ${controller.countSong()}
                            </div>
                           <i class='bx bx-music'></i>
                        </div>
                    </div>
                    <!-- END COUNTER -->
                </div>
            </a>
        </div>
        <div class="col-3 col-md-6 col-sm-12">
            <a href="" style="display:block;">
                <div class="box box-hover bg-yellow">
                    <!-- COUNTER -->
                    <div class="counter">
                        <div class="counter-title">
                            Album
                        </div>
                        <div class="counter-info">
                            <div class="counter-count">
                                ${controller.countAlbum()}
                            </div>
                            <i class='bx bx-photo-album'></i>
                        </div>
                    </div>
                    <!-- END COUNTER -->
                </div>
            </a>
        </div>
        <div class="col-3 col-md-6 col-sm-12">
            <div class="box box-hover bg-brown">
                <!-- COUNTER -->
                <div class="counter">
                    <div class="counter-title">
                        Category
                    </div>
                    <div class="counter-info">
                        <div class="counter-count">
                            ${controller.countCategory()}
                        </div>
                        <i class='bx bx-category'></i>
                    </div>
                </div>
                <!-- END COUNTER -->
            </div>
        </div>
        <div class="col-3 col-md-6 col-sm-12">
            <div class="box box-hover bg-pink">
                <!-- COUNTER -->
                <div class="counter">
                    <div class="counter-title">
                        News
                    </div>
                    <div class="counter-info">
                        <div class="counter-count">
                            ${controller.countNew()}
                        </div>
                        <i class='bx bx-news'></i>
                    </div>
                </div>
                <!-- END COUNTER -->
            </div>
        </div>
        <div class="col-3 col-md-6 col-sm-12">
            <div class="box box-hover bg-blue">
                <!-- COUNTER -->
                <div class="counter">
                    <div class="counter-title">
                        Account
                    </div>
                    <div class="counter-info">
                        <div class="counter-count">
                            ${controller.countAccount()}
                        </div>
                        <i class='bx bx-user'></i>
                    </div>
                </div>
                <!-- END COUNTER -->
            </div>
        </div>
        <div class="col-3 col-md-6 col-sm-12">
            <div class="box box-hover bg-gray">
                <!-- COUNTER -->
                <div class="counter">
                    <div class="counter-title">
                        Feedback
                    </div>
                    <div class="counter-info">
                        <div class="counter-count">
                            0
                        </div>
                        <i class='bx bxs-error-circle'></i>
                    </div>
                </div>
                <!-- END COUNTER -->
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
