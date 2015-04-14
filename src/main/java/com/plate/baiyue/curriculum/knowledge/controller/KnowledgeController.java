package com.plate.baiyue.curriculum.knowledge.controller;

import javax.servlet.http.HttpServletRequest;

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

import com.plate.baiyue.common.BaiYueSQLiteUtil;
import com.plate.baiyue.curriculum.knowledge.model.Knowledge;
import com.plate.baiyue.curriculum.knowledge.service.KnowledgeService;
import com.plate.frame.base.BaseController;
import com.plate.frame.core.model.Catalog;
import com.plate.frame.core.service.CatalogService;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-27
 * @描述:
 */
@Controller
@Scope("prototype")
@RequestMapping("/curr/know")
public class KnowledgeController extends BaseController {
	
	@Autowired
	private KnowledgeService service;
	@Autowired
	private CatalogService sysCatalogService;
	
	private Log logger =  LogFactory.getLog(KnowledgeController.class);
	
	/**
	 * 根据知识点id获取知识点内容，如果为非叶子节点，则列出知识点下的所有子知识点
	 * @param id	目录id
	 * @param model
	 * @return
	 */
	@RequestMapping("/info")
	public String getList(@RequestParam(required = true) Integer id,Model model){
		model.addAttribute("id", id);
		return "/curriculum/knowledge/know_tree_content";
	}
	
	/**
	 * <功能简述>知识点创建页面
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
		return "/curriculum/knowledge/know_content";
	}
	
	/**
	 * <功能简述>菜单管理 - 所有树形结构数据
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/tree")
	public String tree(@RequestParam(required = false) Integer id,Model model){
		Catalog catalog = sysCatalogService.get(id);
		model.addAttribute("catalog", catalog);
		return "/curriculum/knowledge/know_tree_left";
	}
	
	/**
	 * <功能简述>添加知识点
			* <功能详细描述>跳转添加页
			* @param id
			* @param model
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/form")
	public String form(@RequestParam(required = false) Integer dirid,Model model,HttpServletRequest request){
		Knowledge knowledge = service.getByDirId(dirid);
		String dirIds = knowledge.getDir_ids();
		if(dirIds == null){
			Catalog catalog = sysCatalogService.get(dirid);
			dirIds = String.valueOf(catalog.getDir_ids());
		}
		dirIds = dirIds.substring(dirIds.indexOf(",")+1);
		dirIds = dirIds.substring(0,dirIds.indexOf(","));
		model.addAttribute("knowledge", knowledge);
		model.addAttribute("dirIds", dirIds);
		return "/curriculum/knowledge/knowForm";
	}
	
	/**
	 * 获取目录对应的内容信息
	 * @param dirid 目录id
	 * @param model
	 * @return
	 */
	@RequestMapping("/dircontent")
	public String dirContent(@RequestParam(required = false) Integer dirid,Model model){
		//根据目录获取知识内容
		Knowledge knowledge = service.getByDirId(dirid);
		model.addAttribute("knowledge", knowledge);
		return "/curriculum/knowledge/knowInfo";
	}
	
	
	/**
	 * <功能简述>保存/修改
			* <功能详细描述>
			* @param dict
			* @param model
			* @param redirectAttributes
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/save")
	public String save(Knowledge Knowledge,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes){
		if(!beanValidator(model, Knowledge)){
			return form(Knowledge.getDir_id(),model,request);
		}
		String content = request.getParameter("content");
		Knowledge.setContent(content);
		try {
			Integer id = Knowledge.getId();
			if(id==null || id==0){
				service.save(Knowledge);
			}else{
				service.update(Knowledge);
			}
			addMessage(redirectAttributes, "保存知识点成功");
		} catch (Exception e) {
			logger.info("添加知识点信息出错---->"+e.getStackTrace());
		}
		return "redirect:/curr/know/dircontent?dirid="+Knowledge.getDir_id();
	}
	
	@RequestMapping("/generatequestionbank")
	@ResponseBody
	public Integer generateQuestionBank(@RequestParam(required = false) Integer id,Model model){
		Integer result = 1;
		try {
			BaiYueSQLiteUtil.generateKnowledgeBank(id);
		} catch (Exception e) {
			result = 2;
			e.printStackTrace();
		}
		return result;
	}
	
}
