package Application;

import javafx.scene.Node;

public class DLL {
	NodeDL first, last;
	int count = 0;

	NodeDL getfirst() {
		return first;
	}

	NodeDL getLast() {
		return last;
	}

	void addLast(Brand element) {
		NodeDL newNode = new NodeDL(element);
		if (count == 0) {
			first = last = newNode;
		} else {
			
			newNode.prev = last;
			last.next = newNode;
			last=newNode;
		}
		count++;

	}

	void addFirst(Brand element) {
		NodeDL newNode = new NodeDL(element);
		if (count == 0) {
			first = last = newNode;
		} else {
			newNode.next = first;
			newNode.prev=last;
			first.prev=last.next=newNode;
			first=newNode;
		}
		count++;
	}

	public boolean removeLast() {
		if (count == 0) {
			return false;
		}
		if (count == 1) {
			first = last = null;
		} else {
			NodeDL current = first;
			for (int i = 0; i < count; i++) {
				current = current.getNext();
			}
			last.setPrev(current.getPrev());
			last = current;
			last.setNext(null);
		}
		count--;
		return true;
	}

	public boolean removeFirst() {
		if (count == 0) {
			return false;
		} else {
			NodeDL temp = first;
			first = first.next;
			first.prev = null;
			temp.next = null;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		NodeDL current = first;
		NodeDL prev = null;
		if (index == 1) {
			return removeFirst();
		} else if (index == count) {
			return removeLast();
		} else if (index <= 0 || index > count) {
			return false;
		} else {
			for (int i = 0; i < index - 1; i++)
				prev = current;
			for (int i = 1; i < index; i++) {
				prev = current;
				current = current.next;
			}
			prev.next = current.next;
			current.next = null;
			current.prev = null;
			count--;
			return true;
		}
	}

	public boolean remove(Brand element) {
		if (count == 0)
			return false;
		if (element.equals(first.getElement()))
			return removeFirst();
		if (element.equals(last.getElement()))
			return removeLast();
		else {
			NodeDL current = first.getNext();
			for (int i = 1; i < count - 1; i++) {
				if (current.getElement().equals(element))
					return remove(i);
				current = current.getNext();
			}
			return false;
		}
	}

	public void printList() {
		NodeDL current = first;
		if (count == 0)
			return;
		for (int i = 0; i < count; i++) {
			System.out.println(current.toString());
			current = current.next;
		}
	}

	public int getSize() {
		return count;
	}

	public boolean isFind(String brand) throws Exception {
		NodeDL node = first;
		for (int i = 0; i < getSize(); i++) {
			if ( node.getElement().getBrand().equals(brand)) {
				return true;
			}
			node = node.next;
		}

		return false;
	}
	public NodeSL found(String brand) throws Exception {
			NodeDL node = first;
			for (int i = 0; i < getSize(); i++) {
				if ( node.getElement().getBrand().equals(brand)) {
					
				   System.out.println(node.getElement().getList());
					return node.element.list.first;
					
				}
			}
		return null;
			
	}

	public void add(Brand element, int index) {
		NodeDL newnode = new NodeDL(element);
		NodeDL current = first;
		if (count == 0)
			addFirst( element);
		else if (index == count)
			addLast( element);
		else {
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			newnode.next = current.next;
			current.next = newnode;
			newnode.prev = current;
			newnode.next.prev = newnode;
			count++;
		}

	}
}



