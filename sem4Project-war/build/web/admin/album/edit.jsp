<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">
        Edit album
    </div>
</div>
<div class="main-content">
    <%        String msg = request.getParameter("msg");
        if (msg != null && msg.equals("success")) {
            out.println("<div class='alert alert-success'>Create success!</div><br/>");
        }
    %>
    <form action="${pageContext.request.contextPath}/admin-album" method="post" autocomplete="off" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${album.albumID}">
        <div class="col-12 col-md-12 col-sm-12">
            <div class="form-group">
                <div class="center">
                    <img src="${pageContext.request.contextPath}/storage/album/${album.thumbnail}">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Album Name
                    </div>
                    <input type="text" name="name" class="form-control" value="${album.albumName}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Release date
                    </div>
                    <input type="date" name="release" class="form-control" value="${date}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Cover
                    </div>
                    <input type="file" name="thumbnail" class="form-control">
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
            <a href="${pageContext.request.contextPath}/admin-album?do=list&id=${album.getArtistID().getArtistID()}" class="btn btn-default light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
