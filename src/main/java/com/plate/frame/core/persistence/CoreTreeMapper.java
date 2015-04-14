package com.plate.frame.core.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.plate.common.mybatis.MybatisSqlMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：系统核心组织机构权限树查询公共接口
 * 			部门
 * 			人员
 * 			角色
 * 			菜单
 * 			字典
 */
public interface CoreTreeMapper extends MybatisSqlMapper {
	
	/**
	 * 查询所有组织信息
	 * @return
	 */
	@Select("select org.id,org.org_name name,org.parent_id pId from wp_organization org")
	public List<Map<String,Object>> findAllOrgs();
	
	/**
	 * 根据部门id获取部门信息
	 * @param id
	 * @return
	 */
	@Select("select org.id,org.org_name name,org.parent_id pId from wp_organization org where org.id=#{id}")
	public Map<String,Object> getOrg(Integer id);
	
	/**
	 * 根据部门id获取部门的直接子部门
	 * @param id
	 * @return
	 */
	@Select("select org.id,org.org_name name,org.parent_id pId from wp_organization org where org.parent_id=#{id}")
	public List<Map<String,Object>> getDirectOrgsByParent(Integer id);
	
	/**
	 * 根据部门id获取部门的所有子部门
	 * @param id
	 * @return
	 */
	@Select("select org.id,org.org_name name,org.parent_id pId from wp_organization org where org.parent_ids like concat('%,',#{id},',%')")
	public Map<String,Object> getAllOrgsByParent(Integer id);
	
	/**
	 * 根据部门id获取部门下人员
	 * @param id
	 * @return
	 */
	@Select("select u.id,u.user_name name from wp_user u where u.wp_org_id=#{id}")
	public List<Map<String,Object>> getUsersByOrgId(Integer id);
	
	/**
	 * 根据部门id获取部门及所属部门的人员
	 * @param id
	 * @return
	 */
	@Select("select u.id,u.user_name name,u.wp_org_id pId from wp_user u where u.wp_org_id=#{id}")
	public Map<String,Object> getAllUsersByOrgId(Integer id);
	
	/**
	 * 获取完整菜单信息
	 * @param menutreeId
	 * @return
	 */
	@Select("select m.id,m.parent_id pId,m.menu_name name,m.menu_url url,m.is_leaf leaf from wp_menutree m order by menu_no")
	public List<Map<String, Object>> getAllMenuTrees();
	
	/**
	 *  获取指定用户完整菜单信息
	 * @param id 用户ID
	 * @return
	 */
	@SelectProvider(type=CoreTreeSqlProvider.class,method="getPermissionAllMenuTreesSql")
	public List<Map<String, Object>> getPermissionAllMenuTrees(Integer id);
	
	/**
	 * 根据菜单id获取菜单下所有直接子菜单
	 * @param menutreeId
	 * @return
	 */
	@Select("select m.id,m.parent_id pId,m.menu_name name,m.menu_url url,m.is_leaf leaf from wp_menutree m where m.parent_id=#{menutreeId}")
	public List<Map<String, Object>> getMenuTreesByParent(Integer menutreeId);
	
	/**
	 * 根据菜单id获取菜单下所有子菜单(直接和间接)
	 * @param menutreeId
	 * @return
	 */
	@Select("select m.id,m.parent_id pId,m.menu_name name,m.menu_url url,m.is_leaf leaf from wp_menutree m where where m.menu_path like concat('%,',#{id},',%')")
	public List<Map<String, Object>> getAllMenuTreeByParent(Integer id);
	
	
}
