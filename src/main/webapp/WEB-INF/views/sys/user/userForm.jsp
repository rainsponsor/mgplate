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
	 var regBox = {
		        regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
		        regTel : /^0[\d]{2,3}-[\d]{7,8}$/
		    };
	var mobile = $("#phone").val();
	var loginName = $("#login_name").val();
	if((mobile==null || mobile=="" || mobile == undefined) || (regBox.regMobile.test(mobile) ||regBox.regTel.test(mobile))){
		var url="${ctx}/sys/user/isRepeat";
		$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			async:false,
			data:{
				loginName:loginName
			},
			success:function(result){
				if(result){
					$("#lgname").html("*登陆名称已存在！");
					$("#login_name").val('');
					$("#login_name").focus();
					return;
				}else{
					$("#appForm").attr('action', "${ctx}/sys/user/save");
					$("#appForm").submit();
				}
			}
		});
	}else{
		tableList.app.popAlert("错误提示","电话号码填写不正确！");
		return;
	}
}
function isRepeat(){
	var loginName = $("#login_name").val();
	var url="${ctx}/sys/user/isRepeat";
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		async:false,
		data:{
			loginName:loginName
		},
		success:function(result){
			if(result){
				$("#lgname").html("*登陆名称已存在！");
				return;
			}else{
				$("#lgname").html("");
			}
		}
	});
}
</script>
</head>

<body>
	<div class="col_12">
		<div class="nav">
			<span>系统管理&gt;用户添加</span>
		</div>
		<div class="table_header">
			<span class="fl table_top"></span>
		</div>
		<form:form name="appForm" id="appForm" method="post"
			modelAttribute="user">
			<form:hidden path="id" />
			<fieldset class="fieldset_box">
				<legend>
					<i class="legend_tag fa-pencil fa"></i>用户信息
				</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="table_form">
					<tr>
						<th>用户名称:</th>
						<td><input type="text" class="length_2 validate[required]" id="user_name"
							name="user_name"></td>

						<th>登录名称:</th>
						<td><input type="text" class="length_2 validate[required]" id="login_name"
							name="login_name" onchange="isRepeat();"><span id="lgname" class="hint"></span></td>

						<th>电子邮件:</th>
						<td><input type="text" class="length_2 validate[custom[email]]"  id="email"
							name="email">
						</td>
					</tr>

					<tr>
						<th>用户电话:</th>
						<td><input type="text" id="phone" name="phone" class="length_2">
						</td>
						<th>所属部门:</th>
						<td><ptags:orgtree id="parent_id" name="wp_org_id"
								value="${user.wp_org_id }" labelId="parent_name"
								labelName="org_name " labelValue="${user.org_name }"/>
						</td>

						<th>直接主管:</th>
						<td><ptags:usertree checkType="radio"
								checkFieldId="parent_id" checkFieldProm="请先选择人员所属部门！"
								id="manager" name="major" value="${user.major }"
								labelId="manager_name" labelName="major_name"
								labelValue="${user.major_name }" />
						</td>
					</tr>
					<tr>
						<th>是否为主管:</th>
						<td><select class="form-control length_2" id="is_admin_user" name="is_major">
								<option value="0">否</option>
								<option value="1">是</option>
						</select>
						</td>
					</tr>
				</table>
				<center class="mt10">
					<a href="javascript:save();" class="btn btn-primary btn-table">提交</a>
					<a href="javascript:history.back();" type="button"
						class="btn btn-primary row_tj">返回</a>
				</center>
			</fieldset>
		</form:form>
	</div>

</body>
</html>
