<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="/WEB-INF/web/head.jsp"%>
    <title>忘记密码</title>
</head>
<body>
<jsp:include page="/WEB-INF/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4 border" style="border: grey;border-radius: 15px;padding: 20px;margin-left: auto;margin-right: auto;">
            <form action="/user/validateForgetPassword.action" method="post">
                <div class="form-group">
                    <div class="text-danger text-center">
                        <h5>
                            <s:property value="message_info"></s:property>
                        </h5>
                    </div>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="user.username" value="<s:property value="user.username"></s:property>" disabled>
                    <input type="text" class="form-control" name="user.id" value="<s:property value="user.id"></s:property>" hidden>
                    <input type="text" class="form-control" name="activekey" value="<s:property value="activekey"></s:property>" hidden>
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>

                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="user.password" placeholder="请输入密码">
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>
                <div class="form-group">
                    <label>再次确认密码</label>
                    <input type="password" class="form-control" name="password_repeat" placeholder="请再次输入密码">
                    <!--<small class="form-text text-muted" name=""></small>-->
                </div>
                <!--
                <div class="form-group">
                    <div class="input-group">
                        <label>验证码</label>
                        <img src="getVerifyCode.action" width="100" height="30" alt="无法显示验证码">
                        <input type="text" class="form-control" name="verifyCode" placeholder="请输入左侧验证码">
                    </div>
                    <small class="form-text text-muted" name=""></small>
                </div>
                -->
                <div class="form-group text-center mt-sm-4">
                    <input class="btn btn-primary btn-block" type="submit" value="立即修改密码">
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/web/foot.jsp"%>
</body>
</html>