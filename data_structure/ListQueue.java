public class ListQueue {
	private class Node{
		private Object obj;
		private Node nextNode;
		
		private Node(Object obj){
			this.obj = obj;
			this.nextNode = null;
		}
	}
	private Node front;
	private Node rear;
	
	public ListQueue(){
		this.front = null;
		this.rear = null;
	}
	
	public void add(Object obj){
		Node node = new Node(obj);
		node.nextNode = null;
		if(isEmpty()){
			rear = node;
			front = node;
		}
		else{
			rear.nextNode = node;
			rear = node;
		}
	}
	
	public Object peek(){
		return front.obj;
	}
	
	public Object remove(){
		Object obj = front.obj;
		front = front.nextNode;
		if(front==null) rear = null;
		return obj;
	}
	
	public boolean isEmpty(){
		return front==null;
	}
}