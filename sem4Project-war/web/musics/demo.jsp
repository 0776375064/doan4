<%-- 
    Document   : Payment_results_ returned
    Created on : Jul 7, 2021, 12:29:10 AM
    Author     : hmtua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>
<%    //tra ve trang thai giao dich
    String trangThai = "0";

%>


<script>
    <%if (trangThai.equals("0")) {%>
    Swal.fire(
            'Successful Transaction',
            'Please continue to enjoy the music!',
            'success'
            );
    <%} else {%>
    Swal.fire(
            'Payment Error',
            'Please re-create it, Or contact the administrator for help',
            'error'
            );
    <%}%>
</script>


