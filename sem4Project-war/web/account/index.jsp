<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<%    int id = Integer.parseInt(request.getParameter("id"));
    if (user_id == 0) {
        response.sendRedirect("index.jsp");
    }
%>
<!-- SECTION INFOR MUSIC -->
<div class="section section-sm" style="padding-top: -15px;">
    <div class="profile-container">
        <div class="profile-avatar">
            <img src="${pageContext.request.contextPath}/storage/profile/${account.avatar}" alt="">
        </div>
        <div class="profile-content">
            <h2 class="profile-name">
                <c:if test="${account.status == 0}">
                    ${account.username}
                </c:if> 
                <c:if test="${account.status == 1}">
                    <s>${account.username}</s>
                    </c:if> 
                    <c:if test="${account.gender == 'm'}">
                    <i class='bx bx-male-sign'></i>

                </c:if>
                <c:if test="${account.gender == 'f'}">
                    <i class='bx bx-female-sign'></i>
                </c:if>
            </h2>
                        <!--tuan da den day -->
            <style>
                .anhna{
                    height:18px!important;
                    width: 18px!important;
                }
            </style>
            
            <p> 
                <img class="anhna" width="10px"  style=""  src="${pageContext.request.contextPath}/storage/images/monny.png" alt="" /> ${account.money}$
                <a href="#" onclick="document.getElementById('addMony').style.display = 'block'">
                    <img class="anhna" src="${pageContext.request.contextPath}/storage/images/addmoney.png" alt=""/>
                </a>
            </p>
            <p><i class='bx bxs-detail'></i> ${account.fullname}</p>
            <p><i class='bx bxs-location-plus'></i> ${account.address}</p>
            <p><i class='bx bxs-bookmark-heart'></i> ${account.favourite}</p>
            <p class="profile-controls">
                <%
                    if (session.getAttribute("admin") != null && user_id != id) {
                %>
                <c:if test="${account.status == 0}">
                    <a href='' class='btn btn-block bg-red' data-id="${param.id}"><i class='bx bx-block'></i> Block</a>
                </c:if>
                <c:if test="${account.status == 1}">
                    <a href='' class='btn btn-block bg-yellow' data-id="${param.id}"><i class='bx bx-block'></i> Unblock</a>
                </c:if>
                <%
                    }
                    if (user_id == id) {
                        out.println("<a href='' class='btn btn-block tooltip' data-toggle='setting'><i class='bx bx-cog'></i> Setting</a>");
                        out.println("<a href='' class='btn btn-block tooltip' data-toggle='change'><i class='bx bx-cog'></i> Change password</a>");
                    }
                %>

            </p>
        </div>
    </div>
</div>

<div class="section section-sm">
    <div class="container">
        <div class="row">
            <div class="col-2 col-sm-12"></div>
            <div class="col-8 col-sm-12">
                <div class="profile-diary">
                    <div class="diary-title">
                        ${account.username}'s diary
                    </div>
                    <div class="diary-post">
                        <%
                            if (user_id == id) {
                        %>   
                        <a href="" class="btn btn-post tooltip" data-toggle="new-post"> New post</a>
                        <%}
                        %>
                    </div>
                </div>
                <div class="profile-diary-list">
                    <c:forEach var="story" items="${list}">
                        <div class="media">
                            <div class="media-left">
                                <a href=""><img class="is-50-50 is-rounded"
                                                src="${pageContext.request.contextPath}/storage/profile/${story.getAccountID().getAvatar()}"
                                                alt=""></a>
                            </div>
                            <div class="media-content">
                                <div class="media-title">
                                    <a href="">${story.getAccountID().getUsername()}</a>
                                    <c:if test="${story.feeling == 1}">
                                        <span class="media-react">
                                            - Feeling happy <img src="${pageContext.request.contextPath}/storage/images/smile.svg" alt="">
                                        </span>
                                    </c:if>
                                    <c:if test="${story.feeling == 2}">
                                        <span class="media-react">
                                            - Feeling sad <img src="${pageContext.request.contextPath}/storage/images/sad.svg" alt="">
                                        </span>
                                    </c:if>
                                    <c:if test="${story.feeling == 3}">
                                        <span class="media-react">
                                            - Feeling angry <img src="${pageContext.request.contextPath}/storage/images/angry.svg" alt="">
                                        </span>
                                    </c:if>
                                    <c:if test="${story.feeling == 4}">
                                        <span class="media-react">
                                            - Feeling excited <img src="${pageContext.request.contextPath}/storage/images/heart.svg" alt="">
                                        </span>
                                    </c:if>
                                    <c:if test="${story.feeling == 5}">
                                        <span class="media-react">
                                            - Feeling scary <img src="${pageContext.request.contextPath}/storage/images/omg.svg" alt="">
                                        </span>
                                    </c:if>
                                </div>
                                <div class="media-subtitle light-text text-message">
                                    <c:if test="${story.songID != 0}">
                                        <div class="media-play">
                                            <span>Shared with</span> 
                                            <a href="" class="btn-play btn-a" data-id="${story.songID}">
                                                <i class='bx bx-headphone bx-tada'></i>
                                                ${controller.songInfo(story.songID).songName} - ${controller.songInfo(story.songID).getArtistID().getNickname()}
                                            </a>
                                        </div>
                                    </c:if>
                                    <p>${story.message}</p>
                                </div>
                                <div class="diary-times">
                                    <i class='bx bx-stopwatch'></i> <i>${controller.convertToTime(story.created)}</i>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- col  -->
            <div class="col-2 col-sm-12">
            </div>
        </div>
        <!-- end row -->
    </div>
