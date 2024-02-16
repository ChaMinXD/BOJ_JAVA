package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1991 {
	static int N;
	static ArrayList<String> ans=new ArrayList();
	static StringBuilder sb=new StringBuilder();
	static Node head = new Node('A',null,null);
	static class Node{
		char root;
		Node left;
		Node right;
		Node(char root, Node left, Node right){
			this.root = root;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			char rt=st.nextToken().charAt(0);
			char left=st.nextToken().charAt(0);
			char right=st.nextToken().charAt(0);
			insertNode(head,rt,left,right);
		}
		pre(head);
		System.out.println();
		in(head);
		System.out.println();
		post(head);
	}

	static void insertNode(Node n, char root,char left, char right) {
		if(n.root==root) {
			if(left=='.') {
				n.left=null;
			}
			else {
				n.left=new Node(left,null,null);
			}
			if(right=='.') {
				n.right=null;
			}
			else {
				n.right=new Node(right,null,null);
			}
		}
		else {
			if(n.left!=null) insertNode(n.left, root, left, right);
			if(n.right!=null) insertNode(n.right, root, left, right);
		}
	}
	static void pre(Node n) {
		if(n==null) return;
		System.out.print(n.root);
		pre(n.left);
		pre(n.right);
	}
	static void in(Node n) {
		if(n==null) return;
		in(n.left);
		System.out.print(n.root);
		in(n.right);
	}
	static void post(Node n) {
		if(n==null) return;
		post(n.left);
		post(n.right);
		System.out.print(n.root);
		
	}
}
