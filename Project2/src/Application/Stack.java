package Application;

public class Stack {
	Object[]s;
	final static int SIZE=100;
	int top=-1;
	public Stack() {
		this(SIZE);
	}
	public Stack (int size) {
		s=new Object[size];
	}
	public boolean isEmpty() {
		return top==-1;
	}
	public boolean isFull() {
		return top==SIZE;
	}
	public void push(Object data) {
		if(!isFull()) {
			s[++top]=data;
		}
	}
	public Object peek() {
		if(!isEmpty()) {
			return s[top];
		}
		return null;
	}
	public Object pop(){
		if(isEmpty()) {
			throw new IllegalArgumentException("stack is Empty");
		}
		Object temp=s[top--];
		return  temp;
		}
	public void displayStack(){
	System.out.print( peek());
	System.out.print(" ");
	}

}
