<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="main-header">
    <div class="mobile-toggle" id="mobile-toggle">
        <i class='bx bx-menu-alt-right'></i>
    </div>
    <div class="main-title">
        Edit language of song
    </div>
</div>
<div class="main-content">

    <c:if test = "${msg == 'success'}">
        <div class='alert alert-success'>Update success!</div><br/>
    </c:if>

    <form action="${pageContext.request.contextPath}/admin-language" method="post" autocomplete="off">
        <input type="hidden" name="typeID" value="${lang.langID}">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Name
                    </div>
                    <input type="text" name="name" class="form-control" value="${lang.langName}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        <i class='bx bx-hash'></i> Description
                    </div>
                    <textarea class="form-control" name="description" rows="5">${lang.description}</textarea>
                </div>
            </div>

            <div class="col-12">
                <button class="btn btn-default btn-add" type="submit" name="submit" value="edit"><i class='bx bx-pencil'></i> Update</button>
            </div>

        </div>
    </form>
    <div class="space-heigh"></div>
    <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
            <a href="${pageContext.request.contextPath}/admin-language?do=list" class="btn btn-default light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
