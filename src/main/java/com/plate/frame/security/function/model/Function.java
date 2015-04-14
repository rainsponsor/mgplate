package com.plate.frame.security.function.model;

import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：功能模型
 */
@PlateTable(tableName="wp_function")
public class Function extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@PlateColumn
	private String function_name;// 功能名称
	@PlateColumn
	private String function_url;// 功能路径
	@PlateColumn
	private Integer function_no;// 功能排序
	@PlateColumn
	private Integer menutree_id;//菜单ID

	public Function() {
		super();
	}

	public String getFunction_name() {
		return function_name;
	}

	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}

	public String getFunction_url() {
		return function_url;
	}

	public void setFunction_url(String function_url) {
		this.function_url = function_url;
	}

	public Integer getFunction_no() {
		return function_no;
	}

	public void setFunction_no(Integer function_no) {
		this.function_no = function_no;
	}

	public Integer getMenutree_id() {
		return menutree_id;
	}

	public void setMenutree_id(Integer menutree_id) {
		this.menutree_id = menutree_id;
	}

}
