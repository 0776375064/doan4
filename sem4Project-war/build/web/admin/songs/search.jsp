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
    <div class="main-title">Search ${param.s} about ${list.size()} result</div>
</div>
<div class="main-content">
    <div class="row">
          <div class="col-3 col-sm-4">
            <a class="btn btn-default bg-blue light-text btn-print"><i class='bx bx-printer'></i> Print</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="box-search">
                <form action="${pageContext.request.contextPath}/admin-songs?do=search" method="get" autocomplete="off">
                    <div class="search-container">
                        <input type="hidden" name="do" value="search">
                        <input type="text" class="form-control" name="s" value="${param.s}" placeholder="Search ...">
                        <button type="submit"><i class='bx bx-search'></i></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="box" id="listSearch">
                <div class="box-header">List</div>
                <div class="box-body overflow-scroll">
                    <table cellspacing="0">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Artist</th>
                                <th>Soft of</th>
                                <th>Language</th>
                                <th>Release date</th>
                                <th>Album</th>
                                <th class="no-print">Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item.songID}">
                                    <td class="avatar">
                                        <img src="${pageContext.request.contextPath}/storage/song/${item.thumbnail}"/><br/>
                                        <span>${item.songName}</span>
                                    </td>
                                    <td>${item.getArtistID().getFullname()}</td>
                                    <td>${item.getTypeID().getTypeName()}</td>
                                    <td>${item.getLangID().getLangName()}</td>
                                    <td>${item.release}</td>
                                    <c:if test="${item.typeAlbum == 0}">
                                        <td>Single</td>
                                    </c:if>
                                    <c:if test="${item.typeAlbum == 1}">
                                        <td>${item.getAlbumID().getAlbumName()}</td>
                                    </c:if>
                                    <td class="operations no-print">
                                        <a href="${pageContext.request.contextPath}/admin-songs?do=edit&id=${item.songID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <a href="" data-id="${item.songID}" class="btn-default bg-red light-text"><i class='bx bx-trash'></i> Del</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
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
                                    'This Artist has data in system.',
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
      $(function () {
        $('.btn-print').on('click', function () {
            $.print("#listSearch");
        });
    });
</script>
<%@include file="../footer.jsp"%>
