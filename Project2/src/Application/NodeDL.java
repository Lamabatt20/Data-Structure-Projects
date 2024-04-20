package Application;

public class NodeDL {
	Brand element;
	NodeDL next, prev;
	NodeDL(Brand element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return element.toString() + "\n";
	}

	public NodeDL getNext() {
		return null;
	}

	public Brand getElement() {
		return element;
	}

	public NodeDL getPrev() {
		return prev;
	}

	public void setPrev(NodeDL prev) {
		this.prev = prev;
	}

	public void setElement(Brand element) {
		this.element = element;
	}

	public void setNext(NodeDL next) {
		this.next = next;
	}
	
}







	





