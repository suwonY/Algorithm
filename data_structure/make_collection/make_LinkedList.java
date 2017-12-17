public class make_LinkedList {
	private class Node{
		Object obj;
		Node nextNode;
		private Node(Object obj){
			this.obj = obj;
			nextNode = null;
		}
	}
	private Node head;
	private Node tail;
	private int size;
	public make_LinkedList(){
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public void addFirst(Object obj){
		Node node = new Node(obj);
		if(size==0){
			head = node;
			tail = node;
		}
		else{
			node.nextNode = head;
			head = node;
		}
		++size;
	}
	public void addLast(Object obj){
		Node node = new Node(obj);
		if(size==0){
			head = node;
			tail = node;
		}
		else{
			tail.nextNode = node;
			tail = node;
		}
		++size;
	}
	public Node get(int index){
		Node node = head;	//head부터 index번 next로 넘어가기
		for(int i=0; i<index; i++)
			node = node.nextNode;
		return node;		
	}
	public Object getFirst(){
		return head.obj;
	}
	public Object getLast(){
		return tail.obj;
	}
	public void add(int index, Object obj){
		if(index==0) addFirst(obj); //처음에 삽입할 경우 addFirst와 같다
		else{
			Node node = new Node(obj); //삽입할 노드
			Node first = get(index-1); //삽입 할 위치의 앞 노드 (앞노드라고 하겠다)
			Node second = first.nextNode; //삽입 할 위치의 뒷 노드 (뒷노드라고 하겠다)
			
			first.nextNode = node; //앞 노드의 next는 삽입할 노드
			node.nextNode = second; //삽입할 노드의 next는 뒷노드
			
			if(node.nextNode==null)
				tail = node; //뒷 노드가 없다면 node가 tail이 되야한다
			++size;
		}
	}
	public Object removeFirst(){
		Node node = head; //삭제할 노드는 해드노드
		head = node.nextNode; //해드노드는 삭제할 노드의 다음노드로 옮긴다
		--size;
		return node.obj;
	}
	
	public Object removeLast(){
		Node node = tail;
		tail = get(size-2);
		--size;
		return node.obj;
	}
	public Object remove(int index){
		if(index==0) return removeFirst();
		else{
			Node first = get(index-1); //삭제할 노드의 앞노드
			Node delete = first.nextNode; //삭제할 노드
			first.nextNode = delete.nextNode; //앞노드를 삭제할 노드 뒷 노드에 연결
			if(delete==tail) //삭제될 노드가 tail이면 tail을 옮기기
				tail = first;
			--size;
			return delete;
		}
	}
	public int size(){
		return size;
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
}