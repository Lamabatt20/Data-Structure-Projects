public class TreeNode<T> {
	T data;
	TreeNode<T> left;
	TreeNode<T> right;
	int Height;

	public TreeNode(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
