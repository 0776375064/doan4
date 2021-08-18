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
<%    int pageUrl = 1; // declare the variable outside the if else
    if (request.getParameter("page") != null) {
        pageUrl = Integer.parseInt(request.getParameter("page"));
    }
%>
<div class="modal-duy">
    <div class="dialog-lg">
        <div class="modal-header flex-p">
            <div class="flex woa"></div>
            <h1 class="modal-title flex">Tracking listeners of <span id='sTitle' style="margin-left:4px;"></span></h1>
            <div class="flex modal-duy-close woa no-print">
                <button>
                    <svg aria-label="Đóng" class="_8-yf5 " fill="#262626" height="24" viewBox="0 0 48 48" width="24">
                    <path clip-rule="evenodd" d="M41.1 9.1l-15 15L41 39c.6.6.6 1.5 0 2.1s-1.5.6-2.1 0L24 26.1l-14.9 15c-.6.6-1.5.6-2.1 0-.6-.6-.6-1.5 0-2.1l14.9-15-15-15c-.6-.6-.6-1.5 0-2.1s1.5-.6 2.1 0l15 15 15-15c.6-.6 1.5-.6 2.1 0 .6.6.6 1.6 0 2.2z" fill-rule="evenodd"></path>
                    </svg>
                </button>
            </div>
        </div>
        <!-- model header -->
        <div class="modal-body">
            <div class="row">
                <div class="col-4 col-sm-12">
                    <div class="box-search">
                        <form action="GET" method="GET" class="frm">
                            <div class="form-group">
                                <span><i class='bx bx-calendar'></i></span>
                                <input id="reportrange1" type="text" readonly="readonly" class="form-input"/>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-3 col-sm-6 no-print">
                    <a class="btn btn-default bg-blue light-text btn-print" data-id="print1"><i class='bx bx-printer'></i> Print</a>
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
                            <div id="listenersChart"></div>
                        </div>
                    </div>
                    <!-- END CUSTOMERS CHART -->
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="box">
                        <div class="box-body overflow-scroll">
                            <table cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Tracking date</th>
                                        <th>Listeners</th>
                                    </tr>
                                </thead>
                                <tbody id="result1">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">Tracking listeners</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-3 col-sm-4">
            <a class="btn btn-default bg-blue light-text btn-print" data-id="print"><i class='bx bx-printer'></i> Print</a>
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
                                <th>Listeners</th>
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
                                    <td>${controller.formatDate(controller.songInfo(item[0]).release)}</td>
                                    <c:if test="${controller.songInfo(item[0]).typeAlbum == 0}">
                                        <td>Single</td>
                                    </c:if>
                                    <c:if test="${controller.songInfo(item[0]).typeAlbum == 1}">
                                        <td>${controller.songInfo(item[0]).getAlbumID().getAlbumName()}</td>
                                    </c:if>
                                    <td>${controller.songInfo(item[0]).price} $</td>
                                    <td><a href="" class="listeners" data-id="${controller.songInfo(item[0]).songID}"><i class='bx bx-info-circle'></i> ${item[1]}</a></td>
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
                name: "Listen",
                data: []}],
        colors: ['#ff7940'],
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

    let customer_chart = new ApexCharts(document.querySelector("#listenersChart"), customer_options)
    customer_chart.render()
</script>
<script type="text/javascript">
    var startD;
    var endD;
    var id;
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
            url: $("#url").val() + "/admin-listener?do=top",
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
        ;
    });
    // click view modal tracking
    $(document).on('click', '.listeners', function (e) {
        e.preventDefault();
        id = $(this).attr("data-id");
        $.ajax({
            url: $("#url").val() + "/admin-listener?do=tracking",
            dataType: 'text',
            type: 'get',
            cache: false,
            data: {
                id: id,
                fdate: startD,
                tdate: endD
            },
            success: function (result) {
                $("#result1").html(result);
            },
            error: function () {
                alert("Error.....")
            }
        });
        $.ajax({
            url: $("#url").val() + "/admin-listener?do=jsonTracking",
            dataType: 'json',
            type: 'get',
            cache: false,
            data: {
                id: id,
                fdate: startD,
                tdate: endD
            },
            success: function (result) {
                $("#sTitle").html(" " +result.song + " by " + result.artist);
                let data = {
                    series: [
                        {
                            data: result.values
                        }
                    ],
                    xaxis: {
                        categories: result.labels,
                    },
                };
                customer_chart.updateOptions(data);
            },
            error: function () {
                console.log("Occur error when request show chart");
            }
        });
        $(".modal-duy").addClass("open");

    });
    $('#reportrange1').daterangepicker({
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
            url: $("#url").val() + "/admin-listener?do=tracking",
            dataType: 'text',
            type: 'get',
            cache: false,
            data: {
                id: id,
                fdate: startD,
                tdate: endD
            },
            success: function (result) {
                $("#result1").html(result);
            },
            error: function () {
                alert("Error.....")
            }
        });
        $.ajax({
            url: $("#url").val() + "/admin-listener?do=jsonTracking",
            dataType: 'json',
            type: 'get',
            cache: false,
            data: {
                id: id,
                fdate: startD,
                tdate: endD
            },
            success: function (result) {
                let data = {
                    series: [
                        {
                            data: result.values
                        }
                    ],
                    xaxis: {
                        categories: result.labels,
                    },
                };
                customer_chart.updateOptions(data);
            },
            error: function () {
                console.log("Occur error when request show chart");
            }
        });
    });
     /// print tracking song
    $(function () {
        $('.btn-print').on('click', function (e) {
            var attr = $(this).attr("data-id");
           if(attr == "print"){
                $.print("#statis");
           }
           else{
                $.print(".dialog-lg");
           }
        });
    });
</script>
<%@include file="../footer.jsp"%>
