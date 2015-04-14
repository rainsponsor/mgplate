package com.plate.frame.security.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.common.spring.SpringContextHolder;
import com.plate.frame.base.BaseModel;
import com.plate.frame.core.service.CoreTreeService;
import com.plate.frame.security.acl.model.Acl;
import com.plate.frame.security.acl.persistence.AclMapper;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-13
 * @描述:
 */
@SuppressWarnings("unchecked")
public class SecurityUtils {
	
	private static CoreTreeService coreService = SpringContextHolder.getBean(CoreTreeService.class);
	private static AclMapper aclMapper = SpringContextHolder.getBean(AclMapper.class);
	
	/**
	 * 角色资源Key
	 */
	public final static String GRANT_ROLE_ACL = "grantRole";
	
	public final static String GRANT_USER_ACL_MENU_FUNC = "grantUserMenuFunc";
	public final static String GRANT_USER_ACL_MENU = "grantUserMenu";
	public final static String GRANT_USER_ACL_FUNC = "grantUserFunc";
	
	/**
	 * 获取指定菜单id的所有直接子菜单
	 * @return
	 */
	public static List<Map<String,Object>> getMenuTrees(Integer menutreeId){
		List<Map<String,Object>> result = null;
		result = coreService.getMenuTreeByParent(menutreeId);
		return result;
	}
	
	/**
	 * 获取完整菜单树
	 * @return
	 */
	public static List<Map<String,Object>> getAllMenuTrees(){
		List<Map<String,Object>> result = (List<Map<String, Object>>) CacheUtils.get(CacheContant.SYS_CACHE_TREE_MENU);
		if(result == null || result.size() == 0){
			result = coreService.getAllMenuTrees();
			Map<String,Object> root = Maps.newHashMap();
			root.put("id", 0);
			root.put("name", "菜单管理");
			root.put("open", true);
			result.add(0, root);
			for(Map<String,Object> menu : result){
				menu.put("target", "tree_info_content");
				menu.put("url", SpringContextHolder.getContextPath()+"/sys/menutree/content?id="+menu.get("id"));
			}
			CacheUtils.put(CacheContant.SYS_CACHE_TREE_MENU, result);
		}
		return result;
	}
	
	/**
	 * 获取完整菜单树
	 * @return
	 */
	public static List<Map<String,Object>> getAdminMenuTrees(){
		List<Map<String,Object>> result = null;//(List<Map<String, Object>>) CacheUtils.get(CacheContant.SYS_CACHE_TREE_MENU);
		if(result == null || result.size() == 0){
			result = coreService.getAllMenuTrees();
			boolean bol = true;
			for(Map<String,Object> menu : result){
				menu.put("target", "tree_info_content");
				if(bol){
					menu.put("open", true);
					bol = false;
				}
				//menu.put("url", SpringContextHolder.getContextPath()+"/sys/menutree/content?id="+menu.get("id"));
			}
			//CacheUtils.put(CacheContant.SYS_CACHE_TREE_MENU, result);
		}
		return result;
	}
	
	/**
	 * 获取用户对应的权限菜单树
	 * @return
	 */
	public static List<Map<String,Object>> getUserPermission(){
		Integer id = UserUtils.getUser().getId();
		List<Map<String,Object>> result = null;//(List<Map<String, Object>>) CacheUtils.get(GRANT_USER_ACL_MENU_FUNC+id);
		List<Map<String,Object>> menuList = Lists.newArrayList();
		List<Map<String,Object>> funcList = Lists.newArrayList();
		if(UserUtils.getUser().getLogin_name().equals("admin")){
			result = getAdminMenuTrees();
			for(Map<String,Object> acl : result){
				if("1".equals(acl.get("leaf").toString())){
					acl.put("target", "fr3");
					String url = acl.get("url").toString();
					if(!url.contains("?")){
						acl.put("url", SpringContextHolder.getContextPath()+acl.get("url").toString()+"?menuId="+acl.get("id"));
					}else{
						acl.put("url", SpringContextHolder.getContextPath()+acl.get("url").toString()+"&menuId="+acl.get("id"));
					}
				}
				menuList.add(acl);
			}
			CacheUtils.put(GRANT_USER_ACL_MENU_FUNC+id, result);
			CacheUtils.put(GRANT_USER_ACL_MENU+id, menuList);
			CacheUtils.put(GRANT_USER_ACL_FUNC+id, funcList);
		}
		if(result == null || result.size() == 0){
			result = coreService.getPermissionAllMenuTrees(id);
			boolean bol = true;
			Map<String, Object> temp = Maps.newHashMap();//过滤重复数据选择器
			for(Map<String,Object> acl : result){
				if("menutree".equals(acl.get("resource_type").toString())){
					String key = acl.get("resource_type").toString()+acl.get("resource_id");
					if(!temp.containsKey(key)){
						temp.put(key, "");
						if("0".equals(acl.get("is_leaf").toString()) && bol){
							acl.put("open", true);
							bol = false;
						}
						if("1".equals(acl.get("is_leaf").toString())){
							acl.put("target", "fr3");
							String url = acl.get("url").toString();
							if(!url.contains("?")){
								acl.put("url", SpringContextHolder.getContextPath()+"/"+acl.get("url").toString()+"?menuId="+acl.get("id"));
							}else{
								acl.put("url", SpringContextHolder.getContextPath()+"/"+acl.get("url").toString()+"&menuId="+acl.get("id"));
							}
						}
						menuList.add(acl);
					}
				}else{
					funcList.add(acl);
				}
			}
			CacheUtils.put(GRANT_USER_ACL_MENU_FUNC+id, result);
			CacheUtils.put(GRANT_USER_ACL_MENU+id, menuList);
			CacheUtils.put(GRANT_USER_ACL_FUNC+id, funcList);
		}
		return result;
	}
	
	/**
	 * 获取用户具有的菜单资源
	 * @return
	 */
	public static List<Map<String,Object>> getUserMenuPermission(){
		getUserPermission();
		return (List<Map<String, Object>>) CacheUtils.get(GRANT_USER_ACL_MENU+UserUtils.getUser().getId());
	}
	
	/**
	 * 获取用户具有的功能资源
	 * @return
	 */
	public static List<Map<String,Object>> getUserFuncPermission(){
		getUserPermission();
		return (List<Map<String, Object>>) CacheUtils.get(GRANT_USER_ACL_FUNC+UserUtils.getUser().getId());
	}
	
	/**
	 * 构建指定角色权限以及完整权限
	 * @param roleId	角色ID
	 * @param hashPermission	角色具有的权限
	 * @param hashNonePermission 角色不具有的权限
	 * @return	完整资源权限
	 */
	public static Map<String,Object> getAcl(Integer roleId,Map<String,Object> hashPermission,Map<String,Object> hashNonePermission){
		//缓存当前角色资源权限
		CacheUtils.put(GRANT_ROLE_ACL+roleId,aclMapper.getAclByRole(roleId));
		//完整资源权限
		Map<String,Object> map = coreService.getAcls(hashPermission, roleId);
		return map;
	}
	
}
