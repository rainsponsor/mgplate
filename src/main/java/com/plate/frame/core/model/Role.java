package com.plate.frame.core.model;

import java.util.List;

import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：角色模型
 */
@PlateTable(tableName="wp_role")
public class Role extends BaseModel {

	@PlateColumn
	private String role_name;// 角色名称
	@PlateColumn
	private Integer active_flag;// 是否活动
	@PlateColumn
	private String description;// 角色描述
	@PlateColumn
	private Integer creator;// 创建人
	private String user_name;//创建人名
	private List<User> users;//角色管理人员
	private String ids;//添加维护系统用户id
	private String names;//维护的系统用户名称
	
	public Role() {
		super();
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	/**
	 * @return 返回 user_name
	 */
	
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param 对user_name进行赋值
	 */
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return 返回 users
	 */
	
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param 对users进行赋值
	 */
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return 返回 ids
	 */
	
	public String getIds() {
		if(ids == null || ids.length()==0){
			StringBuilder sb = new StringBuilder();
			if(users!=null && users.size() > 0){
				int len = users.size();
				for(int i=0;i<len;i++){
					if(i != 0){
						sb.append(",");
					}
					sb.append(users.get(i).getId());
				}
				return sb.toString();
			}
		}
		return ids;
	}

	/**
	 * @param 对ids进行赋值
	 */
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return 返回 names
	 */
	
	public String getNames() {
		StringBuilder sb = new StringBuilder();
		if(users!=null && users.size() > 0){
			int len = users.size();
			for(int i=0;i<len;i++){
				if(i != 0){
					sb.append(",");
				}
				sb.append(users.get(i).getUser_name());
			}
		}
		return sb.toString();
	}

	/**
	 * @param 对names进行赋值
	 */
	
	public void setNames(String names) {
		this.names = names;
	}

}
