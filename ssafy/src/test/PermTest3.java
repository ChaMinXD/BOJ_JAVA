package test;
import java.util.Arrays;

public class PermTest3 {
	static int [] p= {1,2,3,4,5};
	static int N;
	static int R;
	static int count;
	static int[] nums;
	
	public static void main(String[] args) {
		N=p.length;
		R=3;
		count=0;
		nums=new int[R];
		perm(0,0);
		System.out.println(count);
	}
	//0 & (1<<0) = 0 
	//0|(1<<0)=> 1 
	// 1 & (1<<0) =1 x, 1 & (1<<1) = 1 &10 
	// 1|(1<<1)=>  11
	static void perm(int cnt,int v) {
		if(cnt==R) {
			count++;
			System.out.println(Arrays.toString(nums));
			//로직넣을 꺼야
			return ;
		}
		for (int i = 0; i < N; i++) {
			if((v & (1<<i))!=0) continue;
			nums[cnt]=p[i];
			perm(cnt+1, v | (1<<i));
			//nums[cnt]=0;
		}
	}

}