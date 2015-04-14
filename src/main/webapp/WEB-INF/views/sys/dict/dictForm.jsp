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
		<span>数据字典管理&gt;&gt;添加数据&gt;&gt;${dict.dict_name }</span>
	</div>
    <form:form name="inputForm" id="inputForm" method="post" modelAttribute="dict">
    	<input type="hidden" name="parent_id" id="parent_id" value="${dict.id }">
		<fieldset class="fieldset_box">
			<legend><i class="legend_tag fa-pencil fa"></i>菜单基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
			    <tr>
			        <th>排序编号:</th>
			        <td>
			        	<input type="text" id="sort" name="sort" value="${dict.sort+1 }" class="length_2">
			        	
			        </td>
			        <th>字典名称:</th>
			        <td>
			            <input type="text" id="dict_name" name="dict_name" class="length_2 validate[required]">
			        </td>
			        <th>VALUE值:</th>
			        <td>
			        	<input type="text" id="dict_value" name="dict_value" class="length_2 validate[required]">
			        </td>
			    </tr>
			    <tr>
			        <th>描述:</th>
			        <td>
			        	<textarea id="dict_depict" name="dict_depict" ></textarea>
			        </td>
			    </tr>
			</table>
			<center class="mt10">
				<a href="javascript:save();" type="button" class="btn btn-primary">提交</a>
				<a href="javascript:history.back();" type="button" class="btn btn-primary row_tj">返回</a>
			</center>
	    </fieldset>
    </form:form>
</div>
</body>
</html>
