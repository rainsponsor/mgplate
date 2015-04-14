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
			modelAttribute="role">
			<form:hidden path="id" />
			<fieldset class="fieldset_box">
				<legend>
					<i class="legend_tag fa-pencil fa"></i>用户信息
				</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="table_form">
					<tr>
						<th>角色名称:</th>
						<td>
							${role.role_name }
						</td>
							
						<th>是否活动:</th>
						<td>
							<c:if test="${role.active_flag == 0 }">是</c:if>
							<c:if test="${role.active_flag == 1 }">否</c:if>
						</td>
						
						<th>角色描述:</th>
						<td>
							${role.description }
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
