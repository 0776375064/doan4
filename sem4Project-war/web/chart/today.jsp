<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<div class="section-chart">
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
                <div class="zm-navbar">
            <ul class="zm-nav-info">
                <li class="zm-nav-info-item active">
                    <a href="${pageContext.request.contextPath}/clientChart?do=today">Today</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientChart?do=week">Week</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientChart?do=month">Month</a>
                </li>
            </ul>
        </div>
        <div class="section-top">Rankings</div>
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <div class="chart-header">
                        <a href="">Today</a>
                    </div>
                    <table class="list-week-chart" cellpadding="0" cellspacing="0">
                        <c:forEach varStatus="loop" var="day" items="${chart}">
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
                                            <a href="SongDetail?songID=${day[0]}">
                                                <span class="chart-title">${controller.songInfo(day[0]).songName}</span>
                                                <c:if test="${controller.songInfo(day[0]).price == 0}">
                                                <span class="sticker free">Free</span>
                                            </c:if>
                                            </a>
                                            <a href="ClientArtist?do=info&id=${controller.songInfo(day[0]).getArtistID().getArtistID()}"><span class="chart-singer">${controller.songInfo(day[0]).getArtistID().getNickname()}</span></a>
                                        </div>
                                    </div>
                                </td>
                                <td style="width:25%">
                                    <span><i class='red bx bx-headphone'></i></span>
                                    <span>${controller.songInfo(day[0]).views}</span>
                                </td>
                                <td style="width:15%">
                                    <span class="chart-like">
                                        <i class='bx bx-heart red'></i> ${controller.countLikeofSong(controller.songInfo(day[0]).songID)}
                                    </span>
                                </td>
                                <td>
                                    <c:if test="${sessionScope.accountID != null}">
                                        <c:if test="${controller.songInfo(day[0]).price != 0}">
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == true}'>
                                                <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                            </c:if>
                                            <c:if test='${controller.alreadyBought(sessionScope.accountID,controller.songInfo(day[0]).songID) == false}'>
                                                <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order">Buy ${controller.songInfo(day[0]).price}$</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${controller.songInfo(day[0]).price == 0}">
                                            <a href="" data-id="${controller.songInfo(day[0]).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.accountID == null}">
                                         <a href="SongDetail?songID=${controller.songInfo(day[0]).songID}" class="btn-link btn-order btn-per">Buy ${controller.songInfo(day[0]).price}$</a>
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
<%@include file="/includes/footer.jsp"%>
