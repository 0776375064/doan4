<%-- 
    Document   : Show
    Created on : Jun 26, 2021, 10:53:21 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">News</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/admin/news/statistic.jsp" class="btn btn-default light-text"><i class='bx bx-stats'></i> Report</a>
        </div>
    </div>
    <div class="row">
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/admin-news?do=create" class="btn btn-default light-text"><i class='bx bx-plus'></i> Create new</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="box-search">
                <form action="${pageContext.request.contextPath}/admin-news?do=search" method="get" autocomplete="off">
                    <div class="search-container">
                        <input type="hidden" name="do" value="search">
                        <input type="text" class="form-control" name="s" placeholder="Search ...">
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
                                <th style="text-align: center">Title</th>
                                <th>Thumbnail</th>
                                <th style="text-align: center">View</th>
                                <th style="text-align: center">Created At</th>
                                <th style="text-align: center">Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr id="row${item.newID}">
                                    <td>${item.title}</td>
                                    <td>
                                        <img src="${pageContext.request.contextPath}/storage/news/${item.thumbnail}" width="160px"/><br/>
                                    </td>
                                    <td style="width:20%;text-align: center">${item.views}</td>
                                    <td style="width:20%;text-align: center">${controller.formatDate(item.createdAt)}</td>
                                    <td class="operations">
                                        <a href="${pageContext.request.contextPath}/admin-news?do=edit&id=${item.newID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <form method="post" action="${pageContext.request.contextPath}/admin-news">
                                            <input type="hidden" name="newID" value="${item.newID}">
                                            <input type="submit" name="submit" value="delete" class="btn-sm btn-default bg-red light-text">
                                        </form>
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
<%@include file="../footer.jsp"%>
