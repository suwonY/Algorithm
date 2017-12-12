public class QuickSort {
	public void sort(int[] a, int l, int r){
      int left = l;
      int right = r;
      int pivot = a[(l+r)/2];
      do{
         while(a[left]<pivot)++left;
         while(a[right]>pivot)--right;
         if(left<=right){
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            ++left;--right;
         }
      }
      while(left<=right);
      if(left<r) sort(a,left,r);
      if(l<right) sort(a,l,right);
   }
}
