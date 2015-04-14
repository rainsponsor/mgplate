// JavaScript Document
$(document).ready(function(e) {
    top.app.logout();
	top.app.menu();
});
var top = {};

top.app = {};

var logout1;
var logout2;
top.app.logout = function()
{
	$('.horizontal_user').mouseover(function()
	{
		clearTimeout(logout1);
		clearTimeout(logout2);
		$('#fr1',window.parent.document).parents('body').find('.js_logout').show();
	}).mouseleave(function()
	{
		logout1 = setTimeout(function()
		{
			$('#fr1',window.parent.document).parents('body').find('.js_logout').hide();
		},500);
	});
	$('#fr1',window.parent.document).parents('body').find('.js_logout').mouseover(function()
	{
		clearTimeout(logout1);
		clearTimeout(logout2);
		$(this).show();
	}).mouseout(function()
	{
		var iThisLogout = $(this);
		logout2 = setTimeout(function()
		{
			iThisLogout.hide();
		},500);
	});
}

//快捷方式
var menu1;
var menu2;
top.app.menu = function()
{
	$('.js_menu').click(function()
	{
		clearTimeout(menu1);
		clearTimeout(menu2);
		$('#fr1',window.parent.document).parents('body').find('.menu').show();
	}).mouseleave(function()
	{
		menu1 = setTimeout(function()
		{
			$('#fr1',window.parent.document).parents('body').find('.menu').hide();
		},500);
		//$('#fr1',window.parent.document).parents('body').find('.menu').hide();
	});
	$('#fr1',window.parent.document).parents('body').find('.menu').mouseenter(function()
	{
		clearTimeout(menu1);
		clearTimeout(menu2);
		$(this).show();
	}).mouseout(function()
	{
		var iThisMenu = $(this);
		menu2 = setTimeout(function()
		{
			iThisMenu.hide();
		},500);
	});
}