<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title><s:property value="subForum.name"></s:property></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">

            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="mainforum.action">全部</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="mainforum.action?mfid=<s:property value="subForum.mainForum.id"></s:property>"><s:property value="subForum.mainForum.name"></s:property></a>
                </li>
                <li class="breadcrumb-item active">
                    <s:property value="subForum.name"></s:property>
                </li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <h3><s:property value="subForum.name"></s:property></h3>
            <p><s:property value="subForum.info"></s:property></p>
            <div class="float-right mb-sm-2">
                <a class="btn btn-primary" href="posting.action?subforumid=<s:property value="subForum.id"></s:property>">发帖</a>
            </div>
            <div>
                <s:if test="order=='postsend'">
                    <a class="btn btn-primary btn-sm" href="/subforum.action?sfid=<s:property value="subForum.id"></s:property>&order=lastfollowpost">按最后回复时间排序</a>
                    <a class="btn btn-primary btn-sm active" href="/subforum.action?sfid=<s:property value="subForum.id"></s:property>&order=postsend">按发帖时间排序</a>
                </s:if>
                <s:else>
                    <a class="btn btn-primary btn-sm active" href="/subforum.action?sfid=<s:property value="subForum.id"></s:property>&order=lastfollowpost">按最后回复时间排序</a>
                    <a class="btn btn-primary btn-sm" href="/subforum.action?sfid=<s:property value="subForum.id"></s:property>&order=postsend">按发帖时间排序</a>
                </s:else>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">标题</th>
                    <th scope="col">作者</th>
                    <th scope="col">发帖时间</th>
                    <th scope="col">最后回复时间</th>
                    <th scope="col">回复/查看</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="posts" var="temp">
                    <s:set var="post" value="#temp[0]"></s:set>
                    <s:set var="lastfollowpostsendtime" value="#temp[1]"></s:set>
                    <tr>
                        <th scope="row">
                            <s:if test="#post.type==1">
                                [<span style="color: indigo">精华</span>]
                            </s:if>
                            <s:if test="#post.top==1">
                                [<span style="color: red">置顶</span>]
                            </s:if>
                            <a href="post.action?postid=<s:property value="#post.id"></s:property>">
                                <s:property value="#post.title"></s:property>
                            </a>
                        </th>
                        <td><s:property value="#post.user.username"></s:property></td>
                        <td><s:date name="#post.sendTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                        <td><s:date name="#lastfollowpostsendtime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                        <td><s:property value="#post.followposts.size" default="0"></s:property>/<s:property value="#post.viewNum"></s:property></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <nav aria-label="Page navigation example" class="my-sm-3">
                <ul class="pagination justify-content-center">

                    <s:if test='#request.pager.isFirstPage!=true'>
                        <li class="page-item">
                            <a class="page-link" href="subforum.action?sfid=<s:property value="subForum.id"></s:property>&page=1">
                                首页
                            </a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="subforum.action?sfid=<s:property value="subForum.id"></s:property>&page=<s:property value="#request.pager.getCurrentPage-1"></s:property>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </s:if>
                    <s:iterator value='#request.pager.getPageList' var="item">
                        <s:if test="#item==#request.pager.getCurrentPage">
                            <li class="page-item active">
                                <a class="page-link" href=""><s:property value="#item"></s:property></a>
                            </li>
                        </s:if>
                        <s:elseif test="#item=='...'">
                            <li class="page-item">
                                <div class="page-link">
                                    <s:property value="#item"></s:property>
                                </div>
                            </li>
                        </s:elseif>
                        <s:else>
                            <li class="page-item">
                                <a class="page-link" href="subforum.action?sfid=<s:property value="subForum.id"></s:property>&page=<s:property value="#item"></s:property>"><s:property value="#item"></s:property></a>
                            </li>
                        </s:else>
                    </s:iterator>
                    <s:if test='#request.pager.isLastPage!=true'>
                        <li class="page-item">
                            <a class="page-link" href="subforum.action?sfid=<s:property value="subForum.id"></s:property>&page=<s:property value="#request.pager.getCurrentPage+1"></s:property>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </s:if>
                </ul>
            </nav>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<%@include file="foot.jsp"%>
</body>
</html>