</div>

<!-- modal -->
<div class="modal" id="setting">
    <div class="modal-content">
        <div class="modal-header">
            <h3 class="modal-title">Setting profile</h3>
            <button class="modal-close"><i class='bx bx-x-circle'></i></button>
        </div>
        <div class="mdal-body">
            <form action="${pageContext.request.contextPath}/clientAccount" id="formSetting" class="form-setting" method="post" enctype="multipart/form-data">
                <div class="input avatar-setting">
                    <input type="file" name="avatarFile" id="avatarFile" class="hidden" onchange="previewFile()" accept="image/png, image/jpeg" />
                    <img src="${pageContext.request.contextPath}/storage/profile/${account.avatar}" class="avatar-click" onclick="$('#avatarFile').trigger('click');">
                </div>
                <div class="input">
                    <label>Full name</label>
                    <input type="text" name="fullname" value="${account.fullname}"/>
                </div>
                <div class="input">
                    <label>Phone number</label>
                    <input type="text" name="phone" value="${account.phone}"/>
                </div>
                <div class="input">
                    <label>Address</label>
                    <textarea class="form-control" rows="3" name="address">${account.address}</textarea>
                </div>
                <div class="input">
                    <label>Favorite</label>
                    <textarea class="form-control" rows="4" name="favorite">${account.favourite}</textarea>
                </div>
                <div class="input">
                    <label>Gender</label>
                    <select name="gender" clas="input">
                        <c:if test="${account.gender == 'm'}">
                            <option value="f">Female</option>
                            <option value="m" selected>Male</option>
                        </c:if>
                        <c:if test="${account.gender == 'f'}">
                            <option value="f" selected>Female</option>
                            <option value="m">Male</option>
                        </c:if>
                    </select>
                </div>
                <div class="input">
                    <button type="submit" name="submit" value="setting" class="btn btn-post">Update</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- modal -->
<div class="modal" id="new-post">
    <div class="modal-content">
        <div class="modal-header">
            <h3 class="modal-title">New post</h3>
            <button class="modal-close"><i class='bx bx-x-circle'></i></button>
        </div>
        <div class="modal-body">
            <form id="frmStory" method="post">
                <div class="form-new-post">
                    <p class="center">How do you feel today ?</p>
                    <div class="feeling">
                        <div class="feeling-item active" data-id="1">
                            <img src="${pageContext.request.contextPath}/storage/images/smile.svg" alt="">
                            <span>Happy</span>
                        </div>
                        <div class="feeling-item" data-id="2">
                            <img src="${pageContext.request.contextPath}/storage/images/sad.svg" alt="">
                            <span>Sad</span>
                        </div>
                        <div class="feeling-item" data-id="3">
                            <img src="${pageContext.request.contextPath}/storage/images/angry.svg" alt="">
                            <span>Angry</span>
                        </div>
                        <div class="feeling-item" data-id="4">
                            <img src="${pageContext.request.contextPath}/storage/images/heart.svg" alt="">
                            <span>Excited</span>
                        </div>
                        <div class="feeling-item" data-id="5">
                            <img src="${pageContext.request.contextPath}/storage/images/omg.svg" alt="">
                            <span>Scary</span>
                        </div>
                    </div>
                    <!-- end feeeling -->
                    <div class="div-story">
                        <textarea name="story" id="msgStory" placeholder="Your story" rows="5" required="required"></textarea>
                    </div>
                    <div>
                        <button class="btn-search-song"><i class='bx bx-search'></i> 
                            <span>Choose a song that expresses your mood</span>
                        </button>
                    </div>
                    <div>
                        <button type="submit" class="btn-add-post" name="submit" value="create">Post</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- change password -->
<div class="modal" id="change">
    <div class="modal-content">
        <div class="modal-header">
            <h3 class="modal-title">Change password</h3>
            <button class="modal-close"><i class='bx bx-x-circle'></i></button>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/clientAccount" id="formChange" class="form-setting" method="post">
                <div class="input">
                    <label>Old password</label>
                    <input type="password" name="oldpass" id="oldpass" value=""/>
                </div>
                <div class="input">
                    <label>New password</label>
                    <input type="password" name="newpass" id="newpass" value=""/>
                </div>
                <div class="input">
                    <button type="submit" name="submit" value="changepass" class="btn btn-post">Change</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- theme choose song -->
<div class="theme-choose-song">
    <div class="form-choose-song">
        <h2>Choose a song that expresses your mood</h2>
        <form action="" method="post">
            <input type="text" id="keyup" placeholder="Choose a song that expresses your mood">
            <i class='bx bx-search'></i>
        </form>
        <div class="form-result">
            <table class="form-result-table" cellpadding="0" cellspacing="0">
            </table>
        </div>
        <div class="form-choose-song-end">
            <button class="zm-btn-close"><i class='bx bx-arrow-back' ></i> Back</button>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/account.js"></script>
<%@include file="/includes/footer.jsp"%>
