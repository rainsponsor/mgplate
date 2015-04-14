package com.plate.frame.core.controller;

import java.util.Map;

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

import com.google.common.collect.Maps;
import com.plate.frame.base.BaseController;
import com.plate.frame.core.model.Role;
import com.plate.frame.core.model.User;
import com.plate.frame.core.service.RoleService;
import com.plate.frame.security.util.SecurityUtils;
import com.plate.frame.security.util.UserUtils;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService sysRoleService;
	
	// log4j 日志
	private Log logger = LogFactory.getLog(UserController.class);

	/**
	 * <功能简述> 根据ID获取对象 <功能详细描述> 根据系统角色id获取Role对象
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ModelAttribute("role")
	public Role get(@RequestParam(required = false) Integer id) {
		Role role = null;
		if (id != null && id != 0) {
			role = sysRoleService.get(id);
		} else {
			role = new Role();
		}
		bindAcl(this.getClass(),role);//将此Controller绑定权限
		return role;
	}

	/**
	 * 系统角色列表
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rolelist")
	public String userList(Role role, Model model) {
		bindAcl(this.getClass(),role);//将此Controller绑定权限
		PageMyBatis<Role> roleList = sysRoleService.getListByPage(role);
		model.addAttribute("roleList", roleList);
		model.addAttribute("role", role);
		return "/sys/role/roleList";
	}

	/**
	 * 跳转到添加系统角色页面
	 * 
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form")
	public String userForm(Role role, Model model) {
		return "/sys/role/roleForm";
	}

	/**
	 * 添加系统角色
	 * 
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	public String save(Role role, Model model) {
		Integer id = 0;
		User user = (User)UserUtils.getCache(UserUtils.CACHE_USER);
		try {
			role.setCreator(user.getId());
			Integer count = sysRoleService.save(role);
			if(count > 0){
				id = role.getId();
			}
		} catch (Exception e) {
			logger.info("保存系统角色失败-->" + e.getStackTrace());
		}
		return "redirect:/sys/role/toupdate?id="+id;
	}

	/**
	 * <功能简述>停用系统角色 <功能详细描述>改变用户状态
	 * 
	 * @param uid
	 * @param user
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/{uid}/delete")
	public String delete(@PathVariable Integer uid, Role role, Model model) {
		try {
			sysRoleService.delete(uid);
		} catch (Exception e) {
			logger.info("删除系统角色失败-->" + e.getStackTrace());
		}
		return "redirect:/sys/role/rolelist";
	}

	/**
	 * <功能简述>恢复用户 <功能详细描述>恢复
	 * 
	 * @param uid
	 * @param role
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/{uid}/recovery")
	public String recovery(@PathVariable Integer uid, Role role, Model model) {
		try {
			sysRoleService.recovery(uid);
		} catch (Exception e) {
			logger.info("恢复系统角色失败-->" + e.getStackTrace());
		}
		return "redirect:/sys/role/rolelist";
	}

	/**
	 * <功能简述>跳转用户修改页 <功能详细描述>根据id获取系统角色信息
	 * 
	 * @param uid
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/toupdate")
	public String toUpdate(Model model) {
		return "/sys/role/roleUpdate";
	}

	/**
	 * <功能简述>系统角色修改 
	 * <功能详细描述>修改用户并保存
	 * @param user
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/update")
	public String update(Role role, Model model) {
		try {
			sysRoleService.update(role);
		} catch (Exception e) {
			logger.info("修改系统角色失败-->" + e.getStackTrace());
		}
		return "redirect:/sys/role/rolelist";
	}

	/**
	 * <功能简述>系统角色详细查看 <功能详细描述>获取系统角色详细
	 * 
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/detail")
	public String detail(Model model) {
		return "/sys/role/roleInfo";
	}
	
	
	/**
	 * <功能简述>物理删除系统用户
			* <功能详细描述>根据id获取系统用户 是否存在
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/{uid}/delrole")
	public String delRole(@PathVariable Integer uid,  Model model){
		try {
			sysRoleService.delRole(uid);
		} catch (Exception e) {
			logger.info("删除系统角色失败-->" + e.getStackTrace());
		}
		return "redirect:/sys/role/rolelist";
	}

	/**
	 * 系统授权列表
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/acllist")
	public String aclList(Role role, Model model) {
		PageMyBatis<Role> roleList = sysRoleService.getListByPage(role);
		model.addAttribute("roleList", roleList);
		model.addAttribute("role", role);
		return "/sys/acl/aclList";
	}
	

	/**
	 * 系统授权列表
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rolegrant")
	public String roleGrant(@RequestParam(required = true) Integer id, Model model) {
		model.addAttribute("roleId", id);
		//查询当前菜单没有的菜单以及菜单下的功能资源
		return "sys/acl/aclForm";
	}
	
	@RequestMapping(value = "/acl")
	@ResponseBody
	public Map<String,Object> aclGrantRoleInfo(@RequestParam(required = true) Integer id) {
		//角色具有的权限
		Map<String,Object> hashPermission = Maps.newHashMap();
		//角色不具有的权限
		Map<String,Object> hashNonePermission = Maps.newHashMap();
		//过滤结果
		Map<String,Object> resultMap = Maps.newHashMap();
		resultMap.put("all", SecurityUtils.getAcl(id, hashPermission, hashNonePermission));
		resultMap.put("have", hashPermission);
		return resultMap;
	}
	
	@RequestMapping(value = "/savegrant")
	public String saveGrant(HttpServletRequest request) {
		String roleId = request.getParameter("id");
		String aclIds = request.getParameter("role_acl_ids");
		String aclTypes = request.getParameter("role_acl_types");
		sysRoleService.saveGrant(roleId,aclIds,aclTypes);
		return "redirect:/sys/role/acllist";
	}
	
	@RequestMapping(value="/isexist")
	@ResponseBody
	public boolean isExist(HttpServletRequest request){
		String roleName = request.getParameter("roleName");
		String roleId = request.getParameter("roleId");
		Integer id  = sysRoleService.isExist(roleName);
		boolean bol = false;
		if(null == id || id ==0 || Integer.valueOf(roleId) == id){
			bol = true;
		}
		return bol;
	}
}
