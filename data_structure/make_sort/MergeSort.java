public class MergeSort {
	public static void mergeSort(int[] a){
		if(a.length==1) return; //�迭�� ũ�Ⱑ 1�� �� ������ ����

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
		int x = 0, y = 0, z = 0; //a, b, c �� index
		
		while(x != a.length){ //a�� index�� �迭�� ���� �ƴ� ������
			
			if(y != b.length){ //b�� index�� �迭�� ���� �ƴ� ������
				
				if(a[x] < b[y] ) //x index�� a�迭 ���� y index�� b�迭 ������ ���� ���
					c[z++] = a[x++];
				else	//x index�� a�迭 ���� y index�� b�迭 ������ Ŭ ���
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