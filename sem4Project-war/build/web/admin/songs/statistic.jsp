<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/daterangepicker.css" />
<%
    int pageUrl = 1; // declare the variable outside the if else
    if (request.getParameter("page") != null) {
        pageUrl = Integer.parseInt(request.getParameter("page"));
    }
%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">Report</div>
</div>
<div class="main-content">
    <div class="row">
         <div class="col-3 col-sm-4">
            <a class="btn btn-default bg-blue light-text btn-print"><i class='bx bx-printer'></i> Print</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="box-search">
                <form action="GET" method="GET" class="frm">
                    <div class="form-group">
                        <span><i class='bx bx-calendar'></i></span>
                        <input id="reportrange" type="text" readonly="readonly" class="form-input"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 col-md-12 col-sm-12">
            <!-- CUSTOMERS CHART -->
            <div class="box f-height">
                <div class="box-header">
                    Chart
                </div>
                <div class="box-body">
                    <div id="customer-chart"></div>
                </div>
            </div>
            <!-- END CUSTOMERS CHART -->
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="box" id="statis">
                <div class="box-header">List</div>
                <div class="box-body overflow-scroll">
                    <table cellspacing="0">
                        <thead>
                            <tr>
                                <th class="center">Name</th>
                                <th>Artist</th>
                                <th>Soft of</th>
                                <th>Language</th>
                                <th>Release date</th>
                                <th>Album</th>
                                <th>Price</th>
                                <th class="center">Sales</th>
                            </tr>
                        </thead>
                        <tbody id="result">
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item[0]}">
                                    <td class="avatar">
                                        <img src="${pageContext.request.contextPath}/storage/song/${controller.songInfo(item[0]).thumbnail}"/><br/>
                                        <span>${controller.songInfo(item[0]).songName}</span>
                                    </td>
                                    <td>${controller.songInfo(item[0]).getArtistID().nickname}</td>
                                    <td>${controller.songInfo(item[0]).getTypeID().getTypeName()}</td>
                                    <td>${controller.songInfo(item[0]).getLangID().getLangName()}</td>
                                    <td>${controller.songInfo(item[0]).release}</td>
                                    <c:if test="${controller.songInfo(item[0]).typeAlbum == 0}">
                                        <td>Single</td>
                                    </c:if>
                                    <c:if test="${controller.songInfo(item[0]).typeAlbum == 1}">
                                        <td>${controller.songInfo(item[0]).getAlbumID().getAlbumName()}</td>
                                    </c:if>
                                    <td>${controller.songInfo(item[0]).price} $</td>
                                    <td><span class="center">${item[1]}</span></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- APEX CHART -->
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
<script>
    let customer_options = {
        series: [{
                name: "Order",
                data: []}],
        colors: ['#6ab04c'],
        chart: {
            height: 350,
            type: 'line',
        },
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth'
        },
        xaxis: {categories: [],
        },
        legend: {position: 'top'
        }
    }

    let customer_chart = new ApexCharts(document.querySelector("#customer-chart"), customer_options)
    customer_chart.render()
</script>
<script type="text/javascript">
    var startD;
    var endD;
    $('#reportrange').daterangepicker({
        linkedCalendars: false,
        "showDropdowns": true,
        ranges: {
            'Today': [moment(), moment()],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last week': [moment().subtract(6, 'days'), moment()],
            '30 days ago': [moment().subtract(29, 'days'), moment()],
            'This month': [moment().startOf('month'), moment().endOf('month')],
            'Last month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        "startDate": moment(),
        "endDate": moment()
    }, function (start, end, label) {
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
        startD = start.format('YYYY-MM-DD');
        endD = end.format('YYYY-MM-DD');
        $.ajax({
            url: $("#url").val() + "/admin-songs?do=report",
            dataType: 'text',
            type: 'get',
            cache: false,
            data: {
                fdate: startD,
                tdate: endD
            },
            success: function (result) {
                $("#result").html(result);
            },
            error: function () {
                alert("Error.....")
            }
        });
        $.ajax({
            url: $("#url").val() + "/admin-songs?do=getDate",
            dataType: 'json',
            type: 'get',
            cache: false,
            data: {
                fdate: startD,
                tdate: endD
            },
            success: function (result) {
                let data = {
                    series: [
                        {
                            data: result.value
                        }
                    ],
                    xaxis: {
                        categories: result.list,
                    },
                };
                customer_chart.updateOptions(data);
            },
            error: function () {
                alert("Error.....")
            }
        });
    });
    $(function () {
        $('.btn-print').on('click', function () {
            $.print("#statis");
        });
    });
</script>
<%@include file="../footer.jsp"%>
