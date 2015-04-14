<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>数据字典管理</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
function delMenu(id){
	tableList.app.popConfirm("提示：","您确定要删除此条数据吗？","confirm_win");
	$('.confirm_win').click(function(){
		var url = "${ctx}/sys/dict/delete?id="+id;
		$.ajax({
			url:url,
			type:'post',
			async: false,
			dataType:'json',
			success:function(result){
				if(result==-1){//不能删除
					tableList.app.popAlert('提示:',"此菜单包含子菜单，如果要删除请先删除其子菜单！");
					return;
				}else{
					window.location = "${ctx}/sys/dict/content?id="+result;
					var iframe = window.parent.document.getElementById("fr_tree_content");
					iframe.contentWindow.location.reload(true);
				}
				app.close('confirm_win');
			}
		});
	});
}

function update(){
	var url = "${ctx}/sys/dict/update";
	$.ajax({
		url:url,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			id:$("#id").val(),
			sort:$("#sort").val(),
			parent_id:$("#parent_id").val(),
			dict_name:$("#dict_name").val(),
			dict_value:$("#dict_value").val(),
			dict_depict:$("#dict_depict").val(),
		},
		success:function(result){
			window.location = "${ctx}/sys/dict/content?id="+result;
			var iframe = window.parent.document.getElementById("fr_tree_content");
			iframe.contentWindow.location.reload(true);
		}
	});
}
</script>
</head>
<body>
<div class="col_12">
	<form:form modelAttribute="dict" method="post">
		<form:input path="id" type="hidden"/>
		<form:input path="parent_id" type="hidden"/>
				<fieldset class="fieldset_box">
					<legend><i class="legend_tag fa-pencil fa"></i>数据字典基本信息</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
					    <tr>
					        <th>排序编号:</th>
					        <td>
					       		 <form:input path="sort" cssClass="length_2"/>
					        </td>
					        <th>字典名称:</th>
					        <td>
					        	<form:input path="dict_name" cssClass="length_2 validate[required]"/>
					        </td>
					        <th>VALUE值:</th>
					        <td>
					       		 <form:input path="dict_value" cssClass="length_2 validate[required]"/>
					        </td>
					    </tr>
					    <tr>
					     <th>父ID:</th>
					        <td>
					       		 <form:input readonly="readonly" path="parent_id" cssClass="length_2"/>
					        </td>
					        <th>描述:</th>
					        <td>
					       		 <form:textarea path="dict_depict" cssClass="length_2"/>
					        </td>
					        
					        <td align="right">
					        	<shiro:hasPermission name="${menu.menuId }:update">
									<a href="javascript:update();" type="button" class="btn btn-primary">保存</a>
								</shiro:hasPermission>
					        </td>
					    </tr>
					</table>
			    </fieldset>
				<div class="table_header">
					<span class="fl table_top">子字典信息</span>
				    <div class="table_btn_box">
				    	<shiro:hasPermission name="${menu.menuId }:create">
				       	 <a href="${ctx }/sys/dict/form?id=${dict.id}" class="btn btn-primary fl">增加子字典</a>
				        </shiro:hasPermission>
				    </div>
				    <div class="clear"></div>
				</div>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
				    <tr>
				        <th width="7%">编号</th>
				        <th width="22%">字典名称</th>
				        <th width="12%">VALUE值</th>
				        <th width="12%">父ID</th>
				        <th width="40%">描述</th>
				        <th width="7%">操作</th>
				    </tr>
				    <c:forEach items="${dict.children }" varStatus="index" var="dict">
					    <tr>
					        <td align="center">${dict.sort }</td>
					        <td align="center">${dict.dict_name }</td>
					        <td align="center">${dict.dict_value }</td>
					        <td align="center">${dict.parent_id }</td>
					        <td align="right">${dict.dict_depict }</td>
					        <td align="center">
					        	<div class="action-buttons">
					        		<shiro:hasPermission name="${menu.menuId }:delete">
						                <a class="red" href="javascript:delMenu(${dict.id })">
						                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
						                </a>
					                </shiro:hasPermission>
					            </div>
					        </td>
					    </tr>
				    </c:forEach>
				</table>
	</form:form>
</div>
</body>
</html>
