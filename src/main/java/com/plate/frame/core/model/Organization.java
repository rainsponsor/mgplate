package com.plate.frame.core.model;

import java.util.List;


import org.hibernate.validator.constraints.NotEmpty;

import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateSelectCondition;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：组织机构模型
 */
@PlateTable(tableName = "wp_organization")
public class Organization extends BaseModel {

	private static final long serialVersionUID = 1L;

	@PlateColumn
	@PlateSelectCondition(linkAlias = "like")
	private String org_name;// 组织名称
	@PlateColumn
	private String org_code;// 组织编码
	@PlateColumn
	private Integer parent_id;// 上级组织机构ID
	@PlateColumn
	@PlateSelectCondition(linkAlias = "like")
	private String parent_ids;// 所有上级组织机构ID集合
	@PlateColumn
	private String parent_name;// 上级组织机构名称
	@PlateColumn
	private Integer sort_no;// 优先级(排序序号)
	@PlateColumn
	private Integer manager;// 负责人
	private Integer manager_name;// 负责人
	@PlateColumn
	private String contact;// 联系电话
	@PlateColumn
	private String org_address;// 地址
	@PlateColumn
	private Integer is_admin_org;// 是否为管理部门0非管理部门 1管理部门

	private Organization parent;// 父部门

	private List<Organization> children;// 子部门

	public Organization() {
		super();
	}
	
	@NotEmpty
	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getParent_ids() {
		return parent_ids;
	}

	public void setParent_ids(String parent_ids) {
		this.parent_ids = parent_ids;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public Integer getSort_no() {
		return sort_no;
	}

	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public Integer getManager_name() {
		return manager_name;
	}

	public void setManager_name(Integer manager_name) {
		this.manager_name = manager_name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getOrg_address() {
		return org_address;
	}

	public void setOrg_address(String org_address) {
		this.org_address = org_address;
	}

	public Integer getIs_admin_org() {
		return is_admin_org;
	}

	public void setIs_admin_org(Integer is_admin_org) {
		this.is_admin_org = is_admin_org;
	}

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public List<Organization> getChildren() {
		return children;
	}

	public void setChildren(List<Organization> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Organization [org_name=" + org_name + ", org_code=" + org_code
				+ ", parent_id=" + parent_id + ", parent_ids=" + parent_ids
				+ ", parent_name=" + parent_name + ", sort_no=" + sort_no
				+ ", manager=" + manager + ", contact=" + contact
				+ ", org_address=" + org_address + ", is_admin_org="
				+ is_admin_org + "]";
	}

}
