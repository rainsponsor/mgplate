package com.plate.frame.core.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plate.frame.base.BaseController;
import com.plate.frame.core.model.Organization;
import com.plate.frame.core.service.OrganizationService;
import com.plate.frame.security.util.ControllerHashUtil;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：组织机构控制器
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/org")
public class OrganizationController extends BaseController {
	
	@Autowired
	private OrganizationService sysOrgService;
	private Log logger =  LogFactory.getLog(OrganizationController.class);
	
	@ModelAttribute("org")
	public Organization get(@RequestParam(required = false) Integer id) {
		Organization organization = null;
		if (id !=null && id != 0) {
			organization = sysOrgService.get(id);
		} else {
			organization = new Organization();
		}
		bindAcl(this.getClass(),organization);
		return organization;
	}
	
	@RequestMapping("/list")
	public String getList(Organization org,Model model){
		bindAcl(this.getClass(),org);//将此Controller绑定权限
		PageMyBatis<Organization> orgList = sysOrgService.getListByPage(org);
		model.addAttribute("orgList", orgList);
		model.addAttribute("org",org);
		return "/sys/org/orgList";
	}
	
	/**
	 * 保存/修改部门信息
	 * @param org
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Organization org,Model model,RedirectAttributes redirectAttributes){
		if(!beanValidator(model, org)){
			return form(org, model);
		}
		if(beanIndentityValidator(org)){
			sysOrgService.update(org);
		}else{
			sysOrgService.save(org);
		}
		addMessage(redirectAttributes, "保存部门'" + org.getOrg_name() + "'成功");
		return "redirect:/sys/org/list";
	}
	
	/**
	 * <功能简述>根据部门id删除部门
			* <功能详细描述> 根据部门id判断是否有子部门，如果没有删除
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Integer id){
		List<Organization> orgList = sysOrgService.getOrgList(id);
		Integer parentId = -1;
		if(orgList==null || orgList.size()==0){
			try {
				parentId = sysOrgService.delete(id);
			} catch (Exception e) {
				logger.info("删除部门信息出错---->"+e.getStackTrace());
			}
			return String.valueOf(parentId);
		}else{//包含子菜单，不许删除
			return String.valueOf(-1);
		}
	}
	
	@RequestMapping("/form")
	public String form(Organization org,Model model){
		return "/sys/org/orgForm";
	}
	
	@RequestMapping("/info")
	public String info(Model model){
		return "/sys/org/orgInfo";
	}
	
}
