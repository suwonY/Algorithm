public class ListStack {
	private class Node{
		private Object obj;
		private Node nextNode;
		private Node(Object obj){
			this.obj = obj;
			this.nextNode = null;
		}
	}
	private Node top;
	public ListStack(){
		this.top = null;
	}
	public void push(Object obj){
		Node node = new Node(obj);
		node.nextNode = top;
		top = node;
	}
	public Object peek(){
        return top.obj;
	}
	public Object pop(){
		Object obj = top.obj;
		top = top.nextNode;
		return obj;
	}
	public boolean isEmpty(){
		return top==null;
	}
}
