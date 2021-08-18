<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawVisualization);
    function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable(${chartMoney});
        var options = {
            title: '',
            vAxis: {title: ''},
            hAxis: {title: ''},
            seriesType: 'bars',
            series: {0: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chartMoney'));
        chart.draw(data, options);
    }
</script>
<div class="w3-row">
    <div class="w3-col" name="Dashboard" style="width:49.525%">
        <div class="w3-container">
            <div class="w3-col" style="width:40%">
                <img height="48px" src="https://img.icons8.com/ios/50/000000/counter--v2.png"/>
            </div>
            <div class="w3-col" style="width:60%">
                <div class="dashboardNoiDung">
                    <p>Total Order</p>
                    <b>${countMoney}</b>
                </div>
            </div>  
        </div>
    </div>

    <div class="w3-col"  style="width:0.5%"></div>   
    <div class="w3-col" name="Dashboard" style="width:49.525%">
        <div class="w3-container">
            <div class="w3-col" style="width:40%">
                <img height="48px"  src="https://img.icons8.com/office/80/000000/money-bag.png"/>
            </div>
            <div class="w3-col" style="width:60%">
                <div class="dashboardNoiDung" >
                    <b>Total Money</b> 
                    <p>
                        <b>${sumMoney}$</b>
                    </p>
                </div>
            </div>  
        </div>
    </div>
</div>
<br/>
<div class="w3-row">
    <div class="w3-col" style="width:100%">
        <div class="card form-group">
            <div class="card-header">
                <img height="25px" src="https://img.icons8.com/cotton/64/000000/statistics--v3.png"/>
                <b>Recharge</b>
            </div>
            <div class="card-body">
                <div id="chartMoney" style="width:100%; height:400px;"></div>
            </div>
        </div>
    </div>
</div>