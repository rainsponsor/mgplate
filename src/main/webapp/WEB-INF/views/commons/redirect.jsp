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
<link href="<%=path %>/resources/css/main.css" type="text/css" rel="stylesheet" />
<script src="<%=path %>/resources/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/resources/js/top.js"></script>

<script type="text/javascript">
function loadUrl(){
	var parent = window.parent;
	if(parent!=null){
		//loginFrom.submit();
		alert(1);
	}else{
		//loginFrom.submit();
		alert(2);
	}
}
</script>
</head>
<body class="login_bg" onload="loadUrl();">
<form id="loginFrom" method="post" action="<%=path%>/index">
</form>
</body>
</html>
