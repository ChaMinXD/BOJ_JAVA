package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2457 {
	static int N;
	static class Project {
		int month;
		int day;
		Project(int m,int d){
			month=m;
			day=d;
		}
		
	}
	static class Com implements Comparator<Project[]>{

		@Override
		public int compare(Project[] o1, Project[] o2) {
			if(o1[1].month==o2[1].month&&o1[1].day==o2[1].day) {
				if(o1[0].month==o2[0].month)
					return o1[0].day-o2[0].day;
				else
					return o1[0].month-o2[0].month;
				
			}
			else {
				if(o1[1].month==o2[1].month)
					return o2[1].day-o1[1].day;
				else
					return o2[1].month-o1[1].month;
			}
			
		}
		
	}
	
	static Project[][] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new Project[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int sm=Integer.parseInt(st.nextToken());
			int sd=Integer.parseInt(st.nextToken());
			int em=Integer.parseInt(st.nextToken());
			int ed=Integer.parseInt(st.nextToken());
			list[i][0]=new Project(sm,sd);
			list[i][1]=new Project(em,ed);
		}
		Arrays.sort(list, new Com());
		Project start=new Project(3,1);
		Project end=new Project(12,1);
		int cnt=0;

		Project temp=null;
		for(int i=0;i<N;i++) {
			if(Compare(end,start)<=0) {
				temp=null;
				break;
			}
				
			if(Compare(list[i][1],end)<0) {
				if(temp!=null) {
					end=temp;
					cnt++;
					temp=null;
					i--;
				}
				else {
					cnt=-1;
					temp=null;
					break;
				}
				
			}
			else {
				if(temp==null) {
					temp=new Project(list[i][0].month,list[i][0].day);

				}		
				else if(Compare(temp,new Project(list[i][0].month,list[i][0].day))>0){
					temp=new Project(list[i][0].month,list[i][0].day);

				}
			}	
		}
		if(temp!=null) {
			if(Compare(temp,start)<=0)
				cnt++;
			else
				cnt=-1;
		}
		if(cnt==-1)
			System.out.println(0);
		else
			System.out.println(cnt);
	}
	static int Compare(Project o1,Project o2) {
		if(o1.month==o2.month&&o1.day==o2.day) {
			if(o1.month==o2.month)
				return o1.day-o2.day;
			else
				return o1.month-o2.month;
			
		}
		else {
			if(o1.month==o2.month)
				return o1.day-o2.day;
			else
				return o1.month-o2.month;
		}
		
	}

}
