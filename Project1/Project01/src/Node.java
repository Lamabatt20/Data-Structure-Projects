public class Node<T> {
	T element;
	Node next;
	

	Node(T element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return element + " ";
	}

	public Node getNext() {
		return null;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}

