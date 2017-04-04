<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>自动登录</title>
</head>
<body>
<form action="${_CONTEXT_PATH}/j_spring_security_check" method="POST" name="loginForm">
 <input type='hidden' name='j_username' value="${logUserInfo.logonInfo.logonId}" />
 <input type='hidden' name='j_password' value="${logUserInfo.logonInfo.password}" >
 <input type='hidden' name='j_proxy_code' />
</form>
<script type="text/javascript">
document.forms[0].submit();
</script>
</body>
</html>