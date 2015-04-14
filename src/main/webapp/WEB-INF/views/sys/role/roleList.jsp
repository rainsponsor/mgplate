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
<script type="text/javascript">
function searchBtn(){
	$("#searchForm").attr('action',"${ctx}/sys/role/rolelist");
	$("#searchForm").submit();
}
function checkAll_onClick(obj){
	var checkAllId = document.getElementById("chk_All");
	var checkItems = document.getElementsByName(obj);
	if(checkAllId.checked){
		for(var i=0; i<checkItems.length; i++){
			checkItems[i].checked = true;
		}
	}else{
		for(var i=0; i<checkItems.length; i++){
			checkItems[i].checked = false;
		}
	}
}

function detail(uid){
	$("#searchForm").attr('action',"${ctx}/sys/user/"+uid+"/detail");
	$("#searchForm").submit();
}
function delUpdate(uid){
	$("#searchForm").attr('action',"${ctx}/sys/user/"+uid+"/delete");
	$("#searchForm").submit();
}

//checkbox 多删除 处理id传值
function deleteCompany() {
    var ids = document.getElementsByName("checkbox_template");
    var result = "";
    if (typeof (ids) != "undefined") {
        if (typeof (ids.length) != "undefined") {
            for ( var i = 0; i < ids.length; i++) {
                if (ids[i].checked) {
                    result += ids[i].value + ",";
                }
            }
            result = result.substring(0, result.length - 1);
        } else {
            if(ids.checked)
                result = ids.value;
        }
    }
    if (result == "") {
        alert("请选择要删除的记录");
        return;
    } 
    if(confirm("是否确认删除？")){
		$('#searchForm').attr('action',"${ctx}/sys/user/"+result+"/deleteitem");
  		$('#searchForm').submit();
  	}
}
</script>
  </head>
  
  <body>
  <div class="col_12">
	  <div class="nav">
		<span>系统管理 &gt;&gt; 角色管理</span>
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
			<shiro:hasPermission name="${role.menuId }:create">  
            	<a href="${ctx}/sys/role/form" class="btn btn-primary fl">增 加</a>
            </shiro:hasPermission> 
	            
	<%--            <a href="#" class="btn btn-primary fl">删 除</a>--%>
	        </div>
	        <div class="clear"></div>
	    </div>
	    <table cellspacing="0" cellpadding="0" border="0" width="100%" class="table_list">
	        <tr>
	        <th width="3%">	        	
	        	<label>
	                <input type="checkbox" class="ace" id="chk_All" onclick='checkAll_onClick("checkbox_template")'/>
	                <span class="lbl"></span>
				</label>
		    </th>
	        	<th width="5%">序号</th>
	            <th width="10%">角色名称</th>
	            <th width="10%">是否活动</th>
	            <th>角色描述</th>
	            <th width="10%">创建人</th>
	            <th width="12%">操作</th>
	        </tr>
	        <c:forEach items="${roleList }" var="roleModel" varStatus="i">
	        <tr>
	            <td class="row_center">
	            	<label>
	                    <input type="checkbox" class="ace" name="checkbox_template" value="${roleModel.id }">
	                    <span class="lbl"></span>
	                </label>
	            </td>
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
	            		<shiro:hasPermission name="${role.menuId }:view">  
			                <a class="blue" href="${ctx}/sys/role/detail?id=${roleModel.id }" title="详细">
			                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
			                </a>
		                </shiro:hasPermission>
						<c:if test="${roleModel.active_flag == 0  }">
							<shiro:hasPermission name="${role.menuId }:update">  
								<a class="green" href="${ctx}/sys/role/toupdate?id=${roleModel.id }" title="修改">
			                 	   <i class="ace-icon fa fa-pencil bigger-130"></i>
			                	</a>
		                	</shiro:hasPermission>
		                	<shiro:hasPermission name="${role.menuId }:stop">  
				                <a class="red" href="${ctx}/sys/role/${roleModel.id }/delete" onclick="return app.confirm('提示：','您确定要停用此系统角色吗？',this.href)" title="停用">
				                    <i class="ace-icon fa  fa-minus-circle bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
			                <shiro:hasPermission name="${role.menuId }:recover">  
			               		<a class="grey" title="恢复">
				                    <i class="ace-icon fa fa-reply bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
						</c:if>
						 <c:if test="${roleModel.active_flag == 1 }">
							 <shiro:hasPermission name="${role.menuId }:update">  
								<a class="grey" title="修改">
			                 	   <i class="ace-icon fa fa-pencil bigger-130"></i>
			                	</a>
		                	</shiro:hasPermission>
		                	<shiro:hasPermission name="${role.menuId }:stop">  
				                <a class="grey" title="停用">
				                    <i class="ace-icon fa fa-minus-circle bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
			                <shiro:hasPermission name="${role.menuId }:recover">  
				                <a class="red" href="${ctx}/sys/role/${roleModel.id }/recovery" onclick="return app.confirm('提示：','您确定要恢复此系统角色吗？',this.href)" title="恢复">
				                    <i class="ace-icon fa fa-reply bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
						 </c:if>
						 <shiro:hasPermission name="${role.menuId }:delete">  
			                <a class="red" href="${ctx}/sys/role/${roleModel.id }/delrole" onclick="return app.confirm('提示：','您确定要删除此系统角色吗？',this.href)" title="删除">
			                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
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
