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
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=album&id=${param.id}">Albums</a>
                </li>
                <li class="zm-nav-info-item active">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=songs&id=${param.id}">Songs</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=award&id=${param.id}">Award</a>
                </li>
            </ul>
        </div>
        <div class="list">
            <div class="section-header">ALL SONGS</div>
            <c:if test="${empty list}">
                <div class="center">No data available....</div>
            </c:if>
            <div class="chart-music">
                <table class="music-list-chart">
                    <c:forEach var="songItem" items="${list}">
                        <tr>
                            <td style="width:35%;text-align: left;">
                                <div class="chart-info">
                                    <div class="chart-thumbnail"><img
                                            src="${pageContext.request.contextPath}/storage/song/${songItem.thumbnail}"
                                            alt=""></div>
                                    <div class="chart-name">
                                        <a href=""><span class="chart-title">${songItem.songName}</span></a>
                                        <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=${songItem.getArtistID().getArtistID()}">
                                            <span class="chart-singer">${songItem.getArtistID().getNickname()}</span>
                                        </a>
                                    </div>
                                </div>
                            </td>
                            <td style="width:25%">
                                <span><i class='red bx bx-headphone'></i></span>
                                <span>${songItem.views}</span>
                            </td>
                            <td style="width:15%">
                                <span class="chart-like">
                                    <i class='bx bx-heart red'></i> ${controller.countLikeofSong(songItem.songID)}
                                </span>
                            </td>
                            <td style="width:10%" class="chart-action">
                                <c:if test="${sessionScope.accountID != null}">
                                    <c:if test="${songItem.price != 0}">
                                        <c:if test='${controller.alreadyBought(sessionScope.accountID,songItem.songID) == true}'>
                                            <a href="" data-id="${songItem.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                        <c:if test='${controller.alreadyBought(sessionScope.accountID,songItem.songID) == false}'>
                                            <a href="SongDetail?songID=${songItem.songID}" class="btn-link btn-order">Buy ${item.price} $</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${songItem.price == 0}">
                                        <a href="" data-id="${songItem.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${sessionScope.accountID == null}">
                                    <c:if test="${songItem.price == 0}">
                                        <a href="" data-id="${songItem.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                    </c:if>
                                    <c:if test="${songItem.price != 0}">
                                        <a href="SongDetail?songID=${songItem.songID}" class="btn-link btn-order btn-per">Buy ${songItem.price} $</a>

                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="empty-space"></div>
        </div>
        <!-- LATEST SERIES SECTION -->
        <div class="section-header">
            May you like
        </div>
        <div class="artist-slide carousel-nav-center owl-carousel">
            <!-- MOVIE ITEM -->
            <c:forEach var="artistItem" items="${artists}">
                <div class="suggest-artist">
                    <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=${artistItem.artistID}" class="suggest-artist-avatar">
                        <img src="${pageContext.request.contextPath}/storage/artist/${artistItem.avatar}" alt="">
                    </a>
                    <p><a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=${artistItem.artistID}">${artistItem.nickname}</a></p>
                    <div class="artist-button">
                        <button class="is-small"><i class='bx bx-user-plus'></i>FOLLOW</button>
                    </div>
                </div>
            </c:forEach>

            <!-- END MOVIE ITEM -->
        </div>
        <!-- END LATEST SERIES SECTION -->
    </div>
</div>
<%@include file="/includes/footer.jsp"%>
