import java.time.LocalDate;

public class LinkedList<T> {
	Node<T> first, last;
	int count = 0;

	public void addFirst(T object) {
		Node<T> node = new Node<>(object);
		if (count == 0) {
			first = last = node;
		} else {
			node.next = first;
			first = node;
		}
		count++;
	}

	public T getFirst() {
		if (count == 0) {
			return null;
		}
		return first.element;
	}

	public T getLast() {
		if (count == 0) {
			return null;
		}
		return last.element;
	}

	public void addLast(T object) {
		Node<T> node = new Node<>(object);
		if (count == 0) {
			first = last = node;
		} else {
			last.next = node;
			last = last.next;
		}
		count++;
	}

	public void print() {
		Node<T> ptr = first;
		String s = "";
		while (ptr != null) {
			s += ptr.element + "->";
			ptr = ptr.next;
		}
		System.out.println(s);
	}

	public T get(int index) {
		if (count == 0) {
			return null;
		}

		if (index < 0 || index >= count) {
			return null;
		} else if (index == 0)
			return getFirst();
		else if (index == count - 1)
			return getLast();
		else {
			Node<T> current = first;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}

			return current.element;
		}
	}
	

	private void add(int index, T element) {
		if (index <= 0) {
			addFirst(element);
		} else if (index >= count) {
			addLast(element);
		} else {
			Node<T> current = first;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			Node<T> newNode = new Node<>(element);
			newNode.next = current.next;
			current.next = newNode;
			count++;
		}
	}

	public boolean removeFirst() {
		if (first == null) {
			return false;
		}
		// Check if we have one element only
		if (first == last) {
			first = last = null;
			return true;
		}
		first = first.next;
		count--;
		return true;
	}

	public boolean removeLast() {
		if (first == null) {
			return false;
		}
		// Check if we have one element only
		if (first == last) {
			first = last = null;
			return true;
		}
		Node<T> current = first;
		for (int i = 0; i < count - 2; i++) {
			current = current.next;
		}
		current.next = null;
		last = current;
		count--;
		return true;
	}

	public boolean remove(int index) {
		if (index <= 0) {
			return removeFirst();
		} else if (index >= count) {
			return removeLast();
		} else {
			Node<T> current = first;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			Node<T> temp = current.next;
			current.next = temp.next;
			count--;
			return false;
		}
	}

	/*public boolean remove(T element) {
	    if (first==null) {
	        return false;
	    }
	    if (first.getElement().equals(element)) {
	        removeFirst();
	        return true;
	    }

	    Node<T> current = first;
	    Node<T> previous = null;
	    while (current != null && !current.getElement().equals(element)) {
	        previous = current;
	        current = current.getNext();
	    }
	    if (current != null) {
	        previous.setNext(current.getNext());
	        current.setNext(null);
	        count--;
	        return true;
	    }

	    return false;
	}*/
	 public void remove(T data) {
	        if (first == null) {
	            System.out.println("List is empty");
	            return;
	        }

	        if (first.element.equals(data)) {
	            first = first.next;
	            return;
	        }

	        Node<T> current = first;
	        Node<T> previous = null;

	        while (current != null && !current.element.equals(data)) {
	            previous = current;
	            current = current.next;
	        }

	        if (current == null) {
	            System.out.println("Element not found in the list");
	            return;
	        }

	        previous.next = current.next;
	    }
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setFirst(Node<T> first) {
		this.first = first;
	}

	public void setLast(Node<T> last) {
		this.last = last;
	}

	public T find(T element) {
		Node<T> current = first;
		while (current != null && current.element.equals(element)) {
			current = current.next;
		}
		if (current == null) {
			return null;
		}
		return current.element;
	}

	public void addSorted(T element) {
		Comparable<T> c = (Comparable<T>) element;
		if (first == null) {
			this.addFirst(element);
		} else if (c.compareTo(first.element) <= 0) {
			this.addFirst(element);
		} else {
			Node<T> newNode = new Node<>(element);
			Node<T> current = first;

			while (current.next != null && c.compareTo((T) current.next.element) > 0) {
				current = current.next;
			}

			newNode.next = current.next;
			current.next = newNode;
			count++;
		}

	}

	public boolean isFindm(int month) throws Exception {
		Node<T> node = first;
		for (int i = 0; i < count; i++) {
			if (((Month) node.element).getMonth() == month) {
				return true;
			}
			node = node.next;
		}

		return false;
	}
	
   
}

