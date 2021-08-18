<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%
    int pageUrl = 1; // declare the variable outside the if else
    if (request.getParameter("page") != null) {
        pageUrl = Integer.parseInt(request.getParameter("page"));
    }
%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">${artist} - List of songs</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-5">
            <a href="${pageContext.request.contextPath}/admin-songs?do=create&id=${param.id}" class="btn btn-default light-text"><i class='bx bx-plus'></i> Create new song</a>
        </div>
        <div class="col-2">

        </div>
        <div class="col-5">
            <a href="${pageContext.request.contextPath}/admin-album?do=list&id=${id}" class="btn btn-default light-text"><i class='bx bxs-playlist'></i> Artist's Album</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="box">
                <div class="box-header">List</div>
                <div class="box-body overflow-scroll">
                    <table cellspacing="0">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Release</th>
                                <th>Album</th>
                                <th>Soft of</th>
                                <th>Language</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${songs}">
                                <tr id="row${item.getSongID().getSongID()}">
                                    <td class="avatar">
                                        <img src="${pageContext.request.contextPath}/storage/song/${item.getSongID().getThumbnail()}"/><br/>
                                        <span>${item.getSongID().getSongName()}</span>
                                    </td>
                                    <td>${item.getSongID().getPrice()} $</td>
                                    <td>${controller.formatDate(item.getSongID().getRelease())}</td>
                                    <c:if test="${item.getSongID().getTypeAlbum() == 0}">
                                        <td>Single</td>
                                    </c:if>
                                    <c:if test="${item.getSongID().getTypeAlbum() == 1}">
                                        <td>${item.getSongID().getAlbumID().getAlbumName()}</td>
                                    </c:if>
                                    <td>${item.getSongID().getTypeID().getTypeName()}</td>
                                    <td>${item.getSongID().getLangID().getLangName()}</td>
                                    <td class="operations">
                                        <a href="${pageContext.request.contextPath}/admin-songs?do=edit&id=${item.getSongID().getSongID()}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <a href="" data-id="${item.getSongID().getSongID()}" class="btn-default bg-red"><i class='bx bx-trash'></i> Del</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-12">
            <div class="center">
                <ul class="pagination">
                    <c:set var="pageUrl" value="<%=pageUrl%>"/>
                    <c:forEach var="currentPage" begin="1" end="${countPage}">
                        <li class="${pageUrl == currentPage ? 'active': ''}">
                            <a href="${pageContext.request.contextPath}/admin-songs?do=list&page=${currentPage}">${currentPage}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="space-heigh"></div>
    <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
            <a href="${pageContext.request.contextPath}/admin-artist?do=list" class="btn btn-default bg-blue light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<script>
    $('.bg-red').on('click', function (e) {
        e.preventDefault();
        var id = $(this).attr("data-id");
        console.log(id);
        //console.log($("#url").val());
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: $("#url").val() + "/admin-songs?do=del",
                    cache: false,
                    type: 'get',
                    dataType: 'json',
                    data: {
                        id: id
                    },
                    success: function (result) {
                        console.log(result);
                        if (result.msg == "error") {
                            Swal.fire(
                                    'Failed!',
                                    'This song has data related.',
                                    'error'
                                    )
                        } else if (result.msg == "success") {
                            $("#row" + id).fadeOut(1000);
                            Swal.fire(
                                    'Deleted!',
                                    'Deleted success.',
                                    'success'
                                    )
                        }

                    },
                    error: function () {
                        Swal.fire(
                                'Deleted!',
                                'Occur error when delete this album.',
                                'error'
                                )
                    }
                });
            }
        })
    });
</script>
<%@include file="../footer.jsp"%>
