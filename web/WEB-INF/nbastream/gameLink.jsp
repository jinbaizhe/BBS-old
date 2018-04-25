<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/web/head.jsp"%>
    <title><strong><s:property value="game.away"></s:property></strong>&nbsp;VS&nbsp;<strong><s:property value="game.home"></s:property></strong></title>
</head>
<body>
<jsp:include page="/WEB-INF/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-10" style="margin-left: auto;margin-right: auto">
            <h2 class="text-center"><strong><s:property value="game.away"></s:property></strong>&nbsp;VS&nbsp;<strong><s:property value="game.home"></s:property></strong></h2>
            <s:if test="gameLinks.size==0">
                <h2 style="text-align: center">暂无今日直播信息</h2>
                <h3 style="text-align: center">比赛前一小时左右更新直播链接</h3>
            </s:if>
            <s:iterator value="gameLinks" var="gameLink">
                <div class="card my-sm-5">
                    <div class="card-body">
                        <h5 class="card-title">链接：</h5>
                        <p class="card-text">
                            <s:property value="#gameLink.info" escapeHtml="false"></s:property>
                        </p>
                    </div>
                </div>
            </s:iterator>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/web/foot.jsp"%>
</body>
</html>