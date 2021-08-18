<%-- 
    Document   : details
    Created on : Jul 11, 2021, 9:42:15 PM
    Author     : HP
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">News</div>
</div>

 <div class="section-sm container">
        <div class="section-header">Category</div>
        <div class="row">
            <div class="col-12">
                <div class="row" style="margin-left:-10px;margin-right:-10px">
                    <c:forEach var="item" items="${list}">
                                <tr id="row${item.newID}">

                                    <td class="avatar">
                                        <img src="${pageContext.request.contextPath}/storage/news/${item.thumbnail}" width="400" height="500"/><br/>
                                    </td>
                                </tr>
                                <div class="col-9 col-m-8 col-sm-7">
                        <div class="post-category">
                            <a href="">Title</a>
                        </div>
                        <div class="post-title">
                            <a href="">
                                <h3 class="sm-text">${item.title}</h3>
                            </a>
                        </div>
                       
                        <div class="simple-text">
                            <span>${item.description}
                            </span>
                        </div>
                        <div class="post-action">
                            <span class="tt-post-comment"><i class='bx bxs-comment'></i>0 Comment</span>
                            <span class="tt-post-views"><i class="far fa-eye"></i>${item.views} Views</span>
                            <a href="${pageContext.request.contextPath}/admin-news?do=category&id=${item.newID}" class="btn btn-follow bg-blue light-text"><i class="bx bx-heart"></i>Read</a>
                                      
                        </div>
                    </div>
                    </c:forEach>
                    
                    <!-- div -->
                </div>
            </div>
            <!-- recent div -->
        </div>
    </div>
<%@include file="/includes/footer.jsp"%>