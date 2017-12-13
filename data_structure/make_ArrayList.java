public class make_ArrayList {
	private Object[] objs;
	private int index;
	private int capacity, amount;

	public make_ArrayList() { // 기본 생성자 10크기
		this(10, 10);
	}

	public make_ArrayList(int capacity, int amount) { // 배열크기와 늘릴크기 정하기
		this.capacity = capacity;
		this.amount = amount;
		objs = new Object[capacity];
		index = 0;
	}

	public void add(Object obj) { // 추가 함수
		if (index >= capacity) {
			Object[] temp = new Object[capacity + amount];
			for (int i = 0; i < capacity; i++)
				temp[i] = objs[i];
			capacity += amount;
			objs = temp;
		}
		objs[index++] = obj;
	}

	public void add(Object obj, int idx) {
		if (index >= capacity) {
			Object[] temp = new Object[capacity + amount];
			for (int i = 0; i < capacity; i++)
				temp[i] = objs[i];
			capacity += amount;
			objs = temp;
		}
		for(int i=index; i>idx; i--)
			objs[i] = objs[i-1];
		objs[idx] = obj;
		++index;
	}

	public Object get(int index) { // 해당 index가져오기
		return objs[index];
	}

	public int size() {
		return index;
	}

	public Object remove() { // 마지막 index 삭제
		if (index == 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return objs[--index];
	}

	public Object indexOf(Object obj) {
		for (int i = 0; i < index; i++)
			if (objs[i] == obj)
				return i;
		return null;
	}

	public Object lastIndexOf(Object obj) {
		for (int i = index - 1; i >= 0; i--)
			if (objs[i] == obj)
				return i;
		return null;
	}
}