<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="mainforum.action">全部</a>
        </li>
    </ol>
    <div class="row">
        <div class="col-sm-12">
            <div class="card mb-sm-4">
                <h5 class="card-header bg-dark text-white">
                    <a href="mainforum.action?mfid=<s:property value="mainForum.id"></s:property>">
                        <s:property value="mainForum.name"></s:property>
                    </a>
                    <small>
                        <s:property value="mainForum.info"></s:property>
                    </small>
                </h5>
                <table class="table table-hover" style="margin-bottom: 0px">
                    <tbody>
                    <s:iterator value="forums" var="subforum">
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
                                <s:property value="#subforum.createTime"></s:property>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<%@include file="foot.jsp"%>
</body>
</html>