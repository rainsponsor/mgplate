package com.plate.app.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-5
 * @描述:移动端上层控制类
 */
@Controller
@RequestMapping("/mobile/app")
public class AppController{
	
	public final static String APP_REQUEST_SUCCESS = "200";
	public final static String APP_REQUEST_ERROR = "0";
	
	@RequestMapping("/start")
	public String appStartPage(){
		//Test github
		return "app/appStartPage";
	}
	
	
}
