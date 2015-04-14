package com.plate.frame.core.persistence;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.core.model.User;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface MemberMapper extends MybatisSqlMapper {
	
	
	/**
	 * 根据ID获取类实例
	 * @param id
	 * @return User
	 */
	@SelectProvider(type=SysCoreSqlProvider.class,method="getUser")
	public User get(Integer id);
	
	/**
	 * <功能简述>保存密码
			* <功能详细描述>
			* @param user
			* @see [类、类#方法、类#成员]
	 */
	@Update("update wp_user set login_password=#{user.new_password} where id=#{user.id}")
	public void updatePwd(@Param("user")User user);
	
	/**
	 * <功能简述>验证密码
			* <功能详细描述>
			* @param user
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("select count(*) from wp_user where id = #{user.id} and login_password = #{user.login_password}")
	public Integer validatePwd(@Param("user")User user);
	
	/**
	 * <功能简述>个人信息保存
			* <功能详细描述>
			* @param user
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Update("update wp_user set user_name=#{user.user_name}, email=#{user.email},phone=#{user.phone} where id=#{user.id}")
	public void form(@Param("user")User user);
}
