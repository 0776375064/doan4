<%-- 
    Document   : list
    Created on : Aug 2, 2021, 7:50:40 PM
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
        <div class="col-12">
            <div class="box">
                <div class="box-header">List</div>
                <div class="box-body overflow-scroll">
                    <table cellspacing="0">
                        <thead>
                            <tr>
                                
                               
                                <th>User</th>
                                <th>Content</th>
                                <th>Theme</th>
                               
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr>
                                    <td>${item.accountID.username}</td>
                                    <td>${item.message}</td>
                                    <td>${item.newID.title}</td> 
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
