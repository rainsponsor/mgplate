<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>顶部页面</title>
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
<link href="<%=path %>/resources/font/font-awesome-4.2.0/css/font-awesome.css" type="text/css" rel="stylesheet" />
<link href="<%=path %>/resources/css/main.css" type="text/css" rel="stylesheet" />
<script src="<%=path %>/resources/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/resources/js/top.js"></script>

<script type="text/javascript">
function home(){
	window.parent.open('<%=path%>/index','_self');
}
function help(){
	window.parent.open('<%=path%>/resources/help/math.jsp','_block');
}
</script>
</head>
<body class="login_bg">
<form id="loginFrom" method="post">
	<div id="header">
		<div class="header_top">
	    	<div class="header_lbox">
	            <div id="logo"><a href="#"><img src="<%=path %>/resources/images/logo.png" /><span class="logo_font">五大连池市行政审批与公共服务平台</span></a></div>
	        </div>
	        <div class="horizontal_nav">
	        	<ul>
	            	<li class="b_purple" title="在线帮助"><a href="javascript:help();"><i class="fa fa-question-circle horizontal_nav_icon"></i><br/><span class="horizontal_nav_num">在线帮助</span></a></li>
	            	<li class="horizontal_user">
	                	<a href="#">
	                    	<!--<i class="fa fa-smile-o horizontal_nav_icon_user"></i>-->
	                        <span style="font-size: 12px;float: left;margin:15px 5px 5px 5px; line-height:18px; color:#fff;">欢迎您<br/><shiro:authenticated><shiro:principal property="name"/></shiro:authenticated></span>
	                    	<i class="horizontal_nav_slide fa fa-caret-down"></i>
	                    </a>
	                </li>
	            </ul>
	        </div>
	    </div>
		<div class="header_nav">
	    	<div class="sidebar">
	        	<a href="javascript:home();" title="首页"><i class="fa fa-home sidebar_left"></i></a>
	        	<a class="js_menu" title="导航"><i class="fa fa-navicon sidebar_right"></i></a>
	        </div>
	        <div class="header_info">2014年12月9日 星期二</div>
	    </div>
	</div>
</form>
</body>
</html>
