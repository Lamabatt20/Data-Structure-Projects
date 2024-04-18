
public class Stack<T extends Comparable<T>> {
	private CursorArray<T> cursor = new CursorArray<>(60);
	private int l;

	public Stack() {
		l = cursor.createList();
	}

	public void push(T data) throws IndexOutOfBoundsException {
		cursor.insertFirst(data, l);
	}

	public T pop() {
		return cursor.deleteFirst(l);
	}

	public T peek() {
		return cursor.getFirst(l);
	}

	public boolean isEmpty() {
		return cursor.isEmpty(l);
	}

	public void clear() {
		cursor = new CursorArray<>(l);

	}

	public void Print() {
		cursor.print(l);
	}
	
	

}