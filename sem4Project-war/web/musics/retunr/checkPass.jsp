<%-- 
    Document   : checkPass
    Created on : Aug 1, 2021, 11:16:41 PM
    Author     : hmtua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String thongBao = request.getAttribute("ThongTin").toString();
    out.print(thongBao);
    if (thongBao.equals("Successful")) {
%>

<script>
    document.getElementById('Bcoin').style.display = 'none';
    document.getElementById('id01').style.display = 'none'; 
    location.reload();
    Swal.fire(
            'Successful Transaction',
            'Please continue to enjoy the music!',
            'success'
            );
   
</script>
<%
    }
%>
