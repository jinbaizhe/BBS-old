<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2018/3/23
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../head.jsp"%>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="my-sm-5">
                <h1 class="text-center">登录成功</h1>
            </div>
            <div class="my-sm-5">
                <h5 class="text-center">
                    <a href="${sessionScope.get("referURL")}">返回登录前页面</a>
                </h5>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
<%@include file="../foot.jsp"%>
</body>
</html>
