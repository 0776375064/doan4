<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<div class="artist-hero">
    <div class="blur" style="background-image: url('https://photo-resize-zmp3.zadn.vn/w360_r1x1_jpeg/avatars/0/3/033e843ca1e354b5298fc21816a633d4_1516949382.jpg');"></div>
    <div class="hero-body">
        <div class="columns">
            <div class="left column">
                <div>
                    <h3 class="title">${artist.nickname}</h3>
                    <div class="bio">
                        ${artist.description}
                    </div>
                    <div class="artist-action">
                        <button class="btn-action">
                            <i class='bx bxs-user-plus'></i> FOLLOW
                        </button>
                        <span class="followers">30K QUAN TÂM</span>
                    </div>
                    <div class="artist-small-infor">
                        <ul class="list-infor">
                            <li>${artist.fullname} -</li>
                            <li>${artist.birthday} -</li>
                            <li>${artist.nationality}</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right column">
                <div class="artist-hero-avatar">
                    <img src="${pageContext.request.contextPath}/storage/artist/${artist.avatar}" alt="">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section section-sm">
    <div class="sm-container">
        <div class="zm-navbar">
            <ul class="zm-nav-info">
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=${param.id}">Overview</a>
                </li>
                <li class="zm-nav-info-item active">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=album&id=${param.id}">Albums</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=songs&id=${param.id}">Songs</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=award&id=${param.id}">Award</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <c:if test="${empty list}">
                <div class="center">No data available..</div>
            </c:if>
            <c:forEach items="${list}" var="item">
                <div class="col-3 col-md-6 col-sm-12">
                    <a class="media-thumb" href="${pageContext.request.contextPath}/ClientArtist?do=albumdetails&id=${item.albumID}">
                        <img src="${pageContext.request.contextPath}/storage/album/${item.thumbnail}" alt="">
                    </a>
                    <div class="media">
                        <div class="media-left">
                            <a href=""><img class="is-40-40 is-rounded" src="${pageContext.request.contextPath}/storage/artist/${artist.avatar}" alt=""></a>
                        </div>
                        <div class="media-content">
                            <div class="media-title">
                                <a href="">${item.albumName}</a>
                            </div>
                            <div class="media-subtitle">
                                <span>${item.release}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- col 4 -->
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp"%>
