package com.plate.frame.core.persistence;
/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public class CoreTreeSqlProvider {
	
	/**
	 * 根据部门获取部门下所有人员SQL
	 * @return
	 */
	public String getPermissionAllMenuTreesSql(){
		StringBuilder sql = new StringBuilder("SELECT acl.resource_id,acl.role_id,acl.resource_type, menu.menu_url url, menu.is_leaf, menu.id, menu.parent_id pId," +
				" menu.menu_name name FROM wp_acl acl LEFT JOIN wp_menutree menu ON acl.resource_id = menu.id WHERE acl.role_id IN (SELECT  role_id "+
                " FROM wp_user_role WHERE user_id = #{id})");
		return sql.toString();
	}
	
}
