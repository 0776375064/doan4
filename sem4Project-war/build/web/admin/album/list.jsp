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
    <div class="main-title">List of Album</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/admin/album/create.jsp?id=${param.id}" class="btn btn-default light-text"><i class='bx bx-plus'></i> Create new album</a>
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
                                <th>Cover</th>
                                <th>Album name</th>
                                <th>Release</th>
                                <th>Artist</th>
                                <th>Total track</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item.albumID}">
                                    <td class="avatar"><img src="${pageContext.request.contextPath}/storage/album/${item.thumbnail}"></td>
                                    <td>${item.albumName}</td>
                                    <td>${item.release}</td>
                                    <td>${item.getArtistID().getNickname()}</td>
                                    <td>${controller.countTrack(item.albumID)}</td>
                                    <td class="operations">
                                        <a href="${pageContext.request.contextPath}/admin-album?do=edit&id=${item.albumID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <a href="" data-id="${item.albumID}" class="btn-sm btn-default bg-red">Del</a>
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
                            <a href="${pageContext.request.contextPath}/admin-artist?do=list&page=${currentPage}">${currentPage}</a>
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
            <a href="${pageContext.request.contextPath}/admin-artist?do=song&id=${param.id}" class="btn btn-default bg-blue light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>

<script>
    $('.bg-red').on('click', function (e) {
        e.preventDefault();
        var id = $(this).attr("data-id");
        //console.log(id);
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
                    url: $("#url").val() + "/admin-album?do=del",
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
                                    'This album has song in it.',
                                    'error'
                                    )
                        } else if (result.msg == "success") {
                            $("#row"+id).fadeOut(1000);
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
