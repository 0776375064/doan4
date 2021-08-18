
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
    .chart-title:hover{
        color: red;
    }
    .chart-singer:hover{
        color: red;
    }
    .week-chart{
        overflow-y:hidden; 
        height:700px;
    }
    .week-chart:hover{
        overflow-y:scroll; 
        height:700px;
    }
    ::-webkit-scrollbar {
        width: 5px;
    }
    ::-webkit-scrollbar-track {
        background: #f1f1f1; 
    }
    ::-webkit-scrollbar-thumb {
        background: #888;
        border-radius:10px;
    }
    ::-webkit-scrollbar-thumb:hover {
        background: #555; 
    }

</style>
<div class="section-chart">
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
        <div class="section-top">New release</div>
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <div class="chart-header">
                        <a href="">Latest</a>
                    </div>
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
                                            <a  href="SongDetail?songID=${item.songID}&idArtist=${item.getArtistID().getArtistID()}">
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
                                    <!--  c:if test="{sessionScope.accountID != null}">-->
                                    <c:if test="${item.price != 0}">
                                        <c:if test='${controller.alreadyBought(sessionScope.accountID,item.songID) == true}'>
                                            <a href="" data-id="${item.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                        <c:if test='${controller.alreadyBought(sessionScope.accountID,item.songID) == false}'>
                                            <a onclick="loatOrder(${item.songID});document.getElementById('id01').style.display = 'block'"  href="#" class="btn-link btn-order">Buy ${item.price}$</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${item.price == 0}">
                                        <a href="" data-id="${item.songID}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                    </c:if>
                                    <!-- /c:if>-->
                                    <!--c:if test="{sessionScope.accountID == null}">
                                        <a href="" data-id="0" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                    /c:if>-->
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
<script>
    
</script>
<!-- END SECTION MUSIC -->
<%@include file="/includes/footer.jsp"%>
