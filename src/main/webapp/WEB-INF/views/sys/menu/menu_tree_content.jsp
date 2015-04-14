<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统主页面-菜单信息管理</title>
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
	<div id="main_wrap">
		<div id="main_nav">
	    	<iframe name="fr_tree_content" id="fr_tree_content" frameborder="0" scrolling="yes" src="${ctx}/sys/menutree/tree" allowtrancparency="true"></iframe>
	    	<div class="width_line" onmousedown="moveWidth($('#main_nav'));"></div>
	    </div>
	    <div id="main_content">
	    	<iframe class="tree_info_content" name="tree_info_content" id="tree_info_content" frameborder="0" scrolling="yes" src="${ctx}/sys/menutree/content?id=${id}"></iframe>   
	    </div>
	</div>
</body>
</html>
