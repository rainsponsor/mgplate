<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
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
<link href="${ctx}/resources/font/font-awesome-4.2.0/css/font-awesome.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/css/main.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${ctx}/resources/zTree/js/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript">
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		var zNodes = [];
		var menutreeUrl = '${ctx}/sys/coretree/catalogbyid?id=${catalog.id}';
		$.ajax({
			url:menutreeUrl,
			type:'post',
			async: false,
			dataType:'json',
			data:{
				
			},
			success:function(result){
				zNodes = result;
			}
		});
		
		$(document).ready(function(){
			$.fn.zTree.init($("#dict"), setting, zNodes);
		});
</script>
</head>
<body>
<div class="tree_content_box">
    <div class="tree_nav">
        <span>${catalog.name}</span>
    </div>
	<div class="content_wrap">
		<div>
			<ul id="dict" class="ztree"></ul>
		</div>
	</div>
</div>
</body>
</html>
