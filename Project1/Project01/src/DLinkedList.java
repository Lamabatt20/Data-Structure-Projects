

public class DLinkedList<T> {
	NodeD<T> first;
	NodeD<T> last;
	 int size;
	public DLinkedList() {
		first = last = null;
		 size = 0;
	}
	
	public NodeD<T> getFirst() {
		return first;
	}

	public void setFirst(NodeD<T> first) {
		this.first = first;
	}

	public NodeD<T> getLast() {
		return last;
	}

	public void setLast(NodeD<T> last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void addFirst(T element) {
		 NodeD <T> node = new NodeD<>(element);
		 if (size == 0) {
		 first = last = node;
		 } else {
		 node.next = first;
		 first.prev = node;
		 first = node;
		 }
		 size++;
		}
	void addLast(T element) {
		NodeD<T> newNode = new NodeD<T> (element);
		if (size == 0) {
			first = last = newNode;
		} else {
			last.next = newNode;
			newNode.prev = last;
			last = newNode;
		}
		size++;

	}
	public boolean removeLast() {
		if (size == 0) {
			return false;
		}
		if (size == 1) {
			first = last = null;
		} else {
			NodeD<T> current = first;
			for (int i = 0; i < size; i++) {
				current = current.next;
			}
			last.setPrev(current.prev);
			last = current;
			last.setNext(null);
		}
		size--;
		return true;
	}

	public boolean removeFirst() {
		if (size == 0) {
			return false;
		} else {
			NodeD<T> temp = first;
			first = first.next;
			first.prev = null;
			temp.next = null;
		}
		size--;
		return true;
	}

	public boolean remove(int index) {
		NodeD<T> current = first;
		NodeD<T> prev = null;
		if (index == 1) {
			return removeFirst();
		} else if (index == size) {
			return removeLast();
		} else if (index <= 0 || index > size) {
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
			size--;
			return true;
		}
	}

	public boolean remove(T element) {
		if (size == 0)
			return false;
		if (element.equals(first.val))
			return removeFirst();
		if (element.equals(last.val))
			return removeLast();
		else {
			NodeD<T> current = first.getNext();
			for (int i = 1; i < size - 1; i++) {
				if (current.val.equals(element))
					return remove(i);
				current = current.getNext();
			}
			return false;
		}
	}

	public void printList() {
		NodeD<T>ptr=first;
		String s="";
		while ( ptr!=null) {
			s+=ptr.val+"<->";
			ptr=ptr.next;
		}
		System.out.println(s);
	}

	public boolean isFind(int year) throws Exception {
		NodeD<T> node = first;
		for (int i = 0; i < size; i++) {
			if (((Year)node.val).getYear()==year) {
				return true;
			}
			node = node.next;
		}

		return false;
	}
	public T get(int index) {
		 if (size == 0) {
		 return null;
		 }
		 if (index < 0 || index >= size) {
		 return null;
		 }
		 NodeD<T> current = first;
		 for (int i = 0; i < index; i++) {
		 current = current.next;
		 }
		 return current.val;
		}
	public T find(T element) {
		 NodeD<T> current = first;
		 while (current != null && current.val.equals(element)) {
		 current = current.next;
		 }
		 if (current == null) {
		 return null;
		 }
		 return current.val;
	}
	public void add(T element, int index) {
		NodeD<T> newnode = new NodeD<T>(element);
		NodeD<T> current = first;
		if (size == 0)
			addFirst( element);
		else if (index == size)
			addLast( element);
		else {
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			newnode.next = current.next;
			current.next = newnode;
			newnode.prev = current;
			newnode.next.prev = newnode;
			size++;
		}

	}
	public void addSorted(T element) {
		Comparable<T> c = (Comparable<T>) element;
		if (first == null) {
			this.addFirst(element);
		} else if (c.compareTo(first.getVal()) <= 0) {
			this.addFirst(element);
		} else {
			NodeD<T> newNode = new NodeD<>(element);
			NodeD<T> current = first;

			while (current.next != null && c.compareTo((T) current.next.getVal()) > 0) {
				current = current.next;
			}

			newNode.next = current.next;
			current.next = newNode;
			size++;
		}

	}
	
	 
}
