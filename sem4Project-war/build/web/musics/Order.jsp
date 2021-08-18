<%-- 
    Document   : Order
    Created on : Jul 2, 2021, 3:17:03 AM
    Author     : hmtua
--%>

<%@page import="entities.Accounts"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="entities.Songs"%>
<%@page import="entities.Songs_"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
<style>
    .btn_print:hover{
        background-color: #ccc;
    }
    .btn_pay:hover{
        background-color: #ccc;
    }
</style>
<%    Random rn = new Random();
    String merRef = "TEST_" + System.currentTimeMillis() + rn.nextInt();
    String ipClient = request.getRemoteAddr();
    String vpc_OrderInfo, vpc_Amount, vpc_Locale, vpc_TicketNo, vpc_SHIP_Street01, vpc_SHIP_Provice, vpc_SHIP_City,
            vpc_SHIP_Country, vpc_Customer_Phone, vpc_Customer_Email, vpc_Customer_Id, AVS_Street01, AVS_City, AVS_StateProv,
            AVS_PostCode, AVS_Country, MonnyVND, MonnyUSD;
    //tine thanh toan
    MonnyUSD = "";

    if (!request.getAttribute("songID").toString().equals("null")) {
        MonnyUSD = request.getAttribute("songPrice") != null ? request.getAttribute("songPrice").toString() : "0";
    } else {
        MonnyUSD = request.getAttribute("mony") != null ? request.getAttribute("mony").toString() : "0";
    }

    String email = request.getAttribute("email") != null ? request.getAttribute("email").toString() : "hmtuana18122@cusc.ctu.edu.vn";
    double chuyenDoi = Integer.parseInt(MonnyUSD);
    chuyenDoi /= 0.000044;
    MonnyVND = String.valueOf(Math.round(chuyenDoi)) + "00";
    //ma hoa don
    vpc_OrderInfo = "Code Bill: " + request.getAttribute("id");
    //so tien can thanh toan (da nhan vs 100)
    vpc_Amount = MonnyVND;
    //Ngon ngu hien thi tren cong
    vpc_Locale = "en";
    //ip khách hàng
    vpc_TicketNo = ipClient;
    //Dia chi gui hang 
    vpc_SHIP_Street01 = " hdg dkdd d";
//    //Quan huyen 
    vpc_SHIP_Provice = "ninh kieu";
//    //tinh thanh pho 
    vpc_SHIP_City = "Cantho ";
//    //quoc gia
    vpc_SHIP_Country = "Vn";
//    //phone
    vpc_Customer_Phone = "0796864785";
//    //emial
    vpc_Customer_Email = "hmdk@gmail.com";
//    //id khach hang tren wed 
    vpc_Customer_Id = "sssa222";
    //dia chi ngan hang phat hanh    
%>
<script>
    $(".an").hide();
</script>
<style>
    .an{
        display:none;
    }
    .w3-panel{
        border-radius:4px
    }
