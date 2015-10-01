/* 
4.4 Check Balanced: 
	Implement a function to check if a binary tree is balanced. For the purposes
	of this question, a balanced tree is defined to be a tree such that the 
	heights of the two subtrees of any node never differ by more than one.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;
	
	public TreeNode left;
	public TreeNode right;

	TreeNode (int val) { this.val = val; }
}

public class Solution04 {
	public static void main(String[] args) {
		System.out.println("4.4 Check Balanced");

		test1();
		test2();
	}

	private static void test1() {
		TreeNode root = generateTreeBalanced();

		System.out.println(checkBalanced(root));
	}

	private static void test2() {
		TreeNode root = generateTreeUnbalanced();

		System.out.println(checkBalanced(root));
	}

	private static TreeNode generateTreeBalanced() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n1.right = n4;

		return n0;
	}

	private static TreeNode generateTreeUnbalanced() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n3.right = n4;

		return n0;
	}

	/*	Solution

		Recursively get the height of left and right subtree.

		Time: O(n) for traversal
		Space: O(1)
	*/
	private static boolean checkBalanced(TreeNode root) {
		int height = getHeight(root);

		if (height == -1) //unbalanced
			return false;
		else 
			return true;
	}

	private static int getHeight(TreeNode root) {
		if (root == null)
			return 0;

		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);

		int height;
		if (leftHeight == -1 || rightHeight == -1 || //one of the subtree is already unbalanced
			Math.abs(leftHeight - rightHeight) > 1)	 //unbalanced in current height
			return -1;
		else
			return Math.max(leftHeight, rightHeight) + 1;
	}
}