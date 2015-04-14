<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link href="<%=path %>/resources/css/table.css" type="text/css" rel="stylesheet" />
<script src="<%=path %>/resources/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/resources/js/table_list.js"></script>

<script type="text/javascript">
function login(){
	//TODO 验证
	
	$("#loginFrom").attr('action','<%=path%>/login');
	$("#loginFrom").submit();
}
</script>
</head>
<body>
<form id="loginFrom" method="post">
<div class="col_12">
	<div class="color_box col_5 col_right js_box_height_30">
    	<div class="color_box_top"><i class="color_box_icon fa-building fa"></i>办公提醒</div>
    </div>
	<div class="color_box col_5 col_left js_box_height_30">
    	<div class="color_box_top"><i class="color_box_icon fa-newspaper-o fa"></i>个人业务</div>
    </div>
	<div class="color_box col_10 js_box_height_70">
    	<div class="color_box_top"><i class="color_box_icon fa-envelope fa"></i>待办件</div>
    </div>
</div>
</form>
</body>
</html>
