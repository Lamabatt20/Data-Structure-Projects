package Application;

import java.util.Arrays;

public class Queue {
	private int front;
	private int rear;
	int capcity;
	Object[]queue;
	public Queue(int capcity) {
		this.capcity = capcity;
		queue=new Object[capcity];
		front=rear=capcity-1;	
	}
	public boolean isEmpty(){
		return front==rear;
	}
	public int nextRear() {
		if (rear==capcity-1) {
			return 0;
		}
		return rear++;
	}
	public boolean isFull() {
		 if(nextRear()==front)
			 return true;
		 return false;
	}
	public int nextFront() {
		if (front==capcity-1)
			return 0;
		return front++;
	}
	public void enQueue(Object x) {
		if (isFull()) {
			throw new IllegalArgumentException("Queue is full");
		}
		rear=nextRear();
		queue[rear]=x;
	}
	public Object deQueue() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Queue is empty");
		}
		Object temp=queue[front];
		front=nextFront();
		return temp;
		
	}
	public void printQueue() {
		if (isEmpty()) {
			System.out.println("queue is empty");
		}
		else {
			if (front<=rear) {
				for(int i=front;i<=rear;i++)
					System.out.println(queue[i]+" ");
			}
			else {
				for(int i=0;i<=rear;i++) {
					System.out.println(queue[i]+" ");
				}
				for(int i=front;i<capcity;i++) {
					System.out.println(queue[i]+" ");
				}
			}
		}
	}
	@Override
	public String toString() {
		return "Queue [front=" + front + ", rear=" + rear + ", capcity=" + capcity + ", queue=" + Arrays.toString(queue)
				+ "]";
	}
	
	

}
