// JavaScript Document
$(document).ready(function(e) {
    
	//弹出层 .tanchu
	table.app.tanchu();
	
	//表格二级单元
	
	table.app.mySub();
	
	table.app.imageShow();
	
	//input框
	table.app.onBlur();
	
});

var table = {};

table.app = {};


//input框
table.app.onBlur = function()
{
	$('input').blur(function()
	{
		$(this).removeClass('input_on').addClass('input_off');
	}).focus(function()
	{
		$(this).removeClass('input_off').addClass('input_on');
	});
};


//添加图片imageshow.html
table.app.imageShow = function()
{
	var box = '<div class="image_box"><a class="image_box_fm">设为封面</a><a class="image_box_close" href="javascript:void(0)">删除</a><img src="images/banner_3.jpg"></div>';
	
	//$('.image_add').bind('click',function()
	$('.image_add').click(function()
	{
		$('.image_show').append(box);
	});
	$(".image_show").delegate(".image_box_close","click",function()
	{
		if($(this).parents('.image_box').find('.aaa').length)
		{
			alert(1);
		}
		else
		{
			alert(2);
			
		}
		
		$(this).parents('.image_box').remove();
	});
	$(".image_show").delegate(".image_box","mouseenter",function()
	{
		$(this).find('.image_box_fm').show();
	});
	$(".image_show").delegate(".image_box","mouseleave",function()
	{
		$(this).find('.image_box_fm').hide();
	});
	
};




table.app.tanchu = function()
{
	$('.mytable').click(function()
	{
		$('.tanchu').show();
		$('.tanchu').animate({opacity:'1'},'fast');
	});
	
	$('.tanchu_close').click(function()
	{
		$('.tanchu').animate({opacity:'0'},'fast',function()
		{
			$('.tanchu').hide();
		});
		
	});
}

table.app.mySub = function()
{
	var myshow = false;
	$('.my_sub').click(function()
	{
		var area = $(this).parents('tr').next('.subarea');
		if(!myshow)
		{
			$(this).html('-');
			area.show();
			myshow = true;
			
		}
		else
		{
			$(this).html('+');
			area.hide();
			myshow = false;
		}
	});
};