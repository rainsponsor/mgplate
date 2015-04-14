package com.plate.frame.core.persistence;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.plate.frame.core.model.Role;
import com.plate.frame.core.model.User;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public class SysCoreSqlProvider {

	// ================================部门=====================================
	public String getOrganizationPageList() {
		StringBuilder sql = new StringBuilder(
				"select org.id,org.org_name,org.org_code,org.parent_id,org.parent_name,org.sort_no,org.manager,org.contact,org.org_address,org.is_admin_org "
						+ "from wp_organization org where 1=1 ");
		// TODO 条件查询

		return sql.toString();
	}

	// ================================用户=====================================
	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public String getUserList(Map<String, Object> map) {
		User user = (User) map.get("userModel");
		StringBuilder sql = new StringBuilder(
				"select u.id, u.user_name,u.login_name,u.login_password,u.is_major,"
						+ "u.wp_org_id,org.org_name,u.user_status, u.phone,u.email,u.major,w.user_name major_name"
						+ " from wp_user u left join wp_user w on u.major = w.id left join wp_organization org on org.id = u.wp_org_id where 1=1 ");

		//  条件查询
		if (user.getUser_name() != null && user.getUser_name().length() > 0) {	//用户名
			sql.append(" and u.user_name like concat('%',#{userModel.user_name},'%')");
		}
		if (user.getPhone() != null && user.getPhone().length() > 0) {			//电话
			sql.append(" and u.phone=#{userModel.phone} ");
		}
		if (user.getWp_org_id() != null) {										//部门	
			sql.append(" and u.wp_org_id=#{userModel.wp_org_id} ");
		}
		if (user.getUser_status() != null) {									//用户状态
			sql.append(" and u.user_status=#{userModel.user_status} ");
		}
		if (user.getIs_major() != null) {										//是否是领导
			sql.append(" and u.is_major=#{userModel.is_major} ");
		}
		if (user.getMajor_name() != null  && user.getMajor_name().length() > 0) {//领导名	
			sql.append(" and w.user_name like concat('%',#{userModel.major_name},'%')");
		}
		sql.append(" order by id desc");
		return sql.toString();
	}

	/**
	 * 逻辑删除系统用户--改变用户状态值
	 * 
	 * @return
	 */
	public String getDelete() {
		String sql = "update wp_user set user_status = 1 where id = #{id}";
		return sql;
	}

	/**
	 * <逻辑恢复系统用户>
			* <改名用户状态值>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public String getRecovery() {
		String sql = "update wp_user set user_status = 0 where id = #{id}";
		return sql;
	}
	
	/**
	 * <功能简述>根据id获取系统用户对象
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public String getUser(){
		String sql = "select u.id, u.user_name,u.login_name,u.login_password,u.is_major,"
				+ "u.wp_org_id,org.org_name,u.user_status, u.phone,u.email,u.major,w.user_name major_name"
				+ " from wp_user u left join wp_user w on u.major = w.id left join wp_organization org on org.id = u.wp_org_id where u.id =#{id} ";
		return sql;
	}
	
	
	// ================================权限=====================================
	/**
	 * <功能简述>根据id获取系统角色对象
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public String getRole(){
		String sql ="select r.id, r.id role_id, r.role_name,r.active_flag,r.description,r.creator,u.user_name from wp_role r left join wp_user u on u.id = r.creator where r.id =#{id}";
		return sql;
	}
	
	/**
	 * <功能简述>根据id获取系统角色列表
			* <功能详细描述>查询，检索
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public String getRoleList(Map<String, Object> map){
		Role role = (Role) map.get("roleModel");
		StringBuilder sql = new StringBuilder("select r.id, r.role_name,r.active_flag,r.description,r.creator,u.user_name from wp_role r left join wp_user u on u.id = r.creator where 1=1");

		// TODO 条件查询
		if (role.getRole_name() != null && role.getRole_name().length() > 0) {			//角色名称
			sql.append(" and r.role_name like concat('%',#{roleModel.role_name},'%')");	
		}
		if (role.getActive_flag() != null) {											//角色状态
			sql.append(" and r.active_flag =#{roleModel.active_flag}");
		}
		if (role.getUser_name() != null && role.getUser_name().length() > 0) {			//创建人
			sql.append(" and u.user_name like concat('%',#{roleModel.user_name},'%')");
		}
		sql.append(" order by r.id desc");
		return sql.toString();
	}

	/**
	 * <功能简述>根据id删除系统角色
			* <功能详细描述>修改活动状态  1 不活动
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public String getRoleDelete(){
		String sql ="update wp_role set active_flag=1 where id=#{id} ";
		return sql;
	}
	
	/**
	 * <功能简述>根据id恢复系统角色
			* <功能详细描述>修改活动状态  1 不活动
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public String getRoleRecovery(){
		String sql ="update wp_role set active_flag=0 where id=#{id} ";
		return sql;
	}
	
	/**
	 * <功能简述>获取user列表
			* <功能详细描述>根据id获取user列表 （user_id,user_name）
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public String getUserByRoleId(){
		String sql ="select r.user_id id ,u.user_name from wp_user_role r left join wp_user u on u.id = r.user_id  where role_id = #{id}";
		return sql;
	}
	
	/**
	 * <功能简述>删除角色维护的用户
			* <功能详细描述>根据角色id删除所有维护的用户
			* @param id
			* @see [类、类#方法、类#成员]
	 */
	public String deleteRoleUser(){
		String sql = "delete from wp_user_role where role_id=#{id}";
		return sql;
	}
	
	/**
	 * <功能简述>添加角色维护的用户
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public String addRoleUser(Map<String, Object> map){
		List<Integer> paramList = (List<Integer>) map.get("paramList");
		StringBuffer sb = new StringBuffer("insert into wp_user_role(user_id,role_id) values");
		MessageFormat msf = new MessageFormat("#'{'paramList[{0}]}");
		for (int i = 0; i < paramList.size(); i++) {
			sb.append("("+msf.format(new Integer[]{i})+",#{id})");
			if (i<paramList.size()-1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * <功能简述>添加角色资源权限
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public String addRoleGrant(Map<String, Object> map){
		List<Integer> params = (List<Integer>) map.get("params");
		StringBuffer sb = new StringBuffer("insert into wp_acl(resource_id,role_id,permission,resource_type) values ");
		MessageFormat msf = new MessageFormat("(#'{'params[{0}].resource_id},#'{'params[{0}].role_id},#'{'params[{0}].permission},#'{'params[{0}].resource_type})");
		for (int i = 0; i < params.size(); i++) {
			sb.append(msf.format(new Object[]{i}));
			if (i<params.size()-1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * <功能简述>删除角色资源权限
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public String delRoleGrant(){
		StringBuffer sb = new StringBuffer("delete from wp_acl where resource_id=#{acl.resource_id} and resource_type=#{acl.resource_type} and role_id=#{acl.role_id}");
		return sb.toString();
	}
	
}
