<%-- 
    Document   : statistic
    Created on : Jul 14, 2021, 2:05:19 PM
    Author     : HP
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/daterangepicker.css" />

<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">Report</div>
</div>
<div class="main-content">
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
    
</div>
<!-- APEX CHART -->
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
<script>
    let customer_options = {
        series: [{
                name: "Views",
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
           'Last week': [moment().subtract(6, 'days'), moment()],
            '30 days ago': [moment().subtract(29, 'days'), moment()],
           },
        "startDate": moment(),
        "endDate": moment()
    }, function (start, end, label) {
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
        startD = start.format('YYYY-MM-DD');
        endD = end.format('YYYY-MM-DD');
        $.ajax({
            url: $("#url").val() + "/admin-news?do=getDate",
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
</script>
<%@include file="../footer.jsp"%>

