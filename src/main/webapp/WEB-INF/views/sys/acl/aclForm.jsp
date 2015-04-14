<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>授权管理 - 角色授权页面</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<ptags:staticfile/>
<script type="text/javascript">
//全局变量,树1 , 树2
var SrcTree, TarTree;
var srcData = {};
var tarData = {};
var aclUrl = '${ctx}/sys/role/acl';
$.ajax({
	url:aclUrl,
	type:'post',
	async: false,
	dataType:'json',
	data:{
		id:'${role.id }'
	},
	success:function(result){
		srcData = result.all;
		tarData = result.have;
	}
});
//ztree 的设置.对于标准格式数据.不需要设置我注释的那部分.
//那部分属于简单格式数据需要设置的...不清楚去看ztree demo
var setting = {
	check: {
        enable: true
    }
};
//数据右移动
function addRole() {
	//移动方法
	//右移时SrcTree 在第一个参数,TarTree第二个参数
	//表示SrcTree移动致TarTree
	moveTreeNode(SrcTree, TarTree);
}
	
//数据左移动
function delRole() {
	//移动方法 参数相反
	moveTreeNode(TarTree, SrcTree);
}
	
function moveTreeNode(zSrcTree, zTarTree){
	var nodes = zSrcTree.getCheckedNodes(true);	//获取选中需要移动的数据
	for(var i=0;i<nodes.length;i++){			//把选中的数据从根开始一条一条往右添加
		var node = nodes[i];
		var strs={};			//新建一个JSON 格式数据,表示为一个节点,可以是根也可以是叶
		strs.id =node.id;
		strs.name=node.name;
		strs.code= node.code;
		strs.resourceType= node.resourceType;
		strs.children = new Array();	//树节点里面有个 nodes 集合,用来存储父子之间的包涵关系
		
		//调用添加方法
		//strs : json 格式..拼装成树的一个节点
		//zTarTree: 表示需要添加节点的树
		zTreeDataAddNode(strs,zTarTree);
		//获取这个被添加的code 如果是右增加  用来把它从左边移除掉
		var scode = strs.code;
		scode = scode.substring(0, scode.lastIndexOf("-"));
		//使用递归移除 移除的时候从叶子开始找  和增加的时候刚好相反
		//参数1就是数组最后一个数据  
		//scode  : 上面截取的code 表示父亲节点 
		//zSrcTree : 需要移除的树,在zSrcTree 里面移除此对象
		zTreeDataDelete(nodes[nodes.length-(i+1)],scode,zSrcTree);
	}
	//把选中状态改为未选择
	zTarTree.checkAllNodes(false);
	zSrcTree.checkAllNodes(false);
	//刷新
	zTarTree.refresh();
	zSrcTree.refresh();
}
	
//树数据移动方法
function zTreeDataAddNode(strs,zTarTree){
	var nodes = zTarTree.transformToArray(zTarTree.getNodes());	//获取需要添加数据的树下面所有节点数据
	//如果有多个数据需要遍历,找到strs 属于那个父亲节点下面的元素.然后把自己添加进去
	if(nodes.length > 0){
		//这个循环判断是否已经存在,true表示不存在可以添加,false存在不能添加
		var isadd=true;
		for(var j=0;j<nodes.length;j++){
			if(strs.code==nodes[j].code){
				isadd=false;
				break;
			}
		}
		//找到父亲节点
		var scode = strs.code;
		scode = scode.substring(0, scode.lastIndexOf("-"));
		var i=0;
		var flag =false;
		for(i ;i<nodes.length;i++){
			if(scode ==nodes[i].code){
				flag = true;
				break;
			}
		}
		//同时满足两个条件就加进去,就是加到父亲节点下面去
		if(flag && isadd){
			var treeNode1=nodes[i];
			treeNode1.children[treeNode1.children.length <=0 ? 0 : treeNode1.children.length++]=strs;
		//如果zTarTree 里面找不到,也找不到父亲节点.就把自己作为一个根add进去
		}else if(isadd){
			zTarTree.addNodes(null,strs);
		}
	}else{
		//树没任何数据时,第一个被加进来的元素
		zTarTree.addNodes(null,strs);
	}
}
	
