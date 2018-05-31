<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title><s:property value="mainForum.name"></s:property></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="mainforum.action">全部</a>
        </li>
        <li class="breadcrumb-item active">
            <s:iterator value="mainForumMap" var="item">
                <s:property value="#item.key.name"></s:property>
            </s:iterator>
        </li>
    </ol>
    <div class="row">
        <div class="col-sm-12">
            <s:iterator value="mainForumMap" var="item">
                <div class="card mb-sm-4">
                    <h5 class="card-header bg-dark text-white">
                        <s:property value="#item.key.name"></s:property>
                        <small>
                            <s:property value="#item.key.info"></s:property>
                        </small>
                    </h5>
                    <table class="table table-hover" style="margin-bottom: 0px">
                        <tbody>
                            <s:if test="#item.value.size==0">
                                <tr>
                                    <td>
                                        <div class="text-center">
                                            <h5>暂无子版块</h5>
                                        </div>
                                    </td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="#item.value" var="subforum">
                                    <tr>
                                        <td>
                                            <a href="subforum.action?sfid=<s:property value="#subforum.id"></s:property>">
                                                <s:property value="#subforum.name"></s:property>
                                            </a>
                                        </td>
                                        <td>
                                            <s:property value="#subforum.info"></s:property>
                                        </td>
                                        <td>
                                            帖数：<s:property value="#subforum.posts.size"></s:property>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                        </tbody>
                    </table>
                </div>
            </s:iterator>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<%@include file="foot.jsp"%>
</body>
</html>