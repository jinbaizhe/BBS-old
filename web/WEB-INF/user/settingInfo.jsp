<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/web/head.jsp"%>
    <title>个人信息</title>
</head>
<body>
<jsp:include page="/WEB-INF/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="nav flex-column nav-pills nav-justified" id="v-pills-tab" role="tablist" aria-orientation="vertical">

                <s:if test="type=='info'">
                    <a class="nav-link active" id="v-pills-info-tab" data-toggle="pill" href="#v-pills-info" role="tab" aria-controls="v-pills-info" aria-selected="true">
                </s:if>
                <s:else>
                    <a class="nav-link" id="v-pills-info-tab" data-toggle="pill" href="#v-pills-info" role="tab" aria-controls="v-pills-info" aria-selected="false">
                </s:else>
                    修改资料
                </a>
                <s:if test="type=='password'">
                    <a class="nav-link active" id="v-pills-password-tab" data-toggle="pill" href="#v-pills-password" role="tab" aria-controls="v-pills-password" aria-selected="true">
                </s:if>
                <s:else>
                    <a class="nav-link" id="v-pills-password-tab" data-toggle="pill" href="#v-pills-password" role="tab" aria-controls="v-pills-password" aria-selected="false">
                </s:else>
                    修改密码
                </a>

                <s:if test="type=='post'">
                    <a class="nav-link active" id="v-pills-post-tab" data-toggle="pill" href="#v-pills-post" role="tab" aria-controls="v-pills-post" aria-selected="true">
                </s:if>
                <s:else>
                    <a class="nav-link" id="v-pills-post-tab" data-toggle="pill" href="#v-pills-post" role="tab" aria-controls="v-pills-post" aria-selected="false">
                </s:else>
                    我的发帖
                </a>

                <s:if test="type=='followpost'">
                    <a class="nav-link active" id="v-pills-followpost-tab" data-toggle="pill" href="#v-pills-followpost" role="tab" aria-controls="v-pills-followpost" aria-selected="true">
                </s:if>
                <s:else>
                    <a class="nav-link" id="v-pills-followpost-tab" data-toggle="pill" href="#v-pills-followpost" role="tab" aria-controls="v-pills-followpost" aria-selected="false">
                </s:else>
                    我的回帖
                </a>


                <s:if test="type=='followpost'">
                    <a class="nav-link active" id="v-pills-star-tab" data-toggle="pill" href="#v-pills-star" role="tab" aria-controls="v-pills-star" aria-selected="true">
                </s:if>
                <s:else>
                    <a class="nav-link" id="v-pills-star-tab" data-toggle="pill" href="#v-pills-star" role="tab" aria-controls="v-pills-star" aria-selected="false">
                </s:else>
                    我的收藏
                </a>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="tab-content" id="v-pills-tabContent">

                <s:if test="type=='info'">
                    <div class="tab-pane fade show active" id="v-pills-info" role="tabpanel" aria-labelledby="v-pills-info-tab">
                </s:if>
                <s:else>
                    <div class="tab-pane fade show" id="v-pills-info" role="tabpanel" aria-labelledby="v-pills-info-tab">
                </s:else>
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

                <s:if test="type=='password'">
                    <div class="tab-pane fade show active" id="v-pills-password" role="tabpanel" aria-labelledby="v-pills-password-tab">
                </s:if>
                <s:else>
                    <div class="tab-pane fade show" id="v-pills-password" role="tabpanel" aria-labelledby="v-pills-password-tab">
                </s:else>
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
                                    <input type="password" class="form-control" name="password_repeat" placeholder="请再次输入密码">
                                    <!--<small class="form-text text-muted" name=""></small>-->
                                </div>

                                <div class="form-group text-center mt-sm-4">
                                    <input class="btn btn-primary btn-block" type="submit" value="保存">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <s:if test="type=='post'">
                    <div class="tab-pane fade show active" id="v-pills-post" role="tabpanel" aria-labelledby="v-pills-post-tab">
                </s:if>
                <s:else>
                    <div class="tab-pane fade show" id="v-pills-post" role="tabpanel" aria-labelledby="v-pills-post-tab">
                </s:else>
                    <div class="row">
                        <div class="col-sm-10">
                            <s:iterator value="posts" var="post">
                                <div class="card my-sm-3">
                                    <div class="card-body">
                                        <h5 class="card-title">帖子标题：<a href="/post.action?postid=<s:property value="#post.id"></s:property>"><s:property value="#post.title"></s:property></a></h5>
                                        <p class="card-text">
                                            <s:property value="#post.content" escapeHtml="false"></s:property>
                                        </p>
                                    </div>
                                </div>
                            </s:iterator>
                        </div>
                    </div>
                </div>


                <s:if test="type=='followpost'">
                    <div class="tab-pane fade show active" id="v-pills-followpost" role="tabpanel" aria-labelledby="v-pills-followpost-tab">
                </s:if>
                <s:else>
                    <div class="tab-pane fade show" id="v-pills-followpost" role="tabpanel" aria-labelledby="v-pills-followpost-tab">
                        <div class="row">
                            <div class="col-sm-10">
                                <s:iterator value="followposts" var="followpost">
                                    <div class="card my-sm-3">
                                        <div class="card-body">
                                            <h5 class="card-title">回复帖子：<a href="/post.action?postid=<s:property value="#followpost.post.id"></s:property>"><s:property value="#followpost.post.title"></s:property></a></h5>
                                            <p class="card-text">
                                                <s:property value="#followpost.content" escapeHtml="false"></s:property>
                                            </p>
                                        </div>
                                    </div>
                                </s:iterator>
                            </div>
                        </div>
                    </div>
                </s:else>

                <s:if test="type=='star'">
                    <div class="tab-pane fade show active" id="v-pills-star" role="tabpanel" aria-labelledby="v-pills-star-tab">
                </s:if>
                <s:else>
                    <div class="tab-pane fade show" id="v-pills-star" role="tabpanel" aria-labelledby="v-pills-star-tab">
                        <p>“我的收藏”正在开发中...</p>
                    </div>
                </s:else>

            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/web/foot.jsp"%>
</body>
</html>