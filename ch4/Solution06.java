/* 
4.6 Successor:
	Write an algorithm to find the "next" node (i.e., in-order successor) of a
	given node in a binary search tree. You may assume that each node has a link
	to its parent.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;

	public TreeNode left;
	public TreeNode right;

	public TreeNode parent;

	TreeNode (int val) { this.val = val; }
}

public class Solution06 {
	public static void main(String[] args) {
		System.out.println("4.6 Successor");
		
		test1();
		test2();
	}

	private static void test1() {
		TreeNode node = generateTreeRightChild();

		TreeNode result = successor(node);

		if (result == null)
			System.out.println("null");
		else
			System.out.println(result.val);
	}

	private static void test2() {
		TreeNode node = generateTreeParent();

		TreeNode result = successor(node);

		if (result == null)
			System.out.println("null");
		else
			System.out.println(result.val);
	}

	private static TreeNode generateTreeRightChild() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		n0.parent = n1;

		n1.left = n0;
		n1.right = n3;

		n3.left = n2;
		n3.right = n4;
		n3.parent = n1;

		n2.parent = n3;

		n4.parent = n3;

		return n1;
	}

	private static TreeNode generateTreeParent() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		n0.parent = n1;

		n1.left = n0;
		n1.right = n3;

		n3.left = n2;
		n3.right = n4;
		n3.parent = n1;

		n2.parent = n3;

		n4.parent = n3;

		return n2;
	}

	/*	Solution

		If the node has right subtree, the most left node will be the successor.
		Otherwise, it will be the first ancestor that has the node in its left
		subtree.

		Time: O(n)
		Space: O(1) 
	*/
	private static TreeNode successor(TreeNode node) {
		if (node == null)
			return node;

		//has right subtree, return the leftmost node of the right subtree
		if (node.right != null) {
			node = node.right;

			while (node.left != null)
				node = node.left;

			return node;
		} else { //return the first ancestor that has the node in its left subtree
			TreeNode parent = node.parent;

			while (parent != null && parent.left != node) {
				node = parent;
				parent = node.parent;
			}

			return parent;
		}
	}
}