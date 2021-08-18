<%-- 
    Document   : edit
    Created on : Jun 28, 2021, 11:51:08 AM
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
        Edit news for ${categories.categoryName}
    </div>
</div>
<div class="main-content">
    <%        String msg = request.getParameter("msg");
        if (msg != null && msg.equals("success")) {
            out.println("<div class='alert alert-success'>Create success!</div><br/>");
        }
    %>
    <form action="${pageContext.request.contextPath}/admin-news" method="post" autocomplete="off" enctype="multipart/form-data">
        <input type="hidden" name="newID" value="${param.id}">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="center">
                        <img src="${pageContext.request.contextPath}/storage/news/${news.thumbnail}" style="width: 200px;">
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Title
                    </div>
                    <input type="text" name="title" class="form-control" value="${news.title}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Description
                    </div>
                    <textarea class="form-control" name="description" rows="5" required>${news.description}</textarea>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Thumbnail
                    </div>
                    <div class="form-group center" id="imgThumbnail">

                    </div>
                    <input type="file" name="thumbnail" class="form-control" onchange="newsFile();">
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
            <a href="${pageContext.request.contextPath}/admin-news?do=list" class="btn btn-default bg-blue light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<script>
    var checkBox = false;
    $('#type1').click(function () {
        checkBox = false;
        var divContent = document.querySelector(".scroll-content");
        divContent.style.height = "0px";
        divConttent.classList.remove("active");
    });
    $('#type2').click(function () {
        checkBox = true;
        var divContent = document.querySelector(".scroll-content");
        divContent.style.height = divContent.scrollHeight + "px";
        divConttent.classList.add("active");

    });
    function newsFile(input) {
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
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script>
CKEDITOR.replace( 'description' );
</script>
<%@include file="../footer.jsp"%>
