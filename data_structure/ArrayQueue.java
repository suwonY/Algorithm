public class ArrayQueue {
	private int front, rear;
	private Object[] queue;
	public ArrayQueue(int size){
		queue = new Object[size];
		front = rear = 0;
	}
	public void add(Object obj){
		queue[rear++]=obj;
	}
	public Object peek(){
		return queue[front];
	}
	public Object remove(){
		return queue[front++];
	}
	public boolean isEmpty(){
		return front==rear;
	}
}
