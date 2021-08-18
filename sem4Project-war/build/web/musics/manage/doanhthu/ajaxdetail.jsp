<%-- 
    Document   : ajaxdetail
    Created on : Jul 23, 2021, 7:15:57 PM
    Author     : hmtua
--%>

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

<table class="w3-table-all w3-hoverable"  style=" border-radius:2px;">
    <thead >
        <tr class="w3-light-grey">
            <th style="border:0px"></th>
            <th style="border:0px">Song Name</th>
            <th style="border:0px">Customer Name</th>
            <th style="border:0px">Date Order</th>
            <th style="border:0px">Price</th>
        </tr>
    </thead > 
    <tbody id="myTable">
        <%
            List l = new ArrayList();
            l = (ArrayList) request.getAttribute("listSong");
            int i = 0;
            for (int idx = 0; idx < l.size(); idx++) {
                i++;
                String t = l.get(idx).toString();
                String SongID, OrderID, SongName, CustomerName, DateOrder, PriceSong, anhBaiHat, duongDan, idNgheSi, TenNgheSi;
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
                DateOrder = DateOrder.substring(0, 18);
                String cat6 = cat5.substring(cat5.indexOf("/") + 1);

                PriceSong = cat6.substring(0, cat6.indexOf("/"));
                String cat7 = cat6.substring(cat6.indexOf("/") + 1);

                anhBaiHat = cat7.substring(0, cat7.indexOf("/"));
                String cat8 = cat7.substring(cat7.indexOf("/") + 1);

                duongDan = cat8.substring(0, cat8.indexOf("/"));
                String cat9 = cat8.substring(cat8.indexOf("/") + 1);

                idNgheSi = cat9.substring(0, cat9.indexOf("/"));
                String cat10 = cat9.substring(cat9.indexOf("/") + 1);

                TenNgheSi = cat10;

        %>
        <tr>
            <td style="border:0px"><% out.print(i); %></td>
            <td style="width:20%;border:0px">
                <a class="idex" href="SongDetail?songID=<%out.print(SongID);%>&idArtist=<%out.print(idNgheSi);%>">
                    <% out.print(SongName); %>
                </a>&nbsp;<br/>
                <a  href="#<% out.print(OrderID);%>"  onclick="document.getElementById('<% out.print(OrderID);%>').style.display = 'block'" style="color: #008CBA;font-size: 13px;text-align:center">Details</a>
                <div id="<% out.print(OrderID);%>" class="w3-modal">
                    <div class="w3-modal-content w3-animate-top w3-card-4">
                        <header class="w3-container "> 
                            <span style="text-align:left;font-size:20px">
                                <b>Detail</b>
                            </span>
                            <span  onclick="document.getElementById('<% out.print(OrderID);%>').style.display = 'none'" 
                                   class="w3-button w3-display-topright">&times;</span>
                            <br/>
                        </header>
                        <div class="w3-container">
                            <div class="w3-row">
                                <div class="w3-col container" style="width:30%">
                                    <img height="250px" style="border-radius:5px" class="anhbaihat" src="${pageContext.request.contextPath}/storage/song/<% out.print(anhBaiHat); %>" >
                                </div>
                                <div class="w3-col container" style="width:60%">
                                    <a class="idex" href="SongDetail?songID=<%out.print(SongID);%>&idArtist=<%out.print(idNgheSi);%>">
                                        <b style="text-align:left;font-size:20px">
                                            <% out.print(SongName); %>
                                        </b>
                                    </a>
                                    <hr style=" background-color:red; height:1px" /> 
                                    <br/>
                                    <%out.print(PriceSong);%>$<br/>

                                    <b> 
                                        <a class="idex"  href="ClientArtist?do=info&id=<%out.print(idNgheSi);%>">
                                            <%out.print(TenNgheSi);%>
                                        </a>
                                    </b><br/>
                                    Date Order : <%out.print(DateOrder);%>
                                </div>
                            </div>
                        </div>
                        <footer class="w3-container">
                            <div style="text-align:right">        
                                <span onclick="document.getElementById('<% out.print(OrderID);%>').style.display = 'none'">
                                    <button class="w3-button w3-red" type="button"   style="width: 80px; border: 1px solid  ; border-radius: .25rem;border-color: #B7B7B7">
                                        Close
                                    </button>
                                </span>                                                        
                            </div>
                        </footer>
                    </div>
                </div>
            </td>

            <td style="border:0px"><% out.print(CustomerName); %></td>
            <td style="border:0px"><% out.print(DateOrder); %></td>
            <td style="border:0px"><% out.print(PriceSong); %></td>
        </tr>
        <%}%>
    </tbody> 
</table>


