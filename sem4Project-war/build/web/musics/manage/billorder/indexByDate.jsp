<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    /* Removes the clear button from date inputs */
    input[type="date"]::-webkit-clear-button {
        display: none;
    }

    /* Removes the spin button */
    input[type="date"]::-webkit-inner-spin-button { 
        display: none;
    }

    /* Always display the drop down caret */
    input[type="date"]::-webkit-calendar-picker-indicator {
        color: #2c3e50;
    }

    /* A few custom styles for date inputs */
    input[type="date"] {
        appearance: none;
        -webkit-appearance: none;
        color: #95a5a6;
        font-family: "Helvetica", arial, sans-serif;
        font-size: 18px;
        background-color: #fff;
        padding:5px;
        display: inline-block !important;
        visibility: visible !important;
        border: 1px solid rgba(0,0,0,.125);
        border-radius: .25rem;
    }

    input[type="date"], focus {
        color: #95a5a6;
        box-shadow: none;
        -webkit-box-shadow: none;
        -moz-box-shadow: none;
    }
    .w3-container{
        padding:10px
    }
    .form-group {
        margin-bottom: 1rem;
    }
    .card {
        position: relative;
        display: -webkit-box;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 1px solid rgba(0,0,0,.125);
        border-radius: .25rem;
    }
    .card-header {
        padding: .75rem 1.25rem;
        margin-bottom: 0;
        background-color: rgba(0,0,0,.03);
        border-bottom: 1px solid rgba(0,0,0,.125);
    }
    .card-body {
        -webkit-box-flex: 1;
        flex: 1 1 auto;
        padding: 1.25rem;
    }
    .w3-col{
        padding:2px;
    }
    div[name="Dashboard"]{
        background-color:#ffffff;
        width:98%;
        border-radius:2px;
        border:1;
        box-shadow: 0px 0px 1px;
        padding:5px;
    }
    .dashboardNoiDung{
        text-align: right!important;
    }
</style>
<script>
    google.charts.load('current', {'packages': ['bar']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable(${order});
        var options = {
            seriesType: 'bars',
            series: {1: {type: 'line'}}
            //                    chart: {
            //                        title: 'Order Performance',
            //                        subtitle: 'Sales of orders in the month',
            //                    }
        };
        var chart = new google.charts.Bar(document.getElementById('chart_div'));
        chart.draw(data, google.charts.Bar.convertOptions(options));
    }

</script>
<div class="w3-container">
    <div class="w3-row">
        <div class="w3-col" name="Dashboard" style="width:24.625%">
            <div class="">
                <div class="w3-col" style="width:40%">
                    <img src="https://img.icons8.com/fluency/48/000000/order-history.png"/>
                </div>
                <div class="w3-col" style="width:60%">
                    <div class="dashboardNoiDung">
                        <p>Total Order</p>
                        <b>${sunAll}</b>
                    </div>
                </div>  
            </div>
        </div>
        <div class="w3-col" style="width:0.5%"></div>  
        <div class="w3-col" name="Dashboard" style="width:24.625%">
            <div class="">
                <div class="w3-col" style="width:40%">
                    <img src="https://img.icons8.com/fluency/48/000000/paid-bill.png"/>
                </div>
                <div class="w3-col" style="width:60%">
                    <div class="dashboardNoiDung" >
                        <p>Total Order Paid</p>
                        <p>
                            <b>${sumPaid}</b>
                        </p>
                    </div>
                </div>  
            </div>
        </div>
        <div class="w3-col" style="width:0.5%"></div>  
        <div class="w3-col" name="Dashboard" style="width:24.625%">
            <div class="">
                <div class="w3-col" style="width:40%">
                    <img height="48px" src="${pageContext.request.contextPath}/storage/images/orderunpaid.png" alt=""/>
                </div>
                <div class="w3-col" style="width:60%">
                    <div class="dashboardNoiDung">
                        <p>Total Order Unpaid</p>
                        <b>${sumUnpaid}</b>
                    </div>
                </div>
            </div>
        </div>
        <div class="w3-col" style="width:0.5%"></div>    
        <div class="w3-col" name="Dashboard" style="width:24.625%">
            <div class="">
                <div class="w3-col" style="width:40%">
                    <img height="48px" src="https://img.icons8.com/office/80/000000/Delete-production-order.png"/>
                </div>
                <div class="w3-col" style="width:60%">
                    <div class="dashboardNoiDung">
                        <p>Total Order Canceled </p>
                        <b>${sumCanceled}</b>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>
<div class="w3-row">
    <div class="w3-col container" style="width:100%">
        <div class="card form-group w3-container">
            <div class="card-header">
                <img height="25px" src="https://img.icons8.com/cotton/64/000000/statistics--v1.png"/>
                <b>ORDER OVERVIEW</b>
            </div>
            <div class="card-body">
                <div class="w3-container" id="chart_div" style="width: 100%; height:400px"></div>
            </div>
        </div>
    </div>
</div>
</div>
