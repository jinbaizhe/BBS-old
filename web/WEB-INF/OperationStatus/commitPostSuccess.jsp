<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/3/23
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <%@include file="/WEB-INF/web/head.jsp"%>
    <title>发表帖子成功</title>
</head>
<body>
<jsp:include page="/WEB-INF/web/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="my-sm-5">
                <h1 class="text-center">发表帖子成功</h1>
            </div>
            <div class="my-sm-5">
                <h5 class="text-center">
                    <a href="/subforum.action?sfid=<s:property value="subforumid"></s:property>">返回当前版块</a>
                </h5>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/web/footer.jsp"></jsp:include>
<%@include file="/WEB-INF/web/foot.jsp"%>
</body>
</html>
