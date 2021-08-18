<%-- 
    Document   : create
    Created on : Jun 28, 2021, 11:42:54 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">
        Create

    </div>
</div>
<div class="main-content">
    <%        String msg = request.getParameter("msg");
        if (msg != null && msg.equals("success")) {
            out.println("<div class='alert alert-success'>Create success!</div><br/>");
        }
    %>
    <form action="${pageContext.request.contextPath}/admin-news" method="post" autocomplete="off" enctype="multipart/form-data">
        <div class="row">

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Title
                    </div>
                    <input type="text" name="title" class="form-control" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Description
                    </div>
                    <textarea class="form-control" name="description" rows="5" required="required"></textarea>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Thumbnail
                    </div>
                    <input type="file" name="thumbnail" class="form-control">
                </div>
            </div>


            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Category ID
                    </div>
                    <select name="categoryID" class="form-control">
                        <c:forEach items="${categories}" var="category">
                            <c:if test="${category.status!=1}">
                                <option value="${category.status}">
                                    ${category.categoryName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
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
            <a href="${pageContext.request.contextPath}/admin-news?do=list" class="btn btn-default bg-blue light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script>
    CKEDITOR.replace('description');
</script>
<%@include file="../footer.jsp"%>
