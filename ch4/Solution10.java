 /* 
4.10 Check Subtree:
	T1 and T2 are two very large binary trees, with T1 much bigger than T2. 
	Create an algorithm to determine if T2 is a subtree of T1.
	
	A tree T2 is a subtree of T1 if there exists a node n in T1 such that
	the subtree of n is identical to T2. That is, if you cut off at node n,
	the two trees would be identical.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;

	public TreeNode left;
	public TreeNode right;

	TreeNode (int val) { this.val = val; }
}

class Input {
	TreeNode t1;
	TreeNode t2;

	public Input(TreeNode t1, TreeNode t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
}

public class Solution10 {
	public static void main(String[] args) {
		System.out.println("4.10 Check Subtree");

		test();
	}

	private static void test() {
		Input input = generateTree();

		System.out.println(checkSubtree(input.t1, input.t2));
	}

	private static Input generateTree() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);

		n0.left = n1;
		n0.right = n2;

		n1.left = n3;
		n1.right = n4;

		n3.left = n5;

		return new Input(n0, n1);
	}

	/*	Solution
		
	Analysis:
		Preorder and inorder sequence could determine a unique tree. 

		And if a sequence is consective in inorder, it must represent 
		one tree rather than two trees with a common ancestor that is 
		not in the sequence.

	Assumption:
		t1 is the bigger tree, t2 is the smaller one

	Implementation:
		Traverse two trees preorder and inorder, if both the preorder and inorder 
		sequences of t2 are part of t1, then t2 is t1's subtree.

		Time: O(m+n) for traversal, O(m+n) for isSubstring
		Space: O(m+n) for storing traversal sequence.
	*/

	private static boolean checkSubtree(TreeNode t1, TreeNode t2) {
		StringBuilder seq = new StringBuilder();

		//get preorder sequence
		preorderSeq(t1, seq);
		String t1Preorder = seq.toString();

		seq.setLength(0);
		preorderSeq(t2, seq);
		String t2Preorder = seq.toString();

		//check preorder sequence
		if (t1Preorder.indexOf(t2Preorder) == -1)
			return false;

		//get inorder sequence
		seq.setLength(0);
		inorderSeq(t1, seq);
		String t1Inorder = seq.toString();

		seq.setLength(0);
		inorderSeq(t2, seq);
		String t2Inorder = seq.toString();

		//check inorder sequence
		if (t1Inorder.indexOf(t2Inorder) == -1) //not a substring
			return false;

		return true;
	}

	private static void preorderSeq(TreeNode root, StringBuilder seq){
		if (root == null)
			return;

		seq.append(root.val);

		preorderSeq(root.left, seq);

		preorderSeq(root.right, seq);
	}

	private static void inorderSeq(TreeNode root, StringBuilder seq){
		if (root == null)
			return;

		inorderSeq(root.left, seq);

		seq.append(root.val);

		inorderSeq(root.right, seq);
	}
}