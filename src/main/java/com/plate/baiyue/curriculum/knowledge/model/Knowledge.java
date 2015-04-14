package com.plate.baiyue.curriculum.knowledge.model;

import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;
import com.plate.frame.core.model.Catalog;
import com.plate.frame.security.util.UserUtils;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-27
 * @描述:
 */
@PlateTable(tableName = "c_knowledge")
public class Knowledge extends BaseModel {

	private static final long serialVersionUID = 1L;

	@PlateColumn
	private String search_key;// 检索内容
	@PlateColumn
	private String content;
	@PlateColumn
	private String backups;
	@PlateColumn
	private String dir_ids;
	@PlateColumn
	private Integer dir_id;
	@PlateColumn
	private Integer sub_id;
	@PlateColumn
	private Integer serialno;
	@PlateColumn
	private Integer creator;

	private Catalog catalog;// 知识点对应的目录

	public String getSearch_key() {
		return search_key;
	}

	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBackups() {
		return backups;
	}

	public void setBackups(String backups) {
		this.backups = backups;
	}

	public String getDir_ids() {
		return dir_ids;
	}

	public void setDir_ids(String dir_ids) {
		this.dir_ids = dir_ids;
	}

	public Integer getDir_id() {
		return dir_id;
	}

	public void setDir_id(Integer dir_id) {
		this.dir_id = dir_id;
	}

	public Integer getSub_id() {
		return sub_id;
	}

	public void setSub_id(Integer sub_id) {
		this.sub_id = sub_id;
	}

	public Integer getSerialno() {
		return serialno;
	}

	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}

	public Integer getCreator() {
		if (creator == null) {
			creator = UserUtils.getUser().getId();
		}
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}
