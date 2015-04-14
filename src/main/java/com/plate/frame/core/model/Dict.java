package com.plate.frame.core.model;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Lists;
import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;
import com.plate.frame.security.menutree.model.MenuTree;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：用户模型
 */
@PlateTable(tableName = "wp_dict")
public class Dict extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@PlateColumn
	private String dict_name;//字典名称
	@PlateColumn
	private String dict_depict;//描述
	@PlateColumn
	private Integer parent_id;//父ID
	private String parent_ids;//所有父ID
	@PlateColumn
	private Integer sort;//排序
	@PlateColumn
	private String dict_value;//值
	private List<Dict> children = Lists.newArrayList();//子数据列表
	
	public Dict() {
		super();
	}

	/**
	 * @return 返回 dict_name
	 */
	@NotEmpty
	public String getDict_name() {
		return dict_name;
	}

	/**
	 * @param 对dict_name进行赋值
	 */
	
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}

	/**
	 * @return 返回 dict_depict
	 */
	
	public String getDict_depict() {
		return dict_depict;
	}

	/**
	 * @param 对dict_depict进行赋值
	 */
	
	public void setDict_depict(String dict_depict) {
		this.dict_depict = dict_depict;
	}

	/**
	 * @return 返回 parent_id
	 */
	
	public Integer getParent_id() {
		return parent_id;
	}

	/**
	 * @param 对parent_id进行赋值
	 */
	
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	/**
	 * @return 返回 sort
	 */
	
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param 对sort进行赋值
	 */
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return 返回 dict_value
	 */
	
	public String getDict_value() {
		return dict_value;
	}

	/**
	 * @param 对dict_value进行赋值
	 */
	
	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}

	/**
	 * @return 返回 parent_ids
	 */
	
	public String getParent_ids() {
		return parent_ids;
	}

	/**
	 * @param 对parent_ids进行赋值
	 */
	
	public void setParent_ids(String parent_ids) {
		this.parent_ids = parent_ids;
	}

	/**
	 * @return 返回 children
	 */
	
	public List<Dict> getChildren() {
		return children;
	}

	/**
	 * @param 对children进行赋值
	 */
	
	public void setChildren(List<Dict> children) {
		this.children = children;
	}
	
}
