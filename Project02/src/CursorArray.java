

public class CursorArray<T extends Comparable<T>> {
	private int size = 10;

	Node<T>[] arr;

	public CursorArray(int size) {
		super();
		this.size = size;
		arr = new Node[size];
		initialization();
	}

	public CursorArray() {
		arr = new Node[size];
		initialization();
	}

	private void initialization() {
		for (int i = 0; i < arr.length - 1; i++)
			arr[i] = new Node<T>(null, i + 1, -1);

		arr[arr.length - 1] = new Node<>(null, 0, -1);
	}

	public int malloc() {
		int i = arr[0].getNext();
		arr[0].setNext(arr[i].getNext());
		arr[i].setPrev(-1);
		return i;
	}

	public void free(int p) {
		arr[p] = new Node<T>(null, arr[0].getNext(), -1);
		arr[0].setNext(p);
	}

	public boolean isNull(int i) {
		return arr[i] == null;
	}

	public boolean isEmpty(int l) {
		return arr[l].getNext() == 0;
	}

	public boolean isLast(int p) {
		return arr[p].getNext() == 0;
	}

	public int createList() {
		int p = malloc();
		if (p != 0) {
			arr[p].setNext(0);
			arr[p].setPrev(-1);
		} else {
			p = -1;
		}
		return p;
	}

	public void insertFirst(T data, int l) throws IndexOutOfBoundsException {
		if (isNull(l))
			return;
		int p = malloc();
		if (p != 0) {
			arr[p] = new Node<>(data, arr[l].getNext(), -1);
			arr[l].setNext(p);
			arr[arr[p].getNext()].setPrev(p);
		} else
			throw new IndexOutOfBoundsException("cursor array is Full");
	}

	public T deleteFirst(int l) {
		if (!isNull(l) && !isEmpty(l)) {
			int c = arr[l].getNext();
			Node<T> temp = arr[c];
			arr[l].setNext(temp.getNext());
			arr[arr[l].getNext()].setPrev(l);
			free(c);
			return temp.getData();
		}
		return null;
	}

	public T getFirst(int l) {
		int c = arr[l].getNext();
		Node<T> temp = arr[c];
		return temp.getData();
	}

	public int getSize() {
		return size;
	}

	public void clear(int l) {
		while (!isEmpty(l)) {
			deleteFirst(l);
		}
	}

	public void print(int l) {
		int ptr = arr[l].next;
		String s = "";
		while (ptr != 0) {
			s += arr[ptr].data + " -> ";
			ptr = arr[ptr].next;

		}
		s += "null";
		System.out.println(s);
	}
}