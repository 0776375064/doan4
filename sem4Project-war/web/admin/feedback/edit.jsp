<%-- 
    Document   : edit
    Created on : Jul 3, 2021, 9:32:57 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">
        Edit Feedback
    </div>
</div>
<div class="main-content">

    <%        String msg = request.getParameter("msg");
        if (msg != null && msg.equals("success")) {
            out.println("<div class='alert alert-success'>Update data success!</div><br/>");
        }
    %>

    <form action="${pageContext.request.contextPath}/admin-feedback" method="post" autocomplete="off">
        <input type="hidden" name="feedbackID" value="${feedback.feedbackID}">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Title
                    </div>
                    <input type="text" name="title" class="form-control" value="${feedback.title}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Description
                    </div>
                    <textarea class="form-control" name="description" rows="5">${feedback.description}</textarea>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Email
                    </div>
                    <textarea class="form-control" name="email" rows="5">${feedback.email}</textarea>
                </div>
            </div>


            <div class="col-12">
                <button class="btn btn-default btn-add" type="submit" name="submit" value="edit"><i class='bx bx-pencil'></i> Update</button>
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

