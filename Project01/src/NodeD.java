
public class NodeD<T> {
	public T val;
	public NodeD<T> next;
	public NodeD<T> prev;
	@Override
	public String toString() {
		return val.toString() + "\n";
	}

	public NodeD(T val) {
		this(val, null, null);

	}

	public NodeD(T val, NodeD<T> next) {
		this(val, next, null);

	}

	public NodeD(T val, NodeD<T> next, NodeD<T> prev) {
		this.val = val;
		this.next = next;
		this.prev = prev;

	}

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}

	public NodeD<T> getNext() {
		return next;
	}

	public void setNext(NodeD<T> next) {
		this.next = next;
	}

	public NodeD<T> getPrev() {
		return prev;
	}

	public void setPrev(NodeD<T> prev) {
		this.prev = prev;
	}

	
}
