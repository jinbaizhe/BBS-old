<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>全部版块</title>
</head>
<body>
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
        <s:iterator value="mainForumMap" var="item">
            <div class="card mb-sm-4">
                <h5 class="card-header bg-dark text-white">
                    <s:property value="#item.key.name"></s:property>
                    <small><s:property value="#item.key.info"></s:property></small>
                    <a class="float-right" href="mainforum.action?mfid=<s:property value="#item.key.id"></s:property>">
                        更多
                    </a>
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
                            <s:iterator value="#item.value" var="subforum" status="st">
                                <s:if test="#st.index<5">
                                    <tr>
                                        <td>
                                            <a href="subforum.action?sfid=<s:property value="#subforum.id"></s:property>">
                                                <s:property value="#subforum.name"></s:property>
                                            </a>
                                        </td>
                                        <td><s:property value="#subforum.info"></s:property></td>
                                        <td>帖数：<s:property value="#subforum.posts.size"></s:property></td>
                                    </tr>
                                </s:if>
                            </s:iterator>
                        </s:else>
                    </tbody>
                </table>
            </div>
        </s:iterator>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
<%@include file="foot.jsp"%>
</body>
</html>