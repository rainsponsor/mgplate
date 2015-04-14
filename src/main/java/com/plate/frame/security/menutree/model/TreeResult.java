package com.plate.frame.security.menutree.model;

import java.util.HashMap;

import com.plate.frame.util.Tree;
import com.plate.frame.util.TreeNode;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：存放Tree结果的类
 * 
 */
public class TreeResult {

	private Tree tree;
	private HashMap<Integer, TreeNode> indexTree;
	private HashMap<Integer, HashMap<Integer, String>> hashPermission;

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public HashMap<Integer, TreeNode> getIndexTree() {
		return indexTree;
	}

	public void setIndexTree(HashMap<Integer, TreeNode> indexTree) {
		this.indexTree = indexTree;
	}

	public HashMap<Integer, HashMap<Integer, String>> getHashPermission() {
		return hashPermission;
	}

	public void setHashPermission(
			HashMap<Integer, HashMap<Integer, String>> hashPermission) {
		this.hashPermission = hashPermission;
	}

}
