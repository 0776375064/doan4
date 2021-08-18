
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<style>
    .itemsMusic:hover{
        background-color:#636768;
        box-shadow: 0px 0px 5px;
        font-size:120%
    }
    td{
        border: none;
    }
    table{
        border: 0px ;
        border-collapse: collapse;

    }
    tr:hover{
        color:#ffffff;
    }
    .chart-title:hover{
        color:#f73943;
    }
    .chart-singer:hover{
        color:#f73943;
    }

    
    
</style>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br/>
<div class="w3-row">
    <div class="w3-col container" style="width:25%"></div>
    <div class="w3-col container" style="width:50%">
        <center>
            <input class="w3-input w3-border w3-round-xxlarge" id="myInput"    type="text" placeholder="Search..">
        </center>        
    </div><h1 style="font-size: 20">Bungs</h1>
    <div class="w3-col container" style="width:25%"></div>
</div>
<div class="section">
    <div class="container">
        <div class="section-header">
            Music list
        </div>       
        <div class="music-container">
            <div class="chart-music">               
                <table class="music-list-chart">
                    <thead>
                        <tr>
                            <th style="width:10%"></th>
                            <th style="width:35%;text-align: left;">Song</th>
                            <th></th>
                            <th></th>
                            <th style="width:10%;text-align: left" class="chart-action">Price</th>
                        </tr>
                    </thead>
                    <tbody id="myTable">
                        <%
                            //lay list danh sach da mua cua acc
                            String idSongOrder =null;
                            if (session.getAttribute("accountID") != null) {
                                 idSongOrder = String.valueOf(request.getAttribute("idSongOrder"));
                            } else {
                                 idSongOrder = null;
                            }
                            //lay id khac hang 
                            String AccountIDSession = String.valueOf(session.getAttribute("accountID"));
                            int i = 0;
                            List l = new ArrayList();
                            l = (ArrayList) request.getAttribute("listSongAll");
                            for (int idx = 0; idx < l.size(); idx++) {
                                String t = l.get(idx).toString();
                                char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                                String AccountID, SongID, SongName, Path, PriceSong, ngheSiID, tenNgheSi, anhBiaHat, trangThaiThanhToan, TrangThaiOrder;
                                String cat = t.substring(1, t.indexOf("]")).trim();
                                if (!cat.substring(0, cat.indexOf("/")).equals("null")) {
                                    i++;
                                    SongID = cat.substring(0, cat.indexOf("/"));
                                    String cat2 = cat.substring(cat.indexOf("/") + 1).trim();
                                    //
                                    AccountID = cat2.substring(0, cat2.indexOf("/"));
                                    String cat3 = cat2.substring(cat2.indexOf("/") + 1).trim();
                                    //
                                    SongName = cat3.substring(0, cat3.indexOf("/"));
                                    String cat4 = cat3.substring(cat3.indexOf("/") + 1).trim();
                                    //
                                    Path = cat4.substring(0, cat4.indexOf("/"));
                                    String cat5 = cat4.substring(cat4.indexOf("/") + 1).trim();
                                    //
                                    PriceSong = cat5.substring(0, cat5.indexOf("/"));
                                    String cat6 = cat5.substring(cat5.indexOf("/") + 1).trim();
                                    //
                                    ngheSiID = cat6.substring(0, cat6.indexOf("/"));
                                    String cat7 = cat6.substring(cat6.indexOf("/") + 1).trim();
                                    //
                                    tenNgheSi = cat7.substring(0, cat7.indexOf("/"));
                                    String cat8 = cat7.substring(cat7.indexOf("/") + 1).trim();
                                    //jjj
                                    anhBiaHat = cat8.substring(0, cat8.indexOf("/"));
                                    if (anhBiaHat.equals("null")) {
                                        anhBiaHat = "https://img.icons8.com/cute-clipart/64/000000/test-account.png";
                                    } else {
                                        anhBiaHat = anhBiaHat;
                                    }
                                    String cat9 = cat8.substring(cat8.indexOf("/") + 1).trim();
                                    //
                                    trangThaiThanhToan = cat9.substring(0, cat9.indexOf("/"));
                                    String cat10 = cat9.substring(cat9.indexOf("/") + 1).trim();
                                    //
                                    TrangThaiOrder = cat10;
                        %>
                        <tr class="itemsMusic">
                            <td>
                                <div class="chart-stt">
                                    <% out.print(i); %>
                                </div>                              
                            </td>
                            <td style="width:35%;text-align: left;">
                                <div class="chart-info">
                                    <div class="chart-thumbnail">
                                        <img src="<% out.print(anhBiaHat); %>" alt="">
                                    </div>
                                    <div class="chart-name">
                                        <a href="${pageContext.request.contextPath}/SongDetail?songID=<% out.print(SongID); %>">
                                            <span class="chart-title"><% out.print(SongName); %></span>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/?ID=<% out.print(ngheSiID); %>">
                                            <span class="chart-singer"><% out.print(tenNgheSi); %></span>
                                        </a>
                                    </div> 
                                </div>
                            </td>
                            <td></td>
                            <td></td>
                            <td style="width:10%" class="chart-action">

                                <%
                                    if (idSongOrder.indexOf(SongID) != -1) {
                                        //dung de chua duong dan nhac
                                        //duong dan nap trong 
                                        //<% out.print(Path); 
                                %>
                                <a href="" data-src="music2.mp3" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                <%
                                } else {
                                    if (PriceSong.equals("null") || PriceSong.equals("0")) {
                                %>
                                <a href="" data-src="music2.mp3" class="btn-link btn-play"><i class='bx bx-play'></i>Play</a>
                                <%
                                } else if (!PriceSong.equals("null") || !PriceSong.equals("0")) {
                                %>
                                <a href="${pageContext.request.contextPath}/Order?idSong=<% out.print(SongID); %>" class="btn-link btn-order"> Buy <% out.print(PriceSong); %>$</a>
                                <%
                                        }

                                    }
                                %>

                            </td>
                        </tr>
                        <%   }
                            }
                        %> 
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- END SECTION MUSIC -->
<%@include file="../includes/footer.jsp"%>

