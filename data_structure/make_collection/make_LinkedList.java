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
		Node node = head;	//head���� index�� next�� �Ѿ��
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
		if(index==0) addFirst(obj); //ó���� ������ ��� addFirst�� ����
		else{
			Node node = new Node(obj); //������ ���
			Node first = get(index-1); //���� �� ��ġ�� �� ��� (�ճ���� �ϰڴ�)
			Node second = first.nextNode; //���� �� ��ġ�� �� ��� (�޳���� �ϰڴ�)
			
			first.nextNode = node; //�� ����� next�� ������ ���
			node.nextNode = second; //������ ����� next�� �޳��
			
			if(node.nextNode==null)
				tail = node; //�� ��尡 ���ٸ� node�� tail�� �Ǿ��Ѵ�
			++size;
		}
	}
	public Object removeFirst(){
		Node node = head; //������ ���� �ص���
		head = node.nextNode; //�ص���� ������ ����� �������� �ű��
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
			Node first = get(index-1); //������ ����� �ճ��
			Node delete = first.nextNode; //������ ���
			first.nextNode = delete.nextNode; //�ճ�带 ������ ��� �� ��忡 ����
			if(delete==tail) //������ ��尡 tail�̸� tail�� �ű��
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