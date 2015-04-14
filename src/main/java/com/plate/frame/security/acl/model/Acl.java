package com.plate.frame.security.acl.model;

import com.plate.frame.base.BaseModel;
import com.plate.frame.core.model.Role;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：资源请求路由模型
 */
public class Acl extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private Integer resource_id;// 资源ID
	private Integer role_id;// 角色ID
	private String permission;// 权限类型
	private String resource_type;// 资源类型

	private Role role;
	
	public Acl(Integer role_id,Integer resource_id,String permission,String resource_type){
		this.role_id = role_id;
		this.resource_id = resource_id;
		this.permission = permission;
		this.resource_type = resource_type;
	}

	public Acl() {
		super();
	}

	public Integer getResource_id() {
		return resource_id;
	}

	public void setResource_id(Integer resource_id) {
		this.resource_id = resource_id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
