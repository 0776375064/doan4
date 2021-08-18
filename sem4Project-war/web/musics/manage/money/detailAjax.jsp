<%-- 
    Document   : ajaxdetail
    Created on : Jul 23, 2021, 7:15:57 PM
    Author     : hmtua
--%>

<%@page import="entities.OrderDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    .idex:hover{
        color: red;
    }

    .close:hover{
        box-shadow: 1px 1px 1px 4px red;
        font-size: 110%;
        font-family: OpenSans;
    }
    w3-button w3-display-topright:hover{
        box-shadow: 1px 1px 1px 4px red;
        font-size: 110%;
        font-family: OpenSans;
    }
</style>
<%
    List<OrderDetails> listDetail = new ArrayList<OrderDetails>();
    for (OrderDetails cx : ((ArrayList<OrderDetails>) request.getAttribute("detail"))) {

    }

    //  listDetail =  request.getAttribute("detail");
%>
<table class="w3-table-all w3-hoverable"  style=" border-radius:2px;">
    <thead >
        <tr class="w3-light-grey">
            <th style="border:0px"></th>
            <th style="border:0px">Customer Name</th>
            <th style="border:0px">Bank Name</th>
            <th style="border:0px">Date Order</th>
            <th style="border:0px">Price</th>
        </tr>
    </thead > 
    <tbody id="myTable">
        <tr>
        </tr>
        <%%>
    </tbody> 
</table>


