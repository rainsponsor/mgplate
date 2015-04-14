<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>目录管理 - 添加子目录</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#inputForm").validationEngine();
});

function save(){
	if($("#inputForm").validationEngine('validate')){
		var url = "${ctx}/sys/catalog/save";
		$.ajax({
			url:url,
			type:'post',
			async: false,
			dataType:'json',
			data:{
				name:$("#name").val(),
				parent_id:$("#parent_id").val(),
				sub_id:$("#sub_id").val(),
				leaf:$("#leaf").val(),
				file_path:$("#file_path").val(),
				serialno:$("#serialno").val()
			},
			success:function(result){
				window.location = "${ctx}/sys/catalog/content?id="+result;
				var iframe = window.parent.document.getElementById("fr_tree_content");
				iframe.contentWindow.location.reload(true);
			}
		});
	}
}
</script>
</head>
<body>
<div class="col_12">
	<div class="nav">
		<span>目录管理&gt;&gt;添加子目录&gt;&gt;${catalog.name }</span>
	</div>
    <form:form name="inputForm" id="inputForm" method="post" modelAttribute="catalog">
    	<input type="hidden" name="parent_id" id="parent_id" value="${catalog.id }">
		<fieldset class="fieldset_box">
			<legend><i class="legend_tag fa-pencil fa"></i>目录基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
			    <tr>
			        <th>目录名称:</th>
			        <td>
			        	<input type="text" id="name" name="name" class="length_2 validate[required]"/>
			        </td>
			        <th>所属科目:</th>
			        <td>
			       		<ptags:select option="true" parentDicId="10" value="${catalog.sub_id}" id="sub_id" cssClass="form-control length_2 validate[required]"/>
			        </td>
			        <th>叶子目录:</th>
			        <td>
			       		<ptags:select parentDicId="1" value="${catalog.leaf}" id="leaf" cssClass="form-control length_2 validate[required]"/>
			        </td>
			    </tr>
			    <tr>
			        <th>排序编号:</th>
			        <td>
			       		 <input type="text" id="serialno" name="serialno" class="length_2"/>
			        </td>
			        <th>文件目录:</th>
			        <td>
			       		 <input type="text" id="file_path" name="file_path" class="length_2"/>
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
