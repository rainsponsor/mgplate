<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>知识管理</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
function generateQuestionBank(id){
	tableList.app.popConfirm("提示：","您确定要生成最新题库吗？","confirm_win");
	$('.confirm_win').click(function(){
		var url = "${ctx}/curr/know/generatequestionbank?id="+id;
		$.ajax({
			url:url,
			type:'post',
			async: false,
			dataType:'json',
			success:function(result){
				if(result==1){//不能删除
					tableList.app.popAlert('提示:',"创建知识库成功！");
					return;
				}else{
					tableList.app.popAlert('提示:',"创建知识库失败！");
					return;
				}
				app.close('confirm_win');
			}
		});
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
			<legend><i class="legend_tag fa-pencil fa"></i>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_list">
			    <tr>
			        <th>ID:</th>
			        <td>
			       		 ${catalog.id }
			        </td>
			        <th>名称:</th>
			        <td>
			       		 ${catalog.name }
			        </td>
			        <th>所属科目:</th>
			        <td>
			        	<ptags:dictext parentDicId="10" value="${catalog.sub_id }" id="sub_id"/>
			        </td>
			        <th>当前版本:</th>
			        <td>
			        	${catalog.version }
			        </td>
			    </tr>
				</table>
	    </fieldset>
		<div class="table_header">
	        <div class="table_btn_box">
	            <a href="javascript:generateQuestionBank(${catalog.id });" 
	            	class="btn btn-primary fl"><i class="legend_tag fa-pencil fa"></i>生成${catalog.name }知识点题库</a>
	        </div>
			<span class="fl table_top">${catalog.name }信息</span>
		    <div class="clear"></div>
		</div>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
		    <tr>
		        <th width="7%">编号</th>
		        <th width="40%">名称</th>
		        <th width="22%">ID</th>
		        <th width="12%">父ID</th>
		        <th width="12%">文件</th>
		    </tr>
		    <c:forEach items="${catalog.children }" varStatus="index" var="log">
			    <tr>
			        <td align="center">${log.serialno }</td>
			        <td align="center">${log.name }</td>
			        <td align="center">${log.id }</td>
			        <td align="center">${catalog.id }</td>
			        <td align="center">${catalog.file_path }</td>
			    </tr>
		    </c:forEach>
		</table>
	</form:form>
</div>
</body>
</html>
