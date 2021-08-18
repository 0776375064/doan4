<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp"%>
<!-- HERO SECTION -->
<div class="hero-section">
    <!-- HERO SLIDE -->
    <div class="hero-slide">
        <div class="owl-carousel carousel-nav-center" id="hero-carousel">
            <!-- SLIDE ITEM -->
            <c:forEach items="${controller.listAdvertise()}" var="ads">
                <div class="hero-slide-item">
                    <img src="${pageContext.request.contextPath}/storage/banner/${ads.thumbnail}" alt="">
                    <div class="overlay"></div>
                    <div class="hero-slide-item-content">
                        <div class="item-content-wraper">
                            <div class="item-content-title top-down">
                                ${ads.advertiseName}
                            </div>
                            <div class="item-content-description top-down delay-2">
                                ${ads.description}
                            </div>
                            <div class="item-action top-down delay-4">
                                <a href="${ads.url}" class="btn btn-hover">
                                    <i class="bx bxs-right-arrow"></i>
                                    <span>Watch now</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- END SLIDE ITEM -->
        </div>
    </div>
    <!-- END HERO SLIDE -->
</div>
<!-- END HERO SECTION -->

<!-- LATEST SERIES SECTION -->
<div class="section">
    <div class="container">
        <div class="section-header">
            news
        </div>
        <div class="news-slide carousel-nav-center owl-carousel">
            <!-- MOVIE ITEM -->
            <c:forEach var="new" items="${controller.listNews()}">
                <a href="${pageContext.request.contextPath}/clientNews?do=view&id=${new.newID}" class="news-item">
                    <img src="${pageContext.request.contextPath}/storage/news/${new.thumbnail}" alt="">
                    <div class="news-item-content">
                        <div class="news-item-catgeory">
                            <span class="span-category">${new.getCategoryID().getCategoryName()}</span>
                            <span class="news-item-times">${controller.formatDate(new.createdAt)}</span>
                        </div>
                        <div class="news-item-title">${new.title}</div>
                    </div>
                </a>

            </c:forEach>
        </div>
    </div>
</div>
<!-- END LATEST SERIES SECTION -->

<!-- SECTION MUSIC -->
<div class="section">
    <div class="container">
        <div class="section-header">
            NEW RELEASE
        </div>
        <div class="music-container">
            <div class="chart-music">
                <table class="music-list-chart">
                    <c:forEach varStatus="loop" var="songItem" items="#{controller.TopSongs()}">
                        <tr>
                            <td style="width:10%">
                                <c:if test="${loop.index == 0}">
                                    <div class="chart-stt stt-top-1">${loop.index + 1}St</div>
                                </c:if>
                                <c:if test="${loop.index == 1}">
                                    <div class="chart-stt stt-top-2">${loop.index + 1}Rd</div>
                                </c:if>
                                <c:if test="${loop.index == 2}">
                                    <div class="chart-stt stt-top-3">${loop.index + 1}Nd</div>
                                </c:if>
                                <c:if test="${loop.index > 2}">
                                    <div class="chart-stt">${loop.index + 1}</div>
                                </c:if>
                            </td>
                            <td style="width:35%;text-align: left;">
                                <div class="chart-info">
                                    <div class="chart-thumbnail"><img
                                            src="${pageContext.request.contextPath}/storage/song/${songItem.thumbnail}"
                                            alt=""></div>
                                    <div class="chart-name">
                                        <a href="SongDetail?songID=${songItem.songID}">
                                            <span class="chart-title">${songItem.songName}</span>
                                            <c:if test="${songItem.price == 0}">
                                                <span class="sticker free">Free</span>
                                            </c:if>
                                        </a>
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
                                            <a onclick="document.getElementById('id01').style.display = 'block';loatOrder(${songItem.songID})" class="btn-link btn-order">Buy ${songItem.price}$</a>
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
                                        <a href="SongDetail?songID=${songItem.songID}" class="btn-link btn-order btn-per">Buy ${songItem.price}$</a>

                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- END SECTION MUSIC -->

<!-- SECTION GENE MUSIC -->
<div class="section">
    <div class="container">
        <div class="section-header">
            music genre
        </div>
        <div class="row">
            <c:forEach items="${controller.listGenre()}" var="genre">
                <div class="col-4 col-md-6 col-sm-6">
                    <a href="${pageContext.request.contextPath}/clientGenre?do=view&id=${genre.typeID}" class="gennre-music">
                        <img src="${pageContext.request.contextPath}/storage/genre/${genre.thumbnail}"/>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<!-- section chart song -->
