<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<div class="section-chart">
    <div class="bg-genre">
        <div class="genre-description">
            <h1>${genre.typeName}</h1>
            <p>${genre.description}</p>
        </div>
        <div class="genre-thumbnail">
            <img src="storage/genre/${genre.thumbnail}"/>
        </div>
    </div>
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
        <div class="section-top">List</div>
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <c:if test="${list.size() == 0}">
                        <div class="center">
                            No data available...
                        </div>
                    </c:if>
                    <table class="list-week-chart" cellpadding="0" cellspacing="0">
                        <c:forEach varStatus="loop" var="item" items="${list}">
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
                                                <a href="SongDetail?songID=${item.songID}" class="btn-link btn-order">Buy ${item.price}$</a>
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
                                            <a href="SongDetail?songID=${item.songID}" class="btn-link btn-order btn-per">Buy ${item.price}$</a>

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
<%@include file="/includes/footer.jsp"%>
