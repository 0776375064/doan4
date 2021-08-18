<%-- 
    Document   : Payment_results_ returned
    Created on : Jul 7, 2021, 12:29:10 AM
    Author     : hmtua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!DOCTYPE html>
<%@include file="../includes/header.jsp"%>
<%
    //tra ve trang thai giao dich
    String trangThai = request.getParameter("vpc_TxnResponseCode");
    //Giá trị của vpc_Command đã gửi đi trong file DO đượctrả lại trên file DRString
    String vpc_Command = request.getParameter("vpc_Command ");
    //Giá trị của đối số vpc_MerchTxnRef gửi đi từ file DO trả lại trên file DR
    String vpc_MerchTxnRef = request.getParameter("vpc_MerchTxnRef");
    //Giá trị của đối số vpc_Merchant gửi đi từ file DO được trả lại trong file DR.
    String vpc_Merchant = request.getParameter("vpc_Merchant ");
    //Giá trị của đối số vpc_OrderInfo trên file DO được trả lại trên file DR
    String vpc_OrderInfo = request.getParameter("vpc_OrderInfo");
    //Giá trị của đối số vpc_Amount trên file DO được trả lại trên file DR
    String vpc_Amount = request.getParameter("vpc_Amount");
    //Thông tin này được lưu trên cổng thanh toán để ánh xạ cho phép người sử dụng thực hiện các chức năng như refund hay capture.
    String vpc_TransactionNo = request.getParameter("vpc_TransactionNo ");
    //Mô tả lỗi giao dịch khi thanh toán
    String vcp_Message = request.getParameter("vcp_Message ");
    //6 số định danh ngân hàng thanh toán (tương tự 6 số đầu của thẻ)
    String vpc_AcqResponseCode = request.getParameter("vpc_AcqResponseCode");
    //Một mã code được sinh ra từ ngân hàng để chấp nhận từ chối giao dịch.
    String vpc_Authorizeld = request.getParameter("vpc_Authorizeld ");
    //Loại the thanh toan vc mc jc ae 
    String vpc_Card = request.getParameter("vpc_Card ");
%>
<style>
    div[name="thongtin"]{
        background-color:#555 ;
        box-shadow: 0px 0px 5px;
        font-size:120%;
        border-radius:10px
    }
</style>
<br/><br/><br/>
<%if (trangThai.equals("0")) {%> 
<div class="thanhCong">
    <div class="w3-row">
        <div class="w3-col  w3-container" style="width:25%"></div>
        <div name="thongtin" class="w3-col  w3-container" style="width:50%">
            <center>
                <img src="https://img.icons8.com/color/144/000000/ok--v1.png"/>
                <h2>Successful Transaction</h2>
            </center>
            <div class="w3-container">
                <h4>Trading code: <%out.print(vpc_MerchTxnRef + "<br/>");%></h4>
                <%out.print(vpc_OrderInfo.substring(0, vpc_OrderInfo.indexOf("/")) + "<br/>");%>
                <%
                    int i = Integer.parseInt(vpc_Amount);
                    i/=100;
                    String string = String.valueOf(i);
                    String string2 ="";
                    char[] ch1 = string.toCharArray();
                    char[] ch2 = null;
                    for (int idx = 0; idx < ch1.length; idx++) {
                            if(idx%3==0 && idx!=0){
                                string2+="."+ch1[idx];
                            }else{
                                string2+=ch1[idx] ;
                            }
                        }
                %>
                <h4>Total money: <%out.print(string2 +  " VND<br/>");%></h4>
            </div>
        </div>
        <div class="w3-col  w3-container" style="width:25%"></div>
    </div>
</div>
<%}%> 
<%if (!trangThai.equals("0")) {%> 
<div class="thatBai">
    <div>

    </div>
</div>
<%}%>


<%@include file="../includes/footer.jsp"%>
