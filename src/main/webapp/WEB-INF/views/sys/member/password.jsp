<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人信息</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#appForm").validationEngine();
    var flag = $("#flag").val();
	if(flag == 1){
		tableList.app.popAlert("提示", "修改密码成功！");
		return;
	}
});

function save() {
	if($("#appForm").validationEngine('validate')){
		var oldPwd = $("#login_password").val();
		var newPwd = $("#new_password").val();
		var conPwd = $("#confirm_password").val();
		if(newPwd != conPwd){
			$("#conf").html("两次密码不一致！");
			return;
		}else{
			var url = "${ctx}/sys/member/validatepwd";
			$.ajax({
				url:url,
				type:'post',
				async: false,
				dataType:'json',
				data:{
					password:oldPwd
				},
				success:function(bol){
					if(bol){
						tableList.app.popAlert("提示","输入的密码不正确，请重新输入！");
						return;
					}else{
						$("#appForm").attr('action', "${ctx}/sys/member/updatepwd");
						$("#appForm").submit();
					}
				}
				
			});
		}
	}
}
</script>
</head>

<body>
<input type="hidden" id="flag" value="${flag }"/>
	<div class="col_12">
		<div class="nav">
			<span>个人信息&gt;修改密码</span>
		</div>
		<div class="table_header">
			<span class="fl table_top"></span>
		</div>
		<form:form name="appForm" id="appForm" method="post"
			modelAttribute="role">
			<fieldset class="fieldset_box">
				<legend>
					<i class="legend_tag fa-pencil fa"></i>个人信息
				</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="table_form">
					<tr>
						<th>原密码:</th>
						<td>
							<input type="text" class="length_2 validate[required]" id="login_password" name="login_password">
						</td>
							
						<th>新密码</th>
						<td>
							<input type="password" class="length_2 validate[required]" id="new_password" name="new_password">
						</td>
						<th>确认密码</th>
						<td>
							<input type="password" class="length_2 validate[required]" id="confirm_password"><span id="conf" class="hint"></span>
						</td>
					</tr>
				</table>
				<center class="mt10">
					<a href="javascript:save();" class="btn btn-primary btn-table">保存</a>
				</center>
			</fieldset>
		</form:form>
	</div>

</body>
</html>