<div class="section-chart">
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
        <div class="section-top">Rankings</div>
        <div class="row">
            <div class="col-4 col-md-6 col-sm-12">
                <div class="week-chart">
                    <div class="chart-header">
                        <a href="">Today</a>
                    </div>
                    <table class="list-week-chart" cellpadding="0" cellspacing="0">
                        <c:forEach varStatus="loop" var="day" items="${controller.topMusicInday(0)}">
                            <tr>
                                <td style="width:10%">
                                    <div class="chart-stt">${loop.index+1}</div>
                                </td>
                                <td style="width:35%;text-align: left;">
                                    <div class="chart-info">
                                        <div class="chart-thumbnail"><img
                                                src="${pageContext.request.contextPath}/storage/song/${controller.songInfo(day[0]).thumbnail}"
                                                alt=""></div>
                                        <div class="chart-name">
                                            <a href="SongDetail?songID=${day[0]}"><span class="chart-title">${controller.songInfo(day[0]).songName}</span></a>
                                            <a href="ClientArtist?do=info&id=${controller.songInfo(day[0]).getArtistID().getArtistID()}"><span class="chart-singer">${controller.songInfo(day[0]).getArtistID().getNickname()}</span></a>
                                        </div>
                                    </div>
                                </td>
                                <td style="width:10%" class="chart-action">
                                    <c:if test="${sessionScope.accountID != null}">
                                        <c:if test="${controller.songInfo(day[0]).price != 0}">
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == true}'>
                                                <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                            </c:if>
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == false}'>
                                                <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order">Buy ${controller.songInfo(day[0]).price} $</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${controller.songInfo(day[0]).price == 0}">
                                            <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.accountID == null}">
                                        <c:if test="${controller.songInfo(day[0]).price == 0}">
                                            <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                        <c:if test="${controller.songInfo(day[0]).price != 0}">
                                            <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order btn-per">Buy ${controller.songInfo(day[0]).price} $</a>

                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="is-center">
                        <a href="${pageContext.request.contextPath}/clientChart?do=today" class="show-all">See more</a>
                    </div>
                </div>
            </div>
            <!-- col - 4 -->
            <div class="col-4 col-md-6 col-sm-12">
                <div class="week-chart">
                    <div class="chart-header">
                        <a href="">Weekly</a>
                    </div>
                    <table class="list-week-chart" cellpadding="0" cellspacing="0">
                        <c:forEach varStatus="loop" var="day" items="${controller.topMusicInWeek(0)}">
                            <tr>
                                <td style="width:10%">
                                    <div class="chart-stt">${loop.index+1}</div>
                                </td>
                                <td style="width:35%;text-align: left;">
                                    <div class="chart-info">
                                        <div class="chart-thumbnail"><img
                                                src="${pageContext.request.contextPath}/storage/song/${controller.songInfo(day[0]).thumbnail}"
                                                alt=""></div>
                                        <div class="chart-name">
                                            <a href="SongDetail?songID=${day[0]}"><span class="chart-title">${controller.songInfo(day[0]).songName}</span></a>
                                            <a href="ClientArtist?do=info&id=${controller.songInfo(day[0]).getArtistID().getArtistID()}"><span class="chart-singer">${controller.songInfo(day[0]).getArtistID().getNickname()}</span></a>
                                        </div>
                                    </div>
                                </td>
                                <td style="width:10%" class="chart-action">
                                    <c:if test="${sessionScope.accountID != null}">
                                        <c:if test="${controller.songInfo(day[0]).price != 0}">
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == true}'>
                                                <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                            </c:if>
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == false}'>
                                                <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order">Buy ${controller.songInfo(day[0]).price} $</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${controller.songInfo(day[0]).price == 0}">
                                            <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.accountID == null}">
                                        <c:if test="${controller.songInfo(day[0]).price == 0}">
                                            <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                        <c:if test="${controller.songInfo(day[0]).price != 0}">
                                            <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order btn-per">Buy ${controller.songInfo(day[0]).price} $</a>

                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="is-center">
                        <a href="${pageContext.request.contextPath}/clientChart?do=week" class="show-all">See more</a>
                    </div>
                </div>
            </div>
            <!-- col - 4 -->
            <div class="col-4 col-md-6 col-sm-12">
                <div class="week-chart">
                    <div class="chart-header">
                        <a href="">Monthly</a>
                    </div>
                    <table class="list-week-chart" cellpadding="0" cellspacing="0">
                        <c:forEach varStatus="loop" var="day" items="${controller.topMusicInMonth(0)}">
                            <tr>
                                <td style="width:10%">
                                    <div class="chart-stt">${loop.index+1}</div>
                                </td>
                                <td style="width:35%;text-align: left;">
                                    <div class="chart-info">
                                        <div class="chart-thumbnail"><img
                                                src="${pageContext.request.contextPath}/storage/song/${controller.songInfo(day[0]).thumbnail}"
                                                alt=""></div>
                                        <div class="chart-name">
                                            <a href="SongDetail?songID=${day[0]}"><span class="chart-title">${controller.songInfo(day[0]).songName}</span></a>
                                            <a href="ClientArtist?do=info&id=${controller.songInfo(day[0]).getArtistID().getArtistID()}"><span class="chart-singer">${controller.songInfo(day[0]).getArtistID().getNickname()}</span></a>
                                        </div>
                                    </div>
                                </td>
                                <td style="width:10%" class="chart-action">
                                    <c:if test="${sessionScope.accountID != null}">
                                        <c:if test="${controller.songInfo(day[0]).price != 0}">
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == true}'>
                                                <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                            </c:if>
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == false}'>
                                                <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order">Buy ${controller.songInfo(day[0]).price} $</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${controller.songInfo(day[0]).price == 0}">
                                            <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.accountID == null}">
                                        <c:if test="${controller.songInfo(day[0]).price == 0}">
                                            <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                        <c:if test="${controller.songInfo(day[0]).price != 0}">
                                            <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order btn-per">Buy ${controller.songInfo(day[0]).price} $</a>

                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="is-center">
                        <a href="${pageContext.request.contextPath}/clientChart?do=month" class="show-all">See more</a>
                    </div>
                </div>
            </div>
            <!-- col - 4 -->
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp"%>
