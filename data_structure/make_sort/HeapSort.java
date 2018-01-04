public class HeapSort {
	static void swap(int x, int y, int [] a){
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	static int[] maxHeap(int[] a){ //최대힙 만들기
		int[] tree = new int[a.length];
		for(int i=0; i<tree.length; i++){
			tree[i] = a[i];
			if(i==0) continue;
			int parent = (i-1)/2;
			if(tree[i]>tree[parent]) swap(i, parent, tree);
		}
		return tree;
	}
	static int[] heapSort(int[] a){
		int[] sorted = new int[a.length];
		for(int i=a.length-1; i>=0; i--){
			a = maxHeap(a);
			sorted[i] = a[0];
			a[0] = 0;
		}
		return sorted;
	}
	public static void main(String[] args) {
		int[] a = {4,2,5,1,3};
		a = heapSort(a);
		for(int i=0; i<5; i++)
			System.out.print(a[i]+" ");
	}
}
