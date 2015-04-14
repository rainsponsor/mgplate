// JavaScript Document
$(document).ready(function(e) {
    tableList.app.trColor();
	tableList.app.popup();
	tableList.app.dialogClose();
	//首页色块高度百分比
	tableList.app.boxHeight();
	//tableList.app.movePopup();
	
});

window.onresize = function()
{
	tableList.app.popup();
}

var tableList = {};

tableList.app = {};

tableList.app.boxHeight = function()
{
	$('.js_box_height_30').height(($(window).height()-24)*0.28);
	$('.js_box_height_70').height(($(window).height()-24)*0.68);
};

tableList.app.trColor = function()
{
	var oldColor = '';
	$('.table_list tr:odd').css(
	{
		'background-color':'#f9f9f9'
	});
	$('.table_list tr').mouseenter(function()
	{
		oldColor = $(this).css('background-color');
		$(this).css(
		{
			'background-color':'#f5f5f5'
		});
	}).mouseleave(function()
	{
		$(this).css(
		{
			'background-color':oldColor
		});
	});
}

tableList.app.popup = function()
{
	var oHeight = $(window.parent).height() - 111;
	
	
	
	var iHeight = $('.popup').height();
	var iWidth = $('.popup').width();
	if(iHeight > oHeight)
	{
		$('.popup').css(
		{
			'top':$(window).scrollTop(),
			'margin-left':-iWidth/2
		});
	}
	else
	{
		$('.popup').css(
		{
			'margin-top':$(window).scrollTop() - iHeight/2,
			'margin-left':-iWidth/2
		});
	}
}
tableList.app.dialogShow = function()
{
	$('.popup').animate(
	{
		opacity:1
	},200).show();
}

tableList.app.dialogClose = function()
{
	$('body').delegate('.js_close','click',function()
	{
		$('.popup').animate(
		{
			opacity:0
		},200,function()
		{
			$('.popup').remove();
		});
	});
}

tableList.app.popConfirm = function(head,obj,funClass,isWidth)
{
	var	popConfirmgHtml = '';
	popConfirmgHtml += '<div class="popup popup_confirm_close">';
	popConfirmgHtml += 	 	'<div class="popup_box popup_confirmg">';
	popConfirmgHtml += 	 		'<div class="popup_top" onclick="tableList.app.movePopup()">';
	popConfirmgHtml += 	 		'<i class="fa fa-check pop_top_confirm_icon"></i>';
	popConfirmgHtml += 	 			   head;
	popConfirmgHtml += 	 		'</div>';
	popConfirmgHtml += 	 		'<div class="popup_main">';
	popConfirmgHtml +=                 obj;
	popConfirmgHtml += 	 		'</div>';
	popConfirmgHtml +=    		'<div class="popup_foot">';
	popConfirmgHtml +=    			'<button data-bb-handler="cancel" type="button" class="btn btn-default js_close">取 消</button>';
	popConfirmgHtml +=    			'<button data-bb-handler="confirm" type="button" class="btn btn-primary  '+funClass+'">确 定</button>';
	popConfirmgHtml +=    		'</div>';
	popConfirmgHtml +=    	'</div>';
	popConfirmgHtml +=    	'<div class="fade"></div>';
	popConfirmgHtml += '</div>';
	$('body').append(popConfirmgHtml);
	//自定义宽度
	if(isWidth){
		$('.popup_confirmg').width(isWidth);
	}
	tableList.app.popup();
	tableList.app.dialogShow();
}

tableList.app.popAlert = function(head,obj,isWidth)
{
	var	popAlertHtml = '';
	popAlertHtml += '<div class="popup popup_alert_close">';
	popAlertHtml += 	 	'<div class="popup_box popup_alert">';
	popAlertHtml += 	 		'<div class="popup_top" onclick="tableList.app.movePopup()">';
	popAlertHtml += 	 		'<i class="fa fa-exclamation-triangle pop_top_icon"></i>';
	popAlertHtml += 	 		head;
	popAlertHtml += 	 		'</div>';
	popAlertHtml += 	 		'<div class="popup_main">';
	popAlertHtml +=                 obj;
	popAlertHtml += 	 		'</div>';
	popAlertHtml +=    		'<div class="popup_foot">';
	popAlertHtml +=    			'<button data-bb-handler="confirm" type="button" class="btn btn-primary js_close">确 认</button>';
	popAlertHtml +=    		'</div>';
	popAlertHtml +=    	'</div>';
	popAlertHtml +=    	'<div class="fade"></div>';
	popAlertHtml += '</div>';
	$('body').append(popAlertHtml);
	//自定义宽度
	if(isWidth){
		$('.popup_alert').width(isWidth);
	}
	tableList.app.popup();
	tableList.app.dialogShow();
}

/*
 * isHeight：调用方法时自定义高度
 * isWidth：调用方法时自定义宽度
 * */
