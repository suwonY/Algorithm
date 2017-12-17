public class make_DoubleLinkedList {
	private class Node{
		private Object obj;
		private Node nextNode, prevNode;
		private Node(Object obj){
			this.obj = obj;
			nextNode = prevNode = null;
		}
	}
	private Node head, tail;
	private int size;
	public make_DoubleLinkedList(){
		head = tail = null;
		size = 0;
	}
	public void addFirst(Object obj){
		Node node = new Node(obj);
		if(size==0)
			head = tail = node;
		else{
			node.nextNode = head;
			head.prevNode = node;
			head = node;
		}
		++size;
	}
	public void addLast(Object obj){
		Node node = new Node(obj);
		if(size==0)
			head = tail = node;
		else{
			tail.nextNode = node;
			node.prevNode = tail;
			tail = node;
		}
		++size;
	}
	public Node get(int index){
		Node node = head;
		for(int i=0; i<index; i++)
			node = node.nextNode;
		return node;
	}
	public void add(int index, Object obj){
		if(index==0) addFirst(obj);
		if(index==size-1) addLast(obj);
		else{
			Node node = new Node(obj);
			Node first = get(index-1);
			Node second = first.nextNode;
			
			first.nextNode = node;
			second.prevNode = node;
			
			node.nextNode = second;
			node.prevNode = first;
			if(node.nextNode==null)
				tail = node;
			++size;
		}
	}
	public Object getFirst(){
		return head.obj;
	}
	public Object getLast(){
		return tail.obj;
	}
	public Object removeFirst(){
		Node node = head;
		head = node.nextNode;
		--size;
		return node.obj;
	}
	public Object removeLast(){
		Node node = tail;
		tail = node.prevNode;
		--size;
		return node.obj;
	}
	public Object remove(int index){
		if(index==0) removeFirst();
		if(index==size-1) removeLast();
		Node first = get(index-1);
		Node delete = first.nextNode;
		Node second = delete.nextNode;
		
		first.nextNode = second;
		second.prevNode = first;
		--size;
		return delete.obj;
	}
	public int indexOf(Object obj){
		Node node = head;
		for(int i=0; i<size; i++){
			if(node.obj==obj)
				return i;
			node = node.nextNode;
		}
		return -1;
	}
	public int size(){
		return size;
	}
}
