package com.plate.frame.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

@SuppressWarnings({ "rawtypes", "serial","unchecked" })
public class TreeNode implements Serializable {
	/**
	 * 当前节点的父节点，如果当前节点没有父节点则为空
	 */
	protected TreeNode parent;

	/**
	 * 子节点序列，如果当前节点没有子节点则为空
	 */
	protected Vector children;

	/**
	 * user object
	 */
	transient protected Object userObject;

	/**
	 * 如果当前节点可以有子节点则值为真
	 */
	protected boolean allowsChildren;

	/**
	 * 一个总是为空的枚举，通常被用在枚举需要枚举叶子节点的子节点时
	 */
	static public final Enumeration EMPTY_ENUMERATION = new Enumeration() {
		public boolean hasMoreElements() {
			return false;
		}

		public Object nextElement() {
			throw new NoSuchElementException("No more elements");
		}
	};

	/**
	 * 创建一个没有父、子节点的树节点，但是可以有子节点
	 */
	public TreeNode() {
		this(null);

	}

	/**
	 * 创建一个没有父、子节点的树节点, 可以有子节点，并且可以用指定的用 户对象初始化
	 */
	public TreeNode(Object obj) {
		this(obj, true);
	}

	/**
	 * 创建一个没有父、子节点的树节点，可以用指定的用户对象初始化，并且
	 * 
	 * 只能拥有指定的子节点
	 */
	public TreeNode(Object obj, boolean allowsChildren) {
		parent = null;
		this.allowsChildren = allowsChildren;
		this.userObject = obj;
	}

	/**
	 * 如果当前节点允许有子节点，返回真值
	 */
	public boolean getAllowsChildren() {
		return allowsChildren;
	}

	/**
	 * 返回在当前节点的子节点序列中紧跟在aChild后的节点，它必须是当前节点的
	 * 子节点，如果aChild是最后一个节点，则返回null，这个方法执行了一个对当前
	 * 节点子节点中aChild的近查询,复杂度为O(n),n是子节点的数量，使用枚举替代 遍历整个字节点序列，。
	 */

	public TreeNode getChildAfter(TreeNode aChild) {
		if (aChild == null) {
			throw new IllegalArgumentException("argument is null");
		}

		int index = getIndex(aChild); // linear search

		if (index == -1) {
			throw new IllegalArgumentException("node is not a child");
		}

		if (index < getChildCount() - 1) {
			return getChildAt(index + 1);
		} else {
			return null;
		}
	}

	/**
	 * 返回当前节点子节点序列中被指定子节点的索引（标志），如果被指定的字节 点不是当前节点的子节点，返回－1，这个方法执行了一个近查询，时间复杂度
	 * 为O(n),n为字节点的数量
	 */
	public int getIndex(TreeNode aChild) {
		if (aChild == null) {
			throw new IllegalArgumentException("argument is null");
		}

		if (!isNodeChild(aChild)) {
			return -1;
		}
		return children.indexOf(aChild); // linear search
	}

	/**
	 * 返回当前节点子节点的数目
	 */
	public int getChildCount() {
		if (children == null) {
			return 0;
		} else {
			return children.size();
		}
	}

	/**
	 * 返回当前节点的字节点序列中被指定索引（标识）的字节点
	 */
	public TreeNode getChildAt(int index) {
		if (children == null) {
			throw new ArrayIndexOutOfBoundsException("node has no children");
		}
		return (TreeNode) children.elementAt(index);
	}

	/**
	 * 返回当前节点的父节点，如果当前节点没有父节点则返回null
	 */
	public TreeNode getParent() {
		return parent;
	}

	/**
	 * 如果当前aNode是当前节点的子节点返回true，如果aNode是null则返回false
	 */
	public boolean isNodeChild(TreeNode aNode) {
		boolean retval;

		if (aNode == null) {
			retval = false;
		} else {
			if (getChildCount() == 0) {
				retval = false;
			} else {
				retval = (aNode.getParent() == this);
			}
		}

		return retval;
	}

	/**
	 * 如果当前节点没有子节点则返回true，用来辨别没有子节点和部能有子节点的节点
	 * （例如：判别空目录中的文件），这个方法常和getAllowsChildren联合使用
	 */
	public boolean isLeaf() {
		return (getChildCount() == 0);
	}

	/**
	 * 创建并且返回一个当前节点的字节点的forward-order枚举，编辑当前节点的字节点
	 * 
	 * 序列使任何在编辑前创建的子节点枚举无效
	 */
	public Enumeration children() {
		if (children == null) {
			return EMPTY_ENUMERATION;
		} else {
			return children.elements();
		}
	}