tableList.app.popDialog = function(head,obj,isHeight,isWidth,funClass)
{
	var	popDialogHtml = '';
	popDialogHtml += '<div class="popup" onmousedown="tableList.app.movePopup(event,$(this))">';
	popDialogHtml += 	 	'<div class="popup_box">';
	popDialogHtml += 	 		'<div class="popup_top">';
	popDialogHtml += 	 		'<i class="fa fa-cog horizontal_nav_icon"></i>';
	popDialogHtml += 	 		head;
	popDialogHtml += 	 		'</div>';
	popDialogHtml += 	 		'<div class="popup_main popup_dialog">';
	popDialogHtml +=                 obj;
	popDialogHtml += 	 		'</div>';
	popDialogHtml +=    		'<div class="popup_foot">';
	popDialogHtml +=    			'<button data-bb-handler="confirm" type="button" class="btn btn-primary js_close">取 消</button>';
	popDialogHtml +=    			'<button data-bb-handler="confirm" type="button" style="margin-left:25px;" class="btn btn-primary '+funClass+'">确 认</button>';
	popDialogHtml +=    		'</div>';
	popDialogHtml +=    	'</div>';
	popDialogHtml +=    	'<div class="fade"></div>';
	popDialogHtml += '</div>';
	$('body').append(popDialogHtml);
	$('.popup_dialog').width(isWidth).height(isHeight);
	tableList.app.popup();
	tableList.app.dialogShow();
};

//html写在脚本里
tableList.app.contentInJs = function()
{
	var html = '<input type="text" class="length_1"  placeholder="输入用户名">';
	return html;
};

var disX=0;
var disY=0;

tableList.app.movePopup = function(event,obj)
{
	$('input').mousemove(function(event){
		event.stopPropagation();
	});
	//$('body').css('overflow-x','hidden')
	disX = event.clientX - obj.offset().left;
	disY = event.clientY - obj.offset().top;
	obj.css(
	{
		'margin':0,
		'left':event.clientX - disX,
		'top':event.clientY - disY
	});
	
	$(document).mousemove(function(event)
	{
		var l = event.clientX - disX;
		var t = event.clientY - disY;
		if(l<0)
		{
			//l=0;
		}
		else if(l>document.documentElement.clientWidth - obj.offset().width)
		{
			//l=document.documentElement.clientWidth - parents('.popup').offset().width;
		}
		
		if(t<0)
		{
			t=0;
		}
		else if(t>document.documentElement.clientHeight - obj.offset().height)
		{
			//t=document.documentElement.clientHeight - obj.parents('.popup').offset().height;
		}
		obj.css(
		{
			'left':l,
			'top':t
		});
	});
	$(document).mouseup(function()
	{
		$(document).unbind("mousemove");
		$(document).unbind("mouseup");
		//document.onmousemove=null;
		//document.onmouseup=null;
	});
	return false;
}

/**
 * 定义平台全局树
 */
var tree = {};

var app = {};

/**
 * 关闭
 */
app.close = function(clazz){
	$('body').delegate('.'+clazz,'click',function(){
		$('.popup').animate({
			opacity:0
		},200,
		function(){
			$('.popup').remove();
		});
	});
};

app.confirmclose = function(clazz){
	$('body').delegate('.'+clazz,'click',function(){
		$('.popup_confirm_close').animate({
			opacity:0
		},200,
		function(){
			$('.popup_confirm_close').remove();
		});
	});
};

/**
 * 
 * @param title  :confirm's title
 * @param content:confirm's content
 * @param href    :<a href='...'>...</a>
 */
app.confirm = function(title,content,href){
	tableList.app.popConfirm(title,content,'confirm_win');
	var conf = false;
	$('.confirm_win').click(function(){
		if(href!=null && href!="" && href!=undefined){
			location.href = href;
		}
		app.close('confirm_win');
		return true;
	});
	return conf;
};

/**
 * 组织机构树
 * */
tree.org = {};
tree.org.orgId;
tree.org.orgName;

/**
 * hideId:操作部门的ID域的id值
 * showId:操作部门的名称域的id值
 */
tree.org.show = function(ctxpath,hideId,showId){
	//初始化数据
	tree.org.orgId = $('#'+hideId).val();
	tree.org.orgName = $('#'+showId).val();
	//定义树内容
	var orgTreeHTML = "<div class=\"content_wrap\">"+
					  "		<div>"+
					  "			<ul id=\"orgTree\" class=\"ztree\"></ul>"+
					  "		</div>"+
					  "</div>";
	tableList.app.popDialog('组织机构树',orgTreeHTML,'340','270','org_tree');
	//
	var orgUrl = ctxpath+"/sys/coretree/allorg";
	var zNodes = [];
	$.ajax({
		url:orgUrl,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			id:$('#'+hideId).val()
		},
		success:function(result){
			tree.org.load(result,$('#'+hideId).val());
		}
	});
	$('.org_tree').click(function(){
		var ztree = $.fn.zTree.getZTreeObj("orgTree");
		//如果选择把值带回页面，如果取消选择值为空
		var nodes = ztree.getCheckedNodes(true);
		if(nodes.length == 0){
			$('#'+hideId).val("");
			$('#'+showId).val("");
		}else{
			$('#'+hideId).val(tree.org.orgId);
			$('#'+showId).val(tree.org.orgName);
		}
		app.close('org_tree');
	});
}

