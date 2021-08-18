<%-- 
    Document   : Show
    Created on : Jun 26, 2021, 3:59:23 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">Advertisement</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/admin/advertise/create.jsp" class="btn btn-default light-text"><i class='bx bx-plus'></i> Create new</a>
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
                                <th>Advertise name</th>
                                <th>Description</th>
                                <th>Thumbnail</th>
                                <th>Author</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item.advertiseID}">
                                    <td>${item.advertiseName}</td>
                                    <td style="width:35%;">${item.description}</td>
                                    <td class="thumbnail"><img src="${pageContext.request.contextPath}/storage/banner/${item.thumbnail}"></td>
                                    <td>${item.getAccountID().getFullname()}</td>
                                    <td class="operations">
                                        <a href="${pageContext.request.contextPath}/admin-advertise?do=edit&id=${item.advertiseID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <a href="" data-id="${item.advertiseID}" class="btn-default bg-red"><i class='bx bx-trash-alt'></i> Del</a>
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
                    url: $("#url").val() + "/admin-advertise?do=del",
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
                                    'Not found this advertisement.',
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
                                'Occur error when delete this advertisement.',
                                'error'
                                )
                    }
                });
            }
        })
    });
</script>
<%@include file="../footer.jsp"%>
