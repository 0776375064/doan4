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
        <div class="section-top">My song list</div>
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <c:if test="${list.size() == 0}">
                        <div class="center">No data available...</div>
                    </c:if>
                    <table class="list-week-chart" cellpadding="0" cellspacing="0">
                        <c:forEach varStatus="loop" var="day" items="${list}">
                            <tr>
                                <td style="width:10%">
                                    <div class="chart-stt">${loop.index+1}</div>
                                </td>
                                <td style="width:35%;text-align: left;">
                                    <div class="chart-info">
                                        <div class="chart-thumbnail"><img
                                                src="${pageContext.request.contextPath}/storage/song/${controller.songInfo(day).thumbnail}"
                                                alt=""></div>
                                        <div class="chart-name">
                                            <a href="SongDetail?songID=${day}">
                                                <span class="chart-title">${controller.songInfo(day).songName}</span>
                                                <c:if test="${controller.songInfo(day).price == 0}">
                                                    <span class="sticker free">Free</span>
                                                </c:if>
                                            </a>
                                            <a href="ClientArtist?do=info&id=${controller.songInfo(day).getArtistID().getArtistID()}"><span class="chart-singer">${controller.songInfo(day).getArtistID().getNickname()}</span></a>
                                        </div>
                                    </div>
                                </td>
                                <td style="width:25%">
                                    <span><i class='red bx bx-headphone'></i></span>
                                    <span>${controller.songInfo(day).views}</span>
                                </td>
                                <td style="width:15%">
                                    <span class="chart-like">
                                        <i class='bx bx-heart red'></i> ${controller.countLikeofSong(controller.songInfo(day).songID)}
                                    </span>
                                </td>
                                <td>

                                    <a href="" data-id="${controller.songInfo(day).songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
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
