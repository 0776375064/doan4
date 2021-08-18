<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp"%>

<div class="section-chart">
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
        <div class="section-top">Genre</div>
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <div class="row">
                        <c:forEach items="${list}" var="genre">
                            <div class="col-4 col-md-6 col-sm-6">
                                <a href="${pageContext.request.contextPath}/clientGenre?do=view&id=${genre.typeID}" class="gennre-music">
                                    <img src="${pageContext.request.contextPath}/storage/genre/${genre.thumbnail}"/>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- col - 4 -->
        </div>
    </div>
</div>
<!-- END SECTION MUSIC -->
<%@include file="/includes/footer.jsp"%>
