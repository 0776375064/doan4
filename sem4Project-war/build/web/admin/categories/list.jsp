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
    <div class="main-title">Category</div>
</div>
<div class="main-content">
    <div class="row">
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/admin/categories/create.jsp" class="btn btn-default light-text"><i class='bx bx-plus'></i> Create new</a>
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
                                <th>STT</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th style="text-align: center">Status</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr>
                                    <td>${item.categoryID}</td>
                                    <td>${item.categoryName}</td>
                                    <td style="width: 40%">${item.description}</td>
                                    <c:if test="${item.status == 0}">
                                        <td style="width: 20%; text-align: center;color:green;">Active</td>
                                    </c:if>
                                    <c:if test="${item.status == 1}">
                                        <td style="width: 20%; text-align: center;color:red;">Inactive</td>
                                    </c:if>
                                    <td class="operations">
                                        <a href="${pageContext.request.contextPath}/admin-categories?do=edit&id=${item.categoryID}" class="btn-default bg-blue light-text"><i class='bx bx-pencil'></i> Edit</a>
                                        <form method="post" action="${pageContext.request.contextPath}/admin-categories">
                                            <input type="hidden" value="${item.categoryID}" name="categoryID"/>
                                             <button type="submit" value="delete" name="submit" class="btn-default bg-red light-text"><i class='bx bx-trash'></i> Del</button>
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
