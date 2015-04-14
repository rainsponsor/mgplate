package com.plate.frame.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.common.util.StringUtils;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.Role;
import com.plate.frame.core.model.User;
import com.plate.frame.core.persistence.RoleMapper;
import com.plate.frame.security.acl.model.Acl;
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
public class RoleService extends BaseService {

	@Autowired 
	private RoleMapper mapper;
	
	/**
	 * 系统角色列表
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	public PageMyBatis<Role> getListByPage(Role model) {
		return mapper.getListByPage(model,model.getPagingCriteria());
	}
	
	/**
	 * 添加系统角色
	 * 
	 * @param role
	 * @param request
	 * @return
	 */
	public Integer save(Role model) {
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
		return mapper.save(model);
	}

	/**
	 * <功能简述>修改角色信息
			* <功能详细描述>修改角色信息以及角色维护用户
			* @param model
			* @see [类、类#方法、类#成员]
	 */
	public void update(Role model) {
		if(model.getIds() != null && model.getIds().length()>0){
			//根据角色id删除维护的用户
			mapper.deleteRoleUser(model.getId());
			//添加新增加的维护用户
			List<Integer> paramList = new ArrayList<Integer>();
			String[] str = model.getIds().split(",");
			for (int i = 0; i < str.length; i++) {
				paramList.add(Integer.parseInt(str[i]));
			}
			mapper.addRoleUser(model.getId(),paramList);
		}
		mapper.update(model);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}
	
	/**
	 * <功能简述>停用系统角色 <功能详细描述>改变用户状态
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void delete(Integer id) {
		mapper.delete(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}
	
	/**
	 * <功能简述>物理删除系统角色 <功能详细描述>处理删除角色 以及所维护的用户
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void delRole(Integer id) {
		mapper.delRole(id);
		mapper.deleteRoleUser(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}
	
	/**
	 * <功能简述>恢复系统角色 <功能详细描述>改变用户状态
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void recovery(Integer id) {
		mapper.recovery(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}

	/**
	 * 根据ID获取类实例
	 * @param id
	 * @return
	 */
	public Role get(Integer id){
		return mapper.get(id);
	}
	
	/**
	 * 保存角色权限
	 * @param roleId	角色ID
	 * @param aclIds	资源id
	 * @param aclTypes	资源类型
	 */
	public void saveGrant(String roleId, String aclIds, String aclTypes) {
		List<Acl> aclList = (List<Acl>)CacheUtils.get(SecurityUtils.GRANT_ROLE_ACL+roleId);
		String[] ids = aclIds.split(",");
		String[] types = aclTypes.split(",");
		List<Acl> insertParams = Lists.newArrayList();//批量插入集合
		List<Acl> deleteParams = Lists.newArrayList();//批量删除结合
		Map<String,Object> grand = Maps.newHashMap();//权限资源记录器
		for(int i=0;i<ids.length;i++){
			String id = ids[i];
			String type = types[i];
			if(!StringUtils.isBlank(id)){
				boolean invoke = false;
				for(Acl acl : aclList){
					boolean flag = acl.getRole_id()==Integer.parseInt(roleId) &&
							   acl.getResource_id()==Integer.parseInt(id)&&
							   acl.getResource_type().equals(type);
					if(flag){//权限匹配
						grand.put(roleId+id+type, null);
						invoke = true;
						break;
					}
				}
				if(!invoke){
					grand.put(roleId+id+type, null);
					insertParams.add(new Acl(Integer.parseInt(roleId),Integer.parseInt(id), "using", types[i]));
				}
			}
		}
		
		for(Acl acl : aclList){
			String key = String.valueOf(acl.getRole_id())+""+String.valueOf(acl.getResource_id())+String.valueOf(acl.getResource_type());
			if(!grand.containsKey(key)){
				deleteParams.add(acl);
			}
		}
		
		if(insertParams.size() != 0){
			mapper.saveGrant(insertParams);
			CacheUtils.remove(SecurityUtils.GRANT_ROLE_ACL+roleId);
		}
		if(deleteParams.size() != 0){
			for(Acl acl : deleteParams){
				mapper.deleteGrant(acl);
			}
			CacheUtils.remove(SecurityUtils.GRANT_ROLE_ACL+roleId);
		}
	}
	
	public Integer isExist(String roleName){
		return mapper.isExist(roleName);
	}
}
