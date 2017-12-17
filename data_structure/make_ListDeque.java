public class make_ListDeque {
	private class Node{
		Object obj;
		Node left, right;
		private Node(Object obj){
			this.obj = obj;
			left = right = null;
		}
	}
	
	private Node front, rear;
	private int size;
	
	public make_ListDeque(){
		front = rear = null;
		size = 0;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object obj){
		Node node = new Node(obj);
		if(size==0)
			front = rear = node;
		if(size==1){
			front.left = node;
			rear.left = node;
			node.right = front;
			front = node;
		}
		else{
			front.left = node;
			node.right = front;
			front = node;
		}
		++size;
	}
	
	public void addLast(Object obj){
		Node node = new Node(obj);
		if(size==0)
			front = rear = null;
		else if(size==1){
			front.right = node;
			rear.right = node;
			node.left = front;
			rear = node;
		}
		else {
			node.left = rear;
			rear.right = node;
			rear = node;
		}
		++size;
	}
	public Object removeFirst(){
		Node node = front;
		front = node.right;
		if(front!=null)
			front.left = null;
		--size;
		return node.obj;
	}
	
	public Object removeLast(){
		Node node = rear;
		rear = node.left;
		if(rear==null) front = null;
		rear.right = null;
		--size;
		return node.obj;
	}
	
	public Object peekFirst(){
		return front.obj;
	}
	
	public Object peekLast(){
		return rear.obj;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
}
