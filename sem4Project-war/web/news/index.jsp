<%-- 
    Document   : category
    Created on : Jul 13, 2021, 10:43:47 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<div class="main-content">
    <!-- SECTION NEWS -->
    <div class="tt-blog-head background-block" style="background-image: url('storage/news/${news.thumbnail}')">
        <div class="tt-blog-head-inner">
            <div class="container">
                <div class="tt-blog-category">
                    <a href=""><span class="tag">${news.getCategoryID().getCategoryName()}</span></a>
                </div>
                <h1 class="tt-blog-title light-text center">${news.title}</h1>
                <div class="center">
                    <div class="tt-blog-user">
                        <a href="" class="tt-blog-user-img"><img alt="" src="https://secure.gravatar.com/avatar/578816ffce137858255eff793b54ce91?s=40&amp;d=mm&amp;r=g" srcset="https://secure.gravatar.com/avatar/578816ffce137858255eff793b54ce91?s=80&amp;d=mm&amp;r=g 2x" height="40" width="40"></a>
                        <div class="tt-blog-user-content center">
                            <span class="sp-author"><a href="" class="tt-post-author">${news.getAccountID().getFullname()}</a></span>
                            <span class="tt-post-date">${news.createdAt}</span>
                            - <span>View ${news.views}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="tt-blog-description">
            <div class="tt-blog-wrapper">
                ${news.description}
            </div>

            <div class="tt-blog-like">
                <%                if (session.getAttribute("accountID") != null) {
                %>
                <a href="${pageContext.request.contextPath}/clientNews?do=like&id=${param.id}" class="btn-like-blog" title="Like this article">
                    <c:if test="${statusLike == 0}">
                        <i class='bx bx-like'></i>
                    </c:if>
                    <c:if test="${statusLike == 1}">
                        <i class='bx bx-dislike'></i>
                    </c:if>
                    </a>
                <%
                    }
                %>
                <p><i class='bx bxs-heart red'></i> ${likes} people like this article</p>
            </div>

            <div class="empty-space"></div>
            <div class="tt-devider"></div>
            <div class="empty-space"></div>
            <%               if (session.getAttribute("accountID") != null) {
            %>
            <form action="${pageContext.request.contextPath}/clientNews" method="post">
                <input type="hidden" name="newID" value="${param.id}"/>
                <div class="box-comment">
                    <h2>Leave a Comment</h2>
                    <div class="box-comment-input">
                        <textarea name="comment" rows="3" class="form-control" placeholder="Enter your message"></textarea>
                    </div>
                    <div class="box-comment-input">
                        <button type="submit" name="submit" value="comment" class="btn btn-create">Post</button>
                    </div>
                </div>
            </form>
            <%
                } else {
                    out.println("<div class='empty center'>Please, login to discuss about this article!</div>");
                }
            %>
            <div class="empty-space"></div>

            <div class="box bg-white">
                <c:forEach items="${listCmt}" var="cmt">
                    <div class="author-flex">
                        <a href="clientAccount?do=profile&id=${cmt.getAccountID().getAccountID()}" class="author-flex-img"><img src="storage/profile/${cmt.getAccountID().getAvatar()}" alt=""></a>
                        <div class="author-flex-info">
                            <a href="clientAccount?do=profile&id=${cmt.getAccountID().getAccountID()}" class="author-flex-text">${cmt.getAccountID().getUsername()}</a>
                            <i class="times" style="color:#666">${controller.convertToTime(cmt.createdAt)}</i>
                            <div class="simple-text">
                                <p>${cmt.message}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="space-heigh"></div>
</div>
<%@include file="/includes/footer.jsp"%>
