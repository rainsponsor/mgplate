<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>机构管理 - 添加机构信息</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
</head>
<body>
<div class="col_12">
	<div class="nav">
		<span>系统管理&gt;&gt;部门管理&gt;&gt;部门信息</span>
	</div>
    <form:form name="inputForm" id="inputForm" method="post" modelAttribute="org">
    	<form:hidden path="id"/>
		<fieldset class="fieldset_box">
			<legend><i class="legend_tag fa-pencil fa"></i>部门信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
			    <tr>
			        <th>部门名称:</th>
			        <td>${org.org_name }</td>
			        <th>上级部门:</th>
			        <td>${org.parent_name }</td>
			        <th>联系电话:</th>
			        <td>${org.contact } </td>
			    </tr>
			    <tr>
			        <th>管理部门:</th>
			        <td>${org.is_admin_org==0?"否":"是" } </td>
			        <th>部门编号:</th>
			        <td>${org.org_code}</td>
			        <th>部门领导:</th>
			        <td>${org.manager}</td>
			    </tr>
			    <tr>
			        <th>排序号:</th>
			        <td>${org.sort_no}</td>
			        <th>部门地址:</th>
			        <td>${org.org_address}</td>
			    </tr>
			</table>
			<center class="mt10">
				<a href="javascript:history.back();" type="button" class="btn btn-primary row_tj">返回</a>
			</center>
	    </fieldset>
    </form:form>
</div>
</body>
</html>
