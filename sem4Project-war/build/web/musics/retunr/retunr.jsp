<%-- 
    Document   : retunr
    Created on : Jul 25, 2021, 2:14:02 AM
    Author     : hmtua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (String.valueOf(request.getAttribute("tinNhan")).equals("Update successful")) {
%>
<div class="w3-panel w3-green w3-round-large w3-display-container">
    <span id="d" onclick="this.parentElement.style.display = 'none'"
          class=" w3-button w3-large w3-display-topright w3-round-large">&times;</span>
    <h3>
        <%  out.print(request.getAttribute("tinNhan"));%>
    </h3>
    <p>Public status update process!</p>
</div>
<script>
//    var anThongBao = function () {
//        this.
//        this.parentElement.style.display = 'none'
//    };
//    setTimeout(do_alert, 1000);
    $.ajax({
        url: $("#url").val() + "/ManageBillOrder?date=detailsAll",
        dataType: 'text',
        type: 'get',
        cache: false,
        success: function (result) {
            $("#tableAll").html(result);
            //  console.log(result);
        },
        error: function () {
            alert("Error.....")

        }
    });

    $.ajax({
        url: $("#url").val() + "/ManageBillOrder?date=detailsPaid",
        dataType: 'text',
        type: 'get',
        cache: false,
        success: function (result) {
            $("#tablePaid").html(result);
            //  console.log(result);
        },
        error: function () {
            alert("Error.....")

        }
    });

    $.ajax({
        url: $("#url").val() + "/ManageBillOrder?date=detailsUnpaid",
        dataType: 'text',
        type: 'get',
        cache: false,
        success: function (result) {
            $("#tableUnpaid").html(result);
            // console.log(result);
        },
        error: function () {
            alert("Error.....")

        }
    });

    $.ajax({
        url: $("#url").val() + "/ManageBillOrder?date=detailsCanceled",
        dataType: 'text',
        type: 'get',
        cache: false,
        success: function (result) {
            $("#tableCanceled").html(result);
            // console.log(result);
        },
        error: function () {
            alert("Error.....")

        }
    });
</script>
<%
} else {
%>
<div class="w3-panel w3-red w3-round-large w3-display-container">
    <span id="d" onclick="this.parentElement.style.display = 'none'"
          class=" w3-button w3-large w3-display-topright w3-round-large">&times;</span>
    <h3>
        <%  out.print(request.getAttribute("tinNhan"));%>
    </h3>
    <p></p>
</div>
<script>
    <%
        }
    %>