<%@ page language="java" pageEncoding="utf-8"%>
<%@	taglib prefix="s" uri="/struts-tags"%>
<html>
	<head><title>成功页面</title></head>
	<body>
		<p>
			<s:property value="user.getUsername()"/>，您好！欢迎光临叮当书店。
		</p>
		<a href="logout.action">注销</a>
	</body>
</html>