	/**
	 * 返回在当前节点的子节点序列中刚好在aChild前的节点，它必须是当前节点的
	 * 子节点，如果aChild是第一个节点，则返回null，这个方法执行了一个对当前
	 * 节点子节点中aChild的近查询,复杂度为O(n),n是子节点的数量，使用枚举替代 遍历整个字节点序列，。
	 */
	public TreeNode getChildBefore(TreeNode aChild) {
		if (aChild == null) {
			throw new IllegalArgumentException("argument is null");
		}

		int index = getIndex(aChild); // linear search

		if (index == -1) {
			throw new IllegalArgumentException("argument is not a child");
		}

		if (index > 0) {
			return getChildAt(index - 1);
		} else {
			return null;
		}
	}

	/**
	 * 返回以当前节点作为根节点的树的深度－－从当前节点到叶子节点的最远距离，
	 * 如果当前节点没有子节点，则返回0，这一操作比起getLevel来代价更高，因为 它必须遍历以当前节点做为根节点的整个树。
	 */
	public int getDepth() {
		Object last = null;
		Enumeration enums = breadthFirstEnumeration();

		while (enums.hasMoreElements()) {
			last = enums.nextElement();
		}

		if (last == null) {
			throw new Error("nodes should be null");
		}

		return ((TreeNode) last).getLevel() - getLevel();
	}

	/**
	 * 返回当前节点所在的层数－－即当前节点到根的距离。如果当前节点就是根 则返回0。
	 */
	public int getLevel() {
		TreeNode ancestor;
		int levels = 0;

		ancestor = this;
		while ((ancestor = ancestor.getParent()) != null) {
			levels++;
		}

		return levels;
	}

	/**
	 * 返回当前节点的第一个子节点，如果当前节点没有字节点则抛出
	 * 
	 * NoSuchElemenException异常
	 */
	public TreeNode getFirstChild() {
		if (getChildCount() == 0) {
			throw new NoSuchElementException("node has no children");
		}
		return getChildAt(0);
	}

	/**
	 * 寻找并且返回当前节点的后代中的第一个叶子节点－－不是当前节点本身
	 * 
	 * 就是其第一个子节点的第一个叶子节点，如果当前节点就是叶子节点则返回
	 * 
	 * 本身。
	 * 
	 * 
	 * @see #isLeaf
	 * @see #isNodeDescendant
	 * @return the first leaf in the subtree rooted at this node
	 */
	public TreeNode getFirstLeaf() {
		TreeNode node = this;

		while (!node.isLeaf()) {
			node = node.getFirstChild();
		}

		return node;
	}

	/**
	 * 返回当前节点的最后一个子节点，如果当前节点没有字节点则抛出
	 * 
	 * NoSuchElemenException异常
	 * 
	 * @return the last child of this node
	 * @exception NoSuchElementException
	 *                if this node has no children
	 */
	public TreeNode getLastChild() {
		if (getChildCount() == 0) {
			throw new NoSuchElementException("node has no children");
		}
		return getChildAt(getChildCount() - 1);
	}

	/**
	 * 寻找并且返回当前节点的后代中的最后一个叶子节点－－不是当前节点本身
	 * 
	 * 就是其最后一个子节点的最后一个叶子节点，如果当前节点就是叶子节点则
	 * 
	 * 返回本身。
	 * 
	 * 
	 * @see #isLeaf
	 * @see #isNodeDescendant
	 * @return the last leaf in the subtree rooted at this node
	 */
	public TreeNode getLastLeaf() {
		TreeNode node = this;

		while (!node.isLeaf()) {
			node = node.getLastChild();
		}

		return node;
	}

	/**
	 * 返回当前节点的后代中所有叶子节点的数量，如果当前节点本身就是叶子节点
	 * 
	 * 返回1，这个方法的时间复杂度是O（n），n为当前节点的后代个数。
	 * 
	 * 
	 * @see #isNodeAncestor
	 * @return the number of leaves beneath this node
	 */
	public int getLeafCount() {
		int count = 0;

		TreeNode node;
		Enumeration enums = breadthFirstEnumeration(); // order matters not

		while (enums.hasMoreElements()) {
			node = (TreeNode) enums.nextElement();
			if (node.isLeaf()) {
				count++;
			}
		}

		if (count < 1) {
			throw new Error("tree has zero leaves");
		}

		return count;
	}

