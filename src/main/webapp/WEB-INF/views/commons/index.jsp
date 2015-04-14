<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统登录页面</title>
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=path %>/resources/images/favicon.ico;" media="screen" />
<link href="<%=path %>/resources/font/font-awesome-4.2.0/css/font-awesome.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/resources/css/table.css" type="text/css" rel="stylesheet" />
<script src="<%=path %>/resources/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/resources/js/table_list.js"></script>
<script type="text/javascript">
function login(){
	//TODO 验证
	
	$("#loginFrom").attr('action','<%=path%>/login');
	$("#loginFrom").submit();
}

function KeyDown()
{
    if (event.keyCode == 13)
    {
        event.returnValue=false;
        event.cancel = true;
        login();
    }
}
function setPass(passinput){
	passinput.style.color='#225289';
	if(passinput.value==''){
		passinput.value='请输入密码';
		passinput.type='text';
	}else{
		passinput.type='password';
	}
}
</script>
<script type="text/javascript">
if (window != top) 
top.location.href = location.href; 
</script>
</head>
<body class="login_bg">
	<form id="loginFrom" method="post">
		<div class="login_top"><img src="<%=path %>/resources/images/login_logo.png" /></div>
	    <div class="login_main js_login_center">
	    	<div class="login_main_box">
	            <img class="fl" src="<%=path %>/resources/images/login_bg_logo.png" />
	            <div id="login" class="login_box fl">
	                <div class="login_h2">
						行政审批与公共服务平台
	                </div>
	                <c:if test="${not empty param.kickout}">
                		<div style="color:red;">
	                		您被踢出登录。
	               		</div>
	                </c:if>
	              	<span style="color:red;">${ERROR_LOGIN }</span>
	                <input type="text" name="username" class="login_input" onkeydown="KeyDown()" onblur=" this.style.color='#225289'; if(this.value=='') this.value='请输入用户名';" onfocus="this.style.color='#225289';if(this.value=='请输入用户名') this.value='';" style="color: #225289;" value="请输入用户名"/>
	                <input type="text" name="password" onkeydown="KeyDown()" class="login_input js_password" onblur="setPass(this);" onfocus="this.style.color='#225289';if(this.value=='请输入密码') this.value='';this.type='password'" style="color: #225289;" value="请输入密码"/>
	                <a href="javascript:login();" class="login_submit">登陆系统</a>
	            </div>
	        </div>
	    </div>
	</form>
</body>
</html>
