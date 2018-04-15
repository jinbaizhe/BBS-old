<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/head.jsp"%>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="nav flex-column nav-pills nav-justified" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">
                    修改资料
                </a>
                <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">
                    修改密码</a>
                <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">
                    我的评论
                </a>
                <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">
                    我的留言
                </a>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
                    <div class="row">
                        <div class="col-sm-6">
                            <form action="updateUserInfo.action" method="post">
                                <div class="form-group">
                                    <p class="text-danger"><s:property value="message_info"></s:property></p>
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">用户名</label>
                                    <input type=text class="form-control" name="user.username" placeholder="请输入用户名" value="<s:property value="user.username"></s:property>" readonly>
                                    <!--<small class="form-text text-muted" name=""></small>-->
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">用户类型</label>
                                    <input type="text" class="form-control" name="user.type" readonly value="<s:property value="user.type"></s:property>">
                                    <!--<small class="form-text text-muted" name=""></small>-->
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">邮箱</label>
                                    <input type="email" class="form-control" name="user.email" placeholder="请输入邮箱" value="<s:property value="user.email"></s:property>">
                                    <!--<small class="form-text text-muted" name=""></small>-->
                                </div>

                                <div class="form-group">
                                    <label for="exampleFormControlInput1">注册时间</label>
                                    <input type="text" class="form-control" name="user.registerTime" readonly value="<s:date name="user.registerTime" format="yyyy-MM-dd HH:mm:ss"></s:date>">
                                </div>

                                <div class="form-group text-center mt-sm-4">
                                    <input class="btn btn-primary btn-block" type="submit" value="保存">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade show" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                    <div class="row">
                        <div class="col-sm-6">
                            <form action="updateUserPassword.action" method="post">

                                <div class="form-group">
                                    <p class="text-danger"><s:property value="message_password"></s:property></p>
                                </div>

                                <div class="form-group">
                                    <label for="exampleFormControlInput1">旧密码</label>
                                    <input type="password" class="form-control" name="password_old" placeholder="请输入密码">
                                    <!--<small class="form-text text-muted" name=""></small>-->
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">新密码</label>
                                    <input type="password" class="form-control" name="user.password" placeholder="请输入密码">
                                    <!--<small class="form-text text-muted" name=""></small>-->
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">再次确认密码</label>
                                    <input type="password" class="form-control" name="password" placeholder="请再次输入密码">
                                    <!--<small class="form-text text-muted" name=""></small>-->
                                </div>

                                <div class="form-group text-center mt-sm-4">
                                    <input class="btn btn-primary btn-block" type="submit" value="保存">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade show" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">

                    <p>正在开发中...</p>
                </div>

                <div class="tab-pane fade show" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                    <p>未曾留言</p>

                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/foot.jsp"%>
</body>
</html>