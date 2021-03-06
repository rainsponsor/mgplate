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
</head>
  
<body>
<div class="col_12">
<div class="nav">
	<span>系统管理&gt;用户详细</span>
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
						<td>
							${user.user_name }
						</td>

						<th>登录名称:</th>
						<td>
							${user.login_name }
						</td>
						<th>电子邮件:</th>
						<td>
							${user.email }
						</td>
					</tr>

					<tr>
						<th>用户电话:</th>
						<td>
							${user.phone }
						</td>
							
						<th>所属部门:</th>
						<td>
							${user.org_name }
						</td>

						<th>直接主管:</th>
						<td>
							${user.major_name }
						</td>
					</tr>
					<tr>
						<th>是否为主管:</th>
						<td>
							<c:if test="${user.is_major == 0 }">否</c:if>
							<c:if test="${user.is_major == 1 }">是</c:if>
						</td>
					</tr>
				</table>
				<center class="mt10">
					<a href="javascript:history.back();" type="button"
						class="btn btn-primary row_tj">返回</a>
				</center>
			</fieldset>
		</form:form>
</div>

</body>
</html>
