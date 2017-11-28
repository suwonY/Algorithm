package week_13th_mon;

import java.util.*;
public class Baek5397 {
	/*다르게 풀자 그냥 짜증난다
	 * */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=0; t<tc; t++){
			String l = in.next();
			char[] a = l.toCharArray();
			int index = 0;
			int size = a.length-1;
			char[] ans = new char[size+1];
			
			for(int i=0; i<size+1; i++){
				if(a[i]=='<'){
					--index;
					if(index<0) index = 0;
				}
				else if(a[i]=='>'){
					++index;
					if(index>size) index = size;
				}
				else if(a[i]=='-'){
					if(index==0) continue;
					--index;
					ans[index] = '\0';
				}
				else{
					char[] temp = new char[size+1];
					for(int j=index+1; j<size+1; j++)
						temp[j] = ans[j-1];
					
					temp[index] = a[i];
					
					for(int j=0; j<index; j++)
						temp[j] = ans[j];
					
					++index;
					ans = temp;
				}
			}
			String k = String.copyValueOf(ans);
			k = k.replaceAll("\0", "");
			System.out.println(k);
		}
		in.close();
	}
}
