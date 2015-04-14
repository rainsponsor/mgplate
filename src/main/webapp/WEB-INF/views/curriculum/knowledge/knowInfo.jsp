<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>数据字典 - 添加数据</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#inputForm").validationEngine();
});

function save(){
	var url = "${ctx}/sys/dict/save";
	$.ajax({
		url:url,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			sort:$("#sort").val(),
			parent_id:$("#parent_id").val(),
			dict_name:$("#dict_name").val(),
			dict_value:$("#dict_value").val(),
			dict_depict:$("#dict_depict").val(),
		},
		success:function(result){
			window.location = "${ctx}/sys/dict/content?id="+result;
			var iframe = window.parent.document.getElementById("fr_tree_content");
			iframe.contentWindow.location.reload(true);
		}
	});
}
</script>
</head>
<body>
<div class="col_12">
	<div class="nav">
		<span>邮箱管理&gt;&gt;${knowledge.catalog.name }信息</span>
	</div>
    <form:form name="inputForm" id="inputForm" method="post" modelAttribute="knowledge">
    	<input type="hidden" name="dir_id" id="dir_id" value="${knowledge.catalog.id }">
    	<input type="hidden" name="dir_ids" id="dir_ids" value="${knowledge.catalog.dir_ids }">
    	<input type="hidden" name="sub_id" id="sub_id" value="${knowledge.catalog.sub_id }">
	    <div class="table_header">
	        <div class="table_btn_box_left">
	            <a href="${ctx}/curr/know/form?dirid=${knowledge.catalog.id }" class="btn btn-primary fl">编辑</a>
	        </div>
	        <div class="clear"></div>
	    </div>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
		    <tr>
		        <td align="right">标题:</td>
		        <td>${knowledge.catalog.name }</td>
		        <td align="right">目录:</td>
		        <td>${knowledge.catalog.name }</td>
		        <td align="right">序号:</td>
		        <td>${knowledge.serialno }</td>
		    </tr>
		    <tr>
		        <th align="right" colspan="6">内容:</th>
		    </tr>
		    <tr>
		        <td colspan="6">
		        	<c:if test="${knowledge.content == null }">
		        		暂无内容
		        	</c:if>
		        	<c:if test="${knowledge.content != null }">
		        		${knowledge.content }
		        	</c:if>
		        </td>
		    </tr>
		</table>
    </form:form>
</div>
</body>
</html>
