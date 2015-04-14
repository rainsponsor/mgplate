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
import com.plate.frame.core.model.Catalog;
import com.plate.frame.core.service.CatalogService;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/catalog")
public class CatalogController extends BaseController {

	@Autowired
	private CatalogService sysCatalogService;

	// log4j 日志
	private Log logger = LogFactory.getLog(CatalogController.class);

	@RequestMapping("/info")
	public String getList(@RequestParam(required = false) Integer id,Model model){
		model.addAttribute("id", id);
		return "/sys/catalog/catalog_tree_content";
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
	public String content(@RequestParam(required = false) Integer id,Model model){
		Catalog catalog = sysCatalogService.get(id);
		model.addAttribute("catalog", catalog);
		return "sys/catalog/catalog_content";
	}
	
	/**
	 * <功能简述>菜单管理 - 所有树形结构数据
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/tree")
	public String tree(){
		return "sys/catalog/catalog_tree_left";
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
		Catalog catalog = sysCatalogService.get(id);
		if(id == 0){
			catalog.setName("目录管理");
		}
		model.addAttribute("catalog", catalog);
		return "/sys/catalog/catalogForm";
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
	public String save(Catalog catalog,Model model,RedirectAttributes redirectAttributes){
		if(!beanValidator(model, catalog)){
			return form(catalog.getParent_id(),model);
		}
		try {
			sysCatalogService.save(catalog);
			addMessage(redirectAttributes, "保存目录'" + catalog.getName() + "'成功");
		} catch (Exception e) {
			logger.info("添加信息出错---->"+e.getStackTrace());
		}
		return String.valueOf(catalog.getParent_id());
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
	public String update(Catalog catalog,Model model,RedirectAttributes redirectAttributes){
		if(!beanValidator(model, catalog)){
			return form(catalog.getParent_id(),model);
		}
		try {
			sysCatalogService.update(catalog);
			addMessage(redirectAttributes, "修改数据字典信息'" + catalog.getName() + "'成功");
		} catch (Exception e) {
			logger.info("修改信息出错---->"+e.getStackTrace());
		}
		return String.valueOf(catalog.getId());
	}
	
	/**
	 * 删除字典
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(Integer id){
		List<Catalog> children = sysCatalogService.getCatalogsByParentId(id);
		Integer parentId = -1;
		if(children==null || children.size()==0){
			try {
				parentId = sysCatalogService.delete(id);
			} catch (Exception e) {
				logger.info("删除字典出错---->"+e.getStackTrace());
			}
			return String.valueOf(parentId);
		}else{//包含子目录，不许删除
			return String.valueOf(-1);
		}
	}
}
