<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>授权管理</title>
    <meta name="renderer" content="webkit" />
  	<meta name="decorator" content="default"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<ptags:staticfile/>
<script type="text/javascript">
function searchBtn(){
	$("#searchForm").attr('action',"${ctx}/sys/role/acllist");
	$("#searchForm").submit();
}
</script>
  </head>
  
  <body>
  <div class="col_12">
	  <div class="nav">
		<span>系统管理 &gt;&gt; 授权管理</span>
	</div>
	<div class="search_area">
		<form action="" name="searchForm" id="searchForm" method="post">
			<table width="99%">
				<tr>
			    	<td align="right">角色名称：</td>
			    	<td>
			            <input type="text" name="role_name" value="${role.role_name }" class="length_2"  placeholder="输入用户名">
			        </td>
			    	<th align="right">角色状态：</th>
			    	<td>
			           <ptags:select parentDicId="7" option="true" value="${role.active_flag }" id="active_flag" cssClass="form-control length_2"/>
			        </td>
			    	<th align="right">创建人：</th>
			    	<td>
			           <input type="text" name="user_name" value="${role.user_name }" class="length_2"  placeholder="输入主管姓名">
			        </td>
			    	<td align="right"><a href="javascript:searchBtn();" class="btn btn-primary fr">检 索</a></td>
			    </tr>
			</table>
		</form>
	</div>
	    <div class="table_header">
	        <span class="fl table_top">角色列表</span>
	        <div class="table_btn_box">
<%--	            <a href="${ctx}/sys/role/form" class="btn btn-primary fl">增 加</a>--%>
	        </div>
	        <div class="clear"></div>
	    </div>
	    <table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
	        <tr>
	        	<th width="5%">序号</th>
	            <th width="10%">角色名称</th>
	            <th width="10%">是否活动</th>
	            <th>角色描述</th>
	            <th width="10%">创建人</th>
	            <th width="5%">操作</th>
	        </tr>
	        <c:forEach items="${roleList }" var="roleModel" varStatus="i">
	        <tr>
	            <td align="center">${i.count }</td>
	            <td>${roleModel.role_name }</td>
	            <td>
		            <c:if test="${roleModel.active_flag == 0 }">是</c:if>
		            <c:if test="${roleModel.active_flag == 1}">否 </c:if> 
	            </td>
	            <td>${roleModel.description }</td>
	            <td>${roleModel.user_name }</td>
	            <td>
		            <div class="action-buttons" align="center">
		            <shiro:hasPermission name="${role.menuId }:authorize">  
		                <a class="blue" href="${ctx}/sys/role/rolegrant?id=${roleModel.id }" title="授权">
		                    <i class="ace-icon fa fa-exchange bigger-130"></i>
		                </a>
	                </shiro:hasPermission>
			        </div>
		        </td>
	        </tr>
	        </c:forEach>
	    </table>
	    <div class="row">
	    <p:page mhFrom="searchForm" action="/sys/role/rolelist"/>
	    </div>
    </div>
  </body>
</html>
