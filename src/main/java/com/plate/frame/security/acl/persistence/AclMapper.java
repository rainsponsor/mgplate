package com.plate.frame.security.acl.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.security.acl.model.Acl;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface AclMapper extends MybatisSqlMapper {
	
	/**
	 * 根据角色和资源类型获取Acl信息
	 * @param roleId	   :角色ID
	 * @param resourceType :资源类型
	 * @return
	 */
	@Select("select acl.id,acl.resource_id,acl.role_id,acl.resource_type from wp_acl acl where acl.role_id=#{roleId} and acl.resource_type=#{resourceType}")
	public List<Acl> getAclByRoleAndResourceType(@Param("roleId")Integer roleId,@Param("resourceType")String resourceType);
	
	/**
	 * 根据角色获取Acl信息
	 * @param roleId	   :角色ID
	 * @return
	 */
	@Select("select acl.id,acl.resource_id,acl.role_id,acl.resource_type from wp_acl acl where acl.role_id=#{roleId}")
	public List<Acl> getAclByRole(@Param("roleId")Integer roleId);
	
	
}
