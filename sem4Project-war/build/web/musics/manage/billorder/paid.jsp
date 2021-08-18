<%-- 
    Document   : paid
    Created on : Jul 23, 2021, 9:15:57 PM
    Author     : hmtua
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table class="w3-table-all w3-hoverable"  style=" border-radius:2px;">            
    <thead >
        <tr class="w3-light-grey">
            <th  style="border:0px"></th>
            <th style="border:0px">Song Name</th>
            <th style="border:0px">Customer Name</th>
            <th style="border:0px">Date Order</th>
            <th style="border:0px">Price</th>
            <th style="border:0px;width:100px">Name Payment</th>
            <th style="border:0px">Status</th>
            <th style="border:0px">Payment Time</th>
        </tr>
    </thead >
    <tbody id="myTable2">
        <%
            int b = 0;
            List l1 = new ArrayList();
            l1 = (ArrayList) request.getAttribute("informationOrderDate");
            for (int idx = 0; idx < l1.size(); idx++) {
                b++;
                String t = l1.get(idx).toString();
                char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                String SongID, OrderID, SongName, CustomerName, DateOrder, PriceSong, StatusPay, StatusOrder, DatePay, idDetail, BankName;
                String cat = t.substring(1, t.indexOf("]"));

                SongID = cat.substring(0, cat.indexOf("/"));
                String cat2 = cat.substring(cat.indexOf("/") + 1);

                OrderID = cat2.substring(0, cat2.indexOf("/"));
                String cat3 = cat2.substring(cat2.indexOf("/") + 1);

                SongName = cat3.substring(0, cat3.indexOf("/"));
                String cat4 = cat3.substring(cat3.indexOf("/") + 1);
                if (SongName.equals("null")) {
                    continue;
                }
                CustomerName = cat4.substring(0, cat4.indexOf("/"));
                String cat5 = cat4.substring(cat4.indexOf("/") + 1);

                DateOrder = cat5.substring(0, cat5.indexOf("/"));
                DateOrder = DateOrder.substring(0, DateOrder.indexOf("."));
                String cat6 = cat5.substring(cat5.indexOf("/") + 1);

                PriceSong = cat6.substring(0, cat6.indexOf("/"));
                String cat7 = cat6.substring(cat6.indexOf("/") + 1);

                StatusPay = cat7.substring(0, cat7.indexOf("/"));
                String cat8 = cat7.substring(cat7.indexOf("/") + 1);

                StatusOrder = cat8.substring(0, cat8.indexOf("/"));
                String cat9 = cat8.substring(cat8.indexOf("/") + 1);

                DatePay = cat9.substring(0, cat9.indexOf("/"));
                String cat10 = cat9.substring(cat9.indexOf("/") + 1);

                idDetail = cat10.substring(0, cat10.indexOf("/"));
                String cat11 = cat10.substring(cat10.indexOf("/") + 1);

                BankName = cat11;
                if (StatusOrder.equals("1") && StatusPay.equals("1")) {
                } else {
                    continue;
                }


        %>
        <tr>
            <td  style="border:0px"><% out.print(b); %></td>
            <td  style="border:0px">
                <% out.print(SongName); %><br/>
                <a  href="#<% out.print(idDetail);%>"  onclick="document.getElementById('tableModa').style.display = 'block';layDataLenModa(<% out.print(idDetail); %>)" style="color: #008CBA;font-size: 13px;text-align:center">Details</a>          
            </td>
            <td style="border:0px"><% out.print(CustomerName); %></td>
            <td style="border:0px"><% out.print(DateOrder); %></td>
            <td style="border:0px"><% out.print(PriceSong); %></td>
            <td style="border:0px;width:100px">  
                <% if (BankName.endsWith("OnePay")) {
                %>
                <p class="w3-tooltip">
                    <span style="font-size: 18px;color: blue"><b style="color: #737357">One</b>PAY</span>
                    <span style="position:absolute;left:10;background-color: #029ef3;border-radius:5px;width: 300px" class="w3-text w3-tag">
                        Pay via OnePay gateway</span>                  
                </p>
                <%
                } else if (BankName.endsWith("Bcoin")) {
                %>
                <p class="w3-tooltip">
                    <span style="font-size: 18px"><b style="color:red">B</b>coin</span>
                    <span style="position:absolute;left:10;background-color: #029ef3;border-radius:5px;width: 400px" class="w3-text w3-tag">
                        Payment through money in the account</span>
                </p>
                <%
                } else {
                %>
                <p class="w3-tooltip">
                    <span style="font-size: 18px"><b  style="color:red" title="">Admin</b></span>
                    <span style="position:absolute;left:10;background-color: #029ef3;border-radius:5px;width: 200px" class="w3-text w3-tag">
                        Updated by admin</span>
                </p>
                <% }
                %>
            </td>
            <td style="border:0px">
                <%                    if (StatusOrder.equals("3")) {
                %>
                <img height="20px"  src="https://img.icons8.com/officexs/80/000000/cancel-subscription.png" title="Order has been cancelled"/>
                <%
                } else {
                    if (!StatusPay.equals("null") && StatusPay.equals("1")) {

                %> 
                <img height="20px" src="https://img.icons8.com/color/48/000000/paid.png" title="Order has been successfully paid"/>
                <%} else { %>
                <img height="20px" src="https://img.icons8.com/cotton/64/000000/delete-sign--v2.png" title="Order unpaid"/> <%if (BankName.equals("Admin")) {
                        out.print("<samp style='color: red'> Admin</samp>");
                    } %>
                <%}
                    }%>
            </td> 
            <td style="border:0px"><% out.print(DateOrder); %></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>