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
    <div class="main-title">List of Artist</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-12">
            <div class="box-search">
                <form action="${pageContext.request.contextPath}/admin-artist?do=search" method="get" autocomplete="off">
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
            <div class="box">
                <div class="box-header">List</div>
                <div class="box-body overflow-scroll">
                    <table cellspacing="0">
                        <thead>
                            <tr>
                                <th>Artist</th>
                                <th>Full name</th>
                                <th>Birthday</th>
                                <th>Nationality</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item.artistID}">
                                    <td class="avatar">
                                        <img src="${pageContext.request.contextPath}/storage/artist/${item.avatar}"><br/>
                                        <span>${item.nickname}</span>
                                    </td>
                                    <td>${item.fullname}</td>
                                    <td>${item.birthday}</td>
                                    <td>${item.nationality}</td>
                                    <td class="operations">
                                        <a href="${pageContext.request.contextPath}/admin-artist?do=song&id=${item.artistID}" class="btn-default bg-purple"><i class='bx bx-music'></i> Songs</a>
                                        <a href="${pageContext.request.contextPath}/admin-artist?do=edit&id=${item.artistID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <a href="" data-id="${item.artistID}" class="btn-default bg-red light-text"><i class='bx bx-trash'></i> Del</a>
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
                    url: $("#url").val() + "/admin-artist?do=del",
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
</script>
<%@include file="../footer.jsp"%>