tree.org.load = function(zNodes,id){
	var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "level"
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onCheck: function(e, treeId, treeNode){
				tree.org.orgId = treeNode.id;
				tree.org.orgName = treeNode.name;
			}
		}
	};
	
	var code;
	
	function setCheck() {
		var type = "all";
		setting.check.radioType = type;
		showCode('setting.check.radioType = "' + type + '";');
		$.fn.zTree.init($("#orgTree"), setting, zNodes);
		if(id!=0 && id!=null && id != undefined){
			var treeObj = $.fn.zTree.getZTreeObj("orgTree");
			var node = treeObj.getNodeByParam("id", id);
			treeObj.selectNode(node,false);
			treeObj.checkNode(node,true,true);
		}
	}
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
	
	$(document).ready(function(){
		setCheck();
		$("#level").bind("change", setCheck);
		$("#all").bind("change", setCheck);
	});
}

/**
 * 机构人员树
 * */
tree.user = {};
tree.user.userId;
tree.user.userName;

/**
 * hideId:操作部门的ID域的id值
 * showId:操作部门的名称域的id值
 */
tree.user.show = function(ctxpath,
						  hideId,
						  showId,
						  checkType,//选择类型(radio/checkbox)
						  orgId,//当前部门人员指定
						  checkFieldId,//关联必填属性 (false)
						  checkFieldProm//关联必填属性提示(false)
							){
	//初始化数据
	tree.user.userId = $('#'+hideId).val();
	tree.user.userName = $('#'+showId).val();
	//定义树内容
	/*关联属性验证*/
	var checkFieldVal;
	if(checkFieldId!='' && checkFieldId!=null && checkFieldId!=undefined){
		checkFieldVal = $('#'+checkFieldId).val();
		if(checkFieldVal=='' || checkFieldVal==0 || checkFieldVal==null){
			tableList.app.popAlert('提示:',checkFieldProm);
			return;
		}
	}
	
	var userTreeHTML = "<div class=\"content_wrap\">"+
	"		<div>"+
	"			<ul id=\"userTree\" class=\"ztree\"></ul>"+
	"		</div>"+
	"</div>";
	tableList.app.popDialog('组织机构人员树',userTreeHTML,'340','270','user_tree');
	//
	var userUrl = ctxpath+"/sys/coretree/userbyorg";
	var zNodes = [];

	if(orgId==null || orgId=='' || orgId==undefined){ 
		orgId = 1;
		if(checkFieldVal){
			orgId = checkFieldVal;
		}
	}
	$.ajax({
		url:userUrl,
		type:'post',
		async: false,
		dataType:'json',
		data:{
			id:orgId
		},
		success:function(result){
			tree.user.load(result,$('#'+hideId).val(),checkType);
		}
	});
	$('.user_tree').click(function(){
		$('#'+hideId).val(tree.user.userId);
		$('#'+showId).val(tree.user.userName);
		app.close('user_tree');
	});
}

tree.user.load = function(zNodes,id,checkType){
	var check;
	
	if(checkType=='radio'){
		check = {enable: true,chkStyle: "radio",radioType: "level"};
	}else{
		check = {enable: true};
	}
	
	var setting = {
			check: check,
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: function(e, treeId, treeNode){
					tree.user.userId = '';
					tree.user.userName = '';
					var treeObj = $.fn.zTree.getZTreeObj("userTree");
					var ns = treeObj.getCheckedNodes(true);
					for(var i=0;i<ns.length;i++){
						var n  = ns[i];
						if(n.id){
							tree.user.userId +=n.id+',';
							tree.user.userName +=n.name+',';
						}
					}
					if(tree.user.userId.length>1){
						tree.user.userId = tree.user.userId.substring(0,tree.user.userId.length - 1);
						tree.user.userName = tree.user.userName.substring(0,tree.user.userName.length - 1);
					}
				}
			}
	};
	
	var code;
	
	function setCheck() {
		var treeObj = $.fn.zTree.getZTreeObj("userTree");
		if(checkType=='radio'){
			var nodes = treeObj.getNodesByParam("id", null);
			var len = nodes.length;
			for(var i=0;i<len;i++){
				var node = nodes[i];
				node.nocheck = true;
				treeObj.updateNode(node);
			}
			var type = "all";
			treeObj.setting.check.radioType = type;
			showCode('setting.check.radioType = "' + type + '";');
			if(id!=0 && id!=null && id != undefined){
				var node = treeObj.getNodeByParam("id", id);
				treeObj.selectNode(node,false);
				treeObj.checkNode(node,true,true);
				
			}
		}else{
			var py = "p",sy ="s",pn ="p",sn = "s",type = { "Y":py + sy, "N":pn + sn};
			treeObj.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
			if(id!=0 && id!=null && id != undefined){
				var ids = id.split(',');
				for(var i=0;i<ids.length;i++){
					var node = treeObj.getNodeByParam("id", ids[i]);
					treeObj.selectNode(node,false);
					treeObj.checkNode(node,true,true);
				}
			}
			
		}
	}
	
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
	
	$(document).ready(function(){
		$.fn.zTree.init($("#userTree"), setting, zNodes);
		setCheck();
	});
}
