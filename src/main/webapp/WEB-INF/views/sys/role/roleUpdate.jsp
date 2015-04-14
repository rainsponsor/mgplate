<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户管理</title>
    <meta name="renderer" content="webkit" />
    <meta name="decorator" content="default"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#appForm").validationEngine();
});
function update(){
	var roleName = $("#role_name").val();
	var roleId = $("#id").val();
	var url = "${ctx}/sys/role/isexist";
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		async:false,
		data:{
			roleName:roleName,
			roleId:roleId
			},
			success:function(result){
				if(result){
					$("#appForm").attr('action',"${ctx}/sys/role/update");
					$("#appForm").submit();
				}else{
					tableList.app.popAlert("提示", "角色重复，请重新输入！");
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
	<span>系统管理&gt;角色修改</span>
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
						<td>
							<form:input path="role_name" class="length_2 validate[required]"/>
						</td>
							
						<th>是否活动:</th>
						<td>
							<select id="active_flag_id" class="form-control length_2"  name="active_flag">
									<option value="0" <c:if test="${role.active_flag == 0 }">selected="selected"</c:if>>是</option>
									<option value="1" <c:if test="${role.active_flag == 1 }">selected="selected"</c:if>>否</option>
							</select>
						</td>
						
						<th>角色描述:</th>
						<td>
							<form:textarea path="description" class="validate[required]" />
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="fieldset_box">
				<legend>
					<i class="legend_tag fa-pencil fa"></i>角色维护用户
				</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="table_form">
					<tr>
						<th>
							选择系统用户：
						</th>
						<td>
							<ptags:usertree checkType="checkbox" id="manager" name="ids" value="${role.ids }" labelId="manager_name" labelName="manager_name" labelValue="${role.names }"/>
						</td>
					</tr>
				</table>
				<center class="mt10">
					<a href="javascript:update();" class="btn btn-primary btn-table">保存</a>
					<a href="javascript:history.back();" type="button"
						class="btn btn-primary row_tj">返回</a>
				</center>
			</fieldset>
		</form:form>
</div>

</body>
</html>
