package com.plate.frame.security.menutree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plate.frame.base.BaseController;
import com.plate.frame.security.function.model.Function;
import com.plate.frame.security.menutree.model.MenuTree;
import com.plate.frame.security.menutree.service.MenuTreeService;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/menutree")
public class MenuTreeController extends BaseController {
	
	@Autowired
	private MenuTreeService sysMenuTreeService;
	
	/**
	 * 保存/修改部门信息
	 * @param org
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save")
	public String save(MenuTree menu,Model model,RedirectAttributes redirectAttributes){
		bindAcl(this.getClass(),menu);//将此Controller绑定权限
		if(!beanValidator(model, menu)){
			return form(menu.getParent_id(),model);
		}
		sysMenuTreeService.save(menu);
		addMessage(redirectAttributes, "保存菜单'" + menu.getMenu_name() + "'成功");
		return String.valueOf(menu.getParent_id());
	}
	
	/**
	 * 保存/修改部门信息
	 * @param org
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String update(MenuTree menu,Model model,RedirectAttributes redirectAttributes){
		bindAcl(this.getClass(),menu);//将此Controller绑定权限
		if(!beanValidator(model, menu)){
			return form(menu.getParent_id(),model);
		}
		sysMenuTreeService.update(menu);
		addMessage(redirectAttributes, "保存菜单'" + menu.getMenu_name() + "'成功");
		return String.valueOf(menu.getId());
	}
	
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(Integer id){
		List<MenuTree> children = sysMenuTreeService.getChildMenuTrees(id);
		if(children==null || children.size()==0){
			Integer result = sysMenuTreeService.isExistFunc(id);
			if(result>0){
				return String.valueOf(-2);
			}
			Integer parentId = sysMenuTreeService.delete(id);
			return String.valueOf(parentId);
		}else{//包含子菜单，不许删除
			return String.valueOf(-1);
		}
	}
	
	@RequestMapping("/form")
	public String form(@RequestParam(required = false) Integer id,Model model){
		MenuTree menutree = sysMenuTreeService.get(id);
		model.addAttribute("menu", menutree);
		return "/sys/menu/menuForm";
	}
	
	/**
	 * 获取所有子菜单
	 */
	@RequestMapping("/children")
	public String getChildren(MenuTree menu,Model model){
		
		return "/sys/org/menuForm";
	}
	
	@RequestMapping("/menuinfo")
	public String getList(MenuTree menu ,@RequestParam(required = false) Integer id,Model model){
		bindAcl(this.getClass(),menu);//将此Controller绑定权限
		model.addAttribute("id", id);
		return "/sys/menu/menu_tree_content";
	}
	
	/**
	 * 菜单管理 - 所有树形结构数据
	 * @return
	 */
	@RequestMapping("/tree")
	public String tree(){
		return "sys/menu/menu_tree_left";
	}
	
	/**
	 * 节点创建页面
	 * @return
	 */
	@RequestMapping("/content")
	public String content(MenuTree menuModel, @RequestParam(required = false) Integer id,Model model){
		bindAcl(this.getClass(),menuModel);//将此Controller绑定权限
		MenuTree menutree = sysMenuTreeService.get(id);
		model.addAttribute("menu", menutree);
		model.addAttribute("menuModel", menuModel);
		return "sys/menu/menu_content";
	}
	
}
