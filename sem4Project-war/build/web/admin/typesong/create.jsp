<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">
        Create new Type of song
    </div>
</div>
<div class="main-content">
    <%            String msg = request.getParameter("msg");
        if (msg != null && msg.equals("success")) {
            out.println("<div class='alert alert-success'>Create success!</div><br/>");
        }
    %>
    <c:if test="${not empty msg}">
        <div class='alert alert-danger'>File uploaded is not a valid format. You must have uploaded file with extension JPG, PNG, JPEG!</div><br/>
    </c:if>
    <form action="${pageContext.request.contextPath}/admin-typesong" method="post" autocomplete="off" enctype="multipart/form-data">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Name
                    </div>
                    <input type="text" name="typename" class="form-control" required>
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
                        <i class='bx bx-upload'></i> Thumbnail (Only PNG, JPG or JPEG images are allowed to be uploaded)
                    </div>
                     <div class="form-group center" id="imgThumbnail">

                    </div>
                    <input type="file" name="thumbnail" class="form-control" onchange="previewFile();" accept="image/*" required>
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
            <a href="${pageContext.request.contextPath}/admin-typesong?do=list" class="btn btn-default light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
            <script>
    function previewFile(input) {
        var file = $("input[type=file]").get(0).files[0];
        var preThum = document.querySelector('#imgThumbnail');
        if (file) {
            var reader = new FileReader();

            reader.onload = function () {
                preThum.innerHTML = "";
                var image = new Image();
                image.width = 200;
                image.title = file.name;
                image.src = reader.result;
                preThum.appendChild(image);
            }

            reader.readAsDataURL(file);
        }
    }
</script>
<%@include file="../footer.jsp"%>
