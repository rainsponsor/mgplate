// JavaScript Document

$(document).ready(function(e) {
    houtaiMain.app.chicun();
	houtaiMain.app.hideLeft();
	//houtaiMain.app.logout();
});

$(window).resize(function()
{
    houtaiMain.app.chicun();
});

var houtaiMain = {};

houtaiMain.app = {};

houtaiMain.app.chicun = function()
{
	//console.log($('#fr3').attr('width'));
	var headHeight = $('#header_wrap').height();
	$('.hidefr2').height($(window).height() - headHeight);
	//$('#fr3').attr('width',($(window).width() - ($('#fr2').outerWidth() + 10)));
	$('#fr3').width($(window).width() - ($('#main_nav').width() +10));
	$('#fr2').height($(window).height() - headHeight);
	$('#fr3').height($(window).height() - headHeight);
	
	//tree_content
	$('#fr_tree_content').height($(window).height());
	$('.tree_info_content').width($(window).width() - ($('#fr_tree_content').width()) - 6);
	$('.tree_info_content').height($(window).height() - headHeight);
	//树的右侧左右滑动线
	$('.width_line').height($(window).height());
};

houtaiMain.app.hideLeft = function()
{
	var myShow = true;
	$('.hidefr2').click(function()
	{
		if(myShow)
		{
			$('#main_nav').animate({width:0},function()
			{
				$(this).hide();
			});
			$('#fr3').animate({width:$(window).width() - 10});
			myShow = false;
			$(this).removeClass('hidden_btn').addClass('show_btn');
		}
		else
		{
			$('#main_nav').show();
			$('#main_nav').animate({width:'190px'});
			$('#fr3').animate({width:$(window).width() - 200});
			myShow = true;
			$(this).removeClass('show_btn').addClass('hidden_btn');
		}
		
	});
}

houtaiMain.app.logout = function()
{
	$('.js_logout').mouseenter(function()
	{
		$(this).show();
	}).mouseleave(function()
	{
		$(this).hide();
	});
}
function moveWidth(obj)
{
	$('#main_wrap').append('<div class="js_size_window" style="cursor:e-resize;position:fixed; top:0; left:0; right:0; bottom:0; z-index:1;"></div>');
	//$('.js_size_window').css('z-index',1);
	disX = obj.width() - event.clientX;

	$(document).mousemove(function(event)
	{
		$('#main_nav').width(event.clientX + disX + 'px');
		$('#fr_tree_content').width(event.clientX + disX - 6 + 'px');
		$('.tree_info_content').width($(window).width() - ($('#main_nav').width()));
		event.preventDefault();//取消默认行为，没有这句话的话，会跳转到baidu网站上 
	});
	
	$(document).mouseup(function()
	{
		$('.js_size_window').remove();
		$(document).unbind("mousemove");
		$(document).unbind("mouseup");
	});
	return false;
	
}
