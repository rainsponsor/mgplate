package com.plate.frame.core.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.StatementType;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.core.model.Role;
import com.plate.frame.core.model.User;
import com.plate.frame.security.acl.model.Acl;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface RoleMapper extends MybatisSqlMapper {
	
	/**
	 * 根据ID获取类实例
	 * @param id
	 * @return User
	 */
	@SelectProvider(type=SysCoreSqlProvider.class,method="getRole")
	@Results(value={
			@Result(property="id",column="role_id"),
			@Result(property="users",column="role_id",javaType=List.class,
					many=@Many(select="com.plate.frame.core.persistence.RoleMapper.getUserByRoleId"))
	})
	public Role get(Integer id);
	
	/**
	 * <功能简述>获取系统用户列表
			* <功能详细描述>根据角色id获取系统用户List
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@SelectProvider(type=SysCoreSqlProvider.class,method="getUserByRoleId")
	public List<User> getUserByRoleId(Integer role_id);
	
	/**
	 * <功能简述>获取角色列表
			* <功能详细描述>查看角色列表以及分页，检索
			* @param roleModel
			* @param pagingCriteria
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@SelectProvider(type=SysCoreSqlProvider.class,method="getRoleList")
	public PageMyBatis<Role> getListByPage(@Param("roleModel")Role roleModel,PagingCriteria pagingCriteria);
	
	/**
	 * <功能简述>保存系统角色
			* <功能详细描述> 添加系统角色并保存
			* @param user
			* @see [类、类#方法、类#成员]
	 */
	@InsertProvider(type=Role.class,method=METHOD_INSERT_SQL)
	@SelectKey(before=false,keyProperty="role.id",resultType=Integer.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
	Integer save(@Param("role") Role role);
	
	/**
	 * <功能简述>修改系统角色
	 * <功能详细描述> 修改系统角色并保存
	 * @param user
	 * @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(type=Role.class,method=METHOD_UPDATE_SQL)
	void update(@Param("role") Role role);
	
	/**
	 * <功能简述>逻辑删除系统角色
			* <功能详细描述>修改用户状态
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(method = "getRoleDelete", type = SysCoreSqlProvider.class)
	void delete(Integer id);
	
	/**
	 * <功能简述>物理删除系统角色
			* <功能详细描述>彻底删除
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@DeleteProvider(type=Role.class,method=METHOD_DELETE_SQL)
	void delRole(@Param("id")Integer id);
	
	/**
	 * <功能简述>物理删除系统角色维护的系统用户
			* <功能详细描述>彻底删除
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@Delete("delete from wp_user_role where role_id=#{id}")
	void delRoleUser(Integer id);
	/**
	 * <功能简述>逻辑恢复系统角色
	 * <功能详细描述>修改用户状态
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(method = "getRoleRecovery", type = SysCoreSqlProvider.class)
	void recovery(Integer id);
	
	/**
	 * <功能简述>删除角色维护的用户
			* <功能详细描述>根据角色id删除所有维护的用户
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@DeleteProvider(method = "deleteRoleUser", type = SysCoreSqlProvider.class)
	void deleteRoleUser(Integer id);
	
	/**
	 * <功能简述>添加角色维护的用户
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	@InsertProvider(method = "addRoleUser", type = SysCoreSqlProvider.class)
	void addRoleUser(@Param("id")Integer id, @Param("paramList") List<Integer> paramList);
	
	/**
	 * 批量添加角色资源信息
	 * @param params
	 */
	@InsertProvider(method = "addRoleGrant", type = SysCoreSqlProvider.class)
	public void saveGrant(@Param("params") List<Acl> params);
	
	/**
	 * 批量删除角色资源信息
	 * @param params
	 */
	@InsertProvider(method = "delRoleGrant", type = SysCoreSqlProvider.class)
	public void deleteGrant(@Param("acl") Acl acl);

	/**
	 * <功能简述>获取用户角色
			* <功能详细描述>根据用户id 获取用户所有启用的角色
			* @param userId
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("select r.id,r.role_name from wp_user_role ur left join wp_role r on r.id = ur.role_id where ur.user_id = #{userId} and r.active_flag=0")
	public List<Role> getRoleByUserId(@Param("userId")Integer userId);
	
	/**
	 *  获取指定用户功能信息
	 * @param id 用户ID
	 * @return
	 */
	@Select("SELECT acl.resource_id,acl.role_id,acl.resource_type, menu.menu_url url, menu.is_leaf, menu.id, menu.parent_id pId," +
				" menu.menu_name name, func.function_name FROM wp_acl acl LEFT JOIN wp_menutree menu ON acl.resource_id = menu.id " +
				" left join wp_function func on func.id = acl.RESOURCE_ID WHERE acl.role_id IN (SELECT role_id "+
                " FROM wp_user_role WHERE user_id = #{id})")
	public List<Map<String, Object>> getPermissionAllFunctionById(@Param("id")Integer id);
	
	/**
	 *  获取指定用户功能信息
	 * @param id 用户ID
	 * @return
	 */
	@Select("SELECT func.function_name,func.menutree_id FROM wp_function func")
	public List<Map<String, Object>> getPermissionAllFunction();
	
	/**
	 *  获取指定用户功能信息
	 * @param id 用户ID
	 * @return
	 */
	@Select("select id from wp_role where role_name = #{roleName}")
	public Integer isExist(@Param("roleName")String roleName);
	
	
}
