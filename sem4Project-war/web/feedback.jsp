<%-- 
    Document   : index
    Created on : Jun 18, 2021, 2:09:08 PM
    Author     : asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp"%>
<style>
    .input label{
        margin-bottom: 10px;
        display: block;
    }
    .input{
        margin-bottom: 10px;
    }
    .form-control{
        border-radius: 10px;
    }
    .msg-success{
        position: relative;
        color: #fff;
        text-align:center;
        padding: 10px 5px;
        background-color: #11a761;
        margin-bottom: 10px;
    }
</style>
<div class="section-chart">
    <div class="bg-blur"></div>
    <div class="bg-alpha"></div>
    <div class="container">
        <div class="section-top">Feedback</div>
        <%
        if(request.getParameter("msg") != null)
        {
            out.println("<div class='msg-success'>Send feedback to admin success!</div>");
        }
        %>
        <div class="row">
            <div class="col-12 col-md-12 col-sm-12">
                <div class="week-chart">
                    <form action="${pageContext.request.contextPath}/admin-feedback" method="post">
                        <div class="input">
                            <label>Email</label>
                            <input type="email" name="email" required="required" class="form-control">
                        </div>
                        <div class="input">
                            <label>Title</label>
                            <input type="text" name="title" required="required" class="form-control">
                        </div>
                        <div class="input">
                            <label>Description</label>
                            <textarea class="form-control" rows="5" name="description"></textarea>
                        </div>
                        <div class="input">
                            <button type="submit" name="submit" value="them" class="btn btn-create btn-add">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- col - 4 -->
        </div>
    </div>
</div>
<!-- END SECTION MUSIC -->
<%@include file="includes/footer.jsp"%>
