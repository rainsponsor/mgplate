package com.plate.frame.core.persistence;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.core.model.User;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface UserMapper extends MybatisSqlMapper {
	
	/**
	 * 用户管理列表
	 * @return
	 */
	@SelectProvider(method = "getUserList", type = SysCoreSqlProvider.class)
	PageMyBatis<User> getListByPage(@Param("userModel")User userModel,PagingCriteria pagingCriteria);
	
	/**
	 * <功能简述>保存系统用户
			* <功能详细描述> 添加系统用户并保存
			* @param user
			* @see [类、类#方法、类#成员]
	 */
	@InsertProvider(type=User.class,method=METHOD_INSERT_SQL)
	void save(@Param("user") User user);
	
	/**
	 * <功能简述>重置密码
			* <功能详细描述>重置密码为123456
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@Update("update wp_user set login_password=#{password} where id = #{id}")
	void reset(@Param("id") Integer id, @Param("password") String password);
	
	/**
	 * <功能简述>逻辑删除系统用户
			* <功能详细描述>修改用户状态
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(method = "getDelete", type = SysCoreSqlProvider.class)
	void delete(Integer id);
	
	/**
	 * <功能简述>删除系统用户
			* <功能详细描述>
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@DeleteProvider(type=User.class,method=METHOD_DELETE_SQL)
	void delUser(Integer id);
	
	/**
	 * <功能简述>删除系统用户对应的角色
			* <功能详细描述>
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@Delete("delete from wp_user_role where user_id = #{id}")
	void delUserRole(Integer id);
	
	/**
	 * <功能简述>逻辑恢复系统用户
			* <功能详细描述>恢复用户
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(method = "getRecovery", type = SysCoreSqlProvider.class)
	void recovery(Integer id);
	
	/**
	 * <功能简述>修改系统用户
			* <功能详细描述>修改系统用户并保存
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(method = METHOD_UPDATE_SQL, type = User.class)
	void update(@Param("user") User user);
	
	/**
	 * 根据ID获取类实例
	 * @param id
	 * @return User
	 */
	@SelectProvider(type=SysCoreSqlProvider.class,method="getUser")
	public User get(Integer id);
	
	/**
	 * <功能简述>登陆认证
			* <功能详细描述>根据用户登陆名获取用户信息
			* @param loginName
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("select u.id, u.user_name,u.login_name,u.login_password,u.is_major,"
			+ "u.wp_org_id,org.org_name,u.user_status, u.phone,u.email,u.major,w.user_name major_name"
			+ " from wp_user u left join wp_user w on u.major = w.id left join wp_organization org on org.id = u.wp_org_id" +
			" where u.login_name = #{loginName}")
	public User getUserByLoginName(String loginName);
}
