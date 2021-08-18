<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<!--Put content here-->
<div class="section-sm sm-container">
    <div class="section-header">NEWS</div>
    <div class="zm-navbar">
        <ul class="zm-nav-info">
            <li class="zm-nav-info-item active">
                <a href="${pageContext.request.contextPath}/clientNews?do=list">All</a>
            </li>
            <c:forEach var="category" items="${controller.listCategories()}">
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientNews?do=category&id=${category.categoryID}">${category.categoryName}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="empty-space"></div>
    <div class="row">
        <div class="col-12">
            <div class="row" style="margin-left:-10px;margin-right:-10px">
                <c:forEach var="new" items="${news}">
                    <div class="col-3 col-m-4 col-sm-5">
                        <a href="${pageContext.request.contextPath}/clientNews?do=view&id=${new.newID}" class="post-thumb">
                            <img src="${pageContext.request.contextPath}/storage/news/${new.thumbnail}"
                                 alt="">
                        </a>
                    </div>
                    <div class="col-9 col-m-8 col-sm-7">
                        <div class="post-category">
                            <a href="">${new.getCategoryID().getCategoryName()}</a>
                        </div>
                        <div class="post-title">
                            <a href="${pageContext.request.contextPath}/clientNews?do=view&id=${new.newID}">
                                <h3 class="sm-text">${new.title}</h3>
                            </a>
                        </div>
                        <p class="times">
                            <span style="font-size:12px;">${new.createdAt}</span>
                        </p>
                        <div class="simple-text">
                            <span>${controller.subWord(new.description)}</span>
                        </div>
                    </div>
                    <!-- div -->
                </c:forEach>
            </div>
        </div>
        <!-- recent div -->
    </div>
</div>
<%@include file="/includes/footer.jsp"%>
