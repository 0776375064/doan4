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
        Edit supplier
    </div>
</div>
<div class="main-content">
    <c:if test = "${msg == 'success'}">
        <div class='alert alert-success'>Update success!</div><br/>
    </c:if>
    <form action="${pageContext.request.contextPath}/admin-supplier" method="post" autocomplete="off">
        <input type="hidden" name="id" value="${supplier.supplierID}">
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Company Name
                    </div>
                    <input type="text" name="compayName" class="form-control" value="${supplier.companyName}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Contact Name
                    </div>
                    <input type="text" name="contactName" class="form-control" value="${supplier.contactName}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Address
                    </div>
                    <input type="text" name="address" class="form-control" value="${supplier.address}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Phone
                    </div>
                    <input type="text" name="phone" class="form-control" value="${supplier.phone}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Fax
                    </div>
                    <input type="text" name="fax" class="form-control" value="${supplier.fax}" required>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12">
                <div class="form-group">
                    <div class="label">
                        Home page
                    </div>
                    <input type="text" name="homepage" class="form-control" value="${supplier.hompage}" required>
                </div>
            </div>

            <div class="col-12">
                <button class="btn btn-default btn-add" type="submit" name="submit" value="edit"><i class='bx bx-plus'></i> Update</button>
            </div>

        </div>
    </form>
    <div class="space-heigh"></div>
    <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
            <a href="${pageContext.request.contextPath}/admin-supplier?do=list" class="btn btn-default light-text"><i class='bx bx-arrow-back'></i> Back to list</a>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
