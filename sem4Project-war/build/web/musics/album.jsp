
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<div><div name="dsMusic" class="music-container">
        <div class="music-container">
            <div class="chart-music">               
                <table class="music-list-chart">
                    <tbody id="myTable" class="list-week-chart">
                        <%
                            //lay id khac hang 
                            String idSongOrdera = null;
                            if (session.getAttribute("accountID") != null) {
                                idSongOrdera = String.valueOf(request.getAttribute("idSongOrder"));
                            } else {
                                idSongOrdera = null;
                            }
                            //String AccountIDSession = String.valueOf(session.getAttribute("userID"));
                            List lsu = new ArrayList();
                            lsu = (ArrayList) request.getAttribute("album");
                            for (int idx = 0; idx < lsu.size(); idx++) {
                                String t = lsu.get(idx).toString();
                                char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                                String AccountID, SongID, SongName, Path, PriceSong, ngheSiID, tenNgheSi, anhBiaHat, trangThaiThanhToan, TrangThaiOrder;
                                String cat = t.substring(1, t.indexOf("]")).trim();
                                if (!cat.substring(0, cat.indexOf("/")).equals("null")) {
                                    SongID = cat.substring(0, cat.indexOf("/"));
                                    String cat2 = cat.substring(cat.indexOf("/") + 1).trim();
                                    //
                                    SongName = cat2.substring(0, cat2.indexOf("/"));
                                    String cat3 = cat2.substring(cat2.indexOf("/") + 1).trim();
                                    //
                                    Path = cat3.substring(0, cat3.indexOf("/"));
                                    String cat4 = cat3.substring(cat3.indexOf("/") + 1).trim();
                                    //
                                    PriceSong = cat4.substring(0, cat4.indexOf("/"));
                                    String cat5 = cat4.substring(cat4.indexOf("/") + 1).trim();
                                    //
                                    ngheSiID = cat5.substring(0, cat5.indexOf("/"));
                                    String cat6 = cat5.substring(cat5.indexOf("/") + 1).trim();
                                    //
                                    anhBiaHat = cat6.substring(0, cat6.indexOf("/"));
                                    String cat7 = cat6.substring(cat6.indexOf("/") + 1).trim();
                                    //
                                    tenNgheSi = cat7;
                        %>
                        <tr class="itemsMusic">
                            <td style="width:65%;text-align: left;">
                                <div class="chart-info">
                                    
                                    <div class="chart-thumbnail">
                                        <img  src="${pageContext.request.contextPath}<% out.print("/storage/song/" + anhBiaHat);%>" alt="">
                                    </div>
                                    <div class="chart-name">
                                        <a href="${pageContext.request.contextPath}/SongDetail?songID=<% out.print(SongID); %>&idArtist=<% out.print(ngheSiID); %>">
                                            <span class="chart-title"><% out.print(SongName); %></span>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/ClientArtist?do=info&id=<% out.print(ngheSiID); %>">
                                            <span class="chart-singer"><% out.print(tenNgheSi); %></span>
                                        </a>
                                    </div> 
                                </div>
                            </td>
                            <td></td>
                            <td></td>
                            <td style="width:35%;text-align: right" class="chart-action">

                                <%
                                    if (idSongOrdera.indexOf(SongID) != -1) {
                                %>
                                <a href="" data-id="<% out.print(SongID);%>" class="btn-link btn-play" style="background:none;border:0px;height:20px">
                                    <img style="height:20px"src="${pageContext.request.contextPath}/storage/song/iconplay.png" alt=""/>
                                </a>
                                <%
                                } else {
                                    if (PriceSong.equals("null") || PriceSong.equals("0")) {
                                %>
                                <a href="" data-id="<% out.print(SongID);%>" class="btn-link btn-play" style="background:none;border:0px;height:20px">
                                    <img style="height:20px"src="${pageContext.request.contextPath}/storage/song/iconplay.png" alt=""/>
                                </a>
                                <%
                                } else if (!PriceSong.equals("null") || !PriceSong.equals("0")) {
                                %>
                                <a  onclick="document.getElementById('id01').style.display = 'block';
                                        loatOrder(<% out.print(SongID); %>)"  href="#" class="btn-link btn-order"  style="background:none;border:0px"><% out.print(PriceSong); %>$</a>
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
    </div></div>

