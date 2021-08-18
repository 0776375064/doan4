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
    <div class="main-title">List of Artist</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-3 col-sm-4">
            <a href="${pageContext.request.contextPath}/admin/artist/create.jsp" class="btn btn-default light-text"><i class='bx bx-plus'></i> Create new</a>
        </div>
         <div class="col-3 col-sm-4">
            <a class="btn btn-default bg-blue light-text btn-print"><i class='bx bx-printer'></i> Print</a>
        </div>
    </div>
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
            <div class="box" id="artist">
                <div class="box-header">List</div>
                <div class="box-body overflow-scroll">
                    <table cellspacing="0">
                        <thead>
                            <tr>
                                <th>Artist</th>
                                 <th>Nick name</th>
                                <th>Full name</th>
                                <th>Birthday</th>
                                <th>Nationality</th>
                                <th>Total song</th>
                                <th class="no-print">Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item.artistID}">
                                    <td class="avatar">
                                        <img src="${pageContext.request.contextPath}/storage/artist/${item.avatar}">
                                    </td>
                                     <td>${item.nickname}</td>
                                    <td>${item.fullname}</td>
                                    <td>${item.birthday}</td>
                                    <td>${item.nationality}</td>
                                    <td><span class="dem" data-id="${item.artistID}">${controller.countSongOfArtist(item.artistID)}</span></td>
                                    <td class="operations no-print">
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
        $(document).on('click', '.dem', function (e) {
        e.preventDefault();
        var id = $(this).attr("data-id");
        console.log(id);
        $(".modal").addClass("open");
        $(".modal-title").html("Song list");
        var flList = document.createElement("ul");
        flList.classList.add("list-followers");
        $(".modal-body").html(flList);
        $div = $("<div/>");
        $div.addClass("center");
        $div.attr("id", "loading");
        $div.html('<img src="' + $("#url").val() + '/storage/images/loading.gif" width="70" alt="">');
        $(".list-followers").append($div);
        $.ajax({
            url: $("#url").val() + "/admin-artist?do=getList",
            type: 'get',
            dataType: 'text',
            cache: false,
            data: {
                id: id,
            },
            success: function (result) {
                $(".list-followers").html(result);
                $(".list-followers").css("margin-bottom", "30px");
            },
            error: function () {
                alert("Occur error when view followers");
            }
        })
    });
     $(function () {
        $('.btn-print').on('click', function () {
            $.print("#artist");
        });
    });
</script>
<%@include file="../footer.jsp"%>
