<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.soule.GetUserInfo" %>
<%@ page import="com.soule.GetUserInfoImpl" %>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>自动登录</title>
</head>
<body>
<% 
	GetUserInfo info = new GetUserInfoImpl();
	String user = info.getLogonId();
	String pwd = info.getUserPassword();
%>

<form action="${_CONTEXT_PATH}/j_acegi_security_check" method="POST" name="loginForm">
 <input type='hidden' name='j_username' value="<%=user%>" />
 <input type='hidden' name='j_password' value="<%=pwd%>" >
</form>
<script type="text/javascript">
document.loginForm.submit();
</script>
</body>
</html>