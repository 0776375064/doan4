<%-- 
    Document   : listView
    Created on : Jul 16, 2021, 10:33:40 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/admin/feedback/header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="center">
        Create new Feedbacks
    </div>
</div>
<div class="main-content">
    <%
        String msg = request.getParameter("msg");
        if (msg != null && msg.equals("success")) {
            out.println("<div class='alert alert-success'>Create success!</div><br/>");
        }
    %>
    <form action="${pageContext.request.contextPath}/admin-feedback" method="post" autocomplete="off">
        <div class="center">
            <div class="col-6 col-md-6 ">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Title
                    </div>
                    <input type="text" name="title" class="form-control" required>
                </div>
            </div>

            <div class="col-6 col-md-6 col-sm-6">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Description
                    </div>
                    <textarea class="form-control" name="description" rows="5"></textarea>
                </div>
            </div>

            <div class="col-6 col-md-6 col-sm-6">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Email
                    </div>
                    <textarea class="form-control" name="email" rows="5"></textarea>
                </div>
            </div>




            <div class="col-12">
                <button class="btn btn-follow btn-add" type="submit" name="submit" value="them"><i class='bx bx-plus'></i> Create new</button>
            </div>

        </div>
    </form>
    <div class="space-heigh"></div>

</div>
<%@include file="../footer.jsp"%>