//数据移除
function zTreeDataDelete(node,scode,zSrcTree){
	if(node.isParent){	//判断是不是一个根节点,如果是一个根几点从叶子开始遍历寻找
		//如果是个根就检测nodes里面是否有数据
			if(node.children.length > 0){
				//取出来
				var fnodes  = node.children;
				for(var x = 0; x<fnodes.length; x++){
					//不是根节点.并且code 相当就是需要移除的元素
					if(!(fnodes[x].isParent) && fnodes[x].code==scode){
						//调用ztree 的移除方法,参数是一个节点json格式数据
						zSrcTree.removeNode(fnodes[x]);
						//如果当前这个节点又是个根节点.开始递归
					}else if(fnodes[x].isParent){
						zTreeDataDelete(fnodes[x],scode);
					}
				}
			}else{
				//如果是个根,但是下面的元素没有了.就把这个根移除掉
				zSrcTree.removeNode(node);
			}
		}else{
			//不是就直接移除
			zSrcTree.removeNode(node);
		}
}

function setCheck(){
	var py = "p",sy ="",pn ="p",sn = "s",type = { "Y":py + sy, "N":pn + sn};
	SrcTree.setting.check.chkboxType = type;
	TarTree.setting.check.chkboxType = type;
	var nodes = TarTree.transformToArray(TarTree.getNodes());
	for(var i=0;i<nodes.length;i++){
		var node = SrcTree.getNodeByParam("code", nodes[i].code);
		SrcTree.selectNode(node,false);
		SrcTree.checkNode(node,true,true);
		TarTree.selectNode(nodes[i],false);
	}
	moveTreeNode(SrcTree, TarTree);
	SrcTree.setting.check.chkboxType.Y = py + "s";
}

function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}
//zTree说明:
//这里没有样式之类的东西,需要看到还需要引人几个css 和 imp 之类的东西.
//可以去看zTree的demo 和 API 
	
//初始化方法
$(document).ready(function(){
	//树1 数据 生成
	SrcTree = $.fn.zTree.init($("#srcTree"), setting, srcData);
	//树2 数据null
	TarTree = $.fn.zTree.init($("#tarTree"), setting, tarData);
	setCheck();
});

function aclGrantRole(){
	var roleId = $("#id").val();
	var selNodes = TarTree.transformToArray(TarTree.getNodes());
	var len = selNodes.length;
	var $roleids = $("#role_acl_ids");
	var $acltype = $("#role_acl_types");
	$roleids.val(",");
	$acltype.val(",");
	for(var i=0;i<len;i++){
		var node = selNodes[i];
		var id = node.id;
		var type = node.resourceType;
		if(id==0){
			continue;
		}
		var aclVal = $roleids.val();
		var typeVal = $acltype.val();
		var idIndex = aclVal.indexOf(","+id+",");
		var typeIndex = typeVal.indexOf(","+type+",");
		if(idIndex>=0 && typeIndex>=0 && (idIndex == typeIndex)){
			continue;
		}
		$roleids.val(aclVal+id+",");
		$acltype.val(typeVal+type+",");
	}
	var $fm = $("#grantForm");
	$fm.attr("action","${ctx}/sys/role/savegrant");
	$fm.submit();
}
	
</script>
</head>
<body>
	<div class="nav">
		<span>系统管理 &gt;&gt; 授权管理&gt;&gt; 角色授权</span>
	</div>
	<div style="width: 90%;margin: 0 auto;">
		<div class="tree_layout">
			<div align="right"><span style="color: #2679b5;">授权资源 <i class="ace-icon fa fa-arrow-circle-right bigger-130"></i></span></div>
			<div >
				<div>
					<ul id="srcTree" class="ztree"></ul>
				</div>
			</div>
		</div>
		<div class="grant_box" >
			<a href="javascript:addRole();" class="btn btn-primary fl">》》</a>
			<a href="javascript:delRole();" class="btn btn-primary fl" style="margin-top: 20px;">《《</a>
			<a href="${ctx}/sys/role/rolegrant?id=${role.id }" class="btn btn-primary fl" style="margin-top: 50px;">重置</a>
			<a href="javascript:aclGrantRole();" class="btn btn-primary fl" style="margin-top: 20px;">保存</a>
		</div>
		<div class="tree_layout">
			<div><span style="color: #2679b5;"><i class="ace-icon fa fa-arrow-circle-left bigger-130"></i> 已有授权</span></div>
			<div>
				<div>
					<ul id="tarTree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<form:form modelAttribute="role" method="post" id="grantForm" name="grantForm">
		<form:hidden path="id"/>
		<input type="hidden" id="role_acl_ids" name="role_acl_ids">
		<input type="hidden" id="role_acl_types" name="role_acl_types">
	</form:form>
</body>
</html>
