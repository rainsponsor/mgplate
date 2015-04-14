<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单管理 - 添加子菜单</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#inputForm").validationEngine();
});

function save(){
	var url = "${ctx}/sys/menutree/save";
	$.ajax({
		url:url,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			parent_id:$("#parent_id").val(),
			menu_name:$("#menu_name").val(),
			is_leaf:$("#is_leaf").val(),
			menu_url:$("#menu_url").val(),
			menu_code:$("#menu_code").val(),
			menu_no:$("#menu_no").val()
		},
		success:function(result){
			window.location = "${ctx}/sys/menutree/content?id="+result;
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
		<span>菜单管理&gt;&gt;添加子菜单&gt;&gt;${menu.menu_name }</span>
	</div>
    <form:form name="inputForm" id="inputForm" method="post" modelAttribute="menu">
    	<input type="hidden" id="parent_id" name="parent_id" value="${menu.id }">
		<fieldset class="fieldset_box">
			<legend><i class="legend_tag fa-pencil fa"></i>菜单基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
			    <tr>
			        <th><font color="red">*</font>菜单名称:</th>
			        <td>
			        	<input type="text" id="menu_name" name="menu_name" class="length_2 validate[required]">
			        </td>
			        <th><font color="red">*</font>叶子菜单:</th>
			        <td>
			            <select class="form-control length_2 validate[required]" id="is_leaf" name="is_leaf">
			                    <option value="1">是</option>
			                    <option value="0">否</option>
			            </select>
			        </td>
			        <th>请求地址:</th>
			        <td>
			        	<input type="text" id="menu_url" name="menu_url" class="length_2">
			        </td>
			    </tr>
			    <tr>
			        <th>菜单编号:</th>
			        <td>
			        	<input type="text" id="menu_code" name="menu_code" class="length_2">
			        </td>
			        <th>序号:</th>
			        <td>
			        	<input type="text" id="menu_no" name="menu_no" class="length_2">
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
