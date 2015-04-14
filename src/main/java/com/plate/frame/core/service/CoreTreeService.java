package com.plate.frame.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plate.common.cache.CacheUtils;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.persistence.CoreTreeMapper;
import com.plate.frame.security.acl.model.Acl;
import com.plate.frame.security.acl.persistence.AclMapper;
import com.plate.frame.security.function.model.Function;
import com.plate.frame.security.function.persistence.FunctionMapper;
import com.plate.frame.security.menutree.model.MenuTree;
import com.plate.frame.security.menutree.persistence.MenuTreeMapper;
import com.plate.frame.security.util.SecurityUtils;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class CoreTreeService extends BaseService{
	
	@Autowired
	private CoreTreeMapper mapper;
	@Autowired
	private AclMapper aclMapper;
	@Autowired
	private MenuTreeMapper menuTreeMapper;
	@Autowired
	private FunctionMapper functionMapper;
	
	private final static String KEY_CHILDREN = "children";
	private final static String KEY_ID = "id";
	private final static String KEY_NAME = "name";
	private final static String KEY_CODE = "code";
	private final static String KEY_LEAF = "leaf";
	private final static String KEY_RESOURCE_TYPE = "resourceType";
	private final static String KEY_RESOURCE_MENU = "menutree";
	private final static String KEY_RESOURCE_VIRT = "virtual";
	
	/**
	 * 获取所有组织结构数据
	 * @return
	 */
	public List<Map<String,Object>> findAllOrgs(){
		return mapper.findAllOrgs();
	}
	
	/**
	 * 获取系统组织结构中所有人员信息
	 * @return
	 */
	public List<Map<String,Object>> findAllUserInOrg(Integer rootId){
		List<Map<String,Object>> result = Lists.newArrayList();
		//获取组织机构的跟节点
		Map<String,Object> root = mapper.getOrg(rootId);
		parseUserInOrg(root);
		root.remove(KEY_ID);
		result.add(root);
		return result;
	}
	
	private void parseUserInOrg(Map<String,Object> node){
		List<Map<String,Object>> children = Lists.newArrayList();
		node.put("isParent", true);
		//node.put("nocheck", true);
		node.put(KEY_CHILDREN, children);
		Integer id = Integer.parseInt(node.get(KEY_ID).toString());
		//部门下人员
		List<Map<String,Object>> userList = mapper.getUsersByOrgId(id);
		if(userList!=null && userList.size()>0){
			children.addAll(userList);
		}
		//子部门
		List<Map<String,Object>> orgList = mapper.getDirectOrgsByParent(id);
		if(orgList!=null && orgList.size()>0){
			for(Map<String,Object> org : orgList){
				parseUserInOrg(org);
				org.remove(KEY_ID);
				children.add(org);
			}
		}
	}
	
	/**
	 *  获取指定菜单id的所有直接子菜单
	 * @param menutreeId
	 * @return
	 */
	public List<Map<String, Object>> getMenuTreeByParent(Integer menutreeId) {
		List<Map<String, Object>> result = mapper.getMenuTreesByParent(menutreeId);
		return result;
	}
	
	/**
	 *  获取指定菜单id的所有子菜单(直接和间接)
	 * @param menutreeId
	 * @return
	 */
	public List<Map<String, Object>> getAllMenuTreeByParent(Integer menutreeId) {
		List<Map<String, Object>> result = mapper.getAllMenuTreeByParent(menutreeId);
		return result;
	}
	
	/**
	 *  获取全部菜单树
	 * @param menutreeId
	 * @return
	 */
	public List<Map<String, Object>> getAllMenuTrees() {
		List<Map<String, Object>> result = mapper.getAllMenuTrees();
		return result;
	}
	
	/**
	 *  获取指定用户的菜单树
	 * @param menutreeId
	 * @return
	 */
	public List<Map<String, Object>> getPermissionAllMenuTrees(Integer userId) {
		List<Map<String, Object>> result = mapper.getPermissionAllMenuTrees(userId);
		return result;
	}
	
	/**
	 * 获取角色具有的资源权限(菜单及菜单内功能)
	 * @return
	 */
	public List<Acl> getHaveAclByRole(Integer roleId){
		return aclMapper.getAclByRoleAndResourceType(roleId, KEY_RESOURCE_MENU);
	}
	
	/**
	 * 格式化角色权限Hash
	 * @param hashPermision		角色具有的权限
	 * @param hashNonePermision 角色不具有的权限
	 * @param roleId
	 * @return
	 */
	public Map<String,Object> getAcls(Map<String,Object> hashPermission,Integer roleId){
		//虚拟根节点	-	资源权限
		final Integer rootId = 0;
		Map<String,Object> rootAcl = Maps.newHashMap();
		rootAcl.put(KEY_CODE,"A");
		rootAcl.put(KEY_ID, rootId);
		rootAcl.put(KEY_NAME, "权限资源");
		rootAcl.put(KEY_LEAF, 0);
		rootAcl.put(KEY_RESOURCE_TYPE, KEY_RESOURCE_VIRT);
		hashPermission.put(KEY_CODE,"A");
		hashPermission.put(KEY_ID, rootId);
		hashPermission.put(KEY_NAME, "权限资源");
		hashPermission.put(KEY_LEAF, 0);
		hashPermission.put(KEY_RESOURCE_TYPE, KEY_RESOURCE_VIRT);
		aclGrant(roleId,rootAcl,hashPermission);
		return rootAcl;
	}
	
	private void aclGrant(Integer roleId,Map<String,Object> aclMap,Map<String,Object> hashPermission){
		List<Map<String,Object>> children = Lists.newArrayList();
		List<Map<String,Object>> permissionChildren = Lists.newArrayList();
		hashPermission.put(KEY_CHILDREN, permissionChildren);
		aclMap.put(KEY_CHILDREN, children);
		Integer id = Integer.parseInt(aclMap.get(KEY_ID).toString());
		//如果为叶子菜单，则将对应的功能加入
		Integer leaf = Integer.parseInt(aclMap.get(KEY_LEAF).toString());
		if(leaf == 1){
			List<Function> funcList = functionMapper.getFuncsByMenuTreeId(id);
			if(funcList !=null && funcList.size()>0){
				for(Function func : funcList){
					Map<String,Object> funcMap = Maps.newHashMap();
					funcMap.put(KEY_CODE, aclMap.get(KEY_CODE)+"-"+func.getId());
					funcMap.put(KEY_ID, func.getId());
					funcMap.put(KEY_NAME, func.getFunction_name());
					funcMap.put(KEY_RESOURCE_TYPE, String.valueOf(id));
					boolean flag = isContainResource(roleId, func.getId(), String.valueOf(id));
					if(flag){
						permissionChildren.add(funcMap);
					}
					children.add(funcMap);
				}
			}
		}
		//获取所有根菜单
		List<MenuTree> menuRootList = menuTreeMapper.getChildrenById(id);
		for(MenuTree menutree : menuRootList){
			Map<String,Object> node = Maps.newHashMap();
			node.put(KEY_CODE, aclMap.get(KEY_CODE)+"-"+menutree.getId());
			node.put(KEY_ID, menutree.getId());
			node.put(KEY_NAME, menutree.getMenu_name());
			node.put(KEY_LEAF, menutree.getIs_leaf());
			node.put(KEY_RESOURCE_TYPE, KEY_RESOURCE_MENU);
			Map<String,Object> permission = Maps.newHashMap();
			permission.put(KEY_CODE, aclMap.get(KEY_CODE)+"-"+menutree.getId());
			permission.put(KEY_ID, menutree.getId());
			permission.put(KEY_NAME, menutree.getMenu_name());
			permission.put(KEY_LEAF, menutree.getIs_leaf());
			permission.put(KEY_RESOURCE_TYPE, KEY_RESOURCE_MENU);
			aclGrant(roleId,node,permission);
			boolean flag = isContainResource(roleId, menutree.getId(), KEY_RESOURCE_MENU);
			if(flag){
				permissionChildren.add(permission);
			}
			children.add(node);
		}
	}
	
	/**
	 * 判断角色是否拥有指定资源
	 * @param roleId
	 * @param resourceId
	 * @param resourceType
	 * @return
	 */
	private boolean isContainResource(Integer roleId,Integer resourceId,String resourceType){
		boolean flag = false;
		List<Acl> aclList = (List<Acl>) CacheUtils.get(SecurityUtils.GRANT_ROLE_ACL+roleId);
		if(aclList !=null && aclList.size() > 0){
			for(Acl acl : aclList){
				if(acl.getResource_id() == resourceId && resourceType.equals(acl.getResource_type())){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
}
