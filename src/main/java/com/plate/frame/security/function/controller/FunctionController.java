package com.plate.frame.security.function.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plate.frame.base.BaseController;
import com.plate.frame.security.function.model.Function;
import com.plate.frame.security.function.service.FunctionService;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/func")
public class FunctionController extends BaseController {
	
	@Autowired
	private FunctionService sysFuncService;
	
	@RequestMapping("/save")
	public String save(Function func,Model model){
		sysFuncService.save(func);
		return "redirect:/sys/menutree/content?id="+func.getMenutree_id();
	}
	
	@RequestMapping("/delFunc")
	public String delete(@RequestParam (required = false) Integer id,@RequestParam (required = false) Integer menuId,Model model){
		sysFuncService.delete(id);
		return "redirect:/sys/menutree/content?id="+menuId;
	}
	
	/**
	 * <功能简述>获取功能信息bean
			* <功能详细描述>根据功能id获取功能信息--功能修改
			* @param func
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/funcinfo")
	@ResponseBody
	public Function funcInfo(Function func,Model model){
		Integer id =func.getId();
		if (id != null) {
			func = sysFuncService.getFuncInfo(func.getId());
		}else{
			return new Function();
		}
		return func;
	}
	
	/**
	 * <功能简述>修改
			* <功能详细描述>保存修改内容
			* @param func
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/update")
	public String update(Function func, Model model){
		sysFuncService.update(func);
		return "redirect:/sys/menutree/content?id="+func.getMenutree_id();
	}
	
}
