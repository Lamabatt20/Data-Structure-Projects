package Application;

public class SLL {
	NodeSL first;
	NodeSL last;
	int count = 0;

	NodeSL getfirst() {
		return first;
	}

	NodeSL getLast() {
		return last;
	}

	void addLast(Object element) {
		if (count == 0) {
			first = last = new NodeSL(element);
		} else {
			NodeSL temp = new NodeSL(element);
			last.setNext(temp);
			last = temp;
		}
		count++;
	}

	void addFirst(Object element) {
		if (first == null) {
			first = last = new NodeSL(element);
		} else {
			NodeSL temp = new NodeSL(element);
			temp.setNext(first);
			first = temp;
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
			NodeSL current = first;
			while(current.next.next!=null)
				current = current.next;
			last = current;
			last.next=null;
		}
		count--;
		return true;
	}

	public boolean removeFirst() {
		if (count == 0) {
			return false;
		}
		if (count == 1) {
			first = last = null;
		} else {
			NodeSL current = first;
			first = first.getNext();
			current = null;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		NodeSL prev = first;
		if (count == 1) {
			return removeFirst();
		}
		if (index == count) {
			return removeLast();
		}
		if (index <= 0 || index > count) {
			return false;
		} else {
			NodeSL current = first.getNext();
			for (int i = 1; i < index; i++) {
				prev = current;
				current = current.getNext();
			}
			prev.setNext(current.getNext());
			current.setNext(null);
			count--;
			return true;
		}
	}

	public boolean remove(Object element) {
		if (count == 0)
			return false;
		if (element.equals(first.getElement()))
			return removeFirst();
		if (element.equals(last.getElement()))
			return removeLast();
		else {
			NodeSL current = first.getNext();
			for (int i = 1; i < count - 1; i++) {
				System.out.println(i + "  " + current.getElement());
				if (current.getElement().equals(element))
					return remove(i);
				current = current.getNext();
			}
			return false;
		}
	}

	public void printList() {
		NodeSL current = first;
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

	void add(Object element, int index) {
		if (count == 0) {
			first = last = new NodeSL(element);
			addFirst(element);
		} else if (index == count) {
			addLast(element);
		} else {
			NodeSL current = first;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			NodeSL temp = new NodeSL(element);
			temp.next = current.next;
			current.next = temp;
			{
				count++;
			}

		}

	}

}