</style>
<%
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String d1 = formatter.format(date);
%>
<div class="home" style="color: black"> 
    <form action="musics/do.jsp" method="post">
        <div class="an">
            <div class="cauhinh">
                <input type="hidden" name="Title" value="PHP VPC 3-Party">
                <input type="text" name="virtualPaymentClientURL" size="63"value="https://mtf.onepay.vn/vpcpay/vpcpay.op" maxlength="250"> 
                <input type="text" name="vpc_ReturnURL" size="50"value="${host}/Retunr"maxlength="250"/>
                <input type="text" name="vpc_Merchant" value="TESTONEPAY" size="30"maxlength="16"/>
                <input type="text" name="vpc_AccessCode" value="6BEB2546"size="30" maxlength="8"/>

                <input type="text" name="vpc_Version" value="2" size="30"maxlength="8"/>
                <input type="text" name="vpc_Command" value="pay" size="30"maxlength="16"/>
                <input type="text" name="vpc_Locale" value="en" size="30"maxlength="5"/>       
            </div>   
            <input type="text" name="vpc_MerchTxnRef"value="<%out.println(merRef);%>" size="30" maxlength="40"/>
            <input type="text" name="vpc_OrderInfo" value="<%out.println(vpc_OrderInfo);%>"size="30" maxlength="34"/>
            <input type="text" name="vpc_Amount" value="<% out.println(vpc_Amount);%>" size="30"maxlength="30"/> 
            <input type="text" name="vpc_TicketNo" maxlength="15"value="<% out.println(vpc_TicketNo);%>"/>
            <input type="text" name="vpc_SHIP_Street01" value="<% out.println(vpc_SHIP_Street01);%>" size="30"maxlength="500"/>
            <input type="text" name="vpc_SHIP_Provice" value="<% out.println(vpc_SHIP_Provice);%>"size="30" maxlength="50"/>
            <input type="text" name="vpc_SHIP_City"value="<% out.println(vpc_SHIP_City);%>" size="30"maxlength="50"/>
            <input type="text" name="vpc_SHIP_Country" value="<% out.println(vpc_SHIP_Country);%>"size="30" maxlength="50"/>
            <input type="text" name="vpc_Customer_Phone" value="<% out.println(vpc_Customer_Phone);%>" size="30"maxlength="50"/>
            <input type="text" name="vpc_Customer_Email" size="30"value="<%out.println(vpc_Customer_Email);%>"maxlength="50"/>
        </div>
        <div class="thongTinOrder">
            <div>
                <div class="w3-panel w3-card-4" >
                    <div class="w3-container">
                        <p>
                        <div class="w3-col" style="width:50%">
                            <span><h2>Invoice Information, Lnc.</h2></span> 
                        </div>
                        </p>
                        <div class="w3-col" style="width:50%">
                            <span style="margin-left:80%;padding-bottom:100px">
                                <% out.print(d1); %>
                            </span>
                        </div>
                    </div>

                    <div class="w3-row">
                        <div class="w3-col  w3-container" style="width:25%;color: black">
                            <p>Customer information</p>
                            <div class="thongTinKhachHang">
                                <p class="name"><b>${user.fullname}</b></p>
                                <p class="Addr">${user.address}</p>
                                <p class="phone">${user.phone}</p>
                                <p class="email">${user.email}</p>
                            </div>
                        </div>
                        <div class="w3-col  w3-container" style="width:25%;color: black">
                            <p>Order information</p>
                            <div class="thongTinMuaHang">
                                <p class="Order"><b>Invoice #${id}</b></p>
                                <p class="idOrder"><b>Order: </b>${id}</p>
                                <p class="date"><b>Payment Due: </b> <% out.print(d1); %></p>
                                <p class="acc"><b>Account: </b>${user.username} </p>
                            </div>
                        </div>
                        <div class="w3-col  w3-container" style="width:25%">

                        </div>
                        <div class="w3-col  w3-container" style="width:25%">

                        </div>
                    </div>
                    <%
                        if (!request.getAttribute("songID").toString().equals("null")) {
                    %>
                    <div class="thongTinSanPham">
                        <div class="sanPham">
                            <div class="w3-container">
                                <div class="music-container">
                                    <div class="chart-music">
                                        <center>
                                            <table class="music-list-chart" style="color:black">
                                                <tr class="w3-light-grey">
                                                    <td>
                                                        <b>
                                                            Qty 
                                                        </b> 
                                                    <td>
                                                        <b>
                                                            SongName  
                                                        </b>
                                                    </td>
                                                    <td>
                                                        <b>
                                                            Autdor 
                                                        </b>
                                                    </td>
                                                    <td>
                                                        <b>
                                                            Delivery date 
                                                        </b>
                                                    </td>
                                                    <td style="text-align:right">
                                                        <b>
                                                            Price  
                                                        </b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        1                    
                                                    </td>
                                                    <td>                                     
                                                        ${songID.songName}     
                                                    </td>
                                                    <td>
                                                        <%
                                                            Songs song = new Songs();
                                                            song = (Songs) request.getAttribute("songID");
                                                            out.print(song.getArtistID().getNickname());

                                                        %>

                                                    </td>
                                                    <td>
                                                        <%                                                            out.print(formatter.format(song.getRelease()));
                                                        %>
                                                    </td>

                                                    <td style="text-align:right">
                                                        ${songID.price}<span>$</span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                    </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>

                </div>
            </div>
        </div>
        <div class="thanhToan">
            <div class="">
                <div class="w3-col  " style="width:60%">
                    <div class="w3-panel w3-card-4"style="height:260px">
                        <br/>
                        <p>
                            Payment Methods:
                        </p>
                        <p>
                            <% if (request.getAttribute("songID").toString().equals("null")) {  %>
                            <input class="w3-radio" type="radio" name="pay" value="OnPay" onclick="handleClick(this.value);" checked>
                            <label>
                                <img style="width:10%;border-radius:100%" src="https://web30s.vn/datafiles/3/2016-09-15/14739292305730_onepay.jpg" alt=""/>
                            </label>
                            <%} else {
                            %>
                            <input class="w3-radio" type="radio" name="pay" value="OnPay" onclick="handleClick(this.value);" checked>
                            <label>
                                <img style="width:10%;border-radius:100%" src="https://web30s.vn/datafiles/3/2016-09-15/14739292305730_onepay.jpg" alt=""/>
                            </label>&nbsp;
                            <%
                                Accounts accont = new Accounts();
                                accont = (Accounts) request.getAttribute("user");
                                if (accont.getMoney() >= Integer.parseInt(MonnyUSD)) {
                            %>
                            <input class="w3-radio" type="radio" name="pay" value="Bcoin" onclick="handleClick(this.value);" >
                            <label style="font-size: 25px"><b style="color:red">B</b>coin</label>
                            <%
                            } else {
                            %>
                            <input class="w3-radio" type="radio" name="pay" value="Bcoin" onclick="handleClick(this.value);" disabled>
                            <label style="font-size: 25px"><b style="color:red">B</b>coin</label>
                            <%}
                                }
                            %>
                        </p>
                        <p style="font-size:10px;color: #595959">
                            Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles, weebly ning heekya handango imeem plugg dopplr jibjab, movity jajah plickers sifteo edmodo ifttt zimbra.
                        </p>
                    </div>  
                </div>
                <div class="w3-col" style="width:2%">&nbsp;</div>
                <div class="w3-col" style="width:38%">
                    <div class="w3-panel w3-card-4" style="height:260px">
                        <br/>
                        <p>Amount Due  <% out.print(d1); %></p>
                        <div class="music-container">
                            <div class="chart-music">
                                <center>
                                    <table class="music-list-chart" style="color: black">
                                        <tr>
                                            <td>
                                                <b>Subtotal: </b>                 
                                            <td style="text-align:right">
                                                <b><% out.print(MonnyUSD);  %>$ </b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>                                     
                                                <b>Tax (0%)</b>    
                                            <td style="text-align:right">                                     
                                                <b>0$</b>
                                            </td>
                                        </tr>
                                        <tr> 
                                            <td>                                     
                                                <b>Total:</b>    
                                            </td>
                                            <td style="text-align:right">                                     
                                                <b><% out.print(MonnyUSD);%>$</b> 
                                                <% session.setAttribute("Moneyn", MonnyUSD);%>
                                                <br
                                            </td>
                                        </tr>
                                    </table>
                                </center>
                            </div>
                        </div> 
                        <div class="congCu">
                            <br>    
                            <div class="">
                                <label> 
                                    <div  style="text-align:right">
                                        <button  class="w3-button w3-blue-grey w3-round-xxlarge" type="button" style="text-align:left"> 
                                            <img style="width:23px" src="${pageContext.request.contextPath}/storage/images/print.png"/> 
                                            <a href="#" onclick="inOrder()"> Print</a>
                                        </button>
                                        <button  class="w3-button w3-blue w3-round-xxlarge" value="${user.password}" type="submit"  id="thanhToan" >
                                            <img style=";content:\f09d "
                                                 height="20px" src="${pageContext.request.contextPath}/storage/images/payment.png"/> Payment
                                        </button>
                                        <button  type="button" onclick="document.getElementById('id01').style.display = 'none'"  class="w3-button w3-red w3-round-xxlarge">Close</button>                                           
                                    </div>
                                </label>                                    
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script>


    function handleClick(myRadio) {
        if (myRadio == "Bcoin") {
            document.getElementById('thanhToan').type = "button";
            //ockick thanh toan qua bcoin
            document.getElementById('thanhToan').onclick = function () {
                //goi moda thanh toan bcoin
                document.getElementById('Bcoin').style.display = 'block';
            }

        } else {
            document.getElementById('thanhToan').type = "submit";
        }


    }
    function inOrder() {
        window.print();
    }

</script>


