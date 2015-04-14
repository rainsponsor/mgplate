<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统主页面</title>
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/resources/images/favicon.ico;" media="screen" />
<link href="${ctx}/resources/font/font-awesome-4.2.0/css/font-awesome.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/css/frame.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${ctx}/resources/js/frame.js"></script>
<script type="text/javascript">
function logout(){
	location.href="${ctx}/logout";
}
</script>
</head>
<body>
	<div id="header_wrap">
		<iframe name="fr1" id="fr1" width="100%" height="111" allowtransparency="true" frameborder="0"  scrolling="no" src="${ctx}/top"></iframe>
	    <div class="horizontal_user_box js_logout" style="display:none;">    
	        <ul>
	            <li><i class="ace-icon fa fa-cog"></i><span class="horizontal_user_box_font">系统设置</span></li>
	            <li><i class="ace-icon fa fa-user"></i><span class="horizontal_user_box_font">个人信息</span></li>
	            <li class="logout" onclick="logout();"><i class="ace-icon fa fa-power-off"></i><span class="horizontal_user_box_font">退出</span></li>
	        </ul>
	    </div>
	    <div class="menu">快捷方式</div>
	</div>
	<div id="main_wrap">
		<div id="main_nav">
	    	<iframe name="fr2" id="fr2" frameborder="0" scrolling="no" src="${ctx}/tree" allowtrancparency="true"></iframe>
	    </div>
	    <div class="hidefr2 hidden_btn"></div>
	   <div id="main_content">
	    	<iframe name="fr3" id="fr3" frameborder="0" id="siderbar-box" scrolling="auto" src="${ctx}/content"></iframe>   
	   </div> 
	</div>
</body>
</html>
