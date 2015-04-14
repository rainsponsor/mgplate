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
	$("#searchForm").attr('action',"${ctx}/sys/user/userlist");
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
	if(confirm("是否确认删除？")){
		$("#searchForm").attr('action',"${ctx}/sys/user/"+uid+"/delete");
		$("#searchForm").submit();
	}
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
		<span>系统管理 &gt;&gt; 用户管理</span>
	</div>
	<div class="search_area">
		<form action="" name="searchForm" id="searchForm" method="post">
			<table width="99%">
				<tr>
			    	<th align="right">用户名：</th>
			    	<td>
			            <input type="text" name="user_name" value="${user.user_name }" class="length_2"  placeholder="输入用户名">
			        </td>
			    	<th align="right">用户电话：</th>
			    	<td>
			           <input type="text" name="phone" value="${user.phone }" class="length_2"  placeholder="输入用户电话号">
			        </td>
			        <th>所属部门:</th>
					<td><ptags:orgtree id="wp_org_id" name="wp_org_id"
							value="${user.wp_org_id }" labelId="org_name"
							labelName="org_name" labelValue="${user.org_name }" />
					</td>
					</tr>
					<tr>
			    	<th align="right">用户状态：</th>
			    	<td>
			           <ptags:select parentDicId="4" option="true" value="${user.user_status }" id="user_status" cssClass="form-control length_2"/>
			        </td>
			    	<th align="right">是否为主管：</th>
			    	<td>
			           <ptags:select parentDicId="1" option="true" value="${user.is_major }" id="is_major" cssClass="form-control length_2"/>
			        </td>
			    	<th align="right">主管名：</th>
			    	<td>
			           <input type="text" name="major_name" value="${user.major_name }" class="length_2"  placeholder="输入主管姓名">
			        </td>
			        
			    	<td align="right"><a href="javascript:searchBtn();" class="btn btn-primary fr">检 索</a></td>
			    </tr>
			</table>
		</form>
	</div>
	    <div class="table_header">
	        <span class="fl table_top">我是表头</span>
	        <div class="table_btn_box">
	        	<shiro:hasPermission name="${user.menuId }:create">
	            	<a href="${ctx}/sys/user/form" class="btn btn-primary fl">增 加</a>
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
	            <th>用户名</th>
	            <th>登录名</th>
	            <th>所属部门</th>
	            <th width="7%">用户状态</th>
	            <th>用户电话</th>
	            <th>email</th>
	            <th>直接主管</th>
	            <th width="8%">是否为主管</th>
	            <th width="14%">操作</th>
	        </tr>
	        <c:forEach items="${userList }" var="userModle" varStatus="i">
	        <tr>
	            <td class="row_center">
	            	<label>
	                    <input type="checkbox" class="ace" name="checkbox_template" value="${userModle.id }">
	                    <span class="lbl"></span>
	                </label>
	            </td>
	            <td align="center">${i.count }</td>
	            <td>${userModle.user_name }</td>
	            <td>${userModle.login_name }</td>
	            <td>${userModle.org_name }</td>
	            <td align="center">
		            <c:if test="${user.user_status == 0 }">有效</c:if>
		            <c:if test="${user.user_status == 1}">无效 </c:if> 
	            </td>
	            <td>${userModle.phone }</td>
	            <td>${userModle.email }</td>
	            <td>${userModle.major_name }</td>
	            <td align="center">
	            	<c:if test="${userModle.is_major == 0}">否</c:if> 
	            	<c:if test="${userModle.is_major == 1}">是</c:if> 
	            </td>
	            <td align="center">
	            <div class="action-buttons">
	          			<shiro:hasPermission name="${user.menuId }:view">
			                <a class="blue" href="${ctx}/sys/user/detail?id=${userModle.id }" title="详细">
			                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
			                </a>
			             </shiro:hasPermission>
						 <c:if test="${userModle.user_status == 0 }">
							<shiro:hasPermission name="${user.menuId }:update">
				                <a class="green" href="${ctx}/sys/user/toupdate?id=${userModle.id }" title="修改">
				                    <i class="ace-icon fa fa-pencil bigger-130"></i>
				                </a>
				             </shiro:hasPermission>
				             <shiro:hasPermission name="${user.menuId }:stop">
				                <a class="red" href="${ctx}/sys/user/${userModle.id }/delete" onclick="return app.confirm('提示：','您确定要停用此系统用户吗？',this.href)" title="停用">
				                    <i class="ace-icon fa fa-minus-circle bigger-130"></i>
				                </a>
			                 </shiro:hasPermission>
			                 <shiro:hasPermission name="${user.menuId }:recover">
				                 <a class="grey" title="恢复">
				                    <i class="ace-icon fa fa-reply bigger-130"></i>
				                 </a>
			                 </shiro:hasPermission>
						 </c:if>
						 <c:if test="${userModle.user_status == 1}">
							<shiro:hasPermission name="${user.menuId }:update">
								<a class="grey" title="修改">
				                    <i class="ace-icon fa fa-pencil bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
			                <shiro:hasPermission name="${user.menuId }:delete">
				                <a class="grey" title="删除">
				                    <i class="ace-icon fa fa-minus-circle bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
			                <shiro:hasPermission name="${user.menuId }:recover">
				                <a class="red" href="${ctx}/sys/user/${userModle.id }/recovery" onclick="return app.confirm('提示：','您确定要恢复此系统用户吗？',this.href)" title="恢复">
				                    <i class="ace-icon fa fa-reply bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
						 </c:if>
						 	<shiro:hasPermission name="${user.menuId }:reset">
				                <a class="green" href="${ctx}/sys/user/${userModle.id }/reset" onclick="return app.confirm('提示：','您确定要重置此系统用户密码吗？(默认密码：123456)',this.href)" title="重置密码">
			                   	 	<i class="ace-icon fa fa-rotate-left bigger-130"></i>
			               	    </a>
		               	    </shiro:hasPermission>
		               	    <shiro:hasPermission name="${user.menuId }:delete">
				                <a class="red" href="${ctx}/sys/user/${userModle.id }/deluser" onclick="return app.confirm('提示：','您确定要删除此系统用户吗？',this.href)" title="删除">
				                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
				                </a>
			                </shiro:hasPermission>
		            </div>
		            </td>
	        </tr>
	        </c:forEach>
	    </table>
	    <div class="row">
	    <p:page mhFrom="searchForm" action="/sys/user/userlist"/>
	    </div>
    </div>
  </body>
</html>
