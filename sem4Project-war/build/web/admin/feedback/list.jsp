<%-- 
    Document   : list
    Created on : Jul 3, 2021, 9:38:23 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">Feedback</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/admin/feedback/create.jsp" class="btn btn-default light-text"><i class='bx bx-plus'></i> Create new</a>
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
                                <th>Title</th>
                                <th>Description</th>
                                <th>Email</th>
                                <th>Created At</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="tem" items="${list}">
                                <tr>
                                    <td>${tem.title}</td>
                                    <td>${tem.description}</td>
                                    <td>${tem.email}</td>
                                    <td>${tem.createdAt}</td>
                                    <td class="operations">
                                        <a href="${pageContext.request.contextPath}/admin-feedback?do=edit&id=${tem.feedbackID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <form method="post" action="${pageContext.request.contextPath}/admin-feedback">
                                            <input type="hidden" name="feedbackID" value="${tem.feedbackID}">
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
