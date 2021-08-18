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
    <div class="section-header">My history orders</div>
    <div class="zm-navbar">
        <ul class="zm-nav-info">
            <c:if test="${type == 'all'}">
                <li class="zm-nav-info-item active">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=all">All</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=paid">Paid</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=unpaid">Unpaid</a>
                </li>
            </c:if>
            <c:if test="${type == 'paid'}">
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=all">All</a>
                </li>
                <li class="zm-nav-info-item active">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=paid">Paid</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=unpaid">Unpaid</a>
                </li>
            </c:if>
            <c:if test="${type == 'unpaid'}">
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=all">All</a>
                </li>
                <li class="zm-nav-info-item">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=paid">Paid</a>
                </li>
                <li class="zm-nav-info-item active">
                    <a href="${pageContext.request.contextPath}/clientAccount?do=orders&type=unpaid">Unpaid</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="empty-space"></div>
    <div class="music-container">
        <div class="chart-music">
            <table class="music-list-chart" cellpadding="0" cellspacing="0">
                <tr>
                    <th>Order ID</th>
                    <th>Song</th>
                    <th>Price</th>
                    <th>Order Date</th>
                    <th>Bank name</th>
                    <th>Status</th>
                </tr>
                <c:forEach varStatus="loop" var="order" items="${list}">
                    <tr>
                        <td>${order[0]}</td>
                        <td><a href="SongDetail?songID=${order[7]}">${controller.songInfo(order[7]).songName}</a></td>
                        <td>${order[1]} $</td>
                        <td>${order[2]}</td>
                        <td>${order[5]}</td>
                        <td>
                            <c:if test="${order[4] == 1 && order[6] == 1}">
                                <span class="status">Paid</span>
                            </c:if>
                            <c:if test="${order[4] == 1 && order[6] == null}">
                                <span class="status unpaid">Unpaid</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp"%>
