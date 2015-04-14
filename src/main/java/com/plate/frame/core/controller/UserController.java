package com.plate.frame.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plate.frame.base.BaseController;
import com.plate.frame.core.model.User;
import com.plate.frame.core.service.UserService;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/user")
public class UserController extends BaseController {

	@Autowired
	private UserService sysUserService;

	// log4j 日志
	private Log logger = LogFactory.getLog(UserController.class);

	/**
	 * <功能简述> 根据ID获取对象 <功能详细描述> 根据系统用户id获取User对象
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ModelAttribute("user")
	public User get(@RequestParam(required = false) Integer id) {
		User user = null;
		if (id != null && id != 0) {
			user = sysUserService.get(id);
		} else {
			user = new User();
		}
		bindAcl(this.getClass(),user);//将此Controller绑定权限
		return user;
	}

	/**
	 * 系统用户列表
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userlist")
	public String userList(User user, Model model) {
		bindAcl(this.getClass(),user);//将此Controller绑定权限
		PageMyBatis<User> userList = sysUserService.getListByPage(user);
		model.addAttribute("userList", userList);
		model.addAttribute("user", user);
		return "/sys/user/userList";
	}

	/**
	 * 跳转到添加系统用户页面
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form")
	public String userForm(User user, Model model) {
		return "/sys/user/userForm";
	}

	/**
	 * 添加系统用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	public String save(User user, Model model) {
		try {
			sysUserService.save(user);
		} catch (Exception e) {
			logger.info("保存系统用户失败-->"+e.getStackTrace());
		}
		return "redirect:/sys/user/userlist";
	}

	/**
	 * <功能简述>停用系统用户 <功能详细描述>改变用户状态
	 * 
	 * @param uid
	 * @param user
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/{uid}/delete")
	public String delete(@PathVariable Integer uid, User user, Model model) {
		try {
			sysUserService.delete(uid);
		} catch (Exception e) {
			logger.info("停用系统用户失败-->"+e.getStackTrace());
		}
		return "redirect:/sys/user/userlist";
	}

	/**
	 * <功能简述>删除系统用户 <功能详细描述>物理删除
	 * 
	 * @param uid
	 * @param user
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/{uid}/deluser")
	public String delUser(@PathVariable Integer uid, User user, Model model) {
		try {
			sysUserService.delUser(uid);
		} catch (Exception e) {
			logger.info("删除系统用户失败-->"+e.getStackTrace());
		}
		return "redirect:/sys/user/userlist";
	}
	
	/**
	 * <功能简述>密码重置
			* <功能详细描述>默认是12345
			* @param uid
			* @param user
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/{uid}/reset")
	public String reset(@PathVariable Integer uid, User user, Model model) {
		try {
			sysUserService.reset(uid);
		} catch (Exception e) {
			logger.info("重置密码失败-->"+e.getStackTrace());
		}
		return "redirect:/sys/user/userlist";
		
	}
	/**
	 * <功能简述>恢复用户 <功能详细描述>恢复
	 * 
	 * @param uid
	 * @param user
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/{uid}/recovery")
	public String recovery(@PathVariable Integer uid, User user, Model model) {
		try {
			sysUserService.recovery(uid);
		} catch (Exception e) {
			logger.info("恢复系统用户失败-->"+e.getStackTrace());
		}
		return "redirect:/sys/user/userlist";
	}

	/**
	 * <功能简述>跳转用户修改页
			* <功能详细描述>根据id获取系统用户信息
			* @param uid
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/toupdate")
	public String toUpdate(Model model) {
		return "/sys/user/userUpdate";
	}

	/**
	 * <功能简述>系统用户修改
			* <功能详细描述>修改用户并保存
			* @param user
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	 @RequestMapping("/update")
	 public String update(User user,Model model){
		 try {
			 sysUserService.update(user);
		 } catch (Exception e) {
				logger.info("修改系统用户失败-->"+e.getStackTrace());
			}
		 return "redirect:/sys/user/userlist";
	 }
	
	 /**
	  * <功能简述>系统用户详细查看
	 		* <功能详细描述>获取系统用户详细
	 		* @param model
	 		* @return
	 		* @see [类、类#方法、类#成员]
	  */
	 @RequestMapping("/detail")
	 public String detail(Model model){
	 return "/sys/user/userInfo";
	 }

	 /**
	  * <功能简述>登陆名是否重复
	 		* <功能详细描述>null 不重复
	 		* @param request
	 		* @return
	 		* @see [类、类#方法、类#成员]
	  */
	 @RequestMapping("/isRepeat")
	 @ResponseBody
	 public boolean isRepeat(HttpServletRequest request){
		 String loginName = request.getParameter("loginName");
		 boolean bol = false;
		 User user = sysUserService.getUserByLoginName(loginName);
		 if(user != null){
			 bol = true;
		 }
		 return bol;
	 }
}
