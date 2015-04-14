<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单内容管理</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
$(document).ready(function(){
    $("#menuForm").validationEngine();
});
function delMenu(id){
	tableList.app.popConfirm("提示：","您确定要删除此菜单吗？","confirm_win");
	$('.confirm_win').click(function(){
		var url = "${ctx}/sys/menutree/delete?id="+id;
		$.ajax({
			url:url,
			type:'post',
			async: false,
			dataType:'json',
			success:function(result){
				if(result==-1){//不能删除
					tableList.app.popAlert('提示:',"此菜单包含子菜单，如果要删除请先删除其子菜单！");
					return;
				}else if(result==-2){
					tableList.app.popAlert('提示:',"此菜单包含子菜单的功能，如果要删除请先删除其子菜单的功能！");
					return;
				}else{
					window.location = "${ctx}/sys/menutree/content?id="+result;
					var iframe = window.parent.document.getElementById("fr_tree_content");
					iframe.contentWindow.location.reload(true);
				}
				app.close('confirm_win');
			}
		});
	});
}

function update(){
	var url = "${ctx}/sys/menutree/update";
	$.ajax({
		url:url,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			id:$("#id").val(),
			parent_id:$("#parent_id").val(),
			menu_name:$("#menu_name").val(),
			is_leaf:$("#is_leaf").val(),
			menu_url:$("#menu_url").val(),
			menu_code:$("#menu_code").val(),
			menu_no:$("#menu_no").val()
		},
		success:function(result){
			window.location = "${ctx}/sys/menutree/content?id="+result;
			var iframe = window.parent.document.getElementById("fr_tree_content");
			iframe.contentWindow.location.reload(true);
		}
	});
}

var funDialog = "<div class=\"func\">"+
		    	"	<form id=\"funcForm\" name=\"funcForm\" method=\"post\">"+
		    	"		 <input type=\"hidden\" name=\"menutree_id\" id=\"menutree_id\">"+
		    	"		 <input type=\"hidden\" name=\"id\" id=\"id\">"+
				"        功能名称：<input type=\"text\" name=\"function_name\" id=\"function_name\" class=\"length_1 validate[required]\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"        功能序号：<input type=\"text\" name=\"function_no\" id=\"function_no\" class=\"length_1 validate[required,custom[number]]\"><br/><br/>"+
				"        功能路径：<input type=\"text\" name=\"function_url\" id=\"function_url\" class=\"length_3 validate[required]\"><br/><br/>"+
		        "	</form>"+
    			"</div>";

function saveFunc(){
	tableList.app.popDialog("添加功能:",funDialog,"90","400","func_dialog");
	$("#funcForm").validationEngine();
	$('.func_dialog').click(function(){
		if($("#funcForm").validationEngine('validate')){
			$("#menutree_id").val($("#id").val());
			$("#funcForm").attr("action","${ctx}/sys/func/save");
			$("#funcForm").submit();
			app.close('func_dialog');
		}
	});
};

function updateFunc(id){
	var url = "${ctx}/sys/func/funcinfo";
	$.ajax({
		url:url,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			id:id
		},
		success:function(result){
			if(result != null){
				tableList.app.popDialog("修改功能:",funDialog,"90","400","func_dialog");
				$("#funcForm").validationEngine();
				$("input[id='id']").val(result.id);
				$("input[id='menutree_id']").val(result.menutree_id);
				$("input[id='function_name']").val(result.function_name);
				$("input[id='function_no']").val(result.function_no);
				$("input[id='function_url']").val(result.function_url);
			}else{
				tableList.app.popAlert("警告提示", "抱歉您修改的内容不存在！");
				app.close('func_dialog');
			}
		}
	});
	$('.func_dialog').click(function(){
		if($("#funcForm").validationEngine('validate')){
			$("#funcForm").attr("action","${ctx}/sys/func/update");
			$("#funcForm").submit();
			app.close('func_dialog');
		}
	});
}

