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
    <div class="main-title">Account</div>
</div>
<div class="main-content">
    <div class="row">
         <div class="col-3 col-sm-4">
            <a class="btn btn-default bg-blue light-text btn-print"><i class='bx bx-printer'></i> Print</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="box" id="accountPg">
                <div class="box-header">List</div>
                <div class="box-body overflow-scroll">
                    <table cellspacing="0">
                        <thead>
                            <tr>
                                <th>Avatar</th>
                                <th>Username</th>
                                <th>Full name</th>
                                <th>Email</th>
                                <th>Gender</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th class="no-print">Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item.accountID}">
                                    <td class="avatar-profile"><img src="${pageContext.request.contextPath}/storage/profile/${item.avatar}"></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/clientAccount?do=profile&id=${item.accountID}" target="_blank">${item.username}</a>
                                    </td>
                                    <td>${item.fullname}</td>
                                    <td>${item.email}</td>
                                    <td>
                                        <c:if test="${item.gender == 'f'}">
                                            <span>Female</span>
                                        </c:if>
                                        <c:if test="${item.gender == 'm'}">
                                            <span>Male</span>
                                        </c:if>
                                    </td>
                                    <td>${item.role}</td>
                                    <td>
                                        <c:if test="${item.status == 0}">
                                            <font color="green">Active</font>
                                        </c:if>
                                        <c:if test="${item.status == 1}">
                                            <font color="red">Banned</font>
                                        </c:if>
                                    </td>
                                    <td class="operations no-print">
                                        <a href="${pageContext.request.contextPath}/admin-account?do=edit&id=${item.accountID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <c:if test="${item.status == 0}">
                                            <a href="" data-id="${item.accountID}" class="btn-default bg-yellow"><i class='bx bx-block'></i> Ban</a>
                                        </c:if>
                                        <c:if test="${item.status == 1}">
                                            <a href="" data-id="${item.accountID}" class="btn-default bg-green"><i class='bx bx-block'></i> Unlocked</a>
                                        </c:if>
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
    /// block user
    $(".bg-yellow").on('click', function (e) {
        e.preventDefault();
        var id = $(this).attr("data-id");
        Swal.fire({
            title: 'Are you sure?',
            text: "This account is going block and cannot login any more!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, I know!'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: $("#url").val() + "/admin-account?do=block",
                    type: 'get',
                    dataType: 'json',
                    cache: false,
                    data: {
                        id: id
                    },
                    success: function (result) {
                        if (result.msg == "block") {
                            Swal.fire(
                                    'Success!',
                                    'Account has blocked!',
                                    'success'
                                    );
                        } else {
                            Swal.fire(
                                    'Success!',
                                    'Account has unblocked!',
                                    'success'
                                    );
                        }
                    },
                    error: function () {
                        Swal.fire(
                                'Error!',
                                'Can not handling this request right now!',
                                'error'
                                );
                    }
                });
            }
        })
    });

/// block user
    $(".bg-green").on('click', function (e) {
        e.preventDefault();
        var id = $(this).attr("data-id");
        Swal.fire({
            title: 'Are you sure?',
            text: "This account is going unblock!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, I know!'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: $("#url").val() + "/admin-account?do=block",
                    type: 'get',
                    dataType: 'json',
                    cache: false,
                    data: {
                        id: id
                    },
                    success: function (result) {
                        if (result.msg == "block") {
                            Swal.fire(
                                    'Success!',
                                    'Account has blocked!',
                                    'success'
                                    );
                        } else {
                            Swal.fire(
                                    'Success!',
                                    'Account has unblocked!',
                                    'success'
                                    );
                        }
                    },
                    error: function () {
                        Swal.fire(
                                'Error!',
                                'Can not handling this request right now!',
                                'error'
                                );
                    }
                });
            }
        })
    });
     $(function () {
        $('.btn-print').on('click', function () {
            $.print("#accountPg");
        });
    });
</script>
<%@include file="../footer.jsp"%>
