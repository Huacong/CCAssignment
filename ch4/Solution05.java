/* 
4.5 Validate BST:
	Implement a function to check if a binary tree is a binary search tree.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;
	
	public TreeNode left;
	public TreeNode right;

	TreeNode (int val) { this.val = val; }
}

public class Solution05 {
	public static void main(String[] args) {
		System.out.println("4.5 Validate BST");

		test1();
		test2();
	}

	private static void test1() {
		TreeNode root = generateBST();

		System.out.println(validateBST(root));
	}

	private static void test2() {
		TreeNode root = generateNotBST();

		System.out.println(validateBST(root));
	}

	private static TreeNode generateBST() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		n1.left = n0;
		n1.right = n3;
		n3.left = n2;
		n3.right = n4;

		return n1;
	}

	private static TreeNode generateNotBST() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		n1.left = n0;
		n1.right = n3;
		n3.left = n4;
		n3.right = n2;

		return n1;
	}

	/*	Solution
	Analysis:
		Inorder traversal a BST will get a acsending number sequence

	Assumptions:
		No duplicates in the tree

		Traverse the tree in order to check if the number sequence is ascending

		Time: O(n) for traversal
		Space: O(1)
	*/

	private static TreeNode preNode = null;

	private static boolean validateBST(TreeNode root) {
		if (root == null)
			return true;

		//left tree is not BST
		if (!validateBST(root.left))
			return false;

		//Initialize preNode
		if (preNode == null)
			preNode = root;

		//check if in ascending order
		if (preNode.val > root.val)
			return false;

		//update preNode
		preNode = root;

		//right tree
		if (!validateBST(root.right))
			return false;

		return true;
	}
}