// JavaScript Document

//树滚动
var documentHeight;
var windowHeight;
var num = 0;
var maxHeight = $(window,parent.document).height();
$(document).ready(function(e) {
   $('.js_asd').focus(function()
   {
	   alert(1);
   });
   //右侧页面
   ty.app.myContent(); 
   
   //调整左侧树高度
	ty.app.chicun();
	
	//一级菜单
	ty.app.title();
	//ty.app.hideLeft();
	//二级菜单
    ty.app.secTree();
	
	//树滚动
	setScrollHeight();
	$('.tree_fir').click(function()
	{
		setTimeout(function(){setScrollHeight();},200);
	});
});

$(window).resize(function()
{
	ty.app.chicun();
	//树滚动
	setScrollHeight();
});

//树滚动
function setScrollHeight()
{
	documentHeight = $(document).height();
	windowHeight = $(window).height();
	maxHeight = documentHeight - windowHeight;
}
var wheel = function(event) 
{
	var delta = 0;
	if (!event) /* For IE. */
	{
		event = window.event;
	}
	
	if (event.wheelDelta)/* IE/Opera. */
	{
		delta = event.wheelDelta / 120;
	} 
	else if (event.detail)
	{
		delta = -event.detail / 3;
	}
	
	if (delta)
	{
		handle(delta);
	}
	if (event.preventDefault)
	{
		event.preventDefault();
	}
	event.returnValue = false;
}
		
if (window.addEventListener) {
	window.addEventListener('DOMMouseScroll', wheel, false);
}
		
//window.onmousewheel = document.onmousewheel = wheel;
window.onmousewheel = wheel;
		
var handle = function(delta) {
	var random_num = Math.floor((Math.random() * 100) + 50);
	
	if (delta < 0) {
		//$('#tree').scrollTop() += 60;
		num += 60;
		if(num > maxHeight)
		{
			num = maxHeight;
		}
		$(window).scrollTop(num);
		return;
	} else {
		num -= 60;
		if(num < 0)
		{
			num = 0;
		}
		$(window).scrollTop(num);
		return;
	}
}

//树滚动 end

var ty = {};

ty.ui = {};


ty.ui.listLi = function(main,box)
{
/*	$(".list_main li").hover(function()
	{
		$(this).addClass('active');
		$(this).find('.down_label').show();
		$(this).find('.push_red').animate({left:0});
	});
	$('.list_main li').mouseleave(function()
	{
		$(this).removeClass('active');
		$(this).find('.down_label').hide();
		$(this).find('.push_red').animate({left:-53});
	});*/
	
	//$('.list_main li').mouseover(function(e)
	$(main).delegate(box,"mouseover",function(e)
	{
		var iThis = $(this);
		var s = e.fromElement || e.relatedTarget ;
		if( document.all ){
			var e = window.event || ae ;
			if(  !(s == this || this.contains(s))  ){
				//$(this).addClass('active');
			}
		}else{
			var res= this.compareDocumentPosition(s) ;   
			if(  !(s == this || res == 20 || res == 0 )  ){
				//alert("FF: 你 in 了 ！");
				//$(this).addClass('active');
			}
		}
	});
	$(main).delegate(box,"mouseout",function(e)
	{
		var iThis = $(this);
		var s = e.toElement || e.relatedTarget;   
		//var temp = document.getElementById('but_temp');   
		if(document.all){   
			var e = window.event || ae;
			if( !this.contains(s) ){   
				//$(this).removeClass('active');
			}   
		}else{   
			var res= this.compareDocumentPosition(s) ;     
			if( ! ( res == 20 || res == 0) ){      
				//alert('FF: 你 out 了');
				//$(this).removeClass('active');

			}     
		}  
	});
}

ty.app = {};

ty.app.myContent = function()
{
	//console.log($('#fr1',window.parent.document).parents('body').find('#fr2').html());
	$('.tree_sub').click(function()
	{
		//console.log($(this).attr('area'));
		//$('#fr1',window.parent.document).parents('body').find('#fr3').css({'opacity':0}).animate({opacity:1},'slow');
		$('#fr1',window.parent.document).parents('body').find('#fr3').attr('src',$(this).attr('area'));
		for(var i=0;i<$('.tree_sub').length;i++)
		{
			$('.tree_sub').eq(i).removeClass('sub_active');
		}
		for(var i=0;i<$('.tree_fir').length;i++)
		{
			$('.tree_fir').eq(i).removeClass('active');
			$('.tree_fir').eq(i).parents('.tree_box_active').removeClass('tree_box_active');
			$('.tree_fir').eq(i).find('.has_sub').remove();
		}
		$(this).addClass('sub_active');
		$(this).parents('.tree_box').addClass('tree_box_active');
	});
}

//二级下拉
ty.app.secTree = function()
{
	$('.tree_box_sec').click(function()
	{
		$(this).find('.tree_sbox_sec').slideToggle('fast');
		//$(this).addClass('tree_show_sec');
	});
}

ty.app.chicun = function()
{
	//$('#tree').height($(window).height() - 20);
	$('#tree').css('min-height',$(window).height() - 0);
	
};

ty.app.title = function()
{
	$('.tree_fir').click(function()
	{
		var noSlide = $(this).parents('.tree_box').index();
		for(var i=0;i<$('.tree_sbox').length;i++)
		{
			//是不是当前点开的
			if($('.tree_box').eq(i).hasClass('tree_box_active'))
			{
				//是否展开
				if($('.tree_box').eq(i).find('.tree_sbox').css('display') == 'none')
				{
					//没展开点击删除角标
					$(this).find('.has_sub').remove();
				}
				else
				{
					//展开添加一个角标
					if($(this).find('.tree_fir .has_sub').length == 0)
					{
						var treeDir = $('.tree_box').eq(i).find('.tree_fir');
						setTimeout(function()
						{
							treeDir.append('<span class="has_sub"></span>');
						},160);
					}
				}
			}
			
			$('.tree_fir').eq(i).removeClass('active');
			if(i != noSlide)
			{
				$('.tree_sbox').eq(i).slideUp('fast');
			}
			else
			{
				$('.tree_sbox').eq(i).slideToggle('fast');
				
			}
		}
		$(this).addClass('active');
		
	});
/*	$('.tree_fir').mouseenter(function()
	{
		$(this).find('.tree_fir_bor').show();
	}).mouseleave(function()
	{
		$(this).find('.tree_fir_bor').hide();
	});
	$('.tree_fir i').mouseenter(function()
	{
		$(this).parent('.tree_fir').find('.tree_fir_bor').show();
	}).mouseleave(function()
	{
		$(this).parent('.tree_fir').find('.tree_fir_bor').hide();
	});*/
	ty.ui.listLi('#tree','.tree_fir');
}

