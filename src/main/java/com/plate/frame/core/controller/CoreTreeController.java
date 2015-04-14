package com.plate.frame.core.controller;

import java.util.List;
import java.util.Map;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plate.frame.base.BaseController;
import com.plate.frame.core.util.CatalogUtil;
import com.plate.frame.core.util.DictUtil;
import com.plate.frame.core.util.SysCoreUtil;
import com.plate.frame.security.util.SecurityUtils;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：核心机构树控制类
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/coretree")
public class CoreTreeController extends BaseController {
	
	/**
	 * 获取组织机构信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/allorg")
	public List<Map<String,Object>> findAllOrgs(Integer id){
		List<Map<String,Object>> list = SysCoreUtil.getAllOrgsTreeList();
		return list;
	}
	
	/**
	 * 获取完整菜单树
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/allmenu")
	public List<Map<String,Object>> findAllMenuTreess(){
		List<Map<String,Object>> list = SecurityUtils.getAllMenuTrees();
		return list;
	}
	
	/**
	 * 获取部门下所有工作人员信息
	 * @param id : 指定部门
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userbyorg")
	public List<Map<String,Object>> findAllUsersByOrg(Integer id){
		List<Map<String,Object>> list = SysCoreUtil.getAllUsersTreeList(id);
		return list;
	}
	
	/**
	 * <功能简述>获取数据字典
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping(value="/alldict")
	public List<Map<String,Object>> findAllDictTreess(){
		List<Map<String,Object>> list = DictUtil.getAllDictTrees();
		return list;
	}
	
	/**
	 * <功能简述>获取所有目录信息
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping(value="/allcatalog")
	public List<Map<String,Object>> findAllCatalogTreess(){
		List<Map<String,Object>> list = CatalogUtil.getAllCatalogTrees();
		return list;
	}
	
	/**
	 * <功能简述>获取所有目录信息
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping(value="/catalogbyid")
	public List<Map<String,Object>> findAllCatalogTreess(@RequestParam(required = true) Integer id){
		List<Map<String,Object>> list = CatalogUtil.getCatalogTreeById(id);
		return list;
	}
	
}
