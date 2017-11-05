<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>登陆页面</title>
<style type="text/css">
body {
    padding:0;
     margin:0;
    overflow: hidden;
    background: url('images/index/left.png') left top repeat-y #6BAEEF;
}
.main {
    margin:0 0;
}

.content{
    padding: 100px 0 0 342px;
    background: url('images/index/content.png') left top no-repeat ;
    width:614;
    height:339;
}
.firstcol {
    width: 200px;
}
.logininfo {
    line-height: 30px;
}
.logininfo input {
    width: 150px;
}
.logininfo td:first-child {width:70px;text-align: left;}
#Button_submit {
    border:0;
    width:101px;
    height:32px;
    background: url('images/index/ok.png') left top no-repeat;
}
#Button_reset {
    border:0;
    width:101px;
    height:32px;
    background: url('images/index/reset.png') left top no-repeat;
}
</style>
<script type="text/javascript">
function initLoad() {
	var p = this;
	var pp = p.parent;
	if (pp != p) {
		window.top.location.href = "index.jsp";
	}else {
		checkParams();
	}
}
function resetValue(){
	document.getElementById('j_username').value = '';
	document.getElementById('j_password').value = '';
}


function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

/* function getUrlParams()
{
    var args = {};
    var query = location.search.substring(1);
    alert(query);
    var pairs = query.split("&");//在逗号处断开   
    for (var i = 0; i < pairs.length; i++)
    {
        var pos = pairs[i].indexOf('=');
        if (pos == -1)   continue;
        var argname = pairs[i].substring(0, pos);
        var value = pairs[i].substring(pos + 1);
        args[argname] = unescape(value);
    }
    //console.log(JSON.stringify(args));
    return args;
} */


 function keydown(){
	if(event.keyCode == 13){
		loginForm.submit();
	}
} 
function checkParams()
{	
    //var args = getUrlParams();
    //var val = args["l"];
    //alert(location.href);
    var val = GetQueryString("l");
    //alert(val);
    var msgbox = document.getElementById('error_msg');
    if (val == "f")
    	msgbox.innerHTML = "用户登陆名不存在或者密码错误";
    else if (val == "l")
    	msgbox.innerHTML = "用户已被锁定，无法登录！请与系统管理员联系。" ;
    else if (val == "i")
    	msgbox.innerHTML = "无效的用户密码！" ;
    else if (val == "c")
    	msgbox.innerHTML = "当前浏览器会话已登陆！" ;
    else if (val == "t")
    	msgbox.innerHTML = "用户已被锁定，因连续多次的错误登录！请与系统管理员联系。" ;
    else if (val == "p")
    	msgbox.innerHTML = "无有效的代理关系！" ;
    else if (val == "b")
    	msgbox.innerHTML = "正在进行批处理，请稍后登录！" ;
}
</script>
</head>
<body onload="initLoad()" onkeydown="keydown()">
<form action="j_spring_security_check" method="POST" name="loginForm" defaultfocus="j_username" >
<div class='main'>
    <div>
        <table border="0" align="center" cellpadding="0" cellspacing="0" id="dcontent" >
            <tr>
                <td rowspan="3" width="50%" style="height:100%;background: url('images/index/left.png') right center scroll;"></td>
                <td rowspan="3" >
                    <img src="images/index/ileft.png" alt="" ></td>
                <td align="bottom">
                    <img src="images/index/top.png" alt="" ></td>
                <td rowspan="3">
                    <img src="images/index/iright.png" alt="" ></td>
                <td rowspan="3" width="50%" style="background: url('images/index/right.png') right top scroll ;"></td>
            </tr>
            <tr>
                <td class="content">
                    <table class="logininfo">
                        <tr>
                            <td nowrap="nowrap"><font size="2">用&nbsp;户：</font></td>
                            <td width="100%"><input type='text' id='j_username' name='j_username' value=""
                                maxLength="64" /></td>
                        </tr>
                        <tr>
                            <td nowrap="nowrap"><font size="2">密&nbsp;码：</font></td>
                            <td width="100%"><input type='password' id='j_password' name='j_password' value=""
                                maxLength="6"></td>
                        </tr>
                       <tr>
                            <td colspan="2"><font size="2" color="red" ><span id="error_msg"></span></font></td>
                       </tr>
                        <!--  
                        <tr>
                            <td nowrap="nowrap">被代理人：</td>
                            <td width="100%"><input type='text' name='j_proxy_code'
                                maxLength="64" style="width: 130px; height: 20px;"></td>
                        </tr>
                        -->
                        <tr>
                            <td nowrap="nowrap" colspan='2' style="text-align:left;padding-top:5px;">
                                <input id="Button_submit" type='button' onclick="loginForm.submit();"/>
                                 &nbsp;
                                <input id="Button_reset" type='button' onclick="resetValue();"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <img src="images/index/ibottom.png"   alt=""></td>
            </tr>
        </table>
    </div>
</div>

</form>
</body>
</html>