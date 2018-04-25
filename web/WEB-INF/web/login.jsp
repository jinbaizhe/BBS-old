<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>登录</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4 border" style="border: grey;border-radius: 15px;padding: 20px;margin-left: auto;margin-right: auto;">
            <form action="validateLogin.action" method="post">
                <div class="form-group">
                    <div class="text-danger text-center">
                        <h5>
                            <s:property value="loginInfo"></s:property>
                        </h5>
                    </div>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="user.username" placeholder="请输入用户名">
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="user.password" placeholder="请输入密码">
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" name="autologin" checked>
                    <label class="form-check-label">下次自动登录</label>
                </div>
                <div class="form-group text-center mt-sm-4">
                    <input class="btn btn-primary btn-block" type="submit" value="登录">
                </div>

                <div class="form-group">
                    <a class="float-sm-left" href="#">忘记密码?</a>
                    <a class="float-sm-right" href="register.action">立即注册</a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<%@include file="foot.jsp"%>
</body>
</html>