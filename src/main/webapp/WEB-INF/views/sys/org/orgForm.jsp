<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>机构管理 - 机构信息操作页面(添加/修改)</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#inputForm").validationEngine();
});

function save(){
	$("#inputForm").attr("action","${ctx}/sys/org/save");
	$("#inputForm").submit();
}
</script>
</head>
<body>
<div class="col_12">
	<div class="nav">
		<span>系统管理&gt;&gt;部门管理&gt;&gt;新增部门</span>
	</div>
    <form:form name="inputForm" id="inputForm" method="post" modelAttribute="org">
    	<form:hidden path="id"/>
		<fieldset class="fieldset_box">
			<legend><i class="legend_tag fa-pencil fa"></i>部门信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
			    <tr>
			        <th>部门名称:</th>
			        <td>
			        	<form:input path="org_name" cssClass="length_2 validate[required]"/>
			        </td>
			        <th>上级部门:</th>
			        <td>
			        	<ptags:orgtree cssClass="validate[required]" id="parent_id" name="parent_id" value="${org.parent_id }" labelId="parent_name" labelName="parent_name" labelValue="${org.parent_name }"/>
			        </td>
			        <th>联系电话:</th>
			        <td>
			        	<form:input path="contact" cssClass="length_2 validate[required,custom[phone]]"/>
			        </td>
			    </tr>
			    <tr>
			        <th>管理部门:</th>
			        <td>
			            <select class="form-control length_2 validate[required]" id="is_admin_org">
			                    <option value="0">否</option>
			                    <option value="1">是</option>
			            </select>
			        </td>
			        <th>部门编号:</th>
			        <td>
			        	<form:input path="org_code" class="length_2"/>
			        </td>
			        <th>部门领导:</th>
			        <td>
			        	<ptags:usertree checkType="radio" id="manager" name="manager" value="${org.manager }" labelId="manager_name" labelName="manager_name" labelValue="${org.manager_name }"/>
			        </td>
			    </tr>
			    <tr>
			        <th>排序号:</th>
			        <td>
			        	<form:input path="sort_no" class="length_2"/>
			        </td>
			        <th>部门地址:</th>
			        <td>
			        	<form:input path="org_address" class="length_3"/>
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
