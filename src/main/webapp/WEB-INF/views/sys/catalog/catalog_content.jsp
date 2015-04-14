<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>目录信息管理</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
function delCatalog(id){
	tableList.app.popConfirm("提示：","您确定要删除此条数据吗？","confirm_win");
	$('.confirm_win').click(function(){
		var url = "${ctx}/sys/catalog/delete?id="+id;
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
					window.location = "${ctx}/sys/catalog/content?id="+result;
					var iframe = window.parent.document.getElementById("fr_tree_content");
					iframe.contentWindow.location.reload(true);
				}
				app.close('confirm_win');
			}
		});
	});
}

function update(){
	var id = $("#id").val();
	if(id==0){//不能删除
		tableList.app.popAlert('提示:',"此目录为管理根目录，不允许操作！");
		return;
	}
	var url = "${ctx}/sys/catalog/update";
	$.ajax({
		url:url,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			id:$("#id").val(),
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
</script>
</head>
<body>
<div class="col_12">
	<form:form modelAttribute="catalog" method="post">
		<form:input path="id" type="hidden"/>
		<form:input path="parent_id" type="hidden"/>
		<fieldset class="fieldset_box">
			<legend><i class="legend_tag fa-pencil fa"></i>目录基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
			    <tr>
			        <th>目录名称:</th>
			        <td>
			        	<form:input path="name" cssClass="length_2 validate[required]"/>
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
			       		 <form:input path="serialno" cssClass="length_2"/>
			        </td>
			        <th>文件目录:</th>
			        <td>
			       		 <form:input path="file_path" cssClass="length_2"/>
			        </td>
			     	<th>ID:</th>
			        <td>${catalog.id } </td>
			        <td align="right">
						<a href="javascript:update();" type="button" class="btn btn-primary">保存</a>
			        </td>
			    </tr>
			</table>
	    </fieldset>
		<div class="table_header">
			<span class="fl table_top">子目录信息列表</span>
		    <div class="table_btn_box">
		        <a href="${ctx }/sys/catalog/form?id=${catalog.id}" class="btn btn-primary fl">添加子目录</a>
		    </div>
		    <div class="clear"></div>
		</div>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
		    <tr>
		        <th width="7%">序号</th>
		        <th width="15%">目录名称</th>
		        <th width="8%">科目</th>
		        <th width="10%">版本号</th>
		        <th width="40%">文件目录</th>
		        <th width="7%">操作</th>
		    </tr>
		    <c:forEach items="${catalog.children }" varStatus="index" var="log">
			    <tr>
			        <td align="center">${log.serialno }</td>
			        <td align="center">${log.name }</td>
			        <td align="center">
			        	<ptags:dictext parentDicId="10" value="${log.sub_id }" id="sub_id"/>
			        </td>
			        <td align="center">${log.version }</td>
			        <td align="right">${log.file_path }</td>
			        <td align="center">
			        	<div class="action-buttons">
			                <a class="red" href="javascript:delCatalog(${log.id })">
			                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
			                </a>
			            </div>
			        </td>
			    </tr>
		    </c:forEach>
		</table>
	</form:form>
</div>
</body>
</html>
