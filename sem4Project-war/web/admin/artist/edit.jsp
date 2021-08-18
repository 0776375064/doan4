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
        Update information of artist
    </div>
</div>
<div class="main-content">
    <c:if test = "${msg == 'success'}">
        <div class='alert alert-success'>Update success!</div><br/>
    </c:if>
    <c:if test = "${msg == 'avatar'}">
        <div class='alert alert-danger'>Format of file invalid. Must be PNG, JPEG or JPG!</div><br/>
    </c:if>

    <form action="${pageContext.request.contextPath}/admin-artist" method="post" autocomplete="off" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${artist.artistID}">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="center">
                        <img src="${pageContext.request.contextPath}/storage/artist/${artist.avatar}" class="edit-thumbnail">
                    </div>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Nick name
                    </div>
                    <input type="text" name="nickname" class="form-control" value="${artist.nickname}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Full name
                    </div>
                    <input type="text" name="fullname" class="form-control" value="${artist.fullname}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Birthday
                    </div>
                    <input type="text" name="birthday" class="form-control" value="${artist.birthday}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Nationality
                    </div>
                    <input type="text" name="nationality" class="form-control" value="${artist.nationality}" required>
                </div>
            </div>


            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Description
                    </div>
                    <textarea class="form-control" name="description" rows="5" required="required">${artist.description}</textarea>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Achievement
                    </div>
                    <textarea class="form-control" name="achievement" rows="5">${artist.achievement}</textarea>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Avatar (Only PNG, JPG or JPEG images are allowed to be uploaded)
                    </div>
                    <div class="form-group center" id="imgThumbnail">

                    </div>
                    <input type="file" name="avatar" class="form-control" onchange="previewFile();" accept="image/*">
                </div>
            </div>

            <div class="col-12">
                <button class="btn btn-default btn-add" type="submit" name="submit" value="edit"><i class='bx bx-plus'></i> Update</button>
            </div>

        </div>
    </form>
    <div class="space-heigh"></div>
    <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
            <a href="${pageContext.request.contextPath}/admin-artist?do=list" class="btn btn-default light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script>
    CKEDITOR.replace('achievement');
</script>
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
