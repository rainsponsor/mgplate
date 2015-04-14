package com.plate.frame.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plate.frame.base.BaseController;
import com.plate.frame.core.model.User;
import com.plate.frame.core.service.MemberService;
import com.plate.frame.security.util.UserUtils;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService service;

	// log4j 日志
	private Log logger = LogFactory.getLog(MemberController.class);

	/**
	 * <功能简述> 根据ID获取对象 <功能详细描述> 根据系统用户id获取User对象
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ModelAttribute("member")
	public User get(@RequestParam(required = false) Integer id) {
		if (id != null && id != 0) {
			return service.get(id);
		} else {
			return new User();
		}
	}
	
	
	
	/**
	 * 跳转到个人信息修改密码页面
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/password")
	public String userForm(Model model) {
		return "/sys/member/password";
	}
	
	/**
	 * <功能简述>验证密码
			* <功能详细描述>
			* @param user
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value= "/validatepwd")
	@ResponseBody
	public boolean validatePwd(HttpServletRequest request){
			boolean bol = false;
			User member = (User)UserUtils.getCache(UserUtils.CACHE_USER);
			String pwd = request.getParameter("password");
			member.setLogin_password(pwd);
			bol = service.validatePwd(member);
		return bol;
	}
	
	/**
	 * <功能简述>保存密码
			* <功能详细描述>
			* @param model
			* @param user
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value= "/updatepwd")
	public String updatePwd(User user,Model model){
		try {
			User member = (User)UserUtils.getCache(UserUtils.CACHE_USER);
			user.setId(member.getId());
			service.updatePwd(user);
		} catch (Exception e) {
			logger.info("修改密码失败!");
		}
		model.addAttribute("flag", 1);
		return "/sys/member/password";
	}
	
	/**
	 * <功能简述>个人信息
			* <功能详细描述>修改页面
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value= "/tomember")
	public String toMember(Model model){
		User user = (User)UserUtils.getCache(UserUtils.CACHE_USER);
		User member = service.get(user.getId());
		model.addAttribute("user", member);
		return "/sys/member/form";
	}
	
	/**
	 * <功能简述>保存修改信息
			* <功能详细描述>
			* @param user
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value="/form")
	public String form(User user, Model model){
		try {
			service.form(user);
		} catch (Exception e) {
			logger.info("修改个人信息失败!");
		}
		return "redirect:/sys/member/tomember";
	}
}
