package com.plate.frame.core.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：目录模型
 */
@PlateTable(tableName = "c_dir")
public class Catalog extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@PlateColumn
	private String name;//字典名称
	@PlateColumn
	private Integer parent_id;//父ID
	@PlateColumn
	private Integer leaf;//是否为叶子	0为非叶子 1为叶子
	@PlateColumn
	private String dir_ids;//所有父ID
	@PlateColumn
	private Integer serialno;//排序
	@PlateColumn
	private Integer sub_id;//科目ID
	@PlateColumn
	private Integer version;//版本号
	@PlateColumn
	private String file_path;//文件路径
	
	private List<Catalog> children = Lists.newArrayList();//子数据列表
	
	public Catalog() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getDir_ids() {
		return dir_ids;
	}

	public void setDir_ids(String dir_ids) {
		this.dir_ids = dir_ids;
	}

	public Integer getSerialno() {
		return serialno;
	}

	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}

	public Integer getSub_id() {
		return sub_id;
	}

	public void setSub_id(Integer sub_id) {
		this.sub_id = sub_id;
	}

	public Integer getVersion() {
		if(version == null){
			version = 0;
		}
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public List<Catalog> getChildren() {
		return children;
	}

	public void setChildren(List<Catalog> children) {
		this.children = children;
	}
	
}