	/**
	 * 返回当前节点的下一个叶子节点，如果当前节点就是树中最后一个叶子节点则
	 * 返回null。这里实现了接口MutableNode，但是这一操作是非常低效率的，为了
	 * 检测下一个节点，这一方法首先在父节点的子节点列表中执行了一个近查询用
	 * 
	 * 来发现当前节点。这一实现所做的操作比较适合从已知位置开始的短遍历。但
	 * 是要遍历树中所有叶子节点，你应该使用depthFirstEnumeration来枚举树中的
	 * 节点并且使用isLeaf在每一个检测到的节点上来判断是否为叶子。
	 * 
	 * 
	 * @see #depthFirstEnumeration
	 * @see #isLeaf
	 * @return returns the next leaf past this node
	 */
	public TreeNode getNextLeaf() {
		TreeNode nextSibling;
		TreeNode myParent = getParent();

		if (myParent == null)
			return null;

		nextSibling = getNextSibling(); // linear search

		if (nextSibling != null)
			return nextSibling.getFirstLeaf();

		return myParent.getNextLeaf(); // tail recursion
	}

	/**
	 * 在当前节点所在的树中进行前序遍历时返回当前节点的下一个节点，如果当前节点
	 * 在遍历中是最后一个节点返回null。在遍历整个树时这是一个非常低效率的方法， 使用枚举替代它。
	 * 
	 * Returns the node that follows this node in a preorder traversal of this
	 * node's tree. Returns null if this node is the last node of the traversal.
	 * This is an inefficient way to traverse the entire tree; use an
	 * enumeration, instead.
	 * 
	 * @see #preorderEnumeration
	 * @return the node that follows this node in a preorder traversal, or null
	 *         if this node is last
	 */
	public TreeNode getNextNode() {
		if (getChildCount() == 0) {
			// No children, so look for nextSibling
			TreeNode nextSibling = getNextSibling();

			if (nextSibling == null) {
				TreeNode aNode = getParent();

				do {
					if (aNode == null) {
						return null;
					}

					nextSibling = aNode.getNextSibling();
					if (nextSibling != null) {
						return nextSibling;
					}

					aNode = aNode.getParent();
				} while (true);
			} else {
				return nextSibling;
			}
		} else {
			return getChildAt(0);
		}
	}

	/**
	 * 返回当前节点在其父节点的子节点列表中的下一个兄弟节点，如果当前节点没有 父节点或它本身就是父节点的最后一个子节点则返回null。这一方法执行了一个
	 * 
	 * 近查询，时间复杂度o（n），这里的n是子节点的数目，要遍历整个序列
	 * 
	 * Returns the next sibling of this node in the parent's children array.
	 * Returns null if this node has no parent or is the parent's last child.
	 * This method performs a linear search that is O(n) where n is the number
	 * of children; to traverse the entire array, use the parent's child
	 * enumeration instead.
	 * 
	 * @see #children
	 * @return the sibling of this node that immediately follows this node
	 */
	public TreeNode getNextSibling() {
		TreeNode retval;

		TreeNode myParent = getParent();

		if (myParent == null) {
			retval = null;
		} else {
			retval = myParent.getChildAfter(this);
			// linear search
		}

		if (retval != null && !isNodeSibling(retval)) {
			throw new Error("child of parent is not a sibling");
		}

		return retval;
	}

	/**
	 * 返回从树根到当前节点的路径，路径中最后一个元素就是当前结点
	 * 
	 * Returns the path from the root, to get to this node. The last element in
	 * the path is this node.
	 * 
	 * @return an array of TreeNode objects giving the path, where the first
	 *         element in the path is the root and the last element is this
	 *         node.
	 */
	public TreeNode[] getPath() {
		return getPathToRoot(this, 0);
	}

