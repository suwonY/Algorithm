public class ArrayStack {
	private int top;
	private Object[] stack;
	
	public ArrayStack(int size){
		stack = new Object[size];
		top = 0;
	}
	public void push(Object obj){
		stack[top++] = obj;
	}
	public Object pop(){
		return stack[--top];
	}
	public Object peek(){
		return stack[top-1];
	}
	public boolean isEmpty(){
		return top<0;
	}
}
