
<%@page import="hmt.SuLy"%>
<%@page import="entities.Songs"%>
<%@page import="entities.OrderDetails"%>
<%@page import="entities.Accounts"%>
<%@page import="entities.Orders"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function openCity1(evt, cityName) {
        var i, x, tablinks;
        x = document.getElementsByClassName("city");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " w3-red";
    }

    function updateStatus(idOrder) {
        var trangThai, note;
        trangThai = document.getElementById("status").value;
        note = document.getElementById("note").value;
        $.ajax({
            url: $("#url").val() + "/Moda?moda=updateStatusOrder",
            dataType: 'text',
            type: 'get',
            cache: false,
            data: {
                idOrder: idOrder,
                trangThai: trangThai,
                note: note
            },
            success: function (result) {
                $("#ketquacapnhat").html(result);
                //console.log(result);
            },
            error: function (e) {
                alert("Error..... " + e)
            }
        });
    }



    function openLink(evt, animName) {
        var i, x, tablinks;
        x = document.getElementsByClassName("w3-containers city");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("w3-bar-item w3-button tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
        }
        document.getElementById(animName).style.display = "block";
        evt.currentTarget.className += " w3-red";
    }
</script>
<style>
    .pay:hover{
        color: red;
    }
</style>
<%
    //khoi tao doi tuong
    OrderDetails orderDetail = new OrderDetails();
    //lasy gai tri
    orderDetail = (OrderDetails) request.getAttribute("orderDetail");
%>
<div class="w3-row">
    <div class="w3-col" style="width:60%">
        <div class="card form-group" >
            <div class="card-header">
                <img height="25px"  src="https://img.icons8.com/cotton/48/000000/mobile-order.png"/>
                <b>Order Information</b>
            </div>
            <div class="card-body">
                <div class="" id="columnchart_material" style="width: 100%; height:488px">
                    <div class="date">
                        <p><b>Date Order:</b> <% out.print(orderDetail.getOrderID().getDayTrading()); %> </p>
                        <%
                            if (orderDetail.getOrderID().getOrderDate() != null) {
                                out.print(" <p><b>Date PayMent: </b>" + orderDetail.getOrderID().getOrderDate() + " </p>");
                            }
                        %>
                    </div>
                    <br/>
                    <div class="tableSong">
                        <table class="list-week-chart" cellpadding="0" cellspacing="0">
                            <tr style="background-color: rgba(0,0,0,.03);height:10px">
                                <th style="width:5%;border: 0px">#</th>
                                <th style="text-align:left;border:0px">Song Name</th>
                                <th  style="width:10%;border: 0px;text-align: right">Price&nbsp;</th>
                            </tr>
                            <tr>
                                <td style="width:5%;border: 0px">1</td>
                                <td  style="border:0px">
                                    <div class="chart-info">
                                        <div class="w3-row">
                                            <div class="w3-col" style="width:15%">
                                                <div class="chart-thumbnail">
                                                    <img style="border-radius: 100%" height="40px"src="${pageContext.request.contextPath}/storage/song/<% out.print(orderDetail.getSongID().getThumbnail()); %>"
                                                         alt="">
                                                </div>
                                            </div>
                                            <div class="w3-col" style="width:85%">
                                                <div class="chart-name">
                                                    <b class="pay">
                                                        <a  href="SongDetail?songID=<% out.print(orderDetail.getSongID().getSongID()); %>&idArtist=<% out.print(orderDetail.getSongID().getArtistID()); %>">
                                                            <span class="chart-title"><% out.print(orderDetail.getSongID().getSongName()); %></span>
                                                            <%if (orderDetail.getSongID().getPrice() == 0) {
                                                                    out.print(" <span class='sticker free'>Free</span>");
                                                                } %>                                                                                            
                                                        </a>
                                                    </b>
                                                    <br/>
                                                    <a class="pay" href="ClientArtist?do=info&id=<% out.print(orderDetail.getSongID().getArtistID().getArtistID()); %>"><span class="chart-singer"><% out.print(orderDetail.getSongID().getArtistID().getFullname()); %></span></a>
                                                </div>
                                            </div>
                                        </div>  
                                    </div>

                                </td>
                                <td style="width:10%;border: 0px;text-align: center">
                                    <% out.print(orderDetail.getPrice()); %>$
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>  
        </div>
    </div>

    <div class="w3-col" style="width:40%">
        <div class="card form-group">
            <div class="card-header">
                <img height="25px" src="https://img.icons8.com/color/48/000000/user.png"/>
                <b>User Information</b>
            </div>
            <div class="card-body">
                <div class="" id="columnchart_material" style="width: 100%; height:100px">
                    <div class="w3-row">
                        <div class="w3-col" style="width:35%">  
                            <%
                                String avartar = orderDetail.getOrderID().getAccountID().getAvatar().toString();

                                if (!avartar.equals(null)) {
                            %>
                            <img class="w3-card" style="border-radius:4px" height="120px" src="${pageContext.request.contextPath}/storage/profile/<% out.print(avartar); %>" alt=""/>
                            <%
                            } else {
                                avartar = "data:image/jpeg;base64,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";
                            %>
                            <img class="w3-card" style="border-radius:4px" height="120px" src="<% out.print(avartar); %>" alt=""/>
                            <%
                                }
                            %>
                        </div>
                        <div class="w3-col" style="width:65%">
                            <b>
                                <% out.print(orderDetail.getOrderID().getAccountID().getFullname()); %>
                            </b>
                            <br/>
                            <% out.print(orderDetail.getOrderID().getAccountID().getPhone()); %>
                            <br/>
                            <% out.print(orderDetail.getOrderID().getAccountID().getEmail());%>
                            <br/>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
        <div class="card form-group">
            <div class="card-header">
                <img height="25px" src="https://img.icons8.com/dusk/64/000000/online-payment-.png"/>
                <b>Type of payment</b>
            </div>
            <div class="card-body">
                <div class="" id="columnchart_material" style="width: 100%; height:100px">
                    <div class="w3-row">
                        <div class="w3-col" style="width:100%">
                            <p>Pay online through <% out.print(orderDetail.getOrderID().getBankName()); %> payment gateway</p><br/> 
                        </div> 
                        <div class="w3-col" style="width:50%">
                            <%  if (orderDetail.getOrderID().getBankName().equals("OnePay")) {
                            %>
                            <img height="25px" src="https://i-kinhdoanh.vnecdn.net/2009/02/11/31-1367011326_500x0.JPG">
                            <% } else if (orderDetail.getOrderID().getBankName().equals("Bcoin")) {%>
                            <label style="font-size: 25px"><b style="color:red">B</b>coin</label>
                            <%} else {%>
                            <label style="font-size: 25px"><b style="color:red">A</b>dmin</label>
                            <%} %>
                        </div>
                        <div class="w3-col" style="width:50%">
                            <table style="border:0px">
                                <tr  style="border: 0px">
                                    <td style="border: 0px">Subtotal</td>
                                    <td style="border: 0px"><% out.print(orderDetail.getOrderID().getPrice());  %>$</td>
                                </tr>
                                <tr>
                                    <td style="border: 0px">Tax (0%)</td>
                                    <td style="border: 0px">0$</td>
                                </tr>
                                <tr>
                                    <td style="border: 0px">Total</td>
                                    <td style="border: 0px"><% out.print(orderDetail.getOrderID().getPrice());%>$</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
    </div>
