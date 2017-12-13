public class make_ArrayList {
	private Object[] objs;
	private int index;
	private int capacity, amount;

	public make_ArrayList() { // �⺻ ������ 10ũ��
		this(10, 10);
	}

	public make_ArrayList(int capacity, int amount) { // �迭ũ��� �ø�ũ�� ���ϱ�
		this.capacity = capacity;
		this.amount = amount;
		objs = new Object[capacity];
		index = 0;
	}

	public void add(Object obj) { // �߰� �Լ�
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

	public Object get(int index) { // �ش� index��������
		return objs[index];
	}

	public int size() {
		return index;
	}

	public Object remove() { // ������ index ����
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