<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户管理</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#appForm").validationEngine();
});

function save() {
	var roleName = $("#role_name").val();
	var url = "${ctx}/sys/role/isexist";
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		async:false,
		data:{ 
			roleName:roleName
			},
			success:function(result){
				if(result){
					$("#appForm").attr('action', "${ctx}/sys/role/save");
					$("#appForm").submit();
				}else{
					$("#lgname").html("*角色名称已存在！");
					$("#role_name").val('');
					$("#role_name").focus();
					return;
				}
			}
	});
}
function isExist(roleName){
	var url = "${ctx}/sys/role/isexist";
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		async:false,
		data:{ 
			roleName:roleName
			},
			success:function(result){
				if(!result){
					$("#lgname").html("*角色名称已存在！");
					$("#role_name").focus();
					return;
				}
			}
	});
}
</script>
</head>

<body>
	<div class="col_12">
		<div class="nav">
			<span>系统管理&gt;角色添加</span>
		</div>
		<div class="table_header">
			<span class="fl table_top"></span>
		</div>
		<form:form name="appForm" id="appForm" method="post"
			modelAttribute="role">
			<form:hidden path="id" />
			<form:hidden path="menuId" />
			<fieldset class="fieldset_box">
				<legend>
					<i class="legend_tag fa-pencil fa"></i>角色信息
				</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="table_form">
					<tr>
						<th>角色名称:</th>
						<td><input type="text" class="length_2 validate[required]" id="role_name"
							name="role_name" onchange="isExist(this.value);"><span id="lgname" class="hint"></span></td>
							
						<th>是否活动:</th>
						<td><select id="active_flag_id" class="form-control length_2"  name="active_flag">
								<option value="0">是</option>
								<option value="1">否</option>
						</select>
						</td>
						
						<th>角色描述:</th>
						<td><textarea class="validate[required]"  id="description"
							name="description"></textarea>
						</td>
					</tr>
				</table>
				<center class="mt10">
					<a href="javascript:save();" class="btn btn-primary btn-table">保存</a>
					<a href="javascript:history.back();" type="button"
						class="btn btn-primary row_tj">返回</a>
				</center>
			</fieldset>
		</form:form>
	</div>

</body>
</html>
