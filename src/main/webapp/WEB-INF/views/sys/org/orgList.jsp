<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>部门管理</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
function delOrg(id){
	tableList.app.popConfirm("提示：","您确定要删除此部门吗？","confirm_win");
	$('.confirm_win').click(function(){
		var url = "${ctx }/sys/org/delete?id="+id;
		$.ajax({
				url:url,
				type:'post',
				async: false,
				dataType:'json',
				success:function(result){
					if(result==-1){//不能删除
						app.confirmclose('confirm_win');
						tableList.app.popAlert('提示:',"此部门包含子部门，如果要删除请先删除其子部门！");
						return;
					}else{
						window.location.reload(true);
					}
			}
		});
	});
}
function search(){
	$("#searchForm").attr('action', "${ctx}/sys/org/list");
	$("#searchForm").submit();
}
</script>
</head>
<body>
<div class="col_12">
	<div class="nav">
		<span>系统管理 &gt;&gt; 部门管理</span>
	</div>
	<div class="search_area">
		<form action="" name="searchForm" id="searchForm">
			<table width="99%">
				<tr>
			    	<td align="right">部门名称：</td>
			    	<td>
			            <input type="text" class="length_2" id="org_name" name="org_name" value="${org.org_name }" placeholder="输入部门名称">
			        </td>
			    	<td align="right">所有子部门：</td>
			    	<td>
			    	<ptags:orgtree id="orgid" name="id" value="${org.id }" labelId="parent_name" labelName="parent_name" labelValue="${org.parent_name }" />
			        </td>
			    	<td align="right"><a href="javascript:search();" class="btn btn-primary fr">检 索</a></td>
			    </tr>
			</table>
		</form>
	</div>
	<div class="table_header">
	    <span class="fl table_top">部门信息</span>
	    <div class="table_btn_box">
	    <shiro:hasPermission name="${org.menuId }:create">  
	        <a href="${ctx }/sys/org/form" class="btn btn-primary fl">增 加</a>
        </shiro:hasPermission>
	    </div>
	    <div class="clear"></div>
	</div>
	<table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
	    <tr>
	        <th width="3%">	        	
	        	<label>
	                <input type="checkbox" class="ace">
	                <span class="lbl"></span>
				</label>
		    </th>
	        <th width="5%">序号</th>
	        <th>部门名称</th>
	        <th>上级部门</th>
	        <th>联系电话</th>
	        <th width="8%">操作</th>
	    </tr>
	    <c:forEach items="${orgList }" varStatus="index" var="orgModel">
		    <tr>
		        <td class="row_center">
		        	<label>
		                <input type="checkbox" class="ace">
		                <span class="lbl"></span>
		            </label>
		        </td>
		        <td align="center">${index.count }</td>
		        <td>${orgModel.org_name }</td>
		        <td>${orgModel.parent_name }</td>
		        <td align="right">${orgModel.contact }</td>
		        <td align="center">
		        	<div class="action-buttons">
		        	 <shiro:hasPermission name="${org.menuId }:view">  
		                <a class="blue" href="${ctx }/sys/org/info?id=${orgModel.id }" title="详细">
		                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
		                </a>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="${org.menuId }:update">  
		                <a class="green" href="${ctx }/sys/org/form?id=${orgModel.id }" title="修改">
		                    <i class="ace-icon fa fa-pencil bigger-130"></i>
		                </a>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="${org.menuId }:delete">  
		                <a class="red" href="javascript:delOrg(${orgModel.id });" title="删除">
		                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
		                </a>
	                </shiro:hasPermission>
		            </div>
		        </td>
		    </tr>
	    </c:forEach>
	</table>
	    <div class="row">
	    	<p:page mhFrom="searchForm" action="/sys/org/list"/>
	   </div>
   </div>
  </body>
</html>
