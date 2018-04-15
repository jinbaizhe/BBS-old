<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<%@ include file="slideBar.jsp"%>

<div class="content-wrapper">

    <div class="container">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/">后台管理系统</a>
            </li>
            <li class="breadcrumb-item active">留言板管理</li>
        </ol>
        <h3 style="text-align: center">暂无留言</h3>
    </div>
    <%@include file="/WEB-INF/manage/footer.jsp"%>
</div>
<%@include file="/WEB-INF/manage/foot.jsp"%>
</body>
</html>