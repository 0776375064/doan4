<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="w3-row">
    <div class="w3-col" name="Dashboard" style="width:24.625%">
        <div class="w3-container">
            <div class="w3-col" style="width:40%">
                <img height="48px" src="https://img.icons8.com/fluent/48/000000/total-sales-1.png"/>
            </div>
            <div class="w3-col" style="width:60%">
                <div class="dashboardNoiDung">
                    <p>Total revenue</p>
                    <b>${sumPriceAllInDate} $</b>
                </div>
            </div>  
        </div>
    </div>

    <div class="w3-col"  style="width:0.5%"></div>
    <div class="w3-col" name="Dashboard" style="width:24.625%">
        <div class="w3-container">
            <div class="w3-col" style="width:40%">
                <img height="48px" src="https://img.icons8.com/fluency/48/000000/money-bag-bitcoin.png"/>
            </div>
            <div class="w3-col" style="width:60%">
                <div class="dashboardNoiDung" >
                    <label ><div style="background-color: blue;width: 40px;height: 5px"></div>BCoin</label>
                    <p>
                        <b>${countOrderISAccount}$</b>
                    </p>
                </div>
            </div>  
        </div>
    </div>

    <div class="w3-col"  style="width:0.5%"></div>
    <div class="w3-col" name="Dashboard" style="width:24.625%">
        <div class="w3-container">
            <div class="w3-col" style="width:40%">
                <img  height="48px" src="https://img.icons8.com/color/48/000000/online-payment-.png"/>
            </div>
            <div class="w3-col" style="width:60%">
                <div class="dashboardNoiDung">
                    <p><div style="background-color: red;width: 40px;height: 5px"></div>Online</p>
                    <b>${countOrderIS}$</b>

                </div>
            </div>
        </div>
    </div>

    <div class="w3-col"  style="width:0.5%"></div>
    <div class="w3-col" name="Dashboard" style="width:24.625%">
        <div class="w3-container">
            <div class="w3-col" style="width:40%">
                <img src="https://img.icons8.com/color/48/000000/administrator-male-skin-type-7.png"/>
            </div>
            <div class="w3-col" style="width:60%">
                <div class="dashboardNoiDung">
                    <p><div style="background-color: green;width: 40px;height: 5px"></div>Admin</p>
                    <b>${countOrderAdmin}$</b>

                </div>
            </div>
        </div>
    </div>
</div>
<br/>
<div class="w3-row">
    <div class="w3-col" style="width:70.5%">
        <div class="card form-group">
            <div class="card-header">
                <img height="25px" src="https://img.icons8.com/cotton/64/000000/statistics--v3.png"/>
                <b>REVENUE STATISTICS</b>
            </div>
            <div class="card-body">
                <canvas id="myChart" style="width:98%"></canvas>
                <script >
                    var xValues = ${listDay};

                    new Chart("myChart", {
                        type: "line",
                        data: {
                            labels: xValues,
                            datasets: [{
                                    data: ${listPriceAll},
                                    borderColor: "red",
                                    fill: false
                                }, {
                                    data:${listPriceAllAccount},
                                    borderColor: "blue",
                                    fill: false
                                }, {
                                    data:${listPriceAllAdmint},
                                    borderColor: "green",
                                    fill: false
                                }]
                        },
                        options: {
                            legend: {display: false}
                        }
                    });
                </script> 
            </div>
        </div>
    </div>
    <div class="w3-col" style="width:0.5%"></div>
    <div class="w3-col" style="width:29%">
        <div class="card form-group">
            <div class="card-header">
                <img height="25px" src="https://img.icons8.com/cotton/64/000000/statistics--v1.png"/>
                <b>TOP 5 PRODUCTS</b>
            </div>
            <div class="card-body">
                <div
                    id="myChart1" style="width:100%; height:405px;">
                </div>
                <script>
                    google.charts.load('current', {'packages': ['corechart']});
                    google.charts.setOnLoadCallback(drawChart);

                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ['Contry', 'Mhl'],
                    ${sumTop10}
                        ]);

                        var options = {
                            title: 'Top 5 Grossing Songs'
                        };

                        var chart = new google.visualization.PieChart(document.getElementById('myChart1'));
                        chart.draw(data, options);
                    }
                </script>
            </div>
        </div>
    </div>
</div>