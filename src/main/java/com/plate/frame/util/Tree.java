package com.plate.frame.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tree implements Serializable {

	/**
	 * Root of the tree.
	 */
	protected TreeNode root;

	/**
	 * 检测isLeaf方法如何确定一个节点为叶子节点。如果为真，不允许
	 * 
	 * 有子节点，则节点为叶子节点。（如果允许有子节点，则不是叶子 节点。即时它当前并没有子节点）同时它还可以让你在文件系统中
	 * 
	 * 辨别文件夹节点和文件节点。例如：如果当前值为假，则任何没有
	 * 
	 * 子节点的节点都是叶子节点。并且任何节点都可以获得子节点。
	 * 
	 * 
	 * @see TreeNode#getAllowsChildren
	 * @see TreeModel#isLeaf
	 * @see #setAsksAllowsChildren
	 */
	protected boolean asksAllowsChildren;

	/**
	 * 创建一个允许任何节点有子节点的树
	 * 
	 * 
	 * @param root
	 *            a TreeNode object that is the root of the tree
	 * @see #DefaultTreeModel(TreeNode, boolean)
	 */
	public Tree(TreeNode root) {
		this(root, false);
	}

	/**
	 * 创建一棵指定的无论是允许任何节点都有子节点还是仅仅允许指定节点 可以拥有子节点。
	 * 
	 * 
	 * @param root
	 *            a TreeNode object that is the root of the tree
	 * @param askAllowsChildren
	 *            a boolean, false if any node can have children, true if each
	 *            node is asked to see if it can have children
	 * @see #asksAllowsChildren
	 */
	public Tree(TreeNode root, boolean asksAllowsChildren) {
		super();
		if (root == null) {
			throw new IllegalArgumentException("root is null");
		}
		this.root = root;
		this.asksAllowsChildren = asksAllowsChildren;
	}

	/**
	 * 设置对树中节点是用getAllowsChildren（）还是用isLeaf（）来测试
	 * 
	 * 节点是否为叶子，如果新值为真。调用getAllowChildren（），否则
	 * 
	 * 调用isLeaf（）。
	 */
	public void setAsksAllowsChildren(boolean newValue) {
		asksAllowsChildren = newValue;
	}

	/**
	 * 如果不被允许有子节点的节点都是叶子节点则值为真，如果没有子节点
	 * 
	 * （即时允许它有）节点都是叶子节点。
	 * 
	 * Tells how leaf nodes are determined.
	 * 
	 * @return true if only nodes which do not allow children are leaf nodes,
	 *         false if nodes which have no children (even if allowed) are leaf
	 *         nodes
	 * @see #asksAllowsChildren
	 */
	public boolean asksAllowsChildren() {
		return asksAllowsChildren;
	}

	/**
	 * 设置根节点为root。如果root为null则会抛出异常IllegalArgumentException
	 */
	public void setRoot(TreeNode root) {
		if (root == null)
			throw new IllegalArgumentException(
					"Root of tree is not allowed to be null");
		this.root = root;
	}

	/**
	 * 返回树中的根，仅当树没有任何节点时返回null。
	 * 
	 * 
	 * @return the root of the tree
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * 返回父节点的子节点索引
	 */
	public int getIndexOfChild(Object parent, Object child) {
		if (parent == null || child == null)
			return -1;
		return ((TreeNode) parent).getIndex((TreeNode) child);
	}

	/**
	 * 返回指定parent的子节点序列中指定索引值的子节点。parent必须从数据源中先获
	 * 
	 * 得的节点。如果index是一个对于parent来说正确的索引值，则应该不返回null（
	 * 
	 * 
	 * @param parent
	 *            a node in the tree, obtained from this data source
	 * @return the child of <I>parent</I> at index <I>index</I>
	 */
	public Object getChild(Object parent, int index) {
		return ((TreeNode) parent).getChildAt(index);
	}

	/**
	 * 返回parent的子节点数目。如果节点是叶子节点或它没有子节点则返回0。parent 必须是预先从数据源取出的节点。
	 * 
	 * 
	 * @param parent
	 *            a node in the tree, obtained from this data source
	 * @return the number of children of the node <I>parent</I>
	 */
	public int getChildCount(Object parent) {
		return ((TreeNode) parent).getChildCount();
	}

	/**
	 * 返回指定的节点是否是叶子节点。这个测试方法的执行依赖于askAllowChildren 的设置
	 * 
	 * 
	 * @param node
	 *            the node to check
	 * @return true if the node is a leaf node
	 * @see #asksAllowsChildren
	 * @see TreeModel#isLeaf
	 */
	public boolean isLeaf(Object node) {
		if (asksAllowsChildren)
			return !((TreeNode) node).getAllowsChildren();
		return ((TreeNode) node).isLeaf();
	}

	/**
	 * 这里将设置由路径标识的树节点的user object并且提交改变。如果你 在树模型中使用定制的user object，你将需要在细分类，并且设置
	 * 被改变的节点的user object。
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
		TreeNode aNode = (TreeNode) path.getLastPathComponent();

		aNode.setUserObject(newValue);
		nodeChanged(aNode);
	}

	/**
	 * 调用此方法将会在parent节点的子节点索引中添加节点newChild。
	 * 
	 * Invoked this to insert newChild at location index in parents children.
	 * This will then message nodesWereInserted to create the appropriate event.
	 * This is the preferred way to add children as it will create the
	 * appropriate event.
	 */
	public void insertNodeInto(TreeNode newChild, TreeNode parent, int index) {
		parent.insert(newChild, index);

		int[] newIndexs = new int[1];

		newIndexs[0] = index;
		nodesWereInserted(parent, newIndexs);
	}

	/**
	 * 从当前节点的父节点上删除当前节点。
	 * 
	 * Message this to remove node from its parent. This will message
	 * nodesWereRemoved to create the appropriate event. This is the preferred
	 * way to remove a node as it handles the event creation for you.
	 */
	public void removeNodeFromParent(TreeNode node) {
		TreeNode parent = node.getParent();

		if (parent == null)
			throw new IllegalArgumentException("node does not have a parent.");

		int[] childIndex = new int[1];
		Object[] removedArray = new Object[1];

		childIndex[0] = parent.getIndex(node);
		parent.remove(childIndex[0]);
		removedArray[0] = node;
		nodesWereRemoved(parent, childIndex, removedArray);
	}

	/**
	 * 在你已经删除了树中的节点后调用此方法 Invoke this method after you've changed how node is to
	 * be represented in the tree.
	 */
	public void nodeChanged(TreeNode node) {
		if (node != null) {
			TreeNode parent = node.getParent();

			if (parent != null) {
				int anIndex = parent.getIndex(node);
				if (anIndex != -1) {
					int[] cIndexs = new int[1];

					cIndexs[0] = anIndex;
					nodesChanged(parent, cIndexs);
				}
			} else if (node == getRoot()) {
				nodesChanged(node, null);
			}
		}
	}

	/**
	 * Invoke this method after you've inserted some TreeNodes into node.
	 * childIndices should be the index of the new elements and must be sorted
	 * in ascending order.
	 */
	public void nodesWereInserted(TreeNode node, int[] childIndices) {
		if (node != null && childIndices != null && childIndices.length > 0) {
			int cCount = childIndices.length;
			Object[] newChildren = new Object[cCount];

			for (int counter = 0; counter < cCount; counter++)
				newChildren[counter] = node.getChildAt(childIndices[counter]);
		}
	}

	/**
	 * 在你已经从节点中删除了一些树节点后调用这个方法，childIndices应该是被
	 * 删除的元素的索引并且必须按升序排序。removeChildren应该是被删除的子节点 序列。
	 */
	public void nodesWereRemoved(TreeNode node, int[] childIndices,
			Object[] removedChildren) {
		if (node != null && childIndices != null) {
		}
	}

	/**
	 * 在你已经通过childIndicies改变子节点的标识后，调用这个方法 Invoke this method after you've
	 * changed how the children identified by childIndicies are to be
	 * represented in the tree.
	 */
	public void nodesChanged(TreeNode node, int[] childIndices) {
		if (node != null) {
			if (childIndices != null) {
				int cCount = childIndices.length;

				if (cCount > 0) {
					Object[] cChildren = new Object[cCount];

					for (int counter = 0; counter < cCount; counter++)
						cChildren[counter] = node
								.getChildAt(childIndices[counter]);
				}
			} else if (node == getRoot()) {
			}
		}
	}

	/**
	 * Builds the parents of node up to and including the root node, where the
	 * original node is the last element in the returned array. The length of
	 * the returned array gives the node's depth in the tree.
	 * 
	 * @param aNode
	 *            the TreeNode to get the path for
	 * @param an
	 *            array of TreeNodes giving the path from the root to the
	 *            specified node.
	 */
	public TreeNode[] getPathToRoot(TreeNode aNode) {
		return getPathToRoot(aNode, 0);
	}

	/**
	 * Builds the parents of node up to and including the root node, where the
	 * original node is the last element in the returned array. The length of
	 * the returned array gives the node's depth in the tree.
	 * 
	 * @param aNode
	 *            the TreeNode to get the path for
	 * @param depth
	 *            an int giving the number of steps already taken towards the
	 *            root (on recursive calls), used to size the returned array
	 * @return an array of TreeNodes giving the path from the root to the
	 *         specified node
	 */
	protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
		TreeNode[] retNodes;
		// This method recurses, traversing towards the root in order
		// size the array. On the way back, it fills in the nodes,
		// starting from the root and working back to the original node.

		/*
		 * Check for null, in case someone passed in a null node, or they passed
		 * in an element that isn't rooted at root.
		 */
		if (aNode == null) {
			if (depth == 0)
				return null;
			else
				retNodes = new TreeNode[depth];
		} else {
			depth++;
			if (aNode == root)
				retNodes = new TreeNode[depth];
			else
				retNodes = getPathToRoot(aNode.getParent(), depth);
			retNodes[retNodes.length - depth] = aNode;
		}
		return retNodes;
	}
}
