package com.plate.frame.security.menutree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.frame.base.BaseService;
import com.plate.frame.security.menutree.model.MenuTree;
import com.plate.frame.security.menutree.persistence.MenuTreeMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
public class MenuTreeService extends BaseService {
	
	@Autowired
	private MenuTreeMapper mapper;
	
	/**
	 * 保存菜单基本信息
	 * @param model
	 */
	public void save(MenuTree model) {
		model.setMenu_path(getParentIds(model.getParent_id()));
		mapper.save(model);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_MENU);
	}
	
	private String getParentIds(Integer parentId){
		if(parentId==null || parentId == 0 ){
			return "0,";
		}else{
			return mapper.getMenuTreeById(parentId).getMenu_path() + parentId+",";
		}
	}
	
	/**
	 * 修改菜单基本信息
	 * @param model
	 */
	public void update(MenuTree model) {
		model.setMenu_path(getParentIds(model.getParent_id()));
		mapper.update(model);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_MENU);
	}
	
	/**
	 * 删除指定菜单
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id) {
		Integer parentId = mapper.getMenuTreeById(id).getParent_id();
		mapper.delete(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_MENU);
		return parentId;
	}
	
	/**
	 * 获取所有子菜单
	 * @param id
	 * @return
	 */
	public List<MenuTree> getChildMenuTrees(Integer id){
		return mapper.getChildrenById(id);
	}
	
	/**
	 * 根据id获取菜单信息
	 * @param id
	 * @return
	 */
	public MenuTree get(Integer id) {
		MenuTree menutree = null;
		if(id==null || id == 0){
			menutree = new MenuTree();
			menutree.setId(0);
			menutree.setMenu_name("菜单管理");
			menutree.setIs_leaf(0);
			menutree.setChildren(mapper.getChildrenById(0));
		}else{
			menutree = mapper.get(id);
		}
		return menutree;
	}
	
	public Integer isExistFunc(Integer id){
		return mapper.isExistFunc(id);
	}
}
