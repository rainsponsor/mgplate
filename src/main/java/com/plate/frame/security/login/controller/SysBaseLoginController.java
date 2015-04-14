package com.plate.frame.security.login.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plate.frame.base.BaseController;
import com.plate.frame.core.model.User;
import com.plate.frame.security.util.UserUtils;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
public class SysBaseLoginController extends BaseController {
	
	private Log logger = LogFactory.getLog(SysBaseLoginController.class);

	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model){
		logger.info("User login......");
		 	String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
	        String error = null;
	        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
	            error = "用户名或密码不正确";
	        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
	            error = "用户名或密码不正确";
	        } else if(exceptionClassName != null) {
	            error = "其他错误：" + exceptionClassName;
	        }
	        model.addAttribute("ERROR_LOGIN", error);
	        return "/commons/index";
	}
	
	/**
	 * 登录成功，进入管理首页
	 */
//	@RequiresUser
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		// 未登录，则跳转到登录页
		if(user.getId() == null){
			System.out.println("*************************");
			return "redirect:/login";
		}
		// 登录成功后，验证码计算器清零
		//isValidateCodeLogin(user.getLogin_name(), false, true);
		// 登录成功后，获取上次登录的当前站点ID
		//UserUtils.putCache("siteId", CookieUtils.getCookie(request, "siteId"));
		return "commons/main";
	}
	
	@RequiresRoles("admin")  
	public void hello() {  
	    //有权限  
		System.out.println("有权限");
	} 
	
	@RequestMapping("/top")
	public String top(){
		//TODO 顶部信息处理
		
		return "commons/top";
	}
	
	@RequestMapping("/tree")
	public String tree(){
		//TODO 树形结构处理
		
		return "commons/tree";
	}
	
	@RequestMapping("/getmenutree")
	@ResponseBody
	public List<Map<String,Object>> getMenuTree(){
		List<Map<String, Object>> list = com.plate.frame.security.util.SecurityUtils.getUserMenuPermission();
		return list;
	}
	
	@RequestMapping("/content")
	public String content(){
		//TODO 展示内容处理
		
		return "commons/content";
	}
	
}
