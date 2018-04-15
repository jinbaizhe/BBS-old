<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title>登录</title>
</head>
<body>
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
        <s:iterator value="forums" var="mainforum">
            <div class="card mb-sm-4">
                <h5 class="card-header bg-dark text-white">
                    <a href="mainforum.action?mfid=<s:property value="#mainforum.id"></s:property>">
                        <s:property value="#mainforum.name"></s:property>
                    </a>
                    <small><s:property value="#mainforum.info"></s:property></small>
                </h5>
                <table class="table table-hover" style="margin-bottom: 0px">
                    <tbody>
                    <s:iterator value="#mainforum.subForums" var="subforum">
                        <tr>
                            <td>
                                <a href="subforum.action?sfid=<s:property value="#subforum.id"></s:property>">
                                    <s:property value="#subforum.name"></s:property>
                                </a>
                            </td>
                            <td><s:property value="#subforum.info"></s:property></td>
                            <td><s:date name="#subforum.createTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                        </tr>
                        </s:iterator>
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