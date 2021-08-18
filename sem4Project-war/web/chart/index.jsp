<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<div class="section section-sm">
    <div class="container">
        <div class="section-header">
            daily chart
        </div>
        <div class="col-12">
            <div id="customer-chart"></div>
        </div>
    </div>
</div>
<!-- SECTION MUSIC -->
<div class="section">
    <div class="container">
        <div class="music-container">
            <div class="chart-music">
                <table class="music-list-chart">
                    <c:forEach varStatus="loop" var="songItem" items="${chart}">
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
                                            src="${pageContext.request.contextPath}/storage/song/${songItem.getSongID().getThumbnail()}"
                                            alt=""></div>
                                    <div class="chart-name">
                                        <a href=""><span class="chart-title">${songItem.getSongID().getSongName()}</span></a>
                                        <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=${songItem.getSongID().getArtistID().getArtistID()}">
                                            <span class="chart-singer">${songItem.getSongID().getArtistID().getNickname()}</span>
                                        </a>
                                    </div>
                                </div>
                            </td>
                            <td style="width:25%">
                                <span><i class='red bx bx-headphone'></i></span>
                                <span>${songItem.getSongID().getViews()}</span>
                            </td>
                            <td style="width:15%">
                                <span class="chart-like">
                                    <i class='bx bx-heart red'></i> ${controller.countLikeofSong(songItem.getSongID().getSongID())}
                                </span>
                            </td>
                            <td style="width:10%" class="chart-action">
                                <c:if test="${sessionScope.accountID != null}">
                                    <c:if test="${songItem.getSongID().getPrice() != 0}">
                                        <c:if test='${controller.alreadyBought(sessionScope.accountID,songItem.getSongID().getSongID()) == true}'>
                                            <a href="" data-id="${songItem.getSongID().getSongID()}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                        </c:if>
                                        <c:if test='${controller.alreadyBought(sessionScope.accountID,songItem.getSongID().getSongID()) == false}'>
                                            <a href="Order?idSong=${songItem.getSongID().getSongID()}" class="btn-link btn-order">Buy ${songItem.getSongID().getPrice()} $</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${songItem.getSongID().getPrice() == 0}">
                                        <a href="" data-id="${songItem.getSongID().getSongID()}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${sessionScope.accountID == null}">
                                    <c:if test="${songItem.getSongID().getPrice() == 0}">
                                        <a href="" data-id="${songItem.getSongID().getSongID()}" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                    </c:if>
                                    <c:if test="${songItem.getSongID().getPrice() != 0}">
                                        <a href="Order?idSong=${songItem.getSongID().getSongID()}" class="btn-link btn-order">Buy ${songItem.getSongID().getPrice()}$</a>
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
<!-- APEX CHART -->
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
<script>
    let customer_options = {
        series: [],
        colors: ['#ff5159', '#26c5ff', '#18d657', ],
        chart: {
            height: 350,
            type: 'line',
        },
        dataLabels: {
            enabled: false
        },
        fill: {
            type: "solid",
        },
        stroke: {
            curve: 'smooth',
            width: 5
        },
        xaxis: {
            categories: [],
        },
        legend: {
            position: 'top'
        },
        theme: {
            mode: 'dark'
        },
        markers: {
            size: 4,
            strokeWidth: 2,
            hover: {
                size: 7,
            }
        }
    }
    let customer_chart = new ApexCharts(document.querySelector("#customer-chart"), customer_options);
    customer_chart.render();
    $.ajax({
        url: $("#url").val() + "/player?do=chart",
        dataType: 'json',
        type: 'get',
        cache: false,
        success: function (result) {
            console.log(result);
            let data = {
                series: result.value,
                xaxis: {
                    categories: result.labels,
                },
            };
            customer_chart.updateOptions(data);
        },
        error: function () {
            console.log("Occur error wehn fetch data.......");
        }
    });

</script>
<%@include file="/includes/footer.jsp"%>
