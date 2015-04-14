package com.plate.app.curriculum.knowledge.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plate.app.base.controller.AppController;
import com.plate.app.curriculum.knowledge.service.AppKnowService;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-30
 * @描述:
 */
@Controller
@Scope("prototype")
@RequestMapping("/mobile/know")
public class AppKnowController extends AppController {
	
	@Autowired
	private AppKnowService service;
	
	/**
	 * http://localhost:8080/plate/mobile/know/correct.app?knowledge_id=1&content=1&user_id=1
	 * 记录用户纠错信息
	 * @param id		知识点id
	 * @param content	纠正内容
	 * @param userid	用户id	如果userid为0则说明未登陆用户
	 * @return
	 * 
	 * {result_code:123,result_desc:'xxxxx'}
	 */
	@RequestMapping("/correct")
	@ResponseBody
	public String correct(
			@RequestParam("knowledge_id") Integer knowledge_id,
			@RequestParam("content")String content,
			@RequestParam("user_id")Integer user_id){
		try{
			//service.correct(knowledge_id, content, user_id);
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+content);
			
			return "{result:"+APP_REQUEST_SUCCESS+"}";//处理成功
		}catch(Exception e){
			e.printStackTrace();
			return "{result:"+APP_REQUEST_ERROR+"}";//处理失败
		}
	}
	
	public List<Map<String,Object>> getCorrect(@RequestParam("userId") Integer userId){
		
		return null;
	}
	
}