</script>
</head>
<body>
<div class="col_12">
	<form:form id="menuForm" name="menuForm" modelAttribute="menu" method="post">
		<form:hidden path="id"/>
		<form:hidden path="parent_id"/>
		<c:choose>
			<c:when test="${menu.is_leaf == 0 }">
				<fieldset class="fieldset_box">
					<legend><i class="legend_tag fa-pencil fa"></i>菜单基本信息</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
					    <tr>
					        <th>菜单名称:</th>
					        <td>
					        	<form:input path="menu_name" cssClass="length_2 validate[required]"/>
					        </td>
					        <th>叶子菜单:</th>
					        <td>
					        	<ptags:select parentDicId="1" value="${menu.is_leaf}" id="is_leaf" cssClass="form-control length_2 validate[required]"/>
					        </td>
					        <td align="right">
					      	    <shiro:hasPermission name="${menuModel.menuId }:update">
									<a href="javascript:update();" type="button" class="btn btn-primary">保存</a>
								</shiro:hasPermission>
					        </td>
					    </tr>
					</table>
			    </fieldset>
				<div class="table_header">
					<span class="fl table_top">子菜单信息</span>
				    <div class="table_btn_box">
				  	    <shiro:hasPermission name="${menuModel.menuId }:create">
				        	<a href="${ctx }/sys/menutree/form?id=${menu.id}" class="btn btn-primary fl">增加子菜单</a>
				        </shiro:hasPermission>
				    </div>
				    <div class="clear"></div>
				</div>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
				    <tr>
				        <th width="5%">序号</th>
				        <th width="20%">子菜单名称</th>
				        <th width="10%">菜单序号</th>
				        <th>菜单路径</th>
				        <th width="8%">操作</th>
				    </tr>
				    <c:forEach items="${menu.children }" varStatus="index" var="menu">
					    <tr>
					        <td align="center">${index.count }</td>
					        <td align="center">${menu.menu_name }</td>
					        <td align="center">${menu.menu_no }</td>
					        <td align="right">${menu.menu_url }</td>
					        <td align="center">
					        	<div class="action-buttons">
					        		<shiro:hasPermission name="${menuModel.menuId }:delete">
						                <a class="red" href="javascript:delMenu(${menu.id })">
						                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
						                </a>
					                </shiro:hasPermission>
					            </div>
					        </td>
					    </tr>
				    </c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<fieldset class="fieldset_box">
					<legend><i class="legend_tag fa-pencil fa"></i>菜单基本信息</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_form">
					    <tr>
					        <th>菜单名称:</th>
					        <td>
					        	<form:input path="menu_name" cssClass="length_2 validate[required]"/>
					        </td>
					        <th>菜单路径:</th>
					        <td>
					        	<form:input path="menu_url" cssClass="length_2 validate[required]"/>
					        </td>
					        <th>叶子菜单:</th>
					        <td>
					        	<ptags:select parentDicId="1" value="${menu.is_leaf}" id="is_leaf" cssClass="form-control length_2 validate[required]"/>
					        </td>
					    </tr>
					    <tr>
					        <th>菜单编号:</th>
					        <td>
					        	<form:input path="menu_code" cssClass="length_2 validate[required]"/>
					        </td>
					        <th>序号:</th>
					        <td>
					        	<form:input path="menu_no" cssClass="length_2 validate[required]"/>
					        </td>
					    </tr>
					    <tr>
					        <td align="right" colspan="6">
					        	<shiro:hasPermission name="${menuModel.menuId }:update">
									<a href="javascript:update();" type="button" class="btn btn-primary">保存</a>
								</shiro:hasPermission>
					        </td>
					    </tr>
					</table>
			    </fieldset>
				<div class="table_header">
					<span class="fl table_top">功能信息</span>
				    <div class="table_btn_box">
				     	<shiro:hasPermission name="${menuModel.menuId }:create">
				    		<a href="javascript:saveFunc();" class="btn btn-primary btn-table">增加功能</a>
				    	</shiro:hasPermission>
				    </div>
				    <div class="clear"></div>
				</div>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
				    <tr>
				        <th width="5%">序号</th>
				        <th>功能名称</th>
				        <th>功能地址</th>
				        <th>编号</th>
				        <th width="7%">操作</th>
				    </tr>
				    <c:forEach items="${menu.functions }" varStatus="index" var="functions">
					    <tr>
					        <td align="center">${index.count }</td>
					        <td>${functions.function_name }</td>
					        <td>${functions.function_url }</td>
					        <td align="right">${functions.function_no }</td>
					        <td>
					        	<div class="action-buttons">
					        	 	<shiro:hasPermission name="${menuModel.menuId }:update">
						                <a class="green" href="javascript:updateFunc(${functions.id });">
						                    <i class="ace-icon fa fa-pencil bigger-130"></i>
						                </a>
					                </shiro:hasPermission>
					                <shiro:hasPermission name="${menuModel.menuId }:delete">
						                <a class="red" href="${ctx }/sys/func/delFunc?id=${functions.id }&menuId=${functions.menutree_id }" onclick="return app.confirm('提示：','您确定要删除此功能吗？',this.href)">
						                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
						                </a>
					                </shiro:hasPermission>
					            </div>
					        </td>
					    </tr>
				    </c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</form:form>
</div>
</body>
</html>
