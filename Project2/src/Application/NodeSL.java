package Application;

public class NodeSL {
	Object element;
	NodeSL next;

	NodeSL(Object element2) {
		this.element = element2;
	}
	NodeSL() {
		this.element = element;
	}

	@Override
	public String toString() {
		return element + " ";
	}

	public NodeSL getNext() {
		return null;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(CarInfo element) {
		this.element = element;
	}

	public void setNext(NodeSL next) {
		this.next = next;
	}
	

}
