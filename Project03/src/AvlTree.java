import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.TextArea;

public class AvlTree<T> {

	TreeNode<T> root;
	private Comparator<T> comparator;

	public AvlTree() {
		root = null;
	}

	public AvlTree(Comparator<T> comparator) {
		this.comparator = comparator;
		root = null;
	}

	private int getCompareToValue(T element, T ptrValue) {
		if (comparator != null) {
			return comparator.compare(element, ptrValue);
		}

		Comparable<T> comparable = (Comparable<T>) element;
		return comparable.compareTo(ptrValue);
	}

	private int height(TreeNode<T> curr) {
		if (curr == null)
			return 0;
		else
			return Math.max(1 + height(curr.left), 1 + height(curr.right));
	}

	public int height() {
		return height(root);
	}

	public TreeNode<T> rotateRight(TreeNode<T> k2) {
		TreeNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k1.Height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.Height = Math.max(height(k2.left), height(k2.right)) + 1;
		return k1;
	}

	public TreeNode<T> rotateLeft(TreeNode<T> k1) {
		TreeNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.Height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.Height = Math.max(height(k2.left), height(k2.right)) + 1;
		return k2;
	}

	public TreeNode<T> doubleRotateRight(TreeNode<T> k3) {
		k3.left = rotateLeft(k3.left);
		return rotateRight(k3);
	}

	public TreeNode<T> doubleRotateLeft(TreeNode<T> k3) {
		k3.right = rotateRight(k3.right);
		return rotateLeft(k3);
	}

	private TreeNode<T> insert(TreeNode<T> ptr, T element) {
		if (ptr == null) {
			ptr = new TreeNode<>(element);
			ptr.Height = Math.max(height(ptr.left), height(ptr.right)) + 1;
			return ptr;
		}
		if (getCompareToValue(element, ptr.data) < 0) {
			ptr.left = insert(ptr.left, element);
		} else {
			ptr.right = insert(ptr.right, element);
		}

		ptr.Height = Math.max(height(ptr.left), height(ptr.right)) + 1;
		ptr = rebalance(ptr);
		return ptr;
	}

	public void insert(T element) {
		root = insert(root, element);
	}

	public void delete(T currentDay) {
		root = delete(root, currentDay);
	}

	public void print() {
		print(root);
	}

	private void print(TreeNode<T> root) {
		if (root == null) {
			return;
		}
		print(root.left);
		System.out.print(root.data + " ");
		print(root.right);
	}

	private TreeNode<T> rebalance(TreeNode<T> ptr) {
		if (ptr == null) {
			return null;
		}
		int balance = Math.abs(height(ptr.left) - height(ptr.right));
		if (balance <= 1) {
			return ptr;
		}
		int leftHeight = height(ptr.left);
		int rightHeight = height(ptr.right);

		if (rightHeight > leftHeight) {
			TreeNode<T> right = ptr.right;
			leftHeight = height(right.left);
			rightHeight = height(right.right);
			if (rightHeight >= leftHeight) {
				return rotateLeft(ptr);
			}

			return doubleRotateLeft(ptr);
		}
		TreeNode<T> left = ptr.left;
		leftHeight = height(left.left);
		rightHeight = height(left.right);
		if (leftHeight >= rightHeight) {
			return rotateRight(ptr);
		}

		return doubleRotateRight(ptr);
	}

	private TreeNode<T> delete(TreeNode<T> ptr, T currentDay) {
		if (ptr == null) {
			return null;
		}

		if (getCompareToValue(currentDay, ptr.data) < 0) {
			ptr.left = delete(ptr.left, currentDay);
		} else if (getCompareToValue(currentDay, ptr.data) > 0) {
			ptr.right = delete(ptr.right, currentDay);
		} else {
			if (currentDay == null) {
				if (ptr.left == null || ptr.right == null) {
					ptr = (ptr.left == null) ? ptr.right : ptr.left;
				} else {
					T minRight = getMin(ptr.right);
					ptr.data = minRight;
					ptr.right = delete(ptr.right, minRight);
				}
			} else {

			}
		}

		ptr.Height = Math.max(height(ptr.left), height(ptr.right)) + 1;
		ptr = rebalance(ptr);
		return ptr;
	}

	public T getMin() {
		return getMin(root);
	}

	private T getMin(TreeNode<T> ptr) {
		if (ptr == null) {
			return null;
		}
		if (ptr.left != null) {
			return getMin(ptr.left);
		}
		return ptr.data;
	}

	public T getMax() {
		return getMax(root);
	}

	private T getMax(TreeNode<T> ptr) {
		if (ptr == null) {
			return null;
		}

		if (ptr.right != null) {
			return getMax(ptr.right);
		}

		return ptr.data;
	}
// find node data 
	private TreeNode<T> find(TreeNode<T> ptr, T element) {
		if (ptr == null) {
			return null;
		}
		if (getCompareToValue(element, ptr.data) < 0) {
			return find(ptr.left, element);
		}
		if (getCompareToValue(element, ptr.data) > 0) {
			return find(ptr.right, element);
		}
		return ptr;
	}

	public TreeNode<T> find(T element) {
		return find(root, element);
	}

	public int heighte(TreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return Math.max(heighte(node.left), heighte(node.right)) + 1;
	}
// get node 
	private TreeNode<T> getNode(TreeNode<T> root, int index) {
		int left = height(root.left);
		if (index < left) {
			return root;
		} else if (index < left) {
			return getNode(root.left, index);
		} else {
			return getNode(root.right, index - left);
		}
	}

	public T get(int index) {
		if (root == null || index < 0) {
			return null;
		}
		int nodeheight = height(root) + 1;
		if (index >= nodeheight) {
			return null;
		}
		TreeNode<T> result = getNode(root, index);
		if (result != null) {
			return result.data;
		} else {
			return null;
		}
	}
//print trees in order
	public void traverseInOrder() {
		traverseInOrder(root);
	}

	public void traverseInOrder(TreeNode<T> node) {
		if (node != null) {
			if (node.left != null)
				traverseInOrder(node.left);
			System.out.print(node + " ");
			if (node.right != null)
				traverseInOrder(node.right);
		}
	}
//print trees level by level 
	private void traverseLevel(TreeNode<T> node, int level, StringBuilder result) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			result.append(node.data).append("  ");
		} else if (level > 1) {
			traverseLevel(node.left, level - 1, result);
			traverseLevel(node.right, level - 1, result);
		}
	}

	public void traverseLevell(StringBuilder result) {
		int height = height(root);
		for (int level = 1; level <= height; level++) {
			traverseLevel(root, level, result);
			result.append("\n");
		}
	}

}