</div>



<div class="card form-group" >
    <div class="card-header">
        <img height="25px" src="https://img.icons8.com/fluent/48/000000/service.png"/>
        <b>Operation</b>
    </div>
    <div class="card-body">
        <div class="" id="columnchart_material" style="width: 100%; height:200px">
            <div>
                <div class="w3-col" style="width:15%">
                    <div>
                        <div class=" w3-bar-block  w3-card" style="width:130px">
                            <h5 class="w3-bar-item"><b>Menu</b></h5>
                            <button class="w3-bar-item w3-button tablink" onclick="openLink(event, 'Bottom')">UpDate</button>
                            <button class="w3-bar-item w3-button tablink" onclick="openLink(event, 'Zoom')">Note</button>
                        </div>
                    </div>
                </div>
                <div class="w3-col" style="width:85%">
                    <div id="Bottom" class="w3-containers city w3-animate-bottom" style="display: block">
                        <div>
                            <div id="capNhat">
                                <div id="ketquacapnhat">
                                </div>
                                <form > 
                                    <h2>Update Status</h2>
                                    <select id="status" style="width:100px" class="w3-input w3-border w3-round-large" >
                                        <%
                                            String TransactionStatus = String.valueOf(request.getAttribute("TransactionStatus"));
                                            //                        //dơn hang da mua 
                                            if (orderDetail.getOrderID().getStatus().equals(1) && TransactionStatus.equals("1")) {
                                                out.print("<option value='1'  selected='selected'>Can use</option>");
                                                out.print("<option value='2'>Unpaid</option>");
                                                out.print("<option value='3'>Cancel</option>");
                                                out.print("<option value='4' disabled='true'>Waiting to process purchased orders</option>");
                                                out.print("<option value='5' disabled='true'>Pending unpurchased orders</option>");
                                            } // don hang chua mua 
                                            else if (orderDetail.getOrderID().getStatus().equals(1) && TransactionStatus.equals("null")) {
                                                out.print("<option value='1' >Can use</option>");
                                                out.print("<option value='2' selected='selected'>Unpaid</option>");
                                                out.print("<option value='3'>Cancel</option>");
                                                out.print("<option value='4' disabled='true'>Waiting to process purchased orders</option>");
                                                out.print("<option value='5' disabled='true'>Pending unpurchased orders</option>");
                                            } //don hang da huy
                                            else if (orderDetail.getOrderID().getStatus().equals(3) && TransactionStatus.equals("1")) {
                                                out.print("<option value='1'  selected='selected'>Can use</option>");
                                                out.print("<option value='2'>Unpaid</option>");
                                                out.print("<option value='3' selected='selected'>Cancel</option>");
                                                out.print("<option value='4' disabled='true'>Waiting to process purchased orders</option>");
                                                out.print("<option value='5' disabled='true'>Pending unpurchased orders</option>");
                                            } //dơn hang da mua dang su ly
                                            else if (orderDetail.getOrderID().getStatus().equals(2) && TransactionStatus.equals("1")) {
                                                out.print("<option value='1'  selected='selected'>Can use</option>");
                                                out.print("<option value='2'>Unpaid</option>");
                                                out.print("<option value='3'>Cancel</option>");
                                                out.print("<option value='4' selected='selected' disabled='true'>Waiting to process purchased orders</option>");
                                                out.print("<option value='5' disabled='true'>Pending unpurchased orders</option>");
                                            } //dơn ahng chua mua dang su ly
                                            else if (orderDetail.getOrderID().getStatus().equals(2) && TransactionStatus.equals("null")) {
                                                out.print("<option value='1'  selected='selected'>Can use</option>");
                                                out.print("<option value='2'>Unpaid</option>");
                                                out.print("<option value='3'>Cancel</option>");
                                                out.print("<option value='4' disabled='true'>Waiting to process purchased orders</option>");
                                                out.print("<option value='5' selected='selected' disabled='true'>Pending unpurchased orders</option>");
                                            }
                                        %>
                                    </select><br/>
                                    <textarea placeholder=" Enter the reason for the modification ..." id="note" style="" type="text" class="w3-input w3-border w3-round-large"></textarea><br/>
                                    <p style="text-align:right" >
                                        <button type="button" style="border-radius:4px;"  onclick="document.getElementById('modaXacNhan').style.display = 'block'" class="w3-button w3-teal">Update</button>
                                    </p>
                                </form>
                            </div>      
                        </div>
                    </div>
                    <div id="Zoom" class="w3-containers city w3-animate-zoom" style="display:none">
                        <p>   
                        <table class="w3-table w3-bordered">
                            <tr>
                                <th>Account</th>
                                <th>Date</th>
                                <th>Initial State</th>
                                <th>End Status</th>
                                <th>Note</th>
                            </tr>
                            <%
                                String note = request.getAttribute("Note").toString();
                                if (!note.equals("null")) {
                                    List listNote = new ArrayList();
                                    listNote = SuLy.taoListTuNote(note);
                                    System.out.println(note);
                                    System.out.println(listNote);
                                    System.out.println(listNote.size());
                                    for (int idx = 0; idx < listNote.size(); idx++) {
                                        String t = listNote.get(idx).toString();
                                        char[] ch = t.substring(1, t.indexOf("]")).toCharArray();
                                        String Note, Acc, Date, Ttbandau, Ttsau;
                                        String cat = t.substring(1, t.indexOf("]"));
                                        Note = cat.substring(0, cat.indexOf("/"));
                                        String cat2 = cat.substring(cat.indexOf("/") + 1);

                                        Acc = cat2.substring(0, cat2.indexOf("/"));
                                        String cat3 = cat2.substring(cat2.indexOf("/") + 1);

                                        Date = cat3.substring(0, cat3.indexOf("/"));
                                        String cat4 = cat3.substring(cat3.indexOf("/") + 1);

                                        Ttbandau = cat4.substring(0, cat4.indexOf("/"));
                                        String cat5 = cat4.substring(cat4.indexOf("/") + 1);
                                        Ttsau = cat5;
                            %>
                            <tr>
                                <td><% out.print(Acc); %></td>
                                <td><% out.print(Date); %></td>
                                <td><% out.print(Ttbandau); %></td>
                                <td><% out.print(Ttsau); %></td>
                                <td><% out.print(Note); %></td>
                            </tr>
                            <%}
                            } else {
                            %>
                            <tr>            
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <%} %>
                        </table>
                        </p>               
                    </div>
                </div>             
            </div>       
        </div>  
    </div>
</div>



<div id="modaXacNhan" class="w3-modal" >
    <div class="w3-modal-content w3-animate-top w3-card-4"style="width:25%">
        <header class="w3-container "> 
        </header>
        <center>
            <img src="https://img.icons8.com/dusk/64/000000/ask-question.png"/>
            <p>Do you want to update the status of this Order?</p>
        </center>
        <footer class="w3-container">
            <div style="text-align:right">
                <center>
                    <span onclick="document.getElementById('modaXacNhan').style.display = 'none';updateStatus(<% out.print(orderDetail.getOrderID().getOrderID());%>)">
                        <button class="w3-button w3-teal">Agree</button>
                    </span>
                    <span onclick="document.getElementById('modaXacNhan').style.display = 'none'">
                        <button class="w3-button w3-red">Cancel</button>
                    </span>    
                </center>

            </div>
        </footer>
    </div>
</div>




