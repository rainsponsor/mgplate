package com.plate.frame.core.util;

import java.util.List;
import java.util.Map;

import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.common.spring.SpringContextHolder;
import com.plate.frame.core.service.CoreTreeService;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-7
 * @描述: 核心系统缓存工具类
 */
@SuppressWarnings("unchecked")
public class SysCoreUtil {
	
	private static CoreTreeService coreService = SpringContextHolder.getBean(CoreTreeService.class);
	
	/**
	 * 获取系统组织结构信息
	 * @return
	 */
	public static List<Map<String,Object>> getAllOrgsTreeList(){
		List<Map<String,Object>> treeList = (List<Map<String, Object>>) CacheUtils.get(CacheContant.SYS_CACHE_TREE_ORGS);
		if(treeList==null || treeList.size() == 0){
			treeList = coreService.findAllOrgs();
			CacheUtils.put(CacheContant.SYS_CACHE_TREE_ORGS, treeList);
		}
		return treeList;
	}
	
	/**
	 * 获取系统组织结构中所有人员信息
	 * @return
	 */
	public static List<Map<String,Object>> getAllUsersTreeList(Integer id){
		List<Map<String,Object>> treeList = null;
		if(id==null || id==1){//所有组织机构及其人员
			treeList = (List<Map<String, Object>>) CacheUtils.get(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
			if(treeList==null || treeList.size() == 0){
				treeList = coreService.findAllUserInOrg(id);
				CacheUtils.put(CacheContant.SYS_CACHE_TREE_ORGS_USERS, treeList);
			}
		}else{//指定id的部门及所有子部门人员
			treeList = coreService.findAllUserInOrg(id);
		}
		return treeList;
	}
	
}
