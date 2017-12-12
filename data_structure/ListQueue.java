public class ListQueue {
	private class Node{
		private Object data;
		private Node nextNode;
		
		Node(Object data){
			this.data = data;
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
		if(isEmpty()){ //ù ����
			rear = node;
			front = node;
		}
		else{ //�ڿ� ���Ե� ����
			rear.nextNode = node;
			rear = node;
		}
	}
	
	public Object peek(){
		return front.data;
	}
	
	public Object remove(){
		Object obj = front.data;
		front = front.nextNode;
		if(front==null) rear = null;
		
		return obj;
	}
	
	public boolean isEmpty(){
		return front==null;
	}
	
}
