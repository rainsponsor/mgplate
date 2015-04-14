package com.plate.frame.core.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-6
 * @描述: 平台树
 */
public class CoreTree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * TreeNode's Id
	 */
	private Integer id;

	/**
	 * TreeNode's parent_id
	 */
	private Integer pId;

	/**
	 * TreeNode's name
	 */
	private String name;

	/**
	 * TreeNode's opened
	 */
	private Boolean open;

	/**
	 * TreeNode's is checked
	 */
	private Boolean checked;

	private CoreTree parentNode;
	
	private List<CoreTree> children = Lists.newArrayList();

	public CoreTree() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
		/**
		 * 如果为二级菜单则自动展开
		 */
		if (pId == 0) {
			this.open = true;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public CoreTree getParentNode() {
		return parentNode;
	}

	public void setParentNode(CoreTree parentNode) {
		this.parentNode = parentNode;
	}

	public List<CoreTree> getChildren() {
		return children;
	}

	public void setChildren(List<CoreTree> children) {
		this.children = children;
	}

}
