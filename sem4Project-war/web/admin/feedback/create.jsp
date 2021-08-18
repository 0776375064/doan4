<%-- 
   Document   : create
   Created on : Jul 3, 2021, 9:32:35 PM
   Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">
        Create new Feedbacks
    </div>
</div>
<div class="main-content">
    <%            String msg = request.getParameter("msg");
        if (msg != null && msg.equals("success")) {
            out.println("<div class='alert alert-success'>Create success!</div><br/>");
        }
    %>
    <form action="${pageContext.request.contextPath}/admin-feedback" method="post" autocomplete="off">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Title
                    </div>
                    <input type="text" name="title" class="form-control" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Description
                    </div>
                    <textarea class="form-control" name="description" rows="5"></textarea>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Email
                    </div>
                    <textarea class="form-control" name="email" rows="5"></textarea>
                </div>
            </div>




            <div class="col-12">
                <button class="btn btn-default btn-add" type="submit" name="submit" value="add"><i class='bx bx-plus'></i> Create new</button>
            </div>

        </div>
    </form>
    <div class="space-heigh"></div>
    <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
            <a href="${pageContext.request.contextPath}/admin-feedback?do=list" class="btn btn-default light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
