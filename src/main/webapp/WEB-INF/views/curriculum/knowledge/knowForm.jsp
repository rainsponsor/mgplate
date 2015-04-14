<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>知识点- 添加数据</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript" src="${ctx }/resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx }/resources/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx }/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel=stylesheet href="${ctx }/resources/ueditor/themes/default/css/ueditor.css">

<script type="text/javascript" charset="utf-8" src="${ctx }/resources/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/resources/ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/resources/ueditor/kityformula-plugin/defaultFilterFix.js"></script>

<script type="text/javascript">
var folder = '${dirIds }';
var $fm;
$(document).ready(function(){
    $("#inputForm").validationEngine();
    loadFoler(folder);
    $fm = $("#inputForm");
});

function save(){
	var url = "${ctx}/curr/know/save";
	$fm.attr("action",url);
	$fm.submit();
}
</script>
</head>
<body>
<div class="col_12">
	<div class="nav">
		<span>邮箱管理&gt;&gt;${knowledge.catalog.name }信息</span>
	</div>
    <form:form name="inputForm" id="inputForm" method="post" modelAttribute="knowledge">
    	<input type="hidden" name="id" id="id" value="${knowledge.id }">
    	<input type="hidden" name="dir_id" id="dir_id" value="${knowledge.catalog.id }">
    	<input type="hidden" name="dir_ids" id="dir_ids" value="${knowledge.catalog.dir_ids }">
    	<input type="hidden" name="sub_id" id="sub_id" value="${knowledge.catalog.sub_id }">
	    <div class="table_header">
	        <div class="table_btn_box_left">
	            <a href="javascript:history.back();" type="button" class="btn btn-primary row_tj">返回</a>
	        </div>
	        <div class="table_btn_box">
	            <a href="javascript:save();" class="btn btn-primary row_tj">保存</a>
	        </div>
	        <div class="clear"></div>
	    </div>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
			<tr></tr>
		    <tr>
		        <td align="right">目录:</td>
		        <td>
		        	${knowledge.catalog.name }
		        </td>
		        <td align="right">序号:</td>
		        <td>
		        	<form:input path="serialno" cssClass="length_1 validate[required]"/>
		        </td>
		    </tr>
		    <tr>
		        <td align="right">索引:</td>
		        <td colspan="5">
		        	<input type="text" class="length_2 validate[required]" name="search_key" id="search_key" value="${knowledge.search_key }">
		        </td>
		    </tr>
		    <tr>
		        <td colspan="6">
	        		<textarea id="editor" name="content" style="width:100%;height: 400px;">${knowledge.content}</textarea>
					<script type="text/javascript">
						var ue = UE.getEditor("editor",{
							'enterTag':'br' 
						});
						/*
						*/
						ue.ready(function() {
						    ue.execCommand('serverparam', {
						        "folder":"${dirIds }"
						    });
						});
						//alert(this.queryCommandValue('serverparam').file_path+"--");
					</script>
		        </td>
		    </tr>
		</table>
    </form:form>
</div>
</body>
</html>