	/**
	 * 建立一个当前节点的父节点直到根节点，当前节点为最后一个元素的的数组
	 * 
	 * 所要求的数组的长度为当前节点的在树中深度。
	 * 
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
	public TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
		TreeNode[] retNodes;

		/*
		 * 检查是否为空，万一有人传送一个空节点或者他们传送在根上但是不是根节点的 元素 Check for null, in case
		 * someone passed in a null node, or they passed in an element that
		 * isn't rooted at root.
		 */
		if (aNode == null) {
			if (depth == 0)
				return null;
			else
				retNodes = new TreeNode[depth];
		} else {
			depth++;
			retNodes = getPathToRoot(aNode.getParent(), depth);
			retNodes[retNodes.length - depth] = aNode;
		}
		return retNodes;
	}

	/**
	 * 返回当前节点的前一个叶子节点，如果当前节点就是树中第一个叶子节点则
	 * 返回null。这里实现了接口MutableNode，但是这一操作是非常低效率的，为了
	 * 检测前一个节点，这一方法首先在父节点的子节点列表中执行了一个近查询用
	 * 
	 * 来发现当前节点。这一实现所做的操作比较适合从已知位置开始的短遍历。但
	 * 是要遍历树中所有叶子节点，你应该使用depthFirstEnumeration来枚举树中的
	 * 节点并且使用isLeaf在每一个检测到的节点上来判断是否为叶子。
	 * 
	 * 
	 * @see #depthFirstEnumeration
	 * @see #isLeaf
	 * @return returns the leaf before this node
	 */
	public TreeNode getPreviousLeaf() {
		TreeNode previousSibling;
		TreeNode myParent = getParent();

		if (myParent == null)
			return null;

		previousSibling = getPreviousSibling(); // linear search

		if (previousSibling != null)
			return previousSibling.getLastLeaf();

		return myParent.getPreviousLeaf(); // tail recursion
	}

	/**
	 * 在当前节点所在的树中进行前序遍历时返回当前节点的前一个节点，如果当前节点
	 * 在遍历中是第一个节点返回null。在遍历整个树时这是一个非常低效率的方法， 使用枚举替代它。
	 * 
	 * Returns the node that precedes this node in a preorder traversal of this
	 * node's tree. Returns null if this node is the first node of the traveral
	 * -- the root of the tree. This is an inefficient way to traverse the
	 * entire tree; use an enumeration, instead.
	 * 
	 * @see #preorderEnumeration
	 * @return the node that precedes this node in a preorder traversal, or null
	 *         if this node is the first
	 */
	public TreeNode getPreviousNode() {
		TreeNode previousSibling;
		TreeNode myParent = getParent();

		if (myParent == null) {
			return null;
		}

		previousSibling = getPreviousSibling();

		if (previousSibling != null) {
			if (previousSibling.getChildCount() == 0)
				return previousSibling;
			else
				return previousSibling.getLastLeaf();
		} else {
			return myParent;
		}
	}

	/**
	 * 返回当前节点在其父节点的子节点列表中的前一个兄弟节点，如果当前节点没有 父节点或它本身就是父节点的第一个子节点则返回null。这一方法执行了一个
	 * 
	 * 近查询，时间复杂度o（n），这里的n是子节点的数目，要遍历整个序列
	 * 
	 * 
	 * @return the sibling of this node that immediately precedes this node
	 */
	public TreeNode getPreviousSibling() {
		TreeNode retval;

		TreeNode myParent = getParent();

		if (myParent == null) {
			retval = null;
		} else {
			retval = myParent.getChildBefore(this); // linear search
		}

		if (retval != null && !isNodeSibling(retval)) {
			throw new Error("child of parent is not a sibling");
		}

		return retval;
	}

	/**
	 * 返回包含当前节点的树的根节点。根节点是父节点为空的节点
	 * 
	 * 
	 * @see #isNodeAncestor
	 * @return the root of the tree that contains this node
	 */
	public TreeNode getRoot() {
		TreeNode ancestor = this;
		TreeNode previous;

		do {
			previous = ancestor;
			ancestor = ancestor.getParent();
		} while (ancestor != null);

		return previous;
	}

	/**
	 * 返回当前节点和aNode的最近的共同祖先，如果这样的祖先不存在即当前节点和
	 * 
	 * aNode位于不同的树或aNode为空则返回null。单个节点被认为是它自己的祖先。
	 * 
	 * 
	 * @see #isNodeAncestor
	 * @see #isNodeDescendant
	 * @param aNode
	 *            node to find common ancestor with
	 * @return nearest ancestor common to this node and <code>aNode</code>, or
	 *         null if none
	 */
	public TreeNode getSharedAncestor(TreeNode aNode) {
		if (aNode == this) {
			return this;
		} else if (aNode == null) {
			return null;
		}

		int level1, level2, diff;
		TreeNode node1, node2;

		level1 = getLevel();
		level2 = aNode.getLevel();

		if (level2 > level1) {
			diff = level2 - level1;
			node1 = aNode;
			node2 = this;
		} else {
			diff = level1 - level2;
			node1 = this;
			node2 = aNode;
		}

		// Go up the tree until the nodes are at the same level
		while (diff > 0) {
			node1 = node1.getParent();
			diff--;
		}

		// Move up the tree until we find a common ancestor. Since we know
		// that both nodes are at the same level, we won't cross paths
		// unknowingly (if there is a common ancestor, both nodes hit it in
		// the same iteration).

		do {
			if (node1 == node2) {
				return node1;
			}
			node1 = node1.getParent();
			node2 = node2.getParent();
		} while (node1 != null); // only need to check one -- they're at the
		// same level so if one is null, the other is

		if (node1 != null || node2 != null) {
			throw new Error("nodes should be null");
		}

		return null;
	}

	/**
	 * 返回当前节点的兄弟节点的个数。单个节点被认为是自己的兄弟（如果它没有父
	 * 
	 * 节点或兄弟节点，此时此方法返回1。
	 * 
	 * 
	 * @return the number of siblings of this node
	 */
	public int getSiblingCount() {
		TreeNode myParent = getParent();

		if (myParent == null) {
			return 1;
		} else {
			return myParent.getChildCount();
		}
	}

	/**
	 * 返回当前节点的user object
	 * 
	 * @return the Object stored at this node by the user
	 * @see #setUserObject
	 * @see #toString
	 */
	public Object getUserObject() {
		return userObject;
	}

	/**
	 * 返回从根到当前节点user object的路径，如果此路径中一些TreeNodes包含 空user objects，则返回路径将包含nulls
	 * Returns the user object path, from the root, to get to this node. If some
	 * of the TreeNodes in the path have null user objects, the returned path
	 * will contain nulls.
	 */
	public Object[] getUserObjectPath() {
		TreeNode[] realPath = getPath();
		Object[] retPath = new Object[realPath.length];

		for (int counter = 0; counter < realPath.length; counter++)
			retPath[counter] = (realPath[counter]).getUserObject();
		return retPath;
	}

	/**
	 * 移除newChild从它的父节点上（如果它有父节点），设置它的新父节点为当前
	 * 节点，并且添加childIndex作为索引到当前节点的子节点序列中。newChild不
	 * 
	 * 能为空而且不能是当前结点的祖先。
	 * 
	 * 
	 * @param newChild
	 *            the TreeNode to insert under this node
	 * @param childIndex
	 *            the index in this node's child array where this node is to be
	 *            inserted
	 * @exception ArrayIndexOutOfBoundsException
	 *                if <code>childIndex</code> is out of bounds
	 * @exception IllegalArgumentException
	 *                if <code>newChild</code> is null or is an ancestor of this
	 *                node
	 * @exception IllegalStateException
	 *                if this node does not allow children
	 * @see #isNodeDescendant
	 */
	public void insert(TreeNode newChild, int childIndex) {
		if (!allowsChildren) {
			throw new IllegalStateException("node does not allow children");
		} else if (newChild == null) {
			throw new IllegalArgumentException("new child is null");
		} else if (isNodeAncestor(newChild)) {
			throw new IllegalArgumentException("new child is an ancestor");
		}

		TreeNode oldParent = newChild.getParent();

		if (oldParent != null) {
			oldParent.remove(newChild);
		}
		newChild.setParent(this);
		if (children == null) {
			children = new Vector();
		}
		children.insertElementAt(newChild, childIndex);
	}

	/**
	 * 如果anotherNode是当前节点的祖先（即是当前节点本身或它的父节点或它的
	 * 父节点的祖先），则返回true。（注意：单个节点被认为是它自己的祖先）
	 * 如果anotherNode为空，此方法将返回false，这一操作的时间复杂度为O(h) 这里的h是根到当前节点的距离。
	 * 
	 * 
	 * @see #isNodeDescendant
	 * @see #getSharedAncestor
	 * @param anotherNode
	 *            node to test as an ancestor of this node
	 * @return true if this node is a descendant of <code>anotherNode</code>
	 */
	public boolean isNodeAncestor(TreeNode anotherNode) {
		if (anotherNode == null) {
			return false;
		}

		TreeNode ancestor = this;

		do {
			if (ancestor == anotherNode) {
				return true;
			}
		} while ((ancestor = ancestor.getParent()) != null);

		return false;
	}

	/**
	 * 如果anotherNode是当前节点的后裔（即是当前节点本身或它的子节点或它的
	 * 子节点的后裔），则返回true。（注意：单个节点被认为是它自己的后裔）
	 * 如果anotherNode为空，此方法将返回false，这一操作的时间复杂度为O(h) 这里的h是根到anotherNode的距离。
	 * 
	 * 
	 * @see #isNodeAncestor
	 * @see #getSharedAncestor
	 * @param anotherNode
	 *            node to test as descendant of this node
	 * @return true if this node is an ancestor of <code>anotherNode</code>
	 */
	public boolean isNodeDescendant(TreeNode anotherNode) {
		if (anotherNode == null)
			return false;

		return anotherNode.isNodeAncestor(this);
	}

	/**
	 * 当且仅当aNode和当前节点位于同一棵树时返回true，如果aNode为空返回false
	 * 
	 * @see #getSharedAncestor
	 * @see #getRoot
	 * @return true if <code>aNode</code> is in the same tree as this node;
	 *         false if <code>aNode</code> is null
	 */
	public boolean isNodeRelated(TreeNode aNode) {
		return (aNode != null) && (getRoot() == aNode.getRoot());
	}

	/**
	 * 如果anotherNode是当前节点的兄弟（有共同的父节点）则返回true，单个节点
	 * 
	 * 是其自身的兄弟。如果anotherNode为空，返回false
	 * 
	 * @param anotherNode
	 *            node to test as sibling of this node
	 * @return true if <code>anotherNode</code> is a sibling of this node
	 */
	public boolean isNodeSibling(TreeNode anotherNode) {
		boolean retval;

		if (anotherNode == null) {
			retval = false;
		} else if (anotherNode == this) {
			retval = true;
		} else {
			TreeNode myParent = getParent();
			retval = (myParent != null && myParent == anotherNode.getParent());

			if (retval && !(getParent()).isNodeChild(anotherNode)) {
				throw new Error("sibling has different parent");
			}
		}

		return retval;
	}

	/**
	 * 如果当前节点是树的根节点则返回true，根是树中唯一的父节点为空的节点， 每一棵树只能有一个根。
	 * 
	 * 
	 * @return true if this node is the root of its tree
	 */
	public boolean isRoot() {
		return getParent() == null;
	}

	/**
	 * 从当前节点的子节点中删除指定索引的子节点，并且设定所删除节点的父节点 为null。被删除的子节点必须是TreeNode。
	 * 
	 * 
	 * @param childIndex
	 *            the index in this node's child array of the child to remove
	 * @exception ArrayIndexOutOfBoundsException
	 *                if <code>childIndex</code> is out of bounds
	 */
	public void remove(int childIndex) {
		TreeNode child = getChildAt(childIndex);
		children.removeElementAt(childIndex);
		child.setParent(null);
	}

	/**
	 * 从当前节点的子节点序列中删除指定的aChild。并且设置其父节点为空
	 * 
	 * 
	 * @param aChild
	 *            a child of this node to remove
	 * @exception IllegalArgumentException
	 *                if <code>aChild</code> is null or is not a child of this
	 *                node
	 */
	public void remove(TreeNode aChild) {
		if (aChild == null) {
			throw new IllegalArgumentException("argument is null");
		}

		if (!isNodeChild(aChild)) {
			throw new IllegalArgumentException("argument is not a child");
		}
		remove(getIndex(aChild)); // linear search
	}

	/**
	 * 删除当前节点的所有子节点，设置这些节点的父节点为null 如果当前节点没有子节点，这个方法不作任何事情
	 */
	public void removeAllChildren() {
		for (int i = getChildCount() - 1; i >= 0; i--) {
			remove(i);
		}
	}

	/**
	 * 删除树中以当前节点作为根的子树。设置当前节点的父节点为null。如果当 前节点是树的根则不作任何操作。
	 */
	public void removeFromParent() {
		TreeNode parent = getParent();
		if (parent != null) {
			parent.remove(this);
		}
	}

	/**
	 * 检测当前节点是否被允许有子节点，如果allows为false则当前节点的所有
	 * 
	 * 子节点将被删除。注意：节点默认允许有子节点。
	 * 
	 * 
	 * @param allows
	 *            true if this node is allowed to have children
	 */
	public void setAllowsChildren(boolean allows) {
		if (allows != allowsChildren) {
			allowsChildren = allows;
			if (!allowsChildren) {
				removeAllChildren();
			}
		}
	}

	/**
	 * 设置当前节点的父节点为newParent，但是不改变父节点的子节点序列。这一
	 * 方法会被insert（）和remove（）调用用来重新分配子节点的父节点。
	 * 
	 * it should not be messaged from anywhere else.
	 * 
	 * @param newParent
	 *            this node's new parent
	 */
	public void setParent(TreeNode newParent) {
		parent = newParent;
	}

	/**
	 * 设置当前节点的user Object为userObject。
	 * 
	 * 
	 * @param userObject
	 *            the Object that constitutes this node's user-specified data
	 * @see #getUserObject
	 * @see #toString
	 */
	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}

	/**
	 * 返回发送toString的结果到当前节点的user object。如果当前节点没有user Object则为null
	 * 
	 * @see #getUserObject
	 */
	public String toString() {
		if (userObject == null) {
			return null;
		} else {
			return userObject.toString();
		}
	}

	/**
	 * 从newChild的父节点上删除newChild，并且将该节点添加到当前节点的子节点 序列中的末端。
	 * 
	 * 
	 * @see #insert
	 * @param newChild
	 *            node to add as a child of this node
	 * @exception IllegalArgumentException
	 *                if <code>newChild</code> is null
	 * @exception IllegalStateException
	 *                if this node does not allow children
	 */
	public void add(TreeNode newChild) {
		if (newChild != null && newChild.getParent() == this)
			insert(newChild, getChildCount() - 1);
		else
			insert(newChild, getChildCount());
	}

	private void readObject(ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		Object[] tValues;

		s.defaultReadObject();

		tValues = (Object[]) s.readObject();

		if (tValues.length > 0 && tValues[0].equals("userObject"))
			userObject = tValues[1];
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		Object[] tValues;

		s.defaultWriteObject();
		// Save the userObject, if its Serializable.
		if (userObject != null && userObject instanceof Serializable) {
			tValues = new Object[2];
			tValues[0] = "userObject";
			tValues[1] = userObject;
		} else
			tValues = new Object[0];
		s.writeObject(tValues);
	}

	/**
	 * Overridden to make clone public. Returns a shallow copy of this node; the
	 * new node has no parent or children and has a reference to the same user
	 * object, if any.
	 * 
	 * @return a copy of this node
	 */
	public Object clone() {
		TreeNode newNode = null;

		try {
			newNode = (TreeNode) super.clone();

			// shallow copy -- the new node has no parent or children
			newNode.children = null;
			newNode.parent = null;

		} catch (CloneNotSupportedException e) {
			// Won't happen because we implement Cloneable
			throw new Error(e.toString());
		}

		return newNode;
	}

	/**
	 * 对以当前节点为根节点的子树进行深度优先搜索，创建并返回一个枚举，通过此
	 * 
	 * 枚举的nextElement（）方法返回的第一个节点是此子树最左端的叶子节点，这
	 * 
	 * 一点与后序遍历相同。
	 * 
	 * 通过添加、删除、移动节点来编辑树会导致在编辑前所创建的枚举失效。
	 * 
	 * 
	 * @see #breadthFirstEnumeration
	 * @see #postorderEnumeration
	 * @return an enumeration for traversing the tree in depth-first order
	 */
	public Enumeration depthFirstEnumeration() {
		return postorderEnumeration();
	}

	/**
	 * 创建并且返回一个从祖先到当前节点的路径枚举，此枚举的nextElement（）
	 * 方法第一个返回ancesor，然后是ancestor的子节点直到当前节点的祖先，最
	 * 终返回当前节点。创建枚举的时间复杂度为O(m)，这里m是当前节点到ancestor
	 * 之间的节点个数，包括，每一个nextElement（）的时间复杂度是O(1)。
	 * 
	 * <p/> 通过插入，删除，移动树中的节点回使得编辑前建立的枚举失效。
	 * 
	 * 
	 * @see #isNodeAncestor
	 * @see #isNodeDescendant
	 * @exception IllegalArgumentException
	 *                if <code>ancestor</code> is not an ancestor of this node
	 * @return an enumeration for following the path from an ancestor of this
	 *         node to this one
	 */
	public Enumeration pathFromAncestorEnumeration(TreeNode ancestor) {
		return new PathBetweenNodesEnumeration(ancestor, this);
	}

	/**
	 * 前序遍历以当前节点为根节点的子树，创建并且返回一个枚举，通过枚举的
	 * 
	 * nextElement（）方法返回的第一个节点是当前节点。
	 * 
	 * <p/> 通过增加、删除、或移动节点来编辑树回导致在编辑前创建的任何枚举失效。
	 * 
	 * 
	 * @see #postorderEnumeration
	 * @return an enumeration for traversing the tree in preorder
	 */
	public Enumeration preorderEnumeration() {
		return new PreorderEnumeration(this);
	}

	final class PreorderEnumeration implements Enumeration {
		protected Stack stack;

		public PreorderEnumeration(TreeNode rootNode) {
			super();
			Vector v = new Vector(1);
			v.addElement(rootNode); // PENDING: don't really need a vector
			stack = new Stack();
			stack.push(v.elements());
		}

		public boolean hasMoreElements() {
			return (!stack.empty() && ((Enumeration) stack.peek())
					.hasMoreElements());
		}

		public Object nextElement() {
			Enumeration enumer = (Enumeration) stack.peek();
			TreeNode node = (TreeNode) enumer.nextElement();
			Enumeration children = node.children();

			if (!enumer.hasMoreElements()) {
				stack.pop();
			}
			if (children.hasMoreElements()) {
				stack.push(children);
			}
			return node;
		}

	} // End of class PreorderEnumeration

	/**
	 * 后序遍历以当前节点为根节点的子树，创建并且返回一个枚举，用枚举的nextElement（） 方法返回第一个节点是最左边的叶子。与深度优先搜索一样。
	 * 
	 * <p/> 通过增加、删除、移动节点来编辑树将会导致编辑前建立的任何枚举失效
	 * 
	 * 
	 * @see #depthFirstEnumeration
	 * @see #preorderEnumeration
	 * @return an enumeration for traversing the tree in postorder
	 */
	public Enumeration postorderEnumeration() {
		return new PostorderEnumeration(this);
	}

	final class PostorderEnumeration implements Enumeration {
		protected TreeNode root;
		protected Enumeration children;
		protected Enumeration subtree;

		public PostorderEnumeration(TreeNode rootNode) {
			super();
			root = rootNode;
			children = root.children();
			subtree = EMPTY_ENUMERATION;
		}

		public boolean hasMoreElements() {
			return root != null;
		}

		public Object nextElement() {
			Object retval;

			if (subtree.hasMoreElements()) {
				retval = subtree.nextElement();
			} else if (children.hasMoreElements()) {
				subtree = new PostorderEnumeration((TreeNode) children
						.nextElement());
				retval = subtree.nextElement();
			} else {
				retval = root;
				root = null;
			}

			return retval;
		}

	} // End of class PostorderEnumeration

	final class PathBetweenNodesEnumeration implements Enumeration {
		protected Stack stack;

		public PathBetweenNodesEnumeration(TreeNode ancestor,
				TreeNode descendant) {
			super();

			if (ancestor == null || descendant == null) {
				throw new IllegalArgumentException("argument is null");
			}

			TreeNode current;

			stack = new Stack();
			stack.push(descendant);

			current = descendant;
			while (current != ancestor) {
				current = current.getParent();
				if (current == null && descendant != ancestor) {
					throw new IllegalArgumentException("node " + ancestor
							+ " is not an ancestor of " + descendant);
				}
				stack.push(current);
			}
		}

		public boolean hasMoreElements() {
			return stack.size() > 0;
		}

		public Object nextElement() {
			try {
				return stack.pop();
			} catch (EmptyStackException e) {
				throw new NoSuchElementException("No more elements");
			}
		}

	} // End of class PathBetweenNodesEnumeration

	/**
	 * 用广度优先搜索创建并且返回一个遍历以当前节点为根的子树的枚举。通过枚举的
	 * 
	 * nextElement（）方法返回的第一个节点是当前节点。
	 */
	public Enumeration breadthFirstEnumeration() {
		return new BreadthFirstEnumeration(this);
	}

	final class BreadthFirstEnumeration implements Enumeration {
		protected Queue queue;

		public BreadthFirstEnumeration(TreeNode rootNode) {
			super();
			Vector v = new Vector(1);
			v.addElement(rootNode); // PENDING: don't really need a vector
			queue = new Queue();
			queue.enqueue(v.elements());
		}

		public boolean hasMoreElements() {
			return (!queue.isEmpty() && ((Enumeration) queue.firstObject())
					.hasMoreElements());
		}

		public Object nextElement() {
			Enumeration enumer = (Enumeration) queue.firstObject();
			TreeNode node = (TreeNode) enumer.nextElement();
			Enumeration children = node.children();

			if (!enumer.hasMoreElements()) {
				queue.dequeue();
			}
			if (children.hasMoreElements()) {
				queue.enqueue(children);
			}
			return node;
		}

		// A simple queue with a linked list data structure.
		final class Queue {
			QNode head; // null if empty
			QNode tail;

			final class QNode {
				public Object object;
				public QNode next; // null if end

				public QNode(Object object, QNode next) {
					this.object = object;
					this.next = next;
				}
			}

			public void enqueue(Object anObject) {
				if (head == null) {
					head = tail = new QNode(anObject, null);
				} else {
					tail.next = new QNode(anObject, null);
					tail = tail.next;
				}
			}

			public Object dequeue() {
				if (head == null) {
					throw new NoSuchElementException("No more elements");
				}

				Object retval = head.object;
				QNode oldHead = head;
				head = head.next;
				if (head == null) {
					tail = null;
				} else {
					oldHead.next = null;
				}
				return retval;
			}

			public Object firstObject() {
				if (head == null) {
					throw new NoSuchElementException("No more elements");
				}

				return head.object;
			}

			public boolean isEmpty() {
				return head == null;
			}

		} // End of class Queue
	} // End of class BreadthFirstEnumeration
}
