public class MergeSort {
	public static void mergeSort(int[] a){
		if(a.length==1) return; //배열의 크기가 1이 될 때까지 분할

		int len = a.length;
		
		int[] temp1 = new int[len/2];
		int[] temp2 = new int[len-len/2];
		
		for(int i=0; i<temp1.length; i++)
			temp1[i] = a[i];
		
		for(int i=0; i!=temp2.length; i++)
			temp2[i] = a[i+len/2];
		
		mergeSort(temp1);
		mergeSort(temp2);
		
		merge(temp1, temp2, a);
		
	}
	public static void merge(int[] a, int[] b, int[] c){
		int x = 0, y = 0, z = 0; //a, b, c 의 index
		
		while(x != a.length){ //a의 index가 배열의 끝이 아닐 때까지
			
			if(y != b.length){ //b의 index가 배열의 끝이 아닐 때까지
				
				if(a[x] < b[y] ) //x index의 a배열 값이 y index의 b배열 값보다 작을 경우
					c[z++] = a[x++];
				else	//x index의 a배열 값이 y index의 b배열 값보다 클 경우
					c[z++] = b[y++];
			}
			
			else{
				while(x != a.length)
					c[z++] = a[x++];
			}
		}
		
		while(y < b.length)
			c[z++] = b[y++];
	}
	public static void main(String[] args) {
		int[] a = {4,3,5,2,1};
		mergeSort(a);
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+" ");
	}
}