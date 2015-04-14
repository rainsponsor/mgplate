package com.plate.frame.core.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plate.frame.base.BaseController;
import com.plate.frame.core.model.Dict;
import com.plate.frame.core.service.DictService;
import com.plate.frame.security.menutree.model.MenuTree;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService sysDictService;

	// log4j 日志
	private Log logger = LogFactory.getLog(DictController.class);

	@RequestMapping("/dictinfo")
	public String getList(Dict dict,@RequestParam(required = false) Integer id,Model model){
		model.addAttribute("id", id);
		bindAcl(this.getClass(),dict);//将此Controller绑定权限
		model.addAttribute("dict",dict);
		return "/sys/dict/dict_tree_content";
	}

	/**
	 * 获取所有子菜单
	 */
	@RequestMapping("/children")
	public String getChildren(MenuTree menu,Model model){
		
		return "/sys/org/dictForm";
	}
	
	/**
	 * <功能简述>节点创建页面
			* <功能详细描述>
			* @param id
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/content")
	public String content(Dict dict,@RequestParam(required = false) Integer id,Model model){
		bindAcl(this.getClass(),dict);//将此Controller绑定权限
		Dict dictTree = sysDictService.get(id);
		model.addAttribute("dict", dictTree);
		model.addAttribute("menu",dict);
		return "sys/dict/dict_content";
	}
	
	/**
	 * <功能简述>菜单管理 - 所有树形结构数据
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/tree")
	public String tree(){
		return "sys/dict/dict_tree_left";
	}
	
	/**
	 * <功能简述>添加子字典
			* <功能详细描述>跳转添加页
			* @param id
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/form")
	public String form(@RequestParam(required = false) Integer id,Model model){
			Dict dict = sysDictService.get(id);
			model.addAttribute("dict", dict);
		return "/sys/dict/dictForm";
	}
	
	/**
	 * <功能简述>保存/修改字典
			* <功能详细描述>
			* @param dict
			* @param model
			* @param redirectAttributes
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("/save")
	public String save(Dict dict,Model model,RedirectAttributes redirectAttributes){
		bindAcl(this.getClass(),dict);//将此Controller绑定权限
		if(!beanValidator(model, dict)){
			return form(dict.getParent_id(),model);
		}
		try {
			sysDictService.save(dict);
			addMessage(redirectAttributes, "保存字典'" + dict.getDict_name() + "'成功");
		} catch (Exception e) {
			logger.info("添加信息出错---->"+e.getStackTrace());
		}
		return String.valueOf(dict.getParent_id());
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
	public String update(Dict dict,Model model,RedirectAttributes redirectAttributes){
		bindAcl(this.getClass(),dict);//将此Controller绑定权限
		if(!beanValidator(model, dict)){
			return form(dict.getParent_id(),model);
		}
		try {
			sysDictService.update(dict);
			addMessage(redirectAttributes, "保存菜单'" + dict.getDict_name() + "'成功");
		} catch (Exception e) {
			logger.info("修改信息出错---->"+e.getStackTrace());
		}
		return String.valueOf(dict.getId());
	}
	
	/**
	 * 删除字典
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(Integer id){
		List<Dict> children = sysDictService.getDictsByParentId(id);
		Integer parentId = -1;
		if(children==null || children.size()==0){
			try {
				parentId = sysDictService.delete(id);
			} catch (Exception e) {
				logger.info("删除字典出错---->"+e.getStackTrace());
			}
			return String.valueOf(parentId);
		}else{//包含子菜单，不许删除
			return String.valueOf(-1);
		}
	}
}
