package test;

public class BitSubset {
	static int[] p= {1,2,3,4,5};
	static int N=p.length;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int j=1;j<(1<<N);j++) {
			for(int i=0;i<N;i++) {
				if((j&(1<<i))!=0)
					System.out.print(p[i]+" ");
			}
			System.out.println();
		}
	}

}
