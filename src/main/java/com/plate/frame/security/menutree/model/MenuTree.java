package com.plate.frame.security.menutree.model;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Lists;
import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@PlateTable(tableName="wp_menutree")
public class MenuTree extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@PlateColumn
	private Integer parent_id;//父ID
	@PlateColumn
	private String menu_name;//菜单名称
	@PlateColumn
	private String menu_url;//菜单路径
	@PlateColumn
	private String menu_code;//菜单编码
	@PlateColumn
	private String menu_path;//菜单路径
	@PlateColumn
	private Integer menu_no;//菜单显示顺序
	@PlateColumn
	private Integer is_leaf;//是否为叶子菜单0:非叶子 1:叶子
	
	private MenuTree parent;
	
	private List<MenuTree> functions = Lists.newArrayList();
	
	private List<MenuTree> children = Lists.newArrayList();
	
	public MenuTree() {
		super();
	}
	
	@Min(value=0)
	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	@NotEmpty
	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	public String getMenu_path() {
		return menu_path;
	}

	public void setMenu_path(String menu_path) {
		this.menu_path = menu_path;
	}

	public Integer getMenu_no() {
		return menu_no;
	}

	public void setMenu_no(Integer menu_no) {
		this.menu_no = menu_no;
	}
	
	public Integer getIs_leaf() {
		return is_leaf;
	}

	public void setIs_leaf(Integer is_leaf) {
		this.is_leaf = is_leaf;
	}

	public MenuTree getParent() {
		return parent;
	}

	public void setParent(MenuTree parent) {
		this.parent = parent;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public List<MenuTree> getFunctions() {
		return functions;
	}

	public void setFunctions(List<MenuTree> functions) {
		this.functions = functions;
	}

}
