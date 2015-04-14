<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
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
<script src="<%=path %>/resources/js/main.js"></script>

<link href="${ctx}/resources/jquery/menutree/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/jquery/menutree/js/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript">
var curMenu = null, zTree_Menu = null;
		var setting = {
			view: {
				showLine: true,
				selectedMulti: false,
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onNodeCreated: this.onNodeCreated,
				beforeClick: this.beforeClick,
			}
		};
		
		var zNodes = [];
		
		var menutreeUrl = '${ctx}/getmenutree';
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
		
		function beforeClick(treeId, node) {
			if (node.isParent) {
				if (node.level === 0) {
					var pNode = curMenu;
					while (pNode && pNode.level !==0) {
						pNode = pNode.getParentNode();
					}
					if (pNode !== node) {
						var a = $("#" + pNode.tId + "_a");
						a.removeClass("cur");
						zTree_Menu.expandNode(pNode, false);
					}
					a = $("#" + node.tId + "_a");
					a.addClass("cur");

					var isOpen = false;
					for (var i=0,l=node.children.length; i<l; i++) {
						if(node.children[i].open) {
							isOpen = true;
							break;
						}
					}
					if (isOpen) {
						zTree_Menu.expandNode(node, true);
						curMenu = node;
					} else {
						zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
						curMenu = node.children[0];
					}
				} else {
					zTree_Menu.expandNode(node);
				}
			}
			return !node.isParent;
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#menutree"), setting, zNodes);
			zTree_Menu = $.fn.zTree.getZTreeObj("menutree");
			curMenu = zTree_Menu.getNodes()[0].children[0];
			//zTree_Menu.selectNode(curMenu);
			var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
			a.addClass("cur");
		});
</script>
</head>
<body>
<div id="tree">
	<div class="tree_box">
		<div class="tree_content_box">
			<div class="">
				<div>
					<ul id="menutree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
