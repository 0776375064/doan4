<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<div class="section-sm sm-container">
    <div class="section-header">${category.categoryName}</div>
    <div class="zm-navbar">
        <div class="form-search">
            <form action="${pageContext.request.contextPath}/clientSearch" method="get">
                <input type="text" value="${param.s}" name="s" placeholder="Enter song, artist, news ...">
                <button type="submit" name="submit" value="search" class="btn-search"><i class='bx bx-search'></i></button>
            </form>
        </div>
    </div>
    <div class="empty-space"></div>
</div>
    
<!--Put content here-->
<div class="section-chart">
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <c:if test="${listMusic.size() == 0}">
                        <div class="center">No data available</div>
                    </c:if>
                    <table class="list-week-chart" cellpadding="0" cellspacing="0">
                        <c:forEach varStatus="loop" var="item" items="${listMusic}">
                            <tr>
                                <td style="width:10%">
                                    <div class="chart-stt">${loop.index+1}</div>
                                </td>
                                <td style="width:35%;text-align: left;">
                                    <div class="chart-info">
                                        <div class="chart-thumbnail"><img
                                                src="${pageContext.request.contextPath}/storage/song/${item.thumbnail}"
                                                alt=""></div>
                                        <div class="chart-name">
                                            <a href="SongDetail?songID=${item.songID}">
                                                <span class="chart-title">${item.songName}</span>
                                                <c:if test="${item.price == 0}">
                                                    <span class="sticker free">Free</span>
                                                </c:if>
                                            </a>
                                            <a href="ClientArtist?do=info&id=${item.getArtistID().getArtistID()}"><span class="chart-singer">${item.getArtistID().getNickname()}</span></a>
                                        </div>
                                    </div>
                                </td>
                                <td style="width:25%">
                                    <span><i class='red bx bx-headphone'></i></span>
                                    <span>${item.views}</span>
                                </td>
                                <td style="width:15%">
                                    <span class="chart-like">
                                        <i class='bx bx-heart red'></i> ${controller.countLikeofSong(item.songID)}
                                    </span>
                                </td>
                                <td>
                                    <c:if test="${sessionScope.accountID != null}">
                                        <c:if test="${item.price != 0}">
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,item.songID) == true}'>
                                                <a href="" data-id="${item.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                            </c:if>
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,item.songID) == false}'>
                                                <a href="SongDetail?songID=${item.songID}" class="btn-link btn-order">Buy ${item.price} $</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${item.price == 0}">
                                            <a href="" data-id="${item.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.accountID == null}">
                                        <c:if test="${item.price == 0}">
                                            <a href="" data-id="${item.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                        <c:if test="${item.price != 0}">
                                            <a href="SongDetail?songID=${item.songID}" class="btn-link btn-order btn-per">Buy ${item.price} $</a>
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <!-- col - 4 -->
        </div>
    </div>
</div>
<!-- END SECTION MUSIC -->
<div class="empty-space"></div>
<div class="sm-container">
    <div class="row">
        <div class="col-12">
            <div class="row" style="margin-left:-10px;margin-right:-10px">
                <c:forEach var="new" items="${news}">
                    <div class="col-3 col-m-4 col-sm-5">
                        <a href="${pageContext.request.contextPath}/clientNews?do=view&id=${new.newID}" class="post-thumb">
                            <img src="${pageContext.request.contextPath}/storage/news/${new.thumbnail}"
                                 alt="">
                        </a>
                    </div>
                    <div class="col-9 col-m-8 col-sm-7">
                        <div class="post-category">
                            <a href="${pageContext.request.contextPath}/clientNews?do=category&id=${new.getCategoryID().getCategoryID()}">${new.getCategoryID().getCategoryName()}</a>
                        </div>
                        <div class="post-title">
                            <a href="${pageContext.request.contextPath}/clientNews?do=view&id=${new.newID}">
                                <h3 class="sm-text">${new.title}</h3>
                            </a>
                        </div>
                        <p class="times">
                            <span style="font-size:12px;">${new.createdAt}</span>
                        </p>
                        <div class="simple-text">
                            <span>${controller.subWord(new.description)}</span>
                        </div>
                    </div>
                    <!-- div -->
                </c:forEach>
            </div>
        </div>
        <!-- recent div -->
    </div>
    <div class="empty-space"></div>
    <div class="artist-slide carousel-nav-center owl-carousel">
        <!-- MOVIE ITEM -->
        <c:forEach var="artistItem" items="${artists}">
            <div class="suggest-artist">
                <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=${artistItem.artistID}" class="suggest-artist-avatar">
                    <img src="${pageContext.request.contextPath}/storage/artist/${artistItem.avatar}" alt="">
                </a>
                <p><a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=${artistItem.artistID}">${artistItem.nickname}</a></p>
                <div class="artist_followers center">${controller.countFollowers(artistItem.artistID)} Follower</div>
                <div class="artist-button">
                    <button class="is-small fl-btn" data-id="${artistItem.artistID}">
                        <i class='bx bx-user-plus'></i>
                        <c:if test="${sessionScope.accountID == null}">
                            <span class="fl-text">FOLLOW</span>
                        </c:if>
                        <c:if test="${sessionScope.accountID != null}">
                            <c:if test="${controller.checkIfFollow(sessionScope.accountID, artistItem.artistID) == true}">
                                <span class="fl-text">Unfollow</span>
                            </c:if>
                            <c:if test="${controller.checkIfFollow(sessionScope.accountID, artistItem.artistID) == false}">
                                <span class="fl-text">Follow</span>
                            </c:if>
                        </c:if>
                    </button>
                </div>
            </div>
        </c:forEach>
        <!-- END MOVIE ITEM -->
    </div>
</div>
<%@include file="/includes/footer.jsp"%>
